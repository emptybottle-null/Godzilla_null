/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import org.fife.ui.rsyntaxtextarea.modes.CSSTokenMaker;

public class LessTokenMaker
extends CSSTokenMaker {
    public LessTokenMaker() {
        this.setHighlightingLess(true);
    }

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"//", null};
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 17 || super.getMarkOccurrencesOfTokenType(type);
    }
}

