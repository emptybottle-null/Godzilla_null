/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.UIManager;

public class SizeGripIcon
implements Icon {
    private static final int SIZE = 16;

    @Override
    public int getIconHeight() {
        return 16;
    }

    @Override
    public int getIconWidth() {
        return 16;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Dimension dim = c.getSize();
        Color c1 = UIManager.getColor("Label.disabledShadow");
        Color c2 = UIManager.getColor("Label.disabledForeground");
        ComponentOrientation orientation = c.getComponentOrientation();
        int height = dim.height -= 3;
        if (orientation.isLeftToRight()) {
            int width = dim.width -= 3;
            g.setColor(c1);
            g.fillRect(width - 9, height - 1, 3, 3);
            g.fillRect(width - 5, height - 1, 3, 3);
            g.fillRect(width - 1, height - 1, 3, 3);
            g.fillRect(width - 5, height - 5, 3, 3);
            g.fillRect(width - 1, height - 5, 3, 3);
            g.fillRect(width - 1, height - 9, 3, 3);
            g.setColor(c2);
            g.fillRect(width - 9, height - 1, 2, 2);
            g.fillRect(width - 5, height - 1, 2, 2);
            g.fillRect(width - 1, height - 1, 2, 2);
            g.fillRect(width - 5, height - 5, 2, 2);
            g.fillRect(width - 1, height - 5, 2, 2);
            g.fillRect(width - 1, height - 9, 2, 2);
        } else {
            g.setColor(c1);
            g.fillRect(10, height - 1, 3, 3);
            g.fillRect(6, height - 1, 3, 3);
            g.fillRect(2, height - 1, 3, 3);
            g.fillRect(6, height - 5, 3, 3);
            g.fillRect(2, height - 5, 3, 3);
            g.fillRect(2, height - 9, 3, 3);
            g.setColor(c2);
            g.fillRect(10, height - 1, 2, 2);
            g.fillRect(6, height - 1, 2, 2);
            g.fillRect(2, height - 1, 2, 2);
            g.fillRect(6, height - 5, 2, 2);
            g.fillRect(2, height - 5, 2, 2);
            g.fillRect(2, height - 9, 2, 2);
        }
    }
}

