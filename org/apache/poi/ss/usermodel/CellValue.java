/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.Removal;

public final class CellValue {
    public static final CellValue TRUE = new CellValue(CellType.BOOLEAN, 0.0, true, null, 0);
    public static final CellValue FALSE = new CellValue(CellType.BOOLEAN, 0.0, false, null, 0);
    private final CellType _cellType;
    private final double _numberValue;
    private final boolean _booleanValue;
    private final String _textValue;
    private final int _errorCode;

    private CellValue(CellType cellType, double numberValue, boolean booleanValue, String textValue, int errorCode) {
        this._cellType = cellType;
        this._numberValue = numberValue;
        this._booleanValue = booleanValue;
        this._textValue = textValue;
        this._errorCode = errorCode;
    }

    public CellValue(double numberValue) {
        this(CellType.NUMERIC, numberValue, false, null, 0);
    }

    public static CellValue valueOf(boolean booleanValue) {
        return booleanValue ? TRUE : FALSE;
    }

    public CellValue(String stringValue) {
        this(CellType.STRING, 0.0, false, stringValue, 0);
    }

    public static CellValue getError(int errorCode) {
        return new CellValue(CellType.ERROR, 0.0, false, null, errorCode);
    }

    public boolean getBooleanValue() {
        return this._booleanValue;
    }

    public double getNumberValue() {
        return this._numberValue;
    }

    public String getStringValue() {
        return this._textValue;
    }

    @Deprecated
    @Removal(version="4.2")
    public CellType getCellTypeEnum() {
        return this.getCellType();
    }

    public CellType getCellType() {
        return this._cellType;
    }

    public byte getErrorValue() {
        return (byte)this._errorCode;
    }

    public String toString() {
        return this.getClass().getName() + " [" + this.formatAsString() + "]";
    }

    public String formatAsString() {
        switch (this._cellType) {
            case NUMERIC: {
                return String.valueOf(this._numberValue);
            }
            case STRING: {
                return '\"' + this._textValue + '\"';
            }
            case BOOLEAN: {
                return this._booleanValue ? "TRUE" : "FALSE";
            }
            case ERROR: {
                return ErrorEval.getText(this._errorCode);
            }
        }
        return "<error unexpected cell type " + (Object)((Object)this._cellType) + ">";
    }
}

