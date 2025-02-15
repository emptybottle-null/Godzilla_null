/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEditorPaneUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.JTextComponent;

public class FlatTextAreaUI
extends BasicTextAreaUI {
    protected int minimumWidth;
    protected boolean isIntelliJTheme;
    protected Color background;
    protected Color disabledBackground;
    protected Color inactiveBackground;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTextAreaUI();
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        this.updateBackground();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.background = UIManager.getColor("TextArea.background");
        this.disabledBackground = UIManager.getColor("TextArea.disabledBackground");
        this.inactiveBackground = UIManager.getColor("TextArea.inactiveBackground");
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.background = null;
        this.disabledBackground = null;
        this.inactiveBackground = null;
    }

    @Override
    protected void propertyChange(PropertyChangeEvent e) {
        super.propertyChange(e);
        FlatEditorPaneUI.propertyChange(this.getComponent(), e);
        switch (e.getPropertyName()) {
            case "editable": 
            case "enabled": {
                this.updateBackground();
            }
        }
    }

    private void updateBackground() {
        Color newBackground;
        JTextComponent c = this.getComponent();
        Color background = c.getBackground();
        if (!(background instanceof UIResource)) {
            return;
        }
        if (background != this.background && background != this.disabledBackground && background != this.inactiveBackground) {
            return;
        }
        Color color = !c.isEnabled() ? this.disabledBackground : (newBackground = !c.isEditable() ? this.inactiveBackground : this.background);
        if (newBackground != background) {
            c.setBackground(newBackground);
        }
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return this.applyMinimumWidth(c, super.getPreferredSize(c));
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return this.applyMinimumWidth(c, super.getMinimumSize(c));
    }

    private Dimension applyMinimumWidth(JComponent c, Dimension size) {
        if (c instanceof JTextArea && ((JTextArea)c).getColumns() > 0) {
            return size;
        }
        return FlatEditorPaneUI.applyMinimumWidth(c, size, this.minimumWidth);
    }

    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(HiDPIUtils.createGraphicsTextYCorrection((Graphics2D)g));
    }

    @Override
    protected void paintBackground(Graphics g) {
        JTextComponent c = this.getComponent();
        if (this.isIntelliJTheme && (!c.isEnabled() || !c.isEditable()) && c.getBackground() instanceof UIResource) {
            FlatUIUtils.paintParentBackground(g, c);
            return;
        }
        super.paintBackground(g);
    }
}

