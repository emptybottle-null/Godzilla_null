/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.swing;

import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import org.dom4j.Node;

public class LeafTreeNode
implements TreeNode {
    protected static final Enumeration<? extends TreeNode> EMPTY_ENUMERATION = new Enumeration(){

        @Override
        public boolean hasMoreElements() {
            return false;
        }

        public Object nextElement() {
            return null;
        }
    };
    private TreeNode parent;
    protected Node xmlNode;

    public LeafTreeNode() {
    }

    public LeafTreeNode(Node xmlNode) {
        this.xmlNode = xmlNode;
    }

    public LeafTreeNode(TreeNode parent, Node xmlNode) {
        this.parent = parent;
        this.xmlNode = xmlNode;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return EMPTY_ENUMERATION;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    public String toString() {
        String text = this.xmlNode.getText();
        return text != null ? text.trim() : "";
    }

    public void setParent(LeafTreeNode parent) {
        this.parent = parent;
    }

    public Node getXmlNode() {
        return this.xmlNode;
    }
}

