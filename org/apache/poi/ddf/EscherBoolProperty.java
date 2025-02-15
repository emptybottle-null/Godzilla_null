/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherSimpleProperty;

public class EscherBoolProperty
extends EscherSimpleProperty {
    public EscherBoolProperty(short propertyNumber, int value) {
        super(propertyNumber, value);
    }

    public EscherBoolProperty(EscherPropertyTypes propertyType, int value) {
        super(propertyType.propNumber, value);
    }

    public boolean isTrue() {
        return this.getPropertyValue() != 0;
    }
}

