// Title:              Animal
// Files:              Animal.java
//
// Author:             Aniruddha Dasu


/**
 * Class that initializes the core characteristics of an Animal and defines 
 * the default behavior of specific methods
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

public class Animal {
    private int age;
    private int health;
    private int strength;

    /**
     * No-arg constructor.
     */
    public Animal() {
        this.age = 0;
        this.health = 0;
        this.strength = 0;
    }

    /**
     * Constructor method for Animal
     *
     * @param age the age
     * @param health the health
     * @param strength the strength
     * @return does not return anything
     */
    public Animal(int age, int health, int strength) {
        this.age = age;
        this.health = health;
        this.strength = strength;
    }

    /**
     * Getter method for age
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter method for health
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Getter method for strength
     *
     * @return strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Setter method for health
     *
     * @param health
     * @return health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Setter method for strength
     *
     * @param strength
     * @return strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Checks if two species are the same
     * 
     * @param animal 
     * @return a boolean value
     */
    public boolean sameSpecies(Animal animal) {
        return (this.getClass().getName().equals(animal.getClass().getName()));
    }

    /**
     * Method to attack another animal
     *
     * @param animal
     * @return attack value
     */
    public int attack(Animal animal) {
        int attackRand = (int) ((Math.random()) * strength) + 1;
        animal.setHealth(animal.getHealth() - attackRand);
        return attackRand;
    }


    /**
     *  Method should return the string representation of the Animal object
     *
     * @return the string representation of the Animal object
     */
    @Override
    public String toString() {
        return "(" + getClass().getName() + ")" + " age: " + getAge() +
                "; health: " + getHealth() + "; strength: " + getStrength();
    }

    /**
     * To be overridden by subclasses that extend from Animal.
     *
     * @return void
     */
    public void sleep() {
    }
}
