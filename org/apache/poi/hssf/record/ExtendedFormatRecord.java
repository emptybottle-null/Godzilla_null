/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.util.Objects;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianOutput;

public final class ExtendedFormatRecord
extends StandardRecord {
    public static final short sid = 224;
    public static final short NULL = -16;
    public static final short XF_STYLE = 1;
    public static final short XF_CELL = 0;
    public static final short NONE = 0;
    public static final short THIN = 1;
    public static final short MEDIUM = 2;
    public static final short DASHED = 3;
    public static final short DOTTED = 4;
    public static final short THICK = 5;
    public static final short DOUBLE = 6;
    public static final short HAIR = 7;
    public static final short MEDIUM_DASHED = 8;
    public static final short DASH_DOT = 9;
    public static final short MEDIUM_DASH_DOT = 10;
    public static final short DASH_DOT_DOT = 11;
    public static final short MEDIUM_DASH_DOT_DOT = 12;
    public static final short SLANTED_DASH_DOT = 13;
    public static final short GENERAL = 0;
    public static final short LEFT = 1;
    public static final short CENTER = 2;
    public static final short RIGHT = 3;
    public static final short FILL = 4;
    public static final short JUSTIFY = 5;
    public static final short CENTER_SELECTION = 6;
    public static final short VERTICAL_TOP = 0;
    public static final short VERTICAL_CENTER = 1;
    public static final short VERTICAL_BOTTOM = 2;
    public static final short VERTICAL_JUSTIFY = 3;
    public static final short NO_FILL = 0;
    public static final short SOLID_FILL = 1;
    public static final short FINE_DOTS = 2;
    public static final short ALT_BARS = 3;
    public static final short SPARSE_DOTS = 4;
    public static final short THICK_HORZ_BANDS = 5;
    public static final short THICK_VERT_BANDS = 6;
    public static final short THICK_BACKWARD_DIAG = 7;
    public static final short THICK_FORWARD_DIAG = 8;
    public static final short BIG_SPOTS = 9;
    public static final short BRICKS = 10;
    public static final short THIN_HORZ_BANDS = 11;
    public static final short THIN_VERT_BANDS = 12;
    public static final short THIN_BACKWARD_DIAG = 13;
    public static final short THIN_FORWARD_DIAG = 14;
    public static final short SQUARES = 15;
    public static final short DIAMONDS = 16;
    private static final BitField _locked = ExtendedFormatRecord.bf(1);
    private static final BitField _hidden = ExtendedFormatRecord.bf(2);
    private static final BitField _xf_type = ExtendedFormatRecord.bf(4);
    private static final BitField _123_prefix = ExtendedFormatRecord.bf(8);
    private static final BitField _parent_index = ExtendedFormatRecord.bf(65520);
    private static final BitField _alignment = ExtendedFormatRecord.bf(7);
    private static final BitField _wrap_text = ExtendedFormatRecord.bf(8);
    private static final BitField _vertical_alignment = ExtendedFormatRecord.bf(112);
    private static final BitField _justify_last = ExtendedFormatRecord.bf(128);
    private static final BitField _rotation = ExtendedFormatRecord.bf(65280);
    private static final BitField _indent = ExtendedFormatRecord.bf(15);
    private static final BitField _shrink_to_fit = ExtendedFormatRecord.bf(16);
    private static final BitField _merge_cells = ExtendedFormatRecord.bf(32);
    private static final BitField _reading_order = ExtendedFormatRecord.bf(192);
    private static final BitField _indent_not_parent_format = ExtendedFormatRecord.bf(1024);
    private static final BitField _indent_not_parent_font = ExtendedFormatRecord.bf(2048);
    private static final BitField _indent_not_parent_alignment = ExtendedFormatRecord.bf(4096);
    private static final BitField _indent_not_parent_border = ExtendedFormatRecord.bf(8192);
    private static final BitField _indent_not_parent_pattern = ExtendedFormatRecord.bf(16384);
    private static final BitField _indent_not_parent_cell_options = ExtendedFormatRecord.bf(32768);
    private static final BitField _border_left = ExtendedFormatRecord.bf(15);
    private static final BitField _border_right = ExtendedFormatRecord.bf(240);
    private static final BitField _border_top = ExtendedFormatRecord.bf(3840);
    private static final BitField _border_bottom = ExtendedFormatRecord.bf(61440);
    private static final BitField _left_border_palette_idx = ExtendedFormatRecord.bf(127);
    private static final BitField _right_border_palette_idx = ExtendedFormatRecord.bf(16256);
    private static final BitField _diag = ExtendedFormatRecord.bf(49152);
    private static final BitField _top_border_palette_idx = ExtendedFormatRecord.bf(127);
    private static final BitField _bottom_border_palette_idx = ExtendedFormatRecord.bf(16256);
    private static final BitField _adtl_diag = ExtendedFormatRecord.bf(2080768);
    private static final BitField _adtl_diag_line_style = ExtendedFormatRecord.bf(0x1E00000);
    private static final BitField _adtl_fill_pattern = ExtendedFormatRecord.bf(-67108864);
    private static final BitField _fill_foreground = ExtendedFormatRecord.bf(127);
    private static final BitField _fill_background = ExtendedFormatRecord.bf(16256);
    private short field_1_font_index;
    private short field_2_format_index;
    private short field_3_cell_options;
    private short field_4_alignment_options;
    private short field_5_indention_options;
    private short field_6_border_options;
    private short field_7_palette_options;
    private int field_8_adtl_palette_options;
    private short field_9_fill_palette_options;

    private static BitField bf(int i) {
        return BitFieldFactory.getInstance(i);
    }

    public ExtendedFormatRecord() {
    }

    public ExtendedFormatRecord(ExtendedFormatRecord other) {
        super(other);
        this.field_1_font_index = other.field_1_font_index;
        this.field_2_format_index = other.field_2_format_index;
        this.field_3_cell_options = other.field_3_cell_options;
        this.field_4_alignment_options = other.field_4_alignment_options;
        this.field_5_indention_options = other.field_5_indention_options;
        this.field_6_border_options = other.field_6_border_options;
        this.field_7_palette_options = other.field_7_palette_options;
        this.field_8_adtl_palette_options = other.field_8_adtl_palette_options;
        this.field_9_fill_palette_options = other.field_9_fill_palette_options;
    }

    public ExtendedFormatRecord(RecordInputStream in) {
        this.field_1_font_index = in.readShort();
        this.field_2_format_index = in.readShort();
        this.field_3_cell_options = in.readShort();
        this.field_4_alignment_options = in.readShort();
        this.field_5_indention_options = in.readShort();
        this.field_6_border_options = in.readShort();
        this.field_7_palette_options = in.readShort();
        this.field_8_adtl_palette_options = in.readInt();
        this.field_9_fill_palette_options = in.readShort();
    }

    public void setFontIndex(short index) {
        this.field_1_font_index = index;
    }

    public void setFormatIndex(short index) {
        this.field_2_format_index = index;
    }

    public void setCellOptions(short options) {
        this.field_3_cell_options = options;
    }

    public void setLocked(boolean locked) {
        this.field_3_cell_options = _locked.setShortBoolean(this.field_3_cell_options, locked);
    }

    public void setHidden(boolean hidden) {
        this.field_3_cell_options = _hidden.setShortBoolean(this.field_3_cell_options, hidden);
    }

    public void setXFType(short type) {
        this.field_3_cell_options = _xf_type.setShortValue(this.field_3_cell_options, type);
    }

    public void set123Prefix(boolean prefix) {
        this.field_3_cell_options = _123_prefix.setShortBoolean(this.field_3_cell_options, prefix);
    }

    public void setParentIndex(short parent) {
        this.field_3_cell_options = _parent_index.setShortValue(this.field_3_cell_options, parent);
    }

    public void setAlignmentOptions(short options) {
        this.field_4_alignment_options = options;
    }

    public void setAlignment(short align) {
        this.field_4_alignment_options = _alignment.setShortValue(this.field_4_alignment_options, align);
    }

    public void setWrapText(boolean wrapped) {
        this.field_4_alignment_options = _wrap_text.setShortBoolean(this.field_4_alignment_options, wrapped);
    }

    public void setVerticalAlignment(short align) {
        this.field_4_alignment_options = _vertical_alignment.setShortValue(this.field_4_alignment_options, align);
    }

    public void setJustifyLast(short justify) {
        this.field_4_alignment_options = _justify_last.setShortValue(this.field_4_alignment_options, justify);
    }

    public void setRotation(short rotation) {
        this.field_4_alignment_options = _rotation.setShortValue(this.field_4_alignment_options, rotation);
    }

    public void setIndentionOptions(short options) {
        this.field_5_indention_options = options;
    }

    public void setIndent(short indent) {
        this.field_5_indention_options = _indent.setShortValue(this.field_5_indention_options, indent);
    }

    public void setShrinkToFit(boolean shrink) {
        this.field_5_indention_options = _shrink_to_fit.setShortBoolean(this.field_5_indention_options, shrink);
    }

    public void setMergeCells(boolean merge) {
        this.field_5_indention_options = _merge_cells.setShortBoolean(this.field_5_indention_options, merge);
    }

    public void setReadingOrder(short order) {
        this.field_5_indention_options = _reading_order.setShortValue(this.field_5_indention_options, order);
    }

    public void setIndentNotParentFormat(boolean parent) {
        this.field_5_indention_options = _indent_not_parent_format.setShortBoolean(this.field_5_indention_options, parent);
    }

    public void setIndentNotParentFont(boolean font) {
        this.field_5_indention_options = _indent_not_parent_font.setShortBoolean(this.field_5_indention_options, font);
    }

    public void setIndentNotParentAlignment(boolean alignment) {
        this.field_5_indention_options = _indent_not_parent_alignment.setShortBoolean(this.field_5_indention_options, alignment);
    }

    public void setIndentNotParentBorder(boolean border) {
        this.field_5_indention_options = _indent_not_parent_border.setShortBoolean(this.field_5_indention_options, border);
    }

    public void setIndentNotParentPattern(boolean pattern) {
        this.field_5_indention_options = _indent_not_parent_pattern.setShortBoolean(this.field_5_indention_options, pattern);
    }

    public void setIndentNotParentCellOptions(boolean options) {
        this.field_5_indention_options = _indent_not_parent_cell_options.setShortBoolean(this.field_5_indention_options, options);
    }

    public void setBorderOptions(short options) {
        this.field_6_border_options = options;
    }

    public void setBorderLeft(short border) {
        this.field_6_border_options = _border_left.setShortValue(this.field_6_border_options, border);
    }

    public void setBorderRight(short border) {
        this.field_6_border_options = _border_right.setShortValue(this.field_6_border_options, border);
    }

    public void setBorderTop(short border) {
        this.field_6_border_options = _border_top.setShortValue(this.field_6_border_options, border);
    }

    public void setBorderBottom(short border) {
        this.field_6_border_options = _border_bottom.setShortValue(this.field_6_border_options, border);
    }

    public void setPaletteOptions(short options) {
        this.field_7_palette_options = options;
    }

    public void setLeftBorderPaletteIdx(short border) {
        this.field_7_palette_options = _left_border_palette_idx.setShortValue(this.field_7_palette_options, border);
    }

    public void setRightBorderPaletteIdx(short border) {
        this.field_7_palette_options = _right_border_palette_idx.setShortValue(this.field_7_palette_options, border);
    }

    public void setDiag(short diag) {
        this.field_7_palette_options = _diag.setShortValue(this.field_7_palette_options, diag);
    }

    public void setAdtlPaletteOptions(short options) {
        this.field_8_adtl_palette_options = options;
    }

    public void setTopBorderPaletteIdx(short border) {
        this.field_8_adtl_palette_options = _top_border_palette_idx.setValue(this.field_8_adtl_palette_options, border);
    }

    public void setBottomBorderPaletteIdx(short border) {
        this.field_8_adtl_palette_options = _bottom_border_palette_idx.setValue(this.field_8_adtl_palette_options, border);
    }

    public void setAdtlDiag(short diag) {
        this.field_8_adtl_palette_options = _adtl_diag.setValue(this.field_8_adtl_palette_options, diag);
    }

    public void setAdtlDiagLineStyle(short diag) {
        this.field_8_adtl_palette_options = _adtl_diag_line_style.setValue(this.field_8_adtl_palette_options, diag);
    }

    public void setAdtlFillPattern(short fill) {
        this.field_8_adtl_palette_options = _adtl_fill_pattern.setValue(this.field_8_adtl_palette_options, fill);
    }

    public void setFillPaletteOptions(short options) {
        this.field_9_fill_palette_options = options;
    }

    public void setFillForeground(short color) {
        this.field_9_fill_palette_options = _fill_foreground.setShortValue(this.field_9_fill_palette_options, color);
    }

    public void setFillBackground(short color) {
        this.field_9_fill_palette_options = _fill_background.setShortValue(this.field_9_fill_palette_options, color);
    }

    public short getFontIndex() {
        return this.field_1_font_index;
    }

    public short getFormatIndex() {
        return this.field_2_format_index;
    }

    public short getCellOptions() {
        return this.field_3_cell_options;
    }

    public boolean isLocked() {
        return _locked.isSet(this.field_3_cell_options);
    }

    public boolean isHidden() {
        return _hidden.isSet(this.field_3_cell_options);
    }

    public short getXFType() {
        return _xf_type.getShortValue(this.field_3_cell_options);
    }

    public boolean get123Prefix() {
        return _123_prefix.isSet(this.field_3_cell_options);
    }

    public short getParentIndex() {
        return _parent_index.getShortValue(this.field_3_cell_options);
    }

    public short getAlignmentOptions() {
        return this.field_4_alignment_options;
    }

    public short getAlignment() {
        return _alignment.getShortValue(this.field_4_alignment_options);
    }

    public boolean getWrapText() {
        return _wrap_text.isSet(this.field_4_alignment_options);
    }

    public short getVerticalAlignment() {
        return _vertical_alignment.getShortValue(this.field_4_alignment_options);
    }

    public short getJustifyLast() {
        return _justify_last.getShortValue(this.field_4_alignment_options);
    }

    public short getRotation() {
        return _rotation.getShortValue(this.field_4_alignment_options);
    }

    public short getIndentionOptions() {
        return this.field_5_indention_options;
    }

    public short getIndent() {
        return _indent.getShortValue(this.field_5_indention_options);
    }

    public boolean getShrinkToFit() {
        return _shrink_to_fit.isSet(this.field_5_indention_options);
    }

    public boolean getMergeCells() {
        return _merge_cells.isSet(this.field_5_indention_options);
    }

    public short getReadingOrder() {
        return _reading_order.getShortValue(this.field_5_indention_options);
    }

    public boolean isIndentNotParentFormat() {
        return _indent_not_parent_format.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentFont() {
        return _indent_not_parent_font.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentAlignment() {
        return _indent_not_parent_alignment.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentBorder() {
        return _indent_not_parent_border.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentPattern() {
        return _indent_not_parent_pattern.isSet(this.field_5_indention_options);
    }

    public boolean isIndentNotParentCellOptions() {
        return _indent_not_parent_cell_options.isSet(this.field_5_indention_options);
    }

    public short getBorderOptions() {
        return this.field_6_border_options;
    }

    public short getBorderLeft() {
        return _border_left.getShortValue(this.field_6_border_options);
    }

    public short getBorderRight() {
        return _border_right.getShortValue(this.field_6_border_options);
    }

    public short getBorderTop() {
        return _border_top.getShortValue(this.field_6_border_options);
    }

    public short getBorderBottom() {
        return _border_bottom.getShortValue(this.field_6_border_options);
    }

    public short getPaletteOptions() {
        return this.field_7_palette_options;
    }

    public short getLeftBorderPaletteIdx() {
        return _left_border_palette_idx.getShortValue(this.field_7_palette_options);
    }

    public short getRightBorderPaletteIdx() {
        return _right_border_palette_idx.getShortValue(this.field_7_palette_options);
    }

    public short getDiag() {
        return _diag.getShortValue(this.field_7_palette_options);
    }

    public int getAdtlPaletteOptions() {
        return this.field_8_adtl_palette_options;
    }

    public short getTopBorderPaletteIdx() {
        return (short)_top_border_palette_idx.getValue(this.field_8_adtl_palette_options);
    }

    public short getBottomBorderPaletteIdx() {
        return (short)_bottom_border_palette_idx.getValue(this.field_8_adtl_palette_options);
    }

    public short getAdtlDiag() {
        return (short)_adtl_diag.getValue(this.field_8_adtl_palette_options);
    }

    public short getAdtlDiagLineStyle() {
        return (short)_adtl_diag_line_style.getValue(this.field_8_adtl_palette_options);
    }

    public short getAdtlFillPattern() {
        return (short)_adtl_fill_pattern.getValue(this.field_8_adtl_palette_options);
    }

    public short getFillPaletteOptions() {
        return this.field_9_fill_palette_options;
    }

    public short getFillForeground() {
        return _fill_foreground.getShortValue(this.field_9_fill_palette_options);
    }

    public short getFillBackground() {
        return _fill_background.getShortValue(this.field_9_fill_palette_options);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[EXTENDEDFORMAT]\n");
        if (this.getXFType() == 1) {
            buffer.append(" STYLE_RECORD_TYPE\n");
        } else if (this.getXFType() == 0) {
            buffer.append(" CELL_RECORD_TYPE\n");
        }
        buffer.append("    .fontindex       = ").append(Integer.toHexString(this.getFontIndex())).append("\n");
        buffer.append("    .formatindex     = ").append(Integer.toHexString(this.getFormatIndex())).append("\n");
        buffer.append("    .celloptions     = ").append(Integer.toHexString(this.getCellOptions())).append("\n");
        buffer.append("          .islocked  = ").append(this.isLocked()).append("\n");
        buffer.append("          .ishidden  = ").append(this.isHidden()).append("\n");
        buffer.append("          .recordtype= ").append(Integer.toHexString(this.getXFType())).append("\n");
        buffer.append("          .parentidx = ").append(Integer.toHexString(this.getParentIndex())).append("\n");
        buffer.append("    .alignmentoptions= ").append(Integer.toHexString(this.getAlignmentOptions())).append("\n");
        buffer.append("          .alignment = ").append(this.getAlignment()).append("\n");
        buffer.append("          .wraptext  = ").append(this.getWrapText()).append("\n");
        buffer.append("          .valignment= ").append(Integer.toHexString(this.getVerticalAlignment())).append("\n");
        buffer.append("          .justlast  = ").append(Integer.toHexString(this.getJustifyLast())).append("\n");
        buffer.append("          .rotation  = ").append(Integer.toHexString(this.getRotation())).append("\n");
        buffer.append("    .indentionoptions= ").append(Integer.toHexString(this.getIndentionOptions())).append("\n");
        buffer.append("          .indent    = ").append(Integer.toHexString(this.getIndent())).append("\n");
        buffer.append("          .shrinktoft= ").append(this.getShrinkToFit()).append("\n");
        buffer.append("          .mergecells= ").append(this.getMergeCells()).append("\n");
        buffer.append("          .readngordr= ").append(Integer.toHexString(this.getReadingOrder())).append("\n");
        buffer.append("          .formatflag= ").append(this.isIndentNotParentFormat()).append("\n");
        buffer.append("          .fontflag  = ").append(this.isIndentNotParentFont()).append("\n");
        buffer.append("          .prntalgnmt= ").append(this.isIndentNotParentAlignment()).append("\n");
        buffer.append("          .borderflag= ").append(this.isIndentNotParentBorder()).append("\n");
        buffer.append("          .paternflag= ").append(this.isIndentNotParentPattern()).append("\n");
        buffer.append("          .celloption= ").append(this.isIndentNotParentCellOptions()).append("\n");
        buffer.append("    .borderoptns     = ").append(Integer.toHexString(this.getBorderOptions())).append("\n");
        buffer.append("          .lftln     = ").append(Integer.toHexString(this.getBorderLeft())).append("\n");
        buffer.append("          .rgtln     = ").append(Integer.toHexString(this.getBorderRight())).append("\n");
        buffer.append("          .topln     = ").append(Integer.toHexString(this.getBorderTop())).append("\n");
        buffer.append("          .btmln     = ").append(Integer.toHexString(this.getBorderBottom())).append("\n");
        buffer.append("    .paleteoptns     = ").append(Integer.toHexString(this.getPaletteOptions())).append("\n");
        buffer.append("          .leftborder= ").append(Integer.toHexString(this.getLeftBorderPaletteIdx())).append("\n");
        buffer.append("          .rghtborder= ").append(Integer.toHexString(this.getRightBorderPaletteIdx())).append("\n");
        buffer.append("          .diag      = ").append(Integer.toHexString(this.getDiag())).append("\n");
        buffer.append("    .paleteoptn2     = ").append(Integer.toHexString(this.getAdtlPaletteOptions())).append("\n");
        buffer.append("          .topborder = ").append(Integer.toHexString(this.getTopBorderPaletteIdx())).append("\n");
        buffer.append("          .botmborder= ").append(Integer.toHexString(this.getBottomBorderPaletteIdx())).append("\n");
        buffer.append("          .adtldiag  = ").append(Integer.toHexString(this.getAdtlDiag())).append("\n");
        buffer.append("          .diaglnstyl= ").append(Integer.toHexString(this.getAdtlDiagLineStyle())).append("\n");
        buffer.append("          .fillpattrn= ").append(Integer.toHexString(this.getAdtlFillPattern())).append("\n");
        buffer.append("    .fillpaloptn     = ").append(Integer.toHexString(this.getFillPaletteOptions())).append("\n");
        buffer.append("          .foreground= ").append(Integer.toHexString(this.getFillForeground())).append("\n");
        buffer.append("          .background= ").append(Integer.toHexString(this.getFillBackground())).append("\n");
        buffer.append("[/EXTENDEDFORMAT]\n");
        return buffer.toString();
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.getFontIndex());
        out.writeShort(this.getFormatIndex());
        out.writeShort(this.getCellOptions());
        out.writeShort(this.getAlignmentOptions());
        out.writeShort(this.getIndentionOptions());
        out.writeShort(this.getBorderOptions());
        out.writeShort(this.getPaletteOptions());
        out.writeInt(this.getAdtlPaletteOptions());
        out.writeShort(this.getFillPaletteOptions());
    }

    @Override
    protected int getDataSize() {
        return 20;
    }

    @Override
    public short getSid() {
        return 224;
    }

    public void cloneStyleFrom(ExtendedFormatRecord source) {
        this.field_1_font_index = source.field_1_font_index;
        this.field_2_format_index = source.field_2_format_index;
        this.field_3_cell_options = source.field_3_cell_options;
        this.field_4_alignment_options = source.field_4_alignment_options;
        this.field_5_indention_options = source.field_5_indention_options;
        this.field_6_border_options = source.field_6_border_options;
        this.field_7_palette_options = source.field_7_palette_options;
        this.field_8_adtl_palette_options = source.field_8_adtl_palette_options;
        this.field_9_fill_palette_options = source.field_9_fill_palette_options;
    }

    public int hashCode() {
        return Objects.hash(this.field_1_font_index, this.field_2_format_index, this.field_3_cell_options, this.field_4_alignment_options, this.field_5_indention_options, this.field_6_border_options, this.field_7_palette_options, this.field_8_adtl_palette_options, this.field_9_fill_palette_options);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof ExtendedFormatRecord) {
            ExtendedFormatRecord other = (ExtendedFormatRecord)obj;
            if (this.field_1_font_index != other.field_1_font_index) {
                return false;
            }
            if (this.field_2_format_index != other.field_2_format_index) {
                return false;
            }
            if (this.field_3_cell_options != other.field_3_cell_options) {
                return false;
            }
            if (this.field_4_alignment_options != other.field_4_alignment_options) {
                return false;
            }
            if (this.field_5_indention_options != other.field_5_indention_options) {
                return false;
            }
            if (this.field_6_border_options != other.field_6_border_options) {
                return false;
            }
            if (this.field_7_palette_options != other.field_7_palette_options) {
                return false;
            }
            if (this.field_8_adtl_palette_options != other.field_8_adtl_palette_options) {
                return false;
            }
            return this.field_9_fill_palette_options == other.field_9_fill_palette_options;
        }
        return false;
    }

    public int[] stateSummary() {
        return new int[]{this.field_1_font_index, this.field_2_format_index, this.field_3_cell_options, this.field_4_alignment_options, this.field_5_indention_options, this.field_6_border_options, this.field_7_palette_options, this.field_8_adtl_palette_options, this.field_9_fill_palette_options};
    }

    @Override
    public ExtendedFormatRecord copy() {
        return new ExtendedFormatRecord(this);
    }
}

