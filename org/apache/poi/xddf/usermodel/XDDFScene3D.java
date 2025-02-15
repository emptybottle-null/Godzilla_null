/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;

public class XDDFScene3D {
    private CTScene3D scene;

    protected XDDFScene3D(CTScene3D scene) {
        this.scene = scene;
    }

    @Internal
    public CTScene3D getXmlObject() {
        return this.scene;
    }
}

