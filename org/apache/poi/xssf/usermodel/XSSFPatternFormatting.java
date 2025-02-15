/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

public class XSSFPatternFormatting
implements PatternFormatting {
    IndexedColorMap _colorMap;
    CTFill _fill;

    XSSFPatternFormatting(CTFill fill, IndexedColorMap colorMap) {
        this._fill = fill;
        this._colorMap = colorMap;
    }

    @Override
    public XSSFColor getFillBackgroundColorColor() {
        if (!this._fill.isSetPatternFill()) {
            return null;
        }
        return XSSFColor.from(this._fill.getPatternFill().getBgColor(), this._colorMap);
    }

    @Override
    public XSSFColor getFillForegroundColorColor() {
        if (!this._fill.isSetPatternFill() || !this._fill.getPatternFill().isSetFgColor()) {
            return null;
        }
        return XSSFColor.from(this._fill.getPatternFill().getFgColor(), this._colorMap);
    }

    @Override
    public short getFillPattern() {
        if (!this._fill.isSetPatternFill() || !this._fill.getPatternFill().isSetPatternType()) {
            return 0;
        }
        return (short)(this._fill.getPatternFill().getPatternType().intValue() - 1);
    }

    @Override
    public short getFillBackgroundColor() {
        XSSFColor color = this.getFillBackgroundColorColor();
        if (color == null) {
            return 0;
        }
        return color.getIndexed();
    }

    @Override
    public short getFillForegroundColor() {
        XSSFColor color = this.getFillForegroundColorColor();
        if (color == null) {
            return 0;
        }
        return color.getIndexed();
    }

    @Override
    public void setFillBackgroundColor(Color bg) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(bg);
        if (xcolor == null) {
            this.setFillBackgroundColor((CTColor)null);
        } else {
            this.setFillBackgroundColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setFillBackgroundColor(short bg) {
        CTColor bgColor = CTColor.Factory.newInstance();
        bgColor.setIndexed(bg);
        this.setFillBackgroundColor(bgColor);
    }

    private void setFillBackgroundColor(CTColor color) {
        CTPatternFill ptrn;
        CTPatternFill cTPatternFill = ptrn = this._fill.isSetPatternFill() ? this._fill.getPatternFill() : this._fill.addNewPatternFill();
        if (color == null) {
            ptrn.unsetBgColor();
        } else {
            ptrn.setBgColor(color);
        }
    }

    @Override
    public void setFillForegroundColor(Color fg) {
        XSSFColor xcolor = XSSFColor.toXSSFColor(fg);
        if (xcolor == null) {
            this.setFillForegroundColor((CTColor)null);
        } else {
            this.setFillForegroundColor(xcolor.getCTColor());
        }
    }

    @Override
    public void setFillForegroundColor(short fg) {
        CTColor fgColor = CTColor.Factory.newInstance();
        fgColor.setIndexed(fg);
        this.setFillForegroundColor(fgColor);
    }

    private void setFillForegroundColor(CTColor color) {
        CTPatternFill ptrn;
        CTPatternFill cTPatternFill = ptrn = this._fill.isSetPatternFill() ? this._fill.getPatternFill() : this._fill.addNewPatternFill();
        if (color == null) {
            ptrn.unsetFgColor();
        } else {
            ptrn.setFgColor(color);
        }
    }

    @Override
    public void setFillPattern(short fp) {
        CTPatternFill ptrn;
        CTPatternFill cTPatternFill = ptrn = this._fill.isSetPatternFill() ? this._fill.getPatternFill() : this._fill.addNewPatternFill();
        if (fp == 0) {
            ptrn.unsetPatternType();
        } else {
            ptrn.setPatternType(STPatternType.Enum.forInt(fp + 1));
        }
    }
}

