/*
 * SOProcessQueue.java
 *
 * Created on 11/09/2007, 20:12:58
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solab.so;

import com.solab.model.SOProcessQueueModel;
import com.solab.model.event.SOProcessQueueEvent;
import com.solab.model.event.SOProcessQueueListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * $Id$
 *
 * @author Mayko Sartor - <a
 *         href="mailto:maykosartor@gmail.com">maykosartor@gmail.com </a><br>
 */
public class SOProcessQueue<E extends SOProcess> extends Vector<E> implements SOProcessQueueModel {

    private Set<SOProcessQueueListener> soProcessQueueListeners;

    public SOProcessQueue() {
        soProcessQueueListeners = new HashSet<SOProcessQueueListener>(1);
    }

    @Override
    public synchronized boolean add(E e) {
        if (super.add(e)) {
            fireProcessAdded(new SOProcessQueueEvent(this, e));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);

        fireProcessAdded(new SOProcessQueueEvent(this, element));
    }

    @Override
    public synchronized E remove(int index) {
        E result = super.remove(index);

        if (result != null) {
            fireProcessRemoved(new SOProcessQueueEvent(this, result));
        }

        return result;
    }

    public boolean remove(E e) {
        if (super.remove(e)) {
            fireProcessRemoved(new SOProcessQueueEvent(this, e));
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void clear() {
        super.clear();        
        fireProcessRemoved(new SOProcessQueueEvent(this, null));
    }

    @Override
    public synchronized E set(int index, E element) {
        E result = super.set(index, element);

        if (result != null) {
            fireProcessModified(new SOProcessQueueEvent(this, element));
        }

        return result;
    }

    public void addSOProcessQueueListener(SOProcessQueueListener l) {
        synchronized (soProcessQueueListeners) {
            soProcessQueueListeners.add(l);
        }
    }

    public void removeSOProcessQueueListener(SOProcessQueueListener l) {
        synchronized (soProcessQueueListeners) {
            soProcessQueueListeners.remove(l);
        }
    }

    public void fireProcessAdded(SOProcessQueueEvent e) {
        Iterator<SOProcessQueueListener> iterator;
        synchronized (soProcessQueueListeners) {
            iterator = new HashSet<SOProcessQueueListener>(soProcessQueueListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOProcessQueueListener listener = iterator.next();

            listener.processAdded(e);
        }

        iterator = null;
    }

    public void fireProcessModified(SOProcessQueueEvent e) {
        Iterator<SOProcessQueueListener> iterator;
        synchronized (soProcessQueueListeners) {
            iterator = new HashSet<SOProcessQueueListener>(soProcessQueueListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOProcessQueueListener listener = iterator.next();

            listener.processModified(e);
        }

        iterator = null;
    }

    public void fireProcessRemoved(SOProcessQueueEvent e) {
        Iterator<SOProcessQueueListener> iterator;
        synchronized (soProcessQueueListeners) {
            iterator = new HashSet<SOProcessQueueListener>(soProcessQueueListeners).iterator();
        }

        while (iterator.hasNext()) {
            SOProcessQueueListener listener = iterator.next();

            listener.processRemoved(e);
        }

        iterator = null;
    }
}