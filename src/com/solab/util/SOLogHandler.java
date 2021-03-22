/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solab.util;

import com.solab.ui.SOLogPanel;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author comp2
 */
public class SOLogHandler extends Handler {

    private SOLogPanel soLogPanel;

    @Override
    public void publish(LogRecord record) {
        soLogPanel.log(record.getMessage());
    }

    @Override
    public void flush() {
        return;
    }

    @Override
    public void close() throws SecurityException {
        return;
    }

    public SOLogPanel getSoLogPanel() {
        return soLogPanel;
    }

    public void setSoLogPanel(SOLogPanel soLogPanel) {
        this.soLogPanel = soLogPanel;
    }
}
