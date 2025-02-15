/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class ArchiveUtils {
    private static final int MAX_SANITIZED_NAME_LENGTH = 255;

    private ArchiveUtils() {
    }

    public static String toString(ArchiveEntry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append(entry.isDirectory() ? (char)'d' : '-');
        String size = Long.toString(entry.getSize());
        sb.append(' ');
        for (int i = 7; i > size.length(); --i) {
            sb.append(' ');
        }
        sb.append(size);
        sb.append(' ').append(entry.getName());
        return sb.toString();
    }

    public static boolean matchAsciiBuffer(String expected, byte[] buffer, int offset, int length) {
        byte[] buffer1;
        try {
            buffer1 = expected.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer, offset, length, false);
    }

    public static boolean matchAsciiBuffer(String expected, byte[] buffer) {
        return ArchiveUtils.matchAsciiBuffer(expected, buffer, 0, buffer.length);
    }

    public static byte[] toAsciiBytes(String inputString) {
        try {
            return inputString.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toAsciiString(byte[] inputBytes) {
        try {
            return new String(inputBytes, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toAsciiString(byte[] inputBytes, int offset, int length) {
        try {
            return new String(inputBytes, offset, length, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2, boolean ignoreTrailingNulls) {
        int i;
        int minLen = length1 < length2 ? length1 : length2;
        for (i = 0; i < minLen; ++i) {
            if (buffer1[offset1 + i] == buffer2[offset2 + i]) continue;
            return false;
        }
        if (length1 == length2) {
            return true;
        }
        if (ignoreTrailingNulls) {
            if (length1 > length2) {
                for (i = length2; i < length1; ++i) {
                    if (buffer1[offset1 + i] == 0) continue;
                    return false;
                }
            } else {
                for (i = length1; i < length2; ++i) {
                    if (buffer2[offset2 + i] == 0) continue;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2) {
        return ArchiveUtils.isEqual(buffer1, offset1, length1, buffer2, offset2, length2, false);
    }

    public static boolean isEqual(byte[] buffer1, byte[] buffer2) {
        return ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, false);
    }

    public static boolean isEqual(byte[] buffer1, byte[] buffer2, boolean ignoreTrailingNulls) {
        return ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, ignoreTrailingNulls);
    }

    public static boolean isEqualWithNull(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2) {
        return ArchiveUtils.isEqual(buffer1, offset1, length1, buffer2, offset2, length2, true);
    }

    public static boolean isArrayZero(byte[] a, int size) {
        for (int i = 0; i < size; ++i) {
            if (a[i] == 0) continue;
            return false;
        }
        return true;
    }

    public static String sanitize(String s) {
        char[] chars;
        char[] cs = s.toCharArray();
        char[] cArray = chars = cs.length <= 255 ? cs : Arrays.copyOf(cs, 255);
        if (cs.length > 255) {
            for (int i = 252; i < 255; ++i) {
                chars[i] = 46;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            Character.UnicodeBlock block;
            if (!Character.isISOControl(c) && (block = Character.UnicodeBlock.of(c)) != null && block != Character.UnicodeBlock.SPECIALS) {
                sb.append(c);
                continue;
            }
            sb.append('?');
        }
        return sb.toString();
    }
}

