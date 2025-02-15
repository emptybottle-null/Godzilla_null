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
import org.fife.ui.rsyntaxtextarea.TokenUtils;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;

public class PythonFoldParser
implements FoldParser {
    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        ArrayList<Fold> folds = new ArrayList<Fold>();
        Fold currentFold = null;
        int lineCount = textArea.getLineCount();
        int tabSize = textArea.getTabSize();
        Stack<Integer> foldStartLeadingWhiteSpaceCounts = new Stack<Integer>();
        int currentNextFoldStart = 0;
        int currentLeadingWhiteSpaceCount = 0;
        try {
            for (int line = 0; line < lineCount; ++line) {
                Token t = textArea.getTokenListForLine(line);
                int leadingWhiteSpaceCount = PythonFoldParser.getLeadingWhiteSpaceCount(t, tabSize);
                if (leadingWhiteSpaceCount == -1) continue;
                if (leadingWhiteSpaceCount == currentLeadingWhiteSpaceCount) {
                    currentNextFoldStart = t.getOffset() + leadingWhiteSpaceCount;
                } else if (leadingWhiteSpaceCount > currentLeadingWhiteSpaceCount) {
                    if (currentFold != null) {
                        currentFold = currentFold.createChild(0, currentNextFoldStart);
                    } else {
                        currentFold = new Fold(0, textArea, currentNextFoldStart);
                        folds.add(currentFold);
                    }
                    foldStartLeadingWhiteSpaceCounts.push(currentLeadingWhiteSpaceCount);
                    currentNextFoldStart = t.getOffset() + leadingWhiteSpaceCount;
                } else {
                    currentNextFoldStart = t.getOffset() + leadingWhiteSpaceCount;
                    int prevLine = line - 1;
                    while (TokenUtils.isBlankOrAllWhiteSpace(t = textArea.getTokenListForLine(prevLine--))) {
                    }
                    int endOffs = t.getEndOffset() - 1;
                    boolean foundBlock = false;
                    while (!foldStartLeadingWhiteSpaceCounts.isEmpty() && (Integer)foldStartLeadingWhiteSpaceCounts.peek() >= leadingWhiteSpaceCount) {
                        currentFold.setEndOffset(endOffs);
                        currentFold = currentFold.getParent();
                        foldStartLeadingWhiteSpaceCounts.pop();
                        foundBlock = true;
                    }
                    if (!foundBlock && currentFold != null && !currentFold.removeFromParent()) {
                        folds.remove(folds.size() - 1);
                    }
                }
                currentLeadingWhiteSpaceCount = leadingWhiteSpaceCount;
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return folds;
    }

    private static int getLeadingWhiteSpaceCount(Token t, int tabSize) {
        if (t == null || t.getType() == 13 || t.getType() == 14) {
            return -1;
        }
        int count = 0;
        while (t != null && t.isPaintable()) {
            if (!t.isWhitespace()) {
                return t.getType() == 1 ? -1 : count;
            }
            count += TokenUtils.getWhiteSpaceTokenLength(t, tabSize, count);
            t = t.getNextToken();
        }
        return -1;
    }
}

