package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import core.Controller;
import javax.swing.table.*;

/**
 * Creates a User Interface to Initialize Game Settings
 *
 * @author Nick Alexander
 * @version 5-11-14
 */
public class Setup extends JFrame {

    /**
     * Variable Declarations
     */
    private JComboBox boardLengthCombo;
    private JLabel boardSizeLabel;
    private JComboBox boardWidthCombo;
    private JLabel byLabel;
    private JScrollPane tableScrollPane;
    private JComboBox numPlayersCombo;
    private JLabel numPlayersLabel;
    private JTable playersTable;
    private JButton randomizeButton;
    private JButton startButton;
    private JTextField updateNameTxt;
    private JButton updatePlayerButton;
    private JTextField updateTurnTxt;
    private JLabel welcomeLabel;

    /**
     * Insertion Point for the Game
     * @param args Runtime Arguments
     */
    public static void main(String args[])
    {
        new Setup().setVisible(true);
    }

    /**
     * Initializes a New Setup Interface
     */
    public Setup()
    {
        initComponents();
    }

    /**
     * Action Listener to Synchronize Board Size Combo Boxes
     */
    private class ComboSync implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox)e.getSource();
            if (combo.equals(boardLengthCombo))
                boardWidthCombo.setSelectedIndex(combo.getSelectedIndex());
            else
                boardLengthCombo.setSelectedIndex(combo.getSelectedIndex());
        }
    }

    /**
     * Action Listener that Updates Player when Enter Key is Pressed while Editing Player
     */
    private class TxtKeyListener implements KeyListener
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            if (e.getKeyCode()==KeyEvent.VK_ENTER)
            {
                if (!updateNameTxt.getText().equals("") || !updateTurnTxt.getText().equals(""))
                {
                    updatePlayerButton.doClick();
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {}

    }

    /**
     * Initializes all GUI Components
     */
    private void initComponents()
    {

        welcomeLabel = new JLabel();
        boardWidthCombo = new JComboBox();
        boardSizeLabel = new JLabel();
        boardLengthCombo = new JComboBox();
        byLabel = new JLabel();
        numPlayersLabel = new JLabel();
        numPlayersCombo = new JComboBox();
        tableScrollPane = new JScrollPane();
        playersTable = new JTable();
        playersTable.setModel(new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        randomizeButton = new JButton();
        updateNameTxt = new JTextField();
        updateTurnTxt = new JTextField();
        updatePlayerButton = new JButton();
        startButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Setup");
        setAlwaysOnTop(true);
        setLocation(new Point(0, 0));
        setMinimumSize(new Dimension(300, 400));
        setName("setup"); // NOI18N
        setResizable(false);

        welcomeLabel.setFont(new Font("Lucida Grande", 0, 18)); // NOI18N
        welcomeLabel.setForeground(Color.blue);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setText("Welcome to Connect 5!");
        welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        boardWidthCombo.setModel(new DefaultComboBoxModel(new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        boardWidthCombo.setToolTipText("Size of the Game Board");
        boardWidthCombo.setName("boardWidthCombo"); // NOI18N
        boardWidthCombo.addActionListener(new ComboSync());

        boardSizeLabel.setText("Board Size:");
        boardSizeLabel.setName("boardSizeLabel"); // NOI18N

        boardLengthCombo.setModel(new DefaultComboBoxModel(new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
        boardLengthCombo.setToolTipText("Size of the Game Board");
        boardLengthCombo.setName("boardLengthCombo"); // NOI18N
        boardLengthCombo.addActionListener(new ComboSync());

        byLabel.setText("x");
        byLabel.setName("xLabel"); // NOI18N

        numPlayersLabel.setText("Number of Players:");
        numPlayersLabel.setName("numPlayersLabel"); // NOI18N

        numPlayersCombo.setModel(new DefaultComboBoxModel(new String[] { "2", "3", "4", "5" }));
        numPlayersCombo.setToolTipText("Number of Players Playing");
        numPlayersCombo.setName("numPlayersCombo"); // NOI18N
        numPlayersCombo.addActionListener((ActionEvent evt) -> numPlayersComboActionPerformed(evt));

        playersTable.setFont(new Font("Lucida Grande", 0, 18)); // NOI18N
        playersTable.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Name", "Turn Order"
            }
        )
        {
            Class[] types = new Class []
            {
                String.class, Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false
            };

            @Override
            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        playersTable.setToolTipText("Players");
        playersTable.setColumnSelectionAllowed(true);
        playersTable.setGridColor(Color.lightGray);
        playersTable.setName("playersTable"); // NOI18N
        playersTable.setRowHeight(20);
        playersTable.setShowGrid(true);
        playersTable.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerer = new DefaultTableCellRenderer();
        centerer.setHorizontalAlignment(JLabel.CENTER);
        playersTable.getColumnModel().getColumn(1).setCellRenderer(centerer);
        numPlayersCombo.setSelectedIndex(0);
        playersTable.setAutoCreateRowSorter(true);
        playersTable.addMouseListener(new MouseAdapter()
        {
            public void mouseReleased(MouseEvent evt)
            {
                playersTableMouseReleased(evt);
            }
        });
        tableScrollPane.setViewportView(playersTable);
        playersTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (playersTable.getColumnModel().getColumnCount() > 0)
        {
            playersTable.getColumnModel().getColumn(0).setResizable(false);
            playersTable.getColumnModel().getColumn(0).setPreferredWidth(195);
            playersTable.getColumnModel().getColumn(1).setResizable(false);
            playersTable.getColumnModel().getColumn(1).setPreferredWidth(90);
        }

        randomizeButton.setForeground(Color.magenta);
        randomizeButton.setText("Randomize");
        randomizeButton.setToolTipText("Randomly Select Turn Order");
        randomizeButton.setName("randomizeButton"); // NOI18N
        randomizeButton.addActionListener((ActionEvent evt) -> randomizeButtonActionPerformed(evt));

        updateNameTxt.setToolTipText("Update Player Name");
        updateNameTxt.setDragEnabled(false);
        updateNameTxt.addKeyListener(new TxtKeyListener());
        updateNameTxt.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                updateNameTxtMouseReleased(evt);
            }
        });

        updateTurnTxt.setHorizontalAlignment(JTextField.CENTER);
        updateTurnTxt.setToolTipText("Update Turn Order");
        updateTurnTxt.addKeyListener(new TxtKeyListener());
        updateTurnTxt.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                updateTurnTxtMouseReleased(evt);
            }
        });

        updatePlayerButton.setText("Update Player");
        updatePlayerButton.setFocusPainted(false);
        updatePlayerButton.setFocusable(false);
        updatePlayerButton.setRequestFocusEnabled(false);
        updatePlayerButton.setVerifyInputWhenFocusTarget(false);
        updatePlayerButton.addActionListener((ActionEvent evt) -> updatePlayerButtonActionPerformed(evt));

        startButton.setForeground(new Color(0, 210, 0));
        startButton.setText("Start Game!");
        startButton.addActionListener((ActionEvent evt) -> startButtonActionPerformed(evt));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(welcomeLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(boardSizeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(boardLengthCombo, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPlayersLabel)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(byLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(numPlayersCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boardWidthCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(randomizeButton)
                        .addGap(18, 18, 18)
                        .addComponent(updatePlayerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateNameTxt, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateTurnTxt, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(boardSizeLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                    .addComponent(boardWidthCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(boardLengthCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(byLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(numPlayersLabel)
                    .addComponent(numPlayersCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(updateNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateTurnTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(randomizeButton)
                    .addComponent(updatePlayerButton))
                .addGap(12, 12, 12)
                .addComponent(startButton, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Mouse Listener that Selects a Player from the Table
     * @param evt Mouse Event
     */
    private void playersTableMouseReleased(MouseEvent evt)
    {
        JTable table = (JTable)evt.getSource();
        int row = table.getSelectedRow();
        String name = (String)table.getValueAt(row, 0);
        int turn = (int)table.getValueAt(row, 1);
        updateNameTxt.setText(name);
        updateTurnTxt.setText(String.valueOf(turn));
    }

    /**
     * Mouse Listener that Removes Error Color when Clicking Name Text Field
     * @param evt Mouse Event
     */
    private void updateNameTxtMouseReleased(MouseEvent evt)
    {
        updateTurnTxt.setForeground(Color.BLACK);
    }

    /**
     * Mouse Listener that Removes Error Color when Clicking Turn Text Field
     * @param evt Mouse Event
     */
    private void updateTurnTxtMouseReleased(MouseEvent evt)
    {
        updateTurnTxt.setForeground(Color.BLACK);
    }

    /**
     * Action Listener for when Number of Players Combo Box Value is Changed
     * @param evt Action Event
     */
    private void numPlayersComboActionPerformed(ActionEvent evt)
    {
        JComboBox combo = (JComboBox)evt.getSource();
        int players = Integer.parseInt((String)combo.getSelectedItem());
        DefaultTableModel model = (DefaultTableModel)playersTable.getModel();
        Object[][] list = new Object[model.getRowCount()][model.getColumnCount()];

        if (model.getRowCount() > players)
        {
            for (int i = 0; i < model.getRowCount(); i++)
            {
                list[i][0] = model.getValueAt(i, 0);
                list[i][1] = model.getValueAt(i, 1);
            }

            while (model.getRowCount() != 0)
                model.removeRow(0);

            for (int i = 0; i < players; i++)
                model.addRow(list[i]);

            return;
        }

        for (int i = model.getRowCount(); i < players; i++)
        {
            if (i < players)
                model.addRow(new Object[]{"Player " + (i+1), i + 1});
        }
    }

    /**
     * Updates the Player Table with New Player Information from Text Fields
     * @param evt Action Event
     */
    private void updatePlayerButtonActionPerformed(ActionEvent evt)
    {
        try
        {
            String name = updateNameTxt.getText();
            int newTurn = Integer.parseInt(updateTurnTxt.getText());
            if (newTurn > Integer.parseInt((String)numPlayersCombo.getSelectedItem()) || newTurn < 1)
                throw new NumberFormatException();
            playersTable.setValueAt(name, playersTable.getSelectedRow(), 0);
            int oldTurn = (int)playersTable.getValueAt(playersTable.getSelectedRow(), 1);
            for (int i = 0; i < playersTable.getRowCount(); i++)
            {
                int loopTurn = (int)playersTable.getValueAt(i, 1);
                if (loopTurn == newTurn)
                {
                    playersTable.setValueAt(newTurn, playersTable.getSelectedRow(), 1);
                    playersTable.setValueAt(oldTurn, i, 1);
                    break;
                }
            }
            if ((int)playersTable.getValueAt(playersTable.getSelectedRow(), 1) == oldTurn)
                playersTable.setValueAt(newTurn, playersTable.getSelectedRow(), 1);
        } catch (NumberFormatException e)
        {
            updateTurnTxt.setForeground(Color.RED);
        }
    }

    /**
     * Randomizes the Turn Order of the Players in the Players Table
     * @param evt Action Event
     */
    private void randomizeButtonActionPerformed(ActionEvent evt)
    {
        playersTable.clearSelection();
        updateNameTxt.setText("Player Name");
        updateTurnTxt.setText("0");
        Random random = new Random();
        int players = Integer.parseInt((String)numPlayersCombo.getSelectedItem());
        int[] turnsUsed = new int[players];
        for (Integer i = 0; i < players; i++)
        {
            int newTurn;
            do
            {
                newTurn = random.nextInt(players) + 1;
            } while (intIsInList(newTurn, turnsUsed));
            playersTable.setValueAt(newTurn, i, 1);
            turnsUsed[i] = newTurn;
        }
    }

    /**
     * Starts the Game
     * @param evt Action Event
     */
    private void startButtonActionPerformed(ActionEvent evt)
    {
        int boardSize = Integer.parseInt((String)boardWidthCombo.getSelectedItem());
        int players = Integer.parseInt((String)numPlayersCombo.getSelectedItem());
        Controller controller = new Controller(boardSize, players);
        for (int i = 0; i < playersTable.getRowCount(); i++)
        {
            String name = (String)playersTable.getValueAt(i, 0);
            int turn = (int)playersTable.getValueAt(i, 1);
            controller.addPlayer(name, turn);
        }
        Game game = new Game(controller);
        this.dispose();
    }

    /**
     * Returns true if the Target int is in the Array
     * @param target Target int
     * @param list Search List
     * @return true if the Target int is in the Array
     */
    private static boolean intIsInList(int target, int[] list)
    {
        for (int i : list)
        {
            if (i == target)
                return true;
        }
        return false;
    }
}