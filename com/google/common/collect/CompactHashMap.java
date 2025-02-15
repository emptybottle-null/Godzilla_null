/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapEntry;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.CollectSpliterators;
import com.google.common.collect.Hashing;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
class CompactHashMap<K, V>
extends AbstractMap<K, V>
implements Serializable {
    private static final int MAXIMUM_CAPACITY = 0x40000000;
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    private static final long NEXT_MASK = 0xFFFFFFFFL;
    private static final long HASH_MASK = -4294967296L;
    static final int DEFAULT_SIZE = 3;
    static final int UNSET = -1;
    private transient int @MonotonicNonNull [] table;
    @VisibleForTesting
    transient long @MonotonicNonNull [] entries;
    @VisibleForTesting
    transient Object @MonotonicNonNull [] keys;
    @VisibleForTesting
    transient Object @MonotonicNonNull [] values;
    transient float loadFactor;
    transient int modCount;
    private transient int threshold;
    private transient int size;
    private transient @MonotonicNonNull Set<K> keySetView;
    private transient @MonotonicNonNull Set<Map.Entry<K, V>> entrySetView;
    private transient @MonotonicNonNull Collection<V> valuesView;

    public static <K, V> CompactHashMap<K, V> create() {
        return new CompactHashMap<K, V>();
    }

    public static <K, V> CompactHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactHashMap<K, V>(expectedSize);
    }

    CompactHashMap() {
        this.init(3, 1.0f);
    }

    CompactHashMap(int capacity) {
        this(capacity, 1.0f);
    }

    CompactHashMap(int expectedSize, float loadFactor) {
        this.init(expectedSize, loadFactor);
    }

    void init(int expectedSize, float loadFactor) {
        Preconditions.checkArgument(expectedSize >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(loadFactor > 0.0f, "Illegal load factor");
        int buckets = Hashing.closedTableSize(expectedSize, loadFactor);
        this.table = CompactHashMap.newTable(buckets);
        this.loadFactor = loadFactor;
        this.keys = new Object[expectedSize];
        this.values = new Object[expectedSize];
        this.entries = CompactHashMap.newEntries(expectedSize);
        this.threshold = Math.max(1, (int)((float)buckets * loadFactor));
    }

    private static int[] newTable(int size) {
        int[] array = new int[size];
        Arrays.fill(array, -1);
        return array;
    }

    private static long[] newEntries(int size) {
        long[] array = new long[size];
        Arrays.fill(array, -1L);
        return array;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    private static int getHash(long entry) {
        return (int)(entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int)entry;
    }

    private static long swapNext(long entry, int newNext) {
        return 0xFFFFFFFF00000000L & entry | 0xFFFFFFFFL & (long)newNext;
    }

    void accessEntry(int index) {
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable V put(@Nullable K key, @Nullable V value) {
        long[] entries = this.entries;
        Object[] keys = this.keys;
        Object[] values = this.values;
        int hash = Hashing.smearedHash(key);
        int tableIndex = hash & this.hashTableMask();
        int newEntryIndex = this.size;
        int next = this.table[tableIndex];
        if (next == -1) {
            this.table[tableIndex] = newEntryIndex;
        } else {
            long entry;
            do {
                int last = next;
                entry = entries[next];
                if (CompactHashMap.getHash(entry) != hash || !Objects.equal(key, keys[next])) continue;
                Object oldValue = values[next];
                values[next] = value;
                this.accessEntry(next);
                return (V)oldValue;
            } while ((next = CompactHashMap.getNext(entry)) != -1);
            entries[last] = CompactHashMap.swapNext(entry, newEntryIndex);
        }
        if (newEntryIndex == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int newSize = newEntryIndex + 1;
        this.resizeMeMaybe(newSize);
        this.insertEntry(newEntryIndex, key, value, hash);
        this.size = newSize;
        if (newEntryIndex >= this.threshold) {
            this.resizeTable(2 * this.table.length);
        }
        ++this.modCount;
        return null;
    }

    void insertEntry(int entryIndex, @Nullable K key, @Nullable V value, int hash) {
        this.entries[entryIndex] = (long)hash << 32 | 0xFFFFFFFFL;
        this.keys[entryIndex] = key;
        this.values[entryIndex] = value;
    }

    private void resizeMeMaybe(int newSize) {
        int entriesSize = this.entries.length;
        if (newSize > entriesSize) {
            int newCapacity = entriesSize + Math.max(1, entriesSize >>> 1);
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            if (newCapacity != entriesSize) {
                this.resizeEntries(newCapacity);
            }
        }
    }

    void resizeEntries(int newCapacity) {
        this.keys = Arrays.copyOf(this.keys, newCapacity);
        this.values = Arrays.copyOf(this.values, newCapacity);
        long[] entries = this.entries;
        int oldCapacity = entries.length;
        entries = Arrays.copyOf(entries, newCapacity);
        if (newCapacity > oldCapacity) {
            Arrays.fill(entries, oldCapacity, newCapacity, -1L);
        }
        this.entries = entries;
    }

    private void resizeTable(int newCapacity) {
        int[] oldTable = this.table;
        int oldCapacity = oldTable.length;
        if (oldCapacity >= 0x40000000) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int newThreshold = 1 + (int)((float)newCapacity * this.loadFactor);
        int[] newTable = CompactHashMap.newTable(newCapacity);
        long[] entries = this.entries;
        int mask = newTable.length - 1;
        for (int i = 0; i < this.size; ++i) {
            long oldEntry = entries[i];
            int hash = CompactHashMap.getHash(oldEntry);
            int tableIndex = hash & mask;
            int next = newTable[tableIndex];
            newTable[tableIndex] = i;
            entries[i] = (long)hash << 32 | 0xFFFFFFFFL & (long)next;
        }
        this.threshold = newThreshold;
        this.table = newTable;
    }

    private int indexOf(@Nullable Object key) {
        int hash = Hashing.smearedHash(key);
        int next = this.table[hash & this.hashTableMask()];
        while (next != -1) {
            long entry = this.entries[next];
            if (CompactHashMap.getHash(entry) == hash && Objects.equal(key, this.keys[next])) {
                return next;
            }
            next = CompactHashMap.getNext(entry);
        }
        return -1;
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return this.indexOf(key) != -1;
    }

    @Override
    public V get(@Nullable Object key) {
        int index = this.indexOf(key);
        this.accessEntry(index);
        return (V)(index == -1 ? null : this.values[index]);
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable V remove(@Nullable Object key) {
        return this.remove(key, Hashing.smearedHash(key));
    }

    private @Nullable V remove(@Nullable Object key, int hash) {
        int tableIndex = hash & this.hashTableMask();
        int next = this.table[tableIndex];
        if (next == -1) {
            return null;
        }
        int last = -1;
        do {
            if (CompactHashMap.getHash(this.entries[next]) == hash && Objects.equal(key, this.keys[next])) {
                Object oldValue = this.values[next];
                if (last == -1) {
                    this.table[tableIndex] = CompactHashMap.getNext(this.entries[next]);
                } else {
                    this.entries[last] = CompactHashMap.swapNext(this.entries[last], CompactHashMap.getNext(this.entries[next]));
                }
                this.moveLastEntry(next);
                --this.size;
                ++this.modCount;
                return (V)oldValue;
            }
            last = next;
        } while ((next = CompactHashMap.getNext(this.entries[next])) != -1);
        return null;
    }

    @CanIgnoreReturnValue
    private V removeEntry(int entryIndex) {
        return this.remove(this.keys[entryIndex], CompactHashMap.getHash(this.entries[entryIndex]));
    }

    void moveLastEntry(int dstIndex) {
        int srcIndex = this.size() - 1;
        if (dstIndex < srcIndex) {
            long lastEntry;
            this.keys[dstIndex] = this.keys[srcIndex];
            this.values[dstIndex] = this.values[srcIndex];
            this.keys[srcIndex] = null;
            this.values[srcIndex] = null;
            this.entries[dstIndex] = lastEntry = this.entries[srcIndex];
            this.entries[srcIndex] = -1L;
            int tableIndex = CompactHashMap.getHash(lastEntry) & this.hashTableMask();
            int lastNext = this.table[tableIndex];
            if (lastNext == srcIndex) {
                this.table[tableIndex] = dstIndex;
            } else {
                long entry;
                do {
                    int previous = lastNext;
                } while ((lastNext = CompactHashMap.getNext(entry = this.entries[lastNext])) != srcIndex);
                this.entries[previous] = CompactHashMap.swapNext(entry, dstIndex);
            }
        } else {
            this.keys[dstIndex] = null;
            this.values[dstIndex] = null;
            this.entries[dstIndex] = -1L;
        }
    }

    int firstEntryIndex() {
        return this.isEmpty() ? -1 : 0;
    }

    int getSuccessor(int entryIndex) {
        return entryIndex + 1 < this.size ? entryIndex + 1 : -1;
    }

    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove - 1;
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Preconditions.checkNotNull(function);
        for (int i = 0; i < this.size; ++i) {
            this.values[i] = function.apply(this.keys[i], this.values[i]);
        }
    }

    @Override
    public Set<K> keySet() {
        return this.keySetView == null ? (this.keySetView = this.createKeySet()) : this.keySetView;
    }

    Set<K> createKeySet() {
        return new KeySetView();
    }

    Iterator<K> keySetIterator() {
        return new Itr<K>(){

            @Override
            K getOutput(int entry) {
                return CompactHashMap.this.keys[entry];
            }
        };
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        for (int i = 0; i < this.size; ++i) {
            action.accept(this.keys[i], this.values[i]);
        }
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return this.entrySetView == null ? (this.entrySetView = this.createEntrySet()) : this.entrySetView;
    }

    Set<Map.Entry<K, V>> createEntrySet() {
        return new EntrySetView();
    }

    Iterator<Map.Entry<K, V>> entrySetIterator() {
        return new Itr<Map.Entry<K, V>>(){

            @Override
            Map.Entry<K, V> getOutput(int entry) {
                return new MapEntry(entry);
            }
        };
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        for (int i = 0; i < this.size; ++i) {
            if (!Objects.equal(value, this.values[i])) continue;
            return true;
        }
        return false;
    }

    @Override
    public Collection<V> values() {
        return this.valuesView == null ? (this.valuesView = this.createValues()) : this.valuesView;
    }

    Collection<V> createValues() {
        return new ValuesView();
    }

    Iterator<V> valuesIterator() {
        return new Itr<V>(){

            @Override
            V getOutput(int entry) {
                return CompactHashMap.this.values[entry];
            }
        };
    }

    public void trimToSize() {
        double load;
        int minimumTableSize;
        int size = this.size;
        if (size < this.entries.length) {
            this.resizeEntries(size);
        }
        if ((minimumTableSize = Math.max(1, Integer.highestOneBit((int)((float)size / this.loadFactor)))) < 0x40000000 && (load = (double)size / (double)minimumTableSize) > (double)this.loadFactor) {
            minimumTableSize <<= 1;
        }
        if (minimumTableSize < this.table.length) {
            this.resizeTable(minimumTableSize);
        }
    }

    @Override
    public void clear() {
        ++this.modCount;
        Arrays.fill(this.keys, 0, this.size, null);
        Arrays.fill(this.values, 0, this.size, null);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size);
        for (int i = 0; i < this.size; ++i) {
            stream.writeObject(this.keys[i]);
            stream.writeObject(this.values[i]);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int elementCount;
        stream.defaultReadObject();
        this.init(3, 1.0f);
        int i = elementCount = stream.readInt();
        while (--i >= 0) {
            Object key = stream.readObject();
            Object value = stream.readObject();
            this.put(key, value);
        }
    }

    class ValuesView
    extends Maps.Values<K, V> {
        ValuesView() {
            super(CompactHashMap.this);
        }

        @Override
        public Iterator<V> iterator() {
            return CompactHashMap.this.valuesIterator();
        }

        @Override
        public void forEach(Consumer<? super V> action) {
            Preconditions.checkNotNull(action);
            for (int i = 0; i < CompactHashMap.this.size; ++i) {
                action.accept(CompactHashMap.this.values[i]);
            }
        }

        @Override
        public Spliterator<V> spliterator() {
            return Spliterators.spliterator(CompactHashMap.this.values, 0, CompactHashMap.this.size, 16);
        }

        @Override
        public Object[] toArray() {
            return ObjectArrays.copyAsObjectArray(CompactHashMap.this.values, 0, CompactHashMap.this.size);
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return ObjectArrays.toArrayImpl(CompactHashMap.this.values, 0, CompactHashMap.this.size, a);
        }
    }

    final class MapEntry
    extends AbstractMapEntry<K, V> {
        private final @Nullable K key;
        private int lastKnownIndex;

        MapEntry(int index) {
            this.key = CompactHashMap.this.keys[index];
            this.lastKnownIndex = index;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        private void updateLastKnownIndex() {
            if (this.lastKnownIndex == -1 || this.lastKnownIndex >= CompactHashMap.this.size() || !Objects.equal(this.key, CompactHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
            }
        }

        @Override
        public V getValue() {
            this.updateLastKnownIndex();
            return this.lastKnownIndex == -1 ? null : CompactHashMap.this.values[this.lastKnownIndex];
        }

        @Override
        public V setValue(V value) {
            this.updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                CompactHashMap.this.put(this.key, value);
                return null;
            }
            Object old = CompactHashMap.this.values[this.lastKnownIndex];
            CompactHashMap.this.values[this.lastKnownIndex] = value;
            return old;
        }
    }

    class EntrySetView
    extends Maps.EntrySet<K, V> {
        EntrySetView() {
        }

        @Override
        Map<K, V> map() {
            return CompactHashMap.this;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override
        public Spliterator<Map.Entry<K, V>> spliterator() {
            return CollectSpliterators.indexed(CompactHashMap.this.size, 17, x$0 -> new MapEntry(x$0));
        }

        @Override
        public boolean contains(@Nullable Object o) {
            if (o instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry)o;
                int index = CompactHashMap.this.indexOf(entry.getKey());
                return index != -1 && Objects.equal(CompactHashMap.this.values[index], entry.getValue());
            }
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            Map.Entry entry;
            int index;
            if (o instanceof Map.Entry && (index = CompactHashMap.this.indexOf((entry = (Map.Entry)o).getKey())) != -1 && Objects.equal(CompactHashMap.this.values[index], entry.getValue())) {
                CompactHashMap.this.removeEntry(index);
                return true;
            }
            return false;
        }
    }

    class KeySetView
    extends Maps.KeySet<K, V> {
        KeySetView() {
            super(CompactHashMap.this);
        }

        @Override
        public Object[] toArray() {
            return ObjectArrays.copyAsObjectArray(CompactHashMap.this.keys, 0, CompactHashMap.this.size);
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return ObjectArrays.toArrayImpl(CompactHashMap.this.keys, 0, CompactHashMap.this.size, a);
        }

        @Override
        public boolean remove(@Nullable Object o) {
            int index = CompactHashMap.this.indexOf(o);
            if (index == -1) {
                return false;
            }
            CompactHashMap.this.removeEntry(index);
            return true;
        }

        @Override
        public Iterator<K> iterator() {
            return CompactHashMap.this.keySetIterator();
        }

        @Override
        public Spliterator<K> spliterator() {
            return Spliterators.spliterator(CompactHashMap.this.keys, 0, CompactHashMap.this.size, 17);
        }

        @Override
        public void forEach(Consumer<? super K> action) {
            Preconditions.checkNotNull(action);
            for (int i = 0; i < CompactHashMap.this.size; ++i) {
                action.accept(CompactHashMap.this.keys[i]);
            }
        }
    }

    private abstract class Itr<T>
    implements Iterator<T> {
        int expectedModCount;
        int currentIndex;
        int indexToRemove;

        private Itr() {
            this.expectedModCount = CompactHashMap.this.modCount;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
            this.indexToRemove = -1;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex >= 0;
        }

        abstract T getOutput(int var1);

        @Override
        public T next() {
            this.checkForConcurrentModification();
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.indexToRemove = this.currentIndex;
            T result = this.getOutput(this.currentIndex);
            this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
            return result;
        }

        @Override
        public void remove() {
            this.checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.indexToRemove >= 0);
            ++this.expectedModCount;
            CompactHashMap.this.removeEntry(this.indexToRemove);
            this.currentIndex = CompactHashMap.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
            this.indexToRemove = -1;
        }

        private void checkForConcurrentModification() {
            if (CompactHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

