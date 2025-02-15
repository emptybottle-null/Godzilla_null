/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RangeCopier;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFRangeCopier
extends RangeCopier {
    public XSSFRangeCopier(Sheet sourceSheet, Sheet destSheet) {
        super(sourceSheet, destSheet);
    }

    @Override
    protected void adjustCellReferencesInsideFormula(Cell cell, Sheet destSheet, int deltaX, int deltaY) {
        int destSheetIndex;
        XSSFWorkbook hostWorkbook = (XSSFWorkbook)destSheet.getWorkbook();
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(hostWorkbook);
        Ptg[] ptgs = FormulaParser.parse(cell.getCellFormula(), fpb, FormulaType.CELL, 0);
        if (this.adjustInBothDirections(ptgs, destSheetIndex = hostWorkbook.getSheetIndex(destSheet), deltaX, deltaY)) {
            cell.setCellFormula(FormulaRenderer.toFormulaString(fpb, ptgs));
        }
    }
}

