/*
 * SOListener.java
 *
 * Created on 10/09/2007, 22:36:40
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model.event;

/**
 * $Id: SOListener.java,v 1.2 2008/05/13 22:37:37 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOListener {

    public void soStarted(SOEvent e);

    public void processQueueInitialized(SOEvent e);

    public void schedulerInitialized(SOEvent e);
    
    public void memoryInitialized(SOEvent e);
}