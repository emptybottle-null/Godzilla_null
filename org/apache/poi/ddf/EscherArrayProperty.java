/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ddf;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.poi.ddf.EscherComplexProperty;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public final class EscherArrayProperty
extends EscherComplexProperty
implements Iterable<byte[]> {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final int FIXED_SIZE = 6;
    private boolean sizeIncludesHeaderSize = true;
    private final boolean emptyComplexPart;

    @Deprecated
    @Removal(version="5.0.0")
    @Internal
    public EscherArrayProperty(short id, byte[] complexData) {
        this(id, EscherArrayProperty.safeSize(complexData == null ? 0 : complexData.length));
        this.setComplexData(complexData);
    }

    @Internal
    public EscherArrayProperty(short id, int complexSize) {
        super(id, complexSize);
        this.emptyComplexPart = complexSize == 0;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public EscherArrayProperty(short propertyNumber, boolean isBlipId, byte[] complexData) {
        this((short)(propertyNumber | (isBlipId ? 16384 : 0)), EscherArrayProperty.safeSize(complexData == null ? 0 : complexData.length));
        this.setComplexData(complexData);
    }

    public EscherArrayProperty(EscherPropertyTypes type, boolean isBlipId, int complexSize) {
        this((short)(type.propNumber | (isBlipId ? 16384 : 0)), EscherArrayProperty.safeSize(complexSize));
    }

    private static int safeSize(int complexSize) {
        return complexSize == 0 ? 6 : complexSize;
    }

    public int getNumberOfElementsInArray() {
        return this.emptyComplexPart ? 0 : LittleEndian.getUShort(this.getComplexData(), 0);
    }

    public void setNumberOfElementsInArray(int numberOfElements) {
        if (this.emptyComplexPart) {
            return;
        }
        this.rewriteArray(numberOfElements, false);
        LittleEndian.putShort(this.getComplexData(), 0, (short)numberOfElements);
    }

    private void rewriteArray(int numberOfElements, boolean copyToNewLen) {
        int expectedArraySize = numberOfElements * EscherArrayProperty.getActualSizeOfElements(this.getSizeOfElements()) + 6;
        this.resizeComplexData(expectedArraySize, copyToNewLen ? expectedArraySize : this.getComplexData().length);
    }

    public int getNumberOfElementsInMemory() {
        return this.emptyComplexPart ? 0 : LittleEndian.getUShort(this.getComplexData(), 2);
    }

    public void setNumberOfElementsInMemory(int numberOfElements) {
        if (this.emptyComplexPart) {
            return;
        }
        this.rewriteArray(numberOfElements, true);
        LittleEndian.putShort(this.getComplexData(), 2, (short)numberOfElements);
    }

    public short getSizeOfElements() {
        return this.emptyComplexPart ? (short)0 : LittleEndian.getShort(this.getComplexData(), 4);
    }

    public void setSizeOfElements(int sizeOfElements) {
        if (this.emptyComplexPart) {
            return;
        }
        LittleEndian.putShort(this.getComplexData(), 4, (short)sizeOfElements);
        int expectedArraySize = this.getNumberOfElementsInArray() * EscherArrayProperty.getActualSizeOfElements(this.getSizeOfElements()) + 6;
        this.resizeComplexData(expectedArraySize, 6);
    }

    public byte[] getElement(int index) {
        int actualSize = EscherArrayProperty.getActualSizeOfElements(this.getSizeOfElements());
        byte[] result = IOUtils.safelyAllocate(actualSize, 100000);
        System.arraycopy(this.getComplexData(), 6 + index * actualSize, result, 0, result.length);
        return result;
    }

    public void setElement(int index, byte[] element) {
        if (this.emptyComplexPart) {
            return;
        }
        int actualSize = EscherArrayProperty.getActualSizeOfElements(this.getSizeOfElements());
        System.arraycopy(element, 0, this.getComplexData(), 6 + index * actualSize, actualSize);
    }

    public int setArrayData(byte[] data, int offset) {
        if (this.emptyComplexPart) {
            this.resizeComplexData(0);
        } else {
            short numElements = LittleEndian.getShort(data, offset);
            short sizeOfElements = LittleEndian.getShort(data, offset + 4);
            int cdLen = this.getComplexData().length;
            int arraySize = EscherArrayProperty.getActualSizeOfElements(sizeOfElements) * numElements;
            if (arraySize == cdLen) {
                this.resizeComplexData(arraySize + 6, 0);
                this.sizeIncludesHeaderSize = false;
            }
            this.setComplexData(data, offset);
        }
        return this.getComplexData().length;
    }

    @Override
    public int serializeSimplePart(byte[] data, int pos) {
        LittleEndian.putShort(data, pos, this.getId());
        int recordSize = this.getComplexData().length;
        if (!this.sizeIncludesHeaderSize) {
            recordSize -= 6;
        }
        LittleEndian.putInt(data, pos + 2, recordSize);
        return 6;
    }

    private static int getActualSizeOfElements(short sizeOfElements) {
        if (sizeOfElements < 0) {
            return (short)(-sizeOfElements >> 2);
        }
        return sizeOfElements;
    }

    @Override
    public Iterator<byte[]> iterator() {
        return new Iterator<byte[]>(){
            int idx;

            @Override
            public boolean hasNext() {
                return this.idx < EscherArrayProperty.this.getNumberOfElementsInArray();
            }

            @Override
            public byte[] next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return EscherArrayProperty.this.getElement(this.idx++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("not yet implemented");
            }
        };
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", () -> super.getGenericProperties(), "numElements", this::getNumberOfElementsInArray, "numElementsInMemory", this::getNumberOfElementsInMemory, "sizeOfElements", this::getSizeOfElements, "elements", () -> StreamSupport.stream(this.spliterator(), false).collect(Collectors.toList()));
    }
}

