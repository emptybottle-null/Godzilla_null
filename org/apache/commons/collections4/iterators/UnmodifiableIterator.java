/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.Unmodifiable;

public final class UnmodifiableIterator<E>
implements Iterator<E>,
Unmodifiable {
    private final Iterator<? extends E> iterator;

    public static <E> Iterator<E> unmodifiableIterator(Iterator<? extends E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (iterator instanceof Unmodifiable) {
            Iterator<? extends E> tmpIterator = iterator;
            return tmpIterator;
        }
        return new UnmodifiableIterator<E>(iterator);
    }

    private UnmodifiableIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public E next() {
        return this.iterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported");
    }
}

