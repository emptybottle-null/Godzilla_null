/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.TokenFactory;
import org.fife.ui.rsyntaxtextarea.TokenImpl;

class DefaultTokenFactory
implements TokenFactory {
    private int size;
    private int increment;
    private TokenImpl[] tokenList;
    private int currentFreeToken;
    protected static final int DEFAULT_START_SIZE = 30;
    protected static final int DEFAULT_INCREMENT = 10;

    DefaultTokenFactory() {
        this(30, 10);
    }

    DefaultTokenFactory(int size, int increment) {
        this.size = size;
        this.increment = increment;
        this.currentFreeToken = 0;
        this.tokenList = new TokenImpl[size];
        for (int i = 0; i < size; ++i) {
            this.tokenList[i] = new TokenImpl();
        }
    }

    private void augmentTokenList() {
        TokenImpl[] temp = new TokenImpl[this.size + this.increment];
        System.arraycopy(this.tokenList, 0, temp, 0, this.size);
        this.size += this.increment;
        this.tokenList = temp;
        for (int i = 0; i < this.increment; ++i) {
            this.tokenList[this.size - i - 1] = new TokenImpl();
        }
    }

    @Override
    public TokenImpl createToken() {
        TokenImpl token = this.tokenList[this.currentFreeToken];
        token.text = null;
        token.setType(0);
        token.setOffset(-1);
        token.setNextToken(null);
        ++this.currentFreeToken;
        if (this.currentFreeToken == this.size) {
            this.augmentTokenList();
        }
        return token;
    }

    @Override
    public TokenImpl createToken(Segment line, int beg, int end, int startOffset, int type) {
        return this.createToken(line.array, beg, end, startOffset, type);
    }

    @Override
    public TokenImpl createToken(char[] line, int beg, int end, int startOffset, int type) {
        TokenImpl token = this.tokenList[this.currentFreeToken];
        token.set(line, beg, end, startOffset, type);
        ++this.currentFreeToken;
        if (this.currentFreeToken == this.size) {
            this.augmentTokenList();
        }
        return token;
    }

    @Override
    public void resetAllTokens() {
        this.currentFreeToken = 0;
    }
}

