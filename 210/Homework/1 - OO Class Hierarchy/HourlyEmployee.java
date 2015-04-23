/**
 * Employee with an Hourly Wage subclassing Employee
 * 
 * @author Nicholas Alexander
 * @version 1-21-14
 */

import java.text.NumberFormat;

public class HourlyEmployee extends Employee
{
    private double hoursWorked;
    private double hourlyRate;
    
    /**
     * Creates an Employee that is Paid by the Hour
     * 
     * @param hoursWorked Number of Hours Worked by the Hourly Employee
     * @param hourslyRate Pay Rate per Hour for Hourly Employee
     */
    public HourlyEmployee(String name, int ssn, int age, String gender, String address, String phoneNumber, String department, String jobTitle, int yearHired, double hoursWorked, double hourlyRate)
    {
        super(name, ssn, age, gender, address, phoneNumber, department, jobTitle, yearHired);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    
    public double getHoursWorked()
    {
        return this.hoursWorked;
    }
    
    public void setHoursWorked(double newHours)
    {
        this.hoursWorked = newHours;
    }
    
    /**
     * Calculates Wages based on Hours Worked and Hourly Rate
     * 
     * @return Hourly Employee Wages
     */
    public double calculateWages()
    {
        if (this.hasOverTime())
            this.calculateOverTime();
        return this.hoursWorked * this.hourlyRate;
    }
    
    /**
     * Tests if the Employee has Worked Over Time (>40 hours)
     * 
     * @return Whether or Not the Employee Worked Over Time
     */
    private boolean hasOverTime()
    {
        if (this.hoursWorked > 40)
            return true;
        return false;    
    }
    
    /**
     * Returns Wages Earned based on Over Time Rates (Time and a Half)
     * 
     * @return Over Time Wages
     */
    private double calculateOverTime()
    {
        double wages = (this.hoursWorked - 40) * (this.hourlyRate * 1.5);
        this.hoursWorked -= 40;
        return wages;
    }
    
    public String toString()
    {
        //Used to Properly Format Currency
        NumberFormat n = NumberFormat.getCurrencyInstance();
        return super.toString() + ";\n\tHourly; Hours Worked: " + this.hoursWorked + "; Hourly Rate: " + n.format(this.hourlyRate) + "; Wages: " + n.format(this.calculateWages());
    }
}