/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Color;

public class Stop
extends SVGElement {
    public static final String TAG_NAME = "stop";
    float offset = 0.0f;
    float opacity = 1.0f;
    Color color = Color.black;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("offset"))) {
            this.offset = sty.getFloatValue();
            String units = sty.getUnits();
            if (units != null && units.equals("%")) {
                this.offset /= 100.0f;
            }
            if (this.offset > 1.0f) {
                this.offset = 1.0f;
            }
            if (this.offset < 0.0f) {
                this.offset = 0.0f;
            }
        }
        if (this.getStyle(sty.setName("stop-color"))) {
            this.color = sty.getColorValue();
        }
        if (this.getStyle(sty.setName("stop-opacity"))) {
            this.opacity = sty.getRatioValue();
        }
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        StyleAttribute sty = new StyleAttribute();
        boolean shapeChange = false;
        if (this.getPres(sty.setName("offset")) && (newVal = sty.getFloatValue()) != this.offset) {
            this.offset = newVal;
            shapeChange = true;
        }
        if (this.getStyle(sty.setName("stop-color")) && (newVal = sty.getColorValue()) != this.color) {
            this.color = newVal;
            shapeChange = true;
        }
        if (this.getStyle(sty.setName("stop-opacity")) && (newVal = sty.getFloatValue()) != this.opacity) {
            this.opacity = newVal;
            shapeChange = true;
        }
        return shapeChange;
    }
}

