/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.modes;

import org.fife.ui.rsyntaxtextarea.AbstractJFlexTokenMaker;

public abstract class AbstractMarkupTokenMaker
extends AbstractJFlexTokenMaker {
    public abstract boolean getCompleteCloseTags();

    @Override
    public String[] getLineCommentStartAndEnd(int languageIndex) {
        return new String[]{"<!--", "-->"};
    }

    @Override
    public final boolean isMarkupLanguage() {
        return true;
    }
}

