/**
 * Test Program for the Person Superclass and Subclasses
 * 
 * @author Nicholas Alexander
 * @version 1-28-14
 */

import java.util.Scanner;

public class Test
{
    private static Person[] array = new Person[10];
    private static Scanner scanner = new Scanner(System.in);
    
    //Fields to make code a little more reusable simply for testing purposes
    // See getPersonInfo method for example
    private static int people = 0;
    private static int ssn, age, yearHired;
    private static String name, gender, address, phoneNumber, department, jobTitle;
    
    public static void main(String[] args)
    {
        boolean done = false;
        int choice = 0;
        do
        {
            System.out.print("\n0 - Exit\n" + "1 - Create a New Person\n" + "2 - Create a New Student\n" + "3 - Create a New Hourly Employee\n"
                + "4 - Create a New Salaried Employee\n" + "5 - Test Equality\n" + "6 - Display People\n");
            choice = Integer.parseInt(scanner.nextLine());
            //Each Function is seperated into a static method for clarity
            switch (choice)
            {
                case 0 : System.out.println("Goodbye!"); System.exit(0); break;
                case 1 : createPerson(); break;
                case 2 : createStudent(); break;
                case 3 : createHourlyEmployee(); break;
                case 4 : createSalariedEmployee(); break;
                case 5 : testEquality(); break;
                case 6 : displayPeople(); break;   
                
                default : System.out.println("Invalid Entry!"); break;              
            }
        }
        while (!done);    
    }
    
    /**
     * Static Method to Retrieve Information Common to All Objects of Type Person
     */
    private static void getPersonInfo()
    {
        if (people == 10)
        {
            System.out.println("Too Many People!");
            return;
        }
        System.out.print("Name: ");
        name = scanner.nextLine();
        System.out.print("Social Security Number: ");
        ssn = Integer.parseInt(scanner.nextLine());
        System.out.print("Age: ");
        age = Integer.parseInt(scanner.nextLine());
        System.out.print("Gender: ");
        gender = scanner.nextLine();
        System.out.print("Address: ");
        address = scanner.nextLine();
        System.out.print("Phone Number: ");
        phoneNumber = scanner.nextLine();
    }
    
    /**
     * Static Method to Retrieve Information Common to All Objects of Type Employee
     */
    private static void getEmployeeInfo()
    {
        if (people == 10)
        {
            System.out.println("Too Many People!");
            return;
        }
        getPersonInfo();
        System.out.print("Department: ");
        department = scanner.nextLine();
        System.out.print("Job Title: ");
        jobTitle = scanner.nextLine();
        System.out.print("Year Hired: ");
        yearHired = Integer.parseInt(scanner.nextLine());
    }
    
    /**
     * Static Method to Swiftly Create a New Person
     */
    private static void createPerson()
    {
        if (people == 10)
        {
            System.out.println("Too Many People!");
            return;
        }
        getPersonInfo();
        array[people] = new Person(name, ssn, age, gender, address, phoneNumber);
        people++;
        System.out.println("New Person Created!\n");
    }
    
    /**
     * Static Method to Swiftly Create a New Student
     */
    private static void createStudent()
    {
        if (people == 10)
        {
            System.out.println("Too Many People!");
            return;
        }
        getPersonInfo();
        System.out.print("GPA: ");
        double gpa = Double.parseDouble(scanner.nextLine());
        System.out.print("Major: ");
        String major = scanner.nextLine();
        System.out.print("Graduation Year: ");
        int gradYear = Integer.parseInt(scanner.nextLine());
        array[people] = new Student(name, ssn, age, gender, address, phoneNumber, gpa, major, gradYear);
        people++;
        System.out.println("New Student Created!");
    }
    
    /**
     * Static Method to Swiftly Create a New Hourly Employee
     */
    private static void createHourlyEmployee()
    {
        if (people == 10)
        {
            System.out.println("Too Many People!");
            return;
        }
        getEmployeeInfo();
        System.out.print("Hours Worked: ");
        double hoursWorked = Double.parseDouble(scanner.nextLine());
        System.out.print("Hourly Rate: ");
        double hourlyRate = Double.parseDouble(scanner.nextLine());
        array[people] = new HourlyEmployee(name, ssn, age, gender, address, phoneNumber, department, jobTitle, yearHired, hoursWorked, hourlyRate);
        people++;
        System.out.println("New Hourly Employee Created!");
    }
    
    /**
     * Static Method to Swiftly Create a New Salaried Employee
     */
    private static void createSalariedEmployee()
    {
        if (people == 10)
        {
            System.out.println("Too Many People!");
            return;
        }
        getEmployeeInfo();
        System.out.print("Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());
        array[people] = new SalariedEmployee(name, ssn, age, gender, address, phoneNumber, department, jobTitle, yearHired, salary);
        people++;
        System.out.println("New Hourly Employee Created!");
    }
    
    /**
     * Static Method to Test Person Equality
     */
    private static void testEquality()
    {
        if (array[0] == null)
        {
            System.out.println("No People Exist!");
            return;
        }
        displayPeople();
        System.out.println();
        Person person1 = getPersonForEquality();
        Person person2 = getPersonForEquality();
        
        if (person1.equals(person2))
            System.out.println("The People are Equal!");
        else
            System.out.println("The People are Not Equal!");
    }
    
    /**
     * Static Method to Retrieve People for Equality Test
     */
    private static Person getPersonForEquality()
    {
        System.out.print("Enter the Number of the Person to be Tested as Shown in the Above List: ");
        int selection = Integer.parseInt(scanner.nextLine()) - 1;
        if (selection >= 0 && selection < 10)
        {
            if (array[selection] != null)
                return array[selection];
            else
                System.out.println("Person Does Not Exist! Automatically Set to Person 1");
        }
        return array[0];
    }
    
    /**
     * Static Method to Quickly Print All People
     */
    private static void displayPeople()
    {
        if (array[0] != null)
         {
            System.out.println();
            for (int i = 0; i < people; i++)
            {
                if (array[i] != null)
                {
                    System.out.println((i+1) + ") " + array[i].toString());
                }
            }
         }
         else
            System.out.println("No People!");
    }
}