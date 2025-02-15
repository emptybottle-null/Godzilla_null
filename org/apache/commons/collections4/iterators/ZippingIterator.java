/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.FluentIterable;

public class ZippingIterator<E>
implements Iterator<E> {
    private final Iterator<Iterator<? extends E>> iterators;
    private Iterator<? extends E> nextIterator = null;
    private Iterator<? extends E> lastReturned = null;

    public ZippingIterator(Iterator<? extends E> a, Iterator<? extends E> b) {
        this(new Iterator[]{a, b});
    }

    public ZippingIterator(Iterator<? extends E> a, Iterator<? extends E> b, Iterator<? extends E> c) {
        this(new Iterator[]{a, b, c});
    }

    public ZippingIterator(Iterator<? extends E> ... iterators) {
        ArrayList<Iterator<? extends E>> list = new ArrayList<Iterator<? extends E>>();
        for (Iterator<? extends E> iterator : iterators) {
            if (iterator == null) {
                throw new NullPointerException("Iterator must not be null.");
            }
            list.add(iterator);
        }
        this.iterators = FluentIterable.of(list).loop().iterator();
    }

    @Override
    public boolean hasNext() {
        if (this.nextIterator != null) {
            return true;
        }
        while (this.iterators.hasNext()) {
            Iterator<E> childIterator = this.iterators.next();
            if (childIterator.hasNext()) {
                this.nextIterator = childIterator;
                return true;
            }
            this.iterators.remove();
        }
        return false;
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        E val = this.nextIterator.next();
        this.lastReturned = this.nextIterator;
        this.nextIterator = null;
        return val;
    }

    @Override
    public void remove() {
        if (this.lastReturned == null) {
            throw new IllegalStateException("No value can be removed at present");
        }
        this.lastReturned.remove();
        this.lastReturned = null;
    }
}

