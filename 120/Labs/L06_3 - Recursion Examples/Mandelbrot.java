import java.applet.Applet;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Mandelbrot extends Applet
{
   public Mandelbrot()
   {
      final JTextField xminField = new JTextField(5);
      final JTextField yminField = new JTextField(5);;
      final JTextField xmaxField = new JTextField(5);
      final JTextField ymaxField = new JTextField(5);
      started=false;

      JButton moveButton = new JButton("Plot",
          new ImageIcon("truck.jpg"));

          JButton upperLeft=new JButton("Upper left");
          JButton upperRight=new JButton("Upper right");
          JButton lowerLeft=new JButton("Lower left");
          JButton lowerRight=new JButton("Lower right");

      class MoveButtonListener implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {

           xmax=Double.parseDouble(xmaxField.getText());
           xmin=Double.parseDouble(xminField.getText());
           ymax=Double.parseDouble(ymaxField.getText());
           ymin=Double.parseDouble(yminField.getText());
           started=true;
           repaint();

         }
      };


      class UpperLeftListener implements ActionListener
	  {
	     public void actionPerformed(ActionEvent event)
	     {
	       xmax=(xmax+xmin)/2.0;
	       ymin=(ymax+ymin)/2.0;

	       started=true;
	       repaint();
	      }
      };


      class UpperRightListener implements ActionListener
	   {
	       public void actionPerformed(ActionEvent event)
	       {
	  	       xmin=(xmax+xmin)/2.0;
	  	       ymin=(ymax+ymin)/2.0;
               Double newXmin=new Double(xmin);
               xminField.setText(newXmin.toString());
               Double newYmin=new Double(ymin);
               yminField.setText(newYmin.toString());
	  	       started=true;
	  	       repaint();
	        }
      };

	class LowerLeftListener implements ActionListener
	 {
	     public void actionPerformed(ActionEvent event)
	     {
	       xmax=(xmax+xmin)/2.0;
	       ymax=(ymax+ymin)/2.0;
	       started=true;
	        repaint();
	      }
     };

     class LowerRightListener implements ActionListener
	  {
	 	     public void actionPerformed(ActionEvent event)
	 	     {
	 	       xmin=(xmax+xmin)/2.0;
	 	       ymax=(ymax+ymin)/2.0;
	 	       started=true;
	 	        repaint();
	 	      }
     };

      ActionListener listener4=new LowerRightListener();
      lowerRight.addActionListener(listener4);

      ActionListener listener5=new LowerLeftListener();
	        lowerLeft.addActionListener(listener5);





      ActionListener listener = new MoveButtonListener();
      moveButton.addActionListener(listener);

      ActionListener listener2=new UpperLeftListener();
      upperLeft.addActionListener(listener2);

      ActionListener listener3=new UpperRightListener();
      upperRight.addActionListener(listener3);


      JLabel xmaxLabel = new JLabel("xmax = ");
      JLabel xminLabel = new JLabel("xmin = ");
      JLabel ymaxLabel = new JLabel("ymax = ");
      JLabel yminLabel = new JLabel("ymin = ");

      JPanel panel = new JPanel();

      panel.add(xminLabel);
      panel.add(xminField);
      panel.add(xmaxLabel);
      panel.add(xmaxField);

      panel.add(yminLabel);
	  panel.add(yminField);
	  panel.add(ymaxLabel);
      panel.add(ymaxField);

      panel.add(moveButton);

      panel.add(upperLeft);
      panel.add(upperRight);
      panel.add(lowerLeft);
      panel.add(lowerRight);



      JFrame frame = new JFrame();
      frame.setContentPane(panel);
      frame.pack();
      frame.show();
     }

   public void paint(Graphics g)
   {
      if(started==false)
        return;
        //this.resize(300,300);
      Graphics2D g2 = (Graphics2D)g;

      double rad;
      int count;
      double x, savex, y, savey;
      double xgap=(xmax-xmin)/this.getWidth();
      double ygap=(ymax-ymin)/this.getHeight();
      for(int i=0; i<this.getWidth(); i++)
	  {
	   for(int j=0; j<this.getHeight(); j++)
	   {
	     x=xmin+i*xgap;
	     y=ymin+j*ygap;
	     savex=x;
	     savey=y;
	     rad=0; count=0;
	     while(rad<400 && count<10*256)
	     {
		   double savex2=x;
		   x=x*x-y*y +savex;
		   y=2*savex2*y+savey;
		   rad=x*x+y*y;
		   count++;
	     }
         if(count>=10*256)
	     {
			p=new Point(i,j);
			Color c = new Color(0.0f, 0.0f, 0.0f);
			g2.setColor(c);
	    }
	    else
	     {
	      count=count%256;
	      p=new Point(i,j);
		  Color c = new Color(0.0f, (float)count/256.0f,0.0f);//,(float)count/255.0f,0.0f);//float)count/500.0f);//(float)count/10255.0f,(float)count/10255.0f);
		  g2.setColor(c);
	     }
	     g2.drawOval(p.x,this.getHeight()-p.y,0,0);

        // g2.draw(circle);
	   }
	  }

   }

   private double xmin, xmax, ymin, ymax;
   private Point p;
   private boolean started;

}
