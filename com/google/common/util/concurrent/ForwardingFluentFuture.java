/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@GwtCompatible
final class ForwardingFluentFuture<V>
extends FluentFuture<V> {
    private final ListenableFuture<V> delegate;

    ForwardingFluentFuture(ListenableFuture<V> delegate) {
        this.delegate = Preconditions.checkNotNull(delegate);
    }

    @Override
    public void addListener(Runnable listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return this.delegate.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return this.delegate.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.delegate.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return this.delegate.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.delegate.get(timeout, unit);
    }
}

