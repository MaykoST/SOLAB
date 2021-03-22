/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.ui;

import com.solab.so.SO;
import com.solab.so.SOProcess;
import javax.swing.table.AbstractTableModel;

/**
 * $Id: SOProcessQueueTableModel.java,v 1.1 2008/05/25 18:41:50 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcessQueueTableModel extends AbstractTableModel{

    private SO so;
    
    public SOProcessQueueTableModel(SO so) {
        this.so = so;
    }
    
    public int getRowCount() {
        if (this.so.getAllProcess() == null) {
            return 0;
        }
        return this.so.getAllProcess().size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (this.so.getAllProcess() == null) {
            return null;
        }
        
        SOProcess process = so.getAllProcess().get(rowIndex);
        
        switch(columnIndex) {
            case 1: return new String(process.getName());
            case 2: return new Integer(process.getPriority());
            
            default: return new Integer(process.getId());
        }
    }
    
    @Override
    public String getColumnName(int column) {
	String result = "";
	
        switch(column) {
            case 0: result = "Identificador"; break;
            case 1: result = "Nome"; break;            
            default: result = "";
        }
        
        return result;
    }

    public SO getSo() {
        return so;
    }

    public void setSo(SO so) {
        this.so = so;
    }    
}
