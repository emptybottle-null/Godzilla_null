/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.ByteArrayInputStream;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianInputStream;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;

public final class EmbeddedObjectRefSubRecord
extends SubRecord {
    private static POILogger logger = POILogFactory.getLogger(EmbeddedObjectRefSubRecord.class);
    private static final int MAX_RECORD_LENGTH = 100000;
    public static final short sid = 9;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private int field_1_unknown_int;
    private Ptg field_2_refPtg;
    private byte[] field_2_unknownFormulaData;
    private boolean field_3_unicode_flag;
    private String field_4_ole_classname;
    private Byte field_4_unknownByte;
    private Integer field_5_stream_id;
    private byte[] field_6_unknown;

    public EmbeddedObjectRefSubRecord() {
        this.field_2_unknownFormulaData = new byte[]{2, 108, 106, 22, 1};
        this.field_6_unknown = EMPTY_BYTE_ARRAY;
        this.field_4_ole_classname = null;
    }

    public EmbeddedObjectRefSubRecord(EmbeddedObjectRefSubRecord other) {
        super(other);
        this.field_1_unknown_int = other.field_1_unknown_int;
        this.field_2_refPtg = other.field_2_refPtg == null ? null : other.field_2_refPtg.copy();
        this.field_2_unknownFormulaData = other.field_2_unknownFormulaData == null ? null : (byte[])other.field_2_unknownFormulaData.clone();
        this.field_3_unicode_flag = other.field_3_unicode_flag;
        this.field_4_ole_classname = other.field_4_ole_classname;
        this.field_4_unknownByte = other.field_4_unknownByte;
        this.field_5_stream_id = other.field_5_stream_id;
        this.field_6_unknown = other.field_6_unknown == null ? null : (byte[])other.field_6_unknown.clone();
    }

    public EmbeddedObjectRefSubRecord(LittleEndianInput in, int size) {
        int nUnexpectedPadding;
        int stringByteCount;
        short streamIdOffset = in.readShort();
        int remaining = size - 2;
        int dataLenAfterFormula = remaining - streamIdOffset;
        int formulaSize = in.readUShort();
        remaining -= 2;
        this.field_1_unknown_int = in.readInt();
        remaining -= 4;
        byte[] formulaRawBytes = EmbeddedObjectRefSubRecord.readRawData(in, formulaSize);
        this.field_2_refPtg = EmbeddedObjectRefSubRecord.readRefPtg(formulaRawBytes);
        this.field_2_unknownFormulaData = (byte[])(this.field_2_refPtg == null ? formulaRawBytes : null);
        if ((remaining -= formulaSize) >= dataLenAfterFormula + 3) {
            byte tag = in.readByte();
            stringByteCount = 1;
            if (tag != 3) {
                throw new RecordFormatException("Expected byte 0x03 here");
            }
            int nChars = in.readUShort();
            stringByteCount += 2;
            if (nChars > 0) {
                this.field_3_unicode_flag = (in.readByte() & 1) != 0;
                ++stringByteCount;
                if (this.field_3_unicode_flag) {
                    this.field_4_ole_classname = StringUtil.readUnicodeLE(in, nChars);
                    stringByteCount += nChars * 2;
                } else {
                    this.field_4_ole_classname = StringUtil.readCompressedUnicode(in, nChars);
                    stringByteCount += nChars;
                }
            } else {
                this.field_4_ole_classname = "";
            }
        } else {
            this.field_4_ole_classname = null;
            stringByteCount = 0;
        }
        remaining -= stringByteCount;
        if ((stringByteCount + formulaSize) % 2 != 0) {
            byte b = in.readByte();
            --remaining;
            if (this.field_2_refPtg != null && this.field_4_ole_classname == null) {
                this.field_4_unknownByte = b;
            }
        }
        if ((nUnexpectedPadding = remaining - dataLenAfterFormula) > 0) {
            logger.log(7, "Discarding " + nUnexpectedPadding + " unexpected padding bytes ");
            EmbeddedObjectRefSubRecord.readRawData(in, nUnexpectedPadding);
            remaining -= nUnexpectedPadding;
        }
        if (dataLenAfterFormula >= 4) {
            this.field_5_stream_id = in.readInt();
            remaining -= 4;
        } else {
            this.field_5_stream_id = null;
        }
        this.field_6_unknown = EmbeddedObjectRefSubRecord.readRawData(in, remaining);
    }

    public short getSid() {
        return 9;
    }

    private static Ptg readRefPtg(byte[] formulaRawBytes) {
        LittleEndianInputStream in = new LittleEndianInputStream(new ByteArrayInputStream(formulaRawBytes));
        byte ptgSid = in.readByte();
        switch (ptgSid) {
            case 37: {
                return new AreaPtg(in);
            }
            case 59: {
                return new Area3DPtg(in);
            }
            case 36: {
                return new RefPtg(in);
            }
            case 58: {
                return new Ref3DPtg(in);
            }
        }
        return null;
    }

    private static byte[] readRawData(LittleEndianInput in, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative size (" + size + ")");
        }
        if (size == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        byte[] result = IOUtils.safelyAllocate(size, 100000);
        in.readFully(result);
        return result;
    }

    private int getStreamIDOffset(int formulaSize) {
        int result = 6;
        result += formulaSize;
        if (this.field_4_ole_classname == null) {
            boolean stringLen = false;
        } else {
            result += 3;
            int stringLen = this.field_4_ole_classname.length();
            if (stringLen > 0) {
                ++result;
                result = this.field_3_unicode_flag ? (result += stringLen * 2) : (result += stringLen);
            }
        }
        if (result % 2 != 0) {
            ++result;
        }
        return result;
    }

    private int getDataSize(int idOffset) {
        int result = 2 + idOffset;
        if (this.field_5_stream_id != null) {
            result += 4;
        }
        return result + this.field_6_unknown.length;
    }

    @Override
    protected int getDataSize() {
        int formulaSize = this.field_2_refPtg == null ? this.field_2_unknownFormulaData.length : this.field_2_refPtg.getSize();
        int idOffset = this.getStreamIDOffset(formulaSize);
        return this.getDataSize(idOffset);
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        int formulaSize = this.field_2_refPtg == null ? this.field_2_unknownFormulaData.length : this.field_2_refPtg.getSize();
        int idOffset = this.getStreamIDOffset(formulaSize);
        int dataSize = this.getDataSize(idOffset);
        out.writeShort(9);
        out.writeShort(dataSize);
        out.writeShort(idOffset);
        out.writeShort(formulaSize);
        out.writeInt(this.field_1_unknown_int);
        int pos = 12;
        if (this.field_2_refPtg == null) {
            out.write(this.field_2_unknownFormulaData);
        } else {
            this.field_2_refPtg.write(out);
        }
        pos += formulaSize;
        if (this.field_4_ole_classname == null) {
            boolean stringLen = false;
        } else {
            out.writeByte(3);
            ++pos;
            int stringLen = this.field_4_ole_classname.length();
            out.writeShort(stringLen);
            pos += 2;
            if (stringLen > 0) {
                out.writeByte(this.field_3_unicode_flag ? 1 : 0);
                ++pos;
                if (this.field_3_unicode_flag) {
                    StringUtil.putUnicodeLE(this.field_4_ole_classname, out);
                    pos += stringLen * 2;
                } else {
                    StringUtil.putCompressedUnicode(this.field_4_ole_classname, out);
                    pos += stringLen;
                }
            }
        }
        switch (idOffset - (pos - 6)) {
            case 1: {
                out.writeByte(this.field_4_unknownByte == null ? 0 : this.field_4_unknownByte.intValue());
                ++pos;
                break;
            }
            case 0: {
                break;
            }
            default: {
                throw new IllegalStateException("Bad padding calculation (" + idOffset + ", " + pos + ")");
            }
        }
        if (this.field_5_stream_id != null) {
            out.writeInt(this.field_5_stream_id);
            pos += 4;
        }
        out.write(this.field_6_unknown);
    }

    public Integer getStreamId() {
        return this.field_5_stream_id;
    }

    public String getOLEClassName() {
        return this.field_4_ole_classname;
    }

    public byte[] getObjectData() {
        return this.field_6_unknown;
    }

    @Override
    @Deprecated
    @Removal(version="5.0.0")
    public EmbeddedObjectRefSubRecord clone() {
        return this.copy();
    }

    @Override
    public EmbeddedObjectRefSubRecord copy() {
        return new EmbeddedObjectRefSubRecord(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ftPictFmla]\n");
        sb.append("    .f2unknown     = ").append(HexDump.intToHex(this.field_1_unknown_int)).append("\n");
        if (this.field_2_refPtg == null) {
            sb.append("    .f3unknown     = ").append(HexDump.toHex(this.field_2_unknownFormulaData)).append("\n");
        } else {
            sb.append("    .formula       = ").append(this.field_2_refPtg).append("\n");
        }
        if (this.field_4_ole_classname != null) {
            sb.append("    .unicodeFlag   = ").append(this.field_3_unicode_flag).append("\n");
            sb.append("    .oleClassname  = ").append(this.field_4_ole_classname).append("\n");
        }
        if (this.field_4_unknownByte != null) {
            sb.append("    .f4unknown   = ").append(HexDump.byteToHex(this.field_4_unknownByte.intValue())).append("\n");
        }
        if (this.field_5_stream_id != null) {
            sb.append("    .streamId      = ").append(HexDump.intToHex(this.field_5_stream_id)).append("\n");
        }
        if (this.field_6_unknown.length > 0) {
            sb.append("    .f7unknown     = ").append(HexDump.toHex(this.field_6_unknown)).append("\n");
        }
        sb.append("[/ftPictFmla]");
        return sb.toString();
    }

    public void setUnknownFormulaData(byte[] formularData) {
        this.field_2_unknownFormulaData = formularData;
    }

    public void setOleClassname(String oleClassname) {
        this.field_4_ole_classname = oleClassname;
    }

    public void setStorageId(int storageId) {
        this.field_5_stream_id = storageId;
    }
}

