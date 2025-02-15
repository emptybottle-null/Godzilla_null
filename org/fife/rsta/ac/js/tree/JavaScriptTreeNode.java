/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.tree;

import java.util.List;
import javax.swing.Icon;
import javax.swing.text.Position;
import org.fife.rsta.ac.SourceTreeNode;
import org.fife.rsta.ac.js.util.RhinoUtil;
import org.mozilla.javascript.ast.AstNode;

public class JavaScriptTreeNode
extends SourceTreeNode {
    private Position pos;
    private String text;
    private Icon icon;

    public JavaScriptTreeNode(List<AstNode> userObject) {
        super(userObject);
    }

    public JavaScriptTreeNode(AstNode userObject) {
        this(RhinoUtil.toList(userObject));
    }

    public JavaScriptTreeNode(AstNode userObject, boolean sorted) {
        super(RhinoUtil.toList(userObject), sorted);
    }

    public Icon getIcon() {
        return this.icon;
    }

    public int getLength() {
        int length = 0;
        List nodes = (List)this.getUserObject();
        for (AstNode node : nodes) {
            length += node.getLength();
        }
        return length += nodes.size() - 1;
    }

    public int getOffset() {
        return this.pos.getOffset();
    }

    public String getText(boolean selected) {
        return this.text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public void setOffset(Position offs) {
        this.pos = offs;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.getText(false);
    }
}

