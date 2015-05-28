/**
 *User Interface for Payday Interface
 * 
 * @author Nick Alexander
 * @version 11/5/13
 */

import java.util.ArrayList;

public class PaydayGUI extends javax.swing.JFrame {
    private ArrayList<Payday> list;

    /**
     * Creates new form PaydayGUI
     */
    public PaydayGUI() {
        initComponents();
        list = new ArrayList<Payday>();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        panelID = new javax.swing.JPanel();
        txtID = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        panelHour = new javax.swing.JPanel();
        lblHours = new javax.swing.JLabel();
        txtHours = new javax.swing.JTextField();
        panelSalary = new javax.swing.JPanel();
        lblSalary = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        btnHourly = new javax.swing.JButton();
        btnPT = new javax.swing.JButton();
        btnSalaried = new javax.swing.JButton();
        panelDisplay = new javax.swing.JPanel();
        btnDisplayHourly = new javax.swing.JButton();
        btnDisplayPT = new javax.swing.JButton();
        btnDisplayAll = new javax.swing.JToggleButton();
        cmbEmployees = new javax.swing.JComboBox();
        btnClearAll = new javax.swing.JButton();
        btnDeleteAll = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 600));
        setMinimumSize(new java.awt.Dimension(600, 600));

        panelID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panelID.setMaximumSize(new java.awt.Dimension(400, 50));
        panelID.setMinimumSize(new java.awt.Dimension(400, 50));
        panelID.setPreferredSize(new java.awt.Dimension(400, 50));

        txtID.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N

        lblID.setFont(new java.awt.Font("Helvetica", 0, 14)); // NOI18N
        lblID.setText("Employee ID");
        lblID.setPreferredSize(new java.awt.Dimension(100, 20));
        lblID.setSize(new java.awt.Dimension(45, 20));

        org.jdesktop.layout.GroupLayout panelIDLayout = new org.jdesktop.layout.GroupLayout(panelID);
        panelID.setLayout(panelIDLayout);
        panelIDLayout.setHorizontalGroup(
            panelIDLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panelIDLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .add(lblID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(64, 64, 64)
                .add(txtID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelIDLayout.setVerticalGroup(
            panelIDLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelIDLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelIDLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(txtID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .add(lblID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lblTitle.setFont(new java.awt.Font("Helvetica", 0, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Employee Tracker");

        panelHour.setBorder(javax.swing.BorderFactory.createTitledBorder("Hourly and Part-Time"));
        panelHour.setMaximumSize(new java.awt.Dimension(295, 100));
        panelHour.setSize(new java.awt.Dimension(295, 100));

        lblHours.setText("Hours Worked:");
        lblHours.setMaximumSize(new java.awt.Dimension(100, 15));
        lblHours.setMinimumSize(new java.awt.Dimension(100, 15));
        lblHours.setPreferredSize(new java.awt.Dimension(100, 15));
        lblHours.setSize(new java.awt.Dimension(50, 15));

        txtHours.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.jdesktop.layout.GroupLayout panelHourLayout = new org.jdesktop.layout.GroupLayout(panelHour);
        panelHour.setLayout(panelHourLayout);
        panelHourLayout.setHorizontalGroup(
            panelHourLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelHourLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblHours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(txtHours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHourLayout.setVerticalGroup(
            panelHourLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelHourLayout.createSequentialGroup()
                .add(23, 23, 23)
                .add(panelHourLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblHours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtHours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSalary.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salaried", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelSalary.setMaximumSize(new java.awt.Dimension(295, 100));
        panelSalary.setMinimumSize(new java.awt.Dimension(295, 100));
        panelSalary.setPreferredSize(new java.awt.Dimension(295, 100));
        panelSalary.setRequestFocusEnabled(false);

        lblSalary.setText("Salary:");
        lblSalary.setMaximumSize(new java.awt.Dimension(100, 15));
        lblSalary.setMinimumSize(new java.awt.Dimension(100, 15));
        lblSalary.setPreferredSize(new java.awt.Dimension(100, 15));
        lblSalary.setSize(new java.awt.Dimension(50, 15));

        txtSalary.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.jdesktop.layout.GroupLayout panelSalaryLayout = new org.jdesktop.layout.GroupLayout(panelSalary);
        panelSalary.setLayout(panelSalaryLayout);
        panelSalaryLayout.setHorizontalGroup(
            panelSalaryLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelSalaryLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 61, Short.MAX_VALUE)
                .add(txtSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelSalaryLayout.setVerticalGroup(
            panelSalaryLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelSalaryLayout.createSequentialGroup()
                .add(23, 23, 23)
                .add(panelSalaryLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(txtSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnHourly.setText("Add Hourly");
        btnHourly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHourlyActionPerformed(evt);
            }
        });

        btnPT.setText("Add Part-Time");
        btnPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPTActionPerformed(evt);
            }
        });

        btnSalaried.setText("Add Salaried");
        btnSalaried.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalariedActionPerformed(evt);
            }
        });

        panelDisplay.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clear and Display", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        panelDisplay.setMaximumSize(new java.awt.Dimension(150, 40));
        panelDisplay.setMinimumSize(new java.awt.Dimension(150, 40));
        panelDisplay.setPreferredSize(new java.awt.Dimension(150, 40));
        panelDisplay.setSize(new java.awt.Dimension(150, 40));

        btnDisplayHourly.setText("Display Hourly");
        btnDisplayHourly.setMaximumSize(new java.awt.Dimension(150, 40));
        btnDisplayHourly.setMinimumSize(new java.awt.Dimension(150, 40));
        btnDisplayHourly.setSize(new java.awt.Dimension(150, 40));
        btnDisplayHourly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayHourlyActionPerformed(evt);
            }
        });

        btnDisplayPT.setText("Display Part-Time");
        btnDisplayPT.setMaximumSize(new java.awt.Dimension(150, 40));
        btnDisplayPT.setMinimumSize(new java.awt.Dimension(150, 40));
        btnDisplayPT.setPreferredSize(new java.awt.Dimension(150, 40));
        btnDisplayPT.setSize(new java.awt.Dimension(150, 40));
        btnDisplayPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayPTActionPerformed(evt);
            }
        });

        btnDisplayAll.setText("Display All");
        btnDisplayAll.setMaximumSize(new java.awt.Dimension(150, 40));
        btnDisplayAll.setMinimumSize(new java.awt.Dimension(150, 40));
        btnDisplayAll.setPreferredSize(new java.awt.Dimension(150, 40));
        btnDisplayAll.setSize(new java.awt.Dimension(150, 40));
        btnDisplayAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayAllActionPerformed(evt);
            }
        });

        cmbEmployees.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Empty" }));
        cmbEmployees.setMaximumSize(new java.awt.Dimension(300, 30));
        cmbEmployees.setMinimumSize(new java.awt.Dimension(300, 30));
        cmbEmployees.setPreferredSize(new java.awt.Dimension(300, 30));
        cmbEmployees.setSize(new java.awt.Dimension(300, 30));

        btnClearAll.setText("Clear All Text Boxes");
        btnClearAll.setMaximumSize(new java.awt.Dimension(150, 40));
        btnClearAll.setMinimumSize(new java.awt.Dimension(150, 40));
        btnClearAll.setPreferredSize(new java.awt.Dimension(150, 40));
        btnClearAll.setSize(new java.awt.Dimension(150, 40));
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        btnDeleteAll.setText("Delete All Employees");
        btnDeleteAll.setMaximumSize(new java.awt.Dimension(150, 40));
        btnDeleteAll.setMinimumSize(new java.awt.Dimension(150, 40));
        btnDeleteAll.setPreferredSize(new java.awt.Dimension(150, 40));
        btnDeleteAll.setSize(new java.awt.Dimension(150, 40));
        btnDeleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAllActionPerformed(evt);
            }
        });

        lblStatus.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        lblStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatus.setText("Add an Employee");

        org.jdesktop.layout.GroupLayout panelDisplayLayout = new org.jdesktop.layout.GroupLayout(panelDisplay);
        panelDisplay.setLayout(panelDisplayLayout);
        panelDisplayLayout.setHorizontalGroup(
            panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelDisplayLayout.createSequentialGroup()
                .addContainerGap()
                .add(panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(btnDisplayAll, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnDisplayHourly, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(btnDisplayPT, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(112, 112, 112)
                .add(panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(btnClearAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(cmbEmployees, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnDeleteAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
            .add(panelDisplayLayout.createSequentialGroup()
                .add(12, 12, 12)
                .add(lblStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDisplayLayout.setVerticalGroup(
            panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panelDisplayLayout.createSequentialGroup()
                .add(25, 25, 25)
                .add(panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(btnDisplayHourly, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(cmbEmployees, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnDisplayPT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnClearAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(panelDisplayLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnDisplayAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnDeleteAll, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(100, 100, 100)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(panelID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(lblTitle, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(btnHourly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 40, Short.MAX_VALUE)
                                .add(btnPT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(5, 5, 5)
                                .add(panelHour, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(panelSalary, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(87, 87, 87)
                                .add(btnSalaried, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .add(5, 5, 5))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(panelDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(lblTitle)
                .add(18, 18, 18)
                .add(panelID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(panelSalary, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(panelHour, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnHourly)
                    .add(btnPT)
                    .add(btnSalaried))
                .add(18, 18, 18)
                .add(panelDisplay, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles Press of Add Hourly Employee
     * Adds an Hourly Employee to the List
     * @param evt Event Sent
     */
    private void btnHourlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHourlyActionPerformed
        //Read Hourse Worked
        //Read the ID
        //Create Hourly Employee Object
        //Add Object to List
        String id = txtID.getText();
        double hours = 0;
        try
        {
            hours = Double.parseDouble(txtHours.getText());
            list.add(new HourlyEmployee(id, hours));
            lblStatus.setText("Hourly Employee Added");
        } catch (Exception e)
        {
            lblStatus.setText("Invalid");
        }
    }//GEN-LAST:event_btnHourlyActionPerformed

    /**
     * Handles Press of Add Part-Time Employee
     * Adds a Part Time Employee to the List
     * @param evt Event Sent
     */
    private void btnPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPTActionPerformed
        //Read Hourse Worked
        //Read the ID
        //Create PT Employee Object
        //Add Object to List
        String id = txtID.getText();
        double hours = 0;
        try
        {
            hours = Double.parseDouble(txtHours.getText());
            list.add(new PartTimeEmployee(id, hours));
            lblStatus.setText("Part-Time Employee Added");
        } catch (Exception e)
        {
            lblStatus.setText("Invalid");
        }
    }//GEN-LAST:event_btnPTActionPerformed

    /**
     * Handles Press of Add Salaried Employee
     * Adds a Salaried Employee to the List
     * @param evt Event Sent
     */
    private void btnSalariedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalariedActionPerformed
        //Read Salary
        //Read the ID
        //Create Salary Employee Object
        //Add Object to List
        String id = txtID.getText();
        double salary = 0;
        try
        {
            salary = Double.parseDouble(txtSalary.getText().replace(",", ""));
            list.add(new SalariedEmployee(id, salary));
            lblStatus.setText("Salaried Employee Added");
        } catch (Exception e)
        {
            lblStatus.setText("Invalid");
        }
    }//GEN-LAST:event_btnSalariedActionPerformed

    /**
     * Handles Press of Display Hourly Employees
     * Displays Hourly Employees in Combo Box
     * @param evt Event Sent
     */
    private void btnDisplayHourlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayHourlyActionPerformed
        //Clear the Combo Box
        //Place all Hourly Employees in ComboBox using paystub
        cmbEmployees.removeAllItems();
        for (Payday obj : list)
        {
            if (obj instanceof HourlyEmployee)
                cmbEmployees.addItem((obj.payStub()));
        }
        
        if (cmbEmployees.getItemCount() == 0)
        {
            cmbEmployees.addItem("Empty");
        }
        lblStatus.setText("Displaying Hourly Employees");
    }//GEN-LAST:event_btnDisplayHourlyActionPerformed

    /**
     * Handles Press of Display Part-Time Employees
     * Displays Part Time Employees in Combo Box
     * @param evt Event Sent
     */
    private void btnDisplayPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayPTActionPerformed
        //Clear the Combo Box
        //Place all PT Employees in ComboBox using paystub
        boolean exist = false;
        cmbEmployees.removeAllItems();
        for (Payday obj : list)
        {
            if (obj instanceof PartTimeEmployee)
                cmbEmployees.addItem(obj.payStub());
        }
        
        if (cmbEmployees.getItemCount() == 0)
        {
            cmbEmployees.addItem("Empty");
        }
        lblStatus.setText("Displaying Part-Time Employees");
    }//GEN-LAST:event_btnDisplayPTActionPerformed

    /**
     * Handles Press of Display All Employees
     * Displays All Listed Employees in Combo Box
     * @param evt Event Sent
     */
    private void btnDisplayAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayAllActionPerformed
        //Clears the Combo Box
        //Place ALL employees in Combo box using paystub
        cmbEmployees.removeAllItems();
        
        for (Payday obj : list)
        {
            cmbEmployees.addItem(obj.payStub());
        }
        
        if (cmbEmployees.getItemCount() == 0)
        {
            cmbEmployees.addItem("Empty");
        }
        
        lblStatus.setText("Displaying All");
    }//GEN-LAST:event_btnDisplayAllActionPerformed

    /**
     * Handles Press of Clear All Button
     * Clears All Text Fields
     * @param evt Event Sent
     */
    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        //Set all text boxes to ""
        txtID.setText("");
        txtHours.setText("");
        txtSalary.setText("");
        lblStatus.setText("Cleared");
    }//GEN-LAST:event_btnClearAllActionPerformed

    /**
     * Handles Press of Delete All
     * Deletes All Employees in the List
     * @param evt Event Sent
     */
    private void btnDeleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAllActionPerformed
        //Clear the ArrayList
        for (int i = 0; i < list.size(); i++)
        {
            list.remove(i);
        }
        cmbEmployees.removeAllItems();
        cmbEmployees.addItem("Empty");
        lblStatus.setText("All Employees Deleted");
    }//GEN-LAST:event_btnDeleteAllActionPerformed

    /**
     * Main Method
     * @param args Runtime Arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PaydayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaydayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaydayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaydayGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PaydayGUI().setVisible(true);
            }
        });
    }
    
    // <editor-fold desc="Variable Declarations">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnDeleteAll;
    private javax.swing.JToggleButton btnDisplayAll;
    private javax.swing.JButton btnDisplayHourly;
    private javax.swing.JButton btnDisplayPT;
    private javax.swing.JButton btnHourly;
    private javax.swing.JButton btnPT;
    private javax.swing.JButton btnSalaried;
    private javax.swing.JComboBox cmbEmployees;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel lblHours;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblSalary;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel panelDisplay;
    private javax.swing.JPanel panelHour;
    private javax.swing.JPanel panelID;
    private javax.swing.JPanel panelSalary;
    private javax.swing.JTextField txtHours;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtSalary;
    // End of variables declaration//GEN-END:variables
    // </editor-fold>
}
