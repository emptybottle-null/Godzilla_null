/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import org.fife.ui.autocomplete.Util;

public class SourceTreeNode
extends DefaultMutableTreeNode
implements Comparable<SourceTreeNode> {
    private boolean sortable;
    private boolean sorted;
    private Pattern pattern;
    private List<TreeNode> visibleChildren = new ArrayList<TreeNode>();
    private int sortPriority;

    public SourceTreeNode(Object userObject) {
        this(userObject, false);
    }

    public SourceTreeNode(Object userObject, boolean sorted) {
        super(userObject);
        this.setSortable(true);
        this.setSorted(sorted);
    }

    @Override
    public void add(MutableTreeNode child) {
        if (child != null && child.getParent() == this) {
            this.insert(child, super.getChildCount() - 1);
        } else {
            this.insert(child, super.getChildCount());
        }
        if (this.sortable && this.sorted) {
            this.refreshVisibleChildren();
        }
    }

    @Override
    public Enumeration<TreeNode> children() {
        return Collections.enumeration(this.visibleChildren);
    }

    @Override
    public Object clone() {
        SourceTreeNode node = (SourceTreeNode)super.clone();
        node.visibleChildren = new ArrayList<TreeNode>();
        return node;
    }

    public SourceTreeNode cloneWithChildren() {
        SourceTreeNode clone = (SourceTreeNode)this.clone();
        for (int i = 0; i < super.getChildCount(); ++i) {
            clone.add(((SourceTreeNode)super.getChildAt(i)).cloneWithChildren());
        }
        return clone;
    }

    @Override
    public int compareTo(SourceTreeNode stn2) {
        int res = -1;
        if (stn2 != null && (res = this.getSortPriority() - stn2.getSortPriority()) == 0 && ((SourceTreeNode)this.getParent()).isSorted()) {
            res = this.toString().compareToIgnoreCase(stn2.toString());
        }
        return res;
    }

    protected void filter(Pattern pattern) {
        this.pattern = pattern;
        this.refreshVisibleChildren();
        for (int i = 0; i < super.getChildCount(); ++i) {
            Object child = this.children.get(i);
            if (!(child instanceof SourceTreeNode)) continue;
            ((SourceTreeNode)child).filter(pattern);
        }
    }

    @Override
    public TreeNode getChildAfter(TreeNode child) {
        if (child == null) {
            throw new IllegalArgumentException("child cannot be null");
        }
        int index = this.getIndex(child);
        if (index == -1) {
            throw new IllegalArgumentException("child node not contained");
        }
        return index < this.getChildCount() - 1 ? this.getChildAt(index + 1) : null;
    }

    @Override
    public TreeNode getChildAt(int index) {
        return this.visibleChildren.get(index);
    }

    @Override
    public TreeNode getChildBefore(TreeNode child) {
        if (child == null) {
            throw new IllegalArgumentException("child cannot be null");
        }
        int index = this.getIndex(child);
        if (index == -1) {
            throw new IllegalArgumentException("child node not contained");
        }
        return index > 0 ? this.getChildAt(index - 1) : null;
    }

    @Override
    public int getChildCount() {
        return this.visibleChildren.size();
    }

    @Override
    public int getIndex(TreeNode child) {
        if (child == null) {
            throw new IllegalArgumentException("child cannot be null");
        }
        for (int i = 0; i < this.visibleChildren.size(); ++i) {
            TreeNode node = this.visibleChildren.get(i);
            if (!node.equals(child)) continue;
            return i;
        }
        return -1;
    }

    public int getSortPriority() {
        return this.sortPriority;
    }

    public boolean isSortable() {
        return this.sortable;
    }

    public boolean isSorted() {
        return this.sorted;
    }

    public void refresh() {
        this.refreshVisibleChildren();
        for (int i = 0; i < this.getChildCount(); ++i) {
            TreeNode child = this.getChildAt(i);
            if (!(child instanceof SourceTreeNode)) continue;
            ((SourceTreeNode)child).refresh();
        }
    }

    private void refreshVisibleChildren() {
        this.visibleChildren.clear();
        if (this.children != null) {
            this.visibleChildren.addAll(this.children);
            if (this.sortable && this.sorted) {
                this.visibleChildren.sort(null);
            }
            if (this.pattern != null) {
                Iterator<TreeNode> i = this.visibleChildren.iterator();
                while (i.hasNext()) {
                    TreeNode node = i.next();
                    if (!node.isLeaf()) continue;
                    String text = node.toString();
                    if (this.pattern.matcher(text = Util.stripHtml(text)).find()) continue;
                    i.remove();
                }
            }
        }
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public void setSorted(boolean sorted) {
        if (sorted != this.sorted) {
            this.sorted = sorted;
            if (this.sortable) {
                this.refreshVisibleChildren();
            }
            for (int i = 0; i < super.getChildCount(); ++i) {
                Object child = this.children.get(i);
                if (!(child instanceof SourceTreeNode)) continue;
                ((SourceTreeNode)child).setSorted(sorted);
            }
        }
    }

    public void setSortPriority(int priority) {
        this.sortPriority = priority;
    }
}

