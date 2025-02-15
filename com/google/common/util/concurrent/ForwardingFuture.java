/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@CanIgnoreReturnValue
@GwtCompatible
public abstract class ForwardingFuture<V>
extends ForwardingObject
implements Future<V> {
    protected ForwardingFuture() {
    }

    @Override
    protected abstract Future<? extends V> delegate();

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.delegate().cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return this.delegate().isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.delegate().isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return this.delegate().get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.delegate().get(timeout, unit);
    }

    public static abstract class SimpleForwardingFuture<V>
    extends ForwardingFuture<V> {
        private final Future<V> delegate;

        protected SimpleForwardingFuture(Future<V> delegate) {
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        protected final Future<V> delegate() {
            return this.delegate;
        }
    }
}

