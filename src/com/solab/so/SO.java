/*
 * SO.java
 *
 * Created on 10/09/2007, 22:30:31
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

import com.solab.model.SOModel;
import com.solab.model.event.SOEvent;
import com.solab.model.event.SOListener;
import com.solab.util.SOConstants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * $Id: SO.java,v 1.6 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SO extends SOObject implements SOModel {

    private static Logger logger = Logger.getLogger(SO.class.getName());
    private SOProcessQueue<SOProcess> processQueue;
    private SOProcessQueue<SOProcess> lockQueue;
    private SOProcessQueue<SOProcess> finishedQueue;
    private SOProcessQueue<SOProcess> allProcess;
    private Set<SOListener> soListeners;
    private SOCPU CPU;
    private SOMemory soMemory;
    private SOScheduler scheduler;
    private Random random;
    private int quantum;

    public SO() {
        this.random = new Random();
        this.quantum = SOConstants.QUANTUM;

        this.soListeners = new HashSet<SOListener>(1);
        this.soMemory = new SOMemory(SOConstants.MEMORY_SIZE, SOConstants.PAGE_SIZE);
        this.CPU = new SOCPU();
        this.CPU.setSoMemory(this.soMemory);

        SOProcessFactory.getInstance().setSoMemory(this.soMemory);

        this.lockQueue = new SOProcessQueue<SOProcess>();
        this.finishedQueue = new SOProcessQueue<SOProcess>();
        this.allProcess = new SOProcessQueue<SOProcess>();

        chanceQueueType(SOConstants.NORMAL_QUEUE);
        changeSchedulerType(SOConstants.FIFO_SCHEDULER);
        this.scheduler.setSo(this);
    }

    public void resetSO() {
        this.soMemory.reset();
        this.CPU.setCurrentProcess(null);

        this.processQueue.clear();
        this.lockQueue.clear();
        this.finishedQueue.clear();
        this.allProcess.clear();

        SOProcessFactory.getInstance().setProcessCounter(0);

        /*fireProcessQueueInitialized(new SOEvent(this));
        fireSchedulerInitialized(new SOEvent(this));
        fireMemoryInitialized(new SOEvent(this));
        fireSOStarted(new SOEvent(this));
         */
    }

    public SOCPU getCPU() {
        return CPU;
    }

    public void setCPU(SOCPU CPU) {
        this.CPU = CPU;
    }

    public void startSO() {
    }

    public SOProcessQueue<SOProcess> getProcessQueue() {
        return processQueue;
    }

    public void setProcessQueue(SOProcessQueue<SOProcess> processQueue) {
        this.processQueue = processQueue;
    }

    public void chanceQueueType(int type) {
        if (type == SOConstants.NORMAL_QUEUE) {
            processQueue = new SOProcessQueue<SOProcess>();
        } else if (type == SOConstants.SIZE_QUEUE) {
            processQueue = new SOProcessSizeQueue<SOProcess>();
        } else if (type == SOConstants.PRIORITY_QUEUE) {
            processQueue = new SOProcessPriorityQueue<SOProcess>();
        } else {
            throw new IllegalArgumentException("Invalid queue type");
        }

        fireProcessQueueInitialized(new SOEvent(this));
    }

    public void clearQueue() {
        if (processQueue != null) {
            while (!processQueue.isEmpty()) {
                processQueue.remove(processQueue.size() - 1);
            }
        }
    }

    public void addSOListener(SOListener l) {
        synchronized (soListeners) {
            soListeners.add(l);
        }
    }

    public void removeSOListener(SOListener l) {
        synchronized (soListeners) {
            soListeners.remove(l);
        }
    }

    public void fireProcessQueueInitialized(SOEvent e) {
        Iterator<SOListener> iterator;
        synchronized (soListeners) {
            iterator = new HashSet<SOListener>(soListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOListener listener = iterator.next();

            listener.processQueueInitialized(e);
        }

        iterator = null;
    }

    public void fireSchedulerInitialized(SOEvent e) {
        Iterator<SOListener> iterator;
        synchronized (soListeners) {
            iterator = new HashSet<SOListener>(soListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOListener listener = iterator.next();

            listener.schedulerInitialized(e);
        }

        iterator = null;
    }

    public void fireMemoryInitialized(SOEvent e) {
        Iterator<SOListener> iterator;
        synchronized (soListeners) {
            iterator = new HashSet<SOListener>(soListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOListener listener = iterator.next();

            listener.memoryInitialized(e);
        }

        iterator = null;
    }

    public void fireSOStarted(SOEvent e) {
        Iterator<SOListener> iterator;
        synchronized (soListeners) {
            iterator = new HashSet<SOListener>(soListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOListener listener = iterator.next();

            listener.soStarted(e);
        }

        iterator = null;
    }

    public void createProcess() {
        SOProcess proc = SOProcessFactory.getInstance().createProcess();

        processQueue.add(proc);
        allProcess.add(proc);
    }

    public void createProcess(SOProcess proc) {
        processQueue.add(proc);
        allProcess.add(proc);

        logger.log(Level.INFO, "Processo-> " + proc.getName() + " criado.");
    }

    public void lockProcess(SOProcess proc) {
        proc.setLockedCycles(Math.max(1, this.random.nextInt(SOConstants.MAX_LOCKED_CYCLES)));

        this.lockQueue.add(proc);
    }

    public void finishProcess(SOProcess proc) {
        //Desaloca processo da memoria        
        this.processQueue.remove(proc);
        this.soMemory.deallocProcess(proc);
        this.finishedQueue.add(proc);
    }

    public void executeLoop() {
        this.scheduler.schedule();
    }

    public SOScheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(SOScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void updateLockQueue() {
        SOProcess proc;

        synchronized (this.lockQueue) {
            Iterator<SOProcess> it = this.lockQueue.iterator();

            while (it.hasNext()) {
                proc = it.next();

                proc.decrementLockeCycles();

                if (proc.IsReady()) {
                    it.remove();

                    proc.unblock();

                    this.processQueue.add(proc);

                    if (DEBUG) {
                        logger.log(Level.INFO, "Processo-> " + proc.getName() + " pronto.");
                    }
                }
            }
        }
    }

    public void changeSchedulerType(int type) {
        if (type == SOConstants.FIFO_SCHEDULER) {
            scheduler = new SOFIFOScheduler();

            if (!(this.getProcessQueue() instanceof SOProcessQueue)) {
                chanceQueueType(SOConstants.NORMAL_QUEUE);
            }
        } else if (type == SOConstants.SJF_SCHEDULER) {
            scheduler = new SOSJFScheduler();

            if (!(this.getProcessQueue() instanceof SOProcessSizeQueue)) {
                chanceQueueType(SOConstants.SIZE_QUEUE);
            }
        } else if (type == SOConstants.PRIORITY_SCHEDULER) {
            scheduler = new SOPriorityScheduler();

            if (!(this.getProcessQueue() instanceof SOProcessPriorityQueue)) {
                chanceQueueType(SOConstants.PRIORITY_QUEUE);
            }
        } else {
            throw new IllegalArgumentException("Invalid scheduler type");
        }

        scheduler.setSo(this);
        scheduler.setQuantum(this.quantum);

        fireSchedulerInitialized(new SOEvent(this));
    }

    public SOProcessQueue<SOProcess> getFinishedQueue() {
        return finishedQueue;
    }

    public void setFinishedQueue(SOProcessQueue<SOProcess> finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    public SOProcessQueue<SOProcess> getLockQueue() {
        return lockQueue;
    }

    public void setLockQueue(SOProcessQueue<SOProcess> lockQueue) {
        this.lockQueue = lockQueue;
    }

    public void enablePreemption() {
        if (this.getScheduler() != null) {
            this.getScheduler().setPreemptive(true);
        }
    }

    public void disablePreemption() {
        if (this.getScheduler() != null) {
            this.getScheduler().setPreemptive(false);
        }
    }

    public SOMemory getSoMemory() {
        return soMemory;
    }

    public void setSoMemory(SOMemory soMemory) {
        this.soMemory = soMemory;
    }

    public SOProcessQueue<SOProcess> getAllProcess() {
        return allProcess;
    }

    public void setAllProcess(SOProcessQueue<SOProcess> allProcess) {
        this.allProcess = allProcess;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}
