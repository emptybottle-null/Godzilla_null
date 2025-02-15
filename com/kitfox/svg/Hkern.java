/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;

public class Hkern
extends SVGElement {
    public static final String TAG_NAME = "hkern";
    String u1;
    String u2;
    int k;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("u1"))) {
            this.u1 = sty.getStringValue();
        }
        if (this.getPres(sty.setName("u2"))) {
            this.u2 = sty.getStringValue();
        }
        if (this.getPres(sty.setName("k"))) {
            this.k = sty.getIntValue();
        }
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }
}

