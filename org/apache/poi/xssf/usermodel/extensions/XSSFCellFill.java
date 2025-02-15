/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.extensions;

import java.util.Objects;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;

public final class XSSFCellFill {
    private IndexedColorMap _indexedColorMap;
    private CTFill _fill;

    public XSSFCellFill(CTFill fill, IndexedColorMap colorMap) {
        this._fill = fill;
        this._indexedColorMap = colorMap;
    }

    public XSSFCellFill() {
        this._fill = CTFill.Factory.newInstance();
    }

    public XSSFColor getFillBackgroundColor() {
        CTPatternFill ptrn = this._fill.getPatternFill();
        if (ptrn == null) {
            return null;
        }
        CTColor ctColor = ptrn.getBgColor();
        return XSSFColor.from(ctColor, this._indexedColorMap);
    }

    public void setFillBackgroundColor(int index) {
        CTPatternFill ptrn = this.ensureCTPatternFill();
        CTColor ctColor = ptrn.isSetBgColor() ? ptrn.getBgColor() : ptrn.addNewBgColor();
        ctColor.setIndexed(index);
    }

    public void setFillBackgroundColor(XSSFColor color) {
        CTPatternFill ptrn = this.ensureCTPatternFill();
        if (color == null) {
            ptrn.unsetBgColor();
        } else {
            ptrn.setBgColor(color.getCTColor());
        }
    }

    public XSSFColor getFillForegroundColor() {
        CTPatternFill ptrn = this._fill.getPatternFill();
        if (ptrn == null) {
            return null;
        }
        CTColor ctColor = ptrn.getFgColor();
        return XSSFColor.from(ctColor, this._indexedColorMap);
    }

    public void setFillForegroundColor(int index) {
        CTPatternFill ptrn = this.ensureCTPatternFill();
        CTColor ctColor = ptrn.isSetFgColor() ? ptrn.getFgColor() : ptrn.addNewFgColor();
        ctColor.setIndexed(index);
    }

    public void setFillForegroundColor(XSSFColor color) {
        CTPatternFill ptrn = this.ensureCTPatternFill();
        if (color == null) {
            ptrn.unsetFgColor();
        } else {
            ptrn.setFgColor(color.getCTColor());
        }
    }

    public STPatternType.Enum getPatternType() {
        CTPatternFill ptrn = this._fill.getPatternFill();
        return ptrn == null ? null : ptrn.getPatternType();
    }

    public void setPatternType(STPatternType.Enum patternType) {
        CTPatternFill ptrn = this.ensureCTPatternFill();
        ptrn.setPatternType(patternType);
    }

    private CTPatternFill ensureCTPatternFill() {
        CTPatternFill patternFill = this._fill.getPatternFill();
        if (patternFill == null) {
            patternFill = this._fill.addNewPatternFill();
        }
        return patternFill;
    }

    @Internal
    public CTFill getCTFill() {
        return this._fill;
    }

    public int hashCode() {
        return this._fill.toString().hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof XSSFCellFill)) {
            return false;
        }
        XSSFCellFill cf = (XSSFCellFill)o;
        return Objects.equals(this.getFillBackgroundColor(), cf.getFillBackgroundColor()) && Objects.equals(this.getFillForegroundColor(), cf.getFillForegroundColor()) && Objects.equals(this.getPatternType(), cf.getPatternType());
    }
}

