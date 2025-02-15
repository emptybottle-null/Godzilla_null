/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.awt.event.ActionEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.text.Caret;
import javax.swing.text.Element;
import javax.swing.text.TextAction;
import org.fife.rsta.ac.AbstractLanguageSupport;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;

public abstract class AbstractMarkupLanguageSupport
extends AbstractLanguageSupport {
    protected static final String INSERT_CLOSING_TAG_ACTION = "HtmlLanguageSupport.InsertClosingTag";
    private boolean autoAddClosingTags;

    protected AbstractMarkupLanguageSupport() {
        this.setAutoAddClosingTags(true);
    }

    public boolean getAutoAddClosingTags() {
        return this.autoAddClosingTags;
    }

    protected void installKeyboardShortcuts(RSyntaxTextArea textArea) {
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        im.put(KeyStroke.getKeyStroke('>'), INSERT_CLOSING_TAG_ACTION);
        am.put(INSERT_CLOSING_TAG_ACTION, new InsertClosingTagAction());
    }

    protected abstract boolean shouldAutoCloseTag(String var1);

    public void setAutoAddClosingTags(boolean autoAdd) {
        this.autoAddClosingTags = autoAdd;
    }

    protected void uninstallKeyboardShortcuts(RSyntaxTextArea textArea) {
        InputMap im = textArea.getInputMap();
        ActionMap am = textArea.getActionMap();
        im.remove(KeyStroke.getKeyStroke('>'));
        am.remove(INSERT_CLOSING_TAG_ACTION);
    }

    private class InsertClosingTagAction
    extends TextAction {
        InsertClosingTagAction() {
            super(AbstractMarkupLanguageSupport.INSERT_CLOSING_TAG_ACTION);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RSyntaxTextArea textArea = (RSyntaxTextArea)this.getTextComponent(e);
            RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
            Caret c = textArea.getCaret();
            int dot = c.getDot();
            boolean selection = dot != c.getMark();
            textArea.replaceSelection(">");
            if (!selection && AbstractMarkupLanguageSupport.this.getAutoAddClosingTags()) {
                String tagName;
                Token t = doc.getTokenListForLine(textArea.getCaretLineNumber());
                if ((t = RSyntaxUtilities.getTokenAtOffset(t, dot)) != null && t.isSingleChar(25, '>') && (tagName = this.discoverTagName(doc, dot)) != null) {
                    textArea.replaceSelection("</" + tagName + ">");
                    textArea.setCaretPosition(dot + 1);
                }
            }
        }

        private String discoverTagName(RSyntaxDocument doc, int dot) {
            String candidate = null;
            Element root = doc.getDefaultRootElement();
            int curLine = root.getElementIndex(dot);
            for (Token t = doc.getTokenListForLine(curLine); t != null && t.isPaintable(); t = t.getNextToken()) {
                if (t.getType() != 25) continue;
                if (t.isSingleChar('<')) {
                    if ((t = t.getNextToken()) == null || !t.isPaintable()) continue;
                    candidate = t.getLexeme();
                    continue;
                }
                if (t.isSingleChar('>')) {
                    if (t.getOffset() != dot) continue;
                    if (candidate == null || AbstractMarkupLanguageSupport.this.shouldAutoCloseTag(candidate)) {
                        return candidate;
                    }
                    return null;
                }
                if (!t.is(25, "</")) continue;
                candidate = null;
            }
            return null;
        }
    }
}

