/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.ArrayBasedEscaperMap;
import com.google.common.escape.UnicodeEscaper;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtCompatible
public abstract class ArrayBasedUnicodeEscaper
extends UnicodeEscaper {
    private final char[][] replacements;
    private final int replacementsLength;
    private final int safeMin;
    private final int safeMax;
    private final char safeMinChar;
    private final char safeMaxChar;

    protected ArrayBasedUnicodeEscaper(Map<Character, String> replacementMap, int safeMin, int safeMax, @Nullable String unsafeReplacement) {
        this(ArrayBasedEscaperMap.create(replacementMap), safeMin, safeMax, unsafeReplacement);
    }

    protected ArrayBasedUnicodeEscaper(ArrayBasedEscaperMap escaperMap, int safeMin, int safeMax, @Nullable String unsafeReplacement) {
        Preconditions.checkNotNull(escaperMap);
        this.replacements = escaperMap.getReplacementArray();
        this.replacementsLength = this.replacements.length;
        if (safeMax < safeMin) {
            safeMax = -1;
            safeMin = Integer.MAX_VALUE;
        }
        this.safeMin = safeMin;
        this.safeMax = safeMax;
        if (safeMin >= 55296) {
            this.safeMinChar = (char)65535;
            this.safeMaxChar = '\u0000';
        } else {
            this.safeMinChar = (char)safeMin;
            this.safeMaxChar = (char)Math.min(safeMax, 55295);
        }
    }

    @Override
    public final String escape(String s) {
        Preconditions.checkNotNull(s);
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if ((c >= this.replacementsLength || this.replacements[c] == null) && c <= this.safeMaxChar && c >= this.safeMinChar) continue;
            return this.escapeSlow(s, i);
        }
        return s;
    }

    @Override
    protected final char[] escape(int cp) {
        char[] chars;
        if (cp < this.replacementsLength && (chars = this.replacements[cp]) != null) {
            return chars;
        }
        if (cp >= this.safeMin && cp <= this.safeMax) {
            return null;
        }
        return this.escapeUnsafe(cp);
    }

    @Override
    protected final int nextEscapeIndex(CharSequence csq, int index, int end) {
        char c;
        while (index < end && ((c = csq.charAt(index)) >= this.replacementsLength || this.replacements[c] == null) && c <= this.safeMaxChar && c >= this.safeMinChar) {
            ++index;
        }
        return index;
    }

    protected abstract char[] escapeUnsafe(int var1);
}

