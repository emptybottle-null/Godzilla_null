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
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.w3c.dom.Node;

public interface CTDocumentBase
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDocumentBase.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctdocumentbasedf5ctype");

    public CTBackground getBackground();

    public boolean isSetBackground();

    public void setBackground(CTBackground var1);

    public CTBackground addNewBackground();

    public void unsetBackground();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDocumentBase.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDocumentBase newInstance() {
            return (CTDocumentBase)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDocumentBase newInstance(XmlOptions xmlOptions) {
            return (CTDocumentBase)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDocumentBase parse(String string) throws XmlException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDocumentBase parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDocumentBase parse(File file) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDocumentBase parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDocumentBase parse(URL uRL) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDocumentBase parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDocumentBase parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDocumentBase parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDocumentBase parse(Reader reader) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDocumentBase parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDocumentBase parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDocumentBase parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDocumentBase parse(Node node) throws XmlException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDocumentBase parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDocumentBase parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDocumentBase parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDocumentBase)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

