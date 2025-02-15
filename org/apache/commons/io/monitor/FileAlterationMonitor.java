/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.monitor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;
import org.apache.commons.io.monitor.FileAlterationObserver;

public final class FileAlterationMonitor
implements Runnable {
    private static final FileAlterationObserver[] EMPTY_ARRAY = new FileAlterationObserver[0];
    private final long interval;
    private final List<FileAlterationObserver> observers = new CopyOnWriteArrayList<FileAlterationObserver>();
    private Thread thread;
    private ThreadFactory threadFactory;
    private volatile boolean running;

    public FileAlterationMonitor() {
        this(10000L);
    }

    public FileAlterationMonitor(long interval) {
        this.interval = interval;
    }

    public FileAlterationMonitor(long interval, Collection<FileAlterationObserver> observers) {
        this(interval, ((Collection)Optional.ofNullable(observers).orElse(Collections.emptyList())).toArray(EMPTY_ARRAY));
    }

    public FileAlterationMonitor(long interval, FileAlterationObserver ... observers) {
        this(interval);
        if (observers != null) {
            for (FileAlterationObserver observer : observers) {
                this.addObserver(observer);
            }
        }
    }

    public long getInterval() {
        return this.interval;
    }

    public synchronized void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void addObserver(FileAlterationObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    public void removeObserver(FileAlterationObserver observer) {
        if (observer != null) {
            while (this.observers.remove(observer)) {
            }
        }
    }

    public Iterable<FileAlterationObserver> getObservers() {
        return this.observers;
    }

    public synchronized void start() throws Exception {
        if (this.running) {
            throw new IllegalStateException("Monitor is already running");
        }
        for (FileAlterationObserver observer : this.observers) {
            observer.initialize();
        }
        this.running = true;
        this.thread = this.threadFactory != null ? this.threadFactory.newThread(this) : new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() throws Exception {
        this.stop(this.interval);
    }

    public synchronized void stop(long stopInterval) throws Exception {
        if (!this.running) {
            throw new IllegalStateException("Monitor is not running");
        }
        this.running = false;
        try {
            this.thread.interrupt();
            this.thread.join(stopInterval);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        for (FileAlterationObserver observer : this.observers) {
            observer.destroy();
        }
    }

    @Override
    public void run() {
        while (this.running) {
            for (FileAlterationObserver observer : this.observers) {
                observer.checkAndNotify();
            }
            if (!this.running) break;
            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException interruptedException) {}
        }
    }
}

