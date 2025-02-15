/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.EasyI18N;
import core.Encoding;
import core.shell.ShellEntity;
import core.ui.component.GBC;
import core.ui.component.dialog.GOptionPane;
import core.ui.component.model.DbInfo;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import util.Log;
import util.automaticBindClick;
import util.functions;

public class DatabaseSetting
extends JDialog {
    private JComboBox<String> dbTypeComboBox;
    private JTextField dbHostTextField;
    private JTextField dbPortTextField;
    private JTextField dbUserNameTextField;
    private JTextField dbPasswordTextField;
    private JComboBox<String> dbCharsetComboBox;
    private JLabel dbTypeLabel;
    private JLabel dbHostLabel;
    private JLabel dbPortLabel;
    private JLabel dbUserNameLabel;
    private JLabel dbPasswordLabel;
    private JLabel dbCharsetLabel;
    private JButton updateButton;
    private Dimension TextFieldDim = new Dimension(200, 23);
    private Dimension labelDim = new Dimension(150, 23);
    private DbInfo dbInfo;

    public DatabaseSetting(ShellEntity shellEntity, DbInfo dbInfo) {
        super(shellEntity.getFrame(), "DbInfo Setting", true);
        String[] databaseTypeArray = shellEntity.getPayloadModule().getAllDatabaseType();
        Container c = this.getContentPane();
        this.setLayout(new GridBagLayout());
        this.dbInfo = dbInfo;
        GBC gbcLDbType = new GBC(0, 0).setInsets(5, -40, 0, 0);
        GBC gbcDbType = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLDbHost = new GBC(0, 1).setInsets(5, -40, 0, 0);
        GBC gbcDbHost = new GBC(1, 1, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLDbPort = new GBC(0, 2).setInsets(5, -40, 0, 0);
        GBC gbcDbPort = new GBC(1, 2, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLDbUserName = new GBC(0, 3).setInsets(5, -40, 0, 0);
        GBC gbcDbUserName = new GBC(1, 3, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLDbPassword = new GBC(0, 4).setInsets(5, -40, 0, 0);
        GBC gbcDbPassword = new GBC(1, 4, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLDbCharset = new GBC(0, 5).setInsets(5, -40, 0, 0);
        GBC gbcDbCharset = new GBC(1, 5, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcUpdate = new GBC(1, 6, 4, 1).setInsets(5, 20, 0, 0);
        this.updateButton = new JButton("Update Db Info");
        this.dbTypeLabel = new JLabel("\u6570\u636e\u5e93\u7c7b\u578b");
        this.dbHostLabel = new JLabel("\u6570\u636e\u5e93\u4e3b\u673a");
        this.dbPortLabel = new JLabel("\u6570\u636e\u5e93\u7aef\u53e3");
        this.dbUserNameLabel = new JLabel("\u6570\u636e\u5e93\u7528\u6237\u540d");
        this.dbPasswordLabel = new JLabel("\u6570\u636e\u5e93\u5bc6\u7801");
        this.dbCharsetLabel = new JLabel("\u6570\u636e\u5e93\u7f16\u7801");
        this.dbTypeLabel.setPreferredSize(this.labelDim);
        this.dbHostLabel.setPreferredSize(this.labelDim);
        this.dbPortLabel.setPreferredSize(this.labelDim);
        this.dbUserNameLabel.setPreferredSize(this.labelDim);
        this.dbPasswordLabel.setPreferredSize(this.labelDim);
        this.dbCharsetLabel.setPreferredSize(this.labelDim);
        this.dbHostTextField = new JTextField(dbInfo.getDbHost());
        this.dbPortTextField = new JTextField(Integer.toString(dbInfo.getDbPort()));
        this.dbUserNameTextField = new JTextField(dbInfo.getDbUserName());
        this.dbPasswordTextField = new JTextField(dbInfo.getDbPassword());
        this.dbTypeComboBox = new JComboBox<String>(databaseTypeArray);
        this.dbCharsetComboBox = new JComboBox<String>(Encoding.getAllEncodingTypes());
        this.dbCharsetComboBox.setEditable(false);
        this.dbCharsetComboBox.setSelectedItem(dbInfo.getCharset().toString());
        this.dbTypeComboBox.setEditable(false);
        this.dbTypeComboBox.setSelectedItem(dbInfo.getDbType());
        this.dbTypeComboBox.setPreferredSize(this.TextFieldDim);
        this.dbHostTextField.setPreferredSize(this.TextFieldDim);
        this.dbPortTextField.setPreferredSize(this.TextFieldDim);
        this.dbUserNameTextField.setPreferredSize(this.TextFieldDim);
        this.dbPasswordTextField.setPreferredSize(this.TextFieldDim);
        this.dbCharsetComboBox.setPreferredSize(this.TextFieldDim);
        this.updateButton.setPreferredSize(this.TextFieldDim);
        c.add((Component)this.dbTypeLabel, gbcLDbType);
        c.add(this.dbTypeComboBox, gbcDbType);
        c.add((Component)this.dbHostLabel, gbcLDbHost);
        c.add((Component)this.dbHostTextField, gbcDbHost);
        c.add((Component)this.dbPortLabel, gbcLDbPort);
        c.add((Component)this.dbPortTextField, gbcDbPort);
        c.add((Component)this.dbUserNameLabel, gbcLDbUserName);
        c.add((Component)this.dbUserNameTextField, gbcDbUserName);
        c.add((Component)this.dbPasswordLabel, gbcLDbPassword);
        c.add((Component)this.dbPasswordTextField, gbcDbPassword);
        c.add((Component)this.dbCharsetLabel, gbcLDbCharset);
        c.add(this.dbCharsetComboBox, gbcDbCharset);
        c.add((Component)this.updateButton, gbcUpdate);
        automaticBindClick.bindJButtonClick(this, this);
        functions.setWindowSize(this, 460, 270);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(2);
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    private void updateButtonClick(ActionEvent actionEvent) {
        try {
            String dbType = (String)this.dbTypeComboBox.getSelectedItem();
            if (dbType.trim().length() > 0) {
                this.dbInfo.setDbHost(this.dbHostTextField.getText());
                this.dbInfo.setDbPort(Integer.parseInt(this.dbPortTextField.getText()));
                this.dbInfo.setDbUserName(this.dbUserNameTextField.getText());
                this.dbInfo.setDbPassword(this.dbPasswordTextField.getText());
                this.dbInfo.setDbType(dbType);
                this.dbInfo.setCharset(this.dbCharsetComboBox.getSelectedItem().toString());
                GOptionPane.showMessageDialog(this, "success", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(this, "no selected DbType", "\u63d0\u793a", 2);
            }
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this, e.getMessage(), "\u63d0\u793a", 2);
            Log.error(e);
        }
        this.dispose();
    }
}

