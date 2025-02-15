/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.util.concurrent.SuccessCallback;

public class SettableListenableFuture<T>
implements ListenableFuture<T> {
    private static final Callable<Object> DUMMY_CALLABLE = () -> {
        throw new IllegalStateException("Should never be called");
    };
    private final SettableTask<T> settableTask = new SettableTask();

    public boolean set(@Nullable T value) {
        return this.settableTask.setResultValue(value);
    }

    public boolean setException(Throwable exception) {
        Assert.notNull((Object)exception, "Exception must not be null");
        return this.settableTask.setExceptionResult(exception);
    }

    @Override
    public void addCallback(ListenableFutureCallback<? super T> callback) {
        this.settableTask.addCallback(callback);
    }

    @Override
    public void addCallback(SuccessCallback<? super T> successCallback, FailureCallback failureCallback) {
        this.settableTask.addCallback(successCallback, failureCallback);
    }

    @Override
    public CompletableFuture<T> completable() {
        return this.settableTask.completable();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean cancelled = this.settableTask.cancel(mayInterruptIfRunning);
        if (cancelled && mayInterruptIfRunning) {
            this.interruptTask();
        }
        return cancelled;
    }

    @Override
    public boolean isCancelled() {
        return this.settableTask.isCancelled();
    }

    @Override
    public boolean isDone() {
        return this.settableTask.isDone();
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return (T)this.settableTask.get();
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T)this.settableTask.get(timeout, unit);
    }

    protected void interruptTask() {
    }

    private static class SettableTask<T>
    extends ListenableFutureTask<T> {
        @Nullable
        private volatile Thread completingThread;

        public SettableTask() {
            super(DUMMY_CALLABLE);
        }

        public boolean setResultValue(@Nullable T value) {
            this.set(value);
            return this.checkCompletingThread();
        }

        public boolean setExceptionResult(Throwable exception) {
            this.setException(exception);
            return this.checkCompletingThread();
        }

        @Override
        protected void done() {
            if (!this.isCancelled()) {
                this.completingThread = Thread.currentThread();
            }
            super.done();
        }

        private boolean checkCompletingThread() {
            boolean check;
            boolean bl = check = this.completingThread == Thread.currentThread();
            if (check) {
                this.completingThread = null;
            }
            return check;
        }
    }
}

