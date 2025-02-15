/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.util.UUID;

public class Conversion {
    private static final boolean[] TTTT = new boolean[]{true, true, true, true};
    private static final boolean[] FTTT = new boolean[]{false, true, true, true};
    private static final boolean[] TFTT = new boolean[]{true, false, true, true};
    private static final boolean[] FFTT = new boolean[]{false, false, true, true};
    private static final boolean[] TTFT = new boolean[]{true, true, false, true};
    private static final boolean[] FTFT = new boolean[]{false, true, false, true};
    private static final boolean[] TFFT = new boolean[]{true, false, false, true};
    private static final boolean[] FFFT = new boolean[]{false, false, false, true};
    private static final boolean[] TTTF = new boolean[]{true, true, true, false};
    private static final boolean[] FTTF = new boolean[]{false, true, true, false};
    private static final boolean[] TFTF = new boolean[]{true, false, true, false};
    private static final boolean[] FFTF = new boolean[]{false, false, true, false};
    private static final boolean[] TTFF = new boolean[]{true, true, false, false};
    private static final boolean[] FTFF = new boolean[]{false, true, false, false};
    private static final boolean[] TFFF = new boolean[]{true, false, false, false};
    private static final boolean[] FFFF = new boolean[]{false, false, false, false};

    public static int hexDigitToInt(char hexDigit) {
        int digit = Character.digit(hexDigit, 16);
        if (digit < 0) {
            throw new IllegalArgumentException("Cannot interpret '" + hexDigit + "' as a hexadecimal digit");
        }
        return digit;
    }

    public static int hexDigitMsb0ToInt(char hexDigit) {
        switch (hexDigit) {
            case '0': {
                return 0;
            }
            case '1': {
                return 8;
            }
            case '2': {
                return 4;
            }
            case '3': {
                return 12;
            }
            case '4': {
                return 2;
            }
            case '5': {
                return 10;
            }
            case '6': {
                return 6;
            }
            case '7': {
                return 14;
            }
            case '8': {
                return 1;
            }
            case '9': {
                return 9;
            }
            case 'A': 
            case 'a': {
                return 5;
            }
            case 'B': 
            case 'b': {
                return 13;
            }
            case 'C': 
            case 'c': {
                return 3;
            }
            case 'D': 
            case 'd': {
                return 11;
            }
            case 'E': 
            case 'e': {
                return 7;
            }
            case 'F': 
            case 'f': {
                return 15;
            }
        }
        throw new IllegalArgumentException("Cannot interpret '" + hexDigit + "' as a hexadecimal digit");
    }

    public static boolean[] hexDigitToBinary(char hexDigit) {
        switch (hexDigit) {
            case '0': {
                return (boolean[])FFFF.clone();
            }
            case '1': {
                return (boolean[])TFFF.clone();
            }
            case '2': {
                return (boolean[])FTFF.clone();
            }
            case '3': {
                return (boolean[])TTFF.clone();
            }
            case '4': {
                return (boolean[])FFTF.clone();
            }
            case '5': {
                return (boolean[])TFTF.clone();
            }
            case '6': {
                return (boolean[])FTTF.clone();
            }
            case '7': {
                return (boolean[])TTTF.clone();
            }
            case '8': {
                return (boolean[])FFFT.clone();
            }
            case '9': {
                return (boolean[])TFFT.clone();
            }
            case 'A': 
            case 'a': {
                return (boolean[])FTFT.clone();
            }
            case 'B': 
            case 'b': {
                return (boolean[])TTFT.clone();
            }
            case 'C': 
            case 'c': {
                return (boolean[])FFTT.clone();
            }
            case 'D': 
            case 'd': {
                return (boolean[])TFTT.clone();
            }
            case 'E': 
            case 'e': {
                return (boolean[])FTTT.clone();
            }
            case 'F': 
            case 'f': {
                return (boolean[])TTTT.clone();
            }
        }
        throw new IllegalArgumentException("Cannot interpret '" + hexDigit + "' as a hexadecimal digit");
    }

    public static boolean[] hexDigitMsb0ToBinary(char hexDigit) {
        switch (hexDigit) {
            case '0': {
                return (boolean[])FFFF.clone();
            }
            case '1': {
                return (boolean[])FFFT.clone();
            }
            case '2': {
                return (boolean[])FFTF.clone();
            }
            case '3': {
                return (boolean[])FFTT.clone();
            }
            case '4': {
                return (boolean[])FTFF.clone();
            }
            case '5': {
                return (boolean[])FTFT.clone();
            }
            case '6': {
                return (boolean[])FTTF.clone();
            }
            case '7': {
                return (boolean[])FTTT.clone();
            }
            case '8': {
                return (boolean[])TFFF.clone();
            }
            case '9': {
                return (boolean[])TFFT.clone();
            }
            case 'A': 
            case 'a': {
                return (boolean[])TFTF.clone();
            }
            case 'B': 
            case 'b': {
                return (boolean[])TFTT.clone();
            }
            case 'C': 
            case 'c': {
                return (boolean[])TTFF.clone();
            }
            case 'D': 
            case 'd': {
                return (boolean[])TTFT.clone();
            }
            case 'E': 
            case 'e': {
                return (boolean[])TTTF.clone();
            }
            case 'F': 
            case 'f': {
                return (boolean[])TTTT.clone();
            }
        }
        throw new IllegalArgumentException("Cannot interpret '" + hexDigit + "' as a hexadecimal digit");
    }

    public static char binaryToHexDigit(boolean[] src) {
        return Conversion.binaryToHexDigit(src, 0);
    }

    public static char binaryToHexDigit(boolean[] src, int srcPos) {
        if (src.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        if (src.length > srcPos + 3 && src[srcPos + 3]) {
            if (src[srcPos + 2]) {
                if (src[srcPos + 1]) {
                    return src[srcPos] ? (char)'f' : 'e';
                }
                return src[srcPos] ? (char)'d' : 'c';
            }
            if (src[srcPos + 1]) {
                return src[srcPos] ? (char)'b' : 'a';
            }
            return src[srcPos] ? (char)'9' : '8';
        }
        if (src.length > srcPos + 2 && src[srcPos + 2]) {
            if (src[srcPos + 1]) {
                return src[srcPos] ? (char)'7' : '6';
            }
            return src[srcPos] ? (char)'5' : '4';
        }
        if (src.length > srcPos + 1 && src[srcPos + 1]) {
            return src[srcPos] ? (char)'3' : '2';
        }
        return src[srcPos] ? (char)'1' : '0';
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] src) {
        return Conversion.binaryToHexDigitMsb0_4bits(src, 0);
    }

    public static char binaryToHexDigitMsb0_4bits(boolean[] src, int srcPos) {
        if (src.length > 8) {
            throw new IllegalArgumentException("src.length>8: src.length=" + src.length);
        }
        if (src.length - srcPos < 4) {
            throw new IllegalArgumentException("src.length-srcPos<4: src.length=" + src.length + ", srcPos=" + srcPos);
        }
        if (src[srcPos + 3]) {
            if (src[srcPos + 2]) {
                if (src[srcPos + 1]) {
                    return src[srcPos] ? (char)'f' : '7';
                }
                return src[srcPos] ? (char)'b' : '3';
            }
            if (src[srcPos + 1]) {
                return src[srcPos] ? (char)'d' : '5';
            }
            return src[srcPos] ? (char)'9' : '1';
        }
        if (src[srcPos + 2]) {
            if (src[srcPos + 1]) {
                return src[srcPos] ? (char)'e' : '6';
            }
            return src[srcPos] ? (char)'a' : '2';
        }
        if (src[srcPos + 1]) {
            return src[srcPos] ? (char)'c' : '4';
        }
        return src[srcPos] ? (char)'8' : '0';
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] src) {
        return Conversion.binaryBeMsb0ToHexDigit(src, 0);
    }

    public static char binaryBeMsb0ToHexDigit(boolean[] src, int srcPos) {
        if (src.length == 0) {
            throw new IllegalArgumentException("Cannot convert an empty array.");
        }
        int beSrcPos = src.length - 1 - srcPos;
        int srcLen = Math.min(4, beSrcPos + 1);
        boolean[] paddedSrc = new boolean[4];
        System.arraycopy(src, beSrcPos + 1 - srcLen, paddedSrc, 4 - srcLen, srcLen);
        src = paddedSrc;
        srcPos = 0;
        if (src[srcPos]) {
            if (src.length > srcPos + 1 && src[srcPos + 1]) {
                if (src.length > srcPos + 2 && src[srcPos + 2]) {
                    return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 102 : 101);
                }
                return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 100 : 99);
            }
            if (src.length > srcPos + 2 && src[srcPos + 2]) {
                return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 98 : 97);
            }
            return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 57 : 56);
        }
        if (src.length > srcPos + 1 && src[srcPos + 1]) {
            if (src.length > srcPos + 2 && src[srcPos + 2]) {
                return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 55 : 54);
            }
            return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 53 : 52);
        }
        if (src.length > srcPos + 2 && src[srcPos + 2]) {
            return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 51 : 50);
        }
        return (char)(src.length > srcPos + 3 && src[srcPos + 3] ? 49 : 48);
    }

    public static char intToHexDigit(int nibble) {
        char c = Character.forDigit(nibble, 16);
        if (c == '\u0000') {
            throw new IllegalArgumentException("nibble value not between 0 and 15: " + nibble);
        }
        return c;
    }

    public static char intToHexDigitMsb0(int nibble) {
        switch (nibble) {
            case 0: {
                return '0';
            }
            case 1: {
                return '8';
            }
            case 2: {
                return '4';
            }
            case 3: {
                return 'c';
            }
            case 4: {
                return '2';
            }
            case 5: {
                return 'a';
            }
            case 6: {
                return '6';
            }
            case 7: {
                return 'e';
            }
            case 8: {
                return '1';
            }
            case 9: {
                return '9';
            }
            case 10: {
                return '5';
            }
            case 11: {
                return 'd';
            }
            case 12: {
                return '3';
            }
            case 13: {
                return 'b';
            }
            case 14: {
                return '7';
            }
            case 15: {
                return 'f';
            }
        }
        throw new IllegalArgumentException("nibble value not between 0 and 15: " + nibble);
    }

    public static long intArrayToLong(int[] src, int srcPos, long dstInit, int dstPos, int nInts) {
        if (src.length == 0 && srcPos == 0 || 0 == nInts) {
            return dstInit;
        }
        if ((nInts - 1) * 32 + dstPos >= 64) {
            throw new IllegalArgumentException("(nInts-1)*32+dstPos is greater or equal to than 64");
        }
        long out = dstInit;
        for (int i = 0; i < nInts; ++i) {
            int shift = i * 32 + dstPos;
            long bits = (0xFFFFFFFFL & (long)src[i + srcPos]) << shift;
            long mask = 0xFFFFFFFFL << shift;
            out = out & (mask ^ 0xFFFFFFFFFFFFFFFFL) | bits;
        }
        return out;
    }

    public static long shortArrayToLong(short[] src, int srcPos, long dstInit, int dstPos, int nShorts) {
        if (src.length == 0 && srcPos == 0 || 0 == nShorts) {
            return dstInit;
        }
        if ((nShorts - 1) * 16 + dstPos >= 64) {
            throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greater or equal to than 64");
        }
        long out = dstInit;
        for (int i = 0; i < nShorts; ++i) {
            int shift = i * 16 + dstPos;
            long bits = (0xFFFFL & (long)src[i + srcPos]) << shift;
            long mask = 65535L << shift;
            out = out & (mask ^ 0xFFFFFFFFFFFFFFFFL) | bits;
        }
        return out;
    }

    public static int shortArrayToInt(short[] src, int srcPos, int dstInit, int dstPos, int nShorts) {
        if (src.length == 0 && srcPos == 0 || 0 == nShorts) {
            return dstInit;
        }
        if ((nShorts - 1) * 16 + dstPos >= 32) {
            throw new IllegalArgumentException("(nShorts-1)*16+dstPos is greater or equal to than 32");
        }
        int out = dstInit;
        for (int i = 0; i < nShorts; ++i) {
            int shift = i * 16 + dstPos;
            int bits = (0xFFFF & src[i + srcPos]) << shift;
            int mask = 65535 << shift;
            out = out & ~mask | bits;
        }
        return out;
    }

    public static long byteArrayToLong(byte[] src, int srcPos, long dstInit, int dstPos, int nBytes) {
        if (src.length == 0 && srcPos == 0 || 0 == nBytes) {
            return dstInit;
        }
        if ((nBytes - 1) * 8 + dstPos >= 64) {
            throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 64");
        }
        long out = dstInit;
        for (int i = 0; i < nBytes; ++i) {
            int shift = i * 8 + dstPos;
            long bits = (0xFFL & (long)src[i + srcPos]) << shift;
            long mask = 255L << shift;
            out = out & (mask ^ 0xFFFFFFFFFFFFFFFFL) | bits;
        }
        return out;
    }

    public static int byteArrayToInt(byte[] src, int srcPos, int dstInit, int dstPos, int nBytes) {
        if (src.length == 0 && srcPos == 0 || 0 == nBytes) {
            return dstInit;
        }
        if ((nBytes - 1) * 8 + dstPos >= 32) {
            throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 32");
        }
        int out = dstInit;
        for (int i = 0; i < nBytes; ++i) {
            int shift = i * 8 + dstPos;
            int bits = (0xFF & src[i + srcPos]) << shift;
            int mask = 255 << shift;
            out = out & ~mask | bits;
        }
        return out;
    }

    public static short byteArrayToShort(byte[] src, int srcPos, short dstInit, int dstPos, int nBytes) {
        if (src.length == 0 && srcPos == 0 || 0 == nBytes) {
            return dstInit;
        }
        if ((nBytes - 1) * 8 + dstPos >= 16) {
            throw new IllegalArgumentException("(nBytes-1)*8+dstPos is greater or equal to than 16");
        }
        short out = dstInit;
        for (int i = 0; i < nBytes; ++i) {
            int shift = i * 8 + dstPos;
            int bits = (0xFF & src[i + srcPos]) << shift;
            int mask = 255 << shift;
            out = (short)(out & ~mask | bits);
        }
        return out;
    }

    public static long hexToLong(String src, int srcPos, long dstInit, int dstPos, int nHex) {
        if (0 == nHex) {
            return dstInit;
        }
        if ((nHex - 1) * 4 + dstPos >= 64) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 64");
        }
        long out = dstInit;
        for (int i = 0; i < nHex; ++i) {
            int shift = i * 4 + dstPos;
            long bits = (0xFL & (long)Conversion.hexDigitToInt(src.charAt(i + srcPos))) << shift;
            long mask = 15L << shift;
            out = out & (mask ^ 0xFFFFFFFFFFFFFFFFL) | bits;
        }
        return out;
    }

    public static int hexToInt(String src, int srcPos, int dstInit, int dstPos, int nHex) {
        if (0 == nHex) {
            return dstInit;
        }
        if ((nHex - 1) * 4 + dstPos >= 32) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 32");
        }
        int out = dstInit;
        for (int i = 0; i < nHex; ++i) {
            int shift = i * 4 + dstPos;
            int bits = (0xF & Conversion.hexDigitToInt(src.charAt(i + srcPos))) << shift;
            int mask = 15 << shift;
            out = out & ~mask | bits;
        }
        return out;
    }

    public static short hexToShort(String src, int srcPos, short dstInit, int dstPos, int nHex) {
        if (0 == nHex) {
            return dstInit;
        }
        if ((nHex - 1) * 4 + dstPos >= 16) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 16");
        }
        short out = dstInit;
        for (int i = 0; i < nHex; ++i) {
            int shift = i * 4 + dstPos;
            int bits = (0xF & Conversion.hexDigitToInt(src.charAt(i + srcPos))) << shift;
            int mask = 15 << shift;
            out = (short)(out & ~mask | bits);
        }
        return out;
    }

    public static byte hexToByte(String src, int srcPos, byte dstInit, int dstPos, int nHex) {
        if (0 == nHex) {
            return dstInit;
        }
        if ((nHex - 1) * 4 + dstPos >= 8) {
            throw new IllegalArgumentException("(nHexs-1)*4+dstPos is greater or equal to than 8");
        }
        byte out = dstInit;
        for (int i = 0; i < nHex; ++i) {
            int shift = i * 4 + dstPos;
            int bits = (0xF & Conversion.hexDigitToInt(src.charAt(i + srcPos))) << shift;
            int mask = 15 << shift;
            out = (byte)(out & ~mask | bits);
        }
        return out;
    }

    public static long binaryToLong(boolean[] src, int srcPos, long dstInit, int dstPos, int nBools) {
        if (src.length == 0 && srcPos == 0 || 0 == nBools) {
            return dstInit;
        }
        if (nBools - 1 + dstPos >= 64) {
            throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 64");
        }
        long out = dstInit;
        for (int i = 0; i < nBools; ++i) {
            int shift = i + dstPos;
            long bits = (src[i + srcPos] ? 1L : 0L) << shift;
            long mask = 1L << shift;
            out = out & (mask ^ 0xFFFFFFFFFFFFFFFFL) | bits;
        }
        return out;
    }

    public static int binaryToInt(boolean[] src, int srcPos, int dstInit, int dstPos, int nBools) {
        if (src.length == 0 && srcPos == 0 || 0 == nBools) {
            return dstInit;
        }
        if (nBools - 1 + dstPos >= 32) {
            throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 32");
        }
        int out = dstInit;
        for (int i = 0; i < nBools; ++i) {
            int shift = i + dstPos;
            int bits = (src[i + srcPos] ? 1 : 0) << shift;
            int mask = 1 << shift;
            out = out & ~mask | bits;
        }
        return out;
    }

    public static short binaryToShort(boolean[] src, int srcPos, short dstInit, int dstPos, int nBools) {
        if (src.length == 0 && srcPos == 0 || 0 == nBools) {
            return dstInit;
        }
        if (nBools - 1 + dstPos >= 16) {
            throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 16");
        }
        short out = dstInit;
        for (int i = 0; i < nBools; ++i) {
            int shift = i + dstPos;
            int bits = (src[i + srcPos] ? 1 : 0) << shift;
            int mask = 1 << shift;
            out = (short)(out & ~mask | bits);
        }
        return out;
    }

    public static byte binaryToByte(boolean[] src, int srcPos, byte dstInit, int dstPos, int nBools) {
        if (src.length == 0 && srcPos == 0 || 0 == nBools) {
            return dstInit;
        }
        if (nBools - 1 + dstPos >= 8) {
            throw new IllegalArgumentException("nBools-1+dstPos is greater or equal to than 8");
        }
        byte out = dstInit;
        for (int i = 0; i < nBools; ++i) {
            int shift = i + dstPos;
            int bits = (src[i + srcPos] ? 1 : 0) << shift;
            int mask = 1 << shift;
            out = (byte)(out & ~mask | bits);
        }
        return out;
    }

    public static int[] longToIntArray(long src, int srcPos, int[] dst, int dstPos, int nInts) {
        if (0 == nInts) {
            return dst;
        }
        if ((nInts - 1) * 32 + srcPos >= 64) {
            throw new IllegalArgumentException("(nInts-1)*32+srcPos is greater or equal to than 64");
        }
        for (int i = 0; i < nInts; ++i) {
            int shift = i * 32 + srcPos;
            dst[dstPos + i] = (int)(0xFFFFFFFFFFFFFFFFL & src >> shift);
        }
        return dst;
    }

    public static short[] longToShortArray(long src, int srcPos, short[] dst, int dstPos, int nShorts) {
        if (0 == nShorts) {
            return dst;
        }
        if ((nShorts - 1) * 16 + srcPos >= 64) {
            throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greater or equal to than 64");
        }
        for (int i = 0; i < nShorts; ++i) {
            int shift = i * 16 + srcPos;
            dst[dstPos + i] = (short)(0xFFFFL & src >> shift);
        }
        return dst;
    }

    public static short[] intToShortArray(int src, int srcPos, short[] dst, int dstPos, int nShorts) {
        if (0 == nShorts) {
            return dst;
        }
        if ((nShorts - 1) * 16 + srcPos >= 32) {
            throw new IllegalArgumentException("(nShorts-1)*16+srcPos is greater or equal to than 32");
        }
        for (int i = 0; i < nShorts; ++i) {
            int shift = i * 16 + srcPos;
            dst[dstPos + i] = (short)(0xFFFF & src >> shift);
        }
        return dst;
    }

    public static byte[] longToByteArray(long src, int srcPos, byte[] dst, int dstPos, int nBytes) {
        if (0 == nBytes) {
            return dst;
        }
        if ((nBytes - 1) * 8 + srcPos >= 64) {
            throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 64");
        }
        for (int i = 0; i < nBytes; ++i) {
            int shift = i * 8 + srcPos;
            dst[dstPos + i] = (byte)(0xFFL & src >> shift);
        }
        return dst;
    }

    public static byte[] intToByteArray(int src, int srcPos, byte[] dst, int dstPos, int nBytes) {
        if (0 == nBytes) {
            return dst;
        }
        if ((nBytes - 1) * 8 + srcPos >= 32) {
            throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 32");
        }
        for (int i = 0; i < nBytes; ++i) {
            int shift = i * 8 + srcPos;
            dst[dstPos + i] = (byte)(0xFF & src >> shift);
        }
        return dst;
    }

    public static byte[] shortToByteArray(short src, int srcPos, byte[] dst, int dstPos, int nBytes) {
        if (0 == nBytes) {
            return dst;
        }
        if ((nBytes - 1) * 8 + srcPos >= 16) {
            throw new IllegalArgumentException("(nBytes-1)*8+srcPos is greater or equal to than 16");
        }
        for (int i = 0; i < nBytes; ++i) {
            int shift = i * 8 + srcPos;
            dst[dstPos + i] = (byte)(0xFF & src >> shift);
        }
        return dst;
    }

    public static String longToHex(long src, int srcPos, String dstInit, int dstPos, int nHexs) {
        if (0 == nHexs) {
            return dstInit;
        }
        if ((nHexs - 1) * 4 + srcPos >= 64) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 64");
        }
        StringBuilder sb = new StringBuilder(dstInit);
        int append = sb.length();
        for (int i = 0; i < nHexs; ++i) {
            int shift = i * 4 + srcPos;
            int bits = (int)(0xFL & src >> shift);
            if (dstPos + i == append) {
                ++append;
                sb.append(Conversion.intToHexDigit(bits));
                continue;
            }
            sb.setCharAt(dstPos + i, Conversion.intToHexDigit(bits));
        }
        return sb.toString();
    }

    public static String intToHex(int src, int srcPos, String dstInit, int dstPos, int nHexs) {
        if (0 == nHexs) {
            return dstInit;
        }
        if ((nHexs - 1) * 4 + srcPos >= 32) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 32");
        }
        StringBuilder sb = new StringBuilder(dstInit);
        int append = sb.length();
        for (int i = 0; i < nHexs; ++i) {
            int shift = i * 4 + srcPos;
            int bits = 0xF & src >> shift;
            if (dstPos + i == append) {
                ++append;
                sb.append(Conversion.intToHexDigit(bits));
                continue;
            }
            sb.setCharAt(dstPos + i, Conversion.intToHexDigit(bits));
        }
        return sb.toString();
    }

    public static String shortToHex(short src, int srcPos, String dstInit, int dstPos, int nHexs) {
        if (0 == nHexs) {
            return dstInit;
        }
        if ((nHexs - 1) * 4 + srcPos >= 16) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 16");
        }
        StringBuilder sb = new StringBuilder(dstInit);
        int append = sb.length();
        for (int i = 0; i < nHexs; ++i) {
            int shift = i * 4 + srcPos;
            int bits = 0xF & src >> shift;
            if (dstPos + i == append) {
                ++append;
                sb.append(Conversion.intToHexDigit(bits));
                continue;
            }
            sb.setCharAt(dstPos + i, Conversion.intToHexDigit(bits));
        }
        return sb.toString();
    }

    public static String byteToHex(byte src, int srcPos, String dstInit, int dstPos, int nHexs) {
        if (0 == nHexs) {
            return dstInit;
        }
        if ((nHexs - 1) * 4 + srcPos >= 8) {
            throw new IllegalArgumentException("(nHexs-1)*4+srcPos is greater or equal to than 8");
        }
        StringBuilder sb = new StringBuilder(dstInit);
        int append = sb.length();
        for (int i = 0; i < nHexs; ++i) {
            int shift = i * 4 + srcPos;
            int bits = 0xF & src >> shift;
            if (dstPos + i == append) {
                ++append;
                sb.append(Conversion.intToHexDigit(bits));
                continue;
            }
            sb.setCharAt(dstPos + i, Conversion.intToHexDigit(bits));
        }
        return sb.toString();
    }

    public static boolean[] longToBinary(long src, int srcPos, boolean[] dst, int dstPos, int nBools) {
        if (0 == nBools) {
            return dst;
        }
        if (nBools - 1 + srcPos >= 64) {
            throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 64");
        }
        for (int i = 0; i < nBools; ++i) {
            int shift = i + srcPos;
            dst[dstPos + i] = (1L & src >> shift) != 0L;
        }
        return dst;
    }

    public static boolean[] intToBinary(int src, int srcPos, boolean[] dst, int dstPos, int nBools) {
        if (0 == nBools) {
            return dst;
        }
        if (nBools - 1 + srcPos >= 32) {
            throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 32");
        }
        for (int i = 0; i < nBools; ++i) {
            int shift = i + srcPos;
            dst[dstPos + i] = (1 & src >> shift) != 0;
        }
        return dst;
    }

    public static boolean[] shortToBinary(short src, int srcPos, boolean[] dst, int dstPos, int nBools) {
        if (0 == nBools) {
            return dst;
        }
        if (nBools - 1 + srcPos >= 16) {
            throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 16");
        }
        assert (nBools - 1 < 16 - srcPos);
        for (int i = 0; i < nBools; ++i) {
            int shift = i + srcPos;
            dst[dstPos + i] = (1 & src >> shift) != 0;
        }
        return dst;
    }

    public static boolean[] byteToBinary(byte src, int srcPos, boolean[] dst, int dstPos, int nBools) {
        if (0 == nBools) {
            return dst;
        }
        if (nBools - 1 + srcPos >= 8) {
            throw new IllegalArgumentException("nBools-1+srcPos is greater or equal to than 8");
        }
        for (int i = 0; i < nBools; ++i) {
            int shift = i + srcPos;
            dst[dstPos + i] = (1 & src >> shift) != 0;
        }
        return dst;
    }

    public static byte[] uuidToByteArray(UUID src, byte[] dst, int dstPos, int nBytes) {
        if (0 == nBytes) {
            return dst;
        }
        if (nBytes > 16) {
            throw new IllegalArgumentException("nBytes is greater than 16");
        }
        Conversion.longToByteArray(src.getMostSignificantBits(), 0, dst, dstPos, Math.min(nBytes, 8));
        if (nBytes >= 8) {
            Conversion.longToByteArray(src.getLeastSignificantBits(), 0, dst, dstPos + 8, nBytes - 8);
        }
        return dst;
    }

    public static UUID byteArrayToUuid(byte[] src, int srcPos) {
        if (src.length - srcPos < 16) {
            throw new IllegalArgumentException("Need at least 16 bytes for UUID");
        }
        return new UUID(Conversion.byteArrayToLong(src, srcPos, 0L, 0, 8), Conversion.byteArrayToLong(src, srcPos + 8, 0L, 0, 8));
    }
}

