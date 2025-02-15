/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class GUID {
    private int _data1;
    private short _data2;
    private short _data3;
    private long _data4;

    public void read(LittleEndianByteArrayInputStream lei) {
        this._data1 = lei.readInt();
        this._data2 = lei.readShort();
        this._data3 = lei.readShort();
        this._data4 = lei.readLong();
    }
}

