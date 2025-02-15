/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.poi.hpsf.TypedPropertyValue;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public class CodePageString {
    private static final int MAX_RECORD_LENGTH = 100000;
    private static final POILogger LOG = POILogFactory.getLogger(CodePageString.class);
    private byte[] _value;

    public void read(LittleEndianByteArrayInputStream lei) {
        int offset = lei.getReadIndex();
        int size = lei.readInt();
        this._value = IOUtils.safelyAllocate(size, 100000);
        if (size == 0) {
            return;
        }
        lei.readFully(this._value);
        if (this._value[size - 1] != 0) {
            String msg = "CodePageString started at offset #" + offset + " is not NULL-terminated";
            LOG.log(5, msg);
        }
        TypedPropertyValue.skipPadding(lei);
    }

    public String getJavaValue(int codepage) throws UnsupportedEncodingException {
        int cp = codepage == -1 ? 1252 : codepage;
        String result = CodePageUtil.getStringFromCodePage(this._value, cp);
        int terminator = result.indexOf(0);
        if (terminator == -1) {
            String msg = "String terminator (\\0) for CodePageString property value not found. Continue without trimming and hope for the best.";
            LOG.log(5, msg);
            return result;
        }
        if (terminator != result.length() - 1) {
            String msg = "String terminator (\\0) for CodePageString property value occured before the end of string. Trimming and hope for the best.";
            LOG.log(5, msg);
        }
        return result.substring(0, terminator);
    }

    public int getSize() {
        return 4 + this._value.length;
    }

    public void setJavaValue(String string, int codepage) throws UnsupportedEncodingException {
        int cp = codepage == -1 ? 1252 : codepage;
        this._value = CodePageUtil.getBytesInCodePage(string + "\u0000", cp);
    }

    public int write(OutputStream out) throws IOException {
        LittleEndian.putUInt(this._value.length, out);
        out.write(this._value);
        return 4 + this._value.length;
    }
}

