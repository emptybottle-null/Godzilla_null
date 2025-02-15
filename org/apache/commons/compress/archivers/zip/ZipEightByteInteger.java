/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;

public final class ZipEightByteInteger
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int BYTE_1 = 1;
    private static final int BYTE_1_MASK = 65280;
    private static final int BYTE_1_SHIFT = 8;
    private static final int BYTE_2 = 2;
    private static final int BYTE_2_MASK = 0xFF0000;
    private static final int BYTE_2_SHIFT = 16;
    private static final int BYTE_3 = 3;
    private static final long BYTE_3_MASK = 0xFF000000L;
    private static final int BYTE_3_SHIFT = 24;
    private static final int BYTE_4 = 4;
    private static final long BYTE_4_MASK = 0xFF00000000L;
    private static final int BYTE_4_SHIFT = 32;
    private static final int BYTE_5 = 5;
    private static final long BYTE_5_MASK = 0xFF0000000000L;
    private static final int BYTE_5_SHIFT = 40;
    private static final int BYTE_6 = 6;
    private static final long BYTE_6_MASK = 0xFF000000000000L;
    private static final int BYTE_6_SHIFT = 48;
    private static final int BYTE_7 = 7;
    private static final long BYTE_7_MASK = 0x7F00000000000000L;
    private static final int BYTE_7_SHIFT = 56;
    private static final int LEFTMOST_BIT_SHIFT = 63;
    private static final byte LEFTMOST_BIT = -128;
    private final BigInteger value;
    public static final ZipEightByteInteger ZERO = new ZipEightByteInteger(0L);

    public ZipEightByteInteger(long value) {
        this(BigInteger.valueOf(value));
    }

    public ZipEightByteInteger(BigInteger value) {
        this.value = value;
    }

    public ZipEightByteInteger(byte[] bytes) {
        this(bytes, 0);
    }

    public ZipEightByteInteger(byte[] bytes, int offset) {
        this.value = ZipEightByteInteger.getValue(bytes, offset);
    }

    public byte[] getBytes() {
        return ZipEightByteInteger.getBytes(this.value);
    }

    public long getLongValue() {
        return this.value.longValue();
    }

    public BigInteger getValue() {
        return this.value;
    }

    public static byte[] getBytes(long value) {
        return ZipEightByteInteger.getBytes(BigInteger.valueOf(value));
    }

    public static byte[] getBytes(BigInteger value) {
        byte[] result = new byte[8];
        long val = value.longValue();
        result[0] = (byte)(val & 0xFFL);
        result[1] = (byte)((val & 0xFF00L) >> 8);
        result[2] = (byte)((val & 0xFF0000L) >> 16);
        result[3] = (byte)((val & 0xFF000000L) >> 24);
        result[4] = (byte)((val & 0xFF00000000L) >> 32);
        result[5] = (byte)((val & 0xFF0000000000L) >> 40);
        result[6] = (byte)((val & 0xFF000000000000L) >> 48);
        result[7] = (byte)((val & 0x7F00000000000000L) >> 56);
        if (value.testBit(63)) {
            result[7] = (byte)(result[7] | 0xFFFFFF80);
        }
        return result;
    }

    public static long getLongValue(byte[] bytes, int offset) {
        return ZipEightByteInteger.getValue(bytes, offset).longValue();
    }

    public static BigInteger getValue(byte[] bytes, int offset) {
        long value = (long)bytes[offset + 7] << 56 & 0x7F00000000000000L;
        value += (long)bytes[offset + 6] << 48 & 0xFF000000000000L;
        value += (long)bytes[offset + 5] << 40 & 0xFF0000000000L;
        value += (long)bytes[offset + 4] << 32 & 0xFF00000000L;
        value += (long)bytes[offset + 3] << 24 & 0xFF000000L;
        value += (long)bytes[offset + 2] << 16 & 0xFF0000L;
        value += (long)bytes[offset + 1] << 8 & 0xFF00L;
        BigInteger val = BigInteger.valueOf(value += (long)bytes[offset] & 0xFFL);
        return (bytes[offset + 7] & 0xFFFFFF80) == -128 ? val.setBit(63) : val;
    }

    public static long getLongValue(byte[] bytes) {
        return ZipEightByteInteger.getLongValue(bytes, 0);
    }

    public static BigInteger getValue(byte[] bytes) {
        return ZipEightByteInteger.getValue(bytes, 0);
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof ZipEightByteInteger)) {
            return false;
        }
        return this.value.equals(((ZipEightByteInteger)o).getValue());
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    public String toString() {
        return "ZipEightByteInteger value: " + this.value;
    }
}

