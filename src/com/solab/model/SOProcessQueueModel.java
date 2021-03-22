/*
 * SOProcessQueueModel.java
 *
 * Created on 11/09/2007, 20:42:29
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model;

import com.solab.model.event.SOProcessQueueListener;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOProcessQueueModel {

    public void addSOProcessQueueListener(SOProcessQueueListener l);

    public void removeSOProcessQueueListener(SOProcessQueueListener l);
}