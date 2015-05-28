
/**
 * Vehicle Superclas
 * 
 * @author Nick Alexander
 * @version 10/30/13
 */
public class Vehicle
{
    private String color;
    private int doors;
    
    public Vehicle()
    {
        this.color = "";
        this.doors = 0;
    }
    
    public Vehicle(String color, int doors)
    {
        this.color = color;
        this.doors = doors;
    }
    
    public String toString()
    {
        return "The Vehicle is " + this.color + ", and has " + this.doors + " doors";
    }
}
