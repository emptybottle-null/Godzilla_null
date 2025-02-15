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
import javax.swing.UIManager;

public class FlatDescendingSortIcon
extends FlatAbstractIcon {
    protected final boolean chevron = FlatUIUtils.isChevron(UIManager.getString("Component.arrowType"));
    protected final Color sortIconColor = UIManager.getColor("Table.sortIconColor");

    public FlatDescendingSortIcon() {
        super(10, 5, null);
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.setColor(this.sortIconColor);
        if (this.chevron) {
            Path2D path = FlatUIUtils.createPath(false, 1.0, 0.0, 5.0, 4.0, 9.0, 0.0);
            g.setStroke(new BasicStroke(1.0f));
            g.draw(path);
        } else {
            g.fill(FlatUIUtils.createPath(0.5, 0.0, 5.0, 5.0, 9.5, 0.0));
        }
    }
}

