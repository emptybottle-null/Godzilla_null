/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.Cut;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RegularImmutableSortedSet;
import com.google.common.collect.SortedLists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public class ImmutableRangeMap<K extends Comparable<?>, V>
implements RangeMap<K, V>,
Serializable {
    private static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap(ImmutableList.of(), ImmutableList.of());
    private final transient ImmutableList<Range<K>> ranges;
    private final transient ImmutableList<V> values;
    private static final long serialVersionUID = 0L;

    public static <T, K extends Comparable<? super K>, V> Collector<T, ?, ImmutableRangeMap<K, V>> toImmutableRangeMap(Function<? super T, Range<K>> keyFunction, Function<? super T, ? extends V> valueFunction) {
        return CollectCollectors.toImmutableRangeMap(keyFunction, valueFunction);
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of() {
        return EMPTY;
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of(Range<K> range, V value) {
        return new ImmutableRangeMap<K, V>(ImmutableList.of(range), ImmutableList.of(value));
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
        if (rangeMap instanceof ImmutableRangeMap) {
            return (ImmutableRangeMap)rangeMap;
        }
        Map<Range<K>, V> map = rangeMap.asMapOfRanges();
        ImmutableList.Builder rangesBuilder = new ImmutableList.Builder(map.size());
        ImmutableList.Builder valuesBuilder = new ImmutableList.Builder(map.size());
        for (Map.Entry<Range<K>, V> entry : map.entrySet()) {
            rangesBuilder.add(entry.getKey());
            valuesBuilder.add(entry.getValue());
        }
        return new ImmutableRangeMap<K, V>(rangesBuilder.build(), valuesBuilder.build());
    }

    public static <K extends Comparable<?>, V> Builder<K, V> builder() {
        return new Builder();
    }

    ImmutableRangeMap(ImmutableList<Range<K>> ranges, ImmutableList<V> values) {
        this.ranges = ranges;
        this.values = values;
    }

    @Override
    public @Nullable V get(K key) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (index == -1) {
            return null;
        }
        Range range = (Range)this.ranges.get(index);
        return range.contains(key) ? (V)this.values.get(index) : null;
    }

    @Override
    public @Nullable Map.Entry<Range<K>, V> getEntry(K key) {
        int index = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(key), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (index == -1) {
            return null;
        }
        Range range = (Range)this.ranges.get(index);
        return range.contains(key) ? Maps.immutableEntry(range, this.values.get(index)) : null;
    }

    @Override
    public Range<K> span() {
        if (this.ranges.isEmpty()) {
            throw new NoSuchElementException();
        }
        Range firstRange = (Range)this.ranges.get(0);
        Range lastRange = (Range)this.ranges.get(this.ranges.size() - 1);
        return Range.create(firstRange.lowerBound, lastRange.upperBound);
    }

    @Override
    @Deprecated
    public void put(Range<K> range, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void putCoalescing(Range<K> range, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void putAll(RangeMap<K, V> rangeMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void remove(Range<K> range) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableMap<Range<K>, V> asMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.of();
        }
        RegularImmutableSortedSet rangeSet = new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering());
        return new ImmutableSortedMap(rangeSet, this.values);
    }

    @Override
    public ImmutableMap<Range<K>, V> asDescendingMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.of();
        }
        RegularImmutableSortedSet<Range<K>> rangeSet = new RegularImmutableSortedSet<Range<K>>(this.ranges.reverse(), Range.rangeLexOrdering().reverse());
        return new ImmutableSortedMap<Range<K>, V>(rangeSet, this.values.reverse());
    }

    @Override
    public ImmutableRangeMap<K, V> subRangeMap(final Range<K> range) {
        int upperIndex;
        if (Preconditions.checkNotNull(range).isEmpty()) {
            return ImmutableRangeMap.of();
        }
        if (this.ranges.isEmpty() || range.encloses(this.span())) {
            return this;
        }
        int lowerIndex = SortedLists.binarySearch(this.ranges, Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (lowerIndex >= (upperIndex = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER))) {
            return ImmutableRangeMap.of();
        }
        final int off = lowerIndex;
        final int len = upperIndex - lowerIndex;
        ImmutableList subRanges = new ImmutableList<Range<K>>(){

            @Override
            public int size() {
                return len;
            }

            @Override
            public Range<K> get(int index) {
                Preconditions.checkElementIndex(index, len);
                if (index == 0 || index == len - 1) {
                    return ((Range)ImmutableRangeMap.this.ranges.get(index + off)).intersection(range);
                }
                return (Range)ImmutableRangeMap.this.ranges.get(index + off);
            }

            @Override
            boolean isPartialView() {
                return true;
            }
        };
        final ImmutableRangeMap outer = this;
        return new ImmutableRangeMap<K, V>(subRanges, (ImmutableList)this.values.subList(lowerIndex, upperIndex)){

            @Override
            public ImmutableRangeMap<K, V> subRangeMap(Range<K> subRange) {
                if (range.isConnected(subRange)) {
                    return outer.subRangeMap(subRange.intersection(range));
                }
                return ImmutableRangeMap.of();
            }
        };
    }

    @Override
    public int hashCode() {
        return ((ImmutableMap)this.asMapOfRanges()).hashCode();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (o instanceof RangeMap) {
            RangeMap rangeMap = (RangeMap)o;
            return ((ImmutableMap)this.asMapOfRanges()).equals(rangeMap.asMapOfRanges());
        }
        return false;
    }

    @Override
    public String toString() {
        return ((ImmutableMap)this.asMapOfRanges()).toString();
    }

    Object writeReplace() {
        return new SerializedForm(this.asMapOfRanges());
    }

    private static class SerializedForm<K extends Comparable<?>, V>
    implements Serializable {
        private final ImmutableMap<Range<K>, V> mapOfRanges;
        private static final long serialVersionUID = 0L;

        SerializedForm(ImmutableMap<Range<K>, V> mapOfRanges) {
            this.mapOfRanges = mapOfRanges;
        }

        Object readResolve() {
            if (this.mapOfRanges.isEmpty()) {
                return ImmutableRangeMap.of();
            }
            return this.createRangeMap();
        }

        Object createRangeMap() {
            Builder builder = new Builder();
            for (Map.Entry entry : this.mapOfRanges.entrySet()) {
                builder.put((Range)entry.getKey(), entry.getValue());
            }
            return builder.build();
        }
    }

    public static final class Builder<K extends Comparable<?>, V> {
        private final List<Map.Entry<Range<K>, V>> entries = Lists.newArrayList();

        @CanIgnoreReturnValue
        public Builder<K, V> put(Range<K> range, V value) {
            Preconditions.checkNotNull(range);
            Preconditions.checkNotNull(value);
            Preconditions.checkArgument(!range.isEmpty(), "Range must not be empty, but was %s", range);
            this.entries.add(Maps.immutableEntry(range, value));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap) {
            for (Map.Entry<Range<K>, V> entry : rangeMap.asMapOfRanges().entrySet()) {
                this.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        Builder<K, V> combine(Builder<K, V> builder) {
            this.entries.addAll(builder.entries);
            return this;
        }

        public ImmutableRangeMap<K, V> build() {
            Collections.sort(this.entries, Range.rangeLexOrdering().onKeys());
            ImmutableList.Builder rangesBuilder = new ImmutableList.Builder(this.entries.size());
            ImmutableList.Builder valuesBuilder = new ImmutableList.Builder(this.entries.size());
            for (int i = 0; i < this.entries.size(); ++i) {
                Range<K> prevRange;
                Range<K> range = this.entries.get(i).getKey();
                if (i > 0 && range.isConnected(prevRange = this.entries.get(i - 1).getKey()) && !range.intersection(prevRange).isEmpty()) {
                    throw new IllegalArgumentException("Overlapping ranges: range " + prevRange + " overlaps with entry " + range);
                }
                rangesBuilder.add(range);
                valuesBuilder.add(this.entries.get(i).getValue());
            }
            return new ImmutableRangeMap(rangesBuilder.build(), valuesBuilder.build());
        }
    }
}

