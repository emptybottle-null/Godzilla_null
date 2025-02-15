/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import com.kichik.pecoff4j.io.IDataReader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class DataReader
implements IDataReader {
    private InputStream dis;
    private int position = 0;

    public DataReader(byte[] buffer) {
        this.dis = new BufferedInputStream(new ByteArrayInputStream(buffer));
    }

    public DataReader(byte[] buffer, int offset, int length) {
        this.dis = new BufferedInputStream(new ByteArrayInputStream(buffer, offset, length));
    }

    public DataReader(InputStream is) {
        this.dis = is instanceof BufferedInputStream ? is : new BufferedInputStream(is);
    }

    @Override
    public int readByte() throws IOException {
        ++this.position;
        return this.safeRead();
    }

    @Override
    public int readWord() throws IOException {
        this.position += 2;
        return this.safeRead() | this.safeRead() << 8;
    }

    @Override
    public long readLong() throws IOException {
        return (long)this.readDoubleWord() & 0xFFFFFFFFL | (long)this.readDoubleWord() << 32;
    }

    @Override
    public int readDoubleWord() throws IOException {
        this.position += 4;
        return this.safeRead() | this.safeRead() << 8 | this.safeRead() << 16 | this.safeRead() << 24;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public boolean hasMore() throws IOException {
        return this.dis.available() > 0;
    }

    @Override
    public void jumpTo(int location) throws IOException {
        if (location < this.position) {
            throw new IOException("DataReader does not support scanning backwards (" + location + ")");
        }
        if (location > this.position) {
            this.skipBytes(location - this.position);
        }
    }

    @Override
    public void skipBytes(int numBytes) throws IOException {
        this.position += numBytes;
        for (int i = 0; i < numBytes; ++i) {
            this.safeRead();
        }
    }

    @Override
    public void close() throws IOException {
        this.dis.close();
    }

    @Override
    public void read(byte[] b) throws IOException {
        this.position += b.length;
        this.safeRead(b);
    }

    @Override
    public String readUtf(int size) throws IOException {
        int i;
        this.position += size;
        byte[] b = new byte[size];
        this.safeRead(b);
        for (i = 0; i < b.length && b[i] != 0; ++i) {
        }
        return new String(b, 0, i);
    }

    @Override
    public String readUtf() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c = 0;
        while ((c = this.readByte()) != 0) {
            if (c == -1) {
                throw new IOException("Unexpected end of stream");
            }
            sb.append((char)c);
        }
        return sb.toString();
    }

    @Override
    public String readUnicode() throws IOException {
        StringBuilder sb = new StringBuilder();
        char c = '\u0000';
        while ((c = (char)this.readWord()) != '\u0000') {
            sb.append(c);
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Override
    public String readUnicode(int size) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            sb.append((char)this.readWord());
        }
        return sb.toString();
    }

    @Override
    public byte[] readAll() throws IOException {
        byte[] all = new byte[this.dis.available()];
        this.read(all);
        return all;
    }

    private int safeRead() throws IOException {
        int b = this.dis.read();
        if (b == -1) {
            throw new EOFException("Expected to read bytes from the stream");
        }
        return b;
    }

    private void safeRead(byte[] b) throws IOException {
        int read = this.dis.read(b);
        if (read != b.length) {
            throw new EOFException("Expected to read bytes from the stream");
        }
    }
}

