/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryElement;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNode;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

public class CategoryExplorerModel
extends DefaultTreeModel {
    private static final long serialVersionUID = -3413887384316015901L;
    protected boolean _renderFatal = true;
    protected ActionListener _listener = null;
    protected ActionEvent _event = new ActionEvent(this, 1001, "Nodes Selection changed");

    public CategoryExplorerModel(CategoryNode node) {
        super(node);
    }

    public void addLogRecord(LogRecord lr) {
        CategoryPath path = new CategoryPath(lr.getCategory());
        this.addCategory(path);
        CategoryNode node = this.getCategoryNode(path);
        node.addRecord();
        if (this._renderFatal && lr.isFatal()) {
            TreeNode[] nodes = this.getPathToRoot(node);
            int len = nodes.length;
            for (int i = 1; i < len - 1; ++i) {
                CategoryNode parent = (CategoryNode)nodes[i];
                parent.setHasFatalChildren(true);
                this.nodeChanged(parent);
            }
            node.setHasFatalRecords(true);
            this.nodeChanged(node);
        }
    }

    public CategoryNode getRootCategoryNode() {
        return (CategoryNode)this.getRoot();
    }

    public CategoryNode getCategoryNode(String category) {
        CategoryPath path = new CategoryPath(category);
        return this.getCategoryNode(path);
    }

    public CategoryNode getCategoryNode(CategoryPath path) {
        CategoryNode root;
        CategoryNode parent = root = (CategoryNode)this.getRoot();
        for (int i = 0; i < path.size(); ++i) {
            CategoryElement element = path.categoryElementAt(i);
            Enumeration children = parent.children();
            boolean categoryAlreadyExists = false;
            while (children.hasMoreElements()) {
                String pathLC;
                CategoryNode node = (CategoryNode)children.nextElement();
                String title = node.getTitle().toLowerCase();
                if (!title.equals(pathLC = element.getTitle().toLowerCase())) continue;
                categoryAlreadyExists = true;
                parent = node;
                break;
            }
            if (categoryAlreadyExists) continue;
            return null;
        }
        return parent;
    }

    public boolean isCategoryPathActive(CategoryPath path) {
        CategoryNode root;
        CategoryNode parent = root = (CategoryNode)this.getRoot();
        boolean active = false;
        for (int i = 0; i < path.size(); ++i) {
            CategoryElement element = path.categoryElementAt(i);
            Enumeration children = parent.children();
            boolean categoryAlreadyExists = false;
            active = false;
            while (children.hasMoreElements()) {
                String pathLC;
                CategoryNode node = (CategoryNode)children.nextElement();
                String title = node.getTitle().toLowerCase();
                if (!title.equals(pathLC = element.getTitle().toLowerCase())) continue;
                categoryAlreadyExists = true;
                parent = node;
                if (!parent.isSelected()) break;
                active = true;
                break;
            }
            if (active && categoryAlreadyExists) continue;
            return false;
        }
        return active;
    }

    public CategoryNode addCategory(CategoryPath path) {
        CategoryNode root;
        CategoryNode parent = root = (CategoryNode)this.getRoot();
        for (int i = 0; i < path.size(); ++i) {
            CategoryElement element = path.categoryElementAt(i);
            Enumeration children = parent.children();
            boolean categoryAlreadyExists = false;
            while (children.hasMoreElements()) {
                String pathLC;
                CategoryNode node = (CategoryNode)children.nextElement();
                String title = node.getTitle().toLowerCase();
                if (!title.equals(pathLC = element.getTitle().toLowerCase())) continue;
                categoryAlreadyExists = true;
                parent = node;
                break;
            }
            if (categoryAlreadyExists) continue;
            CategoryNode newNode = new CategoryNode(element.getTitle());
            this.insertNodeInto(newNode, parent, parent.getChildCount());
            this.refresh(newNode);
            parent = newNode;
        }
        return parent;
    }

    public void update(CategoryNode node, boolean selected) {
        if (node.isSelected() == selected) {
            return;
        }
        if (selected) {
            this.setParentSelection(node, true);
        } else {
            this.setDescendantSelection(node, false);
        }
    }

    public void setDescendantSelection(CategoryNode node, boolean selected) {
        Enumeration descendants = node.depthFirstEnumeration();
        while (descendants.hasMoreElements()) {
            CategoryNode current = (CategoryNode)descendants.nextElement();
            if (current.isSelected() == selected) continue;
            current.setSelected(selected);
            this.nodeChanged(current);
        }
        this.notifyActionListeners();
    }

    public void setParentSelection(CategoryNode node, boolean selected) {
        TreeNode[] nodes = this.getPathToRoot(node);
        int len = nodes.length;
        for (int i = 1; i < len; ++i) {
            CategoryNode parent = (CategoryNode)nodes[i];
            if (parent.isSelected() == selected) continue;
            parent.setSelected(selected);
            this.nodeChanged(parent);
        }
        this.notifyActionListeners();
    }

    public synchronized void addActionListener(ActionListener l) {
        this._listener = AWTEventMulticaster.add(this._listener, l);
    }

    public synchronized void removeActionListener(ActionListener l) {
        this._listener = AWTEventMulticaster.remove(this._listener, l);
    }

    public void resetAllNodeCounts() {
        Enumeration nodes = this.getRootCategoryNode().depthFirstEnumeration();
        while (nodes.hasMoreElements()) {
            CategoryNode current = (CategoryNode)nodes.nextElement();
            current.resetNumberOfContainedRecords();
            this.nodeChanged(current);
        }
    }

    public TreePath getTreePathToRoot(CategoryNode node) {
        if (node == null) {
            return null;
        }
        return new TreePath(this.getPathToRoot(node));
    }

    protected void notifyActionListeners() {
        if (this._listener != null) {
            this._listener.actionPerformed(this._event);
        }
    }

    protected void refresh(final CategoryNode node) {
        SwingUtilities.invokeLater(new Runnable(){

            public void run() {
                CategoryExplorerModel.this.nodeChanged(node);
            }
        });
    }
}

