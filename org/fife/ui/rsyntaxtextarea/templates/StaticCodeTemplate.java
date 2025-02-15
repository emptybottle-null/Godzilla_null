/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.templates;

import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Element;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.templates.AbstractCodeTemplate;

public class StaticCodeTemplate
extends AbstractCodeTemplate {
    private static final long serialVersionUID = 1L;
    private String beforeCaret;
    private String afterCaret;
    private transient int firstBeforeNewline;
    private transient int firstAfterNewline;
    private static final String EMPTY_STRING = "";

    public StaticCodeTemplate() {
    }

    public StaticCodeTemplate(String id, String beforeCaret, String afterCaret) {
        super(id);
        this.setBeforeCaretText(beforeCaret);
        this.setAfterCaretText(afterCaret);
    }

    public String getAfterCaretText() {
        return this.afterCaret;
    }

    public String getBeforeCaretText() {
        return this.beforeCaret;
    }

    private String getAfterTextIndented(String indent) {
        return this.getTextIndented(this.getAfterCaretText(), this.firstAfterNewline, indent);
    }

    private String getBeforeTextIndented(String indent) {
        return this.getTextIndented(this.getBeforeCaretText(), this.firstBeforeNewline, indent);
    }

    private String getTextIndented(String text, int firstNewline, String indent) {
        if (firstNewline == -1) {
            return text;
        }
        int pos = 0;
        int old = firstNewline + 1;
        StringBuilder sb = new StringBuilder(text.substring(0, old));
        sb.append(indent);
        while ((pos = text.indexOf(10, old)) > -1) {
            sb.append(text.substring(old, pos + 1));
            sb.append(indent);
            old = pos + 1;
        }
        if (old < text.length()) {
            sb.append(text.substring(old));
        }
        return sb.toString();
    }

    @Override
    public void invoke(RSyntaxTextArea textArea) throws BadLocationException {
        int endWS;
        Caret c = textArea.getCaret();
        int dot = c.getDot();
        int mark = c.getMark();
        int p0 = Math.min(dot, mark);
        int p1 = Math.max(dot, mark);
        RSyntaxDocument doc = (RSyntaxDocument)textArea.getDocument();
        Element map = doc.getDefaultRootElement();
        int lineNum = map.getElementIndex(dot);
        Element line = map.getElement(lineNum);
        int start = line.getStartOffset();
        int end = line.getEndOffset() - 1;
        String s = textArea.getText(start, end - start);
        int len = s.length();
        for (endWS = 0; endWS < len && RSyntaxUtilities.isWhitespace(s.charAt(endWS)); ++endWS) {
        }
        s = s.substring(0, endWS);
        String beforeText = this.getBeforeTextIndented(s);
        String afterText = this.getAfterTextIndented(s);
        doc.replace(p0 -= this.getID().length(), p1 - p0, beforeText + afterText, null);
        textArea.setCaretPosition(p0 + beforeText.length());
    }

    private void readObject(ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.setBeforeCaretText(this.beforeCaret);
        this.setAfterCaretText(this.afterCaret);
    }

    public void setAfterCaretText(String afterCaret) {
        this.afterCaret = afterCaret == null ? EMPTY_STRING : afterCaret;
        this.firstAfterNewline = this.afterCaret.indexOf(10);
    }

    public void setBeforeCaretText(String beforeCaret) {
        this.beforeCaret = beforeCaret == null ? EMPTY_STRING : beforeCaret;
        this.firstBeforeNewline = this.beforeCaret.indexOf(10);
    }

    public String toString() {
        return "[StaticCodeTemplate: id=" + this.getID() + ", text=" + this.getBeforeCaretText() + "|" + this.getAfterCaretText() + "]";
    }
}

