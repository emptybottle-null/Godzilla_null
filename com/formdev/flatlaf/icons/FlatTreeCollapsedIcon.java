/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeCollapsedIcon
extends FlatAbstractIcon {
    private final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));

    public FlatTreeCollapsedIcon() {
        this(UIManager.getColor("Tree.icon.collapsedColor"));
    }

    FlatTreeCollapsedIcon(Color color) {
        super(11, 11, color);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        this.rotate(c, g);
        if (this.chevron) {
            g.fill(FlatUIUtils.createPath(3.0, 1.0, 3.0, 2.5, 6.0, 5.5, 3.0, 8.5, 3.0, 10.0, 4.5, 10.0, 9.0, 5.5, 4.5, 1.0));
        } else {
            g.fill(FlatUIUtils.createPath(2.0, 1.0, 2.0, 10.0, 10.0, 5.5));
        }
    }

    void rotate(Component c, Graphics2D g) {
        if (!c.getComponentOrientation().isLeftToRight()) {
            g.rotate(Math.toRadians(180.0), (double)this.width / 2.0, (double)this.height / 2.0);
        }
    }
}

