/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.common.collect.ImmutableMapKeySet;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.RegularImmutableMap;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class JdkBackedImmutableBiMap<K, V>
extends ImmutableBiMap<K, V> {
    private final transient ImmutableList<Map.Entry<K, V>> entries;
    private final Map<K, V> forwardDelegate;
    private final Map<V, K> backwardDelegate;
    @LazyInit
    @RetainedWith
    private transient JdkBackedImmutableBiMap<V, K> inverse;

    @VisibleForTesting
    static <K, V> ImmutableBiMap<K, V> create(int n, Map.Entry<K, V>[] entryArray) {
        HashMap forwardDelegate = Maps.newHashMapWithExpectedSize(n);
        HashMap backwardDelegate = Maps.newHashMapWithExpectedSize(n);
        for (int i = 0; i < n; ++i) {
            ImmutableMapEntry<K, V> e = RegularImmutableMap.makeImmutable(entryArray[i]);
            entryArray[i] = e;
            Object oldValue = forwardDelegate.putIfAbsent(e.getKey(), e.getValue());
            if (oldValue != null) {
                throw JdkBackedImmutableBiMap.conflictException("key", e.getKey() + "=" + oldValue, entryArray[i]);
            }
            Object oldKey = backwardDelegate.putIfAbsent(e.getValue(), e.getKey());
            if (oldKey == null) continue;
            throw JdkBackedImmutableBiMap.conflictException("value", oldKey + "=" + e.getValue(), entryArray[i]);
        }
        ImmutableList<Map.Entry<K, V>> entryList = ImmutableList.asImmutableList(entryArray, n);
        return new JdkBackedImmutableBiMap<K, V>(entryList, forwardDelegate, backwardDelegate);
    }

    private JdkBackedImmutableBiMap(ImmutableList<Map.Entry<K, V>> entries, Map<K, V> forwardDelegate, Map<V, K> backwardDelegate) {
        this.entries = entries;
        this.forwardDelegate = forwardDelegate;
        this.backwardDelegate = backwardDelegate;
    }

    @Override
    public int size() {
        return this.entries.size();
    }

    @Override
    public ImmutableBiMap<V, K> inverse() {
        JdkBackedImmutableBiMap<K, V> result = this.inverse;
        if (result == null) {
            this.inverse = result = new JdkBackedImmutableBiMap<K, V>(new InverseEntries(), this.backwardDelegate, this.forwardDelegate);
            result.inverse = this;
        }
        return result;
    }

    @Override
    public V get(@Nullable Object key) {
        return this.forwardDelegate.get(key);
    }

    @Override
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet<K, V>(this, this.entries);
    }

    @Override
    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    @Override
    boolean isPartialView() {
        return false;
    }

    private final class InverseEntries
    extends ImmutableList<Map.Entry<V, K>> {
        private InverseEntries() {
        }

        @Override
        public Map.Entry<V, K> get(int index) {
            Map.Entry entry = (Map.Entry)JdkBackedImmutableBiMap.this.entries.get(index);
            return Maps.immutableEntry(entry.getValue(), entry.getKey());
        }

        @Override
        boolean isPartialView() {
            return false;
        }

        @Override
        public int size() {
            return JdkBackedImmutableBiMap.this.entries.size();
        }
    }
}

