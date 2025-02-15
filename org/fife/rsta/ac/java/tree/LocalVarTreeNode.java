/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.tree;

import org.fife.rsta.ac.java.IconFactory;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.tree.JavaTreeNode;
import org.fife.rsta.ac.java.tree.MemberTreeNode;
import org.fife.ui.autocomplete.Util;

class LocalVarTreeNode
extends JavaTreeNode {
    private String text;

    public LocalVarTreeNode(LocalVariable var) {
        super(var);
        this.setIcon(IconFactory.get().getIcon("localVariableIcon"));
        this.setSortPriority(4);
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append(var.getName());
        sb.append(" : ");
        sb.append("<font color='#888888'>");
        MemberTreeNode.appendType(var.getType(), sb);
        this.text = sb.toString();
    }

    @Override
    public String getText(boolean selected) {
        return selected ? Util.stripHtml(this.text).replaceAll("&lt;", "<").replaceAll("&gt;", ">") : this.text;
    }
}

