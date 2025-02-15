/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PushbackIterator<E>
implements Iterator<E> {
    private final Iterator<? extends E> iterator;
    private final Deque<E> items = new ArrayDeque();

    public static <E> PushbackIterator<E> pushbackIterator(Iterator<? extends E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (iterator instanceof PushbackIterator) {
            PushbackIterator it = (PushbackIterator)iterator;
            return it;
        }
        return new PushbackIterator<E>(iterator);
    }

    public PushbackIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    public void pushback(E item) {
        this.items.push(item);
    }

    @Override
    public boolean hasNext() {
        return !this.items.isEmpty() || this.iterator.hasNext();
    }

    @Override
    public E next() {
        return !this.items.isEmpty() ? this.items.pop() : this.iterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

