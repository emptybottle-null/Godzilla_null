/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.external.biz.binaryEscape;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.yaml.snakeyaml.external.biz.binaryEscape.StringIterator;

public class BinaryEscape {
    public static String escape(byte[] buf) {
        String strHex = "";
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < buf.length; ++n) {
            strHex = Integer.toHexString(buf[n] & 0xFF);
            sb.append("\\x");
            sb.append(strHex.length() == 1 ? "0" + strHex : strHex);
        }
        return sb.toString();
    }

    public static String escapeStr(String str) {
        return BinaryEscape.escape(str.getBytes());
    }

    public static String unescape(byte[] buf) {
        String strHex = "";
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < buf.length; ++n) {
            strHex = Integer.toHexString(buf[n] & 0xFF);
            sb.append("\\x");
            sb.append(strHex.length() == 1 ? "0" + strHex : strHex);
        }
        return sb.toString();
    }

    public static String unescapeToStr(String str) {
        return new String(BinaryEscape.unescapeToBytes(str));
    }

    public static byte[] unescapeToBytes(String str) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringIterator stringIterator = new StringIterator(str);
        while (stringIterator.hasNext()) {
            char next = stringIterator.next();
            if (next == '\\' && stringIterator.hasNext()) {
                char next2 = stringIterator.next();
                if (next2 == 'u') {
                    if (!stringIterator.hasNext(4)) {
                        throw new UnsupportedOperationException("not enough remaining characters for \\uXXXX" + stringIterator.getErrorToken());
                    }
                    String next3 = stringIterator.next(4);
                    try {
                        outputStream.write((char)Integer.parseInt(next3, 16));
                        continue;
                    } catch (NumberFormatException ex) {
                        throw new UnsupportedOperationException("invalid unicode escape \\u" + next3 + " - must be hex digits" + stringIterator.getErrorToken());
                    }
                }
                if (next2 == 'x') {
                    if (!stringIterator.hasNext(2)) {
                        throw new UnsupportedOperationException("not enough remaining characters for \\uXXXX" + stringIterator.getErrorToken());
                    }
                    String next4 = stringIterator.next(2);
                    try {
                        outputStream.write(Integer.parseInt(next4, 16));
                        continue;
                    } catch (NumberFormatException ex2) {
                        throw new UnsupportedOperationException("invalid unicode escape \\x" + next4 + " - must be hex digits" + stringIterator.getErrorToken());
                    }
                }
                if (next2 == 'n') {
                    outputStream.write(10);
                    continue;
                }
                if (next2 == 'r') {
                    outputStream.write(13);
                    continue;
                }
                if (next2 == 't') {
                    outputStream.write(9);
                    continue;
                }
                if (next2 == 'b') {
                    outputStream.write(8);
                    continue;
                }
                if (next2 == 'f') {
                    outputStream.write(12);
                    continue;
                }
                if (next2 == '\\') {
                    outputStream.write(92);
                    continue;
                }
                if (next2 == '\"') {
                    outputStream.write(34);
                    continue;
                }
                if (next2 == '\'') {
                    outputStream.write(39);
                    continue;
                }
                throw new UnsupportedOperationException("unknown escape \\" + next2 + stringIterator.getErrorToken());
            }
            try {
                outputStream.write(Character.toString(next).getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return outputStream.toByteArray();
    }
}

