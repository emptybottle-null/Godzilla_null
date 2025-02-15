/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FilterEffects;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.geom.Point2D;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class Filter
extends SVGElement {
    public static final String TAG_NAME = "filter";
    public static final int FU_OBJECT_BOUNDING_BOX = 0;
    public static final int FU_USER_SPACE_ON_USE = 1;
    protected int filterUnits = 0;
    public static final int PU_OBJECT_BOUNDING_BOX = 0;
    public static final int PU_USER_SPACE_ON_USE = 1;
    protected int primitiveUnits = 0;
    float x = 0.0f;
    float y = 0.0f;
    float width = 1.0f;
    float height = 1.0f;
    Point2D filterRes = new Point2D.Double();
    URL href = null;
    final ArrayList<SVGElement> filterEffects = new ArrayList();

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
        if (child instanceof FilterEffects) {
            this.filterEffects.add(child);
        }
    }

    @Override
    protected void build() throws SVGException {
        String strn;
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("filterUnits"))) {
            strn = sty.getStringValue().toLowerCase();
            this.filterUnits = strn.equals("userspaceonuse") ? 1 : 0;
        }
        if (this.getPres(sty.setName("primitiveUnits"))) {
            strn = sty.getStringValue().toLowerCase();
            this.primitiveUnits = strn.equals("userspaceonuse") ? 1 : 0;
        }
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
        try {
            if (this.getPres(sty.setName("xlink:href"))) {
                URI src = sty.getURIValue(this.getXMLBase());
                this.href = src.toURL();
            }
        } catch (Exception e) {
            throw new SVGException(e);
        }
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
        String strn;
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
        if (this.getPres(sty.setName("filterUnits")) && (newVal = (strn = sty.getStringValue().toLowerCase()).equals("userspaceonuse") ? 1 : 0) != this.filterUnits) {
            this.filterUnits = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("primitiveUnits")) && (newVal = (strn = sty.getStringValue().toLowerCase()).equals("userspaceonuse") ? 1 : 0) != this.filterUnits) {
            this.primitiveUnits = newVal;
            stateChange = true;
        }
        return stateChange;
    }
}

