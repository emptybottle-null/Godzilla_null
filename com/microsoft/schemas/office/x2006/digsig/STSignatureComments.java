/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.digsig;

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

public interface STSignatureComments
extends XmlString {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(STSignatureComments.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("stsignaturecomments47batype");

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        public static STSignatureComments newValue(Object object) {
            return (STSignatureComments)type.newValue(object);
        }

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(STSignatureComments.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static STSignatureComments newInstance() {
            return (STSignatureComments)Factory.getTypeLoader().newInstance(type, null);
        }

        public static STSignatureComments newInstance(XmlOptions xmlOptions) {
            return (STSignatureComments)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static STSignatureComments parse(String string) throws XmlException {
            return (STSignatureComments)Factory.getTypeLoader().parse(string, type, null);
        }

        public static STSignatureComments parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (STSignatureComments)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static STSignatureComments parse(File file) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(file, type, null);
        }

        public static STSignatureComments parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static STSignatureComments parse(URL uRL) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static STSignatureComments parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static STSignatureComments parse(InputStream inputStream) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static STSignatureComments parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static STSignatureComments parse(Reader reader) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static STSignatureComments parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (STSignatureComments)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static STSignatureComments parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (STSignatureComments)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static STSignatureComments parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (STSignatureComments)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static STSignatureComments parse(Node node) throws XmlException {
            return (STSignatureComments)Factory.getTypeLoader().parse(node, type, null);
        }

        public static STSignatureComments parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (STSignatureComments)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static STSignatureComments parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (STSignatureComments)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static STSignatureComments parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (STSignatureComments)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

