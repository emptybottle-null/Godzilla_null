/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.util;

import java.io.UnsupportedEncodingException;

public final class HexBin {
    private static final int BASELENGTH = 255;
    private static final int LOOKUPLENGTH = 16;
    private static byte[] hexNumberTable;
    private static byte[] lookUpHexAlphabet;

    static boolean isHex(byte octect) {
        return hexNumberTable[octect] != -1;
    }

    public static String bytesToString(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        return new String(HexBin.encode(binaryData));
    }

    public static byte[] stringToBytes(String hexEncoded) {
        return HexBin.decode(hexEncoded.getBytes());
    }

    public static byte[] encode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthData = binaryData.length;
        int lengthEncode = lengthData * 2;
        byte[] encodedData = new byte[lengthEncode];
        for (int i = 0; i < lengthData; ++i) {
            encodedData[i * 2] = lookUpHexAlphabet[binaryData[i] >> 4 & 0xF];
            encodedData[i * 2 + 1] = lookUpHexAlphabet[binaryData[i] & 0xF];
        }
        return encodedData;
    }

    public static byte[] decode(byte[] binaryData) {
        if (binaryData == null) {
            return null;
        }
        int lengthData = binaryData.length;
        if (lengthData % 2 != 0) {
            return null;
        }
        int lengthDecode = lengthData / 2;
        byte[] decodedData = new byte[lengthDecode];
        for (int i = 0; i < lengthDecode; ++i) {
            if (!HexBin.isHex(binaryData[i * 2]) || !HexBin.isHex(binaryData[i * 2 + 1])) {
                return null;
            }
            decodedData[i] = (byte)(hexNumberTable[binaryData[i * 2]] << 4 | hexNumberTable[binaryData[i * 2 + 1]]);
        }
        return decodedData;
    }

    public static String decode(String binaryData) {
        if (binaryData == null) {
            return null;
        }
        byte[] decoded = null;
        try {
            decoded = HexBin.decode(binaryData.getBytes("utf-8"));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            // empty catch block
        }
        return decoded == null ? null : new String(decoded);
    }

    public static String encode(String binaryData) {
        if (binaryData == null) {
            return null;
        }
        byte[] encoded = null;
        try {
            encoded = HexBin.encode(binaryData.getBytes("utf-8"));
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            // empty catch block
        }
        return encoded == null ? null : new String(encoded);
    }

    static {
        int i;
        hexNumberTable = new byte[255];
        lookUpHexAlphabet = new byte[16];
        for (i = 0; i < 255; ++i) {
            HexBin.hexNumberTable[i] = -1;
        }
        for (i = 57; i >= 48; --i) {
            HexBin.hexNumberTable[i] = (byte)(i - 48);
        }
        for (i = 70; i >= 65; --i) {
            HexBin.hexNumberTable[i] = (byte)(i - 65 + 10);
        }
        for (i = 102; i >= 97; --i) {
            HexBin.hexNumberTable[i] = (byte)(i - 97 + 10);
        }
        for (i = 0; i < 10; ++i) {
            HexBin.lookUpHexAlphabet[i] = (byte)(48 + i);
        }
        for (i = 10; i <= 15; ++i) {
            HexBin.lookUpHexAlphabet[i] = (byte)(65 + i - 10);
        }
    }
}

