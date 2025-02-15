/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.internal;

import java.lang.reflect.Array;
import java.util.Iterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ArrayIterator
implements Iterator<Object> {
    private final Object array;
    private int currentIndex = 0;

    public ArrayIterator(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("not an array");
        }
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex < Array.getLength(this.array);
    }

    @Override
    public Object next() {
        return Array.get(this.array, this.currentIndex++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("cannot remove items from an array");
    }
}

