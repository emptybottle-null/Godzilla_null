/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.task;

import java.util.concurrent.Callable;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

public interface AsyncListenableTaskExecutor
extends AsyncTaskExecutor {
    public ListenableFuture<?> submitListenable(Runnable var1);

    public <T> ListenableFuture<T> submitListenable(Callable<T> var1);
}

