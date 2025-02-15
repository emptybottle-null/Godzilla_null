/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Gradient;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LinearGradient
extends Gradient {
    public static final String TAG_NAME = "lineargradient";
    float x1 = 0.0f;
    float y1 = 0.0f;
    float x2 = 1.0f;
    float y2 = 0.0f;

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
    }

    @Override
    public Paint getPaint(Rectangle2D bounds, AffineTransform xform) {
        Paint paint;
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
        Point2D.Float pt1 = new Point2D.Float(this.x1, this.y1);
        Point2D.Float pt2 = new Point2D.Float(this.x2, this.y2);
        if (pt1.equals(pt2)) {
            Color[] colors = this.getStopColors();
            paint = colors.length > 0 ? colors[0] : Color.black;
        } else if (this.gradientUnits == 1) {
            paint = new LinearGradientPaint(pt1, pt2, this.getStopFractions(), this.getStopColors(), method, MultipleGradientPaint.ColorSpaceType.SRGB, this.gradientTransform == null ? new AffineTransform() : this.gradientTransform);
        } else {
            AffineTransform viewXform = new AffineTransform();
            viewXform.translate(bounds.getX(), bounds.getY());
            double width = Math.max(1.0, bounds.getWidth());
            double height = Math.max(1.0, bounds.getHeight());
            viewXform.scale(width, height);
            if (this.gradientTransform != null) {
                viewXform.concatenate(this.gradientTransform);
            }
            paint = new LinearGradientPaint(pt1, pt2, this.getStopFractions(), this.getStopColors(), method, MultipleGradientPaint.ColorSpaceType.SRGB, viewXform);
        }
        return paint;
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
        return changeState || shapeChange;
    }
}

