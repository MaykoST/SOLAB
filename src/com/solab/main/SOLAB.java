/*
 * SOLAB.java
 *
 * Created on 10/09/2007, 19:33:00 
 */
package com.solab.main;

import com.solab.so.SO;
import com.solab.so.SOObject;
import com.solab.ui.SOLABFrame;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * $Id: SOLAB.java,v 1.3 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOLAB extends SOObject {

    //Sistema operacional
    SO so;
    SOLABFrame solabFrame;

    public SOLAB() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SOLAB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SOLAB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SOLAB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SOLAB.class.getName()).log(Level.SEVERE, null, ex);
        }

        so = new SO();

        solabFrame = new SOLABFrame();
        solabFrame.setSo(so);
        solabFrame.setTitle("SOLAB");
        solabFrame.setLocationRelativeTo(null);
        solabFrame.setLocation(0, 0);
        solabFrame.setVisible(true);

        so.startSO();
    }

    public static void main(String[] args) {
        new SOLAB();
    }
}