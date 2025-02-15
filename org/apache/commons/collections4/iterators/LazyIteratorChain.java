/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.iterators.EmptyIterator;

public abstract class LazyIteratorChain<E>
implements Iterator<E> {
    private int callCounter = 0;
    private boolean chainExhausted = false;
    private Iterator<? extends E> currentIterator = null;
    private Iterator<? extends E> lastUsedIterator = null;

    protected abstract Iterator<? extends E> nextIterator(int var1);

    private void updateCurrentIterator() {
        if (this.callCounter == 0) {
            this.currentIterator = this.nextIterator(++this.callCounter);
            if (this.currentIterator == null) {
                this.currentIterator = EmptyIterator.emptyIterator();
                this.chainExhausted = true;
            }
            this.lastUsedIterator = this.currentIterator;
        }
        while (!this.currentIterator.hasNext() && !this.chainExhausted) {
            Iterator<E> nextIterator;
            if ((nextIterator = this.nextIterator(++this.callCounter)) != null) {
                this.currentIterator = nextIterator;
                continue;
            }
            this.chainExhausted = true;
        }
    }

    @Override
    public boolean hasNext() {
        this.updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.hasNext();
    }

    @Override
    public E next() {
        this.updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.next();
    }

    @Override
    public void remove() {
        if (this.currentIterator == null) {
            this.updateCurrentIterator();
        }
        this.lastUsedIterator.remove();
    }
}

