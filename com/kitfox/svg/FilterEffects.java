/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.xml.StyleAttribute;
import java.net.URI;
import java.net.URL;

public class FilterEffects
extends SVGElement {
    public static final String TAG_NAME = "filtereffects";
    public static final int FP_SOURCE_GRAPHIC = 0;
    public static final int FP_SOURCE_ALPHA = 1;
    public static final int FP_BACKGROUND_IMAGE = 2;
    public static final int FP_BACKGROUND_ALPHA = 3;
    public static final int FP_FILL_PAINT = 4;
    public static final int FP_STROKE_PAINT = 5;
    public static final int FP_CUSTOM = 5;
    private int filterPrimitiveTypeIn;
    private String filterPrimitiveRefIn;
    float x = 0.0f;
    float y = 0.0f;
    float width = 1.0f;
    float height = 1.0f;
    String result = "defaultFilterName";
    URL href = null;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
        if (child instanceof FilterEffects) {
            // empty if block
        }
    }

    @Override
    protected void build() throws SVGException {
        super.build();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
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
        if (this.getPres(sty.setName("width")) && (newVal = sty.getFloatValueWithUnits()) != this.width) {
            this.width = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("height")) && (newVal = sty.getFloatValueWithUnits()) != this.height) {
            this.height = newVal;
            stateChange = true;
        }
        try {
            URI src;
            URL newVal2;
            if (this.getPres(sty.setName("xlink:href")) && !(newVal2 = (src = sty.getURIValue(this.getXMLBase())).toURL()).equals(this.href)) {
                this.href = newVal2;
                stateChange = true;
            }
        } catch (Exception e) {
            throw new SVGException(e);
        }
        return stateChange;
    }
}

