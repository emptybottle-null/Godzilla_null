/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeUtil;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormatting;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STConditionalFormattingOperator;

public class XSSFSheetConditionalFormatting
implements SheetConditionalFormatting {
    protected static final String CF_EXT_2009_NS_X14 = "http://schemas.microsoft.com/office/spreadsheetml/2009/9/main";
    private final XSSFSheet _sheet;

    XSSFSheetConditionalFormatting(XSSFSheet sheet) {
        this._sheet = sheet;
    }

    @Override
    public XSSFConditionalFormattingRule createConditionalFormattingRule(byte comparisonOperation, String formula1, String formula2) {
        STConditionalFormattingOperator.Enum operator2;
        XSSFConditionalFormattingRule rule = new XSSFConditionalFormattingRule(this._sheet);
        CTCfRule cfRule = rule.getCTCfRule();
        cfRule.addFormula(formula1);
        if (formula2 != null) {
            cfRule.addFormula(formula2);
        }
        cfRule.setType(STCfType.CELL_IS);
        switch (comparisonOperation) {
            case 1: {
                operator2 = STConditionalFormattingOperator.BETWEEN;
                break;
            }
            case 2: {
                operator2 = STConditionalFormattingOperator.NOT_BETWEEN;
                break;
            }
            case 6: {
                operator2 = STConditionalFormattingOperator.LESS_THAN;
                break;
            }
            case 8: {
                operator2 = STConditionalFormattingOperator.LESS_THAN_OR_EQUAL;
                break;
            }
            case 5: {
                operator2 = STConditionalFormattingOperator.GREATER_THAN;
                break;
            }
            case 7: {
                operator2 = STConditionalFormattingOperator.GREATER_THAN_OR_EQUAL;
                break;
            }
            case 3: {
                operator2 = STConditionalFormattingOperator.EQUAL;
                break;
            }
            case 4: {
                operator2 = STConditionalFormattingOperator.NOT_EQUAL;
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown comparison operator: " + comparisonOperation);
            }
        }
        cfRule.setOperator(operator2);
        return rule;
    }

    @Override
    public XSSFConditionalFormattingRule createConditionalFormattingRule(byte comparisonOperation, String formula) {
        return this.createConditionalFormattingRule(comparisonOperation, formula, null);
    }

    @Override
    public XSSFConditionalFormattingRule createConditionalFormattingRule(String formula) {
        XSSFConditionalFormattingRule rule = new XSSFConditionalFormattingRule(this._sheet);
        CTCfRule cfRule = rule.getCTCfRule();
        cfRule.addFormula(formula);
        cfRule.setType(STCfType.EXPRESSION);
        return rule;
    }

    public XSSFConditionalFormattingRule createConditionalFormattingRule(XSSFColor color) {
        XSSFConditionalFormattingRule rule = new XSSFConditionalFormattingRule(this._sheet);
        rule.createDataBarFormatting(color);
        return rule;
    }

    @Override
    public XSSFConditionalFormattingRule createConditionalFormattingRule(ExtendedColor color) {
        return this.createConditionalFormattingRule((XSSFColor)color);
    }

    @Override
    public XSSFConditionalFormattingRule createConditionalFormattingRule(IconMultiStateFormatting.IconSet iconSet) {
        XSSFConditionalFormattingRule rule = new XSSFConditionalFormattingRule(this._sheet);
        rule.createMultiStateFormatting(iconSet);
        return rule;
    }

    @Override
    public XSSFConditionalFormattingRule createConditionalFormattingColorScaleRule() {
        XSSFConditionalFormattingRule rule = new XSSFConditionalFormattingRule(this._sheet);
        rule.createColorScaleFormatting();
        return rule;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public int addConditionalFormatting(CellRangeAddress[] regions, ConditionalFormattingRule[] cfRules) {
        if (regions == null) {
            throw new IllegalArgumentException("regions must not be null");
        }
        for (CellRangeAddress cellRangeAddress : regions) {
            cellRangeAddress.validate(SpreadsheetVersion.EXCEL2007);
        }
        if (cfRules == null) {
            throw new IllegalArgumentException("cfRules must not be null");
        }
        if (cfRules.length == 0) {
            throw new IllegalArgumentException("cfRules must not be empty");
        }
        if (cfRules.length > 3) {
            throw new IllegalArgumentException("Number of rules must not exceed 3");
        }
        CellRangeAddress[] mergeCellRanges = CellRangeUtil.mergeCellRanges(regions);
        CTConditionalFormatting cf = this._sheet.getCTWorksheet().addNewConditionalFormatting();
        ArrayList<String> refs = new ArrayList<String>();
        for (CellRangeAddress a : mergeCellRanges) {
            refs.add(a.formatAsString());
        }
        cf.setSqref(refs);
        boolean bl = true;
        for (CTConditionalFormatting c : this._sheet.getCTWorksheet().getConditionalFormattingArray()) {
            var6_11 += c.sizeOfCfRuleArray();
        }
        for (ConditionalFormattingRule rule : cfRules) {
            void var6_12;
            XSSFConditionalFormattingRule xRule = (XSSFConditionalFormattingRule)rule;
            xRule.getCTCfRule().setPriority((int)(++var6_12));
            cf.addNewCfRule().set(xRule.getCTCfRule());
        }
        return this._sheet.getCTWorksheet().sizeOfConditionalFormattingArray() - 1;
    }

    @Override
    public int addConditionalFormatting(CellRangeAddress[] regions, ConditionalFormattingRule rule1) {
        XSSFConditionalFormattingRule[] xSSFConditionalFormattingRuleArray;
        if (rule1 == null) {
            xSSFConditionalFormattingRuleArray = null;
        } else {
            XSSFConditionalFormattingRule[] xSSFConditionalFormattingRuleArray2 = new XSSFConditionalFormattingRule[1];
            xSSFConditionalFormattingRuleArray = xSSFConditionalFormattingRuleArray2;
            xSSFConditionalFormattingRuleArray2[0] = (XSSFConditionalFormattingRule)rule1;
        }
        return this.addConditionalFormatting(regions, xSSFConditionalFormattingRuleArray);
    }

    @Override
    public int addConditionalFormatting(CellRangeAddress[] regions, ConditionalFormattingRule rule1, ConditionalFormattingRule rule2) {
        XSSFConditionalFormattingRule[] xSSFConditionalFormattingRuleArray;
        if (rule1 == null) {
            xSSFConditionalFormattingRuleArray = null;
        } else {
            XSSFConditionalFormattingRule[] xSSFConditionalFormattingRuleArray2 = new XSSFConditionalFormattingRule[2];
            xSSFConditionalFormattingRuleArray2[0] = (XSSFConditionalFormattingRule)rule1;
            xSSFConditionalFormattingRuleArray = xSSFConditionalFormattingRuleArray2;
            xSSFConditionalFormattingRuleArray2[1] = (XSSFConditionalFormattingRule)rule2;
        }
        return this.addConditionalFormatting(regions, xSSFConditionalFormattingRuleArray);
    }

    @Override
    public int addConditionalFormatting(ConditionalFormatting cf) {
        XSSFConditionalFormatting xcf = (XSSFConditionalFormatting)cf;
        CTWorksheet sh = this._sheet.getCTWorksheet();
        sh.addNewConditionalFormatting().set(xcf.getCTConditionalFormatting().copy());
        return sh.sizeOfConditionalFormattingArray() - 1;
    }

    @Override
    public XSSFConditionalFormatting getConditionalFormattingAt(int index) {
        this.checkIndex(index);
        CTConditionalFormatting cf = this._sheet.getCTWorksheet().getConditionalFormattingArray(index);
        return new XSSFConditionalFormatting(this._sheet, cf);
    }

    @Override
    public int getNumConditionalFormattings() {
        return this._sheet.getCTWorksheet().sizeOfConditionalFormattingArray();
    }

    @Override
    public void removeConditionalFormatting(int index) {
        this.checkIndex(index);
        this._sheet.getCTWorksheet().removeConditionalFormatting(index);
    }

    private void checkIndex(int index) {
        int cnt = this.getNumConditionalFormattings();
        if (index < 0 || index >= cnt) {
            throw new IllegalArgumentException("Specified CF index " + index + " is outside the allowable range (0.." + (cnt - 1) + ")");
        }
    }
}

