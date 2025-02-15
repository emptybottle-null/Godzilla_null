/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.binary;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.CharSequenceUtils;

public class StringUtils {
    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        return cs1.length() == cs2.length() && CharSequenceUtils.regionMatches(cs1, false, 0, cs2, 0, cs1.length());
    }

    private static byte[] getBytes(String string, Charset charset) {
        if (string == null) {
            return null;
        }
        return string.getBytes(charset);
    }

    private static ByteBuffer getByteBuffer(String string, Charset charset) {
        if (string == null) {
            return null;
        }
        return ByteBuffer.wrap(string.getBytes(charset));
    }

    public static ByteBuffer getByteBufferUtf8(String string) {
        return StringUtils.getByteBuffer(string, Charsets.UTF_8);
    }

    public static byte[] getBytesIso8859_1(String string) {
        return StringUtils.getBytes(string, Charsets.ISO_8859_1);
    }

    public static byte[] getBytesUnchecked(String string, String charsetName) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    public static byte[] getBytesUsAscii(String string) {
        return StringUtils.getBytes(string, Charsets.US_ASCII);
    }

    public static byte[] getBytesUtf16(String string) {
        return StringUtils.getBytes(string, Charsets.UTF_16);
    }

    public static byte[] getBytesUtf16Be(String string) {
        return StringUtils.getBytes(string, Charsets.UTF_16BE);
    }

    public static byte[] getBytesUtf16Le(String string) {
        return StringUtils.getBytes(string, Charsets.UTF_16LE);
    }

    public static byte[] getBytesUtf8(String string) {
        return StringUtils.getBytes(string, Charsets.UTF_8);
    }

    private static IllegalStateException newIllegalStateException(String charsetName, UnsupportedEncodingException e) {
        return new IllegalStateException(charsetName + ": " + e);
    }

    private static String newString(byte[] bytes, Charset charset) {
        return bytes == null ? null : new String(bytes, charset);
    }

    public static String newString(byte[] bytes, String charsetName) {
        if (bytes == null) {
            return null;
        }
        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw StringUtils.newIllegalStateException(charsetName, e);
        }
    }

    public static String newStringIso8859_1(byte[] bytes) {
        return StringUtils.newString(bytes, Charsets.ISO_8859_1);
    }

    public static String newStringUsAscii(byte[] bytes) {
        return StringUtils.newString(bytes, Charsets.US_ASCII);
    }

    public static String newStringUtf16(byte[] bytes) {
        return StringUtils.newString(bytes, Charsets.UTF_16);
    }

    public static String newStringUtf16Be(byte[] bytes) {
        return StringUtils.newString(bytes, Charsets.UTF_16BE);
    }

    public static String newStringUtf16Le(byte[] bytes) {
        return StringUtils.newString(bytes, Charsets.UTF_16LE);
    }

    public static String newStringUtf8(byte[] bytes) {
        return StringUtils.newString(bytes, Charsets.UTF_8);
    }
}

