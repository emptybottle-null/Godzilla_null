/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public abstract class FlatOptionPaneAbstractIcon
extends FlatAbstractIcon {
    protected final Color foreground = UIManager.getColor("OptionPane.icon.foreground");

    protected FlatOptionPaneAbstractIcon(String colorKey, String defaultColorKey) {
        super(32, 32, FlatUIUtils.getUIColor(colorKey, defaultColorKey));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        if (this.foreground != null) {
            g.fill(this.createOutside());
            g.setColor(this.foreground);
            g.fill(this.createInside());
        } else {
            Path2D.Float path = new Path2D.Float(0);
            path.append(this.createOutside(), false);
            path.append(this.createInside(), false);
            g.fill(path);
        }
    }

    protected abstract Shape createOutside();

    protected abstract Shape createInside();
}

