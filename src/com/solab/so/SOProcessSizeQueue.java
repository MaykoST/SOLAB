/*
 * SOProcessSizeQueue.java
 *
 * Created on 11/09/2007, 20:21:44
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.so;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcessSizeQueue<E extends SOProcess> extends SOProcessQueue<E> {

    public SOProcessSizeQueue() {
    }

    @Override
    public boolean add(E e) {
        for (int index = 0; index < this.size(); index++) {
            if (e.getCodeSize() < this.get(index).getCodeSize()) {
                this.add(index, e);

                return true;
            }
        }

        return super.add(e);
    }
}