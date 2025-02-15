/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.set.AbstractSortedSetDecorator;

public abstract class AbstractNavigableSetDecorator<E>
extends AbstractSortedSetDecorator<E>
implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528L;

    protected AbstractNavigableSetDecorator() {
    }

    protected AbstractNavigableSetDecorator(NavigableSet<E> set) {
        super(set);
    }

    @Override
    protected NavigableSet<E> decorated() {
        return (NavigableSet)super.decorated();
    }

    @Override
    public E lower(E e) {
        return this.decorated().lower(e);
    }

    @Override
    public E floor(E e) {
        return this.decorated().floor(e);
    }

    @Override
    public E ceiling(E e) {
        return this.decorated().ceiling(e);
    }

    @Override
    public E higher(E e) {
        return this.decorated().higher(e);
    }

    @Override
    public E pollFirst() {
        return this.decorated().pollFirst();
    }

    @Override
    public E pollLast() {
        return this.decorated().pollLast();
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return this.decorated().descendingSet();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return this.decorated().descendingIterator();
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return this.decorated().subSet(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return this.decorated().headSet(toElement, inclusive);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return this.decorated().tailSet(fromElement, inclusive);
    }
}

