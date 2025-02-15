/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorEnumeration<E>
implements Enumeration<E> {
    private Iterator<? extends E> iterator;

    public IteratorEnumeration() {
    }

    public IteratorEnumeration(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasMoreElements() {
        return this.iterator.hasNext();
    }

    @Override
    public E nextElement() {
        return this.iterator.next();
    }

    public Iterator<? extends E> getIterator() {
        return this.iterator;
    }

    public void setIterator(Iterator<? extends E> iterator) {
        this.iterator = iterator;
    }
}

