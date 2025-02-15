/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

class MapIteratorCache<K, V> {
    private final Map<K, V> backingMap;
    private transient @Nullable Map.Entry<K, V> entrySetCache;

    MapIteratorCache(Map<K, V> backingMap) {
        this.backingMap = Preconditions.checkNotNull(backingMap);
    }

    @CanIgnoreReturnValue
    public V put(@Nullable K key, @Nullable V value) {
        this.clearCache();
        return this.backingMap.put(key, value);
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object key) {
        this.clearCache();
        return this.backingMap.remove(key);
    }

    public void clear() {
        this.clearCache();
        this.backingMap.clear();
    }

    public V get(@Nullable Object key) {
        V value = this.getIfCached(key);
        return value != null ? value : this.getWithoutCaching(key);
    }

    public final V getWithoutCaching(@Nullable Object key) {
        return this.backingMap.get(key);
    }

    public final boolean containsKey(@Nullable Object key) {
        return this.getIfCached(key) != null || this.backingMap.containsKey(key);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>(){

            @Override
            public UnmodifiableIterator<K> iterator() {
                final Iterator entryIterator = MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>(){

                    @Override
                    public boolean hasNext() {
                        return entryIterator.hasNext();
                    }

                    @Override
                    public K next() {
                        Map.Entry entry = (Map.Entry)entryIterator.next();
                        MapIteratorCache.this.entrySetCache = entry;
                        return entry.getKey();
                    }
                };
            }

            @Override
            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }

            @Override
            public boolean contains(@Nullable Object key) {
                return MapIteratorCache.this.containsKey(key);
            }
        };
    }

    protected V getIfCached(@Nullable Object key) {
        Map.Entry<K, V> entry = this.entrySetCache;
        if (entry != null && entry.getKey() == key) {
            return entry.getValue();
        }
        return null;
    }

    protected void clearCache() {
        this.entrySetCache = null;
    }
}

