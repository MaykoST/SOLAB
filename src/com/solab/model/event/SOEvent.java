/*
 * SOEvent.java
 *
 * Created on 12/09/2007, 19:45:51
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model.event;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOEvent {

    private Object source;

    public SOEvent(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}