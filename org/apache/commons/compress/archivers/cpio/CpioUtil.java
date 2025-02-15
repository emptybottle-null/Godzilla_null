/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.cpio;

class CpioUtil {
    CpioUtil() {
    }

    static long fileType(long mode) {
        return mode & 0xF000L;
    }

    static long byteArray2long(byte[] number, boolean swapHalfWord) {
        if (number.length % 2 != 0) {
            throw new UnsupportedOperationException();
        }
        long ret = 0L;
        int pos = 0;
        byte[] tmp_number = new byte[number.length];
        System.arraycopy(number, 0, tmp_number, 0, number.length);
        if (!swapHalfWord) {
            byte tmp = 0;
            for (pos = 0; pos < tmp_number.length; ++pos) {
                tmp = tmp_number[pos];
                tmp_number[pos++] = tmp_number[pos];
                tmp_number[pos] = tmp;
            }
        }
        ret = tmp_number[0] & 0xFF;
        for (pos = 1; pos < tmp_number.length; ++pos) {
            ret <<= 8;
            ret |= (long)(tmp_number[pos] & 0xFF);
        }
        return ret;
    }

    static byte[] long2byteArray(long number, int length, boolean swapHalfWord) {
        byte[] ret = new byte[length];
        int pos = 0;
        long tmp_number = 0L;
        if (length % 2 != 0 || length < 2) {
            throw new UnsupportedOperationException();
        }
        tmp_number = number;
        for (pos = length - 1; pos >= 0; --pos) {
            ret[pos] = (byte)(tmp_number & 0xFFL);
            tmp_number >>= 8;
        }
        if (!swapHalfWord) {
            byte tmp = 0;
            for (pos = 0; pos < length; ++pos) {
                tmp = ret[pos];
                ret[pos++] = ret[pos];
                ret[pos] = tmp;
            }
        }
        return ret;
    }
}

