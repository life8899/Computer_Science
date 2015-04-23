import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class HelloWorldGUI
{
  private static final int FRAME_WIDTH = 500, FRAME_HEIGHT = 100;
  public static void main(String[] args)
  {
    JFrame myFrame = new JFrame("GUI Hello World");
    JPanel myPanel = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    final JLabel myLabel = new JLabel();
    c.ipadx= 100; c.gridx = 0; c.gridy = 0;
    myPanel.add(myLabel, c);
    JButton myButton = new JButton("Click Here");
    c.gridx = 0; c.gridy = 1;
    myPanel.add(myButton, c);
    JButton newButton = new JButton("Clear Text!");
    c.gridx = 1; c.gridy = 1;
    myPanel.add(newButton, c);
    myFrame.add(myPanel);
    myFrame.pack();
    myFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    myFrame.setVisible(true);
    class ButtonClickListener implements ActionListener
    {
      public void actionPerformed(ActionEvent e)
      {
        myLabel.setText("Hello, World!");
      }
    }
    ActionListener myListener = new ButtonClickListener();
    class ClearClickListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {myLabel.setText("");}
    }
    ActionListener newListener = new ClearClickListener();
    newButton.addActionListener(newListener);
    myButton.addActionListener(myListener);
  }
}
