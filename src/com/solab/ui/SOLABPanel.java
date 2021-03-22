/*
 * SOProcessPanel.java
 *
 * Created on 10 de Setembro de 2007, 19:35
 */
package com.solab.ui;

import com.solab.model.event.SOCPUEvent;
import com.solab.model.event.SOCPUListener;
import com.solab.model.event.SOProcessQueueEvent;
import com.solab.model.event.SOProcessQueueListener;
import com.solab.so.SO;
import com.solab.so.SOProcess;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;

/**
 * $Id: SOLABPanel.java,v 1.3 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOLABPanel extends javax.swing.JPanel implements SOProcessQueueListener, SOCPUListener {

    private SO so;
    private int cpuX;
    private int cpuY;
    private int cpuWidth;
    private int cpuHeight;
    private int queueX;
    private int queueY;
    private int queueWidth;
    private int queueHeight;
    private int queueSlots;
    private int queueSlotY;
    private int lockX;
    private int lockY;
    private int lockWidth;
    private int lockHeight;
    private int lockSlots;
    private int lockSlotY;

    /** Creates new form SOProcessPanel */
    public SOLABPanel() {
        cpuX = 160;
        cpuY = 50;
        cpuWidth = 92;
        cpuHeight = 60;

        queueSlots = 0;
        lockSlots = 0;
        queueSlotY = 12;
        lockSlotY = 12;

        queueX = 10;
        queueY = 30;
        queueWidth = 80;
        queueHeight = queueSlotY * 25;

        lockX = 320;
        lockY = 30;
        lockWidth = 80;
        lockHeight = lockSlotY * 15;

        initComponents();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        drawCPU(g);
        drawProcessQueue(g);
        drawLockQueue(g);
        drawLegenda(g);
        drawCPUJob(g);        
        drawProcessQueueJobs(g);        
        drawLockQueueJobs(g);        
    }

    public void drawProcessQueue(Graphics g) {
        // Filas de Processo
        g.setFont(new Font("Times New Roman", Font.PLAIN, queueSlotY));
        
        g.drawRect(queueX, queueY, queueWidth, queueHeight);

        queueSlots = 0;

        for (int i = queueY; i < (queueY + queueHeight); i += queueSlotY) {
            g.drawLine(queueX, i + queueSlotY, queueX + queueWidth, i + queueSlotY);
            queueSlots++;
        }

        g.drawString("P", queueX + 2, queueY + queueHeight + 15);
        g.drawString("PP", queueX + 29, queueY + queueHeight + 15);
        g.drawString("T", queueX + 69, queueY + queueHeight + 15);
    }

    public void drawLockQueue(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, queueSlotY));
        
        g.drawRect(lockX, lockY, lockWidth, lockHeight);

        lockSlots = 0;

        for (int i = lockY; i < (lockY + lockHeight); i += lockSlotY) {
            g.drawLine(lockX, i + lockSlotY, lockX + lockWidth, i + lockSlotY);
            lockSlots++;
        }

        g.drawString("P", lockX + 2, lockY + lockHeight + 15);
        g.drawString("PP", lockX + 29, lockY + lockHeight + 15);
        g.drawString("T", lockX + 69, lockY + lockHeight + 15);
    }

    public void drawProcessQueueJobs(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, queueSlotY));

        if ((this.so == null) || (this.so.getProcessQueue() == null)) {
            return;
        }

        synchronized (this.so.getProcessQueue()) {
            Iterator<SOProcess> iterator = this.so.getProcessQueue().iterator();
            SOProcess proc;

            for (int i = 0; i < queueSlots; i++) {
                if (iterator.hasNext()) {
                    proc = iterator.next();

                    g.setColor(proc.getColor());

                    g.fillRect(queueX + 1, queueY + (i * queueSlotY) + 1, queueWidth - 1, queueSlotY - 1);

                    g.setColor(Color.BLACK);

                    g.drawString(proc.getName(), queueX + 5, queueY + (i * queueSlotY) + queueSlotY - 1);
                    g.drawString(String.valueOf(proc.getPriority()), queueX + 46, queueY + (i * queueSlotY) + queueSlotY - 1);
                    g.drawString(String.valueOf(proc.getCodeSize()), queueX + 61, queueY + (i * queueSlotY) + queueSlotY - 1);
                } else {
                    break;
                }
            }
        }
    }

    public void drawLockQueueJobs(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, lockSlotY));

        if ((this.so != null) && (this.so.getLockQueue() != null)) {

            synchronized (this.so.getLockQueue()) {
                Iterator<SOProcess> iterator = this.so.getLockQueue().iterator();
                SOProcess proc;

                for (int i = 0; i < lockSlots; i++) {
                    if (iterator.hasNext()) {
                        proc = iterator.next();

                        //g.setColor(Color.getHSBColor(118, 171, 188
                        g.setColor(proc.getColor());

                        g.fillRect(lockX + 1, lockY + (i * lockSlotY) + 1, lockWidth - 1, lockSlotY - 1);

                        g.setColor(Color.BLACK);

                        g.drawString(proc.getName(), lockX + 5, lockY + (i * lockSlotY) + lockSlotY - 1);
                        g.drawString(String.valueOf(proc.getPriority()), lockX + 43, lockY + (i * lockSlotY) + lockSlotY - 1);
                        g.drawString(String.valueOf(proc.getCodeSize()), lockX + 61, lockY + (i * lockSlotY) + lockSlotY - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void drawCPU(Graphics g) {
        // Processador       
        g.setFont( new Font( "Courier New", Font.PLAIN, 14));
        g.drawString("Processador", cpuX , cpuY - 12);
        g.drawRect(cpuX, cpuY, cpuWidth, cpuHeight);
        g.setColor(Color.ORANGE);
        g.fillRect(cpuX + 1, cpuY + 1, cpuWidth - 1, cpuHeight - 1);
        g.setColor(Color.BLACK);

        for (int i = cpuX + 3; i < (cpuX + cpuWidth - 1); i += 7) {
            g.drawRect(i - 2, cpuY - 5, 5, 5);
            g.drawRect(i - 2, cpuY + cpuHeight, 5, 5);
        }
    }

    public void drawCPUJob(Graphics g) {
        g.setFont(new Font("Times New Roman", Font.PLAIN, queueSlotY));
        
        if (this.so != null) {
            synchronized (this.so.getCPU()) {
                if (this.so.getCPU().getCurrentProcess() != null) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
                    g.drawString("P: " + this.so.getCPU().getCurrentProcess().getName(), cpuX + 4, cpuY + 15);
                    g.drawString("C: " + this.so.getCPU().getCurrentProcess().getIp(), cpuX + 4, cpuY + 30);
                    g.drawString("T: " + this.so.getCPU().getCurrentProcess().getCodeSize(), cpuX + 4, cpuY + 45);
                    g.drawString("PP: " + this.so.getCPU().getCurrentProcess().getPriority(), cpuX + 62, cpuY + 15);
                }
            }
        }
    }
    
    public void drawLegenda(Graphics g) {
        g.setFont( new Font( "Courier New", Font.PLAIN, 14 ) );
        
        g.drawString("Processos", queueX, queueY - 15);
        g.drawString("Prontos", queueX, queueY - 5);
        g.drawString("Processos", lockX, lockY - 15);
        g.drawString("Bloqueados", lockX, lockY - 5);
		
        int x = queueX + queueWidth + 15;
        int y = queueY + queueHeight - 85;
        
	g.drawRect(x, y, 130, 87); 
	g.drawString( "P  = Processo", x + 5, y + 18);
	g.drawString( "C  = Ciclo", x + 5, y + 36);
	g.drawString( "T  = Tamanho", x + 5, y + 54);
	g.drawString( "PP = Prioridade", x + 5, y + 72);
	g.drawString( "     Processo ", x + 5, y + 18);	
    }

    public void processAdded(SOProcessQueueEvent e) {
        this.repaint();
    }

    public void processModified(SOProcessQueueEvent e) {
        this.repaint();
    }

    public void processRemoved(SOProcessQueueEvent e) {
        this.repaint();
    }

    public void processChanged(SOCPUEvent e) {
        this.repaint();
    }

    public void instructionExecuted(SOCPUEvent e) {
        this.repaint();
    }

    public SO getSo() {
        return so;
    }

    public void setSo(SO so) {
        this.so = so;

        if (this.so.getProcessQueue() != null) {
            this.so.getProcessQueue().addSOProcessQueueListener(this);
        }

        if (this.so.getLockQueue() != null) {
            this.so.getLockQueue().addSOProcessQueueListener(this);
        }

        if (this.so.getCPU() != null) {
            this.so.getCPU().addSOCPUListener(this);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
