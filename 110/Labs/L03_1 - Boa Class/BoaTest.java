
/**
 * Instantiates two boas using both constructors
 * @author Nick Alexander
 * @version 1/30/13
 */
public class BoaTest
{
    public static void main(String[] args)
    {
        Boa newBoa;
        newBoa = new Boa("Wool", 5);
        System.out.println("Original Boa: " + " " + "Material: " +newBoa.getMaterial() + " " + "Length: " + newBoa.getLength());
        
        newBoa.setPrice(12.99);
        System.out.println("Boa with new Price: " + " " + "Material: " +newBoa.getMaterial() + " " + "Length: " + newBoa.getLength()+ " " + "Price: " + newBoa.getPrice());
        
        
        Boa longBoa = new Boa("Fur", 10, 20.00);
        System.out.println("Longer Boa: " + " " + "Material: " +longBoa.getMaterial() + " " + "Length: " + longBoa.getLength()+ " " + "Price: " + longBoa.getPrice());
    }    


}
