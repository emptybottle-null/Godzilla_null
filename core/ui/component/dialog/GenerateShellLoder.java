/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.ApplicationContext;
import core.EasyI18N;
import core.imp.Cryption;
import core.ui.MainActivity;
import core.ui.component.GBC;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import util.Log;
import util.automaticBindClick;
import util.functions;

public class GenerateShellLoder
extends JDialog {
    private final JLabel passwordLabel;
    private final JLabel secretKeyLabel;
    private final JLabel cryptionLabel;
    private final JLabel payloadLabel;
    private final JTextField passwordTextField;
    private final JTextField secretKeyTextField;
    private final JComboBox<String> cryptionComboBox;
    private final JComboBox<String> payloadComboBox;
    private final JButton generateButton;
    private final JButton cancelButton;

    public GenerateShellLoder() {
        super(MainActivity.getFrame(), "GenerateShell", true);
        this.setLayout(new GridBagLayout());
        Container c = this.getContentPane();
        GBC gbcLPassword = new GBC(0, 0).setInsets(5, -40, 0, 0);
        GBC gbcPassword = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLSecretKey = new GBC(0, 1).setInsets(5, -40, 0, 0);
        GBC gbcSecretKey = new GBC(1, 1, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLPayload = new GBC(0, 2).setInsets(5, -40, 0, 0);
        GBC gbcPayload = new GBC(1, 2, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLCryption = new GBC(0, 3).setInsets(5, -40, 0, 0);
        GBC gbcCryption = new GBC(1, 3, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcGenerate = new GBC(2, 4).setInsets(5, -40, 0, 0);
        GBC gbcCancel = new GBC(1, 4, 3, 1).setInsets(5, 20, 0, 0);
        this.passwordLabel = new JLabel("\u5bc6\u7801");
        this.secretKeyLabel = new JLabel("\u5bc6\u94a5");
        this.payloadLabel = new JLabel("\u6709\u6548\u8f7d\u8377");
        this.cryptionLabel = new JLabel("\u52a0\u5bc6\u5668");
        this.passwordTextField = new JTextField(16);
        this.secretKeyTextField = new JTextField(16);
        this.payloadComboBox = new JComboBox();
        this.cryptionComboBox = new JComboBox();
        this.generateButton = new JButton("\u751f\u6210");
        this.cancelButton = new JButton("\u53d6\u6d88");
        this.passwordTextField.setText("pass");
        this.secretKeyTextField.setText("key");
        c.add((Component)this.passwordLabel, gbcLPassword);
        c.add((Component)this.passwordTextField, gbcPassword);
        c.add((Component)this.secretKeyLabel, gbcLSecretKey);
        c.add((Component)this.secretKeyTextField, gbcSecretKey);
        c.add((Component)this.payloadLabel, gbcLPayload);
        c.add(this.payloadComboBox, gbcPayload);
        c.add((Component)this.cryptionLabel, gbcLCryption);
        c.add(this.cryptionComboBox, gbcCryption);
        c.add((Component)this.generateButton, gbcGenerate);
        c.add((Component)this.cancelButton, gbcCancel);
        this.addToComboBox(this.payloadComboBox, ApplicationContext.getAllPayload());
        this.payloadComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                String seleteItemString = (String)GenerateShellLoder.this.payloadComboBox.getSelectedItem();
                GenerateShellLoder.this.cryptionComboBox.removeAllItems();
                GenerateShellLoder.this.addToComboBox(GenerateShellLoder.this.cryptionComboBox, ApplicationContext.getAllCryption(seleteItemString));
            }
        });
        automaticBindClick.bindJButtonClick(this, this);
        functions.fireActionEventByJComboBox(this.payloadComboBox);
        functions.setWindowSize(this, 530, 250);
        this.setLocationRelativeTo(MainActivity.getFrame());
        this.setDefaultCloseOperation(2);
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    private void generateButtonClick(ActionEvent actionEvent) {
        String password = this.passwordTextField.getText();
        String secretKey = this.secretKeyTextField.getText();
        if (password != null && secretKey != null && password.trim().length() > 0 && secretKey.trim().length() > 0) {
            if (this.payloadComboBox.getSelectedItem() != null && this.cryptionComboBox.getSelectedItem() != null) {
                String selectedCryption;
                String selectedPayload = (String)this.payloadComboBox.getSelectedItem();
                Cryption cryption = ApplicationContext.getCryption(selectedPayload, selectedCryption = (String)this.cryptionComboBox.getSelectedItem());
                byte[] data = cryption.generate(password, secretKey);
                if (data != null) {
                    GFileChooser chooser = new GFileChooser();
                    chooser.setFileSelectionMode(0);
                    boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
                    File selectdFile = chooser.getSelectedFile();
                    if (flag && selectdFile != null) {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(selectdFile);
                            fileOutputStream.write(data);
                            fileOutputStream.close();
                            GOptionPane.showMessageDialog(this, "success! save file to -> " + selectdFile.getAbsolutePath(), "\u63d0\u793a", 1);
                            this.dispose();
                        } catch (Exception e) {
                            Log.error(e);
                        }
                    } else {
                        Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9....", new Object[0]);
                    }
                } else {
                    GOptionPane.showMessageDialog(this, "\u52a0\u5bc6\u5668\u5728\u751f\u6210\u65f6\u8fd4\u56de\u7a7a", "\u63d0\u793a", 2);
                }
            } else {
                GOptionPane.showMessageDialog(this, "payload \u6216  cryption \u6ca1\u6709\u9009\u4e2d!", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(this, "password \u6216\t secretKey  \u662f\u7a7a\u7684!", "\u63d0\u793a", 2);
        }
    }

    private void cancelButtonClick(ActionEvent actionEvent) {
        this.dispose();
    }

    private void addToComboBox(JComboBox<String> comboBox, String[] data) {
        for (int i = 0; i < data.length; ++i) {
            comboBox.addItem(data[i]);
        }
    }
}

