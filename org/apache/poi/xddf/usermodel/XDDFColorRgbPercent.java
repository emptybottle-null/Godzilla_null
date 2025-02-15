/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.Locale;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;

public class XDDFColorRgbPercent
extends XDDFColor {
    private CTScRgbColor color;

    public XDDFColorRgbPercent(int red, int green, int blue) {
        this(CTScRgbColor.Factory.newInstance(), CTColor.Factory.newInstance());
        this.setRed(red);
        this.setGreen(green);
        this.setBlue(blue);
    }

    @Internal
    protected XDDFColorRgbPercent(CTScRgbColor color) {
        this(color, null);
    }

    @Internal
    protected XDDFColorRgbPercent(CTScRgbColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public int getRed() {
        return this.color.getR();
    }

    public void setRed(int red) {
        this.color.setR(this.normalize(red));
    }

    public int getGreen() {
        return this.color.getG();
    }

    public void setGreen(int green) {
        this.color.setG(this.normalize(green));
    }

    public int getBlue() {
        return this.color.getB();
    }

    public void setBlue(int blue) {
        this.color.setB(this.normalize(blue));
    }

    private int normalize(int value) {
        if (value < 0) {
            return 0;
        }
        if (100000 < value) {
            return 100000;
        }
        return value;
    }

    public String toRGBHex() {
        StringBuilder sb = new StringBuilder(6);
        this.appendHex(sb, this.color.getR());
        this.appendHex(sb, this.color.getG());
        this.appendHex(sb, this.color.getB());
        return sb.toString().toUpperCase(Locale.ROOT);
    }

    private void appendHex(StringBuilder sb, int value) {
        int b = value * 255 / 100000;
        sb.append(String.format(Locale.ROOT, "%02X", b));
    }
}

