/**
 * Creates a Stadium Object with a location and a capacity
 * 
 * @author Nick Alexander
 * @version 8/26/13
 */

public class Stadium
{
    private String location;
    private int capacity;
    
    /**
     * Creates a Stadium Object
     * @param inLocation Location of the Stadium
     * @param inCapacity Capacity of the Stadium
     */
    public Stadium(String inLocation, int inCapacity)
    {
        location = inLocation;
        capacity = inCapacity;
    }
    
    /**
     * Returns Location of Stadium
     * @return Location of Stadium
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     * Returns Capcity of Stadium
     * @return Capacity of Stadium
     */
    public int getCapacity()
    {
        return capacity;
    }
    
    /**
     * Sets new value for Capacity of the Stadium
     * @param newCapcity New Capacity of Stadium
     */
    public void setCapacity(int newCapacity)
    {
        capacity = newCapacity;
    }
}