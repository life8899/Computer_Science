/**
 * Test of the Student and StudentData classes
 * 
 * @author J. Thompson
 * @version 29-Dec-2012
 */
import java.util.*;
import java.io.*;
public class StudentTest
{
    public static void main(String[] args) throws IOException
    {
        Scanner myScanner = new Scanner(System.in);
        StudentData myStudents = new StudentData();
        myStudents.open("students.dat");
        boolean done = false;
        while (!done)
        {
            System.out.println("1 - add a student");
            System.out.println("2 - display student info");
            System.out.println("3 - display student info given their last name");
            System.out.println("4 - initialize file");
            System.out.println("5 - exit");
            int choice = Integer.parseInt(myScanner.nextLine());
            
            if (choice == 1)
            {
                System.out.print("Enter 901 number: ");
                int studentID = Integer.parseInt(myScanner.nextLine());
                System.out.print("Enter last name: ");
                String lastName = myScanner.nextLine();
                System.out.print("Enter first name: ");
                String firstName = myScanner.nextLine();
                System.out.print("Enter gpa: ");
                double gpa = Double.parseDouble(myScanner.nextLine());
                System.out.print("Enter grad year: ");
                int gradYear = Integer.parseInt(myScanner.nextLine());
                Student myStudent = new Student(studentID, lastName, firstName, gpa, gradYear);
                myStudents.writeStudent(myStudent);
            }
            else if (choice == 2)
            {
                System.out.print("Enter 901 number: ");
                int studentID = Integer.parseInt(myScanner.nextLine());
                Student myStudent = myStudents.readStudent(studentID);
                System.out.println(myStudent.toString());
            }
            else if (choice == 3)
            {
                System.out.print("Enter Last Name: ");
                String lastName = myScanner.nextLine();
                for (int i = 0; i < myStudents.size(); i++)
                {
                    int id = 901000000 + i;
                    Student test = myStudents.readStudent(id);
                    if (test.getLastName().trim().equals(lastName))
                    {
                        System.out.println(test.toString());
                        break;
                    }
                    else if (i == myStudents.size())
                    {
                        System.out.println("Student Not Found!");
                    }
                }
            }
            else if (choice == 4)
            {
                System.out.print("This will initialize the file and erase all data. Are you sure? (Y/N)");
                String answer = myScanner.nextLine();
                if (answer.equalsIgnoreCase("Y"))
                {
                    myStudents.loadStudentFile();
                    System.out.println("Done!");
                }
                  else
                    System.out.println("File not initialized");
            }
            else if (choice == 5)
                done = true;
            else
                System.out.println("Invalid menu choice!");
       }
       System.out.println("Goodbye!");
    }
}

