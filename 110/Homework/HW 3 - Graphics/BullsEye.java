
/**
 * Creates a rectangle containing a string - my name - and a Bulls Eye on a Frame
 * 
 * @author Nick Alexander 
 * @version 2-6-13
 */

import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

/**
 * Constructs JFrame and Graphics objects.
 *
 */
public class BullsEye extends JComponent
{
    
/**
 * creates graphic objects to be painted onto frame
 * @param g adds graphic context
 */
public void paintComponent(Graphics g)
    {
        //Draw Name & Container
        Graphics2D graphics = (Graphics2D)g;
        Rectangle container = new Rectangle(75, 50, 150, 25);
        graphics.setColor(Color.BLUE);
        graphics.draw(container);
        graphics.setColor(Color.RED);
        graphics.drawString("Nick Alexander",100, 70);
        
        //Draw BullsEye
        Ellipse2D.Double outer5 = new Ellipse2D.Double(100, 100, 100, 100);
        graphics.setColor(Color.BLACK);
        graphics.fill(outer5);
        graphics.setColor(Color.WHITE);
        Ellipse2D.Double outer4 = new Ellipse2D.Double(110, 110, 80, 80);
        graphics.fill(outer4);
        graphics.setColor(Color.BLACK);
        Ellipse2D.Double mid3 = new Ellipse2D.Double(120, 120, 60, 60);
        graphics.fill(mid3);
        graphics.setColor(Color.WHITE);
        Ellipse2D.Double mid2 = new Ellipse2D.Double(130, 130, 40, 40);
        graphics.fill(mid2);
        graphics.setColor(Color.RED);
        Ellipse2D.Double inner1 = new Ellipse2D.Double(140, 140, 20, 20);
        graphics.fill(inner1);
    }    
    
    /**
     * Creates a JFrame and Draws BullsEye
     */
    public static void main(String[] args)
        {
            JFrame frame = new JFrame();
            frame.setTitle("Bulls Eye");
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            BullsEye name = new BullsEye();
            frame.add(name);
            frame.setVisible(true);
        }    
}
