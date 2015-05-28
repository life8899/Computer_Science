/**
 * Test of the Student and StudentData classes
 * 
 * @author J. Thompson
 * @version 29-Dec-2012
 */
import java.util.*;
import java.io.*;
public class StudentTestOS
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Scanner myScanner = new Scanner(System.in);
        ArrayList<Student> sList = new ArrayList<Student>();
        File sFile = new File ("students.dat");
        
        if (sFile.exists())
        {
            FileInputStream myFIS = new FileInputStream(sFile);
            ObjectInputStream sIn = new ObjectInputStream(myFIS);
            sList = (ArrayList<Student>) sIn.readObject();
            sIn.close();
        }
        
        System.out.println("Students on File: ");
        
        for (int i = 0; i < sList.size(); i++)
        {
            System.out.println(sList.get(i).toString());
        }
        
        boolean done = false;
        do
        {
            System.out.println("1 - Add Student");
            System.out.println("2 - Display Info Using ID");
            System.out.println("3 - Display Info Using Last Name");
            System.out.println("4 - Exit");
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
                sList.add(myStudent);
            }
            else if (choice == 2)
            {
                System.out.print("Enter 901 Number: ");
                int studentID = Integer.parseInt(myScanner.nextLine());
                for (int i = 0; i < sList.size(); i++)
                {
                    if (studentID == sList.get(i).getStudentID())
                    {
                        System.out.println(sList.get(i).toString());
                        break;
                    }
                }
            }
            else if (choice == 3)
            {
                System.out.println("Enter Last Name: ");
                String lastName = myScanner.nextLine();
                for (int i = 0; i < sList.size(); i++)
                {
                    int id = 901000000 + i;
                    Student test = sList.get(i);
                    if (test.getLastName().trim().equals(lastName))
                    {
                        System.out.println(test.toString());
                        break;
                    }
                    else if (i == sList.size())
                    {
                        System.out.println("Student Not Found");
                    }
                }
            }
            else if (choice == 4)
                done = true;
        } while (!done);
        
        FileOutputStream myFOS = new FileOutputStream(sFile);
        ObjectOutputStream sOut = new ObjectOutputStream(myFOS);
        sOut.writeObject(sList);
        sOut.close();
        System.out.println("Goodbye!");
    }
}

