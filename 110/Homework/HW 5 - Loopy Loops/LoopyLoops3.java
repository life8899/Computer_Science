
/**
 * Using While Loops
 * 
 * @author Nick Alexander
 * @version 3/10/13
 */

import java.util.Scanner;

public class LoopyLoops3
{
    public static void main(String[] args)
    {
        //1
        int x = 0;
        while (x < 10)
        {
            x++;
            System.out.println(""+x);
        }
        System.out.println("");
        
        //2
        while (x > 0)
        {
            System.out.println(""+x);
            x--;
        }
        System.out.println("");
        
        //3
        Scanner scanner = new Scanner(System.in);
        System.out.println("Value: ");
        int max = Integer.parseInt(scanner.nextLine());
        int i = 1;
        while (Math.pow(2, i) < max)
        {
            System.out.print(Math.pow(2, i) + " ");  
            i++;
        }
    }
}
