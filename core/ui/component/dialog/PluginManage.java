/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import core.ApplicationContext;
import core.Db;
import core.EasyI18N;
import core.ui.MainActivity;
import core.ui.component.DataView;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import util.Log;
import util.automaticBindClick;
import util.functions;

public class PluginManage
extends JDialog {
    private final DataView pluginView;
    private final JButton addPluginButton = new JButton("\u6dfb\u52a0");
    private final JButton removeButton = new JButton("\u79fb\u9664");
    private final JButton cancelButton;
    private final JButton refreshButton = new JButton("\u5237\u65b0");
    private final Vector<String> columnVector;
    private final JSplitPane splitPane;

    public PluginManage() {
        super(MainActivity.getFrame(), "PluginManage", true);
        this.cancelButton = new JButton("\u53d6\u6d88");
        this.splitPane = new JSplitPane();
        this.columnVector = new Vector();
        this.columnVector.add("pluginJarFile");
        this.pluginView = new DataView(null, this.columnVector, -1, -1);
        this.refreshPluginView();
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(this.addPluginButton);
        bottomPanel.add(this.removeButton);
        bottomPanel.add(this.refreshButton);
        bottomPanel.add(this.cancelButton);
        this.splitPane.setOrientation(0);
        this.splitPane.setTopComponent(new JScrollPane(this.pluginView));
        this.splitPane.setBottomComponent(bottomPanel);
        this.splitPane.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                PluginManage.this.splitPane.setDividerLocation(0.85);
            }
        });
        automaticBindClick.bindJButtonClick(this, this);
        this.add(this.splitPane);
        functions.setWindowSize(this, 420, 420);
        this.setLocationRelativeTo(MainActivity.getFrame());
        this.setDefaultCloseOperation(2);
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    private void refreshPluginView() {
        String[] pluginStrings = Db.getAllPlugin();
        Vector rows = new Vector();
        for (int i = 0; i < pluginStrings.length; ++i) {
            String string = pluginStrings[i];
            Vector<String> rowVector = new Vector<String>();
            rowVector.add(string);
            rows.add(rowVector);
        }
        this.pluginView.AddRows(rows);
        this.pluginView.getModel().fireTableDataChanged();
    }

    private void addPluginButtonClick(ActionEvent actionEvent) {
        GFileChooser chooser = new GFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("*.jar", "jar"));
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            if (Db.addPlugin(selectdFile.getAbsolutePath()) == 1) {
                ApplicationContext.init();
                GOptionPane.showMessageDialog(this, "\u6dfb\u52a0\u63d2\u4ef6\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(this, "\u6dfb\u52a0\u63d2\u4ef6\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            Log.log("\u7528\u6237\u53d6\u6d88\u9009\u62e9.....", new Object[0]);
        }
        this.refreshPluginView();
    }

    private void removeButtonClick(ActionEvent actionEvent) {
        int rowIndex = this.pluginView.getSelectedRow();
        if (rowIndex != -1) {
            Object selectedItem = this.pluginView.getValueAt(rowIndex, 0);
            if (Db.removePlugin((String)selectedItem) == 1) {
                GOptionPane.showMessageDialog(this, "\u79fb\u9664\u63d2\u4ef6\u6210\u529f", "\u63d0\u793a", 1);
            } else {
                GOptionPane.showMessageDialog(this, "\u79fb\u9664\u63d2\u4ef6\u5931\u8d25", "\u63d0\u793a", 2);
            }
        } else {
            GOptionPane.showMessageDialog(this, "\u6ca1\u6709\u9009\u4e2d\u63d2\u4ef6", "\u63d0\u793a", 2);
        }
        this.refreshPluginView();
    }

    private void cancelButtonClick(ActionEvent actionEvent) {
        this.dispose();
    }

    private void refreshButtonClick(ActionEvent actionEvent) {
        this.refreshPluginView();
    }
}

