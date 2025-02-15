/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import com.google.j2objc.annotations.Weak;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BooleanSupplier;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtIncompatible
public final class Monitor {
    private final boolean fair;
    private final ReentrantLock lock;
    @GuardedBy(value="lock")
    private Guard activeGuards = null;

    public Monitor() {
        this(false);
    }

    public Monitor(boolean fair) {
        this.fair = fair;
        this.lock = new ReentrantLock(fair);
    }

    public Guard newGuard(final BooleanSupplier isSatisfied) {
        Preconditions.checkNotNull(isSatisfied, "isSatisfied");
        return new Guard(this){

            @Override
            public boolean isSatisfied() {
                return isSatisfied.getAsBoolean();
            }
        };
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enter(long time, TimeUnit unit) {
        long timeoutNanos = Monitor.toSafeNanos(time, unit);
        ReentrantLock lock = this.lock;
        if (!this.fair && lock.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            long startTime = System.nanoTime();
            long remainingNanos = timeoutNanos;
            while (true) {
                try {
                    boolean bl = lock.tryLock(remainingNanos, TimeUnit.NANOSECONDS);
                    return bl;
                } catch (InterruptedException interrupt) {
                    interrupted = true;
                    remainingNanos = Monitor.remainingNanos(startTime, timeoutNanos);
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
        lock.lockInterruptibly();
        boolean satisfied = false;
        try {
            if (!guard.isSatisfied()) {
                this.await(guard, signalBeforeWaiting);
            }
            satisfied = true;
        } finally {
            if (!satisfied) {
                this.leave();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean enterWhen(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long startTime;
        boolean reentrant;
        ReentrantLock lock;
        long timeoutNanos;
        block19: {
            block18: {
                timeoutNanos = Monitor.toSafeNanos(time, unit);
                if (guard.monitor != this) {
                    throw new IllegalMonitorStateException();
                }
                lock = this.lock;
                reentrant = lock.isHeldByCurrentThread();
                startTime = 0L;
                if (this.fair) break block18;
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                if (lock.tryLock()) break block19;
            }
            startTime = Monitor.initNanoTime(timeoutNanos);
            if (!lock.tryLock(time, unit)) {
                return false;
            }
        }
        boolean satisfied = false;
        boolean threw = true;
        try {
            satisfied = guard.isSatisfied() || this.awaitNanos(guard, startTime == 0L ? timeoutNanos : Monitor.remainingNanos(startTime, timeoutNanos), reentrant);
            threw = false;
            boolean bl = satisfied;
            return bl;
        } finally {
            if (!satisfied) {
                try {
                    if (threw && !reentrant) {
                        this.signalNextWaiter();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
        lock.lock();
        boolean satisfied = false;
        try {
            if (!guard.isSatisfied()) {
                this.awaitUninterruptibly(guard, signalBeforeWaiting);
            }
            satisfied = true;
        } finally {
            if (!satisfied) {
                this.leave();
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long timeoutNanos = Monitor.toSafeNanos(time, unit);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        long startTime = 0L;
        boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
        boolean interrupted = Thread.interrupted();
        if (this.fair || !lock.tryLock()) {
            startTime = Monitor.initNanoTime(timeoutNanos);
            long remainingNanos = timeoutNanos;
            while (true) {
                try {
                    if (!lock.tryLock(remainingNanos, TimeUnit.NANOSECONDS)) {
                        boolean bl = false;
                        return bl;
                    }
                } catch (InterruptedException interrupt) {
                    interrupted = true;
                    remainingNanos = Monitor.remainingNanos(startTime, timeoutNanos);
                    continue;
                }
                break;
            }
        }
        boolean satisfied = false;
        while (true) {
            try {
                if (guard.isSatisfied()) {
                    satisfied = true;
                } else {
                    long remainingNanos;
                    if (startTime == 0L) {
                        startTime = Monitor.initNanoTime(timeoutNanos);
                        remainingNanos = timeoutNanos;
                    } else {
                        remainingNanos = Monitor.remainingNanos(startTime, timeoutNanos);
                    }
                    satisfied = this.awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                }
                boolean remainingNanos = satisfied;
                return remainingNanos;
            } catch (InterruptedException interrupt) {
                interrupted = true;
                signalBeforeWaiting = false;
                continue;
            }
            break;
        }
        finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
        finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        lock.lock();
        boolean satisfied = false;
        try {
            boolean bl = satisfied = guard.isSatisfied();
            return bl;
        } finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean enterIf(Guard guard, long time, TimeUnit unit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        if (!this.enter(time, unit)) {
            return false;
        }
        boolean satisfied = false;
        try {
            boolean bl = satisfied = guard.isSatisfied();
            return bl;
        } finally {
            if (!satisfied) {
                this.lock.unlock();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        boolean satisfied = false;
        try {
            boolean bl = satisfied = guard.isSatisfied();
            return bl;
        } finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        if (!lock.tryLock(time, unit)) {
            return false;
        }
        boolean satisfied = false;
        try {
            boolean bl = satisfied = guard.isSatisfied();
            return bl;
        } finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        if (!lock.tryLock()) {
            return false;
        }
        boolean satisfied = false;
        try {
            boolean bl = satisfied = guard.isSatisfied();
            return bl;
        } finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (!(guard.monitor == this & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (!guard.isSatisfied()) {
            this.await(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long timeoutNanos = Monitor.toSafeNanos(time, unit);
        if (!(guard.monitor == this & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return true;
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return this.awaitNanos(guard, timeoutNanos, true);
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (!guard.isSatisfied()) {
            this.awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long timeoutNanos = Monitor.toSafeNanos(time, unit);
        if (!(guard.monitor == this & this.lock.isHeldByCurrentThread())) {
            throw new IllegalMonitorStateException();
        }
        if (guard.isSatisfied()) {
            return true;
        }
        boolean signalBeforeWaiting = true;
        long startTime = Monitor.initNanoTime(timeoutNanos);
        boolean interrupted = Thread.interrupted();
        try {
            long remainingNanos = timeoutNanos;
            while (true) {
                try {
                    boolean bl = this.awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                    return bl;
                } catch (InterruptedException interrupt) {
                    block12: {
                        interrupted = true;
                        if (!guard.isSatisfied()) break block12;
                        boolean bl = true;
                        if (interrupted) {
                            Thread.currentThread().interrupt();
                        }
                        return bl;
                    }
                    signalBeforeWaiting = false;
                    remainingNanos = Monitor.remainingNanos(startTime, timeoutNanos);
                    continue;
                }
                break;
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void leave() {
        ReentrantLock lock = this.lock;
        try {
            if (lock.getHoldCount() == 1) {
                this.signalNextWaiter();
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return this.getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            int n = guard.waiterCount;
            return n;
        } finally {
            this.lock.unlock();
        }
    }

    private static long toSafeNanos(long time, TimeUnit unit) {
        long timeoutNanos = unit.toNanos(time);
        return timeoutNanos <= 0L ? 0L : (timeoutNanos > 0x5FFFFFFFFFFFFFFDL ? 0x5FFFFFFFFFFFFFFDL : timeoutNanos);
    }

    private static long initNanoTime(long timeoutNanos) {
        if (timeoutNanos <= 0L) {
            return 0L;
        }
        long startTime = System.nanoTime();
        return startTime == 0L ? 1L : startTime;
    }

    private static long remainingNanos(long startTime, long timeoutNanos) {
        return timeoutNanos <= 0L ? 0L : timeoutNanos - (System.nanoTime() - startTime);
    }

    @GuardedBy(value="lock")
    private void signalNextWaiter() {
        Guard guard = this.activeGuards;
        while (guard != null) {
            if (this.isSatisfied(guard)) {
                guard.condition.signal();
                break;
            }
            guard = guard.next;
        }
    }

    @GuardedBy(value="lock")
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable throwable) {
            this.signalAllWaiters();
            throw throwable;
        }
    }

    @GuardedBy(value="lock")
    private void signalAllWaiters() {
        Guard guard = this.activeGuards;
        while (guard != null) {
            guard.condition.signalAll();
            guard = guard.next;
        }
    }

    @GuardedBy(value="lock")
    private void beginWaitingFor(Guard guard) {
        int waiters;
        if ((waiters = guard.waiterCount++) == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy(value="lock")
    private void endWaitingFor(Guard guard) {
        int waiters;
        if ((waiters = --guard.waiterCount) == 0) {
            Guard p = this.activeGuards;
            Guard pred = null;
            while (true) {
                if (p == guard) {
                    if (pred == null) {
                        this.activeGuards = p.next;
                    } else {
                        pred.next = p.next;
                    }
                    p.next = null;
                    break;
                }
                pred = p;
                p = p.next;
            }
        }
    }

    @GuardedBy(value="lock")
    private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            this.signalNextWaiter();
        }
        this.beginWaitingFor(guard);
        try {
            do {
                guard.condition.await();
            } while (!guard.isSatisfied());
        } finally {
            this.endWaitingFor(guard);
        }
    }

    @GuardedBy(value="lock")
    private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
        if (signalBeforeWaiting) {
            this.signalNextWaiter();
        }
        this.beginWaitingFor(guard);
        try {
            do {
                guard.condition.awaitUninterruptibly();
            } while (!guard.isSatisfied());
        } finally {
            this.endWaitingFor(guard);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @GuardedBy(value="lock")
    private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
        boolean firstTime = true;
        try {
            do {
                if (nanos <= 0L) {
                    boolean bl = false;
                    return bl;
                }
                if (firstTime) {
                    if (signalBeforeWaiting) {
                        this.signalNextWaiter();
                    }
                    this.beginWaitingFor(guard);
                    firstTime = false;
                }
                nanos = guard.condition.awaitNanos(nanos);
            } while (!guard.isSatisfied());
            boolean bl = true;
            return bl;
        } finally {
            if (!firstTime) {
                this.endWaitingFor(guard);
            }
        }
    }

    @Beta
    public static abstract class Guard {
        @Weak
        final Monitor monitor;
        final Condition condition;
        @GuardedBy(value="monitor.lock")
        int waiterCount = 0;
        @GuardedBy(value="monitor.lock")
        @Nullable Guard next;

        protected Guard(Monitor monitor) {
            this.monitor = Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }

        public abstract boolean isSatisfied();
    }
}

