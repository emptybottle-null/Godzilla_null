/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorAuto;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorRGB;

public class STHexColorImpl
extends XmlUnionImpl
implements STHexColor,
STHexColorAuto,
STHexColorRGB {
    private static final long serialVersionUID = 1L;

    public STHexColorImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STHexColorImpl(SchemaType schemaType, boolean bl) {
        super(schemaType, bl);
    }
}

