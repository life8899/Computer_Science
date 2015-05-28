/**
 * Abstraction of an Employee who is a Person
 * 
 * @author Nicholas Alexander
 * @version 1-21-14
 */

public abstract class Employee extends Person
{
    private String department;
    private String jobTitle;
    private int yearHired;
    
    /**
     * Employee Constructor for Subclasses; Abstract Class Cannot be Instantiated
     * 
     * @param department Department the Employee Works In
     * @param jobTitle Title of the Job the Employee Performs
     * @param yearHired Year in which the Employee was Hired
     */
    public Employee(String name, int ssn, int age, String gender, String address, String phoneNumber, String department, String jobTitle, int yearHired)
    {
        super(name, ssn, age, gender, address, phoneNumber);
        this.department = department;
        this.jobTitle = jobTitle;
        this.yearHired = yearHired;
    }
    
    public String getDepartment()
    {
        return this.department;
    }
    
    public String getJobTitle()
    {
        return this.jobTitle;
    }
    
    public int getYearHired()
    {
        return this.yearHired;
    }
    
    public void setDepartment(String newDepartment)
    {
        this.department = newDepartment;
    }
    
    public void setJobTitle(String newJobTitle)
    {
        this.jobTitle = newJobTitle;
    }
    
    /**
     * Calculates Wages for Employee
     * 
     * @return Wages for Employee
     */
    public abstract double calculateWages();
    
    public String toString()
    {
        return super.toString() + "; Department: " + this.department + "; Job Title: " + this.jobTitle + "; Year Hired: " + this.yearHired;
    }
    
    /**
     * Tests if Two Students are the Same based on Employee Attributes
     * 
     * @param o Student Object to be Tested
     * @return Whether or Not Students are the Same based on ALL Employee Attributes
     */
    public boolean equals(Object o)
    {
        if (super.equals(o))
        {
            if (o instanceof Employee)
            {
                Employee employeeO = (Employee) o;
                if (this.department.equals(employeeO.getDepartment()) && this.jobTitle.equals(employeeO.getJobTitle())
                    && this.yearHired == employeeO.getYearHired())
                {
                    return true;
                }
            }
        }
        return false;
    }
}