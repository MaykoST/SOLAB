/*
 * SOSchedulerThread.java
 *
 * Created on 24/09/2007, 20:35:15
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.util;

import com.solab.so.SO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * $Id: SOSchedulerThread.java,v 1.2 2008/06/02 23:34:19 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOSchedulerThread extends Thread {

    private SO so;
    private int delay;
    private boolean running;
    
    public SOSchedulerThread() {
        running = true;
        delay = 1000;
    }
    
    @Override
    public void run() {
        while (running) {
            this.getSo().executeLoop();
            
            try {              
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(SOSchedulerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SO getSo() {
        return so;
    }

    public void setSo(SO so) {
        this.so = so;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }    
}