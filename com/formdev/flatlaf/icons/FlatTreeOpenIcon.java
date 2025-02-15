/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeOpenIcon
extends FlatAbstractIcon {
    public FlatTreeOpenIcon() {
        super(16, 16, UIManager.getColor("Tree.icon.openColor"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.fill(FlatUIUtils.createPath(1.0, 2.0, 6.0, 2.0, 8.0, 4.0, 14.0, 4.0, 14.0, 6.0, 3.5, 6.0, 1.0, 11.0));
        g.fill(FlatUIUtils.createPath(4.0, 7.0, 16.0, 7.0, 13.0, 13.0, 1.0, 13.0));
    }
}

