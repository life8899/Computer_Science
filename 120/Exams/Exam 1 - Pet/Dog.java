
/**
 * Creates Dog Objects that implement Pet interface
 * 
 * @author Nick Alexander
 * @version 9/25/13
 */



public class Dog implements Pet
{
    private String name;
    private String type;
    private double weight;
    
    /**
     * Instantiates a new Dog Object
     * @param inName Name of the Dog
     * @param inType Type of the Dog
     * @param inWeight Weight of the Dog
     */
    public Dog(String inName, String inType, double inWeight)
    {
        name = inName;
        type = inType;
        weight = inWeight;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public double getWeight()
    {
        return this.weight;
    }
    
    public void setWeight(double newWeight)
    {
        this.weight = newWeight;
    }
    
    /**
     * Describes the Dog in String format
     * @return String representation of the Dog
     */
    public String toString()
    {
        return "Name: " + name + "; Type: " + type + "; Weight: " + weight;
    }
    
    /**
     * Tests whether two objects are the same Dog Object
     * @param test The Object to test equality against
     * @return True/False if Objects are Equal
     */
    public boolean equals(Object test)
    {
        if (test instanceof Dog)
        {
            Dog dog = (Dog) test;
            if (this.name == dog.getName() && this.weight == dog.getWeight())
                return true;
            else
                return false;
        }
        else
        {
            return false;
        }
    }

}
