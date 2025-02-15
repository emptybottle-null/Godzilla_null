/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.ts;

import org.fife.rsta.ac.js.JsDocCompletionProvider;
import org.fife.rsta.ac.ts.SourceCompletionProvider;
import org.fife.rsta.ac.ts.TypeScriptLanguageSupport;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;

public class TypeScriptCompletionProvider
extends LanguageAwareCompletionProvider {
    private TypeScriptLanguageSupport languageSupport;

    public TypeScriptCompletionProvider(TypeScriptLanguageSupport languageSupport) {
        super(new SourceCompletionProvider());
        this.languageSupport = languageSupport;
        this.setDocCommentCompletionProvider(new JsDocCompletionProvider());
    }

    public TypeScriptLanguageSupport getLanguageSupport() {
        return this.languageSupport;
    }
}

