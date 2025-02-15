/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.RenderableElement;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.net.URI;

public class Use
extends ShapeElement {
    public static final String TAG_NAME = "use";
    float x = 0.0f;
    float y = 0.0f;
    float width = 1.0f;
    float height = 1.0f;
    URI href = null;
    AffineTransform refXform;

    @Override
    public String getTagName() {
        return TAG_NAME;
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
        if (this.getPres(sty.setName("xlink:href"))) {
            URI src;
            this.href = src = sty.getURIValue(this.getXMLBase());
        }
        this.refXform = new AffineTransform();
        this.refXform.translate(this.x, this.y);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        AffineTransform oldXform = g.getTransform();
        g.transform(this.refXform);
        SVGElement ref = this.diagram.getUniverse().getElement(this.href);
        if (ref == null || !(ref instanceof RenderableElement)) {
            return;
        }
        RenderableElement rendEle = (RenderableElement)ref;
        rendEle.pushParentContext(this);
        rendEle.render(g);
        rendEle.popParentContext();
        g.setTransform(oldXform);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        SVGElement ref = this.diagram.getUniverse().getElement(this.href);
        if (ref instanceof ShapeElement) {
            Shape shape = ((ShapeElement)ref).getShape();
            shape = this.refXform.createTransformedShape(shape);
            shape = this.shapeToParent(shape);
            return shape;
        }
        return null;
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        SVGElement ref = this.diagram.getUniverse().getElement(this.href);
        if (ref instanceof ShapeElement) {
            ShapeElement shapeEle = (ShapeElement)ref;
            shapeEle.pushParentContext(this);
            Rectangle2D bounds = shapeEle.getBoundingBox();
            shapeEle.popParentContext();
            bounds = this.refXform.createTransformedShape(bounds).getBounds2D();
            bounds = this.boundsToParent(bounds);
            return bounds;
        }
        return null;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        URI src;
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
        if (this.getPres(sty.setName("xlink:href")) && !(src = sty.getURIValue(this.getXMLBase())).equals(this.href)) {
            this.href = src;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

