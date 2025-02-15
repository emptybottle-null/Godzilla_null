/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AggregateFutureState;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible
abstract class AggregateFuture<InputT, OutputT>
extends AbstractFuture.TrustedFuture<OutputT> {
    private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    private @Nullable RunningState runningState;

    AggregateFuture() {
    }

    @Override
    protected final void afterDone() {
        super.afterDone();
        RunningState localRunningState = this.runningState;
        if (localRunningState != null) {
            this.runningState = null;
            ImmutableCollection futures = localRunningState.futures;
            boolean wasInterrupted = this.wasInterrupted();
            if (wasInterrupted) {
                localRunningState.interruptTask();
            }
            if (this.isCancelled() & futures != null) {
                for (ListenableFuture future : futures) {
                    future.cancel(wasInterrupted);
                }
            }
        }
    }

    @Override
    protected String pendingToString() {
        RunningState localRunningState = this.runningState;
        if (localRunningState == null) {
            return null;
        }
        ImmutableCollection localFutures = localRunningState.futures;
        if (localFutures != null) {
            return "futures=[" + localFutures + "]";
        }
        return null;
    }

    final void init(RunningState runningState) {
        this.runningState = runningState;
        runningState.init();
    }

    private static boolean addCausalChain(Set<Throwable> seen, Throwable t) {
        while (t != null) {
            boolean firstTimeSeen = seen.add(t);
            if (!firstTimeSeen) {
                return false;
            }
            t = t.getCause();
        }
        return true;
    }

    abstract class RunningState
    extends AggregateFutureState
    implements Runnable {
        private ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;
        private final boolean allMustSucceed;
        private final boolean collectsValues;

        RunningState(ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures, boolean allMustSucceed, boolean collectsValues) {
            super(futures.size());
            this.futures = Preconditions.checkNotNull(futures);
            this.allMustSucceed = allMustSucceed;
            this.collectsValues = collectsValues;
        }

        @Override
        public final void run() {
            this.decrementCountAndMaybeComplete();
        }

        private void init() {
            if (this.futures.isEmpty()) {
                this.handleAllCompleted();
                return;
            }
            if (this.allMustSucceed) {
                int i = 0;
                for (final ListenableFuture listenableFuture : this.futures) {
                    final int index = i++;
                    listenableFuture.addListener(new Runnable(){

                        @Override
                        public void run() {
                            try {
                                RunningState.this.handleOneInputDone(index, listenableFuture);
                            } finally {
                                RunningState.this.decrementCountAndMaybeComplete();
                            }
                        }
                    }, MoreExecutors.directExecutor());
                }
            } else {
                for (ListenableFuture listenableFuture : this.futures) {
                    listenableFuture.addListener(this, MoreExecutors.directExecutor());
                }
            }
        }

        private void handleException(Throwable throwable) {
            Preconditions.checkNotNull(throwable);
            boolean completedWithFailure = false;
            boolean firstTimeSeeingThisException = true;
            if (this.allMustSucceed) {
                completedWithFailure = AggregateFuture.this.setException(throwable);
                if (completedWithFailure) {
                    this.releaseResourcesAfterFailure();
                } else {
                    firstTimeSeeingThisException = AggregateFuture.addCausalChain(this.getOrInitSeenExceptions(), throwable);
                }
            }
            if (throwable instanceof Error | this.allMustSucceed & !completedWithFailure & firstTimeSeeingThisException) {
                String message = throwable instanceof Error ? "Input Future failed with Error" : "Got more than one input Future failure. Logging failures after the first";
                logger.log(Level.SEVERE, message, throwable);
            }
        }

        @Override
        final void addInitialException(Set<Throwable> seen) {
            if (!AggregateFuture.this.isCancelled()) {
                boolean bl = AggregateFuture.addCausalChain(seen, AggregateFuture.this.tryInternalFastPathGetFailure());
            }
        }

        private void handleOneInputDone(int index, Future<? extends InputT> future) {
            Preconditions.checkState(this.allMustSucceed || !AggregateFuture.this.isDone() || AggregateFuture.this.isCancelled(), "Future was done before all dependencies completed");
            try {
                Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.allMustSucceed) {
                    if (future.isCancelled()) {
                        AggregateFuture.this.runningState = null;
                        AggregateFuture.this.cancel(false);
                    } else {
                        Object result = Futures.getDone(future);
                        if (this.collectsValues) {
                            this.collectOneValue(this.allMustSucceed, index, result);
                        }
                    }
                } else if (this.collectsValues && !future.isCancelled()) {
                    this.collectOneValue(this.allMustSucceed, index, Futures.getDone(future));
                }
            } catch (ExecutionException e) {
                this.handleException(e.getCause());
            } catch (Throwable t) {
                this.handleException(t);
            }
        }

        private void decrementCountAndMaybeComplete() {
            int newRemaining = this.decrementRemainingAndGet();
            Preconditions.checkState(newRemaining >= 0, "Less than 0 remaining futures");
            if (newRemaining == 0) {
                this.processCompleted();
            }
        }

        private void processCompleted() {
            if (this.collectsValues & !this.allMustSucceed) {
                int i = 0;
                for (ListenableFuture listenableFuture : this.futures) {
                    this.handleOneInputDone(i++, listenableFuture);
                }
            }
            this.handleAllCompleted();
        }

        @ForOverride
        @OverridingMethodsMustInvokeSuper
        void releaseResourcesAfterFailure() {
            this.futures = null;
        }

        abstract void collectOneValue(boolean var1, int var2, @Nullable InputT var3);

        abstract void handleAllCompleted();

        void interruptTask() {
        }
    }
}

