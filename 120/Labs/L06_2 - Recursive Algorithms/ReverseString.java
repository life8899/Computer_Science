
/**
 * Reverses a String using a Recursive Algorithm
 * 
 * @author Nick Alexander
 * @version 10/2/13
 */

import java.util.Scanner;

public class ReverseString
{
    public static String reverse(String s)
    {
        int end = s.length() - 1;
        if (end == 0)
            return s;
        else
            return s.charAt(end) + reverse(s.substring(0, end));        
    }
    
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("String = ");
        String s = scanner.nextLine();
        System.out.println("Reversed = " + reverse(s));
    }
}
