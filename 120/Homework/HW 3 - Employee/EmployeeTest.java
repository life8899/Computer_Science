
/**
 * Tests the Employee and Manager Classes
 * 
 * @author Nick Alexander
 * @version 9/11
 */

import java.util.Scanner;
import java.util.ArrayList;

public class EmployeeTest
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        ArrayList<Employee> list = new ArrayList<Employee>();
        boolean done = false, error = false;;
        String name = "", department = "";
        double salary = 0;
        int menuChoice = 0;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("1) New Employee\n" + "2) New Manager\n" + "3) Display Employees\n" + "4) Quit");
            menuChoice = Integer.parseInt(scanner.nextLine());
            if (menuChoice == 1)
            {
                
                do {
                    System.out.print("\nEmployee Name: ");
                    try {name = scanner.nextLine(); error = false;}
                    catch (Exception e) {System.out.println("Error: Invalid Entry. Please Try Again"); error = true;}
                } while (error == true);     
                
                
                do {
                    System.out.print("Employee Salary: ");
                    try {salary = Double.parseDouble(scanner.nextLine()); error = false;}
                    catch (Exception e) {System.out.println("Error: Invalid Entry. Please Try Again"); error = true;}
                } while (error == true);
                System.out.println("Employee Added!\n");    
                list.add(new Employee(name, salary));
            }
            else if (menuChoice == 2)
            {
                
                do {
                    System.out.print("Manager Name: ");
                    try {name = scanner.nextLine(); error = false;}
                    catch (Exception e) {System.out.println("Error: Invalid Entry. Please Try Again"); error = true;}
                } while (error == true);     
            
            
                do {
                    System.out.print("Manager Salary: ");
                    try {salary = Double.parseDouble(scanner.nextLine()); error = false;}
                    catch (Exception e) {System.out.println("Error: Invalid Entry. Please Try Again"); error = true;}
                } while (error == true);   
            
            
                do {
                    System.out.print("Manager Department: ");
                    try {department = scanner.nextLine(); error = false;}
                    catch (Exception e) {System.out.println("Error: Invalid Entry. Please Try Again"); error = true;}
                } while (error == true);   
               
                System.out.println("Manager Added!\n");    
                list.add(new Manager(name, salary, department));
            }
            else if (menuChoice == 3)
            {
                System.out.println("\nEmployees:");
                for (int i = 0; i < list.size(); i++)
                {
                    System.out.println(list.get(i).toString());
                }
            }
            else if (menuChoice == 4)
            {
                done = true;
                System.out.println("\nGoodbye!");
            }
            else
            {
                System.out.println("Error: Invalid Entry");
            }
        } while (done == false);
        System.exit(0);
    }
}
