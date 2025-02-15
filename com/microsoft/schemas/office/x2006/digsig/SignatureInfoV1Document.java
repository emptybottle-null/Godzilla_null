/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.microsoft.schemas.office.x2006.digsig;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
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
import org.w3c.dom.Node;

public interface SignatureInfoV1Document
extends XmlObject {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(SignatureInfoV1Document.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s8C3F193EE11A2F798ACF65489B9E6078").resolveHandle("signatureinfov14a6bdoctype");

    public CTSignatureInfoV1 getSignatureInfoV1();

    public void setSignatureInfoV1(CTSignatureInfoV1 var1);

    public CTSignatureInfoV1 addNewSignatureInfoV1();

    public static final class Factory {
        private static SoftReference<SchemaTypeLoader> typeLoader;

        private static synchronized SchemaTypeLoader getTypeLoader() {
            SchemaTypeLoader schemaTypeLoader;
            SchemaTypeLoader schemaTypeLoader2 = schemaTypeLoader = typeLoader == null ? null : typeLoader.get();
            if (schemaTypeLoader == null) {
                schemaTypeLoader = XmlBeans.typeLoaderForClassLoader(SignatureInfoV1Document.class.getClassLoader());
                typeLoader = new SoftReference<SchemaTypeLoader>(schemaTypeLoader);
            }
            return schemaTypeLoader;
        }

        public static SignatureInfoV1Document newInstance() {
            return (SignatureInfoV1Document)Factory.getTypeLoader().newInstance(type, null);
        }

        public static SignatureInfoV1Document newInstance(XmlOptions xmlOptions) {
            return (SignatureInfoV1Document)Factory.getTypeLoader().newInstance(type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(String string) throws XmlException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(string, type, null);
        }

        public static SignatureInfoV1Document parse(String string, XmlOptions xmlOptions) throws XmlException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(string, type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(File file) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(file, type, null);
        }

        public static SignatureInfoV1Document parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(file, type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(URL uRL) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(uRL, type, null);
        }

        public static SignatureInfoV1Document parse(URL uRL, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(uRL, type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(InputStream inputStream) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(inputStream, type, null);
        }

        public static SignatureInfoV1Document parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(inputStream, type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(Reader reader) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(reader, type, null);
        }

        public static SignatureInfoV1Document parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(reader, type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(xMLStreamReader, type, null);
        }

        public static SignatureInfoV1Document parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(xMLStreamReader, type, xmlOptions);
        }

        public static SignatureInfoV1Document parse(Node node) throws XmlException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(node, type, null);
        }

        public static SignatureInfoV1Document parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(node, type, xmlOptions);
        }

        @Deprecated
        public static SignatureInfoV1Document parse(XMLInputStream xMLInputStream) throws XmlException, XMLStreamException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(xMLInputStream, type, null);
        }

        @Deprecated
        public static SignatureInfoV1Document parse(XMLInputStream xMLInputStream, XmlOptions xmlOptions) throws XmlException, XMLStreamException {
            return (SignatureInfoV1Document)Factory.getTypeLoader().parse(xMLInputStream, type, xmlOptions);
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

