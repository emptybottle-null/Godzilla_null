/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AggregateFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.InterruptibleTask;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
final class CombinedFuture<V>
extends AggregateFuture<Object, V> {
    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, Executor listenerExecutor, AsyncCallable<V> callable) {
        this.init(new CombinedFutureRunningState(futures, allMustSucceed, new AsyncCallableInterruptibleTask(callable, listenerExecutor)));
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, Executor listenerExecutor, Callable<V> callable) {
        this.init(new CombinedFutureRunningState(futures, allMustSucceed, new CallableInterruptibleTask(callable, listenerExecutor)));
    }

    private final class CallableInterruptibleTask
    extends CombinedFutureInterruptibleTask<V> {
        private final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable, Executor listenerExecutor) {
            super(listenerExecutor);
            this.callable = Preconditions.checkNotNull(callable);
        }

        @Override
        V runInterruptibly() throws Exception {
            this.thrownByExecute = false;
            return this.callable.call();
        }

        @Override
        void setValue(V value) {
            CombinedFuture.this.set(value);
        }

        @Override
        String toPendingString() {
            return this.callable.toString();
        }
    }

    private final class AsyncCallableInterruptibleTask
    extends CombinedFutureInterruptibleTask<ListenableFuture<V>> {
        private final AsyncCallable<V> callable;

        public AsyncCallableInterruptibleTask(AsyncCallable<V> callable, Executor listenerExecutor) {
            super(listenerExecutor);
            this.callable = Preconditions.checkNotNull(callable);
        }

        @Override
        ListenableFuture<V> runInterruptibly() throws Exception {
            this.thrownByExecute = false;
            ListenableFuture result = this.callable.call();
            return Preconditions.checkNotNull(result, "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", this.callable);
        }

        @Override
        void setValue(ListenableFuture<V> value) {
            CombinedFuture.this.setFuture(value);
        }

        @Override
        String toPendingString() {
            return this.callable.toString();
        }
    }

    private abstract class CombinedFutureInterruptibleTask<T>
    extends InterruptibleTask<T> {
        private final Executor listenerExecutor;
        boolean thrownByExecute = true;

        public CombinedFutureInterruptibleTask(Executor listenerExecutor) {
            this.listenerExecutor = Preconditions.checkNotNull(listenerExecutor);
        }

        @Override
        final boolean isDone() {
            return CombinedFuture.this.isDone();
        }

        final void execute() {
            block2: {
                try {
                    this.listenerExecutor.execute(this);
                } catch (RejectedExecutionException e) {
                    if (!this.thrownByExecute) break block2;
                    CombinedFuture.this.setException(e);
                }
            }
        }

        @Override
        final void afterRanInterruptibly(T result, Throwable error) {
            if (error != null) {
                if (error instanceof ExecutionException) {
                    CombinedFuture.this.setException(error.getCause());
                } else if (error instanceof CancellationException) {
                    CombinedFuture.this.cancel(false);
                } else {
                    CombinedFuture.this.setException(error);
                }
            } else {
                this.setValue(result);
            }
        }

        abstract void setValue(T var1);
    }

    private final class CombinedFutureRunningState
    extends AggregateFuture.RunningState {
        private CombinedFutureInterruptibleTask task;

        CombinedFutureRunningState(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, CombinedFutureInterruptibleTask task) {
            super(futures, allMustSucceed, false);
            this.task = task;
        }

        void collectOneValue(boolean allMustSucceed, int index, @Nullable Object returnValue) {
        }

        @Override
        void handleAllCompleted() {
            CombinedFutureInterruptibleTask localTask = this.task;
            if (localTask != null) {
                localTask.execute();
            } else {
                Preconditions.checkState(CombinedFuture.this.isDone());
            }
        }

        @Override
        void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.task = null;
        }

        @Override
        void interruptTask() {
            CombinedFutureInterruptibleTask localTask = this.task;
            if (localTask != null) {
                localTask.interruptTask();
            }
        }
    }
}

