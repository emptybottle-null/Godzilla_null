/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.ConcurrentInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;

public abstract class BackgroundInitializer<T>
implements ConcurrentInitializer<T> {
    private ExecutorService externalExecutor;
    private ExecutorService executor;
    private Future<T> future;

    protected BackgroundInitializer() {
        this(null);
    }

    protected BackgroundInitializer(ExecutorService exec) {
        this.setExternalExecutor(exec);
    }

    public final synchronized ExecutorService getExternalExecutor() {
        return this.externalExecutor;
    }

    public synchronized boolean isStarted() {
        return this.future != null;
    }

    public final synchronized void setExternalExecutor(ExecutorService externalExecutor) {
        if (this.isStarted()) {
            throw new IllegalStateException("Cannot set ExecutorService after start()!");
        }
        this.externalExecutor = externalExecutor;
    }

    public synchronized boolean start() {
        if (!this.isStarted()) {
            ExecutorService tempExec;
            this.executor = this.getExternalExecutor();
            if (this.executor == null) {
                this.executor = tempExec = this.createExecutor();
            } else {
                tempExec = null;
            }
            this.future = this.executor.submit(this.createTask(tempExec));
            return true;
        }
        return false;
    }

    @Override
    public T get() throws ConcurrentException {
        try {
            return this.getFuture().get();
        } catch (ExecutionException execex) {
            ConcurrentUtils.handleCause(execex);
            return null;
        } catch (InterruptedException iex) {
            Thread.currentThread().interrupt();
            throw new ConcurrentException(iex);
        }
    }

    public synchronized Future<T> getFuture() {
        if (this.future == null) {
            throw new IllegalStateException("start() must be called first!");
        }
        return this.future;
    }

    protected final synchronized ExecutorService getActiveExecutor() {
        return this.executor;
    }

    protected int getTaskCount() {
        return 1;
    }

    protected abstract T initialize() throws Exception;

    private Callable<T> createTask(ExecutorService execDestroy) {
        return new InitializationTask(execDestroy);
    }

    private ExecutorService createExecutor() {
        return Executors.newFixedThreadPool(this.getTaskCount());
    }

    private class InitializationTask
    implements Callable<T> {
        private final ExecutorService execFinally;

        InitializationTask(ExecutorService exec) {
            this.execFinally = exec;
        }

        @Override
        public T call() throws Exception {
            try {
                Object t = BackgroundInitializer.this.initialize();
                return t;
            } finally {
                if (this.execFinally != null) {
                    this.execFinally.shutdown();
                }
            }
        }
    }
}

