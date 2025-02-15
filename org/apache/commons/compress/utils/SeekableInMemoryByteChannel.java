/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class SeekableInMemoryByteChannel
implements SeekableByteChannel {
    private static final int NAIVE_RESIZE_LIMIT = 0x3FFFFFFF;
    private byte[] data;
    private final AtomicBoolean closed = new AtomicBoolean();
    private int position;
    private int size;

    public SeekableInMemoryByteChannel(byte[] data) {
        this.data = data;
        this.size = data.length;
    }

    public SeekableInMemoryByteChannel() {
        this(new byte[0]);
    }

    public SeekableInMemoryByteChannel(int size) {
        this(new byte[size]);
    }

    @Override
    public long position() {
        return this.position;
    }

    @Override
    public SeekableByteChannel position(long newPosition) throws IOException {
        this.ensureOpen();
        if (newPosition < 0L || newPosition > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Position has to be in range 0.. 2147483647");
        }
        this.position = (int)newPosition;
        return this;
    }

    @Override
    public long size() {
        return this.size;
    }

    @Override
    public SeekableByteChannel truncate(long newSize) {
        if ((long)this.size > newSize) {
            this.size = (int)newSize;
        }
        this.repositionIfNecessary();
        return this;
    }

    @Override
    public int read(ByteBuffer buf) throws IOException {
        this.ensureOpen();
        this.repositionIfNecessary();
        int wanted = buf.remaining();
        int possible = this.size - this.position;
        if (possible <= 0) {
            return -1;
        }
        if (wanted > possible) {
            wanted = possible;
        }
        buf.put(this.data, this.position, wanted);
        this.position += wanted;
        return wanted;
    }

    @Override
    public void close() {
        this.closed.set(true);
    }

    @Override
    public boolean isOpen() {
        return !this.closed.get();
    }

    @Override
    public int write(ByteBuffer b) throws IOException {
        this.ensureOpen();
        int wanted = b.remaining();
        int possibleWithoutResize = this.size - this.position;
        if (wanted > possibleWithoutResize) {
            int newSize = this.position + wanted;
            if (newSize < 0) {
                this.resize(Integer.MAX_VALUE);
                wanted = Integer.MAX_VALUE - this.position;
            } else {
                this.resize(newSize);
            }
        }
        b.get(this.data, this.position, wanted);
        this.position += wanted;
        if (this.size < this.position) {
            this.size = this.position;
        }
        return wanted;
    }

    public byte[] array() {
        return this.data;
    }

    private void resize(int newLength) {
        int len = this.data.length;
        if (len <= 0) {
            len = 1;
        }
        if (newLength < 0x3FFFFFFF) {
            while (len < newLength) {
                len <<= 1;
            }
        } else {
            len = newLength;
        }
        this.data = Arrays.copyOf(this.data, len);
    }

    private void ensureOpen() throws ClosedChannelException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
    }

    private void repositionIfNecessary() {
        if (this.position > this.size) {
            this.position = this.size;
        }
    }
}

