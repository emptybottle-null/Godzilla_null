/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;

public abstract class AbstractUntypedIteratorDecorator<I, O>
implements Iterator<O> {
    private final Iterator<I> iterator;

    protected AbstractUntypedIteratorDecorator(Iterator<I> iterator) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        this.iterator = iterator;
    }

    protected Iterator<I> getIterator() {
        return this.iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }
}

