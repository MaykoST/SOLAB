/*
 * SOPriorityScheduler.java
 *
 * Created on 17/09/2007, 20:30:50
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
public class SOPriorityScheduler extends SOScheduler {

    private static Logger logger = Logger.getLogger(SOPriorityScheduler.class.getName());

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
                } else if (this.isPreemptive()) {

                    //BUG, logica invertida, trocar de > para <
                    if ((!this.getSo().getProcessQueue().isEmpty()) && (this.getSo().getProcessQueue().get(0).getPriority() < this.getSo().getCPU().getCurrentProcess().getPriority())) {

                        if (DEBUG) {
                            logger.log(Level.INFO, "Preempção, Processo-> " + this.getSo().getCPU().getCurrentProcess().getName() + " retirado do processador.");
                        }

                        removeProcessFromCPU();
                        insertProcessOnCPU();

                        if (DEBUG) {
                            logger.log(Level.INFO, "Preempção, Processo-> " + this.getSo().getCPU().getCurrentProcess().getName() + " recebeu o processador.");
                        }
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