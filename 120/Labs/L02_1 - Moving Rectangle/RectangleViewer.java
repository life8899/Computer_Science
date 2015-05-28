/**
 * Instantiates RectangleComponent and uses Action Listeners to move the Rectangle across the Screen
 * 
 * @author Nick Alexander
 * @version 9/4/13
 */

import java.awt.event.*;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.Timer;

public class RectangleViewer
{
    public static void main(String[] args)
    {
        final RectangleComponent myComponent = new RectangleComponent();
        
        class TimerListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {myComponent.moveBy(1,1);}
        }
        
        class MousePressListener implements MouseListener
        {
            public void mousePressed(MouseEvent event)
            {
                int x = event.getX();
                int y = event.getY();
                myComponent.setLocation(x, y);
            }
            
            public void mouseReleased(MouseEvent event){};
            public void mouseClicked(MouseEvent event){};
            public void mouseEntered(MouseEvent event){};
            public void mouseExited(MouseEvent event){};
        }
        
        ActionListener myTimerListener = new TimerListener();
        Timer myTimer = new Timer(100, myTimerListener);
        
        MouseListener myMouseListener = new MousePressListener();
        myComponent.addMouseListener(myMouseListener);
        
        JFrame myFrame = new JFrame();
        myFrame.setSize(400, 400);
        myFrame.setTitle("Moving Rectangle");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(myComponent);
        myFrame.setVisible(true);
        myTimer.start();
    }
}