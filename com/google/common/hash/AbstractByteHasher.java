/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.common.hash.AbstractHasher;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@CanIgnoreReturnValue
abstract class AbstractByteHasher
extends AbstractHasher {
    private final ByteBuffer scratch = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

    AbstractByteHasher() {
    }

    protected abstract void update(byte var1);

    protected void update(byte[] b) {
        this.update(b, 0, b.length);
    }

    protected void update(byte[] b, int off, int len) {
        for (int i = off; i < off + len; ++i) {
            this.update(b[i]);
        }
    }

    protected void update(ByteBuffer b) {
        if (b.hasArray()) {
            this.update(b.array(), b.arrayOffset() + b.position(), b.remaining());
            b.position(b.limit());
        } else {
            for (int remaining = b.remaining(); remaining > 0; --remaining) {
                this.update(b.get());
            }
        }
    }

    private Hasher update(int bytes) {
        try {
            this.update(this.scratch.array(), 0, bytes);
        } finally {
            this.scratch.clear();
        }
        return this;
    }

    @Override
    public Hasher putByte(byte b) {
        this.update(b);
        return this;
    }

    @Override
    public Hasher putBytes(byte[] bytes) {
        Preconditions.checkNotNull(bytes);
        this.update(bytes);
        return this;
    }

    @Override
    public Hasher putBytes(byte[] bytes, int off, int len) {
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        this.update(bytes, off, len);
        return this;
    }

    @Override
    public Hasher putBytes(ByteBuffer bytes) {
        this.update(bytes);
        return this;
    }

    @Override
    public Hasher putShort(short s) {
        this.scratch.putShort(s);
        return this.update(2);
    }

    @Override
    public Hasher putInt(int i) {
        this.scratch.putInt(i);
        return this.update(4);
    }

    @Override
    public Hasher putLong(long l) {
        this.scratch.putLong(l);
        return this.update(8);
    }

    @Override
    public Hasher putChar(char c) {
        this.scratch.putChar(c);
        return this.update(2);
    }
}

