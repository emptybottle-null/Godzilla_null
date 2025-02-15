/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractMultimap<K, V>
implements Multimap<K, V> {
    private transient @MonotonicNonNull Collection<Map.Entry<K, V>> entries;
    private transient @MonotonicNonNull Set<K> keySet;
    private transient @MonotonicNonNull Multiset<K> keys;
    private transient @MonotonicNonNull Collection<V> values;
    private transient @MonotonicNonNull Map<K, Collection<V>> asMap;

    AbstractMultimap() {
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        for (Collection<V> collection : this.asMap().values()) {
            if (!collection.contains(value)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsEntry(@Nullable Object key, @Nullable Object value) {
        Collection<V> collection = this.asMap().get(key);
        return collection != null && collection.contains(value);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object key, @Nullable Object value) {
        Collection<V> collection = this.asMap().get(key);
        return collection != null && collection.remove(value);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean put(@Nullable K key, @Nullable V value) {
        return this.get(key).add(value);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean putAll(@Nullable K key, Iterable<? extends V> values) {
        Preconditions.checkNotNull(values);
        if (values instanceof Collection) {
            Collection valueCollection = (Collection)values;
            return !valueCollection.isEmpty() && this.get(key).addAll(valueCollection);
        }
        Iterator<V> valueItr = values.iterator();
        return valueItr.hasNext() && Iterators.addAll(this.get(key), valueItr);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        boolean changed = false;
        for (Map.Entry<K, V> entry : multimap.entries()) {
            changed |= this.put(entry.getKey(), entry.getValue());
        }
        return changed;
    }

    @Override
    @CanIgnoreReturnValue
    public Collection<V> replaceValues(@Nullable K key, Iterable<? extends V> values) {
        Preconditions.checkNotNull(values);
        Collection result = this.removeAll(key);
        this.putAll(key, values);
        return result;
    }

    @Override
    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> result = this.entries;
        return result == null ? (this.entries = this.createEntries()) : result;
    }

    abstract Collection<Map.Entry<K, V>> createEntries();

    abstract Iterator<Map.Entry<K, V>> entryIterator();

    Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return Spliterators.spliterator(this.entryIterator(), (long)this.size(), this instanceof SetMultimap ? 1 : 0);
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = this.keySet;
        return result == null ? (this.keySet = this.createKeySet()) : result;
    }

    abstract Set<K> createKeySet();

    @Override
    public Multiset<K> keys() {
        Multiset<K> result = this.keys;
        return result == null ? (this.keys = this.createKeys()) : result;
    }

    abstract Multiset<K> createKeys();

    @Override
    public Collection<V> values() {
        Collection<V> result = this.values;
        return result == null ? (this.values = this.createValues()) : result;
    }

    abstract Collection<V> createValues();

    Iterator<V> valueIterator() {
        return Maps.valueIterator(this.entries().iterator());
    }

    Spliterator<V> valueSpliterator() {
        return Spliterators.spliterator(this.valueIterator(), (long)this.size(), 0);
    }

    @Override
    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<Collection<V>>> result = this.asMap;
        return result == null ? (this.asMap = this.createAsMap()) : result;
    }

    abstract Map<K, Collection<V>> createAsMap();

    @Override
    public boolean equals(@Nullable Object object) {
        return Multimaps.equalsImpl(this, object);
    }

    @Override
    public int hashCode() {
        return this.asMap().hashCode();
    }

    public String toString() {
        return this.asMap().toString();
    }

    class Values
    extends AbstractCollection<V> {
        Values() {
        }

        @Override
        public Iterator<V> iterator() {
            return AbstractMultimap.this.valueIterator();
        }

        @Override
        public Spliterator<V> spliterator() {
            return AbstractMultimap.this.valueSpliterator();
        }

        @Override
        public int size() {
            return AbstractMultimap.this.size();
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return AbstractMultimap.this.containsValue(o);
        }

        @Override
        public void clear() {
            AbstractMultimap.this.clear();
        }
    }

    class EntrySet
    extends Entries
    implements Set<Map.Entry<K, V>> {
        EntrySet() {
        }

        @Override
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return Sets.equalsImpl(this, obj);
        }
    }

    class Entries
    extends Multimaps.Entries<K, V> {
        Entries() {
        }

        @Override
        Multimap<K, V> multimap() {
            return AbstractMultimap.this;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractMultimap.this.entryIterator();
        }

        @Override
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return AbstractMultimap.this.entrySpliterator();
        }
    }
}

