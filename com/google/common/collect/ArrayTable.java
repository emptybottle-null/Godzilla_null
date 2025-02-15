/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIndexedListIterator;
import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.AbstractTable;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtCompatible(emulated=true)
public final class ArrayTable<R, C, V>
extends AbstractTable<R, C, V>
implements Serializable {
    private final ImmutableList<R> rowList;
    private final ImmutableList<C> columnList;
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final V[][] array;
    private transient @MonotonicNonNull ColumnMap columnMap;
    private transient @MonotonicNonNull RowMap rowMap;
    private static final long serialVersionUID = 0L;

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> rowKeys, Iterable<? extends C> columnKeys) {
        return new ArrayTable<R, C, V>(rowKeys, columnKeys);
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, V> table) {
        return table instanceof ArrayTable ? new ArrayTable<R, C, V>((ArrayTable)table) : new ArrayTable<R, C, V>(table);
    }

    private ArrayTable(Iterable<? extends R> rowKeys, Iterable<? extends C> columnKeys) {
        this.rowList = ImmutableList.copyOf(rowKeys);
        this.columnList = ImmutableList.copyOf(columnKeys);
        Preconditions.checkArgument(this.rowList.isEmpty() == this.columnList.isEmpty());
        this.rowKeyToIndex = Maps.indexMap(this.rowList);
        this.columnKeyToIndex = Maps.indexMap(this.columnList);
        Object[][] tmpArray = new Object[this.rowList.size()][this.columnList.size()];
        this.array = tmpArray;
        this.eraseAll();
    }

    private ArrayTable(Table<R, C, V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        this.putAll(table);
    }

    private ArrayTable(ArrayTable<R, C, V> table) {
        this.rowList = table.rowList;
        this.columnList = table.columnList;
        this.rowKeyToIndex = table.rowKeyToIndex;
        this.columnKeyToIndex = table.columnKeyToIndex;
        Object[][] copy = new Object[this.rowList.size()][this.columnList.size()];
        this.array = copy;
        for (int i = 0; i < this.rowList.size(); ++i) {
            System.arraycopy(table.array[i], 0, copy[i], 0, table.array[i].length);
        }
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    public V at(int rowIndex, int columnIndex) {
        Preconditions.checkElementIndex(rowIndex, this.rowList.size());
        Preconditions.checkElementIndex(columnIndex, this.columnList.size());
        return this.array[rowIndex][columnIndex];
    }

    @CanIgnoreReturnValue
    public V set(int rowIndex, int columnIndex, @Nullable V value) {
        Preconditions.checkElementIndex(rowIndex, this.rowList.size());
        Preconditions.checkElementIndex(columnIndex, this.columnList.size());
        V oldValue = this.array[rowIndex][columnIndex];
        this.array[rowIndex][columnIndex] = value;
        return oldValue;
    }

    @GwtIncompatible
    public V[][] toArray(Class<V> valueClass) {
        Object[][] copy = (Object[][])Array.newInstance(valueClass, this.rowList.size(), this.columnList.size());
        for (int i = 0; i < this.rowList.size(); ++i) {
            System.arraycopy(this.array[i], 0, copy[i], 0, this.array[i].length);
        }
        return copy;
    }

    @Override
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void eraseAll() {
        for (Object[] objectArray : this.array) {
            Arrays.fill(objectArray, null);
        }
    }

    @Override
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return this.containsRow(rowKey) && this.containsColumn(columnKey);
    }

    @Override
    public boolean containsColumn(@Nullable Object columnKey) {
        return this.columnKeyToIndex.containsKey(columnKey);
    }

    @Override
    public boolean containsRow(@Nullable Object rowKey) {
        return this.rowKeyToIndex.containsKey(rowKey);
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        V[][] VArray = this.array;
        int n = VArray.length;
        for (int i = 0; i < n; ++i) {
            V[] row;
            for (V element : row = VArray[i]) {
                if (!Objects.equal(value, element)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        return rowIndex == null || columnIndex == null ? null : (V)this.at(rowIndex, columnIndex);
    }

    @Override
    public boolean isEmpty() {
        return this.rowList.isEmpty() || this.columnList.isEmpty();
    }

    @Override
    @CanIgnoreReturnValue
    public V put(R rowKey, C columnKey, @Nullable V value) {
        Preconditions.checkNotNull(rowKey);
        Preconditions.checkNotNull(columnKey);
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Preconditions.checkArgument(rowIndex != null, "Row %s not in %s", rowKey, this.rowList);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        Preconditions.checkArgument(columnIndex != null, "Column %s not in %s", columnKey, this.columnList);
        return this.set(rowIndex, columnIndex, value);
    }

    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public V remove(Object rowKey, Object columnKey) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public V erase(@Nullable Object rowKey, @Nullable Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        if (rowIndex == null || columnIndex == null) {
            return null;
        }
        return this.set(rowIndex, columnIndex, null);
    }

    @Override
    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    @Override
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Override
    Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(this.size()){

            @Override
            protected Table.Cell<R, C, V> get(int index) {
                return ArrayTable.this.getCell(index);
            }
        };
    }

    @Override
    Spliterator<Table.Cell<R, C, V>> cellSpliterator() {
        return CollectSpliterators.indexed(this.size(), 273, this::getCell);
    }

    private Table.Cell<R, C, V> getCell(final int index) {
        return new Tables.AbstractCell<R, C, V>(){
            final int rowIndex;
            final int columnIndex;
            {
                this.rowIndex = index / ArrayTable.this.columnList.size();
                this.columnIndex = index % ArrayTable.this.columnList.size();
            }

            @Override
            public R getRowKey() {
                return ArrayTable.this.rowList.get(this.rowIndex);
            }

            @Override
            public C getColumnKey() {
                return ArrayTable.this.columnList.get(this.columnIndex);
            }

            @Override
            public V getValue() {
                return ArrayTable.this.at(this.rowIndex, this.columnIndex);
            }
        };
    }

    private V getValue(int index) {
        int rowIndex = index / this.columnList.size();
        int columnIndex = index % this.columnList.size();
        return this.at(rowIndex, columnIndex);
    }

    @Override
    public Map<R, V> column(C columnKey) {
        Preconditions.checkNotNull(columnKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        return columnIndex == null ? ImmutableMap.of() : new Column(columnIndex);
    }

    @Override
    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    @Override
    public Map<C, Map<R, V>> columnMap() {
        ColumnMap map = this.columnMap;
        return map == null ? (this.columnMap = new ColumnMap()) : map;
    }

    @Override
    public Map<C, V> row(R rowKey) {
        Preconditions.checkNotNull(rowKey);
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        return rowIndex == null ? ImmutableMap.of() : new Row(rowIndex);
    }

    @Override
    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    @Override
    public Map<R, Map<C, V>> rowMap() {
        RowMap map = this.rowMap;
        return map == null ? (this.rowMap = new RowMap()) : map;
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    Iterator<V> valuesIterator() {
        return new AbstractIndexedListIterator<V>(this.size()){

            @Override
            protected V get(int index) {
                return ArrayTable.this.getValue(index);
            }
        };
    }

    @Override
    Spliterator<V> valuesSpliterator() {
        return CollectSpliterators.indexed(this.size(), 16, this::getValue);
    }

    private class RowMap
    extends ArrayMap<R, Map<C, V>> {
        private RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        @Override
        String getKeyRole() {
            return "Row";
        }

        @Override
        Map<C, V> getValue(int index) {
            return new Row(index);
        }

        @Override
        Map<C, V> setValue(int index, Map<C, V> newValue) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Map<C, V> put(R key, Map<C, V> value) {
            throw new UnsupportedOperationException();
        }
    }

    private class Row
    extends ArrayMap<C, V> {
        final int rowIndex;

        Row(int rowIndex) {
            super(ArrayTable.this.columnKeyToIndex);
            this.rowIndex = rowIndex;
        }

        @Override
        String getKeyRole() {
            return "Column";
        }

        @Override
        V getValue(int index) {
            return ArrayTable.this.at(this.rowIndex, index);
        }

        @Override
        V setValue(int index, V newValue) {
            return ArrayTable.this.set(this.rowIndex, index, newValue);
        }
    }

    private class ColumnMap
    extends ArrayMap<C, Map<R, V>> {
        private ColumnMap() {
            super(ArrayTable.this.columnKeyToIndex);
        }

        @Override
        String getKeyRole() {
            return "Column";
        }

        @Override
        Map<R, V> getValue(int index) {
            return new Column(index);
        }

        @Override
        Map<R, V> setValue(int index, Map<R, V> newValue) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Map<R, V> put(C key, Map<R, V> value) {
            throw new UnsupportedOperationException();
        }
    }

    private class Column
    extends ArrayMap<R, V> {
        final int columnIndex;

        Column(int columnIndex) {
            super(ArrayTable.this.rowKeyToIndex);
            this.columnIndex = columnIndex;
        }

        @Override
        String getKeyRole() {
            return "Row";
        }

        @Override
        V getValue(int index) {
            return ArrayTable.this.at(index, this.columnIndex);
        }

        @Override
        V setValue(int index, V newValue) {
            return ArrayTable.this.set(index, this.columnIndex, newValue);
        }
    }

    private static abstract class ArrayMap<K, V>
    extends Maps.IteratorBasedAbstractMap<K, V> {
        private final ImmutableMap<K, Integer> keyIndex;

        private ArrayMap(ImmutableMap<K, Integer> keyIndex) {
            this.keyIndex = keyIndex;
        }

        @Override
        public Set<K> keySet() {
            return this.keyIndex.keySet();
        }

        K getKey(int index) {
            return (K)((ImmutableSet)this.keyIndex.keySet()).asList().get(index);
        }

        abstract String getKeyRole();

        abstract @Nullable V getValue(int var1);

        abstract @Nullable V setValue(int var1, V var2);

        @Override
        public int size() {
            return this.keyIndex.size();
        }

        @Override
        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        Map.Entry<K, V> getEntry(final int index) {
            Preconditions.checkElementIndex(index, this.size());
            return new AbstractMapEntry<K, V>(){

                @Override
                public K getKey() {
                    return this.getKey(index);
                }

                @Override
                public V getValue() {
                    return this.getValue(index);
                }

                @Override
                public V setValue(V value) {
                    return this.setValue(index, value);
                }
            };
        }

        @Override
        Iterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIndexedListIterator<Map.Entry<K, V>>(this.size()){

                @Override
                protected Map.Entry<K, V> get(int index) {
                    return this.getEntry(index);
                }
            };
        }

        @Override
        Spliterator<Map.Entry<K, V>> entrySpliterator() {
            return CollectSpliterators.indexed(this.size(), 16, this::getEntry);
        }

        @Override
        public boolean containsKey(@Nullable Object key) {
            return this.keyIndex.containsKey(key);
        }

        @Override
        public V get(@Nullable Object key) {
            Integer index = this.keyIndex.get(key);
            if (index == null) {
                return null;
            }
            return this.getValue(index);
        }

        @Override
        public V put(K key, V value) {
            Integer index = this.keyIndex.get(key);
            if (index == null) {
                throw new IllegalArgumentException(this.getKeyRole() + " " + key + " not in " + this.keyIndex.keySet());
            }
            return this.setValue(index, value);
        }

        @Override
        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }
}

