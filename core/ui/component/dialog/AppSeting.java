/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.ui.component.dialog;

import com.formdev.flatlaf.demo.intellijthemes.IJThemeInfo;
import com.formdev.flatlaf.demo.intellijthemes.IJThemesPanel;
import core.ApplicationContext;
import core.Db;
import core.EasyI18N;
import core.ui.MainActivity;
import core.ui.component.GBC;
import core.ui.component.RTextArea;
import core.ui.component.SimplePanel;
import core.ui.component.dialog.GFileChooser;
import core.ui.component.dialog.GOptionPane;
import core.ui.component.dialog.ShellSuperRequest;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import util.Log;
import util.OpenC;
import util.UiFunction;
import util.automaticBindClick;
import util.functions;

public class AppSeting
extends JDialog {
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel globallHttpHeaderPanel;
    private JPanel setFontPanel;
    private JPanel coreConfigPanel;
    private SimplePanel globalProxyPanel;
    private SimplePanel httpsCertConfigPanel;
    private JComboBox<String> fontNameComboBox;
    private JComboBox<String> fontTypeComboBox;
    private JComboBox<String> fontSizeComboBox;
    private JLabel testFontLabel;
    private JLabel currentFontLabel;
    private JButton updateFontButton;
    private JButton resetFontButton;
    private JLabel fontNameLabel;
    private JLabel fontTypeLabel;
    private JLabel fontSizeLabel;
    private JLabel currentFontLLabel;
    private RTextArea headerTextArea;
    private JButton updateHeaderButton;
    private JLabel godModeLabel;
    private JCheckBox godModeCheckBox;
    private JLabel execCommandModeLabel;
    private JComboBox<String> execCommandModeComboBox;
    private JLabel languageLabel;
    private JComboBox<String> languageComboBox;
    private JCheckBox isOpenCacheCheckBox;
    private JLabel isSuperLogLabel;
    private JLabel isOpenCacheLabel;
    private JCheckBox isSuperLogCheckBox;
    private JLabel superRequestLabel;
    private JButton superRequestButton;
    private JLabel isAutoCloseShellLabel;
    private JCheckBox isAutoCloseShellCheckBox;
    private int currentCoreConfigPanelComponent = 0;
    private JLabel globalProxyTypeLabel;
    private JLabel globalProxyHostLabel;
    private JLabel globalProxyPortLabel;
    private JTextField globalProxyHostTextField;
    private JTextField globalProxyPortTextField;
    private JComboBox<String> globalProxyTypeComboBox;
    private JButton updateGlobalProxyButton;
    private JButton httpsCertConfigExportButton;
    private JButton httpsCertConfigResetButton;
    private SimplePanel bigFilePanel;
    private JLabel bigFileErrorRetryNumLabel;
    private JLabel oneceBigFileUploadByteNumLabel;
    private JLabel oneceBigFileDownloadByteNumLabel;
    private JLabel bigFileSendRequestSleepLabel;
    private JTextField oneceBigFileUploadByteNumTextField;
    private JTextField bigFileErrorRetryNumTextField;
    private JTextField bigFileSendRequestSleepTextField;
    private JTextField oneceBigFileDownloadByteNumTextField;
    private JButton bigFileConfigSaveButton;
    private JSplitPane themesSplitPane;
    private IJThemesPanel themesPanel;
    private JButton updateThemesButton;
    private static final HashMap<String, Class<?>> pluginSeting = new HashMap();

    public AppSeting() {
        super(MainActivity.getFrame(), "AppSeting", true);
        this.initSetFontPanel();
        this.initGloballHttpHeader();
        this.initCoreConfigPanel();
        this.initGlobalProxy();
        this.initHttpsCertConfig();
        this.initBigFilePanel();
        this.initThemesPanel();
        this.tabbedPane.addTab("\u5168\u5c40\u534f\u8bae\u5934", this.globallHttpHeaderPanel);
        this.tabbedPane.addTab("\u5168\u5c40\u4ee3\u7406", this.globalProxyPanel);
        this.tabbedPane.addTab("\u4ee3\u7406\u8bc1\u4e66\u914d\u7f6e", this.httpsCertConfigPanel);
        this.tabbedPane.addTab("\u5b57\u4f53\u8bbe\u7f6e", this.setFontPanel);
        this.tabbedPane.addTab("\u6838\u5fc3\u914d\u7f6e", this.coreConfigPanel);
        this.tabbedPane.addTab("\u5927\u6587\u4ef6\u914d\u7f6e", this.bigFilePanel);
        this.tabbedPane.addTab("UI\u914d\u7f6e", this.themesSplitPane);
        pluginSeting.keySet().forEach(k -> {
            try {
                JPanel panel = (JPanel)pluginSeting.get(k).newInstance();
                EasyI18N.installObject(panel);
                this.tabbedPane.addTab((String)k, panel);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        this.add(this.tabbedPane);
        automaticBindClick.bindJButtonClick(this, this);
        functions.setWindowSize(this, 1200, 500);
        this.setLocationRelativeTo(MainActivity.getFrame());
        EasyI18N.installObject(this);
        this.setVisible(true);
    }

    void initGlobalProxy() {
        this.globalProxyPanel = new SimplePanel();
        this.updateGlobalProxyButton = new JButton("\u4fdd\u5b58\u66f4\u65b0");
        this.globalProxyHostLabel = new JLabel("\u4ee3\u7406\u4e3b\u673a");
        this.globalProxyPortLabel = new JLabel("\u4ee3\u7406\u7aef\u53e3");
        this.globalProxyTypeLabel = new JLabel("\u4ee3\u7406\u7c7b\u578b");
        this.globalProxyHostTextField = new JTextField(Db.tryGetSetingValue("globalProxyHost", "127.0.0.1"), 10);
        this.globalProxyPortTextField = new JTextField(Db.tryGetSetingValue("globalProxyPort", "8888"), 7);
        this.globalProxyTypeComboBox = new JComboBox<String>(ApplicationContext.getAllProxy());
        this.globalProxyTypeComboBox.setSelectedItem(Db.tryGetSetingValue("globalProxyType", "NO_PROXY"));
        this.globalProxyTypeComboBox.removeItem("GLOBAL_PROXY");
        this.globalProxyPanel.setSetup(-270);
        this.globalProxyPanel.addX(this.globalProxyTypeLabel, this.globalProxyTypeComboBox);
        this.globalProxyPanel.addX(this.globalProxyHostLabel, this.globalProxyHostTextField);
        this.globalProxyPanel.addX(this.globalProxyPortLabel, this.globalProxyPortTextField);
        this.globalProxyPanel.addX(this.updateGlobalProxyButton);
    }

    void initSetFontPanel() {
        Font currentFont = ApplicationContext.getFont();
        this.setFontPanel = new JPanel(new GridBagLayout());
        this.fontNameComboBox = new JComboBox<String>(UiFunction.getAllFontName());
        this.fontTypeComboBox = new JComboBox<String>(UiFunction.getAllFontType());
        this.fontSizeComboBox = new JComboBox<String>(UiFunction.getAllFontSize());
        this.testFontLabel = new JLabel("\u4f60\u597d\tHello");
        this.currentFontLabel = new JLabel(functions.toString(currentFont));
        this.currentFontLLabel = new JLabel("\u5f53\u524d\u5b57\u4f53 : ");
        this.updateFontButton = new JButton("\u4fee\u6539");
        this.resetFontButton = new JButton("\u91cd\u7f6e");
        this.fontNameLabel = new JLabel("\u5b57\u4f53:    ");
        this.fontTypeLabel = new JLabel("\u5b57\u4f53\u7c7b\u578b : ");
        this.fontSizeLabel = new JLabel("\u5b57\u4f53\u5927\u5c0f : ");
        GBC gbcLFontName = new GBC(0, 0).setInsets(5, -40, 0, 0);
        GBC gbcFontName = new GBC(1, 0, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLFontType = new GBC(0, 1).setInsets(5, -40, 0, 0);
        GBC gbcFontType = new GBC(1, 1, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLFontSize = new GBC(0, 2).setInsets(5, -40, 0, 0);
        GBC gbcFontSize = new GBC(1, 2, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcLCurrentFont = new GBC(0, 3).setInsets(5, -40, 0, 0);
        GBC gbcCurrentFont = new GBC(1, 3, 3, 1).setInsets(5, 20, 0, 0);
        GBC gbcTestFont = new GBC(0, 4);
        GBC gbcUpdateFont = new GBC(2, 5).setInsets(5, -40, 0, 0);
        GBC gbcResetFont = new GBC(1, 5, 3, 1).setInsets(5, 20, 0, 0);
        this.setFontPanel.add((Component)this.fontNameLabel, gbcLFontName);
        this.setFontPanel.add(this.fontNameComboBox, gbcFontName);
        this.setFontPanel.add((Component)this.fontTypeLabel, gbcLFontType);
        this.setFontPanel.add(this.fontTypeComboBox, gbcFontType);
        this.setFontPanel.add((Component)this.fontSizeLabel, gbcLFontSize);
        this.setFontPanel.add(this.fontSizeComboBox, gbcFontSize);
        this.setFontPanel.add((Component)this.currentFontLLabel, gbcLCurrentFont);
        this.setFontPanel.add((Component)this.currentFontLabel, gbcCurrentFont);
        this.setFontPanel.add((Component)this.testFontLabel, gbcTestFont);
        this.setFontPanel.add((Component)this.updateFontButton, gbcUpdateFont);
        this.setFontPanel.add((Component)this.resetFontButton, gbcResetFont);
        this.fontNameComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                AppSeting.this.testFontLabel.setFont(AppSeting.this.getSelectFont());
            }
        });
        this.fontTypeComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                AppSeting.this.testFontLabel.setFont(AppSeting.this.getSelectFont());
            }
        });
        this.fontSizeComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent paramActionEvent) {
                AppSeting.this.testFontLabel.setFont(AppSeting.this.getSelectFont());
            }
        });
        if (currentFont != null) {
            this.fontNameComboBox.setSelectedItem(currentFont.getName());
            this.fontTypeComboBox.setSelectedItem(UiFunction.getFontType(currentFont));
            this.fontSizeComboBox.setSelectedItem(Integer.toString(currentFont.getSize()));
            this.testFontLabel.setFont(currentFont);
        }
    }

    void initHttpsCertConfig() {
        this.httpsCertConfigPanel = new SimplePanel();
        this.httpsCertConfigPanel.setSetup(200);
        this.httpsCertConfigExportButton = new JButton("\u5bfc\u51fa\u8bc1\u4e66");
        this.httpsCertConfigResetButton = new JButton("\u91cd\u7f6e\u8bc1\u4e66");
        this.httpsCertConfigPanel.addX(this.httpsCertConfigExportButton, this.httpsCertConfigResetButton);
    }

    void initGloballHttpHeader() {
        this.globallHttpHeaderPanel = new JPanel(new BorderLayout(1, 1));
        this.headerTextArea = new RTextArea();
        this.updateHeaderButton = new JButton("\u4fee\u6539");
        this.headerTextArea.setText(ApplicationContext.getGloballHttpHeader());
        Dimension dimension = new Dimension();
        dimension.height = 30;
        JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(0);
        JPanel bottomPanel = new JPanel();
        splitPane.setTopComponent(new JScrollPane(this.headerTextArea));
        bottomPanel.add(this.updateHeaderButton);
        bottomPanel.setMaximumSize(dimension);
        bottomPanel.setMinimumSize(dimension);
        splitPane.setBottomComponent(bottomPanel);
        splitPane.setResizeWeight(0.9);
        this.globallHttpHeaderPanel.add(splitPane);
    }

    public void initThemesPanel() {
        this.themesPanel = new IJThemesPanel();
        this.updateThemesButton = new JButton("\u4fee\u6539");
        this.themesSplitPane = new JSplitPane(0);
        this.themesSplitPane.setBottomComponent(this.updateThemesButton);
        this.themesSplitPane.setTopComponent(this.themesPanel);
        this.themesSplitPane.setResizeWeight(0.99);
    }

    void addCoreConfigPanelComponent(JLabel label, Component component) {
        GBC gbcl = new GBC(0, this.currentCoreConfigPanelComponent).setInsets(5, -40, 0, 0);
        GBC gbc = new GBC(1, this.currentCoreConfigPanelComponent, 3, 1).setInsets(5, 20, 0, 0);
        this.coreConfigPanel.add((Component)label, gbcl);
        this.coreConfigPanel.add(component, gbc);
        ++this.currentCoreConfigPanelComponent;
    }

    void initCoreConfigPanel() {
        this.coreConfigPanel = new JPanel(new GridBagLayout());
        this.godModeLabel = new JLabel("\u8fd0\u884c\u6a21\u5f0f: ");
        this.godModeCheckBox = new JCheckBox("\u4e0a\u5e1d\u6a21\u5f0f", ApplicationContext.isGodMode());
        this.execCommandModeLabel = new JLabel("\u547d\u4ee4\u6267\u884c\u6a21\u5f0f: ");
        this.execCommandModeComboBox = new JComboBox<String>(new String[]{"EASY", "KNIFE", "NO_MODE"});
        this.languageLabel = new JLabel("\u8bed\u8a00");
        this.languageComboBox = new JComboBox<String>(new String[]{"en", "zh"});
        this.isOpenCacheLabel = new JLabel("\u5f00\u542f\u7f13\u5b58");
        this.isOpenCacheCheckBox = new JCheckBox("\u5f00\u542f", ApplicationContext.isOpenCache());
        this.isSuperLogLabel = new JLabel("\u8be6\u7ec6\u65e5\u5fd7: ");
        this.isSuperLogCheckBox = new JCheckBox("\u5f00\u542f", ApplicationContext.isOpenC("isSuperLog"));
        this.isAutoCloseShellLabel = new JLabel("\u81ea\u52a8\u5173\u95edShell");
        this.isAutoCloseShellCheckBox = new JCheckBox("\u5f00\u542f", ApplicationContext.isOpenC("isAutoCloseShell"));
        this.superRequestLabel = new JLabel("\u8bf7\u6c42\u53c2\u6570\u914d\u7f6e: ");
        this.superRequestButton = new JButton("config");
        this.addCoreConfigPanelComponent(this.godModeLabel, this.godModeCheckBox);
        this.addCoreConfigPanelComponent(this.execCommandModeLabel, this.execCommandModeComboBox);
        this.addCoreConfigPanelComponent(this.languageLabel, this.languageComboBox);
        this.addCoreConfigPanelComponent(this.isSuperLogLabel, this.isSuperLogCheckBox);
        this.addCoreConfigPanelComponent(this.isOpenCacheLabel, this.isOpenCacheCheckBox);
        this.addCoreConfigPanelComponent(this.isAutoCloseShellLabel, this.isAutoCloseShellCheckBox);
        this.addCoreConfigPanelComponent(this.superRequestLabel, this.superRequestButton);
        this.isSuperLogCheckBox.addActionListener(new OpenC("isSuperLog", this.isSuperLogCheckBox));
        this.isAutoCloseShellCheckBox.addActionListener(new OpenC("isAutoCloseShell", this.isAutoCloseShellCheckBox));
        this.execCommandModeComboBox.setSelectedItem(Db.getSetingValue("EXEC_COMMAND_MODE", "EASY"));
        this.languageComboBox.setSelectedItem(Db.getSetingValue("language", "zh".equalsIgnoreCase(Locale.getDefault().getLanguage()) ? "zh" : "en"));
        this.godModeCheckBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ApplicationContext.setGodMode(AppSeting.this.godModeCheckBox.isSelected())) {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
                }
            }
        });
        this.execCommandModeComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Db.updateSetingKV("EXEC_COMMAND_MODE", AppSeting.this.execCommandModeComboBox.getSelectedItem().toString())) {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
                }
            }
        });
        this.languageComboBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Db.updateSetingKV("language", AppSeting.this.languageComboBox.getSelectedItem().toString())) {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
                }
            }
        });
        this.isOpenCacheCheckBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (ApplicationContext.setOpenCache(AppSeting.this.isOpenCacheCheckBox.isSelected())) {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
                } else {
                    GOptionPane.showMessageDialog(null, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
                }
            }
        });
    }

    void initBigFilePanel() {
        this.bigFilePanel = new SimplePanel();
        this.bigFileErrorRetryNumLabel = new JLabel("\u9519\u8bef\u91cd\u8bd5\u6700\u5927\u6b21\u6570: ");
        this.bigFileSendRequestSleepLabel = new JLabel("\u8bf7\u6c42\u6296\u52a8\u5ef6\u65f6(ms)");
        this.oneceBigFileDownloadByteNumLabel = new JLabel("\u4e0b\u8f7d\u5355\u6b21\u8bfb\u53d6\u5b57\u8282: ");
        this.oneceBigFileUploadByteNumLabel = new JLabel("\u4e0a\u4f20\u5355\u6b21\u8bfb\u53d6\u5b57\u8282: ");
        this.oneceBigFileDownloadByteNumTextField = new JTextField(String.valueOf(Db.getSetingIntValue("oneceBigFileDownloadByteNum", 0x100000)), 10);
        this.oneceBigFileUploadByteNumTextField = new JTextField(String.valueOf(Db.getSetingIntValue("oneceBigFileUploadByteNum", 0x100000)), 10);
        this.bigFileErrorRetryNumTextField = new JTextField(String.valueOf(Db.getSetingIntValue("bigFileErrorRetryNum", 10)));
        this.bigFileSendRequestSleepTextField = new JTextField(String.valueOf(Db.getSetingIntValue("bigFileSendRequestSleep", 521)));
        this.bigFileConfigSaveButton = new JButton("\u4fdd\u5b58\u914d\u7f6e");
        this.bigFilePanel.setSetup(-270);
        this.bigFilePanel.addX(this.bigFileErrorRetryNumLabel, this.bigFileErrorRetryNumTextField);
        this.bigFilePanel.addX(this.bigFileSendRequestSleepLabel, this.bigFileSendRequestSleepTextField);
        this.bigFilePanel.addX(this.oneceBigFileDownloadByteNumLabel, this.oneceBigFileDownloadByteNumTextField);
        this.bigFilePanel.addX(this.oneceBigFileUploadByteNumLabel, this.oneceBigFileUploadByteNumTextField);
        this.bigFilePanel.addX(this.bigFileConfigSaveButton);
    }

    public Font getSelectFont() {
        try {
            String fontName = (String)this.fontNameComboBox.getSelectedItem();
            String fontType = (String)this.fontTypeComboBox.getSelectedItem();
            int fontSize = Integer.parseInt((String)this.fontSizeComboBox.getSelectedItem());
            Font font = new Font(fontName, UiFunction.getFontType(fontType), fontSize);
            return font;
        } catch (Exception e) {
            Log.error(e);
            return null;
        }
    }

    private void updateFontButtonClick(ActionEvent actionEvent) {
        ApplicationContext.setFont(this.getSelectFont());
        GOptionPane.showMessageDialog(this, "\u4fee\u6539\u6210\u529f! \u91cd\u542f\u7a0b\u5e8f\u751f\u6548!", "\u63d0\u793a", 1);
    }

    private void resetFontButtonClick(ActionEvent actionEvent) {
        ApplicationContext.resetFont();
        GOptionPane.showMessageDialog(this, "\u91cd\u7f6e\u6210\u529f! \u91cd\u542f\u7a0b\u5e8f\u751f\u6548!", "\u63d0\u793a", 1);
    }

    private void updateHeaderButtonClick(ActionEvent actionEvent) {
        String header = this.headerTextArea.getText();
        if (ApplicationContext.updateGloballHttpHeader(header)) {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
        }
    }

    private void superRequestButtonClick(ActionEvent actionEvent) {
        ShellSuperRequest shellSuperRequest = new ShellSuperRequest();
    }

    private void updateGlobalProxyButtonClick(ActionEvent actionEvent) {
        try {
            String globalProxyHostString = this.globalProxyHostTextField.getText().trim();
            String globalProxyPortString = this.globalProxyPortTextField.getText().trim();
            String globalProxyTypeString = this.globalProxyTypeComboBox.getSelectedItem().toString().trim();
            Db.updateSetingKV("globalProxyType", globalProxyTypeString);
            Db.updateSetingKV("globalProxyHost", globalProxyHostString);
            Db.updateSetingKV("globalProxyPort", globalProxyPortString);
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
        }
    }

    private void httpsCertConfigExportButtonClick(ActionEvent actionEvent) throws Exception {
        byte[] cert = ApplicationContext.getHttpsCert().getEncoded();
        GFileChooser chooser = new GFileChooser();
        chooser.setFileSelectionMode(0);
        boolean flag = 0 == chooser.showDialog(new JLabel(), "\u9009\u62e9");
        File selectdFile = chooser.getSelectedFile();
        if (flag && selectdFile != null) {
            if (!selectdFile.getName().endsWith(".crt")) {
                selectdFile = new File(selectdFile.getCanonicalPath() + ".crt");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(selectdFile);
            fileOutputStream.write(cert);
            fileOutputStream.flush();
            fileOutputStream.close();
            GOptionPane.showMessageDialog(this, String.format("Succes! cert >> %s", selectdFile.getCanonicalPath()), "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(this, "\u672a\u9009\u4e2d\u6587\u4ef6\u8def\u5f84", "\u63d0\u793a", 2);
        }
    }

    private void httpsCertConfigResetButtonClick(ActionEvent actionEvent) throws Exception {
        try {
            ApplicationContext.genHttpsConfig();
            GOptionPane.showMessageDialog(this, "Succes!", "\u63d0\u793a", 1);
        } catch (Exception e) {
            GOptionPane.showMessageDialog(this, e.getMessage(), "\u63d0\u793a", 2);
        }
    }

    private void bigFileConfigSaveButtonClick(ActionEvent actionEvent) throws Exception {
        Db.updateSetingKV("oneceBigFileDownloadByteNum", this.oneceBigFileDownloadByteNumTextField.getText().trim());
        Db.updateSetingKV("oneceBigFileUploadByteNum", this.oneceBigFileUploadByteNumTextField.getText().trim());
        Db.updateSetingKV("bigFileErrorRetryNum", String.valueOf(this.bigFileErrorRetryNumTextField.getText().trim()));
        Db.updateSetingKV("bigFileSendRequestSleep", String.valueOf(this.bigFileSendRequestSleepTextField.getText().trim()));
        GOptionPane.showMessageDialog(this, "Succes!", "\u63d0\u793a", 1);
    }

    private void updateThemesButtonClick(ActionEvent actionEvent) {
        IJThemeInfo ijThemeInfo = this.themesPanel.getSelect();
        if (ijThemeInfo != null && ApplicationContext.saveUi(ijThemeInfo)) {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u6210\u529f!", "\u63d0\u793a", 1);
        } else {
            GOptionPane.showMessageDialog(this, "\u4fee\u6539\u5931\u8d25!", "\u63d0\u793a", 2);
        }
    }

    public static void registerPluginSeting(String tabName, Class<?> panelClass) {
        pluginSeting.put(tabName, panelClass);
    }
}

