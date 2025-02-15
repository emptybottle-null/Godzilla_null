/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

public class HexDump {
    private static final int WIDTH = 20;

    public static void dump(byte[] data, int offset, int length) {
        int numRows = length / 20;
        for (int i = 0; i < numRows; ++i) {
            HexDump.dumpRow(data, offset + i * 20, 20);
        }
        int leftover = length % 20;
        if (leftover > 0) {
            HexDump.dumpRow(data, offset + data.length - leftover, leftover);
        }
    }

    public static void dump(byte[] data) {
        HexDump.dump(data, 0, data.length);
    }

    private static void dumpRow(byte[] data, int start, int length) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < length; ++i) {
            String s = Integer.toHexString(data[start + i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s);
            sb.append(" ");
        }
        if (length < 20) {
            for (i = 0; i < 20 - length; ++i) {
                sb.append("   ");
            }
        }
        for (i = 0; i < length; ++i) {
            byte b = data[start + i];
            if (Character.isLetterOrDigit(b)) {
                sb.append(String.valueOf((char)b));
                continue;
            }
            sb.append(".");
        }
        System.out.println(sb.toString());
    }
}

