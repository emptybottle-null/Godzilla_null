/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;

public class EscherSimpleProperty
extends EscherProperty {
    private int propertyValue;

    public EscherSimpleProperty(short id, int propertyValue) {
        super(id);
        this.propertyValue = propertyValue;
    }

    public EscherSimpleProperty(EscherPropertyTypes type, int propertyValue) {
        this(type, false, false, propertyValue);
    }

    public EscherSimpleProperty(short propertyNumber, boolean isComplex, boolean isBlipId, int propertyValue) {
        super(propertyNumber, isComplex, isBlipId);
        this.propertyValue = propertyValue;
    }

    public EscherSimpleProperty(EscherPropertyTypes type, boolean isComplex, boolean isBlipId, int propertyValue) {
        super(type, isComplex, isBlipId);
        this.propertyValue = propertyValue;
    }

    @Override
    public int serializeSimplePart(byte[] data, int offset) {
        LittleEndian.putShort(data, offset, this.getId());
        LittleEndian.putInt(data, offset + 2, this.propertyValue);
        return 6;
    }

    @Override
    public int serializeComplexPart(byte[] data, int pos) {
        return 0;
    }

    public int getPropertyValue() {
        return this.propertyValue;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EscherSimpleProperty)) {
            return false;
        }
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty)o;
        if (this.propertyValue != escherSimpleProperty.propertyValue) {
            return false;
        }
        return this.getId() == escherSimpleProperty.getId();
    }

    public int hashCode() {
        return Objects.hash(this.propertyValue, this.getId());
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "value", this::getPropertyValue);
    }
}

