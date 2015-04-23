/**
 * Student Object Subclassing Person
 * 
 * @author Nicholas ALexander
 * @version 1-28-14
 * 
 */

public class Student extends Person
{
    private double gpa;
    private String major;
    private int gradYear;
    
    /**
     * Creates a Student Object who is a Person
     * 
     * @param gpa Student's Current Grade-Point Average
     * @param major Student's Current Major
     * @param gradYear Student's Year of Graduation
     */
    public Student(String name, int ssn, int age, String gender, String address, String phoneNumber, double gpa, String major, int gradYear)
    {
        super(name, ssn, age, gender, address, phoneNumber);
        this.gpa = gpa;
        this.major = major;
        this.gradYear = gradYear;
    }
    
    public double getGPA()
    {
        return this.gpa;
    }
    
    public String getMajor()
    {
        return this.major;
    }
    
    public int getGradYear()
    {
        return this.gradYear;
    }
    
    public void setMajor(String newMajor)
    {
        this.major = newMajor;
    }
    
    public void setGradYear(int newYear)
    {
        this.gradYear = newYear;
    }
    
    /**
     * Calculuates and Returns the Student's GPA
     * 
     * @param totalQualityPoints Total Quality Points acquired; Found by multiplying the number of credit hours by the grade obtained in the course such
     *      that A = 4, B = 3, C = 2, D = 1, and F = 0
     * @param totalHours Total Number of Hours taken by the Student
     * @return Student's Current GPA
     */
    public double calculateGPA(int totalQuailityPoints, int totalHours)
    {
        return (double) totalQuailityPoints / totalHours;
    }
    
    public String toString()
    {
        return super.toString() + "; GPA: " + this.gpa + "; Major: " + this.major + "; Graduation Year: " + this.gradYear;
    }
    
    /**
     * Tests if Two Students are the Same based on Student Attributes
     * 
     * @param o Student Object to be Tested
     * @return Whether or Not Students are the Same based on ALL Student Attributes
     */
    public boolean equals(Object o)
    {
        if (super.equals(o))
        {
            if (o instanceof Student)
            {
                Student studentO = (Student) o;
                if (this.gpa == studentO.getGPA() && this.major.equals(studentO.getMajor()) && this.gradYear == studentO.getGradYear())
                    return true;
            }
        }
        return false;
    }
}