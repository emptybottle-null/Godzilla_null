/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.FixedField;
import org.apache.poi.util.LittleEndian;

public class ShortField
implements FixedField {
    private short _value;
    private final int _offset;

    public ShortField(int offset) throws ArrayIndexOutOfBoundsException {
        if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset: " + offset);
        }
        this._offset = offset;
    }

    public ShortField(int offset, short value) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.set(value);
    }

    public ShortField(int offset, byte[] data) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.readFromBytes(data);
    }

    public ShortField(int offset, short value, byte[] data) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.set(value, data);
    }

    public short get() {
        return this._value;
    }

    public void set(short value) {
        this._value = value;
    }

    public void set(short value, byte[] data) throws ArrayIndexOutOfBoundsException {
        this._value = value;
        this.writeToBytes(data);
    }

    @Override
    public void readFromBytes(byte[] data) throws ArrayIndexOutOfBoundsException {
        this._value = LittleEndian.getShort(data, this._offset);
    }

    @Override
    public void readFromStream(InputStream stream) throws IOException {
        this._value = LittleEndian.readShort(stream);
    }

    @Override
    public void writeToBytes(byte[] data) throws ArrayIndexOutOfBoundsException {
        LittleEndian.putShort(data, this._offset, this._value);
    }

    @Override
    public String toString() {
        return String.valueOf(this._value);
    }
}

