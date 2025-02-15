/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Action;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.AbstractJFlexTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextAreaEditorKit;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextArea;

public abstract class AbstractJFlexCTokenMaker
extends AbstractJFlexTokenMaker {
    private final Action INSERT_BREAK_ACTION = this.createInsertBreakAction();
    private static final Pattern MLC_PATTERN = Pattern.compile("([ \\t]*)(/?[\\*]+)([ \\t]*)");

    protected AbstractJFlexCTokenMaker() {
    }

    protected Action createInsertBreakAction() {
        return new CStyleInsertBreakAction();
    }

    @Override
    public boolean getCurlyBracesDenoteCodeBlocks(int languageIndex) {
        return true;
    }

    @Override
    public Action getInsertBreakAction() {
        return this.INSERT_BREAK_ACTION;
    }

    @Override
    public boolean getMarkOccurrencesOfTokenType(int type) {
        return type == 20 || type == 8;
    }

    @Override
    public boolean getShouldIndentNextLineAfter(Token t) {
        if (t != null && t.length() == 1) {
            char ch = t.charAt(0);
            return ch == '{' || ch == '(';
        }
        return false;
    }

    private boolean isInternalEolTokenForMLCs(Token t) {
        int type = t.getType();
        if (type < 0) {
            return (type = this.getClosestStandardTokenTypeForInternalType(type)) == 2 || type == 3;
        }
        return false;
    }

    protected class CStyleInsertBreakAction
    extends RSyntaxTextAreaEditorKit.InsertBreakAction {
        protected CStyleInsertBreakAction() {
        }

        @Override
        public void actionPerformedImpl(ActionEvent e, RTextArea textArea) {
            int line;
            if (!textArea.isEditable() || !textArea.isEnabled()) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                return;
            }
            RSyntaxTextArea rsta = (RSyntaxTextArea)this.getTextComponent(e);
            RSyntaxDocument doc = (RSyntaxDocument)rsta.getDocument();
            int type = doc.getLastTokenTypeOnLine(line = textArea.getCaretLineNumber());
            if (type < 0) {
                type = doc.getClosestStandardTokenTypeForInternalType(type);
            }
            if (type == 3 || type == 2) {
                this.insertBreakInMLC(e, rsta, line);
            } else {
                this.handleInsertBreak(rsta, true);
            }
        }

        private boolean appearsNested(RSyntaxTextArea textArea, int line, int offs) {
            int firstLine = line;
            while (line < textArea.getLineCount()) {
                Token t = textArea.getTokenListForLine(line);
                int i = 0;
                if (line++ == firstLine) {
                    if ((t = RSyntaxUtilities.getTokenAtOffset(t, offs)) == null) continue;
                    i = t.documentToToken(offs);
                } else {
                    i = t.getTextOffset();
                }
                int textOffset = t.getTextOffset();
                while (i < textOffset + t.length() - 1) {
                    if (t.charAt(i - textOffset) == '/' && t.charAt(i - textOffset + 1) == '*') {
                        return true;
                    }
                    ++i;
                }
                if ((t = t.getNextToken()) == null || AbstractJFlexCTokenMaker.this.isInternalEolTokenForMLCs(t)) continue;
                return false;
            }
            return true;
        }

        private void insertBreakInMLC(ActionEvent e, RSyntaxTextArea textArea, int line) {
            Matcher m = null;
            int start = -1;
            int end = -1;
            String text = null;
            try {
                start = textArea.getLineStartOffset(line);
                end = textArea.getLineEndOffset(line);
                text = textArea.getText(start, end - start);
                m = MLC_PATTERN.matcher(text);
            } catch (BadLocationException ble) {
                UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                ble.printStackTrace();
                return;
            }
            if (m.lookingAt()) {
                String leadingWS = m.group(1);
                String mlcMarker = m.group(2);
                int dot = textArea.getCaretPosition();
                if (dot >= start && dot < start + leadingWS.length() + mlcMarker.length()) {
                    if (mlcMarker.charAt(0) == '/') {
                        this.handleInsertBreak(textArea, true);
                        return;
                    }
                    textArea.setCaretPosition(end - 1);
                } else {
                    boolean moved = false;
                    while (dot < end - 1 && Character.isWhitespace(text.charAt(dot - start))) {
                        moved = true;
                        ++dot;
                    }
                    if (moved) {
                        textArea.setCaretPosition(dot);
                    }
                }
                boolean firstMlcLine = mlcMarker.charAt(0) == '/';
                boolean nested = this.appearsNested(textArea, line, start + leadingWS.length() + 2);
                String header = leadingWS + (firstMlcLine ? " * " : "*") + m.group(3);
                textArea.replaceSelection("\n" + header);
                if (nested) {
                    dot = textArea.getCaretPosition();
                    textArea.insert("\n" + leadingWS + " */", dot);
                    textArea.setCaretPosition(dot);
                }
            } else {
                this.handleInsertBreak(textArea, true);
            }
        }
    }
}

