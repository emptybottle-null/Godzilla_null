/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.intellijthemes;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.demo.DemoPrefs;
import com.formdev.flatlaf.demo.intellijthemes.IJThemeInfo;
import com.formdev.flatlaf.demo.intellijthemes.IJThemesManager;
import com.formdev.flatlaf.demo.intellijthemes.ListCellTitledBorder;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import net.miginfocom.swing.MigLayout;

public class IJThemesPanel
extends JPanel {
    public static final String THEMES_PACKAGE = "/com/formdev/flatlaf/intellijthemes/themes/";
    private final IJThemesManager themesManager = new IJThemesManager();
    private final List<IJThemeInfo> themes = new ArrayList<IJThemeInfo>();
    private final HashMap<Integer, String> categories = new HashMap();
    private final PropertyChangeListener lafListener = this::lafChanged;
    private final WindowListener windowListener = new WindowAdapter(){

        @Override
        public void windowActivated(WindowEvent e) {
            IJThemesPanel.this.windowActivated();
        }
    };
    private Window window;
    private File lastDirectory;
    private boolean isAdjustingThemesList;
    private JToolBar toolBar;
    private JButton saveButton;
    private JButton sourceCodeButton;
    private JComboBox<String> filterComboBox;
    private JScrollPane themesScrollPane;
    private JList<IJThemeInfo> themesList;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        IJThemesPanel ijThemesPanel = new IJThemesPanel();
        frame.setContentPane(ijThemesPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(2);
    }

    public IJThemesPanel() {
        this.initComponents();
        this.saveButton.setEnabled(false);
        this.sourceCodeButton.setEnabled(false);
        this.saveButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/download.svg"));
        this.sourceCodeButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/github.svg"));
        this.themesList.setCellRenderer(new DefaultListCellRenderer(){

            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                String title = (String)IJThemesPanel.this.categories.get(index);
                String name = ((IJThemeInfo)value).name;
                int sep = name.indexOf(47);
                if (sep >= 0) {
                    name = name.substring(sep + 1).trim();
                }
                JComponent c = (JComponent)super.getListCellRendererComponent(list, name, index, isSelected, cellHasFocus);
                c.setToolTipText(this.buildToolTip((IJThemeInfo)value));
                if (title != null) {
                    c.setBorder(new CompoundBorder(new ListCellTitledBorder(IJThemesPanel.this.themesList, title), c.getBorder()));
                }
                return c;
            }

            private String buildToolTip(IJThemeInfo ti) {
                if (ti.themeFile != null) {
                    return ti.themeFile.getPath();
                }
                if (ti.resourceName == null) {
                    return ti.name;
                }
                return "Name: " + ti.name + "\nLicense: " + ti.license + "\nSource Code: " + ti.sourceCodeUrl;
            }
        });
        this.updateThemesList();
    }

    /*
     * WARNING - void declaration
     */
    private void updateThemesList() {
        Rectangle bounds;
        int n;
        int filterLightDark = this.filterComboBox.getSelectedIndex();
        boolean showLight = filterLightDark != 2;
        boolean showDark = filterLightDark != 1;
        this.themesManager.loadBundledThemes();
        this.themesManager.loadThemesFromDirectory();
        Comparator comparator = (t1, t2) -> t1.name.compareToIgnoreCase(t2.name);
        this.themesManager.bundledThemes.sort(comparator);
        this.themesManager.moreThemes.sort(comparator);
        IJThemeInfo oldSel = this.themesList.getSelectedValue();
        this.themes.clear();
        this.categories.clear();
        this.categories.put(this.themes.size(), "Core Themes");
        if (showLight) {
            this.themes.add(new IJThemeInfo("Flat Light", null, false, null, null, null, null, null, FlatLightLaf.class.getName()));
        }
        if (showDark) {
            this.themes.add(new IJThemeInfo("Flat Dark", null, true, null, null, null, null, null, FlatDarkLaf.class.getName()));
        }
        if (showLight) {
            this.themes.add(new IJThemeInfo("Flat IntelliJ", null, false, null, null, null, null, null, FlatIntelliJLaf.class.getName()));
        }
        if (showDark) {
            this.themes.add(new IJThemeInfo("Flat Darcula", null, true, null, null, null, null, null, FlatDarculaLaf.class.getName()));
        }
        this.categories.put(this.themes.size(), "Current Directory");
        this.themes.addAll(this.themesManager.moreThemes);
        this.categories.put(this.themes.size(), "IntelliJ Themes");
        for (IJThemeInfo iJThemeInfo : this.themesManager.bundledThemes) {
            boolean show = showLight && !iJThemeInfo.dark || showDark && iJThemeInfo.dark;
            if (!show || iJThemeInfo.name.contains("/")) continue;
            this.themes.add(iJThemeInfo);
        }
        String lastCategory = null;
        for (IJThemeInfo ti : this.themesManager.bundledThemes) {
            boolean show = showLight && !ti.dark || showDark && ti.dark;
            int sep = ti.name.indexOf(47);
            if (!show || sep < 0) continue;
            String category = ti.name.substring(0, sep).trim();
            if (!Objects.equals(lastCategory, category)) {
                lastCategory = category;
                this.categories.put(this.themes.size(), category);
            }
            this.themes.add(ti);
        }
        this.themesList.setModel((ListModel<IJThemeInfo>)new AbstractListModel<IJThemeInfo>(){

            @Override
            public int getSize() {
                return IJThemesPanel.this.themes.size();
            }

            @Override
            public IJThemeInfo getElementAt(int index) {
                return (IJThemeInfo)IJThemesPanel.this.themes.get(index);
            }
        });
        if (oldSel != null) {
            void var7_10;
            boolean bl = false;
            while (var7_10 < this.themes.size()) {
                IJThemeInfo theme = this.themes.get((int)var7_10);
                if (oldSel.name.equals(theme.name) && Objects.equals(oldSel.resourceName, theme.resourceName) && Objects.equals(oldSel.themeFile, theme.themeFile) && Objects.equals(oldSel.lafClassName, theme.lafClassName)) {
                    this.themesList.setSelectedIndex((int)var7_10);
                    break;
                }
                ++var7_10;
            }
            if (this.themesList.getSelectedIndex() < 0) {
                this.themesList.setSelectedIndex(0);
            }
        }
        if ((n = this.themesList.getSelectedIndex()) >= 0 && (bounds = this.themesList.getCellBounds(n, n)) != null) {
            this.themesList.scrollRectToVisible(bounds);
        }
    }

    public void selectPreviousTheme() {
        int sel = this.themesList.getSelectedIndex();
        if (sel > 0) {
            this.themesList.setSelectedIndex(sel - 1);
        }
    }

    public void selectNextTheme() {
        int sel = this.themesList.getSelectedIndex();
        this.themesList.setSelectedIndex(sel + 1);
    }

    private void themesListValueChanged(ListSelectionEvent e) {
        IJThemeInfo themeInfo = this.themesList.getSelectedValue();
        boolean bundledTheme = themeInfo != null && themeInfo.resourceName != null;
        this.saveButton.setEnabled(bundledTheme);
        this.sourceCodeButton.setEnabled(bundledTheme);
        if (e.getValueIsAdjusting() || this.isAdjustingThemesList) {
            return;
        }
        EventQueue.invokeLater(() -> IJThemesPanel.setTheme(themeInfo));
    }

    public static void setTheme(IJThemeInfo themeInfo) {
        if (themeInfo == null) {
            return;
        }
        if (themeInfo.lafClassName != null) {
            if (themeInfo.lafClassName.equals(UIManager.getLookAndFeel().getClass().getName())) {
                return;
            }
            FlatAnimatedLafChange.showSnapshot();
            try {
                UIManager.setLookAndFeel(themeInfo.lafClassName);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (themeInfo.themeFile != null) {
            FlatAnimatedLafChange.showSnapshot();
            try {
                if (themeInfo.themeFile.getName().endsWith(".properties")) {
                    FlatLaf.install(new FlatPropertiesLaf(themeInfo.name, themeInfo.themeFile));
                } else {
                    FlatLaf.install(IntelliJTheme.createLaf(new FileInputStream(themeInfo.themeFile)));
                }
                DemoPrefs.getState().put("lafTheme", "file:" + themeInfo.themeFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            FlatAnimatedLafChange.showSnapshot();
            IntelliJTheme.install(IJThemesPanel.class.getResourceAsStream(THEMES_PACKAGE + themeInfo.resourceName));
        }
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    public IJThemeInfo getSelect() {
        return this.themesList.getSelectedValue();
    }

    private void saveTheme() {
    }

    private void browseSourceCode() {
        IJThemeInfo themeInfo = this.themesList.getSelectedValue();
        if (themeInfo == null || themeInfo.resourceName == null) {
            return;
        }
        String themeUrl = (themeInfo.sourceCodeUrl + '/' + themeInfo.sourceCodePath).replace(" ", "%20");
        try {
            Desktop.getDesktop().browse(new URI(themeUrl));
        } catch (IOException | URISyntaxException ex) {
            this.showInformationDialog("Failed to browse '" + themeUrl + "'.", ex);
        }
    }

    private void showInformationDialog(String message, Exception ex) {
        JOptionPane.showMessageDialog(SwingUtilities.windowForComponent(this), message + "\n\n" + ex.getMessage(), "FlatLaf", 1);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.selectedCurrentLookAndFeel();
        UIManager.addPropertyChangeListener(this.lafListener);
        this.window = SwingUtilities.windowForComponent(this);
        if (this.window != null) {
            this.window.addWindowListener(this.windowListener);
        }
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        UIManager.removePropertyChangeListener(this.lafListener);
        if (this.window != null) {
            this.window.removeWindowListener(this.windowListener);
            this.window = null;
        }
    }

    private void lafChanged(PropertyChangeEvent e) {
        if ("lookAndFeel".equals(e.getPropertyName())) {
            this.selectedCurrentLookAndFeel();
        }
    }

    private void windowActivated() {
        if (this.themesManager.hasThemesFromDirectoryChanged()) {
            this.updateThemesList();
        }
    }

    private void selectedCurrentLookAndFeel() {
        Predicate<IJThemeInfo> test;
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        String theme = UIManager.getLookAndFeelDefaults().getString("__FlatLaf.demo.theme");
        if (theme == null && (lookAndFeel instanceof IntelliJTheme.ThemeLaf || lookAndFeel instanceof FlatPropertiesLaf)) {
            return;
        }
        if (theme != null && theme.startsWith("res:")) {
            String resourceName = theme.substring("res:".length());
            test = ti -> Objects.equals(ti.resourceName, resourceName);
        } else if (theme != null && theme.startsWith("file:")) {
            File themeFile = new File(theme.substring("file:".length()));
            test = ti -> Objects.equals(ti.themeFile, themeFile);
        } else {
            String lafClassName = lookAndFeel.getClass().getName();
            test = ti -> Objects.equals(ti.lafClassName, lafClassName);
        }
        int newSel = -1;
        for (int i = 0; i < this.themes.size(); ++i) {
            if (!test.test(this.themes.get(i))) continue;
            newSel = i;
            break;
        }
        this.isAdjustingThemesList = true;
        if (newSel >= 0) {
            if (newSel != this.themesList.getSelectedIndex()) {
                this.themesList.setSelectedIndex(newSel);
            }
        } else {
            this.themesList.clearSelection();
        }
        this.isAdjustingThemesList = false;
    }

    private void filterChanged() {
        this.updateThemesList();
    }

    private void initComponents() {
        JLabel themesLabel = new JLabel();
        this.toolBar = new JToolBar();
        this.saveButton = new JButton();
        this.sourceCodeButton = new JButton();
        this.filterComboBox = new JComboBox();
        this.themesScrollPane = new JScrollPane();
        this.themesList = new JList();
        this.setLayout(new MigLayout("insets dialog,hidemode 3", "[grow,fill]", "[]3[grow,fill]"));
        themesLabel.setText("Themes:");
        this.add((Component)themesLabel, "cell 0 0");
        this.toolBar.setFloatable(false);
        this.saveButton.setToolTipText("Save .theme.json of selected IntelliJ theme to file.");
        this.saveButton.addActionListener(e -> this.saveTheme());
        this.toolBar.add(this.saveButton);
        this.sourceCodeButton.setToolTipText("Opens the source code repository of selected IntelliJ theme in the browser.");
        this.sourceCodeButton.addActionListener(e -> this.browseSourceCode());
        this.toolBar.add(this.sourceCodeButton);
        this.add((Component)this.toolBar, "cell 0 0,alignx right,growx 0");
        this.filterComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"all", "light", "dark"}));
        this.filterComboBox.putClientProperty("JComponent.minimumWidth", 0);
        this.filterComboBox.setFocusable(false);
        this.filterComboBox.addActionListener(e -> this.filterChanged());
        this.add(this.filterComboBox, "cell 0 0,alignx right,growx 0");
        this.themesList.setSelectionMode(0);
        this.themesList.addListSelectionListener(e -> this.themesListValueChanged(e));
        this.themesScrollPane.setViewportView(this.themesList);
        this.add((Component)this.themesScrollPane, "cell 0 1");
    }
}

