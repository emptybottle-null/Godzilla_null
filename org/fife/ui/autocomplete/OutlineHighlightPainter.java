/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import javax.swing.text.View;

class OutlineHighlightPainter
extends DefaultHighlighter.DefaultHighlightPainter {
    private Color color;

    OutlineHighlightPainter(Color color) {
        super(color);
        this.setColor(color);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Shape paintLayer(Graphics g, int p0, int p1, Shape viewBounds, JTextComponent c, View view) {
        g.setColor(this.getColor());
        if (++p0 == p1) {
            try {
                Shape s = view.modelToView(p0, viewBounds, Position.Bias.Forward);
                Rectangle r = s.getBounds();
                g.drawLine(r.x, r.y, r.x, r.y + r.height);
                return r;
            } catch (BadLocationException ble) {
                ble.printStackTrace();
                return null;
            }
        }
        if (p0 == view.getStartOffset() && p1 == view.getEndOffset()) {
            Rectangle alloc = viewBounds instanceof Rectangle ? (Rectangle)viewBounds : viewBounds.getBounds();
            g.drawRect(alloc.x, alloc.y, alloc.width - 1, alloc.height - 1);
            return alloc;
        }
        try {
            Shape shape = view.modelToView(p0, Position.Bias.Forward, p1, Position.Bias.Backward, viewBounds);
            Rectangle r = shape instanceof Rectangle ? (Rectangle)shape : shape.getBounds();
            g.drawRect(r.x, r.y, r.width - 1, r.height - 1);
            return r;
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new IllegalArgumentException("color cannot be null");
        }
        this.color = color;
    }
}

