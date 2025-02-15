/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FontFormatting;

public final class HSSFFontFormatting
implements FontFormatting {
    public static final byte U_NONE = 0;
    public static final byte U_SINGLE = 1;
    public static final byte U_DOUBLE = 2;
    public static final byte U_SINGLE_ACCOUNTING = 33;
    public static final byte U_DOUBLE_ACCOUNTING = 34;
    private final org.apache.poi.hssf.record.cf.FontFormatting fontFormatting;
    private final HSSFWorkbook workbook;

    protected HSSFFontFormatting(CFRuleBase cfRuleRecord, HSSFWorkbook workbook) {
        this.fontFormatting = cfRuleRecord.getFontFormatting();
        this.workbook = workbook;
    }

    protected org.apache.poi.hssf.record.cf.FontFormatting getFontFormattingBlock() {
        return this.fontFormatting;
    }

    @Override
    public short getEscapementType() {
        return this.fontFormatting.getEscapementType();
    }

    @Override
    public short getFontColorIndex() {
        return this.fontFormatting.getFontColorIndex();
    }

    @Override
    public HSSFColor getFontColor() {
        return this.workbook.getCustomPalette().getColor(this.getFontColorIndex());
    }

    @Override
    public void setFontColor(Color color) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(color);
        if (hcolor == null) {
            this.fontFormatting.setFontColorIndex((short)0);
        } else {
            this.fontFormatting.setFontColorIndex(hcolor.getIndex());
        }
    }

    @Override
    public int getFontHeight() {
        return this.fontFormatting.getFontHeight();
    }

    public short getFontWeight() {
        return this.fontFormatting.getFontWeight();
    }

    protected byte[] getRawRecord() {
        return this.fontFormatting.getRawRecord();
    }

    @Override
    public short getUnderlineType() {
        return this.fontFormatting.getUnderlineType();
    }

    @Override
    public boolean isBold() {
        return this.fontFormatting.isFontWeightModified() && this.fontFormatting.isBold();
    }

    public boolean isEscapementTypeModified() {
        return this.fontFormatting.isEscapementTypeModified();
    }

    public boolean isFontCancellationModified() {
        return this.fontFormatting.isFontCancellationModified();
    }

    public boolean isFontOutlineModified() {
        return this.fontFormatting.isFontOutlineModified();
    }

    public boolean isFontShadowModified() {
        return this.fontFormatting.isFontShadowModified();
    }

    public boolean isFontStyleModified() {
        return this.fontFormatting.isFontStyleModified();
    }

    @Override
    public boolean isItalic() {
        return this.fontFormatting.isFontStyleModified() && this.fontFormatting.isItalic();
    }

    public boolean isOutlineOn() {
        return this.fontFormatting.isFontOutlineModified() && this.fontFormatting.isOutlineOn();
    }

    public boolean isShadowOn() {
        return this.fontFormatting.isFontOutlineModified() && this.fontFormatting.isShadowOn();
    }

    @Override
    public boolean isStruckout() {
        return this.fontFormatting.isFontCancellationModified() && this.fontFormatting.isStruckout();
    }

    public boolean isUnderlineTypeModified() {
        return this.fontFormatting.isUnderlineTypeModified();
    }

    public boolean isFontWeightModified() {
        return this.fontFormatting.isFontWeightModified();
    }

    @Override
    public void setFontStyle(boolean italic, boolean bold) {
        boolean modified = italic || bold;
        this.fontFormatting.setItalic(italic);
        this.fontFormatting.setBold(bold);
        this.fontFormatting.setFontStyleModified(modified);
        this.fontFormatting.setFontWieghtModified(modified);
    }

    @Override
    public void resetFontStyle() {
        this.setFontStyle(false, false);
    }

    @Override
    public void setEscapementType(short escapementType) {
        switch (escapementType) {
            case 1: 
            case 2: {
                this.fontFormatting.setEscapementType(escapementType);
                this.fontFormatting.setEscapementTypeModified(true);
                break;
            }
            case 0: {
                this.fontFormatting.setEscapementType(escapementType);
                this.fontFormatting.setEscapementTypeModified(false);
                break;
            }
        }
    }

    public void setEscapementTypeModified(boolean modified) {
        this.fontFormatting.setEscapementTypeModified(modified);
    }

    public void setFontCancellationModified(boolean modified) {
        this.fontFormatting.setFontCancellationModified(modified);
    }

    @Override
    public void setFontColorIndex(short fci) {
        this.fontFormatting.setFontColorIndex(fci);
    }

    @Override
    public void setFontHeight(int height) {
        this.fontFormatting.setFontHeight(height);
    }

    public void setFontOutlineModified(boolean modified) {
        this.fontFormatting.setFontOutlineModified(modified);
    }

    public void setFontShadowModified(boolean modified) {
        this.fontFormatting.setFontShadowModified(modified);
    }

    public void setFontStyleModified(boolean modified) {
        this.fontFormatting.setFontStyleModified(modified);
    }

    public void setOutline(boolean on) {
        this.fontFormatting.setOutline(on);
        this.fontFormatting.setFontOutlineModified(on);
    }

    public void setShadow(boolean on) {
        this.fontFormatting.setShadow(on);
        this.fontFormatting.setFontShadowModified(on);
    }

    public void setStrikeout(boolean strike) {
        this.fontFormatting.setStrikeout(strike);
        this.fontFormatting.setFontCancellationModified(strike);
    }

    @Override
    public void setUnderlineType(short underlineType) {
        switch (underlineType) {
            case 1: 
            case 2: 
            case 33: 
            case 34: {
                this.fontFormatting.setUnderlineType(underlineType);
                this.setUnderlineTypeModified(true);
                break;
            }
            case 0: {
                this.fontFormatting.setUnderlineType(underlineType);
                this.setUnderlineTypeModified(false);
                break;
            }
        }
    }

    public void setUnderlineTypeModified(boolean modified) {
        this.fontFormatting.setUnderlineTypeModified(modified);
    }
}

