/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator<E>
implements Iterator<E> {
    private final Iterator<? extends E> iterator;
    private boolean exhausted = false;
    private boolean slotFilled = false;
    private E slot;

    public static <E> PeekingIterator<E> peekingIterator(Iterator<? extends E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (iterator instanceof PeekingIterator) {
            PeekingIterator it = (PeekingIterator)iterator;
            return it;
        }
        return new PeekingIterator<E>(iterator);
    }

    public PeekingIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    private void fill() {
        if (this.exhausted || this.slotFilled) {
            return;
        }
        if (this.iterator.hasNext()) {
            this.slot = this.iterator.next();
            this.slotFilled = true;
        } else {
            this.exhausted = true;
            this.slot = null;
            this.slotFilled = false;
        }
    }

    @Override
    public boolean hasNext() {
        if (this.exhausted) {
            return false;
        }
        return this.slotFilled || this.iterator.hasNext();
    }

    public E peek() {
        this.fill();
        return this.exhausted ? null : (E)this.slot;
    }

    public E element() {
        this.fill();
        if (this.exhausted) {
            throw new NoSuchElementException();
        }
        return this.slot;
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        E x = this.slotFilled ? this.slot : this.iterator.next();
        this.slot = null;
        this.slotFilled = false;
        return x;
    }

    @Override
    public void remove() {
        if (this.slotFilled) {
            throw new IllegalStateException("peek() or element() called before remove()");
        }
        this.iterator.remove();
    }
}

