
/**
 * Compares literal strings with user input strings
 * 
 * @author Nick Alexander
 * @version 3/4/13
 */

import java.util.Scanner;

public class CompareStrings
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Can I, Dad? Huh? Can I??");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("Sure"))
        {
            System.out.println("Yeaaayy!!");
        }
        else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No"))
        {
            System.out.println("Awww man, that sucks!!");
        }
        else
        {
            System.out.println("Huh?");
        }
        System.exit(0);        
    }
}