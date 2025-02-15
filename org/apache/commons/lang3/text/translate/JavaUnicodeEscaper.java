/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text.translate;

import org.apache.commons.lang3.text.translate.UnicodeEscaper;

@Deprecated
public class JavaUnicodeEscaper
extends UnicodeEscaper {
    public static JavaUnicodeEscaper above(int codepoint) {
        return JavaUnicodeEscaper.outsideOf(0, codepoint);
    }

    public static JavaUnicodeEscaper below(int codepoint) {
        return JavaUnicodeEscaper.outsideOf(codepoint, Integer.MAX_VALUE);
    }

    public static JavaUnicodeEscaper between(int codepointLow, int codepointHigh) {
        return new JavaUnicodeEscaper(codepointLow, codepointHigh, true);
    }

    public static JavaUnicodeEscaper outsideOf(int codepointLow, int codepointHigh) {
        return new JavaUnicodeEscaper(codepointLow, codepointHigh, false);
    }

    public JavaUnicodeEscaper(int below, int above, boolean between) {
        super(below, above, between);
    }

    @Override
    protected String toUtf16Escape(int codepoint) {
        char[] surrogatePair = Character.toChars(codepoint);
        return "\\u" + JavaUnicodeEscaper.hex(surrogatePair[0]) + "\\u" + JavaUnicodeEscaper.hex(surrogatePair[1]);
    }
}

