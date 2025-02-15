/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FeLight;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;

public class FeSpotLight
extends FeLight {
    public static final String TAG_NAME = "fespotlight";
    float x = 0.0f;
    float y = 0.0f;
    float z = 0.0f;
    float pointsAtX = 0.0f;
    float pointsAtY = 0.0f;
    float pointsAtZ = 0.0f;
    float specularComponent = 0.0f;
    float limitingConeAngle = 0.0f;

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
        if (this.getPres(sty.setName("pointsAtX"))) {
            this.pointsAtX = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("pointsAtY"))) {
            this.pointsAtY = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("pointsAtZ"))) {
            this.pointsAtZ = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("specularComponent"))) {
            this.specularComponent = sty.getFloatValueWithUnits();
        }
        if (this.getPres(sty.setName("limitingConeAngle"))) {
            this.limitingConeAngle = sty.getFloatValueWithUnits();
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

    public float getPointsAtX() {
        return this.pointsAtX;
    }

    public float getPointsAtY() {
        return this.pointsAtY;
    }

    public float getPointsAtZ() {
        return this.pointsAtZ;
    }

    public float getSpecularComponent() {
        return this.specularComponent;
    }

    public float getLimitingConeAngle() {
        return this.limitingConeAngle;
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
        if (this.getPres(sty.setName("pointsAtX")) && (newVal = sty.getFloatValueWithUnits()) != this.pointsAtX) {
            this.pointsAtX = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("pointsAtY")) && (newVal = sty.getFloatValueWithUnits()) != this.pointsAtY) {
            this.pointsAtY = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("pointsAtZ")) && (newVal = sty.getFloatValueWithUnits()) != this.pointsAtZ) {
            this.pointsAtZ = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("specularComponent")) && (newVal = sty.getFloatValueWithUnits()) != this.specularComponent) {
            this.specularComponent = newVal;
            stateChange = true;
        }
        if (this.getPres(sty.setName("limitingConeAngle")) && (newVal = sty.getFloatValueWithUnits()) != this.limitingConeAngle) {
            this.limitingConeAngle = newVal;
            stateChange = true;
        }
        return stateChange;
    }
}

