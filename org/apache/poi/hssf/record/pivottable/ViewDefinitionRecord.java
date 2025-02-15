/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.pivottable;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class ViewDefinitionRecord
extends StandardRecord {
    public static final short sid = 176;
    private int rwFirst;
    private int rwLast;
    private int colFirst;
    private int colLast;
    private int rwFirstHead;
    private int rwFirstData;
    private int colFirstData;
    private int iCache;
    private int reserved;
    private int sxaxis4Data;
    private int ipos4Data;
    private int cDim;
    private int cDimRw;
    private int cDimCol;
    private int cDimPg;
    private int cDimData;
    private int cRw;
    private int cCol;
    private int grbit;
    private int itblAutoFmt;
    private String dataField;
    private String name;

    public ViewDefinitionRecord(ViewDefinitionRecord other) {
        super(other);
        this.rwFirst = other.rwFirst;
        this.rwLast = other.rwLast;
        this.colFirst = other.colFirst;
        this.colLast = other.colLast;
        this.rwFirstHead = other.rwFirstHead;
        this.rwFirstData = other.rwFirstData;
        this.colFirstData = other.colFirstData;
        this.iCache = other.iCache;
        this.reserved = other.reserved;
        this.sxaxis4Data = other.sxaxis4Data;
        this.ipos4Data = other.ipos4Data;
        this.cDim = other.cDim;
        this.cDimRw = other.cDimRw;
        this.cDimCol = other.cDimCol;
        this.cDimPg = other.cDimPg;
        this.cDimData = other.cDimData;
        this.cRw = other.cRw;
        this.cCol = other.cCol;
        this.grbit = other.grbit;
        this.itblAutoFmt = other.itblAutoFmt;
        this.name = other.name;
        this.dataField = other.dataField;
    }

    public ViewDefinitionRecord(RecordInputStream in) {
        this.rwFirst = in.readUShort();
        this.rwLast = in.readUShort();
        this.colFirst = in.readUShort();
        this.colLast = in.readUShort();
        this.rwFirstHead = in.readUShort();
        this.rwFirstData = in.readUShort();
        this.colFirstData = in.readUShort();
        this.iCache = in.readUShort();
        this.reserved = in.readUShort();
        this.sxaxis4Data = in.readUShort();
        this.ipos4Data = in.readUShort();
        this.cDim = in.readUShort();
        this.cDimRw = in.readUShort();
        this.cDimCol = in.readUShort();
        this.cDimPg = in.readUShort();
        this.cDimData = in.readUShort();
        this.cRw = in.readUShort();
        this.cCol = in.readUShort();
        this.grbit = in.readUShort();
        this.itblAutoFmt = in.readUShort();
        int cchName = in.readUShort();
        int cchData = in.readUShort();
        this.name = StringUtil.readUnicodeString(in, cchName);
        this.dataField = StringUtil.readUnicodeString(in, cchData);
    }

    @Override
    protected void serialize(LittleEndianOutput out) {
        out.writeShort(this.rwFirst);
        out.writeShort(this.rwLast);
        out.writeShort(this.colFirst);
        out.writeShort(this.colLast);
        out.writeShort(this.rwFirstHead);
        out.writeShort(this.rwFirstData);
        out.writeShort(this.colFirstData);
        out.writeShort(this.iCache);
        out.writeShort(this.reserved);
        out.writeShort(this.sxaxis4Data);
        out.writeShort(this.ipos4Data);
        out.writeShort(this.cDim);
        out.writeShort(this.cDimRw);
        out.writeShort(this.cDimCol);
        out.writeShort(this.cDimPg);
        out.writeShort(this.cDimData);
        out.writeShort(this.cRw);
        out.writeShort(this.cCol);
        out.writeShort(this.grbit);
        out.writeShort(this.itblAutoFmt);
        out.writeShort(this.name.length());
        out.writeShort(this.dataField.length());
        StringUtil.writeUnicodeStringFlagAndData(out, this.name);
        StringUtil.writeUnicodeStringFlagAndData(out, this.dataField);
    }

    @Override
    protected int getDataSize() {
        return 40 + StringUtil.getEncodedSize(this.name) + StringUtil.getEncodedSize(this.dataField);
    }

    @Override
    public short getSid() {
        return 176;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SXVIEW]\n");
        buffer.append("    .rwFirst      =").append(HexDump.shortToHex(this.rwFirst)).append('\n');
        buffer.append("    .rwLast       =").append(HexDump.shortToHex(this.rwLast)).append('\n');
        buffer.append("    .colFirst     =").append(HexDump.shortToHex(this.colFirst)).append('\n');
        buffer.append("    .colLast      =").append(HexDump.shortToHex(this.colLast)).append('\n');
        buffer.append("    .rwFirstHead  =").append(HexDump.shortToHex(this.rwFirstHead)).append('\n');
        buffer.append("    .rwFirstData  =").append(HexDump.shortToHex(this.rwFirstData)).append('\n');
        buffer.append("    .colFirstData =").append(HexDump.shortToHex(this.colFirstData)).append('\n');
        buffer.append("    .iCache       =").append(HexDump.shortToHex(this.iCache)).append('\n');
        buffer.append("    .reserved     =").append(HexDump.shortToHex(this.reserved)).append('\n');
        buffer.append("    .sxaxis4Data  =").append(HexDump.shortToHex(this.sxaxis4Data)).append('\n');
        buffer.append("    .ipos4Data    =").append(HexDump.shortToHex(this.ipos4Data)).append('\n');
        buffer.append("    .cDim         =").append(HexDump.shortToHex(this.cDim)).append('\n');
        buffer.append("    .cDimRw       =").append(HexDump.shortToHex(this.cDimRw)).append('\n');
        buffer.append("    .cDimCol      =").append(HexDump.shortToHex(this.cDimCol)).append('\n');
        buffer.append("    .cDimPg       =").append(HexDump.shortToHex(this.cDimPg)).append('\n');
        buffer.append("    .cDimData     =").append(HexDump.shortToHex(this.cDimData)).append('\n');
        buffer.append("    .cRw          =").append(HexDump.shortToHex(this.cRw)).append('\n');
        buffer.append("    .cCol         =").append(HexDump.shortToHex(this.cCol)).append('\n');
        buffer.append("    .grbit        =").append(HexDump.shortToHex(this.grbit)).append('\n');
        buffer.append("    .itblAutoFmt  =").append(HexDump.shortToHex(this.itblAutoFmt)).append('\n');
        buffer.append("    .name         =").append(this.name).append('\n');
        buffer.append("    .dataField    =").append(this.dataField).append('\n');
        buffer.append("[/SXVIEW]\n");
        return buffer.toString();
    }

    @Override
    public ViewDefinitionRecord copy() {
        return new ViewDefinitionRecord(this);
    }
}

