/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.less;

import org.fife.rsta.ac.css.CssCompletionProvider;
import org.fife.rsta.ac.less.LessCodeCompletionProvider;
import org.fife.ui.autocomplete.CompletionProvider;

class LessCompletionProvider
extends CssCompletionProvider {
    LessCompletionProvider() {
    }

    @Override
    protected CompletionProvider createCodeCompletionProvider() {
        return new LessCodeCompletionProvider();
    }
}

