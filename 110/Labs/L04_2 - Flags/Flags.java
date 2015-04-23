
/**
 * Draws flags of various countries on a Frame
 * 
 * @author Nick Alexander 
 * @version 2/8/13
 */
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

public class Flags extends JComponent
{
    public void paintComponent(Graphics g)
    {
        //Upgrade Graphics
        Graphics2D graphics = (Graphics2D)g;
        
        //Draw Libyan Flag
        Rectangle libya = new Rectangle( 75, 25, 250, 125);
        Color libyaC = new Color(0, 139 ,0);
        graphics.setColor(libyaC);
        graphics.fill(libya);
        
        //Draw Armenian Flag
        Rectangle armenia = new Rectangle(75, 175, 250, 125);
        Rectangle armenia1 = new Rectangle(75, 216, 250, 84);
        Rectangle armenia2 = new Rectangle(75, 257, 250, 43);
        Color armeniaC = new Color(255,0,0);
        graphics.setColor(armeniaC);
        graphics.fill(armenia);
        Color armeniaC1 = new Color(0,0,205);
        graphics.setColor(armeniaC1);
        graphics.fill(armenia1);
        Color armeniaC2 = new Color(255, 215, 0);
        graphics.setColor(armeniaC2);
        graphics.fill(armenia2);
        
        //Draw Laos Flag
        Rectangle laos = new Rectangle(75, 325, 250, 125);
        Rectangle laos1 = new Rectangle(75, 356, 250, 63);
        Ellipse2D.Double laos2 = new Ellipse2D.Double(175, 362, 50, 50);
        Color laosC = new Color(192,0,0);
        graphics.setColor(laosC);
        graphics.fill(laos);
        Color laosC1 = new Color(0,0,139);
        graphics.setColor(laosC1);
        graphics.fill(laos1);
        graphics.setColor(Color.WHITE);
        graphics.fill(laos2);
        
        //Draw Names
        graphics.setColor(libyaC);
        graphics.drawString("Libya", 350, 88);
        graphics.setColor(armeniaC1);
        graphics.drawString("Armenia", 350, 238);
        graphics.setColor(laosC);
        graphics.drawString("Laos", 350, 388);

    }    
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle("Flags");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Flags flags = new Flags();
        frame.add(flags);
        frame.setVisible(true);
    }    
}
