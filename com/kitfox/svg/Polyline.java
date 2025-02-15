/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGException;
import com.kitfox.svg.ShapeElement;
import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.XMLParseUtil;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

public class Polyline
extends ShapeElement {
    public static final String TAG_NAME = "polyline";
    int fillRule = 1;
    String pointsStrn = "";
    GeneralPath path;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("points"))) {
            this.pointsStrn = sty.getStringValue();
        }
        String fillRuleStrn = this.getStyle(sty.setName("fill-rule")) ? sty.getStringValue() : "nonzero";
        this.fillRule = fillRuleStrn.equals("evenodd") ? 0 : 1;
        this.buildPath();
    }

    protected void buildPath() {
        float[] points = XMLParseUtil.parseFloatList(this.pointsStrn);
        this.path = new GeneralPath(this.fillRule, points.length / 2);
        this.path.moveTo(points[0], points[1]);
        for (int i = 2; i < points.length; i += 2) {
            this.path.lineTo(points[i], points[i + 1]);
        }
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
                shapeChange = true;
            }
        }
        if (this.getPres(sty.setName("points")) && !(newVal = sty.getStringValue()).equals(this.pointsStrn)) {
            this.pointsStrn = newVal;
            shapeChange = true;
        }
        if (shapeChange) {
            this.build();
        }
        return changeState || shapeChange;
    }
}

