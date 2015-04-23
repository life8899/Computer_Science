
/**
 * Write a description of class Student here.
 * 
 * @author Nick Alexander
 * @version 1/25/13
 */
public class Student
{
    private String fName, town, state;
    private int gradYear;
    
    public Student(String inFname, int inGradYear, String inTown)
    {
        fName = inFname;
        gradYear = inGradYear;
        town = inTown;
    }    
    
    public String getName()
    {
        return fName;
    }
    
    public String getTown()
    {
        return town;
    }
    
    public String getState()
    {
        return state;
    }
    
    public int getGradYear()
    {
        return gradYear;
    }
    
    public void setGradYear(int inGradYear)
    {
        gradYear = inGradYear;
    }
    
    public void setState(String inState)
    {
        state = inState;
    }
         
}