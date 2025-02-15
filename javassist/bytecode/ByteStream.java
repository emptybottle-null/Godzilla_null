/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.IOException;
import java.io.OutputStream;

final class ByteStream
extends OutputStream {
    private byte[] buf;
    private int count;

    public ByteStream() {
        this(32);
    }

    public ByteStream(int size) {
        this.buf = new byte[size];
        this.count = 0;
    }

    public int getPos() {
        return this.count;
    }

    public int size() {
        return this.count;
    }

    public void writeBlank(int len) {
        this.enlarge(len);
        this.count += len;
    }

    @Override
    public void write(byte[] data) {
        this.write(data, 0, data.length);
    }

    @Override
    public void write(byte[] data, int off, int len) {
        this.enlarge(len);
        System.arraycopy(data, off, this.buf, this.count, len);
        this.count += len;
    }

    @Override
    public void write(int b) {
        this.enlarge(1);
        int oldCount = this.count;
        this.buf[oldCount] = (byte)b;
        this.count = oldCount + 1;
    }

    public void writeShort(int s) {
        this.enlarge(2);
        int oldCount = this.count;
        this.buf[oldCount] = (byte)(s >>> 8);
        this.buf[oldCount + 1] = (byte)s;
        this.count = oldCount + 2;
    }

    public void writeInt(int i) {
        this.enlarge(4);
        int oldCount = this.count;
        this.buf[oldCount] = (byte)(i >>> 24);
        this.buf[oldCount + 1] = (byte)(i >>> 16);
        this.buf[oldCount + 2] = (byte)(i >>> 8);
        this.buf[oldCount + 3] = (byte)i;
        this.count = oldCount + 4;
    }

    public void writeLong(long i) {
        this.enlarge(8);
        int oldCount = this.count;
        this.buf[oldCount] = (byte)(i >>> 56);
        this.buf[oldCount + 1] = (byte)(i >>> 48);
        this.buf[oldCount + 2] = (byte)(i >>> 40);
        this.buf[oldCount + 3] = (byte)(i >>> 32);
        this.buf[oldCount + 4] = (byte)(i >>> 24);
        this.buf[oldCount + 5] = (byte)(i >>> 16);
        this.buf[oldCount + 6] = (byte)(i >>> 8);
        this.buf[oldCount + 7] = (byte)i;
        this.count = oldCount + 8;
    }

    public void writeFloat(float v) {
        this.writeInt(Float.floatToIntBits(v));
    }

    public void writeDouble(double v) {
        this.writeLong(Double.doubleToLongBits(v));
    }

    public void writeUTF(String s) {
        int sLen = s.length();
        int pos = this.count;
        this.enlarge(sLen + 2);
        byte[] buffer = this.buf;
        buffer[pos++] = (byte)(sLen >>> 8);
        buffer[pos++] = (byte)sLen;
        for (int i = 0; i < sLen; ++i) {
            char c = s.charAt(i);
            if ('\u0001' > c || c > '\u007f') {
                this.writeUTF2(s, sLen, i);
                return;
            }
            buffer[pos++] = (byte)c;
        }
        this.count = pos;
    }

    private void writeUTF2(String s, int sLen, int offset) {
        int size = sLen;
        for (int i = offset; i < sLen; ++i) {
            char c = s.charAt(i);
            if (c > '\u07ff') {
                size += 2;
                continue;
            }
            if (c != '\u0000' && c <= '\u007f') continue;
            ++size;
        }
        if (size > 65535) {
            throw new RuntimeException("encoded string too long: " + sLen + size + " bytes");
        }
        this.enlarge(size + 2);
        int pos = this.count;
        byte[] buffer = this.buf;
        buffer[pos] = (byte)(size >>> 8);
        buffer[pos + 1] = (byte)size;
        pos += 2 + offset;
        for (int j = offset; j < sLen; ++j) {
            char c = s.charAt(j);
            if ('\u0001' <= c && c <= '\u007f') {
                buffer[pos++] = (byte)c;
                continue;
            }
            if (c > '\u07ff') {
                buffer[pos] = (byte)(0xE0 | c >> 12 & 0xF);
                buffer[pos + 1] = (byte)(0x80 | c >> 6 & 0x3F);
                buffer[pos + 2] = (byte)(0x80 | c & 0x3F);
                pos += 3;
                continue;
            }
            buffer[pos] = (byte)(0xC0 | c >> 6 & 0x1F);
            buffer[pos + 1] = (byte)(0x80 | c & 0x3F);
            pos += 2;
        }
        this.count = pos;
    }

    public void write(int pos, int value) {
        this.buf[pos] = (byte)value;
    }

    public void writeShort(int pos, int value) {
        this.buf[pos] = (byte)(value >>> 8);
        this.buf[pos + 1] = (byte)value;
    }

    public void writeInt(int pos, int value) {
        this.buf[pos] = (byte)(value >>> 24);
        this.buf[pos + 1] = (byte)(value >>> 16);
        this.buf[pos + 2] = (byte)(value >>> 8);
        this.buf[pos + 3] = (byte)value;
    }

    public byte[] toByteArray() {
        byte[] buf2 = new byte[this.count];
        System.arraycopy(this.buf, 0, buf2, 0, this.count);
        return buf2;
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(this.buf, 0, this.count);
    }

    public void enlarge(int delta) {
        int newCount = this.count + delta;
        if (newCount > this.buf.length) {
            int newLen = this.buf.length << 1;
            byte[] newBuf = new byte[newLen > newCount ? newLen : newCount];
            System.arraycopy(this.buf, 0, newBuf, 0, this.count);
            this.buf = newBuf;
        }
    }
}

