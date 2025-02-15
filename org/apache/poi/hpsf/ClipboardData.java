/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public class ClipboardData {
    private static final int MAX_RECORD_LENGTH = 100000000;
    private static final POILogger LOG = POILogFactory.getLogger(ClipboardData.class);
    private int _format;
    private byte[] _value;

    public void read(LittleEndianByteArrayInputStream lei) {
        int offset = lei.getReadIndex();
        int size = lei.readInt();
        if (size < 4) {
            String msg = "ClipboardData at offset " + offset + " size less than 4 bytes (doesn't even have format field!). Setting to format == 0 and hope for the best";
            LOG.log(5, msg);
            this._format = 0;
            this._value = new byte[0];
            return;
        }
        this._format = lei.readInt();
        this._value = IOUtils.safelyAllocate(size - 4, 100000000);
        lei.readFully(this._value);
    }

    public byte[] getValue() {
        return this._value;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public byte[] toByteArray() {
        byte[] result = new byte[8 + this._value.length];
        LittleEndianByteArrayOutputStream bos = new LittleEndianByteArrayOutputStream(result, 0);
        try {
            bos.writeInt(4 + this._value.length);
            bos.writeInt(this._format);
            bos.write(this._value);
            byte[] byArray = result;
            return byArray;
        } finally {
            IOUtils.closeQuietly(bos);
        }
    }

    public void setValue(byte[] value) {
        this._value = (byte[])value.clone();
    }
}

