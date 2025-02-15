/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.SimpleShape;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColorRgbBinary;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.apache.poi.xssf.usermodel.ListAutoNumber;
import org.apache.poi.xssf.usermodel.TextAutofit;
import org.apache.poi.xssf.usermodel.TextDirection;
import org.apache.poi.xssf.usermodel.TextHorizontalOverflow;
import org.apache.poi.xssf.usermodel.TextVerticalOverflow;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFTextParagraph;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextHorzOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVertOverflowType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShapeNonVisual;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;

public class XSSFSimpleShape
extends XSSFShape
implements Iterable<XSSFTextParagraph>,
SimpleShape,
TextContainer {
    private final XDDFTextBody _textBody;
    private final List<XSSFTextParagraph> _paragraphs;
    private static CTShape prototype;
    private CTShape ctShape;
    private static String[] _romanChars;
    private static int[] _romanAlphaValues;

    protected XSSFSimpleShape(XSSFDrawing drawing, CTShape ctShape) {
        this.drawing = drawing;
        this.ctShape = ctShape;
        this._paragraphs = new ArrayList<XSSFTextParagraph>();
        CTTextBody body = ctShape.getTxBody();
        if (body == null) {
            this._textBody = null;
        } else {
            this._textBody = new XDDFTextBody(this, body);
            for (int i = 0; i < body.sizeOfPArray(); ++i) {
                this._paragraphs.add(new XSSFTextParagraph(body.getPArray(i), ctShape));
            }
        }
    }

    protected static CTShape prototype() {
        if (prototype == null) {
            CTShape shape = CTShape.Factory.newInstance();
            CTShapeNonVisual nv = shape.addNewNvSpPr();
            CTNonVisualDrawingProps nvp = nv.addNewCNvPr();
            nvp.setId(1L);
            nvp.setName("Shape 1");
            nv.addNewCNvSpPr();
            CTShapeProperties sp = shape.addNewSpPr();
            CTTransform2D t2d = sp.addNewXfrm();
            CTPositiveSize2D p1 = t2d.addNewExt();
            p1.setCx(0L);
            p1.setCy(0L);
            CTPoint2D p2 = t2d.addNewOff();
            p2.setX(0L);
            p2.setY(0L);
            CTPresetGeometry2D geom = sp.addNewPrstGeom();
            geom.setPrst(STShapeType.RECT);
            geom.addNewAvLst();
            XDDFTextBody body = new XDDFTextBody(null, shape.addNewTxBody());
            XDDFTextParagraph p = body.initialize();
            XDDFRunProperties rp = p.getAfterLastRunProperties();
            XDDFColorRgbBinary black = new XDDFColorRgbBinary(new byte[]{0, 0, 0});
            XDDFSolidFillProperties fp = new XDDFSolidFillProperties(black);
            rp.setFillProperties(fp);
            prototype = shape;
        }
        return prototype;
    }

    @Internal
    public CTShape getCTShape() {
        return this.ctShape;
    }

    public XDDFTextBody getTextBody() {
        return this._textBody;
    }

    protected void setXfrm(CTTransform2D t2d) {
        this.ctShape.getSpPr().setXfrm(t2d);
    }

    @Override
    public Iterator<XSSFTextParagraph> iterator() {
        return this._paragraphs.iterator();
    }

    public String getText() {
        int MAX_LEVELS = 9;
        StringBuilder out = new StringBuilder();
        ArrayList<Integer> levelCount = new ArrayList<Integer>(9);
        for (int k = 0; k < 9; ++k) {
            levelCount.add(0);
        }
        for (int i = 0; i < this._paragraphs.size(); ++i) {
            XSSFTextParagraph p;
            if (out.length() > 0) {
                out.append('\n');
            }
            if ((p = this._paragraphs.get(i)).isBullet() && p.getText().length() > 0) {
                int level = Math.min(p.getLevel(), 8);
                if (p.isBulletAutoNumber()) {
                    i = this.processAutoNumGroup(i, level, levelCount, out);
                    continue;
                }
                for (int j = 0; j < level; ++j) {
                    out.append('\t');
                }
                String character = p.getBulletCharacter();
                out.append(character.length() > 0 ? character + " " : "- ");
                out.append(p.getText());
                continue;
            }
            out.append(p.getText());
            for (int k = 0; k < 9; ++k) {
                levelCount.set(k, 0);
            }
        }
        return out.toString();
    }

    private int processAutoNumGroup(int index, int level, List<Integer> levelCount, StringBuilder out) {
        XSSFTextParagraph p = this._paragraphs.get(index);
        int startAt = p.getBulletAutoNumberStart();
        ListAutoNumber scheme = p.getBulletAutoNumberScheme();
        if (levelCount.get(level) == 0) {
            levelCount.set(level, startAt == 0 ? 1 : startAt);
        }
        for (int j = 0; j < level; ++j) {
            out.append('\t');
        }
        if (p.getText().length() > 0) {
            out.append(this.getBulletPrefix(scheme, levelCount.get(level)));
            out.append(p.getText());
        }
        while (true) {
            XSSFTextParagraph nextp;
            XSSFTextParagraph xSSFTextParagraph = nextp = index + 1 == this._paragraphs.size() ? null : this._paragraphs.get(index + 1);
            if (nextp == null || !nextp.isBullet() || !p.isBulletAutoNumber()) break;
            if (nextp.getLevel() > level) {
                if (out.length() > 0) {
                    out.append('\n');
                }
                index = this.processAutoNumGroup(index + 1, nextp.getLevel(), levelCount, out);
                continue;
            }
            if (nextp.getLevel() < level) break;
            ListAutoNumber nextScheme = nextp.getBulletAutoNumberScheme();
            int nextStartAt = nextp.getBulletAutoNumberStart();
            if (nextScheme != scheme || nextStartAt != startAt) break;
            ++index;
            if (out.length() > 0) {
                out.append('\n');
            }
            for (int j = 0; j < level; ++j) {
                out.append('\t');
            }
            if (nextp.getText().length() <= 0) continue;
            levelCount.set(level, levelCount.get(level) + 1);
            out.append(this.getBulletPrefix(nextScheme, levelCount.get(level)));
            out.append(nextp.getText());
        }
        levelCount.set(level, 0);
        return index;
    }

    private String getBulletPrefix(ListAutoNumber scheme, int value) {
        StringBuilder out = new StringBuilder();
        switch (scheme) {
            case ALPHA_LC_PARENT_BOTH: 
            case ALPHA_LC_PARENT_R: {
                if (scheme == ListAutoNumber.ALPHA_LC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(this.valueToAlpha(value).toLowerCase(Locale.ROOT));
                out.append(')');
                break;
            }
            case ALPHA_UC_PARENT_BOTH: 
            case ALPHA_UC_PARENT_R: {
                if (scheme == ListAutoNumber.ALPHA_UC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(this.valueToAlpha(value));
                out.append(')');
                break;
            }
            case ALPHA_LC_PERIOD: {
                out.append(this.valueToAlpha(value).toLowerCase(Locale.ROOT));
                out.append('.');
                break;
            }
            case ALPHA_UC_PERIOD: {
                out.append(this.valueToAlpha(value));
                out.append('.');
                break;
            }
            case ARABIC_PARENT_BOTH: 
            case ARABIC_PARENT_R: {
                if (scheme == ListAutoNumber.ARABIC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(value);
                out.append(')');
                break;
            }
            case ARABIC_PERIOD: {
                out.append(value);
                out.append('.');
                break;
            }
            case ARABIC_PLAIN: {
                out.append(value);
                break;
            }
            case ROMAN_LC_PARENT_BOTH: 
            case ROMAN_LC_PARENT_R: {
                if (scheme == ListAutoNumber.ROMAN_LC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(this.valueToRoman(value).toLowerCase(Locale.ROOT));
                out.append(')');
                break;
            }
            case ROMAN_UC_PARENT_BOTH: 
            case ROMAN_UC_PARENT_R: {
                if (scheme == ListAutoNumber.ROMAN_UC_PARENT_BOTH) {
                    out.append('(');
                }
                out.append(this.valueToRoman(value));
                out.append(')');
                break;
            }
            case ROMAN_LC_PERIOD: {
                out.append(this.valueToRoman(value).toLowerCase(Locale.ROOT));
                out.append('.');
                break;
            }
            case ROMAN_UC_PERIOD: {
                out.append(this.valueToRoman(value));
                out.append('.');
                break;
            }
            default: {
                out.append('\u2022');
            }
        }
        out.append(" ");
        return out.toString();
    }

    private String valueToAlpha(int value) {
        String alpha = "";
        while (value > 0) {
            int modulo = (value - 1) % 26;
            alpha = (char)(65 + modulo) + alpha;
            value = (value - modulo) / 26;
        }
        return alpha;
    }

    private String valueToRoman(int value) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; value > 0 && i < _romanChars.length; ++i) {
            while (_romanAlphaValues[i] <= value) {
                out.append(_romanChars[i]);
                value -= _romanAlphaValues[i];
            }
        }
        return out.toString();
    }

    public void clearText() {
        this._paragraphs.clear();
        CTTextBody txBody = this.ctShape.getTxBody();
        txBody.setPArray(null);
    }

    public void setText(String text) {
        this.clearText();
        this.addNewTextParagraph().addNewTextRun().setText(text);
    }

    public void setText(XSSFRichTextString str) {
        XSSFWorkbook wb = (XSSFWorkbook)this.getDrawing().getParent().getParent();
        str.setStylesTableReference(wb.getStylesSource());
        CTTextParagraph p = CTTextParagraph.Factory.newInstance();
        if (str.numFormattingRuns() == 0) {
            CTRegularTextRun r = p.addNewR();
            CTTextCharacterProperties rPr = r.addNewRPr();
            rPr.setLang("en-US");
            rPr.setSz(1100);
            r.setT(str.getString());
        } else {
            for (int i = 0; i < str.getCTRst().sizeOfRArray(); ++i) {
                CTRElt lt = str.getCTRst().getRArray(i);
                CTRPrElt ltPr = lt.getRPr();
                if (ltPr == null) {
                    ltPr = lt.addNewRPr();
                }
                CTRegularTextRun r = p.addNewR();
                CTTextCharacterProperties rPr = r.addNewRPr();
                rPr.setLang("en-US");
                XSSFSimpleShape.applyAttributes(ltPr, rPr);
                r.setT(lt.getT());
            }
        }
        this.clearText();
        this.ctShape.getTxBody().setPArray(new CTTextParagraph[]{p});
        this._paragraphs.add(new XSSFTextParagraph(this.ctShape.getTxBody().getPArray(0), this.ctShape));
    }

    public List<XSSFTextParagraph> getTextParagraphs() {
        return this._paragraphs;
    }

    public XSSFTextParagraph addNewTextParagraph() {
        CTTextBody txBody = this.ctShape.getTxBody();
        CTTextParagraph p = txBody.addNewP();
        XSSFTextParagraph paragraph = new XSSFTextParagraph(p, this.ctShape);
        this._paragraphs.add(paragraph);
        return paragraph;
    }

    public XSSFTextParagraph addNewTextParagraph(String text) {
        XSSFTextParagraph paragraph = this.addNewTextParagraph();
        paragraph.addNewTextRun().setText(text);
        return paragraph;
    }

    public XSSFTextParagraph addNewTextParagraph(XSSFRichTextString str) {
        CTTextBody txBody = this.ctShape.getTxBody();
        CTTextParagraph p = txBody.addNewP();
        if (str.numFormattingRuns() == 0) {
            CTRegularTextRun r = p.addNewR();
            CTTextCharacterProperties rPr = r.addNewRPr();
            rPr.setLang("en-US");
            rPr.setSz(1100);
            r.setT(str.getString());
        } else {
            for (int i = 0; i < str.getCTRst().sizeOfRArray(); ++i) {
                CTRElt lt = str.getCTRst().getRArray(i);
                CTRPrElt ltPr = lt.getRPr();
                if (ltPr == null) {
                    ltPr = lt.addNewRPr();
                }
                CTRegularTextRun r = p.addNewR();
                CTTextCharacterProperties rPr = r.addNewRPr();
                rPr.setLang("en-US");
                XSSFSimpleShape.applyAttributes(ltPr, rPr);
                r.setT(lt.getT());
            }
        }
        XSSFTextParagraph paragraph = new XSSFTextParagraph(p, this.ctShape);
        this._paragraphs.add(paragraph);
        return paragraph;
    }

    public void setTextHorizontalOverflow(TextHorizontalOverflow overflow) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (overflow == null) {
                if (bodyPr.isSetHorzOverflow()) {
                    bodyPr.unsetHorzOverflow();
                }
            } else {
                bodyPr.setHorzOverflow(STTextHorzOverflowType.Enum.forInt(overflow.ordinal() + 1));
            }
        }
    }

    public TextHorizontalOverflow getTextHorizontalOverflow() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetHorzOverflow()) {
            return TextHorizontalOverflow.values()[bodyPr.getHorzOverflow().intValue() - 1];
        }
        return TextHorizontalOverflow.OVERFLOW;
    }

    public void setTextVerticalOverflow(TextVerticalOverflow overflow) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (overflow == null) {
                if (bodyPr.isSetVertOverflow()) {
                    bodyPr.unsetVertOverflow();
                }
            } else {
                bodyPr.setVertOverflow(STTextVertOverflowType.Enum.forInt(overflow.ordinal() + 1));
            }
        }
    }

    public TextVerticalOverflow getTextVerticalOverflow() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetVertOverflow()) {
            return TextVerticalOverflow.values()[bodyPr.getVertOverflow().intValue() - 1];
        }
        return TextVerticalOverflow.OVERFLOW;
    }

    public void setVerticalAlignment(VerticalAlignment anchor) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (anchor == null) {
                if (bodyPr.isSetAnchor()) {
                    bodyPr.unsetAnchor();
                }
            } else {
                bodyPr.setAnchor(STTextAnchoringType.Enum.forInt(anchor.ordinal() + 1));
            }
        }
    }

    public VerticalAlignment getVerticalAlignment() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetAnchor()) {
            return VerticalAlignment.values()[bodyPr.getAnchor().intValue() - 1];
        }
        return VerticalAlignment.TOP;
    }

    public void setTextDirection(TextDirection orientation) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (orientation == null) {
                if (bodyPr.isSetVert()) {
                    bodyPr.unsetVert();
                }
            } else {
                bodyPr.setVert(STTextVerticalType.Enum.forInt(orientation.ordinal() + 1));
            }
        }
    }

    public TextDirection getTextDirection() {
        STTextVerticalType.Enum val;
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && (val = bodyPr.getVert()) != null) {
            return TextDirection.values()[val.intValue() - 1];
        }
        return TextDirection.HORIZONTAL;
    }

    public double getBottomInset() {
        Double inset = this._textBody.getBodyProperties().getBottomInset();
        if (inset == null) {
            return 3.6;
        }
        return inset;
    }

    public double getLeftInset() {
        Double inset = this._textBody.getBodyProperties().getLeftInset();
        if (inset == null) {
            return 3.6;
        }
        return inset;
    }

    public double getRightInset() {
        Double inset = this._textBody.getBodyProperties().getRightInset();
        if (inset == null) {
            return 3.6;
        }
        return inset;
    }

    public double getTopInset() {
        Double inset = this._textBody.getBodyProperties().getTopInset();
        if (inset == null) {
            return 3.6;
        }
        return inset;
    }

    public void setBottomInset(double margin) {
        if (margin == -1.0) {
            this._textBody.getBodyProperties().setBottomInset(null);
        } else {
            this._textBody.getBodyProperties().setBottomInset(margin);
        }
    }

    public void setLeftInset(double margin) {
        if (margin == -1.0) {
            this._textBody.getBodyProperties().setLeftInset(null);
        } else {
            this._textBody.getBodyProperties().setLeftInset(margin);
        }
    }

    public void setRightInset(double margin) {
        if (margin == -1.0) {
            this._textBody.getBodyProperties().setRightInset(null);
        } else {
            this._textBody.getBodyProperties().setRightInset(margin);
        }
    }

    public void setTopInset(double margin) {
        if (margin == -1.0) {
            this._textBody.getBodyProperties().setTopInset(null);
        } else {
            this._textBody.getBodyProperties().setTopInset(margin);
        }
    }

    public boolean getWordWrap() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null && bodyPr.isSetWrap()) {
            return bodyPr.getWrap() == STTextWrappingType.SQUARE;
        }
        return true;
    }

    public void setWordWrap(boolean wrap) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            bodyPr.setWrap(wrap ? STTextWrappingType.SQUARE : STTextWrappingType.NONE);
        }
    }

    public void setTextAutofit(TextAutofit value) {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetSpAutoFit()) {
                bodyPr.unsetSpAutoFit();
            }
            if (bodyPr.isSetNoAutofit()) {
                bodyPr.unsetNoAutofit();
            }
            if (bodyPr.isSetNormAutofit()) {
                bodyPr.unsetNormAutofit();
            }
            switch (value) {
                case NONE: {
                    bodyPr.addNewNoAutofit();
                    break;
                }
                case NORMAL: {
                    bodyPr.addNewNormAutofit();
                    break;
                }
                case SHAPE: {
                    bodyPr.addNewSpAutoFit();
                }
            }
        }
    }

    public TextAutofit getTextAutofit() {
        CTTextBodyProperties bodyPr = this.ctShape.getTxBody().getBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetNoAutofit()) {
                return TextAutofit.NONE;
            }
            if (bodyPr.isSetNormAutofit()) {
                return TextAutofit.NORMAL;
            }
            if (bodyPr.isSetSpAutoFit()) {
                return TextAutofit.SHAPE;
            }
        }
        return TextAutofit.NORMAL;
    }

    public int getShapeType() {
        return this.ctShape.getSpPr().getPrstGeom().getPrst().intValue();
    }

    public void setShapeType(int type) {
        this.ctShape.getSpPr().getPrstGeom().setPrst(STShapeType.Enum.forInt(type));
    }

    @Override
    protected CTShapeProperties getShapeProperties() {
        return this.ctShape.getSpPr();
    }

    private static void applyAttributes(CTRPrElt pr, CTTextCharacterProperties rPr) {
        if (pr.sizeOfBArray() > 0) {
            rPr.setB(pr.getBArray(0).getVal());
        }
        if (pr.sizeOfUArray() > 0) {
            STUnderlineValues.Enum u1 = pr.getUArray(0).getVal();
            if (u1 == STUnderlineValues.SINGLE) {
                rPr.setU(STTextUnderlineType.SNG);
            } else if (u1 == STUnderlineValues.DOUBLE) {
                rPr.setU(STTextUnderlineType.DBL);
            } else if (u1 == STUnderlineValues.NONE) {
                rPr.setU(STTextUnderlineType.NONE);
            }
        }
        if (pr.sizeOfIArray() > 0) {
            rPr.setI(pr.getIArray(0).getVal());
        }
        if (pr.sizeOfRFontArray() > 0) {
            CTTextFont rFont = rPr.isSetLatin() ? rPr.getLatin() : rPr.addNewLatin();
            rFont.setTypeface(pr.getRFontArray(0).getVal());
        }
        if (pr.sizeOfSzArray() > 0) {
            int sz = (int)(pr.getSzArray(0).getVal() * 100.0);
            rPr.setSz(sz);
        }
        if (pr.sizeOfColorArray() > 0) {
            HSSFColor indexed;
            CTSolidColorFillProperties fill = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
            CTColor xlsColor = pr.getColorArray(0);
            if (xlsColor.isSetRgb()) {
                CTSRgbColor clr = fill.isSetSrgbClr() ? fill.getSrgbClr() : fill.addNewSrgbClr();
                clr.setVal(xlsColor.getRgb());
            } else if (xlsColor.isSetIndexed() && (indexed = HSSFColor.getIndexHash().get((int)xlsColor.getIndexed())) != null) {
                byte[] rgb = new byte[]{(byte)indexed.getTriplet()[0], (byte)indexed.getTriplet()[1], (byte)indexed.getTriplet()[2]};
                CTSRgbColor clr = fill.isSetSrgbClr() ? fill.getSrgbClr() : fill.addNewSrgbClr();
                clr.setVal(rgb);
            }
        }
    }

    @Override
    public String getShapeName() {
        return this.ctShape.getNvSpPr().getCNvPr().getName();
    }

    @Override
    public int getShapeId() {
        return (int)this.ctShape.getNvSpPr().getCNvPr().getId();
    }

    @Override
    public <R> Optional<R> findDefinedParagraphProperty(Function<CTTextParagraphProperties, Boolean> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> findDefinedRunProperty(Function<CTTextCharacterProperties, Boolean> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }

    static {
        _romanChars = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        _romanAlphaValues = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    }
}

