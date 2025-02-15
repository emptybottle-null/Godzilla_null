/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.apache.log4j.lf5.viewer.FilteredLogTableModel;
import org.apache.log4j.lf5.viewer.LogTableColumn;
import org.apache.log4j.lf5.viewer.LogTableRowRenderer;

public class LogTable
extends JTable {
    private static final long serialVersionUID = 4867085140195148458L;
    protected int _rowHeight = 30;
    protected JTextArea _detailTextArea;
    protected int _numCols = 9;
    protected TableColumn[] _tableColumns = new TableColumn[this._numCols];
    protected int[] _colWidths = new int[]{40, 40, 40, 70, 70, 360, 440, 200, 60};
    protected LogTableColumn[] _colNames = LogTableColumn.getLogTableColumnArray();
    protected int _colDate = 0;
    protected int _colThread = 1;
    protected int _colMessageNum = 2;
    protected int _colLevel = 3;
    protected int _colNDC = 4;
    protected int _colCategory = 5;
    protected int _colMessage = 6;
    protected int _colLocation = 7;
    protected int _colThrown = 8;
    protected DateFormatManager _dateFormatManager = null;

    public LogTable(JTextArea detailTextArea) {
        this.init();
        this._detailTextArea = detailTextArea;
        this.setModel(new FilteredLogTableModel());
        Enumeration<TableColumn> columns = this.getColumnModel().getColumns();
        int i = 0;
        while (columns.hasMoreElements()) {
            TableColumn col = columns.nextElement();
            col.setCellRenderer(new LogTableRowRenderer());
            col.setPreferredWidth(this._colWidths[i]);
            this._tableColumns[i] = col;
            ++i;
        }
        ListSelectionModel rowSM = this.getSelectionModel();
        rowSM.addListSelectionListener(new LogTableListSelectionListener(this));
    }

    public DateFormatManager getDateFormatManager() {
        return this._dateFormatManager;
    }

    public void setDateFormatManager(DateFormatManager dfm) {
        this._dateFormatManager = dfm;
    }

    public synchronized void clearLogRecords() {
        this.getFilteredLogTableModel().clear();
    }

    public FilteredLogTableModel getFilteredLogTableModel() {
        return (FilteredLogTableModel)this.getModel();
    }

    public void setDetailedView() {
        TableColumnModel model = this.getColumnModel();
        for (int f = 0; f < this._numCols; ++f) {
            model.removeColumn(this._tableColumns[f]);
        }
        for (int i = 0; i < this._numCols; ++i) {
            model.addColumn(this._tableColumns[i]);
        }
        this.sizeColumnsToFit(-1);
    }

    public void setView(List columns) {
        TableColumnModel model = this.getColumnModel();
        for (int f = 0; f < this._numCols; ++f) {
            model.removeColumn(this._tableColumns[f]);
        }
        Iterator selectedColumns = columns.iterator();
        Vector columnNameAndNumber = this.getColumnNameAndNumber();
        while (selectedColumns.hasNext()) {
            model.addColumn(this._tableColumns[columnNameAndNumber.indexOf(selectedColumns.next())]);
        }
        this.sizeColumnsToFit(-1);
    }

    public void setFont(Font font) {
        super.setFont(font);
        Graphics g = this.getGraphics();
        if (g != null) {
            FontMetrics fm = g.getFontMetrics(font);
            int height = fm.getHeight();
            this._rowHeight = height + height / 3;
            this.setRowHeight(this._rowHeight);
        }
    }

    protected void init() {
        this.setRowHeight(this._rowHeight);
        this.setSelectionMode(0);
    }

    protected Vector getColumnNameAndNumber() {
        Vector<LogTableColumn> columnNameAndNumber = new Vector<LogTableColumn>();
        for (int i = 0; i < this._colNames.length; ++i) {
            columnNameAndNumber.add(i, this._colNames[i]);
        }
        return columnNameAndNumber;
    }

    class LogTableListSelectionListener
    implements ListSelectionListener {
        protected JTable _table;

        public LogTableListSelectionListener(JTable table) {
            this._table = table;
        }

        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (!lsm.isSelectionEmpty()) {
                StringBuffer buf = new StringBuffer();
                int selectedRow = lsm.getMinSelectionIndex();
                for (int i = 0; i < LogTable.this._numCols - 1; ++i) {
                    String value = "";
                    Object obj = this._table.getModel().getValueAt(selectedRow, i);
                    if (obj != null) {
                        value = obj.toString();
                    }
                    buf.append(LogTable.this._colNames[i] + ":");
                    buf.append("\t");
                    if (i == LogTable.this._colThread || i == LogTable.this._colMessage || i == LogTable.this._colLevel) {
                        buf.append("\t");
                    }
                    if (i == LogTable.this._colDate || i == LogTable.this._colNDC) {
                        buf.append("\t\t");
                    }
                    buf.append(value);
                    buf.append("\n");
                }
                buf.append(LogTable.this._colNames[LogTable.this._numCols - 1] + ":\n");
                Object obj = this._table.getModel().getValueAt(selectedRow, LogTable.this._numCols - 1);
                if (obj != null) {
                    buf.append(obj.toString());
                }
                LogTable.this._detailTextArea.setText(buf.toString());
            }
        }
    }
}

