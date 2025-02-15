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

public class Ellipse
extends ShapeElement {
    public static final String TAG_NAME = "ellipse";
    float cx = 0.0f;
    float cy = 0.0f;
    float rx = 0.0f;
    float ry = 0.0f;
    Ellipse2D.Float ellipse = new Ellipse2D.Float();

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
        if (this.getPres(sty.setName("rx"))) {
            this.rx = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("ry"))) {
            this.ry = sty.getFloatValueWithUnits();
        }
        this.ellipse.setFrame(this.cx - this.rx, this.cy - this.ry, this.rx * 2.0f, this.ry * 2.0f);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        this.renderShape(g, this.ellipse);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        return this.shapeToParent(this.ellipse);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        return this.boundsToParent(this.includeStrokeInBounds(this.ellipse.getBounds2D()));
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newRy;
        float newRx;
        float newCy;
        float newCx;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("cx")) && (newCx = sty.getFloatValueWithUnits()) != this.cx) {
            this.cx = newCx;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("cy")) && (newCy = sty.getFloatValueWithUnits()) != this.cy) {
            this.cy = newCy;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("rx")) && (newRx = sty.getFloatValueWithUnits()) != this.rx) {
            this.rx = newRx;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("ry")) && (newRy = sty.getFloatValueWithUnits()) != this.ry) {
            this.ry = newRy;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

