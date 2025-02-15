/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;

public class XSLFTableStyle {
    private CTTableStyle _tblStyle;

    XSLFTableStyle(CTTableStyle style) {
        this._tblStyle = style;
    }

    public CTTableStyle getXmlObject() {
        return this._tblStyle;
    }

    public String getStyleName() {
        return this._tblStyle.getStyleName();
    }

    public String getStyleId() {
        return this._tblStyle.getStyleId();
    }

    protected CTTablePartStyle getTablePartStyle(TablePartStyle tps) {
        switch (tps) {
            default: {
                return this._tblStyle.getWholeTbl();
            }
            case band1H: {
                return this._tblStyle.getBand1H();
            }
            case band2H: {
                return this._tblStyle.getBand2H();
            }
            case band1V: {
                return this._tblStyle.getBand1V();
            }
            case band2V: {
                return this._tblStyle.getBand2V();
            }
            case firstCol: {
                return this._tblStyle.getFirstCol();
            }
            case lastCol: {
                return this._tblStyle.getLastCol();
            }
            case firstRow: {
                return this._tblStyle.getFirstRow();
            }
            case lastRow: {
                return this._tblStyle.getLastRow();
            }
            case seCell: {
                return this._tblStyle.getSeCell();
            }
            case swCell: {
                return this._tblStyle.getSwCell();
            }
            case neCell: {
                return this._tblStyle.getNeCell();
            }
            case nwCell: 
        }
        return this._tblStyle.getNwCell();
    }

    public static enum TablePartStyle {
        wholeTbl,
        band1H,
        band2H,
        band1V,
        band2V,
        firstCol,
        lastCol,
        firstRow,
        lastRow,
        seCell,
        swCell,
        neCell,
        nwCell;

    }
}

