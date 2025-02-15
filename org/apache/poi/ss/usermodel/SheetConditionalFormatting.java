/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExtendedColor;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.ss.util.CellRangeAddress;

public interface SheetConditionalFormatting {
    public int addConditionalFormatting(CellRangeAddress[] var1, ConditionalFormattingRule var2);

    public int addConditionalFormatting(CellRangeAddress[] var1, ConditionalFormattingRule var2, ConditionalFormattingRule var3);

    public int addConditionalFormatting(CellRangeAddress[] var1, ConditionalFormattingRule[] var2);

    public int addConditionalFormatting(ConditionalFormatting var1);

    public ConditionalFormattingRule createConditionalFormattingRule(byte var1, String var2, String var3);

    public ConditionalFormattingRule createConditionalFormattingRule(byte var1, String var2);

    public ConditionalFormattingRule createConditionalFormattingRule(String var1);

    public ConditionalFormattingRule createConditionalFormattingRule(ExtendedColor var1);

    public ConditionalFormattingRule createConditionalFormattingRule(IconMultiStateFormatting.IconSet var1);

    public ConditionalFormattingRule createConditionalFormattingColorScaleRule();

    public ConditionalFormatting getConditionalFormattingAt(int var1);

    public int getNumConditionalFormattings();

    public void removeConditionalFormatting(int var1);
}

