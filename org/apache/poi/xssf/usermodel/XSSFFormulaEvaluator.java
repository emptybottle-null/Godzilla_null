/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.BaseXSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class XSSFFormulaEvaluator
extends BaseXSSFFormulaEvaluator {
    private XSSFWorkbook _book;

    public XSSFFormulaEvaluator(XSSFWorkbook workbook) {
        this(workbook, null, null);
    }

    private XSSFFormulaEvaluator(XSSFWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this(workbook, new WorkbookEvaluator(XSSFEvaluationWorkbook.create(workbook), stabilityClassifier, udfFinder));
    }

    protected XSSFFormulaEvaluator(XSSFWorkbook workbook, WorkbookEvaluator bookEvaluator) {
        super(bookEvaluator);
        this._book = workbook;
    }

    public static XSSFFormulaEvaluator create(XSSFWorkbook workbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        return new XSSFFormulaEvaluator(workbook, stabilityClassifier, udfFinder);
    }

    @Override
    public void notifySetFormula(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new XSSFEvaluationCell((XSSFCell)cell));
    }

    @Override
    public void notifyDeleteCell(Cell cell) {
        this._bookEvaluator.notifyDeleteCell(new XSSFEvaluationCell((XSSFCell)cell));
    }

    @Override
    public void notifyUpdateCell(Cell cell) {
        this._bookEvaluator.notifyUpdateCell(new XSSFEvaluationCell((XSSFCell)cell));
    }

    public static void evaluateAllFormulaCells(XSSFWorkbook wb) {
        BaseFormulaEvaluator.evaluateAllFormulaCells(wb);
    }

    @Override
    public XSSFCell evaluateInCell(Cell cell) {
        return (XSSFCell)super.evaluateInCell(cell);
    }

    @Override
    public void evaluateAll() {
        XSSFFormulaEvaluator.evaluateAllFormulaCells(this._book, this);
    }

    @Override
    protected EvaluationCell toEvaluationCell(Cell cell) {
        if (!(cell instanceof XSSFCell)) {
            throw new IllegalArgumentException("Unexpected type of cell: " + cell.getClass() + ". Only XSSFCells can be evaluated.");
        }
        return new XSSFEvaluationCell((XSSFCell)cell);
    }
}

