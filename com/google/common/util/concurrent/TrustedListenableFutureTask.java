/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.InterruptibleTask;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
class TrustedListenableFutureTask<V>
extends FluentFuture.TrustedFuture<V>
implements RunnableFuture<V> {
    private volatile InterruptibleTask<?> task;

    static <V> TrustedListenableFutureTask<V> create(AsyncCallable<V> callable) {
        return new TrustedListenableFutureTask<V>(callable);
    }

    static <V> TrustedListenableFutureTask<V> create(Callable<V> callable) {
        return new TrustedListenableFutureTask<V>(callable);
    }

    static <V> TrustedListenableFutureTask<V> create(Runnable runnable, @Nullable V result) {
        return new TrustedListenableFutureTask<V>(Executors.callable(runnable, result));
    }

    TrustedListenableFutureTask(Callable<V> callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }

    TrustedListenableFutureTask(AsyncCallable<V> callable) {
        this.task = new TrustedFutureInterruptibleAsyncTask(callable);
    }

    @Override
    public void run() {
        InterruptibleTask<?> localTask = this.task;
        if (localTask != null) {
            localTask.run();
        }
        this.task = null;
    }

    @Override
    protected void afterDone() {
        InterruptibleTask<?> localTask;
        super.afterDone();
        if (this.wasInterrupted() && (localTask = this.task) != null) {
            localTask.interruptTask();
        }
        this.task = null;
    }

    @Override
    protected String pendingToString() {
        InterruptibleTask<?> localTask = this.task;
        if (localTask != null) {
            return "task=[" + localTask + "]";
        }
        return super.pendingToString();
    }

    private final class TrustedFutureInterruptibleAsyncTask
    extends InterruptibleTask<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;

        TrustedFutureInterruptibleAsyncTask(AsyncCallable<V> callable) {
            this.callable = Preconditions.checkNotNull(callable);
        }

        @Override
        final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        @Override
        ListenableFuture<V> runInterruptibly() throws Exception {
            return Preconditions.checkNotNull(this.callable.call(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        @Override
        void afterRanInterruptibly(ListenableFuture<V> result, Throwable error) {
            if (error == null) {
                TrustedListenableFutureTask.this.setFuture(result);
            } else {
                TrustedListenableFutureTask.this.setException(error);
            }
        }

        @Override
        String toPendingString() {
            return this.callable.toString();
        }
    }

    private final class TrustedFutureInterruptibleTask
    extends InterruptibleTask<V> {
        private final Callable<V> callable;

        TrustedFutureInterruptibleTask(Callable<V> callable) {
            this.callable = Preconditions.checkNotNull(callable);
        }

        @Override
        final boolean isDone() {
            return TrustedListenableFutureTask.this.isDone();
        }

        @Override
        V runInterruptibly() throws Exception {
            return this.callable.call();
        }

        @Override
        void afterRanInterruptibly(V result, Throwable error) {
            if (error == null) {
                TrustedListenableFutureTask.this.set(result);
            } else {
                TrustedListenableFutureTask.this.setException(error);
            }
        }

        @Override
        String toPendingString() {
            return this.callable.toString();
        }
    }
}

