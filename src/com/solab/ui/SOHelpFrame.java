/*
 * SOHelpFrame.java
 *
 * Created on 16 de Junho de 2008, 16:28
 */
package com.solab.ui;

import java.awt.Font;

/**
 *
 * @author  Mayko
 */
public class SOHelpFrame extends javax.swing.JFrame {

    /** Creates new form SOHelpFrame */
    public SOHelpFrame() {
        initComponents();

        String msg = new String();

        msg += "Ajuda do Simulador de Escalonador.\n\n";

        msg += "Conceitos: \n\n";

        msg += "Ciclo: Numero sequencial utilizado para representar as instruções do\n";
        msg += "processo.\n\n";
        msg += "Tamanho: O  tamanho  de  um  processo  e  definido  pelo  número  de\n";
        msg += "instruções que ele possui.\n\n";

        msg += "Quantum: Fatia   de   Tempo  Fornecida  ao  processo  para  execução.\n";
        msg += "No estado atual do simulador o quantum tem um valor fixo igual a 5.\n\n";

        msg += "Preemptivo: Capacidade   do  Algoritmo  de  interromper  a  execução\n";
        msg += "de   um   processo   de   acordo   com   uma  determinada  condição.\n\n";

        msg += "Prioridade: Prioridade  do  Processo  de acordo com sua importancia.\n";
        msg += "No  sistema  de  prioridade  um numero de menor valor representa uma\n";
        msg += "maior prioridade.\n\n";
    
        msg += "Algoritmo FIFO: \n";
        msg += "O   algoritmo   FIFO   (First in First out)   é   o   mais  simples.\n";
        msg += "Seu  funcionamento consiste em  escolher o primeiro processo da fila\n";
        msg += "de  processos  aptos  e  coloca-lo  no  processador.\n";
        msg += "O  algoritmo  FIFO  trabalha com  quantum.\n\n";

        msg += "Algoritmo SJF:\n";
        msg += "O  algoritmo SJF ou Job mais Curto elege os processos para execução\n";
        msg += "de  acordo  com  seu  tamanho,  ou  seja,  os  processos menores tem\n";
        msg += "preferencia para execução, por isso a fila  de processos esta sempre\n";
        msg += "ordenada   por  tamanho,  sendo  que  os  menores  ficam  na  frente\n";
        msg += "O  algoritmo  SJF  trabalha  com  quantum  e preempção.\n\n";

        msg += "Algoritmo de Prioridade:\n";
        msg += "O  algoritmo  de  Prioridade  elege  os  processos  para execução de\n";
        msg += "acordo   com   sua  Prioridade,  ou seja,  os  processos  com  menor\n";
        msg += "prioridade  tem  preferencia  para  execução,  por  isso  a  fila de\n";
        msg += "processos  esta  sempre  ordenada  por  prioridade.\n";
        msg += "O  algoritmo  de  prioridade  trabalha  com  prioridade,  quantum e\n";
        msg += "preempção.\n\n";

        this.txtMsg.setFont(new Font("Courier New", Font.PLAIN, 14));
        this.txtMsg.setAutoscrolls(true);
        this.txtMsg.setEditable(false);
        this.txtMsg.setText(msg);
        this.txtMsg.setCaretPosition(0);
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
        txtMsg = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuda do Simulador");

        txtMsg.setColumns(20);
        txtMsg.setRows(5);
        jScrollPane1.setViewportView(txtMsg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtMsg;
    // End of variables declaration//GEN-END:variables
}
