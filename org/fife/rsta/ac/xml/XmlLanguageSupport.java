/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.xml;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractMarkupLanguageSupport;
import org.fife.rsta.ac.GoToMemberAction;
import org.fife.rsta.ac.html.HtmlCellRenderer;
import org.fife.rsta.ac.xml.XmlCompletionProvider;
import org.fife.rsta.ac.xml.XmlParser;
import org.fife.rsta.ac.xml.tree.XmlOutlineTree;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class XmlLanguageSupport
extends AbstractMarkupLanguageSupport {
    private XmlCompletionProvider provider;
    private boolean showSyntaxErrors;

    public XmlLanguageSupport() {
        this.setAutoActivationEnabled(true);
        this.setParameterAssistanceEnabled(false);
        this.setShowDescWindow(false);
        this.setShowSyntaxErrors(true);
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new HtmlCellRenderer();
    }

    private XmlCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new XmlCompletionProvider();
        }
        return this.provider;
    }

    public XmlParser getParser(RSyntaxTextArea textArea) {
        Object parser = textArea.getClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser");
        if (parser instanceof XmlParser) {
            return (XmlParser)parser;
        }
        return null;
    }

    public boolean getShowSyntaxErrors() {
        return this.showSyntaxErrors;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        XmlCompletionProvider provider = this.getProvider();
        AutoCompletion ac = this.createAutoCompletion(provider);
        ac.install(textArea);
        this.installImpl(textArea, ac);
        XmlParser parser = new XmlParser(this);
        textArea.addParser(parser);
        textArea.putClientProperty("org.fife.rsta.ac.LanguageSupport.LanguageParser", parser);
        this.installKeyboardShortcuts(textArea);
    }

    @Override
    protected void installKeyboardShortcuts(RSyntaxTextArea textArea) {
        super.installKeyboardShortcuts(textArea);
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        int c = textArea.getToolkit().getMenuShortcutKeyMask();
        int shift = 64;
        im.put(KeyStroke.getKeyStroke(79, c | shift), "GoToType");
        am.put("GoToType", new GoToMemberAction(XmlOutlineTree.class));
    }

    public void setShowSyntaxErrors(boolean show) {
        this.showSyntaxErrors = show;
    }

    @Override
    protected boolean shouldAutoCloseTag(String tag) {
        return true;
    }

    @Override
    public void uninstall(RSyntaxTextArea textArea) {
        this.uninstallImpl(textArea);
        XmlParser parser = this.getParser(textArea);
        if (parser != null) {
            textArea.removeParser(parser);
        }
        this.uninstallKeyboardShortcuts(textArea);
    }

    @Override
    protected void uninstallKeyboardShortcuts(RSyntaxTextArea textArea) {
        super.uninstallKeyboardShortcuts(textArea);
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        int c = textArea.getToolkit().getMenuShortcutKeyMask();
        int shift = 64;
        im.remove(KeyStroke.getKeyStroke(79, c | shift));
        am.remove("GoToType");
    }
}

