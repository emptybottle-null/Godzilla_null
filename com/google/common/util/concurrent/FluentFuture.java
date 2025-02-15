/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ForwardingFluentFuture;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Partially;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
@GwtCompatible(emulated=true)
public abstract class FluentFuture<V>
extends GwtFluentFutureCatchingSpecialization<V> {
    FluentFuture() {
    }

    public static <V> FluentFuture<V> from(ListenableFuture<V> future) {
        return future instanceof FluentFuture ? (FluentFuture<V>)future : new ForwardingFluentFuture<V>(future);
    }

    @Partially.GwtIncompatible(value="AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> FluentFuture<V> catching(Class<X> exceptionType, Function<? super X, ? extends V> fallback, Executor executor) {
        return (FluentFuture)Futures.catching(this, exceptionType, fallback, executor);
    }

    @Partially.GwtIncompatible(value="AVAILABLE but requires exceptionType to be Throwable.class")
    public final <X extends Throwable> FluentFuture<V> catchingAsync(Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback, Executor executor) {
        return (FluentFuture)Futures.catchingAsync(this, exceptionType, fallback, executor);
    }

    @GwtIncompatible
    public final FluentFuture<V> withTimeout(long timeout, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
        return (FluentFuture)Futures.withTimeout(this, timeout, unit, scheduledExecutor);
    }

    public final <T> FluentFuture<T> transformAsync(AsyncFunction<? super V, T> function, Executor executor) {
        return (FluentFuture)Futures.transformAsync(this, function, executor);
    }

    public final <T> FluentFuture<T> transform(Function<? super V, T> function, Executor executor) {
        return (FluentFuture)Futures.transform(this, function, executor);
    }

    public final void addCallback(FutureCallback<? super V> callback, Executor executor) {
        Futures.addCallback(this, callback, executor);
    }

    static abstract class TrustedFuture<V>
    extends FluentFuture<V>
    implements AbstractFuture.Trusted<V> {
        TrustedFuture() {
        }

        @Override
        @CanIgnoreReturnValue
        public final V get() throws InterruptedException, ExecutionException {
            return super.get();
        }

        @Override
        @CanIgnoreReturnValue
        public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return super.get(timeout, unit);
        }

        @Override
        public final boolean isDone() {
            return super.isDone();
        }

        @Override
        public final boolean isCancelled() {
            return super.isCancelled();
        }

        @Override
        public final void addListener(Runnable listener, Executor executor) {
            super.addListener(listener, executor);
        }

        @Override
        @CanIgnoreReturnValue
        public final boolean cancel(boolean mayInterruptIfRunning) {
            return super.cancel(mayInterruptIfRunning);
        }
    }
}

