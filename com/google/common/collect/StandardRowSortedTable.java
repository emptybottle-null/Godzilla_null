/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.RowSortedTable;
import com.google.common.collect.StandardTable;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

@GwtCompatible
class StandardRowSortedTable<R, C, V>
extends StandardTable<R, C, V>
implements RowSortedTable<R, C, V> {
    private static final long serialVersionUID = 0L;

    StandardRowSortedTable(SortedMap<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
        super(backingMap, factory);
    }

    private SortedMap<R, Map<C, V>> sortedBackingMap() {
        return (SortedMap)this.backingMap;
    }

    @Override
    public SortedSet<R> rowKeySet() {
        return (SortedSet)this.rowMap().keySet();
    }

    @Override
    public SortedMap<R, Map<C, V>> rowMap() {
        return (SortedMap)super.rowMap();
    }

    @Override
    SortedMap<R, Map<C, V>> createRowMap() {
        return new RowSortedMap();
    }

    private class RowSortedMap
    extends StandardTable.RowMap
    implements SortedMap<R, Map<C, V>> {
        private RowSortedMap() {
            super(StandardRowSortedTable.this);
        }

        @Override
        public SortedSet<R> keySet() {
            return (SortedSet)super.keySet();
        }

        @Override
        SortedSet<R> createKeySet() {
            return new Maps.SortedKeySet(this);
        }

        @Override
        public Comparator<? super R> comparator() {
            return StandardRowSortedTable.this.sortedBackingMap().comparator();
        }

        @Override
        public R firstKey() {
            return StandardRowSortedTable.this.sortedBackingMap().firstKey();
        }

        @Override
        public R lastKey() {
            return StandardRowSortedTable.this.sortedBackingMap().lastKey();
        }

        @Override
        public SortedMap<R, Map<C, V>> headMap(R toKey) {
            Preconditions.checkNotNull(toKey);
            return new StandardRowSortedTable(StandardRowSortedTable.this.sortedBackingMap().headMap(toKey), StandardRowSortedTable.this.factory).rowMap();
        }

        @Override
        public SortedMap<R, Map<C, V>> subMap(R fromKey, R toKey) {
            Preconditions.checkNotNull(fromKey);
            Preconditions.checkNotNull(toKey);
            return new StandardRowSortedTable(StandardRowSortedTable.this.sortedBackingMap().subMap(fromKey, toKey), StandardRowSortedTable.this.factory).rowMap();
        }

        @Override
        public SortedMap<R, Map<C, V>> tailMap(R fromKey) {
            Preconditions.checkNotNull(fromKey);
            return new StandardRowSortedTable(StandardRowSortedTable.this.sortedBackingMap().tailMap(fromKey), StandardRowSortedTable.this.factory).rowMap();
        }
    }
}

