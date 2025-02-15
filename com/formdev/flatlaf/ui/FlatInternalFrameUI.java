/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.ui.FlatDropShadowBorder;
import com.formdev.flatlaf.ui.FlatEmptyBorder;
import com.formdev.flatlaf.ui.FlatInternalFrameTitlePane;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.FlatWindowResizer;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class FlatInternalFrameUI
extends BasicInternalFrameUI {
    protected FlatWindowResizer windowResizer;

    public static ComponentUI createUI(JComponent c) {
        return new FlatInternalFrameUI((JInternalFrame)c);
    }

    public FlatInternalFrameUI(JInternalFrame b) {
        super(b);
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        LookAndFeel.installProperty(this.frame, "opaque", false);
        this.windowResizer = this.createWindowResizer();
    }

    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        if (this.windowResizer != null) {
            this.windowResizer.uninstall();
            this.windowResizer = null;
        }
    }

    @Override
    protected JComponent createNorthPane(JInternalFrame w) {
        return new FlatInternalFrameTitlePane(w);
    }

    protected FlatWindowResizer createWindowResizer() {
        return new FlatWindowResizer.InternalFrameResizer(this.frame, this::getDesktopManager);
    }

    public static class FlatInternalFrameBorder
    extends FlatEmptyBorder {
        private final Color activeBorderColor = UIManager.getColor("InternalFrame.activeBorderColor");
        private final Color inactiveBorderColor = UIManager.getColor("InternalFrame.inactiveBorderColor");
        private final int borderLineWidth = FlatUIUtils.getUIInt("InternalFrame.borderLineWidth", 1);
        private final boolean dropShadowPainted = UIManager.getBoolean("InternalFrame.dropShadowPainted");
        private final FlatDropShadowBorder activeDropShadowBorder = new FlatDropShadowBorder(UIManager.getColor("InternalFrame.activeDropShadowColor"), UIManager.getInsets("InternalFrame.activeDropShadowInsets"), FlatUIUtils.getUIFloat("InternalFrame.activeDropShadowOpacity", 0.5f));
        private final FlatDropShadowBorder inactiveDropShadowBorder = new FlatDropShadowBorder(UIManager.getColor("InternalFrame.inactiveDropShadowColor"), UIManager.getInsets("InternalFrame.inactiveDropShadowInsets"), FlatUIUtils.getUIFloat("InternalFrame.inactiveDropShadowOpacity", 0.5f));

        public FlatInternalFrameBorder() {
            super(UIManager.getInsets("InternalFrame.borderMargins"));
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            if (c instanceof JInternalFrame && ((JInternalFrame)c).isMaximum()) {
                insets.left = UIScale.scale(Math.min(this.borderLineWidth, this.left));
                insets.top = UIScale.scale(Math.min(this.borderLineWidth, this.top));
                insets.right = UIScale.scale(Math.min(this.borderLineWidth, this.right));
                insets.bottom = UIScale.scale(Math.min(this.borderLineWidth, this.bottom));
                return insets;
            }
            return super.getBorderInsets(c, insets);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JInternalFrame f = (JInternalFrame)c;
            Insets insets = this.getBorderInsets(c);
            float lineWidth = UIScale.scale((float)this.borderLineWidth);
            float rx = (float)(x + insets.left) - lineWidth;
            float ry = (float)(y + insets.top) - lineWidth;
            float rwidth = (float)(width - insets.left - insets.right) + lineWidth * 2.0f;
            float rheight = (float)(height - insets.top - insets.bottom) + lineWidth * 2.0f;
            Graphics2D g2 = (Graphics2D)g.create();
            try {
                FlatUIUtils.setRenderingHints(g2);
                g2.setColor(f.isSelected() ? this.activeBorderColor : this.inactiveBorderColor);
                if (this.dropShadowPainted) {
                    FlatDropShadowBorder dropShadowBorder = f.isSelected() ? this.activeDropShadowBorder : this.inactiveDropShadowBorder;
                    Insets dropShadowInsets = dropShadowBorder.getBorderInsets();
                    dropShadowBorder.paintBorder(c, g2, (int)rx - dropShadowInsets.left, (int)ry - dropShadowInsets.top, (int)rwidth + dropShadowInsets.left + dropShadowInsets.right, (int)rheight + dropShadowInsets.top + dropShadowInsets.bottom);
                }
                g2.fill(FlatUIUtils.createRectangle(rx, ry, rwidth, rheight, lineWidth));
            } finally {
                g2.dispose();
            }
        }
    }
}

