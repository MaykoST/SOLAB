/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.model.event;

import java.util.EventListener;

/**
 * $Id: SOMemoryListener.java,v 1.1 2008/05/13 22:37:37 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOMemoryListener extends EventListener {

    public void memoryChanged(SOMemoryEvent e);
}
