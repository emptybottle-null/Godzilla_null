/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.DescendingImmutableSortedSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.EmptyContiguousSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;
import com.google.common.collect.Range;
import com.google.common.collect.RegularContiguousSet;
import java.util.NoSuchElementException;

@GwtCompatible(emulated=true)
public abstract class ContiguousSet<C extends Comparable>
extends ImmutableSortedSet<C> {
    final DiscreteDomain<C> domain;

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> domain) {
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(domain);
        Range<C> effectiveRange = range;
        try {
            if (!range.hasLowerBound()) {
                effectiveRange = effectiveRange.intersection(Range.atLeast(domain.minValue()));
            }
            if (!range.hasUpperBound()) {
                effectiveRange = effectiveRange.intersection(Range.atMost(domain.maxValue()));
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
        boolean empty = effectiveRange.isEmpty() || Range.compareOrThrow(range.lowerBound.leastValueAbove(domain), range.upperBound.greatestValueBelow(domain)) > 0;
        return empty ? new EmptyContiguousSet<C>(domain) : new RegularContiguousSet<C>(effectiveRange, domain);
    }

    @Beta
    public static ContiguousSet<Integer> closed(int lower, int upper) {
        return ContiguousSet.create(Range.closed(lower, upper), DiscreteDomain.integers());
    }

    @Beta
    public static ContiguousSet<Long> closed(long lower, long upper) {
        return ContiguousSet.create(Range.closed(lower, upper), DiscreteDomain.longs());
    }

    @Beta
    public static ContiguousSet<Integer> closedOpen(int lower, int upper) {
        return ContiguousSet.create(Range.closedOpen(lower, upper), DiscreteDomain.integers());
    }

    @Beta
    public static ContiguousSet<Long> closedOpen(long lower, long upper) {
        return ContiguousSet.create(Range.closedOpen(lower, upper), DiscreteDomain.longs());
    }

    ContiguousSet(DiscreteDomain<C> domain) {
        super(Ordering.natural());
        this.domain = domain;
    }

    @Override
    public ContiguousSet<C> headSet(C toElement) {
        return this.headSetImpl((C)((Comparable)Preconditions.checkNotNull(toElement)), false);
    }

    @Override
    @GwtIncompatible
    public ContiguousSet<C> headSet(C toElement, boolean inclusive) {
        return this.headSetImpl((C)((Comparable)Preconditions.checkNotNull(toElement)), inclusive);
    }

    @Override
    public ContiguousSet<C> subSet(C fromElement, C toElement) {
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        Preconditions.checkArgument(this.comparator().compare(fromElement, toElement) <= 0);
        return this.subSetImpl(fromElement, true, toElement, false);
    }

    @Override
    @GwtIncompatible
    public ContiguousSet<C> subSet(C fromElement, boolean fromInclusive, C toElement, boolean toInclusive) {
        Preconditions.checkNotNull(fromElement);
        Preconditions.checkNotNull(toElement);
        Preconditions.checkArgument(this.comparator().compare(fromElement, toElement) <= 0);
        return this.subSetImpl(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public ContiguousSet<C> tailSet(C fromElement) {
        return this.tailSetImpl((C)((Comparable)Preconditions.checkNotNull(fromElement)), true);
    }

    @Override
    @GwtIncompatible
    public ContiguousSet<C> tailSet(C fromElement, boolean inclusive) {
        return this.tailSetImpl((C)((Comparable)Preconditions.checkNotNull(fromElement)), inclusive);
    }

    @Override
    abstract ContiguousSet<C> headSetImpl(C var1, boolean var2);

    @Override
    abstract ContiguousSet<C> subSetImpl(C var1, boolean var2, C var3, boolean var4);

    @Override
    abstract ContiguousSet<C> tailSetImpl(C var1, boolean var2);

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> var1);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType var1, BoundType var2);

    @Override
    @GwtIncompatible
    ImmutableSortedSet<C> createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }

    @Override
    public String toString() {
        return this.range().toString();
    }

    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }
}

