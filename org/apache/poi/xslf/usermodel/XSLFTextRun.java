/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.xslf.model.CharacterPropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFColor;
import org.apache.poi.xslf.usermodel.XSLFHyperlink;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xslf.usermodel.XSLFTheme;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

public class XSLFTextRun
implements TextRun {
    private static final POILogger LOG = POILogFactory.getLogger(XSLFTextRun.class);
    private final XmlObject _r;
    private final XSLFTextParagraph _p;

    protected XSLFTextRun(XmlObject r, XSLFTextParagraph p) {
        this._r = r;
        this._p = p;
        if (!(r instanceof CTRegularTextRun || r instanceof CTTextLineBreak || r instanceof CTTextField)) {
            throw new OpenXML4JRuntimeException("unsupported text run of type " + r.getClass());
        }
    }

    XSLFTextParagraph getParentParagraph() {
        return this._p;
    }

    @Override
    public String getRawText() {
        if (this._r instanceof CTTextField) {
            return ((CTTextField)this._r).getT();
        }
        if (this._r instanceof CTTextLineBreak) {
            return "\n";
        }
        return ((CTRegularTextRun)this._r).getT();
    }

    @Override
    public void setText(String text) {
        if (this._r instanceof CTTextField) {
            ((CTTextField)this._r).setT(text);
        } else if (!(this._r instanceof CTTextLineBreak)) {
            ((CTRegularTextRun)this._r).setT(text);
        }
    }

    @Internal
    public XmlObject getXmlObject() {
        return this._r;
    }

    @Override
    public void setFontColor(Color color) {
        this.setFontColor(DrawPaint.createSolidPaint(color));
    }

    @Override
    public void setFontColor(PaintStyle color) {
        if (!(color instanceof PaintStyle.SolidPaint)) {
            LOG.log(5, "Currently only SolidPaint is supported!");
            return;
        }
        PaintStyle.SolidPaint sp = (PaintStyle.SolidPaint)color;
        Color c = DrawPaint.applyColorTransform(sp.getSolidColor());
        CTTextCharacterProperties rPr = this.getRPr(true);
        CTSolidColorFillProperties fill = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
        XSLFSheet sheet = this.getParentParagraph().getParentShape().getSheet();
        XSLFColor col = new XSLFColor(fill, sheet.getTheme(), fill.getSchemeClr(), sheet);
        col.setColor(c);
    }

    @Override
    public PaintStyle getFontColor() {
        final boolean hasPlaceholder = this.getParentParagraph().getParentShape().getPlaceholder() != null;
        CharacterPropertyFetcher<PaintStyle> fetcher = new CharacterPropertyFetcher<PaintStyle>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                XSLFTheme theme;
                XSLFSheet sheet;
                PackagePart pp;
                XSLFPropertiesDelegate.XSLFFillProperties fp;
                PaintStyle ps;
                if (props == null) {
                    return false;
                }
                XSLFTextShape shape = XSLFTextRun.this._p.getParentShape();
                CTShapeStyle style = shape.getSpStyle();
                CTSchemeColor phClr = null;
                if (style != null && style.getFontRef() != null) {
                    phClr = style.getFontRef().getSchemeClr();
                }
                if ((ps = shape.selectPaint(fp = XSLFPropertiesDelegate.getFillDelegate(props), phClr, pp = (sheet = shape.getSheet()).getPackagePart(), theme = sheet.getTheme(), hasPlaceholder)) != null) {
                    this.setValue(ps);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return (PaintStyle)fetcher.getValue();
    }

    @Override
    public void setFontSize(Double fontSize) {
        CTTextCharacterProperties rPr = this.getRPr(true);
        if (fontSize == null) {
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

    @Override
    public Double getFontSize() {
        CTTextNormalAutofit afit;
        CTTextBodyProperties tbp;
        double scale = 1.0;
        XSLFTextShape ps = this.getParentParagraph().getParentShape();
        if (ps != null && (tbp = ps.getTextBodyPr()) != null && (afit = tbp.getNormAutofit()) != null && afit.isSetFontScale()) {
            scale = (double)afit.getFontScale() / 100000.0;
        }
        CharacterPropertyFetcher<Double> fetcher = new CharacterPropertyFetcher<Double>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetSz()) {
                    this.setValue((double)props.getSz() * 0.01);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? null : Double.valueOf((Double)fetcher.getValue() * scale);
    }

    public double getCharacterSpacing() {
        CharacterPropertyFetcher<Double> fetcher = new CharacterPropertyFetcher<Double>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetSpc()) {
                    this.setValue((double)props.getSpc() * 0.01);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? 0.0 : (Double)fetcher.getValue();
    }

    public void setCharacterSpacing(double spc) {
        CTTextCharacterProperties rPr = this.getRPr(true);
        if (spc == 0.0) {
            if (rPr.isSetSpc()) {
                rPr.unsetSpc();
            }
        } else {
            rPr.setSpc((int)(100.0 * spc));
        }
    }

    @Override
    public void setFontFamily(String typeface) {
        FontGroup fg = FontGroup.getFontGroupFirst(this.getRawText());
        new XSLFFontInfo(fg).setTypeface(typeface);
    }

    @Override
    public void setFontFamily(String typeface, FontGroup fontGroup) {
        new XSLFFontInfo(fontGroup).setTypeface(typeface);
    }

    @Override
    public void setFontInfo(FontInfo fontInfo, FontGroup fontGroup) {
        new XSLFFontInfo(fontGroup).copyFrom(fontInfo);
    }

    @Override
    public String getFontFamily() {
        FontGroup fg = FontGroup.getFontGroupFirst(this.getRawText());
        return new XSLFFontInfo(fg).getTypeface();
    }

    @Override
    public String getFontFamily(FontGroup fontGroup) {
        return new XSLFFontInfo(fontGroup).getTypeface();
    }

    @Override
    public FontInfo getFontInfo(FontGroup fontGroup) {
        XSLFFontInfo fontInfo = new XSLFFontInfo(fontGroup);
        return fontInfo.getTypeface() != null ? fontInfo : null;
    }

    @Override
    public byte getPitchAndFamily() {
        FontFamily family;
        FontGroup fg = FontGroup.getFontGroupFirst(this.getRawText());
        XSLFFontInfo fontInfo = new XSLFFontInfo(fg);
        FontPitch pitch = fontInfo.getPitch();
        if (pitch == null) {
            pitch = FontPitch.VARIABLE;
        }
        if ((family = fontInfo.getFamily()) == null) {
            family = FontFamily.FF_SWISS;
        }
        return FontPitch.getNativeId(pitch, family);
    }

    @Override
    public void setStrikethrough(boolean strike) {
        this.getRPr(true).setStrike(strike ? STTextStrikeType.SNG_STRIKE : STTextStrikeType.NO_STRIKE);
    }

    @Override
    public boolean isStrikethrough() {
        CharacterPropertyFetcher<Boolean> fetcher = new CharacterPropertyFetcher<Boolean>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetStrike()) {
                    this.setValue(props.getStrike() != STTextStrikeType.NO_STRIKE);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    @Override
    public boolean isSuperscript() {
        CharacterPropertyFetcher<Boolean> fetcher = new CharacterPropertyFetcher<Boolean>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetBaseline()) {
                    this.setValue(props.getBaseline() > 0);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    public void setBaselineOffset(double baselineOffset) {
        this.getRPr(true).setBaseline((int)baselineOffset * 1000);
    }

    public void setSuperscript(boolean flag) {
        this.setBaselineOffset(flag ? 30.0 : 0.0);
    }

    public void setSubscript(boolean flag) {
        this.setBaselineOffset(flag ? -25.0 : 0.0);
    }

    @Override
    public boolean isSubscript() {
        CharacterPropertyFetcher<Boolean> fetcher = new CharacterPropertyFetcher<Boolean>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetBaseline()) {
                    this.setValue(props.getBaseline() < 0);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    @Override
    public TextRun.TextCap getTextCap() {
        CharacterPropertyFetcher<TextRun.TextCap> fetcher = new CharacterPropertyFetcher<TextRun.TextCap>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetCap()) {
                    int idx = props.getCap().intValue() - 1;
                    this.setValue(TextRun.TextCap.values()[idx]);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? TextRun.TextCap.NONE : (TextRun.TextCap)((Object)fetcher.getValue());
    }

    @Override
    public void setBold(boolean bold) {
        this.getRPr(true).setB(bold);
    }

    @Override
    public boolean isBold() {
        CharacterPropertyFetcher<Boolean> fetcher = new CharacterPropertyFetcher<Boolean>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetB()) {
                    this.setValue(props.getB());
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    @Override
    public void setItalic(boolean italic) {
        this.getRPr(true).setI(italic);
    }

    @Override
    public boolean isItalic() {
        CharacterPropertyFetcher<Boolean> fetcher = new CharacterPropertyFetcher<Boolean>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetI()) {
                    this.setValue(props.getI());
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    @Override
    public void setUnderlined(boolean underline) {
        this.getRPr(true).setU(underline ? STTextUnderlineType.SNG : STTextUnderlineType.NONE);
    }

    @Override
    public boolean isUnderlined() {
        CharacterPropertyFetcher<Boolean> fetcher = new CharacterPropertyFetcher<Boolean>(this._p.getIndentLevel()){

            @Override
            public boolean fetch(CTTextCharacterProperties props) {
                if (props != null && props.isSetU()) {
                    this.setValue(props.getU() != STTextUnderlineType.NONE);
                    return true;
                }
                return false;
            }
        };
        this.fetchCharacterProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    protected CTTextCharacterProperties getRPr(boolean create) {
        if (this._r instanceof CTTextField) {
            CTTextField tf = (CTTextField)this._r;
            if (tf.isSetRPr()) {
                return tf.getRPr();
            }
            if (create) {
                return tf.addNewRPr();
            }
        } else if (this._r instanceof CTTextLineBreak) {
            CTTextLineBreak tlb = (CTTextLineBreak)this._r;
            if (tlb.isSetRPr()) {
                return tlb.getRPr();
            }
            if (create) {
                return tlb.addNewRPr();
            }
        } else {
            CTRegularTextRun tr = (CTRegularTextRun)this._r;
            if (tr.isSetRPr()) {
                return tr.getRPr();
            }
            if (create) {
                return tr.addNewRPr();
            }
        }
        return null;
    }

    public String toString() {
        return "[" + this.getClass() + "]" + this.getRawText();
    }

    public XSLFHyperlink createHyperlink() {
        XSLFHyperlink hl = this.getHyperlink();
        if (hl != null) {
            return hl;
        }
        CTTextCharacterProperties rPr = this.getRPr(true);
        return new XSLFHyperlink(rPr.addNewHlinkClick(), this._p.getParentShape().getSheet());
    }

    public XSLFHyperlink getHyperlink() {
        CTTextCharacterProperties rPr = this.getRPr(false);
        if (rPr == null) {
            return null;
        }
        CTHyperlink hl = rPr.getHlinkClick();
        if (hl == null) {
            return null;
        }
        return new XSLFHyperlink(hl, this._p.getParentShape().getSheet());
    }

    private void fetchCharacterProperty(CharacterPropertyFetcher<?> visitor) {
        XSLFTextShape shape = this._p.getParentShape();
        CTTextCharacterProperties rPr = this.getRPr(false);
        if (rPr != null && visitor.fetch(rPr)) {
            return;
        }
        if (shape.fetchShapeProperty(visitor)) {
            return;
        }
        if (this._p.fetchThemeProperty(visitor)) {
            return;
        }
        this._p.fetchMasterProperty(visitor);
    }

    void copy(XSLFTextRun r) {
        XSLFHyperlink hyperSrc;
        boolean strike;
        boolean underline;
        boolean italic;
        boolean bold;
        Double srcFontSize;
        PaintStyle srcFontColor;
        String srcFontFamily = r.getFontFamily();
        if (srcFontFamily != null && !srcFontFamily.equals(this.getFontFamily())) {
            this.setFontFamily(srcFontFamily);
        }
        if ((srcFontColor = r.getFontColor()) != null && !srcFontColor.equals(this.getFontColor())) {
            this.setFontColor(srcFontColor);
        }
        if ((srcFontSize = r.getFontSize()) == null) {
            if (this.getFontSize() != null) {
                this.setFontSize(null);
            }
        } else if (!srcFontSize.equals(this.getFontSize())) {
            this.setFontSize(srcFontSize);
        }
        if ((bold = r.isBold()) != this.isBold()) {
            this.setBold(bold);
        }
        if ((italic = r.isItalic()) != this.isItalic()) {
            this.setItalic(italic);
        }
        if ((underline = r.isUnderlined()) != this.isUnderlined()) {
            this.setUnderlined(underline);
        }
        if ((strike = r.isStrikethrough()) != this.isStrikethrough()) {
            this.setStrikethrough(strike);
        }
        if ((hyperSrc = r.getHyperlink()) != null) {
            XSLFHyperlink hyperDst = this.getHyperlink();
            hyperDst.copy(hyperSrc);
        }
    }

    @Override
    public TextRun.FieldType getFieldType() {
        CTTextField tf;
        if (this._r instanceof CTTextField && "slidenum".equals((tf = (CTTextField)this._r).getType())) {
            return TextRun.FieldType.SLIDE_NUMBER;
        }
        return null;
    }

    public XSLFTextParagraph getParagraph() {
        return this._p;
    }

    private final class XSLFFontInfo
    implements FontInfo {
        private final FontGroup fontGroup;

        private XSLFFontInfo(FontGroup fontGroup) {
            this.fontGroup = fontGroup != null ? fontGroup : FontGroup.getFontGroupFirst(XSLFTextRun.this.getRawText());
        }

        void copyFrom(FontInfo fontInfo) {
            CTTextFont tf = this.getXmlObject(true);
            if (tf == null) {
                return;
            }
            this.setTypeface(fontInfo.getTypeface());
            this.setCharset(fontInfo.getCharset());
            FontPitch pitch = fontInfo.getPitch();
            FontFamily family = fontInfo.getFamily();
            if (pitch == null && family == null) {
                if (tf.isSetPitchFamily()) {
                    tf.unsetPitchFamily();
                }
            } else {
                this.setPitch(pitch);
                this.setFamily(family);
            }
        }

        @Override
        public String getTypeface() {
            CTTextFont tf = this.getXmlObject(false);
            return tf != null && tf.isSetTypeface() ? tf.getTypeface() : null;
        }

        @Override
        public void setTypeface(String typeface) {
            if (typeface != null) {
                CTTextFont tf = this.getXmlObject(true);
                if (tf != null) {
                    tf.setTypeface(typeface);
                }
                return;
            }
            CTTextCharacterProperties props = XSLFTextRun.this.getRPr(false);
            if (props == null) {
                return;
            }
            FontGroup fg = FontGroup.getFontGroupFirst(XSLFTextRun.this.getRawText());
            switch (fg) {
                default: {
                    if (!props.isSetLatin()) break;
                    props.unsetLatin();
                    break;
                }
                case EAST_ASIAN: {
                    if (!props.isSetEa()) break;
                    props.unsetEa();
                    break;
                }
                case COMPLEX_SCRIPT: {
                    if (!props.isSetCs()) break;
                    props.unsetCs();
                    break;
                }
                case SYMBOL: {
                    if (!props.isSetSym()) break;
                    props.unsetSym();
                }
            }
        }

        @Override
        public FontCharset getCharset() {
            CTTextFont tf = this.getXmlObject(false);
            return tf != null && tf.isSetCharset() ? FontCharset.valueOf(tf.getCharset() & 0xFF) : null;
        }

        @Override
        public void setCharset(FontCharset charset) {
            CTTextFont tf = this.getXmlObject(true);
            if (tf == null) {
                return;
            }
            if (charset != null) {
                tf.setCharset((byte)charset.getNativeId());
            } else if (tf.isSetCharset()) {
                tf.unsetCharset();
            }
        }

        @Override
        public FontFamily getFamily() {
            CTTextFont tf = this.getXmlObject(false);
            return tf != null && tf.isSetPitchFamily() ? FontFamily.valueOfPitchFamily(tf.getPitchFamily()) : null;
        }

        @Override
        public void setFamily(FontFamily family) {
            CTTextFont tf = this.getXmlObject(true);
            if (tf == null || family == null && !tf.isSetPitchFamily()) {
                return;
            }
            FontPitch pitch = tf.isSetPitchFamily() ? FontPitch.valueOfPitchFamily(tf.getPitchFamily()) : FontPitch.VARIABLE;
            byte pitchFamily = FontPitch.getNativeId(pitch, family != null ? family : FontFamily.FF_SWISS);
            tf.setPitchFamily(pitchFamily);
        }

        @Override
        public FontPitch getPitch() {
            CTTextFont tf = this.getXmlObject(false);
            return tf != null && tf.isSetPitchFamily() ? FontPitch.valueOfPitchFamily(tf.getPitchFamily()) : null;
        }

        @Override
        public void setPitch(FontPitch pitch) {
            CTTextFont tf = this.getXmlObject(true);
            if (tf == null || pitch == null && !tf.isSetPitchFamily()) {
                return;
            }
            FontFamily family = tf.isSetPitchFamily() ? FontFamily.valueOfPitchFamily(tf.getPitchFamily()) : FontFamily.FF_SWISS;
            byte pitchFamily = FontPitch.getNativeId(pitch != null ? pitch : FontPitch.VARIABLE, family);
            tf.setPitchFamily(pitchFamily);
        }

        private CTTextFont getXmlObject(boolean create) {
            if (create) {
                return this.getCTTextFont(XSLFTextRun.this.getRPr(true), true);
            }
            CharacterPropertyFetcher<CTTextFont> visitor = new CharacterPropertyFetcher<CTTextFont>(XSLFTextRun.this._p.getIndentLevel()){

                @Override
                public boolean fetch(CTTextCharacterProperties props) {
                    CTTextFont font = XSLFFontInfo.this.getCTTextFont(props, false);
                    if (font == null) {
                        return false;
                    }
                    this.setValue(font);
                    return true;
                }
            };
            XSLFTextRun.this.fetchCharacterProperty(visitor);
            return (CTTextFont)visitor.getValue();
        }

        private CTTextFont getCTTextFont(CTTextCharacterProperties props, boolean create) {
            String typeface;
            CTTextFont font;
            if (props == null) {
                return null;
            }
            switch (this.fontGroup) {
                default: {
                    font = props.getLatin();
                    if (font != null || !create) break;
                    font = props.addNewLatin();
                    break;
                }
                case EAST_ASIAN: {
                    font = props.getEa();
                    if (font != null || !create) break;
                    font = props.addNewEa();
                    break;
                }
                case COMPLEX_SCRIPT: {
                    font = props.getCs();
                    if (font != null || !create) break;
                    font = props.addNewCs();
                    break;
                }
                case SYMBOL: {
                    font = props.getSym();
                    if (font != null || !create) break;
                    font = props.addNewSym();
                }
            }
            if (font == null) {
                return null;
            }
            String string = typeface = font.isSetTypeface() ? font.getTypeface() : "";
            if (typeface.startsWith("+mj-") || typeface.startsWith("+mn-")) {
                XSLFTheme theme = XSLFTextRun.this._p.getParentShape().getSheet().getTheme();
                CTFontScheme fontTheme = theme.getXmlObject().getThemeElements().getFontScheme();
                CTFontCollection coll = typeface.startsWith("+mj-") ? fontTheme.getMajorFont() : fontTheme.getMinorFont();
                String fgStr = typeface.substring(4);
                font = "ea".equals(fgStr) ? coll.getEa() : ("cs".equals(fgStr) ? coll.getCs() : coll.getLatin());
                if (font == null || !font.isSetTypeface() || "".equals(font.getTypeface())) {
                    font = coll.getLatin();
                }
            }
            return font;
        }
    }
}

