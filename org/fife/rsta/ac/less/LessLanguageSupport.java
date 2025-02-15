/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.less;

import org.fife.rsta.ac.css.CssCompletionProvider;
import org.fife.rsta.ac.css.CssLanguageSupport;
import org.fife.rsta.ac.less.LessCompletionProvider;

public class LessLanguageSupport
extends CssLanguageSupport {
    public LessLanguageSupport() {
        this.setShowDescWindow(true);
    }

    @Override
    protected CssCompletionProvider createProvider() {
        return new LessCompletionProvider();
    }
}

