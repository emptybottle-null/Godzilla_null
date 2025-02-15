/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.net;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;

@Beta
@GwtCompatible
public final class PercentEscaper
extends UnicodeEscaper {
    private static final char[] PLUS_SIGN = new char[]{'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String safeChars, boolean plusForSpace) {
        Preconditions.checkNotNull(safeChars);
        if (safeChars.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        safeChars = safeChars + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        if (plusForSpace && safeChars.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.plusForSpace = plusForSpace;
        this.safeOctets = PercentEscaper.createSafeOctets(safeChars);
    }

    private static boolean[] createSafeOctets(String safeChars) {
        char[] safeCharArray;
        int maxChar = -1;
        for (char c : safeCharArray = safeChars.toCharArray()) {
            maxChar = Math.max(c, maxChar);
        }
        boolean[] octets = new boolean[maxChar + 1];
        for (char c : safeCharArray) {
            octets[c] = true;
        }
        return octets;
    }

    @Override
    protected int nextEscapeIndex(CharSequence csq, int index, int end) {
        char c;
        Preconditions.checkNotNull(csq);
        while (index < end && (c = csq.charAt(index)) < this.safeOctets.length && this.safeOctets[c]) {
            ++index;
        }
        return index;
    }

    @Override
    public String escape(String s) {
        Preconditions.checkNotNull(s);
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
            return PLUS_SIGN;
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

