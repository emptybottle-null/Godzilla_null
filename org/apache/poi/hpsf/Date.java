/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Date {
    private static final int SIZE = 8;
    private final byte[] _value = new byte[8];

    public void read(LittleEndianByteArrayInputStream lei) {
        lei.readFully(this._value);
    }
}

