/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.CharEscaper;
import com.google.common.escape.Escaper;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;

@Beta
@GwtCompatible
public final class CharEscaperBuilder {
    private final Map<Character, String> map = new HashMap<Character, String>();
    private int max = -1;

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscape(char c, String r) {
        this.map.put(Character.valueOf(c), Preconditions.checkNotNull(r));
        if (c > this.max) {
            this.max = c;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscapes(char[] cs, String r) {
        Preconditions.checkNotNull(r);
        for (char c : cs) {
            this.addEscape(c, r);
        }
        return this;
    }

    public char[][] toArray() {
        char[][] result = new char[this.max + 1][];
        for (Map.Entry<Character, String> entry : this.map.entrySet()) {
            result[entry.getKey().charValue()] = entry.getValue().toCharArray();
        }
        return result;
    }

    public Escaper toEscaper() {
        return new CharArrayDecorator(this.toArray());
    }

    private static class CharArrayDecorator
    extends CharEscaper {
        private final char[][] replacements;
        private final int replaceLength;

        CharArrayDecorator(char[][] replacements) {
            this.replacements = replacements;
            this.replaceLength = replacements.length;
        }

        @Override
        public String escape(String s) {
            int slen = s.length();
            for (int index = 0; index < slen; ++index) {
                char c = s.charAt(index);
                if (c >= this.replacements.length || this.replacements[c] == null) continue;
                return this.escapeSlow(s, index);
            }
            return s;
        }

        @Override
        protected char[] escape(char c) {
            return c < this.replaceLength ? this.replacements[c] : null;
        }
    }
}

