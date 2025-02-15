/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFEvaluationCell;
import org.apache.poi.xssf.streaming.SXSSFFormulaEvaluator;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;

@Internal
final class SXSSFEvaluationSheet
implements EvaluationSheet {
    private final SXSSFSheet _xs;

    public SXSSFEvaluationSheet(SXSSFSheet sheet) {
        this._xs = sheet;
    }

    public SXSSFSheet getSXSSFSheet() {
        return this._xs;
    }

    @Override
    public int getLastRowNum() {
        return this._xs.getLastRowNum();
    }

    @Override
    public boolean isRowHidden(int rowIndex) {
        SXSSFRow row = this._xs.getRow(rowIndex);
        if (row == null) {
            return false;
        }
        return row.getZeroHeight();
    }

    @Override
    public EvaluationCell getCell(int rowIndex, int columnIndex) {
        SXSSFRow row = this._xs.getRow(rowIndex);
        if (row == null) {
            if (rowIndex <= this._xs.getLastFlushedRowNum()) {
                throw new SXSSFFormulaEvaluator.RowFlushedException(rowIndex);
            }
            return null;
        }
        SXSSFCell cell = row.getCell(columnIndex);
        if (cell == null) {
            return null;
        }
        return new SXSSFEvaluationCell(cell, this);
    }

    @Override
    public void clearAllCachedResultValues() {
    }
}

