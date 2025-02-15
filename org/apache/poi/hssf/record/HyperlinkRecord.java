/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.HexRead;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;

public final class HyperlinkRecord
extends StandardRecord {
    public static final short sid = 440;
    private static final POILogger logger = POILogFactory.getLogger(HyperlinkRecord.class);
    private static final int MAX_RECORD_LENGTH = 100000;
    static final int HLINK_URL = 1;
    static final int HLINK_ABS = 2;
    static final int HLINK_LABEL = 20;
    static final int HLINK_PLACE = 8;
    private static final int HLINK_TARGET_FRAME = 128;
    private static final int HLINK_UNC_PATH = 256;
    private static final byte[] URL_TAIL = HexRead.readFromString("79 58 81 F4  3B 1D 7F 48   AF 2C 82 5D  C4 85 27 63   00 00 00 00  A5 AB 00 00");
    private static final byte[] FILE_TAIL = HexRead.readFromString("FF FF AD DE  00 00 00 00   00 00 00 00  00 00 00 00   00 00 00 00  00 00 00 00");
    private static final int TAIL_SIZE = FILE_TAIL.length;
    private CellRangeAddress _range;
    private ClassID _guid;
    private int _fileOpts;
    private int _linkOpts;
    private String _label;
    private String _targetFrame;
    private ClassID _moniker;
    private String _shortFilename;
    private String _address;
    private String _textMark;
    private byte[] _uninterpretedTail;

    public HyperlinkRecord() {
    }

    public HyperlinkRecord(HyperlinkRecord other) {
        super(other);
        this._range = other._range == null ? null : other._range.copy();
        this._guid = other._guid == null ? null : other._guid.copy();
        this._fileOpts = other._fileOpts;
        this._linkOpts = other._linkOpts;
        this._label = other._label;
        this._targetFrame = other._targetFrame;
        this._moniker = other._moniker == null ? null : other._moniker.copy();
        this._shortFilename = other._shortFilename;
        this._address = other._address;
        this._textMark = other._textMark;
        this._uninterpretedTail = other._uninterpretedTail == null ? null : (byte[])other._uninterpretedTail.clone();
    }

    public int getFirstColumn() {
        return this._range.getFirstColumn();
    }

    public void setFirstColumn(int firstCol) {
        this._range.setFirstColumn(firstCol);
    }

    public int getLastColumn() {
        return this._range.getLastColumn();
    }

    public void setLastColumn(int lastCol) {
        this._range.setLastColumn(lastCol);
    }

    public int getFirstRow() {
        return this._range.getFirstRow();
    }

    public void setFirstRow(int firstRow) {
        this._range.setFirstRow(firstRow);
    }

    public int getLastRow() {
        return this._range.getLastRow();
    }

    public void setLastRow(int lastRow) {
        this._range.setLastRow(lastRow);
    }

    ClassID getGuid() {
        return this._guid;
    }

    ClassID getMoniker() {
        return this._moniker;
    }

    private static String cleanString(String s) {
        if (s == null) {
            return null;
        }
        int idx = s.indexOf(0);
        if (idx < 0) {
            return s;
        }
        return s.substring(0, idx);
    }

    private static String appendNullTerm(String s) {
        if (s == null) {
            return null;
        }
        return s + '\u0000';
    }

    public String getLabel() {
        return HyperlinkRecord.cleanString(this._label);
    }

    public void setLabel(String label) {
        this._label = HyperlinkRecord.appendNullTerm(label);
    }

    public String getTargetFrame() {
        return HyperlinkRecord.cleanString(this._targetFrame);
    }

    public String getAddress() {
        if ((this._linkOpts & 1) != 0 && ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
            return HyperlinkRecord.cleanString(this._address != null ? this._address : this._shortFilename);
        }
        if ((this._linkOpts & 8) != 0) {
            return HyperlinkRecord.cleanString(this._textMark);
        }
        return HyperlinkRecord.cleanString(this._address);
    }

    public void setAddress(String address) {
        if ((this._linkOpts & 1) != 0 && ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
            this._shortFilename = HyperlinkRecord.appendNullTerm(address);
        } else if ((this._linkOpts & 8) != 0) {
            this._textMark = HyperlinkRecord.appendNullTerm(address);
        } else {
            this._address = HyperlinkRecord.appendNullTerm(address);
        }
    }

    public String getShortFilename() {
        return HyperlinkRecord.cleanString(this._shortFilename);
    }

    public void setShortFilename(String shortFilename) {
        this._shortFilename = HyperlinkRecord.appendNullTerm(shortFilename);
    }

    public String getTextMark() {
        return HyperlinkRecord.cleanString(this._textMark);
    }

    public void setTextMark(String textMark) {
        this._textMark = HyperlinkRecord.appendNullTerm(textMark);
    }

    int getLinkOptions() {
        return this._linkOpts;
    }

    public int getLabelOptions() {
        return 2;
    }

    public int getFileOptions() {
        return this._fileOpts;
    }

    public HyperlinkRecord(RecordInputStream in) {
        int len;
        this._range = new CellRangeAddress(in);
        this._guid = new ClassID(in);
        int streamVersion = in.readInt();
        if (streamVersion != 2) {
            throw new RecordFormatException("Stream Version must be 0x2 but found " + streamVersion);
        }
        this._linkOpts = in.readInt();
        if ((this._linkOpts & 0x14) != 0) {
            int label_len = in.readInt();
            this._label = in.readUnicodeLEString(label_len);
        }
        if ((this._linkOpts & 0x80) != 0) {
            len = in.readInt();
            this._targetFrame = in.readUnicodeLEString(len);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 0x100) != 0) {
            this._moniker = null;
            int nChars = in.readInt();
            this._address = in.readUnicodeLEString(nChars);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 0x100) == 0) {
            this._moniker = new ClassID(in);
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                int remaining;
                int length = in.readInt();
                if (length == (remaining = in.remaining())) {
                    int nChars = length / 2;
                    this._address = in.readUnicodeLEString(nChars);
                } else {
                    int nChars = (length - TAIL_SIZE) / 2;
                    this._address = in.readUnicodeLEString(nChars);
                    this._uninterpretedTail = HyperlinkRecord.readTail(URL_TAIL, in);
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                this._fileOpts = in.readShort();
                len = in.readInt();
                this._shortFilename = StringUtil.readCompressedUnicode(in, len);
                this._uninterpretedTail = HyperlinkRecord.readTail(FILE_TAIL, in);
                int size = in.readInt();
                if (size > 0) {
                    int charDataSize = in.readInt();
                    in.readUShort();
                    this._address = StringUtil.readUnicodeLE(in, charDataSize / 2);
                } else {
                    this._address = null;
                }
            } else if (ClassIDPredefined.STD_MONIKER.equals(this._moniker)) {
                this._fileOpts = in.readShort();
                len = in.readInt();
                byte[] path_bytes = IOUtils.safelyAllocate(len, 100000);
                in.readFully(path_bytes);
                this._address = new String(path_bytes, StringUtil.UTF8);
            }
        }
        if ((this._linkOpts & 8) != 0) {
            len = in.readInt();
            this._textMark = in.readUnicodeLEString(len);
        }
        if (in.remaining() > 0) {
            logger.log(5, "Hyperlink data remains: " + in.remaining() + " : " + HexDump.toHex(in.readRemainder()));
        }
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        this._range.serialize(out);
        this._guid.write(out);
        out.writeInt(2);
        out.writeInt(this._linkOpts);
        if ((this._linkOpts & 0x14) != 0) {
            out.writeInt(this._label.length());
            StringUtil.putUnicodeLE(this._label, out);
        }
        if ((this._linkOpts & 0x80) != 0) {
            out.writeInt(this._targetFrame.length());
            StringUtil.putUnicodeLE(this._targetFrame, out);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 0x100) != 0) {
            out.writeInt(this._address.length());
            StringUtil.putUnicodeLE(this._address, out);
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 0x100) == 0) {
            this._moniker.write(out);
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                if (this._uninterpretedTail == null) {
                    out.writeInt(this._address.length() * 2);
                    StringUtil.putUnicodeLE(this._address, out);
                } else {
                    out.writeInt(this._address.length() * 2 + TAIL_SIZE);
                    StringUtil.putUnicodeLE(this._address, out);
                    HyperlinkRecord.writeTail(this._uninterpretedTail, out);
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                out.writeShort(this._fileOpts);
                out.writeInt(this._shortFilename.length());
                StringUtil.putCompressedUnicode(this._shortFilename, out);
                HyperlinkRecord.writeTail(this._uninterpretedTail, out);
                if (this._address == null) {
                    out.writeInt(0);
                } else {
                    int addrLen = this._address.length() * 2;
                    out.writeInt(addrLen + 6);
                    out.writeInt(addrLen);
                    out.writeShort(3);
                    StringUtil.putUnicodeLE(this._address, out);
                }
            }
        }
        if ((this._linkOpts & 8) != 0) {
            out.writeInt(this._textMark.length());
            StringUtil.putUnicodeLE(this._textMark, out);
        }
    }

    @Override
    protected int getDataSize() {
        int size = 0;
        size += 8;
        size += 16;
        size += 4;
        size += 4;
        if ((this._linkOpts & 0x14) != 0) {
            size += 4;
            size += this._label.length() * 2;
        }
        if ((this._linkOpts & 0x80) != 0) {
            size += 4;
            size += this._targetFrame.length() * 2;
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 0x100) != 0) {
            size += 4;
            size += this._address.length() * 2;
        }
        if ((this._linkOpts & 1) != 0 && (this._linkOpts & 0x100) == 0) {
            size += 16;
            if (ClassIDPredefined.URL_MONIKER.equals(this._moniker)) {
                size += 4;
                size += this._address.length() * 2;
                if (this._uninterpretedTail != null) {
                    size += TAIL_SIZE;
                }
            } else if (ClassIDPredefined.FILE_MONIKER.equals(this._moniker)) {
                size += 2;
                size += 4;
                size += this._shortFilename.length();
                size += TAIL_SIZE;
                size += 4;
                if (this._address != null) {
                    size += 6;
                    size += this._address.length() * 2;
                }
            }
        }
        if ((this._linkOpts & 8) != 0) {
            size += 4;
            size += this._textMark.length() * 2;
        }
        return size;
    }

    private static byte[] readTail(byte[] expectedTail, LittleEndianInput in) {
        byte[] result = new byte[TAIL_SIZE];
        in.readFully(result);
        return result;
    }

    private static void writeTail(byte[] tail, LittleEndianOutput out) {
        out.write(tail);
    }

    @Override
    public short getSid() {
        return 440;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[HYPERLINK RECORD]\n");
        buffer.append("    .range   = ").append(this._range.formatAsString()).append("\n");
        buffer.append("    .guid    = ").append(this._guid.toString()).append("\n");
        buffer.append("    .linkOpts= ").append(HexDump.intToHex(this._linkOpts)).append("\n");
        buffer.append("    .label   = ").append(this.getLabel()).append("\n");
        if ((this._linkOpts & 0x80) != 0) {
            buffer.append("    .targetFrame= ").append(this.getTargetFrame()).append("\n");
        }
        if ((this._linkOpts & 1) != 0 && this._moniker != null) {
            buffer.append("    .moniker   = ").append(this._moniker.toString()).append("\n");
        }
        if ((this._linkOpts & 8) != 0) {
            buffer.append("    .textMark= ").append(this.getTextMark()).append("\n");
        }
        buffer.append("    .address   = ").append(this.getAddress()).append("\n");
        buffer.append("[/HYPERLINK RECORD]\n");
        return buffer.toString();
    }

    public boolean isUrlLink() {
        return (this._linkOpts & 1) > 0 && (this._linkOpts & 2) > 0;
    }

    public boolean isFileLink() {
        return (this._linkOpts & 1) > 0 && (this._linkOpts & 2) == 0;
    }

    public boolean isDocumentLink() {
        return (this._linkOpts & 8) > 0;
    }

    public void newUrlLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 23;
        this.setLabel("");
        this._moniker = ClassIDPredefined.URL_MONIKER.getClassID();
        this.setAddress("");
        this._uninterpretedTail = URL_TAIL;
    }

    public void newFileLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 21;
        this._fileOpts = 0;
        this.setLabel("");
        this._moniker = ClassIDPredefined.FILE_MONIKER.getClassID();
        this.setAddress(null);
        this.setShortFilename("");
        this._uninterpretedTail = FILE_TAIL;
    }

    public void newDocumentLink() {
        this._range = new CellRangeAddress(0, 0, 0, 0);
        this._guid = ClassIDPredefined.STD_MONIKER.getClassID();
        this._linkOpts = 28;
        this.setLabel("");
        this._moniker = ClassIDPredefined.FILE_MONIKER.getClassID();
        this.setAddress("");
        this.setTextMark("");
    }

    @Deprecated
    @Removal(version="5.0.0")
    public HyperlinkRecord clone() {
        return this.copy();
    }

    @Override
    public HyperlinkRecord copy() {
        return new HyperlinkRecord(this);
    }
}

