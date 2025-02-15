/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.presentationml.x2006.main;

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

public interface STSlideId
extends XmlUnsignedInt {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STSlideId.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stslideida0b3type");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STSlideId newValue(Object object) {
            return (STSlideId)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STSlideId.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STSlideId newInstance() {
            return (STSlideId)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STSlideId newInstance(XmlOptions xmlOptions) {
            return (STSlideId)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STSlideId parse(String string) throws XmlException {
            return (STSlideId)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STSlideId parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STSlideId)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STSlideId parse(File file) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STSlideId parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STSlideId parse(URL uRL) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STSlideId parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STSlideId parse(InputStream inputStream) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STSlideId parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STSlideId parse(Reader reader) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STSlideId parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideId)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STSlideId parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STSlideId)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STSlideId parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STSlideId)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STSlideId parse(Node node) throws XmlException {
            return (STSlideId)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STSlideId parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STSlideId)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STSlideId parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STSlideId)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STSlideId parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STSlideId)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

