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
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface STSqref
extends XmlAnySimpleType {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STSqref.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sD023D6490046BA0250A839A9AD24C443").resolveHandle("stsqrefb044type");

    public List getListValue();

    public List xgetListValue();

    public void setListValue(List var1);

    @Deprecated
    public List listValue();

    @Deprecated
    public List xlistValue();

    @Deprecated
    public void set(List var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STSqref newValue(Object object) {
            return (STSqref)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STSqref.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STSqref newInstance() {
            return (STSqref)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STSqref newInstance(XmlOptions xmlOptions) {
            return (STSqref)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STSqref parse(String string) throws XmlException {
            return (STSqref)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STSqref parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STSqref)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STSqref parse(File file) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STSqref parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STSqref parse(URL uRL) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STSqref parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STSqref parse(InputStream inputStream) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STSqref parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STSqref parse(Reader reader) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STSqref parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSqref)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STSqref parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STSqref)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STSqref parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STSqref)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STSqref parse(Node node) throws XmlException {
            return (STSqref)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STSqref parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STSqref)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STSqref parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STSqref)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STSqref parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STSqref)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

