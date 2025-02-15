/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.extras.components;

import com.formdev.flatlaf.extras.components.FlatComponentExtension;
import javax.swing.JTree;

public class FlatTree
extends JTree
implements FlatComponentExtension {
    public boolean isWideSelection() {
        return this.getClientPropertyBoolean((Object)"JTree.wideSelection", "Tree.wideSelection");
    }

    public void setWideSelection(boolean wideSelection) {
        this.putClientProperty("JTree.wideSelection", wideSelection);
    }

    public boolean isPaintSelection() {
        return this.getClientPropertyBoolean((Object)"JTree.paintSelection", true);
    }

    public void setPaintSelection(boolean paintSelection) {
        this.putClientProperty("JTree.paintSelection", paintSelection);
    }
}

