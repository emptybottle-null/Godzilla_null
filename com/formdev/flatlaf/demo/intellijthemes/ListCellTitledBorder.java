/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.formdev.flatlaf.demo.intellijthemes;

import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.Border;

class ListCellTitledBorder
implements Border {
    private final JList<?> list;
    private final String title;

    ListCellTitledBorder(JList<?> list, String title) {
        this.list = list;
        this.title = title;
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        int height = c.getFontMetrics(this.list.getFont()).getHeight();
        return new Insets(height, 0, 0, 0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        FontMetrics fm = c.getFontMetrics(this.list.getFont());
        int titleWidth = fm.stringWidth(this.title);
        int titleHeight = fm.getHeight();
        g.setColor(this.list.getBackground());
        g.fillRect(x, y, width, titleHeight);
        int gap = UIScale.scale(4);
        Graphics2D g2 = (Graphics2D)g.create();
        try {
            FlatUIUtils.setRenderingHints(g2);
            g2.setColor(UIManager.getColor("Label.disabledForeground"));
            int sepWidth = (width - titleWidth) / 2 - gap - gap;
            if (sepWidth > 0) {
                int sy = y + Math.round((float)titleHeight / 2.0f);
                float sepHeight = UIScale.scale(1.0f);
                g2.fill(new Rectangle2D.Float(x + gap, sy, sepWidth, sepHeight));
                g2.fill(new Rectangle2D.Float(x + width - gap - sepWidth, sy, sepWidth, sepHeight));
            }
            int xt = x + (width - titleWidth) / 2;
            int yt = y + fm.getAscent();
            FlatUIUtils.drawString(this.list, g2, this.title, xt, yt);
        } finally {
            g2.dispose();
        }
    }
}

