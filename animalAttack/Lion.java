// Title:              Lion
// Files:              Lion.java
//
// Author:             Aniruddha Dasu

/**
 * Subclass of Animal to represent Lions
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

public class Lion extends Animal implements Carnivore {

    private final static int TWENTY = 20;
    private final static int TWO = 2;

    //no-arg constructor
    public Lion() {
        super();
    }

    /**
     * Constructor to set the age, health, and strength in its superclass
     * 
     * @param age age
     * @param health health
     * @param strength strength
     */
    public Lion(int age, int health, int strength) {
        super(age, health, strength);
    }

    /**
     * Method representing strength gain during sleep
     * 
     * @return void
     */
    @Override
    public void sleep() {
        setStrength(getStrength() + TWENTY);
    }

    /**
     * Method representing strength gain by eating Animals
     * 
     * @return void
     */
    @Override
    public void eatAnimal(Animal animal) {
        int strengthGained = (animal.getStrength() / TWO);
        setStrength(strengthGained + getStrength());
    }
}
