// Title:              Tiger
// Files:              Tiger.java
//
// Author:             Aniruddha Dasu

/**
 * Subclass of Animal to represent Tigers
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

public class Tiger extends Animal implements Carnivore {

    private final static int FIFTEEN = 15;
    private final static int THREE = 3;

    //no-arg constructor
    public Tiger() {
        super();
    }

    /**
     * Constructor to set the age, health, and strength in its superclass
     * 
     * @param age age
     * @param health health
     * @param strength strength
     */
    public Tiger(int age, int health, int strength) {
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
     * Method representing strength gain by eating Animals
     * 
     * @return void
     */
    @Override
    public void eatAnimal(Animal animal) {
        int strengthGained = (animal.getStrength() / THREE);
        setStrength(strengthGained + getStrength());
    }
}
