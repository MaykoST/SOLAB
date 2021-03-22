/*
 * SOCPUEvent.java
 *
 * Created on 01/10/2007, 21:13:44
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model.event;

import com.solab.so.SOProcess;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOCPUEvent {

    private Object source;
    private SOProcess process;

    public SOCPUEvent(Object source) {
        this.source = source;
    }

    public SOCPUEvent(Object source, SOProcess process) {
        this.source = source;
        this.process = process;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public SOProcess getProcess() {
        return process;
    }

    public void setProcess(SOProcess process) {
        this.process = process;
    }
}