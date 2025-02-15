/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.cf;

import java.util.Locale;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.Removal;

public final class FontFormatting
implements Duplicatable {
    private static final int OFFSET_FONT_NAME = 0;
    private static final int OFFSET_FONT_HEIGHT = 64;
    private static final int OFFSET_FONT_OPTIONS = 68;
    private static final int OFFSET_FONT_WEIGHT = 72;
    private static final int OFFSET_ESCAPEMENT_TYPE = 74;
    private static final int OFFSET_UNDERLINE_TYPE = 76;
    private static final int OFFSET_FONT_COLOR_INDEX = 80;
    private static final int OFFSET_OPTION_FLAGS = 88;
    private static final int OFFSET_ESCAPEMENT_TYPE_MODIFIED = 92;
    private static final int OFFSET_UNDERLINE_TYPE_MODIFIED = 96;
    private static final int OFFSET_FONT_WEIGHT_MODIFIED = 100;
    private static final int OFFSET_NOT_USED1 = 104;
    private static final int OFFSET_NOT_USED2 = 108;
    private static final int OFFSET_NOT_USED3 = 112;
    private static final int OFFSET_FONT_FORMATING_END = 116;
    private static final int RAW_DATA_SIZE = 118;
    public static final int FONT_CELL_HEIGHT_PRESERVED = -1;
    private static final BitField posture = BitFieldFactory.getInstance(2);
    private static final BitField outline = BitFieldFactory.getInstance(8);
    private static final BitField shadow = BitFieldFactory.getInstance(16);
    private static final BitField cancellation = BitFieldFactory.getInstance(128);
    private static final BitField styleModified = BitFieldFactory.getInstance(2);
    private static final BitField outlineModified = BitFieldFactory.getInstance(8);
    private static final BitField shadowModified = BitFieldFactory.getInstance(16);
    private static final BitField cancellationModified = BitFieldFactory.getInstance(128);
    public static final short SS_NONE = 0;
    public static final short SS_SUPER = 1;
    public static final short SS_SUB = 2;
    public static final byte U_NONE = 0;
    public static final byte U_SINGLE = 1;
    public static final byte U_DOUBLE = 2;
    public static final byte U_SINGLE_ACCOUNTING = 33;
    public static final byte U_DOUBLE_ACCOUNTING = 34;
    private static final short FONT_WEIGHT_NORMAL = 400;
    private static final short FONT_WEIGHT_BOLD = 700;
    private final byte[] _rawData = new byte[118];

    public FontFormatting() {
        this.setFontHeight(-1);
        this.setItalic(false);
        this.setFontWieghtModified(false);
        this.setOutline(false);
        this.setShadow(false);
        this.setStrikeout(false);
        this.setEscapementType((short)0);
        this.setUnderlineType((short)0);
        this.setFontColorIndex((short)-1);
        this.setFontStyleModified(false);
        this.setFontOutlineModified(false);
        this.setFontShadowModified(false);
        this.setFontCancellationModified(false);
        this.setEscapementTypeModified(false);
        this.setUnderlineTypeModified(false);
        this.setShort(0, 0);
        this.setInt(104, 1);
        this.setInt(108, 0);
        this.setInt(112, Integer.MAX_VALUE);
        this.setShort(116, 1);
    }

    public FontFormatting(FontFormatting other) {
        System.arraycopy(other._rawData, 0, this._rawData, 0, this._rawData.length);
    }

    public FontFormatting(RecordInputStream in) {
        in.readFully(this._rawData);
    }

    private short getShort(int offset) {
        return LittleEndian.getShort(this._rawData, offset);
    }

    private void setShort(int offset, int value) {
        LittleEndian.putShort(this._rawData, offset, (short)value);
    }

    private int getInt(int offset) {
        return LittleEndian.getInt(this._rawData, offset);
    }

    private void setInt(int offset, int value) {
        LittleEndian.putInt(this._rawData, offset, value);
    }

    public byte[] getRawRecord() {
        return this._rawData;
    }

    public int getDataLength() {
        return 118;
    }

    public void setFontHeight(int height) {
        this.setInt(64, height);
    }

    public int getFontHeight() {
        return this.getInt(64);
    }

    private void setFontOption(boolean option, BitField field) {
        int options = this.getInt(68);
        options = field.setBoolean(options, option);
        this.setInt(68, options);
    }

    private boolean getFontOption(BitField field) {
        int options = this.getInt(68);
        return field.isSet(options);
    }

    public void setItalic(boolean italic) {
        this.setFontOption(italic, posture);
    }

    public boolean isItalic() {
        return this.getFontOption(posture);
    }

    public void setOutline(boolean on) {
        this.setFontOption(on, outline);
    }

    public boolean isOutlineOn() {
        return this.getFontOption(outline);
    }

    public void setShadow(boolean on) {
        this.setFontOption(on, shadow);
    }

    public boolean isShadowOn() {
        return this.getFontOption(shadow);
    }

    public void setStrikeout(boolean strike) {
        this.setFontOption(strike, cancellation);
    }

    public boolean isStruckout() {
        return this.getFontOption(cancellation);
    }

    private void setFontWeight(short pbw) {
        int bw = pbw;
        if (bw < 100) {
            bw = 100;
        }
        if (bw > 1000) {
            bw = 1000;
        }
        this.setShort(72, bw);
    }

    public void setBold(boolean bold) {
        this.setFontWeight(bold ? (short)700 : 400);
    }

    public short getFontWeight() {
        return this.getShort(72);
    }

    public boolean isBold() {
        return this.getFontWeight() == 700;
    }

    public short getEscapementType() {
        return this.getShort(74);
    }

    public void setEscapementType(short escapementType) {
        this.setShort(74, escapementType);
    }

    public short getUnderlineType() {
        return this.getShort(76);
    }

    public void setUnderlineType(short underlineType) {
        this.setShort(76, underlineType);
    }

    public short getFontColorIndex() {
        return (short)this.getInt(80);
    }

    public void setFontColorIndex(short fci) {
        this.setInt(80, fci);
    }

    private boolean getOptionFlag(BitField field) {
        int optionFlags = this.getInt(88);
        int value = field.getValue(optionFlags);
        return value == 0;
    }

    private void setOptionFlag(boolean modified, BitField field) {
        int value = modified ? 0 : 1;
        int optionFlags = this.getInt(88);
        optionFlags = field.setValue(optionFlags, value);
        this.setInt(88, optionFlags);
    }

    public boolean isFontStyleModified() {
        return this.getOptionFlag(styleModified);
    }

    public void setFontStyleModified(boolean modified) {
        this.setOptionFlag(modified, styleModified);
    }

    public boolean isFontOutlineModified() {
        return this.getOptionFlag(outlineModified);
    }

    public void setFontOutlineModified(boolean modified) {
        this.setOptionFlag(modified, outlineModified);
    }

    public boolean isFontShadowModified() {
        return this.getOptionFlag(shadowModified);
    }

    public void setFontShadowModified(boolean modified) {
        this.setOptionFlag(modified, shadowModified);
    }

    public void setFontCancellationModified(boolean modified) {
        this.setOptionFlag(modified, cancellationModified);
    }

    public boolean isFontCancellationModified() {
        return this.getOptionFlag(cancellationModified);
    }

    public void setEscapementTypeModified(boolean modified) {
        int value = modified ? 0 : 1;
        this.setInt(92, value);
    }

    public boolean isEscapementTypeModified() {
        int escapementModified = this.getInt(92);
        return escapementModified == 0;
    }

    public void setUnderlineTypeModified(boolean modified) {
        int value = modified ? 0 : 1;
        this.setInt(96, value);
    }

    public boolean isUnderlineTypeModified() {
        int underlineModified = this.getInt(96);
        return underlineModified == 0;
    }

    public void setFontWieghtModified(boolean modified) {
        int value = modified ? 0 : 1;
        this.setInt(100, value);
    }

    public boolean isFontWeightModified() {
        int fontStyleModified = this.getInt(100);
        return fontStyleModified == 0;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("    [Font Formatting]\n");
        buffer.append("\t.font height = ").append(this.getFontHeight()).append(" twips\n");
        if (this.isFontStyleModified()) {
            buffer.append("\t.font posture = ").append(this.isItalic() ? "Italic" : "Normal").append("\n");
        } else {
            buffer.append("\t.font posture = ]not modified]").append("\n");
        }
        if (this.isFontOutlineModified()) {
            buffer.append("\t.font outline = ").append(this.isOutlineOn()).append("\n");
        } else {
            buffer.append("\t.font outline is not modified\n");
        }
        if (this.isFontShadowModified()) {
            buffer.append("\t.font shadow = ").append(this.isShadowOn()).append("\n");
        } else {
            buffer.append("\t.font shadow is not modified\n");
        }
        if (this.isFontCancellationModified()) {
            buffer.append("\t.font strikeout = ").append(this.isStruckout()).append("\n");
        } else {
            buffer.append("\t.font strikeout is not modified\n");
        }
        if (this.isFontStyleModified()) {
            buffer.append("\t.font weight = ").append(this.getFontWeight()).append(this.getFontWeight() == 400 ? "(Normal)" : (this.getFontWeight() == 700 ? "(Bold)" : "0x" + Integer.toHexString(this.getFontWeight()))).append("\n");
        } else {
            buffer.append("\t.font weight = ]not modified]").append("\n");
        }
        if (this.isEscapementTypeModified()) {
            buffer.append("\t.escapement type = ").append(this.getEscapementType()).append("\n");
        } else {
            buffer.append("\t.escapement type is not modified\n");
        }
        if (this.isUnderlineTypeModified()) {
            buffer.append("\t.underline type = ").append(this.getUnderlineType()).append("\n");
        } else {
            buffer.append("\t.underline type is not modified\n");
        }
        buffer.append("\t.color index = ").append("0x").append(Integer.toHexString(this.getFontColorIndex()).toUpperCase(Locale.ROOT)).append('\n');
        buffer.append("    [/Font Formatting]\n");
        return buffer.toString();
    }

    @Deprecated
    @Removal(version="5.0.0")
    public FontFormatting clone() {
        return this.copy();
    }

    @Override
    public FontFormatting copy() {
        return new FontFormatting(this);
    }
}

