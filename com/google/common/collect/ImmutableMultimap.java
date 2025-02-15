/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.BaseImmutableMultimap;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Ordering;
import com.google.common.collect.Platform;
import com.google.common.collect.Serialization;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public abstract class ImmutableMultimap<K, V>
extends BaseImmutableMultimap<K, V>
implements Serializable {
    final transient ImmutableMap<K, ? extends ImmutableCollection<V>> map;
    final transient int size;
    private static final long serialVersionUID = 0L;

    public static <K, V> ImmutableMultimap<K, V> of() {
        return ImmutableListMultimap.of();
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1) {
        return ImmutableListMultimap.of(k1, v1);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2) {
        return ImmutableListMultimap.of(k1, v1, k2, v2);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return ImmutableListMultimap.of(k1, v1, k2, v2, k3, v3);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ImmutableListMultimap.of(k1, v1, k2, v2, k3, v3, k4, v4);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ImmutableListMultimap.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder();
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        ImmutableMultimap kvMultimap;
        if (multimap instanceof ImmutableMultimap && !(kvMultimap = (ImmutableMultimap)multimap).isPartialView()) {
            return kvMultimap;
        }
        return ImmutableListMultimap.copyOf(multimap);
    }

    @Beta
    public static <K, V> ImmutableMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
        return ImmutableListMultimap.copyOf(entries);
    }

    ImmutableMultimap(ImmutableMap<K, ? extends ImmutableCollection<V>> map, int size) {
        this.map = map;
        this.size = size;
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public ImmutableCollection<V> removeAll(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public ImmutableCollection<V> replaceValues(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public abstract ImmutableCollection<V> get(K var1);

    public abstract ImmutableMultimap<V, K> inverse();

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public boolean put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public boolean putAll(K key, Iterable<? extends V> values) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated
    @CanIgnoreReturnValue
    public boolean remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    boolean isPartialView() {
        return this.map.isPartialView();
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return value != null && super.containsValue(value);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableSet<K> keySet() {
        return this.map.keySet();
    }

    @Override
    Set<K> createKeySet() {
        throw new AssertionError((Object)"unreachable");
    }

    @Override
    public ImmutableMap<K, Collection<V>> asMap() {
        return this.map;
    }

    @Override
    Map<K, Collection<V>> createAsMap() {
        throw new AssertionError((Object)"should never be called");
    }

    @Override
    public ImmutableCollection<Map.Entry<K, V>> entries() {
        return (ImmutableCollection)super.entries();
    }

    @Override
    ImmutableCollection<Map.Entry<K, V>> createEntries() {
        return new EntryCollection(this);
    }

    @Override
    UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
        return new UnmodifiableIterator<Map.Entry<K, V>>(){
            final Iterator<? extends Map.Entry<K, ? extends ImmutableCollection<V>>> asMapItr;
            K currentKey;
            Iterator<V> valueItr;
            {
                this.asMapItr = ((ImmutableSet)ImmutableMultimap.this.map.entrySet()).iterator();
                this.currentKey = null;
                this.valueItr = Iterators.emptyIterator();
            }

            @Override
            public boolean hasNext() {
                return this.valueItr.hasNext() || this.asMapItr.hasNext();
            }

            @Override
            public Map.Entry<K, V> next() {
                if (!this.valueItr.hasNext()) {
                    Map.Entry entry = this.asMapItr.next();
                    this.currentKey = entry.getKey();
                    this.valueItr = entry.getValue().iterator();
                }
                return Maps.immutableEntry(this.currentKey, this.valueItr.next());
            }
        };
    }

    @Override
    Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return CollectSpliterators.flatMap(((ImmutableCollection)((Object)((ImmutableMap)this.asMap()).entrySet())).spliterator(), keyToValueCollectionEntry -> {
            Object key = keyToValueCollectionEntry.getKey();
            Collection valueCollection = (Collection)keyToValueCollectionEntry.getValue();
            return CollectSpliterators.map(valueCollection.spliterator(), value -> Maps.immutableEntry(key, value));
        }, 0x40 | (this instanceof SetMultimap ? 1 : 0), this.size());
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        this.asMap().forEach((key, valueCollection) -> valueCollection.forEach((? super T value) -> action.accept((Object)key, (Object)value)));
    }

    @Override
    public ImmutableMultiset<K> keys() {
        return (ImmutableMultiset)super.keys();
    }

    @Override
    ImmutableMultiset<K> createKeys() {
        return new Keys();
    }

    @Override
    public ImmutableCollection<V> values() {
        return (ImmutableCollection)super.values();
    }

    @Override
    ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    @Override
    UnmodifiableIterator<V> valueIterator() {
        return new UnmodifiableIterator<V>(){
            Iterator<? extends ImmutableCollection<V>> valueCollectionItr;
            Iterator<V> valueItr;
            {
                this.valueCollectionItr = ((ImmutableCollection)ImmutableMultimap.this.map.values()).iterator();
                this.valueItr = Iterators.emptyIterator();
            }

            @Override
            public boolean hasNext() {
                return this.valueItr.hasNext() || this.valueCollectionItr.hasNext();
            }

            @Override
            public V next() {
                if (!this.valueItr.hasNext()) {
                    this.valueItr = this.valueCollectionItr.next().iterator();
                }
                return this.valueItr.next();
            }
        };
    }

    private static final class Values<K, V>
    extends ImmutableCollection<V> {
        @Weak
        private final transient ImmutableMultimap<K, V> multimap;
        private static final long serialVersionUID = 0L;

        Values(ImmutableMultimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override
        public boolean contains(@Nullable Object object) {
            return this.multimap.containsValue(object);
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return this.multimap.valueIterator();
        }

        @Override
        @GwtIncompatible
        int copyIntoArray(Object[] dst, int offset) {
            for (ImmutableCollection valueCollection : this.multimap.map.values()) {
                offset = valueCollection.copyIntoArray(dst, offset);
            }
            return offset;
        }

        @Override
        public int size() {
            return this.multimap.size();
        }

        @Override
        boolean isPartialView() {
            return true;
        }
    }

    @GwtIncompatible
    private static final class KeysSerializedForm
    implements Serializable {
        final ImmutableMultimap<?, ?> multimap;

        KeysSerializedForm(ImmutableMultimap<?, ?> multimap) {
            this.multimap = multimap;
        }

        Object readResolve() {
            return this.multimap.keys();
        }
    }

    class Keys
    extends ImmutableMultiset<K> {
        Keys() {
        }

        @Override
        public boolean contains(@Nullable Object object) {
            return ImmutableMultimap.this.containsKey(object);
        }

        @Override
        public int count(@Nullable Object element) {
            Collection values = ImmutableMultimap.this.map.get(element);
            return values == null ? 0 : values.size();
        }

        @Override
        public ImmutableSet<K> elementSet() {
            return ImmutableMultimap.this.keySet();
        }

        @Override
        public int size() {
            return ImmutableMultimap.this.size();
        }

        @Override
        Multiset.Entry<K> getEntry(int index) {
            Map.Entry entry = (Map.Entry)((ImmutableSet)ImmutableMultimap.this.map.entrySet()).asList().get(index);
            return Multisets.immutableEntry(entry.getKey(), ((Collection)entry.getValue()).size());
        }

        @Override
        boolean isPartialView() {
            return true;
        }

        @Override
        @GwtIncompatible
        Object writeReplace() {
            return new KeysSerializedForm(ImmutableMultimap.this);
        }
    }

    private static class EntryCollection<K, V>
    extends ImmutableCollection<Map.Entry<K, V>> {
        @Weak
        final ImmutableMultimap<K, V> multimap;
        private static final long serialVersionUID = 0L;

        EntryCollection(ImmutableMultimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.multimap.entryIterator();
        }

        @Override
        boolean isPartialView() {
            return this.multimap.isPartialView();
        }

        @Override
        public int size() {
            return this.multimap.size();
        }

        @Override
        public boolean contains(Object object) {
            if (object instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)object;
                return this.multimap.containsEntry(entry.getKey(), entry.getValue());
            }
            return false;
        }
    }

    @GwtIncompatible
    static class FieldSettersHolder {
        static final Serialization.FieldSetter<ImmutableMultimap> MAP_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "map");
        static final Serialization.FieldSetter<ImmutableMultimap> SIZE_FIELD_SETTER = Serialization.getFieldSetter(ImmutableMultimap.class, "size");

        FieldSettersHolder() {
        }
    }

    public static class Builder<K, V> {
        Map<K, Collection<V>> builderMap = Platform.preservesInsertionOrderOnPutsMap();
        @MonotonicNonNull Comparator<? super K> keyComparator;
        @MonotonicNonNull Comparator<? super V> valueComparator;

        Collection<V> newMutableValueCollection() {
            return new ArrayList();
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K key, V value) {
            CollectPreconditions.checkEntryNotNull(key, value);
            Collection<V> valueCollection = this.builderMap.get(key);
            if (valueCollection == null) {
                valueCollection = this.newMutableValueCollection();
                this.builderMap.put(key, valueCollection);
            }
            valueCollection.add(value);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return this.put(entry.getKey(), entry.getValue());
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> entries) {
            for (Map.Entry<K, V> entry : entries) {
                this.put(entry);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, Iterable<? extends V> values) {
            if (key == null) {
                throw new NullPointerException("null key in entry: null=" + Iterables.toString(values));
            }
            Collection<V> valueCollection = this.builderMap.get(key);
            if (valueCollection != null) {
                for (V value : values) {
                    CollectPreconditions.checkEntryNotNull(key, value);
                    valueCollection.add(value);
                }
                return this;
            }
            Iterator<V> valuesItr = values.iterator();
            if (!valuesItr.hasNext()) {
                return this;
            }
            valueCollection = this.newMutableValueCollection();
            while (valuesItr.hasNext()) {
                V value = valuesItr.next();
                CollectPreconditions.checkEntryNotNull(key, value);
                valueCollection.add(value);
            }
            this.builderMap.put(key, valueCollection);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K key, V ... values) {
            return this.putAll(key, (Iterable<? extends V>)Arrays.asList(values));
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            for (Map.Entry<K, Collection<V>> entry : multimap.asMap().entrySet()) {
                this.putAll(entry.getKey(), (Iterable)entry.getValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderKeysBy(Comparator<? super K> keyComparator) {
            this.keyComparator = Preconditions.checkNotNull(keyComparator);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderValuesBy(Comparator<? super V> valueComparator) {
            this.valueComparator = Preconditions.checkNotNull(valueComparator);
            return this;
        }

        @CanIgnoreReturnValue
        Builder<K, V> combine(Builder<K, V> other) {
            for (Map.Entry<K, Collection<V>> entry : other.builderMap.entrySet()) {
                this.putAll(entry.getKey(), (Iterable)entry.getValue());
            }
            return this;
        }

        public ImmutableMultimap<K, V> build() {
            Collection<Map.Entry<K, Collection<V>>> mapEntries = this.builderMap.entrySet();
            if (this.keyComparator != null) {
                mapEntries = Ordering.from(this.keyComparator).onKeys().immutableSortedCopy(mapEntries);
            }
            return ImmutableListMultimap.fromMapEntries(mapEntries, this.valueComparator);
        }
    }
}

