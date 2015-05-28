/**
 * Employee with a Specified Salary subclassing Employee
 * 
 * @author Nicholas Alexander
 * @version 1-21-14
 */

import java.text.NumberFormat;

public class SalariedEmployee extends Employee
{
    private double salary;
    
    /**
     * Creates an Employee that is Paid a Specified Salary
     * 
     * @param salary Salary for the Salaried Employee
     */
    public SalariedEmployee(String name, int ssn, int age, String gender, String address, String phoneNumber, String department, String jobTitle, int yearHired, double salary)
    {
        super(name, ssn, age, gender, address, phoneNumber, department, jobTitle, yearHired);
        this.salary = salary;
    }
    
    public double getSalary()
    {
        return this.salary;
    }
    
    public void setSalary(double newSalary)
    {
        this.salary = newSalary;
    }
    
    /**
     * Returns the Wages (Salary) for the Salaried Employee
     * 
     * @return Wages for the Salaried Employee
     */
    public double calculateWages()
    {
        return this.salary;
    }
    
    public String toString()
    {
        //Used to Properly Format Currency
        NumberFormat n = NumberFormat.getCurrencyInstance();
        return super.toString() + ";\n\tSalaried; Salary: " + n.format(this.salary);
    }
    
    /**
     * Tests if Two Students are the Same based on Salaried Employee Attributes
     * 
     * @param o Student Object to be Tested
     * @return Whether or Not Students are the Same based on ALL Salaried Employee Attributes
     */
    public boolean equals(Object o)
    {
        if (super.equals(o))
        {
            if (o instanceof SalariedEmployee)
            {
                SalariedEmployee seO = (SalariedEmployee) o;
                if (this.salary == seO.getSalary())
                    return true;
            }
        }
        return false;
    }
}