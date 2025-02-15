/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.groovy;

import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.groovy.GroovyCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class GroovyLanguageSupport
extends AbstractLanguageSupport {
    private GroovyCompletionProvider provider;

    public GroovyLanguageSupport() {
        this.setParameterAssistanceEnabled(true);
        this.setShowDescWindow(true);
    }

    private GroovyCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new GroovyCompletionProvider();
        }
        return this.provider;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        GroovyCompletionProvider provider = this.getProvider();
        AutoCompletion ac = this.createAutoCompletion(provider);
        ac.install(textArea);
        this.installImpl(textArea, ac);
        textArea.setToolTipSupplier(provider);
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
    }
}

