package maze;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import java.util.ArrayList;

/**
 * Module 3
 *
 * Graphical User Interface for the Maze Application
 *
 * @author Nick Alexander
 *
 * @version 3/22/14
 */
public class MazeApp extends JFrame {

    /**
     * JPanel on which Maze is Drawn
     */
    private class MazePanel extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent((Graphics2D) g);
            if (isProper())
            {
                paintMaze((Graphics2D) g);
            }
            else
                drawGrid((Graphics2D) g);
        }

        /**
         * Sets ToolTip Text to Show Coordinates of Selected Space
         *
         * @param e MouseEvent
         */
        @Override
        public String getToolTipText(MouseEvent e)
        {
            if (isProper())
            {
                int x = (e.getX() / size) + 1;
                int y = (e.getY() / size) + 1;
                return "(" + String.valueOf(x) + ", " + String.valueOf(y) + ")";
            }
            else
                return "";
        }
    }

    /**
     * Swing Timer to Animate Solution
     */
    private class MazeTimer extends Timer
    {
        private int solutionPointsToPaint;

        /**
         * Creates a Swing Timer
         *
         * @param delay Milliseconds Between Timer Ticks
         * @param listener ActionListener Attached to Timer Tick
         */
        public MazeTimer(int delay, ActionListener listener)
        {
            super(delay, listener);
            this.solutionPointsToPaint = 0;
        }

        /**
         * Sets the Number of Solution Points to Paint onto the Maze
         *
         * @param points Number of Solution Points to Paint
         */
        public void setSolutionPointsToPaint(int points)
        {
            this.solutionPointsToPaint = points;
        }

        /**
         * Returns the Number of Solution Points to Paint onto the Maze
         *
         * @return Number of Solution Points to Paint onto the Maze
         */
        public int getSolutionPointsToPaint()
        {
            return this.solutionPointsToPaint;
        }

        /**
         * Stops the Timer and Sets "Solved" to True
         */
        @Override
        public void stop()
        {
            System.out.println("Timer Stopped");
            solved = true;
            super.stop();
        }
    }

    private final int size = 20;

    private MazeReader reader;
    private MazeSolver solver;
    private ArrayList<Point3D> path;
    private boolean solving;
    private MazeTimer timer = new MazeTimer(0, new ActionListener(){
        @Override
        /**
         * Swing Timer to Trigger Solution Painting Animation
         *
         * @param e Timer Tick
         */
        public void actionPerformed(ActionEvent e)
        {
            MazeTimer tmp = (MazeTimer)e.getSource();
            if (tmp.getSolutionPointsToPaint() < path.size())
                tmp.setSolutionPointsToPaint(tmp.getSolutionPointsToPaint() + 1);
            else
                timer.stop();
            mazePanel.repaint();
            }
    });
    private static boolean solved;

    /**
     * Creates a JFrame and Initializes All GUI Components
     */
    public MazeApp()
    {
        this.reader = null;
        this.solver = null;
        this.path = new ArrayList<>();
        this.solving = false;
        initComponents();
    }

    /**
     * Creates a new Maze Reader
     * Factored Out for Error Handling
     *
     * @param file Maze File to Read
     */
    private void initMazeReader(File file)
    {
        try
        {
            this.reader = new MazeReader(file);
        } catch (Exception e) {
            //Do Nothing, Errors Handled Elsewhere.
        }
    }

    /**
     * Populates Floors Combo Box with Appropriate Number of Floors
     */
    private void populateFloorsComboBox()
    {
        String[] floorStrings = new String[this.reader.getFloors()];
        for (int floor = 0; floor < this.reader.getFloors(); floor++)
            floorStrings[floor] = "Floor " + (floor + 1);
        this.floorsComboBox.setModel(new JComboBox(floorStrings).getModel());
    }

    /**
     * Paints the Maze onto the Panel
     *
     * @param g Graphics Object
     */
    private void paintMaze(Graphics2D g)
    {
        try
        {
            int floor = this.getSelectedFloor();
            for (int col = 0; col < reader.getWidth(); col++)
            {
                for (int row = 0; row < reader.getLength(); row++)
                {
                    if (reader.check(row, col, floor) == MazeConstruct.START)
                        g.setColor(Color.CYAN);
                    else if (reader.check(row, col, floor) == MazeConstruct.FINISH)
                        g.setColor(Color.RED);
                    else if (reader.check(row, col, floor) == MazeConstruct.WALL)
                        g.setColor(Color.BLACK);
                    else if (reader.check(row, col, floor) == MazeConstruct.ELEVATOR)
                        g.setColor(Color.YELLOW);
                    else
                        g.setColor(Color.GRAY);
                    g.fillRect(row * size, col * size, size, size);
                    if (solving)
                    {
                        for (int i = 0; i < timer.getSolutionPointsToPaint(); i++)
                        {
                            int x = (int)path.get(i).x;
                            int y = (int)path.get(i).y;
                            int z = this.getSelectedFloor();
                            this.messagesLabel.setText(this.getSolvingText(x, y, (int)path.get(i).z));
                            if (z == (int)path.get(i).z)
                            {
                                if (reader.check(x, y, z) == MazeConstruct.TRAVERSED)
                                    g.setColor(Color.BLUE);
                                else if (reader.check(x,y,z) == MazeConstruct.SOLUTION)
                                    g.setColor(Color.GREEN);
                                else if (reader.check(x, y, z) == MazeConstruct.ELEVATOR)
                                    g.setColor(Color.YELLOW);
                                else if (reader.check(x, y, z) == MazeConstruct.FINISH)
                                    g.setColor(Color.RED);
                                g.fillRect(x * size, y * size, size, size);
                                g.setColor(Color.DARK_GRAY);
                                g.drawRect(x * size, y * size, size, size);
                            }
                            else
                            {
                                mazePanel.repaint();
                            }
                        }
                    }
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(row * size, col * size, size, size);
                }
            }
        } catch (IllegalComponentStateException e) {
            //Do Nothing, Irrelevant Error
        }
    }

    /**
     * Draws Grid onto Panel
     *
     * @param g Graphics Object
     */
    private void drawGrid(Graphics2D g)
    {
        g.setColor(Color.DARK_GRAY);
        for (int col = 0; col < 25; col++)
        {
            for (int row = 0; row < 25; row++)
            {
                g.drawRect(row * size, col * size, size, size);
            }
        }
    }

    /**
     * Converts Point3D Into String
     * Appropriated for Array Index
     *
     * @return Appropriated Point3D String
     */
    private String solutionPointToString()
    {
        Point3D p = solver.getSolutionPoint();
        return "(" + ((int)p.x + 1) + ", " + ((int)p.y + 1) + ", " + ((int)p.z + 1) + ")";
    }

    /**
     * Determines Starting Point of the Maze
     * and Sets Check Panel Accordingly
     */
    private void getStartingPoint()
    {
        try {
            int[] p = this.reader.getStartingPoint();
            this.checkX.setText(String.valueOf(p[0] + 1));
            this.checkY.setText(String.valueOf(p[1] + 1));
            this.checkFloor.setText(String.valueOf(p[2] + 1));
            this.checkResult.setText("START");
        } catch (MazeFormatException e) {
            JOptionPane.showMessageDialog(mazePanel, e.getMessage(), "Starting Point Not Found", JOptionPane.ERROR_MESSAGE);
        } //Half-Painted Bug
    }

    /**
     * Returns Current Selected Floor from Combo Box
     *
     * @return Current Selected Floor
     */
    private int getSelectedFloor()
    {
        return floorsComboBox.getSelectedIndex();
    }

    /**
     * Returns Message Text for Solving Process
     *
     * @param row current Row
     * @param col current Column
     * @param floor current Floor
     *
     * @return Text for Message Label
     */
    private String getSolvingText(int row, int col, int floor)
    {
        if (solving && timer.getSolutionPointsToPaint() < path.size())
            return "(" + row + ", " + col + ", " + (floor + 1) +") | " + getAnimationSpeedString() + " | " + solutionPointToString();
        else
            return "Solved!";
    }

    /**
     * Returns Animation Speed as a String for Message Label
     *
     * @return Animation Speed as a String Message Label
     */
    private String getAnimationSpeedString()
    {
        double tmp = getAnimationSpeed() / 1000.0;
        if (tmp == 0)
            return "No Delay between Steps";
        else
        {
            String sec = (tmp == 1) ? " Second " : " Seconds ";
            return String.valueOf(tmp) + sec + "between Steps";
        }

    }

    /**
     * Returns if Maze is Loaded & is Formatted Properly
     *
     * @return True if Maze is Loaded & Formatted Properly
     */
    private boolean isProper()
    {
        if (reader == null)
            return false;
        else if (!reader.isFormattedProperly())
            return false;
        return true;
    }

    /**
     * Returns Animation Speed from Slider
     *
     * @return Animation Speed from Slider
     */
    private int getAnimationSpeed()
    {
        if (this.animationSpeedSlider.getValue() == 0)
            return 0;
        return -(this.animationSpeedSlider.getValue());
    }

    /**
     * Checks Specified Location on Click
     *
     * @param evt Check Button Clicked
     */
    private void checkButtonActionPerformed(ActionEvent evt)
    {
        if (isProper())
        {
            String message = "Coordinates Must be Integer Values.";
            String title  = "Non-Integer Coordinates";
            try
            {
                int x = Integer.parseInt(this.checkX.getText()) - 1;
                int y = Integer.parseInt(this.checkY.getText()) - 1;
                int z = Integer.parseInt(this.checkFloor.getText()) - 1;
                if (x < 0 || y < 0 || z < 0)
                {
                    message = "Cooridinates Must Be Positive Integers.";
                    title = "Negative Coordinates";
                    throw new NumberFormatException();
                } else if ((x + 1) > this.reader.getLength() || (y + 1) > this.reader.getWidth() || (z + 1) > this.reader.getFloors())
                {
                    message = "Coordinates Out of Maze Bounds";
                    title = "Out of Bounds";
                    throw new NumberFormatException();
                }
                this.checkResult.setText(this.reader.check(x, y, z).name());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mazePanel, message, title, JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(mazePanel, "Maze File Not Loaded!\nOtherwise, Maze is Not Formatted Properly.", "Maze Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Loads a Maze File on Click
     *
     * @param evt Load Button Clicked
     */
    private void loadButtonActionPerformed(ActionEvent evt)
    {
        try
        {
            if (timer.isRunning())
                timer.stop();
            JFileChooser input = new JFileChooser(new File(".").getCanonicalPath());
            input.updateUI();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
            input.setFileFilter(filter);
            if (input.showOpenDialog(mazePanel) == 1)
                this.messagesLabel.setText("Maze File Load Cancelled");
            else
            {
                File mazeFile = input.getSelectedFile();
                this.initMazeReader(mazeFile);

                if (isProper())
                {
                    this.populateFloorsComboBox();
                    this.getStartingPoint();
                    this.messagesLabel.setText("Maze File Loaded");
                    //this.totalSpacesLabel.setText(String.valueOf(this.getTotalSpaces()));
                }
                mazePanel.repaint();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mazePanel, e.getMessage(), "File I/O Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Begins Solving the Maze
     *
     * @param evt Start Button Clicked
     */
    private void startButtonActionPerformed(ActionEvent evt)
    {
        if (isProper())
        {
            try
            {
                solver = new MazeSolver(this.reader);
                solver.solveMaze();
                if (!solved)
                {
                    Point3D p = solver.getSolutionPoint();
                    this.path = solver.getPath();
                    this.solutionPointLabel.setText("(" + String.valueOf((int)p.x + 1) + ", " + String.valueOf((int)p.y + 1) + ", " + String.valueOf((int)p.z + 1) + ")");
                    this.stepsToSolutionLabel.setText(String.valueOf(solver.getStepsToSolution()));
                    this.totalStepsLabel.setText(String.valueOf(solver.getStepsTaken()));
                    this.solvableLabel.setText(String.valueOf(solver.solvable()).substring(0, 1).toUpperCase() + String.valueOf(solver.solvable()).substring(1));
                }
                this.solving = true;
                timer.setInitialDelay(0);
                timer.setDelay(this.getAnimationSpeed());
                timer.start();
            } catch (NoSolutionException e) {
                JOptionPane.showMessageDialog(mazePanel, e.getMessage(), "No Solution", JOptionPane.INFORMATION_MESSAGE);
            } catch (MazeFormatException e) {
                JOptionPane.showMessageDialog(mazePanel, e.getMessage(), "Maze Format Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(mazePanel, "Load a Maze File to Continue.", "Load a Maze File", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Paints Newly Selected Floor
     * @param evt Floors ComboBox Selection Change Event
     */
    private void floorsComboBoxActionPerformed(ActionEvent evt)
    {
        mazePanel.repaint();
    }

    /**
     * Stops the Timer if Application is Closed
     *
     * @param evt Window Closing Event
     */
    private void mazeAppWindowClosed(WindowEvent evt)
    {
        timer.stop();
    }

    /**
     * Clears the Solution
     *
     * @param evt Clear Button Click
     */
    private void clearSolutionButtonActionPerformed(ActionEvent evt)
    {
        if (timer.isRunning())
            timer.stop();
        this.solving = false;
        timer.setSolutionPointsToPaint(0);
        this.messagesLabel.setText("Solution Cleared!");
        mazePanel.repaint();
    }

    /**
     * If Solving: Pauses the Timer when Floors ComboBox is Opened
     *
     * @param evt Floors ComboBox Opened Event
     */
    private void floorsComboBoxPopupMenuWillBecomeVisible(PopupMenuEvent evt)
    {
        if (timer.isRunning())
            timer.stop();
    }

    /**
     * If Solving: Resumes the Timer when Floors ComboBox is Closed
     *
     * @param evt Floors ComboBox Closed Event
     */
    private void floorsComboBoxPopupMenuWillBecomeInvisible(PopupMenuEvent evt)
    {
        if(solving)
            timer.start();
    }

    /**
     * Sets the Timer Delay to New Value
     *
     * @param evt Animation Speed Slider Value Changed
     */
    private void animationSpeedSliderStateChanged(ChangeEvent evt)
    {
        if (!animationSpeedSlider.getValueIsAdjusting())
            timer.setDelay(getAnimationSpeed());
    }

    /**
     * Main Method - Insertion Point
     *
     * @param args Runtime Arguments
     */
    public static void main(String args[])
    {
        new MazeApp().setVisible(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    /**
     * Initializes All GUI Components -- Generated by Netbeans IDE
     */
    private void initComponents()
    {

        checkPanel = new JPanel();
        checkFloorLabel = new JLabel();
        checkFloor = new JTextField();
        checkButton = new JButton();
        checkResult = new JLabel();
        checkYLabel = new JLabel();
        checkY = new JTextField();
        checkXLabel = new JLabel();
        checkX = new JTextField();
        floorsComboBox = new JComboBox();
        startButton = new JButton();
        mazeInfoPanel = new JPanel();
        staticSolvableLabel = new JLabel();
        solvableLabel = new JLabel();
        staticSolutionPointLabel = new JLabel();
        solutionPointLabel = new JLabel();
        staticStepsToSolutionLabel = new JLabel();
        stepsToSolutionLabel = new JLabel();
        staticTotalStepsLabel = new JLabel();
        totalStepsLabel = new JLabel();
        legendPanel = new JPanel();
        openLegendLabel = new JLabel();
        wallLegendLabel = new JLabel();
        startLegendLabel = new JLabel();
        finishLegendLabel = new JLabel();
        visitedLegendLabel = new JLabel();
        solutionLegendLabel = new JLabel();
        elevatorLegendLabel = new JLabel();
        loadButton = new JButton();
        messagesLabel = new JLabel();
        animationSpeedLabel = new JLabel();
        animationSpeedSlider = new JSlider();
        mazePanel = new MazePanel();
        slowLabel = new JLabel();
        fastLabel = new JLabel();
        clearSolutionButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MazeApp");
        setName("mazeApp"); // NOI18N
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosed(WindowEvent evt)
            {
                mazeAppWindowClosed(evt);
            }
        });

        checkPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Check Location"));
        checkPanel.setMaximumSize(new Dimension(140, 200));
        checkPanel.setPreferredSize(new Dimension(140, 200));
        checkPanel.setSize(new Dimension(140, 200));

        checkFloorLabel.setText("Floor:");

        checkFloor.setHorizontalAlignment(JTextField.CENTER);

        checkButton.setText("Check");
        checkButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                checkButtonActionPerformed(evt);
            }
        });

        checkResult.setHorizontalAlignment(SwingConstants.CENTER);
        checkResult.setText("-");

        checkYLabel.setText("Y:");

        checkY.setHorizontalAlignment(JTextField.CENTER);

        checkXLabel.setText("X:");

        checkX.setHorizontalAlignment(JTextField.CENTER);

        GroupLayout checkPanelLayout = new GroupLayout(checkPanel);
        checkPanel.setLayout(checkPanelLayout);
        checkPanelLayout.setHorizontalGroup(
            checkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(checkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(checkButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(checkResult, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(checkPanelLayout.createSequentialGroup()
                        .addComponent(checkFloorLabel)
                        .addGap(18, 18, 18)
                        .addComponent(checkFloor, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, checkPanelLayout.createSequentialGroup()
                        .addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(checkYLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkXLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(checkX, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkY, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        checkPanelLayout.setVerticalGroup(
            checkPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(checkPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(checkXLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkX))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(checkYLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkY))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(checkPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(checkFloorLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkFloor))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkResult)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkButton)
                .addContainerGap())
        );

        floorsComboBox.setModel(new DefaultComboBoxModel(new String[] { "Floor 0" }));
        floorsComboBox.setToolTipText("");
        floorsComboBox.addPopupMenuListener(new PopupMenuListener()
        {
            public void popupMenuWillBecomeVisible(PopupMenuEvent evt)
            {
                floorsComboBoxPopupMenuWillBecomeVisible(evt);
            }
            public void popupMenuWillBecomeInvisible(PopupMenuEvent evt)
            {
                floorsComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(PopupMenuEvent evt)
            {
            }
        });
        floorsComboBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                floorsComboBoxActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.setToolTipText("");
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                startButtonActionPerformed(evt);
            }
        });

        mazeInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Maze Information"));
        mazeInfoPanel.setMaximumSize(new Dimension(140, 275));
        mazeInfoPanel.setPreferredSize(new Dimension(140, 275));
        mazeInfoPanel.setSize(new Dimension(140, 275));

        staticSolvableLabel.setText("Solvable:");

        solvableLabel.setFont(new Font("Lucida Grande", 1, 13)); // NOI18N
        solvableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        solvableLabel.setText("-");

        staticSolutionPointLabel.setText("Solution Point:");

        solutionPointLabel.setHorizontalAlignment(SwingConstants.CENTER);
        solutionPointLabel.setText("-");

        staticStepsToSolutionLabel.setText("Steps to Solution:");

        stepsToSolutionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stepsToSolutionLabel.setText("-");

        staticTotalStepsLabel.setText("Total Steps Taken:");

        totalStepsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalStepsLabel.setText("-");

        GroupLayout mazeInfoPanelLayout = new GroupLayout(mazeInfoPanel);
        mazeInfoPanel.setLayout(mazeInfoPanelLayout);
        mazeInfoPanelLayout.setHorizontalGroup(
            mazeInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mazeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mazeInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(solvableLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staticSolutionPointLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(solutionPointLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stepsToSolutionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staticTotalStepsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalStepsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staticStepsToSolutionLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(staticSolvableLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mazeInfoPanelLayout.setVerticalGroup(
            mazeInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mazeInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(staticSolvableLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(solvableLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staticSolutionPointLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(solutionPointLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staticStepsToSolutionLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stepsToSolutionLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(staticTotalStepsLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalStepsLabel)
                .addContainerGap())
        );

        legendPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), "Legend"));

        openLegendLabel.setBackground(Color.GRAY);
        openLegendLabel.setForeground(new Color(255, 255, 255));
        openLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        openLegendLabel.setText("OPEN");
        openLegendLabel.setOpaque(true);

        wallLegendLabel.setBackground(Color.BLACK);
        wallLegendLabel.setForeground(new Color(255, 255, 255));
        wallLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wallLegendLabel.setText("WALL");
        wallLegendLabel.setOpaque(true);

        startLegendLabel.setBackground(Color.CYAN);
        startLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        startLegendLabel.setText("START");
        startLegendLabel.setOpaque(true);

        finishLegendLabel.setBackground(Color.BLUE);
        finishLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        finishLegendLabel.setText("FINISH");
        finishLegendLabel.setOpaque(true);

        visitedLegendLabel.setBackground(Color.PINK);
        visitedLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        visitedLegendLabel.setText("VISITED");
        visitedLegendLabel.setOpaque(true);

        solutionLegendLabel.setBackground(Color.GREEN);
        solutionLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        solutionLegendLabel.setText("SOLUTION");
        solutionLegendLabel.setOpaque(true);

        elevatorLegendLabel.setBackground(Color.YELLOW);
        elevatorLegendLabel.setHorizontalAlignment(SwingConstants.CENTER);
        elevatorLegendLabel.setText("ELEVATOR");
        elevatorLegendLabel.setOpaque(true);

        GroupLayout legendPanelLayout = new GroupLayout(legendPanel);
        legendPanel.setLayout(legendPanelLayout);
        legendPanelLayout.setHorizontalGroup(
            legendPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(legendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(legendPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(openLegendLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(wallLegendLabel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(startLegendLabel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(finishLegendLabel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(visitedLegendLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(solutionLegendLabel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(elevatorLegendLabel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                .addContainerGap())
        );
        legendPanelLayout.setVerticalGroup(
            legendPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(legendPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(wallLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(finishLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(elevatorLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(visitedLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(solutionLegendLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loadButton.setText("Load");
        loadButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                loadButtonActionPerformed(evt);
            }
        });

        messagesLabel.setFont(new Font("Lucida Grande", 1, 13)); // NOI18N
        messagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagesLabel.setText("Load a Maze to Begin");

        animationSpeedLabel.setFont(new Font("Lucida Grande", 0, 10)); // NOI18N
        animationSpeedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        animationSpeedLabel.setText("Animation Speed");

        animationSpeedSlider.setMajorTickSpacing(500);
        animationSpeedSlider.setMaximum(0);
        animationSpeedSlider.setMinimum(-2000);
        animationSpeedSlider.setMinorTickSpacing(500);
        animationSpeedSlider.setPaintTicks(true);
        animationSpeedSlider.setSnapToTicks(true);
        animationSpeedSlider.setValue(-1000);
        animationSpeedSlider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent evt)
            {
                animationSpeedSliderStateChanged(evt);
            }
        });

        mazePanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        mazePanel.setToolTipText("\"\"");
        mazePanel.setName(""); // NOI18N

        GroupLayout mazePanelLayout = new GroupLayout(mazePanel);
        mazePanel.setLayout(mazePanelLayout);
        mazePanelLayout.setHorizontalGroup(
            mazePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );
        mazePanelLayout.setVerticalGroup(
            mazePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        slowLabel.setFont(new Font("Lucida Grande", 0, 10)); // NOI18N
        slowLabel.setText("Slow");

        fastLabel.setFont(new Font("Lucida Grande", 0, 10)); // NOI18N
        fastLabel.setText("Fast");

        clearSolutionButton.setText("Clear Solution");
        clearSolutionButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                clearSolutionButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(floorsComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mazeInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(messagesLabel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mazePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(clearSolutionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(legendPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(slowLabel)
                                .addGap(18, 18, 18)
                                .addComponent(animationSpeedLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(fastLabel))
                            .addComponent(animationSpeedSlider, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messagesLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(floorsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(checkPanel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mazeInfoPanel, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE))
                    .addComponent(mazePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearSolutionButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(legendPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(animationSpeedSlider, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(slowLabel, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                                .addComponent(animationSpeedLabel, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
                                .addComponent(fastLabel, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))))
                    .addComponent(loadButton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold defaultstate="collapsed" desc="Variable Declarations">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel animationSpeedLabel;
    private JSlider animationSpeedSlider;
    private JButton checkButton;
    private JTextField checkFloor;
    private JLabel checkFloorLabel;
    private JPanel checkPanel;
    private JLabel checkResult;
    private JTextField checkX;
    private JLabel checkXLabel;
    private JTextField checkY;
    private JLabel checkYLabel;
    private JButton clearSolutionButton;
    private JLabel elevatorLegendLabel;
    private JLabel fastLabel;
    private JLabel finishLegendLabel;
    private JComboBox floorsComboBox;
    private JPanel legendPanel;
    private JButton loadButton;
    private JPanel mazeInfoPanel;
    private JPanel mazePanel;
    private JLabel messagesLabel;
    private JLabel openLegendLabel;
    private JLabel slowLabel;
    private JLabel solutionLegendLabel;
    private JLabel solutionPointLabel;
    private JLabel solvableLabel;
    private JButton startButton;
    private JLabel startLegendLabel;
    private JLabel staticSolutionPointLabel;
    private JLabel staticSolvableLabel;
    private JLabel staticStepsToSolutionLabel;
    private JLabel staticTotalStepsLabel;
    private JLabel stepsToSolutionLabel;
    private JLabel totalStepsLabel;
    private JLabel visitedLegendLabel;
    private JLabel wallLegendLabel;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}