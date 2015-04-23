
/**
 * Quick Excercise 3
 * Accepts taxable and Prints Income Tax
 * 1913 U.s. Income Tax Schedule Data
 * 
 * @author Nick Alexander
 * @version 11/6/13
 */

import java.util.Scanner;

public class QE3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        double taxable = 0;
        double tax = 0;
        String input = "";
        do
        {
            System.out.print("Enter taxable or 'Q' to Quit: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q"))
            {
                done = true;
                break;
            }
           
            try
            {
                taxable = Double.parseDouble(input);
                tax = 0;
                // 1% on 50,000
                // 2% on < 75,000
                // 3% < 100,000
                // 4% < 250,000
                // 5% < 500,000
                // 6% > 500,000
                if (taxable >= 500000)
                {
                    tax += taxable * .06;
                    taxable = 500000;
                }
                else if (taxable <= 500000 && taxable > 250000)
                {
                    tax += taxable * .05;
                    taxable = 250000;
                }
                else if (taxable <= 250000 && taxable > 100000)
                {
                    tax += taxable * .04;
                    taxable = 100000;
                }
                else if (taxable <= 100000 && taxable > 75000)
                {
                    tax += taxable * .03;
                    taxable = 75000;
                }
                else if (taxable <= 75000 && taxable > 50000)
                {
                    tax += taxable * .02;
                    taxable = 50000;
                }
                else if (taxable <= 50000)
                {
                    tax += taxable * .01;
                    taxable -= tax;
                }
                System.out.println("The tax is: " + tax);
            } catch (Exception e)
            {
                System.out.println("Invalid taxable!");
            }
        } while (!done);
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
