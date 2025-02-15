/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.ss.formula.CellCacheEntry;
import org.apache.poi.ss.formula.FormulaUsedBlankCellSet;
import org.apache.poi.ss.formula.IEvaluationListener;
import org.apache.poi.ss.formula.eval.ValueEval;

final class FormulaCellCacheEntry
extends CellCacheEntry {
    private CellCacheEntry[] _sensitiveInputCells;
    private FormulaUsedBlankCellSet _usedBlankCellGroup;

    public boolean isInputSensitive() {
        if (this._sensitiveInputCells != null && this._sensitiveInputCells.length > 0) {
            return true;
        }
        return this._usedBlankCellGroup == null ? false : !this._usedBlankCellGroup.isEmpty();
    }

    public void setSensitiveInputCells(CellCacheEntry[] sensitiveInputCells) {
        if (sensitiveInputCells == null) {
            this._sensitiveInputCells = null;
            this.changeConsumingCells(CellCacheEntry.EMPTY_ARRAY);
        } else {
            this._sensitiveInputCells = (CellCacheEntry[])sensitiveInputCells.clone();
            this.changeConsumingCells(this._sensitiveInputCells);
        }
    }

    public void clearFormulaEntry() {
        CellCacheEntry[] usedCells = this._sensitiveInputCells;
        if (usedCells != null) {
            for (int i = usedCells.length - 1; i >= 0; --i) {
                usedCells[i].clearConsumingCell(this);
            }
        }
        this._sensitiveInputCells = null;
        this.clearValue();
    }

    private void changeConsumingCells(CellCacheEntry[] usedCells) {
        Set usedSet;
        CellCacheEntry[] prevUsedCells = this._sensitiveInputCells;
        int nUsed = usedCells.length;
        for (int i = 0; i < nUsed; ++i) {
            usedCells[i].addConsumingCell(this);
        }
        if (prevUsedCells == null) {
            return;
        }
        int nPrevUsed = prevUsedCells.length;
        if (nPrevUsed < 1) {
            return;
        }
        if (nUsed < 1) {
            usedSet = Collections.emptySet();
        } else {
            usedSet = new HashSet(nUsed * 3 / 2);
            usedSet.addAll(Arrays.asList(usedCells).subList(0, nUsed));
        }
        for (int i = 0; i < nPrevUsed; ++i) {
            CellCacheEntry prevUsed = prevUsedCells[i];
            if (usedSet.contains(prevUsed)) continue;
            prevUsed.clearConsumingCell(this);
        }
    }

    public void updateFormulaResult(ValueEval result, CellCacheEntry[] sensitiveInputCells, FormulaUsedBlankCellSet usedBlankAreas) {
        this.updateValue(result);
        this.setSensitiveInputCells(sensitiveInputCells);
        this._usedBlankCellGroup = usedBlankAreas;
    }

    public void notifyUpdatedBlankCell(FormulaUsedBlankCellSet.BookSheetKey bsk, int rowIndex, int columnIndex, IEvaluationListener evaluationListener) {
        if (this._usedBlankCellGroup != null && this._usedBlankCellGroup.containsCell(bsk, rowIndex, columnIndex)) {
            this.clearFormulaEntry();
            this.recurseClearCachedFormulaResults(evaluationListener);
        }
    }
}

