/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.so;

/**
 * $Id: SOPageTableIndex.java,v 1.2 2008/05/05 00:06:59 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOPageTableIndex {

    private int logicalIndex;
    private int physicalAddress;
    private int sizeUsed;
    private boolean free;
    private boolean available;

    public SOPageTableIndex() {
        this.logicalIndex = -1;
        this.physicalAddress = -1;
        this.sizeUsed = 0;
        this.free = true;
        this.available = true;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getLogicalIndex() {
        return logicalIndex;
    }

    public void setLogicalIndex(int logicalAddress) {
        this.logicalIndex = logicalAddress;
    }

    public int getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(int physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public int getSizeUsed() {
        return sizeUsed;
    }

    public void setSizeUsed(int sizeUsed) {
        this.sizeUsed = sizeUsed;
    }
}
