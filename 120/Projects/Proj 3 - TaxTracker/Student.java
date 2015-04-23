package TaxTracker;

import java.io.Serializable;

/**
 * Creates a Student Worker Object
 *
 * @author Nick Alexander
 * @author Brandon Houser
 * @version December 3, 2013
 */
public class Student implements Serializable, Comparable
{
    private int id;
    private String name;
    private String address;
    private double hoursWorked;
    private double hourlyRate;
    private double netIncome;
    private double grossIncome;

    /**
     * Creates a Student Worker Object
     *
     * @param id Student Worker 901 Number
     * @param name Student Worker Name
     * @param address Student Worker Address
     * @param hoursWorked Hours Worked by the Student Worker
     * @param rate Hourly Rate for the Student Worker
     */
    public Student(int id, String name, String address, double hoursWorked, double rate)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = rate;
        this.grossIncome = hoursWorked * this.hourlyRate;
        this.netIncome = Taxes.deductTaxesFromIncome(this.grossIncome);
    }

    /**
     * Returns the ID of the Student Worker
     *
     * @return ID of the Student Worker
     */
    public int getID()
    {
        return this.id;
    }

    /**
     * Returns the Name of the Student Worker
     *
     * @return Name of the Student Worker
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the Address of the Student Worker
     *
     * @return Address of the Student Worker
     */
    public String getAddress()
    {
        return this.address;
    }

    /**
     * Returns the Hours Worked by the Student Worker
     *
     * @return Hours Worked by the Student Worker
     */
    public double getHoursWorked()
    {
        return this.hoursWorked;
    }

    /**
     * Returns the Gross Income of the Student Worker
     *
     * @return Gross Income of the Student Worker
     */
    public double getGrossIncome()
    {
        return this.grossIncome;
    }

    public double getNetIncome()
    {
        return this.netIncome;
    }

    public double getHourlyRate()
    {
        return this.hourlyRate;
    }

    /**
     * Sets the ID of the Student Worker
     *
     * @param id New ID
     */
    public void setID(int id)
    {
        this.id = id;
    }

    /**
     * Sets the Name of the Student Worker
     *
     * @param id New Name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the Address of the Student Worker
     *
     * @param id New Address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * Sets the Hours Worked by the Student Worker
     *
     * @param id New Hours Worked
     */
    public void setHoursWorked(double hoursWorked)
    {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Sets the Gross Income of the Student Worker
     *
     * @param id New Gross Income
     */
    public void setGrossIncome(double income)
    {
        this.grossIncome = income;
    }

    public void setHourlyRate(double rate)
    {
        this.hourlyRate = rate;
    }

    /**
     * Returns a String Representation of the Student Worker
     *
     * @return String Representation of the Student Worker
     */
    @Override
    public String toString()
    {
        return "ID: " + this.id + "; Name: " + this.name + "; Address: "
                + this.address + "; Hours Worked: " + this.hoursWorked
                + "; Gross Income: " + this.grossIncome;
    }

    /**
     * Tests Equality of Student Workers
     *
     * @param o Object to Test Against
     * @return Boolean Value of Equality
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Student)
        {
            Student s = (Student) o;
            if (s.getName().equals(this.getName()) && s.getID() == this.getID())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares Student Workers Based on Their ID Numbers
     *
     * @param o Student Object to Compare
     * @return Comparison Value
     */
    @Override
    public int compareTo(Object o)
    {
        if (o instanceof Student)
        {
            Student test = (Student) o;
            if (this.getID() < test.getID())
            {
                return -1;
            }
            else if (this.getID() > test.getID())
            {
                return 1;
            }
        }
        return 0;
    }
}
