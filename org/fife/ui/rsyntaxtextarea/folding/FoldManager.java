/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import java.beans.PropertyChangeListener;
import java.util.List;
import org.fife.ui.rsyntaxtextarea.folding.Fold;

public interface FoldManager {
    public static final String PROPERTY_FOLDS_UPDATED = "FoldsUpdated";

    public void addPropertyChangeListener(PropertyChangeListener var1);

    public void clear();

    public boolean ensureOffsetNotInClosedFold(int var1);

    public Fold getDeepestFoldContaining(int var1);

    public Fold getDeepestOpenFoldContaining(int var1);

    public Fold getFold(int var1);

    public int getFoldCount();

    public Fold getFoldForLine(int var1);

    public int getHiddenLineCount();

    public int getHiddenLineCountAbove(int var1);

    public int getHiddenLineCountAbove(int var1, boolean var2);

    public int getLastVisibleLine();

    public int getVisibleLineAbove(int var1);

    public int getVisibleLineBelow(int var1);

    public boolean isCodeFoldingEnabled();

    public boolean isCodeFoldingSupportedAndEnabled();

    public boolean isFoldStartLine(int var1);

    public boolean isLineHidden(int var1);

    public void removePropertyChangeListener(PropertyChangeListener var1);

    public void reparse();

    public void setCodeFoldingEnabled(boolean var1);

    public void setFolds(List<Fold> var1);
}

