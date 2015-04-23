
/**
 * Tests the Dog class which implements Pet
 * 
 * @author Nick Alexander
 * @version 9/25/13
 */

public class DogTest
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        Dog fifi = new Dog("FiFi", "French Poodle", 12.3);
        System.out.println(fifi.toString());
        fifi.setWeight(14.2);
        
        Dog lulu = new Dog("LuLu", "French Poodle", 14.2);
        
        if (lulu.equals(fifi))
            System.out.println("The dogs are equal!");
        else
            System.out.println("Nope, not equal!");
    }
}
