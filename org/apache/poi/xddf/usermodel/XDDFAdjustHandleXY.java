/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class XDDFAdjustHandleXY {
    private CTXYAdjustHandle handle;

    @Internal
    public XDDFAdjustHandleXY(CTXYAdjustHandle handle) {
        this.handle = handle;
    }

    @Internal
    public CTXYAdjustHandle getXmlObject() {
        return this.handle;
    }
}

