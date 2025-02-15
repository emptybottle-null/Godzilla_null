/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.commons.collections4.iterators.EmptyIterator;

public class IteratorChain<E>
implements Iterator<E> {
    private final Queue<Iterator<? extends E>> iteratorChain = new LinkedList<Iterator<? extends E>>();
    private Iterator<? extends E> currentIterator = null;
    private Iterator<? extends E> lastUsedIterator = null;
    private boolean isLocked = false;

    public IteratorChain() {
    }

    public IteratorChain(Iterator<? extends E> iterator) {
        this.addIterator(iterator);
    }

    public IteratorChain(Iterator<? extends E> first, Iterator<? extends E> second) {
        this.addIterator(first);
        this.addIterator(second);
    }

    public IteratorChain(Iterator<? extends E> ... iteratorChain) {
        for (Iterator<? extends E> element : iteratorChain) {
            this.addIterator(element);
        }
    }

    public IteratorChain(Collection<Iterator<? extends E>> iteratorChain) {
        for (Iterator<E> iterator : iteratorChain) {
            this.addIterator(iterator);
        }
    }

    public void addIterator(Iterator<? extends E> iterator) {
        this.checkLocked();
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        this.iteratorChain.add(iterator);
    }

    public int size() {
        return this.iteratorChain.size();
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    private void checkLocked() {
        if (this.isLocked) {
            throw new UnsupportedOperationException("IteratorChain cannot be changed after the first use of a method from the Iterator interface");
        }
    }

    private void lockChain() {
        if (!this.isLocked) {
            this.isLocked = true;
        }
    }

    protected void updateCurrentIterator() {
        if (this.currentIterator == null) {
            this.currentIterator = this.iteratorChain.isEmpty() ? EmptyIterator.emptyIterator() : this.iteratorChain.remove();
            this.lastUsedIterator = this.currentIterator;
        }
        while (!this.currentIterator.hasNext() && !this.iteratorChain.isEmpty()) {
            this.currentIterator = this.iteratorChain.remove();
        }
    }

    @Override
    public boolean hasNext() {
        this.lockChain();
        this.updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.hasNext();
    }

    @Override
    public E next() {
        this.lockChain();
        this.updateCurrentIterator();
        this.lastUsedIterator = this.currentIterator;
        return this.currentIterator.next();
    }

    @Override
    public void remove() {
        this.lockChain();
        if (this.currentIterator == null) {
            this.updateCurrentIterator();
        }
        this.lastUsedIterator.remove();
    }
}

