/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.Objects;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

public final class HSSFFont
implements Font {
    static final short BOLDWEIGHT_NORMAL = 400;
    static final short BOLDWEIGHT_BOLD = 700;
    public static final String FONT_ARIAL = "Arial";
    private FontRecord font;
    private int index;

    protected HSSFFont(int index, FontRecord rec) {
        this.font = rec;
        this.index = index;
    }

    @Override
    public void setFontName(String name) {
        this.font.setFontName(name);
    }

    @Override
    public String getFontName() {
        return this.font.getFontName();
    }

    @Override
    public short getIndex() {
        return (short)this.index;
    }

    @Override
    public int getIndexAsInt() {
        return this.index;
    }

    @Override
    public void setFontHeight(short height) {
        this.font.setFontHeight(height);
    }

    @Override
    public void setFontHeightInPoints(short height) {
        this.font.setFontHeight((short)(height * 20));
    }

    @Override
    public short getFontHeight() {
        return this.font.getFontHeight();
    }

    @Override
    public short getFontHeightInPoints() {
        return (short)(this.font.getFontHeight() / 20);
    }

    @Override
    public void setItalic(boolean italic) {
        this.font.setItalic(italic);
    }

    @Override
    public boolean getItalic() {
        return this.font.isItalic();
    }

    @Override
    public void setStrikeout(boolean strikeout) {
        this.font.setStrikeout(strikeout);
    }

    @Override
    public boolean getStrikeout() {
        return this.font.isStruckout();
    }

    @Override
    public void setColor(short color) {
        this.font.setColorPaletteIndex(color);
    }

    @Override
    public short getColor() {
        return this.font.getColorPaletteIndex();
    }

    public HSSFColor getHSSFColor(HSSFWorkbook wb) {
        HSSFPalette pallette = wb.getCustomPalette();
        return pallette.getColor(this.getColor());
    }

    @Override
    public void setBold(boolean bold) {
        if (bold) {
            this.font.setBoldWeight((short)700);
        } else {
            this.font.setBoldWeight((short)400);
        }
    }

    @Override
    public boolean getBold() {
        return this.font.getBoldWeight() == 700;
    }

    @Override
    public void setTypeOffset(short offset) {
        this.font.setSuperSubScript(offset);
    }

    @Override
    public short getTypeOffset() {
        return this.font.getSuperSubScript();
    }

    @Override
    public void setUnderline(byte underline) {
        this.font.setUnderline(underline);
    }

    @Override
    public byte getUnderline() {
        return this.font.getUnderline();
    }

    @Override
    public int getCharSet() {
        byte charset = this.font.getCharset();
        if (charset >= 0) {
            return charset;
        }
        return charset + 256;
    }

    @Override
    public void setCharSet(int charset) {
        byte cs = (byte)charset;
        if (charset > 127) {
            cs = (byte)(charset - 256);
        }
        this.setCharSet(cs);
    }

    @Override
    public void setCharSet(byte charset) {
        this.font.setCharset(charset);
    }

    public String toString() {
        return "org.apache.poi.hssf.usermodel.HSSFFont{" + this.font + "}";
    }

    public int hashCode() {
        return Objects.hash(this.font, this.index);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof HSSFFont) {
            HSSFFont other = (HSSFFont)obj;
            if (this.font == null ? other.font != null : !this.font.equals(other.font)) {
                return false;
            }
            return this.index == other.index;
        }
        return false;
    }
}

