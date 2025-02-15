/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;

public class XDDFShape3D {
    private CTShape3D shape;

    protected XDDFShape3D(CTShape3D shape) {
        this.shape = shape;
    }

    @Internal
    public CTShape3D getXmlObject() {
        return this.shape;
    }
}

