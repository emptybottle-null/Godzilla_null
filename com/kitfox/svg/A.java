/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.Group;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;
import java.net.URI;

public class A
extends Group {
    public static final String TAG_NAME = "a";
    URI href;
    String title;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("xlink:href"))) {
            this.href = sty.getURIValue(this.getXMLBase());
        }
        if (this.getPres(sty.setName("xlink:title"))) {
            this.title = sty.getStringValue();
        }
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        boolean changeState = super.updateTime(curTime);
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("xlink:href"))) {
            this.href = sty.getURIValue(this.getXMLBase());
        }
        if (this.getPres(sty.setName("xlink:title"))) {
            this.title = sty.getStringValue();
        }
        return changeState;
    }
}

