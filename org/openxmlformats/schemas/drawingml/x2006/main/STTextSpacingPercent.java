/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.openxmlformats.schemas.drawingml.x2006.main;

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
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.w3c.dom.Node;

public interface STTextSpacingPercent
extends STPercentage {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextSpacingPercent.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextspacingpercentde3atype");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextSpacingPercent newValue(Object object) {
            return (STTextSpacingPercent)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextSpacingPercent.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextSpacingPercent newInstance() {
            return (STTextSpacingPercent)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextSpacingPercent newInstance(XmlOptions xmlOptions) {
            return (STTextSpacingPercent)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextSpacingPercent parse(String string) throws XmlException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextSpacingPercent parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextSpacingPercent parse(File file) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextSpacingPercent parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextSpacingPercent parse(URL uRL) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextSpacingPercent parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextSpacingPercent parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextSpacingPercent parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextSpacingPercent parse(Reader reader) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextSpacingPercent parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextSpacingPercent parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextSpacingPercent parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextSpacingPercent parse(Node node) throws XmlException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextSpacingPercent parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextSpacingPercent parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextSpacingPercent parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextSpacingPercent)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

