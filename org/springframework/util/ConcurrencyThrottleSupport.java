/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class ConcurrencyThrottleSupport
implements Serializable {
    public static final int UNBOUNDED_CONCURRENCY = -1;
    public static final int NO_CONCURRENCY = 0;
    protected transient Log logger = LogFactory.getLog(this.getClass());
    private transient Object monitor = new Object();
    private int concurrencyLimit = -1;
    private int concurrencyCount = 0;

    public void setConcurrencyLimit(int concurrencyLimit) {
        this.concurrencyLimit = concurrencyLimit;
    }

    public int getConcurrencyLimit() {
        return this.concurrencyLimit;
    }

    public boolean isThrottleActive() {
        return this.concurrencyLimit >= 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void beforeAccess() {
        if (this.concurrencyLimit == 0) {
            throw new IllegalStateException("Currently no invocations allowed - concurrency limit set to NO_CONCURRENCY");
        }
        if (this.concurrencyLimit > 0) {
            boolean debug = this.logger.isDebugEnabled();
            Object object = this.monitor;
            synchronized (object) {
                boolean interrupted = false;
                while (this.concurrencyCount >= this.concurrencyLimit) {
                    if (interrupted) {
                        throw new IllegalStateException("Thread was interrupted while waiting for invocation access, but concurrency limit still does not allow for entering");
                    }
                    if (debug) {
                        this.logger.debug("Concurrency count " + this.concurrencyCount + " has reached limit " + this.concurrencyLimit + " - blocking");
                    }
                    try {
                        this.monitor.wait();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        interrupted = true;
                    }
                }
                if (debug) {
                    this.logger.debug("Entering throttle at concurrency count " + this.concurrencyCount);
                }
                ++this.concurrencyCount;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void afterAccess() {
        if (this.concurrencyLimit >= 0) {
            Object object = this.monitor;
            synchronized (object) {
                --this.concurrencyCount;
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Returning from throttle at concurrency count " + this.concurrencyCount);
                }
                this.monitor.notify();
            }
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.logger = LogFactory.getLog(this.getClass());
        this.monitor = new Object();
    }
}

