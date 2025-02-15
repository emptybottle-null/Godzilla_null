/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Ordering;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import java.util.function.ObjIntConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
final class RegularImmutableSortedMultiset<E>
extends ImmutableSortedMultiset<E> {
    private static final long[] ZERO_CUMULATIVE_COUNTS = new long[]{0L};
    static final ImmutableSortedMultiset<Comparable> NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(Ordering.natural());
    @VisibleForTesting
    final transient RegularImmutableSortedSet<E> elementSet;
    private final transient long[] cumulativeCounts;
    private final transient int offset;
    private final transient int length;

    RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> elementSet, long[] cumulativeCounts, int offset, int length) {
        this.elementSet = elementSet;
        this.cumulativeCounts = cumulativeCounts;
        this.offset = offset;
        this.length = length;
    }

    private int getCount(int index) {
        return (int)(this.cumulativeCounts[this.offset + index + 1] - this.cumulativeCounts[this.offset + index]);
    }

    @Override
    Multiset.Entry<E> getEntry(int index) {
        return Multisets.immutableEntry(this.elementSet.asList().get(index), this.getCount(index));
    }

    @Override
    public void forEachEntry(ObjIntConsumer<? super E> action) {
        Preconditions.checkNotNull(action);
        for (int i = 0; i < this.length; ++i) {
            action.accept(this.elementSet.asList().get(i), this.getCount(i));
        }
    }

    @Override
    public Multiset.Entry<E> firstEntry() {
        return this.isEmpty() ? null : this.getEntry(0);
    }

    @Override
    public Multiset.Entry<E> lastEntry() {
        return this.isEmpty() ? null : this.getEntry(this.length - 1);
    }

    @Override
    public int count(@Nullable Object element) {
        int index = this.elementSet.indexOf(element);
        return index >= 0 ? this.getCount(index) : 0;
    }

    @Override
    public int size() {
        long size = this.cumulativeCounts[this.offset + this.length] - this.cumulativeCounts[this.offset];
        return Ints.saturatedCast(size);
    }

    @Override
    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    @Override
    public ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        return this.getSubMultiset(0, this.elementSet.headIndex(upperBound, Preconditions.checkNotNull(boundType) == BoundType.CLOSED));
    }

    @Override
    public ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        return this.getSubMultiset(this.elementSet.tailIndex(lowerBound, Preconditions.checkNotNull(boundType) == BoundType.CLOSED), this.length);
    }

    ImmutableSortedMultiset<E> getSubMultiset(int from, int to) {
        Preconditions.checkPositionIndexes(from, to, this.length);
        if (from == to) {
            return RegularImmutableSortedMultiset.emptyMultiset(this.comparator());
        }
        if (from == 0 && to == this.length) {
            return this;
        }
        RegularImmutableSortedSet<E> subElementSet = this.elementSet.getSubSet(from, to);
        return new RegularImmutableSortedMultiset<E>(subElementSet, this.cumulativeCounts, this.offset + from, to - from);
    }

    @Override
    boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }
}

