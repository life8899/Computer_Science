
/**
 * Accepts and Integer and prints Positive, Negative, or Zero
 * 
 * @author Nick Alexander
 * @version 11/1/13
 */

import java.util.Scanner;

public class QE1
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int num = 0;
        String state = "";
        for (int i = 0; i < 5; i++)
        {
            System.out.print("Enter an Integer: ");
            num = scanner.nextInt();
            if (num == 0)
               state = "Zero";
            if (num > 0)
                state = "Positive";
            if (num < 0)
                state = "Negative";
            System.out.println("The number is " + state);  
        }
    }
}
