/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.commons.io.output.QueueOutputStream;

public class QueueInputStream
extends InputStream {
    private final BlockingQueue<Integer> blockingQueue;

    public QueueInputStream() {
        this(new LinkedBlockingQueue<Integer>());
    }

    public QueueInputStream(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = Objects.requireNonNull(blockingQueue, "blockingQueue");
    }

    public QueueOutputStream newQueueOutputStream() {
        return new QueueOutputStream(this.blockingQueue);
    }

    @Override
    public int read() {
        Integer value = (Integer)this.blockingQueue.poll();
        return value == null ? -1 : 0xFF & value;
    }
}

