/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class DrawingSelectionRecord
extends StandardRecord {
    public static final short sid = 237;
    private OfficeArtRecordHeader _header;
    private int _cpsp;
    private int _dgslk;
    private int _spidFocus;
    private int[] _shapeIds;

    public DrawingSelectionRecord(RecordInputStream in) {
        this._header = new OfficeArtRecordHeader(in);
        this._cpsp = in.readInt();
        this._dgslk = in.readInt();
        this._spidFocus = in.readInt();
        int nShapes = in.available() / 4;
        int[] shapeIds = new int[nShapes];
        for (int i = 0; i < nShapes; ++i) {
            shapeIds[i] = in.readInt();
        }
        this._shapeIds = shapeIds;
    }

    @Override
    public short getSid() {
        return 237;
    }

    @Override
    protected int getDataSize() {
        return 20 + this._shapeIds.length * 4;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        this._header.serialize(out);
        out.writeInt(this._cpsp);
        out.writeInt(this._dgslk);
        out.writeInt(this._spidFocus);
        for (int i = 0; i < this._shapeIds.length; ++i) {
            out.writeInt(this._shapeIds[i]);
        }
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DrawingSelectionRecord clone() {
        return this.copy();
    }

    @Override
    public DrawingSelectionRecord copy() {
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[MSODRAWINGSELECTION]\n");
        sb.append("    .rh       =(").append(this._header.debugFormatAsString()).append(")\n");
        sb.append("    .cpsp     =").append(HexDump.intToHex(this._cpsp)).append('\n');
        sb.append("    .dgslk    =").append(HexDump.intToHex(this._dgslk)).append('\n');
        sb.append("    .spidFocus=").append(HexDump.intToHex(this._spidFocus)).append('\n');
        sb.append("    .shapeIds =(");
        for (int i = 0; i < this._shapeIds.length; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(HexDump.intToHex(this._shapeIds[i]));
        }
        sb.append(")\n");
        sb.append("[/MSODRAWINGSELECTION]\n");
        return sb.toString();
    }

    private static final class OfficeArtRecordHeader {
        public static final int ENCODED_SIZE = 8;
        private final int _verAndInstance;
        private final int _type;
        private final int _length;

        public OfficeArtRecordHeader(OfficeArtRecordHeader other) {
            this._verAndInstance = other._verAndInstance;
            this._type = other._type;
            this._length = other._length;
        }

        public OfficeArtRecordHeader(LittleEndianInput in) {
            this._verAndInstance = in.readUShort();
            this._type = in.readUShort();
            this._length = in.readInt();
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._verAndInstance);
            out.writeShort(this._type);
            out.writeInt(this._length);
        }

        public String debugFormatAsString() {
            return "ver+inst=" + HexDump.shortToHex(this._verAndInstance) + " type=" + HexDump.shortToHex(this._type) + " len=" + HexDump.intToHex(this._length);
        }
    }
}

