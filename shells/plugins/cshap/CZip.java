/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.GBC;
import core.ui.component.dialog.GOptionPane;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.Log;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="Zip", DisplayName="ZIP\u538b\u7f29")
public class CZip
implements Plugin {
    private static final String CLASS_NAME = "CZip.Run";
    private ShellEntity shellEntity;
    private Payload payload;
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final JLabel compressSrcDirLabel;
    private final JLabel compressDestFileLabel;
    private final JTextField compressDestFileTextField;
    private final JTextField compressSrcDirTextField;
    private final JButton zipButton;
    private final JButton unZipButton;
    private Encoding encoding;
    private boolean loadState;

    public CZip() {
        GBC gbcLCompressSrcDir = new GBC(0, 0).setInsets(5, -40, 0, 0);
        GBC gbcCompressSrcDir = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLCompressDestFileLabel = new GBC(0, 1).setInsets(5, -40, 0, 0);
        GBC gbcCompressDestFile = new GBC(1, 1, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcZipButton = new GBC(0, 2).setInsets(5, -20, 0, 0);
        GBC gbcUnZipButton = new GBC(0, 2, 5, 1).setInsets(5, 20, 0, 0);
        this.compressSrcDirLabel = new JLabel("\u76ee\u6807\u6587\u4ef6\u5939");
        this.compressDestFileLabel = new JLabel("\u538b\u7f29\u6587\u4ef6");
        this.zipButton = new JButton("\u538b\u7f29");
        this.unZipButton = new JButton("\u89e3\u538b");
        this.compressSrcDirTextField = new JTextField(50);
        this.compressDestFileTextField = new JTextField(50);
        this.panel.add((Component)this.compressSrcDirLabel, gbcLCompressSrcDir);
        this.panel.add((Component)this.compressSrcDirTextField, gbcCompressSrcDir);
        this.panel.add((Component)this.compressDestFileLabel, gbcLCompressDestFileLabel);
        this.panel.add((Component)this.compressDestFileTextField, gbcCompressDestFile);
        this.panel.add((Component)this.zipButton, gbcZipButton);
        this.panel.add((Component)this.unZipButton, gbcUnZipButton);
    }

    private void load() {
        if (!this.loadState) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream(String.format("assets/%s.dll", CLASS_NAME.substring(0, CLASS_NAME.indexOf("."))));
                byte[] binCode = functions.readInputStream(inputStream);
                inputStream.close();
                this.loadState = this.payload.include(CLASS_NAME, binCode);
                if (this.loadState) {
                    Log.log("Load success", new Object[0]);
                } else {
                    Log.log("Load fail", new Object[0]);
                }
            } catch (Exception e) {
                Log.error(e);
            }
        }
    }

    private void zipButtonClick(ActionEvent actionEvent) {
        this.load();
        if (this.compressDestFileTextField.getText().trim().length() > 0 && this.compressSrcDirTextField.getText().trim().length() > 0) {
            ReqParameter reqParameter = new ReqParameter();
            reqParameter.add("compressFile", this.compressDestFileTextField.getText().trim());
            reqParameter.add("compressDir", this.compressSrcDirTextField.getText().trim());
            String resultString = this.encoding.Decoding(this.payload.evalFunc(CLASS_NAME, "zip", reqParameter));
            GOptionPane.showMessageDialog(null, resultString, "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(null, "\u8bf7\u68c0\u67e5\u662f\u5426\u586b\u5199\u5b8c\u6574", "\u63d0\u793a", 1);
        }
    }

    private void unZipButtonClick(ActionEvent actionEvent) {
        this.load();
        if (this.compressDestFileTextField.getText().trim().length() > 0 && this.compressSrcDirTextField.getText().trim().length() > 0) {
            ReqParameter reqParameter = new ReqParameter();
            reqParameter.add("compressFile", this.compressDestFileTextField.getText().trim());
            reqParameter.add("compressDir", this.compressSrcDirTextField.getText().trim());
            String resultString = this.encoding.Decoding(this.payload.evalFunc(CLASS_NAME, "unZip", reqParameter));
            GOptionPane.showMessageDialog(null, resultString, "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(null, "\u8bf7\u68c0\u67e5\u662f\u5426\u586b\u5199\u5b8c\u6574", "\u63d0\u793a", 1);
        }
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = this.shellEntity.getPayloadModule();
        this.encoding = Encoding.getEncoding(this.shellEntity);
        this.compressSrcDirTextField.setText(this.payload.currentDir());
        this.compressDestFileTextField.setText(this.payload.currentDir() + functions.getLastFileName(this.payload.currentDir()) + ".zip");
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        return this.panel;
    }
}

