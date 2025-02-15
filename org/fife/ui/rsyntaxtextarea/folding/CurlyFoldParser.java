/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;

public class CurlyFoldParser
implements FoldParser {
    private boolean foldableMultiLineComments;
    private final boolean java;
    private static final char[] KEYWORD_IMPORT = "import".toCharArray();
    protected static final char[] C_MLC_END = "*/".toCharArray();

    public CurlyFoldParser() {
        this(true, false);
    }

    public CurlyFoldParser(boolean cStyleMultiLineComments, boolean java) {
        this.foldableMultiLineComments = cStyleMultiLineComments;
        this.java = java;
    }

    public boolean getFoldableMultiLineComments() {
        return this.foldableMultiLineComments;
    }

    @Override
    public List<Fold> getFolds(RSyntaxTextArea textArea) {
        ArrayList<Fold> folds = new ArrayList<Fold>();
        Fold currentFold = null;
        int lineCount = textArea.getLineCount();
        boolean inMLC = false;
        int mlcStart = 0;
        int importStartLine = -1;
        int lastSeenImportLine = -1;
        int importGroupStartOffs = -1;
        int importGroupEndOffs = -1;
        int lastRightCurlyLine = -1;
        Fold prevFold = null;
        try {
            for (int line = 0; line < lineCount; ++line) {
                for (Token t = textArea.getTokenListForLine(line); t != null && t.isPaintable(); t = t.getNextToken()) {
                    if (this.getFoldableMultiLineComments() && t.isComment()) {
                        if (this.java && importStartLine > -1) {
                            if (lastSeenImportLine > importStartLine) {
                                Fold fold = null;
                                if (currentFold == null) {
                                    fold = new Fold(2, textArea, importGroupStartOffs);
                                    folds.add(fold);
                                } else {
                                    fold = currentFold.createChild(2, importGroupStartOffs);
                                }
                                fold.setEndOffset(importGroupEndOffs);
                            }
                            importGroupEndOffs = -1;
                            importGroupStartOffs = -1;
                            lastSeenImportLine = -1;
                            importStartLine = -1;
                        }
                        if (inMLC) {
                            if (!t.endsWith(C_MLC_END)) continue;
                            int mlcEnd = t.getEndOffset() - 1;
                            if (currentFold == null) {
                                currentFold = new Fold(1, textArea, mlcStart);
                                currentFold.setEndOffset(mlcEnd);
                                folds.add(currentFold);
                                currentFold = null;
                            } else {
                                currentFold = currentFold.createChild(1, mlcStart);
                                currentFold.setEndOffset(mlcEnd);
                                currentFold = currentFold.getParent();
                            }
                            inMLC = false;
                            mlcStart = 0;
                            continue;
                        }
                        if (t.getType() == 1 || t.endsWith(C_MLC_END)) continue;
                        inMLC = true;
                        mlcStart = t.getOffset();
                        continue;
                    }
                    if (this.isLeftCurly(t)) {
                        if (this.java && importStartLine > -1) {
                            if (lastSeenImportLine > importStartLine) {
                                Fold fold = null;
                                if (currentFold == null) {
                                    fold = new Fold(2, textArea, importGroupStartOffs);
                                    folds.add(fold);
                                } else {
                                    fold = currentFold.createChild(2, importGroupStartOffs);
                                }
                                fold.setEndOffset(importGroupEndOffs);
                            }
                            importGroupEndOffs = -1;
                            importGroupStartOffs = -1;
                            lastSeenImportLine = -1;
                            importStartLine = -1;
                        }
                        if (prevFold != null && line == lastRightCurlyLine) {
                            currentFold = prevFold;
                            prevFold = null;
                            lastRightCurlyLine = -1;
                            continue;
                        }
                        if (currentFold == null) {
                            currentFold = new Fold(0, textArea, t.getOffset());
                            folds.add(currentFold);
                            continue;
                        }
                        currentFold = currentFold.createChild(0, t.getOffset());
                        continue;
                    }
                    if (this.isRightCurly(t)) {
                        if (currentFold == null) continue;
                        currentFold.setEndOffset(t.getOffset());
                        Fold parentFold = currentFold.getParent();
                        if (currentFold.isOnSingleLine()) {
                            if (!currentFold.removeFromParent()) {
                                folds.remove(folds.size() - 1);
                            }
                        } else {
                            lastRightCurlyLine = line;
                            prevFold = currentFold;
                        }
                        currentFold = parentFold;
                        continue;
                    }
                    if (!this.java) continue;
                    if (t.is(6, KEYWORD_IMPORT)) {
                        if (importStartLine == -1) {
                            importStartLine = line;
                            importGroupStartOffs = t.getOffset();
                            importGroupEndOffs = t.getOffset();
                        }
                        lastSeenImportLine = line;
                        continue;
                    }
                    if (importStartLine <= -1 || !t.isIdentifier() || !t.isSingleChar(';')) continue;
                    importGroupEndOffs = t.getOffset();
                }
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
        return folds;
    }

    public boolean isLeftCurly(Token t) {
        return t.isLeftCurly();
    }

    public boolean isRightCurly(Token t) {
        return t.isRightCurly();
    }

    public void setFoldableMultiLineComments(boolean foldable) {
        this.foldableMultiLineComments = foldable;
    }
}

