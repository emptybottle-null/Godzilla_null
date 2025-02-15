/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.awt.Color;
import org.apache.poi.xssf.usermodel.TextCap;
import org.apache.poi.xssf.usermodel.XSSFTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

public class XSSFTextRun {
    private final CTRegularTextRun _r;
    private final XSSFTextParagraph _p;

    XSSFTextRun(CTRegularTextRun r, XSSFTextParagraph p) {
        this._r = r;
        this._p = p;
    }

    XSSFTextParagraph getParentParagraph() {
        return this._p;
    }

    public String getText() {
        return this._r.getT();
    }

    public void setText(String text) {
        this._r.setT(text);
    }

    public CTRegularTextRun getXmlObject() {
        return this._r;
    }

    public void setFontColor(Color color) {
        CTTextCharacterProperties rPr = this.getRPr();
        CTSolidColorFillProperties fill = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
        CTSRgbColor clr = fill.isSetSrgbClr() ? fill.getSrgbClr() : fill.addNewSrgbClr();
        clr.setVal(new byte[]{(byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue()});
        if (fill.isSetHslClr()) {
            fill.unsetHslClr();
        }
        if (fill.isSetPrstClr()) {
            fill.unsetPrstClr();
        }
        if (fill.isSetSchemeClr()) {
            fill.unsetSchemeClr();
        }
        if (fill.isSetScrgbClr()) {
            fill.unsetScrgbClr();
        }
        if (fill.isSetSysClr()) {
            fill.unsetSysClr();
        }
    }

    public Color getFontColor() {
        CTSolidColorFillProperties fill;
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetSolidFill() && (fill = rPr.getSolidFill()).isSetSrgbClr()) {
            CTSRgbColor clr = fill.getSrgbClr();
            byte[] rgb = clr.getVal();
            return new Color(0xFF & rgb[0], 0xFF & rgb[1], 0xFF & rgb[2]);
        }
        return new Color(0, 0, 0);
    }

    public void setFontSize(double fontSize) {
        CTTextCharacterProperties rPr = this.getRPr();
        if (fontSize == -1.0) {
            if (rPr.isSetSz()) {
                rPr.unsetSz();
            }
        } else {
            if (fontSize < 1.0) {
                throw new IllegalArgumentException("Minimum font size is 1pt but was " + fontSize);
            }
            rPr.setSz((int)(100.0 * fontSize));
        }
    }

    public double getFontSize() {
        CTTextCharacterProperties rPr;
        double scale = 1.0;
        double size = 11.0;
        CTTextNormalAutofit afit = this.getParentParagraph().getParentShape().getTxBody().getBodyPr().getNormAutofit();
        if (afit != null) {
            scale = (double)afit.getFontScale() / 100000.0;
        }
        if ((rPr = this.getRPr()).isSetSz()) {
            size = (double)rPr.getSz() * 0.01;
        }
        return size * scale;
    }

    public double getCharacterSpacing() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetSpc()) {
            return (double)rPr.getSpc() * 0.01;
        }
        return 0.0;
    }

    public void setCharacterSpacing(double spc) {
        CTTextCharacterProperties rPr = this.getRPr();
        if (spc == 0.0) {
            if (rPr.isSetSpc()) {
                rPr.unsetSpc();
            }
        } else {
            rPr.setSpc((int)(100.0 * spc));
        }
    }

    public void setFont(String typeface) {
        this.setFontFamily(typeface, (byte)-1, (byte)-1, false);
    }

    public void setFontFamily(String typeface, byte charset, byte pictAndFamily, boolean isSymbol) {
        CTTextCharacterProperties rPr = this.getRPr();
        if (typeface == null) {
            if (rPr.isSetLatin()) {
                rPr.unsetLatin();
            }
            if (rPr.isSetCs()) {
                rPr.unsetCs();
            }
            if (rPr.isSetSym()) {
                rPr.unsetSym();
            }
        } else if (isSymbol) {
            CTTextFont font = rPr.isSetSym() ? rPr.getSym() : rPr.addNewSym();
            font.setTypeface(typeface);
        } else {
            CTTextFont latin = rPr.isSetLatin() ? rPr.getLatin() : rPr.addNewLatin();
            latin.setTypeface(typeface);
            if (charset != -1) {
                latin.setCharset(charset);
            }
            if (pictAndFamily != -1) {
                latin.setPitchFamily(pictAndFamily);
            }
        }
    }

    public String getFontFamily() {
        CTTextCharacterProperties rPr = this.getRPr();
        CTTextFont font = rPr.getLatin();
        if (font != null) {
            return font.getTypeface();
        }
        return "Calibri";
    }

    public byte getPitchAndFamily() {
        CTTextCharacterProperties rPr = this.getRPr();
        CTTextFont font = rPr.getLatin();
        if (font != null) {
            return font.getPitchFamily();
        }
        return 0;
    }

    public void setStrikethrough(boolean strike) {
        this.getRPr().setStrike(strike ? STTextStrikeType.SNG_STRIKE : STTextStrikeType.NO_STRIKE);
    }

    public boolean isStrikethrough() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetStrike()) {
            return rPr.getStrike() != STTextStrikeType.NO_STRIKE;
        }
        return false;
    }

    public boolean isSuperscript() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetBaseline()) {
            return rPr.getBaseline() > 0;
        }
        return false;
    }

    public void setBaselineOffset(double baselineOffset) {
        this.getRPr().setBaseline((int)baselineOffset * 1000);
    }

    public void setSuperscript(boolean flag) {
        this.setBaselineOffset(flag ? 30.0 : 0.0);
    }

    public void setSubscript(boolean flag) {
        this.setBaselineOffset(flag ? -25.0 : 0.0);
    }

    public boolean isSubscript() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetBaseline()) {
            return rPr.getBaseline() < 0;
        }
        return false;
    }

    public TextCap getTextCap() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetCap()) {
            return TextCap.values()[rPr.getCap().intValue() - 1];
        }
        return TextCap.NONE;
    }

    public void setBold(boolean bold) {
        this.getRPr().setB(bold);
    }

    public boolean isBold() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetB()) {
            return rPr.getB();
        }
        return false;
    }

    public void setItalic(boolean italic) {
        this.getRPr().setI(italic);
    }

    public boolean isItalic() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetI()) {
            return rPr.getI();
        }
        return false;
    }

    public void setUnderline(boolean underline) {
        this.getRPr().setU(underline ? STTextUnderlineType.SNG : STTextUnderlineType.NONE);
    }

    public boolean isUnderline() {
        CTTextCharacterProperties rPr = this.getRPr();
        if (rPr.isSetU()) {
            return rPr.getU() != STTextUnderlineType.NONE;
        }
        return false;
    }

    protected CTTextCharacterProperties getRPr() {
        return this._r.isSetRPr() ? this._r.getRPr() : this._r.addNewRPr();
    }

    public String toString() {
        return "[" + this.getClass() + "]" + this.getText();
    }
}

