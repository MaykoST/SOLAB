/*
 * SOProcessPanel.java
 *
 * Created on 25 de Maio de 2008, 14:42
 */
package com.solab.ui;

import com.solab.model.event.SOCPUEvent;
import com.solab.model.event.SOCPUListener;
import com.solab.model.event.SOProcessQueueEvent;
import com.solab.model.event.SOProcessQueueListener;
import com.solab.so.SO;
import com.solab.so.SOProcess;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * $Id: SOProcessPanel.java,v 1.3 2008/06/02 23:34:19 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcessPanel extends javax.swing.JPanel implements SOProcessQueueListener, ListSelectionListener, SOCPUListener {

    private SO so;
    private SOProcessQueueTableModel model;

    /** Creates new form SOProcessPanel */
    public SOProcessPanel() {
        initComponents();
    }

    public SO getSo() {
        return so;
    }

    public void setSo(SO so) {
        this.so = so;
        this.model = new SOProcessQueueTableModel(so);
        this.tblProc.setModel(this.model);
        this.tblProc.getSelectionModel().addListSelectionListener(this);
        this.so.getAllProcess().addSOProcessQueueListener(this);
        this.so.getCPU().addSOCPUListener(this);
    }

    public void processAdded(SOProcessQueueEvent e) {
        this.tblProc.revalidate();
        this.updateProcInfo(this.tblProc.getSelectedRow());
    }

    public void processModified(SOProcessQueueEvent e) {
        this.tblProc.revalidate();
        this.updateProcInfo(this.tblProc.getSelectedRow());
    }

    public void processRemoved(SOProcessQueueEvent e) {
        this.tblProc.revalidate();
        this.updateProcInfo(this.tblProc.getSelectedRow());
    }

    public void processChanged(SOCPUEvent e) {
        this.updateProcInfo(this.tblProc.getSelectedRow());
    }

    public void instructionExecuted(SOCPUEvent e) {
        this.updateProcInfo(this.tblProc.getSelectedRow());
    }

    public void valueChanged(ListSelectionEvent e) {
        this.updateProcInfo(e.getLastIndex());
    }

    public void updateProcInfo(int index) {
        if (index > -1) {
            if (index < so.getAllProcess().size()) {
                SOProcess proc = so.getAllProcess().get(index);

                txtProc.setText(proc.getName());
                txtId.setText(String.valueOf(proc.getId()));
                txtTam.setText(String.valueOf(proc.getCodeSize()));
                txtPrioridade.setText(String.valueOf(proc.getPriority()));
                txtCicloBlo.setText(String.valueOf(proc.getSumLockedCycles()));
                txtCicloPro.setText(String.valueOf(proc.getSumCPUCycles()));

                if (proc.IsBloqued()) {
                    txtSit.setText("Bloqueado");
                } else if (proc.IsReady()) {
                    txtSit.setText("Pronto");
                } else if (proc.IsFinished()) {
                    txtSit.setText("Conclu??do");
                } else {
                    txtSit.setText("Criado");
                }
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblProc = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProc = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtTam = new javax.swing.JTextField();
        txtPrioridade = new javax.swing.JTextField();
        txtCicloPro = new javax.swing.JTextField();
        txtCicloBlo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSit = new javax.swing.JTextField();

        tblProc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProc);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Processo");

        jLabel2.setText("Identificador");

        jLabel3.setText("Prioridade");

        jLabel4.setText("Tamanho");

        jLabel5.setText("Ciclos Processador");

        jLabel6.setText("Ciclos Bloqueado");

        txtProc.setEditable(false);

        txtId.setEditable(false);

        txtTam.setEditable(false);

        txtPrioridade.setEditable(false);

        txtCicloPro.setEditable(false);

        txtCicloBlo.setEditable(false);

        jLabel7.setText("Situa????o");

        txtSit.setEditable(false);
        txtSit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTam)
                    .addComponent(txtPrioridade)
                    .addComponent(txtId)
                    .addComponent(txtProc, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCicloBlo)
                    .addComponent(txtSit, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(txtCicloPro, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtProc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPrioridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtCicloPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCicloBlo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void txtSitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSitActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtSitActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProc;
    private javax.swing.JTextField txtCicloBlo;
    private javax.swing.JTextField txtCicloPro;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrioridade;
    private javax.swing.JTextField txtProc;
    private javax.swing.JTextField txtSit;
    private javax.swing.JTextField txtTam;
    // End of variables declaration//GEN-END:variables
}
