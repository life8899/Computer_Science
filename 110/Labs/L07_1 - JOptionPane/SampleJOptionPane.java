
/**
 * Uses JOptionPane dialog boxes for I/O
 * 
 * @author Nick Alexander
 * @version 2/25/13
 */

import javax.swing.JOptionPane;

public class SampleJOptionPane
{
    public static void main(String[] args)
    {
        String firstInt = JOptionPane.showInputDialog(null, "Enter the First Integer", "First Integer", JOptionPane.QUESTION_MESSAGE);
        int intOne = Integer.parseInt(firstInt);
        String secondInt = JOptionPane.showInputDialog(null, "Enter the Second Integer", "First Integer", JOptionPane.QUESTION_MESSAGE);
        int intTwo = Integer.parseInt(secondInt);
        int sum = intOne + intTwo;
        int dif = intOne - intTwo;
        int rem = intOne % intTwo;
        double pow = Math.pow(intOne, intTwo);
        int neg = intOne * -1;
        int abs = Math.abs(neg);
        JOptionPane.showMessageDialog(null, "The sum of your numbers is: \n" + sum);
        JOptionPane.showMessageDialog(null, "The difference of your numbers is: \n" + dif);
        JOptionPane.showMessageDialog(null, "The remainder of the quotient of your numbers is: \n" + rem);
        JOptionPane.showMessageDialog(null, "First number raised to the power of the Second number is: \n" + pow);
        JOptionPane.showMessageDialog(null, "First number multipled by -1 is: \n" + neg);
        JOptionPane.showMessageDialog(null, "The absolute value of (-) First Number is: \n" + abs);
    }
}
