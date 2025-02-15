/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@GwtCompatible(emulated=true)
public abstract class CacheLoader<K, V> {
    protected CacheLoader() {
    }

    public abstract V load(K var1) throws Exception;

    @GwtIncompatible
    public ListenableFuture<V> reload(K key, V oldValue) throws Exception {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(oldValue);
        return Futures.immediateFuture(this.load(key));
    }

    public Map<K, V> loadAll(Iterable<? extends K> keys) throws Exception {
        throw new UnsupportedLoadingOperationException();
    }

    public static <K, V> CacheLoader<K, V> from(Function<K, V> function) {
        return new FunctionToCacheLoader<K, V>(function);
    }

    public static <V> CacheLoader<Object, V> from(Supplier<V> supplier) {
        return new SupplierToCacheLoader<V>(supplier);
    }

    @GwtIncompatible
    public static <K, V> CacheLoader<K, V> asyncReloading(final CacheLoader<K, V> loader, final Executor executor) {
        Preconditions.checkNotNull(loader);
        Preconditions.checkNotNull(executor);
        return new CacheLoader<K, V>(){

            @Override
            public V load(K key) throws Exception {
                return loader.load(key);
            }

            @Override
            public ListenableFuture<V> reload(final K key, final V oldValue) throws Exception {
                ListenableFutureTask task = ListenableFutureTask.create(new Callable<V>(){

                    @Override
                    public V call() throws Exception {
                        return loader.reload(key, oldValue).get();
                    }
                });
                executor.execute(task);
                return task;
            }

            @Override
            public Map<K, V> loadAll(Iterable<? extends K> keys) throws Exception {
                return loader.loadAll(keys);
            }
        };
    }

    public static final class InvalidCacheLoadException
    extends RuntimeException {
        public InvalidCacheLoadException(String message) {
            super(message);
        }
    }

    public static final class UnsupportedLoadingOperationException
    extends UnsupportedOperationException {
        UnsupportedLoadingOperationException() {
        }
    }

    private static final class SupplierToCacheLoader<V>
    extends CacheLoader<Object, V>
    implements Serializable {
        private final Supplier<V> computingSupplier;
        private static final long serialVersionUID = 0L;

        public SupplierToCacheLoader(Supplier<V> computingSupplier) {
            this.computingSupplier = Preconditions.checkNotNull(computingSupplier);
        }

        @Override
        public V load(Object key) {
            Preconditions.checkNotNull(key);
            return this.computingSupplier.get();
        }
    }

    private static final class FunctionToCacheLoader<K, V>
    extends CacheLoader<K, V>
    implements Serializable {
        private final Function<K, V> computingFunction;
        private static final long serialVersionUID = 0L;

        public FunctionToCacheLoader(Function<K, V> computingFunction) {
            this.computingFunction = Preconditions.checkNotNull(computingFunction);
        }

        @Override
        public V load(K key) {
            return this.computingFunction.apply(Preconditions.checkNotNull(key));
        }
    }
}

