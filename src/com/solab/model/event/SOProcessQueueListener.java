/*
 * SOProcessQueueListener.java
 *
 * Created on 11/09/2007, 20:47:00
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
public interface SOProcessQueueListener extends EventListener {

    public void processAdded(SOProcessQueueEvent e);

    public void processModified(SOProcessQueueEvent e);

    public void processRemoved(SOProcessQueueEvent e);
}