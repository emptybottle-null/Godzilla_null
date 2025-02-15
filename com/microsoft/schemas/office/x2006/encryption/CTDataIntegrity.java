/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.encryption;

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

public interface CTDataIntegrity
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CTDataIntegrity.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("ctdataintegrity6eb5type");

    public byte[] getEncryptedHmacKey();

    public XmlBase64Binary xgetEncryptedHmacKey();

    public void setEncryptedHmacKey(byte[] var1);

    public void xsetEncryptedHmacKey(XmlBase64Binary var1);

    public byte[] getEncryptedHmacValue();

    public XmlBase64Binary xgetEncryptedHmacValue();

    public void setEncryptedHmacValue(byte[] var1);

    public void xsetEncryptedHmacValue(XmlBase64Binary var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CTDataIntegrity.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CTDataIntegrity newInstance() {
            return (CTDataIntegrity)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CTDataIntegrity newInstance(XmlOptions xmlOptions) {
            return (CTDataIntegrity)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CTDataIntegrity parse(String string) throws XmlException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CTDataIntegrity parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CTDataIntegrity parse(File file) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CTDataIntegrity parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CTDataIntegrity parse(URL uRL) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CTDataIntegrity parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CTDataIntegrity parse(InputStream inputStream) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CTDataIntegrity parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CTDataIntegrity parse(Reader reader) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CTDataIntegrity parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CTDataIntegrity parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CTDataIntegrity parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CTDataIntegrity parse(Node node) throws XmlException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CTDataIntegrity parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CTDataIntegrity parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CTDataIntegrity parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CTDataIntegrity)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

