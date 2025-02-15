/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.TokenMakerBase;

public abstract class AbstractJFlexTokenMaker
extends TokenMakerBase {
    protected Segment s;
    protected int start;
    protected int offsetShift;

    public abstract void yybegin(int var1);

    protected void yybegin(int state, int languageIndex) {
        this.yybegin(state);
        this.setLanguageIndex(languageIndex);
    }
}

