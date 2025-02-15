/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.parser.AnimTimeParser;
import com.kitfox.svg.xml.StyleAttribute;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class SetSmil
extends AnimationElement {
    public static final String TAG_NAME = "set";
    private String toValue;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        super.loaderStartElement(helper, attrs, parent);
        this.toValue = attrs.getValue("to");
    }

    @Override
    protected void rebuild(AnimTimeParser animTimeParser) throws SVGException {
        super.rebuild(animTimeParser);
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("to"))) {
            String newVal;
            this.toValue = newVal = sty.getStringValue();
        }
    }

    public String getToValue() {
        return this.toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }
}

