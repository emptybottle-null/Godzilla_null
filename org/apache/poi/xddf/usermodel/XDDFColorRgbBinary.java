/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.Locale;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;

public class XDDFColorRgbBinary
extends XDDFColor {
    private CTSRgbColor color;

    public XDDFColorRgbBinary(byte[] color) {
        this(CTSRgbColor.Factory.newInstance(), CTColor.Factory.newInstance());
        this.setValue(color);
    }

    @Internal
    protected XDDFColorRgbBinary(CTSRgbColor color) {
        this(color, null);
    }

    @Internal
    protected XDDFColorRgbBinary(CTSRgbColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public byte[] getValue() {
        return this.color.getVal();
    }

    public void setValue(byte[] value) {
        this.color.setVal(value);
    }

    public String toRGBHex() {
        StringBuilder sb = new StringBuilder(6);
        for (byte b : this.color.getVal()) {
            sb.append(String.format(Locale.ROOT, "%02X", b));
        }
        return sb.toString().toUpperCase(Locale.ROOT);
    }
}

