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
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.etsi.uri.x01903.v13.CRLIdentifierType;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3c.dom.Node;

public interface CRLRefType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CRLRefType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("crlreftype4444type");

    public DigestAlgAndValueType getDigestAlgAndValue();

    public void setDigestAlgAndValue(DigestAlgAndValueType var1);

    public DigestAlgAndValueType addNewDigestAlgAndValue();

    public CRLIdentifierType getCRLIdentifier();

    public boolean isSetCRLIdentifier();

    public void setCRLIdentifier(CRLIdentifierType var1);

    public CRLIdentifierType addNewCRLIdentifier();

    public void unsetCRLIdentifier();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(CRLRefType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static CRLRefType newInstance() {
            return (CRLRefType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static CRLRefType newInstance(XmlOptions xmlOptions) {
            return (CRLRefType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static CRLRefType parse(String string) throws XmlException {
            return (CRLRefType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static CRLRefType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (CRLRefType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static CRLRefType parse(File file) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static CRLRefType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static CRLRefType parse(URL uRL) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static CRLRefType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static CRLRefType parse(InputStream inputStream) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static CRLRefType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static CRLRefType parse(Reader reader) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static CRLRefType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (CRLRefType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static CRLRefType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (CRLRefType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static CRLRefType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (CRLRefType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static CRLRefType parse(Node node) throws XmlException {
            return (CRLRefType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static CRLRefType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (CRLRefType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static CRLRefType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (CRLRefType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static CRLRefType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (CRLRefType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

