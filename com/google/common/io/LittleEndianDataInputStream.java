/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Beta
@GwtIncompatible
public final class LittleEndianDataInputStream
extends FilterInputStream
implements DataInput {
    public LittleEndianDataInputStream(InputStream in) {
        super(Preconditions.checkNotNull(in));
    }

    @Override
    @CanIgnoreReturnValue
    public String readLine() {
        throw new UnsupportedOperationException("readLine is not supported");
    }

    @Override
    public void readFully(byte[] b) throws IOException {
        ByteStreams.readFully(this, b);
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        ByteStreams.readFully(this, b, off, len);
    }

    @Override
    public int skipBytes(int n) throws IOException {
        return (int)this.in.skip(n);
    }

    @Override
    @CanIgnoreReturnValue
    public int readUnsignedByte() throws IOException {
        int b1 = this.in.read();
        if (0 > b1) {
            throw new EOFException();
        }
        return b1;
    }

    @Override
    @CanIgnoreReturnValue
    public int readUnsignedShort() throws IOException {
        byte b1 = this.readAndCheckByte();
        byte b2 = this.readAndCheckByte();
        return Ints.fromBytes((byte)0, (byte)0, b2, b1);
    }

    @Override
    @CanIgnoreReturnValue
    public int readInt() throws IOException {
        byte b1 = this.readAndCheckByte();
        byte b2 = this.readAndCheckByte();
        byte b3 = this.readAndCheckByte();
        byte b4 = this.readAndCheckByte();
        return Ints.fromBytes(b4, b3, b2, b1);
    }

    @Override
    @CanIgnoreReturnValue
    public long readLong() throws IOException {
        byte b1 = this.readAndCheckByte();
        byte b2 = this.readAndCheckByte();
        byte b3 = this.readAndCheckByte();
        byte b4 = this.readAndCheckByte();
        byte b5 = this.readAndCheckByte();
        byte b6 = this.readAndCheckByte();
        byte b7 = this.readAndCheckByte();
        byte b8 = this.readAndCheckByte();
        return Longs.fromBytes(b8, b7, b6, b5, b4, b3, b2, b1);
    }

    @Override
    @CanIgnoreReturnValue
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }

    @Override
    @CanIgnoreReturnValue
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.readLong());
    }

    @Override
    @CanIgnoreReturnValue
    public String readUTF() throws IOException {
        return new DataInputStream(this.in).readUTF();
    }

    @Override
    @CanIgnoreReturnValue
    public short readShort() throws IOException {
        return (short)this.readUnsignedShort();
    }

    @Override
    @CanIgnoreReturnValue
    public char readChar() throws IOException {
        return (char)this.readUnsignedShort();
    }

    @Override
    @CanIgnoreReturnValue
    public byte readByte() throws IOException {
        return (byte)this.readUnsignedByte();
    }

    @Override
    @CanIgnoreReturnValue
    public boolean readBoolean() throws IOException {
        return this.readUnsignedByte() != 0;
    }

    private byte readAndCheckByte() throws IOException, EOFException {
        int b1 = this.in.read();
        if (-1 == b1) {
            throw new EOFException();
        }
        return (byte)b1;
    }
}

