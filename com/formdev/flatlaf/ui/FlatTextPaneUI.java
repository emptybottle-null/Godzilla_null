/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatEditorPaneUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.HiDPIUtils;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTextPaneUI;
import javax.swing.text.JTextComponent;

public class FlatTextPaneUI
extends BasicTextPaneUI {
    protected int minimumWidth;
    protected boolean isIntelliJTheme;
    private Object oldHonorDisplayProperties;

    public static ComponentUI createUI(JComponent c) {
        return new FlatTextPaneUI();
    }

    @Override
    protected void installDefaults() {
        super.installDefaults();
        this.minimumWidth = UIManager.getInt("Component.minimumWidth");
        this.isIntelliJTheme = UIManager.getBoolean("Component.isIntelliJTheme");
        this.oldHonorDisplayProperties = this.getComponent().getClientProperty("JEditorPane.honorDisplayProperties");
        this.getComponent().putClientProperty("JEditorPane.honorDisplayProperties", true);
    }

    @Override
    protected void uninstallDefaults() {
        super.uninstallDefaults();
        this.getComponent().putClientProperty("JEditorPane.honorDisplayProperties", this.oldHonorDisplayProperties);
    }

    @Override
    protected void propertyChange(PropertyChangeEvent e) {
        super.propertyChange(e);
        FlatEditorPaneUI.propertyChange(this.getComponent(), e);
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return FlatEditorPaneUI.applyMinimumWidth(c, super.getPreferredSize(c), this.minimumWidth);
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return FlatEditorPaneUI.applyMinimumWidth(c, super.getMinimumSize(c), this.minimumWidth);
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

