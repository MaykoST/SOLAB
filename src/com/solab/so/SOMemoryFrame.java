/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

/**
 * $Id: SOMemoryFrame.java,v 1.1 2008/04/25 00:10:32 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOMemoryFrame {

    private boolean free;
    private int physicalAddress;

    public SOMemoryFrame(int physicalAddress) {
        this.physicalAddress = physicalAddress;
        this.free = true;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(int physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
}
