/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class Path
extends ShapeElement {
    public static final String TAG_NAME = "path";
    int fillRule = 1;
    String d = "";
    GeneralPath path;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        String fillRuleStrn = this.getStyle(sty.setName("fill-rule")) ? sty.getStringValue() : "nonzero";
        int n = this.fillRule = fillRuleStrn.equals("evenodd") ? 0 : 1;
        if (this.getPres(sty.setName("d"))) {
            this.d = sty.getStringValue();
        }
        this.path = Path.buildPath(this.d, this.fillRule);
    }

    @Override
    public void render(Graphics2D g) throws SVGException {
        this.beginLayer(g);
        this.renderShape(g, this.path);
        this.finishLayer(g);
    }

    @Override
    public Shape getShape() {
        return this.shapeToParent(this.path);
    }

    @Override
    public Rectangle2D getBoundingBox() throws SVGException {
        return this.boundsToParent(this.includeStrokeInBounds(this.path.getBounds2D()));
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        String newVal;
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getStyle(sty.setName("fill-rule"))) {
            int newVal2;
            int n = newVal2 = sty.getStringValue().equals("evenodd") ? 0 : 1;
            if (newVal2 != this.fillRule) {
                this.fillRule = newVal2;
                changeState = true;
            }
        }
        if (this.getPres(sty.setName("d")) && !(newVal = sty.getStringValue()).equals(this.d)) {
            this.d = newVal;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

