/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.poi.hpsf.IllegalPropertySetDataException;
import org.apache.poi.hpsf.TypedPropertyValue;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.StringUtil;

@Internal
public class UnicodeString {
    private static final POILogger LOG = POILogFactory.getLogger(UnicodeString.class);
    private static final int MAX_RECORD_LENGTH = 100000;
    private byte[] _value;

    public void read(LittleEndianByteArrayInputStream lei) {
        int length = lei.readInt();
        int unicodeBytes = length * 2;
        this._value = IOUtils.safelyAllocate(unicodeBytes, 100000);
        if (length == 0) {
            return;
        }
        int offset = lei.getReadIndex();
        lei.readFully(this._value);
        if (this._value[unicodeBytes - 2] != 0 || this._value[unicodeBytes - 1] != 0) {
            String msg = "UnicodeString started at offset #" + offset + " is not NULL-terminated";
            throw new IllegalPropertySetDataException(msg);
        }
        TypedPropertyValue.skipPadding(lei);
    }

    public byte[] getValue() {
        return this._value;
    }

    public String toJavaString() {
        if (this._value.length == 0) {
            return null;
        }
        String result = StringUtil.getFromUnicodeLE(this._value, 0, this._value.length >> 1);
        int terminator = result.indexOf(0);
        if (terminator == -1) {
            String msg = "String terminator (\\0) for UnicodeString property value not found. Continue without trimming and hope for the best.";
            LOG.log(5, msg);
            return result;
        }
        if (terminator != result.length() - 1) {
            String msg = "String terminator (\\0) for UnicodeString property value occured before the end of string. Trimming and hope for the best.";
            LOG.log(5, msg);
        }
        return result.substring(0, terminator);
    }

    public void setJavaValue(String string) throws UnsupportedEncodingException {
        this._value = CodePageUtil.getBytesInCodePage(string + "\u0000", 1200);
    }

    public int write(OutputStream out) throws IOException {
        LittleEndian.putUInt(this._value.length / 2, out);
        out.write(this._value);
        return 4 + this._value.length;
    }
}

