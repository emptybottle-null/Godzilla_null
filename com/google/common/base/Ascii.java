/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
public final class Ascii {
    public static final byte NUL = 0;
    public static final byte SOH = 1;
    public static final byte STX = 2;
    public static final byte ETX = 3;
    public static final byte EOT = 4;
    public static final byte ENQ = 5;
    public static final byte ACK = 6;
    public static final byte BEL = 7;
    public static final byte BS = 8;
    public static final byte HT = 9;
    public static final byte LF = 10;
    public static final byte NL = 10;
    public static final byte VT = 11;
    public static final byte FF = 12;
    public static final byte CR = 13;
    public static final byte SO = 14;
    public static final byte SI = 15;
    public static final byte DLE = 16;
    public static final byte DC1 = 17;
    public static final byte XON = 17;
    public static final byte DC2 = 18;
    public static final byte DC3 = 19;
    public static final byte XOFF = 19;
    public static final byte DC4 = 20;
    public static final byte NAK = 21;
    public static final byte SYN = 22;
    public static final byte ETB = 23;
    public static final byte CAN = 24;
    public static final byte EM = 25;
    public static final byte SUB = 26;
    public static final byte ESC = 27;
    public static final byte FS = 28;
    public static final byte GS = 29;
    public static final byte RS = 30;
    public static final byte US = 31;
    public static final byte SP = 32;
    public static final byte SPACE = 32;
    public static final byte DEL = 127;
    public static final char MIN = '\u0000';
    public static final char MAX = '\u007f';
    private static final char CASE_MASK = ' ';

    private Ascii() {
    }

    public static String toLowerCase(String string) {
        int length = string.length();
        for (int i = 0; i < length; ++i) {
            if (!Ascii.isUpperCase(string.charAt(i))) continue;
            char[] chars = string.toCharArray();
            while (i < length) {
                char c = chars[i];
                if (Ascii.isUpperCase(c)) {
                    chars[i] = (char)(c ^ 0x20);
                }
                ++i;
            }
            return String.valueOf(chars);
        }
        return string;
    }

    public static String toLowerCase(CharSequence chars) {
        if (chars instanceof String) {
            return Ascii.toLowerCase((String)chars);
        }
        char[] newChars = new char[chars.length()];
        for (int i = 0; i < newChars.length; ++i) {
            newChars[i] = Ascii.toLowerCase(chars.charAt(i));
        }
        return String.valueOf(newChars);
    }

    public static char toLowerCase(char c) {
        return Ascii.isUpperCase(c) ? (char)(c ^ 0x20) : c;
    }

    public static String toUpperCase(String string) {
        int length = string.length();
        for (int i = 0; i < length; ++i) {
            if (!Ascii.isLowerCase(string.charAt(i))) continue;
            char[] chars = string.toCharArray();
            while (i < length) {
                char c = chars[i];
                if (Ascii.isLowerCase(c)) {
                    chars[i] = (char)(c ^ 0x20);
                }
                ++i;
            }
            return String.valueOf(chars);
        }
        return string;
    }

    public static String toUpperCase(CharSequence chars) {
        if (chars instanceof String) {
            return Ascii.toUpperCase((String)chars);
        }
        char[] newChars = new char[chars.length()];
        for (int i = 0; i < newChars.length; ++i) {
            newChars[i] = Ascii.toUpperCase(chars.charAt(i));
        }
        return String.valueOf(newChars);
    }

    public static char toUpperCase(char c) {
        return Ascii.isLowerCase(c) ? (char)(c ^ 0x20) : c;
    }

    public static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public static String truncate(CharSequence seq, int maxLength, String truncationIndicator) {
        Preconditions.checkNotNull(seq);
        int truncationLength = maxLength - truncationIndicator.length();
        Preconditions.checkArgument(truncationLength >= 0, "maxLength (%s) must be >= length of the truncation indicator (%s)", maxLength, truncationIndicator.length());
        if (seq.length() <= maxLength) {
            String string = seq.toString();
            if (string.length() <= maxLength) {
                return string;
            }
            seq = string;
        }
        return new StringBuilder(maxLength).append(seq, 0, truncationLength).append(truncationIndicator).toString();
    }

    public static boolean equalsIgnoreCase(CharSequence s1, CharSequence s2) {
        int length = s1.length();
        if (s1 == s2) {
            return true;
        }
        if (length != s2.length()) {
            return false;
        }
        for (int i = 0; i < length; ++i) {
            int alphaIndex;
            char c2;
            char c1 = s1.charAt(i);
            if (c1 == (c2 = s2.charAt(i)) || (alphaIndex = Ascii.getAlphaIndex(c1)) < 26 && alphaIndex == Ascii.getAlphaIndex(c2)) continue;
            return false;
        }
        return true;
    }

    private static int getAlphaIndex(char c) {
        return (char)((c | 0x20) - 97);
    }
}

