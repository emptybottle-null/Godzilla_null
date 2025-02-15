/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;

@Deprecated
public class OctalUnescaper
extends CharSequenceTranslator {
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        int remaining = input.length() - index - 1;
        StringBuilder builder = new StringBuilder();
        if (input.charAt(index) == '\\' && remaining > 0 && this.isOctalDigit(input.charAt(index + 1))) {
            int next = index + 1;
            int next2 = index + 2;
            int next3 = index + 3;
            builder.append(input.charAt(next));
            if (remaining > 1 && this.isOctalDigit(input.charAt(next2))) {
                builder.append(input.charAt(next2));
                if (remaining > 2 && this.isZeroToThree(input.charAt(next)) && this.isOctalDigit(input.charAt(next3))) {
                    builder.append(input.charAt(next3));
                }
            }
            out.write(Integer.parseInt(builder.toString(), 8));
            return 1 + builder.length();
        }
        return 0;
    }

    private boolean isOctalDigit(char ch) {
        return ch >= '0' && ch <= '7';
    }

    private boolean isZeroToThree(char ch) {
        return ch >= '0' && ch <= '3';
    }
}

