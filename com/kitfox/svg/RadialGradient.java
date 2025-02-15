/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Gradient;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Color;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class RadialGradient
extends Gradient {
    public static final String TAG_NAME = "radialgradient";
    float cx = 0.5f;
    float cy = 0.5f;
    boolean hasFocus = false;
    float fx = 0.0f;
    float fy = 0.0f;
    float r = 0.5f;

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
        this.hasFocus = false;
        if (this.getPres(sty.setName("fx"))) {
            this.fx = sty.getFloatValueWithUnits();
            this.hasFocus = true;
        }
        if (this.getPres(sty.setName("fy"))) {
            this.fy = sty.getFloatValueWithUnits();
            this.hasFocus = true;
        }
        if (this.getPres(sty.setName("r"))) {
            this.r = sty.getFloatValueWithUnits();
        }
    }

    @Override
    public Paint getPaint(Rectangle2D bounds, AffineTransform xform) {
        RadialGradientPaint paint;
        MultipleGradientPaint.CycleMethod method;
        switch (this.spreadMethod) {
            default: {
                method = MultipleGradientPaint.CycleMethod.NO_CYCLE;
                break;
            }
            case 1: {
                method = MultipleGradientPaint.CycleMethod.REPEAT;
                break;
            }
            case 2: {
                method = MultipleGradientPaint.CycleMethod.REFLECT;
            }
        }
        Point2D.Float pt1 = new Point2D.Float(this.cx, this.cy);
        Point2D.Float pt2 = this.hasFocus ? new Point2D.Float(this.fx, this.fy) : pt1;
        float[] stopFractions = this.getStopFractions();
        Color[] stopColors = this.getStopColors();
        if (this.gradientUnits == 1) {
            paint = new RadialGradientPaint(pt1, this.r, pt2, stopFractions, stopColors, method, MultipleGradientPaint.ColorSpaceType.SRGB, this.gradientTransform);
        } else {
            AffineTransform viewXform = new AffineTransform();
            viewXform.translate(bounds.getX(), bounds.getY());
            viewXform.scale(bounds.getWidth(), bounds.getHeight());
            viewXform.concatenate(this.gradientTransform);
            paint = new RadialGradientPaint(pt1, this.r, pt2, stopFractions, stopColors, method, MultipleGradientPaint.ColorSpaceType.SRGB, viewXform);
        }
        return paint;
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
        if (this.getPres(sty.setName("fx")) && (newVal = sty.getFloatValueWithUnits()) != this.fx) {
            this.fx = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("fy")) && (newVal = sty.getFloatValueWithUnits()) != this.fy) {
            this.fy = newVal;
            shapeChange = true;
        }
        if (this.getPres(sty.setName("r")) && (newVal = sty.getFloatValueWithUnits()) != this.r) {
            this.r = newVal;
            shapeChange = true;
        }
        return changeState;
    }
}

