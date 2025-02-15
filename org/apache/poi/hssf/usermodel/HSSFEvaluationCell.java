/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFEvaluationSheet;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Removal;

final class HSSFEvaluationCell
implements EvaluationCell {
    private final EvaluationSheet _evalSheet;
    private final HSSFCell _cell;

    public HSSFEvaluationCell(HSSFCell cell, EvaluationSheet evalSheet) {
        this._cell = cell;
        this._evalSheet = evalSheet;
    }

    public HSSFEvaluationCell(HSSFCell cell) {
        this(cell, new HSSFEvaluationSheet(cell.getSheet()));
    }

    @Override
    public Object getIdentityKey() {
        return this._cell;
    }

    public HSSFCell getHSSFCell() {
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
    public CellType getCellTypeEnum() {
        return this.getCellType();
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
    public CellType getCachedFormulaResultTypeEnum() {
        return this.getCachedFormulaResultType();
    }
}

