/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.github.javaparser.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

public final class StringEscapeUtils {
    private static final LookupTranslator JAVA_CTRL_CHARS_UNESCAPE = new LookupTranslator((CharSequence[][])new String[][]{{"\\b", "\b"}, {"\\n", "\n"}, {"\\t", "\t"}, {"\\f", "\f"}, {"\\r", "\r"}});
    private static final LookupTranslator JAVA_CTRL_CHARS_ESCAPE = new LookupTranslator((CharSequence[][])new String[][]{{"\b", "\\b"}, {"\n", "\\n"}, {"\t", "\\t"}, {"\f", "\\f"}, {"\r", "\\r"}});
    private static final CharSequenceTranslator ESCAPE_JAVA = new AggregateTranslator(new CharSequenceTranslator[]{new LookupTranslator((CharSequence[][])new String[][]{{"\"", "\\\""}, {"\\", "\\\\"}}), JAVA_CTRL_CHARS_ESCAPE});
    private static final CharSequenceTranslator UNESCAPE_JAVA = new AggregateTranslator(new CharSequenceTranslator[]{new OctalUnescaper(), new UnicodeUnescaper(), JAVA_CTRL_CHARS_UNESCAPE, new LookupTranslator((CharSequence[][])new String[][]{{"\\\\", "\\"}, {"\\\"", "\""}, {"\\'", "'"}, {"\\", ""}})});
    private static final CharSequenceTranslator UNESCAPE_JAVA_TEXT_BLOCK = new AggregateTranslator(new CharSequenceTranslator[]{new OctalUnescaper(), new UnicodeUnescaper(), JAVA_CTRL_CHARS_UNESCAPE, new LookupTranslator((CharSequence[][])new String[][]{{"\\\\", "\\"}, {"\\\"", "\""}, {"\\'", "'"}, {"\\", ""}, {"\\s", " "}, {"\\\n", ""}})});

    private StringEscapeUtils() {
    }

    public static String escapeJava(String input) {
        return StringEscapeUtils.ESCAPE_JAVA.translate(input);
    }

    public static String unescapeJava(String input) {
        return StringEscapeUtils.UNESCAPE_JAVA.translate(input);
    }

    public static String unescapeJavaTextBlock(String input) {
        return StringEscapeUtils.UNESCAPE_JAVA_TEXT_BLOCK.translate(input);
    }

    private static class UnicodeUnescaper
    extends CharSequenceTranslator {
        private UnicodeUnescaper() {
        }

        @Override
        protected int translate(CharSequence input, int index, Writer out) throws IOException {
            if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
                int i = 2;
                while (index + i < input.length() && input.charAt(index + i) == 'u') {
                    ++i;
                }
                if (index + i < input.length() && input.charAt(index + i) == '+') {
                    ++i;
                }
                if (index + i + 4 <= input.length()) {
                    CharSequence unicode2 = input.subSequence(index + i, index + i + 4);
                    try {
                        int value = Integer.parseInt(unicode2.toString(), 16);
                        out.write((char)value);
                    } catch (NumberFormatException nfe) {
                        throw new IllegalArgumentException("Unable to parse unicode value: " + unicode2, nfe);
                    }
                    return i + 4;
                }
                throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
            }
            return 0;
        }
    }

    private static class OctalUnescaper
    extends CharSequenceTranslator {
        private OctalUnescaper() {
        }

        @Override
        protected int translate(CharSequence input, int index, Writer out) throws IOException {
            int remaining = input.length() - index - 1;
            StringBuilder builder = new StringBuilder();
            if (input.charAt(index) == '\\' && remaining > 0 && this.isOctalDigit(input.charAt(index + 1))) {
                int next = index + 1;
                int next2 = index + 2;
                int next3 = index + 3;
                builder.append(input.charAt(next));
                if (remaining > 1 && this.isOctalDigit(input.charAt(next2))) {
                    builder.append(input.charAt(next2));
                    if (remaining > 2 && this.isZeroToThree(input.charAt(next)) && this.isOctalDigit(input.charAt(next3))) {
                        builder.append(input.charAt(next3));
                    }
                }
                out.write(Integer.parseInt(builder.toString(), 8));
                return 1 + builder.length();
            }
            return 0;
        }

        private boolean isOctalDigit(char ch) {
            return ch >= '0' && ch <= '7';
        }

        private boolean isZeroToThree(char ch) {
            return ch >= '0' && ch <= '3';
        }
    }

    private static class AggregateTranslator
    extends CharSequenceTranslator {
        private final CharSequenceTranslator[] translators;

        private AggregateTranslator(CharSequenceTranslator ... translators) {
            this.translators = translators == null ? null : (CharSequenceTranslator[])translators.clone();
        }

        @Override
        protected int translate(CharSequence input, int index, Writer out) throws IOException {
            for (CharSequenceTranslator translator : this.translators) {
                int consumed = translator.translate(input, index, out);
                if (consumed == 0) continue;
                return consumed;
            }
            return 0;
        }
    }

    private static class LookupTranslator
    extends CharSequenceTranslator {
        private final HashMap<String, String> lookupMap = new HashMap();
        private final HashSet<Character> prefixSet = new HashSet();
        private final int shortest;
        private final int longest;

        private LookupTranslator(CharSequence[] ... lookup) {
            int _shortest = Integer.MAX_VALUE;
            int _longest = 0;
            if (lookup != null) {
                for (CharSequence[] seq : lookup) {
                    this.lookupMap.put(seq[0].toString(), seq[1].toString());
                    this.prefixSet.add(Character.valueOf(seq[0].charAt(0)));
                    int sz = seq[0].length();
                    if (sz < _shortest) {
                        _shortest = sz;
                    }
                    if (sz <= _longest) continue;
                    _longest = sz;
                }
            }
            this.shortest = _shortest;
            this.longest = _longest;
        }

        @Override
        protected int translate(CharSequence input, int index, Writer out) throws IOException {
            if (this.prefixSet.contains(Character.valueOf(input.charAt(index)))) {
                int max = this.longest;
                if (index + this.longest > input.length()) {
                    max = input.length() - index;
                }
                for (int i = max; i >= this.shortest; --i) {
                    CharSequence subSeq = input.subSequence(index, index + i);
                    String result = this.lookupMap.get(subSeq.toString());
                    if (result == null) continue;
                    out.write(result);
                    return i;
                }
            }
            return 0;
        }
    }

    private static abstract class CharSequenceTranslator {
        private CharSequenceTranslator() {
        }

        protected abstract int translate(CharSequence var1, int var2, Writer var3) throws IOException;

        private String translate(CharSequence input) {
            if (input == null) {
                return null;
            }
            try {
                StringWriter writer = new StringWriter(input.length() * 2);
                this.translate(input, writer);
                return writer.toString();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

        private void translate(CharSequence input, Writer out) throws IOException {
            if (out == null) {
                throw new IllegalArgumentException("The Writer must not be null");
            }
            if (input == null) {
                return;
            }
            int pos = 0;
            int len = input.length();
            while (pos < len) {
                int consumed = this.translate(input, pos, out);
                if (consumed == 0) {
                    char c2;
                    char c1 = input.charAt(pos);
                    out.write(c1);
                    if (!Character.isHighSurrogate(c1) || ++pos >= len || !Character.isLowSurrogate(c2 = input.charAt(pos))) continue;
                    out.write(c2);
                    ++pos;
                    continue;
                }
                for (int pt = 0; pt < consumed; ++pt) {
                    pos += Character.charCount(Character.codePointAt(input, pos));
                }
            }
        }
    }
}

