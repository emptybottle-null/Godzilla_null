/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatTreeCollapsedIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeExpandedIcon
extends FlatTreeCollapsedIcon {
    public FlatTreeExpandedIcon() {
        super(UIManager.getColor("Tree.icon.expandedColor"));
    }

    @Override
    void rotate(Component c, Graphics2D g) {
        g.rotate(Math.toRadians(90.0), (double)this.width / 2.0, (double)this.height / 2.0);
    }
}

