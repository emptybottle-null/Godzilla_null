/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.formula.CellCacheEntry;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaCellCacheEntry;
import org.apache.poi.ss.formula.FormulaUsedBlankCellSet;
import org.apache.poi.ss.formula.eval.ValueEval;

final class CellEvaluationFrame {
    private final FormulaCellCacheEntry _cce;
    private final Set<CellCacheEntry> _sensitiveInputCells;
    private FormulaUsedBlankCellSet _usedBlankCellGroup;

    public CellEvaluationFrame(FormulaCellCacheEntry cce) {
        this._cce = cce;
        this._sensitiveInputCells = new HashSet<CellCacheEntry>();
    }

    public CellCacheEntry getCCE() {
        return this._cce;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.getClass().getName()).append(" [");
        sb.append("]");
        return sb.toString();
    }

    public void addSensitiveInputCell(CellCacheEntry inputCell) {
        this._sensitiveInputCells.add(inputCell);
    }

    private CellCacheEntry[] getSensitiveInputCells() {
        int nItems = this._sensitiveInputCells.size();
        if (nItems < 1) {
            return CellCacheEntry.EMPTY_ARRAY;
        }
        CellCacheEntry[] result = new CellCacheEntry[nItems];
        this._sensitiveInputCells.toArray(result);
        return result;
    }

    public void addUsedBlankCell(EvaluationWorkbook evalWorkbook, int bookIndex, int sheetIndex, int rowIndex, int columnIndex) {
        if (this._usedBlankCellGroup == null) {
            this._usedBlankCellGroup = new FormulaUsedBlankCellSet();
        }
        this._usedBlankCellGroup.addCell(evalWorkbook, bookIndex, sheetIndex, rowIndex, columnIndex);
    }

    public void updateFormulaResult(ValueEval result) {
        this._cce.updateFormulaResult(result, this.getSensitiveInputCells(), this._usedBlankCellGroup);
    }
}

