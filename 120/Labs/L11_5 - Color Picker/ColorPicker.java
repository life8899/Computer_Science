
import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nick
 */
public class ColorPicker extends javax.swing.JFrame {
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    /**
     * Creates new form ColorPicker
     */
    public ColorPicker() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlColorPicker = new javax.swing.JPanel();
        btnGreen = new javax.swing.JButton();
        btnRed1 = new javax.swing.JButton();
        btnBlue = new javax.swing.JButton();
        pnlColor = new javax.swing.JPanel();
        sldRed = new javax.swing.JSlider();
        sldGreen = new javax.swing.JSlider();
        sldBlue = new javax.swing.JSlider();
        lblRed = new javax.swing.JLabel();
        lblGreen = new javax.swing.JLabel();
        lblBlue = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Color Picker");
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        btnGreen.setForeground(new java.awt.Color(0, 255, 0));
        btnGreen.setText("Green");
        btnGreen.setMaximumSize(new java.awt.Dimension(100, 50));
        btnGreen.setMinimumSize(new java.awt.Dimension(100, 50));
        btnGreen.setPreferredSize(new java.awt.Dimension(100, 50));
        btnGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGreenActionPerformed(evt);
            }
        });

        btnRed1.setForeground(new java.awt.Color(255, 0, 0));
        btnRed1.setText("Red");
        btnRed1.setMaximumSize(new java.awt.Dimension(100, 50));
        btnRed1.setMinimumSize(new java.awt.Dimension(100, 50));
        btnRed1.setPreferredSize(new java.awt.Dimension(100, 50));
        btnRed1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRed1ActionPerformed(evt);
            }
        });

        btnBlue.setForeground(new java.awt.Color(0, 0, 255));
        btnBlue.setText("Blue");
        btnBlue.setMaximumSize(new java.awt.Dimension(100, 50));
        btnBlue.setMinimumSize(new java.awt.Dimension(100, 50));
        btnBlue.setPreferredSize(new java.awt.Dimension(100, 50));
        btnBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlueActionPerformed(evt);
            }
        });

        pnlColor.setBackground(new java.awt.Color(204, 204, 204));
        pnlColor.setToolTipText("");

        org.jdesktop.layout.GroupLayout pnlColorLayout = new org.jdesktop.layout.GroupLayout(pnlColor);
        pnlColor.setLayout(pnlColorLayout);
        pnlColorLayout.setHorizontalGroup(
            pnlColorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 200, Short.MAX_VALUE)
        );
        pnlColorLayout.setVerticalGroup(
            pnlColorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        sldRed.setMaximum(255);
        sldRed.setValue(0);
        sldRed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldRedStateChanged(evt);
            }
        });

        sldGreen.setMaximum(255);
        sldGreen.setValue(0);
        sldGreen.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldGreenStateChanged(evt);
            }
        });

        sldBlue.setMaximum(255);
        sldBlue.setValue(0);
        sldBlue.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldBlueStateChanged(evt);
            }
        });

        lblRed.setForeground(new java.awt.Color(255, 0, 0));
        lblRed.setText("Red");

        lblGreen.setForeground(new java.awt.Color(0, 255, 0));
        lblGreen.setText("Green");

        lblBlue.setForeground(new java.awt.Color(0, 0, 255));
        lblBlue.setText("Blue");

        org.jdesktop.layout.GroupLayout pnlColorPickerLayout = new org.jdesktop.layout.GroupLayout(pnlColorPicker);
        pnlColorPicker.setLayout(pnlColorPickerLayout);
        pnlColorPickerLayout.setHorizontalGroup(
            pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlColorPickerLayout.createSequentialGroup()
                .add(54, 54, 54)
                .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(pnlColorPickerLayout.createSequentialGroup()
                        .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnBlue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnRed1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(btnGreen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(113, 113, 113)
                        .add(pnlColor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(pnlColorPickerLayout.createSequentialGroup()
                        .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(lblBlue)
                            .add(lblRed)
                            .add(lblGreen))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, sldRed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, sldGreen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, sldBlue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 307, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        pnlColorPickerLayout.setVerticalGroup(
            pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlColorPickerLayout.createSequentialGroup()
                .add(60, 60, 60)
                .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(pnlColor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(pnlColorPickerLayout.createSequentialGroup()
                        .add(btnRed1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(25, 25, 25)
                        .add(btnGreen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(25, 25, 25)
                        .add(btnBlue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(50, 50, 50)
                .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sldRed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblRed))
                .add(25, 25, 25)
                .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sldGreen, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblGreen))
                .add(25, 25, 25)
                .add(pnlColorPickerLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(sldBlue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblBlue))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlColorPicker, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pnlColorPicker, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRed1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRed1ActionPerformed
        pnlColor.setBackground(Color.RED);
        sldRed.setValue(255);
        sldGreen.setValue(0);
        sldBlue.setValue(0);
    }//GEN-LAST:event_btnRed1ActionPerformed

    private void btnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGreenActionPerformed
        pnlColor.setBackground(Color.GREEN);
        sldRed.setValue(0);
        sldGreen.setValue(255);
        sldBlue.setValue(0);
    }//GEN-LAST:event_btnGreenActionPerformed

    private void btnBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlueActionPerformed
        pnlColor.setBackground(Color.BLUE);
        sldRed.setValue(0);
        sldGreen.setValue(0);
        sldBlue.setValue(255);
    }//GEN-LAST:event_btnBlueActionPerformed

    private void sldRedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldRedStateChanged
        red = sldRed.getValue();
        pnlColor.setBackground(new Color(red, green, blue));
        pnlColor.repaint();
    }//GEN-LAST:event_sldRedStateChanged

    private void sldGreenStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldGreenStateChanged
        green = sldGreen.getValue();
        pnlColor.setBackground(new Color(red, green, blue));
        pnlColor.repaint();
    }//GEN-LAST:event_sldGreenStateChanged

    private void sldBlueStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldBlueStateChanged
        blue = sldBlue.getValue();
        pnlColor.setBackground(new Color(red, green, blue));
        pnlColor.repaint();
    }//GEN-LAST:event_sldBlueStateChanged

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(ColorPicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColorPicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColorPicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColorPicker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColorPicker().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlue;
    private javax.swing.JButton btnGreen;
    private javax.swing.JButton btnRed1;
    private javax.swing.JLabel lblBlue;
    private javax.swing.JLabel lblGreen;
    private javax.swing.JLabel lblRed;
    private javax.swing.JPanel pnlColor;
    private javax.swing.JPanel pnlColorPicker;
    private javax.swing.JSlider sldBlue;
    private javax.swing.JSlider sldGreen;
    private javax.swing.JSlider sldRed;
    // End of variables declaration//GEN-END:variables
}