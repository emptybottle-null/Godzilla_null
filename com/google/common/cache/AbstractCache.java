/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LongAddable;
import com.google.common.cache.LongAddables;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@GwtCompatible
public abstract class AbstractCache<K, V>
implements Cache<K, V> {
    protected AbstractCache() {
    }

    @Override
    public V get(K key, Callable<? extends V> valueLoader) throws ExecutionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
        LinkedHashMap result = Maps.newLinkedHashMap();
        for (Object key : keys) {
            if (result.containsKey(key)) continue;
            Object castKey = key;
            Object value = this.getIfPresent(key);
            if (value == null) continue;
            result.put(castKey, value);
        }
        return ImmutableMap.copyOf(result);
    }

    @Override
    public void put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void cleanUp() {
    }

    @Override
    public long size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void invalidate(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void invalidateAll(Iterable<?> keys) {
        for (Object key : keys) {
            this.invalidate(key);
        }
    }

    @Override
    public void invalidateAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CacheStats stats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConcurrentMap<K, V> asMap() {
        throw new UnsupportedOperationException();
    }

    public static final class SimpleStatsCounter
    implements StatsCounter {
        private final LongAddable hitCount = LongAddables.create();
        private final LongAddable missCount = LongAddables.create();
        private final LongAddable loadSuccessCount = LongAddables.create();
        private final LongAddable loadExceptionCount = LongAddables.create();
        private final LongAddable totalLoadTime = LongAddables.create();
        private final LongAddable evictionCount = LongAddables.create();

        @Override
        public void recordHits(int count) {
            this.hitCount.add(count);
        }

        @Override
        public void recordMisses(int count) {
            this.missCount.add(count);
        }

        @Override
        public void recordLoadSuccess(long loadTime) {
            this.loadSuccessCount.increment();
            this.totalLoadTime.add(loadTime);
        }

        @Override
        public void recordLoadException(long loadTime) {
            this.loadExceptionCount.increment();
            this.totalLoadTime.add(loadTime);
        }

        @Override
        public void recordEviction() {
            this.evictionCount.increment();
        }

        @Override
        public CacheStats snapshot() {
            return new CacheStats(this.hitCount.sum(), this.missCount.sum(), this.loadSuccessCount.sum(), this.loadExceptionCount.sum(), this.totalLoadTime.sum(), this.evictionCount.sum());
        }

        public void incrementBy(StatsCounter other) {
            CacheStats otherStats = other.snapshot();
            this.hitCount.add(otherStats.hitCount());
            this.missCount.add(otherStats.missCount());
            this.loadSuccessCount.add(otherStats.loadSuccessCount());
            this.loadExceptionCount.add(otherStats.loadExceptionCount());
            this.totalLoadTime.add(otherStats.totalLoadTime());
            this.evictionCount.add(otherStats.evictionCount());
        }
    }

    public static interface StatsCounter {
        public void recordHits(int var1);

        public void recordMisses(int var1);

        public void recordLoadSuccess(long var1);

        public void recordLoadException(long var1);

        public void recordEviction();

        public CacheStats snapshot();
    }
}

