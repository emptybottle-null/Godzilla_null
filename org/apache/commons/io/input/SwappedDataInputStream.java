/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io.input;

import java.io.DataInput;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.EndianUtils;
import org.apache.commons.io.input.ProxyInputStream;
import org.apache.commons.io.input.UnsupportedOperationExceptions;

public class SwappedDataInputStream
extends ProxyInputStream
implements DataInput {
    public SwappedDataInputStream(InputStream input) {
        super(input);
    }

    @Override
    public boolean readBoolean() throws IOException, EOFException {
        return 0 != this.readByte();
    }

    @Override
    public byte readByte() throws IOException, EOFException {
        return (byte)this.in.read();
    }

    @Override
    public char readChar() throws IOException, EOFException {
        return (char)this.readShort();
    }

    @Override
    public double readDouble() throws IOException, EOFException {
        return EndianUtils.readSwappedDouble(this.in);
    }

    @Override
    public float readFloat() throws IOException, EOFException {
        return EndianUtils.readSwappedFloat(this.in);
    }

    @Override
    public void readFully(byte[] data) throws IOException, EOFException {
        this.readFully(data, 0, data.length);
    }

    @Override
    public void readFully(byte[] data, int offset, int length) throws IOException, EOFException {
        int count;
        for (int remaining = length; remaining > 0; remaining -= count) {
            int location = offset + length - remaining;
            count = this.read(data, location, remaining);
            if (-1 != count) continue;
            throw new EOFException();
        }
    }

    @Override
    public int readInt() throws IOException, EOFException {
        return EndianUtils.readSwappedInteger(this.in);
    }

    @Override
    public String readLine() throws IOException, EOFException {
        throw UnsupportedOperationExceptions.method("readLine");
    }

    @Override
    public long readLong() throws IOException, EOFException {
        return EndianUtils.readSwappedLong(this.in);
    }

    @Override
    public short readShort() throws IOException, EOFException {
        return EndianUtils.readSwappedShort(this.in);
    }

    @Override
    public int readUnsignedByte() throws IOException, EOFException {
        return this.in.read();
    }

    @Override
    public int readUnsignedShort() throws IOException, EOFException {
        return EndianUtils.readSwappedUnsignedShort(this.in);
    }

    @Override
    public String readUTF() throws IOException, EOFException {
        throw UnsupportedOperationExceptions.method("readUTF");
    }

    @Override
    public int skipBytes(int count) throws IOException, EOFException {
        return (int)this.in.skip(count);
    }
}

