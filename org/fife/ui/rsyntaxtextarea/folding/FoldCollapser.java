/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea.folding;

import java.util.ArrayList;
import java.util.List;
import org.fife.ui.rsyntaxtextarea.folding.Fold;
import org.fife.ui.rsyntaxtextarea.folding.FoldManager;

public class FoldCollapser {
    private List<Integer> typesToCollapse = new ArrayList<Integer>(3);

    public FoldCollapser() {
        this(1);
    }

    public FoldCollapser(int typeToCollapse) {
        this.addTypeToCollapse(typeToCollapse);
    }

    public void addTypeToCollapse(int typeToCollapse) {
        this.typesToCollapse.add(typeToCollapse);
    }

    public void collapseFolds(FoldManager fm) {
        for (int i = 0; i < fm.getFoldCount(); ++i) {
            Fold fold = fm.getFold(i);
            this.collapseImpl(fold);
        }
    }

    protected void collapseImpl(Fold fold) {
        if (this.getShouldCollapse(fold)) {
            fold.setCollapsed(true);
        }
        for (int i = 0; i < fold.getChildCount(); ++i) {
            this.collapseImpl(fold.getChild(i));
        }
    }

    public boolean getShouldCollapse(Fold fold) {
        int type = fold.getFoldType();
        for (Integer typeToCollapse : this.typesToCollapse) {
            if (type != typeToCollapse) continue;
            return true;
        }
        return false;
    }
}

