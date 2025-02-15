/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Removal;
import org.apache.poi.wp.usermodel.CharacterRun;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.IRunElement;
import org.apache.poi.xwpf.usermodel.ISDTContents;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObjectData;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPictureNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFFCheckBox;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrClear;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBrType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorAuto;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHexColorRGB;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STThemeColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalAlignRun;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XWPFRun
implements ISDTContents,
IRunElement,
CharacterRun {
    private CTR run;
    private String pictureText;
    private IRunBody parent;
    private List<XWPFPicture> pictures;

    public XWPFRun(CTR r, IRunBody p) {
        this.run = r;
        this.parent = p;
        for (CTDrawing ctDrawing : r.getDrawingArray()) {
            for (CTAnchor cTAnchor : ctDrawing.getAnchorArray()) {
                if (cTAnchor.getDocPr() == null) continue;
                this.getDocument().getDrawingIdManager().reserve(cTAnchor.getDocPr().getId());
            }
            for (XmlObject xmlObject : ctDrawing.getInlineArray()) {
                if (xmlObject.getDocPr() == null) continue;
                this.getDocument().getDrawingIdManager().reserve(xmlObject.getDocPr().getId());
            }
        }
        StringBuilder text = new StringBuilder();
        ArrayList<XmlObject> pictTextObjs = new ArrayList<XmlObject>();
        pictTextObjs.addAll(Arrays.asList(r.getPictArray()));
        pictTextObjs.addAll(Arrays.asList(r.getDrawingArray()));
        for (XmlObject o : pictTextObjs) {
            XmlObject[] ts;
            for (XmlObject t : ts = o.selectPath("declare namespace w='http://schemas.openxmlformats.org/wordprocessingml/2006/main' .//w:t")) {
                NodeList kids = t.getDomNode().getChildNodes();
                for (int n = 0; n < kids.getLength(); ++n) {
                    if (!(kids.item(n) instanceof Text)) continue;
                    if (text.length() > 0) {
                        text.append("\n");
                    }
                    text.append(kids.item(n).getNodeValue());
                }
            }
        }
        this.pictureText = text.toString();
        this.pictures = new ArrayList<XWPFPicture>();
        for (XmlObject o : pictTextObjs) {
            for (CTPicture pict : this.getCTPictures(o)) {
                XWPFPicture picture = new XWPFPicture(pict, this);
                this.pictures.add(picture);
            }
        }
    }

    @Deprecated
    public XWPFRun(CTR r, XWPFParagraph p) {
        this(r, (IRunBody)p);
    }

    static void preserveSpaces(XmlString xs) {
        String text = xs.getStringValue();
        if (text != null && text.length() >= 1 && (Character.isWhitespace(text.charAt(0)) || Character.isWhitespace(text.charAt(text.length() - 1)))) {
            XmlCursor c = xs.newCursor();
            c.toNextToken();
            c.insertAttributeWithValue(new QName("http://www.w3.org/XML/1998/namespace", "space"), "preserve");
            c.dispose();
        }
    }

    private List<CTPicture> getCTPictures(XmlObject o) {
        XmlObject[] picts;
        ArrayList<CTPicture> pics = new ArrayList<CTPicture>();
        for (XmlObject pict : picts = o.selectPath("declare namespace pic='" + CTPicture.type.getName().getNamespaceURI() + "' .//pic:pic")) {
            if (pict instanceof XmlAnyTypeImpl) {
                try {
                    pict = CTPicture.Factory.parse(pict.toString(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
                } catch (XmlException e) {
                    throw new POIXMLException(e);
                }
            }
            if (!(pict instanceof CTPicture)) continue;
            pics.add((CTPicture)pict);
        }
        return pics;
    }

    @Internal
    public CTR getCTR() {
        return this.run;
    }

    public IRunBody getParent() {
        return this.parent;
    }

    @Deprecated
    public XWPFParagraph getParagraph() {
        if (this.parent instanceof XWPFParagraph) {
            return (XWPFParagraph)this.parent;
        }
        return null;
    }

    public XWPFDocument getDocument() {
        if (this.parent != null) {
            return this.parent.getDocument();
        }
        return null;
    }

    private static boolean isCTOnOff(CTOnOff onoff) {
        if (!onoff.isSetVal()) {
            return true;
        }
        STOnOff.Enum val = onoff.getVal();
        return STOnOff.TRUE == val || STOnOff.X_1 == val || STOnOff.ON == val;
    }

    public String getLang() {
        CTRPr pr = this.getRunProperties(false);
        Object lang = pr == null || !pr.isSetLang() ? null : pr.getLang().getVal();
        return (String)lang;
    }

    public void setLang(String lang) {
        CTRPr pr = this.getRunProperties(true);
        CTLanguage ctLang = pr.isSetLang() ? pr.getLang() : pr.addNewLang();
        ctLang.setVal(lang);
    }

    @Override
    public boolean isBold() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetB() && XWPFRun.isCTOnOff(pr.getB());
    }

    @Override
    public void setBold(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff bold = pr.isSetB() ? pr.getB() : pr.addNewB();
        bold.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    public String getColor() {
        CTRPr pr;
        String color = null;
        if (this.run.isSetRPr() && (pr = this.getRunProperties(false)) != null && pr.isSetColor()) {
            CTColor clr = pr.getColor();
            color = clr.xgetVal().getStringValue();
        }
        return color;
    }

    public void setColor(String rgbStr) {
        CTRPr pr = this.getRunProperties(true);
        CTColor color = pr.isSetColor() ? pr.getColor() : pr.addNewColor();
        color.setVal(rgbStr);
    }

    public String getText(int pos) {
        return this.run.sizeOfTArray() == 0 ? null : this.run.getTArray(pos).getStringValue();
    }

    public String getPictureText() {
        return this.pictureText;
    }

    public void setText(String value) {
        this.setText(value, this.run.sizeOfTArray());
    }

    public void setText(String value, int pos) {
        if (pos > this.run.sizeOfTArray()) {
            throw new ArrayIndexOutOfBoundsException("Value too large for the parameter position in XWPFRun.setText(String value,int pos)");
        }
        CTText t = pos < this.run.sizeOfTArray() && pos >= 0 ? this.run.getTArray(pos) : this.run.addNewT();
        t.setStringValue(value);
        XWPFRun.preserveSpaces(t);
    }

    @Override
    public boolean isItalic() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetI() && XWPFRun.isCTOnOff(pr.getI());
    }

    @Override
    public void setItalic(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff italic = pr.isSetI() ? pr.getI() : pr.addNewI();
        italic.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    public UnderlinePatterns getUnderline() {
        STUnderline.Enum baseValue;
        UnderlinePatterns value = UnderlinePatterns.NONE;
        CTUnderline underline = this.getCTUnderline(false);
        if (underline != null && (baseValue = underline.getVal()) != null) {
            value = UnderlinePatterns.valueOf(baseValue.intValue());
        }
        return value;
    }

    public void setUnderline(UnderlinePatterns value) {
        CTUnderline underline = this.getCTUnderline(true);
        underline.setVal(STUnderline.Enum.forInt(value.getValue()));
    }

    private CTUnderline getCTUnderline(boolean create) {
        CTRPr pr = this.getRunProperties(true);
        CTUnderline underline = pr.getU();
        if (create && underline == null) {
            underline = pr.addNewU();
        }
        return underline;
    }

    public void setUnderlineColor(String color) {
        CTUnderline underline = this.getCTUnderline(true);
        SimpleValue svColor = null;
        if (color.equals("auto")) {
            STHexColorAuto hexColor = STHexColorAuto.Factory.newInstance();
            hexColor.set(STHexColorAuto.Enum.forString(color));
            svColor = (SimpleValue)((Object)hexColor);
        } else {
            STHexColorRGB rgbColor = STHexColorRGB.Factory.newInstance();
            rgbColor.setStringValue(color);
            svColor = (SimpleValue)((Object)rgbColor);
        }
        underline.setColor(svColor);
    }

    public void setUnderlineThemeColor(String themeColor) {
        CTUnderline underline = this.getCTUnderline(true);
        STThemeColor.Enum val = STThemeColor.Enum.forString(themeColor);
        if (val != null) {
            underline.setThemeColor(val);
        }
    }

    public STThemeColor.Enum getUnderlineThemeColor() {
        CTUnderline underline = this.getCTUnderline(false);
        STThemeColor.Enum color = STThemeColor.NONE;
        if (underline != null) {
            color = underline.getThemeColor();
        }
        return color;
    }

    public String getUnderlineColor() {
        CTUnderline underline = this.getCTUnderline(true);
        String colorName = "auto";
        Object rawValue = underline.getColor();
        if (rawValue != null) {
            if (rawValue instanceof String) {
                colorName = (String)rawValue;
            } else {
                byte[] rgbColor = (byte[])rawValue;
                colorName = HexDump.toHex(rgbColor[0]) + HexDump.toHex(rgbColor[1]) + HexDump.toHex(rgbColor[2]);
            }
        }
        return colorName;
    }

    @Override
    public boolean isStrikeThrough() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetStrike() && XWPFRun.isCTOnOff(pr.getStrike());
    }

    @Override
    public void setStrikeThrough(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff strike = pr.isSetStrike() ? pr.getStrike() : pr.addNewStrike();
        strike.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Deprecated
    public boolean isStrike() {
        return this.isStrikeThrough();
    }

    @Deprecated
    public void setStrike(boolean value) {
        this.setStrikeThrough(value);
    }

    @Override
    public boolean isDoubleStrikeThrough() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetDstrike() && XWPFRun.isCTOnOff(pr.getDstrike());
    }

    @Override
    public void setDoubleStrikethrough(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff dstrike = pr.isSetDstrike() ? pr.getDstrike() : pr.addNewDstrike();
        dstrike.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Override
    public boolean isSmallCaps() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetSmallCaps() && XWPFRun.isCTOnOff(pr.getSmallCaps());
    }

    @Override
    public void setSmallCaps(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff caps = pr.isSetSmallCaps() ? pr.getSmallCaps() : pr.addNewSmallCaps();
        caps.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Override
    public boolean isCapitalized() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetCaps() && XWPFRun.isCTOnOff(pr.getCaps());
    }

    @Override
    public void setCapitalized(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff caps = pr.isSetCaps() ? pr.getCaps() : pr.addNewCaps();
        caps.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Override
    public boolean isShadowed() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetShadow() && XWPFRun.isCTOnOff(pr.getShadow());
    }

    @Override
    public void setShadow(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff shadow = pr.isSetShadow() ? pr.getShadow() : pr.addNewShadow();
        shadow.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Override
    public boolean isImprinted() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetImprint() && XWPFRun.isCTOnOff(pr.getImprint());
    }

    @Override
    public void setImprinted(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff imprinted = pr.isSetImprint() ? pr.getImprint() : pr.addNewImprint();
        imprinted.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Override
    public boolean isEmbossed() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetEmboss() && XWPFRun.isCTOnOff(pr.getEmboss());
    }

    @Override
    public void setEmbossed(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff emboss = pr.isSetEmboss() ? pr.getEmboss() : pr.addNewEmboss();
        emboss.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    @Removal(version="4.2")
    public VerticalAlign getSubscript() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetVertAlign() ? VerticalAlign.valueOf(pr.getVertAlign().getVal().intValue()) : VerticalAlign.BASELINE;
    }

    public void setSubscript(VerticalAlign valign) {
        CTRPr pr = this.getRunProperties(true);
        CTVerticalAlignRun ctValign = pr.isSetVertAlign() ? pr.getVertAlign() : pr.addNewVertAlign();
        ctValign.setVal(STVerticalAlignRun.Enum.forInt(valign.getValue()));
    }

    @Override
    public int getKerning() {
        CTRPr pr = this.getRunProperties(false);
        if (pr == null || !pr.isSetKern()) {
            return 0;
        }
        return pr.getKern().getVal().intValue();
    }

    @Override
    public void setKerning(int kern) {
        CTRPr pr = this.getRunProperties(true);
        CTHpsMeasure kernmes = pr.isSetKern() ? pr.getKern() : pr.addNewKern();
        kernmes.setVal(BigInteger.valueOf(kern));
    }

    @Override
    public boolean isHighlighted() {
        CTRPr pr = this.getRunProperties(false);
        if (pr == null || !pr.isSetHighlight()) {
            return false;
        }
        STHighlightColor.Enum val = pr.getHighlight().getVal();
        return val != null && val != STHighlightColor.NONE;
    }

    @Override
    public int getCharacterSpacing() {
        CTRPr pr = this.getRunProperties(false);
        if (pr == null || !pr.isSetSpacing()) {
            return 0;
        }
        return pr.getSpacing().getVal().intValue();
    }

    @Override
    public void setCharacterSpacing(int twips) {
        CTRPr pr = this.getRunProperties(true);
        CTSignedTwipsMeasure spc = pr.isSetSpacing() ? pr.getSpacing() : pr.addNewSpacing();
        spc.setVal(BigInteger.valueOf(twips));
    }

    public String getFontFamily() {
        return this.getFontFamily(null);
    }

    public void setFontFamily(String fontFamily) {
        this.setFontFamily(fontFamily, null);
    }

    @Override
    public String getFontName() {
        return this.getFontFamily();
    }

    public String getFontFamily(FontCharRange fcr) {
        CTRPr pr = this.getRunProperties(false);
        if (pr == null || !pr.isSetRFonts()) {
            return null;
        }
        CTFonts fonts = pr.getRFonts();
        switch (fcr == null ? FontCharRange.ascii : fcr) {
            default: {
                return fonts.getAscii();
            }
            case cs: {
                return fonts.getCs();
            }
            case eastAsia: {
                return fonts.getEastAsia();
            }
            case hAnsi: 
        }
        return fonts.getHAnsi();
    }

    public void setFontFamily(String fontFamily, FontCharRange fcr) {
        CTFonts fonts;
        CTRPr pr = this.getRunProperties(true);
        CTFonts cTFonts = fonts = pr.isSetRFonts() ? pr.getRFonts() : pr.addNewRFonts();
        if (fcr == null) {
            fonts.setAscii(fontFamily);
            if (!fonts.isSetHAnsi()) {
                fonts.setHAnsi(fontFamily);
            }
            if (!fonts.isSetCs()) {
                fonts.setCs(fontFamily);
            }
            if (!fonts.isSetEastAsia()) {
                fonts.setEastAsia(fontFamily);
            }
        } else {
            switch (fcr) {
                case ascii: {
                    fonts.setAscii(fontFamily);
                    break;
                }
                case cs: {
                    fonts.setCs(fontFamily);
                    break;
                }
                case eastAsia: {
                    fonts.setEastAsia(fontFamily);
                    break;
                }
                case hAnsi: {
                    fonts.setHAnsi(fontFamily);
                }
            }
        }
    }

    @Override
    public int getFontSize() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetSz() ? pr.getSz().getVal().divide(new BigInteger("2")).intValue() : -1;
    }

    @Override
    public void setFontSize(int size) {
        BigInteger bint = new BigInteger(Integer.toString(size));
        CTRPr pr = this.getRunProperties(true);
        CTHpsMeasure ctSize = pr.isSetSz() ? pr.getSz() : pr.addNewSz();
        ctSize.setVal(bint.multiply(new BigInteger("2")));
    }

    public int getTextPosition() {
        CTRPr pr = this.getRunProperties(false);
        return pr != null && pr.isSetPosition() ? pr.getPosition().getVal().intValue() : -1;
    }

    public void setTextPosition(int val) {
        BigInteger bint = new BigInteger(Integer.toString(val));
        CTRPr pr = this.getRunProperties(true);
        CTSignedHpsMeasure position = pr.isSetPosition() ? pr.getPosition() : pr.addNewPosition();
        position.setVal(bint);
    }

    public void removeBreak() {
    }

    public void addBreak() {
        this.run.addNewBr();
    }

    public void addBreak(BreakType type) {
        CTBr br = this.run.addNewBr();
        br.setType(STBrType.Enum.forInt(type.getValue()));
    }

    public void addBreak(BreakClear clear) {
        CTBr br = this.run.addNewBr();
        br.setType(STBrType.Enum.forInt(BreakType.TEXT_WRAPPING.getValue()));
        br.setClear(STBrClear.Enum.forInt(clear.getValue()));
    }

    public void addTab() {
        this.run.addNewTab();
    }

    public void removeTab() {
    }

    public void addCarriageReturn() {
        this.run.addNewCr();
    }

    public void removeCarriageReturn() {
    }

    public XWPFPicture addPicture(InputStream pictureData, int pictureType, String filename, int width, int height) throws InvalidFormatException, IOException {
        XWPFPictureData picData;
        if (this.parent.getPart() instanceof XWPFHeaderFooter) {
            XWPFHeaderFooter headerFooter = (XWPFHeaderFooter)this.parent.getPart();
            String relationId = headerFooter.addPictureData(pictureData, pictureType);
            picData = (XWPFPictureData)headerFooter.getRelationById(relationId);
        } else {
            XWPFDocument doc = this.parent.getDocument();
            String relationId = doc.addPictureData(pictureData, pictureType);
            picData = (XWPFPictureData)doc.getRelationById(relationId);
        }
        try {
            CTDrawing drawing = this.run.addNewDrawing();
            CTInline inline = drawing.addNewInline();
            String xml = "<a:graphic xmlns:a=\"" + CTGraphicalObject.type.getName().getNamespaceURI() + "\"><a:graphicData uri=\"" + CTPicture.type.getName().getNamespaceURI() + "\"><pic:pic xmlns:pic=\"" + CTPicture.type.getName().getNamespaceURI() + "\" /></a:graphicData></a:graphic>";
            InputSource is = new InputSource(new StringReader(xml));
            Document doc = DocumentHelper.readDocument(is);
            inline.set(XmlToken.Factory.parse(doc.getDocumentElement(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
            inline.setDistT(0L);
            inline.setDistR(0L);
            inline.setDistB(0L);
            inline.setDistL(0L);
            CTNonVisualDrawingProps docPr = inline.addNewDocPr();
            long id = this.getParent().getDocument().getDrawingIdManager().reserveNew();
            docPr.setId(id);
            docPr.setName("Drawing " + id);
            docPr.setDescr(filename);
            CTPositiveSize2D extent = inline.addNewExtent();
            extent.setCx(width);
            extent.setCy(height);
            CTGraphicalObject graphic = inline.getGraphic();
            CTGraphicalObjectData graphicData = graphic.getGraphicData();
            CTPicture pic = this.getCTPictures(graphicData).get(0);
            CTPictureNonVisual nvPicPr = pic.addNewNvPicPr();
            CTNonVisualDrawingProps cNvPr = nvPicPr.addNewCNvPr();
            cNvPr.setId(0L);
            cNvPr.setName("Picture " + id);
            cNvPr.setDescr(filename);
            CTNonVisualPictureProperties cNvPicPr = nvPicPr.addNewCNvPicPr();
            cNvPicPr.addNewPicLocks().setNoChangeAspect(true);
            CTBlipFillProperties blipFill = pic.addNewBlipFill();
            CTBlip blip = blipFill.addNewBlip();
            blip.setEmbed(this.parent.getPart().getRelationId(picData));
            blipFill.addNewStretch().addNewFillRect();
            CTShapeProperties spPr = pic.addNewSpPr();
            CTTransform2D xfrm = spPr.addNewXfrm();
            CTPoint2D off = xfrm.addNewOff();
            off.setX(0L);
            off.setY(0L);
            CTPositiveSize2D ext = xfrm.addNewExt();
            ext.setCx(width);
            ext.setCy(height);
            CTPresetGeometry2D prstGeom = spPr.addNewPrstGeom();
            prstGeom.setPrst(STShapeType.RECT);
            prstGeom.addNewAvLst();
            XWPFPicture xwpfPicture = new XWPFPicture(pic, this);
            this.pictures.add(xwpfPicture);
            return xwpfPicture;
        } catch (XmlException | SAXException e) {
            throw new IllegalStateException(e);
        }
    }

    @Internal
    public CTInline addChart(String chartRelId) throws InvalidFormatException, IOException {
        try {
            CTInline inline = this.run.addNewDrawing().addNewInline();
            String xml = "<a:graphic xmlns:a=\"" + CTGraphicalObject.type.getName().getNamespaceURI() + "\"><a:graphicData uri=\"" + CTChart.type.getName().getNamespaceURI() + "\"><c:chart xmlns:c=\"" + CTChart.type.getName().getNamespaceURI() + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" r:id=\"" + chartRelId + "\" /></a:graphicData></a:graphic>";
            InputSource is = new InputSource(new StringReader(xml));
            Document doc = DocumentHelper.readDocument(is);
            inline.set(XmlToken.Factory.parse(doc.getDocumentElement(), POIXMLTypeLoader.DEFAULT_XML_OPTIONS));
            inline.setDistT(0L);
            inline.setDistR(0L);
            inline.setDistB(0L);
            inline.setDistL(0L);
            CTNonVisualDrawingProps docPr = inline.addNewDocPr();
            long id = this.getParent().getDocument().getDrawingIdManager().reserveNew();
            docPr.setId(id);
            docPr.setName("chart " + id);
            return inline;
        } catch (XmlException | SAXException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<XWPFPicture> getEmbeddedPictures() {
        return this.pictures;
    }

    public void setStyle(String styleId) {
        CTRPr pr = this.getCTR().getRPr();
        if (null == pr) {
            pr = this.getCTR().addNewRPr();
        }
        CTString style = pr.getRStyle() != null ? pr.getRStyle() : pr.addNewRStyle();
        style.setVal(styleId);
    }

    public String getStyle() {
        CTRPr pr = this.getCTR().getRPr();
        if (pr == null) {
            return "";
        }
        CTString style = pr.getRStyle();
        if (style == null) {
            return "";
        }
        return style.getVal();
    }

    public String toString() {
        String phonetic = this.getPhonetic();
        if (phonetic.length() > 0) {
            return this.text() + " (" + phonetic + ")";
        }
        return this.text();
    }

    @Override
    public String text() {
        StringBuilder text = new StringBuilder(64);
        XmlCursor c = this.run.newCursor();
        c.selectPath("./*");
        while (c.toNextSelection()) {
            XmlObject o = c.getObject();
            if (o instanceof CTRuby) {
                this.handleRuby(o, text, false);
                continue;
            }
            this._getText(o, text);
        }
        c.dispose();
        return text.toString();
    }

    public String getPhonetic() {
        StringBuilder text = new StringBuilder(64);
        XmlCursor c = this.run.newCursor();
        c.selectPath("./*");
        while (c.toNextSelection()) {
            XmlObject o = c.getObject();
            if (!(o instanceof CTRuby)) continue;
            this.handleRuby(o, text, true);
        }
        if (this.pictureText != null && this.pictureText.length() > 0) {
            text.append("\n").append(this.pictureText).append("\n");
        }
        c.dispose();
        return text.toString();
    }

    private void handleRuby(XmlObject rubyObj, StringBuilder text, boolean extractPhonetic) {
        XmlCursor c = rubyObj.newCursor();
        c.selectPath(".//*");
        boolean inRT = false;
        boolean inBase = false;
        while (c.toNextSelection()) {
            XmlObject o = c.getObject();
            if (o instanceof CTRubyContent) {
                String tagName = o.getDomNode().getNodeName();
                if ("w:rt".equals(tagName)) {
                    inRT = true;
                    continue;
                }
                if (!"w:rubyBase".equals(tagName)) continue;
                inRT = false;
                inBase = true;
                continue;
            }
            if (extractPhonetic && inRT) {
                this._getText(o, text);
                continue;
            }
            if (extractPhonetic || !inBase) continue;
            this._getText(o, text);
        }
        c.dispose();
    }

    private void _getText(XmlObject o, StringBuilder text) {
        CTFldChar ctfldChar;
        String tagName;
        if (o instanceof CTText && !"w:instrText".equals(tagName = o.getDomNode().getNodeName())) {
            text.append(((CTText)o).getStringValue());
        }
        if (o instanceof CTFldChar && (ctfldChar = (CTFldChar)o).getFldCharType() == STFldCharType.BEGIN && ctfldChar.getFfData() != null) {
            for (CTFFCheckBox checkBox : ctfldChar.getFfData().getCheckBoxList()) {
                if (checkBox.getDefault() != null && checkBox.getDefault().getVal() == STOnOff.X_1) {
                    text.append("|X|");
                    continue;
                }
                text.append("|_|");
            }
        }
        if (o instanceof CTPTab) {
            text.append('\t');
        }
        if (o instanceof CTBr) {
            text.append('\n');
        }
        if (o instanceof CTEmpty) {
            tagName = o.getDomNode().getNodeName();
            if ("w:tab".equals(tagName) || "tab".equals(tagName)) {
                text.append('\t');
            }
            if ("w:br".equals(tagName) || "br".equals(tagName)) {
                text.append('\n');
            }
            if ("w:cr".equals(tagName) || "cr".equals(tagName)) {
                text.append('\n');
            }
        }
        if (o instanceof CTFtnEdnRef) {
            CTFtnEdnRef ftn = (CTFtnEdnRef)o;
            String footnoteRef = ftn.getDomNode().getLocalName().equals("footnoteReference") ? "[footnoteRef:" + ftn.getId().intValue() + "]" : "[endnoteRef:" + ftn.getId().intValue() + "]";
            text.append(footnoteRef);
        }
    }

    public void setTextScale(int percentage) {
        CTRPr pr = this.getRunProperties(true);
        CTTextScale scale = pr.isSetW() ? pr.getW() : pr.addNewW();
        scale.setVal(percentage);
    }

    public int getTextScale() {
        CTRPr pr = this.getRunProperties(true);
        CTTextScale scale = pr.isSetW() ? pr.getW() : pr.addNewW();
        int value = scale.getVal();
        if (value == 0) {
            value = 100;
        }
        return value;
    }

    public void setTextHighlightColor(String colorName) {
        STHighlightColor.Enum val;
        CTRPr pr = this.getRunProperties(true);
        CTHighlight highlight = pr.isSetHighlight() ? pr.getHighlight() : pr.addNewHighlight();
        STHighlightColor color = highlight.xgetVal();
        if (color == null) {
            color = STHighlightColor.Factory.newInstance();
        }
        if ((val = STHighlightColor.Enum.forString(colorName)) != null) {
            color.setStringValue(val.toString());
            highlight.xsetVal(color);
        }
    }

    public STHighlightColor.Enum getTextHightlightColor() {
        CTRPr pr = this.getRunProperties(true);
        CTHighlight highlight = pr.isSetHighlight() ? pr.getHighlight() : pr.addNewHighlight();
        STHighlightColor color = highlight.xgetVal();
        if (color == null) {
            color = STHighlightColor.Factory.newInstance();
            color.set(STHighlightColor.NONE);
        }
        return (STHighlightColor.Enum)color.enumValue();
    }

    public boolean isVanish() {
        CTRPr pr = this.getRunProperties(true);
        return pr != null && pr.isSetVanish() && XWPFRun.isCTOnOff(pr.getVanish());
    }

    public void setVanish(boolean value) {
        CTRPr pr = this.getRunProperties(true);
        CTOnOff vanish = pr.isSetVanish() ? pr.getVanish() : pr.addNewVanish();
        vanish.setVal(value ? STOnOff.TRUE : STOnOff.FALSE);
    }

    public STVerticalAlignRun.Enum getVerticalAlignment() {
        CTRPr pr = this.getRunProperties(true);
        CTVerticalAlignRun vertAlign = pr.isSetVertAlign() ? pr.getVertAlign() : pr.addNewVertAlign();
        STVerticalAlignRun.Enum val = vertAlign.getVal();
        if (val == null) {
            val = STVerticalAlignRun.BASELINE;
        }
        return val;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        STVerticalAlignRun.Enum val;
        CTRPr pr = this.getRunProperties(true);
        CTVerticalAlignRun vertAlign = pr.isSetVertAlign() ? pr.getVertAlign() : pr.addNewVertAlign();
        STVerticalAlignRun align = vertAlign.xgetVal();
        if (align == null) {
            align = STVerticalAlignRun.Factory.newInstance();
        }
        if ((val = STVerticalAlignRun.Enum.forString(verticalAlignment)) != null) {
            align.setStringValue(val.toString());
            vertAlign.xsetVal(align);
        }
    }

    public STEm.Enum getEmphasisMark() {
        CTRPr pr = this.getRunProperties(true);
        CTEm emphasis = pr.isSetEm() ? pr.getEm() : pr.addNewEm();
        STEm.Enum val = emphasis.getVal();
        if (val == null) {
            val = STEm.NONE;
        }
        return val;
    }

    public void setEmphasisMark(String markType) {
        STEm.Enum val;
        CTRPr pr = this.getRunProperties(true);
        CTEm emphasisMark = pr.isSetEm() ? pr.getEm() : pr.addNewEm();
        STEm mark = emphasisMark.xgetVal();
        if (mark == null) {
            mark = STEm.Factory.newInstance();
        }
        if ((val = STEm.Enum.forString(markType)) != null) {
            mark.setStringValue(val.toString());
            emphasisMark.xsetVal(mark);
        }
    }

    protected CTRPr getRunProperties(boolean create) {
        CTRPr pr;
        CTRPr cTRPr = pr = this.run.isSetRPr() ? this.run.getRPr() : null;
        if (create && pr == null) {
            pr = this.run.addNewRPr();
        }
        return pr;
    }

    public static enum FontCharRange {
        ascii,
        cs,
        eastAsia,
        hAnsi;

    }
}

