/**
 * A Superclass encapsulating all people as objects
 * 
 * @author Nicholas Alexander
 * @version 1-21-14
 */
public class Person
{
    String name;
    int ssn;
    int age;
    String gender;
    String address;
    String phoneNumber;
    
    /**
     * Creates a Person Object
     * 
     * @param name Name of the Person
     * @param ssn Social Security Number of the Person
     * @param age Age in Years of the Person
     * @param gender Gender of the Person
     * @param address Current Address of the Person
     * @param phoneNumber Current Phone Number of the Person
     */
    public Person(String name, int ssn, int age, String gender, String address, String phoneNumber)
    {
        this.name = name;
        this.ssn = ssn;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName()
    {
        return this.name;
    }

    public int getSSN()
    {
        return this.ssn;
    }

    public int getAge()
    {
        return this.age;
    }

    public String getGender()
    {
        return this.gender;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public void setAge(int newAge)
    {
        this.age = newAge;
    }

    public void setAddress(String newAddress)
    {
        this.address = newAddress;
    }
    
    public void setPhoneNumber(String newPhoneNumber)
    {
        this.phoneNumber = newPhoneNumber;
    }
    
    /**
     * String Representation of the Person
     * 
     * @return String Representation of the Person
     */
    public String toString()
    {
        return "Name: " + this.name + "; SSN: " + this.ssn + "; Gender: " + this.gender + "; Address: " + this.address + "; Age: " + this.age
            + "; Phone Number: " + this.phoneNumber;
    }
}