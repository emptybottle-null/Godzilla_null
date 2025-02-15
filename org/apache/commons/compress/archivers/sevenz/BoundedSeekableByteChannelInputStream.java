/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

class BoundedSeekableByteChannelInputStream
extends InputStream {
    private static final int MAX_BUF_LEN = 8192;
    private final ByteBuffer buffer;
    private final SeekableByteChannel channel;
    private long bytesRemaining;

    public BoundedSeekableByteChannelInputStream(SeekableByteChannel channel, long size) {
        this.channel = channel;
        this.bytesRemaining = size;
        this.buffer = size < 8192L && size > 0L ? ByteBuffer.allocate((int)size) : ByteBuffer.allocate(8192);
    }

    @Override
    public int read() throws IOException {
        if (this.bytesRemaining > 0L) {
            --this.bytesRemaining;
            int read = this.read(1);
            if (read < 0) {
                return read;
            }
            return this.buffer.get() & 0xFF;
        }
        return -1;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int bytesRead;
        ByteBuffer buf;
        if (this.bytesRemaining <= 0L) {
            return -1;
        }
        int bytesToRead = len;
        if ((long)bytesToRead > this.bytesRemaining) {
            bytesToRead = (int)this.bytesRemaining;
        }
        if (bytesToRead <= this.buffer.capacity()) {
            buf = this.buffer;
            bytesRead = this.read(bytesToRead);
        } else {
            buf = ByteBuffer.allocate(bytesToRead);
            bytesRead = this.channel.read(buf);
            buf.flip();
        }
        if (bytesRead >= 0) {
            buf.get(b, off, bytesRead);
            this.bytesRemaining -= (long)bytesRead;
        }
        return bytesRead;
    }

    private int read(int len) throws IOException {
        this.buffer.rewind().limit(len);
        int read = this.channel.read(this.buffer);
        this.buffer.flip();
        return read;
    }

    @Override
    public void close() {
    }
}

