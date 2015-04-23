
/**
 * Uses various methods associated with Array Lists
 * 
 * @author Nick Alexander
 * @version 4/8/13
 */

import java.util.*;

public class MemberList
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> members = new ArrayList<String>();
        boolean done = false;
        do
        {
            System.out.print("1 - Add a new member;" + " 2 - Delete by name;" + " 3 - Display All;" + " 4 - Quit\n");
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == 1)
            {
                System.out.println("Enter name to add: ");
                String newMember = scanner.nextLine();
                members.add(newMember);
                System.out.println("Name added\n");
            }
            else if (answer == 2)
            {
                System.out.println("Enter name to delete: ");
                String deleteMember = scanner.nextLine();
                if (members.contains(deleteMember) == false)
                {
                    System.out.println("No such name!");
                }
                else
                {
                    members.remove(deleteMember);
                    System.out.println("Name removed\n");
                }    
            }
            else if (answer ==3)
            {
                if (members.size() == 0)
                {
                    System.out.println("No names!\n");
                }
                else
                {
                    for (int i = 0; i < members.size(); i++)
                    {
                        System.out.println(members.get(i));    
                    }
                    System.out.println("");
                }
            }
            else if (answer == 4)
            {
                System.out.println("Goodbye");
                done = true;
            }
            else
            {
                System.out.println("Invalid Input!");
            }
        } while (done != true);
        
        System.exit(0);
    }
}
