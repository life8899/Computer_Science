
/**
 * Provides a constructor for Customer Object as well as Mutator and Accessor methods
 * 
 * @author Nick Alexander
 * @version 4/8/13
 */
public class Customer
{
    private String name;
    private double sales;
    
    /** Creates Customer object
    * @param inName Name of the customer
    * @param inSales Sales amount
    */
    public Customer(String inName, double inSales)
    {
        name = inName;
        sales = inSales;
    }
    
    /**Accessor for name
     * @return Name of Customer
     */
    public String getName()
    {
        return name;
    }
    
    /**Accessor for sales
     * @return Sales Amount
     */
    public double getSales()
    {
        return sales;
    }
    
    /**Setter for name
     * @param newName New name for the customer
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**Setter for sales
     * @param newSales New Sales Amount
     */
    public void setSales(double newSales)
    {
        sales = newSales;
    }
}
