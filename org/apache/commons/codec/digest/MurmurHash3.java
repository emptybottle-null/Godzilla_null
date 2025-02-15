/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

public final class MurmurHash3 {
    static final int LONG_BYTES = 8;
    static final int INTEGER_BYTES = 4;
    static final int SHORT_BYTES = 2;
    public static final long NULL_HASHCODE = 2862933555777941757L;
    private static final int C1_32 = -862048943;
    private static final int C2_32 = 461845907;
    private static final int R1_32 = 15;
    private static final int R2_32 = 13;
    private static final int M_32 = 5;
    private static final int N_32 = -430675100;
    private static final long C1 = -8663945395140668459L;
    private static final long C2 = 5545529020109919103L;
    private static final int R1 = 31;
    private static final int R2 = 27;
    private static final int R3 = 33;
    private static final int M = 5;
    private static final int N1 = 1390208809;
    private static final int N2 = 944331445;
    public static final int DEFAULT_SEED = 104729;

    private MurmurHash3() {
    }

    public static int hash32(long l0, long l1) {
        return MurmurHash3.hash32(l0, l1, 104729);
    }

    public static int hash32(long l0) {
        return MurmurHash3.hash32(l0, 104729);
    }

    public static int hash32(long l0, int seed) {
        int hash = seed;
        long r0 = Long.reverseBytes(l0);
        hash = MurmurHash3.mix32((int)r0, hash);
        hash = MurmurHash3.mix32((int)(r0 >>> 32), hash);
        return MurmurHash3.fmix32(8, hash);
    }

    public static int hash32(long l0, long l1, int seed) {
        int hash = seed;
        long r0 = Long.reverseBytes(l0);
        long r1 = Long.reverseBytes(l1);
        hash = MurmurHash3.mix32((int)r0, hash);
        hash = MurmurHash3.mix32((int)(r0 >>> 32), hash);
        hash = MurmurHash3.mix32((int)r1, hash);
        hash = MurmurHash3.mix32((int)(r1 >>> 32), hash);
        return MurmurHash3.fmix32(16, hash);
    }

    public static int hash32(byte[] data) {
        return MurmurHash3.hash32(data, 0, data.length, 104729);
    }

    public static int hash32(String data) {
        byte[] origin = data.getBytes();
        return MurmurHash3.hash32(origin, 0, origin.length, 104729);
    }

    public static int hash32(byte[] data, int length) {
        return MurmurHash3.hash32(data, length, 104729);
    }

    public static int hash32(byte[] data, int length, int seed) {
        return MurmurHash3.hash32(data, 0, length, seed);
    }

    public static int hash32(byte[] data, int offset, int length, int seed) {
        int hash = seed;
        int nblocks = length >> 2;
        for (int i = 0; i < nblocks; ++i) {
            int i_4 = i << 2;
            int k = data[offset + i_4] & 0xFF | (data[offset + i_4 + 1] & 0xFF) << 8 | (data[offset + i_4 + 2] & 0xFF) << 16 | (data[offset + i_4 + 3] & 0xFF) << 24;
            hash = MurmurHash3.mix32(k, hash);
        }
        int idx = nblocks << 2;
        int k1 = 0;
        switch (length - idx) {
            case 3: {
                k1 ^= data[offset + idx + 2] << 16;
            }
            case 2: {
                k1 ^= data[offset + idx + 1] << 8;
            }
            case 1: {
                k1 ^= data[offset + idx];
                k1 *= -862048943;
                k1 = Integer.rotateLeft(k1, 15);
                hash ^= (k1 *= 461845907);
            }
        }
        return MurmurHash3.fmix32(length, hash);
    }

    public static long hash64(byte[] data) {
        return MurmurHash3.hash64(data, 0, data.length, 104729);
    }

    public static long hash64(long data) {
        long hash = 104729L;
        long k = Long.reverseBytes(data);
        int length = 8;
        k *= -8663945395140668459L;
        k = Long.rotateLeft(k, 31);
        hash ^= (k *= 5545529020109919103L);
        hash = Long.rotateLeft(hash, 27) * 5L + 1390208809L;
        hash ^= 8L;
        hash = MurmurHash3.fmix64(hash);
        return hash;
    }

    public static long hash64(int data) {
        long k1 = (long)Integer.reverseBytes(data) & 0xFFFFFFFFL;
        int length = 4;
        long hash = 104729L;
        k1 *= -8663945395140668459L;
        k1 = Long.rotateLeft(k1, 31);
        hash ^= (k1 *= 5545529020109919103L);
        hash ^= 4L;
        hash = MurmurHash3.fmix64(hash);
        return hash;
    }

    public static long hash64(short data) {
        long hash = 104729L;
        long k1 = 0L;
        k1 ^= ((long)data & 0xFFL) << 8;
        k1 ^= (long)((data & 0xFF00) >> 8) & 0xFFL;
        k1 *= -8663945395140668459L;
        k1 = Long.rotateLeft(k1, 31);
        hash ^= (k1 *= 5545529020109919103L);
        hash ^= 2L;
        hash = MurmurHash3.fmix64(hash);
        return hash;
    }

    public static long hash64(byte[] data, int offset, int length) {
        return MurmurHash3.hash64(data, offset, length, 104729);
    }

    public static long hash64(byte[] data, int offset, int length, int seed) {
        long hash = seed;
        int nblocks = length >> 3;
        for (int i = 0; i < nblocks; ++i) {
            int i8 = i << 3;
            long k = (long)data[offset + i8] & 0xFFL | ((long)data[offset + i8 + 1] & 0xFFL) << 8 | ((long)data[offset + i8 + 2] & 0xFFL) << 16 | ((long)data[offset + i8 + 3] & 0xFFL) << 24 | ((long)data[offset + i8 + 4] & 0xFFL) << 32 | ((long)data[offset + i8 + 5] & 0xFFL) << 40 | ((long)data[offset + i8 + 6] & 0xFFL) << 48 | ((long)data[offset + i8 + 7] & 0xFFL) << 56;
            k *= -8663945395140668459L;
            k = Long.rotateLeft(k, 31);
            hash ^= (k *= 5545529020109919103L);
            hash = Long.rotateLeft(hash, 27) * 5L + 1390208809L;
        }
        long k1 = 0L;
        int tailStart = nblocks << 3;
        switch (length - tailStart) {
            case 7: {
                k1 ^= ((long)data[offset + tailStart + 6] & 0xFFL) << 48;
            }
            case 6: {
                k1 ^= ((long)data[offset + tailStart + 5] & 0xFFL) << 40;
            }
            case 5: {
                k1 ^= ((long)data[offset + tailStart + 4] & 0xFFL) << 32;
            }
            case 4: {
                k1 ^= ((long)data[offset + tailStart + 3] & 0xFFL) << 24;
            }
            case 3: {
                k1 ^= ((long)data[offset + tailStart + 2] & 0xFFL) << 16;
            }
            case 2: {
                k1 ^= ((long)data[offset + tailStart + 1] & 0xFFL) << 8;
            }
            case 1: {
                k1 ^= (long)data[offset + tailStart] & 0xFFL;
                k1 *= -8663945395140668459L;
                k1 = Long.rotateLeft(k1, 31);
                hash ^= (k1 *= 5545529020109919103L);
            }
        }
        hash ^= (long)length;
        hash = MurmurHash3.fmix64(hash);
        return hash;
    }

    public static long[] hash128(byte[] data) {
        return MurmurHash3.hash128(data, 0, data.length, 104729);
    }

    public static long[] hash128(String data) {
        byte[] origin = data.getBytes();
        return MurmurHash3.hash128(origin, 0, origin.length, 104729);
    }

    public static long[] hash128(byte[] data, int offset, int length, int seed) {
        long h1 = seed;
        long h2 = seed;
        int nblocks = length >> 4;
        for (int i = 0; i < nblocks; ++i) {
            int i16 = i << 4;
            long k1 = (long)data[offset + i16] & 0xFFL | ((long)data[offset + i16 + 1] & 0xFFL) << 8 | ((long)data[offset + i16 + 2] & 0xFFL) << 16 | ((long)data[offset + i16 + 3] & 0xFFL) << 24 | ((long)data[offset + i16 + 4] & 0xFFL) << 32 | ((long)data[offset + i16 + 5] & 0xFFL) << 40 | ((long)data[offset + i16 + 6] & 0xFFL) << 48 | ((long)data[offset + i16 + 7] & 0xFFL) << 56;
            long k2 = (long)data[offset + i16 + 8] & 0xFFL | ((long)data[offset + i16 + 9] & 0xFFL) << 8 | ((long)data[offset + i16 + 10] & 0xFFL) << 16 | ((long)data[offset + i16 + 11] & 0xFFL) << 24 | ((long)data[offset + i16 + 12] & 0xFFL) << 32 | ((long)data[offset + i16 + 13] & 0xFFL) << 40 | ((long)data[offset + i16 + 14] & 0xFFL) << 48 | ((long)data[offset + i16 + 15] & 0xFFL) << 56;
            k1 *= -8663945395140668459L;
            k1 = Long.rotateLeft(k1, 31);
            h1 ^= (k1 *= 5545529020109919103L);
            h1 = Long.rotateLeft(h1, 27);
            h1 += h2;
            h1 = h1 * 5L + 1390208809L;
            k2 *= 5545529020109919103L;
            k2 = Long.rotateLeft(k2, 33);
            h2 ^= (k2 *= -8663945395140668459L);
            h2 = Long.rotateLeft(h2, 31);
            h2 += h1;
            h2 = h2 * 5L + 944331445L;
        }
        long k1 = 0L;
        long k2 = 0L;
        int tailStart = nblocks << 4;
        switch (length - tailStart) {
            case 15: {
                k2 ^= (long)(data[offset + tailStart + 14] & 0xFF) << 48;
            }
            case 14: {
                k2 ^= (long)(data[offset + tailStart + 13] & 0xFF) << 40;
            }
            case 13: {
                k2 ^= (long)(data[offset + tailStart + 12] & 0xFF) << 32;
            }
            case 12: {
                k2 ^= (long)(data[offset + tailStart + 11] & 0xFF) << 24;
            }
            case 11: {
                k2 ^= (long)(data[offset + tailStart + 10] & 0xFF) << 16;
            }
            case 10: {
                k2 ^= (long)(data[offset + tailStart + 9] & 0xFF) << 8;
            }
            case 9: {
                k2 ^= (long)(data[offset + tailStart + 8] & 0xFF);
                k2 *= 5545529020109919103L;
                k2 = Long.rotateLeft(k2, 33);
                h2 ^= (k2 *= -8663945395140668459L);
            }
            case 8: {
                k1 ^= (long)(data[offset + tailStart + 7] & 0xFF) << 56;
            }
            case 7: {
                k1 ^= (long)(data[offset + tailStart + 6] & 0xFF) << 48;
            }
            case 6: {
                k1 ^= (long)(data[offset + tailStart + 5] & 0xFF) << 40;
            }
            case 5: {
                k1 ^= (long)(data[offset + tailStart + 4] & 0xFF) << 32;
            }
            case 4: {
                k1 ^= (long)(data[offset + tailStart + 3] & 0xFF) << 24;
            }
            case 3: {
                k1 ^= (long)(data[offset + tailStart + 2] & 0xFF) << 16;
            }
            case 2: {
                k1 ^= (long)(data[offset + tailStart + 1] & 0xFF) << 8;
            }
            case 1: {
                k1 ^= (long)(data[offset + tailStart] & 0xFF);
                k1 *= -8663945395140668459L;
                k1 = Long.rotateLeft(k1, 31);
                h1 ^= (k1 *= 5545529020109919103L);
            }
        }
        h1 ^= (long)length;
        h1 += (h2 ^= (long)length);
        h2 += h1;
        h1 = MurmurHash3.fmix64(h1);
        h2 = MurmurHash3.fmix64(h2);
        h1 += h2;
        return new long[]{h1, h2 += h1};
    }

    private static int mix32(int k, int hash) {
        k *= -862048943;
        k = Integer.rotateLeft(k, 15);
        return Integer.rotateLeft(hash ^= (k *= 461845907), 13) * 5 + -430675100;
    }

    private static int fmix32(int length, int hash) {
        hash ^= length;
        hash ^= hash >>> 16;
        hash *= -2048144789;
        hash ^= hash >>> 13;
        hash *= -1028477387;
        hash ^= hash >>> 16;
        return hash;
    }

    private static long fmix64(long h) {
        h ^= h >>> 33;
        h *= -49064778989728563L;
        h ^= h >>> 33;
        h *= -4265267296055464877L;
        h ^= h >>> 33;
        return h;
    }

    private static int orBytes(byte b1, byte b2, byte b3, byte b4) {
        return b1 & 0xFF | (b2 & 0xFF) << 8 | (b3 & 0xFF) << 16 | (b4 & 0xFF) << 24;
    }

    public static class IncrementalHash32 {
        byte[] tail = new byte[3];
        int tailLen;
        int totalLen;
        int hash;

        public final void start(int hash) {
            this.totalLen = 0;
            this.tailLen = 0;
            this.hash = hash;
        }

        public final void add(byte[] data, int offset, int length) {
            if (length == 0) {
                return;
            }
            this.totalLen += length;
            if (this.tailLen + length < 4) {
                System.arraycopy(data, offset, this.tail, this.tailLen, length);
                this.tailLen += length;
                return;
            }
            int offset2 = 0;
            if (this.tailLen > 0) {
                offset2 = 4 - this.tailLen;
                int k = -1;
                switch (this.tailLen) {
                    case 1: {
                        k = MurmurHash3.orBytes(this.tail[0], data[offset], data[offset + 1], data[offset + 2]);
                        break;
                    }
                    case 2: {
                        k = MurmurHash3.orBytes(this.tail[0], this.tail[1], data[offset], data[offset + 1]);
                        break;
                    }
                    case 3: {
                        k = MurmurHash3.orBytes(this.tail[0], this.tail[1], this.tail[2], data[offset]);
                        break;
                    }
                    default: {
                        throw new AssertionError(this.tailLen);
                    }
                }
                k *= -862048943;
                k = Integer.rotateLeft(k, 15);
                this.hash ^= (k *= 461845907);
                this.hash = Integer.rotateLeft(this.hash, 13) * 5 + -430675100;
            }
            int length2 = length - offset2;
            offset += offset2;
            int nblocks = length2 >> 2;
            for (int i = 0; i < nblocks; ++i) {
                int i_4 = (i << 2) + offset;
                int k = MurmurHash3.orBytes(data[i_4], data[i_4 + 1], data[i_4 + 2], data[i_4 + 3]);
                k *= -862048943;
                k = Integer.rotateLeft(k, 15);
                this.hash ^= (k *= 461845907);
                this.hash = Integer.rotateLeft(this.hash, 13) * 5 + -430675100;
            }
            int consumed = nblocks << 2;
            this.tailLen = length2 - consumed;
            if (consumed == length2) {
                return;
            }
            System.arraycopy(data, offset + consumed, this.tail, 0, this.tailLen);
        }

        public final int end() {
            int k1 = 0;
            switch (this.tailLen) {
                case 3: {
                    k1 ^= this.tail[2] << 16;
                }
                case 2: {
                    k1 ^= this.tail[1] << 8;
                }
                case 1: {
                    k1 ^= this.tail[0];
                    k1 *= -862048943;
                    k1 = Integer.rotateLeft(k1, 15);
                    this.hash ^= (k1 *= 461845907);
                }
            }
            this.hash ^= this.totalLen;
            this.hash ^= this.hash >>> 16;
            this.hash *= -2048144789;
            this.hash ^= this.hash >>> 13;
            this.hash *= -1028477387;
            this.hash ^= this.hash >>> 16;
            return this.hash;
        }
    }
}

