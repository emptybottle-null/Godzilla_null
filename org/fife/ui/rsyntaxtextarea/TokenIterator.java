/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.util.Iterator;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

class TokenIterator
implements Iterator<Token> {
    private RSyntaxDocument doc;
    private int curLine;
    private Token token;

    TokenIterator(RSyntaxDocument doc) {
        this.doc = doc;
        this.loadTokenListForCurLine();
        int lineCount = this.getLineCount();
        while (!(this.token != null && this.token.isPaintable() || this.curLine >= lineCount - 1)) {
            ++this.curLine;
            this.loadTokenListForCurLine();
        }
    }

    private int getLineCount() {
        return this.doc.getDefaultRootElement().getElementCount();
    }

    @Override
    public boolean hasNext() {
        return this.token != null;
    }

    private void loadTokenListForCurLine() {
        this.token = this.doc.getTokenListForLine(this.curLine);
        if (this.token != null && !this.token.isPaintable()) {
            this.token = null;
        }
    }

    @Override
    public Token next() {
        Token t = this.token;
        boolean tIsCloned = false;
        int lineCount = this.getLineCount();
        if (this.token != null && this.token.isPaintable()) {
            this.token = this.token.getNextToken();
        } else if (this.curLine < lineCount - 1) {
            t = new TokenImpl(t);
            tIsCloned = true;
            ++this.curLine;
            this.loadTokenListForCurLine();
        } else if (this.token != null && !this.token.isPaintable()) {
            this.token = null;
        }
        while (!(this.token != null && this.token.isPaintable() || this.curLine >= lineCount - 1)) {
            if (!tIsCloned) {
                t = new TokenImpl(t);
                tIsCloned = true;
            }
            ++this.curLine;
            this.loadTokenListForCurLine();
        }
        if (this.token != null && !this.token.isPaintable() && this.curLine == lineCount - 1) {
            this.token = null;
        }
        return t;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

