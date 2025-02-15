/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.SoundexUtils;

public class Soundex
implements StringEncoder {
    public static final char SILENT_MARKER = '-';
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    private static final char[] US_ENGLISH_MAPPING = "01230120022455012623010202".toCharArray();
    public static final Soundex US_ENGLISH = new Soundex();
    public static final Soundex US_ENGLISH_SIMPLIFIED = new Soundex("01230120022455012623010202", false);
    public static final Soundex US_ENGLISH_GENEALOGY = new Soundex("-123-12--22455-12623-1-2-2");
    @Deprecated
    private int maxLength = 4;
    private final char[] soundexMapping;
    private final boolean specialCaseHW;

    public Soundex() {
        this.soundexMapping = US_ENGLISH_MAPPING;
        this.specialCaseHW = true;
    }

    public Soundex(char[] mapping) {
        this.soundexMapping = new char[mapping.length];
        System.arraycopy(mapping, 0, this.soundexMapping, 0, mapping.length);
        this.specialCaseHW = !this.hasMarker(this.soundexMapping);
    }

    private boolean hasMarker(char[] mapping) {
        for (char ch : mapping) {
            if (ch != '-') continue;
            return true;
        }
        return false;
    }

    public Soundex(String mapping) {
        this.soundexMapping = mapping.toCharArray();
        this.specialCaseHW = !this.hasMarker(this.soundexMapping);
    }

    public Soundex(String mapping, boolean specialCaseHW) {
        this.soundexMapping = mapping.toCharArray();
        this.specialCaseHW = specialCaseHW;
    }

    public int difference(String s1, String s2) throws EncoderException {
        return SoundexUtils.difference(this, s1, s2);
    }

    @Override
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
        }
        return this.soundex((String)obj);
    }

    @Override
    public String encode(String str) {
        return this.soundex(str);
    }

    @Deprecated
    public int getMaxLength() {
        return this.maxLength;
    }

    private char map(char ch) {
        int index = ch - 65;
        if (index < 0 || index >= this.soundexMapping.length) {
            throw new IllegalArgumentException("The character is not mapped: " + ch + " (index=" + index + ")");
        }
        return this.soundexMapping[index];
    }

    @Deprecated
    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        if ((str = SoundexUtils.clean(str)).length() == 0) {
            return str;
        }
        char[] out = new char[]{'0', '0', '0', '0'};
        int count = 0;
        char first = str.charAt(0);
        out[count++] = first;
        char lastDigit = this.map(first);
        for (int i = 1; i < str.length() && count < out.length; ++i) {
            char digit;
            char ch = str.charAt(i);
            if (this.specialCaseHW && (ch == 'H' || ch == 'W') || (digit = this.map(ch)) == '-') continue;
            if (digit != '0' && digit != lastDigit) {
                out[count++] = digit;
            }
            lastDigit = digit;
        }
        return new String(out);
    }
}

