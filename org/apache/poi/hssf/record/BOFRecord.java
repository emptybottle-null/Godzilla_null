/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class BOFRecord
extends StandardRecord {
    public static final short sid = 2057;
    public static final short biff2_sid = 9;
    public static final short biff3_sid = 521;
    public static final short biff4_sid = 1033;
    public static final short biff5_sid = 2057;
    public static final int VERSION = 1536;
    public static final int BUILD = 4307;
    public static final int BUILD_YEAR = 1996;
    public static final int HISTORY_MASK = 65;
    public static final int TYPE_WORKBOOK = 5;
    public static final int TYPE_VB_MODULE = 6;
    public static final int TYPE_WORKSHEET = 16;
    public static final int TYPE_CHART = 32;
    public static final int TYPE_EXCEL_4_MACRO = 64;
    public static final int TYPE_WORKSPACE_FILE = 256;
    private int field_1_version;
    private int field_2_type;
    private int field_3_build;
    private int field_4_year;
    private int field_5_history;
    private int field_6_rversion;

    public BOFRecord() {
    }

    public BOFRecord(BOFRecord other) {
        super(other);
        this.field_1_version = other.field_1_version;
        this.field_2_type = other.field_2_type;
        this.field_3_build = other.field_3_build;
        this.field_4_year = other.field_4_year;
        this.field_5_history = other.field_5_history;
        this.field_6_rversion = other.field_6_rversion;
    }

    private BOFRecord(int type) {
        this.field_1_version = 1536;
        this.field_2_type = type;
        this.field_3_build = 4307;
        this.field_4_year = 1996;
        this.field_5_history = 1;
        this.field_6_rversion = 1536;
    }

    public static BOFRecord createSheetBOF() {
        return new BOFRecord(16);
    }

    public BOFRecord(RecordInputStream in) {
        this.field_1_version = in.readShort();
        this.field_2_type = in.readShort();
        if (in.remaining() >= 2) {
            this.field_3_build = in.readShort();
        }
        if (in.remaining() >= 2) {
            this.field_4_year = in.readShort();
        }
        if (in.remaining() >= 4) {
            this.field_5_history = in.readInt();
        }
        if (in.remaining() >= 4) {
            this.field_6_rversion = in.readInt();
        }
    }

    public void setVersion(int version) {
        this.field_1_version = version;
    }

    public void setType(int type) {
        this.field_2_type = type;
    }

    public void setBuild(int build) {
        this.field_3_build = build;
    }

    public void setBuildYear(int year) {
        this.field_4_year = year;
    }

    public void setHistoryBitMask(int bitmask) {
        this.field_5_history = bitmask;
    }

    public void setRequiredVersion(int version) {
        this.field_6_rversion = version;
    }

    public int getVersion() {
        return this.field_1_version;
    }

    public int getType() {
        return this.field_2_type;
    }

    public int getBuild() {
        return this.field_3_build;
    }

    public int getBuildYear() {
        return this.field_4_year;
    }

    public int getHistoryBitMask() {
        return this.field_5_history;
    }

    public int getRequiredVersion() {
        return this.field_6_rversion;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[BOF RECORD]\n");
        buffer.append("    .version  = ").append(HexDump.shortToHex(this.getVersion())).append("\n");
        buffer.append("    .type     = ").append(HexDump.shortToHex(this.getType()));
        buffer.append(" (").append(this.getTypeName()).append(")").append("\n");
        buffer.append("    .build    = ").append(HexDump.shortToHex(this.getBuild())).append("\n");
        buffer.append("    .buildyear= ").append(this.getBuildYear()).append("\n");
        buffer.append("    .history  = ").append(HexDump.intToHex(this.getHistoryBitMask())).append("\n");
        buffer.append("    .reqver   = ").append(HexDump.intToHex(this.getRequiredVersion())).append("\n");
        buffer.append("[/BOF RECORD]\n");
        return buffer.toString();
    }

    private String getTypeName() {
        switch (this.field_2_type) {
            case 32: {
                return "chart";
            }
            case 64: {
                return "excel 4 macro";
            }
            case 6: {
                return "vb module";
            }
            case 5: {
                return "workbook";
            }
            case 16: {
                return "worksheet";
            }
            case 256: {
                return "workspace file";
            }
        }
        return "#error unknown type#";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getVersion());
        out.writeShort(this.getType());
        out.writeShort(this.getBuild());
        out.writeShort(this.getBuildYear());
        out.writeInt(this.getHistoryBitMask());
        out.writeInt(this.getRequiredVersion());
    }

    @Override
    protected int getDataSize() {
        return 16;
    }

    @Override
    public short getSid() {
        return 2057;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public BOFRecord clone() {
        return this.copy();
    }

    @Override
    public BOFRecord copy() {
        return new BOFRecord(this);
    }
}

