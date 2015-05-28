
/**
 * Encodes a string
 * 
 * @author Nick Alexander 
 * @version 2/23/13
 */
public class ReplaceTest
{
    public static void main(String[] args)
    {
        System.out.println("Encoding Mississppi:");
        String encode = "Mississippi";
        String encode2 = encode.replace('s', '$');
        String encode3 = encode2.replace('i', '!');
        System.out.println("Expected result: M!$$!$$!pp!");
        System.out.println("Actual result: " + encode3);
    }
}
