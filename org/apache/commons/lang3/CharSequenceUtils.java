/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class CharSequenceUtils {
    private static final int NOT_FOUND = -1;
    static final int TO_STRING_LIMIT = 16;

    public static CharSequence subSequence(CharSequence cs, int start) {
        return cs == null ? null : cs.subSequence(start, cs.length());
    }

    static int indexOf(CharSequence cs, int searchChar, int start) {
        if (cs instanceof String) {
            return ((String)cs).indexOf(searchChar, start);
        }
        int sz = cs.length();
        if (start < 0) {
            start = 0;
        }
        if (searchChar < 65536) {
            for (int i = start; i < sz; ++i) {
                if (cs.charAt(i) != searchChar) continue;
                return i;
            }
            return -1;
        }
        if (searchChar <= 0x10FFFF) {
            char[] chars = Character.toChars(searchChar);
            for (int i = start; i < sz - 1; ++i) {
                char high = cs.charAt(i);
                char low = cs.charAt(i + 1);
                if (high != chars[0] || low != chars[1]) continue;
                return i;
            }
        }
        return -1;
    }

    static int indexOf(CharSequence cs, CharSequence searchChar, int start) {
        if (cs instanceof String) {
            return ((String)cs).indexOf(searchChar.toString(), start);
        }
        if (cs instanceof StringBuilder) {
            return ((StringBuilder)cs).indexOf(searchChar.toString(), start);
        }
        if (cs instanceof StringBuffer) {
            return ((StringBuffer)cs).indexOf(searchChar.toString(), start);
        }
        return cs.toString().indexOf(searchChar.toString(), start);
    }

    static int lastIndexOf(CharSequence cs, int searchChar, int start) {
        if (cs instanceof String) {
            return ((String)cs).lastIndexOf(searchChar, start);
        }
        int sz = cs.length();
        if (start < 0) {
            return -1;
        }
        if (start >= sz) {
            start = sz - 1;
        }
        if (searchChar < 65536) {
            for (int i = start; i >= 0; --i) {
                if (cs.charAt(i) != searchChar) continue;
                return i;
            }
            return -1;
        }
        if (searchChar <= 0x10FFFF) {
            char[] chars = Character.toChars(searchChar);
            if (start == sz - 1) {
                return -1;
            }
            for (int i = start; i >= 0; --i) {
                char high = cs.charAt(i);
                char low = cs.charAt(i + 1);
                if (chars[0] != high || chars[1] != low) continue;
                return i;
            }
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    static int lastIndexOf(CharSequence cs, CharSequence searchChar, int start) {
        if (searchChar == null || cs == null) {
            return -1;
        }
        if (searchChar instanceof String) {
            if (cs instanceof String) {
                return ((String)cs).lastIndexOf((String)searchChar, start);
            }
            if (cs instanceof StringBuilder) {
                return ((StringBuilder)cs).lastIndexOf((String)searchChar, start);
            }
            if (cs instanceof StringBuffer) {
                return ((StringBuffer)cs).lastIndexOf((String)searchChar, start);
            }
        }
        len1 = cs.length();
        len2 = searchChar.length();
        if (start > len1) {
            start = len1;
        }
        if (start < 0 || len2 < 0 || len2 > len1) {
            return -1;
        }
        if (len2 == 0) {
            return start;
        }
        if (len2 <= 16) {
            if (cs instanceof String) {
                return ((String)cs).lastIndexOf(searchChar.toString(), start);
            }
            if (cs instanceof StringBuilder) {
                return ((StringBuilder)cs).lastIndexOf(searchChar.toString(), start);
            }
            if (cs instanceof StringBuffer) {
                return ((StringBuffer)cs).lastIndexOf(searchChar.toString(), start);
            }
        }
        if (start + len2 > len1) {
            start = len1 - len2;
        }
        char0 = searchChar.charAt(0);
        i = start;
        do lbl-1000:
        // 3 sources

        {
            block14: {
                if (cs.charAt(i) == char0) break block14;
                if (--i >= 0) ** GOTO lbl-1000
                return -1;
            }
            if (!CharSequenceUtils.checkLaterThan1(cs, searchChar, len2, i)) continue;
            return i;
        } while (--i >= 0);
        return -1;
    }

    private static boolean checkLaterThan1(CharSequence cs, CharSequence searchChar, int len2, int start1) {
        int i = 1;
        for (int j = len2 - 1; i <= j; ++i, --j) {
            if (cs.charAt(start1 + i) == searchChar.charAt(i) && cs.charAt(start1 + j) == searchChar.charAt(j)) continue;
            return false;
        }
        return true;
    }

    public static char[] toCharArray(CharSequence source) {
        int len = StringUtils.length(source);
        if (len == 0) {
            return ArrayUtils.EMPTY_CHAR_ARRAY;
        }
        if (source instanceof String) {
            return ((String)source).toCharArray();
        }
        char[] array = new char[len];
        for (int i = 0; i < len; ++i) {
            array[i] = source.charAt(i);
        }
        return array;
    }

    static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
        if (cs instanceof String && substring instanceof String) {
            return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;
        int srcLen = cs.length() - thisStart;
        int otherLen = substring.length() - start;
        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }
        if (srcLen < length || otherLen < length) {
            return false;
        }
        while (tmpLen-- > 0) {
            char u2;
            char c2;
            char c1;
            if ((c1 = cs.charAt(index1++)) == (c2 = substring.charAt(index2++))) continue;
            if (!ignoreCase) {
                return false;
            }
            char u1 = Character.toUpperCase(c1);
            if (u1 == (u2 = Character.toUpperCase(c2)) || Character.toLowerCase(u1) == Character.toLowerCase(u2)) continue;
            return false;
        }
        return true;
    }
}

