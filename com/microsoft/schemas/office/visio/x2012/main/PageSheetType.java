/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.SheetType;
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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface PageSheetType
extends SheetType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(PageSheetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("pagesheettype679btype");

    public String getUniqueID();

    public XmlString xgetUniqueID();

    public boolean isSetUniqueID();

    public void setUniqueID(String var1);

    public void xsetUniqueID(XmlString var1);

    public void unsetUniqueID();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(PageSheetType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static PageSheetType newInstance() {
            return (PageSheetType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static PageSheetType newInstance(XmlOptions xmlOptions) {
            return (PageSheetType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static PageSheetType parse(String string) throws XmlException {
            return (PageSheetType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static PageSheetType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (PageSheetType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static PageSheetType parse(File file) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static PageSheetType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static PageSheetType parse(URL uRL) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static PageSheetType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static PageSheetType parse(InputStream inputStream) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static PageSheetType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static PageSheetType parse(Reader reader) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static PageSheetType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PageSheetType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static PageSheetType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (PageSheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static PageSheetType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (PageSheetType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static PageSheetType parse(Node node) throws XmlException {
            return (PageSheetType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static PageSheetType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (PageSheetType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static PageSheetType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (PageSheetType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static PageSheetType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (PageSheetType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

