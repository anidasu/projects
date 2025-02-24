// Title:              AnimalActivities
// Files:              AnimalActivities.java
//
// Author:             Aniruddha Dasu

/**
 * Class with two unique static methods related to animals
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

public class AnimalActivities {

    // Necessary constants
    private final static int NUM_ANIMALS = 6;
    private final static int SPACING = 17;
    private final static int TIE_GAME = 0;
    private final static int ONE_W = 1;
    private final static int TWO_W = 2;
    private final static int MINUS_ONE = -1;
    private final static int OF_AGE = 5;
    private final static int TWO = 2;
    private final static int FULL_HEALTH = 100;
    private final static String LEFT = "Left";
    private final static String RIGHT = "Right";
    
    //Constructor
    private AnimalActivities() {
    }
    
    /**
     * The game where two animals fight
     *
     * @param animal1
     * @param animal2
     * @return the winner
     */
    public static int fight(Animal animal1, Animal animal2) {
        boolean isAnimal1Poisoned = false;
        boolean isAnimal2Poisoned = false;
        
    if (animal1 instanceof Frog) {
        Frog poisonous1 = (Frog) animal1;
        isAnimal1Poisoned = poisonous1.poisonAnimal();
    }
    else if (animal1 instanceof Snake) {
        Snake poisonous1 = (Snake) animal1;
        isAnimal1Poisoned = poisonous1.poisonAnimal();
    }

    if (animal2 instanceof Frog) {
        Frog poisonous2 = (Frog) animal2;
        isAnimal2Poisoned = poisonous2.poisonAnimal();
    }
    else if (animal2 instanceof Snake) {
        Snake poisonous2 = (Snake) animal2;
        isAnimal2Poisoned = poisonous2.poisonAnimal();
    }

    int roundNumber = 0;
    while (animal1.getHealth() > 0 && animal2.getHealth() > 0) {
        printRound(roundNumber);
        printBothAnimals(animal1, animal2);

        int damage1 = (animal1).attack(animal2);
        int damage2 = animal2.attack(animal1);

        printAttack(LEFT, damage1);
        printAttack(RIGHT, damage2);

        roundNumber++;
    }

    if (animal1.getHealth() <= 0 && animal2.getHealth() <= 0) {
        boolean bool = isAnimal1Poisoned || isAnimal2Poisoned;
        printFinalStats(animal1, animal2, bool);
        printTieGame();
        return TIE_GAME;
    }

    if (animal2.getHealth() < 0) {
        if (isAnimal1Poisoned) {
            printFinalStats(animal1, animal2, isAnimal1Poisoned);
            printTieGame();
            return TIE_GAME;
        }
        else {
            if (animal1 instanceof Herbivore) {
                ((Herbivore) animal1).eatPlant();
            }
            else {
                ((Carnivore) animal1).eatAnimal(animal2);
            }
            printFinalStats(animal1, animal2, isAnimal1Poisoned);
            printWinner(LEFT);
            return ONE_W;
        }
    }

    if (animal1.getHealth() < 0) {
        if (isAnimal2Poisoned) {
            printFinalStats(animal1, animal2, isAnimal2Poisoned);
            printTieGame();
            return TIE_GAME;
        }
        else {
            if (animal2 instanceof Herbivore) {
                ((Herbivore) animal2).eatPlant();
            }
            else {
                ((Carnivore) animal2).eatAnimal(animal1);
            }
            printFinalStats(animal1, animal2, isAnimal2Poisoned);
            printWinner(RIGHT);
            return TWO_W;
        }
    }
    return MINUS_ONE;
    }

    /**
     * Checks if two animals are compatible and if they are, they reproduce
     *
     * @param animal1
     * @param animal2
     * @return the baby
     */
    public static Animal reproduce (Animal animal1, Animal animal2) {
        if (animal1.getAge() >= OF_AGE && animal2.getAge() >= OF_AGE 
            && animal1.sameSpecies(animal2)) {
                int babyStrength = (animal1.getStrength() + 
                                    animal2.getStrength()) / TWO;

                if (animal1 instanceof Lion) {
                    Lion baby = new Lion(0, FULL_HEALTH, babyStrength);
                    return baby;
                }
                if (animal1 instanceof Tiger) {
                    Tiger baby = new Tiger(0, FULL_HEALTH, babyStrength);
                    return baby;
                }
                if (animal1 instanceof Elephant) {
                    Elephant baby = new Elephant(0, FULL_HEALTH, 
                                                babyStrength);
                    return baby;
                }
                if (animal1 instanceof Rhino) {
                    Rhino baby = new Rhino(0, FULL_HEALTH, babyStrength);
                    return baby;
                }
                if (animal1 instanceof Snake) {
                    Snake baby = new Snake(0, FULL_HEALTH, babyStrength);
                    return baby;
                }
                if (animal1 instanceof Frog) {
                    Frog baby = new Frog(0, FULL_HEALTH, babyStrength);
                    return baby;
                }
            }
        return new Animal();
    }

    /* Below are helper methods to make fight() work */
/**
* Use this method in fight() to display the stats of both animals together
*
* @param (animal1) Animal on the left side to display stats
* @param (animal2) Animal on the right side to display stats
*/
public static void printBothAnimals(Animal animal1, Animal animal2) {
    int ageSpacing = calcSpacing(Integer.toString(animal1.getAge()));
    int healthSpacing = calcSpacing(Integer.toString(animal1.getHealth()));
    int strSpacing = calcSpacing(Integer.toString(animal1.getStrength()));
    int animalSpacing = calcSpacing(animal1.getClass().getName());
    String str = "(" + animal1.getClass().getName() + ")" +
    " ".repeat(animalSpacing) + "(" +
    animal2.getClass().getName() + ")\n" +
    "----------" + " " + "----------\n" +
    "A: " + animal1.getAge() + " ".repeat(ageSpacing) +
    "A: " + animal2.getAge() + "\n" +
    "H: " + animal1.getHealth() + " ".repeat(healthSpacing) +
    "H: " + animal2.getHealth() + "\n" +
    "S: " + animal1.getStrength() + " ".repeat(strSpacing) +
    "S: " + animal2.getStrength() + "\n";
    System.out.println(str);
    }
    /**
    * Helper method for printBothAnimals()
    *
    * @param (str) String on the left - used to calculate spacing
    * @return An int describing how many spaces to put between strings
    */
    public static int calcSpacing(String str) {
    int totalWidth = SPACING;
    int str1Width = str.length();
    int spacing = (totalWidth - str1Width);
    if (spacing < 0) {
    return 0;
    }
    return spacing;
    }
    /**
    * Use this method in fight() to display the current round.
    * @param (round) An int of the round (should start at 0)
    */
    public static void printRound(int round) {
    System.out.println();
    System.out.println("Round " + round + ":");
    }
    /**
    * Use this method in fight() to display the damage each round.
    *
    * @param (side) The side of the Animal that invoked the attack().
    * @param (damage) The int (damage) returned from an attack() call
    */
    public static void printAttack(String side, int damage) {
    System.out.println(side + " does " + damage + " damage!");
    }
    /**
    * Use this method in fight() to display final stats and poison status.
    *
    * @param (animal1) Left animal
    * @param (animal2) Right animal
    * @param (poisoned) If either animal was poisoned
    */
    public static void printFinalStats(Animal animal1, Animal animal2,
    boolean poisoned) {
    System.out.println();
    printBothAnimals(animal1, animal2);
    if (poisoned) {
    System.out.println("An animal was poisoned.");
    }
    }
    /**
    * Use this method in fight() to display a tie game.
    */
    public static void printTieGame() {
    System.out.println("-------GAME OVER-------");
    System.out.println("TIE: Both animals died!");
    }
    /**
    * Use this method in fight() to display the winner.
    * @param (side) The side of the Animal that won.
    */
    public static void printWinner(String side) {
    System.out.println("-------GAME OVER-------");
    System.out.println(side + " animal wins!");
    }
    
}
