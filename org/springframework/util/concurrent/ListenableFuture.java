/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.util.concurrent.DelegatingCompletableFuture;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

public interface ListenableFuture<T>
extends Future<T> {
    public void addCallback(ListenableFutureCallback<? super T> var1);

    public void addCallback(SuccessCallback<? super T> var1, FailureCallback var2);

    default public CompletableFuture<T> completable() {
        DelegatingCompletableFuture completable = new DelegatingCompletableFuture(this);
        this.addCallback(completable::complete, completable::completeExceptionally);
        return completable;
    }
}

