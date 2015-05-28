
/**
 * Uses Stacks for Palindromes
 * 
 * @author Nick Alexander
 * @version 10/14/13
 */

import java.util.*;

public class Palindrome
{
    public static void main (String[] args)
    {
        String s;
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("Enter a String or q to Quit");
            s = scanner.nextLine();
            if (s.equalsIgnoreCase("Q"))
                done = true;
            else
            {
                if (isPalindrome(s))
                    System.out.println(s + " is a palindrome!");
                else
                    System.out.println(s + " is not a palindrome!");
            }
        } while (!done);
        System.exit(0);
    }
    
    /**
    public static boolean isPalindrome(String s)
    {
        Stack<Character> charStack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++)
            charStack.push(s.charAt(i));
        String reversed = "";
        while (!charStack.empty())
            reversed = reversed + charStack.pop();
        if (reversed.equalsIgnoreCase(s))
            return true;
        else
            return false;
    }
    **/
   
   //Challenge Problem
   public static boolean isPalindrome(String s)
    {
        //Stack<Character> charStack = new Stack<Character>();
       MyStack charStack = new MyStack();
        for (int i = 0; i < s.length(); i++)
            charStack.push(s.charAt(i));
        String reversed = "";
        while (!charStack.empty())
        {
            if (!Character.isLetter(charStack.peek()))
                charStack.pop();
            else
                reversed = reversed + charStack.pop();
        }
        
        // \W expression means all "non-word" characters
        // Defined in Pattern Class
        if (reversed.equalsIgnoreCase(s.replaceAll("\\W", "")))
            return true;
        else
            return false;
    }
}
