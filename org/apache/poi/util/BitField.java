/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import org.apache.poi.util.Internal;

@Internal(since="POI 3.15 beta 3")
public class BitField {
    private final int _mask;
    private final int _shift_count;

    public BitField(int mask) {
        this._mask = mask;
        int count = 0;
        int bit_pattern = mask;
        if (bit_pattern != 0) {
            while ((bit_pattern & 1) == 0) {
                ++count;
                bit_pattern >>= 1;
            }
        }
        this._shift_count = count;
    }

    public int getValue(int holder) {
        return this.getRawValue(holder) >>> this._shift_count;
    }

    public short getShortValue(short holder) {
        return (short)this.getValue(holder);
    }

    public int getRawValue(int holder) {
        return holder & this._mask;
    }

    public short getShortRawValue(short holder) {
        return (short)this.getRawValue(holder);
    }

    public boolean isSet(int holder) {
        return (holder & this._mask) != 0;
    }

    public boolean isAllSet(int holder) {
        return (holder & this._mask) == this._mask;
    }

    public int setValue(int holder, int value) {
        return holder & ~this._mask | value << this._shift_count & this._mask;
    }

    public short setShortValue(short holder, short value) {
        return (short)this.setValue(holder, value);
    }

    public int clear(int holder) {
        return holder & ~this._mask;
    }

    public short clearShort(short holder) {
        return (short)this.clear(holder);
    }

    public byte clearByte(byte holder) {
        return (byte)this.clear(holder);
    }

    public int set(int holder) {
        return holder | this._mask;
    }

    public short setShort(short holder) {
        return (short)this.set(holder);
    }

    public byte setByte(byte holder) {
        return (byte)this.set(holder);
    }

    public int setBoolean(int holder, boolean flag) {
        return flag ? this.set(holder) : this.clear(holder);
    }

    public short setShortBoolean(short holder, boolean flag) {
        return flag ? this.setShort(holder) : this.clearShort(holder);
    }

    public byte setByteBoolean(byte holder, boolean flag) {
        return flag ? this.setByte(holder) : this.clearByte(holder);
    }
}

