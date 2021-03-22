/*
 * SOCPUListener.java
 * 
 * Created on 01/10/2007, 21:13:10
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model.event;

import java.util.EventListener;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOCPUListener extends EventListener {
    
    public void processChanged(SOCPUEvent e);
    
    public void instructionExecuted(SOCPUEvent e);
}
