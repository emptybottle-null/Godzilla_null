/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.poi.util.IOUtils;

public class RLEDecompressingInputStream
extends InputStream {
    private static final int[] POWER2 = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
    private final InputStream in;
    private final byte[] buf;
    private int pos;
    private int len;

    public RLEDecompressingInputStream(InputStream in) throws IOException {
        this.in = in;
        this.buf = new byte[4096];
        this.pos = 0;
        int header = in.read();
        if (header != 1) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Header byte 0x01 expected, received 0x%02X", header & 0xFF));
        }
        this.len = this.readChunk();
    }

    @Override
    public int read() throws IOException {
        if (this.len == -1) {
            return -1;
        }
        if (this.pos >= this.len && (this.len = this.readChunk()) == -1) {
            return -1;
        }
        return this.buf[this.pos++] & 0xFF;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int l) throws IOException {
        if (this.len == -1) {
            return -1;
        }
        int offset = off;
        int length = l;
        while (length > 0) {
            if (this.pos >= this.len && (this.len = this.readChunk()) == -1) {
                return offset > off ? offset - off : -1;
            }
            int c = Math.min(length, this.len - this.pos);
            System.arraycopy(this.buf, this.pos, b, offset, c);
            this.pos += c;
            length -= c;
            offset += c;
        }
        return l;
    }

    @Override
    public long skip(long n) throws IOException {
        int c;
        for (long length = n; length > 0L; length -= (long)c) {
            if (this.pos >= this.len && (this.len = this.readChunk()) == -1) {
                return -1L;
            }
            c = (int)Math.min(n, (long)(this.len - this.pos));
            this.pos += c;
        }
        return n;
    }

    @Override
    public int available() {
        return this.len > 0 ? this.len - this.pos : 0;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    private int readChunk() throws IOException {
        boolean rawChunk;
        this.pos = 0;
        int w = this.readShort(this.in);
        if (w == -1 || w == 0) {
            return -1;
        }
        int chunkSize = (w & 0xFFF) + 1;
        if ((w & 0x7000) != 12288) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Chunksize header A should be 0x3000, received 0x%04X", w & 0xE000));
        }
        boolean bl = rawChunk = (w & 0x8000) == 0;
        if (rawChunk) {
            if (IOUtils.readFully(this.in, this.buf, 0, chunkSize) < chunkSize) {
                throw new IllegalStateException(String.format(Locale.ROOT, "Not enough bytes read, expected %d", chunkSize));
            }
            return chunkSize;
        }
        int inOffset = 0;
        int outOffset = 0;
        while (inOffset < chunkSize) {
            int tokenFlags = this.in.read();
            ++inOffset;
            if (tokenFlags == -1) break;
            for (int n = 0; n < 8 && inOffset < chunkSize; ++n) {
                if ((tokenFlags & POWER2[n]) == 0) {
                    int b = this.in.read();
                    if (b == -1) {
                        return -1;
                    }
                    this.buf[outOffset++] = (byte)b;
                    ++inOffset;
                    continue;
                }
                int token = this.readShort(this.in);
                if (token == -1) {
                    return -1;
                }
                inOffset += 2;
                int copyLenBits = RLEDecompressingInputStream.getCopyLenBits(outOffset - 1);
                int copyOffset = (token >> copyLenBits) + 1;
                int copyLen = (token & POWER2[copyLenBits] - 1) + 3;
                int startPos = outOffset - copyOffset;
                int endPos = startPos + copyLen;
                for (int i = startPos; i < endPos; ++i) {
                    this.buf[outOffset++] = this.buf[i];
                }
            }
        }
        return outOffset;
    }

    static int getCopyLenBits(int offset) {
        for (int n = 11; n >= 4; --n) {
            if ((offset & POWER2[n]) == 0) continue;
            return 15 - n;
        }
        return 12;
    }

    public int readShort() throws IOException {
        return this.readShort(this);
    }

    public int readInt() throws IOException {
        return this.readInt(this);
    }

    private int readShort(InputStream stream) throws IOException {
        int b0 = stream.read();
        if (b0 == -1) {
            return -1;
        }
        int b1 = stream.read();
        if (b1 == -1) {
            return -1;
        }
        return b0 & 0xFF | (b1 & 0xFF) << 8;
    }

    private int readInt(InputStream stream) throws IOException {
        int b0 = stream.read();
        if (b0 == -1) {
            return -1;
        }
        int b1 = stream.read();
        if (b1 == -1) {
            return -1;
        }
        int b2 = stream.read();
        if (b2 == -1) {
            return -1;
        }
        int b3 = stream.read();
        if (b3 == -1) {
            return -1;
        }
        return b0 & 0xFF | (b1 & 0xFF) << 8 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 24;
    }

    public static byte[] decompress(byte[] compressed) throws IOException {
        return RLEDecompressingInputStream.decompress(compressed, 0, compressed.length);
    }

    public static byte[] decompress(byte[] compressed, int offset, int length) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream instream = new ByteArrayInputStream(compressed, offset, length);
        RLEDecompressingInputStream stream = new RLEDecompressingInputStream(instream);
        IOUtils.copy((InputStream)stream, out);
        ((InputStream)stream).close();
        out.close();
        return out.toByteArray();
    }
}

