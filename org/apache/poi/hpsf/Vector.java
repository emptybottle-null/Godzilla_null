/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.util.ArrayList;
import org.apache.poi.hpsf.TypedPropertyValue;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Vector {
    private final short _type;
    private TypedPropertyValue[] _values;

    public Vector(short type) {
        this._type = type;
    }

    public void read(LittleEndianByteArrayInputStream lei) {
        long longLength = lei.readUInt();
        if (longLength > Integer.MAX_VALUE) {
            throw new UnsupportedOperationException("Vector is too long -- " + longLength);
        }
        int length = (int)longLength;
        ArrayList<TypedPropertyValue> values = new ArrayList<TypedPropertyValue>();
        short paddedType = this._type == 12 ? (short)0 : this._type;
        for (int i = 0; i < length; ++i) {
            TypedPropertyValue value = new TypedPropertyValue(paddedType, null);
            if (paddedType == 0) {
                value.read(lei);
            } else {
                value.readValue(lei);
            }
            values.add(value);
        }
        this._values = values.toArray(new TypedPropertyValue[0]);
    }

    public TypedPropertyValue[] getValues() {
        return this._values;
    }
}

