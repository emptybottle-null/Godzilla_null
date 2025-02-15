/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.formula.EvaluationConditionalFormatRule;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

public class ConditionalFormattingEvaluator {
    private final WorkbookEvaluator workbookEvaluator;
    private final Workbook workbook;
    private final Map<String, List<EvaluationConditionalFormatRule>> formats = new HashMap<String, List<EvaluationConditionalFormatRule>>();
    private final Map<CellReference, List<EvaluationConditionalFormatRule>> values = new HashMap<CellReference, List<EvaluationConditionalFormatRule>>();

    public ConditionalFormattingEvaluator(Workbook wb, WorkbookEvaluatorProvider provider) {
        this.workbook = wb;
        this.workbookEvaluator = provider._getWorkbookEvaluator();
    }

    protected WorkbookEvaluator getWorkbookEvaluator() {
        return this.workbookEvaluator;
    }

    public void clearAllCachedFormats() {
        this.formats.clear();
    }

    public void clearAllCachedValues() {
        this.values.clear();
    }

    protected List<EvaluationConditionalFormatRule> getRules(Sheet sheet) {
        String sheetName = sheet.getSheetName();
        List<EvaluationConditionalFormatRule> rules = this.formats.get(sheetName);
        if (rules == null) {
            if (this.formats.containsKey(sheetName)) {
                return Collections.emptyList();
            }
            SheetConditionalFormatting scf = sheet.getSheetConditionalFormatting();
            int count = scf.getNumConditionalFormattings();
            rules = new ArrayList<EvaluationConditionalFormatRule>(count);
            this.formats.put(sheetName, rules);
            for (int i = 0; i < count; ++i) {
                ConditionalFormatting f = scf.getConditionalFormattingAt(i);
                CellRangeAddress[] regions = f.getFormattingRanges();
                for (int r = 0; r < f.getNumberOfRules(); ++r) {
                    ConditionalFormattingRule rule = f.getRule(r);
                    rules.add(new EvaluationConditionalFormatRule(this.workbookEvaluator, sheet, f, i, rule, r, regions));
                }
            }
            Collections.sort(rules);
        }
        return Collections.unmodifiableList(rules);
    }

    public List<EvaluationConditionalFormatRule> getConditionalFormattingForCell(CellReference cellRef) {
        List<EvaluationConditionalFormatRule> rules = this.values.get(cellRef);
        if (rules == null) {
            rules = new ArrayList<EvaluationConditionalFormatRule>();
            Sheet sheet = cellRef.getSheetName() != null ? this.workbook.getSheet(cellRef.getSheetName()) : this.workbook.getSheetAt(this.workbook.getActiveSheetIndex());
            boolean stopIfTrue = false;
            for (EvaluationConditionalFormatRule rule : this.getRules(sheet)) {
                if (stopIfTrue || !rule.matches(cellRef)) continue;
                rules.add(rule);
                stopIfTrue = rule.getRule().getStopIfTrue();
            }
            Collections.sort(rules);
            this.values.put(cellRef, rules);
        }
        return Collections.unmodifiableList(rules);
    }

    public List<EvaluationConditionalFormatRule> getConditionalFormattingForCell(Cell cell) {
        return this.getConditionalFormattingForCell(ConditionalFormattingEvaluator.getRef(cell));
    }

    public static CellReference getRef(Cell cell) {
        return new CellReference(cell.getSheet().getSheetName(), cell.getRowIndex(), cell.getColumnIndex(), false, false);
    }

    public List<EvaluationConditionalFormatRule> getFormatRulesForSheet(String sheetName) {
        return this.getFormatRulesForSheet(this.workbook.getSheet(sheetName));
    }

    public List<EvaluationConditionalFormatRule> getFormatRulesForSheet(Sheet sheet) {
        return this.getRules(sheet);
    }

    public List<Cell> getMatchingCells(Sheet sheet, int conditionalFormattingIndex, int ruleIndex) {
        for (EvaluationConditionalFormatRule rule : this.getRules(sheet)) {
            if (!rule.getSheet().equals(sheet) || rule.getFormattingIndex() != conditionalFormattingIndex || rule.getRuleIndex() != ruleIndex) continue;
            return this.getMatchingCells(rule);
        }
        return Collections.emptyList();
    }

    public List<Cell> getMatchingCells(EvaluationConditionalFormatRule rule) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        Sheet sheet = rule.getSheet();
        for (CellRangeAddress region : rule.getRegions()) {
            for (int r = region.getFirstRow(); r <= region.getLastRow(); ++r) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                for (int c = region.getFirstColumn(); c <= region.getLastColumn(); ++c) {
                    List<EvaluationConditionalFormatRule> cellRules;
                    Cell cell = row.getCell(c);
                    if (cell == null || !(cellRules = this.getConditionalFormattingForCell(cell)).contains(rule)) continue;
                    cells.add(cell);
                }
            }
        }
        return Collections.unmodifiableList(cells);
    }
}

