/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.digest;

public final class MurmurHash2 {
    private MurmurHash2() {
    }

    public static int hash32(byte[] data, int length, int seed) {
        int m = 1540483477;
        int r = 24;
        int h = seed ^ length;
        int length4 = length / 4;
        for (int i = 0; i < length4; ++i) {
            int i4 = i * 4;
            int k = (data[i4 + 0] & 0xFF) + ((data[i4 + 1] & 0xFF) << 8) + ((data[i4 + 2] & 0xFF) << 16) + ((data[i4 + 3] & 0xFF) << 24);
            k *= 1540483477;
            k ^= k >>> 24;
            h *= 1540483477;
            h ^= (k *= 1540483477);
        }
        switch (length % 4) {
            case 3: {
                h ^= (data[(length & 0xFFFFFFFC) + 2] & 0xFF) << 16;
            }
            case 2: {
                h ^= (data[(length & 0xFFFFFFFC) + 1] & 0xFF) << 8;
            }
            case 1: {
                h ^= data[length & 0xFFFFFFFC] & 0xFF;
                h *= 1540483477;
            }
        }
        h ^= h >>> 13;
        h *= 1540483477;
        h ^= h >>> 15;
        return h;
    }

    public static int hash32(byte[] data, int length) {
        return MurmurHash2.hash32(data, length, -1756908916);
    }

    public static int hash32(String text) {
        byte[] bytes = text.getBytes();
        return MurmurHash2.hash32(bytes, bytes.length);
    }

    public static int hash32(String text, int from, int length) {
        return MurmurHash2.hash32(text.substring(from, from + length));
    }

    public static long hash64(byte[] data, int length, int seed) {
        long m = -4132994306676758123L;
        int r = 47;
        long h = (long)seed & 0xFFFFFFFFL ^ (long)length * -4132994306676758123L;
        int length8 = length / 8;
        for (int i = 0; i < length8; ++i) {
            int i8 = i * 8;
            long k = ((long)data[i8 + 0] & 0xFFL) + (((long)data[i8 + 1] & 0xFFL) << 8) + (((long)data[i8 + 2] & 0xFFL) << 16) + (((long)data[i8 + 3] & 0xFFL) << 24) + (((long)data[i8 + 4] & 0xFFL) << 32) + (((long)data[i8 + 5] & 0xFFL) << 40) + (((long)data[i8 + 6] & 0xFFL) << 48) + (((long)data[i8 + 7] & 0xFFL) << 56);
            k *= -4132994306676758123L;
            k ^= k >>> 47;
            h ^= (k *= -4132994306676758123L);
            h *= -4132994306676758123L;
        }
        switch (length % 8) {
            case 7: {
                h ^= (long)(data[(length & 0xFFFFFFF8) + 6] & 0xFF) << 48;
            }
            case 6: {
                h ^= (long)(data[(length & 0xFFFFFFF8) + 5] & 0xFF) << 40;
            }
            case 5: {
                h ^= (long)(data[(length & 0xFFFFFFF8) + 4] & 0xFF) << 32;
            }
            case 4: {
                h ^= (long)(data[(length & 0xFFFFFFF8) + 3] & 0xFF) << 24;
            }
            case 3: {
                h ^= (long)(data[(length & 0xFFFFFFF8) + 2] & 0xFF) << 16;
            }
            case 2: {
                h ^= (long)(data[(length & 0xFFFFFFF8) + 1] & 0xFF) << 8;
            }
            case 1: {
                h ^= (long)(data[length & 0xFFFFFFF8] & 0xFF);
                h *= -4132994306676758123L;
            }
        }
        h ^= h >>> 47;
        h *= -4132994306676758123L;
        h ^= h >>> 47;
        return h;
    }

    public static long hash64(byte[] data, int length) {
        return MurmurHash2.hash64(data, length, -512093083);
    }

    public static long hash64(String text) {
        byte[] bytes = text.getBytes();
        return MurmurHash2.hash64(bytes, bytes.length);
    }

    public static long hash64(String text, int from, int length) {
        return MurmurHash2.hash64(text.substring(from, from + length));
    }
}

