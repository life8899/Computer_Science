
/**
 * Tests the MUQueue Class
 * 
 * @author Nick Alexander
 * @version 10/21/13
 */

import java.util.Scanner;

public class QueueTest
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        MUQueue queue = new MUQueue();
        boolean done = false;
        
        do
        {
            System.out.print("1 - Add\n" + "2 - Remove\n" + "3 - Peek\n" + "4 - Empty\n" + "5 - Exit\n");
            int choice = scanner.nextInt();
            if (choice == 1)
            {
                System.out.print("Enter a String: ");
                String tmp = scanner.next();
                queue.enqueue(tmp);
                System.out.println("Added: " + tmp);
            }
            else if (choice == 2)
            {
                String tmp = queue.dequeue();
                System.out.println("Removed: " + tmp);
            }
            else if (choice == 3)
            {
                String tmp = queue.peek();
                System.out.println("Peeked: " + tmp);
            }  
            else if (choice == 4)
            {
                System.out.println("The queue is Empty: " + queue.empty());
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
