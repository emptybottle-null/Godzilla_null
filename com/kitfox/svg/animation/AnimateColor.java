/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimateBase;
import com.kitfox.svg.animation.AnimateColorIface;
import com.kitfox.svg.animation.parser.AnimTimeParser;
import com.kitfox.svg.xml.ColorTable;
import com.kitfox.svg.xml.StyleAttribute;
import java.awt.Color;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AnimateColor
extends AnimateBase
implements AnimateColorIface {
    public static final String TAG_NAME = "animateColor";
    private Color fromValue;
    private Color toValue;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        super.loaderStartElement(helper, attrs, parent);
        String strn = attrs.getValue("from");
        this.fromValue = ColorTable.parseColor(strn);
        strn = attrs.getValue("to");
        this.toValue = ColorTable.parseColor(strn);
    }

    @Override
    public Color evalColor(double interp) {
        int r1 = this.fromValue.getRed();
        int g1 = this.fromValue.getGreen();
        int b1 = this.fromValue.getBlue();
        int r2 = this.toValue.getRed();
        int g2 = this.toValue.getGreen();
        int b2 = this.toValue.getBlue();
        double invInterp = 1.0 - interp;
        return new Color((int)((double)r1 * invInterp + (double)r2 * interp), (int)((double)g1 * invInterp + (double)g2 * interp), (int)((double)b1 * invInterp + (double)b2 * interp));
    }

    @Override
    protected void rebuild(AnimTimeParser animTimeParser) throws SVGException {
        String strn;
        super.rebuild(animTimeParser);
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("from"))) {
            strn = sty.getStringValue();
            this.fromValue = ColorTable.parseColor(strn);
        }
        if (this.getPres(sty.setName("to"))) {
            strn = sty.getStringValue();
            this.toValue = ColorTable.parseColor(strn);
        }
    }

    public Color getFromValue() {
        return this.fromValue;
    }

    public void setFromValue(Color fromValue) {
        this.fromValue = fromValue;
    }

    public Color getToValue() {
        return this.toValue;
    }

    public void setToValue(Color toValue) {
        this.toValue = toValue;
    }
}

