/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.EasyI18N;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import core.ui.component.listener.ActionDblClick;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.MenuElement;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import util.Log;
import util.automaticBindClick;
import util.functions;

public class DataView
extends JTable {
    private static final long serialVersionUID = -8531006713898868252L;
    private JPopupMenu rightClickMenu;
    private RightClickEvent rightClickEvent;
    private final int imgColumn;
    private TableRowSorter sorter;
    private String lastFiter = "*";
    private Vector columnNameVector;
    private DefaultTableModel model;

    private void initJtableConfig() {
        this.rightClickEvent = new RightClickEvent(this.rightClickMenu, this);
        this.addMouseListener(this.rightClickEvent);
        this.setSelectionMode(0);
        this.setAutoCreateRowSorter(true);
        this.setRowHeight(25);
        this.rightClickMenu = new JPopupMenu();
        JMenuItem copyselectItem = new JMenuItem("\u590d\u5236\u9009\u4e2d");
        copyselectItem.setActionCommand("copySelected");
        JMenuItem copyselectedLineItem = new JMenuItem("\u590d\u5236\u9009\u4e2d\u884c");
        copyselectedLineItem.setActionCommand("copyselectedLine");
        JMenuItem exportAllItem = new JMenuItem("\u5bfc\u51fa");
        exportAllItem.setActionCommand("exportData");
        this.rightClickMenu.add(copyselectItem);
        this.rightClickMenu.add(copyselectedLineItem);
        this.rightClickMenu.add(exportAllItem);
        this.setRightClickMenu(this.rightClickMenu);
        this.sorter = new TableRowSorter<TableModel>(this.dataModel);
        this.setRowSorter(this.sorter);
        automaticBindClick.bindMenuItemClick(this.rightClickMenu, null, this);
        this.addActionForKey("ctrl pressed F", new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DataView.this.ctrlPassF(e);
            }
        });
    }

    public DataView() {
        this(new Vector(), new Vector(), -1, -1);
    }

    public DataView(Vector rowData, Vector columnNames, int imgColumn, int imgMaxWidth) {
        super(rowData, columnNames);
        if (columnNames == null) {
            columnNames = new Vector();
        }
        if (rowData == null) {
            rowData = new Vector();
        }
        this.getModel().setDataVector(rowData, columnNames);
        this.columnNameVector = columnNames;
        this.imgColumn = imgColumn;
        if (imgColumn >= 0) {
            this.getColumnModel().getColumn(0).setMaxWidth(imgMaxWidth);
        }
        this.initJtableConfig();
        EasyI18N.installObject(this);
    }

    public void ctrlPassF(ActionEvent e) {
        Object filterObject = GOptionPane.showInputDialog(null, "input filter", "input filter", 3, null, null, this.lastFiter);
        if (filterObject != null) {
            String fiter;
            this.lastFiter = fiter = filterObject.toString();
            if (fiter.isEmpty()) {
                this.sorter.setRowFilter(null);
            } else {
                this.sorter.setRowFilter(new RowFilter(){

                    public boolean include(RowFilter.Entry entry) {
                        int count = entry.getValueCount();
                        for (int i = 0; i < count; ++i) {
                            if (!functions.isMatch(entry.getStringValue(i), DataView.this.lastFiter, false)) continue;
                            return true;
                        }
                        return false;
                    }
                });
            }
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9", new Object[0]);
        }
    }

    public void setActionDblClick(ActionDblClick actionDblClick) {
        if (this.rightClickEvent != null) {
            this.rightClickEvent.setActionListener(actionDblClick);
        }
    }

    public JPopupMenu getRightClickMenu() {
        return this.rightClickMenu;
    }

    public void addActionForKeyStroke(KeyStroke keyStroke, Action action) {
        this.getActionMap().put(keyStroke.toString(), action);
        this.getInputMap().put(keyStroke, keyStroke.toString());
    }

    public void addActionForKey(String keyString, Action action) {
        this.addActionForKeyStroke(KeyStroke.getKeyStroke(keyString), action);
    }

    public void RemoveALL() {
        DefaultTableModel defaultTableModel = this.getModel();
        while (defaultTableModel.getRowCount() > 0) {
            defaultTableModel.removeRow(0);
        }
        this.updateUI();
    }

    public TableRowSorter getSorter() {
        return this.sorter;
    }

    public void setSorter(TableRowSorter sorter) {
        this.sorter = sorter;
    }

    public Class getColumnClass(int column) {
        return column == this.imgColumn ? Icon.class : Object.class;
    }

    public Vector GetSelectRow() {
        int select_row_id = this.getSelectedRow();
        if (select_row_id != -1) {
            int column_num = this.getColumnCount();
            Vector<Object> vector = new Vector<Object>();
            for (int i = 0; i < column_num; ++i) {
                vector.add(this.getValueAt(select_row_id, i));
            }
            return vector;
        }
        return null;
    }

    public Vector getColumnVector() {
        return this.columnNameVector;
    }

    public String[] GetSelectRow1() {
        int select_row_id = this.getSelectedRow();
        if (select_row_id != -1) {
            int column_num = this.getColumnCount();
            String[] select_row_columns = new String[column_num];
            for (int i = 0; i < column_num; ++i) {
                Object value = this.getValueAt(select_row_id, i);
                if (value instanceof String) {
                    select_row_columns[i] = (String)value;
                    continue;
                }
                if (value != null) {
                    try {
                        select_row_columns[i] = value.toString();
                    } catch (Exception e) {
                        select_row_columns[i] = "null";
                        Log.error(e);
                    }
                    continue;
                }
                select_row_columns[i] = "null";
            }
            return select_row_columns;
        }
        return null;
    }

    @Override
    public DefaultTableModel getModel() {
        if (this.dataModel != null) {
            return (DefaultTableModel)this.dataModel;
        }
        return null;
    }

    public synchronized void AddRow(Object object) {
        Class<?> class1 = object.getClass();
        Field[] fields = class1.getFields();
        String field_name = null;
        String field_value = null;
        DefaultTableModel tableModel = this.getModel();
        Vector<String> rowVector = new Vector<String>(tableModel.getColumnCount());
        Object[] columns = new String[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); ++i) {
            columns[i] = tableModel.getColumnName(i).toUpperCase();
            rowVector.add("NULL");
        }
        for (Field field : fields) {
            field_name = field.getName();
            int find_id = Arrays.binarySearch(columns, field_name.substring(2).toUpperCase());
            if (!field_name.startsWith("s_") || find_id == -1) continue;
            try {
                field_value = field.get(object) instanceof String ? (String)field.get(object) : "NULL";
            } catch (Exception e) {
                field_value = "NULL";
            }
            rowVector.set(find_id, field_value);
        }
        tableModel.addRow(rowVector);
    }

    public synchronized void AddRow(Vector one_row) {
        DefaultTableModel tableModel = this.getModel();
        tableModel.addRow(one_row);
    }

    public synchronized Vector<Vector> getDataVector() {
        this.sorter.setRowFilter(null);
        return this.getModel().getDataVector();
    }

    public synchronized void AddRows(Vector rows) {
        this.sorter.setRowFilter(null);
        DefaultTableModel tableModel = this.getModel();
        Vector columnVector = this.getColumnVector();
        tableModel.setDataVector(rows, columnVector);
    }

    public synchronized void SetRow(int row_id, Object object) {
        Class<?> class1 = object.getClass();
        Field[] fields = class1.getFields();
        String field_name = null;
        String field_value = null;
        DefaultTableModel tableModel = this.getModel();
        Vector rowVector = (Vector)tableModel.getDataVector().get(row_id);
        Object[] columns = new String[tableModel.getColumnCount()];
        for (int i = 0; i < tableModel.getColumnCount(); ++i) {
            columns[i] = tableModel.getColumnName(i).toUpperCase();
        }
        for (Field field : fields) {
            field_name = field.getName();
            int find_id = Arrays.binarySearch(columns, field_name.substring(2).toUpperCase());
            if (!field_name.startsWith("s_") || find_id == -1) continue;
            try {
                field_value = field.get(object) instanceof String ? (String)field.get(object) : "NULL";
            } catch (Exception e) {
                field_value = "NULL";
            }
            rowVector.set(find_id, field_value);
        }
    }

    public void setRightClickMenu(JPopupMenu rightClickMenu) {
        this.setRightClickMenu(rightClickMenu, false);
    }

    public void setRightClickMenu(JPopupMenu rightClickMenu, boolean append) {
        if (append) {
            for (MenuElement c : this.rightClickMenu.getSubElements()) {
                rightClickMenu.add(c.getComponent());
            }
        }
        this.rightClickMenu = rightClickMenu;
        this.rightClickEvent.setRightClickMenu(rightClickMenu);
    }

    @Override
    public JTableHeader getTableHeader() {
        JTableHeader tableHeader = super.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        DefaultTableCellRenderer hr = (DefaultTableCellRenderer)tableHeader.getDefaultRenderer();
        hr.setHorizontalAlignment(0);
        return tableHeader;
    }

    public void addColumn(Object column) {
        this.getModel().addColumn(column);
    }

    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        DefaultTableCellRenderer cr = (DefaultTableCellRenderer)super.getDefaultRenderer(columnClass);
        cr.setHorizontalAlignment(0);
        return cr;
    }

    @Override
    public boolean isCellEditable(int paramInt1, int paramInt2) {
        return false;
    }

    private void copySelectedMenuItemClick(ActionEvent e) {
        int columnIndex = this.getSelectedColumn();
        if (columnIndex != -1) {
            Object o = this.getValueAt(this.getSelectedRow(), this.getSelectedColumn());
            if (o != null) {
                String value = (String)o;
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
                GOptionPane.showMessageDialog(null, "\u590d\u5236\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(null, "\u9009\u4e2d\u5217\u662f\u7a7a\u7684", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(null, "\u672a\u9009\u4e2d\u5217", "\u63d0\u793a", 2);
        }
    }

    private void copyselectedLineMenuItemClick(ActionEvent e) {
        int columnIndex = this.getSelectedColumn();
        if (columnIndex != -1) {
            Object[] o = this.GetSelectRow1();
            if (o != null) {
                String value = Arrays.toString(o);
                this.GetSelectRow1();
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
                GOptionPane.showMessageDialog(null, "\u590d\u5236\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(null, "\u9009\u4e2d\u5217\u662f\u7a7a\u7684", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(null, "\u672a\u9009\u4e2d\u5217", "\u63d0\u793a", 2);
        }
    }

    private void exportDataMenuItemClick(ActionEvent e) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        chooser.setFileFilter(new FileNameExtensionFilter("*.csv", "csv"));
        boolean flag = 0 == chooser.showDialog(null, "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            String fileString = selectdFile.getAbsolutePath();
            if (!fileString.endsWith(".csv")) {
                fileString = fileString + ".csv";
            }
            if (functions.saveDataViewToCsv(this.getColumnVector(), this.getModel().getDataVector(), fileString)) {
                GOptionPane.showMessageDialog(null, "\u5bfc\u51fa\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(null, "\u5bfc\u51fa\u5931\u8d25", "\u63d0\u793a", 1);
            }
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9......", new Object[0]);
        }
    }

    private class RightClickEvent
    extends MouseAdapter {
        private JPopupMenu rightClickMenu;
        private final DataView dataView;
        private ActionDblClick actionDblClick;

        public RightClickEvent(JPopupMenu rightClickMenu, DataView jtable) {
            this.rightClickMenu = rightClickMenu;
            this.dataView = jtable;
        }

        public void setRightClickMenu(JPopupMenu rightClickMenu) {
            this.rightClickMenu = rightClickMenu;
        }

        public void setActionListener(ActionDblClick event) {
            this.actionDblClick = event;
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getButton() == 3) {
                int i;
                if (this.rightClickMenu != null && (i = this.dataView.rowAtPoint(mouseEvent.getPoint())) != -1) {
                    this.rightClickMenu.show(this.dataView, mouseEvent.getX(), mouseEvent.getY());
                    this.dataView.addRowSelectionInterval(i, i);
                }
            } else if (mouseEvent.getClickCount() == 2 && this.actionDblClick != null) {
                this.actionDblClick.dblClick(mouseEvent);
            }
        }
    }
}

