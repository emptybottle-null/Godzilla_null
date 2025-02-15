/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.concurrent;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;

public class TimedSemaphore {
    public static final int NO_LIMIT = 0;
    private static final int THREAD_POOL_SIZE = 1;
    private final ScheduledExecutorService executorService;
    private final long period;
    private final TimeUnit unit;
    private final boolean ownExecutor;
    private ScheduledFuture<?> task;
    private long totalAcquireCount;
    private long periodCount;
    private int limit;
    private int acquireCount;
    private int lastCallsPerPeriod;
    private boolean shutdown;

    public TimedSemaphore(long timePeriod, TimeUnit timeUnit, int limit) {
        this(null, timePeriod, timeUnit, limit);
    }

    public TimedSemaphore(ScheduledExecutorService service, long timePeriod, TimeUnit timeUnit, int limit) {
        Validate.inclusiveBetween(1L, Long.MAX_VALUE, timePeriod, "Time period must be greater than 0!");
        this.period = timePeriod;
        this.unit = timeUnit;
        if (service != null) {
            this.executorService = service;
            this.ownExecutor = false;
        } else {
            ScheduledThreadPoolExecutor s = new ScheduledThreadPoolExecutor(1);
            s.setContinueExistingPeriodicTasksAfterShutdownPolicy(false);
            s.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
            this.executorService = s;
            this.ownExecutor = true;
        }
        this.setLimit(limit);
    }

    public final synchronized int getLimit() {
        return this.limit;
    }

    public final synchronized void setLimit(int limit) {
        this.limit = limit;
    }

    public synchronized void shutdown() {
        if (!this.shutdown) {
            if (this.ownExecutor) {
                this.getExecutorService().shutdownNow();
            }
            if (this.task != null) {
                this.task.cancel(false);
            }
            this.shutdown = true;
        }
    }

    public synchronized boolean isShutdown() {
        return this.shutdown;
    }

    public synchronized void acquire() throws InterruptedException {
        boolean canPass;
        this.prepareAcquire();
        do {
            if (canPass = this.acquirePermit()) continue;
            this.wait();
        } while (!canPass);
    }

    public synchronized boolean tryAcquire() {
        this.prepareAcquire();
        return this.acquirePermit();
    }

    public synchronized int getLastAcquiresPerPeriod() {
        return this.lastCallsPerPeriod;
    }

    public synchronized int getAcquireCount() {
        return this.acquireCount;
    }

    public synchronized int getAvailablePermits() {
        return this.getLimit() - this.getAcquireCount();
    }

    public synchronized double getAverageCallsPerPeriod() {
        return this.periodCount == 0L ? 0.0 : (double)this.totalAcquireCount / (double)this.periodCount;
    }

    public long getPeriod() {
        return this.period;
    }

    public TimeUnit getUnit() {
        return this.unit;
    }

    protected ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    protected ScheduledFuture<?> startTimer() {
        return this.getExecutorService().scheduleAtFixedRate(this::endOfPeriod, this.getPeriod(), this.getPeriod(), this.getUnit());
    }

    synchronized void endOfPeriod() {
        this.lastCallsPerPeriod = this.acquireCount;
        this.totalAcquireCount += (long)this.acquireCount;
        ++this.periodCount;
        this.acquireCount = 0;
        this.notifyAll();
    }

    private void prepareAcquire() {
        if (this.isShutdown()) {
            throw new IllegalStateException("TimedSemaphore is shut down!");
        }
        if (this.task == null) {
            this.task = this.startTimer();
        }
    }

    private boolean acquirePermit() {
        if (this.getLimit() <= 0 || this.acquireCount < this.getLimit()) {
            ++this.acquireCount;
            return true;
        }
        return false;
    }
}

