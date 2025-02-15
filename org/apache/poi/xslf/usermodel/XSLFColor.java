/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.AbstractColorStyle;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.PresetColor;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;
import org.w3c.dom.Node;

@Internal
public class XSLFColor {
    private static final POILogger LOGGER = POILogFactory.getLogger(XSLFColor.class);
    private XmlObject _xmlObject;
    private Color _color;
    private CTSchemeColor _phClr;
    private XSLFSheet _sheet;

    public XSLFColor(XmlObject obj, XSLFTheme theme, CTSchemeColor phClr, XSLFSheet sheet) {
        this._xmlObject = obj;
        this._phClr = phClr;
        this._sheet = sheet;
        this._color = this.toColor(obj, theme);
    }

    @Internal
    public XmlObject getXmlObject() {
        return this._xmlObject;
    }

    public Color getColor() {
        return DrawPaint.applyColorTransform(this.getColorStyle());
    }

    public ColorStyle getColorStyle() {
        return new XSLFColorStyle(this._xmlObject, this._color, this._phClr);
    }

    private Color toColor(CTHslColor hsl) {
        return DrawPaint.HSL2RGB((double)hsl.getHue2() / 60000.0, (double)hsl.getSat2() / 1000.0, (double)hsl.getLum2() / 1000.0, 1.0);
    }

    private Color toColor(CTPresetColor prst) {
        String colorName = prst.getVal().toString();
        PresetColor pc = PresetColor.valueOfOoxmlId(colorName);
        return pc != null ? pc.color : null;
    }

    private Color toColor(CTSchemeColor schemeColor, XSLFTheme theme) {
        String colorRef = schemeColor.getVal().toString();
        if (this._phClr != null) {
            colorRef = this._phClr.getVal().toString();
        }
        CTColor ctColor = theme == null ? null : theme.getCTColor(this._sheet.mapSchemeColor(colorRef));
        return ctColor != null ? this.toColor(ctColor, null) : null;
    }

    private Color toColor(CTScRgbColor scrgb) {
        return new Color(DrawPaint.lin2srgb(scrgb.getR()), DrawPaint.lin2srgb(scrgb.getG()), DrawPaint.lin2srgb(scrgb.getB()));
    }

    private Color toColor(CTSRgbColor srgb) {
        byte[] val = srgb.getVal();
        return new Color(0xFF & val[0], 0xFF & val[1], 0xFF & val[2]);
    }

    private Color toColor(CTSystemColor sys) {
        if (sys.isSetLastClr()) {
            byte[] val = sys.getLastClr();
            return new Color(0xFF & val[0], 0xFF & val[1], 0xFF & val[2]);
        }
        String colorName = sys.getVal().toString();
        PresetColor pc = PresetColor.valueOfOoxmlId(colorName);
        return pc != null && pc.color != null ? pc.color : Color.black;
    }

    private Color toColor(XmlObject obj, XSLFTheme theme) {
        Color color = null;
        ArrayList<XmlObject> xo = new ArrayList<XmlObject>();
        xo.add(obj);
        xo.addAll(Arrays.asList(obj.selectPath("*")));
        boolean isFirst = true;
        for (XmlObject ch : xo) {
            if (ch instanceof CTHslColor) {
                color = this.toColor((CTHslColor)ch);
            } else if (ch instanceof CTPresetColor) {
                color = this.toColor((CTPresetColor)ch);
            } else if (ch instanceof CTSchemeColor) {
                color = this.toColor((CTSchemeColor)ch, theme);
            } else if (ch instanceof CTScRgbColor) {
                color = this.toColor((CTScRgbColor)ch);
            } else if (ch instanceof CTSRgbColor) {
                color = this.toColor((CTSRgbColor)ch);
            } else if (ch instanceof CTSystemColor) {
                color = this.toColor((CTSystemColor)ch);
            } else if (!(ch instanceof CTFontReference) && !isFirst) {
                throw new IllegalArgumentException("Unexpected color choice: " + ch.getClass());
            }
            if (color != null) break;
            isFirst = false;
        }
        return color;
    }

    @Internal
    protected void setColor(Color color) {
        CTPositiveFixedPercentage alphaPct;
        float[] rgbaf;
        boolean addAlpha;
        if (!(this._xmlObject instanceof CTSolidColorFillProperties)) {
            LOGGER.log(7, "XSLFColor.setColor currently only supports CTSolidColorFillProperties");
            return;
        }
        CTSolidColorFillProperties fill = (CTSolidColorFillProperties)this._xmlObject;
        if (fill.isSetSrgbClr()) {
            fill.unsetSrgbClr();
        }
        if (fill.isSetScrgbClr()) {
            fill.unsetScrgbClr();
        }
        if (fill.isSetHslClr()) {
            fill.unsetHslClr();
        }
        if (fill.isSetPrstClr()) {
            fill.unsetPrstClr();
        }
        if (fill.isSetSchemeClr()) {
            fill.unsetSchemeClr();
        }
        if (fill.isSetSysClr()) {
            fill.unsetSysClr();
        }
        boolean bl = addAlpha = (rgbaf = color.getRGBComponents(null)).length == 4 && rgbaf[3] < 1.0f;
        if (XSLFColor.isInt(rgbaf[0]) && XSLFColor.isInt(rgbaf[1]) && XSLFColor.isInt(rgbaf[2])) {
            CTSRgbColor rgb = fill.addNewSrgbClr();
            byte[] rgbBytes = new byte[]{(byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue()};
            rgb.setVal(rgbBytes);
            alphaPct = addAlpha ? rgb.addNewAlpha() : null;
        } else {
            CTScRgbColor rgb = fill.addNewScrgbClr();
            rgb.setR(DrawPaint.srgb2lin(rgbaf[0]));
            rgb.setG(DrawPaint.srgb2lin(rgbaf[1]));
            rgb.setB(DrawPaint.srgb2lin(rgbaf[2]));
            CTPositiveFixedPercentage cTPositiveFixedPercentage = alphaPct = addAlpha ? rgb.addNewAlpha() : null;
        }
        if (alphaPct != null) {
            alphaPct.setVal((int)(100000.0f * rgbaf[3]));
        }
    }

    private static boolean isInt(float f) {
        return Math.abs((double)f * 255.0 - Math.rint((double)f * 255.0)) < 1.0E-5;
    }

    private static int getRawValue(CTSchemeColor phClr, XmlObject xmlObject, String elem) {
        Node attr;
        XmlObject[] obj;
        String query = "declare namespace a='http://schemas.openxmlformats.org/drawingml/2006/main' $this//a:" + elem;
        if (phClr != null && (obj = phClr.selectPath(query)).length == 1 && (attr = obj[0].getDomNode().getAttributes().getNamedItem("val")) != null) {
            return Integer.parseInt(attr.getNodeValue());
        }
        obj = xmlObject.selectPath(query);
        if (obj.length == 1 && (attr = obj[0].getDomNode().getAttributes().getNamedItem("val")) != null) {
            return Integer.parseInt(attr.getNodeValue());
        }
        return -1;
    }

    private int getPercentageValue(String elem) {
        int val = XSLFColor.getRawValue(this._phClr, this._xmlObject, elem);
        return val == -1 ? val : val / 1000;
    }

    int getAlpha() {
        return this.getPercentageValue("alpha");
    }

    int getAlphaMod() {
        return this.getPercentageValue("alphaMod");
    }

    int getAlphaOff() {
        return this.getPercentageValue("alphaOff");
    }

    int getHue() {
        int val = XSLFColor.getRawValue(this._phClr, this._xmlObject, "hue");
        return val == -1 ? val : val / 60000;
    }

    int getHueMod() {
        return this.getPercentageValue("hueMod");
    }

    int getHueOff() {
        return this.getPercentageValue("hueOff");
    }

    int getLum() {
        return this.getPercentageValue("lum");
    }

    int getLumMod() {
        return this.getPercentageValue("lumMod");
    }

    int getLumOff() {
        return this.getPercentageValue("lumOff");
    }

    int getSat() {
        return this.getPercentageValue("sat");
    }

    int getSatMod() {
        return this.getPercentageValue("satMod");
    }

    int getSatOff() {
        return this.getPercentageValue("satOff");
    }

    int getRed() {
        return this.getPercentageValue("red");
    }

    int getRedMod() {
        return this.getPercentageValue("redMod");
    }

    int getRedOff() {
        return this.getPercentageValue("redOff");
    }

    int getGreen() {
        return this.getPercentageValue("green");
    }

    int getGreenMod() {
        return this.getPercentageValue("greenMod");
    }

    int getGreenOff() {
        return this.getPercentageValue("greenOff");
    }

    int getBlue() {
        return this.getPercentageValue("blue");
    }

    int getBlueMod() {
        return this.getPercentageValue("blueMod");
    }

    int getBlueOff() {
        return this.getPercentageValue("blueOff");
    }

    public int getShade() {
        return this.getPercentageValue("shade");
    }

    public int getTint() {
        return this.getPercentageValue("tint");
    }

    private static class XSLFColorStyle
    extends AbstractColorStyle {
        private XmlObject xmlObject;
        private Color color;
        private CTSchemeColor phClr;

        XSLFColorStyle(XmlObject xmlObject, Color color, CTSchemeColor phClr) {
            this.xmlObject = xmlObject;
            this.color = color;
            this.phClr = phClr;
        }

        @Override
        public Color getColor() {
            return this.color;
        }

        @Override
        public int getAlpha() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "alpha");
        }

        @Override
        public int getHueOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "hueOff");
        }

        @Override
        public int getHueMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "hueMod");
        }

        @Override
        public int getSatOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "satOff");
        }

        @Override
        public int getSatMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "satMod");
        }

        @Override
        public int getLumOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "lumOff");
        }

        @Override
        public int getLumMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "lumMod");
        }

        @Override
        public int getShade() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "shade");
        }

        @Override
        public int getTint() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "tint");
        }
    }
}

