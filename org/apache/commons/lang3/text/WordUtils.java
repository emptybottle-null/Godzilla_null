/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@Deprecated
public class WordUtils {
    public static String wrap(String str, int wrapLength) {
        return WordUtils.wrap(str, wrapLength, null, false);
    }

    public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords) {
        return WordUtils.wrap(str, wrapLength, newLineStr, wrapLongWords, " ");
    }

    public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords, String wrapOn) {
        if (str == null) {
            return null;
        }
        if (newLineStr == null) {
            newLineStr = System.lineSeparator();
        }
        if (wrapLength < 1) {
            wrapLength = 1;
        }
        if (StringUtils.isBlank(wrapOn)) {
            wrapOn = " ";
        }
        Pattern patternToWrapOn = Pattern.compile(wrapOn);
        int inputLineLength = str.length();
        int offset = 0;
        StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
        while (offset < inputLineLength) {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset, Math.min((int)Math.min(Integer.MAX_VALUE, (long)(offset + wrapLength) + 1L), inputLineLength)));
            if (matcher.find()) {
                if (matcher.start() == 0) {
                    offset += matcher.end();
                    continue;
                }
                spaceToWrapAt = matcher.start() + offset;
            }
            if (inputLineLength - offset <= wrapLength) break;
            while (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset;
            }
            if (spaceToWrapAt >= offset) {
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
                continue;
            }
            if (wrapLongWords) {
                wrappedLine.append(str, offset, wrapLength + offset);
                wrappedLine.append(newLineStr);
                offset += wrapLength;
                continue;
            }
            matcher = patternToWrapOn.matcher(str.substring(offset + wrapLength));
            if (matcher.find()) {
                spaceToWrapAt = matcher.start() + offset + wrapLength;
            }
            if (spaceToWrapAt >= 0) {
                wrappedLine.append(str, offset, spaceToWrapAt);
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
                continue;
            }
            wrappedLine.append(str, offset, str.length());
            offset = inputLineLength;
        }
        wrappedLine.append(str, offset, str.length());
        return wrappedLine.toString();
    }

    public static String capitalize(String str) {
        return WordUtils.capitalize(str, null);
    }

    public static String capitalize(String str, char ... delimiters) {
        int delimLen;
        int n = delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        char[] buffer = str.toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < buffer.length; ++i) {
            char ch = buffer[i];
            if (WordUtils.isDelimiter(ch, delimiters)) {
                capitalizeNext = true;
                continue;
            }
            if (!capitalizeNext) continue;
            buffer[i] = Character.toTitleCase(ch);
            capitalizeNext = false;
        }
        return new String(buffer);
    }

    public static String capitalizeFully(String str) {
        return WordUtils.capitalizeFully(str, null);
    }

    public static String capitalizeFully(String str, char ... delimiters) {
        int delimLen;
        int n = delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        str = str.toLowerCase();
        return WordUtils.capitalize(str, delimiters);
    }

    public static String uncapitalize(String str) {
        return WordUtils.uncapitalize(str, null);
    }

    public static String uncapitalize(String str, char ... delimiters) {
        int delimLen;
        int n = delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        char[] buffer = str.toCharArray();
        boolean uncapitalizeNext = true;
        for (int i = 0; i < buffer.length; ++i) {
            char ch = buffer[i];
            if (WordUtils.isDelimiter(ch, delimiters)) {
                uncapitalizeNext = true;
                continue;
            }
            if (!uncapitalizeNext) continue;
            buffer[i] = Character.toLowerCase(ch);
            uncapitalizeNext = false;
        }
        return new String(buffer);
    }

    public static String swapCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        char[] buffer = str.toCharArray();
        boolean whitespace = true;
        for (int i = 0; i < buffer.length; ++i) {
            char ch = buffer[i];
            if (Character.isUpperCase(ch) || Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
                continue;
            }
            if (Character.isLowerCase(ch)) {
                if (whitespace) {
                    buffer[i] = Character.toTitleCase(ch);
                    whitespace = false;
                    continue;
                }
                buffer[i] = Character.toUpperCase(ch);
                continue;
            }
            whitespace = Character.isWhitespace(ch);
        }
        return new String(buffer);
    }

    public static String initials(String str) {
        return WordUtils.initials(str, null);
    }

    public static String initials(String str, char ... delimiters) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        if (delimiters != null && delimiters.length == 0) {
            return "";
        }
        int strLen = str.length();
        char[] buf = new char[strLen / 2 + 1];
        int count = 0;
        boolean lastWasGap = true;
        for (int i = 0; i < strLen; ++i) {
            char ch = str.charAt(i);
            if (WordUtils.isDelimiter(ch, delimiters)) {
                lastWasGap = true;
                continue;
            }
            if (!lastWasGap) continue;
            buf[count++] = ch;
            lastWasGap = false;
        }
        return new String(buf, 0, count);
    }

    public static boolean containsAllWords(CharSequence word, CharSequence ... words) {
        if (StringUtils.isEmpty(word) || ArrayUtils.isEmpty(words)) {
            return false;
        }
        for (CharSequence w : words) {
            if (StringUtils.isBlank(w)) {
                return false;
            }
            Pattern p = Pattern.compile(".*\\b" + w + "\\b.*");
            if (p.matcher(word).matches()) continue;
            return false;
        }
        return true;
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (char delimiter : delimiters) {
            if (ch != delimiter) continue;
            return true;
        }
        return false;
    }
}

