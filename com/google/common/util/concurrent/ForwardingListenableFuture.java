/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ForwardingFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;

@CanIgnoreReturnValue
@GwtCompatible
public abstract class ForwardingListenableFuture<V>
extends ForwardingFuture<V>
implements ListenableFuture<V> {
    protected ForwardingListenableFuture() {
    }

    @Override
    protected abstract ListenableFuture<? extends V> delegate();

    @Override
    public void addListener(Runnable listener, Executor exec) {
        this.delegate().addListener(listener, exec);
    }

    public static abstract class SimpleForwardingListenableFuture<V>
    extends ForwardingListenableFuture<V> {
        private final ListenableFuture<V> delegate;

        protected SimpleForwardingListenableFuture(ListenableFuture<V> delegate) {
            this.delegate = Preconditions.checkNotNull(delegate);
        }

        @Override
        protected final ListenableFuture<V> delegate() {
            return this.delegate;
        }
    }
}

