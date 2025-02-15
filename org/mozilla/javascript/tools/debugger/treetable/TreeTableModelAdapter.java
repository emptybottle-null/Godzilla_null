/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger.treetable;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModel;

public class TreeTableModelAdapter
extends AbstractTableModel {
    private static final long serialVersionUID = 48741114609209052L;
    JTree tree;
    TreeTableModel treeTableModel;

    public TreeTableModelAdapter(TreeTableModel treeTableModel, JTree tree) {
        this.tree = tree;
        this.treeTableModel = treeTableModel;
        tree.addTreeExpansionListener(new TreeExpansionListener(){

            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                TreeTableModelAdapter.this.fireTableDataChanged();
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                TreeTableModelAdapter.this.fireTableDataChanged();
            }
        });
        treeTableModel.addTreeModelListener(new TreeModelListener(){

            @Override
            public void treeNodesChanged(TreeModelEvent e) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }

            @Override
            public void treeStructureChanged(TreeModelEvent e) {
                TreeTableModelAdapter.this.delayedFireTableDataChanged();
            }
        });
    }

    @Override
    public int getColumnCount() {
        return this.treeTableModel.getColumnCount();
    }

    @Override
    public String getColumnName(int column) {
        return this.treeTableModel.getColumnName(column);
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return this.treeTableModel.getColumnClass(column);
    }

    @Override
    public int getRowCount() {
        return this.tree.getRowCount();
    }

    protected Object nodeForRow(int row) {
        TreePath treePath = this.tree.getPathForRow(row);
        return treePath.getLastPathComponent();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return this.treeTableModel.getValueAt(this.nodeForRow(row), column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return this.treeTableModel.isCellEditable(this.nodeForRow(row), column);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        this.treeTableModel.setValueAt(value, this.nodeForRow(row), column);
    }

    protected void delayedFireTableDataChanged() {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                TreeTableModelAdapter.this.fireTableDataChanged();
            }
        });
    }
}

