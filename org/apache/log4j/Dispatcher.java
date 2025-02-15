/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.BoundedFIFO;
import org.apache.log4j.spi.LoggingEvent;

class Dispatcher
extends Thread {
    private BoundedFIFO bf;
    private AppenderAttachableImpl aai;
    private boolean interrupted = false;
    AsyncAppender container;

    Dispatcher(BoundedFIFO bf, AsyncAppender container) {
        this.bf = bf;
        this.container = container;
        this.aai = container.aai;
        this.setDaemon(true);
        this.setPriority(1);
        this.setName("Dispatcher-" + this.getName());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void close() {
        BoundedFIFO boundedFIFO = this.bf;
        synchronized (boundedFIFO) {
            this.interrupted = true;
            if (this.bf.length() == 0) {
                this.bf.notify();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void run() {
        while (true) {
            LoggingEvent event;
            Object object = this.bf;
            synchronized (object) {
                if (this.bf.length() == 0) {
                    if (this.interrupted) {
                        break;
                    }
                    try {
                        this.bf.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                event = this.bf.get();
                if (this.bf.wasFull()) {
                    this.bf.notify();
                }
            }
            object = this.container.aai;
            synchronized (object) {
                if (this.aai != null && event != null) {
                    this.aai.appendLoopOnAppenders(event);
                }
            }
        }
        this.aai.removeAllAppenders();
    }
}

