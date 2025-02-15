/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import org.fife.ui.rtextarea.BackgroundPainterStrategy;

public class ColorBackgroundPainterStrategy
implements BackgroundPainterStrategy {
    private Color color;

    public ColorBackgroundPainterStrategy(Color color) {
        this.setColor(color);
    }

    public boolean equals(Object o2) {
        return o2 instanceof ColorBackgroundPainterStrategy && this.color.equals(((ColorBackgroundPainterStrategy)o2).getColor());
    }

    public Color getColor() {
        return this.color;
    }

    public int hashCode() {
        return this.color.hashCode();
    }

    @Override
    public void paint(Graphics g, Rectangle bounds) {
        Color temp = g.getColor();
        g.setColor(this.color);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(temp);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

