/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;

public class CategoryAbstractCellEditor
implements TableCellEditor,
TreeCellEditor {
    protected EventListenerList _listenerList = new EventListenerList();
    protected Object _value;
    protected ChangeEvent _changeEvent = null;
    protected int _clickCountToStart = 1;

    public Object getCellEditorValue() {
        return this._value;
    }

    public void setCellEditorValue(Object value) {
        this._value = value;
    }

    public void setClickCountToStart(int count) {
        this._clickCountToStart = count;
    }

    public int getClickCountToStart() {
        return this._clickCountToStart;
    }

    public boolean isCellEditable(EventObject anEvent) {
        return !(anEvent instanceof MouseEvent) || ((MouseEvent)anEvent).getClickCount() >= this._clickCountToStart;
    }

    public boolean shouldSelectCell(EventObject anEvent) {
        return this.isCellEditable(anEvent) && (anEvent == null || ((MouseEvent)anEvent).getClickCount() >= this._clickCountToStart);
    }

    public boolean stopCellEditing() {
        this.fireEditingStopped();
        return true;
    }

    public void cancelCellEditing() {
        this.fireEditingCanceled();
    }

    public void addCellEditorListener(CellEditorListener l) {
        this._listenerList.add(CellEditorListener.class, l);
    }

    public void removeCellEditorListener(CellEditorListener l) {
        this._listenerList.remove(CellEditorListener.class, l);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        return null;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return null;
    }

    protected void fireEditingStopped() {
        Object[] listeners = this._listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] != (class$javax$swing$event$CellEditorListener == null ? CategoryAbstractCellEditor.class$("javax.swing.event.CellEditorListener") : class$javax$swing$event$CellEditorListener)) continue;
            if (this._changeEvent == null) {
                this._changeEvent = new ChangeEvent(this);
            }
            ((CellEditorListener)listeners[i + 1]).editingStopped(this._changeEvent);
        }
    }

    protected void fireEditingCanceled() {
        Object[] listeners = this._listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] != (class$javax$swing$event$CellEditorListener == null ? CategoryAbstractCellEditor.class$("javax.swing.event.CellEditorListener") : class$javax$swing$event$CellEditorListener)) continue;
            if (this._changeEvent == null) {
                this._changeEvent = new ChangeEvent(this);
            }
            ((CellEditorListener)listeners[i + 1]).editingCanceled(this._changeEvent);
        }
    }
}

