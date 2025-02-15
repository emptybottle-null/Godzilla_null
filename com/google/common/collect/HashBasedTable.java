/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.StandardTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
public class HashBasedTable<R, C, V>
extends StandardTable<R, C, V> {
    private static final long serialVersionUID = 0L;

    public static <R, C, V> HashBasedTable<R, C, V> create() {
        return new HashBasedTable(new LinkedHashMap(), new Factory(0));
    }

    public static <R, C, V> HashBasedTable<R, C, V> create(int expectedRows, int expectedCellsPerRow) {
        CollectPreconditions.checkNonnegative(expectedCellsPerRow, "expectedCellsPerRow");
        LinkedHashMap backingMap = Maps.newLinkedHashMapWithExpectedSize(expectedRows);
        return new HashBasedTable(backingMap, new Factory(expectedCellsPerRow));
    }

    public static <R, C, V> HashBasedTable<R, C, V> create(Table<? extends R, ? extends C, ? extends V> table) {
        HashBasedTable<R, C, V> result = HashBasedTable.create();
        result.putAll((Table)table);
        return result;
    }

    HashBasedTable(Map<R, Map<C, V>> backingMap, Factory<C, V> factory) {
        super(backingMap, factory);
    }

    @Override
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return super.contains(rowKey, columnKey);
    }

    @Override
    public boolean containsColumn(@Nullable Object columnKey) {
        return super.containsColumn(columnKey);
    }

    @Override
    public boolean containsRow(@Nullable Object rowKey) {
        return super.containsRow(rowKey);
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return super.containsValue(value);
    }

    @Override
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        return super.get(rowKey, columnKey);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @Override
    @CanIgnoreReturnValue
    public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
        return super.remove(rowKey, columnKey);
    }

    private static class Factory<C, V>
    implements Supplier<Map<C, V>>,
    Serializable {
        final int expectedSize;
        private static final long serialVersionUID = 0L;

        Factory(int expectedSize) {
            this.expectedSize = expectedSize;
        }

        @Override
        public Map<C, V> get() {
            return Maps.newLinkedHashMapWithExpectedSize(this.expectedSize);
        }
    }
}

