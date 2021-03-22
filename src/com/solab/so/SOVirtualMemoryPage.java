/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.so;

/**
 * $Id: SOVirtualMemoryPage.java,v 1.1 2008/04/25 00:10:23 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOVirtualMemoryPage {
    private int logicalIndex;
    private byte[] data;
    SOPageTableIndex page;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getLogicalIndex() {
        return logicalIndex;
    }

    public void setLogicalIndex(int logicalIndex) {
        this.logicalIndex = logicalIndex;
    }

    public SOPageTableIndex getPage() {
        return page;
    }

    public void setPage(SOPageTableIndex page) {
        this.page = page;
    }    
}
