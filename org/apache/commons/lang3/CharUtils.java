/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

public class CharUtils {
    private static final String[] CHAR_STRING_ARRAY = new String[128];
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final char LF = '\n';
    public static final char CR = '\r';
    public static final char NUL = '\u0000';

    @Deprecated
    public static Character toCharacterObject(char ch) {
        return Character.valueOf(ch);
    }

    public static Character toCharacterObject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return Character.valueOf(str.charAt(0));
    }

    public static char toChar(Character ch) {
        Validate.notNull(ch, "ch", new Object[0]);
        return ch.charValue();
    }

    public static char toChar(Character ch, char defaultValue) {
        if (ch == null) {
            return defaultValue;
        }
        return ch.charValue();
    }

    public static char toChar(String str) {
        Validate.notEmpty(str, "The String must not be empty", new Object[0]);
        return str.charAt(0);
    }

    public static char toChar(String str, char defaultValue) {
        if (StringUtils.isEmpty(str)) {
            return defaultValue;
        }
        return str.charAt(0);
    }

    public static int toIntValue(char ch) {
        if (!CharUtils.isAsciiNumeric(ch)) {
            throw new IllegalArgumentException("The character " + ch + " is not in the range '0' - '9'");
        }
        return ch - 48;
    }

    public static int toIntValue(char ch, int defaultValue) {
        if (!CharUtils.isAsciiNumeric(ch)) {
            return defaultValue;
        }
        return ch - 48;
    }

    public static int toIntValue(Character ch) {
        Validate.notNull(ch, "ch", new Object[0]);
        return CharUtils.toIntValue(ch.charValue());
    }

    public static int toIntValue(Character ch, int defaultValue) {
        if (ch == null) {
            return defaultValue;
        }
        return CharUtils.toIntValue(ch.charValue(), defaultValue);
    }

    public static String toString(char ch) {
        if (ch < '\u0080') {
            return CHAR_STRING_ARRAY[ch];
        }
        return new String(new char[]{ch});
    }

    public static String toString(Character ch) {
        if (ch == null) {
            return null;
        }
        return CharUtils.toString(ch.charValue());
    }

    public static String unicodeEscaped(char ch) {
        return "\\u" + HEX_DIGITS[ch >> 12 & 0xF] + HEX_DIGITS[ch >> 8 & 0xF] + HEX_DIGITS[ch >> 4 & 0xF] + HEX_DIGITS[ch & 0xF];
    }

    public static String unicodeEscaped(Character ch) {
        if (ch == null) {
            return null;
        }
        return CharUtils.unicodeEscaped(ch.charValue());
    }

    public static boolean isAscii(char ch) {
        return ch < '\u0080';
    }

    public static boolean isAsciiPrintable(char ch) {
        return ch >= ' ' && ch < '\u007f';
    }

    public static boolean isAsciiControl(char ch) {
        return ch < ' ' || ch == '\u007f';
    }

    public static boolean isAsciiAlpha(char ch) {
        return CharUtils.isAsciiAlphaUpper(ch) || CharUtils.isAsciiAlphaLower(ch);
    }

    public static boolean isAsciiAlphaUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isAsciiAlphaLower(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    public static boolean isAsciiNumeric(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public static boolean isAsciiAlphanumeric(char ch) {
        return CharUtils.isAsciiAlpha(ch) || CharUtils.isAsciiNumeric(ch);
    }

    public static int compare(char x, char y) {
        return x - y;
    }

    static {
        for (char c = '\u0000'; c < CHAR_STRING_ARRAY.length; c = (char)(c + '\u0001')) {
            CharUtils.CHAR_STRING_ARRAY[c] = String.valueOf(c);
        }
    }
}

