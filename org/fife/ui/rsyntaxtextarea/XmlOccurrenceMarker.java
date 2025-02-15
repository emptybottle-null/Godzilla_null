/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.util.ArrayList;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import org.fife.ui.rsyntaxtextarea.HtmlOccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.SmartHighlightPainter;

public class XmlOccurrenceMarker
implements OccurrenceMarker {
    private static final char[] CLOSE_TAG_START = new char[]{'<', '/'};
    private static final char[] TAG_SELF_CLOSE = new char[]{'/', '>'};

    @Override
    public Token getTokenToMark(RSyntaxTextArea textArea) {
        return HtmlOccurrenceMarker.getTagNameTokenForCaretOffset(textArea, this);
    }

    @Override
    public boolean isValidType(RSyntaxTextArea textArea, Token t) {
        return textArea.getMarkOccurrencesOfTokenType(t.getType());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void markOccurrences(RSyntaxDocument doc, Token t, RSyntaxTextAreaHighlighter h, SmartHighlightPainter p) {
        char[] lexeme = t.getLexeme().toCharArray();
        int tokenOffs = t.getOffset();
        Element root = doc.getDefaultRootElement();
        int lineCount = root.getElementCount();
        int curLine = root.getElementIndex(t.getOffset());
        int depth = 0;
        boolean found = false;
        boolean forward = true;
        for (t = doc.getTokenListForLine(curLine); t != null && t.isPaintable(); t = t.getNextToken()) {
            if (t.getType() != 25) continue;
            if (t.isSingleChar('<') && t.getOffset() + 1 == tokenOffs) {
                found = true;
                break;
            }
            if (!t.is(CLOSE_TAG_START) || t.getOffset() + 2 != tokenOffs) continue;
            found = true;
            forward = false;
            break;
        }
        if (!found) {
            return;
        }
        if (forward) {
            t = t.getNextToken().getNextToken();
            while (true) {
                if (t != null && t.isPaintable()) {
                    if (t.getType() == 25) {
                        if (t.is(CLOSE_TAG_START)) {
                            Token match = t.getNextToken();
                            if (match != null && match.is(lexeme)) {
                                if (depth > 0) {
                                    --depth;
                                } else {
                                    try {
                                        int end = match.getOffset() + match.length();
                                        h.addMarkedOccurrenceHighlight(match.getOffset(), end, p);
                                        end = tokenOffs + match.length();
                                        h.addMarkedOccurrenceHighlight(tokenOffs, end, p);
                                        return;
                                    } catch (BadLocationException ble) {
                                        ble.printStackTrace();
                                    }
                                    return;
                                }
                            }
                        } else if (t.isSingleChar('<') && (t = t.getNextToken()) != null && t.is(lexeme)) {
                            ++depth;
                        }
                    }
                    t = t == null ? null : t.getNextToken();
                    continue;
                }
                if (++curLine < lineCount) {
                    t = doc.getTokenListForLine(curLine);
                }
                if (curLine >= lineCount) break;
            }
            return;
        }
        ArrayList<Entry> openCloses = new ArrayList<Entry>();
        boolean inPossibleMatch = false;
        t = doc.getTokenListForLine(curLine);
        int endBefore = tokenOffs - 2;
        while (true) {
            if (t != null && t.getOffset() < endBefore && t.isPaintable()) {
                if (t.getType() == 25) {
                    Token next;
                    if (t.isSingleChar('<')) {
                        Token next2 = t.getNextToken();
                        if (next2 != null) {
                            if (next2.is(lexeme)) {
                                openCloses.add(new Entry(true, next2));
                                inPossibleMatch = true;
                            } else {
                                inPossibleMatch = false;
                            }
                            t = next2;
                        }
                    } else if (t.isSingleChar('>')) {
                        inPossibleMatch = false;
                    } else if (inPossibleMatch && t.is(TAG_SELF_CLOSE)) {
                        openCloses.remove(openCloses.size() - 1);
                        inPossibleMatch = false;
                    } else if (t.is(CLOSE_TAG_START) && (next = t.getNextToken()) != null) {
                        if (next.is(lexeme)) {
                            openCloses.add(new Entry(false, next));
                        }
                        t = next;
                    }
                }
                t = t.getNextToken();
                continue;
            }
            for (int i = openCloses.size() - 1; i >= 0; --i) {
                Entry entry = (Entry)openCloses.get(i);
                if ((depth += entry.open ? -1 : 1) != -1) continue;
                try {
                    Token match = entry.t;
                    int end = match.getOffset() + match.length();
                    h.addMarkedOccurrenceHighlight(match.getOffset(), end, p);
                    end = tokenOffs + match.length();
                    h.addMarkedOccurrenceHighlight(tokenOffs, end, p);
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
                openCloses.clear();
                return;
            }
            openCloses.clear();
            if (--curLine >= 0) {
                t = doc.getTokenListForLine(curLine);
            }
            if (curLine < 0) break;
        }
    }

    private static class Entry {
        private boolean open;
        private Token t;

        Entry(boolean open, Token t) {
            this.open = open;
            this.t = t;
        }
    }
}

