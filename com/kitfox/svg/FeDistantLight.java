/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FeLight;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;

public class FeDistantLight
extends FeLight {
    public static final String TAG_NAME = "fedistantlight";
    float azimuth = 0.0f;
    float elevation = 0.0f;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("azimuth"))) {
            this.azimuth = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("elevation"))) {
            this.elevation = sty.getFloatValueWithUnits();
        }
    }

    public float getAzimuth() {
        return this.azimuth;
    }

    public float getElevation() {
        return this.elevation;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        float newVal;
        StyleAttribute sty = new StyleAttribute();
        boolean stateChange = false;
        if (this.getPres(sty.setName("azimuth")) && (newVal = sty.getFloatValueWithUnits()) != this.azimuth) {
            this.azimuth = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("elevation")) && (newVal = sty.getFloatValueWithUnits()) != this.elevation) {
            this.elevation = newVal;
            stateChange = true;
        }
        return stateChange;
    }
}

