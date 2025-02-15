/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

@Beta
public final class ExecutionSequencer {
    private final AtomicReference<ListenableFuture<Object>> ref = new AtomicReference<ListenableFuture<Object>>(Futures.immediateFuture(null));

    private ExecutionSequencer() {
    }

    public static ExecutionSequencer create() {
        return new ExecutionSequencer();
    }

    public <T> ListenableFuture<T> submit(final Callable<T> callable, Executor executor) {
        Preconditions.checkNotNull(callable);
        return this.submitAsync(new AsyncCallable<T>(){

            @Override
            public ListenableFuture<T> call() throws Exception {
                return Futures.immediateFuture(callable.call());
            }

            public String toString() {
                return callable.toString();
            }
        }, executor);
    }

    public <T> ListenableFuture<T> submitAsync(final AsyncCallable<T> callable, final Executor executor) {
        Preconditions.checkNotNull(callable);
        final AtomicReference<RunningState> runningState = new AtomicReference<RunningState>(RunningState.NOT_RUN);
        AsyncCallable task = new AsyncCallable<T>(){

            @Override
            public ListenableFuture<T> call() throws Exception {
                if (!runningState.compareAndSet(RunningState.NOT_RUN, RunningState.STARTED)) {
                    return Futures.immediateCancelledFuture();
                }
                return callable.call();
            }

            public String toString() {
                return callable.toString();
            }
        };
        final SettableFuture newFuture = SettableFuture.create();
        final ListenableFuture oldFuture = this.ref.getAndSet(newFuture);
        final ListenableFuture taskFuture = Futures.submitAsync(task, new Executor(){

            @Override
            public void execute(Runnable runnable) {
                oldFuture.addListener(runnable, executor);
            }
        });
        final ListenableFuture outputFuture = Futures.nonCancellationPropagating(taskFuture);
        Runnable listener = new Runnable(){

            @Override
            public void run() {
                if (taskFuture.isDone() || outputFuture.isCancelled() && runningState.compareAndSet(RunningState.NOT_RUN, RunningState.CANCELLED)) {
                    newFuture.setFuture(oldFuture);
                }
            }
        };
        outputFuture.addListener(listener, MoreExecutors.directExecutor());
        taskFuture.addListener(listener, MoreExecutors.directExecutor());
        return outputFuture;
    }

    static enum RunningState {
        NOT_RUN,
        CANCELLED,
        STARTED;

    }
}

