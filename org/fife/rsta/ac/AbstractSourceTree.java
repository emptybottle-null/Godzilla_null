/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac;

import java.util.Enumeration;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.fife.rsta.ac.SourceTreeNode;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;

public abstract class AbstractSourceTree
extends JTree {
    protected RSyntaxTextArea textArea;
    private boolean sorted;
    private Pattern pattern;
    private boolean gotoSelectedElementOnClick;
    private boolean showMajorElementsOnly;

    public AbstractSourceTree() {
        this.getSelectionModel().setSelectionMode(1);
        this.gotoSelectedElementOnClick = true;
        this.showMajorElementsOnly = false;
    }

    public abstract void expandInitialNodes();

    protected boolean fastExpandAll(TreePath parent, boolean expand) {
        TreeExpansionListener[] listeners;
        for (TreeExpansionListener listener : listeners = this.getTreeExpansionListeners()) {
            this.removeTreeExpansionListener(listener);
        }
        boolean result = this.fastExpandAllImpl(parent, expand);
        for (TreeExpansionListener listener : listeners) {
            this.addTreeExpansionListener(listener);
        }
        this.collapsePath(parent);
        this.expandPath(parent);
        return result;
    }

    private boolean fastExpandAllImpl(TreePath parent, boolean expand) {
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        if (node.getChildCount() > 0) {
            boolean childExpandCalled = false;
            Enumeration e = node.children();
            while (e.hasMoreElements()) {
                TreeNode n = (TreeNode)e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                childExpandCalled = this.fastExpandAllImpl(path, expand) || childExpandCalled;
            }
            if (!childExpandCalled) {
                if (expand) {
                    this.expandPath(parent);
                } else {
                    this.collapsePath(parent);
                }
            }
            return true;
        }
        return false;
    }

    public void filter(String pattern) {
        if (pattern == null && this.pattern != null || pattern != null && this.pattern == null || pattern != null && !pattern.equals(this.pattern.pattern())) {
            this.pattern = pattern == null || pattern.length() == 0 ? null : RSyntaxUtilities.wildcardToPattern("^" + pattern, false, false);
            Object root = this.getModel().getRoot();
            if (root instanceof SourceTreeNode) {
                ((SourceTreeNode)root).filter(this.pattern);
            }
            ((DefaultTreeModel)this.getModel()).reload();
            this.expandInitialNodes();
        }
    }

    public boolean getGotoSelectedElementOnClick() {
        return this.gotoSelectedElementOnClick;
    }

    public boolean getShowMajorElementsOnly() {
        return this.showMajorElementsOnly;
    }

    public abstract boolean gotoSelectedElement();

    public boolean isSorted() {
        return this.sorted;
    }

    public abstract void listenTo(RSyntaxTextArea var1);

    public void refresh() {
        DefaultTreeModel model = (DefaultTreeModel)this.getModel();
        Object root = model.getRoot();
        if (root instanceof SourceTreeNode) {
            SourceTreeNode node = (SourceTreeNode)root;
            node.refresh();
            model.reload();
            this.expandInitialNodes();
        }
    }

    public void selectFirstNodeMatchingFilter() {
        if (this.pattern == null) {
            return;
        }
        DefaultTreeModel model = (DefaultTreeModel)this.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        Enumeration en = root.depthFirstEnumeration();
        while (en.hasMoreElements()) {
            SourceTreeNode stn = (SourceTreeNode)en.nextElement();
            JLabel renderer = (JLabel)this.getCellRenderer().getTreeCellRendererComponent(this, stn, true, true, stn.isLeaf(), 0, true);
            String text = renderer.getText();
            if (text == null || !this.pattern.matcher(text).find()) continue;
            this.setSelectionPath(new TreePath(model.getPathToRoot(stn)));
            return;
        }
    }

    public void selectNextVisibleRow() {
        int currentRow = this.getLeadSelectionRow();
        if (++currentRow < this.getRowCount()) {
            TreePath path = this.getPathForRow(currentRow);
            this.setSelectionPath(path);
            this.scrollPathToVisible(path);
        }
    }

    public void selectPreviousVisibleRow() {
        int currentRow = this.getLeadSelectionRow();
        if (--currentRow >= 0) {
            TreePath path = this.getPathForRow(currentRow);
            this.setSelectionPath(path);
            this.scrollPathToVisible(path);
        }
    }

    public void setGotoSelectedElementOnClick(boolean gotoSelectedElement) {
        this.gotoSelectedElementOnClick = gotoSelectedElement;
    }

    public void setShowMajorElementsOnly(boolean show) {
        this.showMajorElementsOnly = show;
    }

    public void setSorted(boolean sorted) {
        if (this.sorted != sorted) {
            this.sorted = sorted;
            Object root = this.getModel().getRoot();
            if (root instanceof SourceTreeNode) {
                ((SourceTreeNode)root).setSorted(sorted);
            }
            ((DefaultTreeModel)this.getModel()).reload();
            this.expandInitialNodes();
        }
    }

    public abstract void uninstall();
}

