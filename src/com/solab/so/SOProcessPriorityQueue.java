/*
 * SOProcessPriorityQueue.java
 *
 * Created on 11/09/2007, 20:32:05
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.so;

/**
 * $Id: SOProcessPriorityQueue.java,v 1.2 2008/06/16 13:22:55 Mayko Exp $
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcessPriorityQueue<E extends SOProcess> extends SOProcessQueue<E> {

    public SOProcessPriorityQueue() {
    }

    @Override
    public boolean add(E e) {
        for (int index = 0; index < this.size(); index++) {
            if (e.getPriority() < this.get(index).getPriority()) {
                this.add(index, e);

                return true;
            }
        }

        return super.add(e);
    }
}