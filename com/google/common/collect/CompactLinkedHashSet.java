/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.CompactHashSet;
import com.google.common.collect.ObjectArrays;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

@GwtIncompatible
class CompactLinkedHashSet<E>
extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int @MonotonicNonNull [] predecessor;
    private transient int @MonotonicNonNull [] successor;
    private transient int firstEntry;
    private transient int lastEntry;

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<E>();
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> set = CompactLinkedHashSet.createWithExpectedSize(collection.size());
        set.addAll(collection);
        return set;
    }

    public static <E> CompactLinkedHashSet<E> create(E ... elements) {
        CompactLinkedHashSet<E> set = CompactLinkedHashSet.createWithExpectedSize(elements.length);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int expectedSize) {
        return new CompactLinkedHashSet<E>(expectedSize);
    }

    CompactLinkedHashSet() {
    }

    CompactLinkedHashSet(int expectedSize) {
        super(expectedSize);
    }

    @Override
    void init(int expectedSize, float loadFactor) {
        super.init(expectedSize, loadFactor);
        this.predecessor = new int[expectedSize];
        this.successor = new int[expectedSize];
        Arrays.fill(this.predecessor, -1);
        Arrays.fill(this.successor, -1);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    private void succeeds(int pred, int succ) {
        if (pred == -2) {
            this.firstEntry = succ;
        } else {
            this.successor[pred] = succ;
        }
        if (succ == -2) {
            this.lastEntry = pred;
        } else {
            this.predecessor[succ] = pred;
        }
    }

    @Override
    void insertEntry(int entryIndex, E object, int hash) {
        super.insertEntry(entryIndex, object, hash);
        this.succeeds(this.lastEntry, entryIndex);
        this.succeeds(entryIndex, -2);
    }

    @Override
    void moveEntry(int dstIndex) {
        int srcIndex = this.size() - 1;
        super.moveEntry(dstIndex);
        this.succeeds(this.predecessor[dstIndex], this.successor[dstIndex]);
        if (srcIndex != dstIndex) {
            this.succeeds(this.predecessor[srcIndex], dstIndex);
            this.succeeds(dstIndex, this.successor[srcIndex]);
        }
        this.predecessor[srcIndex] = -1;
        this.successor[srcIndex] = -1;
    }

    @Override
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
        Arrays.fill(this.predecessor, -1);
        Arrays.fill(this.successor, -1);
    }

    @Override
    void resizeEntries(int newCapacity) {
        super.resizeEntries(newCapacity);
        int oldCapacity = this.predecessor.length;
        this.predecessor = Arrays.copyOf(this.predecessor, newCapacity);
        this.successor = Arrays.copyOf(this.successor, newCapacity);
        if (oldCapacity < newCapacity) {
            Arrays.fill(this.predecessor, oldCapacity, newCapacity, -1);
            Arrays.fill(this.successor, oldCapacity, newCapacity, -1);
        }
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
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override
    int adjustAfterRemove(int indexBeforeRemove, int indexRemoved) {
        return indexBeforeRemove == this.size() ? indexRemoved : indexBeforeRemove;
    }

    @Override
    int getSuccessor(int entryIndex) {
        return this.successor[entryIndex];
    }

    @Override
    public Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 17);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Preconditions.checkNotNull(action);
        int i = this.firstEntry;
        while (i != -2) {
            action.accept(this.elements[i]);
            i = this.successor[i];
        }
    }
}

