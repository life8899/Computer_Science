
/**
 * Uses loops to run the program until users promptly terminates
 * 
 * @author Nick Alexander
 * @version 3/8/13
 */

import java.util.Scanner;

public class UserMenu
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        do
        {
            System.out.println("1 - Compute Square\n2 - Compute Square Root\n3 - Compute Cube\n4 - Exit");
            System.out.println("What would you like to do? ");
            int choice = Integer.parseInt(scanner.nextLine());
            
            if (choice == 1)
            {
                System.out.println("\nEnter a number");
                Double x = Double.parseDouble(scanner.nextLine());
                System.out.println("The square of your number is " + x*x + "\n");
            }
            else if (choice == 2)
            {
                System.out.println("\nEnter a number");
                Double y = Double.parseDouble(scanner.nextLine());
                System.out.println("The square root of your number is " + Math.sqrt(y)+"\n");
            }
            else if (choice == 3)
            {
                System.out.println("\nEnter a number");
                Double z = Double.parseDouble(scanner.nextLine());
                System.out.println("The cube of your number is " + z*z*z + "\n");
            }
            else if (choice == 4)
            {
                System.out.println("Goodbye!");
                done = true;
            }
            else
            {
                System.out.println("\nInvalid Input\n");
            }
        }while(!done);
        System.exit(0);
    }

}
