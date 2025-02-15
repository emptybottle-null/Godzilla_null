/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;
import org.apache.poi.util.FixedField;

public class ByteField
implements FixedField {
    private static final byte _default_value = 0;
    private byte _value;
    private final int _offset;

    public ByteField(int offset) throws ArrayIndexOutOfBoundsException {
        this(offset, 0);
    }

    public ByteField(int offset, byte value) throws ArrayIndexOutOfBoundsException {
        if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException("offset cannot be negative");
        }
        this._offset = offset;
        this.set(value);
    }

    public ByteField(int offset, byte[] data) throws ArrayIndexOutOfBoundsException {
        this(offset);
        this.readFromBytes(data);
    }

    public ByteField(int offset, byte value, byte[] data) throws ArrayIndexOutOfBoundsException {
        this(offset, value);
        this.writeToBytes(data);
    }

    public byte get() {
        return this._value;
    }

    public void set(byte value) {
        this._value = value;
    }

    public void set(byte value, byte[] data) throws ArrayIndexOutOfBoundsException {
        this.set(value);
        this.writeToBytes(data);
    }

    @Override
    public void readFromBytes(byte[] data) throws ArrayIndexOutOfBoundsException {
        this._value = data[this._offset];
    }

    @Override
    public void readFromStream(InputStream stream) throws IOException {
        int ib = stream.read();
        if (ib < 0) {
            throw new BufferUnderflowException();
        }
        this._value = (byte)ib;
    }

    @Override
    public void writeToBytes(byte[] data) throws ArrayIndexOutOfBoundsException {
        data[this._offset] = this._value;
    }

    @Override
    public String toString() {
        return String.valueOf(this._value);
    }
}

