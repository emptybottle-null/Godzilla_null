/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.SheetRangeEvaluator;
import org.apache.poi.ss.formula.SheetRefEvaluator;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;

final class LazyAreaEval
extends AreaEvalBase {
    private final SheetRangeEvaluator _evaluator;

    LazyAreaEval(AreaI ptg, SheetRangeEvaluator evaluator) {
        super(ptg, evaluator);
        this._evaluator = evaluator;
    }

    public LazyAreaEval(int firstRowIndex, int firstColumnIndex, int lastRowIndex, int lastColumnIndex, SheetRangeEvaluator evaluator) {
        super(evaluator, firstRowIndex, firstColumnIndex, lastRowIndex, lastColumnIndex);
        this._evaluator = evaluator;
    }

    @Override
    public ValueEval getRelativeValue(int relativeRowIndex, int relativeColumnIndex) {
        return this.getRelativeValue(this.getFirstSheetIndex(), relativeRowIndex, relativeColumnIndex);
    }

    @Override
    public ValueEval getRelativeValue(int sheetIndex, int relativeRowIndex, int relativeColumnIndex) {
        int rowIx = relativeRowIndex + this.getFirstRow();
        int colIx = relativeColumnIndex + this.getFirstColumn();
        return this._evaluator.getEvalForCell(sheetIndex, rowIx, colIx);
    }

    @Override
    public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {
        AreaI.OffsetArea area = new AreaI.OffsetArea(this.getFirstRow(), this.getFirstColumn(), relFirstRowIx, relLastRowIx, relFirstColIx, relLastColIx);
        return new LazyAreaEval((AreaI)area, this._evaluator);
    }

    @Override
    public LazyAreaEval getRow(int rowIndex) {
        if (rowIndex >= this.getHeight()) {
            throw new IllegalArgumentException("Invalid rowIndex " + rowIndex + ".  Allowable range is (0.." + this.getHeight() + ").");
        }
        int absRowIx = this.getFirstRow() + rowIndex;
        return new LazyAreaEval(absRowIx, this.getFirstColumn(), absRowIx, this.getLastColumn(), this._evaluator);
    }

    @Override
    public LazyAreaEval getColumn(int columnIndex) {
        if (columnIndex >= this.getWidth()) {
            throw new IllegalArgumentException("Invalid columnIndex " + columnIndex + ".  Allowable range is (0.." + this.getWidth() + ").");
        }
        int absColIx = this.getFirstColumn() + columnIndex;
        return new LazyAreaEval(this.getFirstRow(), absColIx, this.getLastRow(), absColIx, this._evaluator);
    }

    public String toString() {
        CellReference crA = new CellReference(this.getFirstRow(), this.getFirstColumn());
        CellReference crB = new CellReference(this.getLastRow(), this.getLastColumn());
        return this.getClass().getName() + "[" + this._evaluator.getSheetNameRange() + '!' + crA.formatAsString() + ':' + crB.formatAsString() + "]";
    }

    @Override
    public boolean isSubTotal(int rowIndex, int columnIndex) {
        SheetRefEvaluator _sre = this._evaluator.getSheetEvaluator(this._evaluator.getFirstSheetIndex());
        return _sre.isSubTotal(this.getFirstRow() + rowIndex, this.getFirstColumn() + columnIndex);
    }

    @Override
    public boolean isRowHidden(int rowIndex) {
        SheetRefEvaluator _sre = this._evaluator.getSheetEvaluator(this._evaluator.getFirstSheetIndex());
        return _sre.isRowHidden(this.getFirstRow() + rowIndex);
    }
}

