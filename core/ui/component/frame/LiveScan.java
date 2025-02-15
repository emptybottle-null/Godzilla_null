/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.frame;

import core.Db;
import core.EasyI18N;
import core.shell.ShellEntity;
import core.ui.MainActivity;
import core.ui.component.DataView;
import core.ui.component.dialog.GOptionPane;
import core.ui.component.dialog.ShellSetting;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import util.Log;
import util.automaticBindClick;
import util.functions;

public class LiveScan
extends JDialog {
    private DataView shellView;
    private JButton addShellButton;
    private JButton removeFailShellButton;
    private JButton scanButton;
    private JButton refreshButton;
    private Vector<String> columnVector;
    private JSplitPane splitPane;
    private boolean isRuning;
    private String groupName;
    private ComponentRenderer COMPONENT_RENDERER = new ComponentRenderer();
    private static JLabel OK_LABEL = new JLabel("Succes");
    private static JLabel FAIL_LABEL = new JLabel("Fail");
    private static JLabel WAIT_LABEL = new JLabel("wait");
    private static JLabel DELETE_LABEL = new JLabel("deleted");

    public LiveScan() {
        this("/");
    }

    public LiveScan(String groupId) {
        super(MainActivity.getFrame(), "LiveScan", true);
        this.groupName = groupId;
        this.addShellButton = new JButton("\u6dfb\u52a0Shell");
        this.removeFailShellButton = new JButton("\u79fb\u9664\u6240\u6709\u5931\u8d25");
        this.refreshButton = new JButton("\u5237\u65b0");
        this.scanButton = new JButton("\u626b\u63cf");
        this.splitPane = new JSplitPane();
        Vector<Vector<String>> allShellVector = new Vector<Vector<String>>();
        allShellVector.addAll(Db.getAllShell(this.groupName));
        this.columnVector = (Vector)allShellVector.remove(0);
        this.columnVector.add("Status");
        this.shellView = new DataView(null, this.columnVector, -1, -1);
        this.refreshshellView();
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(this.addShellButton);
        bottomPanel.add(this.scanButton);
        bottomPanel.add(this.refreshButton);
        bottomPanel.add(this.removeFailShellButton);
        this.splitPane.setOrientation(0);
        this.splitPane.setTopComponent(new JScrollPane(this.shellView));
        this.splitPane.setBottomComponent(bottomPanel);
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                LiveScan.this.splitPane.setDividerLocation(0.85);
            }
        });
        JMenuItem removeShellMenuItem = new JMenuItem("\u5220\u9664");
        removeShellMenuItem.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String shellId;
                int selectedRow = LiveScan.this.shellView.getSelectedRow();
                int lastColumn = LiveScan.this.shellView.getColumnCount() - 1;
                if (selectedRow != -1 && (shellId = (String)LiveScan.this.shellView.getValueAt(selectedRow, 0)) != null) {
                    ShellEntity shellEntity = Db.getOneShell(shellId);
                    Log.log("removeShell -> " + shellEntity.toString(), new Object[0]);
                    if (Db.removeShell(shellId) > 0) {
                        GOptionPane.showMessageDialog(null, "\u5220\u9664\u6210\u529f");
                    } else {
                        GOptionPane.showMessageDialog(null, "\u5220\u9664\u5931\u8d25");
                    }
                    LiveScan.this.shellView.setValueAt(DELETE_LABEL, selectedRow, lastColumn);
                }
            }
        });
        this.shellView.getRightClickMenu().add(removeShellMenuItem);
        automaticBindClick.bindJButtonClick(this, this);
        this.add(this.splitPane);
        functions.setWindowSize(this, 510, 430);
        this.setLocationRelativeTo(MainActivity.getFrame());
        this.setDefaultCloseOperation(2);
        this.shellView.getColumn("Status").setCellRenderer(this.COMPONENT_RENDERER);
        EasyI18N.installObject(this);
        EasyI18N.installObject(this.shellView);
        this.setVisible(true);
    }

    protected void refreshshellView() {
        Vector<Vector<String>> rows = Db.getAllShell(this.groupName);
        rows.remove(0);
        rows.forEach((Consumer<Vector<String>>)((Consumer<Vector>)oneRow -> oneRow.add("WAIT_LABEL")));
        this.shellView.AddRows(rows);
        int max = rows.size();
        int lastColumn = this.shellView.getColumnCount() - 1;
        for (int i = 0; i < max; ++i) {
            this.shellView.setValueAt(WAIT_LABEL, i, lastColumn);
        }
        this.shellView.getModel().fireTableDataChanged();
    }

    protected void addShellButtonClick(ActionEvent actionEvent) {
        ShellSetting setting = new ShellSetting(null);
        this.refreshshellView();
    }

    private void removeFailShellButtonClick(ActionEvent actionEvent) {
        int max = this.shellView.getRowCount();
        int lastColumn = this.shellView.getColumnCount() - 1;
        Object valueObject = null;
        int removeNum = 0;
        for (int i = 0; i < max; ++i) {
            String shellId;
            valueObject = this.shellView.getValueAt(i, lastColumn);
            if (!FAIL_LABEL.equals(valueObject) || (shellId = (String)this.shellView.getValueAt(i, 0)) == null) continue;
            ShellEntity shellEntity = Db.getOneShell(shellId);
            Db.removeShell(shellId);
            Log.log("removeShell -> " + shellEntity.toString(), new Object[0]);
            this.shellView.setValueAt(DELETE_LABEL, i, lastColumn);
            ++removeNum;
        }
        GOptionPane.showMessageDialog(this, String.format(EasyI18N.getI18nString("\u5171\u5220\u9664%s\u6761Shell"), removeNum));
    }

    protected synchronized void scanButtonClick(ActionEvent actionEvent) {
        if (!this.isRuning) {
            new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        LiveScan.this.scanStrart();
                    } catch (Exception e) {
                        Log.error(e);
                    } finally {
                        LiveScan.this.isRuning = false;
                    }
                }
            }).start();
            GOptionPane.showMessageDialog(this, "\u5df2\u5f00\u59cb\u5b58\u6d3b\u68c0\u6d4b");
        } else {
            GOptionPane.showMessageDialog(this, "\u6b63\u5728\u68c0\u6d4b");
        }
    }

    protected void scanStrart() {
        long startTime = System.currentTimeMillis();
        int max = this.shellView.getRowCount();
        int lastColumn = this.shellView.getColumnCount() - 1;
        Object valueObject = null;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(30, 50, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        Log.log(String.format("LiveScanStart startTime:%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())), new Object[0]);
        for (int i = 0; i < max; ++i) {
            this.shellView.setValueAt(WAIT_LABEL, i, lastColumn);
            String shellId = (String)this.shellView.getValueAt(i, 0);
            executor.execute(new ScanShellRunnable(shellId, this.shellView, i, lastColumn));
        }
        while (executor.getActiveCount() != 0) {
        }
        executor.shutdown();
        long endTime = System.currentTimeMillis();
        Log.log(String.format("LiveScanComplete completeTime:%s", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())), new Object[0]);
        int succes = 0;
        int fail = 0;
        for (int i = 0; i < max; ++i) {
            valueObject = this.shellView.getValueAt(i, lastColumn);
            if (OK_LABEL.equals(valueObject)) {
                ++succes;
                continue;
            }
            if (!FAIL_LABEL.equals(valueObject)) continue;
            ++fail;
        }
        Log.log(String.format("LiveScanComplete: \u7528\u65f6:%sms", endTime - startTime), new Object[0]);
        this.setTitle(String.format("LiveScan all:%s succes:%s fail:%s", max, succes, fail));
        GOptionPane.showMessageDialog(this, "Scan complete!");
        Log.log("Scan complete!", new Object[0]);
    }

    protected void refreshButtonClick(ActionEvent actionEvent) {
        this.refreshshellView();
        this.shellView.getColumn("Status").setCellRenderer(this.COMPONENT_RENDERER);
    }

    static {
        OK_LABEL.setOpaque(true);
        FAIL_LABEL.setOpaque(true);
        WAIT_LABEL.setOpaque(true);
        DELETE_LABEL.setOpaque(true);
        DELETE_LABEL.setBackground(Color.DARK_GRAY);
        WAIT_LABEL.setBackground(Color.CYAN);
        OK_LABEL.setBackground(Color.GREEN);
        FAIL_LABEL.setBackground(Color.RED);
    }

    class ComponentRenderer
    implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (Component.class.isAssignableFrom(value.getClass())) {
                Component component = (Component)value;
                if (isSelected) {
                    component.setForeground(table.getSelectionForeground());
                } else {
                    component.setForeground(table.getForeground());
                }
                return component;
            }
            return new JLabel(value.toString());
        }
    }

    class ScanShellRunnable
    implements Runnable {
        private String shellId;
        private DataView dataView;
        private int rowId;
        private int columnId;

        public ScanShellRunnable(String shellId, DataView dataView, int rowId, int columnId) {
            this.shellId = shellId;
            this.dataView = dataView;
            this.rowId = rowId;
            this.columnId = columnId;
        }

        @Override
        public void run() {
            boolean ok = false;
            try {
                ShellEntity shellEntity = Db.getOneShell(this.shellId);
                ok = shellEntity.initShellOpertion();
                try {
                    if (ok) {
                        shellEntity.getPayloadModule().close();
                    }
                } catch (Exception e) {
                    Log.error(e);
                }
            } catch (Exception e) {
                Log.error(e);
            }
            final boolean finalOk = ok;
            try {
                SwingUtilities.invokeAndWait(new Runnable(){

                    @Override
                    public void run() {
                        if (finalOk) {
                            ScanShellRunnable.this.dataView.setValueAt(OK_LABEL, ScanShellRunnable.this.rowId, ScanShellRunnable.this.columnId);
                        } else {
                            ScanShellRunnable.this.dataView.setValueAt(FAIL_LABEL, ScanShellRunnable.this.rowId, ScanShellRunnable.this.columnId);
                        }
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

