/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeRenderer;

public class CategoryNodeEditorRenderer
extends CategoryNodeRenderer {
    private static final long serialVersionUID = -6094804684259929574L;

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component c = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        return c;
    }

    public JCheckBox getCheckBox() {
        return this._checkBox;
    }
}

