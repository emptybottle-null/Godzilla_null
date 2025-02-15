/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.ts;

import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.ts.TypeScriptCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class TypeScriptLanguageSupport
extends AbstractLanguageSupport {
    private TypeScriptCompletionProvider provider = new TypeScriptCompletionProvider(this);

    private AutoCompletion createAutoCompletion() {
        AutoCompletion ac = new AutoCompletion(this.provider);
        return ac;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        AutoCompletion ac = this.createAutoCompletion();
        ac.install(textArea);
        this.installImpl(textArea, ac);
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
    }
}

