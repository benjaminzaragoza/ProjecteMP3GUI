/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finestrabenvinguda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author alumne
 */
public class finestrabenvinguda extends JFrame {

    private Timer t;
    private ActionListener ac;
    private int x = 0;

    public finestrabenvinguda() {
        initComponents();
        this.setLocationRelativeTo(null);
        ac = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                x = x + 1;
                jProgressBar1.setValue(x);
                if (jProgressBar1.getValue() == 100) {
                    dispose();
                    t.stop();
                }
            }
        };
        t = new Timer(50, ac);
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(1, 1, 1));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jProgressBar1.setFont(new java.awt.Font("TeX Gyre Bonum", 1, 16)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(54, 23, 236));
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jProgressBar1.setOpaque(false);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 840, 20));

        jLabel1.setBackground(new java.awt.Color(1, 1, 1));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projecte/images/tumblr_n3ixpenz8H1tukjy5o1_500.gif"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  
        Runnable executable = new Runnable() {
            public void run() {
                finestrabenvinguda finestrabenvinguda2 = new finestrabenvinguda();
                finestrabenvinguda2.setVisible(true);
            }
        };
        
        Thread tarea = new Thread(executable);
        tarea.start();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

}
