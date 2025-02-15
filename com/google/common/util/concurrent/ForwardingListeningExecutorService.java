/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ForwardingExecutorService;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;

@CanIgnoreReturnValue
@GwtIncompatible
public abstract class ForwardingListeningExecutorService
extends ForwardingExecutorService
implements ListeningExecutorService {
    protected ForwardingListeningExecutorService() {
    }

    @Override
    protected abstract ListeningExecutorService delegate();

    @Override
    public <T> ListenableFuture<T> submit(Callable<T> task) {
        return this.delegate().submit((Callable)task);
    }

    @Override
    public ListenableFuture<?> submit(Runnable task) {
        return this.delegate().submit(task);
    }

    @Override
    public <T> ListenableFuture<T> submit(Runnable task, T result) {
        return this.delegate().submit(task, (Object)result);
    }
}

