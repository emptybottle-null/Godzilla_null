/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.IndexedImmutableSet;
import com.google.common.collect.JdkBackedImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Map;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true, emulated=true)
final class RegularImmutableMap<K, V>
extends ImmutableMap<K, V> {
    static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(ImmutableMap.EMPTY_ENTRY_ARRAY, null, 0);
    @VisibleForTesting
    static final double MAX_LOAD_FACTOR = 1.2;
    @VisibleForTesting
    static final double HASH_FLOODING_FPP = 0.001;
    @VisibleForTesting
    static final int MAX_HASH_BUCKET_LENGTH = 8;
    @VisibleForTesting
    final transient Map.Entry<K, V>[] entries;
    private final transient ImmutableMapEntry<K, V>[] table;
    private final transient int mask;
    private static final long serialVersionUID = 0L;

    static <K, V> ImmutableMap<K, V> fromEntries(Map.Entry<K, V> ... entries) {
        return RegularImmutableMap.fromEntryArray(entries.length, entries);
    }

    static <K, V> ImmutableMap<K, V> fromEntryArray(int n, Map.Entry<K, V>[] entryArray) {
        Preconditions.checkPositionIndex(n, entryArray.length);
        if (n == 0) {
            return (RegularImmutableMap)EMPTY;
        }
        Map.Entry<K, V>[] entries = n == entryArray.length ? entryArray : ImmutableMapEntry.createEntryArray(n);
        int tableSize = Hashing.closedTableSize(n, 1.2);
        ImmutableMapEntry<K, V>[] table = ImmutableMapEntry.createEntryArray(tableSize);
        int mask = tableSize - 1;
        for (int entryIndex = 0; entryIndex < n; ++entryIndex) {
            Map.Entry<K, V> entry = entryArray[entryIndex];
            K key = entry.getKey();
            V value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int tableIndex = Hashing.smear(key.hashCode()) & mask;
            ImmutableMapEntry existing = table[tableIndex];
            ImmutableMapEntry newEntry = existing == null ? RegularImmutableMap.makeImmutable(entry, key, value) : new ImmutableMapEntry.NonTerminalImmutableMapEntry<K, V>(key, value, existing);
            table[tableIndex] = newEntry;
            entries[entryIndex] = newEntry;
            int bucketSize = RegularImmutableMap.checkNoConflictInKeyBucket(key, newEntry, existing);
            if (bucketSize <= 8) continue;
            return JdkBackedImmutableMap.create(n, entryArray);
        }
        return new RegularImmutableMap<K, V>(entries, table, mask);
    }

    static <K, V> ImmutableMapEntry<K, V> makeImmutable(Map.Entry<K, V> entry, K key, V value) {
        boolean reusable = entry instanceof ImmutableMapEntry && ((ImmutableMapEntry)entry).isReusable();
        return reusable ? (ImmutableMapEntry)entry : new ImmutableMapEntry<K, V>(key, value);
    }

    static <K, V> ImmutableMapEntry<K, V> makeImmutable(Map.Entry<K, V> entry) {
        return RegularImmutableMap.makeImmutable(entry, entry.getKey(), entry.getValue());
    }

    private RegularImmutableMap(Map.Entry<K, V>[] entries, ImmutableMapEntry<K, V>[] table, int mask) {
        this.entries = entries;
        this.table = table;
        this.mask = mask;
    }

    @CanIgnoreReturnValue
    static int checkNoConflictInKeyBucket(Object key, Map.Entry<?, ?> entry, @Nullable ImmutableMapEntry<?, ?> keyBucketHead) {
        int bucketSize = 0;
        while (keyBucketHead != null) {
            RegularImmutableMap.checkNoConflict(!key.equals(keyBucketHead.getKey()), "key", entry, keyBucketHead);
            ++bucketSize;
            keyBucketHead = keyBucketHead.getNextInKeyBucket();
        }
        return bucketSize;
    }

    @Override
    public V get(@Nullable Object key) {
        return RegularImmutableMap.get(key, this.table, this.mask);
    }

    static <V> @Nullable V get(@Nullable Object key, ImmutableMapEntry<?, V> @Nullable [] keyTable, int mask) {
        if (key == null || keyTable == null) {
            return null;
        }
        int index = Hashing.smear(key.hashCode()) & mask;
        for (ImmutableMapEntry<?, V> entry = keyTable[index]; entry != null; entry = entry.getNextInKeyBucket()) {
            Object candidateKey = entry.getKey();
            if (!key.equals(candidateKey)) continue;
            return entry.getValue();
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        for (Map.Entry<K, V> entry : this.entries) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public int size() {
        return this.entries.length;
    }

    @Override
    boolean isPartialView() {
        return false;
    }

    @Override
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet<K, V>(this, this.entries);
    }

    @Override
    ImmutableSet<K> createKeySet() {
        return new KeySet(this);
    }

    @Override
    ImmutableCollection<V> createValues() {
        return new Values(this);
    }

    @GwtCompatible(emulated=true)
    private static final class Values<K, V>
    extends ImmutableList<V> {
        final RegularImmutableMap<K, V> map;

        Values(RegularImmutableMap<K, V> map) {
            this.map = map;
        }

        @Override
        public V get(int index) {
            return this.map.entries[index].getValue();
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        boolean isPartialView() {
            return true;
        }

        @Override
        @GwtIncompatible
        Object writeReplace() {
            return new SerializedForm<V>(this.map);
        }

        @GwtIncompatible
        private static class SerializedForm<V>
        implements Serializable {
            final ImmutableMap<?, V> map;
            private static final long serialVersionUID = 0L;

            SerializedForm(ImmutableMap<?, V> map) {
                this.map = map;
            }

            Object readResolve() {
                return this.map.values();
            }
        }
    }

    @GwtCompatible(emulated=true)
    private static final class KeySet<K, V>
    extends IndexedImmutableSet<K> {
        private final RegularImmutableMap<K, V> map;

        KeySet(RegularImmutableMap<K, V> map) {
            this.map = map;
        }

        @Override
        K get(int index) {
            return this.map.entries[index].getKey();
        }

        @Override
        public boolean contains(Object object) {
            return this.map.containsKey(object);
        }

        @Override
        boolean isPartialView() {
            return true;
        }

        @Override
        public int size() {
            return this.map.size();
        }

        @Override
        @GwtIncompatible
        Object writeReplace() {
            return new SerializedForm<K>(this.map);
        }

        @GwtIncompatible
        private static class SerializedForm<K>
        implements Serializable {
            final ImmutableMap<K, ?> map;
            private static final long serialVersionUID = 0L;

            SerializedForm(ImmutableMap<K, ?> map) {
                this.map = map;
            }

            Object readResolve() {
                return this.map.keySet();
            }
        }
    }
}

