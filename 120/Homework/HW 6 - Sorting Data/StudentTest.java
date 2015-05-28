/**
 * Tests the Student Class by Sorting Student Objects
 * 
 * @author Nick Alexander
 * @version 10/9/13
 */

import java.util.*;

public class StudentTest
{
    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Student[] array = new Student[5];
        for (int i = 0; i < array.length; i++)
        {
            System.out.print("Student " + (i + 1) + " Name: ");
            String name = scanner.nextLine();
            System.out.print("Student " + (i + 1) + " GPA: ");
            double gpa = Double.parseDouble(scanner.nextLine());
            array[i] = new Student(name, gpa);
        }
        System.out.println();
        System.out.println("Sorting by GPA...");
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++)
        {
            System.out.println("Student " + (i + 1) + " - " + array[i].toString());
        }
        System.out.println();
        Arrays.sort(array, new StudentName());
        System.out.println("Sorting by Name...");
        for (int i = 0; i < array.length; i++)
        {
            System.out.println("Student " + (i + 1) + " - " + array[i].toString());
        }
    }
}