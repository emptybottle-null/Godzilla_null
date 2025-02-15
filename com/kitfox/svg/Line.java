/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Line
extends ShapeElement {
    public static final String TAG_NAME = "line";
    float x1 = 0.0f;
    float y1 = 0.0f;
    float x2 = 0.0f;
    float y2 = 0.0f;
    Line2D.Float line;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("x1"))) {
            this.x1 = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("y1"))) {
            this.y1 = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("x2"))) {
            this.x2 = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("y2"))) {
            this.y2 = sty.getFloatValueWithUnits();
        }
        this.line = new Line2D.Float(this.x1, this.y1, this.x2, this.y2);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        this.renderShape(g, this.line);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        return this.shapeToParent(this.line);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        return this.boundsToParent(this.includeStrokeInBounds(this.line.getBounds2D()));
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("x1")) && (newVal = sty.getFloatValueWithUnits()) != this.x1) {
            this.x1 = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("y1")) && (newVal = sty.getFloatValueWithUnits()) != this.y1) {
            this.y1 = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("x2")) && (newVal = sty.getFloatValueWithUnits()) != this.x2) {
            this.x2 = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("y2")) && (newVal = sty.getFloatValueWithUnits()) != this.y2) {
            this.y2 = newVal;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

