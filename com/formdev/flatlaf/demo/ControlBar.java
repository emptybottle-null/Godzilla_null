/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.demo.DemoFrame;
import com.formdev.flatlaf.demo.LookAndFeelsComboBox;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.util.SystemInfo;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import net.miginfocom.layout.ConstraintParser;
import net.miginfocom.layout.LC;
import net.miginfocom.layout.UnitValue;
import net.miginfocom.swing.MigLayout;

class ControlBar
extends JPanel {
    private DemoFrame frame;
    private JTabbedPane tabbedPane;
    private JSeparator separator1;
    private LookAndFeelsComboBox lookAndFeelComboBox;
    private JCheckBox rightToLeftCheckBox;
    private JCheckBox enabledCheckBox;
    private JLabel infoLabel;
    private JButton closeButton;

    ControlBar() {
        UIManager.LookAndFeelInfo[] lookAndFeels;
        this.initComponents();
        MigLayout layout = (MigLayout)this.getLayout();
        LC lc = ConstraintParser.parseLayoutConstraint((String)layout.getLayoutConstraints());
        UnitValue[] insets = lc.getInsets();
        lc.setInsets(new UnitValue[]{new UnitValue(0.0f, 0, null), insets[1], insets[2], insets[3]});
        layout.setLayoutConstraints(lc);
        DefaultComboBoxModel<UIManager.LookAndFeelInfo> lafModel = new DefaultComboBoxModel<UIManager.LookAndFeelInfo>();
        lafModel.addElement(new UIManager.LookAndFeelInfo("Flat Light (F1)", FlatLightLaf.class.getName()));
        lafModel.addElement(new UIManager.LookAndFeelInfo("Flat Dark (F2)", FlatDarkLaf.class.getName()));
        lafModel.addElement(new UIManager.LookAndFeelInfo("Flat IntelliJ (F3)", FlatIntelliJLaf.class.getName()));
        lafModel.addElement(new UIManager.LookAndFeelInfo("Flat Darcula (F4)", FlatDarculaLaf.class.getName()));
        for (UIManager.LookAndFeelInfo lookAndFeel : lookAndFeels = UIManager.getInstalledLookAndFeels()) {
            String name = lookAndFeel.getName();
            String className = lookAndFeel.getClassName();
            if (className.equals("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel") || className.equals("com.sun.java.swing.plaf.motif.MotifLookAndFeel")) continue;
            if (SystemInfo.isWindows && className.equals("com.sun.java.swing.plaf.windows.WindowsLookAndFeel") || SystemInfo.isMacOS && className.equals("com.apple.laf.AquaLookAndFeel") || SystemInfo.isLinux && className.equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) {
                name = name + " (F9)";
            } else if (className.equals(MetalLookAndFeel.class.getName())) {
                name = name + " (F12)";
            } else if (className.equals(NimbusLookAndFeel.class.getName())) {
                name = name + " (F11)";
            }
            lafModel.addElement(new UIManager.LookAndFeelInfo(name, className));
        }
        this.lookAndFeelComboBox.setModel(lafModel);
        UIManager.addPropertyChangeListener(e -> {
            if ("lookAndFeel".equals(e.getPropertyName())) {
                EventQueue.invokeLater(() -> {
                    this.updateInfoLabel();
                    this.frame.updateFontMenuItems();
                    this.frame.getRootPane().setDefaultButton(this.closeButton);
                });
            }
        });
        UIScale.addPropertyChangeListener(e -> this.updateInfoLabel());
    }

    @Override
    public void updateUI() {
        super.updateUI();
        if (this.infoLabel != null) {
            this.updateInfoLabel();
        }
    }

    void initialize(DemoFrame frame, JTabbedPane tabbedPane) {
        this.frame = frame;
        this.tabbedPane = tabbedPane;
        this.registerSwitchToLookAndFeel(112, FlatLightLaf.class.getName());
        this.registerSwitchToLookAndFeel(113, FlatDarkLaf.class.getName());
        this.registerSwitchToLookAndFeel(114, FlatIntelliJLaf.class.getName());
        this.registerSwitchToLookAndFeel(115, FlatDarculaLaf.class.getName());
        if (SystemInfo.isWindows) {
            this.registerSwitchToLookAndFeel(120, "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } else if (SystemInfo.isMacOS) {
            this.registerSwitchToLookAndFeel(120, "com.apple.laf.AquaLookAndFeel");
        } else if (SystemInfo.isLinux) {
            this.registerSwitchToLookAndFeel(120, "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        this.registerSwitchToLookAndFeel(123, MetalLookAndFeel.class.getName());
        this.registerSwitchToLookAndFeel(122, NimbusLookAndFeel.class.getName());
        ((JComponent)frame.getContentPane()).registerKeyboardAction(e -> frame.themesPanel.selectPreviousTheme(), KeyStroke.getKeyStroke(38, 512), 1);
        ((JComponent)frame.getContentPane()).registerKeyboardAction(e -> frame.themesPanel.selectNextTheme(), KeyStroke.getKeyStroke(40, 512), 1);
        ((JComponent)frame.getContentPane()).registerKeyboardAction(e -> frame.dispose(), KeyStroke.getKeyStroke(27, 0, false), 1);
        frame.getRootPane().setDefaultButton(this.closeButton);
        frame.addWindowListener(new WindowAdapter(){

            @Override
            public void windowOpened(WindowEvent e) {
                ControlBar.this.updateInfoLabel();
                ControlBar.this.closeButton.requestFocusInWindow();
            }
        });
        frame.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentMoved(ComponentEvent e) {
                ControlBar.this.updateInfoLabel();
            }
        });
    }

    private void updateInfoLabel() {
        String javaVendor = System.getProperty("java.vendor");
        if ("Oracle Corporation".equals(javaVendor)) {
            javaVendor = null;
        }
        double systemScaleFactor = UIScale.getSystemScaleFactor(this.getGraphicsConfiguration());
        float userScaleFactor = UIScale.getUserScaleFactor();
        Font font = UIManager.getFont("Label.font");
        String newInfo = "(Java " + System.getProperty("java.version") + (javaVendor != null ? "; " + javaVendor : "") + (systemScaleFactor != 1.0 ? ";  system scale factor " + systemScaleFactor : "") + (userScaleFactor != 1.0f ? ";  user scale factor " + userScaleFactor : "") + (systemScaleFactor == 1.0 && userScaleFactor == 1.0f ? "; no scaling" : "") + "; " + font.getFamily() + " " + font.getSize() + (font.isBold() ? " BOLD" : "") + (font.isItalic() ? " ITALIC" : "") + ")";
        if (!newInfo.equals(this.infoLabel.getText())) {
            this.infoLabel.setText(newInfo);
        }
    }

    private void registerSwitchToLookAndFeel(int keyCode, String lafClassName) {
        ((JComponent)this.frame.getContentPane()).registerKeyboardAction(e -> this.selectLookAndFeel(lafClassName), KeyStroke.getKeyStroke(keyCode, 0, false), 1);
    }

    private void selectLookAndFeel(String lafClassName) {
        this.lookAndFeelComboBox.setSelectedLookAndFeel(lafClassName);
    }

    private void lookAndFeelChanged() {
        String lafClassName = this.lookAndFeelComboBox.getSelectedLookAndFeel();
        if (lafClassName == null) {
            return;
        }
        if (lafClassName.equals(UIManager.getLookAndFeel().getClass().getName())) {
            return;
        }
        EventQueue.invokeLater(() -> {
            try {
                FlatAnimatedLafChange.showSnapshot();
                UIManager.setLookAndFeel(lafClassName);
                if (!(UIManager.getLookAndFeel() instanceof FlatLaf)) {
                    UIManager.put("defaultFont", null);
                }
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
                int width = this.frame.getWidth();
                int height = this.frame.getHeight();
                Dimension prefSize = this.frame.getPreferredSize();
                if (prefSize.width > width || prefSize.height > height) {
                    this.frame.setSize(Math.max(prefSize.width, width), Math.max(prefSize.height, height));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void rightToLeftChanged() {
        boolean rightToLeft = this.rightToLeftCheckBox.isSelected();
        this.rightToLeftChanged(this.frame, rightToLeft);
    }

    private void rightToLeftChanged(Container c, boolean rightToLeft) {
        c.applyComponentOrientation(rightToLeft ? ComponentOrientation.RIGHT_TO_LEFT : ComponentOrientation.LEFT_TO_RIGHT);
        c.revalidate();
        c.repaint();
    }

    private void enabledChanged() {
        this.enabledDisable(this.tabbedPane, this.enabledCheckBox.isSelected());
        this.tabbedPane.repaint();
    }

    private void enabledDisable(Container container, boolean enabled) {
        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                this.enabledDisable((JPanel)c, enabled);
                continue;
            }
            c.setEnabled(enabled);
            if (c instanceof JScrollPane) {
                Component view = ((JScrollPane)c).getViewport().getView();
                if (view != null) {
                    view.setEnabled(enabled);
                }
            } else if (c instanceof JTabbedPane) {
                JTabbedPane tabPane = (JTabbedPane)c;
                int tabCount = tabPane.getTabCount();
                for (int i = 0; i < tabCount; ++i) {
                    Component tab = tabPane.getComponentAt(i);
                    if (tab == null) continue;
                    tab.setEnabled(enabled);
                }
            }
            if (!(c instanceof JToolBar)) continue;
            this.enabledDisable((JToolBar)c, enabled);
        }
    }

    private void closePerformed() {
        this.frame.dispose();
    }

    private void initComponents() {
        this.separator1 = new JSeparator();
        this.lookAndFeelComboBox = new LookAndFeelsComboBox();
        this.rightToLeftCheckBox = new JCheckBox();
        this.enabledCheckBox = new JCheckBox();
        this.infoLabel = new JLabel();
        this.closeButton = new JButton();
        this.setLayout(new MigLayout("insets dialog", "[fill][fill][fill][grow,fill][button,fill]", "[bottom][]"));
        this.add((Component)this.separator1, "cell 0 0 5 1");
        this.lookAndFeelComboBox.addActionListener(e -> this.lookAndFeelChanged());
        this.add((Component)this.lookAndFeelComboBox, "cell 0 1");
        this.rightToLeftCheckBox.setText("right-to-left");
        this.rightToLeftCheckBox.setMnemonic('R');
        this.rightToLeftCheckBox.addActionListener(e -> this.rightToLeftChanged());
        this.add((Component)this.rightToLeftCheckBox, "cell 1 1");
        this.enabledCheckBox.setText("enabled");
        this.enabledCheckBox.setMnemonic('E');
        this.enabledCheckBox.setSelected(true);
        this.enabledCheckBox.addActionListener(e -> this.enabledChanged());
        this.add((Component)this.enabledCheckBox, "cell 2 1");
        this.infoLabel.setText("text");
        this.add((Component)this.infoLabel, "cell 3 1,alignx center,growx 0");
        this.closeButton.setText("Close");
        this.closeButton.addActionListener(e -> this.closePerformed());
        this.add((Component)this.closeButton, "cell 4 1");
    }
}

