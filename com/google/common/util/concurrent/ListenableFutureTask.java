/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
public class ListenableFutureTask<V>
extends FutureTask<V>
implements ListenableFuture<V> {
    private final ExecutionList executionList = new ExecutionList();

    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<V>(callable);
    }

    public static <V> ListenableFutureTask<V> create(Runnable runnable, @Nullable V result) {
        return new ListenableFutureTask<V>(runnable, result);
    }

    ListenableFutureTask(Callable<V> callable) {
        super(callable);
    }

    ListenableFutureTask(Runnable runnable, @Nullable V result) {
        super(runnable, result);
    }

    @Override
    public void addListener(Runnable listener, Executor exec) {
        this.executionList.add(listener, exec);
    }

    @Override
    protected void done() {
        this.executionList.execute();
    }
}

