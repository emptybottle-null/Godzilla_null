/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class LoopingListIterator<E>
implements ResettableListIterator<E> {
    private final List<E> list;
    private ListIterator<E> iterator;

    public LoopingListIterator(List<E> list) {
        if (list == null) {
            throw new NullPointerException("The list must not be null");
        }
        this.list = list;
        this._reset();
    }

    @Override
    public boolean hasNext() {
        return !this.list.isEmpty();
    }

    @Override
    public E next() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        }
        if (!this.iterator.hasNext()) {
            this.reset();
        }
        return this.iterator.next();
    }

    @Override
    public int nextIndex() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        }
        if (!this.iterator.hasNext()) {
            return 0;
        }
        return this.iterator.nextIndex();
    }

    @Override
    public boolean hasPrevious() {
        return !this.list.isEmpty();
    }

    @Override
    public E previous() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        }
        if (!this.iterator.hasPrevious()) {
            E result = null;
            while (this.iterator.hasNext()) {
                result = this.iterator.next();
            }
            this.iterator.previous();
            return result;
        }
        return this.iterator.previous();
    }

    @Override
    public int previousIndex() {
        if (this.list.isEmpty()) {
            throw new NoSuchElementException("There are no elements for this iterator to loop on");
        }
        if (!this.iterator.hasPrevious()) {
            return this.list.size() - 1;
        }
        return this.iterator.previousIndex();
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

    @Override
    public void add(E obj) {
        this.iterator.add(obj);
    }

    @Override
    public void set(E obj) {
        this.iterator.set(obj);
    }

    @Override
    public void reset() {
        this._reset();
    }

    private void _reset() {
        this.iterator = this.list.listIterator();
    }

    public int size() {
        return this.list.size();
    }
}

