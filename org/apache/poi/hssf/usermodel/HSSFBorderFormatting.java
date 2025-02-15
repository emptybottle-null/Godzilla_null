/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Color;

public final class HSSFBorderFormatting
implements org.apache.poi.ss.usermodel.BorderFormatting {
    private final HSSFWorkbook workbook;
    private final CFRuleBase cfRuleRecord;
    private final BorderFormatting borderFormatting;

    protected HSSFBorderFormatting(CFRuleBase cfRuleRecord, HSSFWorkbook workbook) {
        this.workbook = workbook;
        this.cfRuleRecord = cfRuleRecord;
        this.borderFormatting = cfRuleRecord.getBorderFormatting();
    }

    protected BorderFormatting getBorderFormattingBlock() {
        return this.borderFormatting;
    }

    @Override
    public BorderStyle getBorderBottom() {
        return BorderStyle.valueOf((short)this.borderFormatting.getBorderBottom());
    }

    @Override
    public BorderStyle getBorderDiagonal() {
        return BorderStyle.valueOf((short)this.borderFormatting.getBorderDiagonal());
    }

    @Override
    public BorderStyle getBorderLeft() {
        return BorderStyle.valueOf((short)this.borderFormatting.getBorderLeft());
    }

    @Override
    public BorderStyle getBorderRight() {
        return BorderStyle.valueOf((short)this.borderFormatting.getBorderRight());
    }

    @Override
    public BorderStyle getBorderTop() {
        return BorderStyle.valueOf((short)this.borderFormatting.getBorderTop());
    }

    @Override
    public short getBottomBorderColor() {
        return (short)this.borderFormatting.getBottomBorderColor();
    }

    @Override
    public HSSFColor getBottomBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getBottomBorderColor());
    }

    @Override
    public short getDiagonalBorderColor() {
        return (short)this.borderFormatting.getDiagonalBorderColor();
    }

    @Override
    public HSSFColor getDiagonalBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getDiagonalBorderColor());
    }

    @Override
    public short getLeftBorderColor() {
        return (short)this.borderFormatting.getLeftBorderColor();
    }

    @Override
    public HSSFColor getLeftBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getLeftBorderColor());
    }

    @Override
    public short getRightBorderColor() {
        return (short)this.borderFormatting.getRightBorderColor();
    }

    @Override
    public HSSFColor getRightBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getRightBorderColor());
    }

    @Override
    public short getTopBorderColor() {
        return (short)this.borderFormatting.getTopBorderColor();
    }

    @Override
    public HSSFColor getTopBorderColorColor() {
        return this.workbook.getCustomPalette().getColor(this.borderFormatting.getTopBorderColor());
    }

    public boolean isBackwardDiagonalOn() {
        return this.borderFormatting.isBackwardDiagonalOn();
    }

    public boolean isForwardDiagonalOn() {
        return this.borderFormatting.isForwardDiagonalOn();
    }

    public void setBackwardDiagonalOn(boolean on) {
        this.borderFormatting.setBackwardDiagonalOn(on);
        if (on) {
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(on);
        }
    }

    public void setForwardDiagonalOn(boolean on) {
        this.borderFormatting.setForwardDiagonalOn(on);
        if (on) {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(on);
        }
    }

    @Override
    public void setBorderBottom(BorderStyle border) {
        short code = border.getCode();
        this.borderFormatting.setBorderBottom(code);
        if (code != 0) {
            this.cfRuleRecord.setBottomBorderModified(true);
        } else {
            this.cfRuleRecord.setBottomBorderModified(false);
        }
    }

    @Override
    public void setBorderDiagonal(BorderStyle border) {
        short code = border.getCode();
        this.borderFormatting.setBorderDiagonal(code);
        if (code != 0) {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(true);
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(true);
        } else {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(false);
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(false);
        }
    }

    @Override
    public void setBorderLeft(BorderStyle border) {
        short code = border.getCode();
        this.borderFormatting.setBorderLeft(code);
        if (code != 0) {
            this.cfRuleRecord.setLeftBorderModified(true);
        } else {
            this.cfRuleRecord.setLeftBorderModified(false);
        }
    }

    @Override
    public void setBorderRight(BorderStyle border) {
        short code = border.getCode();
        this.borderFormatting.setBorderRight(code);
        if (code != 0) {
            this.cfRuleRecord.setRightBorderModified(true);
        } else {
            this.cfRuleRecord.setRightBorderModified(false);
        }
    }

    @Override
    public void setBorderTop(BorderStyle border) {
        short code = border.getCode();
        this.borderFormatting.setBorderTop(code);
        if (code != 0) {
            this.cfRuleRecord.setTopBorderModified(true);
        } else {
            this.cfRuleRecord.setTopBorderModified(false);
        }
    }

    @Override
    public void setBottomBorderColor(short color) {
        this.borderFormatting.setBottomBorderColor(color);
        if (color != 0) {
            this.cfRuleRecord.setBottomBorderModified(true);
        } else {
            this.cfRuleRecord.setBottomBorderModified(false);
        }
    }

    @Override
    public void setBottomBorderColor(Color color) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(color);
        if (hcolor == null) {
            this.setBottomBorderColor((short)0);
        } else {
            this.setBottomBorderColor(hcolor.getIndex());
        }
    }

    @Override
    public void setDiagonalBorderColor(short color) {
        this.borderFormatting.setDiagonalBorderColor(color);
        if (color != 0) {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(true);
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(true);
        } else {
            this.cfRuleRecord.setBottomLeftTopRightBorderModified(false);
            this.cfRuleRecord.setTopLeftBottomRightBorderModified(false);
        }
    }

    @Override
    public void setDiagonalBorderColor(Color color) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(color);
        if (hcolor == null) {
            this.setDiagonalBorderColor((short)0);
        } else {
            this.setDiagonalBorderColor(hcolor.getIndex());
        }
    }

    @Override
    public void setLeftBorderColor(short color) {
        this.borderFormatting.setLeftBorderColor(color);
        if (color != 0) {
            this.cfRuleRecord.setLeftBorderModified(true);
        } else {
            this.cfRuleRecord.setLeftBorderModified(false);
        }
    }

    @Override
    public void setLeftBorderColor(Color color) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(color);
        if (hcolor == null) {
            this.setLeftBorderColor((short)0);
        } else {
            this.setLeftBorderColor(hcolor.getIndex());
        }
    }

    @Override
    public void setRightBorderColor(short color) {
        this.borderFormatting.setRightBorderColor(color);
        if (color != 0) {
            this.cfRuleRecord.setRightBorderModified(true);
        } else {
            this.cfRuleRecord.setRightBorderModified(false);
        }
    }

    @Override
    public void setRightBorderColor(Color color) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(color);
        if (hcolor == null) {
            this.setRightBorderColor((short)0);
        } else {
            this.setRightBorderColor(hcolor.getIndex());
        }
    }

    @Override
    public void setTopBorderColor(short color) {
        this.borderFormatting.setTopBorderColor(color);
        if (color != 0) {
            this.cfRuleRecord.setTopBorderModified(true);
        } else {
            this.cfRuleRecord.setTopBorderModified(false);
        }
    }

    @Override
    public void setTopBorderColor(Color color) {
        HSSFColor hcolor = HSSFColor.toHSSFColor(color);
        if (hcolor == null) {
            this.setTopBorderColor((short)0);
        } else {
            this.setTopBorderColor(hcolor.getIndex());
        }
    }

    @Override
    public BorderStyle getBorderVertical() {
        return BorderStyle.NONE;
    }

    @Override
    public BorderStyle getBorderHorizontal() {
        return BorderStyle.NONE;
    }

    @Override
    public BorderStyle getBorderBottomEnum() {
        return this.getBorderBottom();
    }

    @Override
    public BorderStyle getBorderDiagonalEnum() {
        return this.getBorderDiagonal();
    }

    @Override
    public BorderStyle getBorderLeftEnum() {
        return this.getBorderLeft();
    }

    @Override
    public BorderStyle getBorderRightEnum() {
        return this.getBorderRight();
    }

    @Override
    public BorderStyle getBorderTopEnum() {
        return this.getBorderTop();
    }

    @Override
    @Deprecated
    public BorderStyle getBorderVerticalEnum() {
        return this.getBorderVertical();
    }

    @Override
    @Deprecated
    public BorderStyle getBorderHorizontalEnum() {
        return this.getBorderHorizontal();
    }

    @Override
    public short getVerticalBorderColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
    }

    @Override
    public Color getVerticalBorderColorColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();
    }

    @Override
    public short getHorizontalBorderColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getIndex();
    }

    @Override
    public Color getHorizontalBorderColorColor() {
        return HSSFColor.HSSFColorPredefined.AUTOMATIC.getColor();
    }

    @Override
    public void setBorderHorizontal(BorderStyle border) {
    }

    @Override
    public void setBorderVertical(BorderStyle border) {
    }

    @Override
    public void setHorizontalBorderColor(short color) {
    }

    @Override
    public void setHorizontalBorderColor(Color color) {
    }

    @Override
    public void setVerticalBorderColor(short color) {
    }

    @Override
    public void setVerticalBorderColor(Color color) {
    }
}

