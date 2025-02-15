/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.streaming;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFEvaluationSheet;

final class SXSSFEvaluationCell
implements EvaluationCell {
    private final EvaluationSheet _evalSheet;
    private final SXSSFCell _cell;

    public SXSSFEvaluationCell(SXSSFCell cell, SXSSFEvaluationSheet evaluationSheet) {
        this._cell = cell;
        this._evalSheet = evaluationSheet;
    }

    public SXSSFEvaluationCell(SXSSFCell cell) {
        this(cell, new SXSSFEvaluationSheet(cell.getSheet()));
    }

    @Override
    public Object getIdentityKey() {
        return this._cell;
    }

    public SXSSFCell getSXSSFCell() {
        return this._cell;
    }

    @Override
    public boolean getBooleanCellValue() {
        return this._cell.getBooleanCellValue();
    }

    @Override
    public CellType getCellType() {
        return this._cell.getCellType();
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    @Internal(since="POI 3.15 beta 3")
    public CellType getCellTypeEnum() {
        return this._cell.getCellTypeEnum();
    }

    @Override
    public int getColumnIndex() {
        return this._cell.getColumnIndex();
    }

    @Override
    public int getErrorCellValue() {
        return this._cell.getErrorCellValue();
    }

    @Override
    public double getNumericCellValue() {
        return this._cell.getNumericCellValue();
    }

    @Override
    public int getRowIndex() {
        return this._cell.getRowIndex();
    }

    @Override
    public EvaluationSheet getSheet() {
        return this._evalSheet;
    }

    @Override
    public String getStringCellValue() {
        return this._cell.getRichStringCellValue().getString();
    }

    @Override
    public CellRangeAddress getArrayFormulaRange() {
        return this._cell.getArrayFormulaRange();
    }

    @Override
    public boolean isPartOfArrayFormulaGroup() {
        return this._cell.isPartOfArrayFormulaGroup();
    }

    @Override
    public CellType getCachedFormulaResultType() {
        return this._cell.getCachedFormulaResultType();
    }

    @Override
    @Deprecated
    @Removal(version="4.2")
    @Internal(since="POI 3.15 beta 3")
    public CellType getCachedFormulaResultTypeEnum() {
        return this.getCachedFormulaResultType();
    }
}

