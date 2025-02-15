/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableScheduledFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
public interface ListeningScheduledExecutorService
extends ScheduledExecutorService,
ListeningExecutorService {
    public ListenableScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4);

    public <V> ListenableScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4);

    public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6);

    public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6);
}

