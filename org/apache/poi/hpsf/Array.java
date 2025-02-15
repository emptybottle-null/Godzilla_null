/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.hpsf.IllegalPropertySetDataException;
import org.apache.poi.hpsf.TypedPropertyValue;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Array {
    private final ArrayHeader _header = new ArrayHeader();
    private TypedPropertyValue[] _values;

    public void read(LittleEndianByteArrayInputStream lei) {
        this._header.read(lei);
        long numberOfScalarsLong = this._header.getNumberOfScalarValues();
        if (numberOfScalarsLong > Integer.MAX_VALUE) {
            String msg = "Sorry, but POI can't store array of properties with size of " + numberOfScalarsLong + " in memory";
            throw new UnsupportedOperationException(msg);
        }
        int numberOfScalars = (int)numberOfScalarsLong;
        this._values = new TypedPropertyValue[numberOfScalars];
        int paddedType = this._header._type == 12 ? 0 : this._header._type;
        for (int i = 0; i < numberOfScalars; ++i) {
            TypedPropertyValue typedPropertyValue = new TypedPropertyValue(paddedType, null);
            typedPropertyValue.read(lei);
            this._values[i] = typedPropertyValue;
            if (paddedType == 0) continue;
            TypedPropertyValue.skipPadding(lei);
        }
    }

    public TypedPropertyValue[] getValues() {
        return this._values;
    }

    static class ArrayHeader {
        private ArrayDimension[] _dimensions;
        private int _type;

        ArrayHeader() {
        }

        void read(LittleEndianByteArrayInputStream lei) {
            this._type = lei.readInt();
            long numDimensionsUnsigned = lei.readUInt();
            if (1L > numDimensionsUnsigned || numDimensionsUnsigned > 31L) {
                String msg = "Array dimension number " + numDimensionsUnsigned + " is not in [1; 31] range";
                throw new IllegalPropertySetDataException(msg);
            }
            int numDimensions = (int)numDimensionsUnsigned;
            this._dimensions = new ArrayDimension[numDimensions];
            for (int i = 0; i < numDimensions; ++i) {
                ArrayDimension ad = new ArrayDimension();
                ad.read(lei);
                this._dimensions[i] = ad;
            }
        }

        long getNumberOfScalarValues() {
            long result = 1L;
            for (ArrayDimension dimension : this._dimensions) {
                result *= dimension._size;
            }
            return result;
        }

        int getType() {
            return this._type;
        }
    }

    static class ArrayDimension {
        private long _size;
        private int _indexOffset;

        ArrayDimension() {
        }

        void read(LittleEndianByteArrayInputStream lei) {
            this._size = lei.readUInt();
            this._indexOffset = lei.readInt();
        }
    }
}

