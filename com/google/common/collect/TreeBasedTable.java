/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.StandardRowSortedTable;
import com.google.common.collect.StandardTable;
import com.google.common.collect.Table;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
public class TreeBasedTable<R, C, V>
extends StandardRowSortedTable<R, C, V> {
    private final Comparator<? super C> columnComparator;
    private static final long serialVersionUID = 0L;

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        return new TreeBasedTable(Ordering.natural(), Ordering.natural());
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> rowComparator, Comparator<? super C> columnComparator) {
        Preconditions.checkNotNull(rowComparator);
        Preconditions.checkNotNull(columnComparator);
        return new TreeBasedTable<R, C, V>(rowComparator, columnComparator);
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> table) {
        TreeBasedTable<R, C, V> result = new TreeBasedTable<R, C, V>(table.rowComparator(), table.columnComparator());
        result.putAll((Table)table);
        return result;
    }

    TreeBasedTable(Comparator<? super R> rowComparator, Comparator<? super C> columnComparator) {
        super(new TreeMap(rowComparator), new Factory(columnComparator));
        this.columnComparator = columnComparator;
    }

    @Deprecated
    public Comparator<? super R> rowComparator() {
        return this.rowKeySet().comparator();
    }

    @Deprecated
    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    @Override
    public SortedMap<C, V> row(R rowKey) {
        return new TreeRow(rowKey);
    }

    @Override
    public SortedSet<R> rowKeySet() {
        return super.rowKeySet();
    }

    @Override
    public SortedMap<R, Map<C, V>> rowMap() {
        return super.rowMap();
    }

    @Override
    Iterator<C> createColumnKeyIterator() {
        final Comparator<C> comparator = this.columnComparator();
        final UnmodifiableIterator<C> merged = Iterators.mergeSorted(Iterables.transform(this.backingMap.values(), new Function<Map<C, V>, Iterator<C>>(){

            @Override
            public Iterator<C> apply(Map<C, V> input) {
                return input.keySet().iterator();
            }
        }), comparator);
        return new AbstractIterator<C>(){
            @Nullable C lastValue;

            @Override
            protected C computeNext() {
                while (merged.hasNext()) {
                    Object next = merged.next();
                    boolean duplicate = this.lastValue != null && comparator.compare(next, this.lastValue) == 0;
                    if (duplicate) continue;
                    this.lastValue = next;
                    return this.lastValue;
                }
                this.lastValue = null;
                return this.endOfData();
            }
        };
    }

    private class TreeRow
    extends StandardTable.Row
    implements SortedMap<C, V> {
        final @Nullable C lowerBound;
        final @Nullable C upperBound;
        transient @Nullable SortedMap<C, V> wholeRow;

        TreeRow(R rowKey) {
            this(rowKey, null, null);
        }

        TreeRow(@Nullable R rowKey, @Nullable C lowerBound, C upperBound) {
            super(rowKey);
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            Preconditions.checkArgument(lowerBound == null || upperBound == null || this.compare(lowerBound, upperBound) <= 0);
        }

        @Override
        public SortedSet<C> keySet() {
            return new Maps.SortedKeySet(this);
        }

        @Override
        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        int compare(Object a, Object b) {
            Comparator cmp = this.comparator();
            return cmp.compare(a, b);
        }

        boolean rangeContains(@Nullable Object o) {
            return !(o == null || this.lowerBound != null && this.compare(this.lowerBound, o) > 0 || this.upperBound != null && this.compare(this.upperBound, o) <= 0);
        }

        @Override
        public SortedMap<C, V> subMap(C fromKey, C toKey) {
            Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(fromKey)) && this.rangeContains(Preconditions.checkNotNull(toKey)));
            return new TreeRow(this.rowKey, fromKey, toKey);
        }

        @Override
        public SortedMap<C, V> headMap(C toKey) {
            Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(toKey)));
            return new TreeRow(this.rowKey, this.lowerBound, toKey);
        }

        @Override
        public SortedMap<C, V> tailMap(C fromKey) {
            Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(fromKey)));
            return new TreeRow(this.rowKey, fromKey, this.upperBound);
        }

        @Override
        public C firstKey() {
            Map backing = this.backingRowMap();
            if (backing == null) {
                throw new NoSuchElementException();
            }
            return this.backingRowMap().firstKey();
        }

        @Override
        public C lastKey() {
            Map backing = this.backingRowMap();
            if (backing == null) {
                throw new NoSuchElementException();
            }
            return this.backingRowMap().lastKey();
        }

        SortedMap<C, V> wholeRow() {
            if (this.wholeRow == null || this.wholeRow.isEmpty() && TreeBasedTable.this.backingMap.containsKey(this.rowKey)) {
                this.wholeRow = (SortedMap)TreeBasedTable.this.backingMap.get(this.rowKey);
            }
            return this.wholeRow;
        }

        SortedMap<C, V> backingRowMap() {
            return (SortedMap)super.backingRowMap();
        }

        SortedMap<C, V> computeBackingRowMap() {
            SortedMap map = this.wholeRow();
            if (map != null) {
                if (this.lowerBound != null) {
                    map = map.tailMap(this.lowerBound);
                }
                if (this.upperBound != null) {
                    map = map.headMap(this.upperBound);
                }
                return map;
            }
            return null;
        }

        @Override
        void maintainEmptyInvariant() {
            if (this.wholeRow() != null && this.wholeRow.isEmpty()) {
                TreeBasedTable.this.backingMap.remove(this.rowKey);
                this.wholeRow = null;
                this.backingRowMap = null;
            }
        }

        @Override
        public boolean containsKey(Object key) {
            return this.rangeContains(key) && super.containsKey(key);
        }

        @Override
        public V put(C key, V value) {
            Preconditions.checkArgument(this.rangeContains(Preconditions.checkNotNull(key)));
            return super.put(key, value);
        }
    }

    private static class Factory<C, V>
    implements Supplier<TreeMap<C, V>>,
    Serializable {
        final Comparator<? super C> comparator;
        private static final long serialVersionUID = 0L;

        Factory(Comparator<? super C> comparator) {
            this.comparator = comparator;
        }

        @Override
        public TreeMap<C, V> get() {
            return new TreeMap(this.comparator);
        }
    }
}

