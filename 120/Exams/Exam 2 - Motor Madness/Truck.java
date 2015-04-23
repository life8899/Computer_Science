/**
 * Truck class that is a subclass of Vehicle
 * 
 * @author Nick Alexander
 * @version 10/30/13
 */


public class Truck extends Vehicle
{
    private double capacity;
    
    public Truck(String color, int doors, double capacity)
    {
        super(color, doors);
        this.capacity = capacity;
    }
    
    public String toString()
    {
        return super.toString() + " and is a Truck with a max capacity of " + this.capacity + " ton(s)";
    }
}
