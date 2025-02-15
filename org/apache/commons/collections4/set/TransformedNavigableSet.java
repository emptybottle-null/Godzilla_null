/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.set.TransformedSortedSet;

public class TransformedNavigableSet<E>
extends TransformedSortedSet<E>
implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528L;

    public static <E> TransformedNavigableSet<E> transformingNavigableSet(NavigableSet<E> set, Transformer<? super E, ? extends E> transformer) {
        return new TransformedNavigableSet<E>(set, transformer);
    }

    public static <E> TransformedNavigableSet<E> transformedNavigableSet(NavigableSet<E> set, Transformer<? super E, ? extends E> transformer) {
        TransformedNavigableSet<E> decorated = new TransformedNavigableSet<E>(set, transformer);
        if (set.size() > 0) {
            Object[] values = set.toArray();
            set.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedNavigableSet(NavigableSet<E> set, Transformer<? super E, ? extends E> transformer) {
        super(set, transformer);
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
        return TransformedNavigableSet.transformingNavigableSet(this.decorated().descendingSet(), this.transformer);
    }

    @Override
    public Iterator<E> descendingIterator() {
        return this.decorated().descendingIterator();
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        NavigableSet<E> sub = this.decorated().subSet(fromElement, fromInclusive, toElement, toInclusive);
        return TransformedNavigableSet.transformingNavigableSet(sub, this.transformer);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        NavigableSet<E> head = this.decorated().headSet(toElement, inclusive);
        return TransformedNavigableSet.transformingNavigableSet(head, this.transformer);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        NavigableSet<E> tail = this.decorated().tailSet(fromElement, inclusive);
        return TransformedNavigableSet.transformingNavigableSet(tail, this.transformer);
    }
}

