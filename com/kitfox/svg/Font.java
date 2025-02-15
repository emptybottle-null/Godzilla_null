/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg;

import com.kitfox.svg.FontFace;
import com.kitfox.svg.Glyph;
import com.kitfox.svg.MissingGlyph;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.SVGParseException;
import com.kitfox.svg.xml.StyleAttribute;
import java.util.HashMap;

public class Font
extends SVGElement {
    public static final String TAG_NAME = "font";
    int horizOriginX = 0;
    int horizOriginY = 0;
    int horizAdvX = -1;
    int vertOriginX = -1;
    int vertOriginY = -1;
    int vertAdvY = -1;
    FontFace fontFace = null;
    MissingGlyph missingGlyph = null;
    final HashMap<String, SVGElement> glyphs = new HashMap();

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderAddChild(SVGLoaderHelper helper, SVGElement child) throws SVGElementException {
        super.loaderAddChild(helper, child);
        if (child instanceof Glyph) {
            this.glyphs.put(((Glyph)child).getUnicode(), child);
        } else if (child instanceof MissingGlyph) {
            this.missingGlyph = (MissingGlyph)child;
        } else if (child instanceof FontFace) {
            this.fontFace = (FontFace)child;
        }
    }

    @Override
    public void loaderEndElement(SVGLoaderHelper helper) throws SVGParseException {
        super.loaderEndElement(helper);
        helper.universe.registerFont(this);
    }

    @Override
    protected void build() throws SVGException {
        super.build();
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("horiz-origin-x"))) {
            this.horizOriginX = sty.getIntValue();
        }
        if (this.getPres(sty.setName("horiz-origin-y"))) {
            this.horizOriginY = sty.getIntValue();
        }
        if (this.getPres(sty.setName("horiz-adv-x"))) {
            this.horizAdvX = sty.getIntValue();
        }
        if (this.getPres(sty.setName("vert-origin-x"))) {
            this.vertOriginX = sty.getIntValue();
        }
        if (this.getPres(sty.setName("vert-origin-y"))) {
            this.vertOriginY = sty.getIntValue();
        }
        if (this.getPres(sty.setName("vert-adv-y"))) {
            this.vertAdvY = sty.getIntValue();
        }
    }

    public FontFace getFontFace() {
        return this.fontFace;
    }

    public void setFontFace(FontFace face) {
        this.fontFace = face;
    }

    public MissingGlyph getGlyph(String unicode2) {
        Glyph retVal = (Glyph)this.glyphs.get(unicode2);
        if (retVal == null) {
            return this.missingGlyph;
        }
        return retVal;
    }

    public int getHorizOriginX() {
        return this.horizOriginX;
    }

    public int getHorizOriginY() {
        return this.horizOriginY;
    }

    public int getHorizAdvX() {
        return this.horizAdvX;
    }

    public int getVertOriginX() {
        if (this.vertOriginX != -1) {
            return this.vertOriginX;
        }
        this.vertOriginX = this.getHorizAdvX() / 2;
        return this.vertOriginX;
    }

    public int getVertOriginY() {
        if (this.vertOriginY != -1) {
            return this.vertOriginY;
        }
        this.vertOriginY = this.fontFace.getAscent();
        return this.vertOriginY;
    }

    public int getVertAdvY() {
        if (this.vertAdvY != -1) {
            return this.vertAdvY;
        }
        this.vertAdvY = this.fontFace.getUnitsPerEm();
        return this.vertAdvY;
    }

    @Override
    public boolean updateTime(double curTime) throws SVGException {
        return false;
    }
}

