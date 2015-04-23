/**
 * Test Class for Vehicle
 */

import java.util.Scanner;
import java.util.ArrayList;

public class VehicleTest
{
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        ArrayList<Vehicle> myList = new ArrayList<Vehicle>();
        boolean done = false;
        do
        {
          System.out.println("1 – add car to list");
          System.out.println("2 – add truck to list");
          System.out.println("3 - display all vehicles in list");
          System.out.println("4 – exit");
          int choice = Integer.parseInt(myScanner.nextLine());
          if (choice == 1)
          {
            System.out.print("Enter color: ");
            String color = myScanner.nextLine();
            System.out.print("Enter number of doors: ");
            int doors = Integer.parseInt(myScanner.nextLine());
            System.out.print("Enter top speed: ");
            int topSpeed = Integer.parseInt(myScanner.nextLine());
            //Instantiate an appropriate object and add it to the list
            myList.add(new Car(color, doors, topSpeed));
          }
          else if (choice == 2)
          {
            System.out.print("Enter color: ");
            String color = myScanner.nextLine();
            System.out.print("Enter number of doors: ");
            int doors = Integer.parseInt(myScanner.nextLine());
            System.out.print("Enter Maximum Capacity: ");
            double capacity = Double.parseDouble(myScanner.nextLine());
            //Instantiate an appropriate object and add it to the list
            myList.add(new Truck(color, doors, capacity));
          }
          else if (choice == 3)
          {
            for (int i = 0; i < myList.size(); i++)
            {
                System.out.println(myList.get(i).toString());
            }
          }
          else if (choice == 4)
          {
            done = true;
          }
          else
            System.out.println("Invalid choice, Bozo!");
        } while (!done);
        System.out.println("Goodbye");
}
}

