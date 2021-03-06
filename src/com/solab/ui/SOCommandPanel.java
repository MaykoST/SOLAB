/*
 * SOCommandPanel.java
 *
 * Created on 11 de Setembro de 2007, 19:56
 */
package com.solab.ui;

import com.solab.model.event.SOEvent;
import com.solab.model.event.SOListener;
import com.solab.so.SO;
import com.solab.util.SOConstants;
import com.solab.util.SOSchedulerThread;

/**
 * $Id: SOCommandPanel.java,v 1.5 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOCommandPanel extends javax.swing.JPanel implements SOListener {

    private SO so;
    private SOSchedulerThread soThread = null;
    private SOCreateProcessFrame soProcFrame = null;

    /** Creates new form SOCommandPanel */
    public SOCommandPanel() {
        initComponents();
    }

    public SO getSo() {
        return so;
    }

    public void setSo(SO so) {
        this.so = so;
    }

    public void soStarted(SOEvent e) {
    }

    public void processQueueInitialized(SOEvent e) {
    }

    public void schedulerInitialized(SOEvent e) {
    }

    public void memoryInitialized(SOEvent e) {
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnStartSO = new javax.swing.JButton();
        btnPauseSO = new javax.swing.JButton();
        btnStopSO = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnStep = new javax.swing.JButton();
        btnCreateProcess = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxSchedulerType = new javax.swing.JComboBox();
        btnChangeScheduler = new javax.swing.JButton();
        chkPreemptive = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtEsp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQua = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Sistema Operacional"));

        btnStartSO.setText("Iniciar ");
        btnStartSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSOActionPerformed(evt);
            }
        });

        btnPauseSO.setText("Pausar");
        btnPauseSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseSOActionPerformed(evt);
            }
        });

        btnStopSO.setText("Parar");
        btnStopSO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopSOActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnStartSO, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnPauseSO, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnStopSO)
                        .addContainerGap(16, Short.MAX_VALUE))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnPauseSO, btnStartSO, btnStopSO});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnStartSO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPauseSO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStopSO)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Simulador"));

        btnStep.setText("Executar Passo");
        btnStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStepActionPerformed(evt);
            }
        });

        btnCreateProcess.setText("Cria Processo");
        btnCreateProcess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateProcessActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCreateProcess, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnStep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCreateProcess)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Escalonador"));

        jLabel2.setText("Tipo");

        cbxSchedulerType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "FIFO", "SJF - Job mais curto", "Prioridades" }));

        btnChangeScheduler.setText("Modificar Escalonador");
        btnChangeScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeSchedulerActionPerformed(evt);
            }
        });

        chkPreemptive.setText("Preemptivo");

        jLabel1.setText("Tempo espera");

        txtEsp.setText("100");

        jLabel3.setText("ms");

        jLabel4.setText("Fatia de Tempo");

        txtQua.setText("5");

        jLabel5.setText("ciclos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkPreemptive)
                            .addComponent(cbxSchedulerType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChangeScheduler)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtQua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbxSchedulerType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPreemptive)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangeScheduler))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateProcessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateProcessActionPerformed
        if (this.soProcFrame != null) {
            this.soProcFrame.dispose();
        }
        this.soProcFrame = new SOCreateProcessFrame();
        this.soProcFrame.setSo(this.so);
        this.soProcFrame.setLocationRelativeTo(null);
        this.soProcFrame.setVisible(true);
    }//GEN-LAST:event_btnCreateProcessActionPerformed

    private void btnStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStepActionPerformed
        this.so.executeLoop();
    }//GEN-LAST:event_btnStepActionPerformed

    private void btnStartSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSOActionPerformed
        if (soThread == null) {
            soThread = new SOSchedulerThread();
        } else {
            soThread.setRunning(false);

            soThread = new SOSchedulerThread();
        }

        soThread.setSo(this.so);
        soThread.setDelay(Integer.parseInt(txtEsp.getText()));
        soThread.start();
    }//GEN-LAST:event_btnStartSOActionPerformed

    private void btnChangeSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeSchedulerActionPerformed
        
        if (this.soThread != null) {
            this.soThread.setRunning(false);
            this.soThread = null;
        }
        this.so.resetSO();
        
        int quantum = SOConstants.QUANTUM;
        try {
             quantum = Integer.parseInt(txtQua.getText());
        } finally {
            this.so.setQuantum(quantum);
        }
        
        this.so.changeSchedulerType(cbxSchedulerType.getSelectedIndex());                       
        
        if (chkPreemptive.isSelected()) {
            this.so.enablePreemption();
        } else {
            this.so.disablePreemption();
        }                
    }//GEN-LAST:event_btnChangeSchedulerActionPerformed

private void btnPauseSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseSOActionPerformed
    if (this.soThread != null) {
        this.soThread.setRunning(false);
        this.soThread = null;
    }
}//GEN-LAST:event_btnPauseSOActionPerformed

private void btnStopSOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopSOActionPerformed
    if (this.soThread != null) {
        this.soThread.setRunning(false);
        this.soThread = null;                
    }
    this.so.resetSO();
}//GEN-LAST:event_btnStopSOActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeScheduler;
    private javax.swing.JButton btnCreateProcess;
    private javax.swing.JButton btnPauseSO;
    private javax.swing.JButton btnStartSO;
    private javax.swing.JButton btnStep;
    private javax.swing.JButton btnStopSO;
    private javax.swing.JComboBox cbxSchedulerType;
    private javax.swing.JCheckBox chkPreemptive;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txtEsp;
    private javax.swing.JTextField txtQua;
    // End of variables declaration//GEN-END:variables
}