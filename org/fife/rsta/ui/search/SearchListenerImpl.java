/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui.search;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.fife.rsta.ui.search.SearchEvent;
import org.fife.rsta.ui.search.SearchListener;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;

public class SearchListenerImpl
implements SearchListener {
    private RSyntaxTextArea textArea;

    public SearchListenerImpl(RSyntaxTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void searchEvent(SearchEvent e) {
        SearchResult result;
        SearchEvent.Type type = e.getType();
        SearchContext context = e.getSearchContext();
        switch (type) {
            default: {
                result = SearchEngine.markAll(this.textArea, context);
                break;
            }
            case FIND: {
                result = SearchEngine.find(this.textArea, context);
                if (result.wasFound() && !result.isWrapped()) break;
                UIManager.getLookAndFeel().provideErrorFeedback(this.textArea);
                break;
            }
            case REPLACE: {
                result = SearchEngine.replace(this.textArea, context);
                if (result.wasFound() && !result.isWrapped()) break;
                UIManager.getLookAndFeel().provideErrorFeedback(this.textArea);
                break;
            }
            case REPLACE_ALL: {
                result = SearchEngine.replaceAll(this.textArea, context);
                JOptionPane.showMessageDialog(null, result.getCount() + " occurrences replaced.");
            }
        }
        if (result.wasFound()) {
            String text = "Text found; occurrences marked: " + result.getMarkedCount();
        } else if (type == SearchEvent.Type.MARK_ALL) {
            if (result.getMarkedCount() > 0) {
                String text = "Occurrences marked: " + result.getMarkedCount();
            } else {
                String text = "";
            }
        } else {
            String text = "Text not found";
        }
    }

    @Override
    public String getSelectedText() {
        return this.textArea.getSelectedText();
    }
}

