
/**
 * Receives user input and converts name to upper case
 * 
 * @author Nick Alexander
 * @version 2/23/13
 */

import java.util.Scanner;

public class Name
{
    public static void main(String[] args)
    {
        System.out.println("Enter your first name: ");
        Scanner myScanner = new Scanner(System.in);
        String firstName = myScanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = myScanner.nextLine();
        System.out.println(lastName.toUpperCase() + ", " + firstName.toUpperCase());
    }
}
