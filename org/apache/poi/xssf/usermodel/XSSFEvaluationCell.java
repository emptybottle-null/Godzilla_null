/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationSheet;

final class XSSFEvaluationCell
implements EvaluationCell {
    private final EvaluationSheet _evalSheet;
    private final XSSFCell _cell;

    public XSSFEvaluationCell(XSSFCell cell, XSSFEvaluationSheet evaluationSheet) {
        this._cell = cell;
        this._evalSheet = evaluationSheet;
    }

    public XSSFEvaluationCell(XSSFCell cell) {
        this(cell, new XSSFEvaluationSheet(cell.getSheet()));
    }

    @Override
    public Object getIdentityKey() {
        return this._cell;
    }

    public XSSFCell getXSSFCell() {
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
    @Internal(since="POI 3.15 beta 3")
    public CellType getCachedFormulaResultTypeEnum() {
        return this.getCachedFormulaResultType();
    }
}

