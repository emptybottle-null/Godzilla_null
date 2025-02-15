/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.wordprocessingml.x2006.main;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.w3c.dom.Node;

public interface CTTabs
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTabs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttabsa2aatype");

    public List<CTTabStop> getTabList();

    @Deprecated
    public CTTabStop[] getTabArray();

    public CTTabStop getTabArray(int var1);

    public int sizeOfTabArray();

    public void setTabArray(CTTabStop[] var1);

    public void setTabArray(int var1, CTTabStop var2);

    public CTTabStop insertNewTab(int var1);

    public CTTabStop addNewTab();

    public void removeTab(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTabs.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTabs newInstance() {
            return (CTTabs)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTabs newInstance(XmlOptions xmlOptions) {
            return (CTTabs)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTabs parse(String string) throws XmlException {
            return (CTTabs)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTabs parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTabs)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTabs parse(File file) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTabs parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTabs parse(URL uRL) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTabs parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTabs parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTabs parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTabs parse(Reader reader) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTabs parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTabs)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTabs parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTabs)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTabs parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTabs)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTabs parse(Node node) throws XmlException {
            return (CTTabs)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTabs parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTabs)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTabs parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTabs)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTabs parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTabs)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

