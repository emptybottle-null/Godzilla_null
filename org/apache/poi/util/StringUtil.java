/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

@Internal
public class StringUtil {
    protected static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
    private static final int MAX_RECORD_LENGTH = 10000000;
    public static final Charset UTF16LE = StandardCharsets.UTF_16LE;
    public static final Charset UTF8 = StandardCharsets.UTF_8;
    public static final Charset WIN_1252 = Charset.forName("cp1252");
    public static final Charset BIG5 = Charset.forName("Big5");
    private static Map<Integer, Integer> msCodepointToUnicode;
    private static final int[] symbolMap_f020;
    private static final int[] symbolMap_f0a0;

    private StringUtil() {
    }

    public static String getFromUnicodeLE(byte[] string, int offset, int len) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (offset < 0 || offset >= string.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset " + offset + " (String data is of length " + string.length + ")");
        }
        if (len < 0 || (string.length - offset) / 2 < len) {
            throw new IllegalArgumentException("Illegal length " + len);
        }
        return new String(string, offset, len * 2, UTF16LE);
    }

    public static String getFromUnicodeLE(byte[] string) {
        if (string.length == 0) {
            return "";
        }
        return StringUtil.getFromUnicodeLE(string, 0, string.length / 2);
    }

    public static byte[] getToUnicodeLE(String string) {
        return string.getBytes(UTF16LE);
    }

    public static String getFromCompressedUnicode(byte[] string, int offset, int len) {
        int len_to_use = Math.min(len, string.length - offset);
        return new String(string, offset, len_to_use, ISO_8859_1);
    }

    public static String readCompressedUnicode(LittleEndianInput in, int nChars) {
        byte[] buf = IOUtils.safelyAllocate(nChars, 10000000);
        in.readFully(buf);
        return new String(buf, ISO_8859_1);
    }

    public static String readUnicodeString(LittleEndianInput in) {
        int nChars = in.readUShort();
        byte flag = in.readByte();
        if ((flag & 1) == 0) {
            return StringUtil.readCompressedUnicode(in, nChars);
        }
        return StringUtil.readUnicodeLE(in, nChars);
    }

    public static String readUnicodeString(LittleEndianInput in, int nChars) {
        byte is16Bit = in.readByte();
        if ((is16Bit & 1) == 0) {
            return StringUtil.readCompressedUnicode(in, nChars);
        }
        return StringUtil.readUnicodeLE(in, nChars);
    }

    public static void writeUnicodeString(LittleEndianOutput out, String value) {
        int nChars = value.length();
        out.writeShort(nChars);
        boolean is16Bit = StringUtil.hasMultibyte(value);
        out.writeByte(is16Bit ? 1 : 0);
        if (is16Bit) {
            StringUtil.putUnicodeLE(value, out);
        } else {
            StringUtil.putCompressedUnicode(value, out);
        }
    }

    public static void writeUnicodeStringFlagAndData(LittleEndianOutput out, String value) {
        boolean is16Bit = StringUtil.hasMultibyte(value);
        out.writeByte(is16Bit ? 1 : 0);
        if (is16Bit) {
            StringUtil.putUnicodeLE(value, out);
        } else {
            StringUtil.putCompressedUnicode(value, out);
        }
    }

    public static int getEncodedSize(String value) {
        int result = 3;
        return result += value.length() * (StringUtil.hasMultibyte(value) ? 2 : 1);
    }

    public static void putCompressedUnicode(String input, byte[] output, int offset) {
        byte[] bytes = input.getBytes(ISO_8859_1);
        System.arraycopy(bytes, 0, output, offset, bytes.length);
    }

    public static void putCompressedUnicode(String input, LittleEndianOutput out) {
        byte[] bytes = input.getBytes(ISO_8859_1);
        out.write(bytes);
    }

    public static void putUnicodeLE(String input, byte[] output, int offset) {
        byte[] bytes = input.getBytes(UTF16LE);
        System.arraycopy(bytes, 0, output, offset, bytes.length);
    }

    public static void putUnicodeLE(String input, LittleEndianOutput out) {
        byte[] bytes = input.getBytes(UTF16LE);
        out.write(bytes);
    }

    public static String readUnicodeLE(LittleEndianInput in, int nChars) {
        byte[] bytes = IOUtils.safelyAllocate((long)nChars * 2L, 10000000);
        in.readFully(bytes);
        return new String(bytes, UTF16LE);
    }

    public static String getPreferredEncoding() {
        return ISO_8859_1.name();
    }

    public static boolean hasMultibyte(String value) {
        if (value == null) {
            return false;
        }
        for (char c : value.toCharArray()) {
            if (c <= '\u00ff') continue;
            return true;
        }
        return false;
    }

    public static boolean isUnicodeString(String value) {
        return !value.equals(new String(value.getBytes(ISO_8859_1), ISO_8859_1));
    }

    public static boolean startsWithIgnoreCase(String haystack, String prefix) {
        return haystack.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    public static boolean endsWithIgnoreCase(String haystack, String suffix) {
        int length = suffix.length();
        int start = haystack.length() - length;
        return haystack.regionMatches(true, start, suffix, 0, length);
    }

    @Internal
    public static String toLowerCase(char c) {
        return Character.toString(c).toLowerCase(Locale.ROOT);
    }

    @Internal
    public static String toUpperCase(char c) {
        return Character.toString(c).toUpperCase(Locale.ROOT);
    }

    @Internal
    public static boolean isUpperCase(char c) {
        String s = Character.toString(c);
        return s.toUpperCase(Locale.ROOT).equals(s);
    }

    public static String mapMsCodepointString(String string) {
        int msCodepoint;
        if (string == null || string.isEmpty()) {
            return string;
        }
        StringUtil.initMsCodepointMap();
        StringBuilder sb = new StringBuilder();
        int length = string.length();
        for (int offset = 0; offset < length; offset += Character.charCount(msCodepoint)) {
            msCodepoint = string.codePointAt(offset);
            Integer uniCodepoint = msCodepointToUnicode.get(msCodepoint);
            sb.appendCodePoint(uniCodepoint == null ? msCodepoint : uniCodepoint);
        }
        return sb.toString();
    }

    public static synchronized void mapMsCodepoint(int msCodepoint, int unicodeCodepoint) {
        StringUtil.initMsCodepointMap();
        msCodepointToUnicode.put(msCodepoint, unicodeCodepoint);
    }

    private static synchronized void initMsCodepointMap() {
        if (msCodepointToUnicode != null) {
            return;
        }
        msCodepointToUnicode = new HashMap<Integer, Integer>();
        int i = 61472;
        for (int ch : symbolMap_f020) {
            msCodepointToUnicode.put(i++, ch);
        }
        i = 61600;
        for (int ch : symbolMap_f0a0) {
            msCodepointToUnicode.put(i++, ch);
        }
    }

    @Internal
    public static String join(Object[] array, String separator) {
        if (array == null || array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(separator).append(array[i]);
        }
        return sb.toString();
    }

    @Internal
    public static String join(Object[] array) {
        if (array == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : array) {
            sb.append(o);
        }
        return sb.toString();
    }

    @Internal
    public static String join(String separator, Object ... array) {
        return StringUtil.join(array, separator);
    }

    public static int countMatches(CharSequence haystack, char needle) {
        if (haystack == null) {
            return 0;
        }
        int count = 0;
        int length = haystack.length();
        for (int i = 0; i < length; ++i) {
            if (haystack.charAt(i) != needle) continue;
            ++count;
        }
        return count;
    }

    public static String getFromUnicodeLE0Terminated(byte[] string, int offset, int len) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        int newLen;
        int newMaxLen;
        String prefix;
        int newOffset;
        if (offset < 0 || offset >= string.length) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset " + offset + " (String data is of length " + string.length + ")");
        }
        if (len < 0 || (string.length - offset) / 2 < len) {
            throw new IllegalArgumentException("Illegal length " + len);
        }
        if (len > 0 && offset < string.length - 1 && string[offset] == 0 && string[offset + 1] == 0) {
            newOffset = offset + 2;
            prefix = "?";
            int cp = len > 1 ? (int)LittleEndian.getShort(string, offset + 2) : 0;
            newMaxLen = Character.isJavaIdentifierPart(cp) ? len - 1 : 0;
        } else {
            newOffset = offset;
            prefix = "";
            newMaxLen = len;
        }
        for (newLen = 0; newLen < newMaxLen && (string[newOffset + newLen * 2] != 0 || string[newOffset + newLen * 2 + 1] != 0); ++newLen) {
        }
        newLen = Math.min(newLen, newMaxLen);
        return prefix + (newLen == 0 ? "" : new String(string, newOffset, newLen * 2, UTF16LE));
    }

    static {
        symbolMap_f020 = new int[]{32, 33, 8704, 35, 8707, 37, 38, 8717, 40, 41, 8727, 43, 44, 8722, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 8773, 913, 914, 935, 916, 917, 934, 915, 919, 921, 977, 922, 923, 924, 925, 927, 928, 920, 929, 931, 932, 933, 962, 937, 926, 936, 918, 91, 8765, 93, 8869, 95, 32, 945, 946, 967, 948, 949, 966, 947, 951, 953, 981, 954, 955, 956, 957, 959, 960, 952, 961, 963, 964, 965, 982, 969, 958, 968, 950, 123, 124, 125, 8764, 32};
        symbolMap_f0a0 = new int[]{8364, 978, 8242, 8804, 8260, 8734, 402, 9827, 9830, 9829, 9824, 8596, 8591, 8593, 8594, 8595, 176, 177, 8243, 8805, 215, 181, 8706, 8729, 247, 8800, 8801, 8776, 8230, 9168, 9135, 8629, 8501, 8475, 8476, 8472, 8855, 8853, 8709, 8745, 8746, 8835, 8839, 8836, 8834, 8838, 8712, 8713, 8736, 8711, 174, 169, 8482, 8719, 8730, 8901, 172, 8743, 8744, 8660, 8656, 8657, 8658, 8659, 9674, 9001, 174, 169, 8482, 8721, 9115, 9116, 9117, 9121, 9122, 9123, 9127, 9128, 9129, 9130, 32, 9002, 8747, 8992, 9134, 8993, 9118, 9119, 9120, 9124, 9125, 9126, 9131, 9132, 9133, 32};
    }

    public static class StringsIterator
    implements Iterator<String> {
        private String[] strings = new String[0];
        private int position;

        public StringsIterator(String[] strings) {
            if (strings != null) {
                this.strings = (String[])strings.clone();
            }
        }

        @Override
        public boolean hasNext() {
            return this.position < this.strings.length;
        }

        @Override
        public String next() {
            int ourPos;
            if ((ourPos = this.position++) >= this.strings.length) {
                throw new ArrayIndexOutOfBoundsException(ourPos);
            }
            return this.strings[ourPos];
        }

        @Override
        public void remove() {
        }
    }
}

