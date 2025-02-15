/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatInternalFrameAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;

public class FlatInternalFrameMaximizeIcon
extends FlatInternalFrameAbstractIcon {
    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        this.paintBackground(c, g);
        g.setColor(c.getForeground());
        g.fill(FlatUIUtils.createRectangle(this.width / 2 - 4, this.height / 2 - 4, 8.0f, 8.0f, 1.0f));
    }
}

