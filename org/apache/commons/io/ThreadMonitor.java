/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.time.Duration;

class ThreadMonitor
implements Runnable {
    private final Thread thread;
    private final Duration timeout;

    static Thread start(Duration timeout) {
        return ThreadMonitor.start(Thread.currentThread(), timeout);
    }

    static Thread start(Thread thread, Duration timeout) {
        if (timeout.isZero() || timeout.isNegative()) {
            return null;
        }
        ThreadMonitor timout = new ThreadMonitor(thread, timeout);
        Thread monitor = new Thread((Runnable)timout, ThreadMonitor.class.getSimpleName());
        monitor.setDaemon(true);
        monitor.start();
        return monitor;
    }

    static void stop(Thread thread) {
        if (thread != null) {
            thread.interrupt();
        }
    }

    private ThreadMonitor(Thread thread, Duration timeout) {
        this.thread = thread;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        try {
            ThreadMonitor.sleep(this.timeout);
            this.thread.interrupt();
        } catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    private static void sleep(Duration duration) throws InterruptedException {
        long millis = duration.toMillis();
        long finishAtMillis = System.currentTimeMillis() + millis;
        long remainingMillis = millis;
        do {
            Thread.sleep(remainingMillis);
        } while ((remainingMillis = finishAtMillis - System.currentTimeMillis()) > 0L);
    }
}

