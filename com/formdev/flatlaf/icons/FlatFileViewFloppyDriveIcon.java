/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import com.formdev.flatlaf.ui.FlatUIUtils;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import javax.swing.UIManager;

public class FlatFileViewFloppyDriveIcon
extends FlatAbstractIcon {
    public FlatFileViewFloppyDriveIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        Path2D.Float path = new Path2D.Float(0);
        path.append(FlatUIUtils.createPath(11.0, 14.0, 11.0, 11.0, 5.0, 11.0, 5.0, 14.0, 2.0, 14.0, 2.0, 2.0, 14.0, 2.0, 14.0, 14.0, 11.0, 14.0), false);
        path.append(FlatUIUtils.createPath(4.0, 4.0, 4.0, 8.0, 12.0, 8.0, 12.0, 4.0, 4.0, 4.0), false);
        g.fill(path);
        g.fillRect(6, 12, 4, 2);
    }
}

