/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval.forked;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.EvaluationSheet;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluationCell;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluationSheet;
import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Internal;

@Internal
final class ForkedEvaluationWorkbook
implements EvaluationWorkbook {
    private final EvaluationWorkbook _masterBook;
    private final Map<String, ForkedEvaluationSheet> _sharedSheetsByName;

    public ForkedEvaluationWorkbook(EvaluationWorkbook master) {
        this._masterBook = master;
        this._sharedSheetsByName = new HashMap<String, ForkedEvaluationSheet>();
    }

    public ForkedEvaluationCell getOrCreateUpdatableCell(String sheetName, int rowIndex, int columnIndex) {
        ForkedEvaluationSheet sheet = this.getSharedSheet(sheetName);
        return sheet.getOrCreateUpdatableCell(rowIndex, columnIndex);
    }

    public EvaluationCell getEvaluationCell(String sheetName, int rowIndex, int columnIndex) {
        ForkedEvaluationSheet sheet = this.getSharedSheet(sheetName);
        return sheet.getCell(rowIndex, columnIndex);
    }

    private ForkedEvaluationSheet getSharedSheet(String sheetName) {
        ForkedEvaluationSheet result = this._sharedSheetsByName.get(sheetName);
        if (result == null) {
            result = new ForkedEvaluationSheet(this._masterBook.getSheet(this._masterBook.getSheetIndex(sheetName)));
            this._sharedSheetsByName.put(sheetName, result);
        }
        return result;
    }

    public void copyUpdatedCells(Workbook workbook) {
        String[] sheetNames = new String[this._sharedSheetsByName.size()];
        this._sharedSheetsByName.keySet().toArray(sheetNames);
        for (String sheetName : sheetNames) {
            ForkedEvaluationSheet sheet = this._sharedSheetsByName.get(sheetName);
            sheet.copyUpdatedCells(workbook.getSheet(sheetName));
        }
    }

    @Override
    public int convertFromExternSheetIndex(int externSheetIndex) {
        return this._masterBook.convertFromExternSheetIndex(externSheetIndex);
    }

    @Override
    public EvaluationWorkbook.ExternalSheet getExternalSheet(int externSheetIndex) {
        return this._masterBook.getExternalSheet(externSheetIndex);
    }

    @Override
    public EvaluationWorkbook.ExternalSheet getExternalSheet(String firstSheetName, String lastSheetName, int externalWorkbookNumber) {
        return this._masterBook.getExternalSheet(firstSheetName, lastSheetName, externalWorkbookNumber);
    }

    @Override
    public Ptg[] getFormulaTokens(EvaluationCell cell) {
        if (cell instanceof ForkedEvaluationCell) {
            throw new RuntimeException("Updated formulas not supported yet");
        }
        return this._masterBook.getFormulaTokens(cell);
    }

    @Override
    public EvaluationName getName(NamePtg namePtg) {
        return this._masterBook.getName(namePtg);
    }

    @Override
    public EvaluationName getName(String name, int sheetIndex) {
        return this._masterBook.getName(name, sheetIndex);
    }

    @Override
    public EvaluationSheet getSheet(int sheetIndex) {
        return this.getSharedSheet(this.getSheetName(sheetIndex));
    }

    @Override
    public EvaluationWorkbook.ExternalName getExternalName(int externSheetIndex, int externNameIndex) {
        return this._masterBook.getExternalName(externSheetIndex, externNameIndex);
    }

    @Override
    public EvaluationWorkbook.ExternalName getExternalName(String nameName, String sheetName, int externalWorkbookNumber) {
        return this._masterBook.getExternalName(nameName, sheetName, externalWorkbookNumber);
    }

    @Override
    public int getSheetIndex(EvaluationSheet sheet) {
        if (sheet instanceof ForkedEvaluationSheet) {
            ForkedEvaluationSheet mes = (ForkedEvaluationSheet)sheet;
            return mes.getSheetIndex(this._masterBook);
        }
        return this._masterBook.getSheetIndex(sheet);
    }

    @Override
    public int getSheetIndex(String sheetName) {
        return this._masterBook.getSheetIndex(sheetName);
    }

    @Override
    public String getSheetName(int sheetIndex) {
        return this._masterBook.getSheetName(sheetIndex);
    }

    @Override
    public String resolveNameXText(NameXPtg ptg) {
        return this._masterBook.resolveNameXText(ptg);
    }

    @Override
    public UDFFinder getUDFFinder() {
        return this._masterBook.getUDFFinder();
    }

    @Override
    public SpreadsheetVersion getSpreadsheetVersion() {
        return this._masterBook.getSpreadsheetVersion();
    }

    @Override
    public void clearAllCachedResultValues() {
        this._masterBook.clearAllCachedResultValues();
    }
}

