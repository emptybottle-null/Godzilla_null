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
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.w3c.dom.Node;

public interface CTStretchInfoProperties
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTStretchInfoProperties.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("ctstretchinfopropertiesde57type");

    public CTRelativeRect getFillRect();

    public boolean isSetFillRect();

    public void setFillRect(CTRelativeRect var1);

    public CTRelativeRect addNewFillRect();

    public void unsetFillRect();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTStretchInfoProperties.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTStretchInfoProperties newInstance() {
            return (CTStretchInfoProperties)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTStretchInfoProperties newInstance(XmlOptions xmlOptions) {
            return (CTStretchInfoProperties)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(String string) throws XmlException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTStretchInfoProperties parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(File file) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTStretchInfoProperties parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(URL uRL) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTStretchInfoProperties parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(InputStream inputStream) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTStretchInfoProperties parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(Reader reader) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTStretchInfoProperties parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTStretchInfoProperties parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTStretchInfoProperties parse(Node node) throws XmlException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTStretchInfoProperties parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTStretchInfoProperties parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTStretchInfoProperties parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTStretchInfoProperties)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

