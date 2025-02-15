/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.PatternFormatting;

public class HSSFPatternFormatting
implements PatternFormatting {
    private final HSSFWorkbook workbook;
    private final CFRuleBase cfRuleRecord;
    private final org.apache.poi.hssf.record.cf.PatternFormatting patternFormatting;

    protected HSSFPatternFormatting(CFRuleBase cfRuleRecord, HSSFWorkbook workbook) {
        this.workbook = workbook;
        this.cfRuleRecord = cfRuleRecord;
        this.patternFormatting = cfRuleRecord.getPatternFormatting();
    }

    protected org.apache.poi.hssf.record.cf.PatternFormatting getPatternFormattingBlock() {
        return this.patternFormatting;
    }

    @Override
    public HSSFColor getFillBackgroundColorColor() {
        return this.workbook.getCustomPalette().getColor(this.getFillBackgroundColor());
    }

    @Override
    public HSSFColor getFillForegroundColorColor() {
        return this.workbook.getCustomPalette().getColor(this.getFillForegroundColor());
    }

    @Override
    public short getFillBackgroundColor() {
        return (short)this.patternFormatting.getFillBackgroundColor();
    }

    @Override
    public short getFillForegroundColor() {
        return (short)this.patternFormatting.getFillForegroundColor();
    }

    @Override
    public short getFillPattern() {
        return (short)this.patternFormatting.getFillPattern();
    }

    @Override
    public void setFillBackgroundColor(Color bg) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(bg);
        if (hcolor == null) {
            this.setFillBackgroundColor((short)0);
        } else {
            this.setFillBackgroundColor(hcolor.getIndex());
        }
    }

    @Override
    public void setFillForegroundColor(Color fg) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(fg);
        if (hcolor == null) {
            this.setFillForegroundColor((short)0);
        } else {
            this.setFillForegroundColor(hcolor.getIndex());
        }
    }

    @Override
    public void setFillBackgroundColor(short bg) {
        this.patternFormatting.setFillBackgroundColor(bg);
        if (bg != 0) {
            this.cfRuleRecord.setPatternBackgroundColorModified(true);
        }
    }

    @Override
    public void setFillForegroundColor(short fg) {
        this.patternFormatting.setFillForegroundColor(fg);
        if (fg != 0) {
            this.cfRuleRecord.setPatternColorModified(true);
        }
    }

    @Override
    public void setFillPattern(short fp) {
        this.patternFormatting.setFillPattern(fp);
        if (fp != 0) {
            this.cfRuleRecord.setPatternStyleModified(true);
        }
    }
}

