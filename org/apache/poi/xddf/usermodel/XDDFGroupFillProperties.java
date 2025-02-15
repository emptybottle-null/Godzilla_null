/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;

public class XDDFGroupFillProperties
implements XDDFFillProperties {
    private CTGroupFillProperties props;

    public XDDFGroupFillProperties() {
        this(CTGroupFillProperties.Factory.newInstance());
    }

    protected XDDFGroupFillProperties(CTGroupFillProperties properties) {
        this.props = properties;
    }

    @Internal
    public CTGroupFillProperties getXmlObject() {
        return this.props;
    }
}

