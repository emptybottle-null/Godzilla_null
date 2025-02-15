/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;

public class XDDFColorHsl
extends XDDFColor {
    private CTHslColor color;

    public XDDFColorHsl(int hue, int saturation, int luminance) {
        this(CTHslColor.Factory.newInstance(), CTColor.Factory.newInstance());
        this.setHue(hue);
        this.setSaturation(saturation);
        this.setLuminance(luminance);
    }

    @Internal
    protected XDDFColorHsl(CTHslColor color) {
        this(color, null);
    }

    @Internal
    protected XDDFColorHsl(CTHslColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public int getHue() {
        return this.color.getHue2();
    }

    public void setHue(int hue) {
        this.color.setHue2(hue);
    }

    public int getSaturation() {
        return this.color.getSat2();
    }

    public void setSaturation(int saturation) {
        this.color.setSat2(saturation);
    }

    public int getLuminance() {
        return this.color.getLum2();
    }

    public void setLuminance(int lightness) {
        this.color.setLum2(lightness);
    }
}

