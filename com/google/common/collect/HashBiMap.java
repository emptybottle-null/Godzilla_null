/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.BiMap;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.Maps;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class HashBiMap<K, V>
extends Maps.IteratorBasedAbstractMap<K, V>
implements BiMap<K, V>,
Serializable {
    private static final double LOAD_FACTOR = 1.0;
    private transient BiEntry<K, V>[] hashTableKToV;
    private transient BiEntry<K, V>[] hashTableVToK;
    private transient @Nullable BiEntry<K, V> firstInKeyInsertionOrder;
    private transient @Nullable BiEntry<K, V> lastInKeyInsertionOrder;
    private transient int size;
    private transient int mask;
    private transient int modCount;
    @RetainedWith
    private transient @MonotonicNonNull BiMap<V, K> inverse;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <K, V> HashBiMap<K, V> create() {
        return HashBiMap.create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int expectedSize) {
        return new HashBiMap<K, V>(expectedSize);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> bimap = HashBiMap.create(map.size());
        bimap.putAll(map);
        return bimap;
    }

    private HashBiMap(int expectedSize) {
        this.init(expectedSize);
    }

    private void init(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        int tableSize = Hashing.closedTableSize(expectedSize, 1.0);
        this.hashTableKToV = this.createTable(tableSize);
        this.hashTableVToK = this.createTable(tableSize);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.size = 0;
        this.mask = tableSize - 1;
        this.modCount = 0;
    }

    private void delete(BiEntry<K, V> entry) {
        int keyBucket = entry.keyHash & this.mask;
        BiEntry<K, V> prevBucketEntry = null;
        BiEntry<K, V> bucketEntry = this.hashTableKToV[keyBucket];
        while (true) {
            if (bucketEntry == entry) {
                if (prevBucketEntry == null) {
                    this.hashTableKToV[keyBucket] = entry.nextInKToVBucket;
                    break;
                }
                prevBucketEntry.nextInKToVBucket = entry.nextInKToVBucket;
                break;
            }
            prevBucketEntry = bucketEntry;
            bucketEntry = bucketEntry.nextInKToVBucket;
        }
        int valueBucket = entry.valueHash & this.mask;
        prevBucketEntry = null;
        BiEntry<K, V> bucketEntry2 = this.hashTableVToK[valueBucket];
        while (true) {
            if (bucketEntry2 == entry) {
                if (prevBucketEntry == null) {
                    this.hashTableVToK[valueBucket] = entry.nextInVToKBucket;
                    break;
                }
                prevBucketEntry.nextInVToKBucket = entry.nextInVToKBucket;
                break;
            }
            prevBucketEntry = bucketEntry2;
            bucketEntry2 = bucketEntry2.nextInVToKBucket;
        }
        if (entry.prevInKeyInsertionOrder == null) {
            this.firstInKeyInsertionOrder = entry.nextInKeyInsertionOrder;
        } else {
            entry.prevInKeyInsertionOrder.nextInKeyInsertionOrder = entry.nextInKeyInsertionOrder;
        }
        if (entry.nextInKeyInsertionOrder == null) {
            this.lastInKeyInsertionOrder = entry.prevInKeyInsertionOrder;
        } else {
            entry.nextInKeyInsertionOrder.prevInKeyInsertionOrder = entry.prevInKeyInsertionOrder;
        }
        --this.size;
        ++this.modCount;
    }

    private void insert(BiEntry<K, V> entry, @Nullable BiEntry<K, V> oldEntryForKey) {
        int keyBucket = entry.keyHash & this.mask;
        entry.nextInKToVBucket = this.hashTableKToV[keyBucket];
        this.hashTableKToV[keyBucket] = entry;
        int valueBucket = entry.valueHash & this.mask;
        entry.nextInVToKBucket = this.hashTableVToK[valueBucket];
        this.hashTableVToK[valueBucket] = entry;
        if (oldEntryForKey == null) {
            entry.prevInKeyInsertionOrder = this.lastInKeyInsertionOrder;
            entry.nextInKeyInsertionOrder = null;
            if (this.lastInKeyInsertionOrder == null) {
                this.firstInKeyInsertionOrder = entry;
            } else {
                this.lastInKeyInsertionOrder.nextInKeyInsertionOrder = entry;
            }
            this.lastInKeyInsertionOrder = entry;
        } else {
            entry.prevInKeyInsertionOrder = oldEntryForKey.prevInKeyInsertionOrder;
            if (entry.prevInKeyInsertionOrder == null) {
                this.firstInKeyInsertionOrder = entry;
            } else {
                entry.prevInKeyInsertionOrder.nextInKeyInsertionOrder = entry;
            }
            entry.nextInKeyInsertionOrder = oldEntryForKey.nextInKeyInsertionOrder;
            if (entry.nextInKeyInsertionOrder == null) {
                this.lastInKeyInsertionOrder = entry;
            } else {
                entry.nextInKeyInsertionOrder.prevInKeyInsertionOrder = entry;
            }
        }
        ++this.size;
        ++this.modCount;
    }

    private BiEntry<K, V> seekByKey(@Nullable Object key, int keyHash) {
        BiEntry<K, V> entry = this.hashTableKToV[keyHash & this.mask];
        while (entry != null) {
            if (keyHash == entry.keyHash && Objects.equal(key, entry.key)) {
                return entry;
            }
            entry = entry.nextInKToVBucket;
        }
        return null;
    }

    private BiEntry<K, V> seekByValue(@Nullable Object value, int valueHash) {
        BiEntry<K, V> entry = this.hashTableVToK[valueHash & this.mask];
        while (entry != null) {
            if (valueHash == entry.valueHash && Objects.equal(value, entry.value)) {
                return entry;
            }
            entry = entry.nextInVToKBucket;
        }
        return null;
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return this.seekByKey(key, Hashing.smearedHash(key)) != null;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return this.seekByValue(value, Hashing.smearedHash(value)) != null;
    }

    @Override
    public @Nullable V get(@Nullable Object key) {
        return Maps.valueOrNull(this.seekByKey(key, Hashing.smearedHash(key)));
    }

    @Override
    @CanIgnoreReturnValue
    public V put(@Nullable K key, @Nullable V value) {
        return this.put(key, value, false);
    }

    private V put(@Nullable K key, @Nullable V value, boolean force) {
        int keyHash = Hashing.smearedHash(key);
        int valueHash = Hashing.smearedHash(value);
        BiEntry<K, V> oldEntryForKey = this.seekByKey(key, keyHash);
        if (oldEntryForKey != null && valueHash == oldEntryForKey.valueHash && Objects.equal(value, oldEntryForKey.value)) {
            return value;
        }
        BiEntry<K, V> oldEntryForValue = this.seekByValue(value, valueHash);
        if (oldEntryForValue != null) {
            if (force) {
                this.delete(oldEntryForValue);
            } else {
                throw new IllegalArgumentException("value already present: " + value);
            }
        }
        BiEntry<K, V> newEntry = new BiEntry<K, V>(key, keyHash, value, valueHash);
        if (oldEntryForKey != null) {
            this.delete(oldEntryForKey);
            this.insert(newEntry, oldEntryForKey);
            oldEntryForKey.prevInKeyInsertionOrder = null;
            oldEntryForKey.nextInKeyInsertionOrder = null;
            return (V)oldEntryForKey.value;
        }
        this.insert(newEntry, null);
        this.rehashIfNecessary();
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable V forcePut(@Nullable K key, @Nullable V value) {
        return this.put(key, value, true);
    }

    private @Nullable K putInverse(@Nullable V value, @Nullable K key, boolean force) {
        int valueHash = Hashing.smearedHash(value);
        int keyHash = Hashing.smearedHash(key);
        BiEntry<K, V> oldEntryForValue = this.seekByValue(value, valueHash);
        BiEntry<K, V> oldEntryForKey = this.seekByKey(key, keyHash);
        if (oldEntryForValue != null && keyHash == oldEntryForValue.keyHash && Objects.equal(key, oldEntryForValue.key)) {
            return key;
        }
        if (oldEntryForKey != null && !force) {
            throw new IllegalArgumentException("key already present: " + key);
        }
        if (oldEntryForValue != null) {
            this.delete(oldEntryForValue);
        }
        if (oldEntryForKey != null) {
            this.delete(oldEntryForKey);
        }
        BiEntry<K, V> newEntry = new BiEntry<K, V>(key, keyHash, value, valueHash);
        this.insert(newEntry, oldEntryForKey);
        if (oldEntryForKey != null) {
            oldEntryForKey.prevInKeyInsertionOrder = null;
            oldEntryForKey.nextInKeyInsertionOrder = null;
        }
        if (oldEntryForValue != null) {
            oldEntryForValue.prevInKeyInsertionOrder = null;
            oldEntryForValue.nextInKeyInsertionOrder = null;
        }
        this.rehashIfNecessary();
        return Maps.keyOrNull(oldEntryForValue);
    }

    private void rehashIfNecessary() {
        BiEntry<K, V>[] oldKToV = this.hashTableKToV;
        if (Hashing.needsResizing(this.size, oldKToV.length, 1.0)) {
            int newTableSize = oldKToV.length * 2;
            this.hashTableKToV = this.createTable(newTableSize);
            this.hashTableVToK = this.createTable(newTableSize);
            this.mask = newTableSize - 1;
            this.size = 0;
            BiEntry<K, V> entry = this.firstInKeyInsertionOrder;
            while (entry != null) {
                this.insert(entry, entry);
                entry = entry.nextInKeyInsertionOrder;
            }
            ++this.modCount;
        }
    }

    private BiEntry<K, V>[] createTable(int length) {
        return new BiEntry[length];
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable V remove(@Nullable Object key) {
        BiEntry<K, V> entry = this.seekByKey(key, Hashing.smearedHash(key));
        if (entry == null) {
            return null;
        }
        this.delete(entry);
        entry.prevInKeyInsertionOrder = null;
        entry.nextInKeyInsertionOrder = null;
        return (V)entry.value;
    }

    @Override
    public void clear() {
        this.size = 0;
        Arrays.fill(this.hashTableKToV, null);
        Arrays.fill(this.hashTableVToK, null);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        ++this.modCount;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }

    @Override
    public Set<V> values() {
        return this.inverse().keySet();
    }

    @Override
    Iterator<Map.Entry<K, V>> entryIterator() {
        return new Itr<Map.Entry<K, V>>(){

            @Override
            Map.Entry<K, V> output(BiEntry<K, V> entry) {
                return new MapEntry(entry);
            }

            class MapEntry
            extends AbstractMapEntry<K, V> {
                BiEntry<K, V> delegate;

                MapEntry(BiEntry<K, V> entry) {
                    this.delegate = entry;
                }

                @Override
                public K getKey() {
                    return this.delegate.key;
                }

                @Override
                public V getValue() {
                    return this.delegate.value;
                }

                @Override
                public V setValue(V value) {
                    Object oldValue = this.delegate.value;
                    int valueHash = Hashing.smearedHash(value);
                    if (valueHash == this.delegate.valueHash && Objects.equal(value, oldValue)) {
                        return value;
                    }
                    Preconditions.checkArgument(HashBiMap.this.seekByValue(value, valueHash) == null, "value already present: %s", value);
                    HashBiMap.this.delete(this.delegate);
                    BiEntry newEntry = new BiEntry(this.delegate.key, this.delegate.keyHash, value, valueHash);
                    HashBiMap.this.insert(newEntry, this.delegate);
                    this.delegate.prevInKeyInsertionOrder = null;
                    this.delegate.nextInKeyInsertionOrder = null;
                    expectedModCount = HashBiMap.this.modCount;
                    if (toRemove == this.delegate) {
                        toRemove = newEntry;
                    }
                    this.delegate = newEntry;
                    return oldValue;
                }
            }
        };
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        BiEntry<K, V> entry = this.firstInKeyInsertionOrder;
        while (entry != null) {
            action.accept(entry.key, entry.value);
            entry = entry.nextInKeyInsertionOrder;
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(function);
        BiEntry<K, V> oldFirst = this.firstInKeyInsertionOrder;
        this.clear();
        BiEntry<K, V> entry = oldFirst;
        while (entry != null) {
            this.put(entry.key, function.apply(entry.key, entry.value));
            entry = entry.nextInKeyInsertionOrder;
        }
    }

    @Override
    public BiMap<V, K> inverse() {
        Inverse result = this.inverse;
        return result == null ? (this.inverse = new Inverse()) : result;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int size = Serialization.readCount(stream);
        this.init(16);
        Serialization.populateMap(this, stream, size);
    }

    private static final class InverseSerializedForm<K, V>
    implements Serializable {
        private final HashBiMap<K, V> bimap;

        InverseSerializedForm(HashBiMap<K, V> bimap) {
            this.bimap = bimap;
        }

        Object readResolve() {
            return this.bimap.inverse();
        }
    }

    private final class Inverse
    extends Maps.IteratorBasedAbstractMap<V, K>
    implements BiMap<V, K>,
    Serializable {
        private Inverse() {
        }

        BiMap<K, V> forward() {
            return HashBiMap.this;
        }

        @Override
        public int size() {
            return HashBiMap.this.size;
        }

        @Override
        public void clear() {
            this.forward().clear();
        }

        @Override
        public boolean containsKey(@Nullable Object value) {
            return this.forward().containsValue(value);
        }

        @Override
        public K get(@Nullable Object value) {
            return Maps.keyOrNull(HashBiMap.this.seekByValue(value, Hashing.smearedHash(value)));
        }

        @Override
        @CanIgnoreReturnValue
        public @Nullable K put(@Nullable V value, @Nullable K key) {
            return HashBiMap.this.putInverse(value, key, false);
        }

        @Override
        public @Nullable K forcePut(@Nullable V value, @Nullable K key) {
            return HashBiMap.this.putInverse(value, key, true);
        }

        @Override
        public @Nullable K remove(@Nullable Object value) {
            BiEntry entry = HashBiMap.this.seekByValue(value, Hashing.smearedHash(value));
            if (entry == null) {
                return null;
            }
            HashBiMap.this.delete(entry);
            entry.prevInKeyInsertionOrder = null;
            entry.nextInKeyInsertionOrder = null;
            return entry.key;
        }

        @Override
        public BiMap<K, V> inverse() {
            return this.forward();
        }

        @Override
        public Set<V> keySet() {
            return new InverseKeySet();
        }

        @Override
        public Set<K> values() {
            return this.forward().keySet();
        }

        @Override
        Iterator<Map.Entry<V, K>> entryIterator() {
            return new Itr<Map.Entry<V, K>>(){

                @Override
                Map.Entry<V, K> output(BiEntry<K, V> entry) {
                    return new InverseEntry(entry);
                }

                class InverseEntry
                extends AbstractMapEntry<V, K> {
                    BiEntry<K, V> delegate;

                    InverseEntry(BiEntry<K, V> entry) {
                        this.delegate = entry;
                    }

                    @Override
                    public V getKey() {
                        return this.delegate.value;
                    }

                    @Override
                    public K getValue() {
                        return this.delegate.key;
                    }

                    @Override
                    public K setValue(K key) {
                        Object oldKey = this.delegate.key;
                        int keyHash = Hashing.smearedHash(key);
                        if (keyHash == this.delegate.keyHash && Objects.equal(key, oldKey)) {
                            return key;
                        }
                        Preconditions.checkArgument(HashBiMap.this.seekByKey(key, keyHash) == null, "value already present: %s", key);
                        HashBiMap.this.delete(this.delegate);
                        BiEntry newEntry = new BiEntry(key, keyHash, this.delegate.value, this.delegate.valueHash);
                        this.delegate = newEntry;
                        HashBiMap.this.insert(newEntry, null);
                        expectedModCount = HashBiMap.this.modCount;
                        return oldKey;
                    }
                }
            };
        }

        @Override
        public void forEach(BiConsumer<? super V, ? super K> action) {
            Preconditions.checkNotNull(action);
            HashBiMap.this.forEach((? super K k, ? super V v) -> action.accept((Object)v, (Object)k));
        }

        @Override
        public void replaceAll(BiFunction<? super V, ? super K, ? extends K> function) {
            Preconditions.checkNotNull(function);
            BiEntry oldFirst = HashBiMap.this.firstInKeyInsertionOrder;
            this.clear();
            BiEntry entry = oldFirst;
            while (entry != null) {
                this.put((V)entry.value, (K)function.apply(entry.value, entry.key));
                entry = entry.nextInKeyInsertionOrder;
            }
        }

        Object writeReplace() {
            return new InverseSerializedForm(HashBiMap.this);
        }

        private final class InverseKeySet
        extends Maps.KeySet<V, K> {
            InverseKeySet() {
                super(Inverse.this);
            }

            @Override
            public boolean remove(@Nullable Object o) {
                BiEntry entry = HashBiMap.this.seekByValue(o, Hashing.smearedHash(o));
                if (entry == null) {
                    return false;
                }
                HashBiMap.this.delete(entry);
                return true;
            }

            @Override
            public Iterator<V> iterator() {
                return new Itr<V>(){

                    @Override
                    V output(BiEntry<K, V> entry) {
                        return entry.value;
                    }
                };
            }
        }
    }

    private final class KeySet
    extends Maps.KeySet<K, V> {
        KeySet() {
            super(HashBiMap.this);
        }

        @Override
        public Iterator<K> iterator() {
            return new Itr<K>(){

                @Override
                K output(BiEntry<K, V> entry) {
                    return entry.key;
                }
            };
        }

        @Override
        public boolean remove(@Nullable Object o) {
            BiEntry entry = HashBiMap.this.seekByKey(o, Hashing.smearedHash(o));
            if (entry == null) {
                return false;
            }
            HashBiMap.this.delete(entry);
            entry.prevInKeyInsertionOrder = null;
            entry.nextInKeyInsertionOrder = null;
            return true;
        }
    }

    abstract class Itr<T>
    implements Iterator<T> {
        BiEntry<K, V> next;
        BiEntry<K, V> toRemove;
        int expectedModCount;
        int remaining;

        Itr() {
            this.next = HashBiMap.this.firstInKeyInsertionOrder;
            this.toRemove = null;
            this.expectedModCount = HashBiMap.this.modCount;
            this.remaining = HashBiMap.this.size();
        }

        @Override
        public boolean hasNext() {
            if (HashBiMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return this.next != null && this.remaining > 0;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            BiEntry entry = this.next;
            this.next = entry.nextInKeyInsertionOrder;
            this.toRemove = entry;
            --this.remaining;
            return this.output(entry);
        }

        @Override
        public void remove() {
            if (HashBiMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
            CollectPreconditions.checkRemove(this.toRemove != null);
            HashBiMap.this.delete(this.toRemove);
            this.expectedModCount = HashBiMap.this.modCount;
            this.toRemove = null;
        }

        abstract T output(BiEntry<K, V> var1);
    }

    private static final class BiEntry<K, V>
    extends ImmutableEntry<K, V> {
        final int keyHash;
        final int valueHash;
        @Nullable BiEntry<K, V> nextInKToVBucket;
        @Nullable BiEntry<K, V> nextInVToKBucket;
        @Nullable BiEntry<K, V> nextInKeyInsertionOrder;
        @Nullable BiEntry<K, V> prevInKeyInsertionOrder;

        BiEntry(K key, int keyHash, V value, int valueHash) {
            super(key, value);
            this.keyHash = keyHash;
            this.valueHash = valueHash;
        }
    }
}

