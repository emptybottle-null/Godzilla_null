/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.officeDocument.x2006.docPropsVTypes;

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

public interface STClsid
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STClsid.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stclsida7datype");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STClsid newValue(Object object) {
            return (STClsid)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STClsid.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STClsid newInstance() {
            return (STClsid)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STClsid newInstance(XmlOptions xmlOptions) {
            return (STClsid)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STClsid parse(String string) throws XmlException {
            return (STClsid)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STClsid parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STClsid)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STClsid parse(File file) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STClsid parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STClsid parse(URL uRL) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STClsid parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STClsid parse(InputStream inputStream) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STClsid parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STClsid parse(Reader reader) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STClsid parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STClsid)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STClsid parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STClsid)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STClsid parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STClsid)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STClsid parse(Node node) throws XmlException {
            return (STClsid)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STClsid parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STClsid)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STClsid parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STClsid)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STClsid parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STClsid)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

