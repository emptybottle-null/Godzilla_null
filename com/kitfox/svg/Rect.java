/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Rect
extends ShapeElement {
    public static final String TAG_NAME = "rect";
    float x = 0.0f;
    float y = 0.0f;
    float width = 0.0f;
    float height = 0.0f;
    float rx = 0.0f;
    float ry = 0.0f;
    RectangularShape rect;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeFloat(this.x);
        out.writeFloat(this.y);
        out.writeFloat(this.width);
        out.writeFloat(this.height);
        out.writeFloat(this.rx);
        out.writeFloat(this.ry);
    }

    private void readObject(ObjectInputStream in) throws IOException {
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.width = in.readFloat();
        this.height = in.readFloat();
        this.rx = in.readFloat();
        this.ry = in.readFloat();
        this.rect = this.rx == 0.0f && this.ry == 0.0f ? new Rectangle2D.Float(this.x, this.y, this.width, this.height) : new RoundRectangle2D.Float(this.x, this.y, this.width, this.height, this.rx * 2.0f, this.ry * 2.0f);
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("x"))) {
            this.x = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("y"))) {
            this.y = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("width"))) {
            this.width = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("height"))) {
            this.height = sty.getFloatValueWithUnits();
        }
        boolean rxSet = false;
        if (this.getPres(sty.setName("rx"))) {
            this.rx = sty.getFloatValueWithUnits();
            rxSet = true;
        }
        boolean rySet = false;
        if (this.getPres(sty.setName("ry"))) {
            this.ry = sty.getFloatValueWithUnits();
            rySet = true;
        }
        if (!rxSet) {
            this.rx = this.ry;
        }
        if (!rySet) {
            this.ry = this.rx;
        }
        this.rect = this.rx == 0.0f && this.ry == 0.0f ? new Rectangle2D.Float(this.x, this.y, this.width, this.height) : new RoundRectangle2D.Float(this.x, this.y, this.width, this.height, this.rx * 2.0f, this.ry * 2.0f);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        this.renderShape(g, this.rect);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        return this.shapeToParent(this.rect);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        return this.boundsToParent(this.includeStrokeInBounds(this.rect.getBounds2D()));
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("x")) && (newVal = sty.getFloatValueWithUnits()) != this.x) {
            this.x = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("y")) && (newVal = sty.getFloatValueWithUnits()) != this.y) {
            this.y = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("width")) && (newVal = sty.getFloatValueWithUnits()) != this.width) {
            this.width = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("height")) && (newVal = sty.getFloatValueWithUnits()) != this.height) {
            this.height = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("rx")) && (newVal = sty.getFloatValueWithUnits()) != this.rx) {
            this.rx = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("ry")) && (newVal = sty.getFloatValueWithUnits()) != this.ry) {
            this.ry = newVal;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

