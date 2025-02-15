/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.keyEncryptor.certificate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.ref.SoftReference;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CTCertificateKeyEncryptor
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTCertificateKeyEncryptor.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctcertificatekeyencryptor1a80type");

    public byte[] getEncryptedKeyValue();

    public XmlBase64Binary xgetEncryptedKeyValue();

    public void setEncryptedKeyValue(byte[] var1);

    public void xsetEncryptedKeyValue(XmlBase64Binary var1);

    public byte[] getX509Certificate();

    public XmlBase64Binary xgetX509Certificate();

    public void setX509Certificate(byte[] var1);

    public void xsetX509Certificate(XmlBase64Binary var1);

    public byte[] getCertVerifier();

    public XmlBase64Binary xgetCertVerifier();

    public void setCertVerifier(byte[] var1);

    public void xsetCertVerifier(XmlBase64Binary var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTCertificateKeyEncryptor.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTCertificateKeyEncryptor newInstance() {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTCertificateKeyEncryptor newInstance(XmlOptions xmlOptions) {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(String string) throws XmlException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTCertificateKeyEncryptor parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(File file) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTCertificateKeyEncryptor parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(URL uRL) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTCertificateKeyEncryptor parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(InputStream inputStream) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTCertificateKeyEncryptor parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(Reader reader) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTCertificateKeyEncryptor parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTCertificateKeyEncryptor parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTCertificateKeyEncryptor parse(Node node) throws XmlException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTCertificateKeyEncryptor parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTCertificateKeyEncryptor parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTCertificateKeyEncryptor parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTCertificateKeyEncryptor)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

