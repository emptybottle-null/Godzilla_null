/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.AbstractTable;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingMapEntry;
import com.google.common.collect.GwtTransient;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
class StandardTable<R, C, V>
extends AbstractTable<R, C, V>
implements Serializable {
    @GwtTransient
    final Map<R, Map<C, V>> backingMap;
    @GwtTransient
    final Supplier<? extends Map<C, V>> factory;
    private transient @MonotonicNonNull Set<C> columnKeySet;
    private transient @MonotonicNonNull Map<R, Map<C, V>> rowMap;
    private transient @MonotonicNonNull ColumnMap columnMap;
    private static final long serialVersionUID = 0L;

    StandardTable(Map<R, Map<C, V>> backingMap, Supplier<? extends Map<C, V>> factory) {
        this.backingMap = backingMap;
        this.factory = factory;
    }

    @Override
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return rowKey != null && columnKey != null && super.contains(rowKey, columnKey);
    }

    @Override
    public boolean containsColumn(@Nullable Object columnKey) {
        if (columnKey == null) {
            return false;
        }
        for (Map<C, V> map : this.backingMap.values()) {
            if (!Maps.safeContainsKey(map, columnKey)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsRow(@Nullable Object rowKey) {
        return rowKey != null && Maps.safeContainsKey(this.backingMap, rowKey);
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return value != null && super.containsValue(value);
    }

    @Override
    public V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        return rowKey == null || columnKey == null ? null : (V)super.get(rowKey, columnKey);
    }

    @Override
    public boolean isEmpty() {
        return this.backingMap.isEmpty();
    }

    @Override
    public int size() {
        int size = 0;
        for (Map<C, V> map : this.backingMap.values()) {
            size += map.size();
        }
        return size;
    }

    @Override
    public void clear() {
        this.backingMap.clear();
    }

    private Map<C, V> getOrCreate(R rowKey) {
        Map<C, V> map = this.backingMap.get(rowKey);
        if (map == null) {
            map = this.factory.get();
            this.backingMap.put(rowKey, map);
        }
        return map;
    }

    @Override
    @CanIgnoreReturnValue
    public V put(R rowKey, C columnKey, V value) {
        Preconditions.checkNotNull(rowKey);
        Preconditions.checkNotNull(columnKey);
        Preconditions.checkNotNull(value);
        return this.getOrCreate(rowKey).put(columnKey, value);
    }

    @Override
    @CanIgnoreReturnValue
    public V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
        if (rowKey == null || columnKey == null) {
            return null;
        }
        Map<C, V> map = Maps.safeGet(this.backingMap, rowKey);
        if (map == null) {
            return null;
        }
        V value = map.remove(columnKey);
        if (map.isEmpty()) {
            this.backingMap.remove(rowKey);
        }
        return value;
    }

    @CanIgnoreReturnValue
    private Map<R, V> removeColumn(Object column) {
        LinkedHashMap<R, V> output = new LinkedHashMap<R, V>();
        Iterator<Map.Entry<R, Map<C, V>>> iterator = this.backingMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<R, Map<C, V>> entry = iterator.next();
            V value = entry.getValue().remove(column);
            if (value == null) continue;
            output.put(entry.getKey(), value);
            if (!entry.getValue().isEmpty()) continue;
            iterator.remove();
        }
        return output;
    }

    private boolean containsMapping(Object rowKey, Object columnKey, Object value) {
        return value != null && value.equals(this.get(rowKey, columnKey));
    }

    private boolean removeMapping(Object rowKey, Object columnKey, Object value) {
        if (this.containsMapping(rowKey, columnKey, value)) {
            this.remove(rowKey, columnKey);
            return true;
        }
        return false;
    }

    @Override
    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Override
    Iterator<Table.Cell<R, C, V>> cellIterator() {
        return new CellIterator();
    }

    @Override
    Spliterator<Table.Cell<R, C, V>> cellSpliterator() {
        return CollectSpliterators.flatMap(this.backingMap.entrySet().spliterator(), rowEntry -> CollectSpliterators.map(((Map)rowEntry.getValue()).entrySet().spliterator(), columnEntry -> Tables.immutableCell(rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue())), 65, this.size());
    }

    @Override
    public Map<C, V> row(R rowKey) {
        return new Row(rowKey);
    }

    @Override
    public Map<R, V> column(C columnKey) {
        return new Column(columnKey);
    }

    @Override
    public Set<R> rowKeySet() {
        return this.rowMap().keySet();
    }

    @Override
    public Set<C> columnKeySet() {
        ColumnKeySet result = this.columnKeySet;
        return result == null ? (this.columnKeySet = new ColumnKeySet()) : result;
    }

    Iterator<C> createColumnKeyIterator() {
        return new ColumnKeyIterator();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, Map<C, V>>> result = this.rowMap;
        return result == null ? (this.rowMap = this.createRowMap()) : result;
    }

    Map<R, Map<C, V>> createRowMap() {
        return new RowMap();
    }

    @Override
    public Map<C, Map<R, V>> columnMap() {
        ColumnMap result = this.columnMap;
        return result == null ? (this.columnMap = new ColumnMap()) : result;
    }

    private class ColumnMap
    extends Maps.ViewCachingAbstractMap<C, Map<R, V>> {
        private ColumnMap() {
        }

        @Override
        public Map<R, V> get(Object key) {
            return StandardTable.this.containsColumn(key) ? StandardTable.this.column(key) : null;
        }

        @Override
        public boolean containsKey(Object key) {
            return StandardTable.this.containsColumn(key);
        }

        @Override
        public Map<R, V> remove(Object key) {
            return StandardTable.this.containsColumn(key) ? StandardTable.this.removeColumn(key) : null;
        }

        @Override
        public Set<Map.Entry<C, Map<R, V>>> createEntrySet() {
            return new ColumnMapEntrySet();
        }

        @Override
        public Set<C> keySet() {
            return StandardTable.this.columnKeySet();
        }

        @Override
        Collection<Map<R, V>> createValues() {
            return new ColumnMapValues();
        }

        private class ColumnMapValues
        extends Maps.Values<C, Map<R, V>> {
            ColumnMapValues() {
                super(ColumnMap.this);
            }

            @Override
            public boolean remove(Object obj) {
                for (Map.Entry entry : ColumnMap.this.entrySet()) {
                    if (!((Map)entry.getValue()).equals(obj)) continue;
                    StandardTable.this.removeColumn(entry.getKey());
                    return true;
                }
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                boolean changed = false;
                for (Object columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
                    if (!c.contains(StandardTable.this.column(columnKey))) continue;
                    StandardTable.this.removeColumn(columnKey);
                    changed = true;
                }
                return changed;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                boolean changed = false;
                for (Object columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
                    if (c.contains(StandardTable.this.column(columnKey))) continue;
                    StandardTable.this.removeColumn(columnKey);
                    changed = true;
                }
                return changed;
            }
        }

        class ColumnMapEntrySet
        extends TableSet<Map.Entry<C, Map<R, V>>> {
            ColumnMapEntrySet() {
            }

            @Override
            public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
                return Maps.asMapEntryIterator(StandardTable.this.columnKeySet(), new Function<C, Map<R, V>>(){

                    @Override
                    public Map<R, V> apply(C columnKey) {
                        return StandardTable.this.column(columnKey);
                    }
                });
            }

            @Override
            public int size() {
                return StandardTable.this.columnKeySet().size();
            }

            @Override
            public boolean contains(Object obj) {
                Map.Entry entry;
                if (obj instanceof Map.Entry && StandardTable.this.containsColumn((entry = (Map.Entry)obj).getKey())) {
                    Object columnKey = entry.getKey();
                    return ColumnMap.this.get(columnKey).equals(entry.getValue());
                }
                return false;
            }

            @Override
            public boolean remove(Object obj) {
                if (this.contains(obj)) {
                    Map.Entry entry = (Map.Entry)obj;
                    StandardTable.this.removeColumn(entry.getKey());
                    return true;
                }
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                return Sets.removeAllImpl(this, c.iterator());
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                Preconditions.checkNotNull(c);
                boolean changed = false;
                for (Object columnKey : Lists.newArrayList(StandardTable.this.columnKeySet().iterator())) {
                    if (c.contains(Maps.immutableEntry(columnKey, StandardTable.this.column(columnKey)))) continue;
                    StandardTable.this.removeColumn(columnKey);
                    changed = true;
                }
                return changed;
            }
        }
    }

    class RowMap
    extends Maps.ViewCachingAbstractMap<R, Map<C, V>> {
        RowMap() {
        }

        @Override
        public boolean containsKey(Object key) {
            return StandardTable.this.containsRow(key);
        }

        @Override
        public Map<C, V> get(Object key) {
            return StandardTable.this.containsRow(key) ? StandardTable.this.row(key) : null;
        }

        @Override
        public Map<C, V> remove(Object key) {
            return key == null ? null : StandardTable.this.backingMap.remove(key);
        }

        @Override
        protected Set<Map.Entry<R, Map<C, V>>> createEntrySet() {
            return new EntrySet();
        }

        class EntrySet
        extends TableSet<Map.Entry<R, Map<C, V>>> {
            EntrySet() {
            }

            @Override
            public Iterator<Map.Entry<R, Map<C, V>>> iterator() {
                return Maps.asMapEntryIterator(StandardTable.this.backingMap.keySet(), new Function<R, Map<C, V>>(){

                    @Override
                    public Map<C, V> apply(R rowKey) {
                        return StandardTable.this.row(rowKey);
                    }
                });
            }

            @Override
            public int size() {
                return StandardTable.this.backingMap.size();
            }

            @Override
            public boolean contains(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry)obj;
                    return entry.getKey() != null && entry.getValue() instanceof Map && Collections2.safeContains(StandardTable.this.backingMap.entrySet(), entry);
                }
                return false;
            }

            @Override
            public boolean remove(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry)obj;
                    return entry.getKey() != null && entry.getValue() instanceof Map && StandardTable.this.backingMap.entrySet().remove(entry);
                }
                return false;
            }
        }
    }

    private class ColumnKeyIterator
    extends AbstractIterator<C> {
        final Map<C, V> seen;
        final Iterator<Map<C, V>> mapIterator;
        Iterator<Map.Entry<C, V>> entryIterator;

        private ColumnKeyIterator() {
            this.seen = StandardTable.this.factory.get();
            this.mapIterator = StandardTable.this.backingMap.values().iterator();
            this.entryIterator = Iterators.emptyIterator();
        }

        @Override
        protected C computeNext() {
            while (true) {
                if (this.entryIterator.hasNext()) {
                    Map.Entry entry = this.entryIterator.next();
                    if (this.seen.containsKey(entry.getKey())) continue;
                    this.seen.put(entry.getKey(), entry.getValue());
                    return entry.getKey();
                }
                if (!this.mapIterator.hasNext()) break;
                this.entryIterator = this.mapIterator.next().entrySet().iterator();
            }
            return this.endOfData();
        }
    }

    private class ColumnKeySet
    extends TableSet<C> {
        private ColumnKeySet() {
        }

        @Override
        public Iterator<C> iterator() {
            return StandardTable.this.createColumnKeyIterator();
        }

        @Override
        public int size() {
            return Iterators.size(this.iterator());
        }

        @Override
        public boolean remove(Object obj) {
            if (obj == null) {
                return false;
            }
            boolean changed = false;
            Iterator iterator = StandardTable.this.backingMap.values().iterator();
            while (iterator.hasNext()) {
                Map map = iterator.next();
                if (!map.keySet().remove(obj)) continue;
                changed = true;
                if (!map.isEmpty()) continue;
                iterator.remove();
            }
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;
            Iterator iterator = StandardTable.this.backingMap.values().iterator();
            while (iterator.hasNext()) {
                Map map = iterator.next();
                if (!Iterators.removeAll(map.keySet().iterator(), c)) continue;
                changed = true;
                if (!map.isEmpty()) continue;
                iterator.remove();
            }
            return changed;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            Preconditions.checkNotNull(c);
            boolean changed = false;
            Iterator iterator = StandardTable.this.backingMap.values().iterator();
            while (iterator.hasNext()) {
                Map map = iterator.next();
                if (!map.keySet().retainAll(c)) continue;
                changed = true;
                if (!map.isEmpty()) continue;
                iterator.remove();
            }
            return changed;
        }

        @Override
        public boolean contains(Object obj) {
            return StandardTable.this.containsColumn(obj);
        }
    }

    private class Column
    extends Maps.ViewCachingAbstractMap<R, V> {
        final C columnKey;

        Column(C columnKey) {
            this.columnKey = Preconditions.checkNotNull(columnKey);
        }

        @Override
        public V put(R key, V value) {
            return StandardTable.this.put(key, this.columnKey, value);
        }

        @Override
        public V get(Object key) {
            return StandardTable.this.get(key, this.columnKey);
        }

        @Override
        public boolean containsKey(Object key) {
            return StandardTable.this.contains(key, this.columnKey);
        }

        @Override
        public V remove(Object key) {
            return StandardTable.this.remove(key, this.columnKey);
        }

        @CanIgnoreReturnValue
        boolean removeFromColumnIf(Predicate<? super Map.Entry<R, V>> predicate) {
            boolean changed = false;
            Iterator iterator = StandardTable.this.backingMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                Map map = entry.getValue();
                Object value = map.get(this.columnKey);
                if (value == null || !predicate.apply(Maps.immutableEntry(entry.getKey(), value))) continue;
                map.remove(this.columnKey);
                changed = true;
                if (!map.isEmpty()) continue;
                iterator.remove();
            }
            return changed;
        }

        @Override
        Set<Map.Entry<R, V>> createEntrySet() {
            return new EntrySet();
        }

        @Override
        Set<R> createKeySet() {
            return new KeySet();
        }

        @Override
        Collection<V> createValues() {
            return new Values();
        }

        private class Values
        extends Maps.Values<R, V> {
            Values() {
                super(Column.this);
            }

            @Override
            public boolean remove(Object obj) {
                return obj != null && Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.equalTo(obj)));
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.in(c)));
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(c))));
            }
        }

        private class KeySet
        extends Maps.KeySet<R, V> {
            KeySet() {
                super(Column.this);
            }

            @Override
            public boolean contains(Object obj) {
                return StandardTable.this.contains(obj, Column.this.columnKey);
            }

            @Override
            public boolean remove(Object obj) {
                return StandardTable.this.remove(obj, Column.this.columnKey) != null;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(c))));
            }
        }

        private class EntrySetIterator
        extends AbstractIterator<Map.Entry<R, V>> {
            final Iterator<Map.Entry<R, Map<C, V>>> iterator;

            private EntrySetIterator() {
                this.iterator = StandardTable.this.backingMap.entrySet().iterator();
            }

            @Override
            protected Map.Entry<R, V> computeNext() {
                while (this.iterator.hasNext()) {
                    final Map.Entry entry = this.iterator.next();
                    if (!entry.getValue().containsKey(Column.this.columnKey)) continue;
                    class EntryImpl
                    extends AbstractMapEntry<R, V> {
                        EntryImpl() {
                        }

                        @Override
                        public R getKey() {
                            return entry.getKey();
                        }

                        @Override
                        public V getValue() {
                            return ((Map)entry.getValue()).get(Column.this.columnKey);
                        }

                        @Override
                        public V setValue(V value) {
                            return ((Map)entry.getValue()).put(Column.this.columnKey, Preconditions.checkNotNull(value));
                        }
                    }
                    return new EntryImpl();
                }
                return (Map.Entry)this.endOfData();
            }
        }

        private class EntrySet
        extends Sets.ImprovedAbstractSet<Map.Entry<R, V>> {
            private EntrySet() {
            }

            @Override
            public Iterator<Map.Entry<R, V>> iterator() {
                return new EntrySetIterator();
            }

            @Override
            public int size() {
                int size = 0;
                for (Map map : StandardTable.this.backingMap.values()) {
                    if (!map.containsKey(Column.this.columnKey)) continue;
                    ++size;
                }
                return size;
            }

            @Override
            public boolean isEmpty() {
                return !StandardTable.this.containsColumn(Column.this.columnKey);
            }

            @Override
            public void clear() {
                Column.this.removeFromColumnIf(Predicates.alwaysTrue());
            }

            @Override
            public boolean contains(Object o) {
                if (o instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry)o;
                    return StandardTable.this.containsMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
                }
                return false;
            }

            @Override
            public boolean remove(Object obj) {
                if (obj instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry)obj;
                    return StandardTable.this.removeMapping(entry.getKey(), Column.this.columnKey, entry.getValue());
                }
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return Column.this.removeFromColumnIf(Predicates.not(Predicates.in(c)));
            }
        }
    }

    class Row
    extends Maps.IteratorBasedAbstractMap<C, V> {
        final R rowKey;
        @Nullable Map<C, V> backingRowMap;

        Row(R rowKey) {
            this.rowKey = Preconditions.checkNotNull(rowKey);
        }

        Map<C, V> backingRowMap() {
            Map map;
            if (this.backingRowMap == null || this.backingRowMap.isEmpty() && StandardTable.this.backingMap.containsKey(this.rowKey)) {
                this.backingRowMap = this.computeBackingRowMap();
                map = this.backingRowMap;
            } else {
                map = this.backingRowMap;
            }
            return map;
        }

        Map<C, V> computeBackingRowMap() {
            return StandardTable.this.backingMap.get(this.rowKey);
        }

        void maintainEmptyInvariant() {
            if (this.backingRowMap() != null && this.backingRowMap.isEmpty()) {
                StandardTable.this.backingMap.remove(this.rowKey);
                this.backingRowMap = null;
            }
        }

        @Override
        public boolean containsKey(Object key) {
            Map backingRowMap = this.backingRowMap();
            return key != null && backingRowMap != null && Maps.safeContainsKey(backingRowMap, key);
        }

        @Override
        public V get(Object key) {
            Map backingRowMap = this.backingRowMap();
            return key != null && backingRowMap != null ? (Object)Maps.safeGet(backingRowMap, key) : null;
        }

        @Override
        public V put(C key, V value) {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(value);
            if (this.backingRowMap != null && !this.backingRowMap.isEmpty()) {
                return this.backingRowMap.put(key, value);
            }
            return StandardTable.this.put(this.rowKey, key, value);
        }

        @Override
        public V remove(Object key) {
            Map backingRowMap = this.backingRowMap();
            if (backingRowMap == null) {
                return null;
            }
            Object result = Maps.safeRemove(backingRowMap, key);
            this.maintainEmptyInvariant();
            return result;
        }

        @Override
        public void clear() {
            Map backingRowMap = this.backingRowMap();
            if (backingRowMap != null) {
                backingRowMap.clear();
            }
            this.maintainEmptyInvariant();
        }

        @Override
        public int size() {
            Map map = this.backingRowMap();
            return map == null ? 0 : map.size();
        }

        @Override
        Iterator<Map.Entry<C, V>> entryIterator() {
            Map map = this.backingRowMap();
            if (map == null) {
                return Iterators.emptyModifiableIterator();
            }
            final Iterator iterator = map.entrySet().iterator();
            return new Iterator<Map.Entry<C, V>>(){

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public Map.Entry<C, V> next() {
                    return Row.this.wrapEntry((Map.Entry)iterator.next());
                }

                @Override
                public void remove() {
                    iterator.remove();
                    Row.this.maintainEmptyInvariant();
                }
            };
        }

        @Override
        Spliterator<Map.Entry<C, V>> entrySpliterator() {
            Map map = this.backingRowMap();
            if (map == null) {
                return Spliterators.emptySpliterator();
            }
            return CollectSpliterators.map(map.entrySet().spliterator(), this::wrapEntry);
        }

        Map.Entry<C, V> wrapEntry(final Map.Entry<C, V> entry) {
            return new ForwardingMapEntry<C, V>(){

                @Override
                protected Map.Entry<C, V> delegate() {
                    return entry;
                }

                @Override
                public V setValue(V value) {
                    return super.setValue(Preconditions.checkNotNull(value));
                }

                @Override
                public boolean equals(Object object) {
                    return this.standardEquals(object);
                }
            };
        }
    }

    private class CellIterator
    implements Iterator<Table.Cell<R, C, V>> {
        final Iterator<Map.Entry<R, Map<C, V>>> rowIterator;
        @Nullable Map.Entry<R, Map<C, V>> rowEntry;
        Iterator<Map.Entry<C, V>> columnIterator;

        private CellIterator() {
            this.rowIterator = StandardTable.this.backingMap.entrySet().iterator();
            this.columnIterator = Iterators.emptyModifiableIterator();
        }

        @Override
        public boolean hasNext() {
            return this.rowIterator.hasNext() || this.columnIterator.hasNext();
        }

        @Override
        public Table.Cell<R, C, V> next() {
            if (!this.columnIterator.hasNext()) {
                this.rowEntry = this.rowIterator.next();
                this.columnIterator = this.rowEntry.getValue().entrySet().iterator();
            }
            Map.Entry columnEntry = this.columnIterator.next();
            return Tables.immutableCell(this.rowEntry.getKey(), columnEntry.getKey(), columnEntry.getValue());
        }

        @Override
        public void remove() {
            this.columnIterator.remove();
            if (this.rowEntry.getValue().isEmpty()) {
                this.rowIterator.remove();
                this.rowEntry = null;
            }
        }
    }

    private abstract class TableSet<T>
    extends Sets.ImprovedAbstractSet<T> {
        private TableSet() {
        }

        @Override
        public boolean isEmpty() {
            return StandardTable.this.backingMap.isEmpty();
        }

        @Override
        public void clear() {
            StandardTable.this.backingMap.clear();
        }
    }
}

