/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Decimal {
    private short field_1_wReserved;
    private byte field_2_scale;
    private byte field_3_sign;
    private int field_4_hi32;
    private long field_5_lo64;

    public void read(LittleEndianByteArrayInputStream lei) {
        this.field_1_wReserved = lei.readShort();
        this.field_2_scale = lei.readByte();
        this.field_3_sign = lei.readByte();
        this.field_4_hi32 = lei.readInt();
        this.field_5_lo64 = lei.readLong();
    }
}

