// Title:              Frog
// Files:              Frog.java
//
// Author:             Aniruddha Dasu

/**
 * Subclass of Animal to represent Frogs
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

import java.util.Random;

public class Frog extends Animal implements Poisonous {

    private final static int TEN = 10;
    private final static int FOUR = 4;
    private final static double TWENTY_PERCENT = 0.2;

    //no-arg constructor
    public Frog() {
        super();
    }

    /**
     * Constructor to set the age, health, and strength in its superclass
     * 
     * @param age age
     * @param health health
     * @param strength strength
     */
    public Frog(int age, int health, int strength) {
        super(age, health, strength);
    }

    /**
     * Method representing strength gain during sleep
     * 
     * @return void
     */
    @Override
    public void sleep() {
        setStrength(getStrength() + TEN);
    }

    /**
     * Method representing strength gain by eating a bug
     * 
     * @return void
     */
    public void eatAnimal(Animal animal) {
        int strengthGained = (animal.getStrength() / FOUR);
        setStrength(strengthGained + getStrength());
    }

    /**
     * Method for the other animal getting poisoned
     * 
     * @return boolean value
     */
    @Override
    public boolean poisonAnimal() {
        Random random = new Random();
        return random.nextDouble() >= TWENTY_PERCENT;
    }
}
