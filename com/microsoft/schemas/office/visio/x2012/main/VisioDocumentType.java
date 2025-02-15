/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.microsoft.schemas.office.visio.x2012.main.ColorsType
 *  com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType
 *  com.microsoft.schemas.office.visio.x2012.main.EventListType
 *  com.microsoft.schemas.office.visio.x2012.main.FaceNamesType
 *  com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType
 *  com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.ColorsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.DocumentSheetType;
import com.microsoft.schemas.office.visio.x2012.main.EventListType;
import com.microsoft.schemas.office.visio.x2012.main.FaceNamesType;
import com.microsoft.schemas.office.visio.x2012.main.HeaderFooterType;
import com.microsoft.schemas.office.visio.x2012.main.PublishSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetsType;
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
import org.w3c.dom.Node;

public interface VisioDocumentType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(VisioDocumentType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("visiodocumenttypebfcatype");

    public DocumentSettingsType getDocumentSettings();

    public boolean isSetDocumentSettings();

    public void setDocumentSettings(DocumentSettingsType var1);

    public DocumentSettingsType addNewDocumentSettings();

    public void unsetDocumentSettings();

    public ColorsType getColors();

    public boolean isSetColors();

    public void setColors(ColorsType var1);

    public ColorsType addNewColors();

    public void unsetColors();

    public FaceNamesType getFaceNames();

    public boolean isSetFaceNames();

    public void setFaceNames(FaceNamesType var1);

    public FaceNamesType addNewFaceNames();

    public void unsetFaceNames();

    public StyleSheetsType getStyleSheets();

    public boolean isSetStyleSheets();

    public void setStyleSheets(StyleSheetsType var1);

    public StyleSheetsType addNewStyleSheets();

    public void unsetStyleSheets();

    public DocumentSheetType getDocumentSheet();

    public boolean isSetDocumentSheet();

    public void setDocumentSheet(DocumentSheetType var1);

    public DocumentSheetType addNewDocumentSheet();

    public void unsetDocumentSheet();

    public EventListType getEventList();

    public boolean isSetEventList();

    public void setEventList(EventListType var1);

    public EventListType addNewEventList();

    public void unsetEventList();

    public HeaderFooterType getHeaderFooter();

    public boolean isSetHeaderFooter();

    public void setHeaderFooter(HeaderFooterType var1);

    public HeaderFooterType addNewHeaderFooter();

    public void unsetHeaderFooter();

    public PublishSettingsType getPublishSettings();

    public boolean isSetPublishSettings();

    public void setPublishSettings(PublishSettingsType var1);

    public PublishSettingsType addNewPublishSettings();

    public void unsetPublishSettings();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(VisioDocumentType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static VisioDocumentType newInstance() {
            return (VisioDocumentType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static VisioDocumentType newInstance(XmlOptions xmlOptions) {
            return (VisioDocumentType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static VisioDocumentType parse(String string) throws XmlException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static VisioDocumentType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static VisioDocumentType parse(File file) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static VisioDocumentType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static VisioDocumentType parse(URL uRL) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static VisioDocumentType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static VisioDocumentType parse(InputStream inputStream) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static VisioDocumentType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static VisioDocumentType parse(Reader reader) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static VisioDocumentType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static VisioDocumentType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static VisioDocumentType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static VisioDocumentType parse(Node node) throws XmlException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static VisioDocumentType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static VisioDocumentType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static VisioDocumentType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (VisioDocumentType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

