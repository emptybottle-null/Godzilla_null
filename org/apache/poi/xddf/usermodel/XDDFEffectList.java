/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;

public class XDDFEffectList {
    private CTEffectList list;

    @Internal
    public XDDFEffectList(CTEffectList list) {
        this.list = list;
    }

    @Internal
    public CTEffectList getXmlObject() {
        return this.list;
    }
}

