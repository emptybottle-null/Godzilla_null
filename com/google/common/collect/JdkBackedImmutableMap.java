/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.common.collect.ImmutableMapKeySet;
import com.google.common.collect.ImmutableMapValues;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.RegularImmutableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
final class JdkBackedImmutableMap<K, V>
extends ImmutableMap<K, V> {
    private final transient Map<K, V> delegateMap;
    private final transient ImmutableList<Map.Entry<K, V>> entries;

    static <K, V> ImmutableMap<K, V> create(int n, Map.Entry<K, V>[] entryArray) {
        HashMap<K, V> delegateMap = Maps.newHashMapWithExpectedSize(n);
        for (int i = 0; i < n; ++i) {
            entryArray[i] = RegularImmutableMap.makeImmutable(entryArray[i]);
            V oldValue = delegateMap.putIfAbsent(entryArray[i].getKey(), entryArray[i].getValue());
            if (oldValue == null) continue;
            throw JdkBackedImmutableMap.conflictException("key", entryArray[i], entryArray[i].getKey() + "=" + oldValue);
        }
        return new JdkBackedImmutableMap(delegateMap, ImmutableList.asImmutableList(entryArray, n));
    }

    JdkBackedImmutableMap(Map<K, V> delegateMap, ImmutableList<Map.Entry<K, V>> entries) {
        this.delegateMap = delegateMap;
        this.entries = entries;
    }

    @Override
    public int size() {
        return this.entries.size();
    }

    @Override
    public V get(@Nullable Object key) {
        return this.delegateMap.get(key);
    }

    @Override
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new ImmutableMapEntrySet.RegularEntrySet<K, V>(this, this.entries);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        Preconditions.checkNotNull(action);
        this.entries.forEach((Consumer<Map.Entry<K, V>>)((Consumer<Map.Entry>)e -> action.accept((Object)e.getKey(), (Object)e.getValue())));
    }

    @Override
    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    @Override
    ImmutableCollection<V> createValues() {
        return new ImmutableMapValues(this);
    }

    @Override
    boolean isPartialView() {
        return false;
    }
}

