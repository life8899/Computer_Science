package TaxTracker;

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Tracks Income and Tax Information for Student Workers
 *
 * @author Nick Alexander
 * @author Brandon Houser
 * @version December 3, 2013
 */
public class TaxTracker extends javax.swing.JFrame
{
    private static ArrayList<Student> list = new ArrayList<>();
    private static DecimalFormat df = new DecimalFormat("#,##0.00");
    private static File sFile;
    private static DefaultTableModel tm;
    private Student student;
    private int row;
    private boolean shouldSortByID;

    /**
     * Creates new User Interface and Initializes Data
     */
    public TaxTracker() throws IOException, ClassNotFoundException
    {
        initComponents();
        this.shouldSortByID = true;
        tm = (DefaultTableModel) tblEmployees.getModel();
        getTableData();
        employees.setLocationRelativeTo(null);
        employees.setVisible(true);
    }

    /**
     * Extracts Data from Random Access File for the JTable
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void getTableData() throws IOException, ClassNotFoundException
    {
        sFile = new File("students.dat");
        if (sFile.exists())
        {
            FileInputStream inputStream = new FileInputStream(sFile);
            ObjectInputStream objectStream = new ObjectInputStream(inputStream);
            list = (ArrayList<Student>) objectStream.readObject();
            objectStream.close();
        }

        this.sortList();
    }

    /**
     * Displays Sorted Table Data
     */
    private void updateTableListing()
    {
        int i = 0;
        while (tm.getRowCount() != 0)
        {
            tm.removeRow(0);
        }

        for (i = 0; i < list.size(); i++)
        {
            Student student = list.get(i);
            tm.addRow(new Object[]
            {
                String.valueOf(student.getID()), student.getName(),
                student.getAddress(), String.valueOf((student.getHoursWorked())),
                String.valueOf(df.format(student.getNetIncome())), String.valueOf(df.format(student.getHourlyRate()))
            });
        }
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (i = 0; i < tm.getColumnCount(); i++)
        {
            tblEmployees.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    /**
     * Handles Double Click of a Student Worker in JTable
     *
     * @param evt Click Event
     */
    private void tblEmployeesMouseReleased(java.awt.event.MouseEvent evt)
    {//GEN-FIRST:event_tblEmployeesMouseReleased
        if (evt.getClickCount() == 2)
        {
            JTable table = (JTable) evt.getSource();
            row = table.getSelectedRow();

            int id = Integer.parseInt((String) tm.getValueAt(row, 0));
            String name = (String) tm.getValueAt(row, 1);
            String address = (String) tm.getValueAt(row, 2);
            String hoursString = (String) tm.getValueAt(row, 3);
            hoursString = hoursString.replaceAll(",", "");
            double hoursWorked = Double.parseDouble(hoursString);
            String rateString = (String) tm.getValueAt(row, 5);
            rateString = rateString.replaceAll(",", "");
            double rate = Double.parseDouble(rateString);
            student = new Student(id, name, address, hoursWorked, rate);

            //2 Decimals
            lblGrossIncome.setText("$" + String.valueOf(df.format(student.getGrossIncome())));
            lblFederalTaxTaken.setText("$" + String.valueOf(df.format(Taxes.computeFederalTax(student.getGrossIncome()))));
            lblStateTaxTaken.setText("$" + String.valueOf(df.format(Taxes.computeStateTax(student.getGrossIncome()))));
            lblSSTaxTaken.setText("$" + String.valueOf(df.format(Taxes.computeSocialSecurityTax(student.getGrossIncome()))));
            lblNetIncome.setText("$" + String.valueOf(df.format(student.getGrossIncome() - Taxes.computeTaxTaken(student.getGrossIncome()))));

            txtEditID.setText(String.valueOf(student.getID()));
            txtEditName.setText(student.getName());
            txtEditAddress.setText(student.getAddress());
            txtEditHours.setText(String.valueOf(student.getHoursWorked()));
            txtEditRate.setText(String.valueOf(student.getHourlyRate()));

            employees.setVisible(false);
            information.setLocationRelativeTo(null);
            information.setVisible(true);
        }
    }

    /**
     * Returns True/False if Students are Sorted by ID
     *
     * @return Boolean of Students are Sorted by ID
     */
    public boolean shouldSortByID()
    {
        return this.shouldSortByID;
    }

    /**
     * Sets True/False if Sorting should be Done via ID
     *
     * @param value True/False for Sorting by ID
     */
    public void setShouldSortByID(boolean value)
    {
        this.shouldSortByID = value;
    }

    /**
     * Checks Which Sorting Method to Use and Sorts
     */
    public void sortList()
    {
        if (this.shouldSortByID)
        {
            Collections.sort(list);
        }
        else
        {
            Collections.sort(list, new CompareStudentName());
        }
        updateTableListing();
    }

    /**
     * Saves the Data File when employees Window is Closing
     *
     * @param evt Window Closing Event
     */
    private void employeesWindowClosing(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_employeesWindowClosing
        try
        {
            FileOutputStream outputStream = new FileOutputStream(sFile);
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(list);
            objectStream.close();
        } catch (Exception e)
        {
        };
    }

    /**
     * Handles Click of New Employee Button
     *
     * @param evt Button Click Event
     */
    private void btnNewEmployeeActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_btnNewEmployeeActionPerformed
        dlgNewEmployee.setLocationRelativeTo(null);
        dlgNewEmployee.setVisible(true);
    }

    /**
     * Handles Click of the Delete Employee Button on Employee Form
     *
     * @param evt Click Event
     */
    private void btnDeleteEmployeeActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_btnDeleteEmployeeActionPerformed
        row = tblEmployees.getSelectedRow();
        if (row == -1)
        {
            JOptionPane.showMessageDialog(null, "No Employee Selected!", "No Employee Selected",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int n = JOptionPane.showConfirmDialog(null, "Permanently Delete Employee?",
                "Delete Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == 0)
        {
            int id = Integer.parseInt((String) tm.getValueAt(row, 0));
            String name = (String) tm.getValueAt(row, 1);
            String address = (String) tm.getValueAt(row, 2);
            double hours = Double.parseDouble((String) tm.getValueAt(row, 3));
            double rate = Double.parseDouble((String) tm.getValueAt(row, 5));
            Student delete = new Student(id, name, address, hours, rate);

            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).equals(delete))
                {
                    list.remove(i);
                    break;
                }
            }
            if (!list.isEmpty())
            {
                this.sortList();
            }

            tm.removeRow(row);
            updateTableListing();
        }
    }

    /**
     * Handles Changing of Checked Status
     *
     * @param evt Checked Status Change Event
     */
    private void chbSortNameItemStateChanged(java.awt.event.ItemEvent evt)
    {//GEN-FIRST:event_chbSortNameItemStateChanged
        JCheckBox sortName = (JCheckBox) evt.getSource();
        if (sortName.isSelected())
        {
            chbSortID.setSelected(false);
        }
        else if (!sortName.isSelected())
        {
            chbSortID.setSelected(true);
            this.setShouldSortByID(true);
        }
        sortList();
    }

    /**
     * Handles Changing of Checked Status
     *
     * @param evt Checked Status Change Event
     */
    private void chbSortIDItemStateChanged(java.awt.event.ItemEvent evt)
    {//GEN-FIRST:event_chbSortIDItemStateChanged
        JCheckBox sortID = (JCheckBox) evt.getSource();
        if (sortID.isSelected())
        {
            chbSortName.setSelected(false);
        }
        else if (!sortID.isSelected())
        {
            chbSortName.setSelected(true);
            this.setShouldSortByID(false);
        }
        sortList();
    }

    /**
     * Handles Click of the Create Employee Button of New Employee Dialog Box
     *
     * @param evt Button Click Event
     */
    private void btnNewEmployeeCreateActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_btnNewEmployeeCreateActionPerformed
        try
        {
            int id = Integer.parseInt(txtNewID.getText());
            if (String.valueOf(id).length() < 3 || String.valueOf(id).length() > 9
                    || !String.valueOf(id).substring(0, 3).equals("901"))
            {
                showInvalidInputDialog();
                return;
            }
            String name = txtNewName.getText();
            String address = txtNewAddress.getText();
            double hoursWorked = Double.parseDouble(txtNewHours.getText());
            double hourlyRate = Double.parseDouble(txtNewRate.getText());
            Student newStudent = new Student(id, name, address, hoursWorked, hourlyRate);
            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getID() == newStudent.getID())
                {
                    JOptionPane.showMessageDialog(null,
                            "Employee Already Exists", "Employee Exists", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            list.add(newStudent);
            this.sortList();
            tm.addRow(new Object[]
            {
                String.valueOf(newStudent.getID()), newStudent.getName(),
                newStudent.getAddress(), String.valueOf(newStudent.getHoursWorked()),
                String.valueOf(newStudent.getNetIncome()), String.valueOf(newStudent.getHourlyRate())
            });
            updateTableListing();

            dlgNewEmployee.setVisible(false);
            txtNewID.setText(null);
            txtNewName.setText(null);
            txtNewAddress.setText(null);
            txtNewHours.setText(null);
            txtNewRate.setText(null);
            dlgNewEmployee.dispose();
        } catch (NumberFormatException e)
        {
            showInvalidInputDialog();
        }
    }

    private void txtNewIDKeyReleased(java.awt.event.KeyEvent evt)
    {//GEN-HEADEREND:event_txtNewIDKeyReleased
        if (txtNewID.getText().length() != 9)
        {
            txtNewID.setForeground(Color.red);
        }
        else
        {
            txtNewID.setForeground(Color.black);
        }
    }

    /**
     * Saves the Data File when information Window is Closing
     *
     * @param evt Window Closing Event
     */
    private void informationWindowClosing(java.awt.event.WindowEvent evt)
    {//GEN-FIRST:event_informationWindowClosing
        try
        {
            FileOutputStream outputStream = new FileOutputStream(sFile);
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);
            objectStream.writeObject(list);
            objectStream.close();
        } catch (Exception e)
        {
        };
    }

    /**
     * Handles Click of the Save and Return Button on information Form
     *
     * @param evt Click Event
     */
    private void btnEditSaveActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_btnEditSaveActionPerformed
        try
        {
            int id = Integer.parseInt(txtEditID.getText());
            String name = txtEditName.getText();
            String address = txtEditAddress.getText();
            double hoursWorked = Double.parseDouble(txtEditHours.getText());
            double rate = Double.parseDouble(txtEditRate.getText());
            Student newStudent = new Student(id, name, address, hoursWorked, rate);

            for (int i = 0; i < list.size(); i++)
            {
                if (list.get(i).getID() == newStudent.getID() && !list.get(i).equals(newStudent))
                {
                    throw new Exception();
                }
            }

            tm.setValueAt(String.valueOf(id), row, 0);
            tm.setValueAt(name, row, 1);
            tm.setValueAt(address, row, 2);
            tm.setValueAt(String.valueOf(hoursWorked), row, 3);

            list.remove(row);
            list.add(row, newStudent);
            this.sortList();

            lblGrossIncome.setText("$" + String.valueOf(df.format(newStudent.getGrossIncome())));
            lblFederalTaxTaken.setText("$" + String.valueOf(df.format(Taxes.computeFederalTax(newStudent.getGrossIncome()))));
            lblStateTaxTaken.setText("$" + String.valueOf(df.format(Taxes.computeStateTax(newStudent.getGrossIncome()))));
            lblSSTaxTaken.setText("$" + String.valueOf(df.format(Taxes.computeSocialSecurityTax(newStudent.getGrossIncome()))));
            lblNetIncome.setText("$" + String.valueOf(df.format(newStudent.getGrossIncome() - Taxes.computeTaxTaken(newStudent.getGrossIncome()))));
        } catch (Exception e)
        {
            showInvalidInputDialog();
        }
    }

    /**
     * Handles Click of the Discard Changes Button on information Form
     *
     * @param evt Click Event
     */
    private void btnEditDiscardActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_btnEditDiscardActionPerformed
        int n = JOptionPane.showConfirmDialog(null, "Discard Changes?", "Discard Changes", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (n == 0)
        {
            txtEditID.setText(String.valueOf(student.getID()));
            txtEditName.setText(student.getName());
            txtEditAddress.setText(student.getAddress());
            txtEditHours.setText(String.valueOf(student.getHoursWorked()));
        }
    }

    /**
     * Handles Click of the Return Button on information Form
     *
     * @param evt Button Click Event
     */
    private void btnEditReturnActionPerformed(java.awt.event.ActionEvent evt)
    {//GEN-FIRST:event_btnEditReturnActionPerformed
        information.setVisible(false);
        employees.setLocationRelativeTo(null);
        employees.setVisible(true);
    }

    private void txtEditIDKeyReleased(java.awt.event.KeyEvent evt)
    {//GEN-HEADEREND:event_txtEditIDKeyReleased
        if (txtEditID.getText().length() != 9)
        {
            txtEditID.setForeground(Color.red);
        }
        else
        {
            txtEditID.setForeground(Color.black);
        }
    }

    /**
     * Shows an Error Box for Giving Invalid Input
     */
    public void showInvalidInputDialog()
    {
        JOptionPane.showMessageDialog(null, "One or More Entries are Invalid.\n"
                + "Please Try Again.", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Main Method -- Insertion Point
     *
     * @param args Runtime Arguments
     */
    public static void main(String args[])
    {
        //<editor-fold defaultstate="collapsed" desc=" Look and Feel">
        //If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        try
        {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e)
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    try
                    {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException | InstantiationException |
                            IllegalAccessException | UnsupportedLookAndFeelException ex)
                    {
                    }
                    break;
                }
            }
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            @SuppressWarnings("empty-statement")
            public void run()
            {
                try
                {
                    new TaxTracker();
                } catch (IOException | ClassNotFoundException ex)
                {
                };
            }
        });
    }

    //NetBeans Generated Code
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        employees = new javax.swing.JFrame();
        pnlEmployees = new javax.swing.JPanel();
        lblEmployees = new javax.swing.JLabel();
        scrollTableContainer = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        lblDoubleClick = new javax.swing.JLabel();
        btnNewEmployee = new javax.swing.JButton();
        btnDeleteEmployee = new javax.swing.JButton();
        chbSortID = new javax.swing.JCheckBox();
        chbSortName = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        information = new javax.swing.JFrame();
        pnlPanel = new javax.swing.JPanel();
        lblEmployeeInformation = new javax.swing.JLabel();
        btnEditReturn = new javax.swing.JButton();
        pnlTaxInfo = new javax.swing.JPanel();
        lblFederalTaxLabel = new javax.swing.JLabel();
        lblStateTaxLabel = new javax.swing.JLabel();
        lblSSTaxLabel = new javax.swing.JLabel();
        lblFederalTaxPercent = new javax.swing.JLabel();
        lblStateTaxPercent = new javax.swing.JLabel();
        lblSSTaxPercent = new javax.swing.JLabel();
        lblGrossIncomeLabel = new javax.swing.JLabel();
        lblNetIncomeLabel = new javax.swing.JLabel();
        lblGrossIncome = new javax.swing.JLabel();
        lblNetIncome = new javax.swing.JLabel();
        lblFederalTaxTaken = new javax.swing.JLabel();
        lblStateTaxTaken = new javax.swing.JLabel();
        lblSSTaxTaken = new javax.swing.JLabel();
        pnlEditInfo = new javax.swing.JPanel();
        lblEditName = new javax.swing.JLabel();
        lblEditAddress = new javax.swing.JLabel();
        lblEditHours = new javax.swing.JLabel();
        lblEditID = new javax.swing.JLabel();
        txtEditID = new javax.swing.JTextField();
        txtEditName = new javax.swing.JTextField();
        txtEditAddress = new javax.swing.JTextField();
        txtEditHours = new javax.swing.JTextField();
        btnEditSave = new javax.swing.JButton();
        btnEditDiscard = new javax.swing.JToggleButton();
        lblEditRate = new javax.swing.JLabel();
        txtEditRate = new javax.swing.JTextField();
        dlgNewEmployee = new javax.swing.JDialog();
        lblNewEmployeeLabel = new javax.swing.JLabel();
        lblNewID = new javax.swing.JLabel();
        lblNewName = new javax.swing.JLabel();
        lblNewHours = new javax.swing.JLabel();
        lblNewAddress = new javax.swing.JLabel();
        txtNewID = new javax.swing.JTextField();
        txtNewName = new javax.swing.JTextField();
        txtNewAddress = new javax.swing.JTextField();
        txtNewHours = new javax.swing.JTextField();
        btnNewEmployeeCreate = new javax.swing.JToggleButton();
        txtNewRate = new javax.swing.JTextField();
        lblNewRate = new javax.swing.JLabel();

        employees.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        employees.setTitle("TaxTracker - Student Workers");
        employees.setMinimumSize(new java.awt.Dimension(800, 500));
        employees.setResizable(false);
        employees.setSize(new java.awt.Dimension(700, 520));
        employees.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                employeesWindowClosing(evt);
            }
        });

        pnlEmployees.setMinimumSize(new java.awt.Dimension(800, 500));

        lblEmployees.setFont(new java.awt.Font("Helvetica", 1, 20)); // NOI18N
        lblEmployees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployees.setText("Student Workers");

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]
        {
        },
                new String[]
        {
            "901", "Name", "Address", "Hours", "Net Income", "Hourly Rate"
        })
        {
            Class[] types = new Class[]
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]
            {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }
        });
        tblEmployees.setShowGrid(true);
        tblEmployees.getTableHeader().setReorderingAllowed(false);
        tblEmployees.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                tblEmployeesMouseReleased(evt);
            }
        });
        scrollTableContainer.setViewportView(tblEmployees);
        tblEmployees.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblEmployees.getColumnModel().getColumn(1).setPreferredWidth(125);
        tblEmployees.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblEmployees.getColumnModel().getColumn(3).setPreferredWidth(70);
        tblEmployees.getColumnModel().getColumn(4).setPreferredWidth(50);
        tblEmployees.getColumnModel().getColumn(5).setResizable(false);
        tblEmployees.getColumnModel().getColumn(5).setPreferredWidth(50);

        lblDoubleClick.setText("Double Click an Employee to View Information");
        lblDoubleClick.setToolTipText("");

        btnNewEmployee.setText("New Employee");
        btnNewEmployee.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnNewEmployeeActionPerformed(evt);
            }
        });

        btnDeleteEmployee.setText("Delete Employee");
        btnDeleteEmployee.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDeleteEmployeeActionPerformed(evt);
            }
        });

        chbSortID.setSelected(true);
        chbSortID.setText("Sort by ID");
        chbSortID.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                chbSortIDItemStateChanged(evt);
            }
        });

        chbSortName.setText("Sort by Name");
        chbSortName.addItemListener(new java.awt.event.ItemListener()
        {
            public void itemStateChanged(java.awt.event.ItemEvent evt)
            {
                chbSortNameItemStateChanged(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data is Automatically Saved on Close");

        org.jdesktop.layout.GroupLayout pnlEmployeesLayout = new org.jdesktop.layout.GroupLayout(pnlEmployees);
        pnlEmployees.setLayout(pnlEmployeesLayout);
        pnlEmployeesLayout.setHorizontalGroup(
                pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(lblEmployees, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(scrollTableContainer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                .add(pnlEmployeesLayout.createSequentialGroup()
                .add(btnNewEmployee, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(chbSortName)
                .add(42, 42, 42)
                .add(btnDeleteEmployee, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
                .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlEmployeesLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(chbSortID)
                .add(lblDoubleClick)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 292, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(248, 248, 248)));
        pnlEmployeesLayout.setVerticalGroup(
                pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlEmployeesLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblEmployees, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollTableContainer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 360, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblDoubleClick)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(btnNewEmployee, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(btnDeleteEmployee, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlEmployeesLayout.createSequentialGroup()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(pnlEmployeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(chbSortName)
                .add(chbSortID))))
                .addContainerGap(42, Short.MAX_VALUE)));

        org.jdesktop.layout.GroupLayout employeesLayout = new org.jdesktop.layout.GroupLayout(employees.getContentPane());
        employees.getContentPane().setLayout(employeesLayout);
        employeesLayout.setHorizontalGroup(
                employeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlEmployees, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        employeesLayout.setVerticalGroup(
                employeesLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlEmployees, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        information.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        information.setTitle("TaxTracker - Information");
        information.setMinimumSize(new java.awt.Dimension(500, 500));
        information.setResizable(false);
        information.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                informationWindowClosing(evt);
            }
        });

        lblEmployeeInformation.setFont(new java.awt.Font("Helvetica", 1, 20)); // NOI18N
        lblEmployeeInformation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmployeeInformation.setText("Employee Information");
        lblEmployeeInformation.setLocation(new java.awt.Point(250, 10));

        btnEditReturn.setText("Main Menu");
        btnEditReturn.setMaximumSize(new java.awt.Dimension(120, 30));
        btnEditReturn.setMinimumSize(new java.awt.Dimension(120, 30));
        btnEditReturn.setPreferredSize(new java.awt.Dimension(120, 30));
        btnEditReturn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditReturnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout pnlPanelLayout = new org.jdesktop.layout.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
                pnlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlPanelLayout.createSequentialGroup()
                .add(btnEditReturn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(lblEmployeeInformation)
                .addContainerGap(146, Short.MAX_VALUE)));
        pnlPanelLayout.setVerticalGroup(
                pnlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblEmployeeInformation)
                .add(btnEditReturn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pnlTaxInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tax Information", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblFederalTaxLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFederalTaxLabel.setText("Federal Income Tax:");

        lblStateTaxLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblStateTaxLabel.setText("State Income Tax:");

        lblSSTaxLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSSTaxLabel.setText("Social Security Tax:");

        lblFederalTaxPercent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFederalTaxPercent.setText("20%");

        lblStateTaxPercent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStateTaxPercent.setText("5%");

        lblSSTaxPercent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSSTaxPercent.setText("12.4%");

        lblGrossIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGrossIncomeLabel.setText("Gross Income: ");

        lblNetIncomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNetIncomeLabel.setText("Net Income:");

        lblGrossIncome.setText("$");

        lblNetIncome.setText("$");

        lblFederalTaxTaken.setText("$");

        lblStateTaxTaken.setText("$");

        lblSSTaxTaken.setText("$");

        org.jdesktop.layout.GroupLayout pnlTaxInfoLayout = new org.jdesktop.layout.GroupLayout(pnlTaxInfo);
        pnlTaxInfo.setLayout(pnlTaxInfoLayout);
        pnlTaxInfoLayout.setHorizontalGroup(
                pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlTaxInfoLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, lblFederalTaxLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblSSTaxLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, lblStateTaxLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblGrossIncomeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblNetIncomeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlTaxInfoLayout.createSequentialGroup()
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(lblFederalTaxPercent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblStateTaxPercent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblSSTaxPercent, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(lblFederalTaxTaken, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblStateTaxTaken, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblSSTaxTaken, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                .add(org.jdesktop.layout.GroupLayout.LEADING, lblNetIncome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, lblGrossIncome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pnlTaxInfoLayout.setVerticalGroup(
                pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlTaxInfoLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblFederalTaxLabel)
                .add(lblFederalTaxPercent)
                .add(lblFederalTaxTaken))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblStateTaxLabel)
                .add(lblStateTaxPercent)
                .add(lblStateTaxTaken))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblSSTaxLabel)
                .add(lblSSTaxPercent)
                .add(lblSSTaxTaken))
                .add(18, 18, 18)
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblGrossIncomeLabel)
                .add(lblGrossIncome))
                .add(18, 18, 18)
                .add(pnlTaxInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblNetIncomeLabel)
                .add(lblNetIncome))
                .addContainerGap(10, Short.MAX_VALUE)));

        pnlEditInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "View/Edit Information", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lblEditName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditName.setText("Name:");

        lblEditAddress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditAddress.setText("Address:");

        lblEditHours.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditHours.setText("Hours Worked:");

        lblEditID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditID.setText("901:");

        txtEditID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtEditIDKeyReleased(evt);
            }
        });

        btnEditSave.setText("Save Changes");
        btnEditSave.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditSaveActionPerformed(evt);
            }
        });

        btnEditDiscard.setText("Discard Changes");
        btnEditDiscard.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEditDiscardActionPerformed(evt);
            }
        });

        lblEditRate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEditRate.setText("Hourly Rate:");

        org.jdesktop.layout.GroupLayout pnlEditInfoLayout = new org.jdesktop.layout.GroupLayout(pnlEditInfo);
        pnlEditInfo.setLayout(pnlEditInfoLayout);
        pnlEditInfoLayout.setHorizontalGroup(
                pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlEditInfoLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(lblEditHours, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblEditID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblEditName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblEditAddress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(lblEditRate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18)
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(txtEditAddress)
                .add(pnlEditInfoLayout.createSequentialGroup()
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(txtEditID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .add(txtEditName)
                .add(txtEditHours)
                .add(txtEditRate))
                .add(18, 18, 18)
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(btnEditSave, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(btnEditDiscard, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap()));
        pnlEditInfoLayout.setVerticalGroup(
                pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, pnlEditInfoLayout.createSequentialGroup()
                .addContainerGap()
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                .add(pnlEditInfoLayout.createSequentialGroup()
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblEditID)
                .add(txtEditID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblEditName)
                .add(txtEditName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(pnlEditInfoLayout.createSequentialGroup()
                .add(1, 1, 1)
                .add(btnEditSave, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblEditAddress)
                .add(txtEditAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlEditInfoLayout.createSequentialGroup()
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblEditHours)
                .add(txtEditHours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(pnlEditInfoLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblEditRate)
                .add(txtEditRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(0, 0, Short.MAX_VALUE))
                .add(pnlEditInfoLayout.createSequentialGroup()
                .add(btnEditDiscard, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .add(2, 2, 2)))));

        org.jdesktop.layout.GroupLayout informationLayout = new org.jdesktop.layout.GroupLayout(information.getContentPane());
        information.getContentPane().setLayout(informationLayout);
        informationLayout.setHorizontalGroup(
                informationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(pnlPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(informationLayout.createSequentialGroup()
                .add(pnlTaxInfo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
                .add(pnlEditInfo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        informationLayout.setVerticalGroup(
                informationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(informationLayout.createSequentialGroup()
                .add(pnlPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlTaxInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(pnlEditInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE)));

        dlgNewEmployee.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlgNewEmployee.setTitle("New Employee");
        dlgNewEmployee.setMinimumSize(new java.awt.Dimension(350, 300));
        dlgNewEmployee.setResizable(false);

        lblNewEmployeeLabel.setFont(new java.awt.Font("Helvetica", 0, 20)); // NOI18N
        lblNewEmployeeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewEmployeeLabel.setText("New Employee");

        lblNewID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNewID.setText("901:");

        lblNewName.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNewName.setText("Name:");

        lblNewHours.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNewHours.setText("Hours Worked:");

        lblNewAddress.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNewAddress.setText("Address:");

        txtNewID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                txtNewIDKeyReleased(evt);
            }
        });

        btnNewEmployeeCreate.setText("Create Employee");
        btnNewEmployeeCreate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnNewEmployeeCreateActionPerformed(evt);
            }
        });

        lblNewRate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNewRate.setText("Hourly Rate:");

        org.jdesktop.layout.GroupLayout dlgNewEmployeeLayout = new org.jdesktop.layout.GroupLayout(dlgNewEmployee.getContentPane());
        dlgNewEmployee.getContentPane().setLayout(dlgNewEmployeeLayout);
        dlgNewEmployeeLayout.setHorizontalGroup(
                dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(dlgNewEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(dlgNewEmployeeLayout.createSequentialGroup()
                .add(btnNewEmployeeCreate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
                .add(dlgNewEmployeeLayout.createSequentialGroup()
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                .add(lblNewID, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, lblNewName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, lblNewAddress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, lblNewHours, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(org.jdesktop.layout.GroupLayout.LEADING, lblNewRate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(txtNewID)
                .add(txtNewName)
                .add(txtNewAddress)
                .add(txtNewHours)
                .add(txtNewRate))
                .add(6, 6, 6))
                .add(dlgNewEmployeeLayout.createSequentialGroup()
                .add(lblNewEmployeeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap()))));
        dlgNewEmployeeLayout.setVerticalGroup(
                dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(dlgNewEmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .add(lblNewEmployeeLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblNewID)
                .add(txtNewID, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblNewName)
                .add(txtNewName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblNewAddress)
                .add(txtNewAddress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblNewHours)
                .add(txtNewHours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(dlgNewEmployeeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(lblNewRate)
                .add(txtNewRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btnNewEmployeeCreate)
                .addContainerGap(14, Short.MAX_VALUE)));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employees");
        setMinimumSize(new java.awt.Dimension(500, 500));
        setName("frame"); // NOI18N
        setResizable(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 466, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 493, Short.MAX_VALUE));

        pack();
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Variable Declarations">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteEmployee;
    private javax.swing.JToggleButton btnEditDiscard;
    private javax.swing.JButton btnEditReturn;
    private javax.swing.JButton btnEditSave;
    private javax.swing.JButton btnNewEmployee;
    private javax.swing.JToggleButton btnNewEmployeeCreate;
    private javax.swing.JCheckBox chbSortID;
    private javax.swing.JCheckBox chbSortName;
    private javax.swing.JDialog dlgNewEmployee;
    private javax.swing.JFrame employees;
    private javax.swing.JFrame information;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDoubleClick;
    private javax.swing.JLabel lblEditAddress;
    private javax.swing.JLabel lblEditHours;
    private javax.swing.JLabel lblEditID;
    private javax.swing.JLabel lblEditName;
    private javax.swing.JLabel lblEditRate;
    private javax.swing.JLabel lblEmployeeInformation;
    private javax.swing.JLabel lblEmployees;
    private javax.swing.JLabel lblFederalTaxLabel;
    private javax.swing.JLabel lblFederalTaxPercent;
    private javax.swing.JLabel lblFederalTaxTaken;
    private javax.swing.JLabel lblGrossIncome;
    private javax.swing.JLabel lblGrossIncomeLabel;
    private javax.swing.JLabel lblNetIncome;
    private javax.swing.JLabel lblNetIncomeLabel;
    private javax.swing.JLabel lblNewAddress;
    private javax.swing.JLabel lblNewEmployeeLabel;
    private javax.swing.JLabel lblNewHours;
    private javax.swing.JLabel lblNewID;
    private javax.swing.JLabel lblNewName;
    private javax.swing.JLabel lblNewRate;
    private javax.swing.JLabel lblSSTaxLabel;
    private javax.swing.JLabel lblSSTaxPercent;
    private javax.swing.JLabel lblSSTaxTaken;
    private javax.swing.JLabel lblStateTaxLabel;
    private javax.swing.JLabel lblStateTaxPercent;
    private javax.swing.JLabel lblStateTaxTaken;
    private javax.swing.JPanel pnlEditInfo;
    private javax.swing.JPanel pnlEmployees;
    private javax.swing.JPanel pnlPanel;
    private javax.swing.JPanel pnlTaxInfo;
    private javax.swing.JScrollPane scrollTableContainer;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTextField txtEditAddress;
    private javax.swing.JTextField txtEditHours;
    private javax.swing.JTextField txtEditID;
    private javax.swing.JTextField txtEditName;
    private javax.swing.JTextField txtEditRate;
    private javax.swing.JTextField txtNewAddress;
    private javax.swing.JTextField txtNewHours;
    private javax.swing.JTextField txtNewID;
    private javax.swing.JTextField txtNewName;
    private javax.swing.JTextField txtNewRate;
    // End of variables declaration//GEN-END:variables
}   //</editor-fold>

