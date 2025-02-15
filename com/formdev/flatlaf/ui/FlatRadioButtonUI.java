/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.ui;

import com.formdev.flatlaf.icons.FlatCheckBoxIcon;
import com.formdev.flatlaf.ui.FlatButtonUI;
import com.formdev.flatlaf.ui.FlatLabelUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.ui.MigLayoutVisualPadding;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRadioButtonUI;

public class FlatRadioButtonUI
extends BasicRadioButtonUI {
    protected int iconTextGap;
    protected Color disabledText;
    private Color defaultBackground;
    private boolean defaults_initialized = false;
    private static Insets tempInsets = new Insets(0, 0, 0, 0);

    public static ComponentUI createUI(JComponent c) {
        return FlatUIUtils.createSharedUI(FlatRadioButtonUI.class, FlatRadioButtonUI::new);
    }

    @Override
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        if (!this.defaults_initialized) {
            String prefix = this.getPropertyPrefix();
            this.iconTextGap = FlatUIUtils.getUIInt(prefix + "iconTextGap", 4);
            this.disabledText = UIManager.getColor(prefix + "disabledText");
            this.defaultBackground = UIManager.getColor(prefix + "background");
            this.defaults_initialized = true;
        }
        LookAndFeel.installProperty(b, "opaque", false);
        LookAndFeel.installProperty(b, "iconTextGap", UIScale.scale(this.iconTextGap));
        MigLayoutVisualPadding.install(b, null);
    }

    @Override
    protected void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        MigLayoutVisualPadding.uninstall(b);
        this.defaults_initialized = false;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension size = super.getPreferredSize(c);
        if (size == null) {
            return null;
        }
        int focusWidth = this.getIconFocusWidth(c);
        if (focusWidth > 0) {
            Insets insets = c.getInsets(tempInsets);
            size.width += Math.max(focusWidth - insets.left, 0) + Math.max(focusWidth - insets.right, 0);
            size.height += Math.max(focusWidth - insets.top, 0) + Math.max(focusWidth - insets.bottom, 0);
        }
        return size;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        int focusWidth;
        if (!c.isOpaque() && ((AbstractButton)c).isContentAreaFilled() && c.getBackground() != this.defaultBackground) {
            g.setColor(c.getBackground());
            g.fillRect(0, 0, c.getWidth(), c.getHeight());
        }
        if ((focusWidth = this.getIconFocusWidth(c)) > 0) {
            int leftOrRightInset;
            boolean ltr = c.getComponentOrientation().isLeftToRight();
            Insets insets = c.getInsets(tempInsets);
            int n = leftOrRightInset = ltr ? insets.left : insets.right;
            if (focusWidth > leftOrRightInset) {
                int offset = focusWidth - leftOrRightInset;
                if (!ltr) {
                    offset = -offset;
                }
                g.translate(offset, 0);
                super.paint(g, c);
                g.translate(-offset, 0);
                return;
            }
        }
        super.paint(FlatLabelUI.createGraphicsHTMLTextYCorrection(g, c), c);
    }

    @Override
    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
        FlatButtonUI.paintText(g, b, textRect, text, b.isEnabled() ? b.getForeground() : this.disabledText);
    }

    private int getIconFocusWidth(JComponent c) {
        AbstractButton b = (AbstractButton)c;
        return b.getIcon() == null && this.getDefaultIcon() instanceof FlatCheckBoxIcon ? UIScale.scale(((FlatCheckBoxIcon)this.getDefaultIcon()).focusWidth) : 0;
    }
}

