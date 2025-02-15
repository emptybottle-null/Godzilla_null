/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@CanIgnoreReturnValue
@GwtIncompatible
abstract class WrappingExecutorService
implements ExecutorService {
    private final ExecutorService delegate;

    protected WrappingExecutorService(ExecutorService delegate) {
        this.delegate = Preconditions.checkNotNull(delegate);
    }

    protected abstract <T> Callable<T> wrapTask(Callable<T> var1);

    protected Runnable wrapTask(Runnable command) {
        final Callable<Object> wrapped = this.wrapTask(Executors.callable(command, null));
        return new Runnable(){

            @Override
            public void run() {
                try {
                    wrapped.call();
                } catch (Exception e) {
                    Throwables.throwIfUnchecked(e);
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private <T> ImmutableList<Callable<T>> wrapTasks(Collection<? extends Callable<T>> tasks) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Callable<T> task : tasks) {
            builder.add(this.wrapTask(task));
        }
        return builder.build();
    }

    @Override
    public final void execute(Runnable command) {
        this.delegate.execute(this.wrapTask(command));
    }

    @Override
    public final <T> Future<T> submit(Callable<T> task) {
        return this.delegate.submit(this.wrapTask(Preconditions.checkNotNull(task)));
    }

    @Override
    public final Future<?> submit(Runnable task) {
        return this.delegate.submit(this.wrapTask(task));
    }

    @Override
    public final <T> Future<T> submit(Runnable task, T result) {
        return this.delegate.submit(this.wrapTask(task), result);
    }

    @Override
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.delegate.invokeAll(this.wrapTasks(tasks));
    }

    @Override
    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate.invokeAll(this.wrapTasks(tasks), timeout, unit);
    }

    @Override
    public final <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.delegate.invokeAny(this.wrapTasks(tasks));
    }

    @Override
    public final <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.delegate.invokeAny(this.wrapTasks(tasks), timeout, unit);
    }

    @Override
    public final void shutdown() {
        this.delegate.shutdown();
    }

    @Override
    public final List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    @Override
    public final boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override
    public final boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override
    public final boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate.awaitTermination(timeout, unit);
    }
}

