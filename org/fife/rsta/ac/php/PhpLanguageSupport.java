/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.php;

import java.util.HashSet;
import java.util.Set;
import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractMarkupLanguageSupport;
import org.fife.rsta.ac.html.HtmlCellRenderer;
import org.fife.rsta.ac.html.HtmlLanguageSupport;
import org.fife.rsta.ac.php.PhpCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class PhpLanguageSupport
extends AbstractMarkupLanguageSupport {
    private PhpCompletionProvider provider;
    private static Set<String> tagsToClose = new HashSet<String>();

    public PhpLanguageSupport() {
        this.setAutoActivationEnabled(true);
        this.setParameterAssistanceEnabled(true);
        this.setShowDescWindow(true);
        this.setAutoActivationDelay(800);
        tagsToClose = HtmlLanguageSupport.getTagsToClose();
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new HtmlCellRenderer();
    }

    private PhpCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new PhpCompletionProvider();
        }
        return this.provider;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        PhpCompletionProvider provider = this.getProvider();
        AutoCompletion ac = this.createAutoCompletion(provider);
        ac.install(textArea);
        this.installImpl(textArea, ac);
        this.installKeyboardShortcuts(textArea);
        textArea.setToolTipSupplier(null);
    }

    @Override
    protected boolean shouldAutoCloseTag(String tag) {
        return tagsToClose.contains(tag.toLowerCase());
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
        this.uninstallKeyboardShortcuts(textArea);
    }
}

