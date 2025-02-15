/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class SearchContext
implements Cloneable,
Serializable {
    public static final String PROPERTY_SEARCH_FOR = "Search.searchFor";
    public static final String PROPERTY_REPLACE_WITH = "Search.replaceWith";
    public static final String PROPERTY_MATCH_CASE = "Search.MatchCase";
    public static final String PROPERTY_MATCH_WHOLE_WORD = "Search.MatchWholeWord";
    public static final String PROPERTY_SEARCH_FORWARD = "Search.Forward";
    public static final String PROPERTY_SEARCH_WRAP = "Search.Wrap";
    public static final String PROPERTY_SELECTION_ONLY = "Search.SelectionOnly";
    public static final String PROPERTY_USE_REGEX = "Search.UseRegex";
    public static final String PROPERTY_MARK_ALL = "Search.MarkAll";
    private String searchFor;
    private String replaceWith;
    private boolean forward;
    private boolean wrap;
    private boolean matchCase;
    private boolean wholeWord;
    private boolean regex;
    private boolean selectionOnly;
    private boolean markAll;
    private transient PropertyChangeSupport support = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;

    public SearchContext() {
        this(null);
    }

    public SearchContext(String searchFor) {
        this(searchFor, false);
    }

    public SearchContext(String searchFor, boolean matchCase) {
        this.searchFor = searchFor;
        this.matchCase = matchCase;
        this.markAll = true;
        this.forward = true;
        this.wrap = false;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        this.support.addPropertyChangeListener(l);
    }

    public SearchContext clone() {
        try {
            SearchContext context = null;
            context = (SearchContext)super.clone();
            context.support = new PropertyChangeSupport(context);
            return context;
        } catch (CloneNotSupportedException cnse) {
            throw new RuntimeException("Should never happen", cnse);
        }
    }

    protected void firePropertyChange(String property, boolean oldValue, boolean newValue) {
        this.support.firePropertyChange(property, oldValue, newValue);
    }

    protected void firePropertyChange(String property, String oldValue, String newValue) {
        this.support.firePropertyChange(property, oldValue, newValue);
    }

    public boolean getMarkAll() {
        return this.markAll;
    }

    public boolean getMatchCase() {
        return this.matchCase;
    }

    public String getReplaceWith() {
        return this.replaceWith;
    }

    public String getSearchFor() {
        return this.searchFor;
    }

    public boolean getSearchForward() {
        return this.forward;
    }

    public boolean getSearchWrap() {
        return this.wrap;
    }

    public boolean getSearchSelectionOnly() {
        return this.selectionOnly;
    }

    public boolean getWholeWord() {
        return this.wholeWord;
    }

    public boolean isRegularExpression() {
        return this.regex;
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        this.support.removePropertyChangeListener(l);
    }

    public void setMarkAll(boolean markAll) {
        if (markAll != this.markAll) {
            this.markAll = markAll;
            this.firePropertyChange(PROPERTY_MARK_ALL, !markAll, markAll);
        }
    }

    public void setMatchCase(boolean matchCase) {
        if (matchCase != this.matchCase) {
            this.matchCase = matchCase;
            this.firePropertyChange(PROPERTY_MATCH_CASE, !matchCase, matchCase);
        }
    }

    public void setRegularExpression(boolean regex) {
        if (regex != this.regex) {
            this.regex = regex;
            this.firePropertyChange(PROPERTY_USE_REGEX, !regex, regex);
        }
    }

    public void setReplaceWith(String replaceWith) {
        if (replaceWith == null && this.replaceWith != null || replaceWith != null && !replaceWith.equals(this.replaceWith)) {
            String old = this.replaceWith;
            this.replaceWith = replaceWith;
            this.firePropertyChange(PROPERTY_REPLACE_WITH, old, replaceWith);
        }
    }

    public void setSearchFor(String searchFor) {
        if (searchFor == null && this.searchFor != null || searchFor != null && !searchFor.equals(this.searchFor)) {
            String old = this.searchFor;
            this.searchFor = searchFor;
            this.firePropertyChange(PROPERTY_SEARCH_FOR, old, searchFor);
        }
    }

    public void setSearchForward(boolean forward) {
        if (forward != this.forward) {
            this.forward = forward;
            this.firePropertyChange(PROPERTY_SEARCH_FORWARD, !forward, forward);
        }
    }

    public void setSearchWrap(boolean wrap) {
        if (wrap != this.wrap) {
            this.wrap = wrap;
            this.firePropertyChange(PROPERTY_SEARCH_WRAP, !wrap, wrap);
        }
    }

    public void setSearchSelectionOnly(boolean selectionOnly) {
        if (selectionOnly != this.selectionOnly) {
            this.selectionOnly = selectionOnly;
            this.firePropertyChange(PROPERTY_SELECTION_ONLY, !selectionOnly, selectionOnly);
            if (selectionOnly) {
                throw new UnsupportedOperationException("Searching in selection is not currently supported");
            }
        }
    }

    public void setWholeWord(boolean wholeWord) {
        if (wholeWord != this.wholeWord) {
            this.wholeWord = wholeWord;
            this.firePropertyChange(PROPERTY_MATCH_WHOLE_WORD, !wholeWord, wholeWord);
        }
    }

    public String toString() {
        return "[SearchContext: searchFor=" + this.getSearchFor() + ", replaceWith=" + this.getReplaceWith() + ", matchCase=" + this.getMatchCase() + ", wholeWord=" + this.getWholeWord() + ", regex=" + this.isRegularExpression() + ", markAll=" + this.getMarkAll() + "]";
    }
}

