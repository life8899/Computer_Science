
/**
 * Tests the Generic MUStack
 * 
 * @author Nick Alexander
 * @version 11/1/13
 */



public class GenericStackTest
{
    public static void main(String[] args)
    {
        MUStack<String> stringStack = new MUStack<String>();
        stringStack.push("John");
        stringStack.push("Mike");
        stringStack.push("Fred");
        
        for (int i = 0; i < 3; i++)
            System.out.println(stringStack.pop());
        
        MUStack<Double> doubleStack = new MUStack<Double>();
        doubleStack.push(12.5);
        doubleStack.push(16.1);
        doubleStack.push(1.0);
        
        for (int i = 0; i < 3; i++)
            System.out.println(doubleStack.pop());
            
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
