/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STBorderStyle;

public class XSSFBorderFormatting
implements BorderFormatting {
    IndexedColorMap _colorMap;
    CTBorder _border;

    XSSFBorderFormatting(CTBorder border, IndexedColorMap colorMap) {
        this._border = border;
        this._colorMap = colorMap;
    }

    @Override
    public BorderStyle getBorderBottom() {
        return this.getBorderStyle(this._border.getBottom());
    }

    @Override
    public BorderStyle getBorderDiagonal() {
        return this.getBorderStyle(this._border.getDiagonal());
    }

    @Override
    public BorderStyle getBorderLeft() {
        return this.getBorderStyle(this._border.getLeft());
    }

    @Override
    public BorderStyle getBorderRight() {
        return this.getBorderStyle(this._border.getRight());
    }

    @Override
    public BorderStyle getBorderTopEnum() {
        return this.getBorderTop();
    }

    @Override
    public BorderStyle getBorderBottomEnum() {
        return this.getBorderBottom();
    }

    @Override
    public BorderStyle getBorderDiagonalEnum() {
        return this.getBorderDiagonal();
    }

    @Override
    public BorderStyle getBorderLeftEnum() {
        return this.getBorderLeft();
    }

    @Override
    public BorderStyle getBorderRightEnum() {
        return this.getBorderRight();
    }

    @Override
    public BorderStyle getBorderTop() {
        return this.getBorderStyle(this._border.getTop());
    }

    @Override
    public XSSFColor getBottomBorderColorColor() {
        return this.getColor(this._border.getBottom());
    }

    @Override
    public short getBottomBorderColor() {
        return this.getIndexedColor(this.getBottomBorderColorColor());
    }

    @Override
    public XSSFColor getDiagonalBorderColorColor() {
        return this.getColor(this._border.getDiagonal());
    }

    @Override
    public short getDiagonalBorderColor() {
        return this.getIndexedColor(this.getDiagonalBorderColorColor());
    }

    @Override
    public XSSFColor getLeftBorderColorColor() {
        return this.getColor(this._border.getLeft());
    }

    @Override
    public short getLeftBorderColor() {
        return this.getIndexedColor(this.getLeftBorderColorColor());
    }

    @Override
    public XSSFColor getRightBorderColorColor() {
        return this.getColor(this._border.getRight());
    }

    @Override
    public short getRightBorderColor() {
        return this.getIndexedColor(this.getRightBorderColorColor());
    }

    @Override
    public XSSFColor getTopBorderColorColor() {
        return this.getColor(this._border.getTop());
    }

    @Override
    public short getTopBorderColor() {
        return this.getIndexedColor(this.getTopBorderColorColor());
    }

    @Override
    public void setBorderBottom(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetBottom() ? this._border.getBottom() : this._border.addNewBottom();
        if (border == BorderStyle.NONE) {
            this._border.unsetBottom();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setBorderDiagonal(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetDiagonal() ? this._border.getDiagonal() : this._border.addNewDiagonal();
        if (border == BorderStyle.NONE) {
            this._border.unsetDiagonal();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setBorderLeft(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetLeft() ? this._border.getLeft() : this._border.addNewLeft();
        if (border == BorderStyle.NONE) {
            this._border.unsetLeft();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setBorderRight(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetRight() ? this._border.getRight() : this._border.addNewRight();
        if (border == BorderStyle.NONE) {
            this._border.unsetRight();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setBorderTop(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetTop() ? this._border.getTop() : this._border.addNewTop();
        if (border == BorderStyle.NONE) {
            this._border.unsetTop();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setBottomBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setBottomBorderColor((CTColor)null);
        } else {
            this.setBottomBorderColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setBottomBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setBottomBorderColor(ctColor);
    }

    public void setBottomBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetBottom() ? this._border.getBottom() : this._border.addNewBottom();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    @Override
    public void setDiagonalBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setDiagonalBorderColor((CTColor)null);
        } else {
            this.setDiagonalBorderColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setDiagonalBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setDiagonalBorderColor(ctColor);
    }

    public void setDiagonalBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetDiagonal() ? this._border.getDiagonal() : this._border.addNewDiagonal();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    @Override
    public void setLeftBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setLeftBorderColor((CTColor)null);
        } else {
            this.setLeftBorderColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setLeftBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setLeftBorderColor(ctColor);
    }

    public void setLeftBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetLeft() ? this._border.getLeft() : this._border.addNewLeft();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    @Override
    public void setRightBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setRightBorderColor((CTColor)null);
        } else {
            this.setRightBorderColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setRightBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setRightBorderColor(ctColor);
    }

    public void setRightBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetRight() ? this._border.getRight() : this._border.addNewRight();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    @Override
    public void setTopBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setTopBorderColor((CTColor)null);
        } else {
            this.setTopBorderColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setTopBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setTopBorderColor(ctColor);
    }

    public void setTopBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetTop() ? this._border.getTop() : this._border.addNewTop();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    @Override
    public BorderStyle getBorderVertical() {
        return this.getBorderStyle(this._border.getVertical());
    }

    @Override
    public BorderStyle getBorderHorizontal() {
        return this.getBorderStyle(this._border.getHorizontal());
    }

    @Override
    public BorderStyle getBorderVerticalEnum() {
        return this.getBorderVertical();
    }

    @Override
    public BorderStyle getBorderHorizontalEnum() {
        return this.getBorderHorizontal();
    }

    @Override
    public short getVerticalBorderColor() {
        return this.getIndexedColor(this.getVerticalBorderColorColor());
    }

    @Override
    public XSSFColor getVerticalBorderColorColor() {
        return this.getColor(this._border.getVertical());
    }

    @Override
    public short getHorizontalBorderColor() {
        return this.getIndexedColor(this.getHorizontalBorderColorColor());
    }

    @Override
    public XSSFColor getHorizontalBorderColorColor() {
        return this.getColor(this._border.getHorizontal());
    }

    @Override
    public void setBorderHorizontal(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetHorizontal() ? this._border.getHorizontal() : this._border.addNewHorizontal();
        if (border == BorderStyle.NONE) {
            this._border.unsetHorizontal();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setBorderVertical(BorderStyle border) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetVertical() ? this._border.getVertical() : this._border.addNewVertical();
        if (border == BorderStyle.NONE) {
            this._border.unsetVertical();
        } else {
            pr.setStyle(STBorderStyle.Enum.forInt(border.getCode() + 1));
        }
    }

    @Override
    public void setHorizontalBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setHorizontalBorderColor(ctColor);
    }

    @Override
    public void setHorizontalBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setBottomBorderColor((CTColor)null);
        } else {
            this.setHorizontalBorderColor(xcolor.getCTColor());
        }
    }

    public void setHorizontalBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetHorizontal() ? this._border.getHorizontal() : this._border.addNewHorizontal();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    @Override
    public void setVerticalBorderColor(short color) {
        CTColor ctColor = CTColor.Factory.newInstance();
        ctColor.setIndexed(color);
        this.setVerticalBorderColor(ctColor);
    }

    @Override
    public void setVerticalBorderColor(Color color) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(color);
        if (xcolor == null) {
            this.setBottomBorderColor((CTColor)null);
        } else {
            this.setVerticalBorderColor(xcolor.getCTColor());
        }
    }

    public void setVerticalBorderColor(CTColor color) {
        CTBorderPr pr;
        CTBorderPr cTBorderPr = pr = this._border.isSetVertical() ? this._border.getVertical() : this._border.addNewVertical();
        if (color == null) {
            pr.unsetColor();
        } else {
            pr.setColor(color);
        }
    }

    private BorderStyle getBorderStyle(CTBorderPr borderPr) {
        if (borderPr == null) {
            return BorderStyle.NONE;
        }
        STBorderStyle.Enum ptrn = borderPr.getStyle();
        return ptrn == null ? BorderStyle.NONE : BorderStyle.valueOf((short)(ptrn.intValue() - 1));
    }

    private short getIndexedColor(XSSFColor color) {
        return color == null ? (short)0 : color.getIndexed();
    }

    private XSSFColor getColor(CTBorderPr pr) {
        return pr == null ? null : XSSFColor.from(pr.getColor(), this._colorMap);
    }
}

