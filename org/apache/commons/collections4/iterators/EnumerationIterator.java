/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationIterator<E>
implements Iterator<E> {
    private final Collection<? super E> collection;
    private Enumeration<? extends E> enumeration;
    private E last;

    public EnumerationIterator() {
        this(null, null);
    }

    public EnumerationIterator(Enumeration<? extends E> enumeration) {
        this(enumeration, null);
    }

    public EnumerationIterator(Enumeration<? extends E> enumeration, Collection<? super E> collection) {
        this.enumeration = enumeration;
        this.collection = collection;
        this.last = null;
    }

    @Override
    public boolean hasNext() {
        return this.enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        this.last = this.enumeration.nextElement();
        return this.last;
    }

    @Override
    public void remove() {
        if (this.collection != null) {
            if (this.last == null) {
                throw new IllegalStateException("next() must have been called for remove() to function");
            }
        } else {
            throw new UnsupportedOperationException("No Collection associated with this Iterator");
        }
        this.collection.remove(this.last);
    }

    public Enumeration<? extends E> getEnumeration() {
        return this.enumeration;
    }

    public void setEnumeration(Enumeration<? extends E> enumeration) {
        this.enumeration = enumeration;
    }
}

