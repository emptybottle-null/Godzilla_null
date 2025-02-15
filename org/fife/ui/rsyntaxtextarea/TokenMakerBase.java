/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.Action;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.DefaultOccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.DefaultTokenFactory;
import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenFactory;
import org.fife.ui.rsyntaxtextarea.TokenImpl;
import org.fife.ui.rsyntaxtextarea.TokenMaker;

public abstract class TokenMakerBase
implements TokenMaker {
    protected TokenImpl firstToken = null;
    protected TokenImpl currentToken = null;
    protected TokenImpl previousToken = null;
    private TokenFactory tokenFactory = new DefaultTokenFactory();
    private OccurrenceMarker occurrenceMarker;
    private int languageIndex;

    @Override
    public void addNullToken() {
        if (this.firstToken == null) {
            this.currentToken = this.firstToken = this.tokenFactory.createToken();
        } else {
            TokenImpl next = this.tokenFactory.createToken();
            this.currentToken.setNextToken(next);
            this.previousToken = this.currentToken;
            this.currentToken = next;
        }
        this.currentToken.setLanguageIndex(this.languageIndex);
    }

    public void addToken(Segment segment, int start, int end, int tokenType, int startOffset) {
        this.addToken(segment.array, start, end, tokenType, startOffset);
    }

    @Override
    public void addToken(char[] array, int start, int end, int tokenType, int startOffset) {
        this.addToken(array, start, end, tokenType, startOffset, false);
    }

    public void addToken(char[] array, int start, int end, int tokenType, int startOffset, boolean hyperlink) {
        if (this.firstToken == null) {
            this.currentToken = this.firstToken = this.tokenFactory.createToken(array, start, end, startOffset, tokenType);
        } else {
            TokenImpl next = this.tokenFactory.createToken(array, start, end, startOffset, tokenType);
            this.currentToken.setNextToken(next);
            this.previousToken = this.currentToken;
            this.currentToken = next;
        }
        this.currentToken.setLanguageIndex(this.languageIndex);
        this.currentToken.setHyperlink(hyperlink);
    }

    protected OccurrenceMarker createOccurrenceMarker() {
        return new DefaultOccurrenceMarker();
    }

    @Override
    public int getClosestStandardTokenTypeForInternalType(int type) {
        return type;
    }

    @Override
    public boolean getCurlyBracesDenoteCodeBlocks(int languageIndex) {
        return false;
    }

    @Override
    public Action getInsertBreakAction() {
        return null;
    }

    protected int getLanguageIndex() {
        return this.languageIndex;
    }

    @Override
    public int getLastTokenTypeOnLine(Segment text, int initialTokenType) {
        Token t = this.getTokenList(text, initialTokenType, 0);
        while (t.getNextToken() != null) {
            t = t.getNextToken();
        }
        return t.getType();
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return null;
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 20;
    }

    protected boolean getNoTokensIdentifiedYet() {
        return this.firstToken == null;
    }

    @Override
    public OccurrenceMarker getOccurrenceMarker() {
        if (this.occurrenceMarker == null) {
            this.occurrenceMarker = this.createOccurrenceMarker();
        }
        return this.occurrenceMarker;
    }

    @Override
    public boolean getShouldIndentNextLineAfter(Token token) {
        return false;
    }

    @Override
    public boolean isIdentifierChar(int languageIndex, char ch) {
        return Character.isLetterOrDigit(ch) || ch == '_' || ch == '$';
    }

    @Override
    public boolean isMarkupLanguage() {
        return false;
    }

    protected void resetTokenList() {
        this.previousToken = null;
        this.currentToken = null;
        this.firstToken = null;
        this.tokenFactory.resetAllTokens();
    }

    protected void setLanguageIndex(int languageIndex) {
        this.languageIndex = Math.max(0, languageIndex);
    }
}

