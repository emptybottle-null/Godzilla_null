/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentRuntimeException;

public class ConcurrentUtils {
    private ConcurrentUtils() {
    }

    public static ConcurrentException extractCause(ExecutionException ex) {
        if (ex == null || ex.getCause() == null) {
            return null;
        }
        ConcurrentUtils.throwCause(ex);
        return new ConcurrentException(ex.getMessage(), ex.getCause());
    }

    public static ConcurrentRuntimeException extractCauseUnchecked(ExecutionException ex) {
        if (ex == null || ex.getCause() == null) {
            return null;
        }
        ConcurrentUtils.throwCause(ex);
        return new ConcurrentRuntimeException(ex.getMessage(), ex.getCause());
    }

    public static void handleCause(ExecutionException ex) throws ConcurrentException {
        ConcurrentException cex = ConcurrentUtils.extractCause(ex);
        if (cex != null) {
            throw cex;
        }
    }

    public static void handleCauseUnchecked(ExecutionException ex) {
        ConcurrentRuntimeException crex = ConcurrentUtils.extractCauseUnchecked(ex);
        if (crex != null) {
            throw crex;
        }
    }

    static Throwable checkedException(Throwable ex) {
        Validate.isTrue(ex != null && !(ex instanceof RuntimeException) && !(ex instanceof Error), "Not a checked exception: " + ex, new Object[0]);
        return ex;
    }

    private static void throwCause(ExecutionException ex) {
        if (ex.getCause() instanceof RuntimeException) {
            throw (RuntimeException)ex.getCause();
        }
        if (ex.getCause() instanceof Error) {
            throw (Error)ex.getCause();
        }
    }

    public static <T> T initialize(ConcurrentInitializer<T> initializer) throws ConcurrentException {
        return initializer != null ? (T)initializer.get() : null;
    }

    public static <T> T initializeUnchecked(ConcurrentInitializer<T> initializer) {
        try {
            return ConcurrentUtils.initialize(initializer);
        } catch (ConcurrentException cex) {
            throw new ConcurrentRuntimeException(cex.getCause());
        }
    }

    public static <K, V> V putIfAbsent(ConcurrentMap<K, V> map, K key, V value) {
        if (map == null) {
            return null;
        }
        V result = map.putIfAbsent(key, value);
        return result != null ? result : value;
    }

    public static <K, V> V createIfAbsent(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init) throws ConcurrentException {
        if (map == null || init == null) {
            return null;
        }
        Object value = map.get(key);
        if (value == null) {
            return ConcurrentUtils.putIfAbsent(map, key, init.get());
        }
        return value;
    }

    public static <K, V> V createIfAbsentUnchecked(ConcurrentMap<K, V> map, K key, ConcurrentInitializer<V> init) {
        try {
            return ConcurrentUtils.createIfAbsent(map, key, init);
        } catch (ConcurrentException cex) {
            throw new ConcurrentRuntimeException(cex.getCause());
        }
    }

    public static <T> Future<T> constantFuture(T value) {
        return new ConstantFuture<T>(value);
    }

    static final class ConstantFuture<T>
    implements Future<T> {
        private final T value;

        ConstantFuture(T value) {
            this.value = value;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public T get() {
            return this.value;
        }

        @Override
        public T get(long timeout, TimeUnit unit) {
            return this.value;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }
    }
}

