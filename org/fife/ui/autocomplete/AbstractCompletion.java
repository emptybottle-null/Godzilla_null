/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;

public abstract class AbstractCompletion
implements Completion {
    private CompletionProvider provider;
    private Icon icon;
    private int relevance;

    protected AbstractCompletion(CompletionProvider provider) {
        this.provider = provider;
    }

    protected AbstractCompletion(CompletionProvider provider, Icon icon) {
        this(provider);
        this.setIcon(icon);
    }

    @Override
    public int compareTo(Completion c2) {
        if (c2 == this) {
            return 0;
        }
        if (c2 != null) {
            return this.toString().compareToIgnoreCase(c2.toString());
        }
        return -1;
    }

    @Override
    public String getAlreadyEntered(JTextComponent comp) {
        return this.provider.getAlreadyEnteredText(comp);
    }

    @Override
    public Icon getIcon() {
        return this.icon;
    }

    @Override
    public String getInputText() {
        return this.getReplacementText();
    }

    @Override
    public CompletionProvider getProvider() {
        return this.provider;
    }

    @Override
    public int getRelevance() {
        return this.relevance;
    }

    @Override
    public String getToolTipText() {
        return null;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }

    public String toString() {
        return this.getInputText();
    }
}

