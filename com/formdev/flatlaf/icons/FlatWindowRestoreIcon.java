/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatWindowAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class FlatWindowRestoreIcon
extends FlatWindowAbstractIcon {
    @Override
    protected void paintIconAt1x(Graphics2D g, int x, int y, int width, int height, double scaleFactor) {
        int iwh = (int)(10.0 * scaleFactor);
        int ix = x + (width - iwh) / 2;
        int iy = y + (height - iwh) / 2;
        int thickness = (int)scaleFactor;
        int rwh = (int)(8.0 * scaleFactor);
        int ro2 = iwh - rwh;
        Path2D r1 = FlatUIUtils.createRectangle(ix + ro2, iy, rwh, rwh, thickness);
        Path2D r2 = FlatUIUtils.createRectangle(ix, iy + ro2, rwh, rwh, thickness);
        Area area = new Area(r1);
        area.subtract(new Area(new Rectangle2D.Float(ix, iy + ro2, rwh, rwh)));
        g.fill(area);
        g.fill(r2);
    }
}

