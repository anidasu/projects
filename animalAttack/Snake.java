// Title:              Snake
// Files:              Snake.java
//
// Author:             Aniruddha Dasu

/**
 * Subclass of Animal to represent Snakes
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

import java.util.Random;

public class Snake extends Animal implements Poisonous {

    private final static int FIFTEEN = 15;
    private final static double FORTY_PERCENT = 0.4;
    
    //no-arg constructor
    public Snake() {
        super();
    }

    /**
     * Constructor to set the age, health, and strength in its superclass
     * 
     * @param age age
     * @param health health
     * @param strength strength
     */
    public Snake(int age, int health, int strength) {
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
     * Method representing strength gain by eating an animal
     * 
     * @return void
     */
    public void eatAnimal(Animal animal) {
        setStrength(getStrength() + animal.getStrength());
    }

    /**
     * Method for the other animal getting poisoned
     * 
     * @return boolean value
     */
    @Override
    public boolean poisonAnimal() {
        Random random = new Random();
        return random.nextDouble() >= FORTY_PERCENT;
    }
}
