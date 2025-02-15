/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.jediterm.terminal.util;

import com.jediterm.terminal.emulator.charset.CharacterSets;
import com.jediterm.terminal.model.CharBuffer;
import java.util.Arrays;

public class CharUtils {
    public static final int ESC = 27;
    public static final int DEL = 127;
    public static final char NUL_CHAR = '\u0000';
    public static final char EMPTY_CHAR = ' ';
    public static final char DWC = '\ue000';
    private static final String[] NONPRINTING_NAMES = new String[]{"NUL", "SOH", "STX", "ETX", "EOT", "ENQ", "ACK", "BEL", "BS", "TAB", "LF", "VT", "FF", "CR", "S0", "S1", "DLE", "DC1", "DC2", "DC3", "DC4", "NAK", "SYN", "ETB", "CAN", "EM", "SUB", "ESC", "FS", "GS", "RS", "US"};
    public static byte[] VT102_RESPONSE = CharUtils.makeCode(27, 91, 63, 54, 99);
    private static final char[][] COMBINING = new char[][]{{'\u0300', '\u036f'}, {'\u0483', '\u0486'}, {'\u0488', '\u0489'}, {'\u0591', '\u05bd'}, {'\u05bf', '\u05bf'}, {'\u05c1', '\u05c2'}, {'\u05c4', '\u05c5'}, {'\u05c7', '\u05c7'}, {'\u0600', '\u0603'}, {'\u0610', '\u0615'}, {'\u064b', '\u065e'}, {'\u0670', '\u0670'}, {'\u06d6', '\u06e4'}, {'\u06e7', '\u06e8'}, {'\u06ea', '\u06ed'}, {'\u070f', '\u070f'}, {'\u0711', '\u0711'}, {'\u0730', '\u074a'}, {'\u07a6', '\u07b0'}, {'\u07eb', '\u07f3'}, {'\u0901', '\u0902'}, {'\u093c', '\u093c'}, {'\u0941', '\u0948'}, {'\u094d', '\u094d'}, {'\u0951', '\u0954'}, {'\u0962', '\u0963'}, {'\u0981', '\u0981'}, {'\u09bc', '\u09bc'}, {'\u09c1', '\u09c4'}, {'\u09cd', '\u09cd'}, {'\u09e2', '\u09e3'}, {'\u0a01', '\u0a02'}, {'\u0a3c', '\u0a3c'}, {'\u0a41', '\u0a42'}, {'\u0a47', '\u0a48'}, {'\u0a4b', '\u0a4d'}, {'\u0a70', '\u0a71'}, {'\u0a81', '\u0a82'}, {'\u0abc', '\u0abc'}, {'\u0ac1', '\u0ac5'}, {'\u0ac7', '\u0ac8'}, {'\u0acd', '\u0acd'}, {'\u0ae2', '\u0ae3'}, {'\u0b01', '\u0b01'}, {'\u0b3c', '\u0b3c'}, {'\u0b3f', '\u0b3f'}, {'\u0b41', '\u0b43'}, {'\u0b4d', '\u0b4d'}, {'\u0b56', '\u0b56'}, {'\u0b82', '\u0b82'}, {'\u0bc0', '\u0bc0'}, {'\u0bcd', '\u0bcd'}, {'\u0c3e', '\u0c40'}, {'\u0c46', '\u0c48'}, {'\u0c4a', '\u0c4d'}, {'\u0c55', '\u0c56'}, {'\u0cbc', '\u0cbc'}, {'\u0cbf', '\u0cbf'}, {'\u0cc6', '\u0cc6'}, {'\u0ccc', '\u0ccd'}, {'\u0ce2', '\u0ce3'}, {'\u0d41', '\u0d43'}, {'\u0d4d', '\u0d4d'}, {'\u0dca', '\u0dca'}, {'\u0dd2', '\u0dd4'}, {'\u0dd6', '\u0dd6'}, {'\u0e31', '\u0e31'}, {'\u0e34', '\u0e3a'}, {'\u0e47', '\u0e4e'}, {'\u0eb1', '\u0eb1'}, {'\u0eb4', '\u0eb9'}, {'\u0ebb', '\u0ebc'}, {'\u0ec8', '\u0ecd'}, {'\u0f18', '\u0f19'}, {'\u0f35', '\u0f35'}, {'\u0f37', '\u0f37'}, {'\u0f39', '\u0f39'}, {'\u0f71', '\u0f7e'}, {'\u0f80', '\u0f84'}, {'\u0f86', '\u0f87'}, {'\u0f90', '\u0f97'}, {'\u0f99', '\u0fbc'}, {'\u0fc6', '\u0fc6'}, {'\u102d', '\u1030'}, {'\u1032', '\u1032'}, {'\u1036', '\u1037'}, {'\u1039', '\u1039'}, {'\u1058', '\u1059'}, {'\u1160', '\u11ff'}, {'\u135f', '\u135f'}, {'\u1712', '\u1714'}, {'\u1732', '\u1734'}, {'\u1752', '\u1753'}, {'\u1772', '\u1773'}, {'\u17b4', '\u17b5'}, {'\u17b7', '\u17bd'}, {'\u17c6', '\u17c6'}, {'\u17c9', '\u17d3'}, {'\u17dd', '\u17dd'}, {'\u180b', '\u180d'}, {'\u18a9', '\u18a9'}, {'\u1920', '\u1922'}, {'\u1927', '\u1928'}, {'\u1932', '\u1932'}, {'\u1939', '\u193b'}, {'\u1a17', '\u1a18'}, {'\u1b00', '\u1b03'}, {'\u1b34', '\u1b34'}, {'\u1b36', '\u1b3a'}, {'\u1b3c', '\u1b3c'}, {'\u1b42', '\u1b42'}, {'\u1b6b', '\u1b73'}, {'\u1dc0', '\u1dca'}, {'\u1dfe', '\u1dff'}, {'\u200b', '\u200f'}, {'\u202a', '\u202e'}, {'\u2060', '\u2063'}, {'\u206a', '\u206f'}, {'\u20d0', '\u20ef'}, {'\u302a', '\u302f'}, {'\u3099', '\u309a'}, {'\ua806', '\ua806'}, {'\ua80b', '\ua80b'}, {'\ua825', '\ua826'}, {'\ufb1e', '\ufb1e'}, {'\ufe00', '\ufe0f'}, {'\ufe20', '\ufe23'}, {'\ufeff', '\ufeff'}, {'\ufff9', '\ufffb'}};
    private static final char[][] AMBIGUOUS = new char[][]{{'\u00a1', '\u00a1'}, {'\u00a4', '\u00a4'}, {'\u00a7', '\u00a8'}, {'\u00aa', '\u00aa'}, {'\u00ae', '\u00ae'}, {'\u00b0', '\u00b4'}, {'\u00b6', '\u00ba'}, {'\u00bc', '\u00bf'}, {'\u00c6', '\u00c6'}, {'\u00d0', '\u00d0'}, {'\u00d7', '\u00d8'}, {'\u00de', '\u00e1'}, {'\u00e6', '\u00e6'}, {'\u00e8', '\u00ea'}, {'\u00ec', '\u00ed'}, {'\u00f0', '\u00f0'}, {'\u00f2', '\u00f3'}, {'\u00f7', '\u00fa'}, {'\u00fc', '\u00fc'}, {'\u00fe', '\u00fe'}, {'\u0101', '\u0101'}, {'\u0111', '\u0111'}, {'\u0113', '\u0113'}, {'\u011b', '\u011b'}, {'\u0126', '\u0127'}, {'\u012b', '\u012b'}, {'\u0131', '\u0133'}, {'\u0138', '\u0138'}, {'\u013f', '\u0142'}, {'\u0144', '\u0144'}, {'\u0148', '\u014b'}, {'\u014d', '\u014d'}, {'\u0152', '\u0153'}, {'\u0166', '\u0167'}, {'\u016b', '\u016b'}, {'\u01ce', '\u01ce'}, {'\u01d0', '\u01d0'}, {'\u01d2', '\u01d2'}, {'\u01d4', '\u01d4'}, {'\u01d6', '\u01d6'}, {'\u01d8', '\u01d8'}, {'\u01da', '\u01da'}, {'\u01dc', '\u01dc'}, {'\u0251', '\u0251'}, {'\u0261', '\u0261'}, {'\u02c4', '\u02c4'}, {'\u02c7', '\u02c7'}, {'\u02c9', '\u02cb'}, {'\u02cd', '\u02cd'}, {'\u02d0', '\u02d0'}, {'\u02d8', '\u02db'}, {'\u02dd', '\u02dd'}, {'\u02df', '\u02df'}, {'\u0391', '\u03a1'}, {'\u03a3', '\u03a9'}, {'\u03b1', '\u03c1'}, {'\u03c3', '\u03c9'}, {'\u0401', '\u0401'}, {'\u0410', '\u044f'}, {'\u0451', '\u0451'}, {'\u2010', '\u2010'}, {'\u2013', '\u2016'}, {'\u2018', '\u2019'}, {'\u201c', '\u201d'}, {'\u2020', '\u2022'}, {'\u2024', '\u2027'}, {'\u2030', '\u2030'}, {'\u2032', '\u2033'}, {'\u2035', '\u2035'}, {'\u203b', '\u203b'}, {'\u203e', '\u203e'}, {'\u2074', '\u2074'}, {'\u207f', '\u207f'}, {'\u2081', '\u2084'}, {'\u20ac', '\u20ac'}, {'\u2103', '\u2103'}, {'\u2105', '\u2105'}, {'\u2109', '\u2109'}, {'\u2113', '\u2113'}, {'\u2116', '\u2116'}, {'\u2121', '\u2122'}, {'\u2126', '\u2126'}, {'\u212b', '\u212b'}, {'\u2153', '\u2154'}, {'\u215b', '\u215e'}, {'\u2160', '\u216b'}, {'\u2170', '\u2179'}, {'\u2190', '\u2199'}, {'\u21b8', '\u21b9'}, {'\u21d2', '\u21d2'}, {'\u21d4', '\u21d4'}, {'\u21e7', '\u21e7'}, {'\u2200', '\u2200'}, {'\u2202', '\u2203'}, {'\u2207', '\u2208'}, {'\u220b', '\u220b'}, {'\u220f', '\u220f'}, {'\u2211', '\u2211'}, {'\u2215', '\u2215'}, {'\u221a', '\u221a'}, {'\u221d', '\u2220'}, {'\u2223', '\u2223'}, {'\u2225', '\u2225'}, {'\u2227', '\u222c'}, {'\u222e', '\u222e'}, {'\u2234', '\u2237'}, {'\u223c', '\u223d'}, {'\u2248', '\u2248'}, {'\u224c', '\u224c'}, {'\u2252', '\u2252'}, {'\u2260', '\u2261'}, {'\u2264', '\u2267'}, {'\u226a', '\u226b'}, {'\u226e', '\u226f'}, {'\u2282', '\u2283'}, {'\u2286', '\u2287'}, {'\u2295', '\u2295'}, {'\u2299', '\u2299'}, {'\u22a5', '\u22a5'}, {'\u22bf', '\u22bf'}, {'\u2312', '\u2312'}, {'\u2460', '\u24e9'}, {'\u24eb', '\u254b'}, {'\u2550', '\u2573'}, {'\u2580', '\u258f'}, {'\u2592', '\u2595'}, {'\u25a0', '\u25a1'}, {'\u25a3', '\u25a9'}, {'\u25b2', '\u25b3'}, {'\u25b6', '\u25b7'}, {'\u25bc', '\u25bd'}, {'\u25c0', '\u25c1'}, {'\u25c6', '\u25c8'}, {'\u25cb', '\u25cb'}, {'\u25ce', '\u25d1'}, {'\u25e2', '\u25e5'}, {'\u25ef', '\u25ef'}, {'\u2605', '\u2606'}, {'\u2609', '\u2609'}, {'\u260e', '\u260f'}, {'\u2614', '\u2615'}, {'\u261c', '\u261c'}, {'\u261e', '\u261e'}, {'\u2640', '\u2640'}, {'\u2642', '\u2642'}, {'\u2660', '\u2661'}, {'\u2663', '\u2665'}, {'\u2667', '\u266a'}, {'\u266c', '\u266d'}, {'\u266f', '\u266f'}, {'\u273d', '\u273d'}, {'\u2776', '\u277f'}, {'\ue000', '\uf8ff'}, {'\ufffd', '\ufffd'}};

    private CharUtils() {
    }

    public static String getNonControlCharacters(int maxChars, char[] buf, int offset, int charsLength) {
        int len;
        int origLen = len = Math.min(maxChars, charsLength);
        while (len > 0) {
            char tmp;
            if (' ' <= (tmp = buf[offset++])) {
                --len;
                continue;
            }
            --offset;
            break;
        }
        int length = origLen - len;
        return new String(buf, offset - length, length);
    }

    public static int countDoubleWidthCharacters(char[] buf, int start, int length, boolean ambiguousIsDWC) {
        int cnt = 0;
        for (int i = 0; i < length; ++i) {
            int ucs = Character.codePointAt(buf, i + start);
            if (!CharUtils.isDoubleWidthCharacter(ucs, ambiguousIsDWC)) continue;
            ++cnt;
        }
        return cnt;
    }

    public static CharacterType appendChar(StringBuilder sb, CharacterType last, char c) {
        if (c <= '\u001f') {
            sb.append(' ');
            sb.append(NONPRINTING_NAMES[c]);
            return CharacterType.NONPRINTING;
        }
        if (c == '\u007f') {
            sb.append(" DEL");
            return CharacterType.NONPRINTING;
        }
        if (c > '\u001f' && c <= '~') {
            if (last != CharacterType.PRINTING) {
                sb.append(' ');
            }
            sb.append(c);
            return CharacterType.PRINTING;
        }
        sb.append(" 0x").append(Integer.toHexString(c));
        return CharacterType.NONASCII;
    }

    public static void appendBuf(StringBuilder sb, char[] bs, int begin, int length) {
        CharacterType last = CharacterType.NONPRINTING;
        int end = begin + length;
        for (int i = begin; i < end; ++i) {
            char c = bs[i];
            last = CharUtils.appendChar(sb, last, c);
        }
    }

    public static byte[] makeCode(int ... bytesAsInt) {
        byte[] bytes = new byte[bytesAsInt.length];
        int i = 0;
        for (int byteAsInt : bytesAsInt) {
            bytes[i] = (byte)byteAsInt;
            ++i;
        }
        return bytes;
    }

    public static int getTextLengthDoubleWidthAware(char[] buffer, int start, int length, boolean ambiguousIsDWC) {
        int result = 0;
        for (int i = start; i < start + length; ++i) {
            result += buffer[i] != '\ue000' && CharUtils.isDoubleWidthCharacter(buffer[i], ambiguousIsDWC) && (i + 1 >= start + length || buffer[i + 1] != '\ue000') ? 2 : 1;
        }
        return result;
    }

    public static boolean isDoubleWidthCharacter(int c, boolean ambiguousIsDWC) {
        if (c == 57344 || c <= 160 || c > 1106 && c < 4352) {
            return false;
        }
        return CharUtils.mk_wcwidth(c, ambiguousIsDWC) == 2;
    }

    public static CharBuffer heavyDecCompatibleBuffer(CharBuffer buf) {
        char[] c = Arrays.copyOfRange(buf.getBuf(), 0, buf.getBuf().length);
        for (int i = 0; i < c.length; ++i) {
            c[i] = CharacterSets.getHeavyDecBoxChar(c[i]);
        }
        return new CharBuffer(c, buf.getStart(), buf.length());
    }

    static int bisearch(char ucs, char[][] table, int max) {
        int min = 0;
        if (ucs < table[0][0] || ucs > table[max][1]) {
            return 0;
        }
        while (max >= min) {
            int mid = (min + max) / 2;
            if (ucs > table[mid][1]) {
                min = mid + 1;
                continue;
            }
            if (ucs < table[mid][0]) {
                max = mid - 1;
                continue;
            }
            return 1;
        }
        return 0;
    }

    private static int mk_wcwidth(int ucs, boolean ambiguousIsDoubleWidth) {
        if (ucs == 0) {
            return 0;
        }
        if (ucs < 32 || ucs >= 127 && ucs < 160) {
            return -1;
        }
        if (ambiguousIsDoubleWidth && CharUtils.bisearch((char)ucs, AMBIGUOUS, AMBIGUOUS.length - 1) > 0) {
            return 2;
        }
        if (CharUtils.bisearch((char)ucs, COMBINING, COMBINING.length - 1) > 0) {
            return 0;
        }
        return 1 + (ucs >= 4352 && (ucs <= 4447 || ucs == 9001 || ucs == 9002 || ucs >= 11904 && ucs <= 42191 && ucs != 12351 || ucs >= 44032 && ucs <= 55203 || ucs >= 63744 && ucs <= 64255 || ucs >= 65040 && ucs <= 65049 || ucs >= 65072 && ucs <= 65135 || ucs >= 65280 && ucs <= 65376 || ucs >= 65504 && ucs <= 65510 || ucs >= 131072 && ucs <= 196605 || ucs >= 196608 && ucs <= 262141) ? 1 : 0);
    }

    public static enum CharacterType {
        NONPRINTING,
        PRINTING,
        NONASCII,
        NONE;

    }
}

