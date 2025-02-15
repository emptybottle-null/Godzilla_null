/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.iterators;

import java.util.ListIterator;

public class AbstractListIteratorDecorator<E>
implements ListIterator<E> {
    private final ListIterator<E> iterator;

    public AbstractListIteratorDecorator(ListIterator<E> iterator) {
        if (iterator == null) {
            throw new NullPointerException("ListIterator must not be null");
        }
        this.iterator = iterator;
    }

    protected ListIterator<E> getListIterator() {
        return this.iterator;
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
    public int nextIndex() {
        return this.iterator.nextIndex();
    }

    @Override
    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    @Override
    public E previous() {
        return this.iterator.previous();
    }

    @Override
    public int previousIndex() {
        return this.iterator.previousIndex();
    }

    @Override
    public void remove() {
        this.iterator.remove();
    }

    @Override
    public void set(E obj) {
        this.iterator.set(obj);
    }

    @Override
    public void add(E obj) {
        this.iterator.add(obj);
    }
}

