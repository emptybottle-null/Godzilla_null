/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.AbstractHasher;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@CanIgnoreReturnValue
abstract class AbstractStreamingHasher
extends AbstractHasher {
    private final ByteBuffer buffer;
    private final int bufferSize;
    private final int chunkSize;

    protected AbstractStreamingHasher(int chunkSize) {
        this(chunkSize, chunkSize);
    }

    protected AbstractStreamingHasher(int chunkSize, int bufferSize) {
        Preconditions.checkArgument(bufferSize % chunkSize == 0);
        this.buffer = ByteBuffer.allocate(bufferSize + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = bufferSize;
        this.chunkSize = chunkSize;
    }

    protected abstract void process(ByteBuffer var1);

    protected void processRemaining(ByteBuffer bb) {
        bb.position(bb.limit());
        bb.limit(this.chunkSize + 7);
        while (bb.position() < this.chunkSize) {
            bb.putLong(0L);
        }
        bb.limit(this.chunkSize);
        bb.flip();
        this.process(bb);
    }

    @Override
    public final Hasher putBytes(byte[] bytes, int off, int len) {
        return this.putBytesInternal(ByteBuffer.wrap(bytes, off, len).order(ByteOrder.LITTLE_ENDIAN));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final Hasher putBytes(ByteBuffer readBuffer) {
        ByteOrder order = readBuffer.order();
        try {
            readBuffer.order(ByteOrder.LITTLE_ENDIAN);
            Hasher hasher = this.putBytesInternal(readBuffer);
            return hasher;
        } finally {
            readBuffer.order(order);
        }
    }

    private Hasher putBytesInternal(ByteBuffer readBuffer) {
        if (readBuffer.remaining() <= this.buffer.remaining()) {
            this.buffer.put(readBuffer);
            this.munchIfFull();
            return this;
        }
        int bytesToCopy = this.bufferSize - this.buffer.position();
        for (int i = 0; i < bytesToCopy; ++i) {
            this.buffer.put(readBuffer.get());
        }
        this.munch();
        while (readBuffer.remaining() >= this.chunkSize) {
            this.process(readBuffer);
        }
        this.buffer.put(readBuffer);
        return this;
    }

    @Override
    public final Hasher putByte(byte b) {
        this.buffer.put(b);
        this.munchIfFull();
        return this;
    }

    @Override
    public final Hasher putShort(short s) {
        this.buffer.putShort(s);
        this.munchIfFull();
        return this;
    }

    @Override
    public final Hasher putChar(char c) {
        this.buffer.putChar(c);
        this.munchIfFull();
        return this;
    }

    @Override
    public final Hasher putInt(int i) {
        this.buffer.putInt(i);
        this.munchIfFull();
        return this;
    }

    @Override
    public final Hasher putLong(long l) {
        this.buffer.putLong(l);
        this.munchIfFull();
        return this;
    }

    @Override
    public final HashCode hash() {
        this.munch();
        this.buffer.flip();
        if (this.buffer.remaining() > 0) {
            this.processRemaining(this.buffer);
            this.buffer.position(this.buffer.limit());
        }
        return this.makeHash();
    }

    protected abstract HashCode makeHash();

    private void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            this.munch();
        }
    }

    private void munch() {
        this.buffer.flip();
        while (this.buffer.remaining() >= this.chunkSize) {
            this.process(this.buffer);
        }
        this.buffer.compact();
    }
}

