
/**
 * Interface for Pet
 * 
 * @author Nick Alexander
 * @version 9/25/13
 */
public interface Pet
{
    /**
     * Returns the name of the Pet
     * @return Name of pet
     */
    public String getName();
    
    /**
     * Returns the type of the Pet
     * @return Type of Pet
     */
    public String getType();
    
    /**
     * Returns the weight of the Pet
     * @return Weight of Pet
     */
    public double getWeight();
    
    /**
     * Sets the Pet's weight to a new value
     * @param newWeight New Weight for the Pet
     */
    public void setWeight(double newWeight);
}
