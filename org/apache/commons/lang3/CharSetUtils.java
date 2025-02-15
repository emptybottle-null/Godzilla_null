/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.StringUtils;

public class CharSetUtils {
    public static boolean containsAny(String str, String ... set) {
        if (StringUtils.isEmpty(str) || CharSetUtils.deepEmpty(set)) {
            return false;
        }
        CharSet chars = CharSet.getInstance(set);
        for (char c : str.toCharArray()) {
            if (!chars.contains(c)) continue;
            return true;
        }
        return false;
    }

    public static int count(String str, String ... set) {
        if (StringUtils.isEmpty(str) || CharSetUtils.deepEmpty(set)) {
            return 0;
        }
        CharSet chars = CharSet.getInstance(set);
        int count = 0;
        for (char c : str.toCharArray()) {
            if (!chars.contains(c)) continue;
            ++count;
        }
        return count;
    }

    private static boolean deepEmpty(String[] strings) {
        if (strings != null) {
            for (String s : strings) {
                if (!StringUtils.isNotEmpty(s)) continue;
                return false;
            }
        }
        return true;
    }

    public static String delete(String str, String ... set) {
        if (StringUtils.isEmpty(str) || CharSetUtils.deepEmpty(set)) {
            return str;
        }
        return CharSetUtils.modify(str, set, false);
    }

    public static String keep(String str, String ... set) {
        if (str == null) {
            return null;
        }
        if (str.isEmpty() || CharSetUtils.deepEmpty(set)) {
            return "";
        }
        return CharSetUtils.modify(str, set, true);
    }

    private static String modify(String str, String[] set, boolean expect) {
        char[] chrs;
        CharSet chars = CharSet.getInstance(set);
        StringBuilder buffer = new StringBuilder(str.length());
        for (char chr : chrs = str.toCharArray()) {
            if (chars.contains(chr) != expect) continue;
            buffer.append(chr);
        }
        return buffer.toString();
    }

    public static String squeeze(String str, String ... set) {
        if (StringUtils.isEmpty(str) || CharSetUtils.deepEmpty(set)) {
            return str;
        }
        CharSet chars = CharSet.getInstance(set);
        StringBuilder buffer = new StringBuilder(str.length());
        char[] chrs = str.toCharArray();
        int sz = chrs.length;
        char lastChar = chrs[0];
        char ch = ' ';
        Character inChars = null;
        Character notInChars = null;
        buffer.append(lastChar);
        for (int i = 1; i < sz; ++i) {
            ch = chrs[i];
            if (ch == lastChar) {
                if (inChars != null && ch == inChars.charValue()) continue;
                if (notInChars == null || ch != notInChars.charValue()) {
                    if (chars.contains(ch)) {
                        inChars = Character.valueOf(ch);
                        continue;
                    }
                    notInChars = Character.valueOf(ch);
                }
            }
            buffer.append(ch);
            lastChar = ch;
        }
        return buffer.toString();
    }
}

