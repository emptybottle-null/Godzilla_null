/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.CheckedFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Deprecated
@Beta
@GwtIncompatible
public abstract class AbstractCheckedFuture<V, X extends Exception>
extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V>
implements CheckedFuture<V, X> {
    protected AbstractCheckedFuture(ListenableFuture<V> delegate) {
        super(delegate);
    }

    protected abstract X mapException(Exception var1);

    @Override
    @CanIgnoreReturnValue
    public V checkedGet() throws X {
        try {
            return this.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw this.mapException(e);
        } catch (CancellationException | ExecutionException e) {
            throw this.mapException(e);
        }
    }

    @Override
    @CanIgnoreReturnValue
    public V checkedGet(long timeout, TimeUnit unit) throws TimeoutException, X {
        try {
            return this.get(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw this.mapException(e);
        } catch (CancellationException | ExecutionException e) {
            throw this.mapException(e);
        }
    }
}

