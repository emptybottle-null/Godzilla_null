/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class EmptyContiguousSet<C extends Comparable>
extends ContiguousSet<C> {
    EmptyContiguousSet(DiscreteDomain<C> domain) {
        super(domain);
    }

    @Override
    public C first() {
        throw new NoSuchElementException();
    }

    @Override
    public C last() {
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ContiguousSet<C> intersection(ContiguousSet<C> other) {
        return this;
    }

    @Override
    public Range<C> range() {
        throw new NoSuchElementException();
    }

    @Override
    public Range<C> range(BoundType lowerBoundType, BoundType upperBoundType) {
        throw new NoSuchElementException();
    }

    @Override
    ContiguousSet<C> headSetImpl(C toElement, boolean inclusive) {
        return this;
    }

    @Override
    ContiguousSet<C> subSetImpl(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        return this;
    }

    @Override
    ContiguousSet<C> tailSetImpl(C fromElement, boolean fromInclusive) {
        return this;
    }

    @Override
    public boolean contains(Object object) {
        return false;
    }

    @Override
    @GwtIncompatible
    int indexOf(Object target) {
        return -1;
    }

    @Override
    public UnmodifiableIterator<C> iterator() {
        return Iterators.emptyIterator();
    }

    @Override
    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return Iterators.emptyIterator();
    }

    @Override
    boolean isPartialView() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ImmutableList<C> asList() {
        return ImmutableList.of();
    }

    @Override
    public String toString() {
        return "[]";
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object instanceof Set) {
            Set that = (Set)object;
            return that.isEmpty();
        }
        return false;
    }

    @Override
    @GwtIncompatible
    boolean isHashCodeFast() {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    @GwtIncompatible
    Object writeReplace() {
        return new SerializedForm(this.domain);
    }

    @Override
    @GwtIncompatible
    ImmutableSortedSet<C> createDescendingSet() {
        return ImmutableSortedSet.emptySet(Ordering.natural().reverse());
    }

    @GwtIncompatible
    private static final class SerializedForm<C extends Comparable>
    implements Serializable {
        private final DiscreteDomain<C> domain;
        private static final long serialVersionUID = 0L;

        private SerializedForm(DiscreteDomain<C> domain) {
            this.domain = domain;
        }

        private Object readResolve() {
            return new EmptyContiguousSet<C>(this.domain);
        }
    }
}

