/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.model;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.Internal;

@Internal
public final class HSSFFormulaParser {
    private static FormulaParsingWorkbook createParsingWorkbook(HSSFWorkbook book) {
        return HSSFEvaluationWorkbook.create(book);
    }

    private HSSFFormulaParser() {
    }

    public static Ptg[] parse(String formula, HSSFWorkbook workbook) throws FormulaParseException {
        return HSSFFormulaParser.parse(formula, workbook, FormulaType.CELL);
    }

    public static Ptg[] parse(String formula, HSSFWorkbook workbook, FormulaType formulaType) throws FormulaParseException {
        return HSSFFormulaParser.parse(formula, workbook, formulaType, -1);
    }

    public static Ptg[] parse(String formula, HSSFWorkbook workbook, FormulaType formulaType, int sheetIndex) throws FormulaParseException {
        return FormulaParser.parse(formula, HSSFFormulaParser.createParsingWorkbook(workbook), formulaType, sheetIndex);
    }

    public static String toFormulaString(HSSFWorkbook book, Ptg[] ptgs) {
        return FormulaRenderer.toFormulaString(HSSFEvaluationWorkbook.create(book), ptgs);
    }
}

