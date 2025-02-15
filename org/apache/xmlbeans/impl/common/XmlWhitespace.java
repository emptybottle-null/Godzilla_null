/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

public class XmlWhitespace {
    public static final int WS_UNSPECIFIED = 0;
    public static final int WS_PRESERVE = 1;
    public static final int WS_REPLACE = 2;
    public static final int WS_COLLAPSE = 3;

    public static boolean isSpace(char ch) {
        switch (ch) {
            case '\t': 
            case '\n': 
            case '\r': 
            case ' ': {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllSpace(String v) {
        int len = v.length();
        for (int i = 0; i < len; ++i) {
            if (XmlWhitespace.isSpace(v.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static boolean isAllSpace(CharSequence v) {
        int len = v.length();
        for (int i = 0; i < len; ++i) {
            if (XmlWhitespace.isSpace(v.charAt(i))) continue;
            return false;
        }
        return true;
    }

    public static String collapse(String v) {
        return XmlWhitespace.collapse(v, 3);
    }

    /*
     * Unable to fully structure code
     */
    public static String collapse(String v, int wsr) {
        if (wsr == 1 || wsr == 0) {
            return v;
        }
        if (v.indexOf(10) >= 0) {
            v = v.replace('\n', ' ');
        }
        if (v.indexOf(9) >= 0) {
            v = v.replace('\t', ' ');
        }
        if (v.indexOf(13) >= 0) {
            v = v.replace('\r', ' ');
        }
        if (wsr == 2) {
            return v;
        }
        j = 0;
        len = v.length();
        if (len == 0) {
            return v;
        }
        if (v.charAt(0) != ' ') {
            block14: {
                for (j = 2; j < len; j += 2) {
                    if (v.charAt(j) != ' ' || v.charAt(j - 1) != ' ' && j != len - 1 && v.charAt(++j) != ' ') {
                        continue;
                    }
                    break block14;
                }
                if (j != len || v.charAt(j - 1) != ' ') {
                    return v;
                }
            }
            i = j;
        } else {
            while (j + 1 < v.length() && v.charAt(j + 1) == ' ') {
                ++j;
            }
            i = 0;
        }
        ch = v.toCharArray();
        block2: while (++j < len) {
            if (v.charAt(j) == ' ') continue;
            do lbl-1000:
            // 3 sources

            {
                ch[i++] = ch[j++];
                if (j >= len) break block2;
                if (ch[j] != ' ') ** GOTO lbl-1000
                ch[i++] = ch[j++];
                if (j >= len) break block2;
            } while (ch[j] != ' ');
        }
        return new String(ch, 0, i == 0 || ch[i - 1] != ' ' ? i : i - 1);
    }
}

