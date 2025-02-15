/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.set.PredicatedSortedSet;

public class PredicatedNavigableSet<E>
extends PredicatedSortedSet<E>
implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528L;

    public static <E> PredicatedNavigableSet<E> predicatedNavigableSet(NavigableSet<E> set, Predicate<? super E> predicate) {
        return new PredicatedNavigableSet<E>(set, predicate);
    }

    protected PredicatedNavigableSet(NavigableSet<E> set, Predicate<? super E> predicate) {
        super(set, predicate);
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
        return PredicatedNavigableSet.predicatedNavigableSet(this.decorated().descendingSet(), this.predicate);
    }

    @Override
    public Iterator<E> descendingIterator() {
        return this.decorated().descendingIterator();
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        NavigableSet<E> sub = this.decorated().subSet(fromElement, fromInclusive, toElement, toInclusive);
        return PredicatedNavigableSet.predicatedNavigableSet(sub, this.predicate);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        NavigableSet<E> head = this.decorated().headSet(toElement, inclusive);
        return PredicatedNavigableSet.predicatedNavigableSet(head, this.predicate);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        NavigableSet<E> tail = this.decorated().tailSet(fromElement, inclusive);
        return PredicatedNavigableSet.predicatedNavigableSet(tail, this.predicate);
    }
}

