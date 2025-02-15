/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.task;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrencyThrottleSupport;
import org.springframework.util.CustomizableThreadCreator;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

public class SimpleAsyncTaskExecutor
extends CustomizableThreadCreator
implements AsyncListenableTaskExecutor,
Serializable {
    public static final int UNBOUNDED_CONCURRENCY = -1;
    public static final int NO_CONCURRENCY = 0;
    private final ConcurrencyThrottleAdapter concurrencyThrottle = new ConcurrencyThrottleAdapter();
    @Nullable
    private ThreadFactory threadFactory;
    @Nullable
    private TaskDecorator taskDecorator;

    public SimpleAsyncTaskExecutor() {
    }

    public SimpleAsyncTaskExecutor(String threadNamePrefix) {
        super(threadNamePrefix);
    }

    public SimpleAsyncTaskExecutor(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void setThreadFactory(@Nullable ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    @Nullable
    public final ThreadFactory getThreadFactory() {
        return this.threadFactory;
    }

    public final void setTaskDecorator(TaskDecorator taskDecorator) {
        this.taskDecorator = taskDecorator;
    }

    public void setConcurrencyLimit(int concurrencyLimit) {
        this.concurrencyThrottle.setConcurrencyLimit(concurrencyLimit);
    }

    public final int getConcurrencyLimit() {
        return this.concurrencyThrottle.getConcurrencyLimit();
    }

    public final boolean isThrottleActive() {
        return this.concurrencyThrottle.isThrottleActive();
    }

    @Override
    public void execute(Runnable task) {
        this.execute(task, Long.MAX_VALUE);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        Runnable taskToUse;
        Assert.notNull((Object)task, "Runnable must not be null");
        Runnable runnable = taskToUse = this.taskDecorator != null ? this.taskDecorator.decorate(task) : task;
        if (this.isThrottleActive() && startTimeout > 0L) {
            this.concurrencyThrottle.beforeAccess();
            this.doExecute(new ConcurrencyThrottlingRunnable(taskToUse));
        } else {
            this.doExecute(taskToUse);
        }
    }

    @Override
    public Future<?> submit(Runnable task) {
        FutureTask<Object> future = new FutureTask<Object>(task, null);
        this.execute(future, Long.MAX_VALUE);
        return future;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        FutureTask<T> future = new FutureTask<T>(task);
        this.execute(future, Long.MAX_VALUE);
        return future;
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        ListenableFutureTask<Object> future = new ListenableFutureTask<Object>(task, null);
        this.execute(future, Long.MAX_VALUE);
        return future;
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        ListenableFutureTask<T> future = new ListenableFutureTask<T>(task);
        this.execute(future, Long.MAX_VALUE);
        return future;
    }

    protected void doExecute(Runnable task) {
        Thread thread = this.threadFactory != null ? this.threadFactory.newThread(task) : this.createThread(task);
        thread.start();
    }

    private class ConcurrencyThrottlingRunnable
    implements Runnable {
        private final Runnable target;

        public ConcurrencyThrottlingRunnable(Runnable target) {
            this.target = target;
        }

        @Override
        public void run() {
            try {
                this.target.run();
            } finally {
                SimpleAsyncTaskExecutor.this.concurrencyThrottle.afterAccess();
            }
        }
    }

    private static class ConcurrencyThrottleAdapter
    extends ConcurrencyThrottleSupport {
        private ConcurrencyThrottleAdapter() {
        }

        @Override
        protected void beforeAccess() {
            super.beforeAccess();
        }

        @Override
        protected void afterAccess() {
            super.afterAccess();
        }
    }
}

