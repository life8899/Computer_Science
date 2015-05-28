
/**
 * Tests the MUStack Class
 * 
 * @author Nick Alexander
 * @version 10/21/13
 */

import java.util.Scanner;

public class StackTest
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        MUStack stack = new MUStack();
        boolean done = false;
        
        do
        {
            System.out.print("1 - Push\n" + "2 - Pop\n" + "3 - Peek\n" + "4 - Empty\n" + "5 - Exit\n");
            int choice = scanner.nextInt();
            if (choice == 1)
            {
                System.out.print("Enter a Character: ");
                Character tmp = scanner.next().charAt(0);
                stack.push(tmp);
                System.out.println("Pushed: " + tmp);
            }
            else if (choice == 2)
            {
                Character tmp = stack.pop();
                System.out.println("Poppped: " + tmp);
            }
            else if (choice == 3)
            {
                Character tmp = stack.peek();
                System.out.println("Peeked: " + tmp);
            }  
            else if (choice == 4)
            {
                System.out.println("The Stack is Empty: " + stack.empty());
            }  
            else if (choice == 5)
            {
                done = true;
                System.out.println("Goodbye!");
            }  
            else
            {
                System.out.println("Invalid Entry, Try Again!");
            }  
            System.out.println();
        } while (!done);
        System.exit(0);
    }
}
