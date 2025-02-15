/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.cf.Threshold;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingRule;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;

public final class HSSFConditionalFormattingThreshold
implements ConditionalFormattingThreshold {
    private final Threshold threshold;
    private final HSSFSheet sheet;
    private final HSSFWorkbook workbook;

    protected HSSFConditionalFormattingThreshold(Threshold threshold, HSSFSheet sheet) {
        this.threshold = threshold;
        this.sheet = sheet;
        this.workbook = sheet.getWorkbook();
    }

    protected Threshold getThreshold() {
        return this.threshold;
    }

    @Override
    public ConditionalFormattingThreshold.RangeType getRangeType() {
        return ConditionalFormattingThreshold.RangeType.byId(this.threshold.getType());
    }

    @Override
    public void setRangeType(ConditionalFormattingThreshold.RangeType type) {
        this.threshold.setType((byte)type.id);
    }

    @Override
    public String getFormula() {
        return HSSFConditionalFormattingRule.toFormulaString(this.threshold.getParsedExpression(), this.workbook);
    }

    @Override
    public void setFormula(String formula) {
        this.threshold.setParsedExpression(CFRuleBase.parseFormula(formula, this.sheet));
    }

    @Override
    public Double getValue() {
        return this.threshold.getValue();
    }

    @Override
    public void setValue(Double value) {
        this.threshold.setValue(value);
    }
}

