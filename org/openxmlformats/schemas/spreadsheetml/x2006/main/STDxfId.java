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
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STDxfId
extends XmlUnsignedInt {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STDxfId.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stdxfid9fdctype");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STDxfId newValue(Object object) {
            return (STDxfId)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STDxfId.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STDxfId newInstance() {
            return (STDxfId)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STDxfId newInstance(XmlOptions xmlOptions) {
            return (STDxfId)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STDxfId parse(String string) throws XmlException {
            return (STDxfId)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STDxfId parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STDxfId)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STDxfId parse(File file) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STDxfId parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STDxfId parse(URL uRL) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STDxfId parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STDxfId parse(InputStream inputStream) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STDxfId parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STDxfId parse(Reader reader) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STDxfId parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STDxfId)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STDxfId parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STDxfId)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STDxfId parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STDxfId)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STDxfId parse(Node node) throws XmlException {
            return (STDxfId)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STDxfId parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STDxfId)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STDxfId parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STDxfId)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STDxfId parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STDxfId)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

