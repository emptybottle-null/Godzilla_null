/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import javax.swing.text.BadLocationException;
import javax.swing.text.GapContent;
import javax.swing.text.PlainDocument;

public class RDocument
extends PlainDocument {
    public RDocument() {
        super(new RGapContent());
    }

    public char charAt(int offset) throws BadLocationException {
        return ((RGapContent)this.getContent()).charAt(offset);
    }

    private static class RGapContent
    extends GapContent {
        private RGapContent() {
        }

        public char charAt(int offset) throws BadLocationException {
            if (offset < 0 || offset >= this.length()) {
                throw new BadLocationException("Invalid offset", offset);
            }
            int g0 = this.getGapStart();
            char[] array = (char[])this.getArray();
            if (offset < g0) {
                return array[offset];
            }
            return array[this.getGapEnd() + offset - g0];
        }
    }
}

