/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.BoundType;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Cut;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.google.common.collect.RangeGwtSerializationDependencies;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Range<C extends Comparable>
extends RangeGwtSerializationDependencies
implements Predicate<C>,
Serializable {
    private static final Range<Comparable> ALL = new Range(Cut.belowAll(), Cut.aboveAll());
    final Cut<C> lowerBound;
    final Cut<C> upperBound;
    private static final long serialVersionUID = 0L;

    static <C extends Comparable<?>> Function<Range<C>, Cut<C>> lowerBoundFn() {
        return LowerBoundFn.INSTANCE;
    }

    static <C extends Comparable<?>> Function<Range<C>, Cut<C>> upperBoundFn() {
        return UpperBoundFn.INSTANCE;
    }

    static <C extends Comparable<?>> Ordering<Range<C>> rangeLexOrdering() {
        return RangeLexOrdering.INSTANCE;
    }

    static <C extends Comparable<?>> Range<C> create(Cut<C> lowerBound, Cut<C> upperBound) {
        return new Range<C>(lowerBound, upperBound);
    }

    public static <C extends Comparable<?>> Range<C> open(C lower, C upper) {
        return Range.create(Cut.aboveValue(lower), Cut.belowValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> closed(C lower, C upper) {
        return Range.create(Cut.belowValue(lower), Cut.aboveValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C lower, C upper) {
        return Range.create(Cut.belowValue(lower), Cut.belowValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C lower, C upper) {
        return Range.create(Cut.aboveValue(lower), Cut.aboveValue(upper));
    }

    public static <C extends Comparable<?>> Range<C> range(C lower, BoundType lowerType, C upper, BoundType upperType) {
        Preconditions.checkNotNull(lowerType);
        Preconditions.checkNotNull(upperType);
        Cut<C> lowerBound = lowerType == BoundType.OPEN ? Cut.aboveValue(lower) : Cut.belowValue(lower);
        Cut<C> upperBound = upperType == BoundType.OPEN ? Cut.belowValue(upper) : Cut.aboveValue(upper);
        return Range.create(lowerBound, upperBound);
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C endpoint) {
        return Range.create(Cut.belowAll(), Cut.belowValue(endpoint));
    }

    public static <C extends Comparable<?>> Range<C> atMost(C endpoint) {
        return Range.create(Cut.belowAll(), Cut.aboveValue(endpoint));
    }

    public static <C extends Comparable<?>> Range<C> upTo(C endpoint, BoundType boundType) {
        switch (boundType) {
            case OPEN: {
                return Range.lessThan(endpoint);
            }
            case CLOSED: {
                return Range.atMost(endpoint);
            }
        }
        throw new AssertionError();
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C endpoint) {
        return Range.create(Cut.aboveValue(endpoint), Cut.aboveAll());
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C endpoint) {
        return Range.create(Cut.belowValue(endpoint), Cut.aboveAll());
    }

    public static <C extends Comparable<?>> Range<C> downTo(C endpoint, BoundType boundType) {
        switch (boundType) {
            case OPEN: {
                return Range.greaterThan(endpoint);
            }
            case CLOSED: {
                return Range.atLeast(endpoint);
            }
        }
        throw new AssertionError();
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return ALL;
    }

    public static <C extends Comparable<?>> Range<C> singleton(C value) {
        return Range.closed(value, value);
    }

    public static <C extends Comparable<?>> Range<C> encloseAll(Iterable<C> values) {
        Comparable min;
        Preconditions.checkNotNull(values);
        if (values instanceof SortedSet) {
            SortedSet<C> set = Range.cast(values);
            Comparator<C> comparator = set.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return Range.closed((Comparable)set.first(), (Comparable)set.last());
            }
        }
        Iterator<C> valueIterator = values.iterator();
        Comparable max = min = (Comparable)Preconditions.checkNotNull(valueIterator.next());
        while (valueIterator.hasNext()) {
            Comparable value = (Comparable)Preconditions.checkNotNull(valueIterator.next());
            min = Ordering.natural().min(min, value);
            max = Ordering.natural().max(max, value);
        }
        return Range.closed(min, max);
    }

    private Range(Cut<C> lowerBound, Cut<C> upperBound) {
        this.lowerBound = Preconditions.checkNotNull(lowerBound);
        this.upperBound = Preconditions.checkNotNull(upperBound);
        if (lowerBound.compareTo(upperBound) > 0 || lowerBound == Cut.aboveAll() || upperBound == Cut.belowAll()) {
            throw new IllegalArgumentException("Invalid range: " + Range.toString(lowerBound, upperBound));
        }
    }

    public boolean hasLowerBound() {
        return this.lowerBound != Cut.belowAll();
    }

    public C lowerEndpoint() {
        return this.lowerBound.endpoint();
    }

    public BoundType lowerBoundType() {
        return this.lowerBound.typeAsLowerBound();
    }

    public boolean hasUpperBound() {
        return this.upperBound != Cut.aboveAll();
    }

    public C upperEndpoint() {
        return this.upperBound.endpoint();
    }

    public BoundType upperBoundType() {
        return this.upperBound.typeAsUpperBound();
    }

    public boolean isEmpty() {
        return this.lowerBound.equals(this.upperBound);
    }

    public boolean contains(C value) {
        Preconditions.checkNotNull(value);
        return this.lowerBound.isLessThan(value) && !this.upperBound.isLessThan(value);
    }

    @Override
    @Deprecated
    public boolean apply(C input) {
        return this.contains(input);
    }

    public boolean containsAll(Iterable<? extends C> values) {
        if (Iterables.isEmpty(values)) {
            return true;
        }
        if (values instanceof SortedSet) {
            SortedSet<C> set = Range.cast(values);
            Comparator<? extends C> comparator = set.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return this.contains((Comparable)set.first()) && this.contains((Comparable)set.last());
            }
        }
        for (Comparable value : values) {
            if (this.contains(value)) continue;
            return false;
        }
        return true;
    }

    public boolean encloses(Range<C> other) {
        return this.lowerBound.compareTo(other.lowerBound) <= 0 && this.upperBound.compareTo(other.upperBound) >= 0;
    }

    public boolean isConnected(Range<C> other) {
        return this.lowerBound.compareTo(other.upperBound) <= 0 && other.lowerBound.compareTo(this.upperBound) <= 0;
    }

    public Range<C> intersection(Range<C> connectedRange) {
        int lowerCmp = this.lowerBound.compareTo(connectedRange.lowerBound);
        int upperCmp = this.upperBound.compareTo(connectedRange.upperBound);
        if (lowerCmp >= 0 && upperCmp <= 0) {
            return this;
        }
        if (lowerCmp <= 0 && upperCmp >= 0) {
            return connectedRange;
        }
        Cut<C> newLower = lowerCmp >= 0 ? this.lowerBound : connectedRange.lowerBound;
        Cut<C> newUpper = upperCmp <= 0 ? this.upperBound : connectedRange.upperBound;
        return Range.create(newLower, newUpper);
    }

    public Range<C> gap(Range<C> otherRange) {
        boolean isThisFirst = this.lowerBound.compareTo(otherRange.lowerBound) < 0;
        Range<C> firstRange = isThisFirst ? this : otherRange;
        Range<C> secondRange = isThisFirst ? otherRange : this;
        return Range.create(firstRange.upperBound, secondRange.lowerBound);
    }

    public Range<C> span(Range<C> other) {
        int lowerCmp = this.lowerBound.compareTo(other.lowerBound);
        int upperCmp = this.upperBound.compareTo(other.upperBound);
        if (lowerCmp <= 0 && upperCmp >= 0) {
            return this;
        }
        if (lowerCmp >= 0 && upperCmp <= 0) {
            return other;
        }
        Cut<C> newLower = lowerCmp <= 0 ? this.lowerBound : other.lowerBound;
        Cut<C> newUpper = upperCmp >= 0 ? this.upperBound : other.upperBound;
        return Range.create(newLower, newUpper);
    }

    public Range<C> canonical(DiscreteDomain<C> domain) {
        Preconditions.checkNotNull(domain);
        Cut<C> lower = this.lowerBound.canonical(domain);
        Cut<C> upper = this.upperBound.canonical(domain);
        return lower == this.lowerBound && upper == this.upperBound ? this : Range.create(lower, upper);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (object instanceof Range) {
            Range other = (Range)object;
            return this.lowerBound.equals(other.lowerBound) && this.upperBound.equals(other.upperBound);
        }
        return false;
    }

    public int hashCode() {
        return this.lowerBound.hashCode() * 31 + this.upperBound.hashCode();
    }

    public String toString() {
        return Range.toString(this.lowerBound, this.upperBound);
    }

    private static String toString(Cut<?> lowerBound, Cut<?> upperBound) {
        StringBuilder sb = new StringBuilder(16);
        lowerBound.describeAsLowerBound(sb);
        sb.append("..");
        upperBound.describeAsUpperBound(sb);
        return sb.toString();
    }

    private static <T> SortedSet<T> cast(Iterable<T> iterable) {
        return (SortedSet)iterable;
    }

    Object readResolve() {
        if (this.equals(ALL)) {
            return Range.all();
        }
        return this;
    }

    static int compareOrThrow(Comparable left, Comparable right) {
        return left.compareTo(right);
    }

    private static class RangeLexOrdering
    extends Ordering<Range<?>>
    implements Serializable {
        static final Ordering<Range<?>> INSTANCE = new RangeLexOrdering();
        private static final long serialVersionUID = 0L;

        private RangeLexOrdering() {
        }

        @Override
        public int compare(Range<?> left, Range<?> right) {
            return ComparisonChain.start().compare(left.lowerBound, right.lowerBound).compare(left.upperBound, right.upperBound).result();
        }
    }

    static class UpperBoundFn
    implements Function<Range, Cut> {
        static final UpperBoundFn INSTANCE = new UpperBoundFn();

        UpperBoundFn() {
        }

        @Override
        public Cut apply(Range range) {
            return range.upperBound;
        }
    }

    static class LowerBoundFn
    implements Function<Range, Cut> {
        static final LowerBoundFn INSTANCE = new LowerBoundFn();

        LowerBoundFn() {
        }

        @Override
        public Cut apply(Range range) {
            return range.lowerBound;
        }
    }
}

