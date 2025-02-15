/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;

abstract class AbstractEmptyIterator<E> {
    protected AbstractEmptyIterator() {
    }

    public boolean hasNext() {
        return false;
    }

    public E next() {
        throw new NoSuchElementException("Iterator contains no elements");
    }

    public boolean hasPrevious() {
        return false;
    }

    public E previous() {
        throw new NoSuchElementException("Iterator contains no elements");
    }

    public int nextIndex() {
        return 0;
    }

    public int previousIndex() {
        return -1;
    }

    public void add(E obj) {
        throw new UnsupportedOperationException("add() not supported for empty Iterator");
    }

    public void set(E obj) {
        throw new IllegalStateException("Iterator contains no elements");
    }

    public void remove() {
        throw new IllegalStateException("Iterator contains no elements");
    }

    public void reset() {
    }
}

