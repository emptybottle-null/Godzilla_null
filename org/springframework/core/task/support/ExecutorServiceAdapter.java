/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.task.support;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.Assert;

public class ExecutorServiceAdapter
extends AbstractExecutorService {
    private final TaskExecutor taskExecutor;

    public ExecutorServiceAdapter(TaskExecutor taskExecutor) {
        Assert.notNull((Object)taskExecutor, "TaskExecutor must not be null");
        this.taskExecutor = taskExecutor;
    }

    @Override
    public void execute(Runnable task) {
        this.taskExecutor.execute(task);
    }

    @Override
    public void shutdown() {
        throw new IllegalStateException("Manual shutdown not supported - ExecutorServiceAdapter is dependent on an external lifecycle");
    }

    @Override
    public List<Runnable> shutdownNow() {
        throw new IllegalStateException("Manual shutdown not supported - ExecutorServiceAdapter is dependent on an external lifecycle");
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new IllegalStateException("Manual shutdown not supported - ExecutorServiceAdapter is dependent on an external lifecycle");
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}

