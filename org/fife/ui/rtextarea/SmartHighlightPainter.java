/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import javax.swing.text.View;
import org.fife.ui.rtextarea.ChangeableHighlightPainter;

public class SmartHighlightPainter
extends ChangeableHighlightPainter {
    private Color borderColor;
    private boolean paintBorder;

    public SmartHighlightPainter() {
        super(Color.BLUE);
    }

    public SmartHighlightPainter(Paint paint) {
        super(paint);
    }

    public boolean getPaintBorder() {
        return this.paintBorder;
    }

    @Override
    public Shape paintLayer(Graphics g, int p0, int p1, Shape viewBounds, JTextComponent c, View view) {
        g.setColor((Color)this.getPaint());
        if (p0 == p1) {
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
            g.fillRect(alloc.x, alloc.y, alloc.width, alloc.height);
            return alloc;
        }
        try {
            Shape shape = view.modelToView(p0, Position.Bias.Forward, p1, Position.Bias.Backward, viewBounds);
            Rectangle r = shape instanceof Rectangle ? (Rectangle)shape : shape.getBounds();
            g.fillRect(r.x, r.y, r.width, r.height);
            if (this.paintBorder) {
                g.setColor(this.borderColor);
                g.drawRect(r.x, r.y, r.width - 1, r.height - 1);
            }
            return r;
        } catch (BadLocationException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setPaint(Paint paint) {
        super.setPaint(paint);
        if (paint instanceof Color) {
            this.borderColor = ((Color)paint).darker();
        }
    }

    public void setPaintBorder(boolean paint) {
        this.paintBorder = paint;
    }
}

