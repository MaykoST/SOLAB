/*
 * SOProcessFactory.java
 *
 * Created on 17/09/2007, 20:44:51
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import com.solab.util.SOConstants;
import java.awt.Color;
import java.util.Random;

/**
 * $Id: SOProcessFactory.java,v 1.5 2008/05/25 18:41:50 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcessFactory {

    private static SOProcessFactory instance;
    private Random random;
    private int processCounter;
    
    private SOMemory soMemory;

    public SOProcessFactory() {
        random = new Random();
        processCounter = 0;
    }

    public static SOProcessFactory getInstance() {
        if (instance == null) {
            instance = new SOProcessFactory();
        }
        return instance;
    }
    
    public int getProcessCounter() {
        return processCounter;
    }

    public void setProcessCounter(int processCounter) {
        this.processCounter = processCounter;
    }

    public SOProcess createProcess() {
        this.processCounter++;
        SOProcess proc = new SOProcess();

        proc.setId(this.processCounter);
        proc.setName("P->" + proc.getId());
        proc.setCodeSize(Math.max(1, this.random.nextInt(SOConstants.MAX_PROCESS_SIZE)));
        byte tmp;
        byte code[] = new byte[proc.getCodeSize()];
        for (int i = 0; i < proc.getCodeSize(); i++) {
            tmp = (byte) (this.random.nextInt(9) + 1);

            if (tmp == 1 || tmp == 2) {
                code[i] = SOConstants.INSTRUCTION_BLOCK;
            } else if (tmp == 3 || tmp == 4) {
                code[i] = SOConstants.INSTRUCTION_READ_MEMORY;
            } else if (tmp == 5 || tmp == 6) {
                code[i] = SOConstants.INSTRUCTION_WRITE_MEMORY;
            } else {
                code[i] = SOConstants.INSTRUCTION_CODE;
            }
        }

        proc.setPriority((byte) this.random.nextInt(SOConstants.BASE_PRIORITY));

        int cor = this.random.nextInt(5);

        switch (cor) {
            case 0:
                proc.setColor(Color.GREEN);
                break;
            case 1:
                proc.setColor(Color.ORANGE);
                break;
            case 2:
                proc.setColor(Color.YELLOW);
                break;
            case 3:
                proc.setColor(Color.CYAN);
                break;
            case 4:
                proc.setColor(Color.LIGHT_GRAY);
                break;
            case 5:
                proc.setColor(Color.MAGENTA);
                break;
        }

        this.soMemory.allocProcess(proc, code);

        return proc;
    }

    public SOProcess createProcess(int type, int priority, int maxFrames) {
        this.processCounter++;
        SOProcess proc = new SOProcess();

        proc.setId(this.processCounter);
        proc.setName("P->" + proc.getId());
        proc.setCodeSize(Math.max(1, this.random.nextInt(SOConstants.PAGE_SIZE * maxFrames)));
        byte tmp;
        byte code[] = new byte[proc.getCodeSize()];
        for (int i = 0; i < proc.getCodeSize(); i++) {
            tmp = (byte) (this.random.nextInt(9) + 1);

            if (type == 3) { //Ambos
                if (tmp == 1 || tmp == 2) {
                    code[i] = SOConstants.INSTRUCTION_BLOCK;
                } else if (tmp == 3 || tmp == 4) {
                    code[i] = SOConstants.INSTRUCTION_READ_MEMORY;
                } else if (tmp == 5 || tmp == 6) {
                    code[i] = SOConstants.INSTRUCTION_WRITE_MEMORY;
                } else {
                    code[i] = SOConstants.INSTRUCTION_CODE;
                }
            } else if (type == 1) {  //Mais CPU
                if (tmp == 1) {
                    code[i] = SOConstants.INSTRUCTION_BLOCK;
                } else if (tmp == 3) {
                    code[i] = SOConstants.INSTRUCTION_READ_MEMORY;
                } else if (tmp == 5) {
                    code[i] = SOConstants.INSTRUCTION_WRITE_MEMORY;
                } else {
                    code[i] = SOConstants.INSTRUCTION_CODE;
                }
            } else if (type == 2) {  //Mais I/O
                if (tmp == 1 || tmp == 2 || tmp == 7 || tmp == 8) {
                    code[i] = SOConstants.INSTRUCTION_BLOCK;
                } else if (tmp == 3 || tmp == 4) {
                    code[i] = SOConstants.INSTRUCTION_READ_MEMORY;
                } else if (tmp == 5 || tmp == 6) {
                    code[i] = SOConstants.INSTRUCTION_WRITE_MEMORY;
                } else {
                    code[i] = SOConstants.INSTRUCTION_CODE;
                }
            }
        }

        proc.setPriority((byte) priority);

        int cor = this.random.nextInt(5);

        switch (cor) {
            case 0:
                proc.setColor(Color.GREEN);
                break;
            case 1:
                proc.setColor(Color.ORANGE);
                break;
            case 2:
                proc.setColor(Color.YELLOW);
                break;
            case 3:
                proc.setColor(Color.CYAN);
                break;
            case 4:
                proc.setColor(Color.LIGHT_GRAY);
                break;
            case 5:
                proc.setColor(Color.MAGENTA);
                break;
        }

        this.soMemory.allocProcess(proc, code);

        return proc;
    }

    public SOMemory getSoMemory() {
        return soMemory;
    }

    public void setSoMemory(SOMemory soMemory) {
        this.soMemory = soMemory;
    }
}