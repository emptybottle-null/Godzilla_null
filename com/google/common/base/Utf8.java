/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@Beta
@GwtCompatible(emulated=true)
public final class Utf8 {
    public static int encodedLength(CharSequence sequence) {
        int i;
        int utf16Length;
        int utf8Length = utf16Length = sequence.length();
        for (i = 0; i < utf16Length && sequence.charAt(i) < '\u0080'; ++i) {
        }
        while (i < utf16Length) {
            char c = sequence.charAt(i);
            if (c < '\u0800') {
                utf8Length += 127 - c >>> 31;
            } else {
                utf8Length += Utf8.encodedLengthGeneral(sequence, i);
                break;
            }
            ++i;
        }
        if (utf8Length < utf16Length) {
            throw new IllegalArgumentException("UTF-8 length does not fit in int: " + ((long)utf8Length + 0x100000000L));
        }
        return utf8Length;
    }

    private static int encodedLengthGeneral(CharSequence sequence, int start) {
        int utf16Length = sequence.length();
        int utf8Length = 0;
        for (int i = start; i < utf16Length; ++i) {
            char c = sequence.charAt(i);
            if (c < '\u0800') {
                utf8Length += 127 - c >>> 31;
                continue;
            }
            utf8Length += 2;
            if ('\ud800' > c || c > '\udfff') continue;
            if (Character.codePointAt(sequence, i) == c) {
                throw new IllegalArgumentException(Utf8.unpairedSurrogateMsg(i));
            }
            ++i;
        }
        return utf8Length;
    }

    public static boolean isWellFormed(byte[] bytes) {
        return Utf8.isWellFormed(bytes, 0, bytes.length);
    }

    public static boolean isWellFormed(byte[] bytes, int off, int len) {
        int end = off + len;
        Preconditions.checkPositionIndexes(off, end, bytes.length);
        for (int i = off; i < end; ++i) {
            if (bytes[i] >= 0) continue;
            return Utf8.isWellFormedSlowPath(bytes, i, end);
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bytes, int off, int end) {
        int index = off;
        while (true) {
            byte byte2;
            byte byte1;
            if (index >= end) {
                return true;
            }
            if ((byte1 = bytes[index++]) >= 0) continue;
            if (byte1 < -32) {
                if (index == end) {
                    return false;
                }
                if (byte1 >= -62 && bytes[index++] <= -65) continue;
                return false;
            }
            if (byte1 < -16) {
                if (index + 1 >= end) {
                    return false;
                }
                if (!((byte2 = bytes[index++]) > -65 || byte1 == -32 && byte2 < -96 || byte1 == -19 && -96 <= byte2) && bytes[index++] <= -65) continue;
                return false;
            }
            if (index + 2 >= end) {
                return false;
            }
            if ((byte2 = bytes[index++]) > -65 || (byte1 << 28) + (byte2 - -112) >> 30 != 0 || bytes[index++] > -65 || bytes[index++] > -65) break;
        }
        return false;
    }

    private static String unpairedSurrogateMsg(int i) {
        return "Unpaired surrogate at index " + i;
    }

    private Utf8() {
    }
}

