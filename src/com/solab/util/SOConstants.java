/*
 * SOConstants.java
 *
 * Created on 12/09/2007, 19:56:03
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.util;

/**
 * $Id: SOConstants.java,v 1.3 2008/05/04 21:54:59 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public interface SOConstants {

    public static final int NORMAL_QUEUE = 0;
    public static final int SIZE_QUEUE = 1;
    public static final int PRIORITY_QUEUE = 2;
    
    public static final int QUANTUM = 5;
    
    public static final int FIFO_SCHEDULER = 0;
    public static final int SJF_SCHEDULER = 1;
    public static final int PRIORITY_SCHEDULER = 2;
    
    public static final int MAX_PROCESS_SIZE = 30;   
    public static final int BASE_PRIORITY = 5;
    public static final int MAX_PRIORITY = 10;
    public static final int MAX_LOCKED_CYCLES = 10;
    
    public static final byte PROCESS_CREATED = 1;
    public static final byte PROCESS_READY = 2;
    public static final byte PROCESS_BLOCKED = 3;
    public static final byte PROCESS_FINISHED = 4;
    
    public static final byte INSTRUCTION_LIST_SIZE = 4;
    public static final byte INSTRUCTION_CODE = 0;
    public static final byte INSTRUCTION_BLOCK = 1;
    public static final byte INSTRUCTION_READ_MEMORY = 2;
    public static final byte INSTRUCTION_WRITE_MEMORY = 3;
    
    public static final int MEMORY_SIZE = 1000;
    public static final int PAGE_SIZE = 10;
}