/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shadow;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.usermodel.XSLFColor;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;

public class XSLFShadow
extends XSLFShape
implements Shadow<XSLFShape, XSLFTextParagraph> {
    private XSLFSimpleShape _parent;

    XSLFShadow(CTOuterShadowEffect shape, XSLFSimpleShape parentShape) {
        super(shape, parentShape.getSheet());
        this._parent = parentShape;
    }

    public XSLFSimpleShape getShadowParent() {
        return this._parent;
    }

    @Override
    public Rectangle2D getAnchor() {
        return this._parent.getAnchor();
    }

    public void setAnchor(Rectangle2D anchor) {
        throw new IllegalStateException("You can't set anchor of a shadow");
    }

    @Override
    public double getDistance() {
        CTOuterShadowEffect ct = (CTOuterShadowEffect)this.getXmlObject();
        return ct.isSetDist() ? Units.toPoints(ct.getDist()) : 0.0;
    }

    @Override
    public double getAngle() {
        CTOuterShadowEffect ct = (CTOuterShadowEffect)this.getXmlObject();
        return ct.isSetDir() ? (double)ct.getDir() / 60000.0 : 0.0;
    }

    @Override
    public double getBlur() {
        CTOuterShadowEffect ct = (CTOuterShadowEffect)this.getXmlObject();
        return ct.isSetBlurRad() ? Units.toPoints(ct.getBlurRad()) : 0.0;
    }

    public Color getFillColor() {
        PaintStyle.SolidPaint ps = this.getFillStyle();
        if (ps == null) {
            return null;
        }
        return DrawPaint.applyColorTransform(ps.getSolidColor());
    }

    @Override
    public PaintStyle.SolidPaint getFillStyle() {
        XSLFTheme theme = this.getSheet().getTheme();
        CTOuterShadowEffect ct = (CTOuterShadowEffect)this.getXmlObject();
        if (ct == null) {
            return null;
        }
        CTSchemeColor phClr = ct.getSchemeClr();
        XSLFColor xc = new XSLFColor(ct, theme, phClr, this.getSheet());
        return DrawPaint.createSolidPaint(xc.getColorStyle());
    }
}

