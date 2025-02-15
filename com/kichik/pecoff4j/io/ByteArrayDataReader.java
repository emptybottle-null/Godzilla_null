/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.io;

import com.kichik.pecoff4j.io.IDataReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayDataReader
implements IDataReader {
    private byte[] data;
    private int position;
    private int offset;
    private int length;

    public ByteArrayDataReader(byte[] data) {
        this.data = data;
        this.length = data.length;
    }

    public ByteArrayDataReader(byte[] data, int offset, int length) {
        this.data = data;
        this.offset = offset;
        this.length = length;
        if (this.length + this.offset > data.length) {
            throw new IndexOutOfBoundsException("length [" + length + "] + offset [" + offset + "] > data.length [" + data.length + "]");
        }
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public boolean hasMore() throws IOException {
        return this.position < this.length;
    }

    @Override
    public void jumpTo(int location) throws IOException {
        this.position = location;
    }

    @Override
    public void read(byte[] b) throws IOException {
        for (int i = 0; i < b.length; ++i) {
            b[i] = this.data[this.offset + this.position + i];
        }
        this.position += b.length;
    }

    @Override
    public int readByte() throws IOException {
        if (!this.hasMore()) {
            throw new EOFException("End of stream");
        }
        return (char)(this.data[this.offset + this.position++] & 0xFF);
    }

    @Override
    public long readLong() throws IOException {
        return (long)this.readDoubleWord() | (long)this.readDoubleWord() << 32;
    }

    @Override
    public int readDoubleWord() throws IOException {
        return this.readWord() | this.readWord() << 16;
    }

    @Override
    public String readUtf(int size) throws IOException {
        byte[] b = new byte[size];
        this.read(b);
        return new String(b);
    }

    @Override
    public String readUtf() throws IOException {
        char c;
        StringBuilder sb = new StringBuilder();
        while ((c = (char)this.readByte()) != '\u0000') {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public int readWord() throws IOException {
        return this.readByte() | this.readByte() << 8;
    }

    @Override
    public void skipBytes(int numBytes) throws IOException {
        this.position += numBytes;
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
        byte[] result = Arrays.copyOfRange(this.data, this.offset + this.position, this.offset + this.length);
        this.position = this.length;
        return result;
    }
}

