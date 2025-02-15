/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;
import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public abstract class ForwardingCache<K, V>
extends ForwardingObject
implements Cache<K, V> {
    protected ForwardingCache() {
    }

    @Override
    protected abstract Cache<K, V> delegate();

    @Override
    public @Nullable V getIfPresent(Object key) {
        return this.delegate().getIfPresent(key);
    }

    @Override
    public V get(K key, Callable<? extends V> valueLoader) throws ExecutionException {
        return this.delegate().get(key, valueLoader);
    }

    @Override
    public ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
        return this.delegate().getAllPresent(keys);
    }

    @Override
    public void put(K key, V value) {
        this.delegate().put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.delegate().putAll(m);
    }

    @Override
    public void invalidate(Object key) {
        this.delegate().invalidate(key);
    }

    @Override
    public void invalidateAll(Iterable<?> keys) {
        this.delegate().invalidateAll(keys);
    }

    @Override
    public void invalidateAll() {
        this.delegate().invalidateAll();
    }

    @Override
    public long size() {
        return this.delegate().size();
    }

    @Override
    public CacheStats stats() {
        return this.delegate().stats();
    }

    @Override
    public ConcurrentMap<K, V> asMap() {
        return this.delegate().asMap();
    }

    @Override
    public void cleanUp() {
        this.delegate().cleanUp();
    }

    public static abstract class SimpleForwardingCache<K, V>
    extends ForwardingCache<K, V> {
        private final Cache<K, V> delegate;

        protected SimpleForwardingCache(Cache<K, V> delegate) {
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        protected final Cache<K, V> delegate() {
            return this.delegate;
        }
    }
}

