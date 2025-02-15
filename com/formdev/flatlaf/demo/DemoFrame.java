/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.demo.BasicComponentsPanel;
import com.formdev.flatlaf.demo.ControlBar;
import com.formdev.flatlaf.demo.DataComponentsPanel;
import com.formdev.flatlaf.demo.DemoPrefs;
import com.formdev.flatlaf.demo.HintManager;
import com.formdev.flatlaf.demo.MoreComponentsPanel;
import com.formdev.flatlaf.demo.NewDialog;
import com.formdev.flatlaf.demo.OptionPanePanel;
import com.formdev.flatlaf.demo.TabsPanel;
import com.formdev.flatlaf.demo.extras.ExtrasPanel;
import com.formdev.flatlaf.demo.intellijthemes.IJThemesPanel;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import com.formdev.flatlaf.extras.FlatUIDefaultsInspector;
import com.formdev.flatlaf.ui.JBRCustomDecorations;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.prefs.Preferences;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleContext;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.layout.LC;
import net.miginfocom.layout.UnitValue;
import net.miginfocom.swing.MigLayout;

class DemoFrame
extends JFrame {
    private final String[] availableFontFamilyNames;
    private int initialFontMenuItemCount = -1;
    private JMenu fontMenu;
    private JMenu optionsMenu;
    private JCheckBoxMenuItem windowDecorationsCheckBoxMenuItem;
    private JCheckBoxMenuItem menuBarEmbeddedCheckBoxMenuItem;
    private JCheckBoxMenuItem underlineMenuSelectionMenuItem;
    private JCheckBoxMenuItem alwaysShowMnemonicsMenuItem;
    private JCheckBoxMenuItem animatedLafChangeMenuItem;
    private JTabbedPane tabbedPane;
    private ControlBar controlBar;
    IJThemesPanel themesPanel;

    DemoFrame() {
        int tabIndex = DemoPrefs.getState().getInt("tab", 0);
        this.availableFontFamilyNames = (String[])GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames().clone();
        Arrays.sort(this.availableFontFamilyNames);
        this.initComponents();
        this.updateFontMenuItems();
        this.controlBar.initialize(this, this.tabbedPane);
        this.setIconImages(FlatSVGUtils.createWindowIconImages("/com/formdev/flatlaf/demo/FlatLaf.svg"));
        if (tabIndex >= 0 && tabIndex < this.tabbedPane.getTabCount() && tabIndex != this.tabbedPane.getSelectedIndex()) {
            this.tabbedPane.setSelectedIndex(tabIndex);
        }
        SwingUtilities.invokeLater(() -> this.showHints());
    }

    @Override
    public void dispose() {
        super.dispose();
        FlatUIDefaultsInspector.hide();
    }

    private void showHints() {
        HintManager.Hint fontMenuHint = new HintManager.Hint("Use 'Font' menu to increase/decrease font size or try different fonts.", this.fontMenu, 3, "hint.fontMenu", null);
        HintManager.Hint optionsMenuHint = new HintManager.Hint("Use 'Options' menu to try out various FlatLaf options.", this.optionsMenu, 3, "hint.optionsMenu", fontMenuHint);
        HintManager.Hint themesHint = new HintManager.Hint("Use 'Themes' list to try out various themes.", this.themesPanel, 2, "hint.themesPanel", optionsMenuHint);
        HintManager.showHint(themesHint);
    }

    private void clearHints() {
        HintManager.hideAllHints();
        Preferences state = DemoPrefs.getState();
        state.remove("hint.fontMenu");
        state.remove("hint.optionsMenu");
        state.remove("hint.themesPanel");
    }

    private void showUIDefaultsInspector() {
        FlatUIDefaultsInspector.show();
    }

    private void newActionPerformed() {
        NewDialog newDialog = new NewDialog((Window)this);
        newDialog.setVisible(true);
    }

    private void openActionPerformed() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
    }

    private void saveAsActionPerformed() {
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(this);
    }

    private void exitActionPerformed() {
        this.dispose();
    }

    private void aboutActionPerformed() {
        JOptionPane.showMessageDialog(this, "FlatLaf Demo", "About", -1);
    }

    private void selectedTabChanged() {
        DemoPrefs.getState().putInt("tab", this.tabbedPane.getSelectedIndex());
    }

    private void menuItemActionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, e.getActionCommand(), "Menu Item", -1));
    }

    private void windowDecorationsChanged() {
        boolean windowDecorations = this.windowDecorationsCheckBoxMenuItem.isSelected();
        this.dispose();
        this.setUndecorated(windowDecorations);
        this.getRootPane().setWindowDecorationStyle(windowDecorations ? 1 : 0);
        this.menuBarEmbeddedCheckBoxMenuItem.setEnabled(windowDecorations);
        this.setVisible(true);
        JFrame.setDefaultLookAndFeelDecorated(windowDecorations);
        JDialog.setDefaultLookAndFeelDecorated(windowDecorations);
    }

    private void menuBarEmbeddedChanged() {
        this.getRootPane().putClientProperty("JRootPane.menuBarEmbedded", this.menuBarEmbeddedCheckBoxMenuItem.isSelected() ? null : Boolean.valueOf(false));
    }

    private void underlineMenuSelection() {
        UIManager.put("MenuItem.selectionType", this.underlineMenuSelectionMenuItem.isSelected() ? "underline" : null);
    }

    private void alwaysShowMnemonics() {
        UIManager.put("Component.hideMnemonics", !this.alwaysShowMnemonicsMenuItem.isSelected());
        this.repaint();
    }

    private void animatedLafChangeChanged() {
        System.setProperty("flatlaf.animatedLafChange", String.valueOf(this.animatedLafChangeMenuItem.isSelected()));
    }

    private void showHintsChanged() {
        this.clearHints();
        this.showHints();
    }

    private void fontFamilyChanged(ActionEvent e) {
        String fontFamily = e.getActionCommand();
        FlatAnimatedLafChange.showSnapshot();
        Font font = UIManager.getFont("defaultFont");
        Font newFont = StyleContext.getDefaultStyleContext().getFont(fontFamily, font.getStyle(), font.getSize());
        UIManager.put("defaultFont", newFont);
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    private void fontSizeChanged(ActionEvent e) {
        String fontSizeStr = e.getActionCommand();
        Font font = UIManager.getFont("defaultFont");
        Font newFont = font.deriveFont((float)Integer.parseInt(fontSizeStr));
        UIManager.put("defaultFont", newFont);
        FlatLaf.updateUI();
    }

    private void restoreFont() {
        UIManager.put("defaultFont", null);
        this.updateFontMenuItems();
        FlatLaf.updateUI();
    }

    private void incrFont() {
        Font font = UIManager.getFont("defaultFont");
        Font newFont = font.deriveFont((float)(font.getSize() + 1));
        UIManager.put("defaultFont", newFont);
        this.updateFontMenuItems();
        FlatLaf.updateUI();
    }

    private void decrFont() {
        Font font = UIManager.getFont("defaultFont");
        Font newFont = font.deriveFont((float)Math.max(font.getSize() - 1, 10));
        UIManager.put("defaultFont", newFont);
        this.updateFontMenuItems();
        FlatLaf.updateUI();
    }

    void updateFontMenuItems() {
        if (this.initialFontMenuItemCount < 0) {
            this.initialFontMenuItemCount = this.fontMenu.getItemCount();
        } else {
            for (int i = this.fontMenu.getItemCount() - 1; i >= this.initialFontMenuItemCount; --i) {
                this.fontMenu.remove(i);
            }
        }
        Font currentFont = UIManager.getFont("Label.font");
        String currentFamily = currentFont.getFamily();
        String currentSize = Integer.toString(currentFont.getSize());
        this.fontMenu.addSeparator();
        ArrayList<String> families = new ArrayList<String>(Arrays.asList("Arial", "Cantarell", "Comic Sans MS", "Courier New", "DejaVu Sans", "Dialog", "Liberation Sans", "Monospaced", "Noto Sans", "Roboto", "SansSerif", "Segoe UI", "Serif", "Tahoma", "Ubuntu", "Verdana"));
        if (!families.contains(currentFamily)) {
            families.add(currentFamily);
        }
        families.sort(String.CASE_INSENSITIVE_ORDER);
        ButtonGroup familiesGroup = new ButtonGroup();
        for (String family : families) {
            if (Arrays.binarySearch(this.availableFontFamilyNames, family) < 0) continue;
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(family);
            item.setSelected(family.equals(currentFamily));
            item.addActionListener(this::fontFamilyChanged);
            this.fontMenu.add(item);
            familiesGroup.add(item);
        }
        this.fontMenu.addSeparator();
        ArrayList<String> sizes = new ArrayList<String>(Arrays.asList("10", "12", "14", "16", "18", "20", "24", "28"));
        if (!sizes.contains(currentSize)) {
            sizes.add(currentSize);
        }
        sizes.sort(String.CASE_INSENSITIVE_ORDER);
        ButtonGroup sizesGroup = new ButtonGroup();
        for (String size : sizes) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(size);
            item.setSelected(size.equals(currentSize));
            item.addActionListener(this::fontSizeChanged);
            this.fontMenu.add(item);
            sizesGroup.add(item);
        }
        boolean enabled = UIManager.getLookAndFeel() instanceof FlatLaf;
        for (Component item : this.fontMenu.getMenuComponents()) {
            item.setEnabled(enabled);
        }
    }

    private void initComponents() {
        JMenuBar menuBar1 = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenuItem newMenuItem = new JMenuItem();
        JMenuItem openMenuItem = new JMenuItem();
        JMenuItem saveAsMenuItem = new JMenuItem();
        JMenuItem closeMenuItem = new JMenuItem();
        JMenuItem exitMenuItem = new JMenuItem();
        JMenu editMenu = new JMenu();
        JMenuItem undoMenuItem = new JMenuItem();
        JMenuItem redoMenuItem = new JMenuItem();
        JMenuItem cutMenuItem = new JMenuItem();
        JMenuItem copyMenuItem = new JMenuItem();
        JMenuItem pasteMenuItem = new JMenuItem();
        JMenuItem deleteMenuItem = new JMenuItem();
        JMenu viewMenu = new JMenu();
        JCheckBoxMenuItem checkBoxMenuItem1 = new JCheckBoxMenuItem();
        JMenu menu1 = new JMenu();
        JMenu subViewsMenu = new JMenu();
        JMenu subSubViewsMenu = new JMenu();
        JMenuItem errorLogViewMenuItem = new JMenuItem();
        JMenuItem searchViewMenuItem = new JMenuItem();
        JMenuItem projectViewMenuItem = new JMenuItem();
        JMenuItem structureViewMenuItem = new JMenuItem();
        JMenuItem propertiesViewMenuItem = new JMenuItem();
        JMenuItem menuItem2 = new JMenuItem();
        JMenuItem menuItem1 = new JMenuItem();
        JRadioButtonMenuItem radioButtonMenuItem1 = new JRadioButtonMenuItem();
        JRadioButtonMenuItem radioButtonMenuItem2 = new JRadioButtonMenuItem();
        JRadioButtonMenuItem radioButtonMenuItem3 = new JRadioButtonMenuItem();
        this.fontMenu = new JMenu();
        JMenuItem restoreFontMenuItem = new JMenuItem();
        JMenuItem incrFontMenuItem = new JMenuItem();
        JMenuItem decrFontMenuItem = new JMenuItem();
        this.optionsMenu = new JMenu();
        this.windowDecorationsCheckBoxMenuItem = new JCheckBoxMenuItem();
        this.menuBarEmbeddedCheckBoxMenuItem = new JCheckBoxMenuItem();
        this.underlineMenuSelectionMenuItem = new JCheckBoxMenuItem();
        this.alwaysShowMnemonicsMenuItem = new JCheckBoxMenuItem();
        this.animatedLafChangeMenuItem = new JCheckBoxMenuItem();
        JMenuItem showHintsMenuItem = new JMenuItem();
        JMenuItem showUIDefaultsInspectorMenuItem = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();
        JToolBar toolBar1 = new JToolBar();
        JButton backButton = new JButton();
        JButton forwardButton = new JButton();
        JButton cutButton = new JButton();
        JButton copyButton = new JButton();
        JButton pasteButton = new JButton();
        JButton refreshButton = new JButton();
        JToggleButton showToggleButton = new JToggleButton();
        JPanel contentPanel = new JPanel();
        this.tabbedPane = new JTabbedPane();
        BasicComponentsPanel basicComponentsPanel = new BasicComponentsPanel();
        MoreComponentsPanel moreComponentsPanel = new MoreComponentsPanel();
        DataComponentsPanel dataComponentsPanel = new DataComponentsPanel();
        TabsPanel tabsPanel = new TabsPanel();
        OptionPanePanel optionPanePanel = new OptionPanePanel();
        ExtrasPanel extrasPanel1 = new ExtrasPanel();
        this.controlBar = new ControlBar();
        this.themesPanel = new IJThemesPanel();
        this.setTitle("FlatLaf Demo");
        this.setDefaultCloseOperation(2);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        fileMenu.setText("File");
        fileMenu.setMnemonic('F');
        newMenuItem.setText("New");
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(78, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        newMenuItem.setMnemonic('N');
        newMenuItem.addActionListener(e -> this.newActionPerformed());
        fileMenu.add(newMenuItem);
        openMenuItem.setText("Open...");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(79, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        openMenuItem.setMnemonic('O');
        openMenuItem.addActionListener(e -> this.openActionPerformed());
        fileMenu.add(openMenuItem);
        saveAsMenuItem.setText("Save As...");
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(83, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        saveAsMenuItem.setMnemonic('S');
        saveAsMenuItem.addActionListener(e -> this.saveAsActionPerformed());
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        closeMenuItem.setText("Close");
        closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(87, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        closeMenuItem.setMnemonic('C');
        closeMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        fileMenu.add(closeMenuItem);
        fileMenu.addSeparator();
        exitMenuItem.setText("Exit");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(81, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        exitMenuItem.setMnemonic('X');
        exitMenuItem.addActionListener(e -> this.exitActionPerformed());
        fileMenu.add(exitMenuItem);
        menuBar1.add(fileMenu);
        editMenu.setText("Edit");
        editMenu.setMnemonic('E');
        undoMenuItem.setText("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(90, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        undoMenuItem.setMnemonic('U');
        undoMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        editMenu.add(undoMenuItem);
        redoMenuItem.setText("Redo");
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(89, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        redoMenuItem.setMnemonic('R');
        redoMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        editMenu.add(redoMenuItem);
        editMenu.addSeparator();
        cutMenuItem.setText("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(88, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        cutMenuItem.setMnemonic('C');
        editMenu.add(cutMenuItem);
        copyMenuItem.setText("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(67, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        copyMenuItem.setMnemonic('O');
        editMenu.add(copyMenuItem);
        pasteMenuItem.setText("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(86, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        pasteMenuItem.setMnemonic('P');
        editMenu.add(pasteMenuItem);
        editMenu.addSeparator();
        deleteMenuItem.setText("Delete");
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(127, 0));
        deleteMenuItem.setMnemonic('D');
        deleteMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        editMenu.add(deleteMenuItem);
        menuBar1.add(editMenu);
        viewMenu.setText("View");
        viewMenu.setMnemonic('V');
        checkBoxMenuItem1.setText("Show Toolbar");
        checkBoxMenuItem1.setSelected(true);
        checkBoxMenuItem1.setMnemonic('T');
        checkBoxMenuItem1.addActionListener(e -> this.menuItemActionPerformed(e));
        viewMenu.add(checkBoxMenuItem1);
        menu1.setText("Show View");
        menu1.setMnemonic('V');
        subViewsMenu.setText("Sub Views");
        subViewsMenu.setMnemonic('S');
        subSubViewsMenu.setText("Sub sub Views");
        subSubViewsMenu.setMnemonic('U');
        errorLogViewMenuItem.setText("Error Log");
        errorLogViewMenuItem.setMnemonic('E');
        errorLogViewMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        subSubViewsMenu.add(errorLogViewMenuItem);
        subViewsMenu.add(subSubViewsMenu);
        searchViewMenuItem.setText("Search");
        searchViewMenuItem.setMnemonic('S');
        searchViewMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        subViewsMenu.add(searchViewMenuItem);
        menu1.add(subViewsMenu);
        projectViewMenuItem.setText("Project");
        projectViewMenuItem.setMnemonic('P');
        projectViewMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        menu1.add(projectViewMenuItem);
        structureViewMenuItem.setText("Structure");
        structureViewMenuItem.setMnemonic('T');
        structureViewMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        menu1.add(structureViewMenuItem);
        propertiesViewMenuItem.setText("Properties");
        propertiesViewMenuItem.setMnemonic('O');
        propertiesViewMenuItem.addActionListener(e -> this.menuItemActionPerformed(e));
        menu1.add(propertiesViewMenuItem);
        viewMenu.add(menu1);
        menuItem2.setText("Disabled Item");
        menuItem2.setEnabled(false);
        viewMenu.add(menuItem2);
        menuItem1.setText("<html>some <b color=\"red\">HTML</b> <i color=\"blue\">text</i></html>");
        viewMenu.add(menuItem1);
        viewMenu.addSeparator();
        radioButtonMenuItem1.setText("Details");
        radioButtonMenuItem1.setSelected(true);
        radioButtonMenuItem1.setMnemonic('D');
        radioButtonMenuItem1.addActionListener(e -> this.menuItemActionPerformed(e));
        viewMenu.add(radioButtonMenuItem1);
        radioButtonMenuItem2.setText("Small Icons");
        radioButtonMenuItem2.setMnemonic('S');
        radioButtonMenuItem2.addActionListener(e -> this.menuItemActionPerformed(e));
        viewMenu.add(radioButtonMenuItem2);
        radioButtonMenuItem3.setText("Large Icons");
        radioButtonMenuItem3.setMnemonic('L');
        radioButtonMenuItem3.addActionListener(e -> this.menuItemActionPerformed(e));
        viewMenu.add(radioButtonMenuItem3);
        menuBar1.add(viewMenu);
        this.fontMenu.setText("Font");
        restoreFontMenuItem.setText("Restore Font");
        restoreFontMenuItem.setAccelerator(KeyStroke.getKeyStroke(48, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        restoreFontMenuItem.addActionListener(e -> this.restoreFont());
        this.fontMenu.add(restoreFontMenuItem);
        incrFontMenuItem.setText("Increase Font Size");
        incrFontMenuItem.setAccelerator(KeyStroke.getKeyStroke(521, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        incrFontMenuItem.addActionListener(e -> this.incrFont());
        this.fontMenu.add(incrFontMenuItem);
        decrFontMenuItem.setText("Decrease Font Size");
        decrFontMenuItem.setAccelerator(KeyStroke.getKeyStroke(45, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        decrFontMenuItem.addActionListener(e -> this.decrFont());
        this.fontMenu.add(decrFontMenuItem);
        menuBar1.add(this.fontMenu);
        this.optionsMenu.setText("Options");
        this.windowDecorationsCheckBoxMenuItem.setText("Window decorations");
        this.windowDecorationsCheckBoxMenuItem.setSelected(true);
        this.windowDecorationsCheckBoxMenuItem.addActionListener(e -> this.windowDecorationsChanged());
        this.optionsMenu.add(this.windowDecorationsCheckBoxMenuItem);
        this.menuBarEmbeddedCheckBoxMenuItem.setText("Embedded menu bar");
        this.menuBarEmbeddedCheckBoxMenuItem.setSelected(true);
        this.menuBarEmbeddedCheckBoxMenuItem.addActionListener(e -> this.menuBarEmbeddedChanged());
        this.optionsMenu.add(this.menuBarEmbeddedCheckBoxMenuItem);
        this.underlineMenuSelectionMenuItem.setText("Use underline menu selection");
        this.underlineMenuSelectionMenuItem.addActionListener(e -> this.underlineMenuSelection());
        this.optionsMenu.add(this.underlineMenuSelectionMenuItem);
        this.alwaysShowMnemonicsMenuItem.setText("Always show mnemonics");
        this.alwaysShowMnemonicsMenuItem.addActionListener(e -> this.alwaysShowMnemonics());
        this.optionsMenu.add(this.alwaysShowMnemonicsMenuItem);
        this.animatedLafChangeMenuItem.setText("Animated Laf Change");
        this.animatedLafChangeMenuItem.setSelected(true);
        this.animatedLafChangeMenuItem.addActionListener(e -> this.animatedLafChangeChanged());
        this.optionsMenu.add(this.animatedLafChangeMenuItem);
        showHintsMenuItem.setText("Show hints");
        showHintsMenuItem.addActionListener(e -> this.showHintsChanged());
        this.optionsMenu.add(showHintsMenuItem);
        showUIDefaultsInspectorMenuItem.setText("Show UI Defaults Inspector");
        showUIDefaultsInspectorMenuItem.addActionListener(e -> this.showUIDefaultsInspector());
        this.optionsMenu.add(showUIDefaultsInspectorMenuItem);
        menuBar1.add(this.optionsMenu);
        helpMenu.setText("Help");
        helpMenu.setMnemonic('H');
        aboutMenuItem.setText("About");
        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.addActionListener(e -> this.aboutActionPerformed());
        helpMenu.add(aboutMenuItem);
        menuBar1.add(helpMenu);
        this.setJMenuBar(menuBar1);
        toolBar1.setMargin(new Insets(3, 3, 3, 3));
        backButton.setToolTipText("Back");
        toolBar1.add(backButton);
        forwardButton.setToolTipText("Forward");
        toolBar1.add(forwardButton);
        toolBar1.addSeparator();
        cutButton.setToolTipText("Cut");
        toolBar1.add(cutButton);
        copyButton.setToolTipText("Copy");
        toolBar1.add(copyButton);
        pasteButton.setToolTipText("Paste");
        toolBar1.add(pasteButton);
        toolBar1.addSeparator();
        refreshButton.setToolTipText("Refresh");
        toolBar1.add(refreshButton);
        toolBar1.addSeparator();
        showToggleButton.setSelected(true);
        showToggleButton.setToolTipText("Show Details");
        toolBar1.add(showToggleButton);
        contentPane.add((Component)toolBar1, "North");
        contentPanel.setLayout(new MigLayout("insets dialog,hidemode 3", "[grow,fill]", "[grow,fill]"));
        this.tabbedPane.setTabLayoutPolicy(1);
        this.tabbedPane.addChangeListener(e -> this.selectedTabChanged());
        this.tabbedPane.addTab("Basic Components", basicComponentsPanel);
        this.tabbedPane.addTab("More Components", moreComponentsPanel);
        this.tabbedPane.addTab("Data Components", dataComponentsPanel);
        this.tabbedPane.addTab("Tabs", tabsPanel);
        this.tabbedPane.addTab("Option Pane", optionPanePanel);
        this.tabbedPane.addTab("Extras", extrasPanel1);
        contentPanel.add((Component)this.tabbedPane, "cell 0 0");
        contentPane.add((Component)contentPanel, "Center");
        contentPane.add((Component)this.controlBar, "South");
        contentPane.add((Component)this.themesPanel, "East");
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButtonMenuItem1);
        buttonGroup1.add(radioButtonMenuItem2);
        buttonGroup1.add(radioButtonMenuItem3);
        undoMenuItem.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/undo.svg"));
        redoMenuItem.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/redo.svg"));
        cutMenuItem.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/menu-cut.svg"));
        copyMenuItem.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/copy.svg"));
        pasteMenuItem.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/menu-paste.svg"));
        backButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/back.svg"));
        forwardButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/forward.svg"));
        cutButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/menu-cut.svg"));
        copyButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/copy.svg"));
        pasteButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/menu-paste.svg"));
        refreshButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/refresh.svg"));
        showToggleButton.setIcon(new FlatSVGIcon("com/formdev/flatlaf/demo/icons/show.svg"));
        cutMenuItem.addActionListener(new DefaultEditorKit.CutAction());
        copyMenuItem.addActionListener(new DefaultEditorKit.CopyAction());
        pasteMenuItem.addActionListener(new DefaultEditorKit.PasteAction());
        boolean supportsWindowDecorations = UIManager.getLookAndFeel().getSupportsWindowDecorations() || JBRCustomDecorations.isSupported();
        this.windowDecorationsCheckBoxMenuItem.setEnabled(supportsWindowDecorations && !JBRCustomDecorations.isSupported());
        this.menuBarEmbeddedCheckBoxMenuItem.setEnabled(supportsWindowDecorations);
        MigLayout layout = (MigLayout)contentPanel.getLayout();
        LC lc = ConstraintParser.parseLayoutConstraint((String)layout.getLayoutConstraints());
        UnitValue[] insets = lc.getInsets();
        lc.setInsets(new UnitValue[]{insets[0], insets[1], new UnitValue(0.0f, 0, null), insets[3]});
        layout.setLayoutConstraints(lc);
    }
}

