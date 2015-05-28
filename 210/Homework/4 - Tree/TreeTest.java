import java.util.Scanner;

public class TreeTest
{
    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        int choice = 0;
        do
        {
            System.out.println("1 - Add an Element\n" + "2 - Delete an Element\n" + "3 - Find an Element\n" + "4 - Traverse In Order\n" + "5 - Quit");
            
            System.out.print("Choice: ");
            
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid Input!");
            }
            
            System.out.println();
            
            String data;
            switch (choice)
            {
                case 1:
                    System.out.print("Element to Add: ");
                    data = scanner.nextLine();
                    if (tree.add(data))
                        System.out.println("Added Element!");
                    else
                        System.out.println("Did Not Add Element");
                    break;
                case 2:
                    System.out.print("Delete Element: ");
                    data = scanner.nextLine();
                    if (tree.delete(data) != null)
                        System.out.println("Deleted " + data);
                    else
                        System.out.println(data + " Not Deleted");
                    break;
                case 3:
                    System.out.print("Find: ");
                    data = scanner.nextLine();
                    if (tree.find(data) != null)
                        System.out.println("Found " + data + "!");
                    else
                        System.out.println(data + " Not Found!");
                    break;
                case 4:
                    tree.traverseInOrder();
                    break;
                case 5:
                    done = true;
                    break;
                default: System.out.println("Invalid Input!");
            }
            System.out.println();
        } while (!done);
        System.exit(0);
    }
}