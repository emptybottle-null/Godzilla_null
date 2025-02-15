/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.JdkBackedImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true, serializable=true)
class RegularImmutableMultiset<E>
extends ImmutableMultiset<E> {
    static final ImmutableMultiset<Object> EMPTY = RegularImmutableMultiset.create(ImmutableList.of());
    @VisibleForTesting
    static final double MAX_LOAD_FACTOR = 1.0;
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001;
    @VisibleForTesting
    static final int MAX_HASH_BUCKET_LENGTH = 9;
    private final transient Multisets.ImmutableEntry<E>[] entries;
    private final transient Multisets.ImmutableEntry<E> @Nullable [] hashTable;
    private final transient int size;
    private final transient int hashCode;
    @LazyInit
    private transient ImmutableSet<E> elementSet;

    static <E> ImmutableMultiset<E> create(Collection<? extends Multiset.Entry<? extends E>> entries) {
        int distinct = entries.size();
        Object[] entryArray = new Multisets.ImmutableEntry[distinct];
        if (distinct == 0) {
            return new RegularImmutableMultiset((Multisets.ImmutableEntry<E>[])entryArray, null, 0, 0, ImmutableSet.of());
        }
        int tableSize = Hashing.closedTableSize(distinct, 1.0);
        int mask = tableSize - 1;
        Multisets.ImmutableEntry[] hashTable = new Multisets.ImmutableEntry[tableSize];
        int index = 0;
        int hashCode = 0;
        long size = 0L;
        for (Multiset.Entry<E> entry : entries) {
            Multisets.ImmutableEntry newEntry;
            E element = Preconditions.checkNotNull(entry.getElement());
            int count = entry.getCount();
            int hash = element.hashCode();
            int bucket = Hashing.smear(hash) & mask;
            Multisets.ImmutableEntry bucketHead = hashTable[bucket];
            if (bucketHead == null) {
                boolean canReuseEntry = entry instanceof Multisets.ImmutableEntry && !(entry instanceof NonTerminalEntry);
                newEntry = canReuseEntry ? (Multisets.ImmutableEntry)entry : new Multisets.ImmutableEntry<E>(element, count);
            } else {
                newEntry = new NonTerminalEntry<E>(element, count, bucketHead);
            }
            hashCode += hash ^ count;
            entryArray[index++] = newEntry;
            hashTable[bucket] = newEntry;
            size += (long)count;
        }
        return RegularImmutableMultiset.hashFloodingDetected(hashTable) ? JdkBackedImmutableMultiset.create(ImmutableList.asImmutableList(entryArray)) : new RegularImmutableMultiset<E>((Multisets.ImmutableEntry<E>[])entryArray, hashTable, Ints.saturatedCast(size), hashCode, null);
    }

    private static boolean hashFloodingDetected(Multisets.ImmutableEntry<?>[] hashTable) {
        for (int i = 0; i < hashTable.length; ++i) {
            int bucketLength = 0;
            for (Multisets.ImmutableEntry<?> entry = hashTable[i]; entry != null; entry = entry.nextInBucket()) {
                if (++bucketLength <= 9) continue;
                return true;
            }
        }
        return false;
    }

    private RegularImmutableMultiset(Multisets.ImmutableEntry<E>[] entries, Multisets.ImmutableEntry<E>[] hashTable, int size, int hashCode, ImmutableSet<E> elementSet) {
        this.entries = entries;
        this.hashTable = hashTable;
        this.size = size;
        this.hashCode = hashCode;
        this.elementSet = elementSet;
    }

    @Override
    boolean isPartialView() {
        return false;
    }

    @Override
    public int count(@Nullable Object element) {
        Multisets.ImmutableEntry<E>[] hashTable = this.hashTable;
        if (element == null || hashTable == null) {
            return 0;
        }
        int hash = Hashing.smearedHash(element);
        int mask = hashTable.length - 1;
        for (Multisets.ImmutableEntry<E> entry = hashTable[hash & mask]; entry != null; entry = entry.nextInBucket()) {
            if (!Objects.equal(element, entry.getElement())) continue;
            return entry.getCount();
        }
        return 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableSet<E> elementSet() {
        ImmutableSet<E> result = this.elementSet;
        return result == null ? (this.elementSet = new ImmutableMultiset.ElementSet(Arrays.asList(this.entries), this)) : result;
    }

    @Override
    Multiset.Entry<E> getEntry(int index) {
        return this.entries[index];
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    private static final class NonTerminalEntry<E>
    extends Multisets.ImmutableEntry<E> {
        private final Multisets.ImmutableEntry<E> nextInBucket;

        NonTerminalEntry(E element, int count, Multisets.ImmutableEntry<E> nextInBucket) {
            super(element, count);
            this.nextInBucket = nextInBucket;
        }

        @Override
        public Multisets.ImmutableEntry<E> nextInBucket() {
            return this.nextInBucket;
        }
    }
}

