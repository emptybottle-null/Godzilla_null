/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashMap;
import com.google.common.collect.ObjectArrays;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

@GwtIncompatible
class CompactLinkedHashMap<K, V>
extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;
    @VisibleForTesting
    transient long @MonotonicNonNull [] links;
    private transient int firstEntry;
    private transient int lastEntry;
    private final boolean accessOrder;

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<K, V>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashMap<K, V>(expectedSize);
    }

    CompactLinkedHashMap() {
        this(3);
    }

    CompactLinkedHashMap(int expectedSize) {
        this(expectedSize, 1.0f, false);
    }

    CompactLinkedHashMap(int expectedSize, float loadFactor, boolean accessOrder) {
        super(expectedSize, loadFactor);
        this.accessOrder = accessOrder;
    }

    @Override
    void init(int expectedSize, float loadFactor) {
        super.init(expectedSize, loadFactor);
        this.firstEntry = -2;
        this.lastEntry = -2;
        this.links = new long[expectedSize];
        Arrays.fill(this.links, -1L);
    }

    private int getPredecessor(int entry) {
        return (int)(this.links[entry] >>> 32);
    }

    @Override
    int getSuccessor(int entry) {
        return (int)this.links[entry];
    }

    private void setSuccessor(int entry, int succ) {
        long succMask = 0xFFFFFFFFL;
        this.links[entry] = this.links[entry] & (succMask ^ 0xFFFFFFFFFFFFFFFFL) | (long)succ & succMask;
    }

    private void setPredecessor(int entry, int pred) {
        long predMask = -4294967296L;
        this.links[entry] = this.links[entry] & (predMask ^ 0xFFFFFFFFFFFFFFFFL) | (long)pred << 32;
    }

    private void setSucceeds(int pred, int succ) {
        if (pred == -2) {
            this.firstEntry = succ;
        } else {
            this.setSuccessor(pred, succ);
        }
        if (succ == -2) {
            this.lastEntry = pred;
        } else {
            this.setPredecessor(succ, pred);
        }
    }

    @Override
    void insertEntry(int entryIndex, K key, V value, int hash) {
        super.insertEntry(entryIndex, key, value, hash);
        this.setSucceeds(this.lastEntry, entryIndex);
        this.setSucceeds(entryIndex, -2);
    }

    @Override
    void accessEntry(int index) {
        if (this.accessOrder) {
            this.setSucceeds(this.getPredecessor(index), this.getSuccessor(index));
            this.setSucceeds(this.lastEntry, index);
            this.setSucceeds(index, -2);
            ++this.modCount;
        }
    }

    @Override
    void moveLastEntry(int dstIndex) {
        int srcIndex = this.size() - 1;
        this.setSucceeds(this.getPredecessor(dstIndex), this.getSuccessor(dstIndex));
        if (dstIndex < srcIndex) {
            this.setSucceeds(this.getPredecessor(srcIndex), dstIndex);
            this.setSucceeds(dstIndex, this.getSuccessor(srcIndex));
        }
        super.moveLastEntry(dstIndex);
    }

    @Override
    void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        this.links = Arrays.copyOf(this.links, newCapacity);
    }

    @Override
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override
    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove >= this.size() ? indexRemoved : indexBeforeRemove;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        int i = this.firstEntry;
        while (i != -2) {
            action.accept(this.keys[i], this.values[i]);
            i = this.getSuccessor(i);
        }
    }

    @Override
    Set<Map.Entry<K, V>> createEntrySet() {
        class EntrySetImpl
        extends CompactHashMap.EntrySetView {
            EntrySetImpl() {
            }

            @Override
            public Spliterator<Map.Entry<K, V>> spliterator() {
                return Spliterators.spliterator(this, 17);
            }
        }
        return new EntrySetImpl();
    }

    @Override
    Set<K> createKeySet() {
        class KeySetImpl
        extends CompactHashMap.KeySetView {
            KeySetImpl() {
            }

            @Override
            public Object[] toArray() {
                return ObjectArrays.toArrayImpl(this);
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return ObjectArrays.toArrayImpl(this, a);
            }

            @Override
            public Spliterator<K> spliterator() {
                return Spliterators.spliterator(this, 17);
            }

            @Override
            public void forEach(Consumer<? super K> action) {
                Preconditions.checkNotNull(action);
                int i = CompactLinkedHashMap.this.firstEntry;
                while (i != -2) {
                    action.accept(CompactLinkedHashMap.this.keys[i]);
                    i = CompactLinkedHashMap.this.getSuccessor(i);
                }
            }
        }
        return new KeySetImpl();
    }

    @Override
    Collection<V> createValues() {
        class ValuesImpl
        extends CompactHashMap.ValuesView {
            ValuesImpl() {
            }

            @Override
            public Object[] toArray() {
                return ObjectArrays.toArrayImpl(this);
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return ObjectArrays.toArrayImpl(this, a);
            }

            @Override
            public Spliterator<V> spliterator() {
                return Spliterators.spliterator(this, 16);
            }

            @Override
            public void forEach(Consumer<? super V> action) {
                Preconditions.checkNotNull(action);
                int i = CompactLinkedHashMap.this.firstEntry;
                while (i != -2) {
                    action.accept(CompactLinkedHashMap.this.values[i]);
                    i = CompactLinkedHashMap.this.getSuccessor(i);
                }
            }
        }
        return new ValuesImpl();
    }

    @Override
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }
}

