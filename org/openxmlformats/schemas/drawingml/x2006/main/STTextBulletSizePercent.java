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

public interface STTextBulletSizePercent
extends STPercentage {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextBulletSizePercent.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextbulletsizepercentb516type");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextBulletSizePercent newValue(Object object) {
            return (STTextBulletSizePercent)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextBulletSizePercent.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextBulletSizePercent newInstance() {
            return (STTextBulletSizePercent)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextBulletSizePercent newInstance(XmlOptions xmlOptions) {
            return (STTextBulletSizePercent)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(String string) throws XmlException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextBulletSizePercent parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(File file) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextBulletSizePercent parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(URL uRL) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextBulletSizePercent parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextBulletSizePercent parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(Reader reader) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextBulletSizePercent parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextBulletSizePercent parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextBulletSizePercent parse(Node node) throws XmlException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextBulletSizePercent parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextBulletSizePercent parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextBulletSizePercent parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextBulletSizePercent)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

