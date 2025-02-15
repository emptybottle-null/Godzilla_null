/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.util.FixedField;
import org.apache.poi.util.LittleEndian;

public class IntegerField
implements FixedField {
    private int _value;
    private final int _offset;

    public IntegerField(int offset) throws ArrayIndexOutOfBoundsException {
        if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException("negative offset");
        }
        this._offset = offset;
    }

    public IntegerField(int offset, int value) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.set(value);
    }

    public IntegerField(int offset, byte[] data) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.readFromBytes(data);
    }

    public IntegerField(int offset, int value, byte[] data) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.set(value, data);
    }

    public int get() {
        return this._value;
    }

    public void set(int value) {
        this._value = value;
    }

    public void set(int value, byte[] data) throws ArrayIndexOutOfBoundsException {
        this._value = value;
        this.writeToBytes(data);
    }

    @Override
    public void readFromBytes(byte[] data) throws ArrayIndexOutOfBoundsException {
        this._value = LittleEndian.getInt(data, this._offset);
    }

    @Override
    public void readFromStream(InputStream stream) throws IOException {
        this._value = LittleEndian.readInt(stream);
    }

    @Override
    public void writeToBytes(byte[] data) throws ArrayIndexOutOfBoundsException {
        LittleEndian.putInt(data, this._offset, this._value);
    }

    @Override
    public String toString() {
        return String.valueOf(this._value);
    }
}

