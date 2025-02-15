/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.collect;

import com.jgoodies.common.collect.ObservableList2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public final class ArrayListModel<E>
extends ArrayList<E>
implements ObservableList2<E> {
    private static final long serialVersionUID = -6165677201152015546L;
    private EventListenerList listenerList;

    public ArrayListModel() {
        this(10);
    }

    public ArrayListModel(int initialCapacity) {
        super(initialCapacity);
    }

    public ArrayListModel(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public final void add(int index, E element) {
        super.add(index, element);
        this.fireIntervalAdded(index, index);
    }

    @Override
    public final boolean add(E e) {
        int newIndex = this.size();
        super.add(e);
        this.fireIntervalAdded(newIndex, newIndex);
        return true;
    }

    @Override
    public final boolean addAll(int index, Collection<? extends E> c) {
        boolean changed = super.addAll(index, c);
        if (changed) {
            int lastIndex = index + c.size() - 1;
            this.fireIntervalAdded(index, lastIndex);
        }
        return changed;
    }

    @Override
    public final boolean addAll(Collection<? extends E> c) {
        int firstIndex = this.size();
        boolean changed = super.addAll(c);
        if (changed) {
            int lastIndex = firstIndex + c.size() - 1;
            this.fireIntervalAdded(firstIndex, lastIndex);
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Iterator e = this.iterator();
        while (e.hasNext()) {
            if (!c.contains(e.next())) continue;
            e.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator e = this.iterator();
        while (e.hasNext()) {
            if (c.contains(e.next())) continue;
            e.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public final void clear() {
        if (this.isEmpty()) {
            return;
        }
        int oldLastIndex = this.size() - 1;
        super.clear();
        this.fireIntervalRemoved(0, oldLastIndex);
    }

    @Override
    public final E remove(int index) {
        Object removedElement = super.remove(index);
        this.fireIntervalRemoved(index, index);
        return removedElement;
    }

    @Override
    public final boolean remove(Object o) {
        boolean contained;
        int index = this.indexOf(o);
        boolean bl = contained = index != -1;
        if (contained) {
            this.remove(index);
        }
        return contained;
    }

    @Override
    protected final void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
        this.fireIntervalRemoved(fromIndex, toIndex - 1);
    }

    @Override
    public final E set(int index, E element) {
        E previousElement = super.set(index, element);
        this.fireContentsChanged(index, index);
        return previousElement;
    }

    @Override
    public final void addListDataListener(ListDataListener l) {
        this.getEventListenerList().add(ListDataListener.class, l);
    }

    @Override
    public final void removeListDataListener(ListDataListener l) {
        this.getEventListenerList().remove(ListDataListener.class, l);
    }

    public final Object getElementAt(int index) {
        return this.get(index);
    }

    @Override
    public final int getSize() {
        return this.size();
    }

    @Override
    public final void fireContentsChanged(int index) {
        this.fireContentsChanged(index, index);
    }

    @Override
    public final void fireContentsChanged(int index0, int index1) {
        Object[] listeners = this.getEventListenerList().getListenerList();
        ListDataEvent e = null;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] != ListDataListener.class) continue;
            if (e == null) {
                e = new ListDataEvent(this, 0, index0, index1);
            }
            ((ListDataListener)listeners[i + 1]).contentsChanged(e);
        }
    }

    public final ListDataListener[] getListDataListeners() {
        return (ListDataListener[])this.getEventListenerList().getListeners(ListDataListener.class);
    }

    private void fireIntervalAdded(int index0, int index1) {
        Object[] listeners = this.getEventListenerList().getListenerList();
        ListDataEvent e = null;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] != ListDataListener.class) continue;
            if (e == null) {
                e = new ListDataEvent(this, 1, index0, index1);
            }
            ((ListDataListener)listeners[i + 1]).intervalAdded(e);
        }
    }

    private void fireIntervalRemoved(int index0, int index1) {
        Object[] listeners = this.getEventListenerList().getListenerList();
        ListDataEvent e = null;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] != ListDataListener.class) continue;
            if (e == null) {
                e = new ListDataEvent(this, 2, index0, index1);
            }
            ((ListDataListener)listeners[i + 1]).intervalRemoved(e);
        }
    }

    private EventListenerList getEventListenerList() {
        if (this.listenerList == null) {
            this.listenerList = new EventListenerList();
        }
        return this.listenerList;
    }
}

