/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import org.apache.poi.ddf.EscherArrayProperty;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherComplexProperty;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherPropertyTypesHolder;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherShapePathProperty;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.util.LittleEndian;

public final class EscherPropertyFactory {
    public List<EscherProperty> createProperties(byte[] data, int offset, short numProperties) {
        ArrayList<EscherProperty> results = new ArrayList<EscherProperty>();
        int pos = offset;
        for (int i = 0; i < numProperties; ++i) {
            BiFunction<Short, Integer, EscherProperty> con;
            short propId = LittleEndian.getShort(data, pos);
            int propData = LittleEndian.getInt(data, pos + 2);
            boolean isComplex = (propId & 0x8000) != 0;
            EscherPropertyTypes propertyType = EscherPropertyTypes.forPropertyID(propId);
            switch (propertyType.holder) {
                case BOOLEAN: {
                    con = EscherBoolProperty::new;
                    break;
                }
                case RGB: {
                    con = EscherRGBProperty::new;
                    break;
                }
                case SHAPE_PATH: {
                    con = EscherShapePathProperty::new;
                    break;
                }
                default: {
                    con = isComplex ? (propertyType.holder == EscherPropertyTypesHolder.ARRAY ? EscherArrayProperty::new : EscherComplexProperty::new) : EscherSimpleProperty::new;
                }
            }
            results.add(con.apply(propId, propData));
            pos += 6;
        }
        for (EscherProperty p : results) {
            if (p instanceof EscherArrayProperty) {
                EscherArrayProperty eap = (EscherArrayProperty)p;
                pos += eap.setArrayData(data, pos);
                continue;
            }
            if (!(p instanceof EscherComplexProperty)) continue;
            int leftover = data.length - pos;
            EscherComplexProperty ecp = (EscherComplexProperty)p;
            int cdLen = ecp.getComplexData().length;
            if (leftover < cdLen) {
                throw new IllegalStateException("Could not read complex escher property, length was " + cdLen + ", but had only " + leftover + " bytes left");
            }
            pos += ecp.setComplexData(data, pos);
        }
        return results;
    }
}

