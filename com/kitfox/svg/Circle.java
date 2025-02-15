/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle
extends ShapeElement {
    public static final String TAG_NAME = "circle";
    float cx = 0.0f;
    float cy = 0.0f;
    float r = 0.0f;
    Ellipse2D.Float circle = new Ellipse2D.Float();

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("cx"))) {
            this.cx = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("cy"))) {
            this.cy = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("r"))) {
            this.r = sty.getFloatValueWithUnits();
        }
        this.circle.setFrame(this.cx - this.r, this.cy - this.r, this.r * 2.0f, this.r * 2.0f);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        this.renderShape(g, this.circle);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        return this.shapeToParent(this.circle);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        return this.boundsToParent(this.includeStrokeInBounds(this.circle.getBounds2D()));
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("cx")) && (newVal = sty.getFloatValueWithUnits()) != this.cx) {
            this.cx = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("cy")) && (newVal = sty.getFloatValueWithUnits()) != this.cy) {
            this.cy = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("r")) && (newVal = sty.getFloatValueWithUnits()) != this.r) {
            this.r = newVal;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

