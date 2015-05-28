
/**
 * Tests the BaseballPlayer class by creating a BaseballPlayer object
 * 
 * @author Nick Alexander
 * @version 2/13/13
 */
public class BaseballPlayerTest
{
    /**
     * Creates BaseballPlayer Objects
     * @param Run-Time arguments
     */

    public static void main(String[] args)
    {
        //Creates David Oritz object
        BaseballPlayer ortiz = new BaseballPlayer("David Ortiz", "first base");
        System.out.println("Name: " + ortiz.getName() + "; " + "Position: " + ortiz.getPosition() + "; " + "Years: " + ortiz.getYears());
        ortiz.setYears(15);
        System.out.println("Name: " + ortiz.getName() + "; " + "Position: " + ortiz.getPosition() + "; " + "Years: " + ortiz.getYears());
        
        //Creates Kevin Youkilis object
        BaseballPlayer kevin = new BaseballPlayer("Kevin Youkilis", "third base");
        kevin.setYears(9);
         System.out.println("Name: " + kevin.getName() + "; " + "Position: " + kevin.getPosition() + "; " + "Years: " + kevin.getYears());
    }
}
