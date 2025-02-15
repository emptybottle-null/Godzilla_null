/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.StyleSheet;

public class Style
extends SVGElement {
    public static final String TAG_NAME = "style";
    String type;
    StringBuffer text = new StringBuffer();
    StyleSheet styleSheet;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddText(SVGLoaderHelper helper, String text) {
        this.text.append(text);
        this.styleSheet = null;
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("type"))) {
            this.type = sty.getStringValue();
        }
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }

    public StyleSheet getStyleSheet() {
        if (this.styleSheet == null && this.text.length() > 0) {
            this.styleSheet = StyleSheet.parseSheet(this.text.toString());
        }
        return this.styleSheet;
    }
}

