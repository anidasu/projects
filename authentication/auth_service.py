import logging
import bcrypt
import os
from datetime import datetime, timedelta
import hashlib
import mysql.connector
# from db import get_db_connection

# Configure logging
logger = logging.getLogger()

db_config = {
    'user': 'root',
    'password': 'P@tronus14',
    'host': '127.0.0.1',
    'port': 3306,
    'database': 'merchantportal'
}

def get_db_connection():
    try:
        conn = mysql.connector.connect(**db_config)
        return conn
    except mysql.connector.Error as err:
        logger.error(f"Error: {err}")
        return None

def hash_password(password):
    # Generate a random salt
    salt = os.urandom(16).hex()
    # Hash the password with the salt using SHA-256
    hashed_pw = hashlib.sha256(salt.encode() + password.encode()).hexdigest()
    return salt, hashed_pw

def add_user(username, password):
    salt, hashed_password = hash_password(password)

    # Establish a connection to the database
    conn = mysql.connector.connect(
        host='127.0.0.1',
        user='root',
        password='P@tronus14',
        database='merchantportal'
    )
    cursor = conn.cursor()

    # Insert the new user with the hashed password and salt
    try:
        cursor.execute(
            "INSERT INTO users (username, password, salt) VALUES (%s, %s, %s)",
            (username, hashed_password, salt)
        )
        conn.commit()
        print(f"User {username} added successfully.")
    except mysql.connector.Error as err:
        print(f"Error: {err}")
    finally:
        cursor.close()
        conn.close()

def verify_password(stored_password, salt, provided_password):
    hashed_pw = hashlib.sha256(salt.encode() + provided_password.encode()).hexdigest()
    return hashed_pw == stored_password

def check_auth(username, password):
    conn = mysql.connector.connect(
        host='127.0.0.1',
        user='root',
        password='P@tronus14',
        database='merchantportal'
    )
    cursor = conn.cursor(dictionary=True)
    cursor.execute("SELECT password, salt FROM users WHERE username = %s", (username,))
    user = cursor.fetchone()
    cursor.close()
    conn.close()

    if user and verify_password(user['password'], user['salt'], password):
        return True
    return False

username = "user3@yahoo.com"
password = "P@ssword3"
add_user(username, password)


def update_otp(username, otp, expiry):
    conn = get_db_connection()
    if conn is None:
        logger.error("Failed to connect to database for updating OTP")
        return False

    cursor = conn.cursor()
    cursor.execute(
        "UPDATE users SET otp = %s, otp_expiry = %s WHERE username = %s",
        (otp, expiry, username)
    )
    conn.commit()
    cursor.close()
    conn.close()
    return True

def generate_otp():
    import random, string
    return ''.join(random.choices(string.digits, k=4))

def verify_otp_in_db(username, otp):
    conn = get_db_connection()
    if conn is None:
        logger.error("Failed to connect to database for OTP verification")
        return None, "Database connection failed"

    cursor = conn.cursor(dictionary=True)
    cursor.execute("SELECT otp, otp_expiry FROM users WHERE username = %s", (username,))
    user = cursor.fetchone()
    cursor.close()
    conn.close()

    if user:
        return user, None
    return None, "User not found"

def store_or_update_otp(email, otp):
    try:
        conn = get_db_connection()
        cursor = conn.cursor()

        # Check if the email already has an OTP entry
        cursor.execute("SELECT id FROM otp_storage WHERE email = %s", (email,))
        result = cursor.fetchone()

        if result:
            # Update existing OTP entry
            cursor.execute("""
                UPDATE otp_storage 
                SET otp = %s, timestamp = %s 
                WHERE email = %s
            """, (otp, datetime.datetime.now(), email))
        else:
            # Insert new OTP entry
            cursor.execute("""
                INSERT INTO otp_storage (email, otp, timestamp)
                VALUES (%s, %s, %s)
            """, (email, otp, datetime.datetime.now()))

        conn.commit()
    except mysql.connector.Error as err:
        logging.error(f"Database error: {err}")
        raise
    finally:
        cursor.close()
        conn.close()
