/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.external.biz.base64Coder;

public class Base64Coder {
    private static final String systemLineSeparator;
    private static char[] map1;
    private static byte[] map2;

    public static String encodeString(String s) {
        return new String(Base64Coder.encode(s.getBytes()));
    }

    public static String encodeLines(byte[] in) {
        return Base64Coder.encodeLines(in, 0, in.length, 76, systemLineSeparator);
    }

    public static String encodeLines(byte[] in, int iOff, int iLen, int lineLen, String lineSeparator) {
        int l;
        int blockLen = lineLen * 3 / 4;
        if (blockLen <= 0) {
            throw new IllegalArgumentException();
        }
        int lines = (iLen + blockLen - 1) / blockLen;
        int bufLen = (iLen + 2) / 3 * 4 + lines * lineSeparator.length();
        StringBuilder buf = new StringBuilder(bufLen);
        for (int ip = 0; ip < iLen; ip += l) {
            l = Math.min(iLen - ip, blockLen);
            buf.append(Base64Coder.encode(in, iOff + ip, l));
            buf.append(lineSeparator);
        }
        return buf.toString();
    }

    public static char[] encode(byte[] in) {
        return Base64Coder.encode(in, 0, in.length);
    }

    public static char[] encode(byte[] in, int iLen) {
        return Base64Coder.encode(in, 0, iLen);
    }

    public static char[] encode(byte[] in, int iOff, int iLen) {
        int oDataLen = (iLen * 4 + 2) / 3;
        int oLen = (iLen + 2) / 3 * 4;
        char[] out = new char[oLen];
        int ip = iOff;
        int iEnd = iOff + iLen;
        int op = 0;
        while (ip < iEnd) {
            int i0 = in[ip++] & 0xFF;
            int i1 = ip < iEnd ? in[ip++] & 0xFF : 0;
            int i2 = ip < iEnd ? in[ip++] & 0xFF : 0;
            int o0 = i0 >>> 2;
            int o1 = (i0 & 3) << 4 | i1 >>> 4;
            int o2 = (i1 & 0xF) << 2 | i2 >>> 6;
            int o3 = i2 & 0x3F;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : 61;
            int n = ++op < oDataLen ? map1[o3] : 61;
            out[op] = n;
            ++op;
        }
        return out;
    }

    public static String decodeString(String s) {
        return new String(Base64Coder.decode(s));
    }

    public static byte[] decodeLines(String s) {
        char[] buf = new char[s.length()];
        int p = 0;
        for (int ip = 0; ip < s.length(); ++ip) {
            char c = s.charAt(ip);
            if (c == ' ' || c == '\r' || c == '\n' || c == '\t') continue;
            buf[p++] = c;
        }
        return Base64Coder.decode(buf, 0, p);
    }

    public static byte[] decode(String s) {
        return Base64Coder.decode(s.toCharArray());
    }

    public static byte[] decode(char[] in) {
        return Base64Coder.decode(in, 0, in.length);
    }

    public static byte[] decode(char[] in, int iOff, int iLen) {
        if (iLen % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (iLen > 0 && in[iOff + iLen - 1] == '=') {
            --iLen;
        }
        int oLen = iLen * 3 / 4;
        byte[] out = new byte[oLen];
        int ip = iOff;
        int iEnd = iOff + iLen;
        int op = 0;
        while (ip < iEnd) {
            int i3;
            char i0 = in[ip++];
            char i1 = in[ip++];
            int i2 = ip < iEnd ? in[ip++] : 65;
            int n = i3 = ip < iEnd ? in[ip++] : 65;
            if (i0 > '\u007f' || i1 > '\u007f' || i2 > 127 || i3 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            byte b0 = map2[i0];
            byte b1 = map2[i1];
            byte b2 = map2[i2];
            byte b3 = map2[i3];
            if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int o0 = b0 << 2 | b1 >>> 4;
            int o1 = (b1 & 0xF) << 4 | b2 >>> 2;
            int o2 = (b2 & 3) << 6 | b3;
            out[op++] = (byte)o0;
            if (op < oLen) {
                out[op++] = (byte)o1;
            }
            if (op >= oLen) continue;
            out[op++] = (byte)o2;
        }
        return out;
    }

    private Base64Coder() {
    }

    static {
        int c;
        systemLineSeparator = System.getProperty("line.separator");
        map1 = new char[64];
        int i = 0;
        for (c = 65; c <= 90; c = (int)((char)(c + 1))) {
            Base64Coder.map1[i++] = c;
        }
        for (c = 97; c <= 122; c = (int)((char)(c + 1))) {
            Base64Coder.map1[i++] = c;
        }
        for (c = 48; c <= 57; c = (int)((char)(c + 1))) {
            Base64Coder.map1[i++] = c;
        }
        Base64Coder.map1[i++] = 43;
        Base64Coder.map1[i++] = 47;
        map2 = new byte[128];
        for (i = 0; i < map2.length; ++i) {
            Base64Coder.map2[i] = -1;
        }
        for (i = 0; i < 64; ++i) {
            Base64Coder.map2[Base64Coder.map1[i]] = (byte)i;
        }
    }
}

