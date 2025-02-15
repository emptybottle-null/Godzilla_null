/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.c;

import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.c.CCellRenderer;
import org.fife.rsta.ac.c.CCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class CLanguageSupport
extends AbstractLanguageSupport {
    private CCompletionProvider provider;

    public CLanguageSupport() {
        this.setShowDescWindow(true);
        this.setAutoCompleteEnabled(true);
        this.setAutoActivationEnabled(true);
        this.setAutoActivationDelay(800);
        this.setParameterAssistanceEnabled(true);
        this.setShowDescWindow(true);
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new CCellRenderer();
    }

    private CCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new CCompletionProvider();
        }
        return this.provider;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        CCompletionProvider provider = this.getProvider();
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

