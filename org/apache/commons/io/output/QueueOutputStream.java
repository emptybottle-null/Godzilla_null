/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.output;

import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.io.input.QueueInputStream;

public class QueueOutputStream
extends OutputStream {
    private final BlockingQueue<Integer> blockingQueue;

    public QueueOutputStream() {
        this(new LinkedBlockingQueue<Integer>());
    }

    public QueueOutputStream(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = Objects.requireNonNull(blockingQueue, "blockingQueue");
    }

    public QueueInputStream newQueueInputStream() {
        return new QueueInputStream(this.blockingQueue);
    }

    @Override
    public void write(int b) throws InterruptedIOException {
        try {
            this.blockingQueue.put(0xFF & b);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            InterruptedIOException interruptedIoException = new InterruptedIOException();
            interruptedIoException.initCause(e);
            throw interruptedIoException;
        }
    }
}

