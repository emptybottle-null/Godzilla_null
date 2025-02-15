/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component;

import core.ApplicationContext;
import core.EasyI18N;
import core.imp.Payload;
import core.shell.ShellEntity;
import core.ui.component.GBC;
import core.ui.component.RTextArea;
import core.ui.component.dialog.GOptionPane;
import core.ui.component.frame.EditFileFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import org.fife.ui.rtextarea.RTextScrollPane;
import util.Log;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;

public class ShellRSFilePanel
extends JPanel {
    private JComboBox<String> encodingComboBox;
    private JButton saveButton;
    private JButton refreshButton;
    private JButton backButton;
    private JTextField readFileTextField;
    private RTextArea fileDataTextArea;
    private JPanel parentPanel;
    private Payload payload;
    private CardLayout cardLayout = null;
    private JSplitPane splitPane;
    private ShellEntity shellContext;
    private byte[] fileData;
    private String containerName = null;
    private JPanel topPanel;
    private RTextScrollPane scrollPane;
    private String encodingTypeString;
    private String currentFile;

    public ShellRSFilePanel(ShellEntity shellContext, JPanel parentPanel, String containerName) {
        super(new BorderLayout());
        this.parentPanel = parentPanel;
        if (parentPanel != null) {
            this.cardLayout = (CardLayout)parentPanel.getLayout();
            this.containerName = containerName;
        }
        this.payload = shellContext.getPayloadModule();
        this.shellContext = shellContext;
        this.topPanel = new JPanel();
        this.encodingComboBox = new JComboBox<String>(ApplicationContext.getAllEncodingTypes());
        this.saveButton = new JButton("\u4fdd\u5b58");
        this.refreshButton = new JButton("\u5237\u65b0");
        this.backButton = new JButton("\u8fd4\u56de");
        this.splitPane = new JSplitPane();
        this.readFileTextField = new JTextField(80);
        this.fileDataTextArea = new RTextArea();
        this.scrollPane = new RTextScrollPane(this.fileDataTextArea, true);
        this.scrollPane.setIconRowHeaderEnabled(true);
        this.scrollPane.getGutter().setBookmarkingEnabled(true);
        this.topPanel.add((Component)this.readFileTextField, new GBC(1, 1).setFill(2).setWeight(1.0, 0.0));
        this.topPanel.add(this.encodingComboBox);
        this.topPanel.add(this.saveButton);
        this.topPanel.add(this.refreshButton);
        this.topPanel.add(this.backButton);
        this.splitPane.setOrientation(0);
        this.splitPane.setTopComponent(this.topPanel);
        this.splitPane.setBottomComponent(this.scrollPane);
        this.encodingComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                if (ShellRSFilePanel.this.fileData != null) {
                    ShellRSFilePanel.this.encodingTypeString = (String)ShellRSFilePanel.this.encodingComboBox.getSelectedItem();
                    ShellRSFilePanel.this.refreshData();
                }
            }
        });
        automaticBindClick.bindJButtonClick(this, this);
        this.encodingTypeString = (String)this.encodingComboBox.getSelectedItem();
        this.add(this.splitPane);
        EasyI18N.installObject(this);
    }

    public void rsFile(String file) {
        if (this.payload.isAlive() || this.shellContext.isUseCache()) {
            this.currentFile = file;
            this.readFileTextField.setText(file);
            this.fileData = this.payload.downloadFile(file);
            UiFunction.setSyntaxEditingStyle(this.fileDataTextArea, file);
            this.refreshData();
        } else {
            this.readFileTextField.setText(this.currentFile);
            GOptionPane.showMessageDialog(this, "\u5237\u65b0\u5931\u8d25 \u6709\u6548\u8f7d\u8377\u5df2\u7ecf\u9500\u6bc1", "\u63d0\u793a", 2);
        }
    }

    private void refreshData() {
        try {
            this.fileDataTextArea.setText(new String(this.fileData, this.encodingTypeString));
        } catch (Exception e) {
            this.fileDataTextArea.setText(new String(this.fileData));
            Log.error(e);
        }
    }

    public void saveButtonClick(ActionEvent e) {
        if (this.payload.isAlive()) {
            String fileString = this.readFileTextField.getText();
            boolean uploadState = this.payload.uploadFile(fileString, functions.stringToByteArray(this.fileDataTextArea.getText(), this.encodingTypeString));
            if (uploadState) {
                GOptionPane.showMessageDialog(this, "\u4fdd\u5b58\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(this, "\u4fdd\u5b58\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(this, "\u4fdd\u5b58\u5931\u8d25 \u6709\u6548\u8f7d\u8377\u5df2\u7ecf\u9500\u6bc1", "\u63d0\u793a", 2);
        }
    }

    public void refreshButtonClick(ActionEvent e) {
        this.rsFile(this.readFileTextField.getText());
    }

    public void backButtonClick(ActionEvent e) {
        if (this.cardLayout != null) {
            this.fileData = null;
            this.fileDataTextArea.setText("");
            this.readFileTextField.setText("");
            this.cardLayout.show(this.parentPanel, this.containerName);
        }
    }

    public String getFile() {
        return this.currentFile;
    }

    public String getShellId() {
        return this.shellContext.getId();
    }

    public void openThisToEditFileFrame() {
        EditFileFrame.OpenNewEdit(this);
    }
}

