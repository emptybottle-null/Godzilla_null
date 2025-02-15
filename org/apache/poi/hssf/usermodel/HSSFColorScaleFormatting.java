/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.cf.ColorGradientFormatting;
import org.apache.poi.hssf.record.cf.ColorGradientThreshold;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.apache.poi.hssf.usermodel.HSSFConditionalFormattingThreshold;
import org.apache.poi.hssf.usermodel.HSSFExtendedColor;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.ColorScaleFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;

public final class HSSFColorScaleFormatting
implements ColorScaleFormatting {
    private final HSSFSheet sheet;
    private final CFRule12Record cfRule12Record;
    private final ColorGradientFormatting colorFormatting;

    protected HSSFColorScaleFormatting(CFRule12Record cfRule12Record, HSSFSheet sheet) {
        this.sheet = sheet;
        this.cfRule12Record = cfRule12Record;
        this.colorFormatting = this.cfRule12Record.getColorGradientFormatting();
    }

    @Override
    public int getNumControlPoints() {
        return this.colorFormatting.getNumControlPoints();
    }

    @Override
    public void setNumControlPoints(int num) {
        this.colorFormatting.setNumControlPoints(num);
    }

    public HSSFExtendedColor[] getColors() {
        ExtendedColor[] colors = this.colorFormatting.getColors();
        HSSFExtendedColor[] hcolors = new HSSFExtendedColor[colors.length];
        for (int i = 0; i < colors.length; ++i) {
            hcolors[i] = new HSSFExtendedColor(colors[i]);
        }
        return hcolors;
    }

    @Override
    public void setColors(Color[] colors) {
        ExtendedColor[] cr = new ExtendedColor[colors.length];
        for (int i = 0; i < colors.length; ++i) {
            cr[i] = ((HSSFExtendedColor)colors[i]).getExtendedColor();
        }
        this.colorFormatting.setColors(cr);
    }

    public HSSFConditionalFormattingThreshold[] getThresholds() {
        ColorGradientThreshold[] t = this.colorFormatting.getThresholds();
        HSSFConditionalFormattingThreshold[] ht = new HSSFConditionalFormattingThreshold[t.length];
        for (int i = 0; i < t.length; ++i) {
            ht[i] = new HSSFConditionalFormattingThreshold(t[i], this.sheet);
        }
        return ht;
    }

    @Override
    public void setThresholds(ConditionalFormattingThreshold[] thresholds) {
        ColorGradientThreshold[] t = new ColorGradientThreshold[thresholds.length];
        for (int i = 0; i < t.length; ++i) {
            HSSFConditionalFormattingThreshold hssfT = (HSSFConditionalFormattingThreshold)thresholds[i];
            t[i] = (ColorGradientThreshold)hssfT.getThreshold();
        }
        this.colorFormatting.setThresholds(t);
    }

    @Override
    public HSSFConditionalFormattingThreshold createThreshold() {
        return new HSSFConditionalFormattingThreshold(new ColorGradientThreshold(), this.sheet);
    }
}

