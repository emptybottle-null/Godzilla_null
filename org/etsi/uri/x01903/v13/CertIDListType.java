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
import java.util.List;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.CertIDType;
import org.w3c.dom.Node;

public interface CertIDListType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CertIDListType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("certidlisttype488btype");

    public List<CertIDType> getCertList();

    @Deprecated
    public CertIDType[] getCertArray();

    public CertIDType getCertArray(int var1);

    public int sizeOfCertArray();

    public void setCertArray(CertIDType[] var1);

    public void setCertArray(int var1, CertIDType var2);

    public CertIDType insertNewCert(int var1);

    public CertIDType addNewCert();

    public void removeCert(int var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CertIDListType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CertIDListType newInstance() {
            return (CertIDListType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CertIDListType newInstance(XmlOptions xmlOptions) {
            return (CertIDListType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CertIDListType parse(String string) throws XmlException {
            return (CertIDListType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CertIDListType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CertIDListType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CertIDListType parse(File file) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CertIDListType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CertIDListType parse(URL uRL) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CertIDListType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CertIDListType parse(InputStream inputStream) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CertIDListType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CertIDListType parse(Reader reader) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CertIDListType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CertIDListType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CertIDListType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CertIDListType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CertIDListType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CertIDListType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CertIDListType parse(Node node) throws XmlException {
            return (CertIDListType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CertIDListType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CertIDListType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CertIDListType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CertIDListType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CertIDListType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CertIDListType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

