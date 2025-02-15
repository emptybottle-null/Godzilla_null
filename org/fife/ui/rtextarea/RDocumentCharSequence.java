/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import javax.swing.text.BadLocationException;
import org.fife.ui.rtextarea.RDocument;

class RDocumentCharSequence
implements CharSequence {
    private RDocument doc;
    private int start;
    private int end;

    RDocumentCharSequence(RDocument doc, int start) {
        this(doc, start, doc.getLength());
    }

    RDocumentCharSequence(RDocument doc, int start, int end) {
        this.doc = doc;
        this.start = start;
        this.end = end;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= this.length()) {
            throw new IndexOutOfBoundsException("Index " + index + " is not in range [0-" + this.length() + ")");
        }
        try {
            return this.doc.charAt(this.start + index);
        } catch (BadLocationException ble) {
            throw new IndexOutOfBoundsException(ble.toString());
        }
    }

    @Override
    public int length() {
        return this.end - this.start;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0) {
            throw new IndexOutOfBoundsException("start must be >= 0 (" + start + ")");
        }
        if (end < 0) {
            throw new IndexOutOfBoundsException("end must be >= 0 (" + end + ")");
        }
        if (end > this.length()) {
            throw new IndexOutOfBoundsException("end must be <= " + this.length() + " (" + end + ")");
        }
        if (start > end) {
            throw new IndexOutOfBoundsException("start (" + start + ") cannot be > end (" + end + ")");
        }
        int newStart = this.start + start;
        int newEnd = this.start + end;
        return new RDocumentCharSequence(this.doc, newStart, newEnd);
    }

    @Override
    public String toString() {
        try {
            return this.doc.getText(this.start, this.length());
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            return "";
        }
    }
}

