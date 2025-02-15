/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;
import org.fife.ui.rsyntaxtextarea.folding.FoldParser;
import org.fife.ui.rsyntaxtextarea.folding.FoldParserManager;
import org.fife.ui.rsyntaxtextarea.parser.AbstractParser;
import org.fife.ui.rsyntaxtextarea.parser.DefaultParseResult;
import org.fife.ui.rsyntaxtextarea.parser.ParseResult;
import org.fife.ui.rsyntaxtextarea.parser.Parser;
import org.fife.ui.rtextarea.RDocument;

public class DefaultFoldManager
implements FoldManager {
    private RSyntaxTextArea textArea;
    private Parser rstaParser;
    private FoldParser foldParser;
    private List<Fold> folds;
    private boolean codeFoldingEnabled;
    private PropertyChangeSupport support;
    private Listener l;

    public DefaultFoldManager(RSyntaxTextArea textArea) {
        this.textArea = textArea;
        this.support = new PropertyChangeSupport(this);
        this.l = new Listener();
        textArea.getDocument().addDocumentListener(this.l);
        textArea.addPropertyChangeListener("RSTA.syntaxStyle", this.l);
        textArea.addPropertyChangeListener("document", this.l);
        this.folds = new ArrayList<Fold>();
        this.updateFoldParser();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        this.support.addPropertyChangeListener(l);
    }

    @Override
    public void clear() {
        this.folds.clear();
    }

    @Override
    public boolean ensureOffsetNotInClosedFold(int offs) {
        boolean foldsOpened = false;
        for (Fold fold = this.getDeepestFoldContaining(offs); fold != null; fold = fold.getParent()) {
            if (!fold.isCollapsed()) continue;
            fold.setCollapsed(false);
            foldsOpened = true;
        }
        if (foldsOpened) {
            RSyntaxUtilities.possiblyRepaintGutter(this.textArea);
        }
        return foldsOpened;
    }

    @Override
    public Fold getDeepestFoldContaining(int offs) {
        Fold deepestFold = null;
        if (offs > -1) {
            for (int i = 0; i < this.folds.size(); ++i) {
                Fold fold = this.getFold(i);
                if (!fold.containsOffset(offs)) continue;
                deepestFold = fold.getDeepestFoldContaining(offs);
                break;
            }
        }
        return deepestFold;
    }

    @Override
    public Fold getDeepestOpenFoldContaining(int offs) {
        Fold deepestFold = null;
        if (offs > -1) {
            for (int i = 0; i < this.folds.size(); ++i) {
                Fold fold = this.getFold(i);
                if (!fold.containsOffset(offs)) continue;
                if (fold.isCollapsed()) {
                    return null;
                }
                deepestFold = fold.getDeepestOpenFoldContaining(offs);
                break;
            }
        }
        return deepestFold;
    }

    @Override
    public Fold getFold(int index) {
        return this.folds.get(index);
    }

    @Override
    public int getFoldCount() {
        return this.folds.size();
    }

    @Override
    public Fold getFoldForLine(int line) {
        return this.getFoldForLineImpl(null, this.folds, line);
    }

    private Fold getFoldForLineImpl(Fold parent, List<Fold> folds, int line) {
        int low = 0;
        int high = folds.size() - 1;
        while (low <= high) {
            int mid = low + high >> 1;
            Fold midFold = folds.get(mid);
            int startLine = midFold.getStartLine();
            if (line == startLine) {
                return midFold;
            }
            if (line < startLine) {
                high = mid - 1;
                continue;
            }
            int endLine = midFold.getEndLine();
            if (line >= endLine) {
                low = mid + 1;
                continue;
            }
            List<Fold> children = midFold.getChildren();
            return children != null ? this.getFoldForLineImpl(midFold, children, line) : null;
        }
        return null;
    }

    @Override
    public int getHiddenLineCount() {
        int count = 0;
        for (Fold fold : this.folds) {
            count += fold.getCollapsedLineCount();
        }
        return count;
    }

    @Override
    public int getHiddenLineCountAbove(int line) {
        return this.getHiddenLineCountAbove(line, false);
    }

    @Override
    public int getHiddenLineCountAbove(int line, boolean physical) {
        int count = 0;
        for (Fold fold : this.folds) {
            int comp;
            int n = comp = physical ? line + count : line;
            if (fold.getStartLine() >= comp) break;
            count += this.getHiddenLineCountAboveImpl(fold, comp, physical);
        }
        return count;
    }

    private int getHiddenLineCountAboveImpl(Fold fold, int line, boolean physical) {
        int count = 0;
        if (fold.getEndLine() < line || fold.isCollapsed() && fold.getStartLine() < line) {
            count = fold.getCollapsedLineCount();
        } else {
            int childCount = fold.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                int comp;
                Fold child = fold.getChild(i);
                int n = comp = physical ? line + count : line;
                if (child.getStartLine() >= comp) break;
                count += this.getHiddenLineCountAboveImpl(child, comp, physical);
            }
        }
        return count;
    }

    @Override
    public int getLastVisibleLine() {
        Fold lastFold;
        int foldCount;
        int lastLine = this.textArea.getLineCount() - 1;
        if (this.isCodeFoldingSupportedAndEnabled() && (foldCount = this.getFoldCount()) > 0 && (lastFold = this.getFold(foldCount - 1)).containsLine(lastLine)) {
            if (lastFold.isCollapsed()) {
                lastLine = lastFold.getStartLine();
            } else {
                while (lastFold.getHasChildFolds() && (lastFold = lastFold.getLastChild()).containsLine(lastLine)) {
                    if (!lastFold.isCollapsed()) continue;
                    lastLine = lastFold.getStartLine();
                    break;
                }
            }
        }
        return lastLine;
    }

    @Override
    public int getVisibleLineAbove(int line) {
        if (line <= 0 || line >= this.textArea.getLineCount()) {
            return -1;
        }
        while (--line >= 0 && this.isLineHidden(line)) {
        }
        return line;
    }

    @Override
    public int getVisibleLineBelow(int line) {
        int lineCount = this.textArea.getLineCount();
        if (line < 0 || line >= lineCount - 1) {
            return -1;
        }
        while (++line < lineCount && this.isLineHidden(line)) {
        }
        return line == lineCount ? -1 : line;
    }

    @Override
    public boolean isCodeFoldingEnabled() {
        return this.codeFoldingEnabled;
    }

    @Override
    public boolean isCodeFoldingSupportedAndEnabled() {
        return this.codeFoldingEnabled && this.foldParser != null;
    }

    @Override
    public boolean isFoldStartLine(int line) {
        return this.getFoldForLine(line) != null;
    }

    @Override
    public boolean isLineHidden(int line) {
        for (Fold fold : this.folds) {
            if (!fold.containsLine(line)) continue;
            if (fold.isCollapsed()) {
                return true;
            }
            return this.isLineHiddenImpl(fold, line);
        }
        return false;
    }

    private boolean isLineHiddenImpl(Fold parent, int line) {
        for (int i = 0; i < parent.getChildCount(); ++i) {
            Fold child = parent.getChild(i);
            if (!child.containsLine(line)) continue;
            if (child.isCollapsed()) {
                return true;
            }
            return this.isLineHiddenImpl(child, line);
        }
        return false;
    }

    private void keepFoldState(Fold newFold, List<Fold> oldFolds) {
        int previousLoc = Collections.binarySearch(oldFolds, newFold);
        if (previousLoc >= 0) {
            Fold prevFold = oldFolds.get(previousLoc);
            newFold.setCollapsed(prevFold.isCollapsed());
        } else {
            List<Fold> children;
            Fold possibleParentFold;
            int insertionPoint = -(previousLoc + 1);
            if (insertionPoint > 0 && (possibleParentFold = oldFolds.get(insertionPoint - 1)).containsOffset(newFold.getStartOffset()) && (children = possibleParentFold.getChildren()) != null) {
                this.keepFoldState(newFold, children);
            }
        }
    }

    private void keepFoldStates(List<Fold> newFolds, List<Fold> oldFolds) {
        for (Fold newFold : newFolds) {
            this.keepFoldState(newFold, this.folds);
            List<Fold> newChildFolds = newFold.getChildren();
            if (newChildFolds == null) continue;
            this.keepFoldStates(newChildFolds, oldFolds);
        }
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        this.support.removePropertyChangeListener(l);
    }

    @Override
    public void reparse() {
        if (this.codeFoldingEnabled && this.foldParser != null) {
            List<Fold> newFolds = this.foldParser.getFolds(this.textArea);
            if (newFolds == null) {
                newFolds = Collections.emptyList();
            } else {
                this.keepFoldStates(newFolds, this.folds);
            }
            this.folds = newFolds;
            this.support.firePropertyChange("FoldsUpdated", null, this.folds);
            this.textArea.repaint();
        } else {
            this.folds.clear();
        }
    }

    @Override
    public void setCodeFoldingEnabled(boolean enabled) {
        if (enabled != this.codeFoldingEnabled) {
            this.codeFoldingEnabled = enabled;
            if (this.rstaParser != null) {
                this.textArea.removeParser(this.rstaParser);
            }
            if (enabled) {
                this.rstaParser = new AbstractParser(){

                    @Override
                    public ParseResult parse(RSyntaxDocument doc, String style) {
                        DefaultFoldManager.this.reparse();
                        return new DefaultParseResult(this);
                    }
                };
                this.textArea.addParser(this.rstaParser);
                this.support.firePropertyChange("FoldsUpdated", null, null);
            } else {
                this.folds = Collections.emptyList();
                this.textArea.repaint();
                this.support.firePropertyChange("FoldsUpdated", null, null);
            }
        }
    }

    @Override
    public void setFolds(List<Fold> folds) {
        this.folds = folds;
    }

    private void updateFoldParser() {
        this.foldParser = FoldParserManager.get().getFoldParser(this.textArea.getSyntaxEditingStyle());
    }

    private class Listener
    implements DocumentListener,
    PropertyChangeListener {
        private Listener() {
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            Fold fold;
            int endLine;
            int startOffs = e.getOffset();
            int endOffs = startOffs + e.getLength();
            Document doc = e.getDocument();
            Element root = doc.getDefaultRootElement();
            int startLine = root.getElementIndex(startOffs);
            if (startLine != (endLine = root.getElementIndex(endOffs)) && (fold = DefaultFoldManager.this.getFoldForLine(startLine)) != null && fold.isCollapsed()) {
                fold.toggleCollapsedState();
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            String name = e.getPropertyName();
            if ("RSTA.syntaxStyle".equals(name)) {
                DefaultFoldManager.this.updateFoldParser();
                DefaultFoldManager.this.reparse();
            } else if ("document".equals(name)) {
                RDocument newDoc;
                RDocument old = (RDocument)e.getOldValue();
                if (old != null) {
                    old.removeDocumentListener(this);
                }
                if ((newDoc = (RDocument)e.getNewValue()) != null) {
                    newDoc.addDocumentListener(this);
                }
                DefaultFoldManager.this.reparse();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int offs = e.getOffset();
            try {
                int lastLineModified = DefaultFoldManager.this.textArea.getLineOfOffset(offs);
                Fold fold = DefaultFoldManager.this.getFoldForLine(lastLineModified);
                if (fold != null && fold.isCollapsed()) {
                    fold.toggleCollapsedState();
                }
            } catch (BadLocationException ble) {
                ble.printStackTrace();
            }
        }
    }
}

