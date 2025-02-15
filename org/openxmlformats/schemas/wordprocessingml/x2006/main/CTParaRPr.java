/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFitText
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFitText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;
import org.w3c.dom.Node;

public interface CTParaRPr
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTParaRPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpararprd6fetype");

    public CTTrackChange getIns();

    public boolean isSetIns();

    public void setIns(CTTrackChange var1);

    public CTTrackChange addNewIns();

    public void unsetIns();

    public CTTrackChange getDel();

    public boolean isSetDel();

    public void setDel(CTTrackChange var1);

    public CTTrackChange addNewDel();

    public void unsetDel();

    public CTTrackChange getMoveFrom();

    public boolean isSetMoveFrom();

    public void setMoveFrom(CTTrackChange var1);

    public CTTrackChange addNewMoveFrom();

    public void unsetMoveFrom();

    public CTTrackChange getMoveTo();

    public boolean isSetMoveTo();

    public void setMoveTo(CTTrackChange var1);

    public CTTrackChange addNewMoveTo();

    public void unsetMoveTo();

    public CTString getRStyle();

    public boolean isSetRStyle();

    public void setRStyle(CTString var1);

    public CTString addNewRStyle();

    public void unsetRStyle();

    public CTFonts getRFonts();

    public boolean isSetRFonts();

    public void setRFonts(CTFonts var1);

    public CTFonts addNewRFonts();

    public void unsetRFonts();

    public CTOnOff getB();

    public boolean isSetB();

    public void setB(CTOnOff var1);

    public CTOnOff addNewB();

    public void unsetB();

    public CTOnOff getBCs();

    public boolean isSetBCs();

    public void setBCs(CTOnOff var1);

    public CTOnOff addNewBCs();

    public void unsetBCs();

    public CTOnOff getI();

    public boolean isSetI();

    public void setI(CTOnOff var1);

    public CTOnOff addNewI();

    public void unsetI();

    public CTOnOff getICs();

    public boolean isSetICs();

    public void setICs(CTOnOff var1);

    public CTOnOff addNewICs();

    public void unsetICs();

    public CTOnOff getCaps();

    public boolean isSetCaps();

    public void setCaps(CTOnOff var1);

    public CTOnOff addNewCaps();

    public void unsetCaps();

    public CTOnOff getSmallCaps();

    public boolean isSetSmallCaps();

    public void setSmallCaps(CTOnOff var1);

    public CTOnOff addNewSmallCaps();

    public void unsetSmallCaps();

    public CTOnOff getStrike();

    public boolean isSetStrike();

    public void setStrike(CTOnOff var1);

    public CTOnOff addNewStrike();

    public void unsetStrike();

    public CTOnOff getDstrike();

    public boolean isSetDstrike();

    public void setDstrike(CTOnOff var1);

    public CTOnOff addNewDstrike();

    public void unsetDstrike();

    public CTOnOff getOutline();

    public boolean isSetOutline();

    public void setOutline(CTOnOff var1);

    public CTOnOff addNewOutline();

    public void unsetOutline();

    public CTOnOff getShadow();

    public boolean isSetShadow();

    public void setShadow(CTOnOff var1);

    public CTOnOff addNewShadow();

    public void unsetShadow();

    public CTOnOff getEmboss();

    public boolean isSetEmboss();

    public void setEmboss(CTOnOff var1);

    public CTOnOff addNewEmboss();

    public void unsetEmboss();

    public CTOnOff getImprint();

    public boolean isSetImprint();

    public void setImprint(CTOnOff var1);

    public CTOnOff addNewImprint();

    public void unsetImprint();

    public CTOnOff getNoProof();

    public boolean isSetNoProof();

    public void setNoProof(CTOnOff var1);

    public CTOnOff addNewNoProof();

    public void unsetNoProof();

    public CTOnOff getSnapToGrid();

    public boolean isSetSnapToGrid();

    public void setSnapToGrid(CTOnOff var1);

    public CTOnOff addNewSnapToGrid();

    public void unsetSnapToGrid();

    public CTOnOff getVanish();

    public boolean isSetVanish();

    public void setVanish(CTOnOff var1);

    public CTOnOff addNewVanish();

    public void unsetVanish();

    public CTOnOff getWebHidden();

    public boolean isSetWebHidden();

    public void setWebHidden(CTOnOff var1);

    public CTOnOff addNewWebHidden();

    public void unsetWebHidden();

    public CTColor getColor();

    public boolean isSetColor();

    public void setColor(CTColor var1);

    public CTColor addNewColor();

    public void unsetColor();

    public CTSignedTwipsMeasure getSpacing();

    public boolean isSetSpacing();

    public void setSpacing(CTSignedTwipsMeasure var1);

    public CTSignedTwipsMeasure addNewSpacing();

    public void unsetSpacing();

    public CTTextScale getW();

    public boolean isSetW();

    public void setW(CTTextScale var1);

    public CTTextScale addNewW();

    public void unsetW();

    public CTHpsMeasure getKern();

    public boolean isSetKern();

    public void setKern(CTHpsMeasure var1);

    public CTHpsMeasure addNewKern();

    public void unsetKern();

    public CTSignedHpsMeasure getPosition();

    public boolean isSetPosition();

    public void setPosition(CTSignedHpsMeasure var1);

    public CTSignedHpsMeasure addNewPosition();

    public void unsetPosition();

    public CTHpsMeasure getSz();

    public boolean isSetSz();

    public void setSz(CTHpsMeasure var1);

    public CTHpsMeasure addNewSz();

    public void unsetSz();

    public CTHpsMeasure getSzCs();

    public boolean isSetSzCs();

    public void setSzCs(CTHpsMeasure var1);

    public CTHpsMeasure addNewSzCs();

    public void unsetSzCs();

    public CTHighlight getHighlight();

    public boolean isSetHighlight();

    public void setHighlight(CTHighlight var1);

    public CTHighlight addNewHighlight();

    public void unsetHighlight();

    public CTUnderline getU();

    public boolean isSetU();

    public void setU(CTUnderline var1);

    public CTUnderline addNewU();

    public void unsetU();

    public CTTextEffect getEffect();

    public boolean isSetEffect();

    public void setEffect(CTTextEffect var1);

    public CTTextEffect addNewEffect();

    public void unsetEffect();

    public CTBorder getBdr();

    public boolean isSetBdr();

    public void setBdr(CTBorder var1);

    public CTBorder addNewBdr();

    public void unsetBdr();

    public CTShd getShd();

    public boolean isSetShd();

    public void setShd(CTShd var1);

    public CTShd addNewShd();

    public void unsetShd();

    public CTFitText getFitText();

    public boolean isSetFitText();

    public void setFitText(CTFitText var1);

    public CTFitText addNewFitText();

    public void unsetFitText();

    public CTVerticalAlignRun getVertAlign();

    public boolean isSetVertAlign();

    public void setVertAlign(CTVerticalAlignRun var1);

    public CTVerticalAlignRun addNewVertAlign();

    public void unsetVertAlign();

    public CTOnOff getRtl();

    public boolean isSetRtl();

    public void setRtl(CTOnOff var1);

    public CTOnOff addNewRtl();

    public void unsetRtl();

    public CTOnOff getCs();

    public boolean isSetCs();

    public void setCs(CTOnOff var1);

    public CTOnOff addNewCs();

    public void unsetCs();

    public CTEm getEm();

    public boolean isSetEm();

    public void setEm(CTEm var1);

    public CTEm addNewEm();

    public void unsetEm();

    public CTLanguage getLang();

    public boolean isSetLang();

    public void setLang(CTLanguage var1);

    public CTLanguage addNewLang();

    public void unsetLang();

    public CTEastAsianLayout getEastAsianLayout();

    public boolean isSetEastAsianLayout();

    public void setEastAsianLayout(CTEastAsianLayout var1);

    public CTEastAsianLayout addNewEastAsianLayout();

    public void unsetEastAsianLayout();

    public CTOnOff getSpecVanish();

    public boolean isSetSpecVanish();

    public void setSpecVanish(CTOnOff var1);

    public CTOnOff addNewSpecVanish();

    public void unsetSpecVanish();

    public CTOnOff getOMath();

    public boolean isSetOMath();

    public void setOMath(CTOnOff var1);

    public CTOnOff addNewOMath();

    public void unsetOMath();

    public CTParaRPrChange getRPrChange();

    public boolean isSetRPrChange();

    public void setRPrChange(CTParaRPrChange var1);

    public CTParaRPrChange addNewRPrChange();

    public void unsetRPrChange();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTParaRPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTParaRPr newInstance() {
            return (CTParaRPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTParaRPr newInstance(XmlOptions xmlOptions) {
            return (CTParaRPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTParaRPr parse(String string) throws XmlException {
            return (CTParaRPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTParaRPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTParaRPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTParaRPr parse(File file) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTParaRPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTParaRPr parse(URL uRL) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTParaRPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTParaRPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTParaRPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTParaRPr parse(Reader reader) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTParaRPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTParaRPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTParaRPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTParaRPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTParaRPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTParaRPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTParaRPr parse(Node node) throws XmlException {
            return (CTParaRPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTParaRPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTParaRPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTParaRPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTParaRPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTParaRPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTParaRPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, null);
        }

        @Deprecated
        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return Factory.getTypeLoader().newValidatingXMLInputStream(xMLInputStream, type, xmlOptions);
        }

        private Factory() {
        }
    }
}

