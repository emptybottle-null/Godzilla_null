/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.set;

import java.util.Comparator;
import java.util.SortedSet;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.set.TransformedSet;

public class TransformedSortedSet<E>
extends TransformedSet<E>
implements SortedSet<E> {
    private static final long serialVersionUID = -1675486811351124386L;

    public static <E> TransformedSortedSet<E> transformingSortedSet(SortedSet<E> set, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSortedSet<E>(set, transformer);
    }

    public static <E> TransformedSortedSet<E> transformedSortedSet(SortedSet<E> set, Transformer<? super E, ? extends E> transformer) {
        TransformedSortedSet<E> decorated = new TransformedSortedSet<E>(set, transformer);
        if (set.size() > 0) {
            Object[] values = set.toArray();
            set.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedSortedSet(SortedSet<E> set, Transformer<? super E, ? extends E> transformer) {
        super(set, transformer);
    }

    protected SortedSet<E> getSortedSet() {
        return (SortedSet)this.decorated();
    }

    @Override
    public E first() {
        return this.getSortedSet().first();
    }

    @Override
    public E last() {
        return this.getSortedSet().last();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.getSortedSet().comparator();
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        SortedSet<E> set = this.getSortedSet().subSet(fromElement, toElement);
        return new TransformedSortedSet<E>(set, this.transformer);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        SortedSet<E> set = this.getSortedSet().headSet(toElement);
        return new TransformedSortedSet<E>(set, this.transformer);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        SortedSet<E> set = this.getSortedSet().tailSet(fromElement);
        return new TransformedSortedSet<E>(set, this.transformer);
    }
}

