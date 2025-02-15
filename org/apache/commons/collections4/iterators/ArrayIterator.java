/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;

public class ArrayIterator<E>
implements ResettableIterator<E> {
    final Object array;
    final int startIndex;
    final int endIndex;
    int index = 0;

    public ArrayIterator(Object array) {
        this(array, 0);
    }

    public ArrayIterator(Object array, int startIndex) {
        this(array, startIndex, Array.getLength(array));
    }

    public ArrayIterator(Object array, int startIndex, int endIndex) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.index = startIndex;
        int len = Array.getLength(array);
        this.checkBound(startIndex, len, "start");
        this.checkBound(endIndex, len, "end");
        if (endIndex < startIndex) {
            throw new IllegalArgumentException("End index must not be less than start index.");
        }
    }

    protected void checkBound(int bound, int len, String type) {
        if (bound > len) {
            throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s beyond the end of the array. ");
        }
        if (bound < 0) {
            throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s before the start of the array. ");
        }
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
        return (E)Array.get(this.array, this.index++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() method is not supported");
    }

    public Object getArray() {
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

