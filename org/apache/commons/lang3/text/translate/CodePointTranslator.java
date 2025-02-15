/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.text.translate.CharSequenceTranslator;

@Deprecated
public abstract class CodePointTranslator
extends CharSequenceTranslator {
    @Override
    public final int translate(CharSequence input, int index, Writer out) throws IOException {
        int codepoint = Character.codePointAt(input, index);
        boolean consumed = this.translate(codepoint, out);
        return consumed ? 1 : 0;
    }

    public abstract boolean translate(int var1, Writer var2) throws IOException;
}

