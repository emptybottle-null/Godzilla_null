/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtIncompatible
final class ListenerCallQueue<L> {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    ListenerCallQueue() {
    }

    public void addListener(L listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<L>(listener, executor));
    }

    public void enqueue(Event<L> event) {
        this.enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String label) {
        this.enqueueHelper(event, label);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void enqueueHelper(Event<L> event, Object label) {
        Preconditions.checkNotNull(event, "event");
        Preconditions.checkNotNull(label, "label");
        List<PerListenerQueue<L>> list = this.listeners;
        synchronized (list) {
            for (PerListenerQueue<L> queue : this.listeners) {
                queue.add(event, label);
            }
        }
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); ++i) {
            this.listeners.get(i).dispatch();
        }
    }

    private static final class PerListenerQueue<L>
    implements Runnable {
        final L listener;
        final Executor executor;
        @GuardedBy(value="this")
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();
        @GuardedBy(value="this")
        final Queue<Object> labelQueue = Queues.newArrayDeque();
        @GuardedBy(value="this")
        boolean isThreadScheduled;

        PerListenerQueue(L listener, Executor executor) {
            this.listener = Preconditions.checkNotNull(listener);
            this.executor = Preconditions.checkNotNull(executor);
        }

        synchronized void add(Event<L> event, Object label) {
            this.waitQueue.add(event);
            this.labelQueue.add(label);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void dispatch() {
            boolean scheduleEventRunner = false;
            PerListenerQueue perListenerQueue = this;
            synchronized (perListenerQueue) {
                if (!this.isThreadScheduled) {
                    this.isThreadScheduled = true;
                    scheduleEventRunner = true;
                }
            }
            if (scheduleEventRunner) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e) {
                    PerListenerQueue perListenerQueue2 = this;
                    synchronized (perListenerQueue2) {
                        this.isThreadScheduled = false;
                    }
                    logger.log(Level.SEVERE, "Exception while running callbacks for " + this.listener + " on " + this.executor, e);
                    throw e;
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            block18: {
                boolean stillRunning = true;
                block14: while (true) {
                    while (true) {
                        Object nextLabel;
                        Event<L> nextToRun;
                        PerListenerQueue perListenerQueue = this;
                        synchronized (perListenerQueue) {
                            Preconditions.checkState(this.isThreadScheduled);
                            nextToRun = this.waitQueue.poll();
                            nextLabel = this.labelQueue.poll();
                            if (nextToRun == null) {
                                this.isThreadScheduled = false;
                                stillRunning = false;
                                break block18;
                            }
                        }
                        try {
                            nextToRun.call(this.listener);
                            continue block14;
                        } catch (RuntimeException e) {
                            logger.log(Level.SEVERE, "Exception while executing callback: " + this.listener + " " + nextLabel, e);
                            continue;
                        }
                        break;
                    }
                }
                finally {
                    if (stillRunning) {
                        PerListenerQueue perListenerQueue = this;
                        synchronized (perListenerQueue) {
                            this.isThreadScheduled = false;
                        }
                    }
                }
            }
        }
    }

    static interface Event<L> {
        public void call(L var1);
    }
}

