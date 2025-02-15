/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.CompletionProvider;

public interface Completion
extends Comparable<Completion> {
    @Override
    public int compareTo(Completion var1);

    public String getAlreadyEntered(JTextComponent var1);

    public Icon getIcon();

    public String getInputText();

    public CompletionProvider getProvider();

    public int getRelevance();

    public String getReplacementText();

    public String getSummary();

    public String getToolTipText();
}

