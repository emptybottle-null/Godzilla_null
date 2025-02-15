/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.UnsupportedEncodingException;
import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;

public final class OldStringRecord {
    private static final int MAX_RECORD_LENGTH = 100000;
    public static final short biff2_sid = 7;
    public static final short biff345_sid = 519;
    private short sid;
    private short field_1_string_len;
    private byte[] field_2_bytes;
    private CodepageRecord codepage;

    public OldStringRecord(RecordInputStream in) {
        this.sid = in.getSid();
        this.field_1_string_len = in.getSid() == 7 ? (short)in.readUByte() : in.readShort();
        this.field_2_bytes = IOUtils.safelyAllocate(this.field_1_string_len, 100000);
        in.read(this.field_2_bytes, 0, this.field_1_string_len);
    }

    public boolean isBiff2() {
        return this.sid == 7;
    }

    public short getSid() {
        return this.sid;
    }

    public void setCodePage(CodepageRecord codepage) {
        this.codepage = codepage;
    }

    public String getString() {
        return OldStringRecord.getString(this.field_2_bytes, this.codepage);
    }

    protected static String getString(byte[] data, CodepageRecord codepage) {
        int cp = 1252;
        if (codepage != null) {
            cp = codepage.getCodepage() & 0xFFFF;
        }
        try {
            return CodePageUtil.getStringFromCodePage(data, cp);
        } catch (UnsupportedEncodingException uee) {
            throw new IllegalArgumentException("Unsupported codepage requested", uee);
        }
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[OLD STRING]\n");
        buffer.append("    .string            = ").append(this.getString()).append("\n");
        buffer.append("[/OLD STRING]\n");
        return buffer.toString();
    }
}

