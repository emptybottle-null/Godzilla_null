/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.AbstractTable;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingTable;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.RowSortedTable;
import com.google.common.collect.StandardTable;
import com.google.common.collect.Synchronized;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public final class Tables {
    private static final Function<? extends Map<?, ?>, ? extends Map<?, ?>> UNMODIFIABLE_WRAPPER = new Function<Map<Object, Object>, Map<Object, Object>>(){

        @Override
        public Map<Object, Object> apply(Map<Object, Object> input) {
            return Collections.unmodifiableMap(input);
        }
    };

    private Tables() {
    }

    @Beta
    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(java.util.function.Function<? super T, ? extends R> rowFunction, java.util.function.Function<? super T, ? extends C> columnFunction, java.util.function.Function<? super T, ? extends V> valueFunction, java.util.function.Supplier<I> tableSupplier) {
        return Tables.toTable(rowFunction, columnFunction, valueFunction, (v1, v2) -> {
            throw new IllegalStateException("Conflicting values " + v1 + " and " + v2);
        }, tableSupplier);
    }

    public static <T, R, C, V, I extends Table<R, C, V>> Collector<T, ?, I> toTable(java.util.function.Function<? super T, ? extends R> rowFunction, java.util.function.Function<? super T, ? extends C> columnFunction, java.util.function.Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction, java.util.function.Supplier<I> tableSupplier) {
        Preconditions.checkNotNull(rowFunction);
        Preconditions.checkNotNull(columnFunction);
        Preconditions.checkNotNull(valueFunction);
        Preconditions.checkNotNull(mergeFunction);
        Preconditions.checkNotNull(tableSupplier);
        return Collector.of(tableSupplier, (table, input) -> Tables.merge(table, rowFunction.apply(input), columnFunction.apply(input), valueFunction.apply(input), mergeFunction), (table1, table2) -> {
            for (Table.Cell cell2 : table2.cellSet()) {
                Tables.merge(table1, cell2.getRowKey(), cell2.getColumnKey(), cell2.getValue(), mergeFunction);
            }
            return table1;
        }, new Collector.Characteristics[0]);
    }

    private static <R, C, V> void merge(Table<R, C, V> table, R row, C column, V value, BinaryOperator<V> mergeFunction) {
        Preconditions.checkNotNull(value);
        V oldValue = table.get(row, column);
        if (oldValue == null) {
            table.put(row, column, value);
        } else {
            Object newValue = mergeFunction.apply(oldValue, value);
            if (newValue == null) {
                table.remove(row, column);
            } else {
                table.put(row, column, newValue);
            }
        }
    }

    public static <R, C, V> Table.Cell<R, C, V> immutableCell(@Nullable R rowKey, @Nullable C columnKey, @Nullable V value) {
        return new ImmutableCell<R, C, V>(rowKey, columnKey, value);
    }

    public static <R, C, V> Table<C, R, V> transpose(Table<R, C, V> table) {
        return table instanceof TransposeTable ? ((TransposeTable)table).original : new TransposeTable<C, R, V>(table);
    }

    @Beta
    public static <R, C, V> Table<R, C, V> newCustomTable(Map<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
        Preconditions.checkArgument(backingMap.isEmpty());
        Preconditions.checkNotNull(factory);
        return new StandardTable<R, C, V>(backingMap, factory);
    }

    @Beta
    public static <R, C, V1, V2> Table<R, C, V2> transformValues(Table<R, C, V1> fromTable, Function<? super V1, V2> function) {
        return new TransformedTable<R, C, V1, V2>(fromTable, function);
    }

    public static <R, C, V> Table<R, C, V> unmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
        return new UnmodifiableTable<R, C, V>(table);
    }

    @Beta
    public static <R, C, V> RowSortedTable<R, C, V> unmodifiableRowSortedTable(RowSortedTable<R, ? extends C, ? extends V> table) {
        return new UnmodifiableRowSortedMap<R, C, V>(table);
    }

    private static <K, V> Function<Map<K, V>, Map<K, V>> unmodifiableWrapper() {
        return UNMODIFIABLE_WRAPPER;
    }

    public static <R, C, V> Table<R, C, V> synchronizedTable(Table<R, C, V> table) {
        return Synchronized.table(table, null);
    }

    static boolean equalsImpl(Table<?, ?, ?> table, @Nullable Object obj) {
        if (obj == table) {
            return true;
        }
        if (obj instanceof Table) {
            Table that = (Table)obj;
            return table.cellSet().equals(that.cellSet());
        }
        return false;
    }

    static final class UnmodifiableRowSortedMap<R, C, V>
    extends UnmodifiableTable<R, C, V>
    implements RowSortedTable<R, C, V> {
        private static final long serialVersionUID = 0L;

        public UnmodifiableRowSortedMap(RowSortedTable<R, ? extends C, ? extends V> delegate) {
            super(delegate);
        }

        @Override
        protected RowSortedTable<R, C, V> delegate() {
            return (RowSortedTable)super.delegate();
        }

        @Override
        public SortedMap<R, Map<C, V>> rowMap() {
            Function wrapper = Tables.unmodifiableWrapper();
            return Collections.unmodifiableSortedMap(Maps.transformValues(this.delegate().rowMap(), wrapper));
        }

        @Override
        public SortedSet<R> rowKeySet() {
            return Collections.unmodifiableSortedSet(this.delegate().rowKeySet());
        }
    }

    private static class UnmodifiableTable<R, C, V>
    extends ForwardingTable<R, C, V>
    implements Serializable {
        final Table<? extends R, ? extends C, ? extends V> delegate;
        private static final long serialVersionUID = 0L;

        UnmodifiableTable(Table<? extends R, ? extends C, ? extends V> delegate) {
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        protected Table<R, C, V> delegate() {
            return this.delegate;
        }

        @Override
        public Set<Table.Cell<R, C, V>> cellSet() {
            return Collections.unmodifiableSet(super.cellSet());
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Map<R, V> column(@Nullable C columnKey) {
            return Collections.unmodifiableMap(super.column(columnKey));
        }

        @Override
        public Set<C> columnKeySet() {
            return Collections.unmodifiableSet(super.columnKeySet());
        }

        @Override
        public Map<C, Map<R, V>> columnMap() {
            Function wrapper = Tables.unmodifiableWrapper();
            return Collections.unmodifiableMap(Maps.transformValues(super.columnMap(), wrapper));
        }

        @Override
        public V put(@Nullable R rowKey, @Nullable C columnKey, @Nullable V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Map<C, V> row(@Nullable R rowKey) {
            return Collections.unmodifiableMap(super.row(rowKey));
        }

        @Override
        public Set<R> rowKeySet() {
            return Collections.unmodifiableSet(super.rowKeySet());
        }

        @Override
        public Map<R, Map<C, V>> rowMap() {
            Function wrapper = Tables.unmodifiableWrapper();
            return Collections.unmodifiableMap(Maps.transformValues(super.rowMap(), wrapper));
        }

        @Override
        public Collection<V> values() {
            return Collections.unmodifiableCollection(super.values());
        }
    }

    private static class TransformedTable<R, C, V1, V2>
    extends AbstractTable<R, C, V2> {
        final Table<R, C, V1> fromTable;
        final Function<? super V1, V2> function;

        TransformedTable(Table<R, C, V1> fromTable, Function<? super V1, V2> function) {
            this.fromTable = Preconditions.checkNotNull(fromTable);
            this.function = Preconditions.checkNotNull(function);
        }

        @Override
        public boolean contains(Object rowKey, Object columnKey) {
            return this.fromTable.contains(rowKey, columnKey);
        }

        @Override
        public V2 get(Object rowKey, Object columnKey) {
            return this.contains(rowKey, columnKey) ? (V2)this.function.apply((V1)this.fromTable.get(rowKey, columnKey)) : null;
        }

        @Override
        public int size() {
            return this.fromTable.size();
        }

        @Override
        public void clear() {
            this.fromTable.clear();
        }

        @Override
        public V2 put(R rowKey, C columnKey, V2 value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void putAll(Table<? extends R, ? extends C, ? extends V2> table) {
            throw new UnsupportedOperationException();
        }

        @Override
        public V2 remove(Object rowKey, Object columnKey) {
            return this.contains(rowKey, columnKey) ? (V2)this.function.apply((V1)this.fromTable.remove(rowKey, columnKey)) : null;
        }

        @Override
        public Map<C, V2> row(R rowKey) {
            return Maps.transformValues(this.fromTable.row(rowKey), this.function);
        }

        @Override
        public Map<R, V2> column(C columnKey) {
            return Maps.transformValues(this.fromTable.column(columnKey), this.function);
        }

        Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> cellFunction() {
            return new Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>>(){

                @Override
                public Table.Cell<R, C, V2> apply(Table.Cell<R, C, V1> cell) {
                    return Tables.immutableCell(cell.getRowKey(), cell.getColumnKey(), function.apply(cell.getValue()));
                }
            };
        }

        @Override
        Iterator<Table.Cell<R, C, V2>> cellIterator() {
            return Iterators.transform(this.fromTable.cellSet().iterator(), this.cellFunction());
        }

        @Override
        Spliterator<Table.Cell<R, C, V2>> cellSpliterator() {
            return CollectSpliterators.map(this.fromTable.cellSet().spliterator(), this.cellFunction());
        }

        @Override
        public Set<R> rowKeySet() {
            return this.fromTable.rowKeySet();
        }

        @Override
        public Set<C> columnKeySet() {
            return this.fromTable.columnKeySet();
        }

        @Override
        Collection<V2> createValues() {
            return Collections2.transform(this.fromTable.values(), this.function);
        }

        @Override
        public Map<R, Map<C, V2>> rowMap() {
            Function rowFunction = new Function<Map<C, V1>, Map<C, V2>>(){

                @Override
                public Map<C, V2> apply(Map<C, V1> row) {
                    return Maps.transformValues(row, function);
                }
            };
            return Maps.transformValues(this.fromTable.rowMap(), rowFunction);
        }

        @Override
        public Map<C, Map<R, V2>> columnMap() {
            Function columnFunction = new Function<Map<R, V1>, Map<R, V2>>(){

                @Override
                public Map<R, V2> apply(Map<R, V1> column) {
                    return Maps.transformValues(column, function);
                }
            };
            return Maps.transformValues(this.fromTable.columnMap(), columnFunction);
        }
    }

    private static class TransposeTable<C, R, V>
    extends AbstractTable<C, R, V> {
        final Table<R, C, V> original;
        private static final Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>> TRANSPOSE_CELL = new Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>>(){

            @Override
            public Table.Cell<?, ?, ?> apply(Table.Cell<?, ?, ?> cell) {
                return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
            }
        };

        TransposeTable(Table<R, C, V> original) {
            this.original = Preconditions.checkNotNull(original);
        }

        @Override
        public void clear() {
            this.original.clear();
        }

        @Override
        public Map<C, V> column(R columnKey) {
            return this.original.row(columnKey);
        }

        @Override
        public Set<R> columnKeySet() {
            return this.original.rowKeySet();
        }

        @Override
        public Map<R, Map<C, V>> columnMap() {
            return this.original.rowMap();
        }

        @Override
        public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
            return this.original.contains(columnKey, rowKey);
        }

        @Override
        public boolean containsColumn(@Nullable Object columnKey) {
            return this.original.containsRow(columnKey);
        }

        @Override
        public boolean containsRow(@Nullable Object rowKey) {
            return this.original.containsColumn(rowKey);
        }

        @Override
        public boolean containsValue(@Nullable Object value) {
            return this.original.containsValue(value);
        }

        @Override
        public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
            return this.original.get(columnKey, rowKey);
        }

        @Override
        public V put(C rowKey, R columnKey, V value) {
            return this.original.put(columnKey, rowKey, value);
        }

        @Override
        public void putAll(Table<? extends C, ? extends R, ? extends V> table) {
            this.original.putAll(Tables.transpose(table));
        }

        @Override
        public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
            return this.original.remove(columnKey, rowKey);
        }

        @Override
        public Map<R, V> row(C rowKey) {
            return this.original.column(rowKey);
        }

        @Override
        public Set<C> rowKeySet() {
            return this.original.columnKeySet();
        }

        @Override
        public Map<C, Map<R, V>> rowMap() {
            return this.original.columnMap();
        }

        @Override
        public int size() {
            return this.original.size();
        }

        @Override
        public Collection<V> values() {
            return this.original.values();
        }

        @Override
        Iterator<Table.Cell<C, R, V>> cellIterator() {
            return Iterators.transform(this.original.cellSet().iterator(), TRANSPOSE_CELL);
        }

        @Override
        Spliterator<Table.Cell<C, R, V>> cellSpliterator() {
            return CollectSpliterators.map(this.original.cellSet().spliterator(), TRANSPOSE_CELL);
        }
    }

    static abstract class AbstractCell<R, C, V>
    implements Table.Cell<R, C, V> {
        AbstractCell() {
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Table.Cell) {
                Table.Cell other = (Table.Cell)obj;
                return Objects.equal(this.getRowKey(), other.getRowKey()) && Objects.equal(this.getColumnKey(), other.getColumnKey()) && Objects.equal(this.getValue(), other.getValue());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.getRowKey(), this.getColumnKey(), this.getValue());
        }

        public String toString() {
            return "(" + this.getRowKey() + "," + this.getColumnKey() + ")=" + this.getValue();
        }
    }

    static final class ImmutableCell<R, C, V>
    extends AbstractCell<R, C, V>
    implements Serializable {
        private final @Nullable R rowKey;
        private final @Nullable C columnKey;
        private final @Nullable V value;
        private static final long serialVersionUID = 0L;

        ImmutableCell(@Nullable R rowKey, @Nullable C columnKey, @Nullable V value) {
            this.rowKey = rowKey;
            this.columnKey = columnKey;
            this.value = value;
        }

        @Override
        public R getRowKey() {
            return this.rowKey;
        }

        @Override
        public C getColumnKey() {
            return this.columnKey;
        }

        @Override
        public V getValue() {
            return this.value;
        }
    }
}

