/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Group;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Symbol
extends Group {
    public static final String TAG_NAME = "symbol";
    AffineTransform viewXform;
    Rectangle2D viewBox;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("viewBox"))) {
            float[] dim = sty.getFloatList();
            this.viewBox = new Rectangle2D.Float(dim[0], dim[1], dim[2], dim[3]);
        }
        if (this.viewBox == null) {
            this.viewBox = new Rectangle(0, 0, 1, 1);
        }
        this.viewXform = new AffineTransform();
        this.viewXform.scale(1.0 / this.viewBox.getWidth(), 1.0 / this.viewBox.getHeight());
        this.viewXform.translate(-this.viewBox.getX(), -this.viewBox.getY());
    }

    @Override
    protected boolean outsideClip(Graphics2D g) throws SVGException {
        Shape clip = g.getClip();
        Rectangle2D rect = super.getBoundingBox();
        return clip != null && !clip.intersects(rect);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        AffineTransform oldXform = g.getTransform();
        g.transform(this.viewXform);
        super.render(g);
        g.setTransform(oldXform);
    }

    @Override
    public Shape getShape() {
        Shape shape = super.getShape();
        return this.viewXform.createTransformedShape(shape);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        Rectangle2D rect = super.getBoundingBox();
        return this.viewXform.createTransformedShape(rect).getBounds2D();
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        boolean changeState = super.updateTime(curTime);
        return changeState;
    }
}

