/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;

public class XDDFNoFillProperties
implements XDDFFillProperties {
    private CTNoFillProperties props;

    public XDDFNoFillProperties() {
        this(CTNoFillProperties.Factory.newInstance());
    }

    protected XDDFNoFillProperties(CTNoFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTNoFillProperties getXmlObject() {
        return this.props;
    }
}

