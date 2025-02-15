/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

class StreamIterator<E>
implements Iterator<E>,
Closeable {
    private final Iterator<E> iterator;
    private final Stream<E> stream;

    public static <T> Iterator<T> iterator(Stream<T> stream) {
        return new StreamIterator<T>(stream).iterator;
    }

    private StreamIterator(Stream<E> stream) {
        this.stream = Objects.requireNonNull(stream, "stream");
        this.iterator = stream.iterator();
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = this.iterator.hasNext();
        if (!hasNext) {
            this.close();
        }
        return hasNext;
    }

    @Override
    public E next() {
        E next = this.iterator.next();
        if (next == null) {
            this.close();
        }
        return next;
    }

    @Override
    public void close() {
        this.stream.close();
    }
}

