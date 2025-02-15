/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTKinsoku
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTModifyVerifier
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum
 *  org.openxmlformats.schemas.presentationml.x2006.main.CTSmartTags
 *  org.openxmlformats.schemas.presentationml.x2006.main.STBookmarkIdSeed
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomShowList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCustomerDataList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTHandoutMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTKinsoku;
import org.openxmlformats.schemas.presentationml.x2006.main.CTModifyVerifier;
import org.openxmlformats.schemas.presentationml.x2006.main.CTNotesMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPhotoAlbum;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideMasterIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideSize;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSmartTags;
import org.openxmlformats.schemas.presentationml.x2006.main.STBookmarkIdSeed;
import org.w3c.dom.Node;

public interface CTPresentation
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTPresentation.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctpresentation56cbtype");

    public CTSlideMasterIdList getSldMasterIdLst();

    public boolean isSetSldMasterIdLst();

    public void setSldMasterIdLst(CTSlideMasterIdList var1);

    public CTSlideMasterIdList addNewSldMasterIdLst();

    public void unsetSldMasterIdLst();

    public CTNotesMasterIdList getNotesMasterIdLst();

    public boolean isSetNotesMasterIdLst();

    public void setNotesMasterIdLst(CTNotesMasterIdList var1);

    public CTNotesMasterIdList addNewNotesMasterIdLst();

    public void unsetNotesMasterIdLst();

    public CTHandoutMasterIdList getHandoutMasterIdLst();

    public boolean isSetHandoutMasterIdLst();

    public void setHandoutMasterIdLst(CTHandoutMasterIdList var1);

    public CTHandoutMasterIdList addNewHandoutMasterIdLst();

    public void unsetHandoutMasterIdLst();

    public CTSlideIdList getSldIdLst();

    public boolean isSetSldIdLst();

    public void setSldIdLst(CTSlideIdList var1);

    public CTSlideIdList addNewSldIdLst();

    public void unsetSldIdLst();

    public CTSlideSize getSldSz();

    public boolean isSetSldSz();

    public void setSldSz(CTSlideSize var1);

    public CTSlideSize addNewSldSz();

    public void unsetSldSz();

    public CTPositiveSize2D getNotesSz();

    public void setNotesSz(CTPositiveSize2D var1);

    public CTPositiveSize2D addNewNotesSz();

    public CTSmartTags getSmartTags();

    public boolean isSetSmartTags();

    public void setSmartTags(CTSmartTags var1);

    public CTSmartTags addNewSmartTags();

    public void unsetSmartTags();

    public CTEmbeddedFontList getEmbeddedFontLst();

    public boolean isSetEmbeddedFontLst();

    public void setEmbeddedFontLst(CTEmbeddedFontList var1);

    public CTEmbeddedFontList addNewEmbeddedFontLst();

    public void unsetEmbeddedFontLst();

    public CTCustomShowList getCustShowLst();

    public boolean isSetCustShowLst();

    public void setCustShowLst(CTCustomShowList var1);

    public CTCustomShowList addNewCustShowLst();

    public void unsetCustShowLst();

    public CTPhotoAlbum getPhotoAlbum();

    public boolean isSetPhotoAlbum();

    public void setPhotoAlbum(CTPhotoAlbum var1);

    public CTPhotoAlbum addNewPhotoAlbum();

    public void unsetPhotoAlbum();

    public CTCustomerDataList getCustDataLst();

    public boolean isSetCustDataLst();

    public void setCustDataLst(CTCustomerDataList var1);

    public CTCustomerDataList addNewCustDataLst();

    public void unsetCustDataLst();

    public CTKinsoku getKinsoku();

    public boolean isSetKinsoku();

    public void setKinsoku(CTKinsoku var1);

    public CTKinsoku addNewKinsoku();

    public void unsetKinsoku();

    public CTTextListStyle getDefaultTextStyle();

    public boolean isSetDefaultTextStyle();

    public void setDefaultTextStyle(CTTextListStyle var1);

    public CTTextListStyle addNewDefaultTextStyle();

    public void unsetDefaultTextStyle();

    public CTModifyVerifier getModifyVerifier();

    public boolean isSetModifyVerifier();

    public void setModifyVerifier(CTModifyVerifier var1);

    public CTModifyVerifier addNewModifyVerifier();

    public void unsetModifyVerifier();

    public CTExtensionList getExtLst();

    public boolean isSetExtLst();

    public void setExtLst(CTExtensionList var1);

    public CTExtensionList addNewExtLst();

    public void unsetExtLst();

    public int getServerZoom();

    public STPercentage xgetServerZoom();

    public boolean isSetServerZoom();

    public void setServerZoom(int var1);

    public void xsetServerZoom(STPercentage var1);

    public void unsetServerZoom();

    public int getFirstSlideNum();

    public XmlInt xgetFirstSlideNum();

    public boolean isSetFirstSlideNum();

    public void setFirstSlideNum(int var1);

    public void xsetFirstSlideNum(XmlInt var1);

    public void unsetFirstSlideNum();

    public boolean getShowSpecialPlsOnTitleSld();

    public XmlBoolean xgetShowSpecialPlsOnTitleSld();

    public boolean isSetShowSpecialPlsOnTitleSld();

    public void setShowSpecialPlsOnTitleSld(boolean var1);

    public void xsetShowSpecialPlsOnTitleSld(XmlBoolean var1);

    public void unsetShowSpecialPlsOnTitleSld();

    public boolean getRtl();

    public XmlBoolean xgetRtl();

    public boolean isSetRtl();

    public void setRtl(boolean var1);

    public void xsetRtl(XmlBoolean var1);

    public void unsetRtl();

    public boolean getRemovePersonalInfoOnSave();

    public XmlBoolean xgetRemovePersonalInfoOnSave();

    public boolean isSetRemovePersonalInfoOnSave();

    public void setRemovePersonalInfoOnSave(boolean var1);

    public void xsetRemovePersonalInfoOnSave(XmlBoolean var1);

    public void unsetRemovePersonalInfoOnSave();

    public boolean getCompatMode();

    public XmlBoolean xgetCompatMode();

    public boolean isSetCompatMode();

    public void setCompatMode(boolean var1);

    public void xsetCompatMode(XmlBoolean var1);

    public void unsetCompatMode();

    public boolean getStrictFirstAndLastChars();

    public XmlBoolean xgetStrictFirstAndLastChars();

    public boolean isSetStrictFirstAndLastChars();

    public void setStrictFirstAndLastChars(boolean var1);

    public void xsetStrictFirstAndLastChars(XmlBoolean var1);

    public void unsetStrictFirstAndLastChars();

    public boolean getEmbedTrueTypeFonts();

    public XmlBoolean xgetEmbedTrueTypeFonts();

    public boolean isSetEmbedTrueTypeFonts();

    public void setEmbedTrueTypeFonts(boolean var1);

    public void xsetEmbedTrueTypeFonts(XmlBoolean var1);

    public void unsetEmbedTrueTypeFonts();

    public boolean getSaveSubsetFonts();

    public XmlBoolean xgetSaveSubsetFonts();

    public boolean isSetSaveSubsetFonts();

    public void setSaveSubsetFonts(boolean var1);

    public void xsetSaveSubsetFonts(XmlBoolean var1);

    public void unsetSaveSubsetFonts();

    public boolean getAutoCompressPictures();

    public XmlBoolean xgetAutoCompressPictures();

    public boolean isSetAutoCompressPictures();

    public void setAutoCompressPictures(boolean var1);

    public void xsetAutoCompressPictures(XmlBoolean var1);

    public void unsetAutoCompressPictures();

    public long getBookmarkIdSeed();

    public STBookmarkIdSeed xgetBookmarkIdSeed();

    public boolean isSetBookmarkIdSeed();

    public void setBookmarkIdSeed(long var1);

    public void xsetBookmarkIdSeed(STBookmarkIdSeed var1);

    public void unsetBookmarkIdSeed();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTPresentation.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTPresentation newInstance() {
            return (CTPresentation)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTPresentation newInstance(XmlOptions xmlOptions) {
            return (CTPresentation)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTPresentation parse(String string) throws XmlException {
            return (CTPresentation)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTPresentation parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTPresentation)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTPresentation parse(File file) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTPresentation parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTPresentation parse(URL uRL) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTPresentation parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTPresentation parse(InputStream inputStream) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTPresentation parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTPresentation parse(Reader reader) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTPresentation parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTPresentation)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTPresentation parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTPresentation)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTPresentation parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTPresentation)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTPresentation parse(Node node) throws XmlException {
            return (CTPresentation)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTPresentation parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTPresentation)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTPresentation parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTPresentation)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTPresentation parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTPresentation)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

