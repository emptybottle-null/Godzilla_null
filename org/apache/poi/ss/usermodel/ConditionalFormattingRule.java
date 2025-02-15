/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.ColorScaleFormatting;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.DataBarFormatting;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.usermodel.PatternFormatting;

public interface ConditionalFormattingRule
extends DifferentialStyleProvider {
    public BorderFormatting createBorderFormatting();

    @Override
    public BorderFormatting getBorderFormatting();

    public FontFormatting createFontFormatting();

    @Override
    public FontFormatting getFontFormatting();

    public PatternFormatting createPatternFormatting();

    @Override
    public PatternFormatting getPatternFormatting();

    public DataBarFormatting getDataBarFormatting();

    public IconMultiStateFormatting getMultiStateFormatting();

    public ColorScaleFormatting getColorScaleFormatting();

    @Override
    public ExcelNumberFormat getNumberFormat();

    public ConditionType getConditionType();

    public ConditionFilterType getConditionFilterType();

    public ConditionFilterData getFilterConfiguration();

    public byte getComparisonOperation();

    public String getFormula1();

    public String getFormula2();

    public String getText();

    public int getPriority();

    public boolean getStopIfTrue();
}

