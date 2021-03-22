/*
 * SOFIFOScheduler.java
 *
 * Created on 17/09/2007, 20:20:54
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOFIFOScheduler extends SOScheduler {

    private static Logger logger = Logger.getLogger(SOFIFOScheduler.class.getName());

    public SOFIFOScheduler() {
    }

    public void schedule() {
        if (this.getSo().getCPU().getCurrentProcess() != null) {
            if (this.getSo().getCPU().getCurrentProcess().IsFinished()) {
                removeProcessFromCPU();
                insertProcessOnCPU();
            } else {
                if (this.getInterval() >= this.getQuantum()) {
                    if (this.getSo().getProcessQueue().size() > 0) {
                        removeProcessFromCPU();
                        insertProcessOnCPU();
                    } else {
                        this.getSo().getCPU().executeInstruction();
                        this.incrementInterval();

                        this.checkProcessState();
                    }
                } else {
                    this.getSo().getCPU().executeInstruction();
                    this.incrementInterval();

                    this.checkProcessState();
                }
            }
        } else {
            insertProcessOnCPU();
        }

        this.getSo().updateLockQueue();
    }
}