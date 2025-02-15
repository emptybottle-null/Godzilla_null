/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.eval.forked;

import java.lang.reflect.Method;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.IStabilityClassifier;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluationCell;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluationWorkbook;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Workbook;

public final class ForkedEvaluator {
    private WorkbookEvaluator _evaluator;
    private ForkedEvaluationWorkbook _sewb;

    private ForkedEvaluator(EvaluationWorkbook masterWorkbook, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        this._sewb = new ForkedEvaluationWorkbook(masterWorkbook);
        this._evaluator = new WorkbookEvaluator(this._sewb, stabilityClassifier, udfFinder);
    }

    private static EvaluationWorkbook createEvaluationWorkbook(Workbook wb) {
        if (wb instanceof HSSFWorkbook) {
            return HSSFEvaluationWorkbook.create((HSSFWorkbook)wb);
        }
        try {
            Class<?> evalWB = Class.forName("org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook");
            Class<?> xssfWB = Class.forName("org.apache.poi.xssf.usermodel.XSSFWorkbook");
            Method createM = evalWB.getDeclaredMethod("create", xssfWB);
            return (EvaluationWorkbook)createM.invoke(null, wb);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unexpected workbook type (" + wb.getClass().getName() + ") - check for poi-ooxml and poi-ooxml schemas jar in the classpath", e);
        }
    }

    public static ForkedEvaluator create(Workbook wb, IStabilityClassifier stabilityClassifier, UDFFinder udfFinder) {
        return new ForkedEvaluator(ForkedEvaluator.createEvaluationWorkbook(wb), stabilityClassifier, udfFinder);
    }

    public void updateCell(String sheetName, int rowIndex, int columnIndex, ValueEval value) {
        ForkedEvaluationCell cell = this._sewb.getOrCreateUpdatableCell(sheetName, rowIndex, columnIndex);
        cell.setValue(value);
        this._evaluator.notifyUpdateCell(cell);
    }

    public void copyUpdatedCells(Workbook workbook) {
        this._sewb.copyUpdatedCells(workbook);
    }

    public ValueEval evaluate(String sheetName, int rowIndex, int columnIndex) {
        EvaluationCell cell = this._sewb.getEvaluationCell(sheetName, rowIndex, columnIndex);
        switch (cell.getCellType()) {
            case BOOLEAN: {
                return BoolEval.valueOf(cell.getBooleanCellValue());
            }
            case ERROR: {
                return ErrorEval.valueOf(cell.getErrorCellValue());
            }
            case FORMULA: {
                return this._evaluator.evaluate(cell);
            }
            case NUMERIC: {
                return new NumberEval(cell.getNumericCellValue());
            }
            case STRING: {
                return new StringEval(cell.getStringCellValue());
            }
            case BLANK: {
                return null;
            }
        }
        throw new IllegalStateException("Bad cell type (" + (Object)((Object)cell.getCellType()) + ")");
    }

    public static void setupEnvironment(String[] workbookNames, ForkedEvaluator[] evaluators) {
        WorkbookEvaluator[] wbEvals = new WorkbookEvaluator[evaluators.length];
        for (int i = 0; i < wbEvals.length; ++i) {
            wbEvals[i] = evaluators[i]._evaluator;
        }
        CollaboratingWorkbooksEnvironment.setup(workbookNames, wbEvals);
    }
}

