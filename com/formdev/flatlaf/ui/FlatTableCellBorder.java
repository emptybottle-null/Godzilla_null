/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatLineBorder;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FlatTableCellBorder
extends FlatLineBorder {
    final boolean showCellFocusIndicator = UIManager.getBoolean("Table.showCellFocusIndicator");

    protected FlatTableCellBorder() {
        super(UIManager.getInsets("Table.cellMargins"), UIManager.getColor("Table.cellFocusColor"));
    }

    public static class Selected
    extends FlatTableCellBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JTable table;
            if (!this.showCellFocusIndicator && (table = (JTable)SwingUtilities.getAncestorOfClass(JTable.class, c)) != null && !this.isSelectionEditable(table)) {
                return;
            }
            super.paintBorder(c, g, x, y, width, height);
        }

        protected boolean isSelectionEditable(JTable table) {
            if (table.getRowSelectionAllowed()) {
                int[] selectedRows;
                int columnCount = table.getColumnCount();
                for (int selectedRow : selectedRows = table.getSelectedRows()) {
                    for (int column = 0; column < columnCount; ++column) {
                        if (!table.isCellEditable(selectedRow, column)) continue;
                        return true;
                    }
                }
            }
            if (table.getColumnSelectionAllowed()) {
                int rowCount = table.getRowCount();
                int[] selectedColumns = table.getSelectedColumns();
                for (int selectedColumn : selectedColumns) {
                    for (int row = 0; row < rowCount; ++row) {
                        if (!table.isCellEditable(row, selectedColumn)) continue;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class Focused
    extends FlatTableCellBorder {
    }

    public static class Default
    extends FlatTableCellBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        }
    }
}

