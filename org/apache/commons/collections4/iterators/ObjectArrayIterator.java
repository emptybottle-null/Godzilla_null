/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

public class ObjectArrayIterator<E>
implements ResettableIterator<E> {
    final E[] array;
    final int startIndex;
    final int endIndex;
    int index = 0;

    public ObjectArrayIterator(E ... array) {
        this(array, 0, array.length);
    }

    public ObjectArrayIterator(E[] array, int start) {
        this(array, start, array.length);
    }

    public ObjectArrayIterator(E[] array, int start, int end) {
        if (start < 0) {
            throw new ArrayIndexOutOfBoundsException("Start index must not be less than zero");
        }
        if (end > array.length) {
            throw new ArrayIndexOutOfBoundsException("End index must not be greater than the array length");
        }
        if (start > array.length) {
            throw new ArrayIndexOutOfBoundsException("Start index must not be greater than the array length");
        }
        if (end < start) {
            throw new IllegalArgumentException("End index must not be less than start index");
        }
        this.array = array;
        this.startIndex = start;
        this.endIndex = end;
        this.index = start;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.endIndex;
    }

    @Override
    public E next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.array[this.index++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported for an ObjectArrayIterator");
    }

    public E[] getArray() {
        return this.array;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    @Override
    public void reset() {
        this.index = this.startIndex;
    }
}

