/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.etsi.uri.x01903.v13;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3.x2000.x09.xmldsig.X509IssuerSerialType;
import org.w3c.dom.Node;

public interface CertIDType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CertIDType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("certidtypee64dtype");

    public DigestAlgAndValueType getCertDigest();

    public void setCertDigest(DigestAlgAndValueType var1);

    public DigestAlgAndValueType addNewCertDigest();

    public X509IssuerSerialType getIssuerSerial();

    public void setIssuerSerial(X509IssuerSerialType var1);

    public X509IssuerSerialType addNewIssuerSerial();

    public String getURI();

    public XmlAnyURI xgetURI();

    public boolean isSetURI();

    public void setURI(String var1);

    public void xsetURI(XmlAnyURI var1);

    public void unsetURI();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CertIDType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CertIDType newInstance() {
            return (CertIDType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CertIDType newInstance(XmlOptions xmlOptions) {
            return (CertIDType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CertIDType parse(String string) throws XmlException {
            return (CertIDType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CertIDType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CertIDType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CertIDType parse(File file) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CertIDType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CertIDType parse(URL uRL) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CertIDType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CertIDType parse(InputStream inputStream) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CertIDType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CertIDType parse(Reader reader) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CertIDType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CertIDType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CertIDType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CertIDType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CertIDType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CertIDType parse(Node node) throws XmlException {
            return (CertIDType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CertIDType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CertIDType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CertIDType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CertIDType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CertIDType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CertIDType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

