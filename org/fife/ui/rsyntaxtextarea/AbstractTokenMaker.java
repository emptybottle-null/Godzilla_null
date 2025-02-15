/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import org.fife.ui.rsyntaxtextarea.TokenMakerBase;
import org.fife.ui.rsyntaxtextarea.TokenMap;

public abstract class AbstractTokenMaker
extends TokenMakerBase {
    protected TokenMap wordsToHighlight = this.getWordsToHighlight();

    public abstract TokenMap getWordsToHighlight();

    public void removeLastToken() {
        if (this.previousToken == null) {
            this.currentToken = null;
            this.firstToken = null;
        } else {
            this.currentToken = this.previousToken;
            this.currentToken.setNextToken(null);
        }
    }
}

