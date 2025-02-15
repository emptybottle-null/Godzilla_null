/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.tools.debugger;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.mozilla.javascript.tools.debugger.VariableModel;
import org.mozilla.javascript.tools.debugger.treetable.JTreeTable;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModel;
import org.mozilla.javascript.tools.debugger.treetable.TreeTableModelAdapter;

class MyTreeTable
extends JTreeTable {
    private static final long serialVersionUID = 3457265548184453049L;

    public MyTreeTable(VariableModel model) {
        super(model);
    }

    public JTree resetTree(TreeTableModel treeTableModel) {
        this.tree = new JTreeTable.TreeTableCellRenderer(this, treeTableModel);
        super.setModel(new TreeTableModelAdapter(treeTableModel, this.tree));
        JTreeTable.ListToTreeSelectionModelWrapper selectionWrapper = new JTreeTable.ListToTreeSelectionModelWrapper(this);
        this.tree.setSelectionModel(selectionWrapper);
        this.setSelectionModel(selectionWrapper.getListSelectionModel());
        if (this.tree.getRowHeight() < 1) {
            this.setRowHeight(18);
        }
        this.setDefaultRenderer(TreeTableModel.class, this.tree);
        this.setDefaultEditor(TreeTableModel.class, new JTreeTable.TreeTableCellEditor(this));
        this.setShowGrid(true);
        this.setIntercellSpacing(new Dimension(1, 1));
        this.tree.setRootVisible(false);
        this.tree.setShowsRootHandles(true);
        DefaultTreeCellRenderer r = (DefaultTreeCellRenderer)this.tree.getCellRenderer();
        r.setOpenIcon(null);
        r.setClosedIcon(null);
        r.setLeafIcon(null);
        return this.tree;
    }

    public boolean isCellEditable(EventObject e) {
        if (e instanceof MouseEvent) {
            MouseEvent me = (MouseEvent)e;
            if (me.getModifiers() == 0 || (me.getModifiers() & 0x410) != 0 && (me.getModifiers() & 0x1ACF) == 0) {
                int row = this.rowAtPoint(me.getPoint());
                for (int counter = this.getColumnCount() - 1; counter >= 0; --counter) {
                    if (TreeTableModel.class != this.getColumnClass(counter)) continue;
                    MouseEvent newME = new MouseEvent(this.tree, me.getID(), me.getWhen(), me.getModifiers(), me.getX() - this.getCellRect((int)row, (int)counter, (boolean)true).x, me.getY(), me.getClickCount(), me.isPopupTrigger());
                    this.tree.dispatchEvent(newME);
                    break;
                }
            }
            return me.getClickCount() >= 3;
        }
        return e == null;
    }
}

