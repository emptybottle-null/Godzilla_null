/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.external.com.google.gdata.util.common.base;

import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.UnicodeEscaper;

public class PercentEscaper
extends UnicodeEscaper {
    public static final String SAFECHARS_URLENCODER = "-_.*";
    public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=";
    public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
    private static final char[] URI_ESCAPED_SPACE = new char[]{'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String safeChars, boolean plusForSpace) {
        if (safeChars.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        if (plusForSpace && safeChars.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (safeChars.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        this.plusForSpace = plusForSpace;
        this.safeOctets = PercentEscaper.createSafeOctets(safeChars);
    }

    private static boolean[] createSafeOctets(String safeChars) {
        int c;
        char[] safeCharArray;
        int maxChar = 122;
        for (char c2 : safeCharArray = safeChars.toCharArray()) {
            maxChar = Math.max(c2, maxChar);
        }
        boolean[] octets = new boolean[maxChar + 1];
        for (c = 48; c <= 57; ++c) {
            octets[c] = true;
        }
        for (c = 65; c <= 90; ++c) {
            octets[c] = true;
        }
        for (c = 97; c <= 122; ++c) {
            octets[c] = true;
        }
        for (char c3 : safeCharArray) {
            octets[c3] = true;
        }
        return octets;
    }

    @Override
    protected int nextEscapeIndex(CharSequence csq, int index, int end) {
        char c;
        while (index < end && (c = csq.charAt(index)) < this.safeOctets.length && this.safeOctets[c]) {
            ++index;
        }
        return index;
    }

    @Override
    public String escape(String s) {
        int slen = s.length();
        for (int index = 0; index < slen; ++index) {
            char c = s.charAt(index);
            if (c < this.safeOctets.length && this.safeOctets[c]) continue;
            return this.escapeSlow(s, index);
        }
        return s;
    }

    @Override
    protected char[] escape(int cp) {
        if (cp < this.safeOctets.length && this.safeOctets[cp]) {
            return null;
        }
        if (cp == 32 && this.plusForSpace) {
            return URI_ESCAPED_SPACE;
        }
        if (cp <= 127) {
            char[] dest = new char[3];
            dest[0] = 37;
            dest[2] = UPPER_HEX_DIGITS[cp & 0xF];
            dest[1] = UPPER_HEX_DIGITS[cp >>> 4];
            return dest;
        }
        if (cp <= 2047) {
            char[] dest = new char[6];
            dest[0] = 37;
            dest[3] = 37;
            dest[5] = UPPER_HEX_DIGITS[cp & 0xF];
            dest[4] = UPPER_HEX_DIGITS[8 | (cp >>>= 4) & 3];
            dest[2] = UPPER_HEX_DIGITS[(cp >>>= 2) & 0xF];
            dest[1] = UPPER_HEX_DIGITS[0xC | (cp >>>= 4)];
            return dest;
        }
        if (cp <= 65535) {
            char[] dest = new char[9];
            dest[0] = 37;
            dest[1] = 69;
            dest[3] = 37;
            dest[6] = 37;
            dest[8] = UPPER_HEX_DIGITS[cp & 0xF];
            dest[7] = UPPER_HEX_DIGITS[8 | (cp >>>= 4) & 3];
            dest[5] = UPPER_HEX_DIGITS[(cp >>>= 2) & 0xF];
            dest[4] = UPPER_HEX_DIGITS[8 | (cp >>>= 4) & 3];
            dest[2] = UPPER_HEX_DIGITS[cp >>>= 2];
            return dest;
        }
        if (cp <= 0x10FFFF) {
            char[] dest = new char[12];
            dest[0] = 37;
            dest[1] = 70;
            dest[3] = 37;
            dest[6] = 37;
            dest[9] = 37;
            dest[11] = UPPER_HEX_DIGITS[cp & 0xF];
            dest[10] = UPPER_HEX_DIGITS[8 | (cp >>>= 4) & 3];
            dest[8] = UPPER_HEX_DIGITS[(cp >>>= 2) & 0xF];
            dest[7] = UPPER_HEX_DIGITS[8 | (cp >>>= 4) & 3];
            dest[5] = UPPER_HEX_DIGITS[(cp >>>= 2) & 0xF];
            dest[4] = UPPER_HEX_DIGITS[8 | (cp >>>= 4) & 3];
            dest[2] = UPPER_HEX_DIGITS[(cp >>>= 2) & 7];
            return dest;
        }
        throw new IllegalArgumentException("Invalid unicode character value " + cp);
    }
}

