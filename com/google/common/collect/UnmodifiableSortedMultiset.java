/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.common.collect.SortedMultiset;
import java.util.Comparator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

@GwtCompatible(emulated=true)
final class UnmodifiableSortedMultiset<E>
extends Multisets.UnmodifiableMultiset<E>
implements SortedMultiset<E> {
    private transient @MonotonicNonNull UnmodifiableSortedMultiset<E> descendingMultiset;
    private static final long serialVersionUID = 0L;

    UnmodifiableSortedMultiset(SortedMultiset<E> delegate) {
        super(delegate);
    }

    @Override
    protected SortedMultiset<E> delegate() {
        return (SortedMultiset)super.delegate();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.delegate().comparator();
    }

    @Override
    NavigableSet<E> createElementSet() {
        return Sets.unmodifiableNavigableSet(this.delegate().elementSet());
    }

    @Override
    public NavigableSet<E> elementSet() {
        return (NavigableSet)super.elementSet();
    }

    @Override
    public SortedMultiset<E> descendingMultiset() {
        UnmodifiableSortedMultiset<E> result = this.descendingMultiset;
        if (result == null) {
            result = new UnmodifiableSortedMultiset(this.delegate().descendingMultiset());
            result.descendingMultiset = this;
            this.descendingMultiset = result;
            return this.descendingMultiset;
        }
        return result;
    }

    @Override
    public Multiset.Entry<E> firstEntry() {
        return this.delegate().firstEntry();
    }

    @Override
    public Multiset.Entry<E> lastEntry() {
        return this.delegate().lastEntry();
    }

    @Override
    public Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override
    public SortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(this.delegate().headMultiset(upperBound, boundType));
    }

    @Override
    public SortedMultiset<E> subMultiset(E lowerBound, BoundType lowerBoundType, E upperBound, BoundType upperBoundType) {
        return Multisets.unmodifiableSortedMultiset(this.delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType));
    }

    @Override
    public SortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(this.delegate().tailMultiset(lowerBound, boundType));
    }
}

