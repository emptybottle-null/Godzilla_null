/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class RowRecord
extends StandardRecord {
    public static final short sid = 520;
    public static final int ENCODED_SIZE = 20;
    private static final int OPTION_BITS_ALWAYS_SET = 256;
    private static final BitField outlineLevel = BitFieldFactory.getInstance(7);
    private static final BitField colapsed = BitFieldFactory.getInstance(16);
    private static final BitField zeroHeight = BitFieldFactory.getInstance(32);
    private static final BitField badFontHeight = BitFieldFactory.getInstance(64);
    private static final BitField formatted = BitFieldFactory.getInstance(128);
    private static final BitField xfIndex = BitFieldFactory.getInstance(4095);
    private static final BitField topBorder = BitFieldFactory.getInstance(4096);
    private static final BitField bottomBorder = BitFieldFactory.getInstance(8192);
    private static final BitField phoeneticGuide = BitFieldFactory.getInstance(16384);
    private int field_1_row_number;
    private int field_2_first_col;
    private int field_3_last_col;
    private short field_4_height;
    private short field_5_optimize;
    private short field_6_reserved;
    private int field_7_option_flags;
    private int field_8_option_flags;

    public RowRecord(RowRecord other) {
        super(other);
        this.field_1_row_number = other.field_1_row_number;
        this.field_2_first_col = other.field_2_first_col;
        this.field_3_last_col = other.field_3_last_col;
        this.field_4_height = other.field_4_height;
        this.field_5_optimize = other.field_5_optimize;
        this.field_6_reserved = other.field_6_reserved;
        this.field_7_option_flags = other.field_7_option_flags;
        this.field_8_option_flags = other.field_8_option_flags;
    }

    public RowRecord(int rowNumber) {
        if (rowNumber < 0) {
            throw new IllegalArgumentException("Invalid row number (" + rowNumber + ")");
        }
        this.field_1_row_number = rowNumber;
        this.field_4_height = (short)255;
        this.field_5_optimize = 0;
        this.field_6_reserved = 0;
        this.field_7_option_flags = 256;
        this.field_8_option_flags = 15;
        this.setEmpty();
    }

    public RowRecord(RecordInputStream in) {
        this.field_1_row_number = in.readUShort();
        if (this.field_1_row_number < 0) {
            throw new IllegalArgumentException("Invalid row number " + this.field_1_row_number + " found in InputStream");
        }
        this.field_2_first_col = in.readShort();
        this.field_3_last_col = in.readShort();
        this.field_4_height = in.readShort();
        this.field_5_optimize = in.readShort();
        this.field_6_reserved = in.readShort();
        this.field_7_option_flags = in.readShort();
        this.field_8_option_flags = in.readShort();
    }

    public void setEmpty() {
        this.field_2_first_col = 0;
        this.field_3_last_col = 0;
    }

    public boolean isEmpty() {
        return (this.field_2_first_col | this.field_3_last_col) == 0;
    }

    public void setRowNumber(int row) {
        this.field_1_row_number = row;
    }

    public void setFirstCol(int col) {
        this.field_2_first_col = col;
    }

    public void setLastCol(int col) {
        this.field_3_last_col = col;
    }

    public void setHeight(short height) {
        this.field_4_height = height;
    }

    public void setOptimize(short optimize) {
        this.field_5_optimize = optimize;
    }

    public void setOutlineLevel(short ol) {
        this.field_7_option_flags = outlineLevel.setValue(this.field_7_option_flags, ol);
    }

    public void setColapsed(boolean c) {
        this.field_7_option_flags = colapsed.setBoolean(this.field_7_option_flags, c);
    }

    public void setZeroHeight(boolean z) {
        this.field_7_option_flags = zeroHeight.setBoolean(this.field_7_option_flags, z);
    }

    public void setBadFontHeight(boolean f) {
        this.field_7_option_flags = badFontHeight.setBoolean(this.field_7_option_flags, f);
    }

    public void setFormatted(boolean f) {
        this.field_7_option_flags = formatted.setBoolean(this.field_7_option_flags, f);
    }

    public void setXFIndex(short index) {
        this.field_8_option_flags = xfIndex.setValue(this.field_8_option_flags, index);
    }

    public void setTopBorder(boolean f) {
        this.field_8_option_flags = topBorder.setBoolean(this.field_8_option_flags, f);
    }

    public void setBottomBorder(boolean f) {
        this.field_8_option_flags = bottomBorder.setBoolean(this.field_8_option_flags, f);
    }

    public void setPhoeneticGuide(boolean f) {
        this.field_8_option_flags = phoeneticGuide.setBoolean(this.field_8_option_flags, f);
    }

    public int getRowNumber() {
        return this.field_1_row_number;
    }

    public int getFirstCol() {
        return this.field_2_first_col;
    }

    public int getLastCol() {
        return this.field_3_last_col;
    }

    public short getHeight() {
        return this.field_4_height;
    }

    public short getOptimize() {
        return this.field_5_optimize;
    }

    public short getOptionFlags() {
        return (short)this.field_7_option_flags;
    }

    public short getOutlineLevel() {
        return (short)outlineLevel.getValue(this.field_7_option_flags);
    }

    public boolean getColapsed() {
        return colapsed.isSet(this.field_7_option_flags);
    }

    public boolean getZeroHeight() {
        return zeroHeight.isSet(this.field_7_option_flags);
    }

    public boolean getBadFontHeight() {
        return badFontHeight.isSet(this.field_7_option_flags);
    }

    public boolean getFormatted() {
        return formatted.isSet(this.field_7_option_flags);
    }

    public short getOptionFlags2() {
        return (short)this.field_8_option_flags;
    }

    public short getXFIndex() {
        return xfIndex.getShortValue((short)this.field_8_option_flags);
    }

    public boolean getTopBorder() {
        return topBorder.isSet(this.field_8_option_flags);
    }

    public boolean getBottomBorder() {
        return bottomBorder.isSet(this.field_8_option_flags);
    }

    public boolean getPhoeneticGuide() {
        return phoeneticGuide.isSet(this.field_8_option_flags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ROW]\n");
        sb.append("    .rownumber      = ").append(Integer.toHexString(this.getRowNumber())).append("\n");
        sb.append("    .firstcol       = ").append(HexDump.shortToHex(this.getFirstCol())).append("\n");
        sb.append("    .lastcol        = ").append(HexDump.shortToHex(this.getLastCol())).append("\n");
        sb.append("    .height         = ").append(HexDump.shortToHex(this.getHeight())).append("\n");
        sb.append("    .optimize       = ").append(HexDump.shortToHex(this.getOptimize())).append("\n");
        sb.append("    .reserved       = ").append(HexDump.shortToHex(this.field_6_reserved)).append("\n");
        sb.append("    .optionflags    = ").append(HexDump.shortToHex(this.getOptionFlags())).append("\n");
        sb.append("        .outlinelvl = ").append(Integer.toHexString(this.getOutlineLevel())).append("\n");
        sb.append("        .colapsed   = ").append(this.getColapsed()).append("\n");
        sb.append("        .zeroheight = ").append(this.getZeroHeight()).append("\n");
        sb.append("        .badfontheig= ").append(this.getBadFontHeight()).append("\n");
        sb.append("        .formatted  = ").append(this.getFormatted()).append("\n");
        sb.append("    .optionsflags2  = ").append(HexDump.shortToHex(this.getOptionFlags2())).append("\n");
        sb.append("        .xfindex       = ").append(Integer.toHexString(this.getXFIndex())).append("\n");
        sb.append("        .topBorder     = ").append(this.getTopBorder()).append("\n");
        sb.append("        .bottomBorder  = ").append(this.getBottomBorder()).append("\n");
        sb.append("        .phoeneticGuide= ").append(this.getPhoeneticGuide()).append("\n");
        sb.append("[/ROW]\n");
        return sb.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getRowNumber());
        out.writeShort(this.getFirstCol() == -1 ? 0 : this.getFirstCol());
        out.writeShort(this.getLastCol() == -1 ? 0 : this.getLastCol());
        out.writeShort(this.getHeight());
        out.writeShort(this.getOptimize());
        out.writeShort(this.field_6_reserved);
        out.writeShort(this.getOptionFlags());
        out.writeShort(this.getOptionFlags2());
    }

    @Override
    protected int getDataSize() {
        return 16;
    }

    @Override
    public short getSid() {
        return 520;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public RowRecord clone() {
        return this.copy();
    }

    @Override
    public RowRecord copy() {
        return new RowRecord(this);
    }
}

