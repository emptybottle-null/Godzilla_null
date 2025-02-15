/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
public final class Callables {
    private Callables() {
    }

    public static <T> Callable<T> returning(final @Nullable T value) {
        return new Callable<T>(){

            @Override
            public T call() {
                return value;
            }
        };
    }

    @Beta
    @GwtIncompatible
    public static <T> AsyncCallable<T> asAsyncCallable(final Callable<T> callable, final ListeningExecutorService listeningExecutorService) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(listeningExecutorService);
        return new AsyncCallable<T>(){

            @Override
            public ListenableFuture<T> call() throws Exception {
                return listeningExecutorService.submit(callable);
            }
        };
    }

    @GwtIncompatible
    static <T> Callable<T> threadRenaming(final Callable<T> callable, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(nameSupplier);
        Preconditions.checkNotNull(callable);
        return new Callable<T>(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public T call() throws Exception {
                Thread currentThread = Thread.currentThread();
                String oldName = currentThread.getName();
                boolean restoreName = Callables.trySetName((String)nameSupplier.get(), currentThread);
                try {
                    Object v = callable.call();
                    return v;
                } finally {
                    if (restoreName) {
                        boolean bl = Callables.trySetName(oldName, currentThread);
                    }
                }
            }
        };
    }

    @GwtIncompatible
    static Runnable threadRenaming(final Runnable task, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(nameSupplier);
        Preconditions.checkNotNull(task);
        return new Runnable(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();
                String oldName = currentThread.getName();
                boolean restoreName = Callables.trySetName((String)nameSupplier.get(), currentThread);
                try {
                    task.run();
                } finally {
                    if (restoreName) {
                        boolean bl = Callables.trySetName(oldName, currentThread);
                    }
                }
            }
        };
    }

    @GwtIncompatible
    private static boolean trySetName(String threadName, Thread currentThread) {
        try {
            currentThread.setName(threadName);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }
}

