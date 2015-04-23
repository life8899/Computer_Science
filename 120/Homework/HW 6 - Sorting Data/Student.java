
/**
 * Creates a Student Object and Implements Comparable
 * 
 * @author Nick Alexander
 * @version 10/9/13
 */

import java.util.*;

public class Student implements Comparable
{
    private String name;
    private double gpa;
    
    public Student(String name, double gpa)
    {
        this.name = name;
        this.gpa = gpa;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public double getGPA()
    {
        return this.gpa;
    }
    
    public String toString()
    {
        return "Name: " + name + "; GPA: " + gpa;
    }
    
    public int compareTo(Object other)
    {
        if (other instanceof Student)
        {
            Student tmp = (Student) other;
            if (this.getGPA() < tmp.getGPA())
                return -1;
            else if (this.getGPA() == tmp.getGPA())
                return 0;
            else
                return 1;
        }
        return 0;
    }
}