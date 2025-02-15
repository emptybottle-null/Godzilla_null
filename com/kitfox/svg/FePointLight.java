/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FeLight;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;

public class FePointLight
extends FeLight {
    public static final String TAG_NAME = "fepointlight";
    float x = 0.0f;
    float y = 0.0f;
    float z = 0.0f;

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
        if (this.getPres(sty.setName("z"))) {
            this.z = sty.getFloatValueWithUnits();
        }
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        StyleAttribute sty = new StyleAttribute();
        boolean stateChange = false;
        if (this.getPres(sty.setName("x")) && (newVal = sty.getFloatValueWithUnits()) != this.x) {
            this.x = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("y")) && (newVal = sty.getFloatValueWithUnits()) != this.y) {
            this.y = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("z")) && (newVal = sty.getFloatValueWithUnits()) != this.z) {
            this.z = newVal;
            stateChange = true;
        }
        return stateChange;
    }
}

