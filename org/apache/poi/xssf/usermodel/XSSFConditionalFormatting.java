/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFConditionalFormattingRule;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTConditionalFormatting;

public class XSSFConditionalFormatting
implements ConditionalFormatting {
    private final CTConditionalFormatting _cf;
    private final XSSFSheet _sh;

    XSSFConditionalFormatting(XSSFSheet sh) {
        this._cf = CTConditionalFormatting.Factory.newInstance();
        this._sh = sh;
    }

    XSSFConditionalFormatting(XSSFSheet sh, CTConditionalFormatting cf) {
        this._cf = cf;
        this._sh = sh;
    }

    CTConditionalFormatting getCTConditionalFormatting() {
        return this._cf;
    }

    @Override
    public CellRangeAddress[] getFormattingRanges() {
        ArrayList<CellRangeAddress> lst = new ArrayList<CellRangeAddress>();
        for (Object stRef : this._cf.getSqref()) {
            String[] regions;
            for (String region : regions = stRef.toString().split(" ")) {
                lst.add(CellRangeAddress.valueOf(region));
            }
        }
        return lst.toArray(new CellRangeAddress[0]);
    }

    @Override
    public void setFormattingRanges(CellRangeAddress[] ranges) {
        if (ranges == null) {
            throw new IllegalArgumentException("cellRanges must not be null");
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (CellRangeAddress range : ranges) {
            if (!first) {
                sb.append(" ");
            } else {
                first = false;
            }
            sb.append(range.formatAsString());
        }
        this._cf.setSqref(Collections.singletonList(sb.toString()));
    }

    @Override
    public void setRule(int idx, ConditionalFormattingRule cfRule) {
        XSSFConditionalFormattingRule xRule = (XSSFConditionalFormattingRule)cfRule;
        this._cf.getCfRuleArray(idx).set(xRule.getCTCfRule());
    }

    @Override
    public void addRule(ConditionalFormattingRule cfRule) {
        XSSFConditionalFormattingRule xRule = (XSSFConditionalFormattingRule)cfRule;
        this._cf.addNewCfRule().set(xRule.getCTCfRule());
    }

    @Override
    public XSSFConditionalFormattingRule getRule(int idx) {
        return new XSSFConditionalFormattingRule(this._sh, this._cf.getCfRuleArray(idx));
    }

    @Override
    public int getNumberOfRules() {
        return this._cf.sizeOfCfRuleArray();
    }

    public String toString() {
        return this._cf.toString();
    }
}

