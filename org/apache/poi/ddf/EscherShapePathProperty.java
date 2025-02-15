/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherSimpleProperty;

public class EscherShapePathProperty
extends EscherSimpleProperty {
    public static final int LINE_OF_STRAIGHT_SEGMENTS = 0;
    public static final int CLOSED_POLYGON = 1;
    public static final int CURVES = 2;
    public static final int CLOSED_CURVES = 3;
    public static final int COMPLEX = 4;

    public EscherShapePathProperty(short propertyNumber, int shapePath) {
        super(propertyNumber, false, false, shapePath);
    }

    public EscherShapePathProperty(EscherPropertyTypes type, int shapePath) {
        super(type, false, false, shapePath);
    }
}

