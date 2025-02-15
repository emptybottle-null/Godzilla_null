/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import javax.swing.UIManager;

public class FlatTreeClosedIcon
extends FlatAbstractIcon {
    public FlatTreeClosedIcon() {
        super(16, 16, UIManager.getColor("Tree.icon.closedColor"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        g.fill(FlatUIUtils.createPath(1.0, 2.0, 6.0, 2.0, 8.0, 4.0, 15.0, 4.0, 15.0, 13.0, 1.0, 13.0));
    }
}

