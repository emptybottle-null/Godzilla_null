/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrChange
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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrInner;
import org.w3c.dom.Node;

public interface CTTcPr
extends CTTcPrInner {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTTcPr.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("cttcpree37type");

    public CTTcPrChange getTcPrChange();

    public boolean isSetTcPrChange();

    public void setTcPrChange(CTTcPrChange var1);

    public CTTcPrChange addNewTcPrChange();

    public void unsetTcPrChange();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTTcPr.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTTcPr newInstance() {
            return (CTTcPr)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTTcPr newInstance(XmlOptions xmlOptions) {
            return (CTTcPr)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTTcPr parse(String string) throws XmlException {
            return (CTTcPr)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTTcPr parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPr)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTTcPr parse(File file) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTTcPr parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTTcPr parse(URL uRL) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTTcPr parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTTcPr parse(InputStream inputStream) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTTcPr parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTTcPr parse(Reader reader) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTTcPr parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTTcPr)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTTcPr parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTTcPr)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTTcPr parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPr)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTTcPr parse(Node node) throws XmlException {
            return (CTTcPr)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTTcPr parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTTcPr)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTTcPr parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTTcPr)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTTcPr parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTTcPr)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

