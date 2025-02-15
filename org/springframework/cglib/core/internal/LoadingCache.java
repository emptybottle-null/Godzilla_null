/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.cglib.core.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.springframework.cglib.core.internal.Function;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class LoadingCache<K, KK, V> {
    protected final ConcurrentMap<KK, Object> map;
    protected final Function<K, V> loader;
    protected final Function<K, KK> keyMapper;
    public static final Function IDENTITY = new Function(){

        public Object apply(Object key) {
            return key;
        }
    };

    public LoadingCache(Function<K, KK> keyMapper, Function<K, V> loader) {
        this.keyMapper = keyMapper;
        this.loader = loader;
        this.map = new ConcurrentHashMap<KK, Object>();
    }

    public static <K> Function<K, K> identity() {
        return IDENTITY;
    }

    public V get(K key) {
        KK cacheKey = this.keyMapper.apply(key);
        Object v = this.map.get(cacheKey);
        if (v != null && !(v instanceof FutureTask)) {
            return v;
        }
        return this.createEntry(key, cacheKey, v);
    }

    protected V createEntry(final K key, KK cacheKey, Object v) {
        Object result;
        FutureTask task;
        boolean creator = false;
        if (v != null) {
            task = (FutureTask)v;
        } else {
            task = new FutureTask(new Callable<V>(){

                @Override
                public V call() throws Exception {
                    return LoadingCache.this.loader.apply(key);
                }
            });
            FutureTask prevTask = this.map.putIfAbsent(cacheKey, task);
            if (prevTask == null) {
                creator = true;
                task.run();
            } else if (prevTask instanceof FutureTask) {
                task = prevTask;
            } else {
                return (V)prevTask;
            }
        }
        try {
            result = task.get();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted while loading cache item", e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            throw new IllegalStateException("Unable to load cache item", cause);
        }
        if (creator) {
            this.map.put(cacheKey, result);
        }
        return result;
    }
}

