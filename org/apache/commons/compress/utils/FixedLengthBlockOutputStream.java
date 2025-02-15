/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedLengthBlockOutputStream
extends OutputStream
implements WritableByteChannel {
    private final WritableByteChannel out;
    private final int blockSize;
    private final ByteBuffer buffer;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public FixedLengthBlockOutputStream(OutputStream os, int blockSize) {
        if (os instanceof FileOutputStream) {
            FileOutputStream fileOutputStream = (FileOutputStream)os;
            this.out = fileOutputStream.getChannel();
            this.buffer = ByteBuffer.allocateDirect(blockSize);
        } else {
            this.out = new BufferAtATimeOutputChannel(os);
            this.buffer = ByteBuffer.allocate(blockSize);
        }
        this.blockSize = blockSize;
    }

    public FixedLengthBlockOutputStream(WritableByteChannel out, int blockSize) {
        this.out = out;
        this.blockSize = blockSize;
        this.buffer = ByteBuffer.allocateDirect(blockSize);
    }

    private void maybeFlush() throws IOException {
        if (!this.buffer.hasRemaining()) {
            this.writeBlock();
        }
    }

    private void writeBlock() throws IOException {
        this.buffer.flip();
        int i = this.out.write(this.buffer);
        boolean hasRemaining = this.buffer.hasRemaining();
        if (i != this.blockSize || hasRemaining) {
            String msg = String.format("Failed to write %,d bytes atomically. Only wrote  %,d", this.blockSize, i);
            throw new IOException(msg);
        }
        this.buffer.clear();
    }

    @Override
    public void write(int b) throws IOException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
        this.buffer.put((byte)b);
        this.maybeFlush();
    }

    @Override
    public void write(byte[] b, int offset, int length) throws IOException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
        int off = offset;
        int len = length;
        while (len > 0) {
            int n = Math.min(len, this.buffer.remaining());
            this.buffer.put(b, off, n);
            this.maybeFlush();
            len -= n;
            off += n;
        }
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
        int srcRemaining = src.remaining();
        if (srcRemaining < this.buffer.remaining()) {
            this.buffer.put(src);
        } else {
            int srcLeft = srcRemaining;
            int savedLimit = src.limit();
            if (this.buffer.position() != 0) {
                int n = this.buffer.remaining();
                src.limit(src.position() + n);
                this.buffer.put(src);
                this.writeBlock();
                srcLeft -= n;
            }
            while (srcLeft >= this.blockSize) {
                src.limit(src.position() + this.blockSize);
                this.out.write(src);
                srcLeft -= this.blockSize;
            }
            src.limit(savedLimit);
            this.buffer.put(src);
        }
        return srcRemaining;
    }

    @Override
    public boolean isOpen() {
        if (!this.out.isOpen()) {
            this.closed.set(true);
        }
        return !this.closed.get();
    }

    public void flushBlock() throws IOException {
        if (this.buffer.position() != 0) {
            this.padBlock();
            this.writeBlock();
        }
    }

    @Override
    public void close() throws IOException {
        if (this.closed.compareAndSet(false, true)) {
            try {
                this.flushBlock();
            } finally {
                this.out.close();
            }
        }
    }

    private void padBlock() {
        this.buffer.order(ByteOrder.nativeOrder());
        int bytesToWrite = this.buffer.remaining();
        if (bytesToWrite > 8) {
            int align = this.buffer.position() & 7;
            if (align != 0) {
                int limit = 8 - align;
                for (int i = 0; i < limit; ++i) {
                    this.buffer.put((byte)0);
                }
                bytesToWrite -= limit;
            }
            while (bytesToWrite >= 8) {
                this.buffer.putLong(0L);
                bytesToWrite -= 8;
            }
        }
        while (this.buffer.hasRemaining()) {
            this.buffer.put((byte)0);
        }
    }

    private static class BufferAtATimeOutputChannel
    implements WritableByteChannel {
        private final OutputStream out;
        private final AtomicBoolean closed = new AtomicBoolean(false);

        private BufferAtATimeOutputChannel(OutputStream out) {
            this.out = out;
        }

        @Override
        public int write(ByteBuffer buffer) throws IOException {
            if (!this.isOpen()) {
                throw new ClosedChannelException();
            }
            if (!buffer.hasArray()) {
                throw new IllegalArgumentException("Direct buffer somehow written to BufferAtATimeOutputChannel");
            }
            try {
                int pos = buffer.position();
                int len = buffer.limit() - pos;
                this.out.write(buffer.array(), buffer.arrayOffset() + pos, len);
                buffer.position(buffer.limit());
                return len;
            } catch (IOException e) {
                try {
                    this.close();
                } catch (IOException iOException) {
                    // empty catch block
                }
                throw e;
            }
        }

        @Override
        public boolean isOpen() {
            return !this.closed.get();
        }

        @Override
        public void close() throws IOException {
            if (this.closed.compareAndSet(false, true)) {
                this.out.close();
            }
        }
    }
}

