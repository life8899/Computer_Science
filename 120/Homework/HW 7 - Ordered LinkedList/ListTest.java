/**
 * Tests the Ordered Linked List Class
 * 
 * @author Nick Alexander
 * @version 11/1/13
 */

import java.util.Scanner;

public class ListTest
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        OrderedLinkedList<String> list = new OrderedLinkedList<String>();
        Scanner scanner = new Scanner(System.in);
        String text = "";
        boolean done = false;
        do
        {
            System.out.print("1 - Add a String to the List\n" + "2 - Remove a String from the List\n" + "3 - Display the List\n" + "4 - Exit\n");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1)
            {
                System.out.print("String to Add: ");
                text = scanner.nextLine();
                list.add(text);
                
            }
            else if (choice == 2)
            {   
                System.out.print("String to Remove: ");
                text = scanner.nextLine();
                list.remove(text);
            }

            else if (choice == 3)
                System.out.println(list.toString());
            else if (choice == 4)
                done = true;
            else
                System.out.println("Invalid Entry!");
        } while (!done);
        System.out.println("Goodbye");
        System.exit(0);
    }
}
