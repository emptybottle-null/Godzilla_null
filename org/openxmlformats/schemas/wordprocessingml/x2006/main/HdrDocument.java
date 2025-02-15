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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.w3c.dom.Node;

public interface HdrDocument
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(HdrDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("hdra530doctype");

    public CTHdrFtr getHdr();

    public void setHdr(CTHdrFtr var1);

    public CTHdrFtr addNewHdr();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(HdrDocument.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static HdrDocument newInstance() {
            return (HdrDocument)Factory.getTypeLoader().newInstance(type, null);
        }

        public static HdrDocument newInstance(XmlOptions xmlOptions) {
            return (HdrDocument)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static HdrDocument parse(String string) throws XmlException {
            return (HdrDocument)Factory.getTypeLoader().parse(string, type, null);
        }

        public static HdrDocument parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (HdrDocument)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static HdrDocument parse(File file) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(file, type, null);
        }

        public static HdrDocument parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static HdrDocument parse(URL uRL) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static HdrDocument parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static HdrDocument parse(InputStream inputStream) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static HdrDocument parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static HdrDocument parse(Reader reader) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static HdrDocument parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (HdrDocument)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static HdrDocument parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (HdrDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static HdrDocument parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (HdrDocument)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static HdrDocument parse(Node node) throws XmlException {
            return (HdrDocument)Factory.getTypeLoader().parse(node, type, null);
        }

        public static HdrDocument parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (HdrDocument)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static HdrDocument parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (HdrDocument)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static HdrDocument parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (HdrDocument)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

