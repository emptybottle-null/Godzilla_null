/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.EasyI18N;
import core.ui.component.GBC;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.model.FileOpertionInfo;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import util.automaticBindClick;
import util.functions;

public class FileDialog
extends JDialog {
    private final JTextField srcFileTextField;
    private final JTextField destFileTextField;
    private final JLabel srcFileLabel;
    private final JLabel destFileLabel;
    private final JButton okButton;
    private final JButton cancelButton;
    private final FileOpertionInfo fileOpertionInfo;
    private final JButton srcSelectdFileButton;
    private final JButton destSelectdFileButton;
    private boolean state;
    Dimension TextFieldDim = new Dimension(500, 23);

    private FileDialog(Frame frame, String tipString, String srcFileString, String destString) {
        super(frame, tipString, true);
        this.fileOpertionInfo = new FileOpertionInfo();
        this.srcFileTextField = new JTextField("srcFileText", 30);
        this.destFileTextField = new JTextField("destText", 30);
        this.srcFileLabel = new JLabel("srcFile");
        this.destFileLabel = new JLabel("destFile");
        this.okButton = new JButton("ok");
        this.cancelButton = new JButton("cancel");
        this.srcSelectdFileButton = new JButton("select File");
        this.destSelectdFileButton = new JButton("select File");
        Dimension TextFieldDim = new Dimension(200, 23);
        GBC gbcLSrcFile = new GBC(0, 0).setInsets(5, -40, 0, 0);
        GBC gbcSrcFile = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcSrcSelectdFie = new GBC(4, 0, 7, 1).setInsets(5, 50, 0, 10);
        GBC gbcDestSelectdFie = new GBC(4, 1, 7, 1).setInsets(5, 50, 0, 10);
        GBC gbcLDestFile = new GBC(0, 1).setInsets(5, -40, 0, 0);
        GBC gbcDestFile = new GBC(1, 1, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcOkButton = new GBC(0, 2, 2, 1).setInsets(5, 20, 0, 0);
        GBC gbcCancelButton = new GBC(2, 2, 1, 1).setInsets(5, 20, 0, 0);
        this.srcFileTextField.setPreferredSize(TextFieldDim);
        this.destFileTextField.setPreferredSize(TextFieldDim);
        this.setLayout(new GridBagLayout());
        this.add((Component)this.srcFileLabel, gbcLSrcFile);
        this.add((Component)this.srcFileTextField, gbcSrcFile);
        this.add((Component)this.srcSelectdFileButton, gbcSrcSelectdFie);
        this.add((Component)this.destSelectdFileButton, gbcDestSelectdFie);
        this.add((Component)this.destFileLabel, gbcLDestFile);
        this.add((Component)this.destFileTextField, gbcDestFile);
        this.add((Component)this.okButton, gbcOkButton);
        this.add((Component)this.cancelButton, gbcCancelButton);
        automaticBindClick.bindJButtonClick(this, this);
        this.addWindowListener(new WindowListener(){

            @Override
            public void windowOpened(WindowEvent paramWindowEvent) {
            }

            @Override
            public void windowIconified(WindowEvent paramWindowEvent) {
            }

            @Override
            public void windowDeiconified(WindowEvent paramWindowEvent) {
            }

            @Override
            public void windowDeactivated(WindowEvent paramWindowEvent) {
            }

            @Override
            public void windowClosing(WindowEvent paramWindowEvent) {
                FileDialog.this.cancelButtonClick(null);
            }

            @Override
            public void windowClosed(WindowEvent paramWindowEvent) {
            }

            @Override
            public void windowActivated(WindowEvent paramWindowEvent) {
            }
        });
        this.srcFileTextField.setText(srcFileString);
        this.destFileTextField.setText(destString);
        functions.setWindowSize(this, 650, 180);
        this.setLocationRelativeTo(frame);
        this.setDefaultCloseOperation(2);
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    public FileOpertionInfo getResult() {
        return this.fileOpertionInfo;
    }

    private void okButtonClick(ActionEvent actionEvent) {
        this.fileOpertionInfo.setOpertionStatus(true);
        this.changeFileInfo();
    }

    private void cancelButtonClick(ActionEvent actionEvent) {
        this.fileOpertionInfo.setOpertionStatus(false);
        this.changeFileInfo();
    }

    private void changeFileInfo() {
        this.fileOpertionInfo.setSrcFileName(this.srcFileTextField.getText());
        this.fileOpertionInfo.setDestFileName(this.destFileTextField.getText());
        this.state = true;
        this.dispose();
    }

    private void srcSelectdFileButtonClick(ActionEvent actionEvent) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            String fileString = selectdFile.getAbsolutePath();
            this.srcFileTextField.setText(fileString);
        }
    }

    private void destSelectdFileButtonClick(ActionEvent actionEvent) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            String fileString = selectdFile.getAbsolutePath();
            this.destFileTextField.setText(fileString);
        }
    }

    public static FileOpertionInfo showFileOpertion(Frame frame, String title, String srcFileString, String destString) {
        return new FileDialog(frame, title, srcFileString, destString).getResult();
    }
}

