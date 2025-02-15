/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.cf.DataBarThreshold;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingThreshold;
import org.apache.poi.hssf.usermodel.HSSFExtendedColor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.DataBarFormatting;

public final class HSSFDataBarFormatting
implements DataBarFormatting {
    private final HSSFSheet sheet;
    private final CFRule12Record cfRule12Record;
    private final org.apache.poi.hssf.record.cf.DataBarFormatting databarFormatting;

    protected HSSFDataBarFormatting(CFRule12Record cfRule12Record, HSSFSheet sheet) {
        this.sheet = sheet;
        this.cfRule12Record = cfRule12Record;
        this.databarFormatting = this.cfRule12Record.getDataBarFormatting();
    }

    @Override
    public boolean isLeftToRight() {
        return !this.databarFormatting.isReversed();
    }

    @Override
    public void setLeftToRight(boolean ltr) {
        this.databarFormatting.setReversed(!ltr);
    }

    @Override
    public int getWidthMin() {
        return this.databarFormatting.getPercentMin();
    }

    @Override
    public void setWidthMin(int width) {
        this.databarFormatting.setPercentMin((byte)width);
    }

    @Override
    public int getWidthMax() {
        return this.databarFormatting.getPercentMax();
    }

    @Override
    public void setWidthMax(int width) {
        this.databarFormatting.setPercentMax((byte)width);
    }

    @Override
    public HSSFExtendedColor getColor() {
        return new HSSFExtendedColor(this.databarFormatting.getColor());
    }

    @Override
    public void setColor(Color color) {
        HSSFExtendedColor hcolor = (HSSFExtendedColor)color;
        this.databarFormatting.setColor(hcolor.getExtendedColor());
    }

    @Override
    public HSSFConditionalFormattingThreshold getMinThreshold() {
        return new HSSFConditionalFormattingThreshold(this.databarFormatting.getThresholdMin(), this.sheet);
    }

    @Override
    public HSSFConditionalFormattingThreshold getMaxThreshold() {
        return new HSSFConditionalFormattingThreshold(this.databarFormatting.getThresholdMax(), this.sheet);
    }

    @Override
    public boolean isIconOnly() {
        return this.databarFormatting.isIconOnly();
    }

    @Override
    public void setIconOnly(boolean only) {
        this.databarFormatting.setIconOnly(only);
    }

    public HSSFConditionalFormattingThreshold createThreshold() {
        return new HSSFConditionalFormattingThreshold(new DataBarThreshold(), this.sheet);
    }
}

