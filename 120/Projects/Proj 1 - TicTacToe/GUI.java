 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * GUI Class
 * Handles All Graphical User Interface Elements for TicTacToe
 * (e.g. Frame, Buttons, Label, etc.)
 * 
 * @author Nicholas Alexander
 * @version 10/21/13
 */
public class GUI {
	
	private static JButton buttonPressed;
	private static JButton[] buttons;
	private static JFrame frame;
	private static JLabel label;
	private final static JPanel PANEL = new JPanel(new GridLayout(6,5));
	private static final JPanel SPLASH = new JPanel(new GridLayout(4,3));
	public static int player;
	
	/**
	 * Instantiates a GUI Object that Initializes the Graphical User Interface
	 */
	public GUI()
	{
		initFrame();
		initSplash();
	}
	
	/**
	 * Initializes the Starting Splash Screen
	 */
	public static void initSplash()
	{
		JLabel splashLabel = new JLabel("Welcome!");
		splashLabel.setFont(new Font("Chalkboard", Font.BOLD, 100));
		JLabel info = new JLabel("Which Player Goes First?");
		info.setFont(new Font("Chalkboard", Font.BOLD, 40));

		final JButton player1 = new JButton("Human!");
		final JButton player2 = new JButton("Computer!");
		
		/**
		 * Inner Class Handling Splash Screen Buttons
		 *
		 */
		class SplashClick implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				//Sets Player to Opposite in order to Call Next Player
				//Calls nextPlayer to ensure Computer Moves First if Necessary
				if (e.getSource().equals(player1))
					Game.setPlayer(2);
				else
					Game.setPlayer(1);
				
				initButtons();
				initLabel();
				frame.remove(SPLASH);
				frame.add(PANEL);
				frame.setVisible(true);
				Game.nextPlayer();
			}
		}
		ActionListener splashClick = new SplashClick();
		
		player1.setFont(new Font("Chalkboard", Font.ITALIC, 80));
		player1.setForeground(Color.red);
		player1.addActionListener(splashClick);
		
		player2.setFont(new Font("Chalkboard", Font.ITALIC, 80));
		player2.setForeground(Color.blue);
		player2.addActionListener(splashClick);

		SPLASH.add(info);
		SPLASH.add(splashLabel, 0);
		SPLASH.add(player1);
		SPLASH.add(player2);
		frame.add(SPLASH);
		frame.setVisible(true);
	}
	
	/**
	 * Formats the Button with Relevant Color and Text
	 */
	public static void formatButton()
	{
		String s = Game.playerToString();
		buttonPressed.setForeground(getPlayerColor(Game.getPlayer()));
		buttonPressed.setText(s);
	}
	
	/**
	 * Formats the Label with Relevant Color and Text
	 */
	public static void formatLabel()
	{
		String s;
		Color color;
		if (Game.getPlayer() == 1)
		{
			s = "O's Turn!";
			color = Color.blue;
		}
		else
		{
			s = "X's Turn!";
			color = Color.red;
		}
		label.setForeground(color);
		label.setText(s);
	}
	
	/**
	 * Returns Button at Index
	 * @param button Index of Button
	 * @return JButton at Index
	 */
	public static JButton getButton(int button)
	{
		return buttons[button];
	}
	
	/**
	 * Returns Button Index from Row and Column
	 * @param row Row Containing Button
	 * @param col Column Containing Button
	 * @return Index of Button
	 */
	public static JButton getButtonIndex(int row, int col)
		{
			if (row == 0 && col == 0)
				return buttons[0];
			else if (row == 0 && col == 1)
				return buttons[1];
			else if (row == 0 && col == 2)
				return buttons[2];
			else if (row == 0 && col == 3)
				return buttons[3];
			else if (row == 0 && col == 4)
				return buttons[4];
			else if (row == 1 && col == 0)
				return buttons[5];
			else if (row == 1 && col == 1)
				return buttons[6];
			else if (row == 1 && col == 2)
				return buttons[7];
			else if (row == 1 && col == 3)
				return buttons[8];
			else if (row == 1 && col == 4)
				return buttons[9];
			else if (row == 2 && col == 0)
				return buttons[10];
			else if (row == 2 && col == 1)
				return buttons[11];
			else if (row == 2 && col == 2)
				return buttons[12];
			else if (row == 2 && col == 3)
				return buttons[13];
			else if (row == 2 && col == 4)
				return buttons[14];
			else if (row == 3 && col == 0)
				return buttons[15];
			else if (row == 3 && col == 1)
				return buttons[16];
			else if (row == 3 && col == 2)
				return buttons[17];
			else if (row == 3 && col == 3)
				return buttons[18];
			else if (row == 3 && col == 4)
				return buttons[19];
			else if (row == 4 && col == 0)
				return buttons[20];
			else if (row == 4 && col == 1)
				return buttons[21];
			else if (row == 4 && col == 2)
				return buttons[22];
			else if (row == 4 && col == 3)
				return buttons[23];
			else if (row == 4 && col == 4)
				return buttons[24];
			return buttons[0];
		}
	
	/**
	 * Extracts Column Index of Button from Array
	 * @param indeces Array of Indeces for the Button
	 * @return Column Index of Button
	 */
	public static int getColIndex(int[] indeces)
		{
			return indeces[1];
		}
		
	/**
	 * Converts Button to Indeces
	 * @param button Button to Convert
	 * @return Array of Indeces
	 */
	public static int[] buttonToIndeces(int button)
		{
			int[] indeces = new int[2];
			switch (button)
	        {
	            case 0: indeces[0] = 0;
	            		indeces[1] = 0;
	                    break;
	            case 1: indeces[0] = 0;
	            		indeces[1] = 1;
	                    break;
	            case 2: indeces[0] = 0; 
	            		indeces[1] = 2;
	                    break;
	            case 3: indeces[0] = 0;
	            		indeces[1] = 3;
	                    break;
	            case 4: indeces[0] = 0;
	            		indeces[1] = 4;
	                    break;
	            case 5: indeces[0] = 1;
	                    indeces[1] = 0;
	                    break;
	            case 6: indeces[0] = 1;
	                    indeces[1] = 1;
	                    break;
	            case 7: indeces[0] = 1;
	                    indeces[1] = 2;
	                    break;
	            case 8: indeces[0] = 1;
	                    indeces[1] = 3;
	                    break;
	            case 9: indeces[0] = 1;
	                    indeces[1] = 4;
	                    break;
	            case 10:indeces[0] = 2;
	                    indeces[1] = 0;
	                    break;
	            case 11:indeces[0] = 2;
	                    indeces[1] = 1;
	                    break;
	            case 12:indeces[0] = 2;
	                    indeces[1] = 2;
	                    break;
	            case 13:indeces[0] = 2;
	                    indeces[1] = 3;
	                    break;
	            case 14:indeces[0] = 2;
	                    indeces[1] = 4;
	                    break;
	            case 15:indeces[0] = 3;
	                    indeces[1] = 0;
	                    break;
	            case 16:indeces[0] = 3;
	                    indeces[1] = 1;
	                    break;
	            case 17:indeces[0] = 3;
	                    indeces[1] = 2;
	                    break;
	            case 18:indeces[0] = 3;
	                    indeces[1] = 3;
	                    break;
	            case 19:indeces[0] = 3;
	            		indeces[1] = 4;
	                    break;
	            case 20:indeces[0] = 4;
	            		indeces[1] = 0;
	                    break;
	            case 21:indeces[0] = 4;
	            		indeces[1] = 1;
	                    break;
	            case 22:indeces[0] = 4;
	            		indeces[1] = 2;
	                    break;
	            case 23:indeces[0] = 4;
	            		indeces[1] = 3;
	                    break;
	            case 24:indeces[0] = 4;
	                    indeces[1] = 4;
	                    break;
	        }
			return indeces;
		}
	
	/**
	 * Returns Color Object Associated with Corresponding Player
	 * (X's are Red & O's are Blue)
	 * @param player Corresponding Player
	 * @return Appropriate Color Object
	 */
	public static Color getPlayerColor(int player)
		{
			if (player == 1)
				return Color.red;
			else
				return Color.blue;
		}
	
	/**
	 * Extracts Row Index of Button from Array
	 * @param indeces Array of Indeces for the Button
	 * @return Row Index of Button
	 */
	public static int getRowIndex(int [] indeces)
		{
			return indeces[0];
		}
		
	/**
	 * Processes the Button Clicks
	 * @param button Button Clicked
	 */
	public static void processMove(int button)
		{
			if (!buttons[button].getText().equals(""))
			{
				JOptionPane.showMessageDialog(frame, "Invalid Move, Please Try Again!", "Invalid Move",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			else
			{
				int[] indeces = buttonToIndeces(button);
				Game.setRow(getRowIndex(indeces));
				Game.setCol(getColIndex(indeces));
				
				Game.setBoardValue(Game.getLastRow(), Game.getLastCol(), Game.getPlayer());
				formatButton();
				formatLabel();
				if (Game.testWinner(Game.getLastRow(), Game.getLastCol(), Game.getPlayer()))
				{
					Object[] options = {"No", "Yes!"};
					int a = JOptionPane.showOptionDialog(frame, Game.playerToString() + " Wins!" +
								"\nPlay Again?", "Winner!", JOptionPane.DEFAULT_OPTION, 
									JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if (a == 1)
						Game.playAgain();
					else
						System.exit(0);
				}
				else if (Game.testDraw())
				{
					Object[] options = {"No", "Yes!"};
					int a = JOptionPane.showOptionDialog(frame, "Draw!" + "\nPlayAgain?", "Draw",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
									options, options[0]);
					if (a == 1)
						Game.playAgain();
					else
						System.exit(0);
		
				}
				else
					Game.nextPlayer();
			}
		}
	
	/**
	 * Initializes Buttons to Empty
	 */
	public static void resetButtons()
		{
			for (JButton i : buttons)
			{
				i.setText("");
			}
		}
		
	/**
	 * Initializes Label to Default
	 */
	public static void resetLabel()
		{
			label.setForeground(getPlayerColor(Game.getPlayer()));
			label.setText(Game.playerToString() + "'s Turn!");
		}
		
	/**
	 * Repaints the Frame with Latest Information
	 */
	public static void updateGUI()
		{
			frame.repaint();
		}
			
	/**
	 * Instantiates each Button and Handles Button Click Actions
	 */
	public static void initButtons()
		{	
			buttons = new JButton[25];
			
			/**
			 *	Inner Class Handling Button Click Action Events
			 */
			class ButtonClick implements ActionListener
	        {
	            @Override
	            public void actionPerformed(ActionEvent e)
	            {
	            	for (int i = 0; i < buttons.length; i++)
	            		if (e.getSource().equals(buttons[i]))
	            		{
	            			buttonPressed = buttons[i];
	            			processMove(i);
	            		}
	            }
			}
			
			ActionListener buttonClick = new ButtonClick();
			for (int i = 0; i < (Game.ROWS * Game.COLS); i++)
			{
				buttons[i] = new JButton();
				buttons[i].setName("Button " + String.valueOf(i));
				buttons[i].setFont(new Font("Chalkboard", Font.PLAIN, 100));
				buttons[i].addActionListener(buttonClick);
				PANEL.add(buttons[i]);
			}
		}
		
	/**
	 * Initializes the Frame
	 */
	public static void initFrame()
		{
			frame = new JFrame();
			frame.setTitle("Tic Tac Toe");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(600, 600);
			frame.setAlwaysOnTop(true);
			frame.setResizable(false);
			frame.setVisible(true);
		}
		
	/**
	 * Initializes the Label
	 */
	public static void initLabel()
	{
		label = new JLabel();
		label.setText(Game.playerToString());
		formatLabel();
		PANEL.add(label);
	}
}