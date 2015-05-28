/**
 * 
 * 
 * @author Nick Alexander
 * @version 8/26/13
 */

import java.util.Scanner;

public class StadiumTest
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Stadium[] stadiums = new Stadium[10];
        System.out.print("Stadium 1 Location: ");
        String location = scanner.nextLine();
        System.out.print("Stadium 1 Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());
        Stadium stadium0 = new Stadium(location, capacity);
        stadiums[0] = stadium0;
        
        System.out.println();
        
        System.out.print("Stadium 2 Location: ");
        location = scanner.nextLine();
        System.out.print("Stadium 2 Capacity: ");
        capacity = Integer.parseInt(scanner.nextLine());
        Stadium stadium1 = new Stadium(location, capacity);
        stadiums[1] = stadium1;
        
        System.out.println();
        
        System.out.print("Stadium 3 Location: ");
        location = scanner.nextLine();
        System.out.print("Stadium 3 Capacity: ");
        capacity = Integer.parseInt(scanner.nextLine());
        Stadium stadium2 = new Stadium(location, capacity);
        stadiums[2] = stadium2;
        
        System.out.println();
        
        System.out.println("Stadiums:");
        
        int total = 0;
        for (int i = 0; i < 3; i++)
        {
            System.out.println("Location: " + stadiums[i].getLocation() + "; Capacity: " + stadiums[i].getCapacity());
            total += stadiums[i].getCapacity();
        }
        System.out.println("Total Capcity: " + total);
        
        System.out.println();
        stadiums[1].setCapacity(100000);
        
        System.out.println("Stadiums:");
        
        total = 0;
        for (int i = 0; i < 3; i++)
        {
            System.out.println("Location: " + stadiums[i].getLocation() + "; Capacity: " + stadiums[i].getCapacity());
            total += stadiums[i].getCapacity();
        }
        System.out.println("Total Capcity: " + total);
    }
}