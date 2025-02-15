/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;

public class LatexFoldParser
implements FoldParser {
    private static final char[] BEGIN = "\\begin".toCharArray();
    private static final char[] END = "\\end".toCharArray();

    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        ArrayList<Fold> folds = new ArrayList<Fold>();
        Stack<String> expectedStack = new Stack<String>();
        Fold currentFold = null;
        int lineCount = textArea.getLineCount();
        try {
            for (int line = 0; line < lineCount; ++line) {
                for (Token t = textArea.getTokenListForLine(line); t != null && t.isPaintable(); t = t.getNextToken()) {
                    Token temp;
                    if (t.is(6, BEGIN)) {
                        temp = t.getNextToken();
                        if (temp == null || !temp.isLeftCurly() || (temp = temp.getNextToken()) == null || temp.getType() != 6) continue;
                        if (currentFold == null) {
                            currentFold = new Fold(0, textArea, t.getOffset());
                            folds.add(currentFold);
                        } else {
                            currentFold = currentFold.createChild(0, t.getOffset());
                        }
                        expectedStack.push(temp.getLexeme());
                        t = temp;
                        continue;
                    }
                    if (!t.is(6, END) || currentFold == null || expectedStack.isEmpty() || (temp = t.getNextToken()) == null || !temp.isLeftCurly() || (temp = temp.getNextToken()) == null || temp.getType() != 6) continue;
                    String value = temp.getLexeme();
                    if (!((String)expectedStack.peek()).equals(value)) continue;
                    expectedStack.pop();
                    currentFold.setEndOffset(t.getOffset());
                    Fold parentFold = currentFold.getParent();
                    if (currentFold.isOnSingleLine() && !currentFold.removeFromParent()) {
                        folds.remove(folds.size() - 1);
                    }
                    t = temp;
                    currentFold = parentFold;
                }
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return folds;
    }
}

