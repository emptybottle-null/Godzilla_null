/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public final class ExecutionList {
    private static final Logger log = Logger.getLogger(ExecutionList.class.getName());
    @GuardedBy(value="this")
    private @Nullable RunnableExecutorPair runnables;
    @GuardedBy(value="this")
    private boolean executed;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        ExecutionList executionList = this;
        synchronized (executionList) {
            if (!this.executed) {
                this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
                return;
            }
        }
        ExecutionList.executeListener(runnable, executor);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void execute() {
        RunnableExecutorPair list;
        ExecutionList executionList = this;
        synchronized (executionList) {
            if (this.executed) {
                return;
            }
            this.executed = true;
            list = this.runnables;
            this.runnables = null;
        }
        RunnableExecutorPair reversedList = null;
        while (list != null) {
            RunnableExecutorPair tmp = list;
            list = list.next;
            tmp.next = reversedList;
            reversedList = tmp;
        }
        while (reversedList != null) {
            ExecutionList.executeListener(reversedList.runnable, reversedList.executor);
            reversedList = reversedList.next;
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    private static final class RunnableExecutorPair {
        final Runnable runnable;
        final Executor executor;
        @Nullable RunnableExecutorPair next;

        RunnableExecutorPair(Runnable runnable, Executor executor, RunnableExecutorPair next) {
            this.runnable = runnable;
            this.executor = executor;
            this.next = next;
        }
    }
}

