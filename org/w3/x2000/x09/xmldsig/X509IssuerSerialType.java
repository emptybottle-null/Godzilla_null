/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.w3.x2000.x09.xmldsig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.math.BigInteger;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface X509IssuerSerialType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(X509IssuerSerialType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("x509issuerserialtype7eb2type");

    public String getX509IssuerName();

    public XmlString xgetX509IssuerName();

    public void setX509IssuerName(String var1);

    public void xsetX509IssuerName(XmlString var1);

    public BigInteger getX509SerialNumber();

    public XmlInteger xgetX509SerialNumber();

    public void setX509SerialNumber(BigInteger var1);

    public void xsetX509SerialNumber(XmlInteger var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(X509IssuerSerialType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static X509IssuerSerialType newInstance() {
            return (X509IssuerSerialType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static X509IssuerSerialType newInstance(XmlOptions xmlOptions) {
            return (X509IssuerSerialType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static X509IssuerSerialType parse(String string) throws XmlException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static X509IssuerSerialType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static X509IssuerSerialType parse(File file) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static X509IssuerSerialType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static X509IssuerSerialType parse(URL uRL) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static X509IssuerSerialType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static X509IssuerSerialType parse(InputStream inputStream) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static X509IssuerSerialType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static X509IssuerSerialType parse(Reader reader) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static X509IssuerSerialType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static X509IssuerSerialType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static X509IssuerSerialType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static X509IssuerSerialType parse(Node node) throws XmlException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static X509IssuerSerialType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static X509IssuerSerialType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static X509IssuerSerialType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (X509IssuerSerialType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

