/**
 * Draws a Rectangle on the Screen
 * 
 * @author Nick Alexander
 * @version 9/4/13
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;

public class RectangleComponent extends JComponent
{
    private Rectangle myBox;
    
    public RectangleComponent()
    {
        myBox = new Rectangle(100, 100, 20, 30);
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.draw(myBox);
    }
    
    public void moveBy(int dx, int dy)
    {
        myBox.translate(dx, dy);
        repaint();
    }
}