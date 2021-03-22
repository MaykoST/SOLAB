/*
 * SOCPU.java
 *
 * Created on 17/09/2007, 20:23:14
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import com.solab.model.SOCPUModel;
import com.solab.model.event.SOCPUEvent;
import com.solab.model.event.SOCPUListener;
import com.solab.util.SOConstants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * $Id: SOCPU.java,v 1.3 2008/05/05 00:06:59 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOCPU extends SOObject implements SOCPUModel {

    private static Logger logger = Logger.getLogger(SOCPU.class.getName());
    private SOProcess currentProcess;
    private Set<SOCPUListener> soCPUListeners;
    private SOMemory soMemory;

    public SOCPU() {
        this.soCPUListeners = new HashSet<SOCPUListener>(1);
    }

    public SOProcess getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(SOProcess currentProcess) {
        this.currentProcess = currentProcess;

        fireProcessChanged(new SOCPUEvent(this, this.currentProcess));
    }

    public SOMemory getSoMemory() {
        return soMemory;
    }

    public void setSoMemory(SOMemory soMemory) {
        this.soMemory = soMemory;
    }

    public void executeInstruction() {
        currentProcess.incrementIp();

        if (!this.currentProcess.IsFinished()) {
            byte inst = soMemory.getProcessBytes(this.currentProcess, 1)[0];

            if (inst == SOConstants.INSTRUCTION_CODE) {
            } else if (inst == SOConstants.INSTRUCTION_BLOCK) {
                currentProcess.block();
            } else if (inst == SOConstants.INSTRUCTION_READ_MEMORY) {
                //TODO: implementar funcoes de memoria
            } else if (inst == SOConstants.INSTRUCTION_WRITE_MEMORY) {
                //TODO: implementar funcoes de memoria
            }
        }

        fireInstructionExecuted(new SOCPUEvent(this, this.currentProcess));
    }

    public void addSOCPUListener(SOCPUListener l) {
        synchronized (soCPUListeners) {
            soCPUListeners.add(l);
        }
    }

    public void removeSOCPUListener(SOCPUListener l) {
        synchronized (soCPUListeners) {
            soCPUListeners.remove(l);
        }
    }

    public void fireProcessChanged(SOCPUEvent e) {
        Iterator<SOCPUListener> iterator;
        synchronized (soCPUListeners) {
            iterator = new HashSet<SOCPUListener>(soCPUListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOCPUListener listener = iterator.next();

            listener.processChanged(e);
        }

        iterator = null;
    }

    public void fireInstructionExecuted(SOCPUEvent e) {
        Iterator<SOCPUListener> iterator;
        synchronized (soCPUListeners) {
            iterator = new HashSet<SOCPUListener>(soCPUListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOCPUListener listener = iterator.next();

            listener.instructionExecuted(e);
        }

        iterator = null;
    }
}