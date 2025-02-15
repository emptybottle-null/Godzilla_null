/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.sh;

import java.io.File;
import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.rsta.ac.sh.ShellCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionCellRenderer;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class ShellLanguageSupport
extends AbstractLanguageSupport {
    private ShellCompletionProvider provider;
    private boolean useLocalManPages;

    public ShellLanguageSupport() {
        this.setParameterAssistanceEnabled(false);
        this.setShowDescWindow(true);
        this.setShowDescWindow(true);
        this.setAutoCompleteEnabled(true);
        this.setAutoActivationEnabled(true);
        this.setAutoActivationDelay(800);
        this.useLocalManPages = File.separatorChar == '/';
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new CompletionCellRenderer();
    }

    private ShellCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new ShellCompletionProvider();
            ShellCompletionProvider.setUseLocalManPages(this.getUseLocalManPages());
        }
        return this.provider;
    }

    public boolean getUseLocalManPages() {
        return this.useLocalManPages;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        ShellCompletionProvider provider = this.getProvider();
        AutoCompletion ac = this.createAutoCompletion(provider);
        ac.install(textArea);
        this.installImpl(textArea, ac);
        textArea.setToolTipSupplier(provider);
    }

    public void setUseLocalManPages(boolean use) {
        if (use != this.useLocalManPages) {
            this.useLocalManPages = use;
            if (this.provider != null) {
                ShellCompletionProvider.setUseLocalManPages(this.useLocalManPages);
            }
        }
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
    }
}

