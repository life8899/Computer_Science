
/**
 * Computes Pi using a Series
 * 
 * @author Nick Alexander
 * @version 11/8/13
 */

import java.util.Scanner;

public class QE4
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        do
        {
            System.out.print("Enter the number of terms or q to quit: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q"))
            {
                done = true;
            }
            else
            {
                int n = Integer.parseInt(input);
                double pi = 0;
                /**
                while (k < n)
                {
                    pi = pi + Math.pow(-1, k+1)/(2*k-1);
                    k++;
                }
                */
               
               for (int k = 1; k < n; k++)
               {
                   pi = pi + Math.pow(-1, k+1)/(2*k-1);
               }
               
                pi = 4 * pi;
                System.out.println("n: " + n + " pi: " + pi);
            }
        } while (!done);
        System.out.println("Goodbye!");
    }
}
