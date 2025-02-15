/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationTracker;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;

final class SheetRefEvaluator {
    private final WorkbookEvaluator _bookEvaluator;
    private final EvaluationTracker _tracker;
    private final int _sheetIndex;
    private EvaluationSheet _sheet;

    public SheetRefEvaluator(WorkbookEvaluator bookEvaluator, EvaluationTracker tracker, int sheetIndex) {
        if (sheetIndex < 0) {
            throw new IllegalArgumentException("Invalid sheetIndex: " + sheetIndex + ".");
        }
        this._bookEvaluator = bookEvaluator;
        this._tracker = tracker;
        this._sheetIndex = sheetIndex;
    }

    public String getSheetName() {
        return this._bookEvaluator.getSheetName(this._sheetIndex);
    }

    public ValueEval getEvalForCell(int rowIndex, int columnIndex) {
        return this._bookEvaluator.evaluateReference(this.getSheet(), this._sheetIndex, rowIndex, columnIndex, this._tracker);
    }

    private EvaluationSheet getSheet() {
        if (this._sheet == null) {
            this._sheet = this._bookEvaluator.getSheet(this._sheetIndex);
        }
        return this._sheet;
    }

    public boolean isSubTotal(int rowIndex, int columnIndex) {
        boolean subtotal = false;
        EvaluationCell cell = this.getSheet().getCell(rowIndex, columnIndex);
        if (cell != null && cell.getCellType() == CellType.FORMULA) {
            EvaluationWorkbook wb = this._bookEvaluator.getWorkbook();
            for (Ptg ptg : wb.getFormulaTokens(cell)) {
                FuncVarPtg f;
                if (!(ptg instanceof FuncVarPtg) || !"SUBTOTAL".equals((f = (FuncVarPtg)ptg).getName())) continue;
                subtotal = true;
                break;
            }
        }
        return subtotal;
    }

    public boolean isRowHidden(int rowIndex) {
        return this.getSheet().isRowHidden(rowIndex);
    }
}

