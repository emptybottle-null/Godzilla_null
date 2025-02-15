/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletColorFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizeFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizePoint;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletTypefaceFollowText;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextIndentLevelType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextMargin;
import org.w3c.dom.Node;

public interface CTTextParagraphProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTextParagraphProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttextparagraphpropertiesdd05type");

    public CTTextSpacing getLnSpc();

    public boolean isSetLnSpc();

    public void setLnSpc(CTTextSpacing var1);

    public CTTextSpacing addNewLnSpc();

    public void unsetLnSpc();

    public CTTextSpacing getSpcBef();

    public boolean isSetSpcBef();

    public void setSpcBef(CTTextSpacing var1);

    public CTTextSpacing addNewSpcBef();

    public void unsetSpcBef();

    public CTTextSpacing getSpcAft();

    public boolean isSetSpcAft();

    public void setSpcAft(CTTextSpacing var1);

    public CTTextSpacing addNewSpcAft();

    public void unsetSpcAft();

    public CTTextBulletColorFollowText getBuClrTx();

    public boolean isSetBuClrTx();

    public void setBuClrTx(CTTextBulletColorFollowText var1);

    public CTTextBulletColorFollowText addNewBuClrTx();

    public void unsetBuClrTx();

    public CTColor getBuClr();

    public boolean isSetBuClr();

    public void setBuClr(CTColor var1);

    public CTColor addNewBuClr();

    public void unsetBuClr();

    public CTTextBulletSizeFollowText getBuSzTx();

    public boolean isSetBuSzTx();

    public void setBuSzTx(CTTextBulletSizeFollowText var1);

    public CTTextBulletSizeFollowText addNewBuSzTx();

    public void unsetBuSzTx();

    public CTTextBulletSizePercent getBuSzPct();

    public boolean isSetBuSzPct();

    public void setBuSzPct(CTTextBulletSizePercent var1);

    public CTTextBulletSizePercent addNewBuSzPct();

    public void unsetBuSzPct();

    public CTTextBulletSizePoint getBuSzPts();

    public boolean isSetBuSzPts();

    public void setBuSzPts(CTTextBulletSizePoint var1);

    public CTTextBulletSizePoint addNewBuSzPts();

    public void unsetBuSzPts();

    public CTTextBulletTypefaceFollowText getBuFontTx();

    public boolean isSetBuFontTx();

    public void setBuFontTx(CTTextBulletTypefaceFollowText var1);

    public CTTextBulletTypefaceFollowText addNewBuFontTx();

    public void unsetBuFontTx();

    public CTTextFont getBuFont();

    public boolean isSetBuFont();

    public void setBuFont(CTTextFont var1);

    public CTTextFont addNewBuFont();

    public void unsetBuFont();

    public CTTextNoBullet getBuNone();

    public boolean isSetBuNone();

    public void setBuNone(CTTextNoBullet var1);

    public CTTextNoBullet addNewBuNone();

    public void unsetBuNone();

    public CTTextAutonumberBullet getBuAutoNum();

    public boolean isSetBuAutoNum();

    public void setBuAutoNum(CTTextAutonumberBullet var1);

    public CTTextAutonumberBullet addNewBuAutoNum();

    public void unsetBuAutoNum();

    public CTTextCharBullet getBuChar();

    public boolean isSetBuChar();

    public void setBuChar(CTTextCharBullet var1);

    public CTTextCharBullet addNewBuChar();

    public void unsetBuChar();

    public CTTextBlipBullet getBuBlip();

    public boolean isSetBuBlip();

    public void setBuBlip(CTTextBlipBullet var1);

    public CTTextBlipBullet addNewBuBlip();

    public void unsetBuBlip();

    public CTTextTabStopList getTabLst();

    public boolean isSetTabLst();

    public void setTabLst(CTTextTabStopList var1);

    public CTTextTabStopList addNewTabLst();

    public void unsetTabLst();

    public CTTextCharacterProperties getDefRPr();

    public boolean isSetDefRPr();

    public void setDefRPr(CTTextCharacterProperties var1);

    public CTTextCharacterProperties addNewDefRPr();

    public void unsetDefRPr();

    public CTOfficeArtExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTOfficeArtExtensionList var1);

    public CTOfficeArtExtensionList addNewExtLst();

    public void unsetExtLst();

    public int getMarL();

    public STTextMargin xgetMarL();

    public boolean isSetMarL();

    public void setMarL(int var1);

    public void xsetMarL(STTextMargin var1);

    public void unsetMarL();

    public int getMarR();

    public STTextMargin xgetMarR();

    public boolean isSetMarR();

    public void setMarR(int var1);

    public void xsetMarR(STTextMargin var1);

    public void unsetMarR();

    public int getLvl();

    public STTextIndentLevelType xgetLvl();

    public boolean isSetLvl();

    public void setLvl(int var1);

    public void xsetLvl(STTextIndentLevelType var1);

    public void unsetLvl();

    public int getIndent();

    public STTextIndent xgetIndent();

    public boolean isSetIndent();

    public void setIndent(int var1);

    public void xsetIndent(STTextIndent var1);

    public void unsetIndent();

    public STTextAlignType.Enum getAlgn();

    public STTextAlignType xgetAlgn();

    public boolean isSetAlgn();

    public void setAlgn(STTextAlignType.Enum var1);

    public void xsetAlgn(STTextAlignType var1);

    public void unsetAlgn();

    public int getDefTabSz();

    public STCoordinate32 xgetDefTabSz();

    public boolean isSetDefTabSz();

    public void setDefTabSz(int var1);

    public void xsetDefTabSz(STCoordinate32 var1);

    public void unsetDefTabSz();

    public boolean getRtl();

    public XmlBoolean xgetRtl();

    public boolean isSetRtl();

    public void setRtl(boolean var1);

    public void xsetRtl(XmlBoolean var1);

    public void unsetRtl();

    public boolean getEaLnBrk();

    public XmlBoolean xgetEaLnBrk();

    public boolean isSetEaLnBrk();

    public void setEaLnBrk(boolean var1);

    public void xsetEaLnBrk(XmlBoolean var1);

    public void unsetEaLnBrk();

    public STTextFontAlignType.Enum getFontAlgn();

    public STTextFontAlignType xgetFontAlgn();

    public boolean isSetFontAlgn();

    public void setFontAlgn(STTextFontAlignType.Enum var1);

    public void xsetFontAlgn(STTextFontAlignType var1);

    public void unsetFontAlgn();

    public boolean getLatinLnBrk();

    public XmlBoolean xgetLatinLnBrk();

    public boolean isSetLatinLnBrk();

    public void setLatinLnBrk(boolean var1);

    public void xsetLatinLnBrk(XmlBoolean var1);

    public void unsetLatinLnBrk();

    public boolean getHangingPunct();

    public XmlBoolean xgetHangingPunct();

    public boolean isSetHangingPunct();

    public void setHangingPunct(boolean var1);

    public void xsetHangingPunct(XmlBoolean var1);

    public void unsetHangingPunct();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTextParagraphProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTextParagraphProperties newInstance() {
            return (CTTextParagraphProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTextParagraphProperties newInstance(XmlOptions xmlOptions) {
            return (CTTextParagraphProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(String string) throws XmlException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTextParagraphProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(File file) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTextParagraphProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(URL uRL) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTextParagraphProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTextParagraphProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(Reader reader) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTextParagraphProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTextParagraphProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTextParagraphProperties parse(Node node) throws XmlException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTextParagraphProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTextParagraphProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTextParagraphProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTextParagraphProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

