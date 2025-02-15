/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.ByteArrayInputStream;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianInput;

public class LittleEndianByteArrayInputStream
extends ByteArrayInputStream
implements LittleEndianInput {
    public LittleEndianByteArrayInputStream(byte[] buf, int offset, int length) {
        super(buf, offset, length);
    }

    public LittleEndianByteArrayInputStream(byte[] buf, int offset) {
        this(buf, offset, buf.length - offset);
    }

    public LittleEndianByteArrayInputStream(byte[] buf) {
        super(buf);
    }

    protected void checkPosition(int i) {
        if (i > this.count - this.pos) {
            throw new RuntimeException("Buffer overrun, having " + this.count + " bytes in the stream and position is at " + this.pos + ", but trying to increment position by " + i);
        }
    }

    public int getReadIndex() {
        return this.pos;
    }

    public void setReadIndex(int pos) {
        if (pos < 0 || pos >= this.count) {
            throw new IndexOutOfBoundsException();
        }
        this.pos = pos;
    }

    @Override
    public byte readByte() {
        this.checkPosition(1);
        return (byte)this.read();
    }

    @Override
    public int readInt() {
        int size = 4;
        this.checkPosition(4);
        int le = LittleEndian.getInt(this.buf, this.pos);
        long skipped = super.skip(4L);
        assert (skipped == 4L) : "Buffer overrun";
        return le;
    }

    @Override
    public long readLong() {
        int size = 8;
        this.checkPosition(8);
        long le = LittleEndian.getLong(this.buf, this.pos);
        long skipped = super.skip(8L);
        assert (skipped == 8L) : "Buffer overrun";
        return le;
    }

    @Override
    public short readShort() {
        int size = 2;
        this.checkPosition(2);
        short le = LittleEndian.getShort(this.buf, this.pos);
        long skipped = super.skip(2L);
        assert (skipped == 2L) : "Buffer overrun";
        return le;
    }

    @Override
    public int readUByte() {
        return this.readByte() & 0xFF;
    }

    @Override
    public int readUShort() {
        return this.readShort() & 0xFFFF;
    }

    public long readUInt() {
        return (long)this.readInt() & 0xFFFFFFFFL;
    }

    @Override
    public double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }

    @Override
    public void readFully(byte[] buffer, int off, int len) {
        this.checkPosition(len);
        this.read(buffer, off, len);
    }

    @Override
    public void readFully(byte[] buffer) {
        this.checkPosition(buffer.length);
        this.read(buffer, 0, buffer.length);
    }

    @Override
    public void readPlain(byte[] buf, int off, int len) {
        this.readFully(buf, off, len);
    }
}

