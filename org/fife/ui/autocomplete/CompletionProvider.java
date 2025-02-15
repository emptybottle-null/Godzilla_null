/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Point;
import java.util.List;
import javax.swing.ListCellRenderer;
import javax.swing.text.JTextComponent;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.ParameterChoicesProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;

public interface CompletionProvider {
    public void clearParameterizedCompletionParams();

    public String getAlreadyEnteredText(JTextComponent var1);

    public List<Completion> getCompletions(JTextComponent var1);

    public List<Completion> getCompletionsAt(JTextComponent var1, Point var2);

    public ListCellRenderer<Object> getListCellRenderer();

    public ParameterChoicesProvider getParameterChoicesProvider();

    public List<ParameterizedCompletion> getParameterizedCompletions(JTextComponent var1);

    public char getParameterListEnd();

    public String getParameterListSeparator();

    public char getParameterListStart();

    public CompletionProvider getParent();

    public boolean isAutoActivateOkay(JTextComponent var1);

    public void setListCellRenderer(ListCellRenderer<Object> var1);

    public void setParameterizedCompletionParams(char var1, String var2, char var3);

    public void setParent(CompletionProvider var1);
}

