/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jgoodies.common.base;

public class Strings {
    public static final String NO_ELLIPSIS_STRING = "...";
    public static final String ELLIPSIS_STRING = "\u2026";

    protected Strings() {
    }

    public static boolean isBlank(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        for (int i = length - 1; i >= 0; --i) {
            if (Character.isWhitespace(str.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return false;
        }
        for (int i = length - 1; i >= 0; --i) {
            if (Character.isWhitespace(str.charAt(i))) continue;
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isTrimmed(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        return !Character.isWhitespace(str.charAt(0)) && !Character.isWhitespace(str.charAt(length - 1));
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null) {
            return prefix == null;
        }
        if (prefix == null) {
            return false;
        }
        return str.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static String abbreviateCenter(String str, int maxLength) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length <= maxLength) {
            return str;
        }
        int headLength = maxLength / 2;
        int tailLength = maxLength - headLength - 1;
        String head = str.substring(0, headLength);
        String tail = str.substring(length - tailLength, length);
        return head + ELLIPSIS_STRING + tail;
    }

    public static String get(String str, Object ... args) {
        return args == null || args.length == 0 ? str : String.format(str, args);
    }
}

