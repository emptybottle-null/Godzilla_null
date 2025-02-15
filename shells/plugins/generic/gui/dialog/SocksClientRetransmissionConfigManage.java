/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic.gui.dialog;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import core.EasyI18N;
import core.socksServer.SocksServerConfig;
import core.ui.component.dialog.GOptionPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class SocksClientRetransmissionConfigManage
extends JDialog {
    private JPanel contentPane;
    public JLabel clientSocketOnceReadSizeLabel;
    public JTextField clientSocketOnceReadSizeTextField;
    public JLabel clientPacketSizeLabel;
    public JTextField clientPacketTextField;
    public JLabel capacityLabel;
    public JTextField capacityTextField;
    public JLabel requestDelayLabel;
    public JTextField requestDelayTextField;
    public JLabel requestErrRetryLabel;
    public JTextField requestErrRetryTextField;
    public JLabel requestErrDelayLabel;
    public JTextField requestErrDelayTextField;
    public JButton saveButton;
    public JButton cancelButton;
    private SocksServerConfig socksServerConfig;

    public SocksClientRetransmissionConfigManage(Window parentWindow, SocksServerConfig socksServerConfig) {
        super(parentWindow, "\u914d\u7f6e\u4ee3\u7406");
        this.$$$setupUI$$$();
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(this.saveButton);
        this.socksServerConfig = socksServerConfig;
        this.clientSocketOnceReadSizeTextField.setText(String.valueOf(socksServerConfig.clientSocketOnceReadSize.get()));
        this.clientPacketTextField.setText(String.valueOf(socksServerConfig.clientPacketSize.get()));
        this.capacityTextField.setText(String.valueOf(socksServerConfig.capacity.get()));
        this.requestDelayTextField.setText(String.valueOf(socksServerConfig.requestDelay.get()));
        this.requestErrRetryTextField.setText(String.valueOf(socksServerConfig.requestErrRetry.get()));
        this.requestErrDelayTextField.setText(String.valueOf(socksServerConfig.requestErrDelay.get()));
        this.saveButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SocksClientRetransmissionConfigManage.this.onOK();
            }
        });
        this.cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SocksClientRetransmissionConfigManage.this.onCancel();
            }
        });
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                SocksClientRetransmissionConfigManage.this.onCancel();
            }
        });
        this.contentPane.registerKeyboardAction(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                SocksClientRetransmissionConfigManage.this.onCancel();
            }
        }, KeyStroke.getKeyStroke(27, 0), 1);
    }

    public static void socksServerConfig(Window parentWindow, SocksServerConfig socksServerConfig) {
        SocksClientRetransmissionConfigManage dialog = new SocksClientRetransmissionConfigManage(parentWindow, socksServerConfig);
        dialog.pack();
        dialog.setLocationRelativeTo(parentWindow);
        EasyI18N.installObject(dialog);
        dialog.setVisible(true);
    }

    private void onOK() {
        try {
            int clientSocketOnceReadSize = Integer.parseInt(this.clientSocketOnceReadSizeTextField.getText().trim());
            int clientPacketSize = Integer.parseInt(this.clientPacketTextField.getText().trim());
            int capacity = Integer.parseInt(this.capacityTextField.getText().trim());
            int requestDelay = Integer.parseInt(this.requestDelayTextField.getText().trim());
            int requestErrRetry = Integer.parseInt(this.requestErrRetryTextField.getText().trim());
            int requestErrDelay = Integer.parseInt(this.requestErrDelayTextField.getText().trim());
            this.socksServerConfig.clientSocketOnceReadSize.set(clientSocketOnceReadSize);
            this.socksServerConfig.clientPacketSize.set(clientPacketSize);
            this.socksServerConfig.capacity.set(capacity);
            this.socksServerConfig.requestDelay.set(requestDelay);
            this.socksServerConfig.requestErrRetry.set(requestErrRetry);
            this.socksServerConfig.requestErrDelay.set(requestErrDelay);
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u6210\u529f \u914d\u7f6e\u5df2\u751f\u6548");
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            return;
        }
        this.dispose();
    }

    private void onCancel() {
        this.dispose();
    }

    private void $$$setupUI$$$() {
        this.contentPane = new JPanel();
        this.contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(8, 2, new Insets(0, 0, 0, 0), -1, -1));
        this.contentPane.add((Component)panel1, new GridConstraints(0, 0, 1, 1, 0, 0, 3, 3, null, null, null, 0, false));
        this.clientSocketOnceReadSizeTextField = new JTextField();
        this.clientSocketOnceReadSizeTextField.setText("102400");
        panel1.add((Component)this.clientSocketOnceReadSizeTextField, new GridConstraints(0, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.clientPacketSizeLabel = new JLabel();
        this.clientPacketSizeLabel.setText("Client\u5355\u6b21\u8bfb\u53d6\u5927\u5c0f:");
        panel1.add((Component)this.clientPacketSizeLabel, new GridConstraints(1, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.clientPacketTextField = new JTextField();
        this.clientPacketTextField.setText("1024000");
        panel1.add((Component)this.clientPacketTextField, new GridConstraints(1, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add((Component)panel2, new GridConstraints(6, 0, 1, 2, 0, 3, 3, 3, null, null, null, 0, false));
        this.saveButton = new JButton();
        this.saveButton.setText("\u4fdd\u5b58");
        panel2.add((Component)this.saveButton, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        this.cancelButton = new JButton();
        this.cancelButton.setText("\u53d6\u6d88");
        panel2.add((Component)this.cancelButton, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add((Component)panel3, new GridConstraints(7, 0, 1, 2, 0, 3, 3, 3, null, null, null, 0, false));
        this.requestDelayLabel = new JLabel();
        this.requestDelayLabel.setText("\u8bf7\u6c42\u6296\u52a8\u5ef6\u8fdf(ms)");
        panel1.add((Component)this.requestDelayLabel, new GridConstraints(3, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.requestDelayTextField = new JTextField();
        this.requestDelayTextField.setText("10");
        panel1.add((Component)this.requestDelayTextField, new GridConstraints(3, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.requestErrRetryLabel = new JLabel();
        this.requestErrRetryLabel.setText("\u9519\u8bef\u91cd\u8bd5\u6700\u5927\u6b21\u6570");
        panel1.add((Component)this.requestErrRetryLabel, new GridConstraints(4, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.requestErrRetryTextField = new JTextField();
        this.requestErrRetryTextField.setText("20");
        panel1.add((Component)this.requestErrRetryTextField, new GridConstraints(4, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.requestErrDelayLabel = new JLabel();
        this.requestErrDelayLabel.setText("\u8bf7\u6c42\u9519\u8bef\u91cd\u8bd5\u6296\u52a8\u5ef6\u65f6(ms)");
        panel1.add((Component)this.requestErrDelayLabel, new GridConstraints(5, 0, 1, 1, 0, 0, 0, 0, null, null, null, 1, false));
        this.requestErrDelayTextField = new JTextField();
        this.requestErrDelayTextField.setText("30");
        panel1.add((Component)this.requestErrDelayTextField, new GridConstraints(5, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.capacityLabel = new JLabel();
        this.capacityLabel.setText("\u5957\u63a5\u5b57\u7f13\u51b2\u961f\u5217\u6570");
        panel1.add((Component)this.capacityLabel, new GridConstraints(2, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
        this.capacityTextField = new JTextField();
        this.capacityTextField.setText("5");
        panel1.add((Component)this.capacityTextField, new GridConstraints(2, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.clientSocketOnceReadSizeLabel = new JLabel();
        this.clientSocketOnceReadSizeLabel.setText("Client\u5957\u63a5\u5b57\u5355\u6b21\u8bfb\u53d6\u5927\u5c0f:");
        panel1.add((Component)this.clientSocketOnceReadSizeLabel, new GridConstraints(0, 0, 1, 1, 0, 0, 0, 0, null, null, null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return this.contentPane;
    }
}

