/**
 * Student class
 * 
 * @author J. Thompson
 * @version 29-Dec-2012
 */
public class Student
{
    private String lastName, firstName;
    private double gpa;
    private int studentID, gradYear;
    
    public Student(int studentID, String lastName, String firstName, double gpa, int gradYear)
    {
        this.studentID = studentID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gpa = gpa;
        this.gradYear = gradYear;
    }
    
    public int getStudentID()
    {
        return studentID;
    }
    
    public String getLastName()
    {
      return lastName;
    }
    
    public String getFirstName()
    {
      return firstName;
    }
    
    public double getGPA()
    {
        return gpa;
    }
    
    public int getGradYear()
    {
        return gradYear;
    }
    
    public String toString()
    {
        return "Student ID: " + studentID + " Name: " + lastName.trim() + ", " + firstName.trim() + " GPA: " + gpa + " Grad year: " + gradYear;
    }
}
