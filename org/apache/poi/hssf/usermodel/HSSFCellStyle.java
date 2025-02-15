/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.List;
import java.util.Objects;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

public final class HSSFCellStyle
implements CellStyle,
Duplicatable {
    private final ExtendedFormatRecord _format;
    private final short _index;
    private final InternalWorkbook _workbook;
    private static final ThreadLocal<Short> lastDateFormat = new ThreadLocal<Short>(){

        @Override
        protected Short initialValue() {
            return (short)Short.MIN_VALUE;
        }
    };
    private static final ThreadLocal<List<FormatRecord>> lastFormats = new ThreadLocal();
    private static final ThreadLocal<String> getDataFormatStringCache = new ThreadLocal();

    protected HSSFCellStyle(short index, ExtendedFormatRecord rec, HSSFWorkbook workbook) {
        this(index, rec, workbook.getWorkbook());
    }

    protected HSSFCellStyle(short index, ExtendedFormatRecord rec, InternalWorkbook workbook) {
        this._workbook = workbook;
        this._index = index;
        this._format = rec;
    }

    protected HSSFCellStyle(HSSFCellStyle other) {
        this._workbook = other._workbook;
        this._index = other._index;
        this._format = other._format;
    }

    @Override
    public short getIndex() {
        return this._index;
    }

    public HSSFCellStyle getParentStyle() {
        short parentIndex = this._format.getParentIndex();
        if (parentIndex == 0 || parentIndex == 4095) {
            return null;
        }
        return new HSSFCellStyle(parentIndex, this._workbook.getExFormatAt(parentIndex), this._workbook);
    }

    @Override
    public void setDataFormat(short fmt) {
        this._format.setFormatIndex(fmt);
    }

    @Override
    public short getDataFormat() {
        return this._format.getFormatIndex();
    }

    @Override
    public String getDataFormatString() {
        if (getDataFormatStringCache.get() != null && lastDateFormat.get().shortValue() == this.getDataFormat() && this._workbook.getFormats().equals(lastFormats.get())) {
            return getDataFormatStringCache.get();
        }
        lastFormats.set(this._workbook.getFormats());
        lastDateFormat.set(this.getDataFormat());
        getDataFormatStringCache.set(this.getDataFormatString(this._workbook));
        return getDataFormatStringCache.get();
    }

    public String getDataFormatString(Workbook workbook) {
        HSSFDataFormat format = new HSSFDataFormat(((HSSFWorkbook)workbook).getWorkbook());
        short idx = this.getDataFormat();
        return idx == -1 ? "General" : format.getFormat(this.getDataFormat());
    }

    public String getDataFormatString(InternalWorkbook workbook) {
        HSSFDataFormat format = new HSSFDataFormat(workbook);
        return format.getFormat(this.getDataFormat());
    }

    @Override
    public void setFont(Font font) {
        this.setFont((HSSFFont)font);
    }

    public void setFont(HSSFFont font) {
        this._format.setIndentNotParentFont(true);
        short fontindex = font.getIndex();
        this._format.setFontIndex(fontindex);
    }

    @Override
    @Deprecated
    public short getFontIndex() {
        return this._format.getFontIndex();
    }

    @Override
    public int getFontIndexAsInt() {
        return this._format.getFontIndex();
    }

    public HSSFFont getFont(Workbook parentWorkbook) {
        return ((HSSFWorkbook)parentWorkbook).getFontAt(this.getFontIndexAsInt());
    }

    @Override
    public void setHidden(boolean hidden) {
        this._format.setIndentNotParentCellOptions(true);
        this._format.setHidden(hidden);
    }

    @Override
    public boolean getHidden() {
        return this._format.isHidden();
    }

    @Override
    public void setLocked(boolean locked) {
        this._format.setIndentNotParentCellOptions(true);
        this._format.setLocked(locked);
    }

    @Override
    public boolean getLocked() {
        return this._format.isLocked();
    }

    @Override
    public void setQuotePrefixed(boolean quotePrefix) {
        this._format.set123Prefix(quotePrefix);
    }

    @Override
    public boolean getQuotePrefixed() {
        return this._format.get123Prefix();
    }

    @Override
    public void setAlignment(HorizontalAlignment align) {
        this._format.setIndentNotParentAlignment(true);
        this._format.setAlignment(align.getCode());
    }

    @Override
    public HorizontalAlignment getAlignment() {
        return HorizontalAlignment.forInt(this._format.getAlignment());
    }

    @Override
    public HorizontalAlignment getAlignmentEnum() {
        return this.getAlignment();
    }

    @Override
    public void setWrapText(boolean wrapped) {
        this._format.setIndentNotParentAlignment(true);
        this._format.setWrapText(wrapped);
    }

    @Override
    public boolean getWrapText() {
        return this._format.getWrapText();
    }

    @Override
    public void setVerticalAlignment(VerticalAlignment align) {
        this._format.setVerticalAlignment(align.getCode());
    }

    @Override
    public VerticalAlignment getVerticalAlignment() {
        return VerticalAlignment.forInt(this._format.getVerticalAlignment());
    }

    @Override
    public VerticalAlignment getVerticalAlignmentEnum() {
        return this.getVerticalAlignment();
    }

    @Override
    public void setRotation(short rotation) {
        if (rotation != 255) {
            if (rotation < 0 && rotation >= -90) {
                rotation = (short)(90 - rotation);
            } else if (!(rotation > 90 && rotation <= 180 || rotation >= -90 && rotation <= 90)) {
                throw new IllegalArgumentException("The rotation must be between -90 and 90 degrees, or 0xff");
            }
        }
        this._format.setRotation(rotation);
    }

    @Override
    public short getRotation() {
        short rotation = this._format.getRotation();
        if (rotation == 255) {
            return rotation;
        }
        if (rotation > 90) {
            rotation = (short)(90 - rotation);
        }
        return rotation;
    }

    @Override
    public void setIndention(short indent) {
        this._format.setIndent(indent);
    }

    @Override
    public short getIndention() {
        return this._format.getIndent();
    }

    @Override
    public void setBorderLeft(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderLeft(border.getCode());
    }

    @Override
    public BorderStyle getBorderLeft() {
        return BorderStyle.valueOf(this._format.getBorderLeft());
    }

    @Override
    public BorderStyle getBorderLeftEnum() {
        return this.getBorderLeft();
    }

    @Override
    public void setBorderRight(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderRight(border.getCode());
    }

    @Override
    public BorderStyle getBorderRight() {
        return BorderStyle.valueOf(this._format.getBorderRight());
    }

    @Override
    public BorderStyle getBorderRightEnum() {
        return this.getBorderRight();
    }

    @Override
    public void setBorderTop(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderTop(border.getCode());
    }

    @Override
    public BorderStyle getBorderTop() {
        return BorderStyle.valueOf(this._format.getBorderTop());
    }

    @Override
    public BorderStyle getBorderTopEnum() {
        return this.getBorderTop();
    }

    @Override
    public void setBorderBottom(BorderStyle border) {
        this._format.setIndentNotParentBorder(true);
        this._format.setBorderBottom(border.getCode());
    }

    @Override
    public BorderStyle getBorderBottom() {
        return BorderStyle.valueOf(this._format.getBorderBottom());
    }

    @Override
    public BorderStyle getBorderBottomEnum() {
        return this.getBorderBottom();
    }

    @Override
    public void setLeftBorderColor(short color) {
        this._format.setLeftBorderPaletteIdx(color);
    }

    @Override
    public short getLeftBorderColor() {
        return this._format.getLeftBorderPaletteIdx();
    }

    @Override
    public void setRightBorderColor(short color) {
        this._format.setRightBorderPaletteIdx(color);
    }

    @Override
    public short getRightBorderColor() {
        return this._format.getRightBorderPaletteIdx();
    }

    @Override
    public void setTopBorderColor(short color) {
        this._format.setTopBorderPaletteIdx(color);
    }

    @Override
    public short getTopBorderColor() {
        return this._format.getTopBorderPaletteIdx();
    }

    @Override
    public void setBottomBorderColor(short color) {
        this._format.setBottomBorderPaletteIdx(color);
    }

    @Override
    public short getBottomBorderColor() {
        return this._format.getBottomBorderPaletteIdx();
    }

    @Override
    public void setFillPattern(FillPatternType fp) {
        this._format.setAdtlFillPattern(fp.getCode());
    }

    @Override
    public FillPatternType getFillPattern() {
        return FillPatternType.forInt(this._format.getAdtlFillPattern());
    }

    @Override
    public FillPatternType getFillPatternEnum() {
        return this.getFillPattern();
    }

    private void checkDefaultBackgroundFills() {
        short autoIdx = HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
        if (this._format.getFillForeground() == autoIdx) {
            if (this._format.getFillBackground() != autoIdx + 1) {
                this.setFillBackgroundColor((short)(autoIdx + 1));
            }
        } else if (this._format.getFillBackground() == autoIdx + 1 && this._format.getFillForeground() != autoIdx) {
            this.setFillBackgroundColor(autoIdx);
        }
    }

    @Override
    public void setFillBackgroundColor(short bg) {
        this._format.setFillBackground(bg);
        this.checkDefaultBackgroundFills();
    }

    @Override
    public short getFillBackgroundColor() {
        short autoIndex = HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
        short result = this._format.getFillBackground();
        if (result == autoIndex + 1) {
            return autoIndex;
        }
        return result;
    }

    @Override
    public HSSFColor getFillBackgroundColorColor() {
        HSSFPalette pallette = new HSSFPalette(this._workbook.getCustomPalette());
        return pallette.getColor(this.getFillBackgroundColor());
    }

    @Override
    public void setFillForegroundColor(short bg) {
        this._format.setFillForeground(bg);
        this.checkDefaultBackgroundFills();
    }

    @Override
    public short getFillForegroundColor() {
        return this._format.getFillForeground();
    }

    @Override
    public HSSFColor getFillForegroundColorColor() {
        HSSFPalette pallette = new HSSFPalette(this._workbook.getCustomPalette());
        return pallette.getColor(this.getFillForegroundColor());
    }

    public String getUserStyleName() {
        StyleRecord sr = this._workbook.getStyleRecord(this._index);
        if (sr == null) {
            return null;
        }
        if (sr.isBuiltin()) {
            return null;
        }
        return sr.getName();
    }

    public void setUserStyleName(String styleName) {
        StyleRecord sr = this._workbook.getStyleRecord(this._index);
        if (sr == null) {
            sr = this._workbook.createStyleRecord(this._index);
        }
        if (sr.isBuiltin() && this._index <= 20) {
            throw new IllegalArgumentException("Unable to set user specified style names for built in styles!");
        }
        sr.setName(styleName);
    }

    @Override
    public void setShrinkToFit(boolean shrinkToFit) {
        this._format.setShrinkToFit(shrinkToFit);
    }

    @Override
    public boolean getShrinkToFit() {
        return this._format.getShrinkToFit();
    }

    public short getReadingOrder() {
        return this._format.getReadingOrder();
    }

    public void setReadingOrder(short order) {
        this._format.setReadingOrder(order);
    }

    public void verifyBelongsToWorkbook(HSSFWorkbook wb) {
        if (wb.getWorkbook() != this._workbook) {
            throw new IllegalArgumentException("This Style does not belong to the supplied Workbook. Are you trying to assign a style from one workbook to the cell of a differnt workbook?");
        }
    }

    @Override
    public void cloneStyleFrom(CellStyle source) {
        if (!(source instanceof HSSFCellStyle)) {
            throw new IllegalArgumentException("Can only clone from one HSSFCellStyle to another, not between HSSFCellStyle and XSSFCellStyle");
        }
        this.cloneStyleFrom((HSSFCellStyle)source);
    }

    public void cloneStyleFrom(HSSFCellStyle source) {
        this._format.cloneStyleFrom(source._format);
        if (this._workbook != source._workbook) {
            lastDateFormat.set((short)Short.MIN_VALUE);
            lastFormats.remove();
            getDataFormatStringCache.remove();
            short fmt = (short)this._workbook.createFormat(source.getDataFormatString());
            this.setDataFormat(fmt);
            FontRecord fr = this._workbook.createNewFont();
            fr.cloneStyleFrom(source._workbook.getFontRecordAt(source.getFontIndexAsInt()));
            HSSFFont font = new HSSFFont((short)this._workbook.getFontIndex(fr), fr);
            this.setFont(font);
        }
    }

    public int hashCode() {
        return Objects.hash(this._format, this._index);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof HSSFCellStyle) {
            HSSFCellStyle other = (HSSFCellStyle)obj;
            if (this._format == null ? other._format != null : !this._format.equals(other._format)) {
                return false;
            }
            return this._index == other._index;
        }
        return false;
    }

    @Override
    public HSSFCellStyle copy() {
        return new HSSFCellStyle(this);
    }
}

