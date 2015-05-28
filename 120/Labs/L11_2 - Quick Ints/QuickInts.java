
/**
 * Quick Excercise 2
 * Accts Ints and Prints the Square until User Quits
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class QuickInts
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        String input = "";
        do
        {
            System.out.print("Type Q to quit, or Integer: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q"))
            {
                System.out.println("Goodbye!");
                done = true;
            }
            else
            {
                System.out.println(Math.sqrt(Integer.parseInt(input)));
            }
        } while (!done);
        System.exit(0);
    }
}
