/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;

public class XDDFPath {
    private CTPath2D path;

    @Internal
    protected XDDFPath(CTPath2D path) {
        this.path = path;
    }

    @Internal
    public CTPath2D getXmlObject() {
        return this.path;
    }
}

