package ui;

import core.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Graphical User Interface for the Connect 5 Game
 *
 * @author Nick Alexander
 * @version 5-11-14
 */
public class Game extends JFrame {

    /**
     * Controller Handling the Game
     */
    private Controller controller;
    /**
     * Two Dimensional Array of Buttons Representing Game Tokens
     */
    private JButton[][] buttons;
    /**
     * Label to Display Game Status
     */
    private JLabel statusLabel;

    /**
     * Creates a New Game Interface given a Controller
     * @param controller Game Controller
     */
    public Game(Controller controller)
    {
        this.controller = controller;
        init();
        this.setVisible(true);
    }

    /**
     * Initializes All User Interface Components and Starts the Game
     */
    private void init()
    {
        JPanel gamePanel = new JPanel();

        setTitle("Connect 5!");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(new Point(0, 0));
        add(gamePanel);

        gamePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        buttons = new JButton[this.controller.getBoardSize()][this.controller.getBoardSize()];
        for (int i = 0; i < buttons.length; i++)
        {
            for (int j = 0; j < buttons[i].length; j++)
            {
                buttons[i][j] = new JButton();
                if (i == buttons[i].length - 1)
                    buttons[i][j].setEnabled(true);
                else
                    buttons[i][j].setEnabled(false);
                buttons[i][j].setOpaque(true);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setPreferredSize(new Dimension(30, 30));
                buttons[i][j].addActionListener((evt) -> buttonPressed(evt));
            }
        }

        for (int i = 0; i < controller.getBoardSize(); i++)
        {
            for (int j = 0; j < controller.getBoardSize(); j++)
            {
                Insets inset;
                if (j != controller.getBoardSize() - 1)
                    inset = new Insets(0, 0, 0, 10);
                else
                    inset = new Insets(0, 0, 0, 0);
                c.insets = inset;

                gamePanel.add(buttons[i][j], c);

                c.gridx++;
            }
            c.gridx = 0;
            c.gridy++;
        }

        c.gridy++;
        c.gridx = 0;
        c.gridwidth = controller.getBoardSize();

        if (controller.getBoardSize() > 10)
            c.anchor = GridBagConstraints.CENTER;
        else
            c.anchor = GridBagConstraints.LINE_START;

        statusLabel = new JLabel("Current Player: " + controller.getCurrentPlayer().getName());
        statusLabel.setForeground(controller.getCurrentPlayer().getColor());
        gamePanel.add(statusLabel, c);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Activates Next Button in a Column After a Token is Placed
     */
    private void checkActivate()
    {
        int row = controller.getLastToken().getRow();
        int col = controller.getLastToken().getColumn();
        if (row != 0)
            buttons[row - 1][col].setEnabled(true);
    }

    /**
     * Returns Indeces of Last Button Pressed (Token Placed)
     * @param button Last Button Pressed (Token Placed)
     * @return Indeces of Last Button Pressed (Token Placed)
     */
    private int[] buttonToIndex(JButton button)
    {
        for (int i = 0; i < controller.getBoardSize(); i++)
        {
            for (int j = 0; j < controller.getBoardSize(); j++)
            {
                if (button.equals(buttons[i][j]))
                    return new int[]{i, j};
            }
        }
        return null;
    }

    /**
     * Handles Button Presses (Token Places)
     * @param evt Button Press Event
     */
    private void buttonPressed(ActionEvent evt)
    {
        JButton button = (JButton)evt.getSource();
        controller.placeToken(buttonToIndex(button)[1]);
        checkActivate();
        button.setBackground(controller.getCurrentPlayer().getColor());
        button.setEnabled(false);
        if (this.controller.checkWinner())
        {
            winner();
            return;
        }
        controller.nextPlayer();
        statusLabel.setForeground(controller.getCurrentPlayer().getColor());
        statusLabel.setText("Current Player: " + controller.getCurrentPlayer().getName());
    }

    /**
     * Display a New Interface when Winning Chain is Found
     */
    private void winner()
    {
        this.setEnabled(false);
        JFrame frame = new JFrame("Winner!");
        frame.setSize(this.getSize());
        frame.setLocation(this.getX(), this.getY());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label = new JLabel();
        label.setForeground(controller.getCurrentPlayer().getColor());
        label.setText(controller.getCurrentPlayer().getName() + " Wins!");
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(label, c);

        JButton quit = new JButton("Quit");
        quit.addActionListener((ActionEvent e) ->
        {
            System.exit(0);
        });
        c.insets = new Insets(20, 0, 0, 0);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 1;
        panel.add(quit, c);

        JButton playAgain = new JButton("Play Again!");
        playAgain.addActionListener((ActionEvent e) ->
        {
            this.dispose();
            frame.dispose();
            Game game = new Game(controller.reset());
        });
        c.gridx++;
        panel.add(playAgain, c);

        frame.add(panel);
        frame.setVisible(true);
    }
}