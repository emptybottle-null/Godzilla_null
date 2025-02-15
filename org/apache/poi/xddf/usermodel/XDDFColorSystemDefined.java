/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.SystemColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class XDDFColorSystemDefined
extends XDDFColor {
    private CTSystemColor color;

    public XDDFColorSystemDefined(SystemColor color) {
        this(CTSystemColor.Factory.newInstance(), CTColor.Factory.newInstance());
        this.setValue(color);
    }

    @Internal
    protected XDDFColorSystemDefined(CTSystemColor color) {
        this(color, null);
    }

    @Internal
    protected XDDFColorSystemDefined(CTSystemColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public SystemColor getValue() {
        return SystemColor.valueOf(this.color.getVal());
    }

    public void setValue(SystemColor value) {
        this.color.setVal(value.underlying);
    }

    public byte[] getLastColor() {
        if (this.color.isSetLastClr()) {
            return this.color.getLastClr();
        }
        return null;
    }

    public void setLastColor(byte[] last) {
        if (last == null) {
            if (this.color.isSetLastClr()) {
                this.color.unsetLastClr();
            }
        } else {
            this.color.setLastClr(last);
        }
    }
}

