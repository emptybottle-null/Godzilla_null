/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.record.cf.ColorGradientFormatting;
import org.apache.poi.hssf.record.cf.DataBarFormatting;
import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.record.cf.IconMultiStateFormatting;
import org.apache.poi.hssf.record.cf.PatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFColorScaleFormatting;
import org.apache.poi.hssf.usermodel.HSSFDataBarFormatting;
import org.apache.poi.hssf.usermodel.HSSFFontFormatting;
import org.apache.poi.hssf.usermodel.HSSFIconMultiStateFormatting;
import org.apache.poi.hssf.usermodel.HSSFPatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;

public final class HSSFConditionalFormattingRule
implements ConditionalFormattingRule {
    private static final byte CELL_COMPARISON = 1;
    private final CFRuleBase cfRuleRecord;
    private final HSSFWorkbook workbook;
    private final HSSFSheet sheet;

    HSSFConditionalFormattingRule(HSSFSheet pSheet, CFRuleBase pRuleRecord) {
        if (pSheet == null) {
            throw new IllegalArgumentException("pSheet must not be null");
        }
        if (pRuleRecord == null) {
            throw new IllegalArgumentException("pRuleRecord must not be null");
        }
        this.sheet = pSheet;
        this.workbook = pSheet.getWorkbook();
        this.cfRuleRecord = pRuleRecord;
    }

    @Override
    public int getPriority() {
        CFRule12Record rule12 = this.getCFRule12Record(false);
        if (rule12 == null) {
            return 0;
        }
        return rule12.getPriority();
    }

    @Override
    public boolean getStopIfTrue() {
        return true;
    }

    CFRuleBase getCfRuleRecord() {
        return this.cfRuleRecord;
    }

    private CFRule12Record getCFRule12Record(boolean create) {
        if (!(this.cfRuleRecord instanceof CFRule12Record)) {
            if (create) {
                throw new IllegalArgumentException("Can't convert a CF into a CF12 record");
            }
            return null;
        }
        return (CFRule12Record)this.cfRuleRecord;
    }

    @Override
    public ExcelNumberFormat getNumberFormat() {
        return null;
    }

    private HSSFFontFormatting getFontFormatting(boolean create) {
        FontFormatting fontFormatting = this.cfRuleRecord.getFontFormatting();
        if (fontFormatting == null) {
            if (!create) {
                return null;
            }
            fontFormatting = new FontFormatting();
            this.cfRuleRecord.setFontFormatting(fontFormatting);
        }
        return new HSSFFontFormatting(this.cfRuleRecord, this.workbook);
    }

    @Override
    public HSSFFontFormatting getFontFormatting() {
        return this.getFontFormatting(false);
    }

    @Override
    public HSSFFontFormatting createFontFormatting() {
        return this.getFontFormatting(true);
    }

    private HSSFBorderFormatting getBorderFormatting(boolean create) {
        BorderFormatting borderFormatting = this.cfRuleRecord.getBorderFormatting();
        if (borderFormatting == null) {
            if (!create) {
                return null;
            }
            borderFormatting = new BorderFormatting();
            this.cfRuleRecord.setBorderFormatting(borderFormatting);
        }
        return new HSSFBorderFormatting(this.cfRuleRecord, this.workbook);
    }

    @Override
    public HSSFBorderFormatting getBorderFormatting() {
        return this.getBorderFormatting(false);
    }

    @Override
    public HSSFBorderFormatting createBorderFormatting() {
        return this.getBorderFormatting(true);
    }

    private HSSFPatternFormatting getPatternFormatting(boolean create) {
        PatternFormatting patternFormatting = this.cfRuleRecord.getPatternFormatting();
        if (patternFormatting == null) {
            if (!create) {
                return null;
            }
            patternFormatting = new PatternFormatting();
            this.cfRuleRecord.setPatternFormatting(patternFormatting);
        }
        return new HSSFPatternFormatting(this.cfRuleRecord, this.workbook);
    }

    @Override
    public HSSFPatternFormatting getPatternFormatting() {
        return this.getPatternFormatting(false);
    }

    @Override
    public HSSFPatternFormatting createPatternFormatting() {
        return this.getPatternFormatting(true);
    }

    private HSSFDataBarFormatting getDataBarFormatting(boolean create) {
        CFRule12Record cfRule12Record = this.getCFRule12Record(create);
        if (cfRule12Record == null) {
            return null;
        }
        DataBarFormatting databarFormatting = cfRule12Record.getDataBarFormatting();
        if (databarFormatting == null) {
            if (!create) {
                return null;
            }
            cfRule12Record.createDataBarFormatting();
        }
        return new HSSFDataBarFormatting(cfRule12Record, this.sheet);
    }

    @Override
    public HSSFDataBarFormatting getDataBarFormatting() {
        return this.getDataBarFormatting(false);
    }

    public HSSFDataBarFormatting createDataBarFormatting() {
        return this.getDataBarFormatting(true);
    }

    private HSSFIconMultiStateFormatting getMultiStateFormatting(boolean create) {
        CFRule12Record cfRule12Record = this.getCFRule12Record(create);
        if (cfRule12Record == null) {
            return null;
        }
        IconMultiStateFormatting iconFormatting = cfRule12Record.getMultiStateFormatting();
        if (iconFormatting == null) {
            if (!create) {
                return null;
            }
            cfRule12Record.createMultiStateFormatting();
        }
        return new HSSFIconMultiStateFormatting(cfRule12Record, this.sheet);
    }

    @Override
    public HSSFIconMultiStateFormatting getMultiStateFormatting() {
        return this.getMultiStateFormatting(false);
    }

    public HSSFIconMultiStateFormatting createMultiStateFormatting() {
        return this.getMultiStateFormatting(true);
    }

    private HSSFColorScaleFormatting getColorScaleFormatting(boolean create) {
        CFRule12Record cfRule12Record = this.getCFRule12Record(create);
        if (cfRule12Record == null) {
            return null;
        }
        ColorGradientFormatting colorFormatting = cfRule12Record.getColorGradientFormatting();
        if (colorFormatting == null) {
            if (!create) {
                return null;
            }
            cfRule12Record.createColorGradientFormatting();
        }
        return new HSSFColorScaleFormatting(cfRule12Record, this.sheet);
    }

    @Override
    public HSSFColorScaleFormatting getColorScaleFormatting() {
        return this.getColorScaleFormatting(false);
    }

    public HSSFColorScaleFormatting createColorScaleFormatting() {
        return this.getColorScaleFormatting(true);
    }

    @Override
    public ConditionType getConditionType() {
        byte code = this.cfRuleRecord.getConditionType();
        return ConditionType.forId(code);
    }

    @Override
    public ConditionFilterType getConditionFilterType() {
        return this.getConditionType() == ConditionType.FILTER ? ConditionFilterType.FILTER : null;
    }

    @Override
    public ConditionFilterData getFilterConfiguration() {
        return null;
    }

    @Override
    public byte getComparisonOperation() {
        return this.cfRuleRecord.getComparisonOperation();
    }

    @Override
    public String getFormula1() {
        return this.toFormulaString(this.cfRuleRecord.getParsedExpression1());
    }

    @Override
    public String getFormula2() {
        byte conditionType = this.cfRuleRecord.getConditionType();
        if (conditionType == 1) {
            byte comparisonOperation = this.cfRuleRecord.getComparisonOperation();
            switch (comparisonOperation) {
                case 1: 
                case 2: {
                    return this.toFormulaString(this.cfRuleRecord.getParsedExpression2());
                }
            }
        }
        return null;
    }

    @Override
    public String getText() {
        return null;
    }

    protected String toFormulaString(Ptg[] parsedExpression) {
        return HSSFConditionalFormattingRule.toFormulaString(parsedExpression, this.workbook);
    }

    protected static String toFormulaString(Ptg[] parsedExpression, HSSFWorkbook workbook) {
        if (parsedExpression == null || parsedExpression.length == 0) {
            return null;
        }
        return HSSFFormulaParser.toFormulaString(workbook, parsedExpression);
    }

    @Override
    public int getStripeSize() {
        return 0;
    }
}

