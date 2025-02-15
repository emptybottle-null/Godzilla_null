/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.util.ScaledImageIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.io.File;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicFileChooserUI;
import javax.swing.plaf.metal.MetalFileChooserUI;

public class FlatFileChooserUI
extends MetalFileChooserUI {
    private final FlatFileView fileView = new FlatFileView();

    public static ComponentUI createUI(JComponent c) {
        return new FlatFileChooserUI((JFileChooser)c);
    }

    public FlatFileChooserUI(JFileChooser filechooser) {
        super(filechooser);
    }

    @Override
    public void installComponents(JFileChooser fc) {
        super.installComponents(fc);
        this.patchUI(fc);
    }

    private void patchUI(JFileChooser fc) {
        Component topButtonPanel;
        Component topPanel = fc.getComponent(0);
        if (topPanel instanceof JPanel && ((JPanel)topPanel).getLayout() instanceof BorderLayout && (topButtonPanel = ((JPanel)topPanel).getComponent(0)) instanceof JPanel && ((JPanel)topButtonPanel).getLayout() instanceof BoxLayout) {
            Insets margin = UIManager.getInsets("Button.margin");
            Component[] comps = ((JPanel)topButtonPanel).getComponents();
            for (int i = comps.length - 1; i >= 0; --i) {
                Component c = comps[i];
                if (c instanceof JButton || c instanceof JToggleButton) {
                    AbstractButton b = (AbstractButton)c;
                    b.putClientProperty("JButton.buttonType", "toolBarButton");
                    b.setMargin(margin);
                    b.setFocusable(false);
                    continue;
                }
                if (!(c instanceof Box.Filler)) continue;
                ((JPanel)topButtonPanel).remove(i);
            }
        }
        try {
            int maximumRowCount;
            Component directoryComboBox = ((JPanel)topPanel).getComponent(2);
            if (directoryComboBox instanceof JComboBox && (maximumRowCount = UIManager.getInt("ComboBox.maximumRowCount")) > 0) {
                ((JComboBox)directoryComboBox).setMaximumRowCount(maximumRowCount);
            }
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            // empty catch block
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return UIScale.scale(super.getPreferredSize(c));
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return UIScale.scale(super.getMinimumSize(c));
    }

    @Override
    public FileView getFileView(JFileChooser fc) {
        return this.fileView;
    }

    @Override
    public void clearIconCache() {
        this.fileView.clearIconCache();
    }

    private class FlatFileView
    extends BasicFileChooserUI.BasicFileView {
        private FlatFileView() {
            super(FlatFileChooserUI.this);
        }

        @Override
        public Icon getIcon(File f) {
            Icon icon = this.getCachedIcon(f);
            if (icon != null) {
                return icon;
            }
            if (f != null && (icon = FlatFileChooserUI.this.getFileChooser().getFileSystemView().getSystemIcon(f)) != null) {
                if (icon instanceof ImageIcon) {
                    icon = new ScaledImageIcon((ImageIcon)icon);
                }
                this.cacheIcon(f, icon);
                return icon;
            }
            icon = super.getIcon(f);
            if (icon instanceof ImageIcon) {
                icon = new ScaledImageIcon((ImageIcon)icon);
                this.cacheIcon(f, icon);
            }
            return icon;
        }
    }
}

