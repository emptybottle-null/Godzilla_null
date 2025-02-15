/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.html;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ListCellRenderer;
import org.fife.rsta.ac.AbstractMarkupLanguageSupport;
import org.fife.rsta.ac.html.HtmlCellRenderer;
import org.fife.rsta.ac.html.HtmlCompletionProvider;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class HtmlLanguageSupport
extends AbstractMarkupLanguageSupport {
    private HtmlCompletionProvider provider;
    private static Set<String> tagsToClose = HtmlLanguageSupport.getTagsToClose("html5_close_tags.txt");

    public HtmlLanguageSupport() {
        this.setAutoActivationEnabled(true);
        this.setParameterAssistanceEnabled(false);
        this.setShowDescWindow(true);
    }

    @Override
    protected ListCellRenderer<Object> createDefaultCompletionCellRenderer() {
        return new HtmlCellRenderer();
    }

    private HtmlCompletionProvider getProvider() {
        if (this.provider == null) {
            this.provider = new HtmlCompletionProvider();
        }
        return this.provider;
    }

    public static Set<String> getTagsToClose() {
        return tagsToClose;
    }

    private static Set<String> getTagsToClose(String res) {
        HashSet<String> tags = new HashSet<String>();
        InputStream in = HtmlLanguageSupport.class.getResourceAsStream(res);
        if (in != null) {
            try {
                String line;
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                while ((line = r.readLine()) != null) {
                    if (line.length() <= 0 || line.charAt(0) == '#') continue;
                    tags.add(line.trim());
                }
                r.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return tags;
    }

    @Override
    public void install(RSyntaxTextArea textArea) {
        HtmlCompletionProvider provider = this.getProvider();
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

