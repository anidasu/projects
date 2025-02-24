// Title:              Elephant
// Files:              Elephant.java
//
// Author:             Aniruddha Dasu

/**
 * Subclass of Animal to represent Elephants
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

import java.util.Random;

public class Elephant extends Animal implements Herbivore {

    private final static int TEN = 10;
    private final static int FORTY = 40;

    //no-arg constructor
    public Elephant() {
        super();
    }

    /**
     * Constructor to set the age, health, and strength in its superclass
     * 
     * @param age age
     * @param health health
     * @param strength strength
     */
    public Elephant(int age, int health, int strength) {
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
     * Method representing strength gain by eating plants
     * 
     * @return void
     */
    @Override
    public void eatPlant() {
        Random random = new Random();
        int randomGain = (int) (random.nextDouble() * FORTY);
        setStrength(randomGain + getStrength());
    }
}
