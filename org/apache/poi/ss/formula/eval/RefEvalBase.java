/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.SheetRange;
import org.apache.poi.ss.formula.eval.RefEval;

public abstract class RefEvalBase
implements RefEval {
    private final int _firstSheetIndex;
    private final int _lastSheetIndex;
    private final int _rowIndex;
    private final int _columnIndex;

    protected RefEvalBase(SheetRange sheetRange, int rowIndex, int columnIndex) {
        if (sheetRange == null) {
            throw new IllegalArgumentException("sheetRange must not be null");
        }
        this._firstSheetIndex = sheetRange.getFirstSheetIndex();
        this._lastSheetIndex = sheetRange.getLastSheetIndex();
        this._rowIndex = rowIndex;
        this._columnIndex = columnIndex;
    }

    protected RefEvalBase(int firstSheetIndex, int lastSheetIndex, int rowIndex, int columnIndex) {
        this._firstSheetIndex = firstSheetIndex;
        this._lastSheetIndex = lastSheetIndex;
        this._rowIndex = rowIndex;
        this._columnIndex = columnIndex;
    }

    protected RefEvalBase(int onlySheetIndex, int rowIndex, int columnIndex) {
        this(onlySheetIndex, onlySheetIndex, rowIndex, columnIndex);
    }

    @Override
    public int getNumberOfSheets() {
        return this._lastSheetIndex - this._firstSheetIndex + 1;
    }

    @Override
    public int getFirstSheetIndex() {
        return this._firstSheetIndex;
    }

    @Override
    public int getLastSheetIndex() {
        return this._lastSheetIndex;
    }

    @Override
    public final int getRow() {
        return this._rowIndex;
    }

    @Override
    public final int getColumn() {
        return this._columnIndex;
    }
}

