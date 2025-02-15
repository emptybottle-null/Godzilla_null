/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.common.collect.TransformedIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractTable<R, C, V>
implements Table<R, C, V> {
    private transient @MonotonicNonNull Set<Table.Cell<R, C, V>> cellSet;
    private transient @MonotonicNonNull Collection<V> values;

    AbstractTable() {
    }

    @Override
    public boolean containsRow(@Nullable Object rowKey) {
        return Maps.safeContainsKey(this.rowMap(), rowKey);
    }

    @Override
    public boolean containsColumn(@Nullable Object columnKey) {
        return Maps.safeContainsKey(this.columnMap(), columnKey);
    }

    @Override
    public Set<R> rowKeySet() {
        return this.rowMap().keySet();
    }

    @Override
    public Set<C> columnKeySet() {
        return this.columnMap().keySet();
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        for (Map row : this.rowMap().values()) {
            if (!row.containsValue(value)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        Map row = Maps.safeGet(this.rowMap(), rowKey);
        return row != null && Maps.safeContainsKey(row, columnKey);
    }

    @Override
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        Map row = Maps.safeGet(this.rowMap(), rowKey);
        return row == null ? null : (V)Maps.safeGet(row, columnKey);
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void clear() {
        Iterators.clear(this.cellSet().iterator());
    }

    @Override
    @CanIgnoreReturnValue
    public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
        Map row = Maps.safeGet(this.rowMap(), rowKey);
        return row == null ? null : (V)Maps.safeRemove(row, columnKey);
    }

    @Override
    @CanIgnoreReturnValue
    public V put(R rowKey, C columnKey, V value) {
        return this.row(rowKey).put(columnKey, value);
    }

    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        for (Table.Cell<R, C, V> cell : table.cellSet()) {
            this.put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
        }
    }

    @Override
    public Set<Table.Cell<R, C, V>> cellSet() {
        Set<Table.Cell<R, C, V>> result = this.cellSet;
        return result == null ? (this.cellSet = this.createCellSet()) : result;
    }

    Set<Table.Cell<R, C, V>> createCellSet() {
        return new CellSet();
    }

    abstract Iterator<Table.Cell<R, C, V>> cellIterator();

    abstract Spliterator<Table.Cell<R, C, V>> cellSpliterator();

    @Override
    public Collection<V> values() {
        Collection<V> result = this.values;
        return result == null ? (this.values = this.createValues()) : result;
    }

    Collection<V> createValues() {
        return new Values();
    }

    Iterator<V> valuesIterator() {
        return new TransformedIterator<Table.Cell<R, C, V>, V>(this.cellSet().iterator()){

            @Override
            V transform(Table.Cell<R, C, V> cell) {
                return cell.getValue();
            }
        };
    }

    Spliterator<V> valuesSpliterator() {
        return CollectSpliterators.map(this.cellSpliterator(), Table.Cell::getValue);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return Tables.equalsImpl(this, obj);
    }

    @Override
    public int hashCode() {
        return this.cellSet().hashCode();
    }

    public String toString() {
        return this.rowMap().toString();
    }

    class Values
    extends AbstractCollection<V> {
        Values() {
        }

        @Override
        public Iterator<V> iterator() {
            return AbstractTable.this.valuesIterator();
        }

        @Override
        public Spliterator<V> spliterator() {
            return AbstractTable.this.valuesSpliterator();
        }

        @Override
        public boolean contains(Object o) {
            return AbstractTable.this.containsValue(o);
        }

        @Override
        public void clear() {
            AbstractTable.this.clear();
        }

        @Override
        public int size() {
            return AbstractTable.this.size();
        }
    }

    class CellSet
    extends AbstractSet<Table.Cell<R, C, V>> {
        CellSet() {
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell)o;
                Map row = Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
                return row != null && Collections2.safeContains(row.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
            }
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            if (o instanceof Table.Cell) {
                Table.Cell cell = (Table.Cell)o;
                Map row = Maps.safeGet(AbstractTable.this.rowMap(), cell.getRowKey());
                return row != null && Collections2.safeRemove(row.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
            }
            return false;
        }

        @Override
        public void clear() {
            AbstractTable.this.clear();
        }

        @Override
        public Iterator<Table.Cell<R, C, V>> iterator() {
            return AbstractTable.this.cellIterator();
        }

        @Override
        public Spliterator<Table.Cell<R, C, V>> spliterator() {
            return AbstractTable.this.cellSpliterator();
        }

        @Override
        public int size() {
            return AbstractTable.this.size();
        }
    }
}

