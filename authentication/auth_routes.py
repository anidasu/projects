from flask import Blueprint, request, jsonify
from functools import wraps
from datetime import datetime, timedelta
import logging
from authentication.auth_service import check_auth, generate_otp, update_otp, verify_otp_in_db

auth_bp = Blueprint('auth', __name__)

# Configure logging
logging.basicConfig(level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger()

def authenticate():
    return jsonify({"message": "Authentication required"}), 401

def requires_auth(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        auth = request.authorization
        if not auth or not check_auth(auth.username, auth.password):
            return authenticate()
        return f(*args, **kwargs)
    return decorated

@auth_bp.route('/login', methods=['POST'])
def login():
    try:
        data = request.get_json()
        if not data:
            logger.warning("No JSON data received")
            return jsonify({"message": "Invalid JSON"}), 400

        username = data.get('username')
        password = data.get('password')

        if not username or not password:
            logger.warning("Username or password not provided")
            return jsonify({"message": "Username and password required"}), 400

        otp = generate_otp()
        otp_expiry = datetime.now() + timedelta(minutes=5)
        if update_otp(username, otp, otp_expiry):
            logger.info(f"Generated OTP for {username}: {otp}")
            return jsonify({"message": "Login successful"}), 200
        else:
            logger.error("Failed to update OTP in the database")
            return jsonify({"message": "Failed to generate OTP"}), 500

    except Exception as e:
        logger.error(f"Exception: {e}")
        return jsonify({"message": "An error occurred"}), 500

@auth_bp.route('/verify-otp', methods=['POST'])
@requires_auth
def verify_otp():
    data = request.get_json()
    otp = data.get('otp')
    auth = request.authorization
    username = auth.username

    if not otp:
        return jsonify({"message": "OTP is required"}), 400

    user, error_message = verify_otp_in_db(username, otp)
    if error_message:
        return jsonify({"message": error_message}), 500 if error_message == "Database connection failed" else 404

    stored_otp = user['otp']
    otp_expiry = user['otp_expiry']
    if stored_otp == otp and datetime.now() < otp_expiry:
        return jsonify({"message": "OTP verified successfully"}), 200
    else:
        return jsonify({"message": "Invalid or expired OTP"}), 400
