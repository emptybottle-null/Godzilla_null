/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import org.fife.rsta.ac.css.PropertyValueCompletionProvider;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;

public class CssCompletionProvider
extends LanguageAwareCompletionProvider {
    public CssCompletionProvider() {
        this.setDefaultCompletionProvider(this.createCodeCompletionProvider());
        this.setCommentCompletionProvider(this.createCommentCompletionProvider());
    }

    protected CompletionProvider createCodeCompletionProvider() {
        return new PropertyValueCompletionProvider(false);
    }

    protected CompletionProvider createCommentCompletionProvider() {
        DefaultCompletionProvider cp = new DefaultCompletionProvider();
        cp.addCompletion(new BasicCompletion(cp, "TODO:", "A to-do reminder"));
        cp.addCompletion(new BasicCompletion(cp, "FIXME:", "A bug that needs to be fixed"));
        return cp;
    }
}

