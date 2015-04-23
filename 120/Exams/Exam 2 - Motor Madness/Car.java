
/**
 * Car class that is a subclass of Vehicle
 * 
 * @author Nick Alexander
 * @version 10/30/13
 */


public class Car extends Vehicle
{
    private int topSpeed;
    
    public Car(String color, int doors, int topSpeed)
    {
        super(color, doors);
        this.topSpeed = topSpeed;
    }
    
    public String toString()
    {
        return super.toString() + " and is a Car with a top speed of " + this.topSpeed + "mph";
    }
}
