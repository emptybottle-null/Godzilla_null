/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;

public final class NoteRecord
extends StandardRecord {
    public static final short sid = 28;
    public static final NoteRecord[] EMPTY_ARRAY = new NoteRecord[0];
    public static final short NOTE_HIDDEN = 0;
    public static final short NOTE_VISIBLE = 2;
    private static final Byte DEFAULT_PADDING = 0;
    private int field_1_row;
    private int field_2_col;
    private short field_3_flags;
    private int field_4_shapeid;
    private boolean field_5_hasMultibyte;
    private String field_6_author;
    private Byte field_7_padding;

    public NoteRecord() {
        this.field_6_author = "";
        this.field_3_flags = 0;
        this.field_7_padding = DEFAULT_PADDING;
    }

    public NoteRecord(NoteRecord other) {
        super(other);
        this.field_1_row = other.field_1_row;
        this.field_2_col = other.field_2_col;
        this.field_3_flags = other.field_3_flags;
        this.field_4_shapeid = other.field_4_shapeid;
        this.field_5_hasMultibyte = other.field_5_hasMultibyte;
        this.field_6_author = other.field_6_author;
        this.field_7_padding = other.field_7_padding;
    }

    @Override
    public short getSid() {
        return 28;
    }

    public NoteRecord(RecordInputStream in) {
        this.field_1_row = in.readUShort();
        this.field_2_col = in.readShort();
        this.field_3_flags = in.readShort();
        this.field_4_shapeid = in.readUShort();
        short length = in.readShort();
        this.field_5_hasMultibyte = in.readByte() != 0;
        this.field_6_author = this.field_5_hasMultibyte ? StringUtil.readUnicodeLE(in, length) : StringUtil.readCompressedUnicode(in, length);
        if (in.available() == 1) {
            this.field_7_padding = in.readByte();
        } else if (in.available() == 2 && length == 0) {
            this.field_7_padding = in.readByte();
            in.readByte();
        }
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_row);
        out.writeShort(this.field_2_col);
        out.writeShort(this.field_3_flags);
        out.writeShort(this.field_4_shapeid);
        out.writeShort(this.field_6_author.length());
        out.writeByte(this.field_5_hasMultibyte ? 1 : 0);
        if (this.field_5_hasMultibyte) {
            StringUtil.putUnicodeLE(this.field_6_author, out);
        } else {
            StringUtil.putCompressedUnicode(this.field_6_author, out);
        }
        if (this.field_7_padding != null) {
            out.writeByte(this.field_7_padding.intValue());
        }
    }

    @Override
    protected int getDataSize() {
        return 11 + this.field_6_author.length() * (this.field_5_hasMultibyte ? 2 : 1) + (this.field_7_padding == null ? 0 : 1);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[NOTE]\n");
        buffer.append("    .row    = ").append(this.field_1_row).append("\n");
        buffer.append("    .col    = ").append(this.field_2_col).append("\n");
        buffer.append("    .flags  = ").append(this.field_3_flags).append("\n");
        buffer.append("    .shapeid= ").append(this.field_4_shapeid).append("\n");
        buffer.append("    .author = ").append(this.field_6_author).append("\n");
        buffer.append("[/NOTE]\n");
        return buffer.toString();
    }

    public int getRow() {
        return this.field_1_row;
    }

    public void setRow(int row) {
        this.field_1_row = row;
    }

    public int getColumn() {
        return this.field_2_col;
    }

    public void setColumn(int col) {
        this.field_2_col = col;
    }

    public short getFlags() {
        return this.field_3_flags;
    }

    public void setFlags(short flags) {
        this.field_3_flags = flags;
    }

    protected boolean authorIsMultibyte() {
        return this.field_5_hasMultibyte;
    }

    public int getShapeId() {
        return this.field_4_shapeid;
    }

    public void setShapeId(int id) {
        this.field_4_shapeid = id;
    }

    public String getAuthor() {
        return this.field_6_author;
    }

    public void setAuthor(String author) {
        this.field_6_author = author;
        this.field_5_hasMultibyte = StringUtil.hasMultibyte(author);
    }

    @Deprecated
    @Removal(version="5.0.0")
    public NoteRecord clone() {
        return this.copy();
    }

    @Override
    public NoteRecord copy() {
        return new NoteRecord(this);
    }
}

