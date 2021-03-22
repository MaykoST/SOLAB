/*
 * SOScheduler.java
 *
 * Created on 17/09/2007, 20:19:16
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import com.solab.util.SOConstants;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * $Id: SOScheduler.java,v 1.2 2008/05/05 00:06:59 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public abstract class SOScheduler extends SOObject {

    private static Logger logger = Logger.getLogger(SOScheduler.class.getName());
    private SO so;
    private int interval;
    private int quantum;
    private boolean preemptive;

    public SOScheduler() {
        this.quantum = SOConstants.QUANTUM;
        this.interval = 0;
        this.preemptive = false;
    }

    public SO getSo() {
        return so;
    }

    public void setSo(SO so) {
        this.so = so;
    }

    public abstract void schedule();

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void incrementInterval() {
        this.interval++;
    }

    public boolean isPreemptive() {
        return preemptive;
    }

    public void setPreemptive(boolean preemptive) {
        this.preemptive = preemptive;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public SOProcess removeProcessFromCPU() {
        SOProcess proc = null;

        synchronized (this.getSo().getCPU()) {
            proc = this.getSo().getCPU().getCurrentProcess();

            this.getSo().getCPU().setCurrentProcess(null);

            if (!proc.IsBloqued()) {
                if (!proc.IsFinished()) {
                    this.getSo().getProcessQueue().add(proc);
                } else {
                    this.getSo().finishProcess(proc);

                    if (DEBUG) {
                        logger.log(Level.INFO, "Processo-> " + proc.getName() + " finalizado.");
                    }
                }
            }
        }

        return proc;
    }

    public void checkProcessState() {
        if (this.getSo().getCPU().getCurrentProcess().IsBloqued()) {
            SOProcess proc = removeProcessFromCPU();

            this.getSo().lockProcess(proc);

            if (DEBUG) {
                logger.log(Level.INFO, "Processo-> " + proc.getName() + " bloqueado.");
            }

            insertProcessOnCPU();
        }
    }

    public SOProcess insertProcessOnCPU() {
        SOProcess proc = null;

        if (this.getSo().getCPU().getCurrentProcess() == null) {
            if (this.getSo().getProcessQueue().size() > 0) {
                proc = this.getSo().getProcessQueue().get(0);
                this.getSo().getCPU().setCurrentProcess(proc);
                this.setInterval(0);

                this.getSo().getProcessQueue().remove(0);
                
                logger.log(Level.INFO, "Processo-> " + proc.getName() + " recebeu o processador.");
            }
        }

        return proc;
    }
}