/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaHighlighter;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.SmartHighlightPainter;

class DefaultOccurrenceMarker
implements OccurrenceMarker {
    DefaultOccurrenceMarker() {
    }

    @Override
    public Token getTokenToMark(RSyntaxTextArea textArea) {
        Caret c;
        int dot;
        int line = textArea.getCaretLineNumber();
        Token tokenList = textArea.getTokenListForLine(line);
        Token t = RSyntaxUtilities.getTokenAtOffset(tokenList, dot = (c = textArea.getCaret()).getDot());
        if (t == null || !this.isValidType(textArea, t) || RSyntaxUtilities.isNonWordChar(t)) {
            --dot;
            try {
                if (dot >= textArea.getLineStartOffset(line)) {
                    t = RSyntaxUtilities.getTokenAtOffset(tokenList, dot);
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
        return t;
    }

    @Override
    public boolean isValidType(RSyntaxTextArea textArea, Token t) {
        return textArea.getMarkOccurrencesOfTokenType(t.getType());
    }

    @Override
    public void markOccurrences(RSyntaxDocument doc, Token t, RSyntaxTextAreaHighlighter h, SmartHighlightPainter p) {
        DefaultOccurrenceMarker.markOccurrencesOfToken(doc, t, h, p);
    }

    public static void markOccurrencesOfToken(RSyntaxDocument doc, Token t, RSyntaxTextAreaHighlighter h, SmartHighlightPainter p) {
        char[] lexeme = t.getLexeme().toCharArray();
        int type = t.getType();
        int lineCount = doc.getDefaultRootElement().getElementCount();
        for (int i = 0; i < lineCount; ++i) {
            for (Token temp = doc.getTokenListForLine(i); temp != null && temp.isPaintable(); temp = temp.getNextToken()) {
                if (!temp.is(type, lexeme)) continue;
                try {
                    int end = temp.getEndOffset();
                    h.addMarkedOccurrenceHighlight(temp.getOffset(), end, p);
                    continue;
                } catch (BadLocationException ble) {
                    ble.printStackTrace();
                }
            }
        }
    }
}

