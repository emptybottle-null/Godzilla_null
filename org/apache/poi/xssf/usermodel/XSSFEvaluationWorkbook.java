/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Internal
public final class XSSFEvaluationWorkbook
extends BaseXSSFEvaluationWorkbook {
    private XSSFEvaluationSheet[] _sheetCache;

    public static XSSFEvaluationWorkbook create(XSSFWorkbook book) {
        if (book == null) {
            return null;
        }
        return new XSSFEvaluationWorkbook(book);
    }

    private XSSFEvaluationWorkbook(XSSFWorkbook book) {
        super(book);
    }

    @Override
    public void clearAllCachedResultValues() {
        super.clearAllCachedResultValues();
        this._sheetCache = null;
    }

    @Override
    public int getSheetIndex(EvaluationSheet evalSheet) {
        XSSFSheet sheet = ((XSSFEvaluationSheet)evalSheet).getXSSFSheet();
        return this._uBook.getSheetIndex(sheet);
    }

    @Override
    public EvaluationSheet getSheet(int sheetIndex) {
        if (this._sheetCache == null) {
            int numberOfSheets = this._uBook.getNumberOfSheets();
            this._sheetCache = new XSSFEvaluationSheet[numberOfSheets];
            for (int i = 0; i < numberOfSheets; ++i) {
                this._sheetCache[i] = new XSSFEvaluationSheet(this._uBook.getSheetAt(i));
            }
        }
        if (sheetIndex < 0 || sheetIndex >= this._sheetCache.length) {
            this._uBook.getSheetAt(sheetIndex);
        }
        return this._sheetCache[sheetIndex];
    }

    @Override
    public Ptg[] getFormulaTokens(EvaluationCell evalCell) {
        XSSFCell cell = ((XSSFEvaluationCell)evalCell).getXSSFCell();
        int sheetIndex = this._uBook.getSheetIndex(cell.getSheet());
        int rowIndex = cell.getRowIndex();
        return FormulaParser.parse(cell.getCellFormula(this), this, FormulaType.CELL, sheetIndex, rowIndex);
    }
}

