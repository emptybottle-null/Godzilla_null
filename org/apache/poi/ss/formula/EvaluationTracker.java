/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.ss.formula.CellCacheEntry;
import org.apache.poi.ss.formula.CellEvaluationFrame;
import org.apache.poi.ss.formula.EvaluationCache;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.FormulaCellCacheEntry;
import org.apache.poi.ss.formula.PlainValueCellCacheEntry;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.ValueEval;

final class EvaluationTracker {
    private final List<CellEvaluationFrame> _evaluationFrames;
    private final Set<FormulaCellCacheEntry> _currentlyEvaluatingCells;
    private final EvaluationCache _cache;

    public EvaluationTracker(EvaluationCache cache) {
        this._cache = cache;
        this._evaluationFrames = new ArrayList<CellEvaluationFrame>();
        this._currentlyEvaluatingCells = new HashSet<FormulaCellCacheEntry>();
    }

    public boolean startEvaluate(FormulaCellCacheEntry cce) {
        if (cce == null) {
            throw new IllegalArgumentException("cellLoc must not be null");
        }
        if (this._currentlyEvaluatingCells.contains(cce)) {
            return false;
        }
        this._currentlyEvaluatingCells.add(cce);
        this._evaluationFrames.add(new CellEvaluationFrame(cce));
        return true;
    }

    public void updateCacheResult(ValueEval result) {
        int nFrames = this._evaluationFrames.size();
        if (nFrames < 1) {
            throw new IllegalStateException("Call to endEvaluate without matching call to startEvaluate");
        }
        CellEvaluationFrame frame = this._evaluationFrames.get(nFrames - 1);
        if (result == ErrorEval.CIRCULAR_REF_ERROR && nFrames > 1) {
            return;
        }
        frame.updateFormulaResult(result);
    }

    public void endEvaluate(CellCacheEntry cce) {
        CellEvaluationFrame frame;
        int nFrames = this._evaluationFrames.size();
        if (nFrames < 1) {
            throw new IllegalStateException("Call to endEvaluate without matching call to startEvaluate");
        }
        if (cce != (frame = this._evaluationFrames.get(--nFrames)).getCCE()) {
            throw new IllegalStateException("Wrong cell specified. ");
        }
        this._evaluationFrames.remove(nFrames);
        this._currentlyEvaluatingCells.remove(cce);
    }

    public void acceptFormulaDependency(CellCacheEntry cce) {
        int prevFrameIndex = this._evaluationFrames.size() - 1;
        if (prevFrameIndex >= 0) {
            CellEvaluationFrame consumingFrame = this._evaluationFrames.get(prevFrameIndex);
            consumingFrame.addSensitiveInputCell(cce);
        }
    }

    public void acceptPlainValueDependency(EvaluationWorkbook evalWorkbook, int bookIndex, int sheetIndex, int rowIndex, int columnIndex, ValueEval value) {
        int prevFrameIndex = this._evaluationFrames.size() - 1;
        if (prevFrameIndex >= 0) {
            CellEvaluationFrame consumingFrame = this._evaluationFrames.get(prevFrameIndex);
            if (value == BlankEval.instance) {
                consumingFrame.addUsedBlankCell(evalWorkbook, bookIndex, sheetIndex, rowIndex, columnIndex);
            } else {
                PlainValueCellCacheEntry cce = this._cache.getPlainValueEntry(bookIndex, sheetIndex, rowIndex, columnIndex, value);
                consumingFrame.addSensitiveInputCell(cce);
            }
        }
    }
}

