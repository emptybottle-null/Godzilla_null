/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(emulated=true)
@ReflectionSupport(value=ReflectionSupport.Level.FULL)
abstract class InterruptibleTask<T>
extends AtomicReference<Runnable>
implements Runnable {
    private static final Runnable DONE;
    private static final Runnable INTERRUPTING;
    private static final Runnable PARKED;
    private static final int MAX_BUSY_WAIT_SPINS = 1000;

    InterruptibleTask() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void run() {
        Throwable error;
        T result;
        boolean run;
        Thread currentThread;
        block26: {
            currentThread = Thread.currentThread();
            if (!this.compareAndSet(null, currentThread)) {
                return;
            }
            run = !this.isDone();
            result = null;
            error = null;
            try {
                if (!run) break block26;
                result = this.runInterruptibly();
            } catch (Throwable t) {
                try {
                    error = t;
                } catch (Throwable throwable) {
                    if (!this.compareAndSet(currentThread, DONE)) {
                        boolean restoreInterruptedBit = false;
                        int spinCount = 0;
                        Runnable state = (Runnable)this.get();
                        while (state == INTERRUPTING || state == PARKED) {
                            if (++spinCount > 1000) {
                                if (state == PARKED || this.compareAndSet(INTERRUPTING, PARKED)) {
                                    restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
                                    LockSupport.park(this);
                                }
                            } else {
                                Thread.yield();
                            }
                            state = (Runnable)this.get();
                        }
                        if (restoreInterruptedBit) {
                            currentThread.interrupt();
                        }
                    }
                    if (run) {
                        this.afterRanInterruptibly(result, error);
                    }
                    throw throwable;
                }
                if (!this.compareAndSet(currentThread, DONE)) {
                    boolean restoreInterruptedBit = false;
                    int spinCount = 0;
                    Runnable state = (Runnable)this.get();
                    while (state == INTERRUPTING || state == PARKED) {
                        if (++spinCount > 1000) {
                            if (state == PARKED || this.compareAndSet(INTERRUPTING, PARKED)) {
                                restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
                                LockSupport.park(this);
                            }
                        } else {
                            Thread.yield();
                        }
                        state = (Runnable)this.get();
                    }
                    if (restoreInterruptedBit) {
                        currentThread.interrupt();
                    }
                }
                if (run) {
                    this.afterRanInterruptibly(result, error);
                }
            }
        }
        if (!this.compareAndSet(currentThread, DONE)) {
            boolean restoreInterruptedBit = false;
            int spinCount = 0;
            Runnable state = (Runnable)this.get();
            while (state == INTERRUPTING || state == PARKED) {
                if (++spinCount > 1000) {
                    if (state == PARKED || this.compareAndSet(INTERRUPTING, PARKED)) {
                        restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
                        LockSupport.park(this);
                    }
                } else {
                    Thread.yield();
                }
                state = (Runnable)this.get();
            }
            if (restoreInterruptedBit) {
                currentThread.interrupt();
            }
        }
        if (run) {
            this.afterRanInterruptibly(result, error);
        }
    }

    abstract boolean isDone();

    abstract T runInterruptibly() throws Exception;

    abstract void afterRanInterruptibly(@Nullable T var1, @Nullable Throwable var2);

    final void interruptTask() {
        Runnable currentRunner = (Runnable)this.get();
        if (currentRunner instanceof Thread && this.compareAndSet(currentRunner, INTERRUPTING)) {
            try {
                ((Thread)currentRunner).interrupt();
            } finally {
                Runnable prev = this.getAndSet(DONE);
                if (prev == PARKED) {
                    LockSupport.unpark((Thread)currentRunner);
                }
            }
        }
    }

    @Override
    public final String toString() {
        Runnable state = (Runnable)this.get();
        String result = state == DONE ? "running=[DONE]" : (state == INTERRUPTING ? "running=[INTERRUPTED]" : (state instanceof Thread ? "running=[RUNNING ON " + ((Thread)state).getName() + "]" : "running=[NOT STARTED YET]"));
        return result + ", " + this.toPendingString();
    }

    abstract String toPendingString();

    static {
        Class<LockSupport> clazz = LockSupport.class;
        DONE = new DoNothingRunnable();
        INTERRUPTING = new DoNothingRunnable();
        PARKED = new DoNothingRunnable();
    }

    private static final class DoNothingRunnable
    implements Runnable {
        private DoNothingRunnable() {
        }

        @Override
        public void run() {
        }
    }
}

