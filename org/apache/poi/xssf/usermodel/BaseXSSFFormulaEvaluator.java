/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.BaseXSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public abstract class BaseXSSFFormulaEvaluator
extends BaseFormulaEvaluator {
    protected BaseXSSFFormulaEvaluator(WorkbookEvaluator bookEvaluator) {
        super(bookEvaluator);
    }

    @Override
    protected RichTextString createRichTextString(String str) {
        return new XSSFRichTextString(str);
    }

    protected abstract EvaluationCell toEvaluationCell(Cell var1);

    @Override
    protected CellValue evaluateFormulaCellValue(Cell cell) {
        EvaluationCell evalCell = this.toEvaluationCell(cell);
        ValueEval eval = this._bookEvaluator.evaluate(evalCell);
        if (eval instanceof NumberEval) {
            NumberEval ne = (NumberEval)eval;
            return new CellValue(ne.getNumberValue());
        }
        if (eval instanceof BoolEval) {
            BoolEval be = (BoolEval)eval;
            return CellValue.valueOf(be.getBooleanValue());
        }
        if (eval instanceof StringEval) {
            StringEval ne = (StringEval)eval;
            return new CellValue(ne.getStringValue());
        }
        if (eval instanceof ErrorEval) {
            return CellValue.getError(((ErrorEval)eval).getErrorCode());
        }
        throw new RuntimeException("Unexpected eval class (" + eval.getClass().getName() + ")");
    }

    @Override
    protected void setCellType(Cell cell, CellType cellType) {
        if (cell instanceof XSSFCell) {
            EvaluationWorkbook evaluationWorkbook = this.getEvaluationWorkbook();
            BaseXSSFEvaluationWorkbook xewb = BaseXSSFEvaluationWorkbook.class.isAssignableFrom(evaluationWorkbook.getClass()) ? (BaseXSSFEvaluationWorkbook)evaluationWorkbook : null;
            ((XSSFCell)cell).setCellType(cellType, xewb);
        } else {
            cell.setCellType(cellType);
        }
    }
}

