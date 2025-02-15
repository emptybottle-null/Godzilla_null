/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.pivottable;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public final class ExtendedPivotTableViewFieldsRecord
extends StandardRecord {
    public static final short sid = 256;
    private static final int STRING_NOT_PRESENT_LEN = 65535;
    private int _grbit1;
    private int _grbit2;
    private int _citmShow;
    private int _isxdiSort;
    private int _isxdiShow;
    private int _reserved1;
    private int _reserved2;
    private String _subtotalName;

    public ExtendedPivotTableViewFieldsRecord(ExtendedPivotTableViewFieldsRecord other) {
        super(other);
        this._grbit1 = other._grbit1;
        this._grbit2 = other._grbit2;
        this._citmShow = other._citmShow;
        this._isxdiSort = other._isxdiSort;
        this._isxdiShow = other._isxdiShow;
        this._reserved1 = other._reserved1;
        this._reserved2 = other._reserved2;
        this._subtotalName = other._subtotalName;
    }

    public ExtendedPivotTableViewFieldsRecord(RecordInputStream in) {
        this._grbit1 = in.readInt();
        this._grbit2 = in.readUByte();
        this._citmShow = in.readUByte();
        this._isxdiSort = in.readUShort();
        this._isxdiShow = in.readUShort();
        switch (in.remaining()) {
            case 0: {
                this._reserved1 = 0;
                this._reserved2 = 0;
                this._subtotalName = null;
                return;
            }
            case 10: {
                break;
            }
            default: {
                throw new RecordFormatException("Unexpected remaining size (" + in.remaining() + ")");
            }
        }
        int cchSubName = in.readUShort();
        this._reserved1 = in.readInt();
        this._reserved2 = in.readInt();
        if (cchSubName != 65535) {
            this._subtotalName = in.readUnicodeLEString(cchSubName);
        }
    }

    @Override
    protected void serialize(LittleEndianOutput out) {
        out.writeInt(this._grbit1);
        out.writeByte(this._grbit2);
        out.writeByte(this._citmShow);
        out.writeShort(this._isxdiSort);
        out.writeShort(this._isxdiShow);
        if (this._subtotalName == null) {
            out.writeShort(65535);
        } else {
            out.writeShort(this._subtotalName.length());
        }
        out.writeInt(this._reserved1);
        out.writeInt(this._reserved2);
        if (this._subtotalName != null) {
            StringUtil.putUnicodeLE(this._subtotalName, out);
        }
    }

    @Override
    protected int getDataSize() {
        return 20 + (this._subtotalName == null ? 0 : 2 * this._subtotalName.length());
    }

    @Override
    public short getSid() {
        return 256;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[SXVDEX]\n");
        buffer.append("    .grbit1 =").append(HexDump.intToHex(this._grbit1)).append("\n");
        buffer.append("    .grbit2 =").append(HexDump.byteToHex(this._grbit2)).append("\n");
        buffer.append("    .citmShow =").append(HexDump.byteToHex(this._citmShow)).append("\n");
        buffer.append("    .isxdiSort =").append(HexDump.shortToHex(this._isxdiSort)).append("\n");
        buffer.append("    .isxdiShow =").append(HexDump.shortToHex(this._isxdiShow)).append("\n");
        buffer.append("    .subtotalName =").append(this._subtotalName).append("\n");
        buffer.append("[/SXVDEX]\n");
        return buffer.toString();
    }

    @Override
    public ExtendedPivotTableViewFieldsRecord copy() {
        return new ExtendedPivotTableViewFieldsRecord(this);
    }
}

