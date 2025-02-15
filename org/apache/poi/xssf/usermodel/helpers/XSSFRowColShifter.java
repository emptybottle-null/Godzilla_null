/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel.helpers;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType;

@Internal
final class XSSFRowColShifter {
    private static final POILogger logger = POILogFactory.getLogger(XSSFRowColShifter.class);

    private XSSFRowColShifter() {
    }

    static void updateNamedRanges(Sheet sheet, FormulaShifter formulaShifter) {
        Workbook wb = sheet.getWorkbook();
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create((XSSFWorkbook)wb);
        for (Name name : wb.getAllNames()) {
            String formula = name.getRefersToFormula();
            int sheetIndex = name.getSheetIndex();
            int rowIndex = -1;
            Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.NAMEDRANGE, sheetIndex, -1);
            if (!formulaShifter.adjustFormula(ptgs, sheetIndex)) continue;
            String shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
            name.setRefersToFormula(shiftedFmla);
        }
    }

    static void updateFormulas(Sheet sheet, FormulaShifter formulaShifter) {
        XSSFRowColShifter.updateSheetFormulas(sheet, formulaShifter);
        Workbook wb = sheet.getWorkbook();
        for (Sheet sh : wb) {
            if (sheet == sh) continue;
            XSSFRowColShifter.updateSheetFormulas(sh, formulaShifter);
        }
    }

    static void updateSheetFormulas(Sheet sh, FormulaShifter formulashifter) {
        for (Row r : sh) {
            XSSFRow row = (XSSFRow)r;
            XSSFRowColShifter.updateRowFormulas(row, formulashifter);
        }
    }

    static void updateRowFormulas(XSSFRow row, FormulaShifter formulaShifter) {
        XSSFSheet sheet = row.getSheet();
        for (Cell c : row) {
            String shiftedFormula;
            XSSFCell cell = (XSSFCell)c;
            CTCell ctCell = cell.getCTCell();
            if (!ctCell.isSetF()) continue;
            CTCellFormula f = ctCell.getF();
            String formula = f.getStringValue();
            if (formula.length() > 0 && (shiftedFormula = XSSFRowColShifter.shiftFormula(row, formula, formulaShifter)) != null) {
                f.setStringValue(shiftedFormula);
                if (f.getT() == STCellFormulaType.SHARED) {
                    int si = Math.toIntExact(f.getSi());
                    CTCellFormula sf = sheet.getSharedFormula(si);
                    sf.setStringValue(shiftedFormula);
                    XSSFRowColShifter.updateRefInCTCellFormula(row, formulaShifter, sf);
                }
            }
            XSSFRowColShifter.updateRefInCTCellFormula(row, formulaShifter, f);
        }
    }

    static String shiftFormula(Row row, String formula, FormulaShifter formulaShifter) {
        Sheet sheet = row.getSheet();
        Workbook wb = sheet.getWorkbook();
        int sheetIndex = wb.getSheetIndex(sheet);
        int rowIndex = row.getRowNum();
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create((XSSFWorkbook)wb);
        try {
            Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.CELL, sheetIndex, rowIndex);
            String shiftedFmla = null;
            if (formulaShifter.adjustFormula(ptgs, sheetIndex)) {
                shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
            }
            return shiftedFmla;
        } catch (FormulaParseException fpe) {
            logger.log(5, "Error shifting formula on row ", row.getRowNum(), fpe);
            return formula;
        }
    }

    static void updateRefInCTCellFormula(Row row, FormulaShifter formulaShifter, CTCellFormula f) {
        String ref;
        String shiftedRef;
        if (f.isSetRef() && (shiftedRef = XSSFRowColShifter.shiftFormula(row, ref = f.getRef(), formulaShifter)) != null) {
            f.setRef(shiftedRef);
        }
    }

    static void updateConditionalFormatting(Sheet sheet, FormulaShifter formulaShifter) {
        XSSFSheet xsheet = (XSSFSheet)sheet;
        XSSFWorkbook wb = xsheet.getWorkbook();
        int sheetIndex = wb.getSheetIndex(sheet);
        int rowIndex = -1;
        XSSFEvaluationWorkbook fpb = XSSFEvaluationWorkbook.create(wb);
        CTWorksheet ctWorksheet = xsheet.getCTWorksheet();
        CTConditionalFormatting[] conditionalFormattingArray = ctWorksheet.getConditionalFormattingArray();
        for (int j = conditionalFormattingArray.length - 1; j >= 0; --j) {
            CTConditionalFormatting cf = conditionalFormattingArray[j];
            ArrayList<CellRangeAddress> cellRanges = new ArrayList<CellRangeAddress>();
            for (Object stRef : cf.getSqref()) {
                String[] regions = stRef.toString().split(" ");
                for (String region : regions) {
                    cellRanges.add(CellRangeAddress.valueOf(region));
                }
            }
            boolean changed = false;
            ArrayList<CellRangeAddress> temp = new ArrayList<CellRangeAddress>();
            for (CellRangeAddress craOld : cellRanges) {
                CellRangeAddress craNew = BaseRowColShifter.shiftRange(formulaShifter, craOld, sheetIndex);
                if (craNew == null) {
                    changed = true;
                    continue;
                }
                temp.add(craNew);
                if (craNew == craOld) continue;
                changed = true;
            }
            if (changed) {
                int nRanges = temp.size();
                if (nRanges == 0) {
                    ctWorksheet.removeConditionalFormatting(j);
                    continue;
                }
                ArrayList<String> refs = new ArrayList<String>();
                for (CellRangeAddress a : temp) {
                    refs.add(a.formatAsString());
                }
                cf.setSqref(refs);
            }
            for (CTCfRule cfRule : cf.getCfRuleArray()) {
                String[] formulaArray = cfRule.getFormulaArray();
                for (int i = 0; i < formulaArray.length; ++i) {
                    String formula = formulaArray[i];
                    Ptg[] ptgs = FormulaParser.parse(formula, fpb, FormulaType.CELL, sheetIndex, -1);
                    if (!formulaShifter.adjustFormula(ptgs, sheetIndex)) continue;
                    String shiftedFmla = FormulaRenderer.toFormulaString(fpb, ptgs);
                    cfRule.setFormulaArray(i, shiftedFmla);
                }
            }
        }
    }

    static void updateHyperlinks(Sheet sheet, FormulaShifter formulaShifter) {
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        List<? extends Hyperlink> hyperlinkList = sheet.getHyperlinkList();
        for (Hyperlink hyperlink : hyperlinkList) {
            XSSFHyperlink xhyperlink = (XSSFHyperlink)hyperlink;
            String cellRef = xhyperlink.getCellRef();
            CellRangeAddress cra = CellRangeAddress.valueOf(cellRef);
            CellRangeAddress shiftedRange = BaseRowColShifter.shiftRange(formulaShifter, cra, sheetIndex);
            if (shiftedRange == null || shiftedRange == cra) continue;
            xhyperlink.setCellReference(shiftedRange.formatAsString());
        }
    }
}

