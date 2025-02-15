/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval;

import org.apache.poi.ss.formula.SheetRange;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;

public abstract class AreaEvalBase
implements AreaEval {
    private final int _firstSheet;
    private final int _firstColumn;
    private final int _firstRow;
    private final int _lastSheet;
    private final int _lastColumn;
    private final int _lastRow;
    private final int _nColumns;
    private final int _nRows;

    protected AreaEvalBase(SheetRange sheets, int firstRow, int firstColumn, int lastRow, int lastColumn) {
        this._firstColumn = firstColumn;
        this._firstRow = firstRow;
        this._lastColumn = lastColumn;
        this._lastRow = lastRow;
        this._nColumns = this._lastColumn - this._firstColumn + 1;
        this._nRows = this._lastRow - this._firstRow + 1;
        if (sheets != null) {
            this._firstSheet = sheets.getFirstSheetIndex();
            this._lastSheet = sheets.getLastSheetIndex();
        } else {
            this._firstSheet = -1;
            this._lastSheet = -1;
        }
    }

    protected AreaEvalBase(int firstRow, int firstColumn, int lastRow, int lastColumn) {
        this(null, firstRow, firstColumn, lastRow, lastColumn);
    }

    protected AreaEvalBase(AreaI ptg) {
        this(ptg, null);
    }

    protected AreaEvalBase(AreaI ptg, SheetRange sheets) {
        this(sheets, ptg.getFirstRow(), ptg.getFirstColumn(), ptg.getLastRow(), ptg.getLastColumn());
    }

    @Override
    public final int getFirstColumn() {
        return this._firstColumn;
    }

    @Override
    public final int getFirstRow() {
        return this._firstRow;
    }

    @Override
    public final int getLastColumn() {
        return this._lastColumn;
    }

    @Override
    public final int getLastRow() {
        return this._lastRow;
    }

    @Override
    public int getFirstSheetIndex() {
        return this._firstSheet;
    }

    @Override
    public int getLastSheetIndex() {
        return this._lastSheet;
    }

    @Override
    public final ValueEval getAbsoluteValue(int row, int col) {
        int rowOffsetIx = row - this._firstRow;
        int colOffsetIx = col - this._firstColumn;
        if (rowOffsetIx < 0 || rowOffsetIx >= this._nRows) {
            throw new IllegalArgumentException("Specified row index (" + row + ") is outside the allowed range (" + this._firstRow + ".." + this._lastRow + ")");
        }
        if (colOffsetIx < 0 || colOffsetIx >= this._nColumns) {
            throw new IllegalArgumentException("Specified column index (" + col + ") is outside the allowed range (" + this._firstColumn + ".." + col + ")");
        }
        return this.getRelativeValue(rowOffsetIx, colOffsetIx);
    }

    @Override
    public final boolean contains(int row, int col) {
        return this._firstRow <= row && this._lastRow >= row && this._firstColumn <= col && this._lastColumn >= col;
    }

    @Override
    public final boolean containsRow(int row) {
        return this._firstRow <= row && this._lastRow >= row;
    }

    @Override
    public final boolean containsColumn(int col) {
        return this._firstColumn <= col && this._lastColumn >= col;
    }

    @Override
    public final boolean isColumn() {
        return this._firstColumn == this._lastColumn;
    }

    @Override
    public final boolean isRow() {
        return this._firstRow == this._lastRow;
    }

    @Override
    public int getHeight() {
        return this._lastRow - this._firstRow + 1;
    }

    @Override
    public final ValueEval getValue(int row, int col) {
        return this.getRelativeValue(row, col);
    }

    @Override
    public final ValueEval getValue(int sheetIndex, int row, int col) {
        return this.getRelativeValue(sheetIndex, row, col);
    }

    @Override
    public abstract ValueEval getRelativeValue(int var1, int var2);

    public abstract ValueEval getRelativeValue(int var1, int var2, int var3);

    @Override
    public int getWidth() {
        return this._lastColumn - this._firstColumn + 1;
    }

    @Override
    public boolean isSubTotal(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public boolean isRowHidden(int rowIndex) {
        return false;
    }
}

