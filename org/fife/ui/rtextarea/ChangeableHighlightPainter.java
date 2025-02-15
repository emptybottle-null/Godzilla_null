/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rtextarea;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.SystemColor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.LayeredHighlighter;
import javax.swing.text.Position;
import javax.swing.text.View;

public class ChangeableHighlightPainter
extends LayeredHighlighter.LayerPainter
implements Serializable {
    private Paint paint;
    private boolean roundedEdges;
    private transient AlphaComposite alphaComposite;
    private float alpha;
    private static final int ARCWIDTH = 8;
    private static final int ARCHEIGHT = 8;

    public ChangeableHighlightPainter() {
        this(null);
    }

    public ChangeableHighlightPainter(Paint paint) {
        this(paint, false);
    }

    public ChangeableHighlightPainter(Paint paint, boolean rounded) {
        this(paint, rounded, 1.0f);
    }

    public ChangeableHighlightPainter(Paint paint, boolean rounded, float alpha) {
        this.setPaint(paint);
        this.setRoundedEdges(rounded);
        this.setAlpha(alpha);
    }

    public float getAlpha() {
        return this.alpha;
    }

    private AlphaComposite getAlphaComposite() {
        if (this.alphaComposite == null) {
            this.alphaComposite = AlphaComposite.getInstance(3, this.alpha);
        }
        return this.alphaComposite;
    }

    public Paint getPaint() {
        return this.paint;
    }

    public boolean getRoundedEdges() {
        return this.roundedEdges;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void paint(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c) {
        Rectangle alloc = bounds.getBounds();
        Graphics2D g2d = (Graphics2D)g;
        Composite originalComposite = null;
        if (this.getAlpha() < 1.0f) {
            originalComposite = g2d.getComposite();
            g2d.setComposite(this.getAlphaComposite());
        }
        try {
            TextUI mapper = c.getUI();
            Rectangle p0 = mapper.modelToView(c, offs0);
            Rectangle p1 = mapper.modelToView(c, offs1);
            Paint paint = this.getPaint();
            if (paint == null) {
                g2d.setColor(c.getSelectionColor());
            } else {
                g2d.setPaint(paint);
            }
            if (p0.y == p1.y) {
                p1.width = 0;
                Rectangle r = p0.union(p1);
                g2d.fillRect(r.x, r.y, r.width, r.height);
            } else {
                int p0ToMarginWidth = alloc.x + alloc.width - p0.x;
                g2d.fillRect(p0.x, p0.y, p0ToMarginWidth, p0.height);
                if (p0.y + p0.height != p1.y) {
                    g2d.fillRect(alloc.x, p0.y + p0.height, alloc.width, p1.y - (p0.y + p0.height));
                }
                g2d.fillRect(alloc.x, p1.y, p1.x - alloc.x, p1.height);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            if (this.getAlpha() < 1.0f) {
                g2d.setComposite(originalComposite);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Shape paintLayer(Graphics g, int offs0, int offs1, Shape bounds, JTextComponent c, View view) {
        Paint paint;
        Graphics2D g2d = (Graphics2D)g;
        Composite originalComposite = null;
        if (this.getAlpha() < 1.0f) {
            originalComposite = g2d.getComposite();
            g2d.setComposite(this.getAlphaComposite());
        }
        if ((paint = this.getPaint()) == null) {
            g2d.setColor(c.getSelectionColor());
        } else {
            g2d.setPaint(paint);
        }
        if (offs0 == offs1) {
            try {
                Shape s = view.modelToView(offs0, bounds, Position.Bias.Forward);
                Rectangle r = s.getBounds();
                g.drawLine(r.x, r.y, r.x, r.y + r.height);
                return r;
            } catch (BadLocationException ble) {
                ble.printStackTrace();
                return null;
            }
        }
        if (offs0 == view.getStartOffset() && offs1 == view.getEndOffset()) {
            Rectangle alloc = bounds instanceof Rectangle ? (Rectangle)bounds : bounds.getBounds();
            g2d.fillRect(alloc.x, alloc.y, alloc.width, alloc.height);
            if (this.getAlpha() < 1.0f) {
                g2d.setComposite(originalComposite);
            }
            return alloc;
        }
        try {
            Rectangle r;
            Shape shape = view.modelToView(offs0, Position.Bias.Forward, offs1, Position.Bias.Backward, bounds);
            Rectangle rectangle = r = shape instanceof Rectangle ? (Rectangle)shape : shape.getBounds();
            if (this.roundedEdges) {
                g2d.fillRoundRect(r.x, r.y, r.width, r.height, 8, 8);
            } else {
                g2d.fillRect(r.x, r.y, r.width, r.height);
            }
            if (this.getAlpha() < 1.0f) {
                g2d.setComposite(originalComposite);
            }
            Rectangle rectangle2 = r;
            return rectangle2;
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        } finally {
            if (this.getAlpha() < 1.0f) {
                g2d.setComposite(originalComposite);
            }
        }
        return null;
    }

    private void readObject(ObjectInputStream s) throws ClassNotFoundException, IOException {
        s.defaultReadObject();
        int rgb = s.readInt();
        this.paint = rgb == -1 ? null : new Color(rgb);
        this.alphaComposite = null;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        this.alpha = Math.max(alpha, 0.0f);
        this.alpha = Math.min(1.0f, alpha);
        this.alphaComposite = null;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void setRoundedEdges(boolean rounded) {
        this.roundedEdges = rounded;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        int rgb = -1;
        if (this.paint != null) {
            SystemColor c = this.paint instanceof Color ? (Color)this.paint : SystemColor.textHighlight;
            rgb = c.getRGB();
        }
        s.writeInt(rgb);
    }
}

