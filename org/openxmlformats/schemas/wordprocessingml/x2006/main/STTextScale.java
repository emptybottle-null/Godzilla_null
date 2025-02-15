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
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STTextScale
extends XmlInteger {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STTextScale.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("sttextscalea465type");

    public int getIntValue();

    public void setIntValue(int var1);

    @Deprecated
    public int intValue();

    @Deprecated
    public void set(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STTextScale newValue(Object object) {
            return (STTextScale)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STTextScale.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STTextScale newInstance() {
            return (STTextScale)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STTextScale newInstance(XmlOptions xmlOptions) {
            return (STTextScale)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STTextScale parse(String string) throws XmlException {
            return (STTextScale)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STTextScale parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STTextScale)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STTextScale parse(File file) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STTextScale parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STTextScale parse(URL uRL) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STTextScale parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STTextScale parse(InputStream inputStream) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STTextScale parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STTextScale parse(Reader reader) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STTextScale parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STTextScale)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STTextScale parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STTextScale)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STTextScale parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STTextScale)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STTextScale parse(Node node) throws XmlException {
            return (STTextScale)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STTextScale parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STTextScale)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STTextScale parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STTextScale)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STTextScale parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STTextScale)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

