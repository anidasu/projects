// Title:              Assignment8
// Files:              Assignment8.java
//
// Author:             Aniruddha Dasu

/**
 * Class with unit tests for various methods in AnimalActivities and 
 * other classes
 *
 * Bugs: 
 *
 * @author Aniruddha Dasu
 */

public class Tester {
    
    /**
     * A tester for the methods
     * 
     * @return return true if the tester passes
     */
    public static boolean unitTests() {

        //eatAnimal()
        Tiger tiger = new Tiger(10, 100, 30);
        Lion lion = new Lion(4, 100, 30);
        tiger.eatAnimal(lion);
       if (tiger.getStrength() != 40) {
            return false;
       } 

       //sleep()
       Elephant elephant = new Elephant(7, 100, 70);
       elephant.sleep();
       if (elephant.getStrength() != 80) {
            return false;
       }

       //poisonAnimal()
       Snake snake = new Snake(5, 100, 20);
       boolean poisoned = snake.poisonAnimal();
       if (!(poisoned == true || poisoned == false)) {
            return false;
       }

       //fight()
       Lion lion2 = new Lion(1, 100, 80);
       Frog frog = new Frog(4, 100, 70);
       int winner = AnimalActivities.fight(lion2, frog);
       if (!(winner == 1 || winner == 2 || winner == 3)) {
            return false;
       }

       //reproduce()
       Elephant peanutDad = new Elephant(6, 100, 90);
       Elephant peanutMom = new Elephant(6, 90, 100);
       Animal peanut = AnimalActivities.reproduce(peanutDad, peanutMom);
       if (!((peanut instanceof Elephant) && peanut.getAge() == 0 
            && peanut.getHealth() == 100 && peanut.getStrength() == 95)) {
                return false;
            }

    
    return true;

        }

    /**
     * The main method, where program execution begins.
     * 
     * @param args Any command-line arguments.
     */
    public static void main(String[] args) {
        boolean finalResult = unitTests();
        System.out.println(finalResult);
    }
}
