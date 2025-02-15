/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtIncompatible
final class SequentialExecutor
implements Executor {
    private static final Logger log = Logger.getLogger(SequentialExecutor.class.getName());
    private final Executor executor;
    @GuardedBy(value="queue")
    private final Deque<Runnable> queue = new ArrayDeque<Runnable>();
    @GuardedBy(value="queue")
    private WorkerRunningState workerRunningState = WorkerRunningState.IDLE;
    @GuardedBy(value="queue")
    private long workerRunCount = 0L;
    private final QueueWorker worker = new QueueWorker();

    SequentialExecutor(Executor executor) {
        this.executor = Preconditions.checkNotNull(executor);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void execute(final Runnable task) {
        boolean alreadyMarkedQueued;
        Runnable submittedTask;
        long oldRunCount;
        Preconditions.checkNotNull(task);
        Deque<Runnable> deque = this.queue;
        synchronized (deque) {
            if (this.workerRunningState == WorkerRunningState.RUNNING || this.workerRunningState == WorkerRunningState.QUEUED) {
                this.queue.add(task);
                return;
            }
            oldRunCount = this.workerRunCount;
            submittedTask = new Runnable(){

                @Override
                public void run() {
                    task.run();
                }
            };
            this.queue.add(submittedTask);
            this.workerRunningState = WorkerRunningState.QUEUING;
        }
        try {
            this.executor.execute(this.worker);
        } catch (Error | RuntimeException t) {
            Deque<Runnable> deque2 = this.queue;
            synchronized (deque2) {
                boolean removed;
                boolean bl = removed = (this.workerRunningState == WorkerRunningState.IDLE || this.workerRunningState == WorkerRunningState.QUEUING) && this.queue.removeLastOccurrence(submittedTask);
                if (!(t instanceof RejectedExecutionException) || removed) {
                    throw t;
                }
            }
            return;
        }
        boolean bl = alreadyMarkedQueued = this.workerRunningState != WorkerRunningState.QUEUING;
        if (alreadyMarkedQueued) {
            return;
        }
        Deque<Runnable> deque3 = this.queue;
        synchronized (deque3) {
            if (this.workerRunCount == oldRunCount && this.workerRunningState == WorkerRunningState.QUEUING) {
                this.workerRunningState = WorkerRunningState.QUEUED;
            }
        }
    }

    static /* synthetic */ WorkerRunningState access$200(SequentialExecutor x0) {
        return x0.workerRunningState;
    }

    static /* synthetic */ long access$308(SequentialExecutor x0) {
        return x0.workerRunCount++;
    }

    static /* synthetic */ Logger access$400() {
        return log;
    }

    private final class QueueWorker
    implements Runnable {
        private QueueWorker() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            try {
                this.workOnQueue();
            } catch (Error e) {
                Deque deque = SequentialExecutor.this.queue;
                synchronized (deque) {
                    SequentialExecutor.this.workerRunningState = WorkerRunningState.IDLE;
                }
                throw e;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void workOnQueue() {
            interruptedDuringTask = false;
            hasSetRunning = false;
            while (true) {
                var4_4 = SequentialExecutor.access$100(SequentialExecutor.this);
                synchronized (var4_4) {
                    if (hasSetRunning) break block14;
                    if (SequentialExecutor.access$200(SequentialExecutor.this) != WorkerRunningState.RUNNING) ** break block15
                    ** if (!interruptedDuringTask) goto lbl11
                }
lbl-1000:
                // 1 sources

                {
                    Thread.currentThread().interrupt();
                }
lbl11:
                // 2 sources

                return;
                break;
            }
            {
                catch (Throwable var6_6) {
                    if (interruptedDuringTask) {
                        Thread.currentThread().interrupt();
                    }
                    throw var6_6;
                }
                {
                    block14: {
                        SequentialExecutor.access$308(SequentialExecutor.this);
                        SequentialExecutor.access$202(SequentialExecutor.this, WorkerRunningState.RUNNING);
                        hasSetRunning = true;
                    }
                    if ((task = (Runnable)SequentialExecutor.access$100(SequentialExecutor.this).poll()) == null) {
                        SequentialExecutor.access$202(SequentialExecutor.this, WorkerRunningState.IDLE);
                        // MONITOREXIT @DISABLED, blocks:[6, 9, 12] lbl21 : MonitorExitStatement: MONITOREXIT : var4_4
                        if (interruptedDuringTask) {
                            Thread.currentThread().interrupt();
                        }
                        return;
                    }
                    // MONITOREXIT @DISABLED, blocks:[3, 6, 9] lbl26 : MonitorExitStatement: MONITOREXIT : var4_4
                    interruptedDuringTask |= Thread.interrupted();
                    try {
                        task.run();
                    } catch (RuntimeException e) {
                        SequentialExecutor.access$400().log(Level.SEVERE, "Exception while executing runnable " + task, e);
                    }
                    continue;
                }
            }
        }
    }

    static enum WorkerRunningState {
        IDLE,
        QUEUING,
        QUEUED,
        RUNNING;

    }
}

