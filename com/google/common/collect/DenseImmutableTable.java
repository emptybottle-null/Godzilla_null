/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.RegularImmutableTable;
import com.google.common.collect.Table;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.Immutable;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@Immutable(containerOf={"R", "C", "V"})
@GwtCompatible
final class DenseImmutableTable<R, C, V>
extends RegularImmutableTable<R, C, V> {
    private final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    private final int[] rowCounts;
    private final int[] columnCounts;
    private final V[][] values;
    private final int[] cellRowIndices;
    private final int[] cellColumnIndices;

    DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        Object[][] array = new Object[rowSpace.size()][columnSpace.size()];
        this.values = array;
        this.rowKeyToIndex = Maps.indexMap(rowSpace);
        this.columnKeyToIndex = Maps.indexMap(columnSpace);
        this.rowCounts = new int[this.rowKeyToIndex.size()];
        this.columnCounts = new int[this.columnKeyToIndex.size()];
        int[] cellRowIndices = new int[cellList.size()];
        int[] cellColumnIndices = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); ++i) {
            Table.Cell cell = (Table.Cell)cellList.get(i);
            Object rowKey = cell.getRowKey();
            Object columnKey = cell.getColumnKey();
            int rowIndex = this.rowKeyToIndex.get(rowKey);
            int columnIndex = this.columnKeyToIndex.get(columnKey);
            V existingValue = this.values[rowIndex][columnIndex];
            this.checkNoDuplicate(rowKey, columnKey, existingValue, cell.getValue());
            this.values[rowIndex][columnIndex] = cell.getValue();
            int n = rowIndex;
            this.rowCounts[n] = this.rowCounts[n] + 1;
            int n2 = columnIndex;
            this.columnCounts[n2] = this.columnCounts[n2] + 1;
            cellRowIndices[i] = rowIndex;
            cellColumnIndices[i] = columnIndex;
        }
        this.cellRowIndices = cellRowIndices;
        this.cellColumnIndices = cellColumnIndices;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    @Override
    public ImmutableMap<C, Map<R, V>> columnMap() {
        ImmutableMap<C, ImmutableMap<R, V>> columnMap = this.columnMap;
        return ImmutableMap.copyOf(columnMap);
    }

    @Override
    public ImmutableMap<R, Map<C, V>> rowMap() {
        ImmutableMap<R, ImmutableMap<C, V>> rowMap = this.rowMap;
        return ImmutableMap.copyOf(rowMap);
    }

    @Override
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        return rowIndex == null || columnIndex == null ? null : (V)this.values[rowIndex][columnIndex];
    }

    @Override
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override
    Table.Cell<R, C, V> getCell(int index) {
        int rowIndex = this.cellRowIndices[index];
        int columnIndex = this.cellColumnIndices[index];
        Object rowKey = ((ImmutableSet)this.rowKeySet()).asList().get(rowIndex);
        Object columnKey = ((ImmutableSet)this.columnKeySet()).asList().get(columnIndex);
        V value = this.values[rowIndex][columnIndex];
        return DenseImmutableTable.cellOf(rowKey, columnKey, value);
    }

    @Override
    V getValue(int index) {
        return this.values[this.cellRowIndices[index]][this.cellColumnIndices[index]];
    }

    @Override
    ImmutableTable.SerializedForm createSerializedForm() {
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
    }

    private final class ColumnMap
    extends ImmutableArrayMap<C, ImmutableMap<R, V>> {
        private ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        @Override
        ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        @Override
        ImmutableMap<R, V> getValue(int keyIndex) {
            return new Column(keyIndex);
        }

        @Override
        boolean isPartialView() {
            return false;
        }
    }

    private final class RowMap
    extends ImmutableArrayMap<R, ImmutableMap<C, V>> {
        private RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        @Override
        ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        @Override
        ImmutableMap<C, V> getValue(int keyIndex) {
            return new Row(keyIndex);
        }

        @Override
        boolean isPartialView() {
            return false;
        }
    }

    private final class Column
    extends ImmutableArrayMap<R, V> {
        private final int columnIndex;

        Column(int columnIndex) {
            super(DenseImmutableTable.this.columnCounts[columnIndex]);
            this.columnIndex = columnIndex;
        }

        @Override
        ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        @Override
        V getValue(int keyIndex) {
            return DenseImmutableTable.this.values[keyIndex][this.columnIndex];
        }

        @Override
        boolean isPartialView() {
            return true;
        }
    }

    private final class Row
    extends ImmutableArrayMap<C, V> {
        private final int rowIndex;

        Row(int rowIndex) {
            super(DenseImmutableTable.this.rowCounts[rowIndex]);
            this.rowIndex = rowIndex;
        }

        @Override
        ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        @Override
        V getValue(int keyIndex) {
            return DenseImmutableTable.this.values[this.rowIndex][keyIndex];
        }

        @Override
        boolean isPartialView() {
            return true;
        }
    }

    private static abstract class ImmutableArrayMap<K, V>
    extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
        private final int size;

        ImmutableArrayMap(int size) {
            this.size = size;
        }

        abstract ImmutableMap<K, Integer> keyToIndex();

        private boolean isFull() {
            return this.size == this.keyToIndex().size();
        }

        K getKey(int index) {
            return (K)((ImmutableSet)this.keyToIndex().keySet()).asList().get(index);
        }

        abstract @Nullable V getValue(int var1);

        @Override
        ImmutableSet<K> createKeySet() {
            return this.isFull() ? this.keyToIndex().keySet() : super.createKeySet();
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public V get(@Nullable Object key) {
            Integer keyIndex = this.keyToIndex().get(key);
            return keyIndex == null ? null : (V)this.getValue(keyIndex);
        }

        @Override
        UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIterator<Map.Entry<K, V>>(){
                private int index = -1;
                private final int maxIndex = this.keyToIndex().size();

                @Override
                protected Map.Entry<K, V> computeNext() {
                    ++this.index;
                    while (this.index < this.maxIndex) {
                        Object value = this.getValue(this.index);
                        if (value != null) {
                            return Maps.immutableEntry(this.getKey(this.index), value);
                        }
                        ++this.index;
                    }
                    return (Map.Entry)this.endOfData();
                }
            };
        }
    }
}

