/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model.event;

/**
 * $Id: SOMemoryEvent.java,v 1.1 2008/05/13 22:37:37 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOMemoryEvent {
    
    private Object source;

    public SOMemoryEvent(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
