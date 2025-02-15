/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.folding.CurlyFoldParser;

public class LispFoldParser
extends CurlyFoldParser {
    @Override
    public boolean isLeftCurly(Token t) {
        return t.isSingleChar(22, '(');
    }

    @Override
    public boolean isRightCurly(Token t) {
        return t.isSingleChar(22, ')');
    }
}

