/**
 * Creates an object "boa" using two constructors with differing parameters
 * @author Nick Alexander
 * @version 1/30/13
 */
public class Boa
{
    private String material;
    private int length;
    private double price;
    
    public Boa(String boaMaterial, int boaLength)
    {
        material = boaMaterial;
        length = boaLength;
    }    
    
    public Boa(String inMaterial, int inLength, double inPrice)
    {
        material = inMaterial;
        length = inLength;
        price = inPrice;                
    } 
    
    public String getMaterial()
    {
        return material;
    }
    
    public int getLength()
    {
        return length;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public void setMaterial(String newMaterial)
    {
        material = newMaterial;  
    }
    
    public void setPrice(double newPrice)
    {
        price = newPrice;    
    }    
}   


