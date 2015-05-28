//<editor-fold desc="Image Credits" defaultstate="collapsed">
// Coin Icon Courtesy of http://www.dryicons.com
// Under the Free for Non-Commercial Use License
// (Personally recolored into a Second .png for a Highlight Effect;
// Image Credit to Original Creator)
// 
// Checkmark, X, and Exclamation Point Icons
// Courtesy of Neurovit:
// http://neurovit.deviantart.com/
// Under Creative Commons License
//</editor-fold>
package Nim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * The Game of Nim User Interface
 *
 * @author Nick Alexander
 * @version Nov 27, 2013
 */
public final class GameInterface {
    //Variables that Require Accessing in the Game and AI Classes
    public static Game game;
    public static int stacks;
    public static int nimSum;
    public static JButton[][] buttons;
    
    //Variables that Require Accessing Throughout the GameInterface Class
    private static JButton confirm;
    private JLabel label;
    private int currentRow;
    
    /**
     * Creates a Game Interface that Contains All Buttons
     * @param game Game Associated with the Interface
     */
    public GameInterface(Game game)
    {
        GameInterface.game = game;
        this.currentRow = -1;
        initComponents();
    }
    
    /**
     * Initializes All Components for the Interface
     */
    public void initComponents()
    {
        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel(new GridBagLayout());
        Random random = new Random();
        int low = 3;
        int high = 11;
        stacks = random.nextInt(high - low) + low;

        //<editor-fold desc="Button Creation" defaultstate="collapsed">
        buttons = new JButton[stacks][10];
        for (int i = 0; i < stacks; i++)
        {
            int tokens = random.nextInt(10) + 1;
            for (int j = 0; j < tokens; j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setText(null);
                buttons[i][j].setIcon(new ImageIcon("resources/coin.png"));
                
                /**
                 * Button Click Listener for Each Token Button
                 */
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton button = (JButton) e.getSource();
                        GridBagLayout layout = (GridBagLayout)panel.getLayout();
                        GridBagConstraints c = layout.getConstraints(button);
                        boolean valid = checkMove(c.gridy);
                        if (valid)
                        {
                            button.setIcon(button.getIcon().toString().equals("resources/coin_hilite.png")
                                    ? new ImageIcon("resources/coin.png")
                                    : new ImageIcon("resources/coin_hilite.png"));
                            currentRow = c.gridy;
                        }
                    }
                });
            }
        }
        //</editor-fold>
        
        //<editor-fold desc="JPanel Setup" defaultstate="collapsed">
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        
        for (int i = 0; i < stacks; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (buttons[i][j] != null)
                {
                    panel.add(buttons[i][j], c);
                    c.gridx++;
                }
            }
            c.gridx = 0;
            c.gridy++;
        }
        
        c.gridy++;
        c.gridx = 0;
        confirm = new JButton();
        confirm.setIcon(new ImageIcon("resources/confirm.png"));
        
        /**
         * Button Click Listener for the Move Confirmation Button
         */
        confirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (currentRow == -1)
                {
                    JOptionPane.showMessageDialog(null, "No Tokens Chosen.\nPlease Select a Token",
                            "No Tokens", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                for (int i = 0; i < 10; i++)
                {
                    if (buttons[currentRow][i] != null)
                    {
                        if (buttonIsSelected(buttons[currentRow][i]))
                            buttons[currentRow][i].setVisible(false);
                    }
                }
                currentRow = -1;
                findNimSum();
                game.setHasWon(testWinner());
                //<editor-fold desc="Post-Game" defaultstate="collapsed">
                if (game.hasWon())
                {
                    Object[] customButtons = {"Play Again", "Exit"};
                    int value = JOptionPane.showOptionDialog(null, "Player " + game.getPlayer() + " Wins!",
                            "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, customButtons, customButtons[1]);
                    
                    if (value == 1)
                    {
                        System.exit(0);
                    }
                    else
                    {
                        frame.dispose();
                        String[] array = {""};
                        main(array);
                        return;
                    }
                }
                //</editor-fold>
                frame.repaint();
                game.nextPlayer();
                updateLabel();
            }
        });
        panel.add(confirm, c);
        
        c.gridx++;
        JButton clear = new JButton();
        clear.setIcon(new ImageIcon("resources/clear.png"));
        
        /**
         * Button Click Listener for the Clear Move Button
         */
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                currentRow = -1;
                for (int i = 0; i < stacks; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        if (buttons[i][j] != null)
                        {
                            buttons[i][j].setIcon(new ImageIcon("resources/coin.png"));
                        }
                    }
                }
            }
        });
        panel.add(clear, c);
        
        c.gridx++;
        JButton instructions = new JButton();
        instructions.setIcon(new ImageIcon("resources/message.png"));
        
        /**
         * Button Click Listener for the Show Instructions Button
         */
        instructions.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               showInstructions();
           }
        });
        panel.add(instructions, c);
        
        c.gridx++;
        label = new JLabel();
        updateLabel();
        panel.add(label, c);
        //</editor-fold>
        
        frame.setSize(800, 800);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        findNimSum();
        showInstructions();
        game.forceAIMove();
        
    }
    
    /**
     * Returns the Array of Token Buttons for the AI
     * 
     * @return 2D Array of JButtons containing the Token Buttons
     */
    public JButton[][] getButtonArray()
    {
        return this.buttons;
    }    
    
    /**
     * Returns the Nim Sum of the Board for the AI
     * 
     * @return Current Nim Sum of the Board
     */
    public int getNimSum()
    {
        return nimSum;
    }    
    
    /**
     * Shows the Instruction Dialog
     */
    public void showInstructions()
    {
        JOptionPane.showMessageDialog(null, "The Game of Nim is Played Horizontally.\n" +
                "That means that each ROW is a Heap and the Tokens stack left-to-right\n\n" +
                "The Objective of the Game is to Force Your Opponent to Take the Last Token!\n" +
                "You May Take Any Number of Tokens BUT Only from a Single Heap (Row).\n" +
                "The Current Player's Turn is Indiciated by the Label Next to the ! Button\n\n" +
                "Press the Checkmark to Confirm Move!\n" + 
                "Press the X to Clear All Tokens!\n" +
                "Press the ! to Show this Dialog Again", "Instuctions", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("resources/message.png"));
    }
    
    /**
     * Updates the Player Turn Label
     */
    public void updateLabel()
    {
        label.setText("Player " + game.getPlayer());
    }
    
    /**
     * Calculates the Nim-Sum for the Current Board
     * Stores into this.nimSum
     */
    public void findNimSum()
    {
        int numberInRow = 0;
        nimSum = 0;
        for (int i = 0; i < stacks; i++)
        {
            numberInRow = 0;
            for (int j = 0; j < 10; j++)
            {
                if (buttons[i][j] != null)
                {
                    if (buttons[i][j].isVisible())
                    {
                        numberInRow++;
                    }
                }
            }
            nimSum = nimSum ^ numberInRow;
        }
    }
    
    /**
     * Returns if a Button is Selected
     * 
     * @param button Button to Test is Selected
     * @return True/False of Selected
     */
    public boolean buttonIsSelected(JButton button)
    {
        return button.getIcon().toString().equals("resources/coin_hilite.png");
    }    
    
    /**
     * Determines if a Move is Valid
     * 
     * @param row Row of Prior Selected Token
     * @return True/False of Validity
     */
    public boolean checkMove(int row)
    {
        if (currentRow == row || currentRow == -1)
            return true;
        return false;
    }
    
    /**
     * Tests the Board for a Winner
     * 
     * @return True/False of a Winner
     */
    public boolean testWinner()
    {
        for (int i = 0; i < stacks; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (buttons[i][j] != null)
                {
                    if (buttons[i][j].isVisible())
                        return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Programmatically Presses the Move Confirmation Button for the AI
     */
    public static void pressConfirm()
    {
        confirm.doClick();
    }
    
    /**
     * Main Method - Insertion Point
     * 
     * @param args Runtime Arguments
     */
    public static void main(String[] args)
    {
        OpeningInterface opening = new OpeningInterface();
        opening.setVisible(true);
    }
}