/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.LazyAreaEval;
import org.apache.poi.ss.formula.SheetRangeEvaluator;
import org.apache.poi.ss.formula.SheetRefEvaluator;
import org.apache.poi.ss.formula.eval.AreaEval;
import org.apache.poi.ss.formula.eval.RefEvalBase;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.util.CellReference;

public final class LazyRefEval
extends RefEvalBase {
    private final SheetRangeEvaluator _evaluator;

    public LazyRefEval(int rowIndex, int columnIndex, SheetRangeEvaluator sre) {
        super(sre, rowIndex, columnIndex);
        this._evaluator = sre;
    }

    @Override
    public ValueEval getInnerValueEval(int sheetIndex) {
        return this._evaluator.getEvalForCell(sheetIndex, this.getRow(), this.getColumn());
    }

    @Override
    public AreaEval offset(int relFirstRowIx, int relLastRowIx, int relFirstColIx, int relLastColIx) {
        AreaI.OffsetArea area = new AreaI.OffsetArea(this.getRow(), this.getColumn(), relFirstRowIx, relLastRowIx, relFirstColIx, relLastColIx);
        return new LazyAreaEval((AreaI)area, this._evaluator);
    }

    public boolean isSubTotal() {
        SheetRefEvaluator sheetEvaluator = this._evaluator.getSheetEvaluator(this.getFirstSheetIndex());
        return sheetEvaluator.isSubTotal(this.getRow(), this.getColumn());
    }

    public boolean isRowHidden() {
        SheetRefEvaluator _sre = this._evaluator.getSheetEvaluator(this._evaluator.getFirstSheetIndex());
        return _sre.isRowHidden(this.getRow());
    }

    public String toString() {
        CellReference cr = new CellReference(this.getRow(), this.getColumn());
        return this.getClass().getName() + "[" + this._evaluator.getSheetNameRange() + '!' + cr.formatAsString() + "]";
    }
}

