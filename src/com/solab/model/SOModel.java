/*
 * SOModel.java
 *
 * Created on 10/09/2007, 22:37:28
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.model;

import com.solab.model.event.SOListener;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOModel {

    public void addSOListener(SOListener l);

    public void removeSOListener(SOListener l);
}