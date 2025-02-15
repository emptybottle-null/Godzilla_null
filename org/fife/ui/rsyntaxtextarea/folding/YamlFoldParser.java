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

public class YamlFoldParser
implements FoldParser {
    private static boolean isSpaces(Token t) {
        String lexeme = t.getLexeme();
        return lexeme.trim().isEmpty();
    }

    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        ArrayList<Fold> folds = new ArrayList<Fold>();
        Stack<Integer> indentStack = new Stack<Integer>();
        Fold currentFold = null;
        int lineCount = textArea.getLineCount();
        int lastOffset = 0;
        try {
            for (int line = 0; line < lineCount; ++line) {
                int outer;
                Token t = textArea.getTokenListForLine(line);
                if (!t.isPaintable()) continue;
                Token startLine = t;
                int offset = t.getOffset();
                int indent = 0;
                while (t != null && t.isPaintable() && YamlFoldParser.isSpaces(t)) {
                    indent += t.length();
                    t = t.getNextToken();
                }
                if (t != null && t.isPaintable() && t.isSingleChar('-')) {
                    ++indent;
                    t = t.getNextToken();
                }
                while (!indentStack.empty() && (outer = ((Integer)indentStack.peek()).intValue()) >= indent && currentFold != null) {
                    currentFold.setEndOffset(lastOffset);
                    Fold parentFold = currentFold.getParent();
                    if (currentFold.isOnSingleLine()) {
                        YamlFoldParser.removeFold(currentFold, folds);
                    }
                    currentFold = parentFold;
                    indentStack.pop();
                }
                while (t != null && t.isPaintable()) {
                    offset = t.getOffset();
                    t = t.getNextToken();
                }
                lastOffset = offset;
                if (currentFold == null) {
                    currentFold = new Fold(0, textArea, startLine.getOffset());
                    folds.add(currentFold);
                } else {
                    currentFold = currentFold.createChild(0, startLine.getOffset());
                }
                indentStack.push(indent);
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return folds;
    }

    private static void removeFold(Fold fold, List<Fold> folds) {
        if (!fold.removeFromParent()) {
            folds.remove(folds.size() - 1);
        }
    }
}

