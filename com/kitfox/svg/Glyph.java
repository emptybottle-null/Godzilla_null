/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.MissingGlyph;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.xml.StyleAttribute;

public class Glyph
extends MissingGlyph {
    public static final String TAG_NAME = "missingglyph";
    String unicode;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("unicode"))) {
            this.unicode = sty.getStringValue();
        }
    }

    public String getUnicode() {
        return this.unicode;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }
}

