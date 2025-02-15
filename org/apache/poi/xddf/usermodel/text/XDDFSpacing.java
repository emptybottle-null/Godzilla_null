/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;

public abstract class XDDFSpacing {
    protected CTTextSpacing spacing;

    @Internal
    protected XDDFSpacing(CTTextSpacing spacing) {
        this.spacing = spacing;
    }

    public abstract Kind getType();

    @Internal
    protected CTTextSpacing getXmlObject() {
        return this.spacing;
    }

    public static enum Kind {
        PERCENT,
        POINTS;

    }
}

