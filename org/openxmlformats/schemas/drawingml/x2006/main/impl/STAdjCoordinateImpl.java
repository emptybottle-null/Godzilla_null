/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlUnionImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STGeomGuideName;

public class STAdjCoordinateImpl
extends XmlUnionImpl
implements STAdjCoordinate,
STCoordinate,
STGeomGuideName {
    private static final long serialVersionUID = 1L;

    public STAdjCoordinateImpl(SchemaType schemaType) {
        super(schemaType, false);
    }

    protected STAdjCoordinateImpl(SchemaType schemaType, boolean bl) {
        super(schemaType, bl);
    }
}

