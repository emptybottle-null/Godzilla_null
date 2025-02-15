/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.StyleSheetType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.List;
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

public interface StyleSheetsType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(StyleSheetsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stylesheetstypeb706type");

    public List<StyleSheetType> getStyleSheetList();

    @Deprecated
    public StyleSheetType[] getStyleSheetArray();

    public StyleSheetType getStyleSheetArray(int var1);

    public int sizeOfStyleSheetArray();

    public void setStyleSheetArray(StyleSheetType[] var1);

    public void setStyleSheetArray(int var1, StyleSheetType var2);

    public StyleSheetType insertNewStyleSheet(int var1);

    public StyleSheetType addNewStyleSheet();

    public void removeStyleSheet(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(StyleSheetsType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static StyleSheetsType newInstance() {
            return (StyleSheetsType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static StyleSheetsType newInstance(XmlOptions xmlOptions) {
            return (StyleSheetsType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static StyleSheetsType parse(String string) throws XmlException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static StyleSheetsType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static StyleSheetsType parse(File file) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static StyleSheetsType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static StyleSheetsType parse(URL uRL) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static StyleSheetsType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static StyleSheetsType parse(InputStream inputStream) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static StyleSheetsType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static StyleSheetsType parse(Reader reader) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static StyleSheetsType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static StyleSheetsType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static StyleSheetsType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static StyleSheetsType parse(Node node) throws XmlException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static StyleSheetsType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static StyleSheetsType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static StyleSheetsType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (StyleSheetsType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

