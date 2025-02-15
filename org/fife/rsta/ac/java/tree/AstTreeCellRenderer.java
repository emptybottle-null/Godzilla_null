/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.tree;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.fife.rsta.ac.java.tree.JavaTreeNode;

class AstTreeCellRenderer
extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = 1L;

    AstTreeCellRenderer() {
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        if (value instanceof JavaTreeNode) {
            JavaTreeNode node = (JavaTreeNode)value;
            this.setText(node.getText(sel));
            this.setIcon(node.getIcon());
        }
        return this;
    }
}

