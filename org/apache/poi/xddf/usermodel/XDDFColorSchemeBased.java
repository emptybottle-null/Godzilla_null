/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.SchemeColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;

public class XDDFColorSchemeBased
extends XDDFColor {
    private CTSchemeColor color;

    public XDDFColorSchemeBased(SchemeColor color) {
        this(CTSchemeColor.Factory.newInstance(), CTColor.Factory.newInstance());
        this.setValue(color);
    }

    @Internal
    protected XDDFColorSchemeBased(CTSchemeColor color) {
        this(color, null);
    }

    @Internal
    protected XDDFColorSchemeBased(CTSchemeColor color, CTColor container) {
        super(container);
        this.color = color;
    }

    @Override
    @Internal
    protected XmlObject getXmlObject() {
        return this.color;
    }

    public SchemeColor getValue() {
        return SchemeColor.valueOf(this.color.getVal());
    }

    public void setValue(SchemeColor scheme) {
        this.color.setVal(scheme.underlying);
    }
}

