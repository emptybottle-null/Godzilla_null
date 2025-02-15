/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

public class SingletonIterator<E>
implements ResettableIterator<E> {
    private final boolean removeAllowed;
    private boolean beforeFirst = true;
    private boolean removed = false;
    private E object;

    public SingletonIterator(E object) {
        this(object, true);
    }

    public SingletonIterator(E object, boolean removeAllowed) {
        this.object = object;
        this.removeAllowed = removeAllowed;
    }

    @Override
    public boolean hasNext() {
        return this.beforeFirst && !this.removed;
    }

    @Override
    public E next() {
        if (!this.beforeFirst || this.removed) {
            throw new NoSuchElementException();
        }
        this.beforeFirst = false;
        return this.object;
    }

    @Override
    public void remove() {
        if (this.removeAllowed) {
            if (this.removed || this.beforeFirst) {
                throw new IllegalStateException();
            }
        } else {
            throw new UnsupportedOperationException();
        }
        this.object = null;
        this.removed = true;
    }

    @Override
    public void reset() {
        this.beforeFirst = true;
    }
}

