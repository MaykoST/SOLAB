/*
 * SOProcess.java
 *
 * Created on 10/09/2007, 22:33:45
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import com.solab.util.SOConstants;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * $Id: SOProcess.java,v 1.7 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcess extends SOObject {

    private static Logger logger = Logger.getLogger(SOProcess.class.getName());
    private int id;
    private String name;
    private byte priority;
    private int codeSize;
    private int ip;
    private byte state;
    private int lockedCycles;
    private int sumLockedCycles;
    private int sumCPUCycles;
    private List<SOPageTableIndex> pageTable;
    private Color color;

    public SOProcess() {
        this.ip = -1;
        this.state = SOConstants.PROCESS_CREATED;
        this.pageTable = (List<SOPageTableIndex>) Collections.synchronizedList(new ArrayList());
        this.sumLockedCycles = 0;
        this.sumCPUCycles = 0;
    }

    public void resetIp() {
        this.ip = -1;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public void incrementIp() {
        ip++;
        sumCPUCycles++;

        if (DEBUG) {
            logger.log(Level.INFO, "Processo-> " + this.getName() + " mudou instrucao para-> " + this.getIp());
        }

        if (ip >= codeSize) {
            state = SOConstants.PROCESS_FINISHED;

            if (DEBUG) {
                logger.log(Level.INFO, "Processo-> " + this.getName() + " mudou para o estado de concluído");
            }
        }
    }

    public void block() {
        this.state = SOConstants.PROCESS_BLOCKED;

        if (DEBUG) {
            logger.log(Level.INFO, "Processo-> " + this.getName() + " mudou para o estado de bloqueado");
        }
    }

    public void unblock() {
        if (ip >= codeSize) {
            this.state = SOConstants.PROCESS_FINISHED;

            if (DEBUG) {
                logger.log(Level.INFO, "Processo-> " + this.getName() + " mudou para o estado de finalizado");
            }
        } else {
            this.state = SOConstants.PROCESS_READY;

            if (DEBUG) {
                logger.log(Level.INFO, "Processo-> " + this.getName() + " mudou para o estado de pronto");
            }
        }
    }

    public List<SOPageTableIndex> getPageTable() {
        return pageTable;
    }

    public void setPageTable(List<SOPageTableIndex> pageTable) {
        this.pageTable = pageTable;
    }

    public boolean IsFinished() {
        return this.state == SOConstants.PROCESS_FINISHED;
    }

    public boolean IsBloqued() {
        return this.state == SOConstants.PROCESS_BLOCKED;
    }

    public boolean IsReady() {
        return this.state == SOConstants.PROCESS_READY;
    }

    public int getCodeSize() {
        return codeSize;
    }

    public void setCodeSize(int codeSize) {
        this.codeSize = codeSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public int getLockedCycles() {
        return lockedCycles;
    }

    public void setLockedCycles(int lockedCycles) {
        //adiciona mais um ciclo porque o SO
        //ja atualiza a fila depois de bloquear
        this.lockedCycles = lockedCycles + 1;
        this.sumLockedCycles += lockedCycles;
    }

    public void decrementLockeCycles() {
        if (this.lockedCycles > 0) {
            this.lockedCycles--;
        }

        if (this.lockedCycles == 0) {
            this.state = SOConstants.PROCESS_READY;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSumCPUCycles() {
        return sumCPUCycles;
    }

    public void setSumCPUCycles(int sumCPUCycles) {
        this.sumCPUCycles = sumCPUCycles;
    }

    public int getSumLockedCycles() {
        return sumLockedCycles;
    }

    public void setSumLockedCycles(int sumLockedCycles) {
        this.sumLockedCycles = sumLockedCycles;
    }
}