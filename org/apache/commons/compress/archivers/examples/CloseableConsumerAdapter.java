/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;
import org.apache.commons.compress.archivers.examples.CloseableConsumer;

final class CloseableConsumerAdapter
implements Closeable {
    private final CloseableConsumer consumer;
    private Closeable closeable;

    CloseableConsumerAdapter(CloseableConsumer consumer) {
        if (consumer == null) {
            throw new NullPointerException("consumer must not be null");
        }
        this.consumer = consumer;
    }

    <C extends Closeable> C track(C closeable) {
        this.closeable = closeable;
        return closeable;
    }

    @Override
    public void close() throws IOException {
        if (this.closeable != null) {
            this.consumer.accept(this.closeable);
        }
    }
}

