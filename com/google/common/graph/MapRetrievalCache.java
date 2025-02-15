/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.graph;

import com.google.common.graph.MapIteratorCache;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

class MapRetrievalCache<K, V>
extends MapIteratorCache<K, V> {
    private transient @Nullable CacheEntry<K, V> cacheEntry1;
    private transient @Nullable CacheEntry<K, V> cacheEntry2;

    MapRetrievalCache(Map<K, V> backingMap) {
        super(backingMap);
    }

    @Override
    public V get(@Nullable Object key) {
        V value = this.getIfCached(key);
        if (value != null) {
            return value;
        }
        value = this.getWithoutCaching(key);
        if (value != null) {
            this.addToCache(key, value);
        }
        return value;
    }

    @Override
    protected V getIfCached(@Nullable Object key) {
        Object value = super.getIfCached(key);
        if (value != null) {
            return value;
        }
        CacheEntry<K, V> entry = this.cacheEntry1;
        if (entry != null && entry.key == key) {
            return entry.value;
        }
        entry = this.cacheEntry2;
        if (entry != null && entry.key == key) {
            this.addToCache(entry);
            return entry.value;
        }
        return null;
    }

    @Override
    protected void clearCache() {
        super.clearCache();
        this.cacheEntry1 = null;
        this.cacheEntry2 = null;
    }

    private void addToCache(K key, V value) {
        this.addToCache(new CacheEntry<K, V>(key, value));
    }

    private void addToCache(CacheEntry<K, V> entry) {
        this.cacheEntry2 = this.cacheEntry1;
        this.cacheEntry1 = entry;
    }

    private static final class CacheEntry<K, V> {
        final K key;
        final V value;

        CacheEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

