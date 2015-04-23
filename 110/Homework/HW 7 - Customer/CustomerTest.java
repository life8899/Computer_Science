import java.util.*;

/**
 * Instantiates Customer objects and stores them in an ArrayList
 * 
 * @author Nick Alexander
 * @version 4/8/13
 */
public class CustomerTest
{
    /** main method
     * @param args Run-Time arguments
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Customer> customers = new ArrayList<Customer>();
        boolean done = false;
        do
        {
            System.out.println("1) Add a new customer\n" + "2) Print all customers (names and sales)\n" + "3) Computer and print total sales\n" + "4) Quit");
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == 1)
            {
                System.out.print("Name of Customer: ");
                String name = scanner.nextLine();
                System.out.print("Sales Amount: ");
                double sales = Double.parseDouble(scanner.nextLine());
                customers.add(new Customer(name, sales));
                System.out.println("Customer added\n");  
            }
            else if (answer == 2)
            {
                if (customers.size() == 0)
                {
                    System.out.println("No customers");
                }
                else
                {
                    int s = 0;
                    System.out.println("Name | Sales");
                    for (int i = 0; i < customers.size(); i++)
                    {
                            System.out.println(customers.get(i).getName() + " | " +  customers.get(i).getSales());
                    }
                    System.out.println();
                }    
            }
            else if (answer == 3)
            {
                double total = 0;
                for (int i = 0; i < customers.size(); i++)
                {
                    total = total + customers.get(i).getSales();
                }
                System.out.println(total+"\n");
            }
            else if (answer == 4)
            {
                System.out.println("Goodbye!");
                done = true;
            }
            else
            {
                System.out.println("Invalid input");
            }
        } while (done != true);
        
        System.exit(0);
    }
}
