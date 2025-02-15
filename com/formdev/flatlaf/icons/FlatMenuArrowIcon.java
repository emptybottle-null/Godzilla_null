/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.JMenu;
import javax.swing.UIManager;

public class FlatMenuArrowIcon
extends FlatAbstractIcon {
    protected final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
    protected final Color arrowColor = UIManager.getColor("Menu.icon.arrowColor");
    protected final Color disabledArrowColor = UIManager.getColor("Menu.icon.disabledArrowColor");
    protected final Color selectionForeground = UIManager.getColor("Menu.selectionForeground");

    public FlatMenuArrowIcon() {
        super(6, 10, null);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        if (!c.getComponentOrientation().isLeftToRight()) {
            g.rotate(Math.toRadians(180.0), (double)this.width / 2.0, (double)this.height / 2.0);
        }
        g.setColor(this.getArrowColor(c));
        if (this.chevron) {
            Path2D path = FlatUIUtils.createPath(false, 1.0, 1.0, 5.0, 5.0, 1.0, 9.0);
            g.setStroke(new BasicStroke(1.0f));
            g.draw(path);
        } else {
            g.fill(FlatUIUtils.createPath(0.0, 0.5, 5.0, 5.0, 0.0, 9.5));
        }
    }

    protected Color getArrowColor(Component c) {
        if (c instanceof JMenu && ((JMenu)c).isSelected() && !this.isUnderlineSelection()) {
            return this.selectionForeground;
        }
        return c.isEnabled() ? this.arrowColor : this.disabledArrowColor;
    }

    protected boolean isUnderlineSelection() {
        return "underline".equals(UIManager.getString("MenuItem.selectionType"));
    }
}

