/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Hashing;
import com.google.common.collect.ObjectArrays;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
class CompactHashSet<E>
extends AbstractSet<E>
implements Serializable {
    private static final int MAXIMUM_CAPACITY = 0x40000000;
    private static final float DEFAULT_LOAD_FACTOR = 1.0f;
    private static final long NEXT_MASK = 0xFFFFFFFFL;
    private static final long HASH_MASK = -4294967296L;
    private static final int DEFAULT_SIZE = 3;
    static final int UNSET = -1;
    private transient int @MonotonicNonNull [] table;
    private transient long @MonotonicNonNull [] entries;
    transient Object @MonotonicNonNull [] elements;
    transient float loadFactor;
    transient int modCount;
    private transient int threshold;
    private transient int size;

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<E>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> set = CompactHashSet.createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    public static <E> CompactHashSet<E> create(E ... elements) {
        CompactHashSet<E> set = CompactHashSet.createWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactHashSet<E>(expectedSize);
    }

    CompactHashSet() {
        this.init(3, 1.0f);
    }

    CompactHashSet(int expectedSize) {
        this.init(expectedSize, 1.0f);
    }

    void init(int expectedSize, float loadFactor) {
        Preconditions.checkArgument(expectedSize >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(loadFactor > 0.0f, "Illegal load factor");
        int buckets = Hashing.closedTableSize(expectedSize, loadFactor);
        this.table = CompactHashSet.newTable(buckets);
        this.loadFactor = loadFactor;
        this.elements = new Object[expectedSize];
        this.entries = CompactHashSet.newEntries(expectedSize);
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

    private static int getHash(long entry) {
        return (int)(entry >>> 32);
    }

    private static int getNext(long entry) {
        return (int)entry;
    }

    private static long swapNext(long entry, int newNext) {
        return 0xFFFFFFFF00000000L & entry | 0xFFFFFFFFL & (long)newNext;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nullable E object) {
        long[] entries = this.entries;
        Object[] elements = this.elements;
        int hash = Hashing.smearedHash(object);
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
                if (CompactHashSet.getHash(entry) != hash || !Objects.equal(object, elements[next])) continue;
                return false;
            } while ((next = CompactHashSet.getNext(entry)) != -1);
            entries[last] = CompactHashSet.swapNext(entry, newEntryIndex);
        }
        if (newEntryIndex == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int newSize = newEntryIndex + 1;
        this.resizeMeMaybe(newSize);
        this.insertEntry(newEntryIndex, object, hash);
        this.size = newSize;
        if (newEntryIndex >= this.threshold) {
            this.resizeTable(2 * this.table.length);
        }
        ++this.modCount;
        return true;
    }

    void insertEntry(int entryIndex, E object, int hash) {
        this.entries[entryIndex] = (long)hash << 32 | 0xFFFFFFFFL;
        this.elements[entryIndex] = object;
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
        this.elements = Arrays.copyOf(this.elements, newCapacity);
        long[] entries = this.entries;
        int oldSize = entries.length;
        entries = Arrays.copyOf(entries, newCapacity);
        if (newCapacity > oldSize) {
            Arrays.fill(entries, oldSize, newCapacity, -1L);
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
        int[] newTable = CompactHashSet.newTable(newCapacity);
        long[] entries = this.entries;
        int mask = newTable.length - 1;
        for (int i = 0; i < this.size; ++i) {
            long oldEntry = entries[i];
            int hash = CompactHashSet.getHash(oldEntry);
            int tableIndex = hash & mask;
            int next = newTable[tableIndex];
            newTable[tableIndex] = i;
            entries[i] = (long)hash << 32 | 0xFFFFFFFFL & (long)next;
        }
        this.threshold = newThreshold;
        this.table = newTable;
    }

    @Override
    public boolean contains(@Nullable Object object) {
        int hash = Hashing.smearedHash(object);
        int next = this.table[hash & this.hashTableMask()];
        while (next != -1) {
            long entry = this.entries[next];
            if (CompactHashSet.getHash(entry) == hash && Objects.equal(object, this.elements[next])) {
                return true;
            }
            next = CompactHashSet.getNext(entry);
        }
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object object) {
        return this.remove(object, Hashing.smearedHash(object));
    }

    @CanIgnoreReturnValue
    private boolean remove(Object object, int hash) {
        int tableIndex = hash & this.hashTableMask();
        int next = this.table[tableIndex];
        if (next == -1) {
            return false;
        }
        int last = -1;
        do {
            if (CompactHashSet.getHash(this.entries[next]) == hash && Objects.equal(object, this.elements[next])) {
                if (last == -1) {
                    this.table[tableIndex] = CompactHashSet.getNext(this.entries[next]);
                } else {
                    this.entries[last] = CompactHashSet.swapNext(this.entries[last], CompactHashSet.getNext(this.entries[next]));
                }
                this.moveEntry(next);
                --this.size;
                ++this.modCount;
                return true;
            }
            last = next;
        } while ((next = CompactHashSet.getNext(this.entries[next])) != -1);
        return false;
    }

    void moveEntry(int dstIndex) {
        int srcIndex = this.size() - 1;
        if (dstIndex < srcIndex) {
            long lastEntry;
            this.elements[dstIndex] = this.elements[srcIndex];
            this.elements[srcIndex] = null;
            this.entries[dstIndex] = lastEntry = this.entries[srcIndex];
            this.entries[srcIndex] = -1L;
            int tableIndex = CompactHashSet.getHash(lastEntry) & this.hashTableMask();
            int lastNext = this.table[tableIndex];
            if (lastNext == srcIndex) {
                this.table[tableIndex] = dstIndex;
            } else {
                long entry;
                do {
                    int previous = lastNext;
                } while ((lastNext = CompactHashSet.getNext(entry = this.entries[lastNext])) != srcIndex);
                this.entries[previous] = CompactHashSet.swapNext(entry, dstIndex);
            }
        } else {
            this.elements[dstIndex] = null;
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
    public Iterator<E> iterator() {
        return new Iterator<E>(){
            int expectedModCount;
            int index;
            int indexToRemove;
            {
                this.expectedModCount = CompactHashSet.this.modCount;
                this.index = CompactHashSet.this.firstEntryIndex();
                this.indexToRemove = -1;
            }

            @Override
            public boolean hasNext() {
                return this.index >= 0;
            }

            @Override
            public E next() {
                this.checkForConcurrentModification();
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.indexToRemove = this.index;
                Object result = CompactHashSet.this.elements[this.index];
                this.index = CompactHashSet.this.getSuccessor(this.index);
                return result;
            }

            @Override
            public void remove() {
                this.checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                ++this.expectedModCount;
                CompactHashSet.this.remove(CompactHashSet.this.elements[this.indexToRemove], CompactHashSet.getHash(CompactHashSet.this.entries[this.indexToRemove]));
                this.index = CompactHashSet.this.adjustAfterRemove(this.index, this.indexToRemove);
                this.indexToRemove = -1;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this.elements, 0, this.size, 17);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Preconditions.checkNotNull(action);
        for (int i = 0; i < this.size; ++i) {
            action.accept(this.elements[i]);
        }
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
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    @Override
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] a) {
        return ObjectArrays.toArrayImpl(this.elements, 0, this.size, a);
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
        Arrays.fill(this.elements, 0, this.size, null);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(this.size);
        for (E e : this) {
            stream.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        int elementCount;
        stream.defaultReadObject();
        this.init(3, 1.0f);
        int i = elementCount = stream.readInt();
        while (--i >= 0) {
            Object element = stream.readObject();
            this.add(element);
        }
    }
}

