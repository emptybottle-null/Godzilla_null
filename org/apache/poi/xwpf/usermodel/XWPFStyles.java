/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.XWPFDefaultParagraphStyle;
import org.apache.poi.xwpf.usermodel.XWPFDefaultRunStyle;
import org.apache.poi.xwpf.usermodel.XWPFLatentStyles;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument;

public class XWPFStyles
extends POIXMLDocumentPart {
    private CTStyles ctStyles;
    private List<XWPFStyle> listStyle = new ArrayList<XWPFStyle>();
    private XWPFLatentStyles latentStyles;
    private XWPFDefaultRunStyle defaultRunStyle;
    private XWPFDefaultParagraphStyle defaultParaStyle;

    public XWPFStyles(PackagePart part) {
        super(part);
    }

    public XWPFStyles() {
    }

    @Override
    protected void onDocumentRead() throws IOException {
        try (InputStream is = this.getPackagePart().getInputStream();){
            StylesDocument stylesDoc = StylesDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            this.setStyles(stylesDoc.getStyles());
            this.latentStyles = new XWPFLatentStyles(this.ctStyles.getLatentStyles(), this);
        } catch (XmlException e) {
            throw new POIXMLException("Unable to read styles", e);
        }
    }

    @Override
    protected void commit() throws IOException {
        if (this.ctStyles == null) {
            throw new IllegalStateException("Unable to write out styles that were never read in!");
        }
        XmlOptions xmlOptions = new XmlOptions(POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
        xmlOptions.setSaveSyntheticDocumentElement(new QName(CTStyles.type.getName().getNamespaceURI(), "styles"));
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.ctStyles.save(out, xmlOptions);
        out.close();
    }

    protected void ensureDocDefaults() {
        CTDocDefaults docDefaults;
        if (!this.ctStyles.isSetDocDefaults()) {
            this.ctStyles.addNewDocDefaults();
        }
        if (!(docDefaults = this.ctStyles.getDocDefaults()).isSetPPrDefault()) {
            docDefaults.addNewPPrDefault();
        }
        if (!docDefaults.isSetRPrDefault()) {
            docDefaults.addNewRPrDefault();
        }
        CTPPrDefault pprd = docDefaults.getPPrDefault();
        CTRPrDefault rprd = docDefaults.getRPrDefault();
        if (!pprd.isSetPPr()) {
            pprd.addNewPPr();
        }
        if (!rprd.isSetRPr()) {
            rprd.addNewRPr();
        }
        this.defaultRunStyle = new XWPFDefaultRunStyle(rprd.getRPr());
        this.defaultParaStyle = new XWPFDefaultParagraphStyle(pprd.getPPr());
    }

    public void setStyles(CTStyles styles) {
        this.ctStyles = styles;
        for (CTStyle style : this.ctStyles.getStyleArray()) {
            this.listStyle.add(new XWPFStyle(style, this));
        }
        if (this.ctStyles.isSetDocDefaults()) {
            CTDocDefaults docDefaults = this.ctStyles.getDocDefaults();
            if (docDefaults.isSetRPrDefault() && docDefaults.getRPrDefault().isSetRPr()) {
                this.defaultRunStyle = new XWPFDefaultRunStyle(docDefaults.getRPrDefault().getRPr());
            }
            if (docDefaults.isSetPPrDefault() && docDefaults.getPPrDefault().isSetPPr()) {
                this.defaultParaStyle = new XWPFDefaultParagraphStyle(docDefaults.getPPrDefault().getPPr());
            }
        }
    }

    public boolean styleExist(String styleID) {
        for (XWPFStyle style : this.listStyle) {
            if (!style.getStyleId().equals(styleID)) continue;
            return true;
        }
        return false;
    }

    public void addStyle(XWPFStyle style) {
        this.listStyle.add(style);
        this.ctStyles.addNewStyle();
        int pos = this.ctStyles.sizeOfStyleArray() - 1;
        this.ctStyles.setStyleArray(pos, style.getCTStyle());
    }

    public XWPFStyle getStyle(String styleID) {
        for (XWPFStyle style : this.listStyle) {
            try {
                if (!style.getStyleId().equals(styleID)) continue;
                return style;
            } catch (NullPointerException nullPointerException) {
            }
        }
        return null;
    }

    public int getNumberOfStyles() {
        return this.listStyle.size();
    }

    public List<XWPFStyle> getUsedStyleList(XWPFStyle style) {
        ArrayList<XWPFStyle> usedStyleList = new ArrayList<XWPFStyle>();
        usedStyleList.add(style);
        return this.getUsedStyleList(style, usedStyleList);
    }

    private List<XWPFStyle> getUsedStyleList(XWPFStyle style, List<XWPFStyle> usedStyleList) {
        String nextStyleID;
        XWPFStyle nextStyle;
        String linkStyleID;
        XWPFStyle linkStyle;
        String basisStyleID = style.getBasisStyleID();
        XWPFStyle basisStyle = this.getStyle(basisStyleID);
        if (basisStyle != null && !usedStyleList.contains(basisStyle)) {
            usedStyleList.add(basisStyle);
            this.getUsedStyleList(basisStyle, usedStyleList);
        }
        if ((linkStyle = this.getStyle(linkStyleID = style.getLinkStyleID())) != null && !usedStyleList.contains(linkStyle)) {
            usedStyleList.add(linkStyle);
            this.getUsedStyleList(linkStyle, usedStyleList);
        }
        if ((nextStyle = this.getStyle(nextStyleID = style.getNextStyleID())) != null && !usedStyleList.contains(nextStyle)) {
            usedStyleList.add(linkStyle);
            this.getUsedStyleList(linkStyle, usedStyleList);
        }
        return usedStyleList;
    }

    protected CTLanguage getCTLanguage() {
        this.ensureDocDefaults();
        CTLanguage lang = null;
        lang = this.defaultRunStyle.getRPr().isSetLang() ? this.defaultRunStyle.getRPr().getLang() : this.defaultRunStyle.getRPr().addNewLang();
        return lang;
    }

    public void setSpellingLanguage(String strSpellingLanguage) {
        CTLanguage lang = this.getCTLanguage();
        lang.setVal(strSpellingLanguage);
        lang.setBidi(strSpellingLanguage);
    }

    public void setEastAsia(String strEastAsia) {
        CTLanguage lang = this.getCTLanguage();
        lang.setEastAsia(strEastAsia);
    }

    public void setDefaultFonts(CTFonts fonts) {
        this.ensureDocDefaults();
        CTRPr runProps = this.defaultRunStyle.getRPr();
        runProps.setRFonts(fonts);
    }

    public XWPFStyle getStyleWithSameName(XWPFStyle style) {
        for (XWPFStyle ownStyle : this.listStyle) {
            if (!ownStyle.hasSameName(style)) continue;
            return ownStyle;
        }
        return null;
    }

    public XWPFDefaultRunStyle getDefaultRunStyle() {
        this.ensureDocDefaults();
        return this.defaultRunStyle;
    }

    public XWPFDefaultParagraphStyle getDefaultParagraphStyle() {
        this.ensureDocDefaults();
        return this.defaultParaStyle;
    }

    public XWPFLatentStyles getLatentStyles() {
        return this.latentStyles;
    }

    public XWPFStyle getStyleWithName(String styleName) {
        XWPFStyle style = null;
        for (XWPFStyle cand : this.listStyle) {
            if (!styleName.equals(cand.getName())) continue;
            style = cand;
            break;
        }
        return style;
    }
}

