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

public class JsonFoldParser
implements FoldParser {
    private static final Object OBJECT_BLOCK = new Object();
    private static final Object ARRAY_BLOCK = new Object();

    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        Stack<Object> blocks = new Stack<Object>();
        ArrayList<Fold> folds = new ArrayList<Fold>();
        Fold currentFold = null;
        int lineCount = textArea.getLineCount();
        try {
            for (int line = 0; line < lineCount; ++line) {
                for (Token t = textArea.getTokenListForLine(line); t != null && t.isPaintable(); t = t.getNextToken()) {
                    Fold parentFold;
                    if (t.isLeftCurly()) {
                        if (currentFold == null) {
                            currentFold = new Fold(0, textArea, t.getOffset());
                            folds.add(currentFold);
                        } else {
                            currentFold = currentFold.createChild(0, t.getOffset());
                        }
                        blocks.push(OBJECT_BLOCK);
                        continue;
                    }
                    if (t.isRightCurly() && JsonFoldParser.popOffTop(blocks, OBJECT_BLOCK)) {
                        if (currentFold == null) continue;
                        currentFold.setEndOffset(t.getOffset());
                        parentFold = currentFold.getParent();
                        if (currentFold.isOnSingleLine() && !currentFold.removeFromParent()) {
                            folds.remove(folds.size() - 1);
                        }
                        currentFold = parentFold;
                        continue;
                    }
                    if (JsonFoldParser.isLeftBracket(t)) {
                        if (currentFold == null) {
                            currentFold = new Fold(0, textArea, t.getOffset());
                            folds.add(currentFold);
                        } else {
                            currentFold = currentFold.createChild(0, t.getOffset());
                        }
                        blocks.push(ARRAY_BLOCK);
                        continue;
                    }
                    if (!JsonFoldParser.isRightBracket(t) || !JsonFoldParser.popOffTop(blocks, ARRAY_BLOCK) || currentFold == null) continue;
                    currentFold.setEndOffset(t.getOffset());
                    parentFold = currentFold.getParent();
                    if (currentFold.isOnSingleLine() && !currentFold.removeFromParent()) {
                        folds.remove(folds.size() - 1);
                    }
                    currentFold = parentFold;
                }
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return folds;
    }

    private static boolean isLeftBracket(Token t) {
        return t.getType() == 22 && t.isSingleChar('[');
    }

    private static boolean isRightBracket(Token t) {
        return t.getType() == 22 && t.isSingleChar(']');
    }

    private static boolean popOffTop(Stack<Object> stack, Object value) {
        if (stack.size() > 0 && stack.peek() == value) {
            stack.pop();
            return true;
        }
        return false;
    }
}

