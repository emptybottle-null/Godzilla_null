/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text;

import java.util.Arrays;
import org.apache.commons.lang3.ArraySorter;
import org.apache.commons.lang3.StringUtils;

@Deprecated
public abstract class StrMatcher {
    private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
    private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
    private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
    private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
    private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
    private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
    private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher('\"');
    private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
    private static final StrMatcher NONE_MATCHER = new NoMatcher();

    public static StrMatcher commaMatcher() {
        return COMMA_MATCHER;
    }

    public static StrMatcher tabMatcher() {
        return TAB_MATCHER;
    }

    public static StrMatcher spaceMatcher() {
        return SPACE_MATCHER;
    }

    public static StrMatcher splitMatcher() {
        return SPLIT_MATCHER;
    }

    public static StrMatcher trimMatcher() {
        return TRIM_MATCHER;
    }

    public static StrMatcher singleQuoteMatcher() {
        return SINGLE_QUOTE_MATCHER;
    }

    public static StrMatcher doubleQuoteMatcher() {
        return DOUBLE_QUOTE_MATCHER;
    }

    public static StrMatcher quoteMatcher() {
        return QUOTE_MATCHER;
    }

    public static StrMatcher noneMatcher() {
        return NONE_MATCHER;
    }

    public static StrMatcher charMatcher(char ch) {
        return new CharMatcher(ch);
    }

    public static StrMatcher charSetMatcher(char ... chars) {
        if (chars == null || chars.length == 0) {
            return NONE_MATCHER;
        }
        if (chars.length == 1) {
            return new CharMatcher(chars[0]);
        }
        return new CharSetMatcher(chars);
    }

    public static StrMatcher charSetMatcher(String chars) {
        if (StringUtils.isEmpty(chars)) {
            return NONE_MATCHER;
        }
        if (chars.length() == 1) {
            return new CharMatcher(chars.charAt(0));
        }
        return new CharSetMatcher(chars.toCharArray());
    }

    public static StrMatcher stringMatcher(String str) {
        if (StringUtils.isEmpty(str)) {
            return NONE_MATCHER;
        }
        return new StringMatcher(str);
    }

    protected StrMatcher() {
    }

    public abstract int isMatch(char[] var1, int var2, int var3, int var4);

    public int isMatch(char[] buffer, int pos) {
        return this.isMatch(buffer, pos, 0, buffer.length);
    }

    static final class TrimMatcher
    extends StrMatcher {
        TrimMatcher() {
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return buffer[pos] <= ' ' ? 1 : 0;
        }
    }

    static final class NoMatcher
    extends StrMatcher {
        NoMatcher() {
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return 0;
        }
    }

    static final class StringMatcher
    extends StrMatcher {
        private final char[] chars;

        StringMatcher(String str) {
            this.chars = str.toCharArray();
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            int len = this.chars.length;
            if (pos + len > bufferEnd) {
                return 0;
            }
            int i = 0;
            while (i < this.chars.length) {
                if (this.chars[i] != buffer[pos]) {
                    return 0;
                }
                ++i;
                ++pos;
            }
            return len;
        }

        public String toString() {
            return super.toString() + ' ' + Arrays.toString(this.chars);
        }
    }

    static final class CharMatcher
    extends StrMatcher {
        private final char ch;

        CharMatcher(char ch) {
            this.ch = ch;
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return this.ch == buffer[pos] ? 1 : 0;
        }
    }

    static final class CharSetMatcher
    extends StrMatcher {
        private final char[] chars;

        CharSetMatcher(char[] chars) {
            this.chars = ArraySorter.sort((char[])chars.clone());
        }

        @Override
        public int isMatch(char[] buffer, int pos, int bufferStart, int bufferEnd) {
            return Arrays.binarySearch(this.chars, buffer[pos]) >= 0 ? 1 : 0;
        }
    }
}

