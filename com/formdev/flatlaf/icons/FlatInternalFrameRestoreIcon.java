/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatInternalFrameAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatInternalFrameRestoreIcon
extends FlatInternalFrameAbstractIcon {
    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        this.paintBackground(c, g);
        g.setColor(c.getForeground());
        int x = this.width / 2 - 4;
        int y = this.height / 2 - 4;
        Path2D r1 = FlatUIUtils.createRectangle(x + 1, y - 1, 8.0f, 8.0f, 1.0f);
        Path2D r2 = FlatUIUtils.createRectangle(x - 1, y + 1, 8.0f, 8.0f, 1.0f);
        Area area = new Area(r1);
        area.subtract(new Area(new Rectangle2D.Float(x - 1, y + 1, 8.0f, 8.0f)));
        g.fill(area);
        g.fill(r2);
    }
}

