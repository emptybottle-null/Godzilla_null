/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.lf5.viewer.LogTableColumnFormatException;

public class LogTableColumn
implements Serializable {
    private static final long serialVersionUID = -4275827753626456547L;
    public static final LogTableColumn DATE = new LogTableColumn("Date");
    public static final LogTableColumn THREAD = new LogTableColumn("Thread");
    public static final LogTableColumn MESSAGE_NUM = new LogTableColumn("Message #");
    public static final LogTableColumn LEVEL = new LogTableColumn("Level");
    public static final LogTableColumn NDC = new LogTableColumn("NDC");
    public static final LogTableColumn CATEGORY = new LogTableColumn("Category");
    public static final LogTableColumn MESSAGE = new LogTableColumn("Message");
    public static final LogTableColumn LOCATION = new LogTableColumn("Location");
    public static final LogTableColumn THROWN = new LogTableColumn("Thrown");
    protected String _label;
    private static LogTableColumn[] _log4JColumns = new LogTableColumn[]{DATE, THREAD, MESSAGE_NUM, LEVEL, NDC, CATEGORY, MESSAGE, LOCATION, THROWN};
    private static Map _logTableColumnMap = new HashMap();

    public LogTableColumn(String label) {
        this._label = label;
    }

    public String getLabel() {
        return this._label;
    }

    public static LogTableColumn valueOf(String column) throws LogTableColumnFormatException {
        LogTableColumn tableColumn = null;
        if (column != null) {
            column = column.trim();
            tableColumn = (LogTableColumn)_logTableColumnMap.get(column);
        }
        if (tableColumn == null) {
            StringBuffer buf = new StringBuffer();
            buf.append("Error while trying to parse (" + column + ") into");
            buf.append(" a LogTableColumn.");
            throw new LogTableColumnFormatException(buf.toString());
        }
        return tableColumn;
    }

    public boolean equals(Object o) {
        boolean equals = false;
        if (o instanceof LogTableColumn && this.getLabel() == ((LogTableColumn)o).getLabel()) {
            equals = true;
        }
        return equals;
    }

    public int hashCode() {
        return this._label.hashCode();
    }

    public String toString() {
        return this._label;
    }

    public static List getLogTableColumns() {
        return Arrays.asList(_log4JColumns);
    }

    public static LogTableColumn[] getLogTableColumnArray() {
        return _log4JColumns;
    }

    static {
        for (int i = 0; i < _log4JColumns.length; ++i) {
            _logTableColumnMap.put(_log4JColumns[i].getLabel(), _log4JColumns[i]);
        }
    }
}

