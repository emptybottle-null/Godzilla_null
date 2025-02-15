/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.OldCellRecord;
import org.apache.poi.hssf.record.OldStringRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RecordFormatException;

public final class OldLabelRecord
extends OldCellRecord {
    private static final POILogger logger = POILogFactory.getLogger(OldLabelRecord.class);
    private static final int MAX_RECORD_LENGTH = 100000;
    public static final short biff2_sid = 4;
    public static final short biff345_sid = 516;
    private short field_4_string_len;
    private final byte[] field_5_bytes;
    private CodepageRecord codepage;

    public OldLabelRecord(RecordInputStream in) {
        super(in, in.getSid() == 4);
        this.field_4_string_len = this.isBiff2() ? (short)in.readUByte() : in.readShort();
        this.field_5_bytes = IOUtils.safelyAllocate(this.field_4_string_len, 100000);
        in.read(this.field_5_bytes, 0, this.field_4_string_len);
        if (in.remaining() > 0) {
            logger.log(3, "LabelRecord data remains: " + in.remaining() + " : " + HexDump.toHex(in.readRemainder()));
        }
    }

    public void setCodePage(CodepageRecord codepage) {
        this.codepage = codepage;
    }

    public short getStringLength() {
        return this.field_4_string_len;
    }

    public String getValue() {
        return OldStringRecord.getString(this.field_5_bytes, this.codepage);
    }

    public int serialize(int offset, byte[] data) {
        throw new RecordFormatException("Old Label Records are supported READ ONLY");
    }

    public int getRecordSize() {
        throw new RecordFormatException("Old Label Records are supported READ ONLY");
    }

    @Override
    protected void appendValueText(StringBuilder sb) {
        sb.append("    .string_len= ").append(HexDump.shortToHex(this.field_4_string_len)).append("\n");
        sb.append("    .value       = ").append(this.getValue()).append("\n");
    }

    @Override
    protected String getRecordName() {
        return "OLD LABEL";
    }
}

