/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.FilteredLogTableModel;

public class LogTableRowRenderer
extends DefaultTableCellRenderer {
    private static final long serialVersionUID = -3951639953706443213L;
    protected boolean _highlightFatal = true;
    protected Color _color = new Color(230, 230, 230);

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        if (row % 2 == 0) {
            this.setBackground(this._color);
        } else {
            this.setBackground(Color.white);
        }
        FilteredLogTableModel model = (FilteredLogTableModel)table.getModel();
        LogRecord record = model.getFilteredRecord(row);
        this.setForeground(this.getLogLevelColor(record.getLevel()));
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    }

    protected Color getLogLevelColor(LogLevel level) {
        return (Color)LogLevel.getLogLevelColorMap().get(level);
    }
}

