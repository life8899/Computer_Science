/**
Draws the Sierpinski gasket
@author J. Fuller
@version 14-Feb-11
*/
import java.applet.Applet;
import java.awt.*;
import java.awt.geom.Line2D;

public class Sierpinski extends Applet
{
    public void myDraw(Graphics2D myG, double x1, double y1, double x2, double y2, double x3, double y3)
    {
       //length1, length2 and length3 are sides of the triangle
       //This code is executed to draw the triangle

       //Calculate the length of each side
       double length1 = Math.pow((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1),0.5);
       double length2 = Math.pow((x2-x3)*(x2-x3) + (y2-y3)*(y2-y3),0.5);
       double length3 = Math.pow((x3-x1)*(x3-x1) + (y3-y1)*(y3-y1),0.5);

       //The perimeter is the sum of the lengths of all the sides
       double perim=length1+length2+length3;

       // If triangle perimeter is too small, stop subdividing it
       if (perim < 18)
       {
           Line2D.Double segment = new Line2D.Double(x1,y1,x2,y2);
           myG.draw(segment);
           Line2D.Double segment2 = new Line2D.Double(x2,y2,x3,y3);
           myG.draw(segment2);
           Line2D.Double segment3 = new Line2D.Double(x3,y3,x1,y1);
           myG.draw(segment3);
	   }
       else
       {
           //This loop slows the program down so we can watch it
           long startTime =System.currentTimeMillis();
           long endTime = System.currentTimeMillis();
           while(endTime - startTime <30)
           {
               endTime=System.currentTimeMillis();
           }
           //Make 3 triangles
           myDraw(myG,x1,y1,(x1+x2)/2.0,(y1+y2)/2.0,(x1+x3)/2.0,(y1+y3)/2.0);
           myDraw(myG,(x1+x2)/2.,(y1+y2)/2.,x2,y2,(x2+x3)/2.0,(y2+y3)/2.0);
           myDraw(myG,(x1+x3)/2.,(y1+y3)/2.,(x3+x2)/2.0,(y3+y2)/2.0,x3,y3);
       }
    }

    public void paint(Graphics g)
    {
        Graphics2D g2=(Graphics2D) g;
        double x1 = 0.0;
        double y1 = this.getHeight()-1;
        double x2 = this.getWidth();
        double y2 = this.getHeight();
        double x3 = this.getWidth()/2;
        double y3 = 0.0;
        myDraw(g2,x1,y1,x2,y2,x3,y3);
    }
}
