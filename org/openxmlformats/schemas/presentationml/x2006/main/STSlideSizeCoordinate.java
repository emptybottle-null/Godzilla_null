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
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveCoordinate32;
import org.w3c.dom.Node;

public interface STSlideSizeCoordinate
extends STPositiveCoordinate32 {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STSlideSizeCoordinate.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stslidesizecoordinate24b5type");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STSlideSizeCoordinate newValue(Object object) {
            return (STSlideSizeCoordinate)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STSlideSizeCoordinate.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STSlideSizeCoordinate newInstance() {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STSlideSizeCoordinate newInstance(XmlOptions xmlOptions) {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(String string) throws XmlException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STSlideSizeCoordinate parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(File file) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STSlideSizeCoordinate parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(URL uRL) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STSlideSizeCoordinate parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(InputStream inputStream) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STSlideSizeCoordinate parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(Reader reader) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STSlideSizeCoordinate parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STSlideSizeCoordinate parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STSlideSizeCoordinate parse(Node node) throws XmlException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STSlideSizeCoordinate parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STSlideSizeCoordinate parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STSlideSizeCoordinate parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STSlideSizeCoordinate)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

