/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;

public final class CacheAreaEval
extends AreaEvalBase {
    private final ValueEval[] _values;

    public CacheAreaEval(AreaI ptg, ValueEval[] values) {
        super(ptg);
        this._values = values;
    }

    public CacheAreaEval(int firstRow, int firstColumn, int lastRow, int lastColumn, ValueEval[] values) {
        super(firstRow, firstColumn, lastRow, lastColumn);
        this._values = values;
    }

    @Override
    public ValueEval getRelativeValue(int relativeRowIndex, int relativeColumnIndex) {
        return this.getRelativeValue(-1, relativeRowIndex, relativeColumnIndex);
    }

    @Override
    public ValueEval getRelativeValue(int sheetIndex, int relativeRowIndex, int relativeColumnIndex) {
        int oneDimensionalIndex = relativeRowIndex * this.getWidth() + relativeColumnIndex;
        return this._values[oneDimensionalIndex];
    }

    @Override
    public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {
        AreaI.OffsetArea area = new AreaI.OffsetArea(this.getFirstRow(), this.getFirstColumn(), relFirstRowIx, relLastRowIx, relFirstColIx, relLastColIx);
        int height = area.getLastRow() - area.getFirstRow() + 1;
        int width = area.getLastColumn() - area.getFirstColumn() + 1;
        ValueEval[] newVals = new ValueEval[height * width];
        int startRow = area.getFirstRow() - this.getFirstRow();
        int startCol = area.getFirstColumn() - this.getFirstColumn();
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                ValueEval temp = startRow + j > this.getLastRow() || startCol + i > this.getLastColumn() ? BlankEval.instance : this._values[(startRow + j) * this.getWidth() + (startCol + i)];
                newVals[j * width + i] = temp;
            }
        }
        return new CacheAreaEval((AreaI)area, newVals);
    }

    @Override
    public TwoDEval getRow(int rowIndex) {
        if (rowIndex >= this.getHeight()) {
            throw new IllegalArgumentException("Invalid rowIndex " + rowIndex + ".  Allowable range is (0.." + this.getHeight() + ").");
        }
        int absRowIndex = this.getFirstRow() + rowIndex;
        ValueEval[] values = new ValueEval[this.getWidth()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = this.getRelativeValue(rowIndex, i);
        }
        return new CacheAreaEval(absRowIndex, this.getFirstColumn(), absRowIndex, this.getLastColumn(), values);
    }

    @Override
    public TwoDEval getColumn(int columnIndex) {
        if (columnIndex >= this.getWidth()) {
            throw new IllegalArgumentException("Invalid columnIndex " + columnIndex + ".  Allowable range is (0.." + this.getWidth() + ").");
        }
        int absColIndex = this.getFirstColumn() + columnIndex;
        ValueEval[] values = new ValueEval[this.getHeight()];
        for (int i = 0; i < values.length; ++i) {
            values[i] = this.getRelativeValue(i, columnIndex);
        }
        return new CacheAreaEval(this.getFirstRow(), absColIndex, this.getLastRow(), absColIndex, values);
    }

    public String toString() {
        CellReference crA = new CellReference(this.getFirstRow(), this.getFirstColumn());
        CellReference crB = new CellReference(this.getLastRow(), this.getLastColumn());
        return this.getClass().getName() + "[" + crA.formatAsString() + ':' + crB.formatAsString() + "]";
    }
}

