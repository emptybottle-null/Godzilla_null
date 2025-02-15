/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.ReadingOrder;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellAlignment;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

public class XSSFCellStyle
implements CellStyle,
Duplicatable {
    private int _cellXfId;
    private final StylesTable _stylesSource;
    private CTXf _cellXf;
    private final CTXf _cellStyleXf;
    private XSSFFont _font;
    private XSSFCellAlignment _cellAlignment;
    private ThemesTable _theme;

    public XSSFCellStyle(int cellXfId, int cellStyleXfId, StylesTable stylesSource, ThemesTable theme) {
        this._cellXfId = cellXfId;
        this._stylesSource = stylesSource;
        this._cellXf = stylesSource.getCellXfAt(this._cellXfId);
        this._cellStyleXf = cellStyleXfId == -1 ? null : stylesSource.getCellStyleXfAt(cellStyleXfId);
        this._theme = theme;
    }

    @Internal
    public CTXf getCoreXf() {
        return this._cellXf;
    }

    @Internal
    public CTXf getStyleXf() {
        return this._cellStyleXf;
    }

    public XSSFCellStyle(StylesTable stylesSource) {
        this._stylesSource = stylesSource;
        this._cellXf = CTXf.Factory.newInstance();
        this._cellStyleXf = null;
    }

    public void verifyBelongsToStylesSource(StylesTable src) {
        if (this._stylesSource != src) {
            throw new IllegalArgumentException("This Style does not belong to the supplied Workbook Styles Source. Are you trying to assign a style from one workbook to the cell of a different workbook?");
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void cloneStyleFrom(CellStyle source) {
        if (!(source instanceof XSSFCellStyle)) throw new IllegalArgumentException("Can only clone from one XSSFCellStyle to another, not between HSSFCellStyle and XSSFCellStyle");
        XSSFCellStyle src = (XSSFCellStyle)source;
        if (src._stylesSource == this._stylesSource) {
            this._cellXf.set(src.getCoreXf());
            this._cellStyleXf.set(src.getStyleXf());
        } else {
            try {
                if (this._cellXf.isSetAlignment()) {
                    this._cellXf.unsetAlignment();
                }
                if (this._cellXf.isSetExtLst()) {
                    this._cellXf.unsetExtLst();
                }
                this._cellXf = CTXf.Factory.parse(src.getCoreXf().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                CTFill fill = CTFill.Factory.parse(src.getCTFill().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.addFill(fill);
                CTBorder border = CTBorder.Factory.parse(src.getCTBorder().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                this.addBorder(border);
                this._stylesSource.replaceCellXfAt(this._cellXfId, this._cellXf);
            } catch (XmlException e) {
                throw new POIXMLException(e);
            }
            String fmt = src.getDataFormatString();
            this.setDataFormat(new XSSFDataFormat(this._stylesSource).getFormat(fmt));
            try {
                CTFont ctFont = CTFont.Factory.parse(src.getFont().getCTFont().toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                XSSFFont font = new XSSFFont(ctFont);
                font.registerTo(this._stylesSource);
                this.setFont(font);
            } catch (XmlException e) {
                throw new POIXMLException(e);
            }
        }
        this._font = null;
        this._cellAlignment = null;
    }

    private void addFill(CTFill fill) {
        int idx = this._stylesSource.putFill(new XSSFCellFill(fill, this._stylesSource.getIndexedColors()));
        this._cellXf.setFillId(idx);
        this._cellXf.setApplyFill(true);
    }

    private void addBorder(CTBorder border) {
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(border, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public HorizontalAlignment getAlignment() {
        CTCellAlignment align = this._cellXf.getAlignment();
        if (align != null && align.isSetHorizontal()) {
            return HorizontalAlignment.forInt(align.getHorizontal().intValue() - 1);
        }
        return HorizontalAlignment.GENERAL;
    }

    @Override
    public HorizontalAlignment getAlignmentEnum() {
        return this.getAlignment();
    }

    @Override
    public BorderStyle getBorderBottom() {
        STBorderStyle.Enum ptrn;
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        CTBorder ct = this._stylesSource.getBorderAt(idx).getCTBorder();
        STBorderStyle.Enum enum_ = ptrn = ct.isSetBottom() ? ct.getBottom().getStyle() : null;
        if (ptrn == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short)(ptrn.intValue() - 1));
    }

    @Override
    public BorderStyle getBorderBottomEnum() {
        return this.getBorderBottom();
    }

    @Override
    public BorderStyle getBorderLeft() {
        STBorderStyle.Enum ptrn;
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        CTBorder ct = this._stylesSource.getBorderAt(idx).getCTBorder();
        STBorderStyle.Enum enum_ = ptrn = ct.isSetLeft() ? ct.getLeft().getStyle() : null;
        if (ptrn == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short)(ptrn.intValue() - 1));
    }

    @Override
    public BorderStyle getBorderLeftEnum() {
        return this.getBorderLeft();
    }

    @Override
    public BorderStyle getBorderRight() {
        STBorderStyle.Enum ptrn;
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        CTBorder ct = this._stylesSource.getBorderAt(idx).getCTBorder();
        STBorderStyle.Enum enum_ = ptrn = ct.isSetRight() ? ct.getRight().getStyle() : null;
        if (ptrn == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short)(ptrn.intValue() - 1));
    }

    @Override
    public BorderStyle getBorderRightEnum() {
        return this.getBorderRight();
    }

    @Override
    public BorderStyle getBorderTop() {
        STBorderStyle.Enum ptrn;
        if (!this._cellXf.getApplyBorder()) {
            return BorderStyle.NONE;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        CTBorder ct = this._stylesSource.getBorderAt(idx).getCTBorder();
        STBorderStyle.Enum enum_ = ptrn = ct.isSetTop() ? ct.getTop().getStyle() : null;
        if (ptrn == null) {
            return BorderStyle.NONE;
        }
        return BorderStyle.valueOf((short)(ptrn.intValue() - 1));
    }

    @Override
    public BorderStyle getBorderTopEnum() {
        return this.getBorderTop();
    }

    @Override
    public short getBottomBorderColor() {
        XSSFColor clr = this.getBottomBorderXSSFColor();
        return clr == null ? IndexedColors.BLACK.getIndex() : clr.getIndexed();
    }

    public XSSFColor getBottomBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        XSSFCellBorder border = this._stylesSource.getBorderAt(idx);
        return border.getBorderColor(XSSFCellBorder.BorderSide.BOTTOM);
    }

    @Override
    public short getDataFormat() {
        return (short)this._cellXf.getNumFmtId();
    }

    @Override
    public String getDataFormatString() {
        short idx = this.getDataFormat();
        return new XSSFDataFormat(this._stylesSource).getFormat(idx);
    }

    @Override
    public short getFillBackgroundColor() {
        XSSFColor clr = this.getFillBackgroundXSSFColor();
        return clr == null ? IndexedColors.AUTOMATIC.getIndex() : clr.getIndexed();
    }

    @Override
    public XSSFColor getFillBackgroundColorColor() {
        return this.getFillBackgroundXSSFColor();
    }

    public XSSFColor getFillBackgroundXSSFColor() {
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return null;
        }
        int fillIndex = (int)this._cellXf.getFillId();
        XSSFCellFill fg = this._stylesSource.getFillAt(fillIndex);
        XSSFColor fillBackgroundColor = fg.getFillBackgroundColor();
        if (fillBackgroundColor != null && this._theme != null) {
            this._theme.inheritFromThemeAsRequired(fillBackgroundColor);
        }
        return fillBackgroundColor;
    }

    @Override
    public short getFillForegroundColor() {
        XSSFColor clr = this.getFillForegroundXSSFColor();
        return clr == null ? IndexedColors.AUTOMATIC.getIndex() : clr.getIndexed();
    }

    @Override
    public XSSFColor getFillForegroundColorColor() {
        return this.getFillForegroundXSSFColor();
    }

    public XSSFColor getFillForegroundXSSFColor() {
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return null;
        }
        int fillIndex = (int)this._cellXf.getFillId();
        XSSFCellFill fg = this._stylesSource.getFillAt(fillIndex);
        XSSFColor fillForegroundColor = fg.getFillForegroundColor();
        if (fillForegroundColor != null && this._theme != null) {
            this._theme.inheritFromThemeAsRequired(fillForegroundColor);
        }
        return fillForegroundColor;
    }

    @Override
    public FillPatternType getFillPattern() {
        if (this._cellXf.isSetApplyFill() && !this._cellXf.getApplyFill()) {
            return FillPatternType.NO_FILL;
        }
        int fillIndex = (int)this._cellXf.getFillId();
        XSSFCellFill fill = this._stylesSource.getFillAt(fillIndex);
        STPatternType.Enum ptrn = fill.getPatternType();
        if (ptrn == null) {
            return FillPatternType.NO_FILL;
        }
        return FillPatternType.forInt(ptrn.intValue() - 1);
    }

    @Override
    public FillPatternType getFillPatternEnum() {
        return this.getFillPattern();
    }

    public XSSFFont getFont() {
        if (this._font == null) {
            this._font = this._stylesSource.getFontAt(this.getFontId());
        }
        return this._font;
    }

    @Override
    @Deprecated
    public short getFontIndex() {
        return (short)this.getFontId();
    }

    @Override
    public int getFontIndexAsInt() {
        return this.getFontId();
    }

    @Override
    public boolean getHidden() {
        return this._cellXf.isSetProtection() && this._cellXf.getProtection().isSetHidden() && this._cellXf.getProtection().getHidden();
    }

    @Override
    public short getIndention() {
        CTCellAlignment align = this._cellXf.getAlignment();
        return (short)(align == null ? 0L : align.getIndent());
    }

    @Override
    public short getIndex() {
        return (short)this._cellXfId;
    }

    protected int getUIndex() {
        return this._cellXfId;
    }

    @Override
    public short getLeftBorderColor() {
        XSSFColor clr = this.getLeftBorderXSSFColor();
        return clr == null ? IndexedColors.BLACK.getIndex() : clr.getIndexed();
    }

    public XSSFColor getLeftBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        XSSFCellBorder border = this._stylesSource.getBorderAt(idx);
        return border.getBorderColor(XSSFCellBorder.BorderSide.LEFT);
    }

    @Override
    public boolean getLocked() {
        return !this._cellXf.isSetProtection() || !this._cellXf.getProtection().isSetLocked() || this._cellXf.getProtection().getLocked();
    }

    @Override
    public boolean getQuotePrefixed() {
        return this._cellXf.getQuotePrefix();
    }

    @Override
    public short getRightBorderColor() {
        XSSFColor clr = this.getRightBorderXSSFColor();
        return clr == null ? IndexedColors.BLACK.getIndex() : clr.getIndexed();
    }

    public XSSFColor getRightBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        XSSFCellBorder border = this._stylesSource.getBorderAt(idx);
        return border.getBorderColor(XSSFCellBorder.BorderSide.RIGHT);
    }

    @Override
    public short getRotation() {
        CTCellAlignment align = this._cellXf.getAlignment();
        return (short)(align == null ? 0L : align.getTextRotation());
    }

    @Override
    public boolean getShrinkToFit() {
        CTCellAlignment align = this._cellXf.getAlignment();
        return align != null && align.getShrinkToFit();
    }

    @Override
    public short getTopBorderColor() {
        XSSFColor clr = this.getTopBorderXSSFColor();
        return clr == null ? IndexedColors.BLACK.getIndex() : clr.getIndexed();
    }

    public XSSFColor getTopBorderXSSFColor() {
        if (!this._cellXf.getApplyBorder()) {
            return null;
        }
        int idx = Math.toIntExact(this._cellXf.getBorderId());
        XSSFCellBorder border = this._stylesSource.getBorderAt(idx);
        return border.getBorderColor(XSSFCellBorder.BorderSide.TOP);
    }

    @Override
    public VerticalAlignment getVerticalAlignment() {
        CTCellAlignment align = this._cellXf.getAlignment();
        if (align != null && align.isSetVertical()) {
            return VerticalAlignment.forInt(align.getVertical().intValue() - 1);
        }
        return VerticalAlignment.BOTTOM;
    }

    @Override
    public VerticalAlignment getVerticalAlignmentEnum() {
        return this.getVerticalAlignment();
    }

    @Override
    public boolean getWrapText() {
        CTCellAlignment align = this._cellXf.getAlignment();
        return align != null && align.getWrapText();
    }

    @Override
    public void setAlignment(HorizontalAlignment align) {
        this.getCellAlignment().setHorizontal(align);
    }

    @Override
    public void setBorderBottom(BorderStyle border) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        CTBorderPr cTBorderPr = pr = ct.isSetBottom() ? ct.getBottom() : ct.addNewBottom();
        if (border == BorderStyle.NONE) {
            ct.unsetBottom();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setBorderLeft(BorderStyle border) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        CTBorderPr cTBorderPr = pr = ct.isSetLeft() ? ct.getLeft() : ct.addNewLeft();
        if (border == BorderStyle.NONE) {
            ct.unsetLeft();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setBorderRight(BorderStyle border) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        CTBorderPr cTBorderPr = pr = ct.isSetRight() ? ct.getRight() : ct.addNewRight();
        if (border == BorderStyle.NONE) {
            ct.unsetRight();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setBorderTop(BorderStyle border) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        CTBorderPr cTBorderPr = pr = ct.isSetTop() ? ct.getTop() : ct.addNewTop();
        if (border == BorderStyle.NONE) {
            ct.unsetTop();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setBottomBorderColor(short color) {
        XSSFColor clr = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        clr.setIndexed(color);
        this.setBottomBorderColor(clr);
    }

    public void setBottomBorderColor(XSSFColor color) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        if (color == null && !ct.isSetBottom()) {
            return;
        }
        CTBorderPr cTBorderPr = pr = ct.isSetBottom() ? ct.getBottom() : ct.addNewBottom();
        if (color != null) {
            pr.setColor(color.getCTColor());
        } else {
            pr.unsetColor();
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setDataFormat(short fmt) {
        this.setDataFormat(fmt & 0xFFFF);
    }

    public void setDataFormat(int fmt) {
        this._cellXf.setApplyNumberFormat(true);
        this._cellXf.setNumFmtId(fmt);
    }

    public void setFillBackgroundColor(XSSFColor color) {
        CTFill ct = this.getCTFill();
        CTPatternFill ptrn = ct.getPatternFill();
        if (color == null) {
            if (ptrn != null && ptrn.isSetBgColor()) {
                ptrn.unsetBgColor();
            }
        } else {
            if (ptrn == null) {
                ptrn = ct.addNewPatternFill();
            }
            ptrn.setBgColor(color.getCTColor());
        }
        this.addFill(ct);
    }

    @Override
    public void setFillBackgroundColor(short bg) {
        XSSFColor clr = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        clr.setIndexed(bg);
        this.setFillBackgroundColor(clr);
    }

    public void setFillForegroundColor(XSSFColor color) {
        CTFill ct = this.getCTFill();
        CTPatternFill ptrn = ct.getPatternFill();
        if (color == null) {
            if (ptrn != null && ptrn.isSetFgColor()) {
                ptrn.unsetFgColor();
            }
        } else {
            if (ptrn == null) {
                ptrn = ct.addNewPatternFill();
            }
            ptrn.setFgColor(color.getCTColor());
        }
        this.addFill(ct);
    }

    @Override
    public void setFillForegroundColor(short fg) {
        XSSFColor clr = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        clr.setIndexed(fg);
        this.setFillForegroundColor(clr);
    }

    private CTFill getCTFill() {
        CTFill ct;
        if (!this._cellXf.isSetApplyFill() || this._cellXf.getApplyFill()) {
            int fillIndex = (int)this._cellXf.getFillId();
            XSSFCellFill cf = this._stylesSource.getFillAt(fillIndex);
            ct = (CTFill)cf.getCTFill().copy();
        } else {
            ct = CTFill.Factory.newInstance();
        }
        return ct;
    }

    public void setReadingOrder(ReadingOrder order) {
        this.getCellAlignment().setReadingOrder(order);
    }

    public ReadingOrder getReadingOrder() {
        return this.getCellAlignment().getReadingOrder();
    }

    private CTBorder getCTBorder() {
        CTBorder ct;
        if (this._cellXf.getApplyBorder()) {
            int idx = Math.toIntExact(this._cellXf.getBorderId());
            XSSFCellBorder cf = this._stylesSource.getBorderAt(idx);
            ct = (CTBorder)cf.getCTBorder().copy();
        } else {
            ct = CTBorder.Factory.newInstance();
        }
        return ct;
    }

    @Override
    public void setFillPattern(FillPatternType pattern) {
        CTPatternFill ctptrn;
        CTFill ct = this.getCTFill();
        CTPatternFill cTPatternFill = ctptrn = ct.isSetPatternFill() ? ct.getPatternFill() : ct.addNewPatternFill();
        if (pattern == FillPatternType.NO_FILL && ctptrn.isSetPatternType()) {
            ctptrn.unsetPatternType();
        } else {
            ctptrn.setPatternType(STPatternType.Enum.forInt(pattern.getCode() + 1));
        }
        this.addFill(ct);
    }

    @Override
    public void setFont(Font font) {
        if (font != null) {
            long index = font.getIndexAsInt();
            this._cellXf.setFontId(index);
            this._cellXf.setApplyFont(true);
        } else {
            this._cellXf.setApplyFont(false);
        }
    }

    @Override
    public void setHidden(boolean hidden) {
        if (!this._cellXf.isSetProtection()) {
            this._cellXf.addNewProtection();
        }
        this._cellXf.getProtection().setHidden(hidden);
    }

    @Override
    public void setIndention(short indent) {
        this.getCellAlignment().setIndent(indent);
    }

    @Override
    public void setLeftBorderColor(short color) {
        XSSFColor clr = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        clr.setIndexed(color);
        this.setLeftBorderColor(clr);
    }

    public void setLeftBorderColor(XSSFColor color) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        if (color == null && !ct.isSetLeft()) {
            return;
        }
        CTBorderPr cTBorderPr = pr = ct.isSetLeft() ? ct.getLeft() : ct.addNewLeft();
        if (color != null) {
            pr.setColor(color.getCTColor());
        } else {
            pr.unsetColor();
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setLocked(boolean locked) {
        if (!this._cellXf.isSetProtection()) {
            this._cellXf.addNewProtection();
        }
        this._cellXf.getProtection().setLocked(locked);
    }

    @Override
    public void setQuotePrefixed(boolean quotePrefix) {
        this._cellXf.setQuotePrefix(quotePrefix);
    }

    @Override
    public void setRightBorderColor(short color) {
        XSSFColor clr = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        clr.setIndexed(color);
        this.setRightBorderColor(clr);
    }

    public void setRightBorderColor(XSSFColor color) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        if (color == null && !ct.isSetRight()) {
            return;
        }
        CTBorderPr cTBorderPr = pr = ct.isSetRight() ? ct.getRight() : ct.addNewRight();
        if (color != null) {
            pr.setColor(color.getCTColor());
        } else {
            pr.unsetColor();
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setRotation(short rotation) {
        this.getCellAlignment().setTextRotation(rotation);
    }

    @Override
    public void setTopBorderColor(short color) {
        XSSFColor clr = XSSFColor.from(CTColor.Factory.newInstance(), this._stylesSource.getIndexedColors());
        clr.setIndexed(color);
        this.setTopBorderColor(clr);
    }

    public void setTopBorderColor(XSSFColor color) {
        CTBorderPr pr;
        CTBorder ct = this.getCTBorder();
        if (color == null && !ct.isSetTop()) {
            return;
        }
        CTBorderPr cTBorderPr = pr = ct.isSetTop() ? ct.getTop() : ct.addNewTop();
        if (color != null) {
            pr.setColor(color.getCTColor());
        } else {
            pr.unsetColor();
        }
        int idx = this._stylesSource.putBorder(new XSSFCellBorder(ct, this._theme, this._stylesSource.getIndexedColors()));
        this._cellXf.setBorderId(idx);
        this._cellXf.setApplyBorder(true);
    }

    @Override
    public void setVerticalAlignment(VerticalAlignment align) {
        this.getCellAlignment().setVertical(align);
    }

    @Override
    public void setWrapText(boolean wrapped) {
        this.getCellAlignment().setWrapText(wrapped);
    }

    public XSSFColor getBorderColor(XSSFCellBorder.BorderSide side) {
        switch (side) {
            case BOTTOM: {
                return this.getBottomBorderXSSFColor();
            }
            case RIGHT: {
                return this.getRightBorderXSSFColor();
            }
            case TOP: {
                return this.getTopBorderXSSFColor();
            }
            case LEFT: {
                return this.getLeftBorderXSSFColor();
            }
        }
        throw new IllegalArgumentException("Unknown border: " + (Object)((Object)side));
    }

    public void setBorderColor(XSSFCellBorder.BorderSide side, XSSFColor color) {
        switch (side) {
            case BOTTOM: {
                this.setBottomBorderColor(color);
                break;
            }
            case RIGHT: {
                this.setRightBorderColor(color);
                break;
            }
            case TOP: {
                this.setTopBorderColor(color);
                break;
            }
            case LEFT: {
                this.setLeftBorderColor(color);
            }
        }
    }

    @Override
    public void setShrinkToFit(boolean shrinkToFit) {
        this.getCellAlignment().setShrinkToFit(shrinkToFit);
    }

    private int getFontId() {
        if (this._cellXf.isSetFontId()) {
            return (int)this._cellXf.getFontId();
        }
        return (int)this._cellStyleXf.getFontId();
    }

    protected XSSFCellAlignment getCellAlignment() {
        if (this._cellAlignment == null) {
            this._cellAlignment = new XSSFCellAlignment(this.getCTCellAlignment());
        }
        return this._cellAlignment;
    }

    private CTCellAlignment getCTCellAlignment() {
        if (this._cellXf.getAlignment() == null) {
            this._cellXf.setAlignment(CTCellAlignment.Factory.newInstance());
        }
        return this._cellXf.getAlignment();
    }

    public int hashCode() {
        return this._cellXf.toString().hashCode();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof XSSFCellStyle)) {
            return false;
        }
        XSSFCellStyle cf = (XSSFCellStyle)o;
        return this._cellXf.toString().equals(cf.getCoreXf().toString());
    }

    @Deprecated
    @Removal(version="5.0.0")
    public XSSFCellStyle clone() {
        return this.copy();
    }

    @Override
    public XSSFCellStyle copy() {
        CTXf xf = (CTXf)this._cellXf.copy();
        int xfSize = this._stylesSource._getStyleXfsSize();
        int indexXf = this._stylesSource.putCellXf(xf);
        return new XSSFCellStyle(indexXf - 1, xfSize - 1, this._stylesSource, this._theme);
    }
}

