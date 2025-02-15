/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.spreadsheetml.x2006.main;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalLink;
import org.w3c.dom.Node;

public interface ExternalLinkDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(ExternalLinkDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("externallinkb4c2doctype");

    public CTExternalLink getExternalLink();

    public void setExternalLink(CTExternalLink var1);

    public CTExternalLink addNewExternalLink();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(ExternalLinkDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static ExternalLinkDocument newInstance() {
            return (ExternalLinkDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static ExternalLinkDocument newInstance(XmlOptions xmlOptions) {
            return (ExternalLinkDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static ExternalLinkDocument parse(String string) throws XmlException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static ExternalLinkDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static ExternalLinkDocument parse(File file) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static ExternalLinkDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static ExternalLinkDocument parse(URL uRL) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static ExternalLinkDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static ExternalLinkDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static ExternalLinkDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static ExternalLinkDocument parse(Reader reader) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static ExternalLinkDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static ExternalLinkDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static ExternalLinkDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static ExternalLinkDocument parse(Node node) throws XmlException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static ExternalLinkDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static ExternalLinkDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static ExternalLinkDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (ExternalLinkDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

