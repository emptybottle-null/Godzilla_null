/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.common;

import javax.swing.text.Element;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;

public class TokenScanner {
    private RSyntaxDocument doc;
    private Element root;
    private Token t;
    private int line;

    public TokenScanner(RSyntaxTextArea textArea) {
        this((RSyntaxDocument)textArea.getDocument());
    }

    public TokenScanner(RSyntaxDocument doc) {
        this.doc = doc;
        this.root = doc.getDefaultRootElement();
        this.line = 0;
        this.t = null;
    }

    public RSyntaxDocument getDocument() {
        return this.doc;
    }

    public Token next() {
        Token next = this.nextRaw();
        while (next != null && (next.isWhitespace() || next.isComment())) {
            next = this.nextRaw();
        }
        return next;
    }

    private Token nextRaw() {
        if (this.t == null || !this.t.isPaintable()) {
            int lineCount = this.root.getElementCount();
            while (!(this.line >= lineCount || this.t != null && this.t.isPaintable())) {
                this.t = this.doc.getTokenListForLine(this.line++);
            }
            if (this.line == lineCount) {
                return null;
            }
        }
        Token next = this.t;
        this.t = this.t.getNextToken();
        return next;
    }
}

