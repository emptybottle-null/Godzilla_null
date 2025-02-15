/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;

public class XDDFAdjustHandlePolar {
    private CTPolarAdjustHandle handle;

    @Internal
    public XDDFAdjustHandlePolar(CTPolarAdjustHandle handle) {
        this.handle = handle;
    }

    @Internal
    public CTPolarAdjustHandle getXmlObject() {
        return this.handle;
    }
}

