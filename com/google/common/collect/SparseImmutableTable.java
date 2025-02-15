/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Maps;
import com.google.common.collect.RegularImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import java.util.AbstractCollection;
import java.util.LinkedHashMap;
import java.util.Map;

@Immutable(containerOf={"R", "C", "V"})
@GwtCompatible
final class SparseImmutableTable<R, C, V>
extends RegularImmutableTable<R, C, V> {
    static final ImmutableTable<Object, Object, Object> EMPTY = new SparseImmutableTable<Object, Object, Object>(ImmutableList.of(), ImmutableSet.of(), ImmutableSet.of());
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    private final int[] cellRowIndices;
    private final int[] cellColumnInRowIndices;

    SparseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        ImmutableMap<R, Integer> rowIndex = Maps.indexMap(rowSpace);
        LinkedHashMap rows = Maps.newLinkedHashMap();
        for (Object row : rowSpace) {
            rows.put(row, new LinkedHashMap());
        }
        LinkedHashMap columns = Maps.newLinkedHashMap();
        for (Object col : columnSpace) {
            columns.put(col, new LinkedHashMap());
        }
        int[] cellRowIndices = new int[cellList.size()];
        int[] cellColumnInRowIndices = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); ++i) {
            Table.Cell cell = (Table.Cell)cellList.get(i);
            Object r = cell.getRowKey();
            Object columnKey = cell.getColumnKey();
            Object value = cell.getValue();
            cellRowIndices[i] = (Integer)rowIndex.get(r);
            Map thisRow = (Map)rows.get(r);
            cellColumnInRowIndices[i] = thisRow.size();
            Object oldValue = thisRow.put(columnKey, value);
            this.checkNoDuplicate(r, columnKey, oldValue, value);
            ((Map)columns.get(columnKey)).put(r, value);
        }
        this.cellRowIndices = cellRowIndices;
        this.cellColumnInRowIndices = cellColumnInRowIndices;
        ImmutableMap.Builder rowBuilder = new ImmutableMap.Builder(rows.size());
        for (Map.Entry entry : rows.entrySet()) {
            rowBuilder.put(entry.getKey(), ImmutableMap.copyOf((Map)entry.getValue()));
        }
        this.rowMap = rowBuilder.build();
        ImmutableMap.Builder columnBuilder = new ImmutableMap.Builder(columns.size());
        for (Map.Entry col : columns.entrySet()) {
            columnBuilder.put(col.getKey(), ImmutableMap.copyOf((Map)col.getValue()));
        }
        this.columnMap = columnBuilder.build();
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
    public int size() {
        return this.cellRowIndices.length;
    }

    @Override
    Table.Cell<R, C, V> getCell(int index) {
        int rowIndex = this.cellRowIndices[index];
        Map.Entry rowEntry = (Map.Entry)((ImmutableSet)this.rowMap.entrySet()).asList().get(rowIndex);
        ImmutableMap row = (ImmutableMap)rowEntry.getValue();
        int columnIndex = this.cellColumnInRowIndices[index];
        Map.Entry colEntry = (Map.Entry)((ImmutableSet)row.entrySet()).asList().get(columnIndex);
        return SparseImmutableTable.cellOf(rowEntry.getKey(), colEntry.getKey(), colEntry.getValue());
    }

    @Override
    V getValue(int index) {
        int rowIndex = this.cellRowIndices[index];
        ImmutableMap row = (ImmutableMap)((ImmutableCollection)this.rowMap.values()).asList().get(rowIndex);
        int columnIndex = this.cellColumnInRowIndices[index];
        return (V)((ImmutableCollection)row.values()).asList().get(columnIndex);
    }

    @Override
    ImmutableTable.SerializedForm createSerializedForm() {
        ImmutableMap columnKeyToIndex = Maps.indexMap(this.columnKeySet());
        int[] cellColumnIndices = new int[((AbstractCollection)((Object)this.cellSet())).size()];
        int i = 0;
        for (Table.Cell cell : this.cellSet()) {
            cellColumnIndices[i++] = (Integer)columnKeyToIndex.get(cell.getColumnKey());
        }
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, cellColumnIndices);
    }
}

