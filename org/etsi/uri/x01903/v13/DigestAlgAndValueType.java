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
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;
import org.w3c.dom.Node;

public interface DigestAlgAndValueType
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(DigestAlgAndValueType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("digestalgandvaluetype234etype");

    public DigestMethodType getDigestMethod();

    public void setDigestMethod(DigestMethodType var1);

    public DigestMethodType addNewDigestMethod();

    public byte[] getDigestValue();

    public DigestValueType xgetDigestValue();

    public void setDigestValue(byte[] var1);

    public void xsetDigestValue(DigestValueType var1);

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(DigestAlgAndValueType.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static DigestAlgAndValueType newInstance() {
            return (DigestAlgAndValueType)Factory.getTypeLoader().newInstance(type, null);
        }

        public static DigestAlgAndValueType newInstance(XmlOptions xmlOptions) {
            return (DigestAlgAndValueType)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(String string) throws XmlException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(string, type, null);
        }

        public static DigestAlgAndValueType parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(File file) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(file, type, null);
        }

        public static DigestAlgAndValueType parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(URL uRL) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static DigestAlgAndValueType parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(InputStream inputStream) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static DigestAlgAndValueType parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(Reader reader) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static DigestAlgAndValueType parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static DigestAlgAndValueType parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static DigestAlgAndValueType parse(Node node) throws XmlException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(node, type, null);
        }

        public static DigestAlgAndValueType parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static DigestAlgAndValueType parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static DigestAlgAndValueType parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (DigestAlgAndValueType)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

