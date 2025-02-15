/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.ListCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Segment;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.ParameterChoicesProvider;
import org.fife.ui.autocomplete.SortByRelevanceComparator;

public abstract class CompletionProviderBase
implements CompletionProvider {
    private CompletionProvider parent;
    private ListCellRenderer<Object> listCellRenderer;
    private char paramListStart;
    private char paramListEnd;
    private String paramListSeparator;
    private boolean autoActivateAfterLetters;
    private String autoActivateChars;
    private ParameterChoicesProvider paramChoicesProvider;
    private Segment s = new Segment();
    protected static final String EMPTY_STRING = "";
    private static final Comparator<Completion> SORT_BY_RELEVANCE_COMPARATOR = new SortByRelevanceComparator();

    @Override
    public void clearParameterizedCompletionParams() {
        this.paramListStart = '\u0000';
        this.paramListEnd = '\u0000';
        this.paramListSeparator = null;
    }

    @Override
    public List<Completion> getCompletions(JTextComponent comp) {
        List<Completion> parentCompletions;
        List<Completion> completions = this.getCompletionsImpl(comp);
        if (this.parent != null && (parentCompletions = this.parent.getCompletions(comp)) != null) {
            completions.addAll(parentCompletions);
            Collections.sort(completions);
        }
        completions.sort(SORT_BY_RELEVANCE_COMPARATOR);
        return completions;
    }

    protected abstract List<Completion> getCompletionsImpl(JTextComponent var1);

    @Override
    public ListCellRenderer<Object> getListCellRenderer() {
        return this.listCellRenderer;
    }

    @Override
    public ParameterChoicesProvider getParameterChoicesProvider() {
        return this.paramChoicesProvider;
    }

    @Override
    public char getParameterListEnd() {
        return this.paramListEnd;
    }

    @Override
    public String getParameterListSeparator() {
        return this.paramListSeparator;
    }

    @Override
    public char getParameterListStart() {
        return this.paramListStart;
    }

    @Override
    public CompletionProvider getParent() {
        return this.parent;
    }

    @Override
    public boolean isAutoActivateOkay(JTextComponent tc) {
        Document doc = tc.getDocument();
        char ch = '\u0000';
        try {
            doc.getText(tc.getCaretPosition(), 1, this.s);
            ch = this.s.first();
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return this.autoActivateAfterLetters && Character.isLetter(ch) || this.autoActivateChars != null && this.autoActivateChars.indexOf(ch) > -1;
    }

    public void setAutoActivationRules(boolean letters, String others) {
        this.autoActivateAfterLetters = letters;
        this.autoActivateChars = others;
    }

    public void setParameterChoicesProvider(ParameterChoicesProvider pcp) {
        this.paramChoicesProvider = pcp;
    }

    @Override
    public void setListCellRenderer(ListCellRenderer<Object> r) {
        this.listCellRenderer = r;
    }

    @Override
    public void setParameterizedCompletionParams(char listStart, String separator, char listEnd) {
        if (listStart < ' ' || listStart == '\u007f') {
            throw new IllegalArgumentException("Invalid listStart");
        }
        if (listEnd < ' ' || listEnd == '\u007f') {
            throw new IllegalArgumentException("Invalid listEnd");
        }
        if (separator == null || separator.length() == 0) {
            throw new IllegalArgumentException("Invalid separator");
        }
        this.paramListStart = listStart;
        this.paramListSeparator = separator;
        this.paramListEnd = listEnd;
    }

    @Override
    public void setParent(CompletionProvider parent) {
        this.parent = parent;
    }
}

