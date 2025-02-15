/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;

public class LbsDataSubRecord
extends SubRecord {
    public static final int sid = 19;
    private int _cbFContinued;
    private int _unknownPreFormulaInt;
    private Ptg _linkPtg;
    private Byte _unknownPostFormulaByte;
    private int _cLines;
    private int _iSel;
    private int _flags;
    private int _idEdit;
    private LbsDropData _dropData;
    private String[] _rgLines;
    private boolean[] _bsels;

    LbsDataSubRecord() {
    }

    public LbsDataSubRecord(LbsDataSubRecord other) {
        super(other);
        this._cbFContinued = other._cbFContinued;
        this._unknownPreFormulaInt = other._unknownPreFormulaInt;
        this._linkPtg = other._linkPtg == null ? null : other._linkPtg.copy();
        this._unknownPostFormulaByte = other._unknownPostFormulaByte;
        this._cLines = other._cLines;
        this._iSel = other._iSel;
        this._flags = other._flags;
        this._idEdit = other._idEdit;
        this._dropData = other._dropData == null ? null : other._dropData.copy();
        this._rgLines = other._rgLines == null ? null : (String[])other._rgLines.clone();
        this._bsels = other._bsels == null ? null : (boolean[])other._bsels.clone();
    }

    public LbsDataSubRecord(LittleEndianInput in, int cbFContinued, int cmoOt) {
        int i;
        this._cbFContinued = cbFContinued;
        int encodedTokenLen = in.readUShort();
        if (encodedTokenLen > 0) {
            int formulaSize = in.readUShort();
            this._unknownPreFormulaInt = in.readInt();
            Ptg[] ptgs = Ptg.readTokens(formulaSize, in);
            if (ptgs.length != 1) {
                throw new RecordFormatException("Read " + ptgs.length + " tokens but expected exactly 1");
            }
            this._linkPtg = ptgs[0];
            switch (encodedTokenLen - formulaSize - 6) {
                case 1: {
                    this._unknownPostFormulaByte = in.readByte();
                    break;
                }
                case 0: {
                    this._unknownPostFormulaByte = null;
                    break;
                }
                default: {
                    throw new RecordFormatException("Unexpected leftover bytes");
                }
            }
        }
        this._cLines = in.readUShort();
        this._iSel = in.readUShort();
        this._flags = in.readUShort();
        this._idEdit = in.readUShort();
        if (cmoOt == 20) {
            this._dropData = new LbsDropData(in);
        }
        if ((this._flags & 2) != 0) {
            this._rgLines = new String[this._cLines];
            for (i = 0; i < this._cLines; ++i) {
                this._rgLines[i] = StringUtil.readUnicodeString(in);
            }
        }
        if ((this._flags >> 4 & 2) != 0) {
            this._bsels = new boolean[this._cLines];
            for (i = 0; i < this._cLines; ++i) {
                this._bsels[i] = in.readByte() == 1;
            }
        }
    }

    public static LbsDataSubRecord newAutoFilterInstance() {
        LbsDataSubRecord lbs = new LbsDataSubRecord();
        lbs._cbFContinued = 8174;
        lbs._iSel = 0;
        lbs._flags = 769;
        lbs._dropData = new LbsDropData();
        lbs._dropData._wStyle = 2;
        lbs._dropData._cLine = 8;
        return lbs;
    }

    @Override
    public boolean isTerminating() {
        return true;
    }

    @Override
    protected int getDataSize() {
        int result = 2;
        if (this._linkPtg != null) {
            result += 2;
            result += 4;
            result += this._linkPtg.getSize();
            if (this._unknownPostFormulaByte != null) {
                ++result;
            }
        }
        result += 8;
        if (this._dropData != null) {
            result += this._dropData.getDataSize();
        }
        if (this._rgLines != null) {
            for (String str : this._rgLines) {
                result += StringUtil.getEncodedSize(str);
            }
        }
        if (this._bsels != null) {
            result += this._bsels.length;
        }
        return result;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(19);
        out.writeShort(this._cbFContinued);
        if (this._linkPtg == null) {
            out.writeShort(0);
        } else {
            int formulaSize = this._linkPtg.getSize();
            int linkSize = formulaSize + 6;
            if (this._unknownPostFormulaByte != null) {
                ++linkSize;
            }
            out.writeShort(linkSize);
            out.writeShort(formulaSize);
            out.writeInt(this._unknownPreFormulaInt);
            this._linkPtg.write(out);
            if (this._unknownPostFormulaByte != null) {
                out.writeByte(this._unknownPostFormulaByte.intValue());
            }
        }
        out.writeShort(this._cLines);
        out.writeShort(this._iSel);
        out.writeShort(this._flags);
        out.writeShort(this._idEdit);
        if (this._dropData != null) {
            this._dropData.serialize(out);
        }
        if (this._rgLines != null) {
            for (String str : this._rgLines) {
                StringUtil.writeUnicodeString(out, str);
            }
        }
        if (this._bsels != null) {
            for (boolean val : this._bsels) {
                out.writeByte(val ? 1 : 0);
            }
        }
    }

    @Override
    @Deprecated
    @Removal(version="5.0.0")
    public LbsDataSubRecord clone() {
        return this.copy();
    }

    @Override
    public LbsDataSubRecord copy() {
        return new LbsDataSubRecord(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("[ftLbsData]\n");
        sb.append("    .unknownShort1 =").append(HexDump.shortToHex(this._cbFContinued)).append("\n");
        sb.append("    .formula        = ").append('\n');
        if (this._linkPtg != null) {
            sb.append(this._linkPtg).append(this._linkPtg.getRVAType()).append('\n');
        }
        sb.append("    .nEntryCount   =").append(HexDump.shortToHex(this._cLines)).append("\n");
        sb.append("    .selEntryIx    =").append(HexDump.shortToHex(this._iSel)).append("\n");
        sb.append("    .style         =").append(HexDump.shortToHex(this._flags)).append("\n");
        sb.append("    .unknownShort10=").append(HexDump.shortToHex(this._idEdit)).append("\n");
        if (this._dropData != null) {
            sb.append('\n').append(this._dropData);
        }
        sb.append("[/ftLbsData]\n");
        return sb.toString();
    }

    public Ptg getFormula() {
        return this._linkPtg;
    }

    public int getNumberOfItems() {
        return this._cLines;
    }

    public static class LbsDropData
    implements Duplicatable {
        public static final int STYLE_COMBO_DROPDOWN = 0;
        public static final int STYLE_COMBO_EDIT_DROPDOWN = 1;
        public static final int STYLE_COMBO_SIMPLE_DROPDOWN = 2;
        private int _wStyle;
        private int _cLine;
        private int _dxMin;
        private final String _str;
        private Byte _unused;

        public LbsDropData() {
            this._str = "";
            this._unused = 0;
        }

        public LbsDropData(LbsDropData other) {
            this._wStyle = other._wStyle;
            this._cLine = other._cLine;
            this._dxMin = other._dxMin;
            this._str = other._str;
            this._unused = other._unused;
        }

        public LbsDropData(LittleEndianInput in) {
            this._wStyle = in.readUShort();
            this._cLine = in.readUShort();
            this._dxMin = in.readUShort();
            this._str = StringUtil.readUnicodeString(in);
            if (StringUtil.getEncodedSize(this._str) % 2 != 0) {
                this._unused = in.readByte();
            }
        }

        public void setStyle(int style) {
            this._wStyle = style;
        }

        public void setNumLines(int num) {
            this._cLine = num;
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this._wStyle);
            out.writeShort(this._cLine);
            out.writeShort(this._dxMin);
            StringUtil.writeUnicodeString(out, this._str);
            if (this._unused != null) {
                out.writeByte(this._unused.byteValue());
            }
        }

        public int getDataSize() {
            int size = 6;
            size += StringUtil.getEncodedSize(this._str);
            if (this._unused != null) {
                ++size;
            }
            return size;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[LbsDropData]\n");
            sb.append("  ._wStyle:  ").append(this._wStyle).append('\n');
            sb.append("  ._cLine:  ").append(this._cLine).append('\n');
            sb.append("  ._dxMin:  ").append(this._dxMin).append('\n');
            sb.append("  ._str:  ").append(this._str).append('\n');
            if (this._unused != null) {
                sb.append("  ._unused:  ").append(this._unused).append('\n');
            }
            sb.append("[/LbsDropData]\n");
            return sb.toString();
        }

        @Override
        public LbsDropData copy() {
            return new LbsDropData(this);
        }
    }
}

