/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.officeDocument.x2006.extendedProperties;

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
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTDigSigBlob;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorLpstr;
import org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTVectorVariant;
import org.w3c.dom.Node;

public interface CTProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctproperties3f10type");

    public String getTemplate();

    public XmlString xgetTemplate();

    public boolean isSetTemplate();

    public void setTemplate(String var1);

    public void xsetTemplate(XmlString var1);

    public void unsetTemplate();

    public String getManager();

    public XmlString xgetManager();

    public boolean isSetManager();

    public void setManager(String var1);

    public void xsetManager(XmlString var1);

    public void unsetManager();

    public String getCompany();

    public XmlString xgetCompany();

    public boolean isSetCompany();

    public void setCompany(String var1);

    public void xsetCompany(XmlString var1);

    public void unsetCompany();

    public int getPages();

    public XmlInt xgetPages();

    public boolean isSetPages();

    public void setPages(int var1);

    public void xsetPages(XmlInt var1);

    public void unsetPages();

    public int getWords();

    public XmlInt xgetWords();

    public boolean isSetWords();

    public void setWords(int var1);

    public void xsetWords(XmlInt var1);

    public void unsetWords();

    public int getCharacters();

    public XmlInt xgetCharacters();

    public boolean isSetCharacters();

    public void setCharacters(int var1);

    public void xsetCharacters(XmlInt var1);

    public void unsetCharacters();

    public String getPresentationFormat();

    public XmlString xgetPresentationFormat();

    public boolean isSetPresentationFormat();

    public void setPresentationFormat(String var1);

    public void xsetPresentationFormat(XmlString var1);

    public void unsetPresentationFormat();

    public int getLines();

    public XmlInt xgetLines();

    public boolean isSetLines();

    public void setLines(int var1);

    public void xsetLines(XmlInt var1);

    public void unsetLines();

    public int getParagraphs();

    public XmlInt xgetParagraphs();

    public boolean isSetParagraphs();

    public void setParagraphs(int var1);

    public void xsetParagraphs(XmlInt var1);

    public void unsetParagraphs();

    public int getSlides();

    public XmlInt xgetSlides();

    public boolean isSetSlides();

    public void setSlides(int var1);

    public void xsetSlides(XmlInt var1);

    public void unsetSlides();

    public int getNotes();

    public XmlInt xgetNotes();

    public boolean isSetNotes();

    public void setNotes(int var1);

    public void xsetNotes(XmlInt var1);

    public void unsetNotes();

    public int getTotalTime();

    public XmlInt xgetTotalTime();

    public boolean isSetTotalTime();

    public void setTotalTime(int var1);

    public void xsetTotalTime(XmlInt var1);

    public void unsetTotalTime();

    public int getHiddenSlides();

    public XmlInt xgetHiddenSlides();

    public boolean isSetHiddenSlides();

    public void setHiddenSlides(int var1);

    public void xsetHiddenSlides(XmlInt var1);

    public void unsetHiddenSlides();

    public int getMMClips();

    public XmlInt xgetMMClips();

    public boolean isSetMMClips();

    public void setMMClips(int var1);

    public void xsetMMClips(XmlInt var1);

    public void unsetMMClips();

    public boolean getScaleCrop();

    public XmlBoolean xgetScaleCrop();

    public boolean isSetScaleCrop();

    public void setScaleCrop(boolean var1);

    public void xsetScaleCrop(XmlBoolean var1);

    public void unsetScaleCrop();

    public CTVectorVariant getHeadingPairs();

    public boolean isSetHeadingPairs();

    public void setHeadingPairs(CTVectorVariant var1);

    public CTVectorVariant addNewHeadingPairs();

    public void unsetHeadingPairs();

    public CTVectorLpstr getTitlesOfParts();

    public boolean isSetTitlesOfParts();

    public void setTitlesOfParts(CTVectorLpstr var1);

    public CTVectorLpstr addNewTitlesOfParts();

    public void unsetTitlesOfParts();

    public boolean getLinksUpToDate();

    public XmlBoolean xgetLinksUpToDate();

    public boolean isSetLinksUpToDate();

    public void setLinksUpToDate(boolean var1);

    public void xsetLinksUpToDate(XmlBoolean var1);

    public void unsetLinksUpToDate();

    public int getCharactersWithSpaces();

    public XmlInt xgetCharactersWithSpaces();

    public boolean isSetCharactersWithSpaces();

    public void setCharactersWithSpaces(int var1);

    public void xsetCharactersWithSpaces(XmlInt var1);

    public void unsetCharactersWithSpaces();

    public boolean getSharedDoc();

    public XmlBoolean xgetSharedDoc();

    public boolean isSetSharedDoc();

    public void setSharedDoc(boolean var1);

    public void xsetSharedDoc(XmlBoolean var1);

    public void unsetSharedDoc();

    public String getHyperlinkBase();

    public XmlString xgetHyperlinkBase();

    public boolean isSetHyperlinkBase();

    public void setHyperlinkBase(String var1);

    public void xsetHyperlinkBase(XmlString var1);

    public void unsetHyperlinkBase();

    public CTVectorVariant getHLinks();

    public boolean isSetHLinks();

    public void setHLinks(CTVectorVariant var1);

    public CTVectorVariant addNewHLinks();

    public void unsetHLinks();

    public boolean getHyperlinksChanged();

    public XmlBoolean xgetHyperlinksChanged();

    public boolean isSetHyperlinksChanged();

    public void setHyperlinksChanged(boolean var1);

    public void xsetHyperlinksChanged(XmlBoolean var1);

    public void unsetHyperlinksChanged();

    public CTDigSigBlob getDigSig();

    public boolean isSetDigSig();

    public void setDigSig(CTDigSigBlob var1);

    public CTDigSigBlob addNewDigSig();

    public void unsetDigSig();

    public String getApplication();

    public XmlString xgetApplication();

    public boolean isSetApplication();

    public void setApplication(String var1);

    public void xsetApplication(XmlString var1);

    public void unsetApplication();

    public String getAppVersion();

    public XmlString xgetAppVersion();

    public boolean isSetAppVersion();

    public void setAppVersion(String var1);

    public void xsetAppVersion(XmlString var1);

    public void unsetAppVersion();

    public int getDocSecurity();

    public XmlInt xgetDocSecurity();

    public boolean isSetDocSecurity();

    public void setDocSecurity(int var1);

    public void xsetDocSecurity(XmlInt var1);

    public void unsetDocSecurity();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTProperties newInstance() {
            return (CTProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTProperties newInstance(XmlOptions xmlOptions) {
            return (CTProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTProperties parse(String string) throws XmlException {
            return (CTProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTProperties parse(File file) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTProperties parse(URL uRL) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTProperties parse(Reader reader) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTProperties parse(Node node) throws XmlException {
            return (CTProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

