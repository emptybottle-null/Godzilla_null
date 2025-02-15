/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.aggregates.CFRecordsAggregate;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingRule;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;

public final class HSSFConditionalFormatting
implements ConditionalFormatting {
    private final HSSFSheet sheet;
    private final CFRecordsAggregate cfAggregate;

    HSSFConditionalFormatting(HSSFSheet sheet, CFRecordsAggregate cfAggregate) {
        if (sheet == null) {
            throw new IllegalArgumentException("sheet must not be null");
        }
        if (cfAggregate == null) {
            throw new IllegalArgumentException("cfAggregate must not be null");
        }
        this.sheet = sheet;
        this.cfAggregate = cfAggregate;
    }

    CFRecordsAggregate getCFRecordsAggregate() {
        return this.cfAggregate;
    }

    @Override
    public CellRangeAddress[] getFormattingRanges() {
        return this.cfAggregate.getHeader().getCellRanges();
    }

    @Override
    public void setFormattingRanges(CellRangeAddress[] ranges) {
        this.cfAggregate.getHeader().setCellRanges(ranges);
    }

    public void setRule(int idx, HSSFConditionalFormattingRule cfRule) {
        this.cfAggregate.setRule(idx, cfRule.getCfRuleRecord());
    }

    @Override
    public void setRule(int idx, ConditionalFormattingRule cfRule) {
        this.setRule(idx, (HSSFConditionalFormattingRule)cfRule);
    }

    public void addRule(HSSFConditionalFormattingRule cfRule) {
        this.cfAggregate.addRule(cfRule.getCfRuleRecord());
    }

    @Override
    public void addRule(ConditionalFormattingRule cfRule) {
        this.addRule((HSSFConditionalFormattingRule)cfRule);
    }

    @Override
    public HSSFConditionalFormattingRule getRule(int idx) {
        CFRuleBase ruleRecord = this.cfAggregate.getRule(idx);
        return new HSSFConditionalFormattingRule(this.sheet, ruleRecord);
    }

    @Override
    public int getNumberOfRules() {
        return this.cfAggregate.getNumberOfRules();
    }

    public String toString() {
        return this.cfAggregate.toString();
    }
}

