/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class StringEscapeUtils {
    public static final char LF = '\n';
    public static final char CR = '\r';
    private static final char CSV_DELIMITER = ',';
    private static final char CSV_QUOTE = '\"';
    private static final String CSV_QUOTE_STR = String.valueOf('\"');
    private static final char[] CSV_SEARCH_CHARS = new char[]{',', '\"', '\r', '\n'};

    public static String escapeJava(String str) {
        return StringEscapeUtils.escapeJavaStyleString(str, false);
    }

    public static void escapeJava(Writer out, String str) throws IOException {
        StringEscapeUtils.escapeJavaStyleString(out, str, false);
    }

    public static String escapeJavaScript(String str) {
        return StringEscapeUtils.escapeJavaStyleString(str, true);
    }

    public static void escapeJavaScript(Writer out, String str) throws IOException {
        StringEscapeUtils.escapeJavaStyleString(out, str, true);
    }

    private static String escapeJavaStyleString(String str, boolean escapeSingleQuotes) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter(str.length() * 2);
            StringEscapeUtils.escapeJavaStyleString(writer, str, escapeSingleQuotes);
            return writer.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    private static void escapeJavaStyleString(Writer out, String str, boolean escapeSingleQuote) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str == null) {
            return;
        }
        int sz = str.length();
        block13: for (int i = 0; i < sz; ++i) {
            char ch = str.charAt(i);
            if (ch > '\u0fff') {
                out.write("\\u" + StringEscapeUtils.hex(ch));
                continue;
            }
            if (ch > '\u00ff') {
                out.write("\\u0" + StringEscapeUtils.hex(ch));
                continue;
            }
            if (ch > '\u007f') {
                out.write("\\u00" + StringEscapeUtils.hex(ch));
                continue;
            }
            if (ch < ' ') {
                switch (ch) {
                    case '\b': {
                        out.write(92);
                        out.write(98);
                        break;
                    }
                    case '\n': {
                        out.write(92);
                        out.write(110);
                        break;
                    }
                    case '\t': {
                        out.write(92);
                        out.write(116);
                        break;
                    }
                    case '\f': {
                        out.write(92);
                        out.write(102);
                        break;
                    }
                    case '\r': {
                        out.write(92);
                        out.write(114);
                        break;
                    }
                    default: {
                        if (ch > '\u000f') {
                            out.write("\\u00" + StringEscapeUtils.hex(ch));
                            break;
                        }
                        out.write("\\u000" + StringEscapeUtils.hex(ch));
                        break;
                    }
                }
                continue;
            }
            switch (ch) {
                case '\'': {
                    if (escapeSingleQuote) {
                        out.write(92);
                    }
                    out.write(39);
                    continue block13;
                }
                case '\"': {
                    out.write(92);
                    out.write(34);
                    continue block13;
                }
                case '\\': {
                    out.write(92);
                    out.write(92);
                    continue block13;
                }
                case '/': {
                    out.write(92);
                    out.write(47);
                    continue block13;
                }
                default: {
                    out.write(ch);
                }
            }
        }
    }

    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase();
    }

    public static String unescapeJava(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter writer = new StringWriter(str.length());
            StringEscapeUtils.unescapeJava(writer, str);
            return writer.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public static void unescapeJava(Writer out, String str) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("The Writer must not be null");
        }
        if (str == null) {
            return;
        }
        int sz = str.length();
        StringBuffer unicode2 = new StringBuffer(4);
        boolean hadSlash = false;
        boolean inUnicode = false;
        for (int i = 0; i < sz; ++i) {
            char ch = str.charAt(i);
            if (inUnicode) {
                unicode2.append(ch);
                if (unicode2.length() != 4) continue;
                try {
                    int value = Integer.parseInt(unicode2.toString(), 16);
                    out.write((char)value);
                    unicode2.setLength(0);
                    inUnicode = false;
                    hadSlash = false;
                    continue;
                } catch (NumberFormatException nfe) {
                    throw new RuntimeException("Unable to parse unicode value: " + unicode2, nfe);
                }
            }
            if (hadSlash) {
                hadSlash = false;
                switch (ch) {
                    case '\\': {
                        out.write(92);
                        break;
                    }
                    case '\'': {
                        out.write(39);
                        break;
                    }
                    case '\"': {
                        out.write(34);
                        break;
                    }
                    case 'r': {
                        out.write(13);
                        break;
                    }
                    case 'f': {
                        out.write(12);
                        break;
                    }
                    case 't': {
                        out.write(9);
                        break;
                    }
                    case 'n': {
                        out.write(10);
                        break;
                    }
                    case 'b': {
                        out.write(8);
                        break;
                    }
                    case 'u': {
                        inUnicode = true;
                        break;
                    }
                    default: {
                        out.write(ch);
                        break;
                    }
                }
                continue;
            }
            if (ch == '\\') {
                hadSlash = true;
                continue;
            }
            out.write(ch);
        }
        if (hadSlash) {
            out.write(92);
        }
    }

    public static String unescapeJavaScript(String str) {
        return StringEscapeUtils.unescapeJava(str);
    }

    public static void unescapeJavaScript(Writer out, String str) throws IOException {
        StringEscapeUtils.unescapeJava(out, str);
    }
}

