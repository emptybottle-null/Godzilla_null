/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.DelegatingCompletableFuture;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureCallbackRegistry;
import org.springframework.util.concurrent.SuccessCallback;

public class ListenableFutureTask<T>
extends FutureTask<T>
implements ListenableFuture<T> {
    private final ListenableFutureCallbackRegistry<T> callbacks = new ListenableFutureCallbackRegistry();

    public ListenableFutureTask(Callable<T> callable) {
        super(callable);
    }

    public ListenableFutureTask(Runnable runnable, @Nullable T result) {
        super(runnable, result);
    }

    @Override
    public void addCallback(ListenableFutureCallback<? super T> callback) {
        this.callbacks.addCallback(callback);
    }

    @Override
    public void addCallback(SuccessCallback<? super T> successCallback, FailureCallback failureCallback) {
        this.callbacks.addSuccessCallback(successCallback);
        this.callbacks.addFailureCallback(failureCallback);
    }

    @Override
    public CompletableFuture<T> completable() {
        DelegatingCompletableFuture completable = new DelegatingCompletableFuture(this);
        this.callbacks.addSuccessCallback(completable::complete);
        this.callbacks.addFailureCallback(completable::completeExceptionally);
        return completable;
    }

    @Override
    protected void done() {
        Throwable cause;
        try {
            Object result = this.get();
            this.callbacks.success(result);
            return;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return;
        } catch (ExecutionException ex) {
            cause = ex.getCause();
            if (cause == null) {
                cause = ex;
            }
        } catch (Throwable ex) {
            cause = ex;
        }
        this.callbacks.failure(cause);
    }
}

