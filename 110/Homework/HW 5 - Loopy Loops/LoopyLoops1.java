
/**
 * Using Do..While loops
 * 
 * @author Nick Alexander
 * @version 3/10/13
 */

public class LoopyLoops1
{
    public static void main(String[] args)
    {
        int x = 0;
        do
        {
            x++;
            System.out.println("" + x);
        } while (x < 10);
        System.out.println("");
        do
        {
            System.out.println("" + x);
            x--;
        } while (x > 0);
    }
}
