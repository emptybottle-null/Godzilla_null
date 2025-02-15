/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.ArrayBasedCharEscaper;
import com.google.common.escape.CharEscaper;
import com.google.common.escape.Escaper;
import com.google.common.escape.UnicodeEscaper;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.Nullable;

@Beta
@GwtCompatible
public final class Escapers {
    private static final Escaper NULL_ESCAPER = new CharEscaper(){

        @Override
        public String escape(String string) {
            return Preconditions.checkNotNull(string);
        }

        @Override
        protected char[] escape(char c) {
            return null;
        }
    };

    private Escapers() {
    }

    public static Escaper nullEscaper() {
        return NULL_ESCAPER;
    }

    public static Builder builder() {
        return new Builder();
    }

    static UnicodeEscaper asUnicodeEscaper(Escaper escaper) {
        Preconditions.checkNotNull(escaper);
        if (escaper instanceof UnicodeEscaper) {
            return (UnicodeEscaper)escaper;
        }
        if (escaper instanceof CharEscaper) {
            return Escapers.wrap((CharEscaper)escaper);
        }
        throw new IllegalArgumentException("Cannot create a UnicodeEscaper from: " + escaper.getClass().getName());
    }

    public static String computeReplacement(CharEscaper escaper, char c) {
        return Escapers.stringOrNull(escaper.escape(c));
    }

    public static String computeReplacement(UnicodeEscaper escaper, int cp) {
        return Escapers.stringOrNull(escaper.escape(cp));
    }

    private static String stringOrNull(char[] in) {
        return in == null ? null : new String(in);
    }

    private static UnicodeEscaper wrap(final CharEscaper escaper) {
        return new UnicodeEscaper(){

            @Override
            protected char[] escape(int cp) {
                int n;
                if (cp < 65536) {
                    return escaper.escape((char)cp);
                }
                char[] surrogateChars = new char[2];
                Character.toChars(cp, surrogateChars, 0);
                char[] hiChars = escaper.escape(surrogateChars[0]);
                char[] loChars = escaper.escape(surrogateChars[1]);
                if (hiChars == null && loChars == null) {
                    return null;
                }
                int hiCount = hiChars != null ? hiChars.length : 1;
                int loCount = loChars != null ? loChars.length : 1;
                char[] output = new char[hiCount + loCount];
                if (hiChars != null) {
                    for (n = 0; n < hiChars.length; ++n) {
                        output[n] = hiChars[n];
                    }
                } else {
                    output[0] = surrogateChars[0];
                }
                if (loChars != null) {
                    for (n = 0; n < loChars.length; ++n) {
                        output[hiCount + n] = loChars[n];
                    }
                } else {
                    output[hiCount] = surrogateChars[1];
                }
                return output;
            }
        };
    }

    @Beta
    public static final class Builder {
        private final Map<Character, String> replacementMap = new HashMap<Character, String>();
        private char safeMin = '\u0000';
        private char safeMax = (char)65535;
        private String unsafeReplacement = null;

        private Builder() {
        }

        @CanIgnoreReturnValue
        public Builder setSafeRange(char safeMin, char safeMax) {
            this.safeMin = safeMin;
            this.safeMax = safeMax;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUnsafeReplacement(@Nullable String unsafeReplacement) {
            this.unsafeReplacement = unsafeReplacement;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addEscape(char c, String replacement) {
            Preconditions.checkNotNull(replacement);
            this.replacementMap.put(Character.valueOf(c), replacement);
            return this;
        }

        public Escaper build() {
            return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax){
                private final char[] replacementChars;
                {
                    super(replacementMap, safeMin, safeMax);
                    this.replacementChars = unsafeReplacement != null ? unsafeReplacement.toCharArray() : null;
                }

                @Override
                protected char[] escapeUnsafe(char c) {
                    return this.replacementChars;
                }
            };
        }
    }
}

