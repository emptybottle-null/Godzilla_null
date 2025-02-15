/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;

@Deprecated
public class UnicodeUnescaper
extends CharSequenceTranslator {
    @Override
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        if (input.charAt(index) == '\\' && index + 1 < input.length() && input.charAt(index + 1) == 'u') {
            int i = 2;
            while (index + i < input.length() && input.charAt(index + i) == 'u') {
                ++i;
            }
            if (index + i < input.length() && input.charAt(index + i) == '+') {
                ++i;
            }
            if (index + i + 4 <= input.length()) {
                CharSequence unicode2 = input.subSequence(index + i, index + i + 4);
                try {
                    int value = Integer.parseInt(unicode2.toString(), 16);
                    out.write((char)value);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("Unable to parse unicode value: " + unicode2, nfe);
                }
                return i + 4;
            }
            throw new IllegalArgumentException("Less than 4 hex digits in unicode value: '" + input.subSequence(index, input.length()) + "' due to end of CharSequence");
        }
        return 0;
    }
}

