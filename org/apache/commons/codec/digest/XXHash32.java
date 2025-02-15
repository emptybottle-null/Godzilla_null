/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

import java.util.zip.Checksum;

public class XXHash32
implements Checksum {
    private static final int BUF_SIZE = 16;
    private static final int ROTATE_BITS = 13;
    private static final int PRIME1 = -1640531535;
    private static final int PRIME2 = -2048144777;
    private static final int PRIME3 = -1028477379;
    private static final int PRIME4 = 668265263;
    private static final int PRIME5 = 374761393;
    private final byte[] oneByte = new byte[1];
    private final int[] state = new int[4];
    private final byte[] buffer = new byte[16];
    private final int seed;
    private int totalLen;
    private int pos;

    public XXHash32() {
        this(0);
    }

    public XXHash32(int seed) {
        this.seed = seed;
        this.initializeState();
    }

    @Override
    public void reset() {
        this.initializeState();
        this.totalLen = 0;
        this.pos = 0;
    }

    @Override
    public void update(int b) {
        this.oneByte[0] = (byte)(b & 0xFF);
        this.update(this.oneByte, 0, 1);
    }

    @Override
    public void update(byte[] b, int off, int len) {
        if (len <= 0) {
            return;
        }
        this.totalLen += len;
        int end = off + len;
        if (this.pos + len < 16) {
            System.arraycopy(b, off, this.buffer, this.pos, len);
            this.pos += len;
            return;
        }
        if (this.pos > 0) {
            int size = 16 - this.pos;
            System.arraycopy(b, off, this.buffer, this.pos, size);
            this.process(this.buffer, 0);
            off += size;
        }
        int limit = end - 16;
        while (off <= limit) {
            this.process(b, off);
            off += 16;
        }
        if (off < end) {
            this.pos = end - off;
            System.arraycopy(b, off, this.buffer, 0, this.pos);
        }
    }

    @Override
    public long getValue() {
        int idx;
        int hash = this.totalLen > 16 ? Integer.rotateLeft(this.state[0], 1) + Integer.rotateLeft(this.state[1], 7) + Integer.rotateLeft(this.state[2], 12) + Integer.rotateLeft(this.state[3], 18) : this.state[2] + 374761393;
        hash += this.totalLen;
        int limit = this.pos - 4;
        for (idx = 0; idx <= limit; idx += 4) {
            hash = Integer.rotateLeft(hash + XXHash32.getInt(this.buffer, idx) * -1028477379, 17) * 668265263;
        }
        while (idx < this.pos) {
            hash = Integer.rotateLeft(hash + (this.buffer[idx++] & 0xFF) * 374761393, 11) * -1640531535;
        }
        hash ^= hash >>> 15;
        hash *= -2048144777;
        hash ^= hash >>> 13;
        hash *= -1028477379;
        hash ^= hash >>> 16;
        return (long)hash & 0xFFFFFFFFL;
    }

    private static int getInt(byte[] buffer, int idx) {
        return (int)(XXHash32.fromLittleEndian(buffer, idx, 4) & 0xFFFFFFFFL);
    }

    private void initializeState() {
        this.state[0] = this.seed + -1640531535 + -2048144777;
        this.state[1] = this.seed + -2048144777;
        this.state[2] = this.seed;
        this.state[3] = this.seed - -1640531535;
    }

    private void process(byte[] b, int offset) {
        int s0 = this.state[0];
        int s1 = this.state[1];
        int s2 = this.state[2];
        int s3 = this.state[3];
        s0 = Integer.rotateLeft(s0 + XXHash32.getInt(b, offset) * -2048144777, 13) * -1640531535;
        s1 = Integer.rotateLeft(s1 + XXHash32.getInt(b, offset + 4) * -2048144777, 13) * -1640531535;
        s2 = Integer.rotateLeft(s2 + XXHash32.getInt(b, offset + 8) * -2048144777, 13) * -1640531535;
        s3 = Integer.rotateLeft(s3 + XXHash32.getInt(b, offset + 12) * -2048144777, 13) * -1640531535;
        this.state[0] = s0;
        this.state[1] = s1;
        this.state[2] = s2;
        this.state[3] = s3;
        this.pos = 0;
    }

    private static long fromLittleEndian(byte[] bytes, int off, int length) {
        if (length > 8) {
            throw new IllegalArgumentException("can't read more than eight bytes into a long value");
        }
        long l = 0L;
        for (int i = 0; i < length; ++i) {
            l |= ((long)bytes[off + i] & 0xFFL) << 8 * i;
        }
        return l;
    }
}

