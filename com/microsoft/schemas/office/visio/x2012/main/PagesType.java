/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.visio.x2012.main;

import com.microsoft.schemas.office.visio.x2012.main.PageType;
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

public interface PagesType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(PagesType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("pagestypef2e7type");

    public List<PageType> getPageList();

    @Deprecated
    public PageType[] getPageArray();

    public PageType getPageArray(int var1);

    public int sizeOfPageArray();

    public void setPageArray(PageType[] var1);

    public void setPageArray(int var1, PageType var2);

    public PageType insertNewPage(int var1);

    public PageType addNewPage();

    public void removePage(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(PagesType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static PagesType newInstance() {
            return (PagesType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static PagesType newInstance(XmlOptions xmlOptions) {
            return (PagesType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static PagesType parse(String string) throws XmlException {
            return (PagesType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static PagesType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (PagesType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static PagesType parse(File file) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static PagesType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static PagesType parse(URL uRL) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static PagesType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static PagesType parse(InputStream inputStream) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static PagesType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static PagesType parse(Reader reader) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static PagesType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (PagesType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static PagesType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (PagesType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static PagesType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (PagesType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static PagesType parse(Node node) throws XmlException {
            return (PagesType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static PagesType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (PagesType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static PagesType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (PagesType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static PagesType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (PagesType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

