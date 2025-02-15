/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.RegularImmutableTable;
import com.google.common.collect.SingletonImmutableTable;
import com.google.common.collect.SparseImmutableTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
public abstract class ImmutableTable<R, C, V>
extends AbstractTable<R, C, V>
implements Serializable {
    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> rowFunction, Function<? super T, ? extends C> columnFunction, Function<? super T, ? extends V> valueFunction) {
        Preconditions.checkNotNull(rowFunction, "rowFunction");
        Preconditions.checkNotNull(columnFunction, "columnFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        return Collector.of(() -> new Builder(), (builder, t) -> builder.put(rowFunction.apply(t), columnFunction.apply(t), valueFunction.apply(t)), (b1, b2) -> b1.combine(b2), b -> b.build(), new Collector.Characteristics[0]);
    }

    public static <T, R, C, V> Collector<T, ?, ImmutableTable<R, C, V>> toImmutableTable(Function<? super T, ? extends R> rowFunction, Function<? super T, ? extends C> columnFunction, Function<? super T, ? extends V> valueFunction, BinaryOperator<V> mergeFunction) {
        Preconditions.checkNotNull(rowFunction, "rowFunction");
        Preconditions.checkNotNull(columnFunction, "columnFunction");
        Preconditions.checkNotNull(valueFunction, "valueFunction");
        Preconditions.checkNotNull(mergeFunction, "mergeFunction");
        return Collector.of(() -> new CollectorState(), (state, input) -> state.put(rowFunction.apply(input), columnFunction.apply(input), valueFunction.apply(input), mergeFunction), (s1, s2) -> s1.combine(s2, mergeFunction), state -> state.toTable(), new Collector.Characteristics[0]);
    }

    public static <R, C, V> ImmutableTable<R, C, V> of() {
        return SparseImmutableTable.EMPTY;
    }

    public static <R, C, V> ImmutableTable<R, C, V> of(R rowKey, C columnKey, V value) {
        return new SingletonImmutableTable<R, C, V>(rowKey, columnKey, value);
    }

    public static <R, C, V> ImmutableTable<R, C, V> copyOf(Table<? extends R, ? extends C, ? extends V> table) {
        if (table instanceof ImmutableTable) {
            ImmutableTable parameterizedTable = (ImmutableTable)table;
            return parameterizedTable;
        }
        return ImmutableTable.copyOf(table.cellSet());
    }

    private static <R, C, V> ImmutableTable<R, C, V> copyOf(Iterable<? extends Table.Cell<? extends R, ? extends C, ? extends V>> cells) {
        Builder<R, C, V> builder = ImmutableTable.builder();
        for (Table.Cell<R, C, V> cell : cells) {
            builder.put(cell);
        }
        return builder.build();
    }

    public static <R, C, V> Builder<R, C, V> builder() {
        return new Builder();
    }

    static <R, C, V> Table.Cell<R, C, V> cellOf(R rowKey, C columnKey, V value) {
        return Tables.immutableCell(Preconditions.checkNotNull(rowKey, "rowKey"), Preconditions.checkNotNull(columnKey, "columnKey"), Preconditions.checkNotNull(value, "value"));
    }

    ImmutableTable() {
    }

    @Override
    public ImmutableSet<Table.Cell<R, C, V>> cellSet() {
        return (ImmutableSet)super.cellSet();
    }

    @Override
    abstract ImmutableSet<Table.Cell<R, C, V>> createCellSet();

    @Override
    final UnmodifiableIterator<Table.Cell<R, C, V>> cellIterator() {
        throw new AssertionError((Object)"should never be called");
    }

    @Override
    final Spliterator<Table.Cell<R, C, V>> cellSpliterator() {
        throw new AssertionError((Object)"should never be called");
    }

    @Override
    public ImmutableCollection<V> values() {
        return (ImmutableCollection)super.values();
    }

    @Override
    abstract ImmutableCollection<V> createValues();

    @Override
    final Iterator<V> valuesIterator() {
        throw new AssertionError((Object)"should never be called");
    }

    @Override
    public ImmutableMap<R, V> column(C columnKey) {
        Preconditions.checkNotNull(columnKey, "columnKey");
        return MoreObjects.firstNonNull((ImmutableMap)((ImmutableMap)this.columnMap()).get(columnKey), ImmutableMap.of());
    }

    @Override
    public ImmutableSet<C> columnKeySet() {
        return ((ImmutableMap)this.columnMap()).keySet();
    }

    @Override
    public abstract ImmutableMap<C, Map<R, V>> columnMap();

    @Override
    public ImmutableMap<C, V> row(R rowKey) {
        Preconditions.checkNotNull(rowKey, "rowKey");
        return MoreObjects.firstNonNull((ImmutableMap)((ImmutableMap)this.rowMap()).get(rowKey), ImmutableMap.of());
    }

    @Override
    public ImmutableSet<R> rowKeySet() {
        return ((ImmutableMap)this.rowMap()).keySet();
    }

    @Override
    public abstract ImmutableMap<R, Map<C, V>> rowMap();

    @Override
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return this.get(rowKey, columnKey) != null;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return ((ImmutableCollection)this.values()).contains(value);
    }

    @Override
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final V put(R rowKey, C columnKey, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public final void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public final V remove(Object rowKey, Object columnKey) {
        throw new UnsupportedOperationException();
    }

    abstract SerializedForm createSerializedForm();

    final Object writeReplace() {
        return this.createSerializedForm();
    }

    static final class SerializedForm
    implements Serializable {
        private final Object[] rowKeys;
        private final Object[] columnKeys;
        private final Object[] cellValues;
        private final int[] cellRowIndices;
        private final int[] cellColumnIndices;
        private static final long serialVersionUID = 0L;

        private SerializedForm(Object[] rowKeys, Object[] columnKeys, Object[] cellValues, int[] cellRowIndices, int[] cellColumnIndices) {
            this.rowKeys = rowKeys;
            this.columnKeys = columnKeys;
            this.cellValues = cellValues;
            this.cellRowIndices = cellRowIndices;
            this.cellColumnIndices = cellColumnIndices;
        }

        static SerializedForm create(ImmutableTable<?, ?, ?> table, int[] cellRowIndices, int[] cellColumnIndices) {
            return new SerializedForm(((ImmutableCollection)((Object)table.rowKeySet())).toArray(), ((ImmutableCollection)((Object)table.columnKeySet())).toArray(), ((ImmutableCollection)table.values()).toArray(), cellRowIndices, cellColumnIndices);
        }

        Object readResolve() {
            if (this.cellValues.length == 0) {
                return ImmutableTable.of();
            }
            if (this.cellValues.length == 1) {
                return ImmutableTable.of(this.rowKeys[0], this.columnKeys[0], this.cellValues[0]);
            }
            ImmutableList.Builder cellListBuilder = new ImmutableList.Builder(this.cellValues.length);
            for (int i = 0; i < this.cellValues.length; ++i) {
                cellListBuilder.add(ImmutableTable.cellOf(this.rowKeys[this.cellRowIndices[i]], this.columnKeys[this.cellColumnIndices[i]], this.cellValues[i]));
            }
            return RegularImmutableTable.forOrderedComponents(cellListBuilder.build(), ImmutableSet.copyOf(this.rowKeys), ImmutableSet.copyOf(this.columnKeys));
        }
    }

    public static final class Builder<R, C, V> {
        private final List<Table.Cell<R, C, V>> cells = Lists.newArrayList();
        private @MonotonicNonNull Comparator<? super R> rowComparator;
        private @MonotonicNonNull Comparator<? super C> columnComparator;

        @CanIgnoreReturnValue
        public Builder<R, C, V> orderRowsBy(Comparator<? super R> rowComparator) {
            this.rowComparator = Preconditions.checkNotNull(rowComparator, "rowComparator");
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> orderColumnsBy(Comparator<? super C> columnComparator) {
            this.columnComparator = Preconditions.checkNotNull(columnComparator, "columnComparator");
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> put(R rowKey, C columnKey, V value) {
            this.cells.add(ImmutableTable.cellOf(rowKey, columnKey, value));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> put(Table.Cell<? extends R, ? extends C, ? extends V> cell) {
            if (cell instanceof Tables.ImmutableCell) {
                Preconditions.checkNotNull(cell.getRowKey(), "row");
                Preconditions.checkNotNull(cell.getColumnKey(), "column");
                Preconditions.checkNotNull(cell.getValue(), "value");
                Table.Cell<R, C, V> immutableCell = cell;
                this.cells.add(immutableCell);
            } else {
                this.put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<R, C, V> putAll(Table<? extends R, ? extends C, ? extends V> table) {
            for (Table.Cell<R, C, V> cell : table.cellSet()) {
                this.put(cell);
            }
            return this;
        }

        Builder<R, C, V> combine(Builder<R, C, V> other) {
            this.cells.addAll(other.cells);
            return this;
        }

        public ImmutableTable<R, C, V> build() {
            int size = this.cells.size();
            switch (size) {
                case 0: {
                    return ImmutableTable.of();
                }
                case 1: {
                    return new SingletonImmutableTable<R, C, V>(Iterables.getOnlyElement(this.cells));
                }
            }
            return RegularImmutableTable.forCells(this.cells, this.rowComparator, this.columnComparator);
        }
    }

    private static final class MutableCell<R, C, V>
    extends Tables.AbstractCell<R, C, V> {
        private final R row;
        private final C column;
        private V value;

        MutableCell(R row, C column, V value) {
            this.row = Preconditions.checkNotNull(row, "row");
            this.column = Preconditions.checkNotNull(column, "column");
            this.value = Preconditions.checkNotNull(value, "value");
        }

        @Override
        public R getRowKey() {
            return this.row;
        }

        @Override
        public C getColumnKey() {
            return this.column;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        void merge(V value, BinaryOperator<V> mergeFunction) {
            Preconditions.checkNotNull(value, "value");
            this.value = Preconditions.checkNotNull(mergeFunction.apply(this.value, value), "mergeFunction.apply");
        }
    }

    private static final class CollectorState<R, C, V> {
        final List<MutableCell<R, C, V>> insertionOrder = new ArrayList<MutableCell<R, C, V>>();
        final Table<R, C, MutableCell<R, C, V>> table = HashBasedTable.create();

        private CollectorState() {
        }

        void put(R row, C column, V value, BinaryOperator<V> merger) {
            MutableCell<R, C, V> oldCell = this.table.get(row, column);
            if (oldCell == null) {
                MutableCell<R, C, V> cell = new MutableCell<R, C, V>(row, column, value);
                this.insertionOrder.add(cell);
                this.table.put(row, column, cell);
            } else {
                oldCell.merge(value, merger);
            }
        }

        CollectorState<R, C, V> combine(CollectorState<R, C, V> other, BinaryOperator<V> merger) {
            for (MutableCell<R, C, V> cell : other.insertionOrder) {
                this.put(cell.getRowKey(), cell.getColumnKey(), cell.getValue(), merger);
            }
            return this;
        }

        ImmutableTable<R, C, V> toTable() {
            return ImmutableTable.copyOf(this.insertionOrder);
        }
    }
}

