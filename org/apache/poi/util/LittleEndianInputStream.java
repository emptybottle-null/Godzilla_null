/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.SuppressForbidden;

public class LittleEndianInputStream
extends FilterInputStream
implements LittleEndianInput {
    private static final int BUFFERED_SIZE = 8096;
    private static final int EOF = -1;
    private int readIndex = 0;
    private int markIndex = -1;

    public LittleEndianInputStream(InputStream is) {
        super(is.markSupported() ? is : new BufferedInputStream(is, 8096));
    }

    @Override
    @SuppressForbidden(value="just delegating")
    public int available() {
        try {
            return super.available();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte readByte() {
        return (byte)this.readUByte();
    }

    @Override
    public int readUByte() {
        byte[] buf = new byte[1];
        try {
            LittleEndianInputStream.checkEOF(this.read(buf), 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LittleEndian.getUByte(buf);
    }

    public float readFloat() {
        return Float.intBitsToFloat(this.readInt());
    }

    @Override
    public double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }

    @Override
    public int readInt() {
        byte[] buf = new byte[4];
        try {
            LittleEndianInputStream.checkEOF(this.read(buf), buf.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LittleEndian.getInt(buf);
    }

    public long readUInt() {
        long retNum = this.readInt();
        return retNum & 0xFFFFFFFFL;
    }

    @Override
    public long readLong() {
        byte[] buf = new byte[8];
        try {
            LittleEndianInputStream.checkEOF(this.read(buf), 8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LittleEndian.getLong(buf);
    }

    @Override
    public short readShort() {
        return (short)this.readUShort();
    }

    @Override
    public int readUShort() {
        byte[] buf = new byte[2];
        try {
            LittleEndianInputStream.checkEOF(this.read(buf), 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LittleEndian.getUShort(buf);
    }

    private static void checkEOF(int actualBytes, int expectedBytes) {
        if (expectedBytes != 0 && (actualBytes == -1 || actualBytes != expectedBytes)) {
            throw new RuntimeException("Unexpected end-of-file");
        }
    }

    @Override
    public void readFully(byte[] buf) {
        this.readFully(buf, 0, buf.length);
    }

    @Override
    public void readFully(byte[] buf, int off, int len) {
        try {
            LittleEndianInputStream.checkEOF(this._read(buf, off, len), len);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int readBytes = super.read(b, off, len);
        this.readIndex += readBytes;
        return readBytes;
    }

    @Override
    public synchronized void mark(int readlimit) {
        super.mark(readlimit);
        this.markIndex = this.readIndex;
    }

    @Override
    public synchronized void reset() throws IOException {
        super.reset();
        if (this.markIndex > -1) {
            this.readIndex = this.markIndex;
            this.markIndex = -1;
        }
    }

    public int getReadIndex() {
        return this.readIndex;
    }

    private int _read(byte[] buffer, int offset, int length) throws IOException {
        int location;
        int remaining;
        int count;
        for (remaining = length; remaining > 0 && -1 != (count = this.read(buffer, offset + (location = length - remaining), remaining)); remaining -= count) {
        }
        return length - remaining;
    }

    @Override
    public void readPlain(byte[] buf, int off, int len) {
        this.readFully(buf, off, len);
    }

    public void skipFully(int len) throws IOException {
        if (len == 0) {
            return;
        }
        long skipped = IOUtils.skipFully(this, len);
        if (skipped > Integer.MAX_VALUE) {
            throw new IOException("can't skip further than 2147483647");
        }
        LittleEndianInputStream.checkEOF((int)skipped, len);
    }
}

