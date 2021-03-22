/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.model;

import com.solab.model.event.SOMemoryListener;

/**
 *
 * @author Mayko
 */
public interface SOMemoryModel {

    public void addSOMemoryListener(SOMemoryListener l);

    public void removeSOMemoryListener(SOMemoryListener l);
}
