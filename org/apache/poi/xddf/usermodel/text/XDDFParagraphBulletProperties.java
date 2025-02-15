/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.text.XDDFBulletSize;
import org.apache.poi.xddf.usermodel.text.XDDFBulletSizeFollowText;
import org.apache.poi.xddf.usermodel.text.XDDFBulletSizePercent;
import org.apache.poi.xddf.usermodel.text.XDDFBulletSizePoints;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyle;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyleAutoNumbered;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyleCharacter;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyleNone;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStylePicture;
import org.apache.poi.xddf.usermodel.text.XDDFFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFParagraphBulletProperties {
    private CTTextParagraphProperties props;

    @Internal
    protected XDDFParagraphBulletProperties(CTTextParagraphProperties properties) {
        this.props = properties;
    }

    public XDDFBulletStyle getBulletStyle() {
        if (this.props.isSetBuAutoNum()) {
            return new XDDFBulletStyleAutoNumbered(this.props.getBuAutoNum());
        }
        if (this.props.isSetBuBlip()) {
            return new XDDFBulletStylePicture(this.props.getBuBlip());
        }
        if (this.props.isSetBuChar()) {
            return new XDDFBulletStyleCharacter(this.props.getBuChar());
        }
        if (this.props.isSetBuNone()) {
            return new XDDFBulletStyleNone(this.props.getBuNone());
        }
        return null;
    }

    public void setBulletStyle(XDDFBulletStyle style) {
        if (this.props.isSetBuAutoNum()) {
            this.props.unsetBuAutoNum();
        }
        if (this.props.isSetBuBlip()) {
            this.props.unsetBuBlip();
        }
        if (this.props.isSetBuChar()) {
            this.props.unsetBuChar();
        }
        if (this.props.isSetBuNone()) {
            this.props.unsetBuNone();
        }
        if (style != null) {
            if (style instanceof XDDFBulletStyleAutoNumbered) {
                this.props.setBuAutoNum(((XDDFBulletStyleAutoNumbered)style).getXmlObject());
            } else if (style instanceof XDDFBulletStyleCharacter) {
                this.props.setBuChar(((XDDFBulletStyleCharacter)style).getXmlObject());
            } else if (style instanceof XDDFBulletStyleNone) {
                this.props.setBuNone(((XDDFBulletStyleNone)style).getXmlObject());
            } else if (style instanceof XDDFBulletStylePicture) {
                this.props.setBuBlip(((XDDFBulletStylePicture)style).getXmlObject());
            }
        }
    }

    public XDDFColor getBulletColor() {
        if (this.props.isSetBuClr()) {
            return XDDFColor.forColorContainer(this.props.getBuClr());
        }
        return null;
    }

    public void setBulletColor(XDDFColor color) {
        if (this.props.isSetBuClrTx()) {
            this.props.unsetBuClrTx();
        }
        if (color == null) {
            if (this.props.isSetBuClr()) {
                this.props.unsetBuClr();
            }
        } else {
            this.props.setBuClr(color.getColorContainer());
        }
    }

    public void setBulletColorFollowText() {
        if (this.props.isSetBuClr()) {
            this.props.unsetBuClr();
        }
        if (!this.props.isSetBuClrTx()) {
            this.props.addNewBuClrTx();
        }
    }

    public XDDFFont getBulletFont() {
        if (this.props.isSetBuFont()) {
            return new XDDFFont(FontGroup.SYMBOL, this.props.getBuFont());
        }
        return null;
    }

    public void setBulletFont(XDDFFont font) {
        if (this.props.isSetBuFontTx()) {
            this.props.unsetBuFontTx();
        }
        if (font == null) {
            if (this.props.isSetBuFont()) {
                this.props.unsetBuFont();
            }
        } else {
            this.props.setBuFont(font.getXmlObject());
        }
    }

    public void setBulletFontFollowText() {
        if (this.props.isSetBuFont()) {
            this.props.unsetBuFont();
        }
        if (!this.props.isSetBuFontTx()) {
            this.props.addNewBuFontTx();
        }
    }

    public XDDFBulletSize getBulletSize() {
        if (this.props.isSetBuSzPct()) {
            return new XDDFBulletSizePercent(this.props.getBuSzPct(), null);
        }
        if (this.props.isSetBuSzPts()) {
            return new XDDFBulletSizePoints(this.props.getBuSzPts());
        }
        if (this.props.isSetBuSzTx()) {
            return new XDDFBulletSizeFollowText(this.props.getBuSzTx());
        }
        return null;
    }

    public void setBulletSize(XDDFBulletSize size) {
        if (this.props.isSetBuSzPct()) {
            this.props.unsetBuSzPct();
        }
        if (this.props.isSetBuSzPts()) {
            this.props.unsetBuSzPts();
        }
        if (this.props.isSetBuSzTx()) {
            this.props.unsetBuSzTx();
        }
        if (size != null) {
            if (size instanceof XDDFBulletSizeFollowText) {
                this.props.setBuSzTx(((XDDFBulletSizeFollowText)size).getXmlObject());
            } else if (size instanceof XDDFBulletSizePercent) {
                this.props.setBuSzPct(((XDDFBulletSizePercent)size).getXmlObject());
            } else if (size instanceof XDDFBulletSizePoints) {
                this.props.setBuSzPts(((XDDFBulletSizePoints)size).getXmlObject());
            }
        }
    }

    public void clearAll() {
        if (this.props.isSetBuAutoNum()) {
            this.props.unsetBuAutoNum();
        }
        if (this.props.isSetBuBlip()) {
            this.props.unsetBuBlip();
        }
        if (this.props.isSetBuChar()) {
            this.props.unsetBuChar();
        }
        if (this.props.isSetBuNone()) {
            this.props.unsetBuNone();
        }
        if (this.props.isSetBuClr()) {
            this.props.unsetBuClr();
        }
        if (this.props.isSetBuClrTx()) {
            this.props.unsetBuClrTx();
        }
        if (this.props.isSetBuFont()) {
            this.props.unsetBuFont();
        }
        if (this.props.isSetBuFontTx()) {
            this.props.unsetBuFontTx();
        }
        if (this.props.isSetBuSzPct()) {
            this.props.unsetBuSzPct();
        }
        if (this.props.isSetBuSzPts()) {
            this.props.unsetBuSzPts();
        }
        if (this.props.isSetBuSzTx()) {
            this.props.unsetBuSzTx();
        }
    }
}

