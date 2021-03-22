/*
 * SOCPUModel.java
 * 
 * Created on 01/10/2007, 21:12:35
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model;

import com.solab.model.event.SOCPUListener;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOCPUModel {

    public void addSOCPUListener(SOCPUListener l);
    
    public void removeSOCPUListener(SOCPUListener l);
}
