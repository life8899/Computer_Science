/**
 * Contains Main method
 * 
 * @author Nick Alexander
 * @version 8/28/13
 */

import java.util.*;

public class Main
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        boolean done = false, error = false;
        String id;
        double salary = 0, hoursWorked = 0;
        int choice = 0;
        ArrayList<Payday> payday = new ArrayList<Payday>();
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("1) Add Salaried Employee\n" + "2) Add Hourly Employee\n" + "3) Add Part-Time Employee\n" + "4) Display All\n" + "5) Quit");
            try
            {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e)
            {
                System.out.println("Error: Invalid Entry!");
                done = false;
            }
            
            //Add Salaried Employee
            if (choice == 1) {
                System.out.print("Salaried Employee ID: ");
                id = scanner.nextLine();
                do
                {
                    System.out.print("Salary: ");
                    try
                    {
                        salary = Double.parseDouble(scanner.nextLine());
                        error = false;
                    } catch (Exception e)
                    {
                        System.out.println("Error: Invalid Entry!");
                        error = true;
                    }
                } while (error == true);
                payday.add(new SalariedEmployee(id, salary));
                System.out.println("Employee Added!");
                System.out.println();
            }
            //Add Hourly Employee
            else if (choice ==2) {
                System.out.print("Hourly Employee ID: ");
                id = scanner.nextLine();
                do
                {
                    System.out.print("Hours Worked: ");
                    try
                    {
                        hoursWorked = Double.parseDouble(scanner.nextLine());
                        error = false;
                    } catch (Exception e)
                    {
                        System.out.println("Error: Invalid Entry!");
                        error = true;
                    }
                } while (error == true);
                payday.add(new HourlyEmployee(id, hoursWorked));
                System.out.println("Employee Added!");
                System.out.println();
            }
            //Add Part-Time Employee
            else if (choice == 3) {
                System.out.print("Part-Time Employee ID: ");
                id = scanner.nextLine();
                do
                {
                    System.out.print("Hours Worked: ");
                    try
                    {
                        hoursWorked = Double.parseDouble(scanner.nextLine());
                        error = false;
                    } catch (Exception e)
                    {
                        System.out.println("Error: Invalid Entry!");
                        error = true;
                    }
                } while (error == true);
                payday.add(new PartTimeEmployee(id, hoursWorked));
                System.out.println("Employee Added!");
                System.out.println();
            }
            //Display All
            else if (choice == 4 ) {
                for (int i = 0; i < payday.size(); i++)
                {
                    payday.get(i).findEarnings();
                    System.out.println(payday.get(i).payStub());
                    System.out.println();
                }
            }
            ///Exit
            else if (choice == 5) {
                done = true;
            }
        } while (done != true);
        System.out.println("Goodbye!");
    } 
}