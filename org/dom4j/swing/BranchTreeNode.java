/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.swing;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;
import org.dom4j.Branch;
import org.dom4j.CharacterData;
import org.dom4j.Node;
import org.dom4j.swing.LeafTreeNode;

public class BranchTreeNode
extends LeafTreeNode {
    protected List<TreeNode> children;

    public BranchTreeNode() {
    }

    public BranchTreeNode(Branch xmlNode) {
        super(xmlNode);
    }

    public BranchTreeNode(TreeNode parent, Branch xmlNode) {
        super(parent, xmlNode);
    }

    @Override
    public Enumeration<TreeNode> children() {
        return new Enumeration<TreeNode>(){
            private int index = -1;

            @Override
            public boolean hasMoreElements() {
                return this.index + 1 < BranchTreeNode.this.getChildCount();
            }

            @Override
            public TreeNode nextElement() {
                return BranchTreeNode.this.getChildAt(++this.index);
            }
        };
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return this.getChildList().get(childIndex);
    }

    @Override
    public int getChildCount() {
        return this.getChildList().size();
    }

    @Override
    public int getIndex(TreeNode node) {
        return this.getChildList().indexOf(node);
    }

    @Override
    public boolean isLeaf() {
        return this.getXmlBranch().nodeCount() <= 0;
    }

    @Override
    public String toString() {
        return this.xmlNode.getName();
    }

    protected List<TreeNode> getChildList() {
        if (this.children == null) {
            this.children = this.createChildList();
        }
        return this.children;
    }

    protected List<TreeNode> createChildList() {
        Branch branch = this.getXmlBranch();
        int size = branch.nodeCount();
        ArrayList<TreeNode> childList = new ArrayList<TreeNode>(size);
        for (int i = 0; i < size; ++i) {
            String text;
            Node node = branch.node(i);
            if (node instanceof CharacterData && ((text = node.getText()) == null || (text = text.trim()).length() <= 0)) continue;
            childList.add(this.createChildTreeNode(node));
        }
        return childList;
    }

    protected TreeNode createChildTreeNode(Node xmlNode) {
        if (xmlNode instanceof Branch) {
            return new BranchTreeNode((TreeNode)this, (Branch)xmlNode);
        }
        return new LeafTreeNode(this, xmlNode);
    }

    protected Branch getXmlBranch() {
        return (Branch)this.xmlNode;
    }
}

