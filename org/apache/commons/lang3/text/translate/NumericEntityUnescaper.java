/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;

@Deprecated
public class NumericEntityUnescaper
extends CharSequenceTranslator {
    private final EnumSet<OPTION> options;

    public NumericEntityUnescaper(OPTION ... options) {
        this.options = options.length > 0 ? EnumSet.copyOf(Arrays.asList(options)) : EnumSet.copyOf(Collections.singletonList(OPTION.semiColonRequired));
    }

    public boolean isSet(OPTION option) {
        return this.options != null && this.options.contains((Object)option);
    }

    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        int seqEnd = input.length();
        if (input.charAt(index) == '&' && index < seqEnd - 2 && input.charAt(index + 1) == '#') {
            int entityValue;
            boolean semiNext;
            int end;
            int start = index + 2;
            boolean isHex = false;
            char firstChar = input.charAt(start);
            if (firstChar == 'x' || firstChar == 'X') {
                isHex = true;
                if (++start == seqEnd) {
                    return 0;
                }
            }
            for (end = start; end < seqEnd && (input.charAt(end) >= '0' && input.charAt(end) <= '9' || input.charAt(end) >= 'a' && input.charAt(end) <= 'f' || input.charAt(end) >= 'A' && input.charAt(end) <= 'F'); ++end) {
            }
            boolean bl = semiNext = end != seqEnd && input.charAt(end) == ';';
            if (!semiNext) {
                if (this.isSet(OPTION.semiColonRequired)) {
                    return 0;
                }
                if (this.isSet(OPTION.errorIfNoSemiColon)) {
                    throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
                }
            }
            try {
                entityValue = isHex ? Integer.parseInt(input.subSequence(start, end).toString(), 16) : Integer.parseInt(input.subSequence(start, end).toString(), 10);
            } catch (NumberFormatException nfe) {
                return 0;
            }
            if (entityValue > 65535) {
                char[] chars = Character.toChars(entityValue);
                out.write(chars[0]);
                out.write(chars[1]);
            } else {
                out.write(entityValue);
            }
            return 2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
        }
        return 0;
    }

    public static enum OPTION {
        semiColonRequired,
        semiColonOptional,
        errorIfNoSemiColon;

    }
}

