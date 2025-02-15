/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtIncompatible
final class TimeoutFuture<V>
extends FluentFuture.TrustedFuture<V> {
    private @Nullable ListenableFuture<V> delegateRef;
    private @Nullable ScheduledFuture<?> timer;

    static <V> ListenableFuture<V> create(ListenableFuture<V> delegate, long time, TimeUnit unit, ScheduledExecutorService scheduledExecutor) {
        TimeoutFuture<V> result = new TimeoutFuture<V>(delegate);
        Fire<V> fire = new Fire<V>(result);
        result.timer = scheduledExecutor.schedule(fire, time, unit);
        delegate.addListener(fire, MoreExecutors.directExecutor());
        return result;
    }

    private TimeoutFuture(ListenableFuture<V> delegate) {
        this.delegateRef = Preconditions.checkNotNull(delegate);
    }

    @Override
    protected String pendingToString() {
        ListenableFuture<V> localInputFuture = this.delegateRef;
        ScheduledFuture<?> localTimer = this.timer;
        if (localInputFuture != null) {
            long delay;
            String message = "inputFuture=[" + localInputFuture + "]";
            if (localTimer != null && (delay = localTimer.getDelay(TimeUnit.MILLISECONDS)) > 0L) {
                message = message + ", remaining delay=[" + delay + " ms]";
            }
            return message;
        }
        return null;
    }

    @Override
    protected void afterDone() {
        this.maybePropagateCancellationTo(this.delegateRef);
        ScheduledFuture<?> localTimer = this.timer;
        if (localTimer != null) {
            localTimer.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }

    private static final class TimeoutFutureException
    extends TimeoutException {
        private TimeoutFutureException(String message) {
            super(message);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            this.setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }

    private static final class Fire<V>
    implements Runnable {
        @Nullable TimeoutFuture<V> timeoutFutureRef;

        Fire(TimeoutFuture<V> timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture == null) {
                return;
            }
            ListenableFuture delegate = ((TimeoutFuture)timeoutFuture).delegateRef;
            if (delegate == null) {
                return;
            }
            this.timeoutFutureRef = null;
            if (delegate.isDone()) {
                timeoutFuture.setFuture(delegate);
            } else {
                try {
                    long overDelayMs;
                    ScheduledFuture timer = ((TimeoutFuture)timeoutFuture).timer;
                    String message = "Timed out";
                    if (timer != null && (overDelayMs = Math.abs(timer.getDelay(TimeUnit.MILLISECONDS))) > 10L) {
                        message = message + " (timeout delayed by " + overDelayMs + " ms after scheduled time)";
                    }
                    ((TimeoutFuture)timeoutFuture).timer = null;
                    timeoutFuture.setException(new TimeoutFutureException(message + ": " + delegate));
                } finally {
                    delegate.cancel(true);
                }
            }
        }
    }
}

