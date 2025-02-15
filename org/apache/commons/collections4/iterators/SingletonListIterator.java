/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;

public class SingletonListIterator<E>
implements ResettableListIterator<E> {
    private boolean beforeFirst = true;
    private boolean nextCalled = false;
    private boolean removed = false;
    private E object;

    public SingletonListIterator(E object) {
        this.object = object;
    }

    @Override
    public boolean hasNext() {
        return this.beforeFirst && !this.removed;
    }

    @Override
    public boolean hasPrevious() {
        return !this.beforeFirst && !this.removed;
    }

    @Override
    public int nextIndex() {
        return this.beforeFirst ? 0 : 1;
    }

    @Override
    public int previousIndex() {
        return this.beforeFirst ? -1 : 0;
    }

    @Override
    public E next() {
        if (!this.beforeFirst || this.removed) {
            throw new NoSuchElementException();
        }
        this.beforeFirst = false;
        this.nextCalled = true;
        return this.object;
    }

    @Override
    public E previous() {
        if (this.beforeFirst || this.removed) {
            throw new NoSuchElementException();
        }
        this.beforeFirst = true;
        return this.object;
    }

    @Override
    public void remove() {
        if (!this.nextCalled || this.removed) {
            throw new IllegalStateException();
        }
        this.object = null;
        this.removed = true;
    }

    @Override
    public void add(E obj) {
        throw new UnsupportedOperationException("add() is not supported by this iterator");
    }

    @Override
    public void set(E obj) {
        if (!this.nextCalled || this.removed) {
            throw new IllegalStateException();
        }
        this.object = obj;
    }

    @Override
    public void reset() {
        this.beforeFirst = true;
        this.nextCalled = false;
    }
}

