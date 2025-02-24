// Title:              Rhino
// Files:              Rhino.java
//
// Author:             Aniruddha Dasu

/**
 * Subclass of Animal to represent Rhinos
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

import java.util.Random;

public class Rhino extends Animal implements Herbivore {

    private final static int FIFTEEN = 15;
    private final static int TWENTY_FIVE = 25;
   
    //no-arg constructor
    public Rhino() {
        super();
    }

    /**
     * Constructor to set the age, health, and strength in its superclass
     * 
     * @param age age
     * @param health health
     * @param strength strength
     */
    public Rhino(int age, int health, int strength) {
        super(age, health, strength);
    }

    /**
     * Method representing strength gain during sleep
     * 
     * @return void
     */
    @Override
    public void sleep() {
        setStrength(getStrength() + FIFTEEN);
    }

    /**
     * Method representing strength gain by eating plants
     * 
     * @return void
     */
    @Override
    public void eatPlant() {
        Random random = new Random();
        int randomGain = (int) (random.nextDouble() * TWENTY_FIVE);
        setStrength(randomGain + getStrength());
    }
}
