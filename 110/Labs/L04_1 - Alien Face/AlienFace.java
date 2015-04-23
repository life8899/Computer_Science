
/**
 * Draws an Alien Face using various shapes on a Frame
 * 
 * @author Nick Alexander 
 * @version 2/6/13
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class AlienFace extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D myG2D = (Graphics2D)g;
        Ellipse2D.Double head = new Ellipse2D.Double(5, 10, 100, 150);
        Ellipse2D.Double eye1 = new Ellipse2D.Double(25, 60, 15, 20);
        Ellipse2D.Double subEye1 = new Ellipse2D.Double(30, 65, 5, 10);
        Ellipse2D.Double eye2 = new Ellipse2D.Double(70, 60, 15, 20);
        Ellipse2D.Double subEye2 = new Ellipse2D.Double(75, 65, 5, 10);
        Rectangle mouth = new Rectangle(30, 130, 50, 5);
        myG2D.setColor(Color.GREEN);
        myG2D.fill(head);
        myG2D.setColor(Color.BLACK);
        myG2D.draw(eye1);
        myG2D.draw(eye2);
        myG2D.setColor(Color.RED);
        myG2D.fill(subEye1);
        myG2D.fill(subEye2);  
        myG2D.setColor(Color.BLACK);
        myG2D.fill(mouth);
        myG2D.setColor(Color.BLUE);
        myG2D.drawString("Take Me To Your Leader.", 10, 200);
    }
    
    public static void main(String[] args)
    {
        JFrame myFrame = new JFrame();
        myFrame.setSize(300, 400);
        myFrame.setTitle("An Alien Face");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AlienFace myFace = new AlienFace();
        myFrame.add(myFace);
        myFrame.setVisible(true);
    }    
}
