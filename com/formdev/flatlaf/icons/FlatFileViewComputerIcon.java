/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.icons;

import com.formdev.flatlaf.icons.FlatAbstractIcon;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.UIManager;

public class FlatFileViewComputerIcon
extends FlatAbstractIcon {
    public FlatFileViewComputerIcon() {
        super(16, 16, UIManager.getColor("Objects.Grey"));
    }

    @Override
    protected void paintIcon(Component c, Graphics2D g) {
        Path2D.Float path = new Path2D.Float(0);
        path.append(new Rectangle2D.Float(2.0f, 3.0f, 12.0f, 8.0f), false);
        path.append(new Rectangle2D.Float(4.0f, 5.0f, 8.0f, 4.0f), false);
        g.fill(path);
        g.fillRect(2, 12, 12, 2);
    }
}

