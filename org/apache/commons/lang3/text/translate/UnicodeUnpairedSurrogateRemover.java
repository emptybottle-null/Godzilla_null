/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import org.apache.commons.lang3.text.translate.CodePointTranslator;

@Deprecated
public class UnicodeUnpairedSurrogateRemover
extends CodePointTranslator {
    @Override
    public boolean translate(int codepoint, Writer out) throws IOException {
        return codepoint >= 55296 && codepoint <= 57343;
    }
}

