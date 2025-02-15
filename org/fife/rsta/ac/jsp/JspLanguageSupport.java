/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.jsp;

import java.util.HashSet;
import java.util.Set;
import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractMarkupLanguageSupport;
import org.fife.rsta.ac.html.HtmlCellRenderer;
import org.fife.rsta.ac.html.HtmlLanguageSupport;
import org.fife.rsta.ac.jsp.JspCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class JspLanguageSupport
extends AbstractMarkupLanguageSupport {
    private JspCompletionProvider provider;
    private static Set<String> tagsToClose = new HashSet<String>();

    public JspLanguageSupport() {
        this.setAutoActivationEnabled(true);
        this.setParameterAssistanceEnabled(false);
        this.setShowDescWindow(true);
        tagsToClose = HtmlLanguageSupport.getTagsToClose();
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new HtmlCellRenderer();
    }

    private JspCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new JspCompletionProvider();
        }
        return this.provider;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        JspCompletionProvider provider = this.getProvider();
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

