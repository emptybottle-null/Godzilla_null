/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

public class FlatCheckBoxMenuItemIcon
extends FlatAbstractIcon {
    protected final Color checkmarkColor = UIManager.getColor("MenuItemCheckBox.icon.checkmarkColor");
    protected final Color disabledCheckmarkColor = UIManager.getColor("MenuItemCheckBox.icon.disabledCheckmarkColor");
    protected final Color selectionForeground = UIManager.getColor("MenuItem.selectionForeground");

    public FlatCheckBoxMenuItemIcon() {
        super(15, 15, null);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g2) {
        boolean selected;
        boolean bl = selected = c instanceof AbstractButton && ((AbstractButton)c).isSelected();
        if (selected) {
            g2.setColor(this.getCheckmarkColor(c));
            this.paintCheckmark(g2);
        }
    }

    protected void paintCheckmark(Graphics2D g2) {
        Path2D.Float path = new Path2D.Float();
        path.moveTo(4.5f, 7.5f);
        path.lineTo(6.6f, 10.0f);
        path.lineTo(11.25f, 3.5f);
        g2.setStroke(new BasicStroke(1.9f, 1, 1));
        g2.draw(path);
    }

    protected Color getCheckmarkColor(Component c) {
        if (c instanceof JMenuItem && ((JMenuItem)c).isArmed() && !this.isUnderlineSelection()) {
            return this.selectionForeground;
        }
        return c.isEnabled() ? this.checkmarkColor : this.disabledCheckmarkColor;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }
}

