import javax.swing.*;
import java.awt.event.*;
public class ButtonTest
{
 public static void main(String[] args)
 {
  JFrame myFrame = new JFrame();
  JButton myButton = new JButton("Click me");
  myFrame.add(myButton);
  class ClickListener implements ActionListener
  {
      public void actionPerformed(ActionEvent event)
      {System.out.println("I was clicked!");}
  }
  ActionListener myListener = new ClickListener();
  myButton.addActionListener(myListener);
  myFrame.setSize(100,60);
  myFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  myFrame.setVisible(true);
 }
}
