/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianInput;

@Internal
public class Blob {
    private static final int MAX_RECORD_LENGTH = 10000000;
    private byte[] _value;

    public void read(LittleEndianInput lei) {
        int size = lei.readInt();
        this._value = IOUtils.safelyAllocate(size, 10000000);
        if (size > 0) {
            lei.readFully(this._value);
        }
    }
}

