/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Platform;
import com.google.errorprone.annotations.ForOverride;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AbstractCatchingFuture<V, X extends Throwable, F, T>
extends FluentFuture.TrustedFuture<V>
implements Runnable {
    @Nullable ListenableFuture<? extends V> inputFuture;
    @Nullable Class<X> exceptionType;
    @Nullable F fallback;

    static <V, X extends Throwable> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback, Executor executor) {
        CatchingFuture<? extends V, ? super X> future = new CatchingFuture<V, X>(input, exceptionType, fallback);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    static <X extends Throwable, V> ListenableFuture<V> create(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback, Executor executor) {
        AsyncCatchingFuture<? extends V, ? super X> future = new AsyncCatchingFuture<V, X>(input, exceptionType, fallback);
        input.addListener(future, MoreExecutors.rejectionPropagatingExecutor(executor, future));
        return future;
    }

    AbstractCatchingFuture(ListenableFuture<? extends V> inputFuture, Class<X> exceptionType, F fallback) {
        this.inputFuture = Preconditions.checkNotNull(inputFuture);
        this.exceptionType = Preconditions.checkNotNull(exceptionType);
        this.fallback = Preconditions.checkNotNull(fallback);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void run() {
        T fallbackResult;
        F localFallback;
        Class<X> localExceptionType;
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        if (localInputFuture == null | (localExceptionType = this.exceptionType) == null | (localFallback = this.fallback) == null | this.isCancelled()) {
            return;
        }
        this.inputFuture = null;
        Object sourceResult = null;
        Throwable throwable = null;
        try {
            sourceResult = Futures.getDone(localInputFuture);
        } catch (ExecutionException e) {
            throwable = Preconditions.checkNotNull(e.getCause());
        } catch (Throwable e) {
            throwable = e;
        }
        if (throwable == null) {
            this.set(sourceResult);
            return;
        }
        if (!Platform.isInstanceOfThrowableClass(throwable, localExceptionType)) {
            this.setFuture(localInputFuture);
            return;
        }
        Throwable castThrowable = throwable;
        try {
            fallbackResult = this.doFallback(localFallback, castThrowable);
        } catch (Throwable t) {
            this.setException(t);
            return;
        } finally {
            this.exceptionType = null;
            this.fallback = null;
        }
        this.setResult(fallbackResult);
    }

    @Override
    protected String pendingToString() {
        ListenableFuture<? extends V> localInputFuture = this.inputFuture;
        Class<X> localExceptionType = this.exceptionType;
        F localFallback = this.fallback;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            resultString = "inputFuture=[" + localInputFuture + "], ";
        }
        if (localExceptionType != null && localFallback != null) {
            return resultString + "exceptionType=[" + localExceptionType + "], fallback=[" + localFallback + "]";
        }
        if (superString != null) {
            return resultString + superString;
        }
        return null;
    }

    @ForOverride
    abstract @Nullable T doFallback(F var1, X var2) throws Exception;

    @ForOverride
    abstract void setResult(@Nullable T var1);

    @Override
    protected final void afterDone() {
        this.maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.exceptionType = null;
        this.fallback = null;
    }

    private static final class CatchingFuture<V, X extends Throwable>
    extends AbstractCatchingFuture<V, X, Function<? super X, ? extends V>, V> {
        CatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, Function<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        @Override
        @Nullable V doFallback(Function<? super X, ? extends V> fallback, X cause) throws Exception {
            return fallback.apply(cause);
        }

        @Override
        void setResult(@Nullable V result) {
            this.set(result);
        }
    }

    private static final class AsyncCatchingFuture<V, X extends Throwable>
    extends AbstractCatchingFuture<V, X, AsyncFunction<? super X, ? extends V>, ListenableFuture<? extends V>> {
        AsyncCatchingFuture(ListenableFuture<? extends V> input, Class<X> exceptionType, AsyncFunction<? super X, ? extends V> fallback) {
            super(input, exceptionType, fallback);
        }

        @Override
        ListenableFuture<? extends V> doFallback(AsyncFunction<? super X, ? extends V> fallback, X cause) throws Exception {
            ListenableFuture<? extends V> replacement = fallback.apply(cause);
            Preconditions.checkNotNull(replacement, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", fallback);
            return replacement;
        }

        @Override
        void setResult(ListenableFuture<? extends V> result) {
            this.setFuture(result);
        }
    }
}

