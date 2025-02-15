/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.css;

import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.css.CssCellRenderer;
import org.fife.rsta.ac.css.CssCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class CssLanguageSupport
extends AbstractLanguageSupport {
    private CssCompletionProvider provider;

    public CssLanguageSupport() {
        this.setAutoActivationEnabled(true);
        this.setAutoActivationDelay(500);
        this.setParameterAssistanceEnabled(true);
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new CssCellRenderer();
    }

    protected CssCompletionProvider createProvider() {
        return new CssCompletionProvider();
    }

    private CssCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = this.createProvider();
        }
        return this.provider;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        CssCompletionProvider provider = this.getProvider();
        AutoCompletion ac = this.createAutoCompletion(provider);
        ac.install(textArea);
        this.installImpl(textArea, ac);
        textArea.setToolTipSupplier(provider);
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
        textArea.setToolTipSupplier(null);
    }
}

