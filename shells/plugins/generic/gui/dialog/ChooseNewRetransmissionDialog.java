/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.plugins.generic.gui.dialog;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import core.EasyI18N;
import core.ui.component.dialog.GOptionPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import shells.plugins.generic.model.Retransmission;
import shells.plugins.generic.model.enums.RetransmissionType;

public class ChooseNewRetransmissionDialog
extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public JTextField listenAddressTextField;
    public JTextField listenPortTextField;
    public JComboBox proxyTypeComboBox;
    public JTextField targetAddressTextField;
    public JTextField targetPortTextField;
    public JLabel listenAddressLabel;
    public JLabel listenPortLabel;
    public JLabel proxyTypeLabel;
    public JLabel targetAddressLabel;
    public JLabel targetPortLabel;
    public Retransmission proxyModel;

    public ChooseNewRetransmissionDialog(Window parentWindow) {
        super(parentWindow);
        this.$$$setupUI$$$();
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane().setDefaultButton(this.buttonOK);
        this.buttonOK.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseNewRetransmissionDialog.this.onOK();
            }
        });
        this.buttonCancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseNewRetransmissionDialog.this.onCancel();
            }
        });
        this.setDefaultCloseOperation(0);
        this.addWindowListener(new WindowAdapter(){

            @Override
            public void windowClosing(WindowEvent e) {
                ChooseNewRetransmissionDialog.this.onCancel();
            }
        });
        this.contentPane.registerKeyboardAction(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                ChooseNewRetransmissionDialog.this.onCancel();
            }
        }, KeyStroke.getKeyStroke(27, 0), 1);
    }

    private void onOK() {
        if (this.proxyModel == null) {
            this.proxyModel = new Retransmission();
        }
        try {
            String chooseProxy = this.proxyTypeComboBox.getSelectedItem().toString().toUpperCase();
            this.proxyModel.listenAddress = this.listenAddressTextField.getText().trim();
            this.proxyModel.listenPort = Integer.parseInt(this.listenPortTextField.getText().trim());
            this.proxyModel.targetAddress = this.targetAddressTextField.getText().trim();
            this.proxyModel.targetPort = Integer.parseInt(this.targetPortTextField.getText().trim());
            this.proxyModel.retransmissionType = "PORT_FORWARD".equals(chooseProxy) ? RetransmissionType.PORT_FORWARD : ("PORT_MAP".equals(chooseProxy) ? RetransmissionType.PORT_MAP : RetransmissionType.NULL);
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this, e.getLocalizedMessage());
            return;
        }
        this.dispose();
    }

    private void onCancel() {
        this.proxyModel = null;
        this.dispose();
    }

    public static Retransmission chooseNewProxy(Window parentWindow) {
        ChooseNewRetransmissionDialog dialog = new ChooseNewRetransmissionDialog(parentWindow);
        dialog.pack();
        dialog.setLocationRelativeTo(parentWindow);
        EasyI18N.installObject(dialog);
        dialog.setVisible(true);
        return dialog.proxyModel;
    }

    private void $$$setupUI$$$() {
        this.contentPane = new JPanel();
        this.contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        this.contentPane.add((Component)panel1, new GridConstraints(1, 0, 1, 1, 0, 3, 3, 1, null, null, null, 0, false));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add((Component)panel2, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, null, null, null, 0, false));
        this.buttonOK = new JButton();
        this.buttonOK.setText("\u6dfb\u52a0");
        panel2.add((Component)this.buttonOK, new GridConstraints(0, 0, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        this.buttonCancel = new JButton();
        this.buttonCancel.setText("\u53d6\u6d88");
        panel2.add((Component)this.buttonCancel, new GridConstraints(0, 1, 1, 1, 0, 1, 3, 0, null, null, null, 0, false));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        this.contentPane.add((Component)panel3, new GridConstraints(0, 0, 1, 1, 0, 3, 3, 3, null, null, null, 0, false));
        this.listenAddressLabel = new JLabel();
        this.listenAddressLabel.setText("\u76d1\u542c\u5730\u5740");
        panel3.add((Component)this.listenAddressLabel, new GridConstraints(0, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.listenAddressTextField = new JTextField();
        this.listenAddressTextField.setText("127.0.0.1");
        panel3.add((Component)this.listenAddressTextField, new GridConstraints(0, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.listenPortLabel = new JLabel();
        this.listenPortLabel.setText("\u76d1\u542c\u7aef\u53e3");
        panel3.add((Component)this.listenPortLabel, new GridConstraints(1, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.listenPortTextField = new JTextField();
        this.listenPortTextField.setText("6666");
        panel3.add((Component)this.listenPortTextField, new GridConstraints(1, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.proxyTypeLabel = new JLabel();
        this.proxyTypeLabel.setText("\u4ee3\u7406\u7c7b\u578b");
        panel3.add((Component)this.proxyTypeLabel, new GridConstraints(2, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.proxyTypeComboBox = new JComboBox();
        DefaultComboBoxModel<String> defaultComboBoxModel1 = new DefaultComboBoxModel<String>();
        defaultComboBoxModel1.addElement("PORT_FORWARD");
        defaultComboBoxModel1.addElement("PORT_MAP");
        this.proxyTypeComboBox.setModel(defaultComboBoxModel1);
        panel3.add((Component)this.proxyTypeComboBox, new GridConstraints(2, 1, 1, 1, 8, 1, 2, 0, null, null, null, 0, false));
        this.targetAddressLabel = new JLabel();
        this.targetAddressLabel.setText("\u76ee\u6807\u5730\u5740");
        panel3.add((Component)this.targetAddressLabel, new GridConstraints(3, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.targetAddressTextField = new JTextField();
        this.targetAddressTextField.setText("8.8.8.8");
        panel3.add((Component)this.targetAddressTextField, new GridConstraints(3, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
        this.targetPortLabel = new JLabel();
        this.targetPortLabel.setText("\u76ee\u6807\u7aef\u53e3");
        panel3.add((Component)this.targetPortLabel, new GridConstraints(4, 0, 1, 1, 8, 0, 0, 0, null, null, null, 0, false));
        this.targetPortTextField = new JTextField();
        this.targetPortTextField.setText("53");
        panel3.add((Component)this.targetPortTextField, new GridConstraints(4, 1, 1, 1, 8, 1, 4, 0, null, new Dimension(150, -1), null, 0, false));
    }

    public JComponent $$$getRootComponent$$$() {
        return this.contentPane;
    }

    private void createUIComponents() {
    }
}

